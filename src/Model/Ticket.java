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
	 * @return Cineplex of Ticket object.
	 */
	public Cineplex getCineplex() {
		return this.ticketCineplex;
	}

	/**
	 * Set Cineplex of Ticket.
	 * 
	 * @param tCineplex Cineplex of Ticket object.
	 */
	public void setCineplex(Cineplex tCineplex) {
		this.ticketCineplex = tCineplex;
	}

	/**
	 * Get Cinema of Ticket.
	 * 
	 * @return Cinema of Ticket object.
	 */
	public Cinema getCinema() {
		return this.ticketCinema;
	}

	/**
	 * Set Cinema of Ticket.
	 * 
	 * @param tCinema Cinema of Ticket object.
	 */
	public void setCinema(Cinema tCinema) {
		this.ticketCinema = tCinema;
	}

	/**
	 * Get Showtime of Ticket.
	 * 
	 * @return Showtime of Ticket object.
	 */
	public LocalDateTime getShowTime() {
		return this.ticketShowTime;
	}

	/**
	 * Set Showtime of Ticket.
	 * 
	 * @param tShowTime Showtime of Ticket object.
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
	 * @return Movie Type of Ticket object.
	 */
	public movieType_Enum getMovieType() {
		return this.ticketMovieType;
	}

	/**
	 * Set Movie Type of Ticket.
	 * 
	 * @param tMovieType_Enum Movie Type of Ticket Object.
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
	 * 
	 * @param tMovieRating Movie Rating of Ticket object.
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
	 * 
	 * @param tSeat SeatID of Ticket object.
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
	 * @param tAgeGroup Age Group of Ticket object.
	 */
	public void setAgeGroup(ageGroup_Enum tAgeGroup) {
		this.ticketAgeGroup = tAgeGroup;
	}
}
