package Model;

public class Review {

    private Movie movie;
    private String review;
    private int rating;
    private Date date;
    private String name;

    public Movie getMovie() {
        return this.movie;
    }

    /**
     * 
     * @param review
     */
    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return this.review;
    }

    /**
     * 
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return this.rating;
    }

    public Date getDate() {
        return this.date;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param movie
     * @param review
     * @param rating
     * @param date
     * @param name
     */
    public Review(Movie movie, String review, int rating, Date date, String name) {
        // TODO - implement Review.Review
        throw new UnsupportedOperationException();
    }

}
