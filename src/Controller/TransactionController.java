package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.Transaction;

public class TransactionController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("Transaction");

    /**
     * READ every row of Transaction Database File
     * If Database file not found, ignore error and return empty list
     * 
     * @return ArrayList<String> Return list of Transactions if any, else
     *         empty list
     */
    public static ArrayList<String> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return new ArrayList<String>();
        }

        // If Database Exists
        String line = "";
        ArrayList<String> transactionList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String entry = "<TID: " + tokens[0] + "> " + tokens[3] + " | " + "Qty Tickets: " + tokens[2] + " | $" + tokens[4];
                transactionList.add(entry);
            }
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return transactionList;
    }

    /**
     * READ every row of Transaction Database File
     * If Database file not found, ignore error and return empty list
     * 

     * @param   email Email of MovieGoer to search for
     * @return  ArrayList<String> Return list of Transactions matching email if any, else
     *          empty list* 
     */
    public static ArrayList<String> readByEmail(String email) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return new ArrayList<String>();
        }

        // If Database Exists
        String line = "";
        ArrayList<String> transactionList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].toLowerCase().equals(email.toLowerCase())) {
                    String entry = "<TID: " + tokens[0] + "> " + tokens[3] + " | " + "Qty Tickets: " + tokens[2] + " | $" + tokens[4];
                    transactionList.add(entry);
                }
            }
            reader.close();

        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<String>();
        }
        return transactionList;
    }

    /**
     * READ every row of Transaction Database File
     * If Database file not found, ignore error and return empty list
     * Consists of Printing Feature as well.

     * @param   email Email of MovieGoer to search for
     * @return  ArrayList<String> Return list of Transactions matching email if any, else
     *          empty list* 
     */
    public static ArrayList<String> readByEmailPrint(String email) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return new ArrayList<String>();
        }

        // If Database Exists
        String line = "";
        ArrayList<String> transactionList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].toLowerCase().equals(email.toLowerCase())) {
                    String entry = "<TID: " + tokens[0] + "> " + tokens[3] + " | " + "Qty Tickets: " + tokens[2] + " | $" + tokens[4];
                    // -- PRINTS -- 
                    System.out.println(entry);
                    transactionList.add(entry);
                }
            }
            reader.close();

        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<String>();
        }
        return transactionList;
    }

    /**
     * READ every row of Transaction Database File
     * If matching Movie Title is found, return the Total Sales

     * @param   title Movie Title search for
     * @return  Integer of Sales 
     */
    public static Integer salesByTitle(String title) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return 0;
        }

        // If Database Exists
        String line = "";
        int tempsum = 0;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[3].toLowerCase().equals(title.toLowerCase())) {
                    tempsum += Integer.parseInt(tokens[2]);
                }
            }
            reader.close();

        } catch (IOException e) {
            // e.printStackTrace();
            return 0;
        }
        return tempsum;
    }
    /**
     * CREATE Transaction in the database
     * 
     * @param transaction Transaction object to be added
     * @return <code>true</code> if Transaction was created, <code>false</code> if
     *         Transaction already exists, email & TID are unique identifiers
     */

    public static Boolean create(Transaction transaction) {
        File inputFile = new File(DataController.getPath("Transaction"));
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

        try {
            writer.append("transactionID");
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("NoOfTickets");
            writer.append(",");
            writer.append("MovieTitle");
            writer.append(",");
            writer.append("TotalPrice");
            writer.append("\n");

        } catch (IOException e) {
            // e.printStackTrace();
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].equals(transaction.getTID()) && tokens[1].equals(transaction.getEmail())) {
                    Found = true;
                    writer.close();
                    reader.close();
                    // delete old file
                    Files.delete(Paths.get(DataController.getPath("Temp")));
                    return false;
                }
                writer.append(tokens[0]);
                writer.append(",");
                writer.append(tokens[1]);
                writer.append(",");
                writer.append(tokens[2]);
                writer.append(",");
                writer.append(tokens[3]);
                writer.append(",");
                writer.append(tokens[4]);
                writer.append("\n");
            }
            if (Found == false) {
                writer.append(transaction.getTID());
                writer.append(",");
                writer.append(transaction.getEmail());
                writer.append(",");
                writer.append(Integer.toString(transaction.getNoOfTickets()));
                writer.append(",");
                writer.append(transaction.getMovieTitle());
                writer.append(",");
                writer.append(Double.toString(transaction.getTotalPrice()));
                writer.append("\n");
                writer.close();
                reader.close();
                Files.delete(Paths.get(DataController.getPath("Transaction")));
                tempFile.renameTo(new File(DataController.getPath("Transaction")));
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        try {
            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Transaction")));
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Didnt manage to delete old data");
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Transaction")));
        return true;
    }


    /**
     * DELETE Transaction by TID in the database
     * 
     * @param  TID Transaction to be removed
     * @return True if TID entry was deleted, False if TID entry doesnt exist or database is
     *         nonexistent
     */
    public static Boolean deleteByTID(String TID) {

        File inputFile = new File(DataController.getPath("Transaction"));
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
            writer.append("transactionID");
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("NoOfTickets");
            writer.append(",");
            writer.append("MovieTitle");
            writer.append(",");
            writer.append("TotalPrice");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].equals(TID)) {
                    // do nothing
                    Found = true;
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
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (Found == false) {
                // row not deleted
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Transaction")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Transaction")));
        return true;
    }
}
