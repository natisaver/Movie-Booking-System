package View;
import java.util.Scanner;

public class MovieGoerLogin extends BaseMenu{
    Scanner sc = new Scanner(System.in);

    public MovieGoerLogin(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute(){
        String username;
        String password;
        System.out.println("Please Login using your Username & Password");
        System.out.println("(Enter blank space for both to quit)");
        do{
            System.out.print("Username:");
            username = sc.next();

            System.out.print("Password:");
            password = sc.next();

            //check if username & password is correct - IDK HOW TO DO HELP

            if () {
                System.out.println("Wrong Username or Password");
                System.out.println("Reenter your credentials");
            } else {
                // Correct Username & Password will direct to UserMainMenu
                return new MovieGoerMainMenu(this.getPreviousMenu());
            }
        }while(username!=" " && password!= " ");
    }
}
