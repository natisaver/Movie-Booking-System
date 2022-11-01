package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;
import Model.showingStatus_Enum;

/**
 Controller for CRUD operations managing data relating to Movie 
 @author Sally Carrera 
 @version 1.0
 @since 28-10-2022
 */

public class MovieController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("Movie");

    /** 
     * READ every row of Movie Database File
     * If Database file not found, ignore error and return empty list
     * @return Model.{@link Movie}  Return list of Movie if any, else empty list
    */
    public static ArrayList<Movie> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Movie>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Movie movie = new Movie(tokens[0], tokens[1], line.split(tokens[2]), LocalDateTime.parse(tokens[3] + "00:00", formatter), LocalDateTime.parse(tokens[4] + "00:00", formatter), tokens[5], Integer.parseInt(tokens[6]), showingStatus_Enum.valueOf(tokens[7]), movieType_Enum.valueOf(tokens[8]), movieRating_Enum.valueOf(tokens[9]), Integer.parseInt(tokens[10]));
                movieArrayList.add(movie);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieArrayList;
    }

    public static Movie readByTitle(String Title) {
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[1].equals(Title)){
                    return new Movie(tokens[0], tokens[1], line.split(tokens[2]), LocalDateTime.parse(tokens[3] + "00:00", formatter), LocalDateTime.parse(tokens[4] + "00:00", formatter), tokens[5], Integer.parseInt(tokens[6]), showingStatus_Enum.valueOf(tokens[7]), movieType_Enum.valueOf(tokens[8]), movieRating_Enum.valueOf(tokens[9]), Integer.parseInt(tokens[10]));
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean create(Movie movie)
    {
        File inputFile = new File(DataController.getPath("Movie"));
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
            writer.append("Title");
            writer.append(",");
            writer.append("Director");
            writer.append(",");
            writer.append("Cast");
            writer.append(",");
            writer.append("releaseDate");
            writer.append(",");
            writer.append("endDate");
            writer.append(",");
            writer.append("synopsis");
            writer.append(",");
            writer.append("duration");
            writer.append(",");
            writer.append("showingStatus");
            writer.append(",");
            writer.append("movieType");
            writer.append(",");
            writer.append("movieRating");
            writer.append(",");
            writer.append("ticketSales");
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
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[1].equals(movie.getTitle())) {
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
                writer.append(",");
                writer.append(tokens[6]);
                writer.append(",");
                writer.append(tokens[7]);
                writer.append(",");
                writer.append(tokens[8]);
                writer.append(",");
                writer.append(tokens[9]);
                writer.append(",");
                writer.append(tokens[10]);
                writer.append("\n");
            }
            if (Found == false){
                writer.append(movie.getTitle());
                writer.append(",");
                writer.append(movie.getDirector());
                writer.append(",");
                writer.append(Arrays.toString(movie.getCast()));
                writer.append(",");
                writer.append(movie.getReleaseDate().format(formatter));
                writer.append(",");
                writer.append(movie.getEndDate().format(formatter));
                writer.append(",");
                writer.append(movie.getSynopsis());
                writer.append(",");
                writer.append(Integer.toString(movie.getDuration()));
                writer.append(",");
                writer.append(movie.getShowingStatus().name());
                writer.append(",");
                writer.append(movie.getMovieType().name());
                writer.append(",");
                writer.append(movie.getMovieRating().name());
                writer.append(",");
                writer.append("0"); //Ticket sales starts at 0.
                writer.append("\n");
            }
        writer.close();
        reader.close();
        //delete old file
        Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
        e.printStackTrace();}
    //replace with the new file
    tempFile.renameTo(new File(DataController.getPath("Movie")));
    return true;
    }
    
    // Need help with update class :<
    /*public static Boolean update(Movie movie)
    {
        File inputFile = new File(DataController.getPath("Movie"));
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
            writer.append("Title");
            writer.append(",");
            writer.append("Director");
            writer.append(",");
            writer.append("Cast");
            writer.append(",");
            writer.append("releaseDate");
            writer.append(",");
            writer.append("endDate");
            writer.append(",");
            writer.append("synopsis");
            writer.append(",");
            writer.append("duration");
            writer.append(",");
            writer.append("showingStatus");
            writer.append(",");
            writer.append("movieType");
            writer.append(",");
            writer.append("movieRating");
            writer.append(",");
            writer.append("ticketSales");
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
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[1].equals(movie.getTitle())) {
                    Found = true;
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
                    writer.append(",");
                    writer.append(tokens[7]);
                    writer.append(",");
                    writer.append(tokens[8]);
                    writer.append(",");
                    writer.append(tokens[9]);
                    writer.append(",");
                    writer.append(tokens[10]);
                    writer.append("\n");
            }
            if (Found == false){
                writer.append(movie.getTitle());
                writer.append(",");
                writer.append(movie.getDirector());
                writer.append(",");
                writer.append(Arrays.toString(movie.getCast()));
                writer.append(",");
                writer.append(movie.getReleaseDate().format(formatter));
                writer.append(",");
                writer.append(movie.getEndDate().format(formatter));
                writer.append(",");
                writer.append(movie.getSynopsis());
                writer.append(",");
                writer.append(Integer.toString(movie.getDuration()));
                writer.append(",");
                writer.append(movie.getShowingStatus().name());
                writer.append(",");
                writer.append(movie.getMovieType().name());
                writer.append(",");
                writer.append(movie.getMovieRating().name());
                writer.append(",");
                writer.append("0"); //Ticket sales starts at 0.
                writer.append("\n");
            }
        writer.close();
        reader.close();
        //delete old file
        Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
        e.printStackTrace();}
    //replace with the new file
    tempFile.renameTo(new File(DataController.getPath("Movie")));
    return true;
    }*/

    public static Boolean delete(Movie movie) {

        File inputFile = new File(DataController.getPath("Movie"));
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
            writer.append("Title");
            writer.append(",");
            writer.append("Director");
            writer.append(",");
            writer.append("Cast");
            writer.append(",");
            writer.append("releaseDate");
            writer.append(",");
            writer.append("endDate");
            writer.append(",");
            writer.append("synopsis");
            writer.append(",");
            writer.append("duration");
            writer.append(",");
            writer.append("showingStatus");
            writer.append(",");
            writer.append("movieType");
            writer.append(",");
            writer.append("movieRating");
            writer.append(",");
            writer.append("ticketSales");
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

                if (tokens[1].equals(movie.getTitle())) {
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
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append(",");
                    writer.append(tokens[7]);
                    writer.append(",");
                    writer.append(tokens[8]);
                    writer.append(",");
                    writer.append(tokens[9]);
                    writer.append(",");
                    writer.append(tokens[10]);
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (Found == false) {
                // movie not deleted
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }

    public static Boolean deleteByTitle(String title) {

        File inputFile = new File(DataController.getPath("Movie"));
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
            writer.append("Title");
            writer.append(",");
            writer.append("Director");
            writer.append(",");
            writer.append("Cast");
            writer.append(",");
            writer.append("releaseDate");
            writer.append(",");
            writer.append("endDate");
            writer.append(",");
            writer.append("synopsis");
            writer.append(",");
            writer.append("duration");
            writer.append(",");
            writer.append("showingStatus");
            writer.append(",");
            writer.append("movieType");
            writer.append(",");
            writer.append("movieRating");
            writer.append(",");
            writer.append("ticketSales");
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

                if (tokens[1].equals(title)) {
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
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append(",");
                    writer.append(tokens[7]);
                    writer.append(",");
                    writer.append(tokens[8]);
                    writer.append(",");
                    writer.append(tokens[9]);
                    writer.append(",");
                    writer.append(tokens[10]);
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
            Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }
}