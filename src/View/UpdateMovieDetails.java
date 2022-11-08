package View;
import Model.Movie;
import Model.MovieSession;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Controller.MovieController;

/**
 * The page for Admin to update details of existing Movie.
 * the MOBLIMA Cinema
 * Application
 * @author Sally Carrera
 * @version 1.0
 * @since 04-11-2022
 */
public class UpdateMovieDetails extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    LocalDateTime insertStart, insertEnd, curStart, curEnd;
    List<MovieSession> sessionArrayList;
    boolean overlaps = false;
    String date, time;
    DateTimeFormatter formatter;

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public UpdateMovieDetails(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    /**
     * UpdateDetails Menu Functionality
     * Update Movie Title, Movie Type, Movie Rating, Movie Duration,
     * Synopsis, Director, Cast, Release Date & End Date, Showtime,
     * Cinema, or Delete Movie
     * User can also choose to go Back or Quit
     * @return AdminMainMenu or Terminates
     */
    @Override
    public BaseMenu execute(){
        BaseMenu nextMenu = this;
        int choice;
        Movie movie = new Movie();
        String inputTitle, choiceStr, inputString, numRegex;
        String dateCheck;
        ArrayList<String> inputArray = new ArrayList<String>(); 

        String stringRegex = "^[^0-9]+$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Title of existing Movie to be updated:" + ConsoleColours.RESET);
        inputTitle = sc.nextLine();
        do{
            //Checks for movie format
            while (!inputTitle.matches(stringRegex)){
                if(inputTitle.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid Movie Title:" + ConsoleColours.RESET);
                inputTitle = sc.nextLine();
            }
            movie = MovieController.readByTitle(inputTitle);
            
            //Checks if Movie exists in the database
            if(movie == null){
                System.out.println(ConsoleColours.RED + "Movie does not exist." + ConsoleColours.RESET);
                System.out.println(ConsoleColours.WHITE_BOLD + "Re-enter Movie Title:" + ConsoleColours.RESET);
                inputTitle = sc.nextLine();
                movie = MovieController.readByTitle(inputTitle);
            }
        }while(movie == null);

        movie = MovieController.readByTitle(inputTitle);

        do{
            //Menu choices to Update Movie Details
            System.out.println(ConsoleColours.PURPLE_BOLD + "Update Movie Details:" + ConsoleColours.RESET);
            System.out.println("1. Movie Type");
            System.out.println("2. Movie Rating");
            System.out.println("3. Movie Duration");
            System.out.println("4. Synopsis");
            System.out.println("5. Director");
            System.out.println("6. Cast");
            System.out.println("7. Release Date & End Date");
            System.out.println("8. Delete Movie");
            System.out.println(ConsoleColours.YELLOW + "9. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "10. Quit" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

            //Keep asking for choice
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
            choiceStr = sc.nextLine();
            System.out.println();
            numRegex = "^([1-9]|[1][0])$";
            while (!choiceStr.matches(numRegex)) {
                //early termination
                if(choiceStr.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choiceStr = sc.nextLine();
                System.out.println();
            }

            choice = Integer.valueOf(choiceStr);

            switch (choice) {
                //UPDATE MOVIE TYPE
                case 1:
                    String[] strRegex = {"TWOD", "THREED", "BLOCKBUSTER"};
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Movie Type: " + ConsoleColours.RESET);
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
                    System.out.println(ConsoleColours.GREEN + "Movie Type changed to: " + inputString + ConsoleColours.RESET);
                    System.out.println();
                    movie.setMovieType(movieType_Enum.valueOf(inputString));
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE RATING
                case 2:
                    String[] strRegex2 = {"PG", "PG13", "NC16", "M18", "R21"};
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Movie Rating: " + ConsoleColours.RESET);
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
                    System.out.println(ConsoleColours.GREEN + "Movie Rating changed to: " + inputString + ConsoleColours.RESET);
                    System.out.println();
                    movie.setMovieRating(movieRating_Enum.valueOf(inputString));
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE DURATION
                case 3:
                    numRegex = "^([1-9][0-9]|[1-2][0-9][0-9])$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Movie Duration (in minutes): " + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    while (!inputString.matches(numRegex)) {
                        //early termination
                        if(inputString.isBlank()){
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter a valid Duration:" + ConsoleColours.RESET);
                        inputString = sc.nextLine();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie Duration changed to: " + inputString + " minutes" + ConsoleColours.RESET);
                    System.out.println();
                    movie.setDuration(Integer.parseInt(inputString));
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE SYNOPSIS
                case 4:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Synopsis: " + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie Synopsis changed" + ConsoleColours.RESET);
                    System.out.println();
                    movie.setSynopsis(inputString);
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE DIRECTOR
                case 5:
                    numRegex = "^[^0-9]+$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Director: " + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    while (!inputString.matches(numRegex)){
                        if(inputString.isBlank()){
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter a valid Name:" + ConsoleColours.RESET);
                        inputString = sc.nextLine();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie Director changed to: " + inputString + ConsoleColours.RESET);
                    System.out.println();
                    movie.setDirector(inputString);
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE CAST
                case 6:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Cast: " + ConsoleColours.RESET);
                    //clear current cast first 
                    movie.setCast(inputArray);
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

                    System.out.println(ConsoleColours.GREEN + "Movie Cast changed" + ConsoleColours.RESET);
                    System.out.println();
                    movie.setCast(inputArray);
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE RELEASE & END DATE
                case 7:
                    dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Release Date (yyyy-MM-dd): " + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    while (!inputString.matches(dateCheck)){
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie Release Date changed to: " + inputString + ConsoleColours.RESET);
                    System.out.println();
                    movie.setReleaseDate(inputString);

                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated End Date (yyyy-MM-dd): " + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    while (!inputString.matches(dateCheck)){
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie End Date changed to: " + inputString + ConsoleColours.RESET);
                    System.out.println();
                    movie.setEndDate(inputString);
                    MovieController.update(movie);
                    break;
                
                //DELETE MOVIE FROM MOVIE.CSV
                case 8:
                    MovieController.delete(movie);
                    return this.getPreviousMenu();

                //RETURN TO PREVIOUSMENU
                case 9:
                    nextMenu = this.getPreviousMenu();
                    return nextMenu;

                //TERMINATE PROGRAM
                case 10:
                    nextMenu = new Quit(this);
                    return nextMenu;
                
                //Should not enter here since regex is used
                default:
                    System.out.println(ConsoleColours.RED + "Invalid choice. Please re-enter your choice." + ConsoleColours.RESET);
                    System.out.println();
                    break;
            }
        }while(true);
    }
}
