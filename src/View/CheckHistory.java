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

        System.out.println("Below is the list of your past transactions: ");
        System.out.println(ConsoleColours.GREEN + "(Leave the field empty to quit)" + ConsoleColours.RESET);

        do {
            // if transaction records exist
            if (!TransactionController.readByEmail(moviegoer.getEmail()).isEmpty()) {
                TransactionController.readByEmailPrint(this.moviegoer.getEmail());
            }
            // if no transaction records exist
            else {
                System.out.println("No Transaction Records found!");
            }

            // type any character to exit
            back = sc.nextLine();

            if (back.isBlank()) {
                break;
            }

            return new MovieGoerMainMenu(this.getPreviousMenu(), 0, moviegoer, movie, movieSession, cinema, ticket,
                    transaction);

        } while (!back.isBlank());

        return this.getPreviousMenu();

    }

}