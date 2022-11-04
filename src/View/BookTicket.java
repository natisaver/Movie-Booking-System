package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
import Model.movieRating_Enum;
import Model.movieType_Enum;

public class BookTicket extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    /**
     * Current User
     */
    MovieGoer moviegoer = null;

    public BookTicket(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer) {
        super(previousMenu, accesslevel);
        this.moviegoer = moviegoer;
    }

    @Override
    public BaseMenu execute() {

        /**
         * Create Cineplex object
         */
        Cineplex cineplex = new Cineplex(null, null, null);

        cinemaClass_Enum cinemaType;
        Cinema cinema;

        /**
         * Create Movie object
         */
        String[] cast = null;
        String str = "2022-11-01";
        Movie movie = new Movie("", "", cast, str, str, null, 0, null, null, null, 0);

        String movieTitle = "";

        String numregex = "^(?!(0))[0-4]{1}$";

        // System.out.println("(Enter blank space for both to quit)");

        /**
         * Display list of Cineplex for users to choose from
         */
        System.out.println("\nList of Cineplex: \n");
        ArrayList<Cineplex> cineplexArray = CineplexController.read();

        // LinkedHashMap<Integer, Cineplex> cineplexList = new LinkedHashMap<Integer,
        // Cineplex>();
        // Set<Integer> keySet = cineplexList.keySet();
        // Integer[] keyArray = keySet.toArray(new Integer[keySet.size()]);
        // for (int j = 1; j < cineplexArray.size(); j++) {
        // keyArray[j] = j;
        // }
        // Integer index = 1;
        // Integer key = keyArray[index - 1];
        // System.out.println("Value at index " + index
        // + " is : " + cineplexArray.get(key));
        // for (int j = 0; j < cineplexArray.size(); j++) {
        // cineplexList.put(index, cineplexList.get(key));
        // }

        // cineplexList.put(index, cineplexList.get(key));

        // System.out.println(cineplexList.get(1));

        // /**
        // * Field for user to enter choice of Cineplex's location
        // */
        System.out.print("\nPlease choose your preferred Cineplex's location: ");

        // String locationStr = sc.nextLine();
        // while (!locationStr.matches(numregex)) {
        // // early termination
        // if (locationStr.isBlank()) {
        // return this.getPreviousMenu();
        // }
        // System.out.println(ConsoleColours.RED + "Please enter a valid choice:" +
        // ConsoleColours.RESET);
        // locationStr = sc.nextLine();
        // }
        // int location = Integer.valueOf(locationStr);

        String location = sc.next();

        /**
         * Set location of cineplex and name of cineplex based on the user's preferred
         * cineplex choice
         */
        cineplex.setLocation(location);
        cineplex.setName("Sally Carrera");

        /**
         * Display list of Cinema Class Type, filtered by choosen Cineplex's location
         */
        System.out.println("\nList of Cinemas Class Type Available at " + location + "\n");
        CineplexController.readByLocation(location);
        // cineplex = new Cineplex(CineplexController.readByLocation(location),
        // cineplex.getName(), location);

        /**
         * Field for user to enter Cinema Class Type
         */
        System.out.print("\nPlease choose your preferred Cinema Class Type: ");
        cinemaType = cinemaClass_Enum.valueOf(sc.next().toUpperCase());
        cinema = new Cinema("101", cinemaType, null);

        /**
         * Display list of Movies for users to choose from
         */
        System.out.println("\nList of Movies Available: \n");
        MovieController.read();

        /**
         * Field for user to enter choice of movieTitle
         */
        System.out.print("\nPlease choose your preferred Movie: ");
        movieTitle = sc.nextLine();
        movieTitle += sc.nextLine();
        movie.setTitle(movieTitle);
        // movieType = movieType_Enum.valueOf(sc.next().toUpperCase());
        movie.setMovieType(movieType_Enum.BLOCKBUSTER);

        /**
         * Display list of Movie Showtime, filtered by choosen movieTitle and movieType
         */
        System.out.println("List of Sessions Available: \n");
        MovieSessionController.readbyMovieTitle(cinema, movie.getTitle(), movie.getMovieType());

        /**
         * Create MovieSession object
         */
        String strdate = "2022-11-01";
        String strtime = "13:00";
        MovieSession movieSession = new MovieSession(strdate, strtime,
                cinema.getCinemaClass(), null,
                movie.getMovieType().toString());

        /**
         * Field for user to enter choice of Session Date
         */
        System.out.print("\nPlease choose your preferred Session Date: ");
        String date = sc.nextLine();
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        while (!date.matches(dateCheck)) {
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            date = sc.nextLine();
        }

        /**
         * Field for user to enter choice of Session Time
         */
        MovieSessionController.readbyShowDate(cinema, movie.getTitle(), movie.getMovieType(), date);
        System.out.print("\nPlease choose your preferred Session Time: ");
        String time = sc.nextLine();
        String timeCheck = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        while (!time.matches(timeCheck)) {
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            date = sc.nextLine();
        }
        movieSession.setShowtime(date, time);

        System.out.println("\nDetails of selected Movie Session: ");

        /**
         * Create MovieSession object
         */
        movieSession = (MovieSessionController.readbyShowTime(cinema, movieTitle, movie.getMovieType(), date, time))
                .get(0);

        movieSession.showSeatings(cinema.getCinemaClass());

        System.out.print("\nPlease enter the number of seats being purchased: ");
        String noOfTicketsStr = sc.nextLine();

        while (!noOfTicketsStr.matches(numregex)) {
            // early termination
            if (noOfTicketsStr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            noOfTicketsStr = sc.nextLine();
        }

        int noOfTickets = Integer.valueOf(noOfTicketsStr);

        // Create an array of tickets to store all tickets made in this transaction
        ArrayList<Ticket> ticket = new ArrayList<>();
        String id;
        float totalPrice = 0;
        ageGroup_Enum ageGroup = null;

        for (int i = 0; i < noOfTickets; i++) {

            System.out.println("\nPlease enter the seat ID for Ticket " + (i + 1) + ": ");
            id = sc.next();
            movieSession.bookSeat(id, cinemaType);

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
            System.out.print("Please select an age group for Ticket " + (i + 1) + ": ");
            // ageGroup = ageGroup_Enum.valueOf(sc.next().toUpperCase());
            int age = sc.nextInt();

            switch (age) {
                case 1:
                    ageGroup = ageGroup_Enum.CHILD;
                    totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass());
                    break;
                case 2:
                    ageGroup = ageGroup_Enum.ADULT;
                    totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass());
                    break;
                case 3:
                    ageGroup = ageGroup_Enum.SENIOR;
                    totalPrice += PriceController.calculatePrice(movieSession, ageGroup, cinema.getCinemaClass());
                    break;
            }

            movieSession.showSeatings(cinema.getCinemaClass());

            ticket.add(new Ticket(cineplex, cinema, movieSession.getShowtime(), movieTitle, movie.getMovieType(),
                    movie.getMovieRating(), id, ageGroup));
        }

        System.out.println("Total Price: " + totalPrice);

        // Get current date time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String formatDateTime = now.format(formatter1);
        System.out.println(formatDateTime);

        String TID = cinema.getCinemaCode().concat(formatDateTime);

        /**
         * Create transaction
         */
        if (moviegoer == null) {
            return new CreateOrLogin(this, -1);
        }

        Transaction transaction = new Transaction(TID, moviegoer.getName(), noOfTickets, movieTitle,
                movieSession.getShowtime().toString(), totalPrice);

        if (TransactionController.create(TID, moviegoer.getName(), noOfTickets, movieTitle,
                movieSession.getShowtime().toString(), totalPrice))
            System.out.println(
                    ConsoleColours.GREEN_BOLD + "Your ticket(s) have been successfully booked!" + ConsoleColours.RESET);
        else
            System.out.println(ConsoleColours.GREEN_BOLD + "Your booking is unsuccessful. Please try again."
                    + ConsoleColours.RESET);

        return this.getPreviousMenu();
    }

}
