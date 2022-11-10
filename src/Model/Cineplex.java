package Model;

import java.util.ArrayList;

/**
 * Represents a cineplex in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 18-10-2022
 */

public class Cineplex {

    /**
     * List of Cinemas under a particular Cineplex
     */
    private ArrayList<Cinema> cinemas;

    /**
     * Cinema's location
     */
    private String location;

    /**
     * Constructor
     * 
     * @param arrayList List of Cinema's under a particular Cineplex
     * @param location  Cinema's location
     */
    public Cineplex(ArrayList<Cinema> arrayList, String location) {
        this.cinemas = arrayList;
        this.location = location;
        // this.cinemas = new Cinema[3];
    }

    /**
     * Get the list of Cinemas under a particular Cineplex
     * 
     * @return Cinema[] List of Cinemas under a particular Cineplex
     */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /**
     * Get the Cinema's location
     * 
     * @return String Cinema's location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the Cinema's location
     * 
     * @param location Cinema's location
     */
    public void setLocation(String location) {
        this.location = location;
    }

}