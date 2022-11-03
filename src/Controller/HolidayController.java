package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
     * READ a list of holidays from Database
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
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
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

    public static Boolean addHoliday(LocalDateTime holiday, String holidayName) {
        File inputFile = new File(DataController.getPath("Holiday"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("Date");
            writer.append(",");
            writer.append("Name");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String holidayExist = tokens[1];
                if (holidayName.equals(holidayExist)) {
                    Found = true;
                }
                writer.append(tokens[0]);
                writer.append(",");
                writer.append(tokens[1]);
                writer.append("\n");
            }
            if (!Found) {
                writer.append(holiday.format(formatter).substring(0,10));
                writer.append(",");
                writer.append(holidayName);
                writer.append("\n");
            }
            writer.close();
            reader.close();
		if (Found) {
			Files.delete(Paths.get(DataController.getPath("Temp")));
			return false;
		}
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Holiday")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Holiday")));
        return true;
    }

    public static Boolean updateHoliday(LocalDateTime holiday, String holidayName) {
        File inputFile = new File(DataController.getPath("Holiday"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("Date");
            writer.append(",");
            writer.append("Name");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String holidayExist = tokens[1];
                if (holidayName.equals(holidayExist)) {
                    Found = true;
                    writer.append(holiday.format(formatter).substring(0,10));
                    writer.append(",");
                    writer.append(holidayName);
                    writer.append("\n");
                }
                else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
		if (!Found) {
			Files.delete(Paths.get(DataController.getPath("Temp")));
			return false;
		}
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Holiday")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Holiday")));
        return true;
    }
}
