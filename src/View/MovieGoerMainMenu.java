package View;

import java.util.Scanner;

import Model.MovieGoer;

/**
 * MovieGoer Main Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public class MovieGoerMainMenu extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    /**
     * Current User
     */
    MovieGoer moviegoer = null;

    /**
     * Constructor to store previous page and access level
     * 
     * @param previousMenu the previous page
     * @param accesslevel  the level of access
     * @param moviegoer    movieGoer Object
     */
    public MovieGoerMainMenu(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer) {
        super(previousMenu, accesslevel);
        this.moviegoer = moviegoer;
    }

    @Override
    public BaseMenu execute() {
        int choice;

        System.out.println(ConsoleColours.YELLOW_BOLD + "Customer Menu Options:" + ConsoleColours.RESET);
        System.out.println("1. Book ticket");
        System.out.println("2. Leave a review");
        System.out.println("3. Check booking history");
        if (this.moviegoer == null) {
            System.out.println("4. Back");
        } else {
            System.out.println("4. Logout");
        }

        BaseMenu nextMenu = this;

        do {
            System.out.print("Enter your choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    nextMenu = new BookTicket(this, -1, moviegoer);
                    break;
                case 2:
                    if (moviegoer == null) {
                        nextMenu = new CreateOrLogin(nextMenu, -1);
                    } else {
                        System.out.println("You can leave a review");
                    }
                    // nextMenu = new LeaveReview(this);
                    break;
                case 3:
                    if (moviegoer == null) {
                        nextMenu = new CreateOrLogin(nextMenu, -1);
                    } else {
                        nextMenu = new CheckHistory(this, 0, moviegoer);
                    }
                    break;
                case 4:
                    nextMenu = new MainMenu(null, -1);
                    break;
                default:
                    choice = -1;
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice." + ConsoleColours.RESET);
                    break;
            }
        } while (choice == -1);

        return nextMenu;

    }
}
