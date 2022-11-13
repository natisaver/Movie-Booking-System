package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Seat;
import Model.Ticket;
import Model.Transaction;

/**
 * MovieGoer Main Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public class MovieGoerMainMenu extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    /**
     * Current User
     */
    MovieGoer moviegoer = null;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    Cineplex cineplex = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    ArrayList<Seat> bookedSeats;

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
    public MovieGoerMainMenu(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer, Movie movie,
            MovieSession movieSession, Cinema cinema, Cineplex cineplex, ArrayList<Ticket> ticket, Transaction transaction, ArrayList<Seat> bookedSeats) {
        super(previousMenu, accesslevel);
        this.moviegoer = moviegoer;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.ticket = ticket;
        this.transaction = transaction;
        this.bookedSeats = bookedSeats;
    }

    /**
     * Moviegoer Main Menu Functionality
     * Moviegoers can Book Ticket, Leave Review, Update/Delete Review, Check History,
     * User can also choose to go Back, Logout or Quit
     * 
     * @return Selected Page or Terminates
     * @see ChooseCineplex
     * @see CreateOrLogin
     * @see LeaveReview
     * @see UpdateReview
     * @see CheckHistory
     * @see ViewMovieDetails
     * @see MainMenu
     */
    @Override
    public BaseMenu execute() {
        int choice;
        String numregex = "^(?!(0))[0-8]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Customer Menu Options:" + ConsoleColours.RESET);
        System.out.println("1. Book ticket");
        System.out.println("2. Leave a review");
        System.out.println("3. Update/Delete a review");
        System.out.println("4. Check booking history");
        System.out.println("5. Check Movies & Details");
        System.out.println("6. Top 5 Movies List");
        if (this.moviegoer == null) {
            System.out.println(ConsoleColours.YELLOW + "7. Back" + ConsoleColours.RESET);
        } else {
            System.out.println(ConsoleColours.YELLOW + "7. Logout" + ConsoleColours.RESET);
        }
        System.out.println(ConsoleColours.RED + "8. Quit" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

        BaseMenu nextMenu = this;

        // keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();
        System.out.println();

        while (!choicestr.matches(numregex)) {
            // early termination
            if (choicestr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            System.out.println();
        }

        choice = Integer.valueOf(choicestr);

        switch (choice) {
            case 1:
                nextMenu = new ChooseCineplex(this, -1, moviegoer, movie, movieSession, cinema, ticket, transaction);
                break;
            case 2:
                if (moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1, null, null, null, null, null, ticket, null, this.bookedSeats);
                } 
                else {
                    System.out.println(ConsoleColours.WHITE_BOLD + "You can leave a review" + ConsoleColours.RESET);
                    nextMenu = new LeaveReview(this, 0, moviegoer);
                }
                break;
            case 3:
                if (moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1, null, null, null, null, null, ticket, null, this.bookedSeats);
                } 
                else {
                    System.out.println(ConsoleColours.WHITE_BOLD + "You can leave a review" + ConsoleColours.RESET);
                    nextMenu = new UpdateReview(this, 0, moviegoer);
                }
            break;
            case 4:
                if (this.moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1, null, null, null, null, null, ticket, null, this.bookedSeats);
                } else {
                    nextMenu = new CheckHistory(this, 0, moviegoer, movie, movieSession, cinema, cineplex, ticket, transaction, null);
                }
                break;
            case 5:
                nextMenu = new ViewMovieDetails(nextMenu, 0);
                break;
            case 6:
                nextMenu = new ViewTopFive(nextMenu, 0);
                break;
            case 7:
                nextMenu = new MainMenu(null, -1, null, movie, movieSession, cinema, cineplex, ticket, transaction, this.bookedSeats);
                break;
            case 8:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println(ConsoleColours.RED + "Going Back" + ConsoleColours.RESET);
                break;
        }

        return nextMenu;

    }
}
