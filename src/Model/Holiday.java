package Model;

import java.util.Date;

/**
 * This class contains information about holiday 
 * - including its date and price of ticket
 */
public class Holiday {

    private Date date;
    private float price;

    /**
     * Constructor to initialize the date of holiday and price of ticket on the holiday
     * @param date the date of the holiday
     * @param price the price of tickets on the holiday
     */
    public Holiday(Date date, float price) {
        this.date = date;
        this.price = price;
    }

    /**
     * This method is to get date of holiday
     * @return the date of holiday
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * This method is to set the price of the tickets on that holiday
     * @param price is the ticket price on the holiday
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * This method is to get the price of the ticket on the holiday
     * @return price of the ticket on the holiday
     */
    public float getPrice() {
        return this.price;
    }

}
