package View;
import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.event.SwingPropertyChangeSupport;

import Controller.MovieController;

import java.time.LocalDateTime;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public EnterMovieDetails(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
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
        Scanner sc = new Scanner(System.in);
        String inputString, numRegex;
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
        movie = new Movie();
        movie.setTitle(inputString);

        //Enter Movie Type
        String[] strRegex = {"TWOD", "THREED", "BLOCKBUSTER"};
        System.out.println("Enter Movie Type: ");
        System.out.println(ConsoleColours.BLUE + "(TWOD, THREED, BLOCKBUSTER)" + ConsoleColours.RESET);
        inputString = sc.nextLine().toUpperCase();
        while (!Arrays.asList(strRegex).contains(inputString)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Type:" + ConsoleColours.RESET);
            inputString = sc.nextLine().toUpperCase();
        }
        System.out.println(ConsoleColours.GREEN + "Movie Type added" + ConsoleColours.RESET);
        movie.setMovieType(movieType_Enum.valueOf(inputString));

        //Enter Movie Rating
        String[] strRegex2 = {"PG", "PG13", "NC16", "M18", "R21"};
        System.out.println("Enter Movie Rating: ");
        System.out.println(ConsoleColours.BLUE + "(PG, PG13, NC16, M18, R21)" + ConsoleColours.RESET);
        inputString = sc.nextLine().toUpperCase();
        while (!Arrays.asList(strRegex2).contains(inputString)) {
            //early termination
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Movie Rating:" + ConsoleColours.RESET);
            inputString = sc.nextLine().toUpperCase();
        }
        System.out.println(ConsoleColours.GREEN + "Movie Rating added" + ConsoleColours.RESET);
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
        System.out.println(ConsoleColours.GREEN + "Movie Duration added" + ConsoleColours.RESET);
        movie.setDuration(Integer.parseInt(inputString));

        //Enter Movie Synopsis
        System.out.println("Enter Synopsis: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        System.out.println(ConsoleColours.GREEN + "Movie Synopsis added" + ConsoleColours.RESET);
        movie.setSynopsis(inputString);

        //Enter Movie Director
        System.out.println("Enter Director: ");
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        System.out.println(ConsoleColours.GREEN + "Movie Director added" + ConsoleColours.RESET);
        movie.setDirector(inputString);

        //Enter Movie Cast
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
        System.out.println(ConsoleColours.GREEN + "Movie Cast added" + ConsoleColours.RESET);
        movie.setCast(inputArray);
        //clear array after - for use next time
        //inputArray.clear();

        //Enter Movie Release Date
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        System.out.println("Enter Release Date (yyyy-MM-dd): ");
        inputString = sc.nextLine();
        while (!inputString.matches(dateCheck) || LocalDateTime.parse(inputString + " 00:00", formatter).isBefore(LocalDateTime.now())){
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            if(!inputString.matches(dateCheck))
                System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
            else
                System.out.println(ConsoleColours.RED + "Please enter a future date" + ConsoleColours.RESET);
            inputString = sc.nextLine();
        }

        System.out.println(ConsoleColours.GREEN + "Movie Release Date added" + ConsoleColours.RESET);
        movie.setReleaseDate(inputString);

        //Enter Movie End Date
        System.out.println("Enter End Date (yyyy-MM-dd): ");
        inputString = sc.nextLine();
        while (!inputString.matches(dateCheck) || LocalDateTime.parse(inputString + " 00:00", formatter).isBefore(movie.getReleaseDate())){
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            if(!inputString.matches(dateCheck))
            {
                System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
            }  
            else 
            {
                System.out.println(ConsoleColours.RED + "Please enter a date after release date" + ConsoleColours.RESET);
            }          
            inputString = sc.nextLine();
        }

        System.out.println(ConsoleColours.GREEN + "Movie End Date added" + ConsoleColours.RESET);
        movie.setEndDate(inputString);
        
        //Set ticket sales to 0 when new movie created
        movie.setTicketSales();

        //Add newly created movie object to csv
        MovieController.create(movie);

        System.out.println(ConsoleColours.GREEN_BOLD + "Movie successfully created." + ConsoleColours.RESET);
        System.out.println();

        System.out.println(ConsoleColours.WHITE_BOLD + "Would you like to continue to create movie sessions for this movie?" + ConsoleColours.RESET);
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println(ConsoleColours.RED + "3. Quit" + ConsoleColours.RESET);
        //keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();
        System.out.println();

        int choice;
        String numregex = "^(?!(0))[0-3]{1}$";
        while (!choicestr.matches(numregex)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            System.out.println();
        }

        choice = Integer.valueOf(choicestr);

        switch (choice) {
            case 1:
                nextMenu = new EnterMovieSession(this, 1, movie);
                break;
            case 2:
                nextMenu = this.getPreviousMenu();
                break;
            case 3:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println("Please enter a valid choice.");
                break;
        }
        return nextMenu;
    }
}