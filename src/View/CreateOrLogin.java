package View;
import java.util.Scanner;

public class CreateOrLogin extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    public CreateOrLogin(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {
        int choice;

        System.out.println("Would you like to:");
        System.out.println("1. Create a new account");
        System.out.println("2. Login to an existing account");
        System.out.println("3. Back");
        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();

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
                    choice = -1;
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }while(choice==-1);

        return nextMenu;

    }
}
