package Model;

/**
 Represents a single seat within a cinema's movie session in the MOBLIMA Cinema Application
 @author Sally Carrera
 @version 1.0
 @since 5-11-2022
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
     * Enum value to check seat type
     */
    private seatType_Enum seatType;

    /**
     * Constructor
     * @param id            The seat's ID
     * @param seatType      The seat type
     */
    public Seat(String id, seatType_Enum seatType) {
        this.seatID = id;
        isOccupied = false;
        this.seatType = seatType;
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
    
    /**
     * Get the seat type
     * @return this seat type 
     */
    public seatType_Enum getSeatType() {
        return this.seatType;
    }

    /**
     * Set the seat type
     */
    public void setSeatType(seatType_Enum seatType) {
        this.seatType = seatType;
    }

}

