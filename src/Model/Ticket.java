package Model;

/**
 * Represents a ticket in the MOBLIMA Cinema Application
 * @author Sally Carrera 
 * @version 1.0
 * @since 18-10-2022
 */

 public class Ticket {

	private String transactionID;
	private float price;
	private ageGroup_Enum ageGroup;
	private MovieSession bookingSession;
	private Cinema bookingCinema;
	private Cineplex bookingCineplex;
	private Movie bookingMovie;
	private Seat bookingSeat;

	/**
	 * Constructor
	 * 
	 * @param transactionID 		Ticket Transaction ID
	 * @param price 				Ticket Price
	 * @param ageGroup 				Ticket Age Group (CHILD, ADULT, SENIOR, M18)
	 * @param bookingSession 		Ticket for which Session
	 * @param bookingCinema 		Ticket for which Cinema 
	 * @param bookingCineplex 		Ticket for which Cineplex
	 * @param bookingMovie 			Ticket for which Movie
	 * @param bookingSeat 			Ticket for which Seat
	 */


	public Ticket(String transactionID, float price, ageGroup_Enum ageGroup, MovieSession bookingSession, Cinema bookingCinema, Cineplex bookingCineplex, Movie bookingMovie, Seat bookingSeat) {
        this.transactionID = transactionID;
        this.price = price;
        this.ageGroup = ageGroup;
        this.bookingSession = bookingSession;
        this.bookingCinema = bookingCinema;
        this.bookingCineplex = bookingCineplex;
        this.bookingMovie = bookingMovie;
        this.bookingSeat = bookingSeat;
	}
    /**
     * Get transactionID of ticket 
     * @return transactionID of Ticket object.
     */
    public String getTransactionID()
    {
        return this.transactionID;
    }

    /**
     * Set transactionID of ticket
     * @param transactionID 		transactionID of the Ticket object in XXXYYYYMMDDhhmm. 
     */
    public void setTransactionID(String transactionID)
    {
        this.transactionID = transactionID;
    }

    /**
     * Get price of Ticket 
     * @return price of Ticket object.
     */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Set price of Ticket
	 * @param price		price of Ticket object.
	 */
	public void setPrice(float price) {
		this.price = price;
	}

    /**
     * Get age group of Ticket 
     * @return age group of Ticket object.
	 * ageGroup_Enum: CHILD, ADULT, SENIOR, M18
     */
	public ageGroup_Enum getAgeGroup() {
		return this.ageGroup;
	}

	/**
	 * Set age group of Ticket
	 * @param ageGroup	age group of the Ticket object.
	 */
	public void setAgeGroup(ageGroup_Enum ageGroup) {
		this.ageGroup = ageGroup;
	}

    /**
     * Get session of Ticket 
     * @return session of Ticket object.
     */
	public MovieSession getSession() {
        return this.bookingSession;
	}

	/**
	 * Set session of Ticket
	 * @param session 		session the Ticket object is for.
	 */
	public void setSession(MovieSession session) {
        this.bookingSession = session;
	}

    /**
     * Get cinema of Ticket 
     * @return Cinema of Ticket object.
     */
	public Cinema getCinema() {
        return this.bookingCinema;
	}

	/**
	 * Set cinema of Ticket
	 * @param cinema 		Cinema of Ticket object.
	 */
	public void setCinema(Cinema cinema) {
        this.bookingCinema = cinema;
	}
    
    /**
     * Get cineplex of Ticket 
     * @return Cineplex of Ticket object.
     */
	public Cineplex getCineplex() {
        return this.bookingCineplex;
	}

	/**
	 * Set cineplex of Ticket
	 * @param cineplex 		Cineplex of the Ticket object.
	 */
	public void setCineplex(Cineplex cineplex) {
        this.bookingCineplex = cineplex;
	}

    /**
     * Get movie of Ticket 
     * @return Movie of Ticket object.
     */
	public Movie getMovie() {
        return this.bookingMovie;
	}

	/**
	 * Set movie of Ticket
	 * @param movie			Movie of Ticket object.
	 */
	public void setMovie(Movie movie) {
        this.bookingMovie = movie;
	}
    
    /**
     * Get seat of Ticket 
     * @return Seat of Ticket object.
     */
	public Seat getSeat() {
        return this.bookingSeat;
	}

	/**
	 * Set seat of Ticket
	 * @param seat			Seat that this Ticket object is for.
	 */
	public void setSeat(Seat seat) {
        this.bookingSeat = seat;
	}

}
