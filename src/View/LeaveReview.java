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
        int choice = -1;
        movies = MovieController.read();
        moviesize = movies.size();
        Scanner sc = new Scanner(System.in);
        String reviewText, reviewRating, choiceStr;
        Double rating;
        String ratingRegex = "\\d{1,2}[,\\.]?(\\d{1,1})?";
        String numregex = "[0-9]+";
        String choiceregex = "^(?!(0))[0-3]{1}$";
        ArrayList<Review> userReviews;

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
            System.out.println(ConsoleColours.WHITE_BOLD + "Leave a Review for a Movie: " + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
    
            for(int i = 1; i <= movies.size() ; i++)
            {
                Movie key = SelectionMenu.get(i);
                System.out.println(i + ". "+ key.getTitle());
            }
        }

        System.out.println(ConsoleColours.YELLOW + (moviesize+1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (moviesize+2) + ". Quit" + ConsoleColours.RESET);
        // Keep asking for choice
        Boolean isOK = false;
        while (!isOK) {
            Boolean reviewExist = false;
            System.out.print("Enter your choice of movie to leave review for (Integer Value): ");
            String choicestr = sc.nextLine();
            if (!choicestr.matches(numregex)){
                System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                continue;
            }
            else if (Integer.valueOf(choicestr) <= (moviesize+2) && Integer.valueOf(choicestr) > 0){
                if (choicestr.equals(String.valueOf(moviesize+1))){
                    return this.getPreviousMenu();
                }
                else if (choicestr.equals(String.valueOf(moviesize+2))){
                    nextMenu = new Quit(this);
                    return nextMenu;
                }
                choice = Integer.valueOf(choicestr);
                userReviews = ReviewController.readByEmail(this.user.getEmail());
                for(Review r : userReviews)
                {
                    if(r.getMovie().getTitle().equalsIgnoreCase(SelectionMenu.get(choice).getTitle()))
                    {
                        System.out.println(ConsoleColours.RED_BOLD+"You have left a review for this movie previously."+ ConsoleColours.RESET);
                        System.out.println(ConsoleColours.RED_BOLD+"Please choose another movie or go back to the previous menu to update your review."+ConsoleColours.RESET);
                        reviewExist = true;
                        continue;
                    }
                }
                if(reviewExist == false)
                    isOK = true;
                else
                    isOK = false;
            }
            else {
                System.out.println(ConsoleColours.RED + "Please enter a valid range between 1 and " + (moviesize+2) + ConsoleColours.RESET);
            }
        }

        Movie temp = SelectionMenu.get(choice);
        System.out.println("Please enter your Review for " + temp.getTitle());
        reviewText = sc.nextLine();
        if(reviewText.isBlank())
        {
            return this.getPreviousMenu();
        }
        reviewRating = "-1";
        rating = 0.0;
        Boolean isOKi = false;
        while (!isOKi) {
            System.out.println("Please enter your Ratings for " + temp.getTitle());
            reviewRating = sc.nextLine();
            if (!reviewRating.matches(ratingRegex)){
                System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
            }
            else if (Double.valueOf(reviewRating) >= 0 && Double.valueOf(reviewRating) <= 5){
                rating = Double.valueOf(reviewRating);
                isOKi = true;
            }
            else {
                System.out.println(ConsoleColours.RED + "Please enter a valid range between 0.0 - 5.0" + ConsoleColours.RESET);
            }
        }
        Review review = new Review(LocalDateTime.now(), this.user.getName(), this.user.getEmail(), temp, reviewText, rating);
        ReviewController.create(review);
        
        choiceregex = "^(?!(0))[0-3]{1}$";
        System.out.println(ConsoleColours.PURPLE_BOLD + "Would you like to leave a review for another movie? " + ConsoleColours.RESET);
        System.out.println("1. Yes ");
        System.out.println("2. No ");
        System.out.println(ConsoleColours.RED + "3. Quit" + ConsoleColours.RESET);
        System.out.println();
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        choiceStr = sc.nextLine();
        System.out.println();
    
        while(!choiceStr.matches(choiceregex))
        {
            if(choiceStr.isBlank())
            {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice: " + ConsoleColours.RESET);
            choiceStr = sc.nextLine();
            System.out.println();
        }
        
        choice = Integer.valueOf(choiceStr);
        switch(choice)
        {
            case 1:
                nextMenu = new LeaveReview(getPreviousMenu(), 0, user);
                break;
            case 2: 
                nextMenu = new MovieGoerMainMenu(nextMenu, 0, user, null, null, null, null, null, null, null);
                break;
            case 3:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println("Please enter a valid choice. ");
                break;
        }
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