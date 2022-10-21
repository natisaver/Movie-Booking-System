package Model;

import Model.Cinema;

/**
 * Represents a ticket in the MOBLIMA Cinema Application
 * 
 * @author CS2002 Group
 * @version 1.0
 * @since 18-10-2022
 */

public class Cineplex {

    /**
     * List of Cinema's under a particular Cineplex
     */
    private Cinema[] cinemas;

    /**
     * Cinema's name
     */
    private String name;

    /**
     * Cinema's location
     */
    private String location;

    /**
     * Constructor
     * 
     * @param cinemas  list of Cinema's under a particular Cineplex
     * @param name     Cinema's name
     * @param location Cinema's location
     */
    public Cineplex(Cinema[] cinemas, String name, String location) {
        this.cinemas = cinemas;
        this.location = location;
        this.cinemas = new Cinema[3];
    }

    /**
     * Get the list of Cinemas under a particular Cineplex
     * 
     * @return Cinema[] List of Cinemas under a particular Cineplex
     */
    public Cinema[] getCinemas() {
        return cinemas;
    }

    /**
     * Get the Cinema's name
     * 
     * @return String Cinema's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Cinema's name
     * 
     * @param name Cinema's name
     */
    public void setName(String name) {
        this.name = name;
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