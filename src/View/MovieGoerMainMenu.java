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
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    ArrayList<Seat> bookedSeats;

    /**
     * Constructor to store previous page and access level
     * 
     * @param previousMenu the previous page
     * @param accesslevel  the level of access
     * @param moviegoer    movieGoer Object
     */
    public MovieGoerMainMenu(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction, ArrayList<Seat> bookedSeats) {
        super(previousMenu, accesslevel);
        this.moviegoer = moviegoer;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.bookedSeats = bookedSeats;
    }

    @Override
    public BaseMenu execute() {
        int choice;
        String numregex = "^(?!(0))[0-7]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Customer Menu Options:" + ConsoleColours.RESET);
        System.out.println("1. Book ticket");
        System.out.println("2. Leave a review");
        System.out.println("3. Update/Delete a review");
        System.out.println("4. Check booking history");
        System.out.println("5. Check Movies & Details");
        if (this.moviegoer == null) {
            System.out.println(ConsoleColours.YELLOW + "6. Back" + ConsoleColours.RESET);
        } else {
            System.out.println(ConsoleColours.YELLOW + "6. Logout" + ConsoleColours.RESET);
        }
        System.out.println(ConsoleColours.RED + "7. Quit" + ConsoleColours.RESET);

        BaseMenu nextMenu = this;

        // keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();

        while (!choicestr.matches(numregex)) {
            // early termination
            if (choicestr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
        }

        choice = Integer.valueOf(choicestr);

        switch (choice) {
            case 1:
                nextMenu = new ChooseCineplex(this, -1, moviegoer, movie, movieSession, cinema, ticket, transaction);
                break;
            case 2:
                //MovieGoer user = new MovieGoer("Niki", "niki@123.com", "81234567",  25, "null");
                if (moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1, null, null, null, null, ticket, null, this.bookedSeats);
                } 
                else {
                    System.out.println(ConsoleColours.WHITE_BOLD + "You can leave a review" + ConsoleColours.RESET);
                    nextMenu = new LeaveReview(this, 0, moviegoer);
                }
                break;
            case 3:
                if (moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1, null, null, null, null, ticket, null, this.bookedSeats);
                } 
                else {
                    System.out.println(ConsoleColours.WHITE_BOLD + "You can leave a review" + ConsoleColours.RESET);
                    nextMenu = new UpdateReview(this, 0, moviegoer);
                }
            break;
            case 4:
                if (this.moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1, null, null, null, null, ticket, null, this.bookedSeats);
                } else {
                    nextMenu = new CheckHistory(this, 0, moviegoer, movie, movieSession, cinema, ticket, transaction, null);
                }
                break;
            case 5:
                nextMenu = new ViewMovieDetails(nextMenu, 0);
                break;
            case 6:
                nextMenu = new MainMenu(null, -1, moviegoer, movie, movieSession, cinema, ticket, transaction, this.bookedSeats);
                break;
            case 7:
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
