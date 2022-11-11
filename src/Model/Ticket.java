package Model;

import java.time.LocalDateTime;

/**
 * Represents a ticket in the MOBLIMA Cinema Application
 * @author Sally Carrera 
 * @version 1.0
 * @since 18-10-2022
 */

/**
 * Constructor
 * 
 * @param ticketCineplex    Ticket for which Cineplex
 * @param ticketCinema      Ticket for which Cinema
 * @param ticketShowTime    Ticket for which Showtime
 * @param ticketMovieTitle  Ticket for which Movie
 * @param ticketMovieType   Ticket for which Movie Type
 * @param ticketMovieRating Ticket for which Movie Rating
 * @param ticketSeat        Ticket for which Seat
 * @param ticketAgeGroup    Ticket for which Age Group
 */
public class Ticket {
	private Cineplex ticketCineplex;
	private Cinema ticketCinema;
	private LocalDateTime ticketShowTime;
	private String ticketMovieTitle;
	private movieType_Enum ticketMovieType;
	private movieRating_Enum ticketMovieRating;
	private Seat ticketSeat;
	private ageGroup_Enum ticketAgeGroup;

	/**
	 * Constructor
	 */
	public Ticket() {}

	/**
	 * Constructor
	 * 
	 * @param ticketCineplex    Ticket for which Cineplex
	 * @param ticketCinema      Ticket for which Cinema
	 * @param ticketShowTime    Ticket for which Showtime
	 * @param ticketMovieTitle  Ticket for which Movie
	 * @param ticketMovieType   Ticket for which Movie Type
	 * @param ticketMovieRating Ticket for which Movie Rating
	 * @param ticketSeat        Ticket for which Seat
	 * @param ticketAgeGroup    Ticket for which Age Group
	 */

	public Ticket(Cineplex tCineplex, Cinema tCinema, LocalDateTime tShowTime, String tMovieTitle,
			movieType_Enum tMovieType, movieRating_Enum tMovieRating, Seat tSeat, ageGroup_Enum tAgeGroup) {
		this.ticketCineplex = tCineplex;
		this.ticketCinema = tCinema;
		this.ticketShowTime = tShowTime;
		this.ticketMovieTitle = tMovieTitle;
		this.ticketMovieType = tMovieType;
		this.ticketMovieRating = tMovieRating;
		this.ticketSeat = tSeat;
		this.ticketAgeGroup = tAgeGroup;
	}

	/**
	 * Get Cineplex of Ticket.
	 * 
	 * @return {@link Cineplex} of Ticket
	 */
	public Cineplex getCineplex() {
		return this.ticketCineplex;
	}

	/**
	 * Set Cineplex of Ticket.
	 * 
	 * @param tCineplex {@link Cineplex} of Ticket
	 */
	public void setCineplex(Cineplex tCineplex) {
		this.ticketCineplex = tCineplex;
	}

	/**
	 * Get Cinema of Ticket.
	 * 
	 * @return {@link Cinema} of Ticket
	 */
	public Cinema getCinema() {
		return this.ticketCinema;
	}

	/**
	 * Set Cinema of Ticket.
	 * 
	 * @param tCinema {@link Cinema} of Ticket
	 */
	public void setCinema(Cinema tCinema) {
		this.ticketCinema = tCinema;
	}

	/**
	 * Get Showtime of Ticket.
	 * @return {@link LocalDateTime} of Ticket
	 */
	public LocalDateTime getShowTime() {
		return this.ticketShowTime;
	}

	/**
	 * Set Showtime of Ticket.
	 * 
	 * @param tShowTime {@link LocalDateTime} of Ticket
	 */
	public void setShowtime(LocalDateTime tShowTime) {
		this.ticketShowTime = tShowTime;
	}

	/**
	 * Get Movie Title of Ticket.
	 * 
	 * @return Movie Title of Ticket.
	 */
	public String getTitle() {
		return this.ticketMovieTitle;
	}

	/**
	 * Set Movie Title of Ticket.
	 * 
	 * @param tMovieTitle Movie Title of Ticket object.
	 */
	public void setTitle(String tMovieTitle) {
		this.ticketMovieTitle = tMovieTitle;
	}

	/**
	 * Get Movie Type of Ticket.
	 * 
	 * @return {@link movieType_Enum} of Ticket
	 */
	public movieType_Enum getMovieType() {
		return this.ticketMovieType;
	}

	/**
	 * Set Movie Type of Ticket.
	 * @param tMovieType_Enum {@link movieType_Enum} of Ticket
	 */
	public void setMovieType(movieType_Enum tMovieType_Enum) {
		this.ticketMovieType = tMovieType_Enum;
	}

	/**
	 * Get Movie Rating of Ticket.
	 * 
	 * @return Movie Rating of Ticket.
	 */
	public movieRating_Enum getMovieRating() {
		return this.ticketMovieRating;
	}

	/**
	 * Set Movie Rating of Ticket.
	 * @param tMovieRating_Enum {@link movieRating_Enum} of Ticket
	 */
	public void setMovieRating(movieRating_Enum tMovieRating) {
		this.ticketMovieRating = tMovieRating;
	}

	/**
	 * Get Seat of Ticket.
	 * 
	 * @return Seat of Ticket.
	 */
	public Seat getSeat() {
		return this.ticketSeat;
	}

	/**
	 * Set Seat of Ticket.
	 * @param tSeat {@link Seat} of Ticket
	 */
	public void setSeat(Seat tSeat) {
		this.ticketSeat = tSeat;
	}

	/**
	 * Get Age Group of Ticket.
	 * 
	 * @return Age Group of Ticket.
	 */
	public ageGroup_Enum getAgeGroup() {
		return this.ticketAgeGroup;
	}

	/**
	 * Set Age Group of Ticket.
	 * 
	 * @param tAgeGroup_Enum {@link ageGroup_Enum} of Ticket
	 */
	public void setAgeGroup(ageGroup_Enum tAgeGroup) {
		this.ticketAgeGroup = tAgeGroup;
	}
}
