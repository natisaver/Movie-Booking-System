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
 * Controller for CRUD operations managing data relating to Movie
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 28-10-2022
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
     * @return Model.{@link Movie} Return list of Movie if any, else empty list
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
            e.printStackTrace();
        }
        return movieArrayList;
    }

    public static Movie readByTitle(String title) {
        updateStatus();
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
                if (tokens[0].substring(1, tokens[0].length() - 1).toLowerCase().equals(title.toLowerCase())) {
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

    public static Boolean create(Movie movie) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
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
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }

    public static Boolean update(Movie movie) {
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

                if (tokens[0].substring(1, tokens[0].length() - 1).equals(movie.getTitle().toLowerCase())) {
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
            if (Found == false) {
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

    /**
     * @param movie
     * @return
     */
    public static Boolean updateStatus() {
        ArrayList<Movie> movies;
        movies = MovieController.read();
        for (int x = 0; x < movies.size(); x++) {
            // Auto setting of Showing Status of Movies
            showingStatus_Enum showingStatus = null;
            LocalDateTime cur = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

            // For all current time before preview date (1 Week Before Movie) is under
            // COMING_SOON.
            if (movies.get(x).getReleaseDate().minusWeeks(1).isAfter(cur)) {
                showingStatus = showingStatus_Enum.COMING_SOON;
            }
            // If current time is 1 week before release date, movie is under PREVIEW.
            if ((movies.get(x).getReleaseDate().minusWeeks(1).isBefore(cur)
                    || movies.get(x).getReleaseDate().minusWeeks(1).equals(cur))
                    && (movies.get(x).getReleaseDate().isAfter(cur))) {
                showingStatus = showingStatus_Enum.PREVIEW;
            }
            // If current time is on release date, or after release date, movie is under
            // NOW_SHOWING.
            if ((movies.get(x).getReleaseDate().isBefore(cur) || movies.get(x).getReleaseDate().equals(cur))
                    && (movies.get(x).getEndDate().isAfter(cur))) {
                showingStatus = showingStatus_Enum.NOW_SHOWING;
            }
            // If current time is on end date, or after end date, movie is under
            // END_OF_SHOWING.
            if (movies.get(x).getEndDate().isBefore(cur) || movies.get(x).getEndDate().equals(cur)) {
                showingStatus = showingStatus_Enum.END_OF_SHOWING;
            }
            movies.get(x).setShowingStatus(showingStatus);
            update(movies.get(x));
        }
        return true;
    }

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

                if (tokens[0].substring(1, tokens[0].length() - 1).equals(movie.getTitle().toLowerCase())) {
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
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Movie")));
        return true;
    }
}
