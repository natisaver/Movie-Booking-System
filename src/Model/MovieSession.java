package Model;

public class MovieSession {
    private LocalTime showtime;
    private Seat[] sessionSeats;
    private Movie movie;

    public LocalTime getShowtime() {
        return this.showtime;
    }

    /**
     * 
     * @param time
     */
    public void setShowtime(LocalTime time) {
        this.showtime = time;
    }

}
