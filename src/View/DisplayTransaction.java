package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieController;
import Controller.MovieSessionController;
import Controller.TransactionController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Seat;
import Model.Transaction;

/**
 * Display Transaction Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 03-11-2022
 */

public class DisplayTransaction extends BaseMenu {

    Scanner sc = new Scanner(System.in);
    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    Cineplex cineplex = null;
    ArrayList<Seat> bookedSeats;


    /**
     * Constructor to store previous page, level of access, user, movie, movieSession, cinema, cineplex, ticket, transaction, bookedSeats
     * 
     * @param previousMenu  the previous page
     * @param accesslevel   the level of access
     * @param user          the moviegoer that is logged in 
     * @param movie         the selected movie
     * @param movieSession  the selected moviesession
     * @param cinema        the selected cinema
     * @param ticket        the array of ticket(s) needed
     * @param transaction   transaction made
     * @param cineplex      the selected cineplex
     * @param bookedSeats   the array of seat(s) booked 
     */
    public DisplayTransaction(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction,
            Cineplex cineplex, ArrayList<Seat> bookedSeats) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.cineplex = cineplex;
        this.bookedSeats = bookedSeats;
    }

    /**
     * Display Transaction functionality
     * Displays TID, MovieGoer's name, Movie's Cineplex, Movie Title, Movie Session, No. of Tickets, Total Price Paid
     * Updates TicketSales of Movie 
     * Book the seats in the session 
     * 
     * @return MovieGoerMainMenu Page or DisplayTransaction Page if successful. else go back a page upon early
     *         termination
     * @see MovieGoerMainMenu, DisplayTransaction
     */
    @Override
    public BaseMenu execute() {

        if (user == null) {
            return new CreateOrLogin(this, 2, user, movie, movieSession, cinema, cineplex, ticket, transaction, bookedSeats);
        }

        transaction = new Transaction(ticket, user);

        if (TransactionController.create(transaction)) {
            System.out.println(ConsoleColours.GREEN_BOLD + "Payment Successful" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN_BOLD + "Your ticket(s) have been successfully booked!" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.WHITE_BOLD + "Transaction" + ConsoleColours.RESET);
            System.out.println("======================================");
            System.out.println("TID: " + transaction.getTID());
            System.out.println("Moviegoer's Name: " + user.getName());
            System.out.println("Cineplex: Sally Carrera "+ cineplex.getLocation());
            System.out.println("Movie Title: " + movie.getTitle());
            System.out.println("Movie Session: " + movieSession.getSessionDate() + " " + movieSession.getSessionTime());
            System.out.println("No. of Tickets " + ticket.size());
            System.out.println("Price Paid: " + transaction.getTotalPrice());
            MovieController.updateSales(movie, ticket.size());
            MovieSessionController.bookSeats(cinema.getCinemaCode(), movieSession, bookedSeats);
        } else System.out.println(ConsoleColours.RED_BOLD + "Your booking is unsuccessful. Please try again." + ConsoleColours.RESET);

        return new MovieGoerMainMenu(null, accesslevel, user, null, null, null, null, null, null, null);
    }

}
