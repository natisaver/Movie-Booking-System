package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.Cinema;
import Model.MovieSession;
import Model.Seat;
import Model.cinemaClass_Enum;
import Model.movieType_Enum;

/**
 * CRUD Operations for Movie Sessions
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
     * @return {@link ArrayList} of {@link MovieSession} objects else return empty {@link ArrayList}
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
            // e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                String movieTitle = tokens[0];
                String movieType = tokens[1];
                LocalDateTime datetime = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                movieSessionList.add(new MovieSession(datetime, cinemaClass, movieTitle, movieType));
            }

            reader[0].close();
            return movieSessionList;
        } catch (IOException e) {
            // e.printStackTrace();
            return movieSessionList;
        }
    }

    /**
     * READ a list of movie sessions from Database with specified cinema code
     * 
     * @param cinemaCode {@link String} of the cinema code to search for
     * @return {@link ArrayList} of {@link MovieSession} objects else return empty {@link ArrayList}
     */

    public static ArrayList<MovieSession> readByCode(String cinemaCode) {
        // Check if databases exist
        String line = "";
        Hashtable<String, String> cineplex = new Hashtable<String, String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cinePATH))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                cineplex.put(tokens[1], tokens[2]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<MovieSession>();
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<MovieSession>();
        }

        // If Databases Exist
        ArrayList<MovieSession> movieSessionList = new ArrayList<MovieSession>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[4].equals(cinemaCode)) {
                    String movieTitle = tokens[0];
                    String movieType = tokens[1];
                    LocalDateTime datetime = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                    cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                    movieSessionList.add(new MovieSession(datetime, cinemaClass, movieTitle, movieType));
                }
            }
            reader.close();
            return movieSessionList;
        } catch (IOException e) {
            // e.printStackTrace();
            return movieSessionList;
        }
    }

    /**
     * READ a list of movie sessions from Database with specified movie title in a cinema
     * 
     * @param cinemaCode {@link String} of the cinema code to search for
     * @param movieTitle {@link String} of the movie title to search for
     * @return {@link ArrayList} of {@link MovieSession} objects else return empty {@link ArrayList}
     */

    public static ArrayList<MovieSession> readByCodeTitle(String cinemaCode, String movieTitle) {
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
            // e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[4].equals(cinemaCode) && tokens[0].toLowerCase().equals(movieTitle.toLowerCase())) {
                    String movieType = tokens[1];
                    LocalDateTime datetime = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                    cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                    movieSessionList.add(new MovieSession(datetime, cinemaClass, movieTitle, movieType));
                }
            }
            reader[0].close();
            return movieSessionList;
        } catch (IOException e) {
            // e.printStackTrace();
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
            // e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals(title)) {
                    String movieTitle = tokens[0];
                    String movieType = tokens[1];
                    LocalDateTime datetime = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                    cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(tokens[4]));
                    movieSessionList.add(new MovieSession(datetime, cinemaClass, movieTitle, movieType));
                }
            }
            reader[0].close();
            return movieSessionList;
        } catch (IOException e) {
            // e.printStackTrace();
            return movieSessionList;
        }
    }

    /**
     * CREATE MovieSession in the database
     * 
     * @param cinemaCode Cinema Code of cinema in which MovieSession will run in
     * @param session    MovieSession to be added
     * @return <code>true</code> if MovieSession was added, else <code>false</code> if
     *         MovieSession clashes with an existing MovieSession
     */
    public static Boolean createSession(String cinemaCode, MovieSession session) {
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

        Boolean Clash = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
            reader.readLine();
            LocalDateTime sessionTime = session.getShowtime();
            LocalDateTime sessionTimeEnd = sessionTime
                    .plusMinutes(MovieController.readByTitle(session.getTitle()).getDuration());
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                LocalDateTime lower = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                LocalDateTime upper = lower.plusMinutes(MovieController.readByTitle(tokens[0]).getDuration());
                if ((tokens[4].equals(cinemaCode)) &&
                        (((sessionTime.isAfter(lower) || sessionTime.equals(lower))
                                && (sessionTime.isBefore(upper) || sessionTime.equals(upper))) ||
                                ((sessionTimeEnd.isAfter(lower) || sessionTimeEnd.equals(lower))
                                        && (sessionTimeEnd.isBefore(upper) || sessionTimeEnd.equals(upper))))) {
                    Clash = true;
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
            if (!Clash) {
                String dateTime = session.getShowtime().format(formatter);
                writer.append(session.getTitle());
                writer.append(",");
                writer.append(session.getMovieType().name());
                writer.append(",");
                writer.append(dateTime.substring(0, 10));
                writer.append(",");
                writer.append(dateTime.substring(11));
                writer.append(",");
                writer.append(cinemaCode);
                writer.append(",");
                writer.append("\"\"");
                writer.append("\n");
            }
            writer.close();
            reader.close();
            if (Clash) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                return false;
            }
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
     * @param cinemaCode Cinema Code of cinema in which MovieSession to be updated
     *                   runs in
     * @param session    {@link MovieSession} to be updated
     * @return           <code>true</code> if MovieSession was updated, else
     *                  <code>false</code> if MovieSession doesnt exist
     */
    public static Boolean update(String cinemaCode, MovieSession session) {

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

        Boolean Found = false;
        Boolean Clash = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
            reader.readLine();
            LocalDateTime sessionTime = session.getShowtime();
            LocalDateTime sessionTimeEnd = sessionTime
                    .plusMinutes(MovieController.readByTitle(session.getTitle()).getDuration());
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                LocalDateTime lower = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                LocalDateTime upper = lower.plusMinutes(MovieController.readByTitle(tokens[0]).getDuration());
                if ((tokens[4].equals(cinemaCode)) &&
                        (((sessionTime.isAfter(lower) || sessionTime.equals(lower))
                                && (sessionTime.isBefore(upper) || sessionTime.equals(upper))) ||
                                ((sessionTimeEnd.isAfter(lower) || sessionTimeEnd.equals(lower))
                                        && (sessionTimeEnd.isBefore(upper) || sessionTimeEnd.equals(upper))))) {
                    Clash = true;
                    break;
                }
                if ((tokens[4].equals(cinemaCode)) && (sessionTime.equals(lower))) {
                    if (!tokens[5].equals("\"\"")) {
                        System.out.println("This session already has bookings!");
                        Clash = true;
                        break;
                    }
                    String dateTime = sessionTime.format(formatter);
                    writer.append(session.getTitle());
                    writer.append(",");
                    writer.append(session.getMovieType().name());
                    writer.append(",");
                    writer.append(dateTime.substring(0, 10));
                    writer.append(",");
                    writer.append(dateTime.substring(11));
                    writer.append(",");
                    writer.append(cinemaCode);
                    writer.append(",");
                    writer.append("\"\"");
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
            if (Clash) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                System.out.println("Movie session clashes with existing movie sessions!");
                return false;
            }
            if (!Found) {
                System.out.println("Movie session not found!");
                return false;
            }
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
     * UPDATE MovieSession' MovieType by Title in the database
     * 
     * @param movieTitle Movie Title
     * @param movieType  {@link movieType_Enum} to be updated
     * @return <code>true</code> if MovieSession was updated, else <code>false</code> if
     *         MovieSession doesnt exist or database is nonexistent
     */
    public static Boolean updateMovieTypeByTitle(String movieTitle, movieType_Enum movieType) {

        File inputFile = new File(DataController.getPath("MovieSession"));
        File tempFile2 = new File(DataController.getPath("Temp2"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Boolean Found = false;
        String line;

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (tokens[0].toLowerCase().equals(movieTitle.toLowerCase())) {
                    Found = true;
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(movieType.name());
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
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
            if (!Found) {
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("MovieSession")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile2.renameTo(new File(DataController.getPath("MovieSession")));
        return true;
    }

    /**
     * DELETE MovieSession in the database
     * 
     * @param cinemaCode Cinema Code of cinema in which MovieSession to be deleted
     *                   runs in
     * @param session    {@link MovieSession} to be deleted
     * @return           <code>true</code> if MovieSession was deleted, else
     *                  <code>false</code> if MovieSession doesnt exist or database is nonexistent
     */
    public static Boolean delete(String cinemaCode, MovieSession session) {

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

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                LocalDateTime sessionTime = LocalDateTime.parse(tokens[2] + " " + tokens[3], formatter);
                if ((tokens[4].equals(cinemaCode)) && (sessionTime.equals(session.getShowtime()))) {
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
            if (!Found) {
                // Session not deleted
                return false;
            }
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
     * Display occupied seats of a MovieSession in a cinema
     * 
     * @param cinemaCode Cinema Code of cinema in which seats of the MovieSession to be displayed are in
     * @param session    {@link MovieSession} to be displayed
     * @return           {@link ArrayList} of {@link Seat} that are occupied else return empty {@link ArrayList}
     */
    
    public static ArrayList<Seat> displaySeats(String cinemaCode, MovieSession session) {
        String line = "";
        Hashtable<String, String> cineplex = new Hashtable<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cinePATH))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                cineplex.put(tokens[1], tokens[2]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return new ArrayList<Seat>();
        } catch (IOException e) {
            // e.printStackTrace();
        }

        // If Databases Exist
        cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(cinemaCode));
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (cinemaCode.equals(tokens[4]) && tokens[2].equals(session.getSessionDate())
                        && tokens[3].equals(session.getSessionTime())) {
                    String seatStr = tokens[5].substring(1, tokens[5].length() - 1);
                    if (seatStr.isEmpty()) {
                        session.showSeatings(cinemaClass);
                        return seatList;
                    } else {
                        String[] seats = seatStr.split(",");
                        for (int i = 0; i < seats.length; i++) {
                            seatList.addAll(session.bookSeat(seats[i], cinemaClass));
                        }
                        session.showSeatings(cinemaClass);
                        return seatList;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return seatList;
    }

    /**
     * Books multiple seats in a MovieSession in a cinema
     * 
     * @param cinemaCode Cinema Code of cinema in which seats of the MovieSession to be displayed are in
     * @param session    {@link MovieSession} to be displayed
     * @param seatList   {@link ArrayList} of {@link Seat} to be booked
     */
    public static void bookSeats(String cinemaCode, MovieSession session, ArrayList<Seat> seatList) {
        File tempFile = new File(DataController.getPath("Temp"));
        BufferedWriter writer = null;
        String line;
        String seatStr = "";
        Hashtable<String, String> cineplex = new Hashtable<>();
        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (IOException e) {
            // e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(cinePATH))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                cineplex.put(tokens[1], tokens[2]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }

        // If Databases Exist

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (cinemaCode.equals(tokens[4]) && tokens[2].equals(session.getSessionDate())
                        && tokens[3].equals(session.getSessionTime())) {
                    for (int i = 0; i < seatList.size(); i++) {
                        seatStr += seatList.get(i).getSeatID();
                        if (i == seatList.size() - 1)
                            break;
                        seatStr += ",";
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
                    writer.append('"' + seatStr + '"');
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
        } catch (IOException e) {
            // e.printStackTrace();
        }
        try {
            Files.delete(Paths.get(DataController.getPath("MovieSession")));
        } catch (IOException e) {
            System.out.println("help3");
            e.printStackTrace();
        }
        tempFile.renameTo(new File(DataController.getPath("MovieSession")));
    }

    /**
     * Temporarily stores the seats booked in a MovieSession in a cinema and displays the seats
     * 
     * @param cinema {@link Cinema} in which seats of the MovieSession to be displayed are in
     * @param seatList {@link ArrayList} of {@link Seat} to be booked as well as all the seats booked in the MovieSession
     */

    public static void tempDisplaySeats(Cinema cinema, ArrayList<Seat> seatList) {
        MovieSession tempSession = new MovieSession(cinema.getCinemaClass());
        for (int i = 0; i < seatList.size(); i++) {
            tempSession.bookSeat(seatList.get(i).getSeatID(), cinema.getCinemaClass());
        }
        tempSession.showSeatings(cinema.getCinemaClass());
    }

    /**
     * Gets the seats booked in a single MovieSession booking
     * 
     * @param cinema {@link Cinema} in which seats of the MovieSession are being booked
     * @param seatID ID of seat to be booked
     * @param session {@link MovieSession} in which the seats are being booked
     * @return bookedSeats {@link ArrayList} of {@link Seat} that is/are booked with the given seatID
     */

    public static ArrayList<Seat> getSeats(Cinema cinema, String seatID, MovieSession session) {
        MovieSession tempSession = new MovieSession(cinema.getCinemaClass());
        ArrayList<Seat> bookedSeats = new ArrayList<Seat>();
        bookedSeats.addAll(tempSession.bookSeat(seatID, cinema.getCinemaClass()));
        return bookedSeats;
    }

    /**
     * Checks if a session has already been booked
     * 
     * @param cinemaCode Cinema Code of cinema in which seats of the MovieSession are to be checked
     * @param session {@link MovieSession} to be checked
     * @return <code>true</code> if the MovieSession has already been booked, <code>false</code> otherwise
     */
    public static boolean checkBooked(String cinemaCode, MovieSession session) {
        if (session.getShowtime().isBefore(LocalDateTime.now())) return false;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return false;
        }

        // If Databases Exist
        String line = "";

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (cinemaCode.equals(tokens[4]) && tokens[2].equals(session.getSessionDate())
                        && tokens[3].equals(session.getSessionTime())
                        && tokens[0].toLowerCase().equals(session.getTitle().toLowerCase())) {
                    String seatStr = tokens[5].substring(1, tokens[5].length() - 1);
                    if (seatStr.isEmpty()) {
                        reader.close();
                        return false;
                    }
                    reader.close();
                }
            }
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return true;
    }
}
