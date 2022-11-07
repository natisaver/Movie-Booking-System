package View;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Scanner sc = new Scanner(System.in);
        String inputString, numRegex;
        Movie movie = new Movie();
        String reviewText;
        float rating;

        //Enter Movie Title to create
        do {
            System.out.println(ConsoleColours.WHITE_BRIGHT + "Leave a Review for a Movie: " + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
            System.out.println("Enter Movie Title: ");
            inputString = sc.nextLine();
            movie = MovieController.readByTitle(inputString);
            
            //Checks if Movie exists in the database
            while(movie == null){
                System.out.println(ConsoleColours.RED + "Movie do not exists." + ConsoleColours.RESET);
                System.out.println("Re-enter Movie Title");
                inputString = sc.nextLine();
                if(inputString.isBlank()){
                    return this.getPreviousMenu();
                }
            }
            System.out.println("Please enter your Review for " + movie.getTitle());
            reviewText = sc.nextLine();
            System.out.println("Please enter your Ratings for " + movie.getTitle());
            rating = sc.nextFloat();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Review review = new Review(LocalDateTime.now(), this.user.getName(), this.user.getEmail(), movie, reviewText, rating);
            ReviewController.create(review);

            System.out.println(ConsoleColours.PURPLE_BOLD + "Would you like to leave a review for another movie? " + ConsoleColours.RESET);
            System.out.println("1. Yes ");
            System.out.println("2. No ");
            System.out.println(ConsoleColours.YELLOW + "3. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);
        }
    }
}
