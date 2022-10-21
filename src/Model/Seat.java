package Model;

/**
 Represents a single seat within a cinema's movie session in the MOBLIMA Cinema Application
 @author Sally Carrera
 @version 1.0
 @since 21-10-2022
 */

public class Seat {
    /**
     * Seat's ID
     */
    private String seatID;

    /**
     * Boolean value to check if seat is occupied
     */
    private boolean isOccupied;

    /**
     * Constructor
     * @param id        The seat's ID
     */
    public Seat(String id) {
        this.seatID = id;
        isOccupied = false;
    }
    
    /**
     * Get the seat's ID
     * @return this seat's ID
     */
    public String getSeatID() {
        return this.seatID;
    }
    
    /**
     * Check if the seat is occupied
     * @return <code>true</code> if the seat is occupied
     */
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    /**
     * Changes the value of the seat's isOccupied attribute to <code>true</code> if the seat is unoccupied,
     * otherwise sets it to <code>false</code>
     */
    public void setIsOccupied() {
        this.isOccupied = !this.isOccupied;
    }
}
