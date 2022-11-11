package Model;

import java.util.ArrayList;

/**
 * Represents a cinema in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 18-10-2022
 */

public class Cinema {

    /**
     * Cinema's code which is a 3 number String (eg. XXX)
     * Cinema's code will be used in the TransactionID
     */
    private String cinemaCode;

    /**
     * Cinema's class type
     * Possible Cinema's class type: (Standard, Platinum Movie Suites)
     */
    private cinemaClass_Enum cinemaClass;

    /**
     * Cinema's list of movie sessions available
     */
    private ArrayList<MovieSession> showings;

    /**
     * Constructor
     * 
     * @param cinemaCode  Cinema's code
     * @param cinemaClass Cinema's class type
     * @param showings    Cinema's list of movies sessions available
     */
    public Cinema(String cinemaCode, cinemaClass_Enum cinemaClass, ArrayList<MovieSession> showings) {
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.showings = showings;
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
        this.cinemaCode = cinemaCode;
    }

    /**
     * Get the Cinema's class type
     * 
     * @return cinemaClass_Enum Cinema's class type
     *         Possible Cinema's class type: (Standard, Platinum Movie Suites)
     */
    public cinemaClass_Enum getCinemaClass() {
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

    /**s
     * Get the Cinema's sessions showings
     * 
     * @return {@code ArrayList<MovieSession>} Cinema's sessions showings
     */
    public ArrayList<MovieSession> getShowings() {
        return this.showings;
    }

    /**
     * Set the Cinema's sessions showings
     * 
     * @param showings {@code ArrayList<MovieSession>} Cinema's sessions showings
     */
    public void setShowings(ArrayList<MovieSession> showings) {
        this.showings = showings;
    }

}