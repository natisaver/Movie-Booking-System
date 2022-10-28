package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Model.Cineplex;
import Model.Holiday;

/**
 * Reads name and date of public holidays from csv file in the MOBLIMA Cinema
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
     * READ and list of holidays from Database
     * 
     * @return ArrayList Return array of Holiday if database exists, else null
     *         object
     */
    public static ArrayList<Cineplex> readByLocation(String location) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Cineplex>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].equals(location)) {
                    cineplexArrayList.add(new Cineplex(null, tokens[0], tokens[0]));
                }

            }

            reader.close();
            return cineplexArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return cineplexArrayList;
        }
    }
}