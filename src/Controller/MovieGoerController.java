package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.MovieGoer;

/**
 * Controller for CRUD operations managing data relating to MovieGoer
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 17-10-2022
 */

public class MovieGoerController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("MovieGoer");

    /**
     * READ every row of MovieGoer Database File
     * If Database file not found, ignore error and return empty list
     * 
     * @return Model.{@link MovieGoer} Return list of MovieGoers if any, else empty
     *         list
     */
    public static ArrayList<MovieGoer> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return new ArrayList<MovieGoer>();
        }

        // If Database Exists
        String line = "";
        ArrayList<MovieGoer> userArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                MovieGoer user = new MovieGoer(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                userArrayList.add(user);
            }
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return userArrayList;
    }

    /**
     * READ and return a MovieGoer by searching for one with matching email in the
     * Database
     * 
     * @param email Email of MovieGoer to search for
     * @return Return MovieGoer if found, else null object
     */
    public static MovieGoer readByEmail(String email) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return null;
        }

        // If Database Exists
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].toLowerCase().equals(email)) {
                    return new MovieGoer(tokens[0], tokens[1], tokens[2], Integer.valueOf(tokens[3]), tokens[4]);
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * CREATE MovieGoer in the database
     * 
     * @param user User object to be added
     * @return <code>true</code> if User was created, <code>false</code> if User
     *         already exists
     *         email is a unique identifier
     */
    public static Boolean create(MovieGoer user) {
        File inputFile = new File(DataController.getPath("MovieGoer"));
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
            writer.append("Name");
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("PhoneNum");
            writer.append(",");
            writer.append("Age");
            writer.append(",");
            writer.append("Password");
            writer.append(",");
            writer.append("Role");
            writer.append("\n");

        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[1].toLowerCase().equals(user.getEmail())) {
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
                writer.append(user.getName());
                writer.append(",");
                writer.append(user.getEmail());
                writer.append(",");
                writer.append(user.getPhoneNum());
                writer.append(",");
                writer.append(String.valueOf(user.getAge()));
                writer.append(",");
                writer.append(user.getPassword());
                writer.append(",");
                writer.append("0");
                writer.append("\n");
            }
        } catch (IOException e) {
            return false;
        }
        try {
            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("MovieGoer")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("didnt manage to delete old data");
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieGoer")));
        return true;
    }

    /**
     * UPDATE MovieGoer in the database
     * 
     * @param user MovieGoer object to be updated
     * @return <code>true</code> if MovieGoer was updated, <code>false</code> if
     *         MovieGoer doesnt exist or database is nonexistent
     */
    public static Boolean update(MovieGoer user) {

        File inputFile = new File(DataController.getPath("MovieGoer"));
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
            writer.append("Name");
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("PhoneNum");
            writer.append(",");
            writer.append("Age");
            writer.append(",");
            writer.append("Password");
            writer.append(",");
            writer.append("Role");
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

                if (tokens[1].toLowerCase().equals(user.getEmail())) {
                    Found = true;
                    writer.append(user.getName());
                    writer.append(",");
                    writer.append(user.getEmail());
                    writer.append(",");
                    writer.append(user.getPhoneNum());
                    writer.append(",");
                    writer.append(String.valueOf(user.getAge()));
                    writer.append(",");
                    writer.append(user.getPassword());
                    writer.append(",");
                    writer.append("0");
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
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (Found == false) {
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("MovieGoer")));
        } catch (IOException e) {
            // e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieGoer")));
        return true;
    }

    /**
     * DELETE MovieGoer in the database
     * 
     * @param user MovieGoer object to be deleted
     * @return <code>true</code> if MovieGoer was deleted, <code>false</code> if
     *         MovieGoer doesnt exist or database is nonexistent
     */
    public static Boolean delete(MovieGoer user) {

        File inputFile = new File(DataController.getPath("MovieGoer"));
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
            writer.append("Name");
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("PhoneNum");
            writer.append(",");
            writer.append("Age");
            writer.append(",");
            writer.append("Password");
            writer.append(",");
            writer.append("Role");
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

                if (tokens[1].toLowerCase().equals(user.getEmail())) {
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
            Files.delete(Paths.get(DataController.getPath("MovieGoer")));
        } catch (IOException e) {
            // e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieGoer")));
        return true;
    }

    /**
     * <<<<<<< HEAD
     * DELETE MovieGoer by Email in the database
     * 
     * @param email Email of MovieGoer to be deleted
     * @return <code>true</code> if MovieGoer was deleted, <code>false</code> if
     *         MovieGoer doesnt exist or database is nonexistent
     *         =======
     *         DELETE MovieGoer by Email in the database
     * 
     * @param email User email to be added
     * @return True if User was updated, False if User doesnt exist or database is
     *         nonexistent
     *         >>>>>>> 352355738788b608b51d3b96a167c7e64e111cbd
     */
    public static Boolean deleteByEmail(String email) {

        File inputFile = new File(DataController.getPath("MovieGoer"));
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
            writer.append("Name");
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("PhoneNum");
            writer.append(",");
            writer.append("Age");
            writer.append(",");
            writer.append("Password");
            writer.append(",");
            writer.append("Role");
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

                if (tokens[1].toLowerCase().equals(email)) {
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
            Files.delete(Paths.get(DataController.getPath("MovieGoer")));
        } catch (IOException e) {
            // e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieGoer")));
        return true;
    }
}
