package Model;

import java.util.ArrayList;

import Model.Cinema;

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
    private ArrayList<Cineplex> cinemas;

    /**
     * Cinema's name
     */
    private static String name;

    /**
     * Cinema's location
     */
    private static String location;

    /**
     * Constructor
     * 
     * @param arrayList List of Cinema's under a particular Cineplex
     * @param name      Cinema's name
     * @param location  Cinema's location
     */
    public Cineplex(ArrayList<Cineplex> arrayList, String name, String location) {
        this.cinemas = arrayList;
        this.name = name;
        Cineplex.location = location;
        // this.cinemas = new Cinema[3];
    }

    /**
     * Get the list of Cinemas under a particular Cineplex
     * 
     * @return Cinema[] List of Cinemas under a particular Cineplex
     */
    public ArrayList<Cineplex> getCinemas() {
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
    public static void setName(String cineplexName) {
        name = cineplexName;
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
    public static void setLocation(String cinelocation) {
        location = cinelocation;
    }

}