package View;
import java.util.Scanner;

/**
 * Intermediary Access Denied Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 03-11-2022
 */
public class CreateOrLogin extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public CreateOrLogin(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }
    /**
     * Selection Menu
     * Create Account
     * Login
     * Go Back
     * @return to the page according to user's selected choice
     */
    @Override
    public BaseMenu execute() {
        int choice;
        System.out.println(ConsoleColours.RED_BOLD + "<<ACCESS REQUIRES LOGIN>>" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.WHITE_BRIGHT + "Would you like to:" + ConsoleColours.RESET);
        System.out.println("1. Create a new account");
        System.out.println("2. Login to an existing account");
        System.out.println("3. Back");
        
        BaseMenu nextMenu = this;
        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();
  
            switch (choice) {
                case 1:
                    nextMenu = new CreateAccount(this, -1);
                    break;
                case 2:
                    nextMenu = new Login(this, -1);
                    break;
                case 3:
                    nextMenu = this.getPreviousMenu();
                    break;
                default:
                    choice = -1;
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice." + ConsoleColours.RESET);
                    break;
            }
        }while(choice==-1);

        return nextMenu;

    }
}
