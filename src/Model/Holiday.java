package Model;

import java.time.LocalDateTime;

 /**
 * Represents name and date of public holidays in the MOBLIMA Cinema Application
 * @author Sally Carrera
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
     * Constructor
     * @param name          The name of the public holiday
     * @param date          {@link LocalDateTime} of the date of the holiday
     */
    public Holiday(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Get the Name of the Holiday
     * @return      The name of the public holiday
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the data of the Holiday
     * @return      The name of the public holiday
     */
    public LocalDateTime getDate() {
        return this.date;
    }

}
