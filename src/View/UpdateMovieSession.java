package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.MovieController;
import Controller.MovieSessionController;
import Controller.CinemaController;
import Model.Movie;
import Model.MovieSession;

/**
 * The page for Admin to update details of existing Movie.
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 04-11-2022
 */
public class UpdateMovieSession extends BaseMenu {
    Scanner sc = new Scanner(System.in);
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
     * 
     * @param previousMenu the previous page
     * @param accesslevel  the level of access
     */
    public UpdateMovieSession(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;
        Movie movie = new Movie();
        String choiceStr, inputString, numRegex;
        String choicestr;
        MovieSession movieSession;
        ArrayList<String> sessionDate = new ArrayList<String>();
        ArrayList<String> sessionTimes = new ArrayList<String>();
        ArrayList<String> sessionTitle = new ArrayList<String>();
        int i;
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        do {
            // Menu choices to Update Movie Details
            System.out.println(ConsoleColours.PURPLE_BOLD + "Update Movie Session:" + ConsoleColours.RESET);
            System.out.println("1. Add new Movie Session (Showtime & Cinema)");
            System.out.println("2. Delete existing Movie Session (Showtime & Cinema)");
            // System.out.println("3. Update existing Movie Session (Showtime & Cinema)");
            System.out.println(ConsoleColours.YELLOW + "3. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

            // Keep asking for choice
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
            choiceStr = sc.nextLine();
            System.out.println();
            numRegex = "^(?!(0))[0-5]{1}$";
            while (!choiceStr.matches(numRegex)) {
                // early termination
                if (choiceStr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choiceStr = sc.nextLine();
                System.out.println();
            }

            choice = Integer.valueOf(choiceStr);

            switch (choice) {
                // ADD MOVIE SESSION
                case 1:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Input a New Session for Existing Movie"
                            + ConsoleColours.RESET);
                    System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

                    System.out.println(ConsoleColours.WHITE_BOLD
                            + "Enter Title of existing Movie to add movie session to:" + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    // Go back to previousMenu if blank is entered
                    if (inputString.isBlank()) {
                        return this.getPreviousMenu();
                    }
                    movie = MovieController.readByTitle(inputString);

                    // Checks if Movie exists in the database
                    while (movie == null) {
                        System.out.println(ConsoleColours.RED + "Movie does not exist." + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.WHITE_BOLD + "Re-enter Movie Title:" + ConsoleColours.RESET);
                        inputString = sc.nextLine();
                        System.out.println();
                        movie = MovieController.readByTitle(inputString);
                    }
                    // If Movie exists, movie object will be the stated Movie

                    // print all current cinema codes
                    System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
                    System.out.println(CineplexController.readCodes());

                    // INPUT CINEMACODE=================
                    numRegex = "^([1-3][0][1-3])$";
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter cinema code of cinema to add session to: "
                            + ConsoleColours.RESET);
                    choicestr = sc.nextLine();
                    System.out.println();
                    // make sure cinemacode is valid
                    sessionArrayList = new ArrayList<MovieSession>();
                    while (!choicestr.matches(numRegex)) {
                        // early termination
                        if (choicestr.isBlank()) {
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
                        System.out.println(
                                ConsoleColours.WHITE_BOLD + "Please Reenter Cinema Code:" + ConsoleColours.RESET);
                        choicestr = sc.nextLine();
                        System.out.println();
                    }
                    // Get all sessions Sessions
                    sessionArrayList = MovieSessionController.readByCode(choicestr);

                    // Print all the sessions
                    i = 0;
                    sessionTitle = new ArrayList<String>();
                    sessionDate = new ArrayList<String>();
                    sessionTimes = new ArrayList<String>();
                    for (i = 0; i < sessionArrayList.size(); i++) {
                        sessionTitle.add(sessionArrayList.get(i).getTitle());
                        sessionDate.add(sessionArrayList.get(i).getSessionDate());
                        sessionTimes.add(sessionArrayList.get(i).getSessionTime());
                    }
                    if (!sessionDate.isEmpty() && !sessionTimes.isEmpty()) {
                        System.out.println(ConsoleColours.BLUE + "Here are the current Sessions in the selected cinema:"
                                + ConsoleColours.RESET);
                        for (i = 0; i < sessionArrayList.size(); i++) {
                            System.out.println(
                                    sessionDate.get(i) + "\t" + sessionTimes.get(i) + "\t" + sessionTitle.get(i));
                        }
                    }
                    LocalDateTime movieReleaseDate = movie.getReleaseDate();
                    LocalDateTime movieEndDate = movie.getEndDate();

                    // INPUT DATE================
                    // check if date is valid

                    // validity check loop, continues while date and time overlaps:
                    do {
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter the date of movie screening: (yyyy-MM-dd)"
                                + ConsoleColours.RESET);
                        date = sc.nextLine();
                        while (!date.matches(dateCheck)
                                || LocalDateTime.parse(date + " 00:00", formatter).isBefore(LocalDateTime.now()) ||
                                LocalDateTime.parse(date + " 00:00", formatter).isBefore(movieReleaseDate) ||
                                LocalDateTime.parse(date + " 00:00", formatter).isAfter(movieEndDate) ||
                                LocalDateTime.parse(date + " 00:00", formatter).equals(movieEndDate)) {
                            if (date.isBlank()) {
                                return this.getPreviousMenu();
                            }
                            if (!date.matches(dateCheck))
                                System.out.println(ConsoleColours.RED
                                        + "Please enter the date in the required format: (yyyy-MM-dd)"
                                        + ConsoleColours.RESET);
                            else if (LocalDateTime.parse(date + " 00:00", formatter).isBefore(movieReleaseDate))
                                System.out.println("Please enter a date after the movie's release date");
                            else if (LocalDateTime.parse(date + " 00:00", formatter).isAfter(movieEndDate)
                                    || LocalDateTime.parse(date + " 00:00", formatter).equals(movieEndDate))
                                System.out.println("Please enter a date before the movie's end date");
                            else
                                System.out.println(
                                        ConsoleColours.RED + "Please enter a future date" + ConsoleColours.RESET);
                            date = sc.nextLine();
                        }

                        // Let sessionTimes array only contain times of specified date
                        sessionTimes.clear();
                        for (i = 0; i < sessionDate.size(); i++) {
                            if (sessionDate.get(i) == date) {
                                sessionTimes.add(sessionArrayList.get(i).getSessionTime());
                            }
                        }

                        // INPUT START TIME================
                        // check for HH:mm to append to the end of the date string
                        String timeRegex = "^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$";
                        System.out.println(ConsoleColours.WHITE_BOLD + "Enter the start time of the movie: (HH:mm)"
                                + ConsoleColours.RESET);
                        Boolean isOK = false;
                        while (!isOK) {
                            time = sc.nextLine();
                            if (!time.matches(timeRegex)) {
                                if (time.isBlank()) {
                                    return this.getPreviousMenu();
                                }
                                System.out.println(
                                        ConsoleColours.RED + "Please enter the time in the required format: (HH:mm)"
                                                + ConsoleColours.RESET);
                                continue;
                            } else if (sessionTimes.contains(time)) {
                                System.out.println(ConsoleColours.RED + "Movie session already exists at this time"
                                        + ConsoleColours.RESET);
                                continue;
                            } else {
                                isOK = true;
                            }
                        }

                        // CREATE INTERVAL TO INSERT ==========
                        date = date + " " + time;
                        insertStart = LocalDateTime.parse(date, formatter);

                        long duration = movie.getDuration();
                        insertEnd = insertStart.plusMinutes(duration);

                        // Iterate Through Array of MovieSessions that have the same MovieCode
                        // Sort the showtimes in ascending order
                        sessionArrayList.sort(
                                Comparator
                                        .comparing(
                                                (MovieSession moviesession) -> moviesession.getShowtime().toLocalDate())
                                        .reversed()
                                        .thenComparing(
                                                Comparator
                                                        .comparing((MovieSession moviesession) -> moviesession
                                                                .getShowtime().toLocalTime()))

                        );

                        // COMPARE INTERVAL TO INSERT WITH CURRENT INTERVALS ==========
                        // updates boolean to true if overlap exists
                        overlaps = false;
                        for (i = 0; i < sessionArrayList.size(); i++) {
                            curStart = sessionArrayList.get(i).getShowtime();
                            curEnd = curStart.plusMinutes(duration);
                            // CASE 1: If inserted interval endtime < cur starttime, SAFE.
                            if (insertEnd.isBefore(curStart)) {
                                break;
                            }
                            // CASE 2: If inserted interval starttime > cur endtime, MAY BE SAFE.
                            else if (insertStart.isAfter(curEnd)) {
                                continue;
                            }
                            // CASE 3: Confirmed Overlap, UNSAFE.
                            else {
                                overlaps = true;
                                System.out.println(ConsoleColours.RED_BOLD
                                        + "ERROR datetime clashes with existing session, please try again."
                                        + ConsoleColours.RESET);
                                break;
                            }
                        }

                    } while (overlaps);
                    System.out.println();

                    // SUCCESS
                    movieSession = new MovieSession(insertStart,
                            CinemaController.readByCode(choicestr).getCinemaClass(), movie.getTitle(),
                            movie.getMovieType().name());
                    MovieSessionController.createSession(choicestr, movieSession);
                    System.out.println(ConsoleColours.GREEN + "Movie Session added" + ConsoleColours.RESET);
                    System.out.println();
                    return this.getPreviousMenu();

                // DELETE MOVIE SESSION
                case 2:
                    // print all current cinema codes
                    System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
                    System.out.println(CineplexController.readCodes());

                    // INPUT CINEMACODE=================
                    numRegex = "^([1-3][0][1-3])$";
                    System.out.println("Enter cinema code of movie session to delete: ");
                    choicestr = sc.nextLine();
                    System.out.println();
                    // make sure cinemacode is valid
                    sessionArrayList = new ArrayList<MovieSession>();
                    while (!choicestr.matches(numRegex)) {
                        // early termination
                        if (choicestr.isBlank()) {
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Cinema Code is Invalid." + ConsoleColours.RESET);
                        System.out.println(ConsoleColours.WHITE + "Please Reenter Cinema Code:" + ConsoleColours.RESET);
                        choicestr = sc.nextLine();
                        System.out.println();
                    }
                    // Get all sessions Sessions
                    sessionArrayList = MovieSessionController.readByCode(choicestr);

                    // Print all the sessions
                    i = 0;
                    sessionTitle = new ArrayList<String>();
                    sessionDate = new ArrayList<String>();
                    sessionTimes = new ArrayList<String>();
                    for (i = 0; i < sessionArrayList.size(); i++) {
                        sessionTitle.add(sessionArrayList.get(i).getTitle());
                        sessionDate.add(sessionArrayList.get(i).getSessionDate());
                        sessionTimes.add(sessionArrayList.get(i).getSessionTime());
                    }
                    if (!sessionDate.isEmpty() && !sessionTimes.isEmpty()) {
                        System.out.println(ConsoleColours.BLUE + "Here are the current Sessions in the selected cinema:"
                                + ConsoleColours.RESET);
                        for (i = 0; i < sessionArrayList.size(); i++) {
                            System.out.println(
                                    sessionDate.get(i) + "\t" + sessionTimes.get(i) + "\t" + sessionTitle.get(i));
                        }
                    }

                    // INPUT DATE================
                    // check if date is valid
                    // validity check loop, continues while date and time overlaps:

                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter date of movie session to delete: (yyyy-MM-dd)"
                            + ConsoleColours.RESET);
                    date = sc.nextLine();
                    while (!date.matches(dateCheck)) {
                        if (date.isBlank()) {
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED
                                + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                        date = sc.nextLine();
                    }

                    sessionArrayListByDate = new ArrayList<MovieSession>();
                    // check whether movie session exists on inputted date
                    for (i = 0; i < sessionArrayList.size(); i++) {
                        if (date.equals(sessionArrayList.get(i).getSessionDate())) {
                            sessionArrayListByDate.add(sessionArrayList.get(i));
                        }
                    }
                    // if only one session on inputted date, delete successful
                    if (sessionArrayListByDate.size() == 1) {
                        // checks if movie theatre is still empty
                        if (MovieSessionController.checkBooked(choicestr, sessionArrayListByDate.get(0))) {
                            System.out.println("checkBooked value is true");
                        } else {
                            System.out.println("checkBooked value is false");
                        }
                        if (!MovieSessionController.checkBooked(choicestr, sessionArrayListByDate.get(0))) {
                            MovieSessionController.delete(choicestr, sessionArrayListByDate.get(0));
                            System.out.println(ConsoleColours.GREEN + "Movie Session deleted." + ConsoleColours.RESET);
                            System.out.println();
                        } else {
                            System.out.println(
                                    ConsoleColours.RED + "This session already has bookings. Unable to be deleted."
                                            + ConsoleColours.RESET);
                        }
                        return this.getPreviousMenu();
                    } else if (sessionArrayListByDate.size() == 0) {
                        System.out.println(ConsoleColours.RED + "There are no available sessions on inputted date."
                                + ConsoleColours.RESET);
                        System.out.println();
                        continue;
                    } else {
                        System.out.println();
                        System.out.println(
                                ConsoleColours.BLUE + "Available sessions on inputted date:" + ConsoleColours.RESET);
                        for (int j = 0; j < sessionArrayListByDate.size(); j++) {
                            System.out.println(sessionArrayListByDate.get(j).getSessionTime());
                        }
                        System.out.println();
                    }

                    // INPUT START TIME================
                    // check for HH:mm to append to the end of the date string
                    timeRegex = "^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]|5[0-9])$";
                    System.out.println(ConsoleColours.WHITE_BOLD
                            + "Enter the start time of the movie session to delete: (HH:mm)" + ConsoleColours.RESET);
                    time = sc.nextLine();
                    System.out.println();
                    while (!time.matches(timeRegex)) {
                        if (time.isBlank()) {
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter the time in the required format: (HH:mm)"
                                + ConsoleColours.RESET);
                        time = sc.nextLine();
                        System.out.println();
                    }

                    sessionArrayListByTime = new ArrayList<MovieSession>();
                    // check whether movie session exists on inputted time
                    for (i = 0; i < sessionArrayListByDate.size(); i++) {
                        if (time.equals(sessionArrayListByDate.get(i).getSessionTime())) {
                            sessionArrayListByTime.add(sessionArrayListByDate.get(i));
                        }
                    }
                    // if session with inputted time exists, successful
                    if (sessionArrayListByTime.size() != 0) {
                        // checks if movie theatre is still empty
                        if (!MovieSessionController.checkBooked(choicestr, sessionArrayListByTime.get(0))) {
                            if (!MovieSessionController.delete(choicestr, sessionArrayListByTime.get(0))) {
                                System.out.println(
                                        ConsoleColours.GREEN + "Movie Session deleted." + ConsoleColours.RESET);
                                System.out.println();
                            }
                        } else {
                            System.out.println(
                                    ConsoleColours.RED + "This session already has bookings. Unable to be deleted."
                                            + ConsoleColours.RESET);
                        }
                        break;
                    } else {
                        System.out.println(
                                ConsoleColours.RED + "Movie session inputted does not exist." + ConsoleColours.RESET);
                        continue;
                    }
                    // RETURN TO PREVIOUS MENU
                case 3:
                    nextMenu = this.getPreviousMenu();
                    return nextMenu;
                // QUIT (TERMINATE PROGRAM)
                case 4:
                    nextMenu = new Quit(this);
                    return nextMenu;
            }
        } while (true);
    }
}
