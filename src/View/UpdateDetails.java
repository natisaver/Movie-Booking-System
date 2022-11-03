package View;
import Model.Movie;
import java.util.Scanner;

public class UpdateDetails extends BaseMenu{
    private Movie movie;

    public UpdateDetails(BaseMenu previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }

    public BaseMenu execute(){
        System.out.println("Update Details:");
        System.out.println("1. Movie Title");
        System.out.println("2. Movie Type");
        System.out.println("3. Movie Rating");
        System.out.println("4. Synopsis");
        System.out.println("5. Director");
        System.out.println("6. Cast");
        System.out.println("7. Release Date & End Date");
        System.out.println("8. Showtime");
        System.out.println("9. Cinema");
        System.out.println("10. Back");

        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            System.out.print("Enter your choice:");
            choice = sc.nextInt();

            BaseMenu nextMenu = this;
            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 10:
                    nextMenu = this.getPreviousMenu();
                default:
                    choice = -1;
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }while(choice==-1);

        return nextMenu;
    }
}
