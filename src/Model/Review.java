package Model;

import java.util.Date;
/**
 * Review class contains information of one customer's review
 * including the movie details, review content, rating, review date and reviewer's name 
 */
public class Review {

    private Movie movie;
    private String review;
    private int rating;
    private Date date;
    private String name;

    private static final int minRating = 1;
    private static final int maxRating = 5;

    /**
     * This is the constructor that initialises the Movie reviewed, review content,
     * rating, time and date of review and name of reviewer
     * @param movie is the reviewed Movie details
     * @param review is the content of the review
     * @param rating is the movie rating out of 5
     * @param date is the time and date the review was made
     * @param name is the name of reviewer
     */
    public Review(Movie movie, String review, int rating, Date date, String name) {
        this.movie = movie;
        this.review = review;
        this.date = new Date();
        this.name = name;

        //when rating given > 5 or < 1, adjust to maxRating(5) and minRating(1) respectively
        if(rating > maxRating) this.rating = maxRating;
        else if (rating < minRating) this.rating = minRating;
        else this.rating = rating;
        
        // TODO - implement Review.Review
        throw new UnsupportedOperationException();
    }

    /**
     * This method to to get details of Movie reviewed
     * @return the movie reviewed
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * This method is to set the review content
     * @param review is the content of the review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * This method is to get the content of the review
     * @return the content of the review
     */
    public String getReview() {
        return this.review;
    }

    /**
     * This method is for reviewer to set the movie rating
     * @param rating is the movie rating out of 5
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * This method is to get reviewer's rating
     * @return rating of movie
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * This method is to get the time and date when review is made
     * @return time when review is made
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * This method is to get the name of the reviewer
     * @return reviewer's name
     */
    public String getName() {
        return this.name;
    }

}
