package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieGoerController;
import Controller.AdminController;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;
import Model.Admin;
import Model.Cinema;
import Model.Movie;

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
    public Login(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
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
     * Main Login Page
     * Depending on the email address of the user, the system will bring them
     * to the relevant page if they are MovieGoer or Admin
     * 
     * @return to the relevant page of the user's role
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
                    return new MovieGoerMainMenu(this, 0, user, movie, movieSession, cinema, ticket, transaction);
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
