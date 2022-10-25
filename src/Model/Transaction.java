package Model;

import java.time.format.DateTimeFormatter;

public class Transaction {

    private String TID;

    private Ticket[] tickets;

    private int noOfTickets;

    private float totalPrice;

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
