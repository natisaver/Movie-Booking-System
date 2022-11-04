package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.CineplexController;
import Controller.MovieSessionController;
import Model.Movie;
import Model.MovieSession;
/**
 * Intermediary Access Denied Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 04-11-2022
 */
public class EnterMovieSession extends BaseMenu{

    private Movie movie;
    private MovieSession movieSession;
    private Pattern regexPattern;
    private Matcher regMatcher;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Scanner sc = new Scanner(System.in);
    String date, time;
    String choicestr;
    LocalDateTime insertStart, insertEnd, curStart, curEnd;
    
    /** 
     * Constructor
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     * @param movie            the movie object to make a session for
     */
    public EnterMovieSession(BaseMenu previousMenu, int accesslevel, Movie movie) {
        super(previousMenu, accesslevel);
        this.movie = movie;
    }

    /**
     * Create a new MovieSession for Movie
     * Validation for CinemaCode, Date and Time
     * Checks for overlaps with current Sessions
     * @return to the Admin Menu Page if successful or upon early termination
     */
    public BaseMenu execute(){
        
        System.out.println("Input a New Session for Current Movie");
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
        
        System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
        System.out.println(CineplexController.read());

        System.out.println(ConsoleColours.BLUE + "Here are the current Sessions:" + ConsoleColours.RESET);
        System.out.println(MovieSessionController.read());

        //accept cinema code and check
        String numregex = "^(?!(0))[0-9]{3}$";
        System.out.println("Enter cinema code of cinema to add to: ");
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
            if (regMatcher.matches() && (CineplexController.readByCode(choicestr) != null)){
                break;
            }
            System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
            System.out.println("Please Reenter Cinema Code:");

        } while (!regMatcher.matches() && (CineplexController.readByCode(choicestr) == null));
        
        //accept date and check
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Enter the date of the holiday: (yyyy-MM-dd)");
		date = sc.nextLine();
        while (!date.matches(dateCheck)) {
            if(date.isBlank()){
                return this.getPreviousMenu().getPreviousMenu();
            }
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            date = sc.nextLine();
        }

        //check for HH:mm to append to the end of the date
        String timeRegex = "/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$/";
        System.out.println("Enter the start time of the movie: (HH:mm)");
        time = sc.nextLine();
        while (!time.matches(timeRegex)) {
            if(date.isBlank()){
                return this.getPreviousMenu().getPreviousMenu();
            }
            System.out.println("Please enter the time in the required format: (HH:mm)");
            time = sc.nextLine();
        }
    
        //create interval to insert dateTime
        date = date + " " + time;
		insertStart = LocalDateTime.parse(date, formatter);
        System.out.println(date);

        //checking for interval overlaps
        long duration = this.movie.getDuration();
        insertEnd = insertStart.plusMinutes(duration);

        //iterate through csv & look for same cinema code in the movieSession csv
        //compare inputted start time (use java fn)
        //compare end time (add duration to start time)

        //start & end dates need to be within the limit of release & end date of movie


        //go back
        return this.getPreviousMenu();
    }
}