package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.TransactionController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

public class DisplayTicket extends BaseMenu {

    Scanner sc = new Scanner(System.in);
    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    Cineplex cineplex = null;

    public DisplayTicket(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
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

        BaseMenu nextMenu = this;

        if (user == null) {
            return new CreateOrLogin(this, 2, user, movie, movieSession, cinema,
                    ticket, transaction);
        }

        // Get current date time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String formatDateTime = now.format(formatter1);

        String TID = cinema.getCinemaCode().concat(formatDateTime);

        transaction = new Transaction(ticket, user);

        if (TransactionController.create(transaction)) {
            System.out.println(
                    ConsoleColours.GREEN_BOLD + "Payment Successful" +
                            ConsoleColours.RESET);
            System.out.println(
                    ConsoleColours.GREEN_BOLD + "Your ticket(s) have been successfully booked!" +
                            ConsoleColours.RESET);
            System.out.println(ConsoleColours.WHITE_BOLD + "Transaction" + ConsoleColours.RESET);
            System.out.println("======================================");
            System.out.println("TID: " + transaction.getTID());
            System.out.println("Moviegoer's Name: " + user.getName());
            System.out.println("Movie Title: " + movie.getTitle());
            System.out.println("Movie Session: " + movieSession.getSessionDate() + " " + movieSession.getSessionTime());
            System.out.println("No. of Tickets " + ticket.size());
            System.out.println("Price Paid: " + transaction.getTotalPrice());
        } else
            System.out.println(ConsoleColours.RED_BOLD + "Your booking is unsuccessful. Please try again."
                    + ConsoleColours.RESET);

        return this.getPreviousMenu();

    }

}
