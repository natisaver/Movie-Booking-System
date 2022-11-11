package Model;

import java.time.LocalDateTime;

/**
 * Represents one user's review in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */
public class Review {
    /**
     * MovieGoer's date of review
     */
    private LocalDateTime date;

    /**
     * MovieGoer's name
     */
    private String name;

    /**
     * MovieGoer's email
     */
    private String email;

    /**
     * MovieGoer's reviewed movie
     */
    private Movie movie;

    /**
     * MovieGoer's review content
     */
    private String review;

    /**
     * MovieGoer's rating
     */
    private double rating;

    /**
     * Min rating possible
     */
    private static final double minRating = 0.0;

    /**
     * Max rating possible
     */
    private static final double maxRating = 5.0;

    /**
     * Constructor
     * 
     * @param date   The time and date review was made
     * @param name   The Reviewer's name
     * @param email  The Reviewer's email
     * @param movie  The reviewed Movie details
     * @param review The content of the review
     * @param rating The movie rating out of 5
     */
    public Review(LocalDateTime date, String name, String email, Movie movie, String review, double rating) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.movie = movie;
        this.review = review;
        this.rating = rating;

        // when rating given > 5 or < 1, adjust to maxRating(5) and minRating(1)
        // respectively
        if (rating > maxRating)
            this.rating = maxRating;
        else if (rating < minRating)
            this.rating = minRating;
        else
            this.rating = rating;

    }

    /**
     * Get the time and date when review is made
     * 
     * @return {@link LocalDateTime}   The time and date review was made
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Set the time and date when review is made
     * 
     * @param date {@link LocalDateTime}   The time and date review was made
     */

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Get the name of the reviewer
     * 
     * @return The reviewer's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the reviewer
     * 
     * @param name The reviewer's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the email of the reviewer
     * 
     * @return The reviewer's email
     */

    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email of the reviewer
     * 
     * @param email The reviewer's email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get details of Movie reviewed
     * 
     * @return The movie reviewed
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Set details of Movie reviewed
     * 
     * @param movie The movie reviewed
     */

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Set the review content
     * 
     * @param review The content of the review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Get the content of the review
     * 
     * @return The content of the review
     */
    public String getReview() {
        return this.review;
    }

    /**
     * Set the movie rating
     * 
     * @param rating The movie rating out of 5
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Get reviewer's rating
     * 
     * @return The rating of movie
     */
    public double getRating() {
        return this.rating;
    }

}
