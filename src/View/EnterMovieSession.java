package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Controller.MovieController;
import Controller.MovieSessionController;
import Model.Cinema;
import Model.Movie;
import Model.MovieSession;

public class EnterMovieSession extends BaseMenu{
    private Movie movie;
    private MovieSession movieSession;
    private Cinema cinema;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EnterMovieSession(BaseMenu previousMenu, int accesslevel, Movie movie) {
        super(previousMenu, accesslevel);
        this.movie = movie;
    }

    public BaseMenu execute(){
        Scanner sc = new Scanner(System.in);
        String inputString;
        int inputInt;

        System.out.println("Enter Cinema Code: ");
        inputString = sc.nextLine();

        System.out.println("Enter Showtime (yyyy-MM-dd HH:mm): ");
        System.out.println("Enter blank to finish adding showtimes.");
        inputString = sc.nextLine();
        movieSession.setShowtime(LocalDateTime.parse(inputString, formatter));

        int duration = movie.getDuration();
        //iterate through csv & look for same cinema code in the movieSession csv
        //compare inputted start time (use java fn)
        //compare end time (add duration to start time)

        //start & end dates need to be within the limit of release & end date of movie


        //go back
        return this.getPreviousMenu();
    }
}
