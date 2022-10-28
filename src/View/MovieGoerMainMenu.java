package View;

import java.util.Scanner;

public class MovieGoerMainMenu extends BaseMenu{
    Scanner sc = new Scanner(System.in);

    public MovieGoerMainMenu(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {
        System.out.println("Customer Menu Options:");
        System.out.println("1. Book ticket");
        System.out.println("2. Leave a review");
        System.out.println("3. Check booking history");
        System.out.println("4. Back");

        System.out.print("Enter your choice:");
        int choice = sc.nextInt();

        BaseMenu nextMenu = this;
        switch (choice) {
            case 1:
                nextMenu = new BookTicket(this);
                break;
            case 2:
                nextMenu = new LeaveReview(this);
                break;
            case 3:
                nextMenu = new CheckHistory(this);
                break;
            case 4:
                nextMenu = this.getPreviousMenu();
                break;
            default:
                System.out.println("Please enter a valid choice.");
                break;
        }
        return nextMenu;

    }
}
