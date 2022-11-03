package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.MovieSession;
import Model.movieRating_Enum;
import Model.movieType_Enum;
import Model.Cinema;
import Model.Movie;

/**
 * Reads movie titles, date and times, and cinema class of movie sessions from
 * csv file in the MOBLIMA Cinema Application
 * The csv file is in the format of "Cinema Code", "Movie Title", "Movie Type",
 * "Session Date", "Session Start Time"
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */
public class MovieSessionController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("MovieSession");

    /**
     * READ a list of movie sessions from Database
     * 
     * @param cinemaCode Cinema's code
     * @return Returns array of MovieSession if database exists, else null object
     */
    public static ArrayList<MovieSession> read(Cinema cinema) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<MovieSession>();
        }

        // If Database Exists
        String line = "";
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String movieTitle = tokens[0];
                String movieType = tokens[1];
                String sessionDate = tokens[2];
                String sessionTime = tokens[3];
                // String dateTime = date + " " + startTime;
                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy
                // HH:mm");
                // String sessionTime = dateTime;
                movieSessionList.add(new MovieSession(sessionDate, sessionTime, cinema.getCinemaClass(),
                        movieTitle, movieType));
                System.out.println(tokens[0]);
            }

            reader.close();
            return movieSessionList;
        } catch (IOException e) {
            e.printStackTrace();
            return movieSessionList;
        }

    }

    public static ArrayList<MovieSession> readbyMovieTitle(Cinema cinema, String movieTitle,
            movieType_Enum movieType) {

        // Cinema cinema = new Cinema(cinemaCode, null, null);

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
        ArrayList<MovieSession> sessionArrayList = new ArrayList<>();
        // transactionArrayList = null;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].equals(movieTitle) || tokens[1].equals(movieType.toString())) {
                    System.out.println(tokens[0] + " " + tokens[2]);
                    sessionArrayList.add(new MovieSession(tokens[2], tokens[3], Cinema.getCinemaClass(),
                            movieTitle, movieType.toString()));
                }
            }
            reader.close();
            return sessionArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<MovieSession> readbyShowtime(Cinema cinema, String movieTitle,
            movieType_Enum movieType, String showtime) {

        // Cinema cinema = new Cinema(cinemaCode, null, null);

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
        ArrayList<MovieSession> sessionArrayList = new ArrayList<>();
        // transactionArrayList = null;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].equals(movieTitle) && tokens[1].equals(movieType.toString())
                        && tokens[2].equals(showtime)) {

                    String sessionDate = tokens[2];
                    String sessionTime = tokens[3];
                    System.out.println(tokens[0] + " " + tokens[2]);
                    sessionArrayList.add(new MovieSession(sessionDate, sessionTime, cinema.getCinemaClass(),
                            movieTitle, movieType.toString()));
                    // String date = tokens[3];
                    // String startTime = tokens[4];
                    // String dateTime = date + " " + startTime;
                    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy
                    // HH:mm");
                    // LocalDateTime sessionTime = LocalDateTime.parse(showtime, formatter);
                    // sessionArrayList.add(new MovieSession(sessionTime, cinema.getCinemaClass(),
                    // movieTitle, movieType.toString()));
                }
            }
            reader.close();
            return sessionArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * CREATE MovieSession in the database
     * 
     * @param cinema  Cinema in which MovieSession will run in
     * @param session MovieSession to be added
     * @return <code>true</code> if MovieSession was added, <code>false</code> if
     *         MovieSession clashes with an existing MovieSession
     */
    public static Boolean createSession(Cinema cinema, MovieSession session) {
        File inputFile = new File(DataController.getPath("MovieSessions"));
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
            writer.append("Cinema Code");
            writer.append(",");
            writer.append("Movie Title");
            writer.append(",");
            writer.append("Movie Type");
            writer.append(",");
            writer.append("Session Date");
            writer.append(",");
            writer.append("Session Start Time");
            writer.append(",");
            writer.append("Session End Time");
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
                String[] tokens = line.split(",");
                LocalDateTime sessionTime = LocalDateTime.parse(tokens[2], formatter);
                if ((tokens[0].equals(cinema.getCinemaCode())) && (sessionTime.equals(session.getShowtime()))) {
                    Found = true;
                }
                writer.append(tokens[0]);
                writer.append(",");
                writer.append(tokens[1]);
                writer.append(",");
                writer.append(tokens[2]);
                writer.append("\n");
            }
            if (!Found) {
                writer.append(cinema.getCinemaCode());
                writer.append(",");
                writer.append(session.getTitle());
                writer.append(",");
                writer.append(session.getShowtime().format(formatter));
                writer.append("\n");
            }
            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("MovieSessions")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieSessions")));
        return true;
    }

    /**
     * UPDATE MovieSession in the database
     * 
     * @param cinema  Cinema in which MovieSession to be updated runs in
     * @param session MovieSession object to be updated
     * @return <code>true</code> if MovieSession was updated, <code>false</code> if
     *         MovieSession doesnt exist or database is nonexistent
     */
    public static Boolean update(Cinema cinema, MovieSession session) {

        File inputFile = new File(DataController.getPath("MovieSessions"));
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
            writer.append("Cinema Code");
            writer.append(",");
            writer.append("Movie Title");
            writer.append(",");
            writer.append("Movie Type");
            writer.append(",");
            writer.append("Session Date");
            writer.append(",");
            writer.append("Session Start Time");
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
                String date = tokens[3];
                String startTime = tokens[4];
                String dateTime = date + " " + startTime;
                LocalDateTime sessionTime = LocalDateTime.parse(dateTime, formatter);
                if ((tokens[0].equals(cinema.getCinemaCode())) && (sessionTime.equals(session.getShowtime()))) {
                    Found = true;
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(session.getTitle());
                    writer.append(",");
                    writer.append(session.getMovieType().name());
                    writer.append(",");
                    writer.append(date);
                    writer.append(",");
                    writer.append(startTime);
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
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (!Found) {
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("MovieSessions")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieSessions")));
        return true;
    }

    /**
     * DELETE MovieSession in the database
     * 
     * @param cinema  Cinema in which MovieSession to be deleted runs in
     * @param session MovieSession object to be deleted
     * @return <code>true</code> if MovieSession was deleted, <code>false</code> if
     *         MovieSession doesnt exist or database is nonexistent
     */
    public static Boolean delete(Cinema cinema, MovieSession session) {

        File inputFile = new File(DataController.getPath("MovieSessions"));
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
            writer.append("Cinema Code");
            writer.append(",");
            writer.append("Movie Title");
            writer.append(",");
            writer.append("Movie Type");
            writer.append(",");
            writer.append("Session Date");
            writer.append(",");
            writer.append("Session Start Time");
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
                String date = tokens[3];
                String startTime = tokens[4];
                String dateTime = date + " " + startTime;
                LocalDateTime sessionTime = LocalDateTime.parse(dateTime, formatter);
                if ((tokens[0].equals(cinema.getCinemaCode())) && (sessionTime.equals(session.getShowtime()))) {
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
            if (!Found) {
                // Session not deleted
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("MovieGoer")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieGoer")));
        return true;
    }
}
