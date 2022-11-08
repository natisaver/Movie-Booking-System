package View;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Controller.*;
import Model.*;

public class ViewMovieDetails extends BaseMenu
{
    Scanner sc = new Scanner(System.in);

    public ViewMovieDetails(BaseMenu previousMenu, int accesslevel) 
    {
        super(previousMenu, accesslevel);
    }
    
    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        Integer choice = -1;
        ArrayList<Movie> movies;
        Integer moviesize;
        HashMap<String, Movie> Movies = new HashMap<String, Movie>();
        HashMap<Integer, Movie> SelectionMenu = new HashMap<Integer, Movie>();
        movies = MovieController.read();
        moviesize = movies.size();

        String numregex = "[0-9]+";
        String nomoviesavailable = "^[^0-2]$";

        //building hashmap of choices
        for(int i = 0; i < movies.size() ; i++)
        {
            Movie key = movies.get(i);
            Movies.put(key.getTitle(), key);
        }

        //sort the hashmap by alphabetical name
        SelectionMenu = sortByName(Movies);
        

        System.out.println(ConsoleColours.PURPLE_BOLD + "Movies Available: " + ConsoleColours.RESET);
        if (moviesize <= 0){
            System.out.println("No Movies Available");
            do {
                System.out.println(ConsoleColours.YELLOW + "1. Back" + ConsoleColours.RESET);
                System.out.println(ConsoleColours.RED + "2. Quit" + ConsoleColours.RESET);
                String inputString = sc.nextLine();
                while (!inputString.matches(nomoviesavailable)){
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice" + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                }
                int finalchoice = Integer.valueOf(inputString);
                switch(finalchoice){
                    case 1:
                        return this.getPreviousMenu();
                        
                    case 2:
                        return new Quit(this);
                }
            } while (true);
        }
        else {
            for(int i = 1; i <= movies.size() ; i++)
            {
                Movie key = SelectionMenu.get(i);
                System.out.println(i + ". "+ key.getTitle());
            }
        }

        System.out.println(ConsoleColours.YELLOW + (moviesize+1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (moviesize+2) + ". Quit" + ConsoleColours.RESET);
        
        do{
            // Keep asking for choice

            Boolean isOK = false;
            while (!isOK) {
                System.out.print("Enter your choice of movie to view details (Integer Value): ");
                String choicestr = sc.nextLine();
                if (!choicestr.matches(numregex)){
                    System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                    continue;
                }
                if (Integer.valueOf(choicestr) <= moviesize+2 && Integer.valueOf(choicestr) > 0){
                    choice = Integer.valueOf(choicestr);
                    isOK = true;
                }
                else {
                    System.out.println(ConsoleColours.RED + "Please enter a valid range between 1<=x<=" + (moviesize+2) + ConsoleColours.RESET);
                }
            }

            if (choice == moviesize+1){
                return this.getPreviousMenu();
            }
            else if (choice == moviesize+2){
                nextMenu = new Quit(this);
                break;
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Movie temp = SelectionMenu.get(choice);
                System.out.println(">>");
                System.out.println(ConsoleColours.CYAN_BRIGHT + "Title: " + temp.getTitle() + ConsoleColours.RESET);
                System.out.println("Rating: " + temp.getMovieRating().name() + " | Duration: " + temp.getDuration() + "mins");
                System.out.println("Release: " + temp.getReleaseDate().format(formatter) + " | End: " + temp.getEndDate().format(formatter));
                System.out.println("Director: " + temp.getDirector());
                System.out.print("Cast: ");
                for (int j = 0; j<temp.getCast().size(); j++){
                    if (j == temp.getCast().size()-1){
                        System.out.println(temp.getCast().get(j));
                    }
                    else {
                        System.out.print(temp.getCast().get(j) + ", ");
                    }
                }
                System.out.println("Synopsis: ");
                System.out.println(temp.getSynopsis());
                System.out.print("Reviews: ");
                
                for (int j = 0; j<ReviewController.readByTitle(temp.getTitle()).size(); j++){
                    ArrayList<Review> reviewlist = ReviewController.readByTitle(temp.getTitle())
                    if (j == reviewlist.size()-1){
                        System.out.println(reviewlist.get(j).getReview());
                        System.out.println("by: " + reviewlist.get(j).getName() + "on: " + reviewlist.get(j).getDate().format(formatter) + " | " + Double.toString(reviewlist.get(j).getRating()));
                    }
                    else {
                        System.out.println(reviewlist.get(j));
                        System.out.println("by: " + reviewlist.get(j).getName() + "on: " + reviewlist.get(j).getDate().format(formatter) + " | " + Double.toString(reviewlist.get(j).getRating()) + " ,");
                    }
                }
                System.out.println(">>");

            }
        }while(choice <= moviesize+1);
        return nextMenu;
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


