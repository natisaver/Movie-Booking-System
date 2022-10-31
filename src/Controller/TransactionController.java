package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.Ticket;
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
     * @return Model.{@link Transaction} Return list of Transactions if any, else
     *         empty list
     */
    public static ArrayList<Transaction> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Transaction>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Transaction> userArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // Transaction transaction = new Transaction(tokens[0], tokens[1], tokens[2],
                // tokens[3], tokens[4], tokens[5]);
                // userArrayList.add(user);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userArrayList;
    }

    /**
     * READ and return a Transaction by searching for one with matching MovieGoer
     * name in the Database
     * 
     * @param name name of MovieGoer to search for
     * @return Transaction Return Transaction if found, else null object
     */
    public static Transaction readByName(String name) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // If Database Exists
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].equals(name)) {
                    return new Transaction(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * CREATE Transaction in the database
     * 
     * @param transaction Transaction object to be added
     * @return <code>true</code> if Transaction was created, <code>false</code> if
     *         Transaction already exists, transactionID is a unique identifier
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
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("transactionID");
            writer.append(",");
            writer.append("MovieGoerName");
            writer.append(",");
            writer.append("NoOfTickets");
            writer.append(",");
            writer.append("MovieTitle");
            writer.append(",");
            writer.append("Showtime");
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

                if (tokens[0].equals(transaction.getTID())) {
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
                writer.append(",");
                writer.append(tokens[5]);
                writer.append("\n");
            }
            if (Found == false) {
                writer.append(transaction.getTID());
                writer.append(",");
                writer.append(transaction.getMovieGoer().getName());
                writer.append(",");
                // writer.append(transaction.getNoOfTickets());
                writer.append(",");
                writer.append(transaction.getTickets().toString());
                writer.append(",");
                // writer.append(transaction.getTotalPrice());
                writer.append("\n");
            }
            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Transaction")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Transaction")));
        return true;
    }

    /**
     * DELETE Transaction in the database
     * 
     * @param transaction Transaction object to be added
     * @return <code>true</code> if Transaction was updated, <code>false</code> if
     *         Transaction doesnt exist or database is nonexistent
     */
    public static Boolean delete(Transaction transaction) {

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
            writer.append("MovieGoerName");
            writer.append(",");
            writer.append("NoOfTickets");
            writer.append(",");
            writer.append("MovieTitle");
            writer.append(",");
            writer.append("Showtime");
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

                if (tokens[0].equals(transaction.getTID())) {
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
                    writer.append(",");
                    writer.append(tokens[5]);
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

    /**
     * DELETE Transaction by Email in the database
     * 
     * @param email User email to be added
     * @return True if User was updated, False if User doesnt exist or database is
     *         nonexistent
     */
    public static Boolean deleteByTransactionID(String TID) {

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
            writer.append("MovieGoerName");
            writer.append(",");
            writer.append("NoOfTickets");
            writer.append(",");
            writer.append("MovieTitle");
            writer.append(",");
            writer.append("Showtime");
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
                    writer.append(",");
                    writer.append(tokens[5]);
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
