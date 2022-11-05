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
        String numregex = "^(?!(0))[0-4]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Customer Menu Options:" + ConsoleColours.RESET);
        System.out.println("1. Book ticket");
        System.out.println("2. Leave a review");
        System.out.println("3. Check booking history");
        if (this.moviegoer == null) {
            System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
        } else {
            System.out.println(ConsoleColours.YELLOW + "4. Logout" + ConsoleColours.RESET);
        }
        System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);

        BaseMenu nextMenu = this;

        // keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();

        while (!choicestr.matches(numregex)) {
            // early termination
            if (choicestr.isBlank()) {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
        }

        choice = Integer.valueOf(choicestr);

        switch (choice) {
            case 1:
                nextMenu = new BookTicket(this, -1, moviegoer);
                break;
            case 2:
                if (moviegoer == null) {
                    nextMenu = new CreateOrLogin(nextMenu, -1);
                } else {
                    System.out.println(ConsoleColours.WHITE_BOLD + "You can leave a review" + ConsoleColours.RESET);
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
            case 5:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println(ConsoleColours.RED + "Going Back" + ConsoleColours.RESET);
                break;
        }

        return nextMenu;

    }
}
