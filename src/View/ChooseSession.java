package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.MovieSessionController;
import Model.Cinema;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

public class ChooseSession extends BaseMenu {
    Scanner sc = new Scanner(System.in);
    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;

    public ChooseSession(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
    }

    @Override
    public BaseMenu execute() {
        int choice = -1;
        BaseMenu nextMenu = this;

        // Display list of Movie ShowDate and ShowTime, filtered by choosen movieTitle
        // and movieType
        System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Session:" + ConsoleColours.RESET);
        ArrayList<MovieSession> movieSessionArr = MovieSessionController.readByTitle(this.movie.getTitle());
        HashMap<Integer, MovieSession> hashMapSession = new HashMap<Integer, MovieSession>();
        for (int j = 0; j < movieSessionArr.size(); j++) {
            int k = j + 1;
            hashMapSession.put(k - 1, movieSessionArr.get(k - 1));
        }
        hashMapSession = sortByDate(hashMapSession);
        if (movieSessionArr.size() <= 0 ){
            System.out.println("No sessions available");
        }
        else {
            for (int k = 1; k<=movieSessionArr.size(); k++){ 
                System.out.println( 
                        k + ": " + hashMapSession.get(k).getTitle() + " "
                                + hashMapSession.get(k).getSessionDate() + " "
                                + hashMapSession.get(k).getSessionTime()
                                + " | (" + hashMapSession.get(k).getMovieType().name() + ")");
            }
        }

        System.out.println(ConsoleColours.YELLOW + (movieSessionArr.size() + 1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (movieSessionArr.size() + 2) + ". Quit" + ConsoleColours.RESET);
        String numregex = "[0-9]+";
        Boolean isOK = false;
        while (!isOK) {
            System.out.print("Enter the session you want (Integer Value): ");
            String choicestr = sc.nextLine();
            if (!choicestr.matches(numregex)){
                System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                continue;
            }
            if (Integer.valueOf(choicestr) <= (movieSessionArr.size()+2) && Integer.valueOf(choicestr) > 0){
                choice = Integer.valueOf(choicestr);
                isOK = true;
            }
            else {
                System.out.println(ConsoleColours.RED + "Please enter a valid range between 1<=x<=" + (movieSessionArr.size()+2) + ConsoleColours.RESET);
            }
        }

        if (choice == movieSessionArr.size() + 1) {
            nextMenu = this.getPreviousMenu();
        } else if (choice == movieSessionArr.size() + 2) {
            nextMenu = new Quit(this);
        } else {
            // nextMenu = new ChooseSeat();
            System.out
                    .println(ConsoleColours.GREEN + "Details of selected Movie Session: " + ConsoleColours.RESET);
            movieSession = hashMapSession.get(choice);
            System.out.println(movieSession.getTitle() + " - "
                    + movieSession.getSessionDate() + " "
                    + movieSession.getSessionTime()
                    + " | (" + movieSession.getMovieType().name() + ")");

            
            nextMenu = new DisplayTicket(this, accesslevel, this.user, movie, movieSession, cinema, ticket,
                    transaction);
        }

        return nextMenu;

    }

    public static HashMap<Integer, MovieSession> sortByDate(HashMap<Integer, MovieSession> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, MovieSession> > list =
               new LinkedList<Map.Entry<Integer, MovieSession> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, MovieSession> >() {
            public int compare(Map.Entry<Integer, MovieSession> o1,
                               Map.Entry<Integer, MovieSession> o2)
            {
                if(o1.getValue().getShowtime().isBefore(o2.getValue().getShowtime())){
                    return -1;
                }
                else if(o1.getValue().getShowtime().isAfter(o2.getValue().getShowtime())){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Integer, MovieSession> temp = new LinkedHashMap<Integer, MovieSession>();
        int i = 1;
        for (Map.Entry<Integer, MovieSession> aa : list) {
            temp.put(i, aa.getValue());
            i++;
        }
        return temp;
    }
}
