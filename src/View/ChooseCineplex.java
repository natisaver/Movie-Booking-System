package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

/**
 * Choose Cineplex Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class ChooseCineplex extends BaseMenu {
    Scanner sc = new Scanner(System.in);
    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket;
    Transaction transaction = null;

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
    public ChooseCineplex(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
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
     * Choose Cineplex Functionality
     * Display list of cineplexes options for moviegoer to choose from
     * 
     * @return Next Page or Previous Page
     * @see ChooseMovie or MovieGoerMainMenu
     */
    @Override
    public BaseMenu execute() {
        int choice;
        String numregex = "^(?!(0))[0-5]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Cineplex:" + ConsoleColours.RESET);
        System.out.println("1. Bishan");
        System.out.println("2. NEX");
        System.out.println("3. Jewel");
        System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);

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
                ArrayList<Cinema> cinemaArrayBishan = CineplexController.readByLocation("Bishan");
                Cineplex cineplexBishan = new Cineplex(cinemaArrayBishan, "Bishan");

                System.out.println(ConsoleColours.GREEN + "You have picked Bishan Cineplex" + ConsoleColours.RESET);
                nextMenu = new ChooseMovie(this, 0, this.user, null, null, null, null, null, cineplexBishan);
                break;
            case 2:
                ArrayList<Cinema> cinemaArrayNex = CineplexController.readByLocation("Nex");
                Cineplex cineplexNex = new Cineplex(cinemaArrayNex, "Nex");

                System.out.println(ConsoleColours.GREEN + "You have picked NEX Cineplex" + ConsoleColours.RESET);
                nextMenu = new ChooseMovie(this, 0, this.user, null, null, null, null, null, cineplexNex);
                break;
            case 3:
                ArrayList<Cinema> cinemaArrayJewel = CineplexController.readByLocation("Jewel");
                Cineplex cineplexJewel = new Cineplex(cinemaArrayJewel, "Jewel");

                System.out.println(ConsoleColours.GREEN + "You have picked Jewel Cineplex" + ConsoleColours.RESET);
                nextMenu = new ChooseMovie(this, 0, this.user, null, null, null, null, null, cineplexJewel);
                break;
            case 4:
                nextMenu = this.getPreviousMenu();
                break;
            case 5:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println("Please enter a valid choice.");
                break;
        }

        return nextMenu;

    }
}
