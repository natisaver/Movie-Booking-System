package Model;

public class Seat {

    private String seatID;
    private int seatRow;
    private int seatCol;
    private boolean isOccupied;

    public Seat(String id) {
        this.seatID = id;
        char row = id.charAt(0);
        this.seatRow = ((int)row - 65);
        char col = id.charAt(1);
        this.seatCol = ((int)col - 49);
        isOccupied = false;
    }

    public String getSeatID() {
        return this.seatID;
    }

    public int getSeatRow() {
        return this.seatRow;
    }

    public int getSeatCol() {
        return this.seatCol;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    public void setIsOccupied() {
        this.isOccupied = !this.isOccupied;
    }
}
