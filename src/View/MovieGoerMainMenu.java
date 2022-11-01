package View;

import java.util.Scanner;

/**
 * MovieGoer Main Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public class MovieGoerMainMenu extends BaseMenu{
    Scanner sc = new Scanner(System.in);

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public MovieGoerMainMenu(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    @Override
    public BaseMenu execute() {
        int choice;

        System.out.println("Customer Menu Options:");
        System.out.println("1. Book ticket");
        System.out.println("2. Leave a review");
        System.out.println("3. Check booking history");
        System.out.println("4. Back");

        BaseMenu nextMenu = this;
        
        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();

            
            switch (choice) {
                case 1:
                    // nextMenu = new BookTicket(this);
                    break;
                case 2:
                    // nextMenu = new LeaveReview(this);
                    break;
                case 3:
                    // nextMenu = new CheckHistory(this);
                    break;
                case 4:
                    nextMenu = this.getPreviousMenu();
                    break;
                default:
                    choice = -1;
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }while(choice == -1);

        return nextMenu;

    }
}
