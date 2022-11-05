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
     * Seat's type
     * Possible seat's types: Basic, Couple, Elite
     */
    private seatType_Enum seatType;

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
     * 
     * @return this seat's ID
     */
    public String getSeatID() {
        return this.seatID;
    }

    /**
     * Set the seat's seatType
     * @param seatType  the seat's seatType
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
