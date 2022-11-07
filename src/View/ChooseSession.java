package View;

import java.util.ArrayList;
import java.util.HashMap;
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
        int choice;

        // Display list of Movie ShowDate and ShowTime, filtered by choosen movieTitle
        // and movieType
        System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Session:" + ConsoleColours.RESET);
        ArrayList<MovieSession> movieSessionArr = MovieSessionController.readByTitle("Black Adam");
        Map<Integer, MovieSession> hashMapSession = new HashMap();
        for (int j = 0; j < movieSessionArr.size(); j++) {
            int k = j + 1;
            hashMapSession.put(k - 1, movieSessionArr.get(k - 1));
            System.out.println(
                    k + ": " + hashMapSession.get(k - 1).getTitle() + " "
                            + hashMapSession.get(k - 1).getSessionDate() + " "
                            + hashMapSession.get(k - 1).getSessionTime()
                            + " | (" + hashMapSession.get(k - 1).getMovieType().name() + ")");
        }
        System.out.println(ConsoleColours.YELLOW + (movieSessionArr.size() + 1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (movieSessionArr.size() + 2) + ". Quit" + ConsoleColours.RESET);

        String numregexSession = "^(?!(0))[0-9]|1[0-9]|2[0-9]$";

        // keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);

        String choiceStr = sc.next();
        while (!choiceStr.matches(numregexSession)
                || ((Integer.valueOf(choiceStr) - 1) >= movieSessionArr.size() + 2)) {
            // early termination
            if (choiceStr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" +
                    ConsoleColours.RESET);
            choiceStr = sc.next();
        }
        choice = Integer.valueOf(choiceStr) - 1;

        BaseMenu nextMenu = this;

        if (choice == movieSessionArr.size() + 1) {
            nextMenu = this.getPreviousMenu();
        } else if (choice == movieSessionArr.size() + 2) {
            nextMenu = new Quit(this);
        } else {
            // nextMenu = new ChooseSeat();
            System.out
                    .println(ConsoleColours.GREEN + "Details of selected Movie Session: " + ConsoleColours.RESET);

            System.out.println(hashMapSession.get(choice).getTitle() + " - "
                    + hashMapSession.get(choice).getSessionDate() + " "
                    + hashMapSession.get(choice).getSessionTime());

            movieSession = hashMapSession.get(choice);
            nextMenu = new DisplayTicket(this, accesslevel, this.user, movie, movieSession, cinema, ticket,
                    transaction);
        }

        return nextMenu;

    }
}
