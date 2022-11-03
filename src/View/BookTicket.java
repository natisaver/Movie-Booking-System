package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.MovieSessionController;
import Controller.PriceController;
import Controller.TransactionController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Seat;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDateTime str = LocalDateTime.now();
        String[] cast = null;
        String str = "2022-11-01";
        Movie movie = new Movie("", "", cast, str, str, null, 0, null, null, null, 0);

        String location;
        cinemaClass_Enum cinemaType;
        Cinema cinema;
        String showtime = "";
        String movieTitle = "";
        movieType_Enum movieType;
        movieRating_Enum movieRating;

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

        // Field for user to enter choice of movieTitle
        System.out.print("\nPlease choose your preferred Movie: ");
        movieTitle = sc.nextLine();
        movieTitle += sc.nextLine();
        movie.setTitle(movieTitle);
        // movieType = movieType_Enum.valueOf(sc.next().toUpperCase());
        movie.setMovieType(movieType_Enum.BLOCKBUSTER);

        // Display list of Movie Showtime, filtered by choosen movieTitle and movieType
        System.out.println("List of Sessions Available: \n");
        cinema.getShowings(); // System.out.print(cinema.getShowings());

        // Field for user to enter choice of session
        System.out.print("\nPlease choose your preferred Session: ");
        showtime = sc.nextLine();
        // movieSession.setShowtime(LocalDateTime.parse(showtime));
        // System.out.println(movieSession.getShowtime());

        System.out.println("\nDetails of selected Movie Session: ");
        MovieSessionController.readbyShowtime(cinema, movieTitle, movie.getMovieType(), showtime);
        // MovieSession session = (MovieSessionController.readbyShowtime(cinema,
        // movieTitle,
        // movie.getMovieType(), showtime)).get(0);

        System.out.print("\nPlease enter the number of seats being purchased: ");
        int noOfTickets = sc.nextInt();

        // Create an array of tickets to store all tickets made in this transaction
        ArrayList<Ticket> ticket = new ArrayList<>();
        int seat = 0;
        float totalPrice = 0;
        ageGroup_Enum ageGroup;

        for (int i = 0; i < noOfTickets; i++) {

            // Display list of age groups available for the tickets
            System.out.println("\nList of Age Groups: \n");
            System.out.println("1) CHILD");
            System.out.println("2) ADULT");
            System.out.println("3) SENIOR");

            // Field for user to enter age group for each ticket
            System.out.print("Please select an age group for Ticket " + (i + 1) + ": ");
            ageGroup = ageGroup_Enum.valueOf(sc.next().toUpperCase());

            // Create MovieSession object
            String strdate = "2022-11-01";
            String strtime = "13:00";
            MovieSession movieSession = new MovieSession(strdate, strtime,
                    cinema.getCinemaClass(), null,
                    movie.getMovieType().toString());

            movieSession.showSeatings(cinema.getCinemaClass());

            switch (ageGroup) {
                case CHILD:
                    ageGroup = ageGroup_Enum.CHILD;
                    totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass());
                    break;
                case ADULT:
                    ageGroup = ageGroup_Enum.ADULT;
                    totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass());
                    break;
                case SENIOR:
                    ageGroup = ageGroup_Enum.SENIOR;
                    totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass());
                    break;
            }

            ticket.add(new Ticket(cineplex, cinema, movieSession.getShowtime(), movieTitle, movie.getMovieType(),
                    movie.getMovieRating(), seat, ageGroup));
        }

        // Get current date time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String formatDateTime = now.format(formatter1);
        System.out.println(formatDateTime);

        String TID = cinema.getCinemaCode().concat(formatDateTime);

        // Create transaction
        Transaction transaction = new Transaction(TID, "", noOfTickets, movieTitle,
                showtime, totalPrice);
        TransactionController.create(transaction);

        return new MovieGoerMainMenu(this.getPreviousMenu());
    }
}
