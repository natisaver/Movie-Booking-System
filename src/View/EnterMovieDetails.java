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
        ArrayList<String> inputArray = new ArrayList<String>(); 
    
    
        System.out.println("Enter Movie Title: ");
        inputString = sc.nextLine();
        movie = MovieController.readByTitle(inputString);
        
        //Checks if Movie already exists in the database
        if(movie == null){
            System.out.println("Movie does not exist.");
            System.out.println("Re-enter Movie Title");
            //need to reprompt here
        }
        movie.setTitle(inputString);

        System.out.println("Enter Movie Type: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        movie.setMovieType(movieType_Enum.valueOf(inputString.toUpperCase()));

        System.out.println("Enter Movie Rating: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        movie.setMovieRating(movieRating_Enum.valueOf(inputString.toUpperCase()));

        System.out.println("Enter Synopsis: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        movie.setSynopsis(inputString);

        System.out.println("Enter Director: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
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

        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

        System.out.println("Enter Release Date (yyyy-MM-dd): ");
        inputString = sc.nextLine();
        while (!inputString.matches(dateCheck)){
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            inputString = sc.nextLine();
        }
        movie.setReleaseDate(inputString);

        System.out.println("Enter End Date (yyyy-MM-dd): ");
        inputString = sc.nextLine();
        while (!inputString.matches(dateCheck)){
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            inputString = sc.nextLine();
        }
        movie.setEndDate(inputString);
        
        //setting ticket sales to 0 when new movie created
        movie.setTicketSales();

        //add newly created movie to csv
        MovieController.create(movie);


        //go to nextMenu
        nextMenu = new EnterMovieSession();
        return nextMenu;
    }
}