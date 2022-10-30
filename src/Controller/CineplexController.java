package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Model.Cineplex;
import Model.Holiday;

/**
 * Reads location, cinema code and cinema type of Cineplexes from csv file in
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
     * READ and list of Cineplexes from Database
     * 
     * @return ArrayList Return array of Cineplex if database exists, else null
     *         object
     */
    public static ArrayList<Cineplex> read() {
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(
                    new FileReader("C:\\Users\\shiqi\\VSCode\\Moblima-Project-2\\Data\\Cineplex.csv"));
            while ((line = br.readLine()) != null)
            // returns a Boolean value
            {
                String[] token = line.split(splitBy);
                // use comma as separator
                System.out.println(token[1] + " " + token[2] + " " + token[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
            // for (Cineplex str : cineplexArrayList) {
            // System.out.println(str);
            // }
            return cineplexArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return cineplexArrayList;
        }

    }
}