package View;
import Model.Admin;
import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MovieController;

import java.time.format.DateTimeFormatter;

/**
 * The page for Admin to input details of new Movie.
 * the MOBLIMA Cinema
 * Application
 * @author Sally Carrera
 * @version 1.0
 * @since 04-11-2022
 */
public class EnterMovieDetails extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    Movie movie = new Movie();
    Admin admin;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public EnterMovieDetails(BaseMenu previousMenu, int accesslevel, Admin admin) {
        super(previousMenu, accesslevel);
        this.admin = admin;
    }

    /**
     * EnterMovieDetails Menu Functionality
     * Create Movie by inputting Movie Title, Movie Type, Movie Rating, 
     * Movie Duration, Synopsis, Director, Cast, Release Date, End Date
     * User can also choose to go Back or Quit
     * @return AdminMainMenu or Terminates
     */
    @Override
    public BaseMenu execute(){
        BaseMenu nextMenu = this;
        String inputString, numRegex, strRegex;
        ArrayList<String> inputArray = new ArrayList<String>(); 

        //Enter Movie Title to create
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
        movie.setTitle(inputString);

        //Enter Movie Type
        strRegex = "TWOD" + "THREED" + "BLOCKBUSTER";
        System.out.println("Enter Movie Type: ");
        inputString = sc.nextLine().toUpperCase();
        while (!inputString.matches(strRegex)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Type:" + ConsoleColours.RESET);
            inputString = sc.nextLine();
        }
        movie.setMovieType(movieType_Enum.valueOf(inputString));

        //Enter Movie Rating
        strRegex = "PG" + "PG13" + "NC16" + "M18" + "R21";
        System.out.println("Enter Movie Rating: ");
        inputString = sc.nextLine().toUpperCase();
        while (!inputString.matches(strRegex)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Rating:" + ConsoleColours.RESET);
            inputString = sc.nextLine();
        }
        movie.setMovieRating(movieRating_Enum.valueOf(inputString));

        //Enter Movie Duration
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

        //Enter Movie Synopsis
        System.out.println("Enter Synopsis: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        movie.setSynopsis(inputString);

        //Enter Movie Director
        System.out.println("Enter Director: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        movie.setDirector(inputString);

        //Enter Movie Cast
        System.out.println("Enter Cast: ");
        int i=0;
        do{
            inputString = sc.nextLine();
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            else{
                inputArray.add(inputString);
                i++;
            }
        }while(inputArray.get(i) != null);
        movie.setCast(inputArray);
        //clear array after - for use next time
        inputArray.clear();

        //Enter Movie Release Date
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

        //Enter Movie End Date
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
        
        //Set ticket sales to 0 when new movie created
        movie.setTicketSales();

        //Add newly created movie object to csv
        MovieController.create(movie);

        //Go to nextMenu
        nextMenu = new EnterMovieSession(this, 1, movie, admin);
        return nextMenu;
    }
}