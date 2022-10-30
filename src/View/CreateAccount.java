package View;

import java.util.Scanner;

import Controller.MovieGoerController;
import Model.MovieGoer;

public class CreateAccount extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    public CreateAccount(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {

        String username;
        String email;
        String phoneNo;
        int age;
        String password;
        String confirmPassword;
        MovieGoer user;

        System.out.println("Please create an account using your preferred Username & Password");
        System.out.println("(Enter blank space for both to quit)");

        do {
            System.out.print("Username: ");
            username = sc.next();

            System.out.print("Email: ");
            email = sc.next();

            System.out.print("Phone Number: ");
            phoneNo = sc.next();

            System.out.print("Age: ");
            age = sc.nextInt();

            System.out.print("Password: ");
            password = sc.next();

            System.out.print("Confirm Password: ");
            confirmPassword = sc.next();

            if (!password.equals(confirmPassword)) {
                System.out.println("Password fields does not match");
                System.out.println("Reenter your credentials");
            } else {
                // Available/Eligible Username & Password will direct to UserMainMenu
                user = new MovieGoer(username, email, phoneNo, age, password);
                System.out.println(MovieGoerController.create(user));
                return new MovieGoerMainMenu(this.getPreviousMenu());

            }

        } while (username != " " && password != " " && confirmPassword != " " && !password.equals(confirmPassword));

        return new MovieGoerMainMenu(this.getPreviousMenu());
    }

}
