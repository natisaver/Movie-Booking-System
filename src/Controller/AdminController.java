package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.Admin;
public class AdminController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("Admin");

    /** 
     * READ every row of Admin Database File
     * If Database file not found, ignore error and return empty list
     * @return Model.{@link Admin}  Return list of Admins if any, else empty list
    */
    public static ArrayList<Admin> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // //e.printStackTrace();
            return new ArrayList<Admin>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Admin> userArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Admin user = new Admin(tokens[0], tokens[1], tokens[4]);
                userArrayList.add(user);
            }
            reader.close();
        } catch (IOException e) {
            // //e.printStackTrace();
        }
        return userArrayList;
    }

    /** 
     * READ and return a Admin by searching for one with matching email in the Database
     * @param email             Email of Admin to search for
     * @return Admin        Return Admin if found, else null object
     */
    public static Admin readByEmail(String email) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // //e.printStackTrace();
            return null;
        }

        // If Database Exists
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].toLowerCase().equals(email)){
                    return new Admin(tokens[0], tokens[1], tokens[4]);
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     *CREATE Admin in the database
     * @param user      User object to be added
     * @return          True if User was created, False if User already exists, email is a unique identifier
     */
    public static Boolean create(Admin user) {
        File inputFile = new File(DataController.getPath("Admin"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return false;

        } catch (IOException e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
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
                    //delete old file
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
            if (Found == false){
                writer.append(user.getName());
                writer.append(",");
                writer.append(user.getEmail());
                writer.append(",");
                writer.append("");
                writer.append(",");
                writer.append("");
                writer.append(",");
                writer.append(user.getPassword());
                writer.append(",");
                writer.append("1");
                writer.append("\n");
            }
        writer.close();
        reader.close();
        //delete old file
        Files.delete(Paths.get(DataController.getPath("Admin")));
        } catch (IOException e) {
        //e.printStackTrace();
        }
    //replace with the new file
    tempFile.renameTo(new File(DataController.getPath("Admin")));
    return true;
    }  

    /**
     *UPDATE Admin in the database
     * @param user      User object to be added
     * @return          True if User was updated, False if User doesnt exist or database is nonexistent
     */
    public static Boolean update(Admin user) {

        File inputFile = new File(DataController.getPath("Admin"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return false;

        } catch (IOException e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
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
                    writer.append("");
                    writer.append(",");
                    writer.append("");
                    writer.append(",");
                    writer.append(user.getPassword());
                    writer.append(",");
                    writer.append("1");
                    writer.append("\n");
                }
                else {
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
            Files.delete(Paths.get(DataController.getPath("Temp")));
            return false;
        }
        //delete old file
        Files.delete(Paths.get(DataController.getPath("Admin")));
        } catch (IOException e) {
        //e.printStackTrace();
        }
    //replace with the new file
    tempFile.renameTo(new File(DataController.getPath("Admin")));
    return true;
    }

    /**
     *DELETE Admin in the database
     * @param user      User object to be added
     * @return          True if User was updated, False if User doesnt exist or database is nonexistent
     */
    public static Boolean delete(Admin user) {

        File inputFile = new File(DataController.getPath("Admin"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return false;

        } catch (IOException e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[1].toLowerCase().equals(user.getEmail())) {
                    //do nothing
                    Found = true;
                }
                else {
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
        if (Found == false){
            //row not deleted
            return false;
        }
        //delete old file
        Files.delete(Paths.get(DataController.getPath("Admin")));
        } catch (IOException e) {
        //e.printStackTrace();
        }
    //replace with the new file
    tempFile.renameTo(new File(DataController.getPath("Admin")));
    return true;
    }

    /**
     *DELETE Admin by Email in the database
     * @param email     User email to be added
     * @return          True if User was updated, False if User doesnt exist or database is nonexistent
     */
    public static Boolean deleteByEmail(String email) {

        File inputFile = new File(DataController.getPath("Admin"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return false;

        } catch (IOException e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[1].toLowerCase().equals(email)) {
                    //do nothing
                    Found = true;
                }
                else {
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
        if (Found == false){
            //row not deleted
            return false;
        }
        //delete old file
        Files.delete(Paths.get(DataController.getPath("Admin")));
        } catch (IOException e) {
        //e.printStackTrace();
        }
    //replace with the new file
    tempFile.renameTo(new File(DataController.getPath("Admin")));
    return true;
    }
}   

