package View;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.MovieController;
import Controller.ReviewController;
import Model.Movie;
import Model.MovieGoer;
import Model.Review;
public class LeaveReview extends BaseMenu {
    private MovieGoer user;
    public LeaveReview(BaseMenu previousMenu, int accesslevel, MovieGoer user) {
        super(previousMenu, accesslevel);
        this.user = user;
    }
    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        ArrayList<Movie> movies;
        Integer moviesize;
        HashMap<String, Movie> Movies = new HashMap<String, Movie>();
        HashMap<Integer, Movie> SelectionMenu = new HashMap<Integer, Movie>();
        movies = MovieController.read();
        moviesize = movies.size();
        Scanner sc = new Scanner(System.in);

        String numregex = "[0-9]+";

        //building hashmap of choices
        for(int i = 0; i < movies.size() ; i++)
        {
            Movie key = movies.get(i);
            Movies.put(key.getTitle(), key);
        }

        //sort the hashmap by alphabetical name
        SelectionMenu = sortByName(Movies);
        
        if (moviesize <= 0){
            System.out.println("No Movies Available");
        }
        else {
            System.out.println(ConsoleColours.WHITE_BRIGHT + "Leave a Review for a Movie: " + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
            System.out.println("Choose movie to leave review for: ");
    
            for(int i = 1; i <= movies.size() ; i++)
            {
                Movie key = SelectionMenu.get(i);
                System.out.println(i + ". "+ key.getTitle());
            }
        }
        
        public static HashMap<Integer, Movie> sortByName(HashMap<String, Movie> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Movie> > list =
               new LinkedList<Map.Entry<String, Movie> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Movie> >() {
            public int compare(Map.Entry<String, Movie> o1,
                               Map.Entry<String, Movie> o2)
            {
                return (o1.getValue().getTitle()).compareTo(o2.getValue().getTitle());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Integer, Movie> temp = new LinkedHashMap<Integer, Movie>();
        int i = 1;
        for (Map.Entry<String, Movie> aa : list) {
            temp.put(i, aa.getValue());
            i++;
        }
        return temp;
    }
    }
}