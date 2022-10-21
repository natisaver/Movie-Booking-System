package Controller;

import java.io.*;
import java.util.*;

import Model.MovieGoer;

public class MovieGoerController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("MovieGoer");

    /** 
     * READ rows of every MovieGoer in Database file
     * If Database file not found, ignore error and return empty list
     * @return Model.{@link MovieGoer}  Return list of MovieGoers if any, else empty list
     */
    public ArrayList<MovieGoer> read() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<MovieGoer>();
        }
        
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[1].toLowerCase().contains(search.toLowerCase())) {
                    List<String> cast = Arrays.asList(tokens[5].split("\\."));
                    ArrayList<String> reviews=new ArrayList<String>(Arrays.asList(tokens[8].split("\\.")));
                    if(reviews.get(0).equals(""))
                        reviews=new ArrayList<>();
                    List<String> temp= Arrays.asList(tokens[3].split("\\."));

                    ArrayList<Integer> ratings=new ArrayList<>();
                    if(!temp.get(0).equals(""))
                        for (String s : temp) ratings.add(Integer.valueOf(s));

                    Movie movie = new Movie(Integer.parseInt(tokens[0]), tokens[1], tokens[2],ratings, tokens[4], cast, tokens[6], tokens[7],reviews,tokens[9]);
                    movieArrayList.add(movie);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
