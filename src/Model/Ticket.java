package Model;

import java.time.LocalDateTime;

/**
 * Represents a ticket in the MOBLIMA Cinema Application
 * @author Sally Carrera 
 * @version 1.0
 * @since 18-10-2022
 */

 public class Ticket {
	private Cineplex ticketCineplex;
	private Cinema ticketCinema;
	private LocalDateTime ticketShowTime;
	private String ticketMovieTitle;
	private movieType_Enum ticketMovieType;
	private movieRating_Enum ticketMovieRating;
	private int ticketSeat;
	private ageGroup_Enum ticketAgeGroup;

	public Ticket(Cineplex tCineplex, Cinema tCinema, LocalDateTime tShowTime, String tMovieTitle, movieType_Enum tMovieType, movieRating_Enum tMovieRating, int tSeat, ageGroup_Enum tAgeGroup)
	{
		this.ticketCineplex = tCineplex;
		this.ticketCinema = tCinema;
		this.ticketShowTime = tShowTime;
		this.ticketMovieTitle = tMovieTitle;
		this.ticketMovieType = tMovieType;
		this.ticketMovieRating = tMovieRating;
		this.ticketSeat = tSeat;
		this.ticketAgeGroup = tAgeGroup;
	}

	public Cineplex getCineplex()
	{
		return this.ticketCineplex;
	}

	public void setCineplex(Cineplex tCineplex)
	{
		this.ticketCineplex = tCineplex;
	}

	public Cinema getCinema()
	{
		return this.ticketCinema;
	}

	public void setCinema(Cinema tCinema)
	{
		this.ticketCinema = tCinema;
	}

	public LocalDateTime getShowTime()
	{
		return this.ticketShowTime;
	}

	public void setShowtime(LocalDateTime tShowTime)
	{
		this.ticketShowTime = tShowTime;
	}

	public String getTitle()
	{
		return this.ticketMovieTitle;
	}

	public void setTitle(String tMovieTitle)
	{
		this.ticketMovieTitle = tMovieTitle;
	}

	public movieType_Enum getMovieType()
	{
		return this.ticketMovieType;
	}

	public void setMovieType(movieType_Enum tMovieType_Enum)
	{
		this.ticketMovieType = tMovieType_Enum;
	}

	public movieRating_Enum getMovieRating()
	{
		return this.ticketMovieRating;
	}

	public void setMovieRating(movieRating_Enum tMovieRating)
	{
		this.ticketMovieRating = tMovieRating;
	}

	public int getSeat()
	{
		return this.ticketSeat;
	}

	public void setSeat(int tSeat)
	{
		this.ticketSeat = tSeat;
	}

	public ageGroup_Enum getAgeGroup()
	{
		return this.ticketAgeGroup;
	}

	public void setAgeGroup(ageGroup_Enum tAgeGroup)
	{
		this.ticketAgeGroup = tAgeGroup;
	}
 }
 