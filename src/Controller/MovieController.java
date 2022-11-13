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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;

import Model.*;

/**
 * CRUD Operations for Movies
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */

public class MovieController {
    /**
     * Path in database
     */
    private final static String PATH = DataController.getPath("Movie");

    /**
     * READ every row of Movie Database File
     * If Database file not found, ignore error and return empty list
     * 
     * @return {@link ArrayList} of {@link Movie} of every row of Movie Database File
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
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Movie movie = new Movie(tokens[0].substring(1, tokens[0].length() - 1),
                        tokens[1].substring(1, tokens[1].length() - 1),
                        new ArrayList<String>(Arrays.asList(tokens[2].substring(1, tokens[2].length() - 1).split(","))),
                        tokens[3], tokens[4], tokens[5].substring(1, tokens[5].length() - 1),
                        Integer.parseInt(tokens[6]), showingStatus_Enum.valueOf(tokens[7]),
                        movieType_Enum.valueOf(tokens[8]), movieRating_Enum.valueOf(tokens[9]),
                        Integer.parseInt(tokens[10]));
                movieArrayList.add(movie);
                // System.out.println(tokens[0]);
            }
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<Movie>();
        }
        return movieArrayList;
    }

    /**
     * READ and list all movies from Database with specified title
     * 
     * @param title {@link String} of movie title
     * @return      {@link Movie} with specified title
     */

    public static Movie readByTitle(String title) {
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
                if (tokens[0].substring(1, tokens[0].length() - 1).toLowerCase().equals(title.toLowerCase())) {
                    reader.close();
                    return new Movie(tokens[0].substring(1, tokens[0].length() - 1),
                            tokens[1].substring(1, tokens[1].length() - 1),
                            new ArrayList<String>(
                                    Arrays.asList(tokens[2].substring(1, tokens[2].length() - 1).split(","))),
                            tokens[3], tokens[4], tokens[5].substring(1, tokens[5].length() - 1),
                            Integer.parseInt(tokens[6]), showingStatus_Enum.valueOf(tokens[7]),
                            movieType_Enum.valueOf(tokens[8]), movieRating_Enum.valueOf(tokens[9]),
                            Integer.parseInt(tokens[10]));
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
     * CREATE new movie in database 
     * 
     * @param movie {@link Movie} to be added to database
     * @return      <code>true</code> if movie is successfully added to database, else
     *             <code>false</code>
     */

    public static Boolean create(Movie movie) {
        File inputFile = new File(DataController.getPath("Movie"));
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

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].substring(1, tokens[0].length() - 1).toLowerCase()
                        .equals(movie.getTitle().toLowerCase())) {
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
                writer.append('"' + movie.getTitle() + '"');
                writer.append(",");
                writer.append('"' + movie.getDirector() + '"');
                writer.append(",");

                writer.append('"');
                for (int i = 0; i < movie.getCast().size(); i++) {
                    writer.append(movie.getCast().get(i));
                    if (i == movie.getCast().size() - 1)
                        break;
                    writer.append(", ");
                }

                writer.append('"');
                writer.append(",");

                writer.append(movie.getReleaseDate().format(formatter));
                writer.append(",");
                writer.append(movie.getEndDate().format(formatter));
                writer.append(",");
                writer.append('"' + movie.getSynopsis() + '"');
                writer.append(",");
                writer.append(Integer.toString(movie.getDuration()));
                writer.append(",");

                // Auto setting of Showing Status of Movies
                showingStatus_Enum showingStatus = null;
                LocalDateTime cur = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

                // For all current time before preview date (1 Week Before Movie) is under
                // COMING_SOON.
                if (movie.getReleaseDate().minusWeeks(1).isAfter(cur)) {
                    showingStatus = showingStatus_Enum.COMING_SOON;
                }
                // If current time is 1 week before release date, movie is under PREVIEW.
                if ((movie.getReleaseDate().minusWeeks(1).isBefore(cur)
                        || movie.getReleaseDate().minusWeeks(1).equals(cur)) && (movie.getReleaseDate().isAfter(cur))) {
                    showingStatus = showingStatus_Enum.PREVIEW;
                }
                // If current time is on release date, or after release date, movie is under
                // NOW_SHOWING.
                if ((movie.getReleaseDate().isBefore(cur) || movie.getReleaseDate().equals(cur))
                        && (movie.getEndDate().isAfter(cur))) {
                    showingStatus = showingStatus_Enum.NOW_SHOWING;
                }
                // If current time is on end date, or after end date, movie is under
                // END_OF_SHOWING.
                if (movie.getEndDate().isBefore(cur) || movie.getEndDate().equals(cur)) {
                    showingStatus = showingStatus_Enum.END_OF_SHOWING;
                }

                writer.append(showingStatus.name());
                writer.append(",");
                writer.append(movie.getMovieType().name());
                writer.append(",");
                writer.append(movie.getMovieRating().name());
                writer.append(",");
                writer.append("0"); // Ticket sales starts at 0.
                writer.append("\n");
            }
            writer.close();
            reader.close();
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }

    /**
     * UPDATE movie details in the database for a particular movie.
     * @param movie {@code Movie} object to be updated.
     * @return <code>true</code> if movie is updated successfully, else
     *        <code>false</code>.
     */

    public static Boolean update(Movie movie) {
        File inputFile = new File(DataController.getPath("Movie"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            // System.out.println("Error 1");
            return false;

        } catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("Error 2");
            return false;
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].substring(1, tokens[0].length() - 1).toLowerCase().equals(movie.getTitle().toLowerCase())) {
                    Found = true;
                    writer.append('"' + movie.getTitle() + '"');
                    writer.append(",");
                    writer.append('"' + movie.getDirector() + '"');
                    writer.append(",");

                    writer.append('"');
                    for (int i = 0; i < movie.getCast().size(); i++) {
                        writer.append(movie.getCast().get(i));
                        if (i == movie.getCast().size() - 1)
                            break;
                        writer.append(",");
                    }

                    writer.append('"');
                    writer.append(",");

                    writer.append(movie.getReleaseDate().format(formatter));
                    writer.append(",");
                    writer.append(movie.getEndDate().format(formatter));
                    writer.append(",");
                    writer.append('"' + movie.getSynopsis() + '"');
                    writer.append(",");
                    writer.append(Integer.toString(movie.getDuration()));
                    writer.append(",");
                    writer.append(movie.getShowingStatus().name());
                    writer.append(",");
                    writer.append(movie.getMovieType().name());
                    writer.append(",");
                    writer.append(movie.getMovieRating().name());
                    writer.append(",");
                    writer.append(Integer.toString(movie.getTicketSales())); // Ticket sales starts at 0.
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
            if(Found)
            {
                // delete old file
                Files.delete(Paths.get(DataController.getPath("Movie")));
                // replace with the new file
                tempFile.renameTo(new File(DataController.getPath("Movie")));
                if(!MovieSessionController.updateMovieTypeByTitle(movie.getTitle(), movie.getMovieType()))
                {                    
                    return false;
                }
            }
            if (Found == false) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                System.out.println("Error 4");
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error 5");
            return false;
        }
        return true;
    }

    /**
     * UPDATE every row of Movie Database File with the latest status
     * If Database file not found, ignore error and return empty list
     * 
     * @return <code>true</code> if movie is updated successfully, else
     *       <code>false</code>.
     */
    public static boolean updateStatusAll() {
        File inputFile = new File(DataController.getPath("Movie"));
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

        String line;

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  
                //adjust token 7 and update it
                //token 3 is release date
                //token 4 is end date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(tokens[3] + " 00:00", formatter);
                LocalDateTime end = LocalDateTime.parse(tokens[4] + " 00:00", formatter);
                showingStatus_Enum showingStatus = null;
                LocalDateTime cur = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                if (start.minusWeeks(1).isAfter(cur)) {
                    showingStatus = showingStatus_Enum.COMING_SOON;
                }
                // If current time is 1 week before release date, movie is under PREVIEW.
                if ((start.minusWeeks(1).isBefore(cur)
                        || start.minusWeeks(1).equals(cur))
                        && (start.isAfter(cur))) {
                    showingStatus = showingStatus_Enum.PREVIEW;
                }
                // If current time is on release date, or after release date, movie is under
                // NOW_SHOWING.
                if ((start.isBefore(cur) || start.equals(cur))
                        && (end.isAfter(cur))) {
                    showingStatus = showingStatus_Enum.NOW_SHOWING;
                }
                // If current time is on end date, or after end date, movie is under
                // END_OF_SHOWING.
                if (end.isBefore(cur) || end.equals(cur)) {
                    showingStatus = showingStatus_Enum.END_OF_SHOWING;
                }
                // System.out.println(showingStatus.name());
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
                writer.append(showingStatus.name());
                writer.append(",");
                writer.append(tokens[8]);
                writer.append(",");
                writer.append(tokens[9]);
                writer.append(",");
                writer.append(tokens[10]);
                writer.append("\n");
            }
            writer.close();
            reader.close();
            // delete old file
        } catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("probably here");
            return false;
        }
        try {
            Files.delete(Paths.get(DataController.getPath("Movie"))); }
        catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("probably NOT here");
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }

    /**
     * DELETE a movie from Movie Database File
     * 
     * @param movie     {@link Movie} to be deleted from database
     * @return          <code>true</code> if movie is deleted successfully, else
     *                  <code>false</code>.
     */

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

        Boolean Found = false;
        String line;

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].substring(1, tokens[0].length() - 1).toLowerCase().equals(movie.getTitle().toLowerCase())) {
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
                System.out.println("Movie Not Found in Database");
                System.out.println(movie.getTitle());
                Files.delete(Paths.get(DataController.getPath("Temp")));
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
            // e.printStackTrace();
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }

    /**
     * DELETE a movie from Movie Database File by title
     * @param title {@link String} title of movie to be deleted from database
     * @return <code>true</code> if movie is deleted successfully, else
     *         <code>false</code>.
     */
    public static Boolean deleteByTitle(String title) {

        File inputFile = new File(DataController.getPath("Movie"));
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

        Boolean Found = false;
        String line;

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
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (tokens[0].substring(1, tokens[0].length() - 1).equals(title.toLowerCase())) {
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
            // e.printStackTrace();
            return false;
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }

    /**
     * UPDATE sales of a movie in Movie Database File
     * 
     * @param movie {@link Movie} movie to be updated in database
     * @param numTickets {@link Integer} number of tickets sold
     */
    public static void updateSales(Movie movie, int numTickets) {
        File inputFile = new File(DataController.getPath("Movie"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();

        } catch (IOException e) {
            // e.printStackTrace();
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
            // e.printStackTrace();
        }

        String title = movie.getTitle();
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (title.equals(tokens[0].substring(1, tokens[0].length() - 1))) {
                    int curSales = Integer.valueOf(tokens[10]);
                    curSales += numTickets;
                    String newSales = String.valueOf(curSales);
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
                    writer.append(newSales);
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
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Movie")));
        } catch (IOException e) {
            // e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
    }
}
