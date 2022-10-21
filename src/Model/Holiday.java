package Model;

import java.time.LocalDateTime;

 /**
 * Represents date and price of tickets on public holidays in the MOBLIMA Cinema Application
 * @author CS2002 Group 
 * @version 1.0
 * @since 21-10-2022
 */
public class Holiday {
    /**
	 * Public holiday name
	 */
    private String name;

    /**
	 * Public holiday date
	 */
    private LocalDateTime date;

    /**
	 * Public holiday ticket price rate
	 */
    private float price;

    /**
     * Constructor
     * @param name          The name of the public holiday
     * @param date          The date of the holiday
     * @param price         The price of tickets on the holiday
     */
    public Holiday(String name, LocalDateTime date, float price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }

    /**
     * Get the price of the ticket on the holiday
     * @return      The name of the public holiday
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get date of holiday
     * @return      The date of holiday
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Set the price of the tickets on that holiday
     * @param price     The ticket price on the holiday
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the price of the ticket on the holiday
     * @return      The price of the ticket on the holiday
     */
    public float getPrice() {
        return this.price;
    }

}
