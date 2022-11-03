package Model;

import java.time.LocalDateTime;

/**
 * Represents a movie viewing session in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */

public class MovieSession {
    /**
     * MovieSession's show time, in the format of dd-MM-yyyy HH:mm
     */
    private static LocalDateTime showtime;

    /**
     * MovieSession's seat arrangement, represented as a 2D array, depending on the
     * cinema's class
     */
    private Seat[][] sessionSeats;

    /**
     * Title of movie being screened in the MovieSession
     */
    private String movieTitle;

    /**
     * Type of movie in the MovieSession (2D, 3D or Blockbuster)
     */
    private movieType_Enum movieType;

    /**
     * Constructor
     * 
     * @param showtime    The MovieSession's showtime
     * @param cinemaClass The MovieSession's cinemaClass
     * @param movie       The MovieSession's movie title
     * @param movieType   The MovieSession's movie type (2D, 3D or Blockbuster)
     */
    public MovieSession(LocalDateTime showtime, cinemaClass_Enum cinemaClass, String title, String movieType) {
        this.showtime = showtime;
        int maxRow = 0;
        int maxCol = 0;
        if (cinemaClass == cinemaClass_Enum.GOLD) {
            maxRow = 4;
            maxCol = 8;
        } else if (cinemaClass == cinemaClass_Enum.MAX) {
            maxRow = 17;
            maxCol = 36;
        } else if (cinemaClass == cinemaClass_Enum.STANDARD) {
            maxRow = 10;
            maxCol = 13;
        }
        this.sessionSeats = new Seat[maxRow][maxCol];
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                String id;
                if (i > 7) {
                    if (i > 12)
                        id = "" + (char) (i + 'C') + String.valueOf(j + 1);
                    else
                        id = "" + (char) (i + 'B') + String.valueOf(j + 1);
                } else
                    id = "" + (char) (i + 'A') + String.valueOf(j + 1);
                sessionSeats[i][j] = new Seat(id);
            }
        }
        this.movieTitle = title;
        this.movieType = movieType_Enum.valueOf(movieType);
    }

    /**
     * Get the MovieSession's showtime
     * 
     * @return this MovieSession's showtime
     */
    public LocalDateTime getShowtime() {
        return this.showtime;
    }

    /**
     * Set the MovieSession's showtime
     * 
     * @param time MovieSession's showtime
     */
    public static void setShowtime(LocalDateTime time) {
        MovieSession.showtime = time;
    }

    /**
     * Get the MovieSession's screened movie title
     * 
     * @return MovieSession's movie title
     */
    public String getTitle() {
        return this.movieTitle;
    }

    /**
     * Get the MovieSession's movie type
     * 
     * @return MovieSession's movie type
     */
    public movieType_Enum getMovieType() {
        return this.movieType;
    }

    /**
     * Books a seat in that MovieSession
     * @param id            The seat ID that the MovieGoer inputs
     * @param cinemaClass   The cinemaClass of the cinema that the MovieSession is being held in
     * @return              <code>true</code> if the booking was successful, otherwise returns <code>false</code>
     */
    public boolean bookSeat(String id, cinemaClass_Enum cinemaClass) {
        if (cinemaClass == cinemaClass_Enum.STANDARD) {
            if (id.equals("K5")) {
                System.out.println("Invalid seat! Please choose again");
                return false;
            }
        }
        if (cinemaClass == cinemaClass_Enum.MAX) {
            if ((id.equals("S1")) || (id.equals("S2")) || (id.equals("S35")) || (id.equals("S36"))) {
                System.out.println("Invalid seat! Please choose again");
                return false;
            }
        }
		int row, column;
        if (cinemaClass == cinemaClass_Enum.STANDARD) {
            if (Character.compare(id.charAt(0), 'K') == 0) {
				row = (int)(id.charAt(0) - 66);
				if (id.length() > 2) column = Integer.parseInt(id.substring(id.length()-2))-1;
				else column = Integer.parseInt(id.substring(1))-1;
				if (column >= 5) column -= 1;
				this.sessionSeats[row][column].setIsOccupied();
				return true;
			}
		}
        if (cinemaClass == cinemaClass_Enum.MAX) {
			if (Character.compare(id.charAt(0), 'S') == 0) {
				row = (int)(id.charAt(0) - 67);
				if (id.length() > 2) column = Integer.parseInt(id.substring(id.length()-2))-3;
				else column = Integer.parseInt(id.substring(1))-3;
				this.sessionSeats[row][column].setIsOccupied();
				return true;
			}
		}
		if (Character.compare('O', id.charAt(0)) < 0) row = (int)(id.charAt(0) - 67);
		else if (Character.compare('I', id.charAt(0)) < 0) row = (int)(id.charAt(0) - 66);
		else row = (int)(id.charAt(0) - 65);
		if (id.length() > 2) column = Integer.parseInt(id.substring(id.length()-2))-1;
		else column = Integer.parseInt(id.substring(1))-1;
		this.sessionSeats[row][column].setIsOccupied();
        return true;
	}

    /**
     * Get the MovieSession's seating arrangement
     * @param cinemaClass The cinemaClass of the cinema that the MovieSession is being held in
     */
    public void showSeatings(cinemaClass_Enum cinemaClass) {
        int i, j;
        int count = 1;
        int maxRow = 0;
        int maxCol = 0;
        if (cinemaClass == cinemaClass_Enum.GOLD) {
            maxRow = 4;
            maxCol = 12;
            System.out.println("  ==============SCREEN==============");
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else if (i%3 == 0) System.out.print(" ");
                else {
                    System.out.print(count + "   ");
                    count++;
                }
            }
            for (i=0;i<maxRow;i++) {
                int seatCount = 0;
                for (j=0;j<=maxCol;j++) {
                    if (j==0) {
                        char rowChar = (char)(i+'A');
                        System.out.print(rowChar);
                    }
                    else if (j==maxCol) {
                        char rowChar = (char)(i+'A');
                        System.out.println(" "+rowChar);
                    }
                    else if (j%3 == 0) System.out.print(" ");
                    else {
                        if (this.sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x]");
                        else System.out.print(" [ ]");
                        seatCount++;
                    }
                }
            }
            count = 1;
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else if (i%3 == 0) System.out.print(" ");
                else {
                    System.out.print(count + "   ");
                    count++;
                }
            }
        } else if (cinemaClass == cinemaClass_Enum.MAX) {
            maxRow = 17;
            maxCol = 39;
            char rowChar;
            System.out.println("  =====================================================================SCREEN======================================================================");
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else if ((i==9) || (i==30)) System.out.print(" ");
                else {
                    if (count<9) System.out.print(count + "   ");
                    else System.out.print(count + "  ");
                    count++;
                }
            }
            for (i=0;i<maxRow;i++) {
                int seatCount = 0;
                for (j=0;j<=maxCol;j++) {
                    if (j==0) {
                        if (i>12) rowChar = (char)(i+'C');
                        else if (i>7) rowChar = (char)(i+'B');
                        else rowChar = (char)(i+'A');
                        System.out.print(rowChar);
                    }
                    else if (j==maxCol) {
                        if (i>12) rowChar = (char)(i+'C');
                        else if (i>7) rowChar = (char)(i+'B');
                        else rowChar = (char)(i+'A');
                        System.out.println(" " + rowChar);
                    }
                    else if ((j==9) || (j==30)) System.out.print(" ");
                    else if ((i==maxRow-1) && ((j==1) || (j==2) || (j==37) || (j==38))) System.out.print("    ");
                    else {
                        if (this.sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x]");
                        else System.out.print(" [ ]");
                        seatCount++;
                    }
                }
            }
            count = 1;
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else if ((i==9) || (i==30)) System.out.print(" ");
                else {
                    if (count<9) System.out.print(count + "   ");
                    else System.out.print(count + "  ");
                    count++;
                }
            }
        } else if (cinemaClass == cinemaClass_Enum.STANDARD) {
            maxRow = 10;
            maxCol = 14;
            char rowChar;
            System.out.println("  ======================SCREEN=======================");
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else {
                    if (count<9) System.out.print(count + "   ");
                    else System.out.print(count + "  ");
                    count++;
                }
            }
            for (i=0;i<maxRow;i++) {
                int seatCount = 0;
                for (j=0;j<=maxCol;j++) {
                    if (j==0) {
                        if (i>7) rowChar = (char)(i+'B');
                        else rowChar = (char)(i+'A');
                        System.out.print(rowChar);
                    }
                    else if (j==maxCol) {
                        if (i>7) rowChar = (char)(i+'B');
                        else rowChar = (char)(i+'A');
                        System.out.println(" "+rowChar);
                    }
                    else if ((i==maxRow-1) && (j==5)) System.out.print("    ");
                    else {
                        if (this.sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x]");
                        else System.out.print(" [ ]");
                        seatCount++;
                    }
                }
            }
            count = 1;
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else {
                    if (count<9) System.out.print(count + "   ");
                    else System.out.print(count + "  ");
                    count++;
                }
            }
        }
    }
}
