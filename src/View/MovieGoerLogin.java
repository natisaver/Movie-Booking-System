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

            // check if username & password is correct - IDK HOW TO DO HELP

            user = MovieGoerController.readByEmail(email);
            // user = MovieGoerController.readByEmailnPassword(email, password);

            if (user == null) {
                System.out.println("Email does not exist");
                System.out.println("Reenter your credentials or create an account");
            } else if (!(user.getPassword().equals(password))) {
                System.out.println(user.getPassword());
                System.out.println("Wrong email or Password");
                System.out.println("Reenter your credentials");
            } else {
                // Correct Username & Password will direct to UserMainMenu
                System.out.println("You've successfully login!");
                return new CreateAccount(this.getPreviousMenu());
            }
        } while (email != " " && password != " ");

        return null;
    }
}
