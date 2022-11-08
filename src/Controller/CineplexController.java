package Controller;

import java.io.*;
import java.util.*;

import Model.Cinema;
import Model.cinemaClass_Enum;

/**
 * CRUD Operations for Cineplexes
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */
public class CineplexController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Cineplex");

    /**
     * READ and list all Cineplexes from Database
     * 
     * @return return List of all the cineplexes as strings
     */
    public static ArrayList<String> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }

        // If Database Exists
        String line = "";
        ArrayList<String> cineplexArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (!cineplexArrayList.contains(tokens[0].toLowerCase())){
                    cineplexArrayList.add(tokens[0].toLowerCase());
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cineplexArrayList;
    }

    /**
     * READ and list all Cineplexes from Database with specified location
     * 
     * @param location Indicates the Location of the Cineplex
     * @return Model.{@link Cinema} Return List Array of Cinema at the location
     *         if the location exists
     */
    public static ArrayList<Cinema> readByLocation(String location) {

        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // If Database Exists
        String line = "";
        ArrayList<Cinema> cinemaArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].toLowerCase().equals(location.toLowerCase())) {
                    cinemaArrayList.add(new Cinema(tokens[1], cinemaClass_Enum.valueOf(tokens[2]), MovieSessionController.readByCode(tokens[2])));
                }
            }
            reader.close();
            return cinemaArrayList;
        } catch (IOException e) {
            // e.printStackTrace();
            return cinemaArrayList;
        }
    }

    /**
     * READ and list all individual cinema codes from Database
     * 
     * @return return List of all the cineplexes as strings
     */
    public static ArrayList<String> readCodes() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }

        // If Database Exists
        String line = "";
        ArrayList<String> cineplexArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                cineplexArrayList.add(tokens[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cineplexArrayList;
    }
}
