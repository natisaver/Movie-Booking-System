package View;
import java.time.LocalDateTime;
import java.util.Scanner;

import Controller.MovieController;
import Controller.ReviewController;
import Model.Movie;
import Model.MovieGoer;
import Model.Review;
import Model.User;
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
        int choice;
        String choiceStr;

        //Enter Movie Title to create
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
            movie = MovieController.readByTitle(inputString);
            if(inputString.isBlank()){
                return this.getPreviousMenu();
            }
        }

        System.out.println("Please enter your Review for " + movie.getTitle());
        reviewText = sc.nextLine();
        System.out.println("Please enter your Ratings for " + movie.getTitle());
        rating = sc.nextFloat();

        Review review = new Review(LocalDateTime.now(), this.user.getName(), this.user.getEmail(), movie, reviewText, rating);
        ReviewController.create(review);
        
        numRegex = "^(?!(0))[0-3]{1}$";
        System.out.println(ConsoleColours.PURPLE_BOLD + "Would you like to leave a review for another movie? " + ConsoleColours.RESET);
        System.out.println("1. Yes ");
        System.out.println("2. No ");
        System.out.println(ConsoleColours.RED + "3. Quit" + ConsoleColours.RESET);
        System.out.println();
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        choiceStr = sc.nextLine();
        System.out.println();

        while(!choiceStr.matches(numRegex))
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
                nextMenu = new LeaveReview(nextMenu, choice, user);
                break;
            case 2: 
                nextMenu = new MovieGoerMainMenu(nextMenu, choice, user, movie, null, null, null, null);
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
}
