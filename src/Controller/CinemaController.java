package Controller;

import java.io.*;

import Model.Cinema;
import Model.cinemaClass_Enum;

/**
 * CRUD Operation for Cinemas
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */
public class CinemaController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Cineplex");
    /**
     * READ and return a Cinema by searching for one with matching cinemacode in the
     * Database
     * 
     * @param   cinemacode CinemaCode of Cinema to search for
     * @return  {@link Cinema} object if found, else return null
     */
    public static Cinema readByCode(String cinemacode) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        }

        // If Database Exists
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].equals(cinemacode)) {
                    return new Cinema(cinemacode, cinemaClass_Enum.valueOf(tokens[2]), MovieSessionController.readByCode(cinemacode));
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }
}