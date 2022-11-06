package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.CineplexController;
import Controller.MovieController;
import Controller.MovieSessionController;
import Controller.CinemaController;
import Model.Movie;
import Model.MovieSession;

public class UpdateMovieSession extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    private Pattern regexPattern;
    private Matcher regMatcher;
    LocalDateTime insertStart, insertEnd, curStart, curEnd;
    List<MovieSession> sessionArrayList;
    List<MovieSession> sessionArrayListByDate;
    List<MovieSession> sessionArrayListByTime;
    boolean overlaps = false;
    String date, time, timeRegex;
    DateTimeFormatter formatter;
    int i;

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public UpdateMovieSession(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    public BaseMenu execute(){
        BaseMenu nextMenu = this;
        int choice;
        Movie movie = new Movie();
        String inputTitle, choiceStr, inputString, numRegex;
        String choicestr;
        String cinemastr;
        String dateCheck;
        ArrayList<String> inputArray = new ArrayList<String>(); 
        
        do{
            //Menu choices to Update Movie Details
            System.out.println(ConsoleColours.PURPLE_BOLD + "Update Movie Session:" + ConsoleColours.RESET);
            System.out.println("1. Add new Movie Session (Showtime & Cinema)");
            System.out.println("2. Delete existing Movie Session (Showtime & Cinema)");
            System.out.println("3. Delete existing Movie Session (Showtime & Cinema)");
            System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

            //Keep asking for choice
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
            choiceStr = sc.nextLine();
            numRegex = "^(?!(0))[0-5]{1}$";
            while (!choiceStr.matches(numRegex)) {
                //early termination
                if(choiceStr.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choiceStr = sc.nextLine();
            }

            choice = Integer.valueOf(choiceStr);

            switch(choice){
                //ADD MOVIE SESSION
                case 1:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Input a New Session for Existing Movie" + ConsoleColours.RESET);
                    System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter Title of existing Movie to be updated:" + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    //Go back to previousMenu if blank is entered
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    movie = MovieController.readByTitle(inputTitle);
                    
                    //Checks if Movie exists in the database
                    while(movie == null){
                        System.out.println(ConsoleColours.RED + "Movie does not exist." + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.WHITE_BOLD + "Re-enter Movie Title:");
                        inputString = sc.nextLine();
                        movie = MovieController.readByTitle(inputString);
                    }
                    //If Movie exists, movie object will be the stated Movie
                    
                    //print all current cinema codes
                    System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
                    System.out.println(ConsoleColours.BLUE + CineplexController.read() + ConsoleColours.RESET);
            
                    //INPUT CINEMACODE=================
                    String numregex = "^(?!(0))[0-9]{3}$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter cinema code of cinema to add to: " + ConsoleColours.RESET);
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
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            
                    //validity check loop, continues while date and time overlaps:
                    do {
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter the date of movie screening: (yyyy-MM-dd)" + ConsoleColours.RESET);
                        date = sc.nextLine();
                        while (!date.matches(dateCheck)) {
                            if(date.isBlank()){
                                return this.getPreviousMenu().getPreviousMenu();
                            }
                            System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                            date = sc.nextLine();
                        }
                
                        //INPUT START TIME================
                        //check for HH:mm to append to the end of the date string
                        timeRegex = "/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$/";
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
            
                    //SUCCESS
                    MovieSession movieSession = new MovieSession(insertStart, CinemaController.readByCode(choicestr).getCinemaClass(),movie.getTitle());
                    break;
                
                //DELETE MOVIE SESSION
                case 2:
                    //print all current cinema codes
                    System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
                    System.out.println(CineplexController.read());

                    //INPUT CINEMACODE=================
                    numRegex = "^(?!(0))[0-9]{3}$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter cinema code of movie session to delete: " + ConsoleColours.RESET);
                    //make sure cinemacode is valid
                    do {
                        cinemastr = sc.nextLine();
                        //early termination
                        if(cinemastr.isBlank()){
                            return this.getPreviousMenu();
                        }
                        regexPattern = Pattern.compile(numregex);
                        regMatcher = regexPattern.matcher(cinemastr);
                        //valid cinemacode
                        if (regMatcher.matches() && (CinemaController.readByCode(cinemastr) != null)){
                            break;
                        }
                        System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.WHITE_BOLD + "Please Reenter Cinema Code:" + ConsoleColours.RESET);

                    } while (!regMatcher.matches() && (CinemaController.readByCode(cinemastr) == null));

                    //Get all sessions Sessions
                    sessionArrayList = MovieSessionController.readByCode(cinemastr);

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

                    //validity check loop
                    
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
                        MovieSessionController.delete(cinemastr, sessionArrayListByDate.get(i));
                        System.out.println(ConsoleColours.GREEN + "Movie Session deleted." + ConsoleColours.RESET);
                        System.out.println();
                        break;
                    }
                    else{
                        System.out.println(ConsoleColours.BLUE + "Available sessions on inputted date:" + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.BLUE + sessionArrayListByDate.toString() + ConsoleColours.RESET);
                    }

                    //INPUT START TIME================
                    //check for HH:mm to append to the end of the date string
                    timeRegex = "/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$/";
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
                    //if session with inputted time exists, successful
                    if(sessionArrayListByTime.size() != 0){
                        MovieSessionController.delete(cinemastr, sessionArrayListByTime.get(i));
                        System.out.println(ConsoleColours.GREEN + "Movie Session deleted." + ConsoleColours.RESET);
                        System.out.println();
                        break;
                    }
                    else{
                        System.out.println(ConsoleColours.RED + "Movie session inputted does not exist." + ConsoleColours.RESET);
                    }

                    break;

                //UPDATE MOVIE SESSION
                case 3:
                    //print all current cinema codes
                    System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
                    System.out.println(CineplexController.read());

                    //INPUT CINEMACODE=================
                    numRegex = "^(?!(0))[0-9]{3}$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter cinema code of movie session to update: " + ConsoleColours.RESET);
                    //make sure cinemacode is valid
                    do {
                        cinemastr = sc.nextLine();
                        //early termination
                        if(cinemastr.isBlank()){
                            return this.getPreviousMenu();
                        }
                        regexPattern = Pattern.compile(numregex);
                        regMatcher = regexPattern.matcher(cinemastr);
                        //valid cinemacode
                        if (regMatcher.matches() && (CinemaController.readByCode(cinemastr) != null)){
                            break;
                        }
                        System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.WHITE_BOLD + "Please Reenter Cinema Code:" + ConsoleColours.RESET);

                    } while (!regMatcher.matches() && (CinemaController.readByCode(cinemastr) == null));

                    //Get all sessions Sessions
                    sessionArrayList = MovieSessionController.readByCode(cinemastr);

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

                    //validity check loop
                    
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter date of movie session to update: (yyyy-MM-dd)" + ConsoleColours.RESET);
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
                    //if only one session on inputted date, update successful
                    if(sessionArrayListByDate.size() == 1){
                        MovieSessionController.update(cinemastr, sessionArrayListByDate.get(i));
                        System.out.println(ConsoleColours.GREEN + "Movie Session updated." + ConsoleColours.RESET);
                        System.out.println();
                        break;
                    }
                    else{
                        System.out.println(ConsoleColours.BLUE + "Available sessions on inputted date:" + ConsoleColours.RESET);
                        System.out.println(sessionArrayListByDate.toString());
                    }

                    //INPUT START TIME================
                    //check for HH:mm to append to the end of the date string
                    timeRegex = "/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$/";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter the start time of the movie session to update: (HH:mm)" + ConsoleColours.RESET);
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
                    //if session with inputted time exists, successful
                    if(sessionArrayListByTime.size() != 0){
                        MovieSessionController.update(cinemastr, sessionArrayListByTime.get(i));
                        System.out.println(ConsoleColours.GREEN + "Movie Session updated." + ConsoleColours.RESET);
                        System.out.println();
                        break;
                    }
                    else{
                        System.out.println(ConsoleColours.RED + "Movie session inputted does not exist." + ConsoleColours.RESET);
                    }
                    break;
            }
        }while(true);
    }
}       

