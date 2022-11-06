package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Model.MovieSession;
import Model.cinemaClass_Enum;

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
            // e.printStackTrace();
            return new ArrayList<MovieSession>();
        }
        ArrayList<MovieSession> movieSessionList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[4].equals(cinemaCode)) {
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
     * @return <code>true</code> if MovieSession was added, <code>false</code> if
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

        Boolean Clash = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
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
     * @param session    MovieSession object to be updated
     * @return <code>true</code> if MovieSession was updated, <code>false</code> if
     *         MovieSession doesnt exist or database is nonexistent
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
        Boolean Clash = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
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
     * DELETE MovieSession in the database
     * 
     * @param cinemaCode Cinema Code of cinema in which MovieSession to be deleted
     *                   runs in
     * @param session    MovieSession object to be deleted
     * @return <code>true</code> if MovieSession was deleted, <code>false</code> if
     *         MovieSession doesnt exist or database is nonexistent
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

    public static ArrayList<String> displaySeats(String cinemaCode, MovieSession session) {
        BufferedReader[] reader = new BufferedReader[2];
        try {
            reader[0] = new BufferedReader(new FileReader(PATH));
            reader[1] = new BufferedReader(new FileReader(cinePATH));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return new ArrayList<String>();
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
        }
        cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(cinemaCode));
        ArrayList<String> seatList = new ArrayList<String>();
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (cinemaCode.equals(tokens[4]) && tokens[2].equals(session.getSessionDate()) && tokens[3].equals(session.getSessionTime())) {
                    String seatStr = tokens[5].substring(1,tokens[5].length()-1);
                    String[] seats = seatStr.split(",");
                    for (int i=0;i<seats.length;i++) {
                        session.bookSeat(seats[i], cinemaClass);
                        seatList.add(seats[i]);
                    }
                    session.showSeatings(cinemaClass);
                }
            }
            reader[0].close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return seatList;
    }

    public static int bookSeats(String cinemaCode, MovieSession session, String seatID) {
        // if (cinemaClass == cinemaClass_Enum.STANDARD) {
        //     String idRegex = "^([a-kA-K&&[^I]&&[^i]])(1[0-3]|[1-9])$";
        //     if (!id.matches(idRegex)) {
        //         System.out.println("Invalid seat! Please choose again");
        //         return false;
        //     }
        // }
        // if (cinemaClass == cinemaClass_Enum.MAX) {
        //     String idRegex = "^(?!s1$|S1$|s2$|S2$|s35$|S35$|s36$|S36$)([a-sA-S&&[^I]&&[^i]&&[^O]&&[^o]])(3[0-6]|[1-2][0-9]|[1-9])$";
		//     if (!id.matches(idRegex)) {
        //         System.out.println("Invalid seat! Please choose again");
        //         return false;
        //     }
        // }
        // if (cinemaClass == cinemaClass_Enum.GOLD) {
        //     String idRegex = "^([a-dA-D])([1-8])$";
        //     if (!id.matches(idRegex)) {
        //         System.out.println("Invalid seat! Please choose again");
        //         return false;
        //     }
        // }
        File tempFile = new File(DataController.getPath("Temp"));
        BufferedWriter writer = null;
        BufferedReader[] reader = new BufferedReader[2];
        try {
            reader[0] = new BufferedReader(new FileReader(PATH));
            reader[1] = new BufferedReader(new FileReader(cinePATH));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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

        String line;

        // If Databases Exist
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
        }
        cinemaClass_Enum cinemaClass = cinemaClass_Enum.valueOf(cineplex.get(cinemaCode));
        try {
            reader[0].readLine();
            while ((line = reader[0].readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (cinemaCode.equals(tokens[4]) && tokens[2].equals(session.getSessionDate()) && tokens[3].equals(session.getSessionTime())) {
                    String seatStr = tokens[5].substring(1,tokens[5].length()-1) + "," + seatID;
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
                    writer.append('"'+seatStr+'"');
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
            reader[0].close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        session.showSeatings(cinemaClass);
        return session.bookSeat(seatID, cinemaClass);
    }
}
