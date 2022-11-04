package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Model.Cineplex;
import Model.Holiday;

/**
 * Reads location, cinema code and cinema type of Cineplexes from csv file in
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */
public class CineplexController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Cineplex");

    /**
     * READ and list of Cineplexes from Database
     * 
     * @return ArrayList Return array of Cineplex if database exists, else null
     *         object
     */

    // public static HashMap<Integer, Cineplex> read() {
    // // Check if database exists
    // BufferedReader reader = null;
    // try {
    // reader = new BufferedReader(new FileReader(PATH));
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // return new HashMap<Integer, Cineplex>();
    // }

    // // If Database Exists
    // String line = "";
    // HashMap<Integer, Cineplex> cineplexList = new HashMap<Integer, Cineplex>();
    // int i = 1;
    // try {
    // reader.readLine();
    // while ((line = reader.readLine()) != null) {
    // String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    // cineplexList.put(i, cineplexList.get(i));
    // System.out.println(i + ": " + tokens[0] + " (" + tokens[2] + ")");
    // i++;
    // }
    // reader.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return cineplexList;
    // }

    public static ArrayList<Cineplex> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Cineplex>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<>();
        int i = 1;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                cineplexArrayList.add(new Cineplex(null, tokens[0], tokens[0]));
                System.out.println(tokens[0] + " (" + tokens[2] + ")");
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cineplexArrayList;
    }

    public static ArrayList<Cineplex> readByLocation(String location) {

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
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<>();
        int i = 1;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].equals(location)) {
                    cineplexArrayList.add(new Cineplex(null, tokens[0], tokens[0]));
                    System.out.println(tokens[2]);
                    i++;
                }
            }
            reader.close();
            return cineplexArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return cineplexArrayList;
        }

        // // Check if database exists
        // BufferedReader reader = null;
        // try {
        // reader = new BufferedReader(new FileReader(PATH));
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // return new ArrayList<Cineplex>();
        // }

        // // If Database Exists
        // String line = "";
        // ArrayList<Cineplex> cineplexArrayList = new ArrayList<>();
        // try {
        // reader.readLine();
        // while ((line = reader.readLine()) != null) {
        // String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        // if (tokens[0].equals(location)) {
        // cineplexArrayList.add(new Cineplex(null, tokens[0], tokens[0]));
        // }

        // }

        // reader.close();
        // // for (Cineplex str : cineplexArrayList) {
        // // System.out.println(str);
        // // }
        // return cineplexArrayList;
        // } catch (IOException e) {
        // e.printStackTrace();
        // return cineplexArrayList;
        // }

    }
}