package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Controller.MovieController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

/**
 * Choose Movie Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class ChooseMovie extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    MovieGoer user;
    Movie movie;
    MovieSession movieSession;
    Cinema cinema;
    ArrayList<Ticket> ticket;
    Transaction transaction;
    Cineplex cineplex;

    /**
     * Constructor to store previous page, access level, moviegoer, movie,
     * movieSession, cinema, cineplex, ticket, transaction, bookedSeats
     * 
     * @param previousMenu the previous page
     * @param accesslevel  the level of access
     * @param moviegoer    the moviegoer that has been logged in
     * @param movie        the selected movie
     * @param movieSession the selected movieSession
     * @param cinema       the selected cinema
     * @param cineplex     the selected cineplex
     * @param ticket       the array of ticket(s) booked
     * @param transaction  the transaction made
     */
    public ChooseMovie(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction,
            Cineplex cineplex) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.cineplex = cineplex;
    }

    /**
     * Choose Movie Functionality
     * Display list of movies options for moviegoer to choose from
     * 
     * @return Next Page or Previous Page
     * @see ChooseSession or ChooseCineplex
     */
    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;
        ArrayList<Movie> movieArr = MovieController.read();

        System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Movie:" + ConsoleColours.RESET);
        HashMap<Integer, Movie> hashMapMovie = new HashMap<Integer, Movie>();
        for (int j = 0; j < movieArr.size(); j++) {
            hashMapMovie.put(j + 1, movieArr.get(j));
            System.out.println(j + 1 + ": " + hashMapMovie.get(j + 1).getTitle());
        }
        System.out.println(ConsoleColours.YELLOW + (movieArr.size() + 1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (movieArr.size() + 2) + ". Quit" + ConsoleColours.RESET);

        String numregex = "[0-9]+";
        String choicestr = "";
        Boolean isOK = false;
        int moviesize = movieArr.size();
        while (!isOK) {
            System.out.print(ConsoleColours.WHITE_BOLD + "Enter your choice of movie to view details (Integer Value): "
                    + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            if (!choicestr.matches(numregex)) {
                if (choicestr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                continue;
            }
            if (Integer.valueOf(choicestr) <= moviesize + 2 && Integer.valueOf(choicestr) > 0) {
                choice = Integer.valueOf(choicestr);
                isOK = true;
            } else {
                System.out.println(ConsoleColours.RED + "Please enter a valid range between 1<=x<=" + (moviesize + 2)
                        + ConsoleColours.RESET);
            }
        }

        choice = Integer.valueOf(choicestr);

        while (choice <= 0 || choice > (movieArr.size() + 2)) {
            // early termination
            if (choicestr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            choice = Integer.valueOf(choicestr);
            System.out.println();
        }

        if (choice <= movieArr.size()) {
            System.out.println(hashMapMovie.get(choice).getTitle());
            this.movie = MovieController.readByTitle(hashMapMovie.get(choice).getTitle());
            System.out.println(ConsoleColours.GREEN + "Movie successfully chosen" + ConsoleColours.RESET);
            System.out.println();
            nextMenu = new ChooseSession(nextMenu, 0, this.user, this.movie, this.movieSession, this.cinema,
                    this.ticket, this.transaction, this.cineplex);
            return nextMenu;
        }

        if (choice == movieArr.size() + 1) {
            nextMenu = this.getPreviousMenu();
            return nextMenu;
        } else if (choice == movieArr.size() + 2) {
            nextMenu = new Quit(this);
            return nextMenu;
        }
        return nextMenu;
    }
}