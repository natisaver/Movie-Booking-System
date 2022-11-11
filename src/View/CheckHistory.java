package View;

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
import Model.Seat;

public class CheckHistory extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    MovieGoer moviegoer = null;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    Cineplex cineplex = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;
    ArrayList<Seat> bookedSeats;

    public CheckHistory(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer, Movie movie,
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

    @Override
    public BaseMenu execute() {

        //String back;

        System.out.println(ConsoleColours.WHITE_BOLD + "Below is the list of your past transactions: " + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave the field empty to quit)" + ConsoleColours.RESET);

        // if transaction records exist
        if (!TransactionController.readByEmail(moviegoer.getEmail()).isEmpty()) {
            TransactionController.readByEmailPrint(this.moviegoer.getEmail());
            System.out.println();
        }
        // if no transaction records exist
        else {
            System.out.println(ConsoleColours.RED + "No Transaction Records found" + ConsoleColours.RESET);
            System.out.println();
        }
        return this.getPreviousMenu();
    }

}