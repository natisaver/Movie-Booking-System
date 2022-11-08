package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Cinema;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

/**
 * The starting page that begins the application.
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class MainMenu extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;

    /**
     * Constructor to store previous page and access level
     * 
     * @param previousMenu the previous page
     * @param accesslevel  the level of access
     */
    public MainMenu(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
    }

    /**
     * Main Menu Functionality
     * Access Main Menu for MovieGoer, Admin or Quit
     * 
     * @return Selected Page or Terminates
     */
    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;
        String numregex = "^(?!(0))[0-4]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Movie Booking and Listing Management Application (MOBLIMA)"
                + ConsoleColours.RESET);
        System.out.println("1. Browse");
        System.out.println("2. Login");
        System.out.println("3. Create Account");
        System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);

        // keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();
        System.out.println();

        while (!choicestr.matches(numregex)) {
            // early termination
            if (choicestr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice (without spaces):" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            System.out.println();
        }

        choice = Integer.valueOf(choicestr);

        switch (choice) {
            case 1:
                nextMenu = new MovieGoerMainMenu(this, -1, null, movie, movieSession, cinema, ticket, transaction);
                break;
            case 2:
                nextMenu = new Login(this, -1, user, movie, movieSession, cinema, ticket, transaction);
                break;
            case 3:
                nextMenu = new CreateAccount(this, this.accesslevel, user, movie, movieSession, cinema, ticket,
                        transaction);
                break;
            case 4:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println(ConsoleColours.RED + "Quitting." + ConsoleColours.RESET);
                break;
        }

        return nextMenu;
    }

}
