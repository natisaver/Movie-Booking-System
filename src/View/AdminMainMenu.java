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
        String numregex = "^(?!(0))[0-5]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Admin Menu Options:" + ConsoleColours.RESET);
        System.out.println("1. Configure System Settings");
        System.out.println("2. Enter movie details");
        System.out.println("3. Update movie details");
        System.out.println("4. List Top 5 movies");
        System.out.println(ConsoleColours.RED + "5. Logout" + ConsoleColours.RESET);

        BaseMenu nextMenu = this;

        //keep asking for choice
        System.out.println("Enter your choice: ");
        String choicestr = sc.nextLine();

        while (!choicestr.matches(numregex)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
        }

        choice = Integer.valueOf(choicestr);


        switch (choice) {
            case 1:
                nextMenu = new ConfigureSettings(this, 1);
                break;
            case 2:
                nextMenu = new EnterMovieDetails(this, 1);
                break;
            case 3:
                nextMenu = new UpdateDetails(this, 1);
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

        return nextMenu;

    }
}
