package View;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Cinema;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

/**
 * Intermediary Access Denied Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 03-11-2022
 */
public class CreateOrLogin extends BaseMenu {
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
    public CreateOrLogin(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
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
     * Selection Menu
     * Create Account
     * Login
     * Go Back
     * 
     * @return to the page according to user's selected choice
     */
    @Override
    public BaseMenu execute() {
        int choice;
        System.out.println(ConsoleColours.RED_BOLD + "<<ACCESS REQUIRES LOGIN>>" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.WHITE_BRIGHT + "Would you like to:" + ConsoleColours.RESET);
        System.out.println("1. Create a new account");
        System.out.println("2. Login to an existing account");
        System.out.println(ConsoleColours.YELLOW + "3. Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);

        String inputString;
        String numRegex = "^[^0-9]+$";
        BaseMenu nextMenu = this;

        System.out.print("Enter your choice:");
        inputString = sc.nextLine();

        while (!inputString.matches(numRegex)){
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Title:" + ConsoleColours.RESET);
            inputString = sc.nextLine();
        }
        choice = Integer.parseInt(inputString);
        switch (choice) {
            case 1:
                nextMenu = new CreateAccount(this, this.accesslevel, user, movie, movieSession, cinema, ticket,
                        transaction);
                break;
            case 2:
                nextMenu = new Login(this, -1, user, movie, movieSession, cinema, ticket,
                        transaction);
                break;
            case 3:
                nextMenu = this.getPreviousMenu();
                break;
            default:
                nextMenu = new Quit(this);
                break;

        }
        return nextMenu;
    }
}