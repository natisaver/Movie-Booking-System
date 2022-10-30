package View;

import java.time.LocalDateTime;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.MovieSessionController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Ticket;
import Model.ageGroup_Enum;
import Model.movieRating_Enum;
import Model.movieType_Enum;

public class BookTicket extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    public BookTicket(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {

        Cineplex cineplex;
        String location;
        Cinema cinema;
        LocalDateTime showtime;
        String movieTitle;
        movieType_Enum movieType;
        movieRating_Enum movieRating;
        int seat;
        ageGroup_Enum ageGroup;
        Ticket ticket;

        System.out.println("(Enter blank space for both to quit)");

        System.out.println("List of Cineplex: ");
        CineplexController.read();
        System.out.print("Please choose your preferred Cineplex's location: ");
        location = sc.next();
        CineplexController.readByLocation(location);
        Cineplex.setLocation(location);
        Cineplex.setName("Sally Carrera");
        cineplex = new Cineplex(CineplexController.readByLocation(location), "Sally Carrera", location);
        // cineplex.setCinemas(CineplexController.readByLocation(location));
        cinema = new Cinema("101", null, null);

        System.out.println("");

        System.out.println("List of Movies Available: ");
        MovieSessionController.read(cinema);
        System.out.print("Please choose your preferred Movie: ");
        movieTitle = sc.next();
        Movie.setTitle(movieTitle);
        MovieSessionController.readbyMovieTitle(cinema, movieTitle);

        for (int i = 0; i < Cinema.getShowings().length; i++) {
            System.out.println("List of Sessions Available: ");
            MovieSessionController.readbyMovieTitle(cinema, movieTitle);

            System.out.print("Please choose your preferred Session: ");
            String movieSession = sc.next();
        }

        // ticket = new Ticket(cineplex, cinema, showtime, movieTitle,
        // movieType,movieRating, seat, ageGroup);
        // System.out.println(TicketController.create(ticket));

        return new MovieGoerMainMenu(this.getPreviousMenu());
    }
}
