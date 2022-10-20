package Model;

import java.time.LocalTime;

public class MovieSession {
    private LocalTime showtime;
    private Seat[][] sessionSeats;
    private String movieTitle;

    public MovieSession(LocalTime time, int maxRow, int maxCol, String title) {
        this.showtime = time;
        this.sessionSeats = new Seat[maxRow][maxCol];
        for (int i=0;i<maxRow;i++) {
            for (int j=0;j<maxCol;j++) {
                String id = "" + (char)(i+'A') + String.valueOf(j+1);
                sessionSeats[i][j] = new Seat(id);
            }
        }
        this.movieTitle = title;
    }

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

    public String getTitle() {
        return this.movieTitle;
    }

}
