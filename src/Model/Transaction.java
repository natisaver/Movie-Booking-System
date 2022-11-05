package Model;

import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Controller.PriceController;

/**
 * Represents a transaction in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 5-11-2022
 */

public class Transaction {

    /**
     * List of tickets a MovieGoer purchased under a particular TransactionID (TID)
     */

    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    /**
     * MovieGoer who made a purchase under a particular TransactionID (TID)
     */
    private MovieGoer movieGoer;

    /**
     * Puchase DateTime of the transaction
     */
    private LocalDateTime boughtTime;

    /**
     * Total price paid by a MovieGoer under a particular TransactionID (TID)
     */
    private double totalPrice = 0;

    /**
     * TransactionID displayed in the format of XXXYYYYMMDDhhmm
     */
    private String TID;

    /**
     * Email of MovieGoer who made a purchase under a particular TransactionID (TID)
     */
    private String email;

    /**
     * Number of tickets a MovieGoer purchased under a particular TransactionID
     * (TID)
     * Equal to tickets.size()
     */
    private int noOfTickets;

    /**
     * Title of the movie made under a particular TransactionID (TID)
     */
    private String movieTitle;

    /**
     * Cinema which the ticket was bought for
     */
    private Cinema cinema;

    /**
     * Cinema code of the cinema made under a particular TransactionID (TID)
     */
    private String cinemaCode;

    /**
     * Constructor
     * 
     * @param tickets   Array List of Tickets bought in this transaction
     * @param movieGoer MovieGoer Object that bought the tickets
     */
    public Transaction(ArrayList<Ticket> tickets, MovieGoer movieGoer) {
        this.tickets = tickets;
        this.movieGoer = movieGoer;
        LocalDateTime now = LocalDateTime.now();
        this.boughtTime = now;
        // calculate price of all tickets
        for (int i = 0; i < tickets.size(); i++) {
            Ticket cur = tickets.get(i);
            totalPrice += PriceController.calculatePriceDirect(cur.getShowTime(), cur.getMovieType(), cur.getAgeGroup(),
                    cur.getCinema().getCinemaClass(), cur.getSeat().getSeatType());
        }
        // other initialised attributes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String formatDateTime = now.format(formatter);
        this.TID = tickets.get(0).getCinema().getCinemaCode() + formatDateTime;
        this.email = movieGoer.getEmail();
        this.noOfTickets = tickets.size();
        this.movieTitle = tickets.get(0).getTitle();
        this.cinemaCode = tickets.get(0).getCinema().getCinemaCode();
    }

    /**
     * Get transactionID
     * 
     * @return TID
     */
    public String getTID() {
        return this.TID;
    }

    /**
     * Get array list of tickets
     * 
     * @return ArrayList<Ticket>
     */
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    /**
     * Get number of tickets
     * 
     * @return noOfTickets
     */
    public int getNoOfTickets() {
        return this.noOfTickets;
    }

    /**
     * Get total Price
     * 
     * @return totalPrice
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Get MovieGoer who made the purchase of ticket(s)
     * 
     * @return movieGoer
     */
    public MovieGoer getMovieGoer() {
        return this.movieGoer;
    }

    /**
     * Get Cinema of where the ticket(s) were bought
     * 
     * @return Cinema
     */
    public Cinema getCinema() {
        return this.cinema;
    }

    /**
     * Get DateTime of when the ticket(s) were bought
     * 
     * @return transaction DateTime
     */
    public LocalDateTime getBoughtTime() {
        return this.boughtTime;
    }

    /**
     * Get Movie Title
     * 
     * @return title
     */
    public String getMovieTitle() {
        return this.movieTitle;
    }

    /**
     * Get Email of the MovieGoer who made the purchase
     * 
     * @return Email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get CinemaCode of the cinema where the ticket was bought
     * 
     * @return CinemaCode
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

}
