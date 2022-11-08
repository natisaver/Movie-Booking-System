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
     * READ every row of Review Database File
     * If Database file not found, ignore error and return empty list
     * 
     * @return Model.{@link Review} Return list of Review if any, else empty list
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                LocalDateTime date = LocalDateTime.parse(tokens[0] + " 00:00", formatter);
                String name = tokens[1].substring(1, tokens[1].length()-1);
                String email = tokens[2].substring(1, tokens[2].length()-1);
                Movie movie = MovieController.readByTitle(tokens[3].substring(1, tokens[3].length()-1));
                String review = tokens[4].substring(1, tokens[4].length()-1);
                double rating = Double.parseDouble(tokens[5]);
                reviewArrayList.add(new Review(date, name, email, movie, review, rating));
                // System.out.println(tokens[0] + tokens[4]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewArrayList;
    }

    /**
     * READ every row of Review Database File matching the email
     * 
     * @param email Email of MovieGoer to be searched
     * @return Model.{@link Review} Return list of Review if any, else empty list
     */
    public static ArrayList<Review> readByEmail(String email) {
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

        try {
            ArrayList<Review> templist = new ArrayList<Review>();
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                if (tokens[2].substring(1, tokens[2].length()-1).toLowerCase().equals(email.toLowerCase())) {
                    LocalDateTime date = LocalDateTime.parse(tokens[0], formatter);
                    String name = tokens[1].substring(1, tokens[1].length()-1);
                    String emailinput = tokens[2].substring(1, tokens[2].length()-1);
                    Movie movie = MovieController.readByTitle(tokens[3].substring(1, tokens[3].length()-1));
                    String review = tokens[4].substring(1, tokens[4].length()-1);
                    double rating = Double.parseDouble(tokens[5]);
                    templist.add(new Review(date, name, emailinput, movie, review, rating));
                }
            }
            reader.close();
            return templist;
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<Review>();
        }
    }

    /**
     * READ every row of Review Database File matching the Movie Title
     * 
     * @param title Movie Title to be searched
     * @return Model.{@link Review} Return list of Review if any, else empty list
     */
    public static ArrayList<Review> readByTitle(String title) {
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

        try {
            ArrayList<Review> templist = new ArrayList<Review>();
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                if (tokens[3].substring(1, tokens[3].length()-1).toLowerCase().equals(title.toLowerCase())) {
                    LocalDateTime date = LocalDateTime.parse(tokens[0], formatter);
                    String name = tokens[1].substring(1, tokens[1].length()-1);
                    String emailinput = tokens[2].substring(1, tokens[2].length()-1);
                    Movie movie = MovieController.readByTitle(tokens[3].substring(1, tokens[3].length()-1));
                    String review = tokens[4].substring(1, tokens[4].length()-1);
                    double rating = Double.parseDouble(tokens[5]);
                    templist.add(new Review(date, name, emailinput, movie, review, rating));
                }
            }
            reader.close();
            return templist;
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<Review>();
        }
    }

    /**
     * CREATE Review in the database
     * 
     * @param review Review object to be added
     * @return <code>true</code> if Review was created, <code>false</code> if Review
     *         already exists
     *         email and movie title is a unique identifier
     */
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[3].toLowerCase().equals(review.getMovie().getTitle().toLowerCase()) &&
                tokens[2].toLowerCase().equals(review.getEmail().toLowerCase())) {
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
                writer.append(review.getDate().format(formatter));
                writer.append(",");
                writer.append('"' + review.getName() + '"');
                writer.append(",");
                writer.append('"' + review.getEmail() + '"');
                writer.append(",");
                writer.append('"' + review.getMovie().getTitle() + '"');
                writer.append(",");
                writer.append('"' + review.getReview() + '"');
                writer.append(",");
                writer.append(Float.toString((float) review.getRating()));
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

    /**
     * DELETE Review by Email and Title in the database
     * @param title     Movie Title of Review to be deleted
     * @param email     Email address of Reviewer
     * @return          <code>true</code> if Review was deleted, <code>false</code> if Review doesnt exist or database is nonexistent
     */
    public static Boolean deleteByTitleEmail(String title, String email) {

        File inputFile = new File(DataController.getPath("Review"));
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
            // e.printStackTrace();
            return false;
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[2].toLowerCase().equals(email.toLowerCase()) && tokens[3].toLowerCase().equals(title.toLowerCase())) {
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
            // e.printStackTrace();
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Review")));
        return true;
    }
}