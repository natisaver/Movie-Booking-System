package View;
import java.util.Scanner;

/**
 * The starting page that begins the application.
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class MainMenu extends BaseMenu{

    Scanner sc = new Scanner(System.in);

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public MainMenu(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    /**
     * Main Menu Functionality
     * Access Main Menu for MovieGoer, Admin or Quit
     * @return Selected Page or Terminates
     */
    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;

        System.out.println(ConsoleColours.PURPLE_BOLD + "Movie Booking and Listing Management Application (MOBLIMA)");
        System.out.flush();
        System.out.println(ConsoleColours.WHITE +"1. Browse");
        System.out.flush();
        System.out.println("2. Login");
        System.out.println("3. Create Account");
        System.out.println("4. " + ConsoleColours.RED + "Quit" + ConsoleColours.RESET);
        System.out.flush();

        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    nextMenu = new MovieGoerMainMenu(this, -1, null);
                    break;
                case 2:
                    nextMenu = new Login(this, -1);
                    break;
                case 3:
                    nextMenu = new CreateAccount(this, -1);
                    break;
                case 4:
                    nextMenu = new Quit(this);
                    break;
                default:
                    choice = -1;
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice." + ConsoleColours.RESET);
                    break;
            }
        } while(choice==-1);

        return nextMenu;
    }

}
