package Model;

import java.time.LocalDateTime;
/**
 * Represents one user's review in the MOBLIMA Cinema Application
 * @author CS2002 Group 
 * @version 1.0
 * @since 21-10-2022
 */
public class Review {

    /**
	 * MovieGoer's reviewed movie details
	 */
    private Movie movie;

    /**
	 * MovieGoer's review content
	 */
    private String review;

    /**
	 * MovieGoer's rating
	 */
    private int rating;

    /**
	 * MovieGoer's date of review
	 */
    private LocalDateTime date;

    /**
	 * MovieGoer's name
	 */
    private String name;

    /**
	 * Min rating possible
	 */
    private static final int minRating = 1;

    /**
	 * Max rating possible
	 */
    private static final int maxRating = 5;

    /**
     * Constructor
     * @param movie         The reviewed Movie details
     * @param review        The content of the review
     * @param rating        The movie rating out of 5
     * @param date          The time and date review was made
     * @param name          The Reviewer's name
     */
    public Review(Movie movie, String review, int rating, LocalDateTime date, String name) {
        this.movie = movie;
        this.review = review;
        this.date = date;
        this.name = name;

        //when rating given > 5 or < 1, adjust to maxRating(5) and minRating(1) respectively
        if(rating > maxRating) this.rating = maxRating;
        else if (rating < minRating) this.rating = minRating;
        else this.rating = rating;
        
        // TODO - implement Review.Review
        throw new UnsupportedOperationException();
    }

    /**
     * Get details of Movie reviewed
     * @return      The movie reviewed
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Set the review content
     * @param review    The content of the review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Get the content of the review
     * @return      The content of the review
     */
    public String getReview() {
        return this.review;
    }

    /**
     * Set the movie rating
     * @param rating    The movie rating out of 5
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Get reviewer's rating
     * @return      The rating of movie
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Get the time and date when review is made
     * @return      The time when review is made
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Get the name of the reviewer
     * @return      The reviewer's name
     */
    public String getName() {
        return this.name;
    }

}
