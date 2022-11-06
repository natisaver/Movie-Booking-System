package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.PriceController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Seat;
import Model.Ticket;
import Model.Transaction;
import Model.ageGroup_Enum;
import Model.seatType_Enum;

public class ChooseAge extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    MovieGoer user;
    Movie movie;
    MovieSession movieSession;
    Cinema cinema;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction;
    Cineplex cineplex;

    public ChooseAge(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
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

    @Override
    public BaseMenu execute() {
        int choice;

        // CHOOSE NUMBER OF TICKETS TO PURCHASE
        System.out.print("\nPlease enter the number of seats being purchased: ");

        String numregexSeats = "^(?!(0))[0-9]{1}$";

        String noOfTicketsStr = sc.next();
        while (!noOfTicketsStr.matches(numregexSeats)) {
            // early termination
            if (noOfTicketsStr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            noOfTicketsStr = sc.nextLine();
        }
        int noOfTickets = Integer.valueOf(noOfTicketsStr);

        String id;
        float totalPrice = 0;
        ageGroup_Enum ageGroup = null;

        System.out.println(ConsoleColours.PURPLE_BOLD + "Age Group Selection:" + ConsoleColours.RESET);
        System.out.println("1. Child");
        System.out.println("2. Adult");
        System.out.println("3. Senior");
        System.out.println(ConsoleColours.YELLOW + "3. Logout" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);

        BaseMenu nextMenu = this;

        for (int k = 0; k < noOfTickets; k++) {

            System.out.print(ConsoleColours.WHITE_BOLD + "Please select an age group for Ticket " + (k + 1) + ": "
                    + ConsoleColours.RESET);
            String numregex = "^(?!(0))[0-4]{1}$";

            String choicestr = sc.next();
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
                // CHILD
                case 1:
                    ageGroup = ageGroup_Enum.CHILD;
                    // totalPrice += PriceController.calculatePrice(movieSession, ageGroup,
                    // cinema.getCinemaClass(),
                    // null);
                    // nextMenu = newDisplayTicket();
                    break;
                // ADULT
                case 2:
                    ageGroup = ageGroup_Enum.ADULT;
                    // totalPrice += PriceController.calculatePrice(movieSession, ageGroup,
                    // cinema.getCinemaClass(),
                    // null);
                    // nextMenu = newDisplayTicket();
                    break;
                // SENIOR
                case 3:
                    ageGroup = ageGroup_Enum.SENIOR;
                    // totalPrice += PriceController.calculatePrice(movieSession, ageGroup,
                    // cinema.getCinemaClass(),
                    // null);
                    // nextMenu = newDisplayTicket();
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

            // ticket.add(new Ticket(cineplex, cinema, movieSession.getShowtime(),
            // movie.getTitle(),
            // movie.getMovieType(),
            // movie.getMovieRating(), seat, ageGroup));

        }
        return nextMenu;
    }
}
