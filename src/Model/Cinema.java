package Model;

import java.util.ArrayList;

import Controller.MovieSessionController;

/**
 * Represents a cinema in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 18-10-2022
 */

public class Cinema {

    /**
     * Cinema's code which is a 3 letters String (eg. XXX)
     * Cinema's code will be used in the TransactionID
     */
    private static String cinemaCode;

    /**
     * Cinema's class type
     * Possible Cinema's class type: (Standard, Platinum Movie Suites)
     */
    private static cinemaClass_Enum cinemaClass;

    /**
     * Cinema's list of movie sessions available
     */
    private static MovieSession[] showings;

    /**
     * Constructor
     * 
     * @param cinemaCode  Cinema's code
     * @param cinemaClass Cinema's class type
     * @param showings    Cinema's list of movies sessions available
     */
    public Cinema(String cinemaCode, cinemaClass_Enum cinemaClass, MovieSession[] showings) {
        Cinema.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        Cinema.showings = new MovieSession[100];
    }

    /**
     * Get the Cinema's code
     * 
     * @return String Cinemas's code
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Set the Cinema's code
     * 
     * @param cinemaCode Cinema's code
     */
    public void setCinemaCode(String cinemaCode) {
        Cinema.cinemaCode = cinemaCode;
    }

    /**
     * Get the Cinema's class type
     * 
     * @return cinemaClass_Enum Cinema's class type
     *         Possible Cinema's class type: (Standard, Platinum Movie Suites)
     */
    public static cinemaClass_Enum getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Set the Cinema's class type
     * 
     * @param cinemaClass Cinema's class type
     */
    public void setCinemaClass(cinemaClass_Enum cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * Get the list of movies sessions available
     * 
     * @return MovieSession[] Cinema's list of movies sessions available
     */
    // public ArrayList<MovieSession> getShowings() {
    // String str = "2022-11-01";
    // Movie movie = new Movie("", "", null, str, str, null, 0, null, null, null,
    // 0);
    // return MovieSessionController.readbyMovieTitle(cinemaCode, movie.getTitle(),
    // movie.getMovieType());
    // }

}