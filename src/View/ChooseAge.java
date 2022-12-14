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
import Model.ageGroup_Enum;

/**
 * Choose Age Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class ChooseAge extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    MovieGoer user;
    Movie movie;
    MovieSession movieSession;
    Cinema cinema;
    ArrayList<Ticket> ticket = new ArrayList<Ticket>();
    Transaction transaction;
    Cineplex cineplex;
    ArrayList<Seat> seat;
    int numTickets;

    /**
     * Constructor to store previous page, access level, moviegoer, movie,
     * movieSession, cinema, cineplex, ticket, transaction, bookedSeats
     * 
     * @param previousMenu  the previous page
     * @param accesslevel   the level of access
     * @param user          the {@link MovieGoer} that has been logged in
     * @param movie         the selected {@link Movie}
     * @param movieSession  the selected {@link MovieSession}
     * @param cinema        the selected {@link Cinema}
     * @param cineplex      the selected {@link Cineplex}
     * @param ticket        the array of {@link Ticket}(s) booked
     * @param transaction   {@link Transaction} made
     * @param seat          the array of {@link Seat}(s) booked in this transaction
     * @param originalSize  the number of ticket(s) booked in this transaction
     */
    public ChooseAge(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction,
            Cineplex cineplex, ArrayList<Seat> seat, int originalSize) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.cineplex = cineplex;
        this.seat = seat;
        this.numTickets = seat.size() - originalSize;
    }

    /**
     * Choose Age Functionality
     * Display list of ageGroup_Enum options for moviegoer to choose from
     * 
     * @return Next Page or Previous Page
     * @see DisplayTransaction or ChooseSeat
     */
    @Override
    public BaseMenu execute() {
        int choice;

        ageGroup_Enum ageGroup = null;

        System.out.println(ConsoleColours.PURPLE_BOLD + "Age Group Selection:" + ConsoleColours.RESET);
        if (this.movie.getMovieRating().name().equals("PG13") || this.movie.getMovieRating().name().equals("PG")){
            System.out.println("1. Child");
            System.out.println("2. Adult");
            System.out.println("3. Senior");
            System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);
        }
        else{
            System.out.println("1. Adult");
            System.out.println("2. Senior");
            System.out.println(ConsoleColours.YELLOW + "3. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);
        }


        BaseMenu nextMenu = this;
        this.ticket = new ArrayList<Ticket>();

        for (int k = 0; k < numTickets; k++) {

            System.out.print(ConsoleColours.WHITE_BOLD + "Please select an age group for Ticket " + (k + 1) + ": "
                    + ConsoleColours.RESET);
            String numregex = "^(?!(0))[0-5]{1}$";

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

            if (this.movie.getMovieRating().name().equals("PG13") || this.movie.getMovieRating().name().equals("PG")){
                switch (choice) {
                    // CHILD
                    case 1:
                        ageGroup = ageGroup_Enum.CHILD;
                        break;
                    // ADULT
                    case 2:
                        ageGroup = ageGroup_Enum.ADULT;
                        break;
                    // SENIOR
                    case 3:
                        ageGroup = ageGroup_Enum.SENIOR;
                        break;
                    // GO TO PREVIOUS PAGE
                    case 4:
                        nextMenu = this.getPreviousMenu();
                        return nextMenu;
                    // QUIT
                    case 5:
                        nextMenu = new Quit(this);
                        return nextMenu;
                    default:
                        choice = -1;
                        System.out.println("Please enter a valid choice.");
                        break;
                }
            }
            else{
                switch (choice) {
                    // ADULT
                    case 1:
                        ageGroup = ageGroup_Enum.ADULT;
                        break;
                    // SENIOR
                    case 2:
                        ageGroup = ageGroup_Enum.SENIOR;
                        break;
                    // GO TO PREVIOUS PAGE
                    case 3:
                        nextMenu = this.getPreviousMenu();
                        return nextMenu;
                    // QUIT
                    case 4:
                        nextMenu = new Quit(this);
                        return nextMenu;
                    default:
                        choice = -1;
                        System.out.println("Please enter a valid choice.");
                        break;
                }
            }



            Ticket newTicket = new Ticket(cineplex, cinema, movieSession.getShowtime(),
                    movie.getTitle(),
                    movie.getMovieType(),
                    movie.getMovieRating(), seat.get(k), ageGroup);
            ticket.add(newTicket);

        }
        return new DisplayTransaction(nextMenu, this.accesslevel, this.user, this.movie, this.movieSession, this.cinema,
                this.ticket, this.transaction,
                this.cineplex, this.seat);
    }
}
