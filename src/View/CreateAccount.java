package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.AdminController;
import Controller.MovieGoerController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;
import Model.Seat;

/**
 * Create Account Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 03-11-2022
 */

public class CreateAccount extends BaseMenu {

    Scanner sc = new Scanner(System.in);
    private Pattern regexPattern;
    private Matcher regMatcher;

    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    Cineplex cineplex = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    ArrayList<Seat> bookedSeats;

    /**
     * Constructor to store previous page, level of access, user, movie, movieSession, cinema, cineplex, ticket, transaction, bookedSeats
     * 
     * @param previousMenu the previous page
     * @param accesslevel  the level of access
     * @param user         the moviegoer that is logging in 
     * @param movie        the selected movie 
     * @param movieSession the selected movieSession
     * @param cinema       the selected cinema
     * @param cineplex     the selected cineplex
     * @param ticket       the array of ticket(s) needed
     * @param transaction  transaction made
     * @param bookedSeats  the array of seat(s) booked
     */
    public CreateAccount(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
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
     * Create Account functionality
     * Ensures that the email the user tries to enter is unique
     * and not already in the database
     * Also has email, password Validation
     * 
     * @return MovieGoerMainMenu Page or DisplayTransaction Page if successful. else go back a page upon early
     *         termination
     * @see MovieGoerMainMenu
     * @see DisplayTransaction
     */
    @Override
    public BaseMenu execute() {
        /**
         * User's indicated details, and a MovieGoer Class Object to store inputted
         * information.
         */
        String name;
        String email;
        String phoneNo;
        int age;
        String password;
        String confirmPassword;
        MovieGoer user;
        Movie movie = null;
        MovieSession movieSession = null;
        Cinema cinema = null;
        ArrayList<Ticket> ticket = new ArrayList<>();
        Transaction transaction = null;

        System.out.println("Please create an account using your Email & Password");
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

        do {
            do {
                System.out.println("Your Name: ");
                name = sc.nextLine();
                if (name.isBlank()) {
                    return this.getPreviousMenu();
                }
                regexPattern = Pattern.compile("^[^,]+$");
                regMatcher = regexPattern.matcher(name);
                if (regMatcher.matches()) {
                    break;
                }
                System.out.println(ConsoleColours.RED + "Name cannot contain commas" + ConsoleColours.RESET);
                System.out.println("Please Reenter a valid name:");
            } while (!regMatcher.matches());

            System.out.println("Your indicated name is " + name);

            System.out.println("Email: ");
            // make sure email is valid
            do {
                email = sc.nextLine();
                System.out.println("You entered: " + email);
                // early termination
                if (email.isBlank()) {
                    return this.getPreviousMenu();
                }
                regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
                regMatcher = regexPattern.matcher(email);
                // valid email can break
                if (regMatcher.matches() && (MovieGoerController.readByEmail(email.toLowerCase()) == null
                        && AdminController.readByEmail(email.toLowerCase()) == null)) {
                    break;
                }
                System.out.println(ConsoleColours.RED
                        + "Either an account already exists under this email address OR the email is invalid"
                        + ConsoleColours.RESET);
                System.out.println("Please Reenter an Email Address:");

            } while (!regMatcher.matches() || (MovieGoerController.readByEmail(email.toLowerCase()) != null
                    || AdminController.readByEmail(email.toLowerCase()) != null));

            // check for valid phone number
            String numregex = "^(?!(0))[0-9]+$";
            System.out.println("Phone Number: ");
            phoneNo = sc.nextLine();

            while (!phoneNo.matches(numregex)) {
                // early termination
                if (phoneNo.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please key a valid Phone Number:" + ConsoleColours.RESET);
                phoneNo = sc.nextLine();
            }

            // check for valid age
            System.out.println("Age: ");
            String ageString = sc.nextLine();
            while (!ageString.matches(numregex)) {
                // early termination
                if (ageString.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please key a valid Age:" + ConsoleColours.RESET);
                ageString = sc.nextLine();
            }
            age = Integer.parseInt(ageString);

            // check for valid password
            System.out.println(ConsoleColours.CYAN_BRIGHT + "Password must be:");
            System.out.println("At least 3 characters long");
            System.out.println("Contain both alphabets and numbers" + ConsoleColours.RESET);
            System.out.println("Password: ");
            password = sc.nextLine();

            if (password.isBlank()) {
                break;
            }

            // ensure password meets valid requirements
            while (!((password.length() >= 3) && (password.matches(".*[a-z]+.*"))
                    && (password.matches(".*[0-9]+.*")))) {
                System.out.println(ConsoleColours.RED + "Password must follow requirements: " + ConsoleColours.RESET);
                password = sc.nextLine();
                // early termination
                if (password.isBlank()) {
                    return this.getPreviousMenu();
                }
            }

            System.out.println("Confirm Password: ");
            confirmPassword = sc.nextLine();
            if (confirmPassword.isBlank()) {
                break;
            }

            while (!password.equals(confirmPassword)) {
                System.out.println(ConsoleColours.RED + "Password fields does not match" + ConsoleColours.RESET);
                System.out.println("Reenter the password");
                confirmPassword = sc.nextLine();
                // early termination
                if (confirmPassword.isBlank()) {
                    return this.getPreviousMenu();
                }
            }

            // Account Creation was Successful
            user = new MovieGoer(name, email.toLowerCase(), phoneNo, age, password);
            if (MovieGoerController.create(user)) {
                System.out.println(ConsoleColours.GREEN_BOLD + "You've successfully created an account!" + ConsoleColours.RESET);
                if (this.accesslevel == 2)
                    return new DisplayTransaction(null, this.accesslevel, user, this.movie, this.movieSession, this.cinema, this.ticket, this.transaction, this.cineplex, this.bookedSeats);
                else
                    return new MovieGoerMainMenu(this.getPreviousMenu().getPreviousMenu(), 0, user, movie, movieSession,
                            cinema, cineplex, ticket, transaction, bookedSeats);
            } else {
                System.out.println(ConsoleColours.RED_BOLD + "ERROR in account creation" + ConsoleColours.RESET);
                return new MovieGoerMainMenu(this.getPreviousMenu().getPreviousMenu(), -1, null, movie, movieSession,
                        cinema, cineplex, ticket, transaction, bookedSeats);
            }

        } while ((!name.isBlank() && !password.isBlank()));

        return this.getPreviousMenu().getPreviousMenu();
    }

}
