package View;
import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieController;

import java.time.format.DateTimeFormatter;

public class EnterMovieDetails extends BaseMenu{
    Movie movie;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EnterMovieDetails(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    public BaseMenu execute(){
        System.out.println("Create Movie:");

        Scanner sc = new Scanner(System.in);
        String inputString;
        int inputInt;
        ArrayList<String> inputArray = new ArrayList<String>(); 

        do{
            System.out.println("Enter Movie Title: ");
            inputString = sc.next();
            movie = MovieController.readByTitle(inputString);
            
            //Checks if Movie already exists in the database
            if(movie == null){
                System.out.println("Movie does not exist.");
                System.out.println("Re-enter Movie Title");
                //need to reprompt here
            }

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
                inputArray.add(sc.next());
                i++;
            }while(inputArray.get(i) != null);
            movie.setCast(inputArray);
            //clear array after - for use next time
            inputArray.clear();


            System.out.println("Enter Release Date (yyyy-MM-dd): ");
            inputString = sc.next();
            movie.setReleaseDate(inputString);

            System.out.println("Enter End Date (yyyy-MM-dd): ");
            inputString = sc.next();
            movie.setEndDate(inputString);
            
            //setting ticket sales to 0 when new movie created
            movie.setTicketSales();

            //add newly created movie to csv
            MovieController.create(movie);

            // System.out.println("Enter 1 to create another movie");
            // System.out.println("Enter any other integer to quit");
            // inputInt = sc.nextInt();
        }while(inputInt == 1);

        //go back
        return this.getPreviousMenu();
    }
}