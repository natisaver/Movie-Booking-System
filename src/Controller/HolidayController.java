package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import Model.Holiday;

public class HolidayController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Holiday");

    /** 
     * READ and return a Holiday by searching for one with matching email in the Database
     * @param email             Email of Holiday to search for
     * @return ArrayList        Return array of Holiday if found, else null object
     */
    public static ArrayList<Holiday> readByEmail(LocalDateTime date) {
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
                    holidayArrayList.add(new Holiday(tokens[1], tokens[0]));
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
