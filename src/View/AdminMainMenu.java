package View;
import java.util.Scanner;

import Model.Admin;

public class AdminMainMenu extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    
    /** 
     * Current User
     */
    Admin admin = null;

    public AdminMainMenu(BaseMenu previousMenu, int accesslevel, Admin admin) {
        super(previousMenu, accesslevel);
        this.admin = admin;
    }

    @Override
    public BaseMenu execute() {
        int choice;

        System.out.println("Admin Menu Options:");
        System.out.println("1. Configure System Settings");
        System.out.println("2. Enter movie details");
        System.out.println("3. Update movie details");
        System.out.println("4. List Top 5 movies");
        System.out.println("5. Logout");

        BaseMenu nextMenu = this;

        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();


            switch (choice) {
                case 1:
                    // nextMenu = new ConfigureSettings(this);
                    break;
                case 2:
                    // nextMenu = new EnterDetails(this);
                    break;
                case 3:
                    // nextMenu = new UpdateDetails(this);
                    break;
                case 4:
                    // nextMenu = new ListTop5(this);
                    break;
                case 5:
                    nextMenu = new MainMenu(null, -1);
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
