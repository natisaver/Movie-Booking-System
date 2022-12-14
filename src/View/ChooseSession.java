package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.CineplexController;
import Controller.CinemaController;
import Controller.MovieSessionController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

/**
 * The starting page that begins the application.
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class ChooseSession extends BaseMenu {
    Scanner sc = new Scanner(System.in);
    MovieGoer user;
    Movie movie = null;
    MovieSession movieSession = null;
    Cinema cinema = null;
    ArrayList<Ticket> ticket;
    Transaction transaction = null;
    Cineplex cineplex = null;

    /**
     * Constructor to store previous page, access level, moviegoer, movie,
     * movieSession, cinema, cineplex, ticket, transaction, bookedSeats
     * 
     * @param previousMenu  the previous page
     * @param accesslevel   the level of access
     * @param user          the {@link MovieGoer} that has been logged in
     * @param movie         the selected {@link Movie}
     * @param movieSession  the selected {@link MovieSession}
     * @param cinema        the selected {@link Cinema}
     * @param ticket        the array of {@link Ticket}(s) booked
     * @param transaction   the {@link Transaction} made
     * @param cineplex      the selected {@link Cineplex}
     */
    public ChooseSession(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
            MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction,
            Cineplex cineplex) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.cineplex = cineplex;
    }

    /**
     * Choose Session Functionality
     * Display list of sessions options for moviegoer to choose from
     * 
     * @return Next Page or Previous Page
     * @see ChooseSeat or ChooseMovie
     */
    @Override
    public BaseMenu execute() {
        int choice = -1;
        BaseMenu nextMenu = this;

        // Based on cineplex chosen, display the sessions according to:
        // Movie Title & Cinema in that cineplex
        System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Session:" + ConsoleColours.RESET);
        ArrayList<Cinema> cinemasavailable = CineplexController.readByLocation(this.cineplex.getLocation());
        ArrayList<MovieSession> movieSessionArr = new ArrayList<MovieSession>();
        HashMap<MovieSession, Cinema> hashMapSession = new HashMap<MovieSession, Cinema>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int j = 0; j < cinemasavailable.size(); j++) {
            movieSessionArr = MovieSessionController.readByCodeTitle(cinemasavailable.get(j).getCinemaCode(),
                    this.movie.getTitle());
            for (int i = 0; i < movieSessionArr.size(); i++) {
                String dateStr = movieSessionArr.get(i).getSessionDate();
                String timeStr = movieSessionArr.get(i).getSessionTime();
                if (LocalDateTime.parse(dateStr + " " + timeStr, formatter).isAfter(LocalDateTime.now()))
                    hashMapSession.put(movieSessionArr.get(i),
                            CinemaController.readByCode(cinemasavailable.get(j).getCinemaCode()));
            }
        }

        // sort the hashmap
        HashMap<Integer, MovieSession> menuselector = new HashMap<Integer, MovieSession>();
        menuselector = sortByDate(hashMapSession);
        if (menuselector.size() <= 0) {
            System.out.println(ConsoleColours.RED + "No sessions available" + ConsoleColours.RESET);
        } else {
            for (int k = 1; k <= menuselector.size(); k++) {
                System.out.println(
                        k + ": " + menuselector.get(k).getTitle() + " "
                                + menuselector.get(k).getSessionDate() + " "
                                + menuselector.get(k).getSessionTime()
                                + " | (" + hashMapSession.get(menuselector.get(k)).getCinemaClass().name() + ")");
            }
        }
        System.out.println(ConsoleColours.YELLOW + (menuselector.size() + 1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (menuselector.size() + 2) + ". Quit" + ConsoleColours.RESET);
        String numregex = "[0-9]+";
        Boolean isOK = false;
        while (!isOK) {
            System.out.print(
                    ConsoleColours.WHITE_BOLD + "Enter the session you want (Integer Value): " + ConsoleColours.RESET);
            String choicestr = sc.nextLine();
            if (!choicestr.matches(numregex)) {
                System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                continue;
            }
            if (Integer.valueOf(choicestr) <= (menuselector.size() + 2) && Integer.valueOf(choicestr) > 0) {
                choice = Integer.valueOf(choicestr);
                isOK = true;
            } else {
                System.out.println(ConsoleColours.RED + "Please enter a valid range between 1<=x<="
                        + (menuselector.size() + 2) + ConsoleColours.RESET);
            }
        }

        if (choice == menuselector.size() + 1) {
            nextMenu = this.getPreviousMenu();
        } else if (choice == menuselector.size() + 2) {
            nextMenu = new Quit(this);
        } else {
            // nextMenu = new ChooseSeat();
            System.out
                    .println(ConsoleColours.GREEN + "Details of selected Movie Session: " + ConsoleColours.RESET);
            movieSession = menuselector.get(choice);
            System.out.println(movieSession.getTitle() + " - "
                    + movieSession.getSessionDate() + " "
                    + movieSession.getSessionTime()
                    + " | (" + movieSession.getMovieType().name() + ")");

            nextMenu = new ChooseSeat(nextMenu, this.accesslevel, this.user, this.movie, movieSession,
                    hashMapSession.get(movieSession), this.ticket,
                    this.transaction, this.cineplex);
        }

        return nextMenu;

    }

    public static HashMap<Integer, MovieSession> sortByDate(HashMap<MovieSession, Cinema> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<MovieSession, Cinema>> list = new LinkedList<Map.Entry<MovieSession, Cinema>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<MovieSession, Cinema>>() {
            public int compare(Map.Entry<MovieSession, Cinema> o1,
                    Map.Entry<MovieSession, Cinema> o2) {
                if (o1.getKey().getShowtime().isBefore(o2.getKey().getShowtime())) {
                    return -1;
                } else if (o1.getKey().getShowtime().isAfter(o2.getKey().getShowtime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, MovieSession> temp = new LinkedHashMap<Integer, MovieSession>();
        int i = 1;
        for (Map.Entry<MovieSession, Cinema> aa : list) {
            temp.put(i, aa.getKey());
            i++;
        }
        return temp;
    }
}
