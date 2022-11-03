package View;

import java.io.Console;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.AdminController;
import Controller.MovieGoerController;
import Model.MovieGoer;

/**
 * Create Account Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public class CreateAccount extends BaseMenu {

    Scanner sc = new Scanner(System.in);
    private Pattern regexPattern;
    private Matcher regMatcher;

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public CreateAccount(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }
    /**
     * Create Account Page
     * Takes in details of user to make new MovieGoer Object
     * @return to the relevant page, to MovieGoer Menu or exit to previous page
     */
    @Override
    public BaseMenu execute() {
        /** 
         * User's inputted name, email, phoneNo, age, password and confirmPassword
         * MovieGoer Class to store the details
         */
        String name;
        String email;
        String phoneNo;
        int age;
        String password;
        String confirmPassword;
        MovieGoer user;

        System.out.println("Please create an account using your Email & Password");
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

        do {
            System.out.println("Your Name: ");
            name = sc.nextLine();
            if(name.isBlank()){
                break;
            }
            System.out.println("ur indicated name is " + name);

            System.out.println("Email: ");
            //make sure email is valid
            do {
                email = sc.nextLine();
                System.out.println("You entered: " + email);
                //early termination
                if(email.isBlank()){
                    return this.getPreviousMenu();
                }
                regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
                regMatcher = regexPattern.matcher(email);
                //valid email can break
                if (regMatcher.matches() && (MovieGoerController.readByEmail(email.toLowerCase()) == null && AdminController.readByEmail(email.toLowerCase()) == null)){
                    break;
                }
                System.out.println(ConsoleColours.RED + "Either an account already exists under this email address OR the email is invalid" + ConsoleColours.RESET);
                System.out.println("Please Reenter an Email Address:");

            } while (!regMatcher.matches() || (MovieGoerController.readByEmail(email.toLowerCase()) != null || AdminController.readByEmail(email.toLowerCase()) != null));

            //check for valid phone number
            String numregex = "^(?!(0))[0-9]+$";
            System.out.println("Phone Number: ");
            phoneNo = sc.nextLine();

            while (!phoneNo.matches(numregex)) {
                //early termination
                if(phoneNo.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please key a valid Phone Number:" + ConsoleColours.RESET);
                phoneNo = sc.nextLine();
            }

            //check for valid age
            System.out.println("Age: ");
            String ageString = sc.nextLine();
            while (!ageString.matches(numregex)) {
                //early termination
                if(ageString.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please key a valid Age:" + ConsoleColours.RESET);
                ageString = sc.nextLine();
            }
            age = Integer.parseInt(ageString);

            //check for valid password
            System.out.println(ConsoleColours.CYAN_BRIGHT + "Password must be:");
            System.out.println("At least 3 characters long");
            System.out.println("Contain both alphabets and numbers" + ConsoleColours.RESET);
            System.out.println("Password: ");
            password = sc.nextLine();

            if(password.isBlank()){
                System.out.println("password is blank");
                break;
            }

            //ensure password meets valid requirements
            while (!((password.length() >= 3) && (password.matches(".*[a-z]+.*")) && (password.matches(".*[0-9]+.*")))){
                System.out.println(ConsoleColours.RED + "Password must follow requirements: " + ConsoleColours.RESET);
                password = sc.nextLine();
                //early termination
                if(password.isBlank()){
                    return this.getPreviousMenu();
                }
            }

            System.out.println("Confirm Password: ");
            confirmPassword = sc.nextLine();
            if(confirmPassword.isBlank()){
                break;
            }

            while (!password.equals(confirmPassword)) {
                System.out.println(ConsoleColours.RED + "Password fields does not match" + ConsoleColours.RESET);
                System.out.println("Reenter the password");
                confirmPassword = sc.nextLine();
                //early termination
                if(confirmPassword.isBlank()){
                    return this.getPreviousMenu();
                }
            } 
            
            //Account Creation was Successful
            user = new MovieGoer(name, email.toLowerCase(), phoneNo, age, password);
            if (MovieGoerController.create(user)) {
                System.out.println(ConsoleColours.GREEN_BOLD + "You've successfully created an account!" + ConsoleColours.RESET);
                return new MovieGoerMainMenu(this.getPreviousMenu().getPreviousMenu(), 0, user);
            }
            else {
                System.out.println(ConsoleColours.RED_BOLD + "ERROR in account creation" + ConsoleColours.RESET);
                return new MovieGoerMainMenu(this.getPreviousMenu().getPreviousMenu(), -1, null);
            }




        } while ((!name.isBlank() && !password.isBlank() ));

        return this.getPreviousMenu().getPreviousMenu();
    }

}
