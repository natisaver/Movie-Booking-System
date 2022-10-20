package Model;

/**
 * Represents a movie in the MOBLIMA Cinema Application
 * @author CS2002 Group 
 * @version 1.0
 * @since 21-10-2022
 */

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

    /**
     * This is the constructor of a Movie object. 
     * 
     * @param title Movie title
     * @param director Movie director
     * @param cast Movie cast (at least 2)
     * @param releaseDate Movie release date
     * @param endDate Movie ending date
     * @param synopsis Movie synopsis
     * @param duration Movie runtime
     * @param showingStatus Movie showing status (Now showing, Coming soon or Preview)
     * @param movieType Movie type (2D, 3D or Blockbuster)
     * @param movieRating Movie rating (PG, PG13, NC16, M18 or R21)
     */
    public Movie(String title, String director, String[] cast, Date releaseDate, Date endDate, String synopsis,
            int duration, showingStatus_Enum showingStatus, movieType_Enum movieType, movieRating_Enum movieRating) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.synopsis = synopsis;
        this.duration = duration;
        this.showingStatus = showingStatus;
        this.movieType = movieType;
        this.movieRating = movieRating;
    }

    /**
     * Get the title of the movie
     * @return a String data type that contains the title of Movie object.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 
     * @param title Specify the title of the Movie object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the director of the movie
     * @return a String data type that contains the director of Movie object.
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * 
     * @param director Specify the director's name of the Movie object.
     */
    public void setDirector(String director) {
        this.director = director;
    }
    /**
     * Get an array of the casts of the movie
     * @return a String array data type that contains the casts of Movie object.
     */
    public String[] getCast() {
        return this.cast;
    }

    /**
     * 
     * @param cast Specify an array of Movie object's cast.
     */
    public void setCast(String[] cast) {
        this.cast = cast;
    }

    /**
     * Get the release date of the movie
     * @return a Date data type that contains the release date of Movie object.
     */
    public Date getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * 
     * @param date Specify the release date of the Movie object.
     */
    public void setReleaseDate(Date date) {
        this.releaseDate = date;
    }

    /**
     * Get the ending date of the movie
     * @return a Date data type that contains the ending date of Movie object.
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * 
     * @param date Specify the ending date of the Movie object.
     */
    public void setEndDate(Date date) {
        this.endDate = date;
    }

    /**
     * Get the synopsis of the movie
     * @return a String data type that contains the synopsis of Movie object.
     */
    public String getSynopsis() {
        return this.synopsis;
    }

    /**
     * 
     * @param synopsis Specify the synopsis of the Movie object. 
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Get the runtime of the movie
     * @return a int data type that contains the runtime of Movie object.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * 
     * @param duration Specify the runtime of the Movie object.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get the showing status of the movie
     * @return an Enum data type that contains the showing status of Movie object.
     * Possible showing status: (COMING_SOON, NOW_SHOWING, PREVIEW, END_OF_SHOWING)
     */
    public showingStatus_Enum getShowingStatus() {
        return this.showingStatus;
    }

    /**
     * 
     * @param showingStatus Specify showing status of Movie object. 
     * Possible showing status: (COMING_SOON, NOW_SHOWING, PREVIEW, END_OF_SHOWING)
     */
    public void setShowingStatus(showingStatus_Enum showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * Get the movie type of the movie
     * @return a Enum data type that contains the movie type of Movie object.
     * Possible movie type: (TWOD, THREED, BLOCKBUSTER)
     */    
    public movieType_Enum getMovieType() {
        return this.movieType;
    }

    /**
     * 
     * @param movieType Specify movie type of Movie object.
     * Possible movie type: (TWOD, THREED, BLOCKBUSTER)
     */
    public void setMovieType(movieType_Enum movieType) {
        this.movieType = movieType;
    }

    /**
     * Get the movie rating of the movie
     * @return a Enum data type that contains the movie rating of Movie object.
     * Possible movie rating: (PG, PG13, NC16, M18, R21)
     */    
    public movieRating_Enum getMovieRating() {
        return this.movieRating;
    }

    /**
     * 
     * @param movieRating Specify movie rating of Movie object
     * Possible movie rating: (PG, PG13, NC16, M18, R21)
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
}
