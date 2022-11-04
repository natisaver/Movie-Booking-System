package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.MovieSession;
import Model.cinemaClass_Enum;
import Model.movieRating_Enum;
import Model.movieType_Enum;
import Model.Cinema;
import Model.Movie;

/**
 * Reads movie titles, date and times, and cinema class of movie sessions from
 * csv file in the MOBLIMA Cinema Application
 * The csv file is in the format of "Movie Title", "Movie Type", "ShowDate"
 * "Showtime", "Cinema Code", "Booked Seats"
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
    public final static String cinePATH = DataController.getPath("Cineplex");

    /**
     * READ a list of movie sessions from Database
     * 
     * @return Returns array of MovieSession if database exists, else null object
     */
    public static ArrayList<MovieSession> read() {
        // Check if databases exist
        BufferedReader[] reader = new BufferedReader[2];
        try {
            reader[0] = new BufferedReader(new FileReader(PATH));
            reader[1] = new BufferedReader(new FileReader(cinePATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<MovieSession>();
        }

        // If Databases Exist
        String line = "";
        Hashtable<String, String> cineplex = new Hashtable<>();
        try {
            reader[1].readLine();
            while ((line = reader[1].readLine()) != null) {
                String[] tokens = line.split(",");
                cineplex.put(tokens[1], tokens[2]);
            }
            reader[1].close();
        } catch (IOException e) {
            //e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                String movieTitle = tokens[0];
                String movieType = tokens[1];
                String sessionDate = tokens[2];
                String sessionTime = tokens[3];
                cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                movieSessionList.add(new MovieSession(sessionDate, sessionTime, cinemaClass,
                        movieTitle, movieType));
            }

            reader[0].close();
            return movieSessionList;
        } catch (IOException e) {
            //e.printStackTrace();
            return movieSessionList;
        }
    }

    public static ArrayList<MovieSession> readByCode(String cinemaCode) {
        // Check if databases exist
        BufferedReader[] reader = new BufferedReader[2];
        try {
            reader[0] = new BufferedReader(new FileReader(PATH));
            reader[1] = new BufferedReader(new FileReader(cinePATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<MovieSession>();
        }

        // If Databases Exist
        String line = "";
        Hashtable<String, String> cineplex = new Hashtable<>();
        try {
            reader[1].readLine();
            while ((line = reader[1].readLine()) != null) {
                String[] tokens = line.split(",");
                cineplex.put(tokens[1], tokens[2]);
            }
            reader[1].close();
        } catch (IOException e) {
            //e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[4].equals(cinemaCode)) {
                    String movieTitle = tokens[0];
                    String movieType = tokens[1];
                    String sessionDate = tokens[2];
                    String sessionTime = tokens[3];
                    cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                    movieSessionList.add(new MovieSession(sessionDate, sessionTime, cinemaClass,
                        movieTitle, movieType));
                }
            }
            reader[0].close();
            return movieSessionList;
        } catch (IOException e) {
            //e.printStackTrace();
            return movieSessionList;
        }
    }

    public static ArrayList<MovieSession> readByTitle(String title) {
        // Check if databases exist
        BufferedReader[] reader = new BufferedReader[2];
        try {
            reader[0] = new BufferedReader(new FileReader(PATH));
            reader[1] = new BufferedReader(new FileReader(cinePATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<MovieSession>();
        }

        // If Databases Exist
        String line = "";
        Hashtable<String, String> cineplex = new Hashtable<>();
        try {
            reader[1].readLine();
            while ((line = reader[1].readLine()) != null) {
                String[] tokens = line.split(",");
                cineplex.put(tokens[1], tokens[2]);
            }
            reader[1].close();
        } catch (IOException e) {
            //e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals(title)) {
                    String movieTitle = tokens[0];
                    String movieType = tokens[1];
                    String sessionDate = tokens[2];
                    String sessionTime = tokens[3];
                    cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                    movieSessionList.add(new MovieSession(sessionDate, sessionTime, cinemaClass,
                        movieTitle, movieType));
                }
            }
            reader[0].close();
            return movieSessionList;
        } catch (IOException e) {
            //e.printStackTrace();
            return movieSessionList;
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
        File inputFile = new File(DataController.getPath("MovieSession"));
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
            writer.append("movieType");
            writer.append(",");
            writer.append("ShowDate");
            writer.append(",");
            writer.append("Showtime");
            writer.append(",");
            writer.append("cinemaCode");
            writer.append(",");
            writer.append("bookedSeats");
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
            Files.delete(Paths.get(DataController.getPath("MovieSession")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("MovieSession")));
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

        File inputFile = new File(DataController.getPath("MovieSession"));
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
