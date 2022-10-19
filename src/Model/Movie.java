package Model;

public class Movie {
    private String title;
    private String director;
    private String[] cast;
    private Date releaseDate;
    private Date endDate;
    private String synopsis;
    private int duration;
    private showingStatus_Enum showingStatus;
    private movieType_Enum movieType;
    private movieRating_Enum movieRating;
    private HashMap<MovieGoer, Review> reviewList;
    private float reviewerRating;
    private int ticketSales;

    public String getTitle() {
        return this.title;
    }

    /**
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return this.director;
    }

    /**
     * 
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        // TODO - implement Movie.getCast
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param cast
     */
    public void setCast(String cast) {
        // TODO - implement Movie.setCast
        throw new UnsupportedOperationException();
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * 
     * @param date
     */
    public void setReleaseDate(Date date) {
        this.releaseDate = date;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * 
     * @param date
     */
    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    /**
     * 
     * @param synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getDuration() {
        return this.duration;
    }

    /**
     * 
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public showingStatus_Enum getShowingStatus() {
        return this.showingStatus;
    }

    /**
     * 
     * @param showingStatus
     */
    public void setShowingStatus(showingStatus_Enum showingStatus) {
        this.showingStatus = showingStatus;
    }

    public movieType_Enum getMovieType() {
        return this.movieType;
    }

    /**
     * 
     * @param movieType
     */
    public void setMovieType(movieType_Enum movieType) {
        this.movieType = movieType;
    }

    public movieRating_Enum getMovieRating() {
        return this.movieRating;
    }

    /**
     * 
     * @param movieRating
     */
    public void setMovieRating(movieRating_Enum movieRating) {
        this.movieRating = movieRating;
    }

    public HashMap<MovieGoer, Review> getReviewList() {
        return this.reviewList;
    }

    /**
     * 
     * @param movieGoer
     * @param review
     */
    public void addReview(String movieGoer, String review) {
        // TODO - implement Movie.addReview
        throw new UnsupportedOperationException();
    }

    public float getOverallRating() {
        // TODO - implement Movie.getOverallRating
        throw new UnsupportedOperationException();
    }

    public int getTicketSales() {
        return this.ticketSales;
    }

    public void addTicketSales() {
        // TODO - implement Movie.addTicketSales
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param title
     * @param director
     * @param cast
     * @param releaseDate
     * @param endDate
     * @param synopsis
     * @param duration
     * @param showingStatus
     * @param movieType
     * @param movieRating
     */
    public Movie(String title, String director, String[] cast, Date releaseDate, Date endDate, String synopsis,
            int duration, showingStatus_Enum showingStatus, movieType_Enum movieType, movieRating_Enum movieRating) {
        // TODO - implement Movie.Movie
        throw new UnsupportedOperationException();
    }

}
