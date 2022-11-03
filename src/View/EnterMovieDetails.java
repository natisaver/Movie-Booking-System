package View;
import Model.Cinema;
import Model.Movie;
import Model.MovieSession;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnterMovieDetails extends BaseMenu{
    private Movie movie;
    private MovieSession movieSession;
    private Cinema cinema;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EnterMovieDetails(BaseMenu previousMenu) {
        super(previousMenu);
    }

    public BaseMenu execute(){
        System.out.println("Create Movie:");

        Scanner sc = new Scanner(System.in);
        String inputString;
        int inputInt;
        String inputArray[];


        do{
            System.out.println("Enter Movie Title: ");
            inputString = sc.next();
            movie.setTitle(inputString);

            System.out.println("Enter Movie Type: ");
            inputString = sc.next();
            movie.setMovieType(movieType_Enum.valueOf(inputString.toUpperCase()));

            System.out.println("Enter Movie Rating: ");
            inputString = sc.next();
            movie.setMovieRating(movieRating_Enum.valueOf(inputString.toUpperCase()));

            System.out.println("Enter Synopsis: ");
            inputString = sc.next();
            movie.setSynopsis(inputString);

            System.out.println("Enter Director: ");
            inputString = sc.next();
            movie.setDirector(inputString);

            System.out.println("Enter Cast: ");
            int i=0;
            do{
                inputArray[i].concat(sc.next());
                i++;
            }while(inputArray[i] != null);
            movie.setCast(inputArray);
            Arrays.fill(inputArray, null); //clear array after - for use next time


            System.out.println("Enter Release Date (yyyy-MM-dd): ");
            inputString = sc.next();
            movie.setReleaseDate(inputString);

            System.out.println("Enter End Date (yyyy-MM-dd): ");
            inputString = sc.next();
            movie.setEndDate(inputString);

            //set showtime & cinemaCode concurrently
            do{
                System.out.println("Enter Cinema Code to screen movie: ");
                inputString = sc.next();
                cinema.setCinemaCode(inputString);
                System.out.println("Enter Showtime (yyyy-MM-dd HH:mm): ");
                System.out.println("Enter blank to finish adding showtimes.");
                inputString = sc.next();
                movieSession.setShowtime(LocalDateTime.parse(inputString, formatter));
            }while(inputString != null);
            
            //setting ticket sales to 0 when new movie created
            movie.setTicketSales();

            System.out.println("Enter 1 to create another movie");
            System.out.println("Enter any other integer to quit");
            inputInt = sc.nextInt();
        }while(inputInt == 1);

        //go back
        return this.getPreviousMenu();
    }
}