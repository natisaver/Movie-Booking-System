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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Movie;
import Model.Review;

public class ReviewController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("Review");

    /**
     * READ every row of Movie Database File
     * If Database file not found, ignore error and return empty list
     * 
     * @return Model.{@link Movie} Return list of Movie if any, else empty list
     */
    public static ArrayList<Review> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Review>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Review> reviewArrayList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                LocalDateTime date = LocalDateTime.parse(tokens[0], formatter);
                String name = tokens[1];
                String email = tokens[2];
                Movie movie = MovieController.readByTitle(tokens[3]);
                String review = tokens[4];
                double rating = Double.parseDouble(tokens[5]);
                reviewArrayList.add(new Review(date, name, email, movie, review, rating));
                // System.out.println(tokens[0]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewArrayList;
    }

    public static Review readByTitle(String title) {
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                if (tokens[3].toLowerCase().equals(title.toLowerCase())) {
                    String date = tokens[0];
                    String name = tokens[1];
                    String email = tokens[2];
                    Movie movie = MovieController.readByTitle(tokens[3]);
                    String review = tokens[4];
                    double rating = Double.parseDouble(tokens[5]);
                    return new Review(LocalDateTime.parse(date, formatter), name, email, movie, review, rating);
                }
            }
            reader.close();
            return null;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }

    public static Boolean create(Review review) {
        File inputFile = new File(DataController.getPath("Review"));
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
            writer.append(",");
            writer.append("Email");
            writer.append(",");
            writer.append("Movie");
            writer.append(",");
            writer.append("Review");
            writer.append(",");
            writer.append("Rating");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[3].substring(1, tokens[0].length() - 1).toLowerCase()
                        .equals(review.getMovie().getTitle().toLowerCase())) {
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
            if (Found == false) {
                writer.append(review.getDate().format(formatter));
                writer.append(",");
                writer.append('"' + review.getName() + '"');
                writer.append(",");
                writer.append('"' + review.getEmail() + '"');
                writer.append(",");
                writer.append(review.getMovie().getTitle());
                writer.append(",");

                writer.append(review.getReview());
                writer.append(",");
                writer.append(Float.toString((float) review.getRating()));
                writer.append(",");
                writer.append("0"); // Ticket sales starts at 0.
                writer.append("\n");
            }
            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Review")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Review")));
        return true;
    }

    public static Boolean deleteByTitle(String title) {

        File inputFile = new File(DataController.getPath("Review"));
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

                if (tokens[3].substring(1, tokens[3].length() - 1).equals(title.toLowerCase())) {
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
            Files.delete(Paths.get(DataController.getPath("Review")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Review")));
        return true;
    }
}