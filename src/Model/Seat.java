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
     * Seat's type
     * Possible seat's types: Basic, Couple, Elite
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
     * 
     * @return this seat's ID
     */
    public String getSeatID() {
        return this.seatID;
    }

    /**
     * Set the seat's seatType
     * @param seatType  {@link seatType_Enum} of the seat
     */
    public void setSeatType(seatType_Enum seatType) {
        this.seatType = seatType;
    }

    /**
     * Get the seat's seatType
     * 
     * @return  the seat's type
     *          Possible seat's type: (Basic, Couple, Elite)
     */
    public seatType_Enum getSeatType() {
        return this.seatType;
    }
    
    /**
     * Check if the seat is occupied
     * 
     * @return <code>true</code> if the seat is occupied else <code>false</code>
     */
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    /**
     * Changes the value of the seat's isOccupied attribute to <code>true</code> if the seat is unoccupied,
     * otherwise sets it to <code>false</code>
     */
    public void setIsOccupied() {
        this.isOccupied = true;
    }
}

