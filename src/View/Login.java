package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieGoerController;
import Controller.AdminController;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;
import Model.User;
import Model.Admin;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Seat;

/**
 * Login Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public class Login extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    User user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    Cineplex cineplex = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    ArrayList<Seat> bookedSeats = new ArrayList<>();

    /**
     * Constructor to store previous page, access level, moviegoer,
     * movieSession, cinema, cineplex, ticket, transaction, bookedSeats
     * 
     * @param previousMenu  the previous page
     * @param accesslevel   the level of access
     * @param moviegoer     movieGoer Object
     * @param movie         the selected movie
     * @param movieSession  the selected movieSession
     * @param cinema        the selected cinema
     * @param cineplex      the selected cineplex
     * @param ticket        the array of ticket(s) booked
     * @param transaction   the transaction made
     * @param bookedSeats   the array of seat(s) booked
     */
    public Login(BaseMenu previousMenu, int accesslevel, User user, Movie movie,
            MovieSession movieSession, Cinema cinema, Cineplex cineplex, ArrayList<Ticket> ticket, Transaction transaction, ArrayList<Seat> bookedSeats) {
        super(previousMenu, accesslevel);

        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.ticket = ticket;
        this.transaction = transaction;
        this.bookedSeats = bookedSeats;
    }

    /**
     * Main Login Functionality
     * Centralised Login Page that will access relevant page depending
     * on the email address of the user (MovieGoer or Admin)
     * 
     * @return to the relevant page of the user's role or Display Transaction page
     * @see DisplayTransaction, MovieGoerMainMenu, AdminMainMenu
     */
    @Override
    public BaseMenu execute() {
        /**
         * User's email, password, and all user related Classes
         */
        String email;
        String password;
        MovieGoer user;
        Admin admin;

        System.out.println(
                ConsoleColours.WHITE_BRIGHT + "Please Login using your Email & Password" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

        do {
            System.out.print(ConsoleColours.WHITE_BOLD + "Email:" + ConsoleColours.RESET);
            email = sc.nextLine().toLowerCase();

            if (email.isBlank()) {
                break;
            }

            System.out.print(ConsoleColours.WHITE_BOLD + "Password:" + ConsoleColours.RESET);
            password = sc.nextLine();

            if (password.isBlank()) {
                break;
            }

            // Checks if Email in database
            user = MovieGoerController.readByEmail(email);
            admin = AdminController.readByEmail(email);

            // Email does not exist in database. Return error message
            if (user == null && admin == null) {
                System.out.println(ConsoleColours.RED + "Email does not exist." + ConsoleColours.RESET);
                System.out.println(ConsoleColours.WHITE_BOLD + "Re-enter your credentials or create an account"
                        + ConsoleColours.RESET);
            }
            // Email exists, but does not match password in database. Return error message
            else if ((user != null && !(user.getPassword().equals(password)))
                    || (admin != null && !(admin.getPassword().equals(password)))) {
                // System.out.println(user.getPassword());
                // System.out.println(admin.getPassword());
                System.out.println(ConsoleColours.RED + "Wrong email or Password." + ConsoleColours.RESET);
                System.out.println(ConsoleColours.WHITE_BOLD + "Reenter your credentials" + ConsoleColours.RESET);
            }
            // Correct Email & Password will direct user to relevant page
            else {
                System.out.println(ConsoleColours.GREEN + "You've successfully logged in!" + ConsoleColours.RESET);
                System.out.println();
                // moviegoer page
                if (admin == null) {
                    if (ticket != null) return new DisplayTransaction(null, 0, user, movie, movieSession, cinema, ticket, transaction, cineplex, bookedSeats);
                    return new MovieGoerMainMenu(this, 0, user, movie, movieSession, cinema, cineplex, ticket, transaction, bookedSeats);
                }
                // admin page
                if (user == null) {
                    return new AdminMainMenu(this, 1, admin);
                }
            }
        } while (!(email.isBlank() || password.isBlank()));
        return this.getPreviousMenu();
        // return new MainMenu(null, -1);
    }
}
