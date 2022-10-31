package View;

import java.util.Scanner;

import Controller.MovieGoerController;
import Model.MovieGoer;

public class MovieGoerLogin extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    public MovieGoerLogin(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {

        String email;
        String password;
        MovieGoer user;

        System.out.println("Please Login using your Email & Password");
        System.out.println("(Enter blank space for both to quit)");
        do {
            System.out.print("Email:");
            email = sc.next();

            System.out.print("Password:");
            password = sc.next();

            user = MovieGoerController.readByEmail(email);

            // Email does not exist. Return error message
            if (user == null) {
                System.out.println("Email does not exist");
                System.out.println("Reenter your credentials or create an account");
            }
            // Email exist, but does not match password in database. Return error message
            else if (!(user.getPassword().equals(password))) {
                System.out.println(user.getPassword());
                System.out.println("Wrong email or Password");
                System.out.println("Reenter your credentials");
            }
            // Correct Email & Password will direct user to UserMainMenu
            else {
                System.out.println("You've successfully login!");
                return new CreateAccount(this.getPreviousMenu());
            }
        } while (email != " " && password != " ");

        return null;
    }
}
