package Model;

/**
 * Represents a ticket in the MOBLIMA Cinema Application
 * @author CS2002 Group 
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
	 * 
	 * @param transactionID Ticket Transaction ID
	 * @param price Ticket Price
	 * @param ageGroup Ticket Age Group (CHILD, ADULT, SENIOR, M18)
	 * @param bookingSession Ticket for which Session
	 * @param bookingCinema Ticket for which Cinema 
	 * @param bookingCineplex Ticket for which Cineplex
	 * @param bookingMovie Ticket for which Movie
	 * @param bookingSeat Ticket for which Seat
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
     * Get the transactionID of the Ticket 
     * @return a String data type that contains the transactionID of Ticket object.
     */
    public String getTransactionID()
    {
        return this.transactionID;
    }

    /**
     * 
     * @param transactionID Specify the transactionID of the Ticket object in XXXYYYYMMDDhhmm 
     * (Y : year, M : month, D : day, h : hour, m : minutes, XXX: cinema code in letter)
     */
    public void setTransactionID(String transactionID)
    {
        this.transactionID = transactionID;
    }

    /**
     * Get the price of the Ticket 
     * @return a float data type that contains the price of Ticket object.
     */
	public float getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price Specify the price of the ticket
	 */
	public void setPrice(float price) {
		this.price = price;
	}

    /**
     * Get the age group of the Ticket 
     * @return a Enum data type that contains the age group of Ticket object.
     */
	public ageGroup_Enum getAgeGroup() {
		return this.ageGroup;
	}

	/**
	 * 
	 * @param ageGroup Specify the age group of the Ticket.
	 */
	public void setAgeGroup(ageGroup_Enum ageGroup) {
		this.ageGroup = ageGroup;
	}

    /**
     * Get the session of the Ticket 
     * @return a MovieSession data type that contains the session of Ticket object.
     */
	public MovieSession getSession() {
        return this.bookingSession;
	}

	/**
	 * 
	 * @param session Specify which movie session the Ticket object is for.
	 */
	public void setSession(MovieSession session) {
        this.bookingSession = session;
	}

    /**
     * Get the cinema of the Ticket 
     * @return a Cinema data type that contains the Cinema of Ticket object.
     */
	public Cinema getCinema() {
        return this.bookingCinema;
	}

	/**
	 * 
	 * @param cinema Specify the cinema of the Ticket object.
	 */
	public void setCinema(Cinema cinema) {
        this.bookingCinema = cinema;
	}
    
    /**
     * Get the cineplex of the Ticket 
     * @return a Cineplex data type that contains the cineplex of Ticket object.
     */
	public Cineplex getCineplex() {
        return this.bookingCineplex;
	}

	/**
	 * 
	 * @param cineplex Specify the cineplex of the Ticket object.
	 */
	public void setCineplex(Cineplex cineplex) {
        this.bookingCineplex = cineplex;
	}

    /**
     * Get the movie of the Ticket 
     * @return a Movie data type that contains the movie of Ticket object.
     */
	public Movie getMovie() {
        return this.bookingMovie;
	}

	/**
	 * 
	 * @param movie Specify the movie that this Ticket object is for.
	 */
	public void setMovie(Movie movie) {
        this.bookingMovie = movie;
	}
    
    /**
     * Get the seat of the Ticket 
     * @return a Seat data type that contains the seat of Ticket object.
     */
	public Seat getSeat() {
        return this.bookingSeat;
	}

	/**
	 * 
	 * @param seat Specify the seat that this Ticket object is for.
	 */
	public void setSeat(Seat seat) {
        this.bookingSeat = seat;
	}

}
