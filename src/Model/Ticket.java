package Model;

/**
 * Represents a ticket in the MOBLIMA Cinema Application
 * @author CS2002 Group 
 * @version 1.0
 * @since 18-10-2022
 */

public class Ticket 
{
    private int transactionID; 
    private float price;
    private ageGroup_Enum ageGroup; 
    private MovieSession bookingSession;
    private Cinema bookingCinema;
    private Cineplex bookingCineplex; 
    private Movie bookingMovie;
    private Seat bookingSeat;

    public Ticket(int transactionID, float price, ageGroup_Enum ageGroup, MovieSession bookingSession, Cinema bookingCinema, Cineplex bookingCineplex, Movie bookingMovie, Seat bookingSeat);
    
}
