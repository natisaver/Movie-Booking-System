package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.MovieSessionController;
import Controller.TransactionController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Ticket;
import Model.Transaction;
import Model.ageGroup_Enum;
import Model.cinemaClass_Enum;
import Model.movieRating_Enum;
import Model.movieType_Enum;

public class BookTicket extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    public BookTicket(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {

        Cineplex cineplex = new Cineplex(null, null, null);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDateTime str = LocalDateTime.now();
        // Movie movie = new Movie("", "", null, LocalDateTime.parse(str.toString(),
        // formatter),
        // LocalDateTime.parse(str.toString(), formatter), "", 0, null, null, null,
        // 0);

        String location;
        cinemaClass_Enum cinemaType;
        Cinema cinema;
        LocalDateTime showtime;
        String movieTitle;
        movieType_Enum movieType;
        movieRating_Enum movieRating;
        int seat;
        float totalPrice;
        ageGroup_Enum ageGroup;

        System.out.println("(Enter blank space for both to quit)");

        // Display list of Cineplex for users to choose from
        System.out.println("List of Cineplex: \n");
        CineplexController.read();

        // Field for user to enter choice of Cineplex's location
        System.out.print("\nPlease choose your preferred Cineplex's location: ");
        location = sc.next();

        // Set location of cineplex and name of cineplex based on the user's preferred
        // cineplex choice
        cineplex.setLocation(location);
        cineplex.setName("Sally Carrera");

        // Display list of Cinema Class Type, filtered by choosen Cineplex's location
        System.out.println("\nList of Cinemas Class Type Available at " + location + "\n");
        CineplexController.readByLocation(location);
        // cineplex = new Cineplex(CineplexController.readByLocation(location),
        // cineplex.getName(), location);

        // Field for user to enter Cinema Class Type
        System.out.print("\nPlease choose your preferred Cinema Class Type: ");
        cinemaType = cinemaClass_Enum.valueOf(sc.next().toUpperCase());

        cinema = new Cinema("101", cinemaType, null);

        // Display list of Movies for users to choose from
        System.out.println("\nList of Movies Available: \n");
        MovieSessionController.read(cinema);

        // Field for user to enter choice of MovieTitle
        System.out.print("\nPlease choose your preferred Movie: ");
        movieTitle = sc.next();
        Movie.setTitle(movieTitle);
        Movie.setMovieType(movieType_Enum.valueOf(sc.next().toUpperCase()));

        // Display list of Movie Showtime, filtered by choosen MovieTitle
        MovieSessionController.readbyMovieTitle(cinema, movieTitle);

        for (int i = 0; i < Cinema.getShowings().length; i++) {
            System.out.println("List of Sessions Available: \n");
            MovieSessionController.readbyMovieTitle(cinema, movieTitle);

            System.out.print("\nPlease choose your preferred Session: ");
            String movieSession = sc.next();
        }

        // Ticket ticket = new Ticket(cineplex, cinema, showtime, movieTitle,
        // movieType,movieRating, seat, ageGroup);
        // System.out.println(TicketController.create(ticket));
        // Transaction transaction = new Transaction(movieTitle, location, seat,
        // movieTitle, showtime, totalPrice);
        // TransactionController.create(transaction);

        return new MovieGoerMainMenu(this.getPreviousMenu());
    }
}
