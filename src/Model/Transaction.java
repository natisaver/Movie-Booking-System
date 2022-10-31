package Model;

import java.time.LocalDateTime;
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
     * List of tickets a MovieGoer purchased under a particular TransactionID (TID)
     */
    private Ticket[] tickets;

    /**
     * MovieGoer who made a purchase under a particular TransactionID (TID)
     */
    private MovieGoer movieGoer;

    /**
     * TransactionID displayed in the format of XXXYYYYMMDDhhmm
     */
    private String TID;

    /**
     * Name of MovieGoer who made a purchase under a particular TransactionID (TID)
     */
    private String name;

    /**
     * Number of tickets a MovieGoer purchased under a particular TransactionID
     * (TID)
     * This is number of tickets is also equals to the length of Tickets[]
     */
    private int noOfTickets;

    /**
     * Title of the movie made uner a particular TransactionID (TID)
     */
    private String movieTitle;

    /**
     * Showtime of the movie made under a particular TransactionID (TID)
     */
    private LocalDateTime showtime;

    /**
     * Total price paid by a MovieGoer under a particular TransactionID (TID)
     */
    private float totalPrice;

    /**
     * Constructor
     * 
     * @param cinemaCode  Cinema's code
     * @param name        Name of MovieGoer who made a purchase under a particular
     *                    TransactionID (TID)
     * @param noOfTickets Number of tickets a MovieGoer purchased under a particular
     *                    TransactionID
     * @param movieTitle  Title of the movie made uner a particular TransactionID
     *                    (TID)
     * @param showtime    Showtime of the movie made under a particular
     *                    TransactionID (TID)
     * @param totalPrice  Total price paid by a MovieGoer under a particular
     *                    TransactionID (TID)
     */
    public Transaction(String TID, String name, int noOfTickets, String movieTitle, LocalDateTime showtime,
            float totalPrice) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // this.TID = cinemaCode.concat(formatter.toString());
        this.TID = TID;
        this.name = movieGoer.getName();
        this.noOfTickets = tickets.length;
        this.movieTitle = tickets[0].getTitle();
        this.showtime = tickets[0].getShowTime();
        this.totalPrice = totalPrice;
    }

    /**
     * Get transactionID
     * 
     * @return getTID
     */
    public String getTID() {
        return TID;
    }

    /**
     * Get list of tickets
     * 
     * @return tickets
     */
    public Ticket[] getTickets() {
        return tickets;
    }

    /**
     * Get number of tickets
     * 
     * @return noOfTickets
     */
    public int getNoOfTickets() {
        noOfTickets = tickets.length;
        return noOfTickets;
    }

    /**
     * Get total Price
     * 
     * @return totalPrice
     */
    public float getTotalPrice() {
        int i;
        for (i = 0; i < tickets.length; i++) {
            // totalPrice += tickets[i].getPrice();
        }
        return totalPrice;
    }

    /**
     * Get MovieGoer who made the purchase of ticket(s)
     * 
     * @return movieGoer
     */
    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    // public String printTransaction() {
    // String toReturn = "";
    // toReturn = "TID" + TID + "no. of tickets" + noOfTickets;
    // System.out.println(toReturn);
    // return toReturn;
    // }

}
