package View;
import java.util.Scanner;

import Model.Movie;
public class LeaveReview extends BaseMenu {
    Scanner sc = new Scanner(System.in);
    private final Movie movie;

    public LeaveReview(BaseMenu previousMenu, int accesslevel, Movie movie) {
        super(previousMenu, accesslevel);
        this.movie = movie;
    }

    @Override
    public BaseMenu execute() {
        String reviewtext;
        int rating;
        System.out.println("Enter your review for " + movie.getTitle() + ": ");
        reviewtext = sc.next();
        System.out.println("Enter your rating for " + movie.getTitle() + " (1 to 5): ");
        rating = sc.nextInt();
        Review review = new Review(movie, reviewtext, rating, date, name);
        movie.addReview(review);
        return this.getPreviousMenu();
    }
}
