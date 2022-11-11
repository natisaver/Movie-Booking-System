package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import View.ConsoleColours;

/**
 * Represents a movie viewing session in the MOBLIMA Cinema Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */

public class MovieSession {
    /**
     * MovieSession's show time, in the format of yyyy-MM-dd HH:mm
     */
    private LocalDateTime showtime;

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

    public MovieSession(cinemaClass_Enum cinemaClass) {
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
                    if (i == 16)
                        id = "" + (char) (i + 'C') + String.valueOf(j + 3);
                    else if (i > 12)
                        id = "" + (char) (i + 'C') + String.valueOf(j + 1);
                    else
                        id = "" + (char) (i + 'B') + String.valueOf(j + 1);
                } else
                    id = "" + (char) (i + 'A') + String.valueOf(j + 1);
                sessionSeats[i][j] = new Seat(id, null);
                sessionSeats[i][j].setSeatType(seatType_Enum.BASIC);
            }
        }
        if (cinemaClass == cinemaClass_Enum.STANDARD) {
            for (int i=2;i<=9;i++) {
                sessionSeats[9][i].setSeatType(seatType_Enum.COUPLE);
            }
        }
        if (cinemaClass == cinemaClass_Enum.MAX) {
            for (int i=4;i<=27;i++) {
                sessionSeats[16][i].setSeatType(seatType_Enum.COUPLE);
            }
            for (int i=6;i<=11;i++) {
                for (int j=15;j<=20;j++) {
                    sessionSeats[i][j].setSeatType(seatType_Enum.ELITE);
                }
            }
        }
        if (cinemaClass == cinemaClass_Enum.GOLD) {
            for (int i=0;i<=7;i++) {
                sessionSeats[3][i].setSeatType(seatType_Enum.COUPLE);
            }
            for (int i=1;i<=2;i++) {
                for (int j=2;j<=5;j++) {
                    sessionSeats[i][j].setSeatType(seatType_Enum.ELITE);
                }
            }
        }
    }

    /**
     * Constructor
     * 
     * @param showtime    The MovieSession's showtime
     * @param cinemaClass The MovieSession's cinemaClass
     * @param movie       The MovieSession's movie title
     * @param movieType   The MovieSession's movie type (2D, 3D or Blockbuster)
     */
    public MovieSession(LocalDateTime showtime, cinemaClass_Enum cinemaClass, String title,
            String movieType) {
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
                sessionSeats[i][j] = new Seat(id, null);
                sessionSeats[i][j].setSeatType(seatType_Enum.BASIC);
            }
        }
        if (cinemaClass == cinemaClass_Enum.STANDARD) {
            for (int i=2;i<=9;i++) {
                sessionSeats[9][i].setSeatType(seatType_Enum.COUPLE);
            }
        }
        if (cinemaClass == cinemaClass_Enum.MAX) {
            for (int i=4;i<=27;i++) {
                sessionSeats[16][i].setSeatType(seatType_Enum.COUPLE);
            }
            for (int i=6;i<=11;i++) {
                for (int j=15;j<=20;j++) {
                    sessionSeats[i][j].setSeatType(seatType_Enum.ELITE);
                }
            }
        }
        if (cinemaClass == cinemaClass_Enum.GOLD) {
            for (int i=0;i<=7;i++) {
                sessionSeats[3][i].setSeatType(seatType_Enum.COUPLE);
            }
            for (int i=1;i<=2;i++) {
                for (int j=2;j<=5;j++) {
                    sessionSeats[i][j].setSeatType(seatType_Enum.ELITE);
                }
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
     * Get the MovieSession's date
     * 
     * @return this MovieSession's date
     */
    public String getSessionDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = this.showtime.format(formatter).substring(0, 10);
        return date;
    }

    /**
     * Get the MovieSession's time
     * 
     * @return this MovieSession's time
     */
    public String getSessionTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String time = this.showtime.format(formatter).substring(11);
        return time;
    }

    /**
     * Set the MovieSession's showtime
     * 
     * @param time MovieSession's showtime
     */
    public void setShowtime(String date, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        this.showtime = LocalDateTime.parse(date + 'T' + time, formatter);
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
     * Sets a seat as occupied in that MovieSession
     * 
     * @param id          The seat ID that the MovieGoer inputs
     * @param cinemaClass The cinemaClass of the cinema that the MovieSession is
     *                    being held in
     * @return <code>1</code> if the booking was successful, otherwise returns
     *         <code>false</code>
     */
    public ArrayList<Seat> bookSeat(String id, cinemaClass_Enum cinemaClass) {
        int row, column;
        ArrayList<Seat> bookedSeats = new ArrayList<Seat>();
        id = id.toUpperCase();
        if (cinemaClass == cinemaClass_Enum.STANDARD) {
            if ((id.equals("K3")) || (id.equals("K4")) || (id.equals("K5")) || (id.equals("K6"))
				|| (id.equals("K7")) || (id.equals("K8")) || (id.equals("K9")) || (id.equals("K10"))) {
				row = (int)(id.charAt(0) - 66);
				if (id.length() > 2) column = Integer.parseInt(id.substring(id.length()-2))-1;
				else column = Integer.parseInt(id.substring(1))-1;
				if (column%2 == 0) {
                    this.sessionSeats[row][column+1].setIsOccupied();
                    bookedSeats.add(this.sessionSeats[row][column+1]);
                }
				else {
                    this.sessionSeats[row][column-1].setIsOccupied();
                    bookedSeats.add(this.sessionSeats[row][column-1]);
                }
				this.sessionSeats[row][column].setIsOccupied();
                bookedSeats.add(this.sessionSeats[row][column]);
				return bookedSeats;
			}
        }
        if (cinemaClass == cinemaClass_Enum.MAX) {
            if ((id.equals("S7")) || (id.equals("S8")) || (id.equals("S9")) || (id.equals("S10")) ||
			(id.equals("S11")) || (id.equals("S12")) || (id.equals("S13")) || (id.equals("S14")) ||
			(id.equals("S15")) || (id.equals("S16")) || (id.equals("S17")) || (id.equals("S18")) ||
			(id.equals("S19")) || (id.equals("S20")) || (id.equals("S21")) || (id.equals("S22")) ||
			(id.equals("S23")) || (id.equals("S24")) || (id.equals("S25")) || (id.equals("S26")) ||
			(id.equals("S27")) || (id.equals("S28")) || (id.equals("S29")) || (id.equals("S30"))) {
				row = (int)(id.charAt(0) - 67);
				if (id.length() > 2) column = Integer.parseInt(id.substring(id.length()-2))-3;
				else column = Integer.parseInt(id.substring(1))-3;
				if (column%2 == 0) {
                    this.sessionSeats[row][column+1].setIsOccupied();
                    bookedSeats.add(this.sessionSeats[row][column+1]);
                }
				else {
                    this.sessionSeats[row][column-1].setIsOccupied();
                    bookedSeats.add(this.sessionSeats[row][column-1]);
                }
				this.sessionSeats[row][column].setIsOccupied();
                bookedSeats.add(this.sessionSeats[row][column]);
				return bookedSeats;
			}
			else if (Character.compare(id.charAt(0), 'S') == 0) {
                row = (int) (id.charAt(0) - 67);
                if (id.length() > 2) column = Integer.parseInt(id.substring(id.length() - 2))-3;
                else column = Integer.parseInt(id.substring(1))-3;
                this.sessionSeats[row][column].setIsOccupied();
                bookedSeats.add(this.sessionSeats[row][column]);
                return bookedSeats;
            }
        }
        if (cinemaClass == cinemaClass_Enum.GOLD) {
			if ((id.equals("D1")) || (id.equals("D2")) || (id.equals("D3")) || (id.equals("D4"))
				|| (id.equals("D5")) || (id.equals("D6")) || (id.equals("D7")) || (id.equals("D8"))) {
				row = (int)(id.charAt(0) - 65);
				if (id.length() > 2) column = Integer.parseInt(id.substring(id.length()-2))-1;
				else column = Integer.parseInt(id.substring(1))-1;
				if (column%2 == 0) {
                    this.sessionSeats[row][column+1].setIsOccupied();
                    bookedSeats.add(this.sessionSeats[row][column+1 ]);
                }
				else {
                    this.sessionSeats[row][column-1].setIsOccupied();
                    bookedSeats.add(this.sessionSeats[row][column-1]);
                }
				this.sessionSeats[row][column].setIsOccupied();
                bookedSeats.add(this.sessionSeats[row][column]);
				return bookedSeats;
			}
		}
        if (Character.compare('O', id.charAt(0)) < 0)
            row = (int) (id.charAt(0) - 67);
        else if (Character.compare('I', id.charAt(0)) < 0)
            row = (int) (id.charAt(0) - 66);
        else
            row = (int) (id.charAt(0) - 65);
        if (id.length() > 2)
            column = Integer.parseInt(id.substring(id.length() - 2)) - 1;
        else
            column = Integer.parseInt(id.substring(1)) - 1;
        this.sessionSeats[row][column].setIsOccupied();
        bookedSeats.add(this.sessionSeats[row][column]);
        return bookedSeats;
    }

    /**
     * Get the MovieSession's seating arrangement
     * 
     * @param cinemaClass The cinemaClass of the cinema that the MovieSession is
     *                    being held in
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
            for (i = 0; i <= maxCol; i++) {
                if (i == 0) {
                    System.out.print("   ");
                } else if (i == maxCol) {
                    System.out.println();
                } else if (i % 3 == 0)
                    System.out.print(" ");
                else {
                    System.out.print(count + "   ");
                    count++;
                }
            }
            for (i = 0; i < maxRow; i++) {
                int seatCount = 0;
                for (j = 0; j <= maxCol; j++) {
                    if (j == 0) {
                        char rowChar = (char) (i + 'A');
                        System.out.print(rowChar);
                    } else if (j == maxCol) {
                        char rowChar = (char) (i + 'A');
                        System.out.println(" " + rowChar);
                    } else if (j % 3 == 0)
                        System.out.print(" ");
                    else {
                        if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.BASIC) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x]");
                            else System.out.print(" [ ]");
                            seatCount++;
                        }
                        else if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.ELITE) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(ConsoleColours.YELLOW + " [x]" + ConsoleColours.RESET);
                            else System.out.print(ConsoleColours.YELLOW + " [ ]" + ConsoleColours.RESET);
                            seatCount++;
                        }
                        else if (sessionSeats[i][seatCount].getSeatType()  == seatType_Enum.COUPLE) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x   x]");
                            else System.out.print(" [_____]");
                            seatCount += 2;
                            j++;
                        }
                    }
                }
            }
            count = 1;
            boolean odd = true;
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
                else if (i==maxCol) {
                    System.out.println();
                }
                else if (i%3 == 0) {
				    System.out.print("  ");
				    odd = !odd;
			    }
                else {
				    if (odd) {
					    if (i%2==0) System.out.print("   ");
					    else System.out.print("  " + count + " ");
				    }
				    else {
					    if (i%2==1) System.out.print("   ");
					    else System.out.print("  " + count + " ");
				    }
                    count++;
                }
            }
            System.out.println();
            System.out.println("LEGEND");
            System.out.print("[ ] Empty seat    [x] Occupied seat    [_____] Couple seat    [x   x] Occupied couple seat");
		    System.out.print(ConsoleColours.YELLOW + "    [ ]" + ConsoleColours.RESET);
		    System.out.print(" Empty elite seat");
		    System.out.print(ConsoleColours.YELLOW + "    [x]" + ConsoleColours.RESET);
		    System.out.print(" Occupied elite seat");
            System.out.println();
        }
        else if (cinemaClass == cinemaClass_Enum.MAX) {
            maxRow = 17;
            maxCol = 39;
            char rowChar;
            System.out.println(
                    "  =====================================================================SCREEN======================================================================");
            for (i = 0; i <= maxCol; i++) {
                if (i == 0) {
                    System.out.print("   ");
                } else if (i == maxCol) {
                    System.out.println();
                } else if ((i == 9) || (i == 30))
                    System.out.print(" ");
                else {
                    if (count < 9)
                        System.out.print(count + "   ");
                    else
                        System.out.print(count + "  ");
                    count++;
                }
            }
            for (i = 0; i < maxRow; i++) {
                int seatCount = 0;
                for (j = 0; j <= maxCol; j++) {
                    if (j == 0) {
                        if (i > 12)
                            rowChar = (char) (i + 'C');
                        else if (i > 7)
                            rowChar = (char) (i + 'B');
                        else
                            rowChar = (char) (i + 'A');
                        System.out.print(rowChar);
                    } else if (j == maxCol) {
                        if (i > 12)
                            rowChar = (char) (i + 'C');
                        else if (i > 7)
                            rowChar = (char) (i + 'B');
                        else
                            rowChar = (char) (i + 'A');
                        System.out.println(" " + rowChar);
                    } else if ((j == 9) || (j == 30))
                        System.out.print(" ");
                    else if ((i == maxRow - 1) && ((j == 1) || (j == 2) || (j == 37) || (j == 38)))
                        System.out.print("    ");
                    else {
                        if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.BASIC) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x]");
                            else System.out.print(" [ ]");
                            seatCount++;
                        }
                        else if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.ELITE) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(ConsoleColours.YELLOW + " [x]" + ConsoleColours.RESET);
                            else System.out.print(ConsoleColours.YELLOW + " [ ]" + ConsoleColours.RESET);
                            seatCount++;
                        }
                        else if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.COUPLE) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x   x]");
                            else System.out.print(" [_____]");
                            seatCount += 2;
                            j++;
                        }
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
                else if ((i==9) || (i==30)) System.out.print("  ");
                else {
				    if ((i>=7) && (i<=8)) {
					    if (i%2==0) System.out.print("   ");
					    else System.out.print("  " + count + " ");
				    }
				    else if ((i>=10) && (i<=29)) {
					    if (i%2==1) System.out.print("   ");
					    else System.out.print("  " + count + " ");
				    }
				    else if ((i>=31) && (i<=32)) {
					    if (i%2==0) System.out.print("  ");
					    else System.out.print(" " + count + "  ");
				    }
                    else if (count >= 31) System.out.print(count + "  ");
				    else System.out.print(count + "   ");
                    count++;
                }
            }
            System.out.println();
            System.out.println("LEGEND");
            System.out.print("[ ] Empty seat    [x] Occupied seat    [_____] Couple seat    [x   x] Occupied couple seat");
		    System.out.print(ConsoleColours.YELLOW + "    [ ]" + ConsoleColours.RESET);
		    System.out.print(" Empty elite seat");
		    System.out.print(ConsoleColours.YELLOW + "    [x]" + ConsoleColours.RESET);
		    System.out.print(" Occupied elite seat");
            System.out.println();
        }
        else if (cinemaClass == cinemaClass_Enum.STANDARD) {
            maxRow = 10;
            maxCol = 15;
            char rowChar;
            System.out.println("  ======================SCREEN=======================");
            for (i=0;i<=maxCol;i++) {
                if (i==0) {
                    System.out.print("   ");
                }
			    else if (i==7) System.out.print("    ");
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
                    else if (j==7) System.out.print("    ");
                    else {
				        if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.BASIC) {
                            if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x]");
                            else System.out.print(" [ ]");
					        seatCount++;
				        }
				        else if (sessionSeats[i][seatCount].getSeatType() == seatType_Enum.COUPLE) {
					        if (sessionSeats[i][seatCount].getIsOccupied()) System.out.print(" [x   x]");
					        else System.out.print(" [_____]");
					        seatCount += 2;
					        j++;
				        }
                    }
                }
            }
            count = 1;
            for (i = 0; i <= maxCol; i++) {
                if (i == 0) {
                    System.out.print("   ");
                }
                else if (i == maxCol) {
                    System.out.println();
                }
                else {
				    if (i==7) {
					    System.out.print("   ");
					    continue;
				    }
                    else if ((i >= 3) && (i <= 6)) {
				        if (i%2 == 1) System.out.print("  " + count + "  ");
				        else System.out.print("   ");
				    }
				    else if ((i >= 8) && (i <= 11)) {
				        if (i%2 == 0) System.out.print("   " + count + " ");
				        else System.out.print("   ");
				    }
				    else if (i > 11) System.out.print(count + "  ");
                    else System.out.print(count + "   ");
                    count++;
                }
            }
            System.out.println();
            System.out.println("LEGEND");
            System.out.println("[ ] Empty seat    [x] Occupied seat    [_____] Couple seat    [x   x] Occupied couple seat");
            System.out.println();
        }
    }
}
