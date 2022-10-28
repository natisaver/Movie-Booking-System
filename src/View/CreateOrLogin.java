package View;
import java.util.Scanner;

public class CreateOrLogin {
    Scanner sc = new Scanner(System.in);

    public CreateOrLogin(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {
        System.out.println("Would you like to:");
        System.out.println("1. Create a new account");
        System.out.println("2. Login to an existing account");
        System.out.println("3. Back");

        System.out.print("Enter your choice:");
        int choice = sc.nextInt();

        BaseMenu nextMenu = this;
        switch (choice) {
            case 1:
                nextMenu = new CreateAccount(this);
                break;
            case 2:
                nextMenu = new MovieGoerLogin(this);
                break;
            case 3:
                nextMenu = this.getPreviousMenu();
                break;
            default:
                System.out.println("Please enter a valid choice.");
                break;
        }
        return nextMenu;

    }
}
