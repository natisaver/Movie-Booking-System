package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Model.Holiday;

 /**
 * Reads name and date of public holidays from csv file in the MOBLIMA Cinema Application
 * @author CS2002 Group 
 * @version 1.0
 * @since 21-10-2022
 */
public class HolidayController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Holiday");

    /** 
     * READ and return a Holiday by searching for one with matching date in the Database
     * @param date          Date of Holiday to search for
     * @return ArrayList    Return array of Holiday if found, else null object
     */
    public static ArrayList<Holiday> readByDate(LocalDateTime date) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Holiday>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Holiday> holidayArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals(date)){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime dateTime = LocalDateTime.parse(tokens[0], formatter);
                    holidayArrayList.add(new Holiday(tokens[1], dateTime));
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return holidayArrayList;
        }
    }
}
