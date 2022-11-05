package View;
import Model.Movie;
import Model.MovieSession;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.CinemaController;
import Controller.CineplexController;
import Controller.MovieController;
import Controller.MovieSessionController;

/**
 * The page for Admin to update details of existing Movie.
 * the MOBLIMA Cinema
 * Application
 * @author Sally Carrera
 * @version 1.0
 * @since 04-11-2022
 */
public class UpdateMovieDetails extends BaseMenu{
    private MovieSession movieSession;
    Scanner sc = new Scanner(System.in);
    private Pattern regexPattern;
    private Matcher regMatcher;
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
        String choicestr;
        String dateCheck;
        ArrayList<String> inputArray = new ArrayList<String>(); 

        System.out.println("Enter Title of existing Movie to be updated:");
        inputTitle = sc.nextLine();
        //Go back to previousMenu if blank is entered
        if(inputTitle.isBlank()){
            return this.getPreviousMenu();
        }
        movie = MovieController.readByTitle(inputTitle);
        
        //Checks if Movie exists in the database
        while(movie == null){
            System.out.println(ConsoleColours.RED + "Movie does not exist." + ConsoleColours.RESET);
            System.out.println("Re-enter Movie Title:");
            inputString = sc.nextLine();
            movie = MovieController.readByTitle(inputTitle);
        }
        //If Movie exists, movie object will be the stated Movie

        do{
            //Menu choices to Update Movie Details
            System.out.println(ConsoleColours.PURPLE_BOLD + "Update Details:" + ConsoleColours.RESET);
            System.out.println("1. Movie Title");
            System.out.println("2. Movie Type");
            System.out.println("3. Movie Rating");
            System.out.println("4. Movie Duration");
            System.out.println("5. Synopsis");
            System.out.println("6. Director");
            System.out.println("7. Cast");
            System.out.println("8. Release Date & End Date");
            System.out.println("9. Delete existing Movie Session (Showtime & Cinema)");
            System.out.println("10. Add new Movie Session (Showtime & Cinema)");
            System.out.println("11. Delete Movie");
            System.out.println(ConsoleColours.YELLOW + "12. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "13. Quit" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

            //Keep asking for choice
            System.out.println("Enter your choice: ");
            choiceStr = sc.nextLine();
            numRegex = "^([1-9]|[1][0-3])$";
            while (!choiceStr.matches(numRegex)) {
                //early termination
                if(choiceStr.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choiceStr = sc.nextLine();
            }

            choice = Integer.valueOf(choiceStr);

            switch (choice) {

                //UPDATE MOVIE TITLE
                case 1:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Title: " + ConsoleColours.RESET);
                    inputTitle = sc.nextLine();
                    if(inputTitle.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie Title changed to: " + inputTitle + ConsoleColours.RESET);
                    System.out.println();
                    movie.setTitle(inputTitle);
                    MovieController.update(movie);
                    break;
                
                //UPDATE MOVIE TYPE
                case 2:
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
                case 3:
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
                case 4:
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
                case 5:
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
                case 6:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Director: " + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.GREEN + "Movie Director changed to: " + inputString + ConsoleColours.RESET);
                    System.out.println();
                    movie.setDirector(inputString);
                    MovieController.update(movie);
                    break;

                //UPDATE MOVIE CAST
                case 7:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Updated Cast: " + ConsoleColours.RESET);
                    //clear current cast first 
                    movie.setCast(inputArray);
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
                        }
                    }while(!inputArray.isEmpty());
                    System.out.println(ConsoleColours.GREEN + "Movie Cast changed" + ConsoleColours.RESET);
                    System.out.println();
                    movie.setCast(inputArray);
                    //clear array after - for use next time
                    inputArray.clear();
                    break;

                //UPDATE MOVIE RELEASE & END DATE
                case 8:
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

                
                //DELETE SHOWTIME
                case 9:
                    List<MovieSession> sessionArrayListByDate;
                    List<MovieSession> sessionArrayListByTime;
                    List<MovieSession> sessionArrayListByCinema;
                    //Get all sessions of selected movie
                    sessionArrayList = MovieSessionController.readByTitle(inputTitle);
                    //Print all the sessions
                    System.out.println(ConsoleColours.BLUE + "Here are the current Sessions for the movie " + inputTitle + ":" + ConsoleColours.RESET);
                    System.out.println(ConsoleColours.BLUE + sessionArrayList.toString() + ConsoleColours.RESET);

                    //INPUT DATE================
                    //check if date is valid
                    dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    //validity check loop
                    do {
                        int i;
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter date of movie session to delete: (yyyy-MM-dd)" + ConsoleColours.RESET);
                        date = sc.nextLine();
                        while (!date.matches(dateCheck)) {
                            if(date.isBlank()){
                                return this.getPreviousMenu().getPreviousMenu();
                            }
                            System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                            date = sc.nextLine();
                        }

                        //check whether movie session exists on inputted date
                        for(i = 0; i < sessionArrayList.size(); i++) {
                            String dateOfSession;
                            dateOfSession = sessionArrayList.get(i).getSessionDate();
                            
                            if(dateOfSession == date){
                                sessionArrayListByDate.add(sessionArrayList.get(i));
                            }
                        }
                        //if only one session on inputted date, delete successful
                        if(sessionArrayListByDate.size() == 1){
                            System.out.println(ConsoleColours.GREEN + "Movie Session deleted." + ConsoleColours.RESET);
                            System.out.println();
                            break;
                        }
                        else{
                            System.out.println(ConsoleColours.BLUE + "Available sessions on inputted date:" + ConsoleColours.RESET);
                            System.out.println(sessionArrayListByDate.toString());
                        }

                        //INPUT START TIME================
                        //check for HH:mm to append to the end of the date string
                        String timeRegex = "/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$/";
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter the start time of the movie session to delete: (HH:mm)" + ConsoleColours.RESET);
                        time = sc.nextLine();
                        while (!time.matches(timeRegex)) {
                            if(date.isBlank()){
                                return this.getPreviousMenu().getPreviousMenu();
                            }
                            System.out.println(ConsoleColours.RED + "Please enter the time in the required format: (HH:mm)" + ConsoleColours.RESET);
                            time = sc.nextLine();
                        }

                        //check whether movie session exists on inputted time
                        for(i = 0; i < sessionArrayListByDate.size(); i++) {
                            String timeOfSession;
                            timeOfSession = sessionArrayListByDate.get(i).getSessionTime();
                            
                            if(timeOfSession == time){
                                sessionArrayListByTime.add(sessionArrayListByDate.get(i));
                            }
                        }
                        //if only one session on inputted time, delete successful
                        if(sessionArrayListByTime.size() == 1){
                            System.out.println("Movie Session deleted.");
                            System.out.println();
                            break;
                        }
                        else{
                            System.out.println("Available sessions on inputted time & date:");
                            System.out.println(sessionArrayListByTime.toString());
                        }

                        //ask user which cinema

                        //check whether movie session exists in inputted Cinema
                        for(i = 0; i < sessionArrayListByTime.size(); i++) {
                            String cinemaOfSession;
                            cinemaOfSession = sessionArrayListByTime.get(i).getSessionCinema();
                            
                            if(cinemaOfSession == cinema){
                                sessionArrayListByCinema.add(sessionArrayListByTime.get(i));
                            }
                        }
                        //if session with inputted cinema exists, successful
                        if(sessionArrayListByTime.size() != 0){
                            System.out.println("Movie Session deleted.");
                            System.out.println();
                        }
                        else{
                            System.out.println("Movie session inputted does not exist.");
                        }

                    }while(true);
                    break;

                //ADD NEW SHOWTIME
                case 10:
                    //Get all sessions Sessions
                    sessionArrayList = MovieSessionController.readByCode(choicestr);
                    //Print all the sessions
                    System.out.println(ConsoleColours.BLUE + "Here are the current Sessions:" + ConsoleColours.RESET);
                    System.out.println(ConsoleColours.BLUE + sessionArrayList.toString() + ConsoleColours.RESET);

                    //INPUT DATE================
                    //check if date is valid
                    dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    //validity check loop, continues while date and time overlaps:
                    do {
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter the new date of movie screening: (yyyy-MM-dd)" + ConsoleColours.RESET);
                        date = sc.nextLine();
                        while (!date.matches(dateCheck)) {
                            if(date.isBlank()){
                                return this.getPreviousMenu().getPreviousMenu();
                            }
                            System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                            date = sc.nextLine();
                        }

                        //check if session for that movie exists on the inputted date

                
                        //INPUT START TIME================
                        //check for HH:mm to append to the end of the date string
                        String timeRegex = "/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$/";
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter the start time of the movie: (HH:mm)" + ConsoleColours.RESET);
                        time = sc.nextLine();
                        while (!time.matches(timeRegex)) {
                            if(date.isBlank()){
                                return this.getPreviousMenu().getPreviousMenu();
                            }
                            System.out.println(ConsoleColours.RED + "Please enter the time in the required format: (HH:mm)" + ConsoleColours.RESET);
                            time = sc.nextLine();
                        }
                    
                        //CREATE INTERVAL TO INSERT ==========
                        date = date + " " + time;
                        insertStart = LocalDateTime.parse(date, formatter);
                
                        long duration = movie.getDuration();
                        insertEnd = insertStart.plusMinutes(duration);
                
                        //Iterate Through Array of MovieSessions that have the same MovieCode
                        //Sort the showtimes in ascending order 
                        sessionArrayList.sort(
                            Comparator
                                    .comparing( ( MovieSession moviesession )->moviesession.getShowtime().toLocalDate() )
                                    .reversed()
                                    .thenComparing(
                                            Comparator
                                                    .comparing( ( MovieSession moviesession ) -> moviesession.getShowtime().toLocalTime() )
                                    )
                    
                        );
                
                        //COMPARE INTERVAL TO INSERT WITH CURRENT INTERVALS ==========
                        //updates boolean to true if overlap exists
                        for(int i = 0; i < sessionArrayList.size(); i++) {
                            curStart = sessionArrayList.get(i).getShowtime();
                            curEnd = curStart.plusMinutes(duration);
                            //CASE 1: If inserted interval endtime < cur starttime, SAFE.
                            if (insertEnd.isBefore(curStart)) {
                                break;
                            }
                            //CASE 2: If inserted interval starttime > cur endtime, MAY BE SAFE.
                            else if (insertStart.isAfter(curEnd)){
                                continue;
                            }
                            //CASE 3: Confirmed Overlap, UNSAFE.
                            else {
                                overlaps = true;
                                System.out.println(ConsoleColours.RED_BOLD + "ERROR datetime clashes with existing session, please try again." + ConsoleColours.RESET);
                                break;
                            }
                        }
                
                    } while (overlaps == true);
                    break;

                //UPDATE CINEMA
                    //print all current cinema codes
                    System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
                    System.out.println(ConsoleColours.BLUE + CineplexController.read() + ConsoleColours.RESET);
                    
                    //INPUT CINEMACODE=================
                    String numregex = "^(?!(0))[0-9]{3}$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter updated cinema code: " + ConsoleColours.RESET);
                    //make sure cinemacode is valid
                    do {
                        choicestr = sc.nextLine();
                        //early termination
                        if(choicestr.isBlank()){
                            return this.getPreviousMenu();
                        }
                        regexPattern = Pattern.compile(numregex);
                        regMatcher = regexPattern.matcher(choicestr);
                        //valid cinemacode
                        if (regMatcher.matches() && (CinemaController.readByCode(choicestr) != null)){
                            break;
                        }
                        System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.WHITE_BOLD + "Please Reenter Cinema Code:" + ConsoleColours.RESET);

                    } while (!regMatcher.matches() && (CinemaController.readByCode(choicestr) == null));
                    System.out.println(ConsoleColours.GREEN + "New Movie Session added" + ConsoleColours.RESET);
                    movieSession = new MovieSession(insertStart, CinemaController.readByCode(choicestr).getCinemaClass(),movie.getTitle());
                    break;
                
                //DELETE MOVIE FROM MOVIE.CSV
                case 11:
                    MovieController.delete(movie);
                    break;

                //RETURN TO PREVIOUSMENU
                case 12:
                    nextMenu = this.getPreviousMenu();
                    return nextMenu;

                //TERMINATE PROGRAM
                case 13:
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
