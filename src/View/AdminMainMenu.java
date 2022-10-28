package View;
import java.util.Scanner;

public class AdminMainMenu extends BaseMenu{
    Scanner sc = new Scanner(System.in);

    public AdminMainMenu(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {
        System.out.println("Admin Menu Options:");
        System.out.println("1. Configure System Settings");
        System.out.println("2. Enter movie details");
        System.out.println("3. Update movie details");
        System.out.println("4. List Top 5 movies");
        System.out.println("5. Back");

        System.out.print("Enter your choice:");
        int choice = sc.nextInt();

        BaseMenu nextMenu = this;
        switch (choice) {
            case 1:
                nextMenu = new ConfigureSettings(this);
                break;
            case 2:
                nextMenu = new EnterDetails(this);
                break;
            case 3:
                nextMenu = new UpdateDetails(this);
                break;
            case 4:
                nextMenu = new ListTop5(this);
                break;
            case 5:
                nextMenu = this.getPreviousMenu();
                break;
            default:
                System.out.println("Please enter a valid choice.");
                break;
        }
        return nextMenu;

    }
}
