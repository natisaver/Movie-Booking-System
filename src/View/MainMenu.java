package View;
import java.util.Scanner;

import Model.User;

public class MainMenu extends BaseMenu{
    private final User user;

    Scanner sc = new Scanner(System.in);

    public MainMenu(BaseMenu previousMenu, User user) {
        super(previousMenu);
        this.user = user;
    }

    @Override
    public BaseMenu execute() {
        int choice;
        System.out.println("MOvie Booking and LIsting Management Application (MOBLIMA)");
        System.out.println("I am a:");
        System.out.println("1. Customer");
        System.out.println("2. Administrator");

        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();

            BaseMenu nextMenu = this;
            switch (choice) {
                case 1:
                    nextMenu = new CreateOrLogin(this);
                    break;
                case 2:
                    nextMenu = new AdminLogIn(this);
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
