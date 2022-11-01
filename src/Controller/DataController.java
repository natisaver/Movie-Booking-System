package Controller;

/**
 * Represents the main controller for accessing data in MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */

public class DataController {
    /**
     * Get path of csv file in database
     * 
     * @param str input String of csv file name
     * @return path of csv file
     */
    public static String getPath(String str) {
        return "Data/" + str + ".csv";
    }

}
