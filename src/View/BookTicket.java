package View;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Templates;

import Controller.CineplexController;
import Controller.MovieController;
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
import Model.seatType_Enum;

public class BookTicket extends BaseMenu {

    Scanner sc = new Scanner(System.in);
    private Pattern regexPattern;
    private Matcher regMatcher;

    MovieGoer moviegoer;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction = null;

    /**
     * Current User
     */

    public BookTicket(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer, Movie movie,
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

        /**
         * Check if user has previously entered the information required for booking
         * of
         * tickets
         */
        if (movie == null || movieSession == null || cinema == null ||
                ticket.isEmpty()) {

            /**
             * Create Movie object
             */
            // String[] cast = null;
            // String str = "2022-11-01";
            // Movie movie = new Movie("", "", cast, str, str, null, 0, null, null, null,
            // 0);

            // System.out.println("(Enter blank space for both to quit)");

            /**
             * Display list of Cineplex for users to choose from
             */
            System.out.println(ConsoleColours.BLUE_BOLD + "\nList of Cineplex: " + ConsoleColours.RESET);

            ArrayList<String> cineplexArray = CineplexController.read();
            Map<Integer, String> hashMap = new HashMap();
            for (int j = 0; j < cineplexArray.size(); j++) {
                hashMap.put(j, cineplexArray.get(j));
                int k = j + 1;
                System.out.println(k + ": " + hashMap.get(k - 1));
            }

            /**
             * Field for user to enter choice of Cineplex's location
             */
            System.out.print(ConsoleColours.WHITE_BOLD + "\nPlease choose your preferred Cineplex's location: "
                    + ConsoleColours.RESET);

            String numregex = "^(?!(0))[0-3]{1}$";

            String locationStr = sc.nextLine();
            while (!locationStr.matches(numregex)) {
                // early termination
                if (locationStr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" +
                        ConsoleColours.RESET);
                locationStr = sc.nextLine();
            }
            int location = Integer.valueOf(locationStr) - 1;

            /**
             * Set location of cineplex and name of cineplex based on the user's preferred
             * cineplex choice
             */
            String cineplexLocation = hashMap.get(location);
            String cineplexName = "Sally Carrera";

            /**
             * Display list of Cinema Class Type, filtered by choosen Cineplex's location
             */
            System.out
                    .println(ConsoleColours.BLUE_BOLD + "\nList of Cinemas Class Type Available at " + cineplexLocation
                            + ConsoleColours.RESET);

            ArrayList<Cinema> cinemaArr = CineplexController.readByLocation(cineplexLocation);
            Map<Integer, Cinema> hashMap1 = new HashMap();
            for (int j = 0; j < cinemaArr.size(); j++) {
                hashMap1.put(j, cinemaArr.get(j));
                int k = j + 1;
                System.out.println(k + ": " + hashMap1.get(k - 1).getCinemaClass());
            }

            /**
             * Field for user to enter Cinema Class Type
             */
            System.out.print(ConsoleColours.WHITE_BOLD + "\nPlease choose your preferred Cinema Class Type: "
                    + ConsoleColours.RESET);

            String cinemaTypeStr = sc.nextLine();
            while (!cinemaTypeStr.matches(numregex)) {
                // early termination
                if (cinemaTypeStr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" +
                        ConsoleColours.RESET);
                cinemaTypeStr = sc.nextLine();
            }
            int cinemaTypeInt = Integer.valueOf(cinemaTypeStr) - 1;

            cinemaClass_Enum cinemaType = hashMap1.get(cinemaTypeInt).getCinemaClass();

            /**
             * Create Cineplex object
             */
            Cineplex cineplex = new Cineplex(cinemaArr, cineplexName);

            /**
             * Display list of Movies for users to choose from
             */
            System.out.println(ConsoleColours.BLUE_BOLD +
                    "\nList of Movies Available at " + cineplexLocation + " (" + cinemaType.toString() + ")"
                    + ConsoleColours.RESET);

            ArrayList<Movie> movieArray = MovieController.read();
            Map<Integer, Movie> hashMapMovie = new HashMap();
            for (int j = 0; j < movieArray.size(); j++) {
                int k = j + 1;
                hashMapMovie.put(k - 1, movieArray.get(k - 1));
                System.out.println(k + ": " + hashMapMovie.get(k - 1).getTitle());
            }

            /**
             * Field for user to enter choice of movieTitle
             */
            System.out
                    .print(ConsoleColours.WHITE_BOLD + "\nPlease choose your preferred Movie: " + ConsoleColours.RESET);

            String numregexMovie = "^(?!(0))[0-9]|1[0-9]|2[0-9]$";

            String movieTitleStr = sc.next();
            while (!movieTitleStr.matches(numregexMovie) || (Integer.valueOf(movieTitleStr) - 1 >= movieArray.size())) {
                // early termination
                if (movieTitleStr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" +
                        ConsoleColours.RESET);
                movieTitleStr = sc.next();
            }
            int movieTitle = Integer.valueOf(movieTitleStr) - 1;

            movie = hashMapMovie.get(movieTitle);

            /**
             * Display movie details
             */
            System.out.println(ConsoleColours.BLUE_BOLD + "Overview of Movie: " + ConsoleColours.RESET);
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Director: " + movie.getDirector());
            System.out.println("Cast: " + movie.getCast());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.println("End Date: " + movie.getEndDate());
            System.out.println("Synopsis: " + movie.getSynopsis());
            System.out.println("Movie Rating: " + movie.getMovieRating());
            System.out.println("Overall Rating: " + movie.getOverallRating());
            System.out.println("Review: " + movie.getReviewList());
            System.out.println("");

            /**
             * Display list of Movie ShowDate, filtered by choosen movieTitle and movieType
             */
            System.out.println(ConsoleColours.BLUE_BOLD + "List of Sessions Available: " + ConsoleColours.RESET);
            ArrayList<MovieSession> movieSessionArr = MovieSessionController
                    .readByTitle(hashMapMovie.get(movieTitle).getTitle());
            Map<Integer, MovieSession> hashMapSession = new HashMap();
            for (int j = 0; j < movieSessionArr.size(); j++) {
                int k = j + 1;
                hashMapSession.put(k - 1, movieSessionArr.get(k - 1));
                System.out.println(
                        k + ": " + hashMapSession.get(k - 1).getTitle() + " "
                                + hashMapSession.get(k - 1).getSessionDate() + " "
                                + hashMapSession.get(k - 1).getSessionTime());
            }

            /**
             * Field for user to enter choice of session
             */
            System.out.print(
                    ConsoleColours.WHITE_BOLD + "\nPlease choose your preferred session: " + ConsoleColours.RESET);

            String numregexSession = "^(?!(0))[0-9]|1[0-9]|2[0-9]$";

            String movieSessionStr = sc.next();
            while (!movieSessionStr.matches(numregexSession)
                    || (Integer.valueOf(movieSessionStr) - 1 >= movieSessionArr.size())) {
                // early termination
                if (movieSessionStr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" +
                        ConsoleColours.RESET);
                movieSessionStr = sc.next();
            }
            int movieSessionInt = Integer.valueOf(movieSessionStr) - 1;

            /**
             * Datails of selected Movie Session
             */
            System.out
                    .println(ConsoleColours.BLUE_BOLD + "\nDetails of selected Movie Session: " + ConsoleColours.RESET);

            System.out.println(hashMapSession.get(movieSessionInt).getTitle() + " "
                    + hashMapSession.get(movieSessionInt).getSessionDate() + " "
                    + hashMapSession.get(movieSessionInt).getSessionTime());

            movieSession = hashMapSession.get(movieSessionInt);

            // for (int i = 0; i < movieSessionArr.size(); i++) {
            // if (movieSessionArr.get(i).getShowtime() == movieSession.getShowtime())
            // ;
            // }
            // MovieSessionController.readbyShowDate(movie.getTitle(),
            // movie.getMovieType(), date);

            // /**
            // * Create MovieSession object
            // */
            // movieSession = (MovieSessionController.readbyShowTime(cinema,
            // movie.getTitle(),
            // movie.getMovieType(), date, time))
            // .get(0);

            /**
             * Create Cinema object
             */
            cinema = new Cinema(hashMap1.get(cinemaTypeInt).getCinemaCode(), cinemaType, movieSessionArr);

            /**
             * Display Available Seats
             */
            System.out.println("");
            movieSession.showSeatings(cinemaType);

            /**
             * Field for user to enter number of seats they would like
             */
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

            /**
             * Create an array of tickets to store all tickets made in this transaction
             */
            ArrayList<Ticket> ticket = new ArrayList<>();
            String id;
            float totalPrice = 0;
            ageGroup_Enum ageGroup = null;

            for (int k = 0; k < noOfTickets; k++) {

                System.out.print("\nPlease enter the seat ID for Ticket " + (k + 1) + ": ");
                id = sc.next();
                movieSession.bookSeat(id, cinemaType);
                Seat seat = new Seat(id, seatType_Enum.BASIC);

                /**
                 * Display list of age groups available for the tickets
                 */
                System.out.println("\nList of Age Groups: ");
                System.out.println("1) CHILD");
                System.out.println("2) ADULT");
                System.out.println("3) SENIOR");

                /**
                 * Field for user to enter age group for each ticket
                 */
                System.out.print("Please select an age group for Ticket " + (k + 1) + ": ");
                String ageStr = sc.next();
                while (!ageStr.matches(numregex)) {
                    // early termination
                    if (ageStr.isBlank()) {
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                    ageStr = sc.nextLine();
                }
                int age = Integer.valueOf(ageStr);

                switch (age) {
                    case 1:
                        ageGroup = ageGroup_Enum.CHILD;
                        totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass(),
                                seat.getSeatType());
                        break;
                    case 2:
                        ageGroup = ageGroup_Enum.ADULT;
                        totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass(),
                                seat.getSeatType());
                        break;
                    case 3:
                        ageGroup = ageGroup_Enum.SENIOR;
                        totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass(),
                                seat.getSeatType());
                        break;
                }

                movieSession.showSeatings(cinema.getCinemaClass());

                /**
                 * Add into ticket object into ticket array
                 */
                ticket.add(new Ticket(cineplex, cinema, movieSession.getShowtime(),
                        hashMapMovie.get(movieTitle).getTitle(),
                        movie.getMovieType(),
                        movie.getMovieRating(), seat, ageGroup));
            }

            System.out.println("Total Price: " + totalPrice);

            /**
             * Check if moviegoer is logged in
             */
            if (moviegoer == null) {
                return new CreateOrLogin(this, 2, moviegoer, movie, movieSession, cinema,
                        ticket, transaction);
            }

            // Get current date time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            String formatDateTime = now.format(formatter1);

            String TID = cinema.getCinemaCode().concat(formatDateTime);

        }

        transaction = new Transaction(ticket, moviegoer);

        if (TransactionController.create(transaction)) {
            System.out.println(
                    ConsoleColours.GREEN_BOLD + "Your ticket(s) have been successfully booked!" +
                            ConsoleColours.RESET);
            System.out.println("TID: " + transaction.getTID());
            System.out.println("Price Paid: " + transaction.getTotalPrice());
        } else
            System.out.println(ConsoleColours.GREEN_BOLD + "Your booking is unsuccessful. Please try again."
                    + ConsoleColours.RESET);

        return this.getPreviousMenu();
    }

}