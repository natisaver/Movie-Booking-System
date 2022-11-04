package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.TransactionController;
import Model.Cinema;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;
import Model.User;

public class CheckHistory extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    MovieGoer moviegoer = null;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;

    public CheckHistory(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction) {
        super(previousMenu, accesslevel);
        this.moviegoer = moviegoer;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
    }

    @Override
    public BaseMenu execute() {

        String back;

        System.out.println("Below is the list of past transactions made: ");
        System.out.println("(Enter blank space for both to quit)");

        do {
            // if transaction records exist
            if (TransactionController.readByName(moviegoer.getName()) != null) {

            }
            // if no transaction records exist
            else {
                System.out.println("No Transaction Records found!");
            }

            // type any character to exit
            back = sc.next();

            if (back.isBlank()) {
                System.out.println("password is blank");
                break;
            }

            return new MovieGoerMainMenu(this.getPreviousMenu(), 0, moviegoer, movie, movieSession, cinema, ticket,
                    transaction);

        } while (!back.isBlank());

        return this.getPreviousMenu();

    }

}