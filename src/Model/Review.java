package Model;


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
        // TODO - implement Review.Review
        throw new UnsupportedOperationException();
    }

}
