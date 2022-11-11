package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Controller for CRUD operations managing pricing related data
 * for the MOBLIMA Cinema
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 05-11-2022
 */

public class PriceDataController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("Price");

    /**
     * READ and return a Price Value based on ticket ID in database
     * 
     * @param id        of ticket to retrieve search for
     * @return          {@Link Double} which stores the price value of the various types of tickets
     */
    public static double[] readByID(String id) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return new double[]{0,0,0,0,0};
        }

        // If Database Exists
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].equals(id)) {
                    double[] array = new double[]{Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6])};
                    reader.close();
                    return array;
                }
            }
            reader.close();
            return new double[]{0,0,0,0,0};
        } catch (IOException e) {
            // e.printStackTrace();
            return new double[]{0,0,0,0,0};
        }
    }


    /**
     *UPDATE Price of Ticket Type by ID in the database
     * @param id                ID of the ticket type
     * @param updatedprices     {@Link Array} of {@Link Double} which stores the updated price value of the various types of tickets
     * @return                  <code>true</code> if price was updated, 
     *                          <code>false</code> if ID doesnt exist or database is nonexistent
     */
    public static Boolean update(String id, double[] updatedprices) {

        File inputFile = new File(DataController.getPath("Price"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return false;

        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }

        Boolean Found = false;
        String line;

        try {
            writer.append("type");
            writer.append(",");
            writer.append("REGULAR");
            writer.append(",");
            writer.append("BLOCKBUSTER");
            writer.append(",");
            writer.append("THREED");
            writer.append(",");
            writer.append("Name");
            writer.append(",");
            writer.append("gold increment");
            writer.append(",");
            writer.append("elite seat increment");
            writer.append("\n");
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].equals(id)) {
                    Found = true;
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(String.valueOf(updatedprices[0]));
                    writer.append(",");
                    writer.append(String.valueOf(updatedprices[1]));
                    writer.append(",");
                    writer.append(String.valueOf(updatedprices[2]));
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(String.valueOf(updatedprices[3]));
                    writer.append(",");
                    writer.append(String.valueOf(updatedprices[4]));
                    writer.append("\n");
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (Found == false) {
                return false;
            }
            // delete old file
        } catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("probably here");
            return false;
        }
        try {
            Files.delete(Paths.get(DataController.getPath("Price"))); }
        catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("probably NOT here");
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Price")));
        return true;
    }
}
