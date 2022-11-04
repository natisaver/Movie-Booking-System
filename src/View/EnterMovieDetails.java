package View;
import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Controller.MovieController;

import java.time.format.DateTimeFormatter;

public class EnterMovieDetails extends BaseMenu{
    Movie movie = new Movie();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EnterMovieDetails(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }


    public BaseMenu execute(){
        BaseMenu nextMenu = this;
        Scanner sc = new Scanner(System.in);
        String inputString, numRegex;
        ArrayList<String> inputArray = new ArrayList<String>(); 

        System.out.println(ConsoleColours.WHITE_BRIGHT + "Enter Details of New Movie:" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
    
        System.out.println("Enter Movie Title: ");
        inputString = sc.nextLine();
        movie = MovieController.readByTitle(inputString);
        
        //Checks if Movie already exists in the database
        while(movie != null){
            System.out.println(ConsoleColours.RED + "Movie already exists." + ConsoleColours.RESET);
            System.out.println("Re-enter Movie Title");
            inputString = sc.nextLine();
            movie = MovieController.readByTitle(inputString);
        }
        movie = new Movie();
        movie.setTitle(inputString);

        String[] strRegex = {"TWOD", "THREED", "BLOCKBUSTER"};
        System.out.println("Enter Movie Type: ");
        inputString = sc.nextLine().toUpperCase();
        System.out.println(inputString);
        while (!Arrays.asList(strRegex).contains(inputString)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Type:" + ConsoleColours.RESET);
            inputString = sc.nextLine().toUpperCase();
            System.out.println(inputString);

        }
        movie.setMovieType(movieType_Enum.valueOf(inputString));

        String[] strRegex2 = {"PG", "PG13", "NC16", "M18", "R21"};
        System.out.println("Enter Movie Rating: ");
        inputString = sc.nextLine().toUpperCase();
        System.out.println(inputString);
        while (!Arrays.asList(strRegex2).contains(inputString)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Rating:" + ConsoleColours.RESET);
            inputString = sc.nextLine().toUpperCase();
            System.out.println(inputString);
        }
        movie.setMovieRating(movieRating_Enum.valueOf(inputString));

        numRegex = "^([1-9][0-9]|[1-2][0-9][0-9])$";
        System.out.println("Enter Movie Duration (in minutes): ");
        inputString = sc.nextLine();
        while (!inputString.matches(numRegex)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Duration:" + ConsoleColours.RESET);
            inputString = sc.nextLine();
        }
        movie.setDuration(Integer.parseInt(inputString));

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
            inputString = sc.nextLine();
            if(inputString.isBlank() && inputArray.isEmpty()){
                return this.getPreviousMenu();
            }
            else if (inputString.isBlank()){
                break;
            }
            else{
                inputArray.add(inputString);
                i++;
            }
        }while(!inputArray.isEmpty());
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
            System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
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
        nextMenu = new EnterMovieSession(this, 1, movie);
        return nextMenu;
    }
}