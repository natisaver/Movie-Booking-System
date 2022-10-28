package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Model.Holiday;

 /**
 * Reads name and date of public holidays from csv file in the MOBLIMA Cinema Application
 * @author Sally Carrera 
 * @version 1.0
 * @since 21-10-2022
 */
public class HolidayController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Holiday");

    /** 
     * READ and list of holidays from Database
     * @return ArrayList    Return array of Holiday if database exists, else null object
     */
    public static ArrayList<Holiday> read() {
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
                    String dateTime = tokens[0] + " 00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime newObj = LocalDateTime.parse(dateTime, formatter);
                    holidayArrayList.add(new Holiday(tokens[1], newObj));
                }
            
            reader.close();
            return holidayArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return holidayArrayList;
        }
    }
}
