package Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Represents a movie in the MOBLIMA Cinema Application
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */

public class Movie {
    private String title;
    private String director;
    private String[] cast;
    private LocalDateTime releaseDate;
    private LocalDateTime endDate;
    private String synopsis;
    private int duration;
    private showingStatus_Enum showingStatus;
    private movieType_Enum[] movieType;
    private movieRating_Enum movieRating;
    private HashMap<MovieGoer, Review> reviewList;
    private float reviewerRating;
    private int ticketSales;

    /**
     * Constructor 
     * 
     * @param title             Movie title
     * @param director          Movie director
     * @param cast              Movie cast (at least 2)
     * @param releaseDate       Movie release date
     * @param endDate           Movie ending date
     * @param synopsis          Movie synopsis
     * @param duration          Movie runtime
     * @param showingStatus     Movie showing status (Now showing, Coming soon or Preview)
     * @param movieType         Movie type (2D, 3D or Blockbuster)
     * @param movieRating       Movie rating (PG, PG13, NC16, M18 or R21)
     */
    public Movie(String title, String director, String[] cast, String releaseDate, String endDate, String synopsis,
            int duration, showingStatus_Enum showingStatus, movieType_Enum[] movieType, movieRating_Enum movieRating) {
        
        // Format the date using dd-MM-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.title = title;
        this.director = director;
        this.cast = cast;
        this.releaseDate = LocalDateTime.parse(releaseDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);
        this.synopsis = synopsis;
        this.duration = duration;
        this.showingStatus = showingStatus;
        this.movieType = movieType;
        this.movieRating = movieRating;
        this.reviewList = new HashMap<>();
    }

    /**
     * Get title of the movie
     * @return title of Movie object.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set title of the movie
     * @param title     title of Movie object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get director of the movie
     * @return director of Movie object.
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * Set director of the movie
     * @param director  director of Movie object.
     */
    public void setDirector(String director) {
        this.director = director;
    }
    /**
     * Get casts of the movie
     * @return casts of Movie object.
     */
    public String[] getCast() {
        return this.cast;
    }

    /**
     * Set casts of the movie
     * @param cast  casts of Movie object.
     */
    public void setCast(String[] cast) {
        this.cast = cast;
    }

    /**
     * Get release date of movie
     * @return release date of Movie object.
     */
    public LocalDateTime getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * Set release date of movie
     * @param date     release date of Movie object.
     */
    public void setReleaseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.releaseDate = LocalDateTime.parse(date, formatter);
    }

    /**
     * Get ending date of movie
     * @return ending date of Movie object.
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Set ending date of movie
     * @param date     ending date of Movie object.
     */
    public void setEndDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.endDate = LocalDateTime.parse(date, formatter);
    }

    /**
     * Get synopsis of movie
     * @return synopsis of Movie object.
     */
    public String getSynopsis() {
        return this.synopsis;
    }

    /**
     * Set synopsis of movie
     * @param synopsis          synopsis of the Movie object. 
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Get runtime of movie
     * @return runtime of Movie object.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Set runtime of movie
     * @param duration      runtime of Movie object.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get showing status of movie
     * @return showing status of Movie object.
     * showingStatus_Enum: COMING_SOON, NOW_SHOWING, PREVIEW, END_OF_SHOWING
     */
    public showingStatus_Enum getShowingStatus() {
        return this.showingStatus;
    }

    /**
     * Set showing status of movie
     * @param showingStatus             showing status of Movie object. 
     * showingStatus_Enum: COMING_SOON, NOW_SHOWING, PREVIEW, END_OF_SHOWING
     */
    public void setShowingStatus(showingStatus_Enum showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * Get movie type(s) of movie
     * @return array of movie types of Movie object.
     * movieType_Enum: TWOD, THREED, BLOCKBUSTER
     */    
    public movieType_Enum[] getMovieType() {
        return this.movieType;
    }

    /**
     * Set movie type of movie
     * @param movieType             movie type of Movie object.
     * movieType_Enum: TWOD, THREED, BLOCKBUSTER
     */
    public void setMovieType(movieType_Enum[] movieType) {
        this.movieType = movieType;
    }

    /**
     * Get movie rating of movie
     * @return movie rating of Movie object.
     * movieRating_Enum: PG, PG13, NC16, M18, R21
     */    
    public movieRating_Enum getMovieRating() {
        return this.movieRating;
    }

    /**
     * Set movie rating of movie
     * @param movieRating           movie rating of Movie object
     * movieRating_Enum: PG, PG13, NC16, M18, R21
     */
    public void setMovieRating(movieRating_Enum movieRating) {
        this.movieRating = movieRating;
    }

    /**
     * Get a list of past review history of the movie.
     * @return Review of Movie object and the MovieGoer object who left the Review.
     * Each review is binded to a MovieGoer object.
     */ 
    public HashMap<MovieGoer, Review> getReviewList() {
        return this.reviewList;
    }

    /**
     * Add review to existing review list.
     * @param movieGoer     MovieGoer object who left the Review.
     * @param review        Review object left by the MovieGoer.
     * MovieGoer and Review are added as a pair of keys and values into reviewList Hashmap using put() function.
     */
    public void addReview(MovieGoer movieGoer, Review review) {
        this.reviewList.put(movieGoer, review);
    }

    /**
     * Get overall rating of movie
     * @return Overall rating of movie
     */
    public float getOverallRating() {
        this.reviewerRating = 0;
        for(Review value: reviewList.values())
        {
            this.reviewerRating += value.getRating();
        }
        return reviewerRating;
    }   

    /**
     * Get number of tickets sold.
     * @return Number of tickets sold. 
     */
    public int getTicketSales() {
        return this.ticketSales;
    }

    /**
     * Increment ticket sales. 
     * @param noOfTicket        number of ticket customer bought in single transaction.
     * Called when customer purchase tickets.
     */
    public void addTicketSales(int noOfTicket) {
        ticketSales += noOfTicket;
    }
}
