package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

public class ChooseMovie extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    
    MovieGoer user;
    Movie movie;
    MovieSession movieSession;
    Cinema cinema;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction;
    Cineplex cineplex;

    public ChooseMovie(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
    MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction, Cineplex cineplex) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.cineplex = cineplex;
    }

    @Override
    public BaseMenu execute() {
        int choice;
        String numregex = "^(?!(0))[0-4]{1}$";
        do{
            System.out.println(ConsoleColours.PURPLE_BOLD + "Movie Selection:" + ConsoleColours.RESET);
            System.out.println("1. View Movie List");
            System.out.println("2. Choose a Movie");
            System.out.println(ConsoleColours.YELLOW + "3. Logout" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);

            BaseMenu nextMenu = this;

            //keep asking for choice
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
            String choicestr = sc.nextLine();
            System.out.println();

            while (!choicestr.matches(numregex)) {
                //early termination
                if(choicestr.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choicestr = sc.nextLine();
                System.out.println();
            }

            choice = Integer.valueOf(choicestr);


            switch (choice) {
                //VIEW MOVIE LIST
                case 1:
                    
                    break;
                //CHOOSE MOVIE
                case 2:
                    
                    break;
                //GO TO PREVIOUS PAGE
                case 3:
                    nextMenu = this.getPreviousMenu();
                    return nextMenu;
                //QUIT
                case 4:
                    nextMenu = new Quit(this);
                    return nextMenu;
                default:
                    choice = -1;
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }while(true);
    }
}
