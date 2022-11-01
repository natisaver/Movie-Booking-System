package View;

import java.util.Scanner;

import Controller.MovieGoerController;
import Controller.AdminController;
import Model.MovieGoer;
import Model.Admin;

/**
 * Login Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public class Login extends BaseMenu {
    Scanner sc = new Scanner(System.in);
    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public Login(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }
    /**
     * Main Login Page
     * Depending on the email address of the user, the system will bring them
     * to the relevant page if they are MovieGoer or Admin
     * @return to the relevant page of the user's role
     */
    @Override
    public BaseMenu execute() {
        /** 
         * User's email, password, and all user related Classes
         */
        String email;
        String password;
        MovieGoer user;
        Admin admin;

        System.out.println(ConsoleColours.WHITE_BRIGHT + "Please Login using your Email & Password" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

        do {
            System.out.print("Email:");
            email = sc.nextLine();

            System.out.print("Password:");
            password = sc.nextLine();

            // Checks if Email in database
            user = MovieGoerController.readByEmail(email);
            admin = AdminController.readByEmail(email);

            // Email does not exist in database. Return error message
            if (user == null && admin == null ) {
                System.out.println("Email does not exist");
                System.out.println("Re-enter your credentials or create an account");
            }
            // Email exists, but does not match password in database. Return error message
            else if ((user != null && !(user.getPassword().equals(password))) || (admin != null && !(admin.getPassword().equals(password)))) {
                // System.out.println(user.getPassword());
                // System.out.println(admin.getPassword());
                System.out.println("Wrong email or Password");
                System.out.println("Reenter your credentials");
            }
            // Correct Email & Password will direct user to relevant page
            else {
                System.out.println("You've successfully login!");
                //moviegoer page
                if (admin == null){

                }
                //admin page
                if (user == null) {

                }
            }
        } while (!(email.isBlank() || password.isBlank()));
        return this.getPreviousMenu();
        // return new MainMenu(null, -1);
    }
}


