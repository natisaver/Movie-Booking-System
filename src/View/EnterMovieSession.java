package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Controller.CinemaController;
import Controller.CineplexController;
import Controller.MovieController;
import Controller.MovieSessionController;
import Model.Movie;
import Model.MovieSession;
/**
 * Create Movie Session Page
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Scanner sc = new Scanner(System.in);
    String date, time;
    String choicestr;
    LocalDateTime insertStart, insertEnd, curStart, curEnd;
    ArrayList<MovieSession> sessionArrayList;
    boolean overlaps = false;
    ArrayList<String> sessionDate = new ArrayList<String>();
    ArrayList<String> sessionTimes = new ArrayList<String>();
    ArrayList<String> sessionTitle = new ArrayList<String>();
    
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
     * @return to the AdminMainMenu if successful or upon early termination
     * @see AdminMainMenu 
     */
    public BaseMenu execute(){
        
        System.out.println(ConsoleColours.WHITE_BOLD + "Input a New Session for Current Movie" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
        
        //print all current cinema codes
        System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
        System.out.println(CineplexController.readCodes());

        //INPUT CINEMACODE=================
        String numRegex = "^([1-3][0][1-3])$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter cinema code of cinema to add session to: " + ConsoleColours.RESET);
        choicestr = sc.nextLine();
        //make sure cinemacode is valid
        sessionArrayList = new ArrayList<MovieSession>();
        while (!choicestr.matches(numRegex)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu().getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
            System.out.println(ConsoleColours.WHITE_BOLD + "Please Reenter Cinema Code:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
        }
        //Get all sessions Sessions
        sessionArrayList = MovieSessionController.readByCode(choicestr);

        //Print all the sessions
        int i=0;
        sessionTitle = new ArrayList<String>();
        sessionDate = new ArrayList<String>();
        sessionTimes = new ArrayList<String>();
        for (i=0;i<sessionArrayList.size();i++) {
            sessionTitle.add(sessionArrayList.get(i).getTitle());
            sessionDate.add(sessionArrayList.get(i).getSessionDate());
            sessionTimes.add(sessionArrayList.get(i).getSessionTime());
        }
        if(!sessionDate.isEmpty() && !sessionTimes.isEmpty()){
            System.out.println(ConsoleColours.BLUE + "Here are the current Sessions in the selected cinema:" + ConsoleColours.RESET);
            for (i=0;i<sessionArrayList.size();i++) {
                System.out.println(sessionDate.get(i) + "\t" + sessionTimes.get(i) + "\t" + sessionTitle.get(i));
            }
        }

        //INPUT DATE================
        //check if date is valid
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime movieReleaseDate = MovieController.readByTitle(movie.getTitle()).getReleaseDate();
        LocalDateTime movieEndDate = MovieController.readByTitle(movie.getTitle()).getEndDate();

        //validity check loop, continues while date and time overlaps:
        do {
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter the date of movie screening: (yyyy-MM-dd)" + ConsoleColours.RESET);
            date = sc.nextLine();
            while (!date.matches(dateCheck) || LocalDateTime.parse(date + " 00:00", formatter).isBefore(LocalDateTime.now()) ||
                    LocalDateTime.parse(date + " 00:00", formatter).isBefore(movieReleaseDate) ||
                    LocalDateTime.parse(date + " 00:00", formatter).isAfter(movieEndDate) ||
                    LocalDateTime.parse(date + " 00:00", formatter).equals(movieEndDate)){
                if(date.isBlank()){
                    return this.getPreviousMenu().getPreviousMenu();
                }
                if(!date.matches(dateCheck))
                    System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                else if (LocalDateTime.parse(date + " 00:00", formatter).isBefore(movieReleaseDate))
                    System.out.println(ConsoleColours.RED + "Please enter a date after the movie's release date" + ConsoleColours.RESET);
                else if (LocalDateTime.parse(date + " 00:00", formatter).isAfter(movieEndDate) || LocalDateTime.parse(date + " 00:00", formatter).equals(movieEndDate))
                    System.out.println(ConsoleColours.RED + "Please enter a date before the movie's end date" + ConsoleColours.RESET);
                else
                    System.out.println(ConsoleColours.RED + "Please enter a future date" + ConsoleColours.RESET);
                date = sc.nextLine();
            }

            //Let sessionTimes array only contain times of specified date
            sessionTimes.clear();
            for (i=0;i<sessionDate.size();i++) {
                if (sessionDate.get(i).equals(date)){
                    sessionTimes.add(sessionArrayList.get(i).getSessionTime());
                }
            }            
    
            //INPUT START TIME================
            //check for HH:mm to append to the end of the date string
            String timeRegex = "^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$";
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter the start time of the movie: (HH:mm)" + ConsoleColours.RESET);
            Boolean isOK = false;
            while(!isOK){
                time = sc.nextLine();
                if(!time.matches(timeRegex)){
                    if(time.isBlank()){
                        return this.getPreviousMenu().getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter the time in the required format: (HH:mm)" + ConsoleColours.RESET);
                    continue;
                }
                else if (sessionTimes.contains(time)) {
                    System.out.println(ConsoleColours.RED + "Movie session already exists at this time" + ConsoleColours.RESET);
                    continue;
                }
                else {
                    isOK = true;
                }
            }
            System.out.println();
        
            //CREATE INTERVAL TO INSERT ==========
            date = date + " " + time;
            insertStart = LocalDateTime.parse(date, formatter);
    
            long duration = this.movie.getDuration();
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
            overlaps = false;
            for(i = 0; i < sessionArrayList.size(); i++) {
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
    
        } while (overlaps);

        //SUCCESS
        movieSession = new MovieSession(insertStart, CinemaController.readByCode(choicestr).getCinemaClass(), movie.getTitle(), movie.getMovieType().name());
        MovieSessionController.createSession(choicestr, movieSession);
        return this.getPreviousMenu().getPreviousMenu();
    }
}