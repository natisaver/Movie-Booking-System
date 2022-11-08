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
        String numregex = "^(?!(0))[0-7]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Admin Menu Options:" + ConsoleColours.RESET);
        System.out.println("1. Configure System Settings");
        System.out.println("2. Enter Movie Details");
        System.out.println("3. Update Movie Details");
        System.out.println("4. Update Movie Session");
        System.out.println("5. List Top 5 movies");
        System.out.println(ConsoleColours.YELLOW + "6. Logout" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + "7. Quit" + ConsoleColours.RESET);

        BaseMenu nextMenu = this;

        //keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();
        System.out.println();

        while (!choicestr.matches(numregex)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            System.out.println();
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
                nextMenu = new UpdateMovieDetails(this, 1);
                break;
            case 4:
                nextMenu = new UpdateMovieSession(this, 1);
                break;
            case 5:
                nextMenu = new ListTopFive(this, 1);
                break;
            case 6:
                nextMenu = new MainMenu(null, -1, null, null, null, null, null, null, null);
                break;
            case 7:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println("Please enter a valid choice.");
                break;
        }

        return nextMenu;

    }
}
