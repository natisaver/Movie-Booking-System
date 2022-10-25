package Model;

import java.time.format.DateTimeFormatter;

/**
 * Represents a transaction in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 18-10-2022
 */

public class Transaction {

    /**
     * TransactionID displayed in the format of XXXYYYYMMDDhhmm
     */
    private String TID;

    /**
     * List of tickets a MovieGoer purchased under a particular TransactionID (TID)
     */
    private Ticket[] tickets;

    /**
     * Number of tickets a MovieGoer purchased under a particular TransactionID
     * (TID)
     * This is number of tickets is also equals to the length of Tickets[]
     */
    private int noOfTickets;

    /**
     * Total price paid by a MovieGoer under a particular TransactionID (TID)
     */
    private float totalPrice;

    /**
     * MovieGoer who made a purchase under a particular TransactionID (TID)
     */
    private MovieGoer movieGoer;

    public Transaction(String cinemaCode, Ticket[] tickets, int noOfTickets, float totalPrice, MovieGoer movieGoer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.TID = cinemaCode.concat(formatter.toString());
        this.tickets = tickets;
        this.noOfTickets = tickets.length;
        this.totalPrice = totalPrice;
        this.movieGoer = movieGoer;
    }

    public String getTID() {
        return TID;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public String printTransaction() {
        String toReturn = "";
        toReturn = "TID" + TID + "no. of tickets" + noOfTickets;
        System.out.println(toReturn);
        return toReturn;
    }

}
