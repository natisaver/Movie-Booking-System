package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ListTopFiveController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("ListTopFiveBy");

    /**
     * READ every row of ListTopFiveBy Database File
     * If Database file not found, ignore error and return -1
     * 
     * @return      1 if ListTopFiveBySales 0 if ListTopFiveByRatings
     */
    public static int read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        // If Database Exists
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                reader.close();
                return Integer.parseInt(tokens[0]);
            }
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
            return -1;
        }
        return -1;
    }


   /**
     * UPDATE ListTopFiveBy Database File
     * @param ListTopFiveBySales option chosen by the admin
     * @return                   <code>true</code> if setting is updated successfully, else
     *                           <code>false</code>.
     */

    public static Boolean update(String ListTopFiveBySales) {
        File inputFile = new File(DataController.getPath("ListTopFiveBy"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            // System.out.println("Error 1");
            return false;

        } catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("Error 2");
            return false;
        }

        try {
            writer.append("BySales");
            writer.append("\n");
            reader.readLine();
            writer.append(ListTopFiveBySales);

            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("ListTopFiveBy")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error 5");
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("ListTopFiveBy")));
        return true;
    }
}