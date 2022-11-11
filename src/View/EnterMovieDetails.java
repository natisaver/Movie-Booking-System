package View;
import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Scanner sc = new Scanner(System.in);

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        BaseMenu nextMenu = this;
        String inputString, numRegex;
        ArrayList<String> inputArray = new ArrayList<String>(); 

        //ENTER MOVIE TITLE TO CREATE
        numRegex = "^[^0-9]+$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Details of New Movie:" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Movie Title: " + ConsoleColours.RESET);
        inputString = sc.nextLine();
        
        //Checks if Movie already exists in the database
        do{
            //Checks for movie format
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }            
            
            //Checks if Movie exists in the database
            if(MovieController.readByTitle(inputString) != null){
                System.out.println(ConsoleColours.RED + "Movie already exists." + ConsoleColours.RESET);
                System.out.println(ConsoleColours.WHITE_BOLD + "Re-enter Movie Title:" + ConsoleColours.RESET);
                inputString = sc.nextLine();
            }
            else {
                break;
            }
        }while(true);

        Movie movie = new Movie();
        movie.setTitle(inputString);

        //ENTER MOVIE TYPE
        String[] strRegex = {"2D", "3D", "BLOCKBUSTER"};
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Movie Type: " + ConsoleColours.RESET);
        System.out.println(ConsoleColours.BLUE + "(2D, 3D, BLOCKBUSTER)" + ConsoleColours.RESET);
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
        System.out.println();
        if (inputString.equals("2D")) {
            inputString = "TWOD";
            movie.setMovieType(movieType_Enum.valueOf(inputString));
        }
        else if (inputString.equals("3D")) {
            inputString = "THREED";
            movie.setMovieType(movieType_Enum.valueOf(inputString));
        }
        else if (inputString.equals("BLOCKBUSTER")) movie.setMovieType(movieType_Enum.valueOf(inputString));

        //ENTER MOVIE RATING
        String[] strRegex2 = {"PG", "PG13", "NC16", "M18", "R21"};
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Movie Rating: " + ConsoleColours.RESET);
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
        System.out.println();
        movie.setMovieRating(movieRating_Enum.valueOf(inputString));

        //ENTER MOVIE DURATION
        numRegex = "^([1-9][0-9]|[1-2][0-9][0-9])$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Movie Duration (in minutes): " + ConsoleColours.RESET);
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
        System.out.println();
        movie.setDuration(Integer.parseInt(inputString));

        //ENTER MOVIE SYNOPSIS
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Synopsis: " + ConsoleColours.RESET);
        inputString = sc.nextLine();
        if(inputString.isBlank()){
            return this.getPreviousMenu();
        }
        System.out.println(ConsoleColours.GREEN + "Movie Synopsis added" + ConsoleColours.RESET);
        System.out.println();
        movie.setSynopsis(inputString);

        //ENTER MOVIE DIRECTOR
        numRegex = "^[^0-9]+$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Director: " + ConsoleColours.RESET);
        inputString = sc.nextLine();
        while (!inputString.matches(numRegex)){
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid Name:" + ConsoleColours.RESET);
            inputString = sc.nextLine();
        }
        System.out.println(ConsoleColours.GREEN + "Movie Director added" + ConsoleColours.RESET);
        System.out.println();
        movie.setDirector(inputString);

        //ENTER MOVIE CAST
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Cast: " + ConsoleColours.RESET);
        do{
            inputString = sc.nextLine();
            if(inputArray.isEmpty() && inputString.isBlank()){
                return this.getPreviousMenu();
            }
            while (!inputString.matches(numRegex)){
                if(!inputArray.isEmpty()){
                    break;
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid Name:" + ConsoleColours.RESET);
                inputString = sc.nextLine();
            }
            if (inputString.isBlank()){
                break;
            }
            else{
                inputArray.add(inputString);
            }
        }while(!inputArray.isEmpty());
        System.out.println(ConsoleColours.GREEN + "Movie Cast added" + ConsoleColours.RESET);
        System.out.println();
        movie.setCast(inputArray);

        //ENTER MOVIE RELEASE DATE
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Release Date (yyyy-MM-dd): " + ConsoleColours.RESET);
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
        System.out.println();
        movie.setReleaseDate(inputString);

        //ENTER MOVIE END DATE
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter End Date (yyyy-MM-dd): " + ConsoleColours.RESET);
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
        System.out.println();
        movie.setEndDate(inputString);
        
        //SET TICKET SALES TO 0 WHEN NEW MOVIE CREATED
        movie.setTicketSales();

        if (!MovieController.updateStatusAll()){
            System.out.println(ConsoleColours.RED + "Failed to update database" + ConsoleColours.RESET);
            return this.getPreviousMenu();
        };

        //ADD NEWLY CREATED MOVIE OBJECT TO CSV
        if (!MovieController.create(movie)){
            System.out.println(ConsoleColours.RED + "Failed to add Movie" + ConsoleColours.RESET);
            return this.getPreviousMenu();
        };

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
                System.out.println(ConsoleColours.RED + "Please enter a valid choice." + ConsoleColours.RESET);
                break;
        }
        return nextMenu;
    }
}