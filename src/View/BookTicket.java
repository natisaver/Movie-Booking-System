package View;

import java.time.LocalDateTime;
import java.util.Scanner;

import Model.Cinema;
import Model.Cineplex;
import Model.Ticket;
import Model.ageGroup_Enum;
import Model.cinemaClass_Enum;
import Model.movieRating_Enum;
import Model.movieType_Enum;

public class BookTicket extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    public BookTicket(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {

        Cineplex cineplex;
        Cinema cinema;
        LocalDateTime showtime;
        String movieTitle;
        movieType_Enum movieType;
        movieRating_Enum movieRating;
        int seat;
        ageGroup_Enum ageGroup;
        Ticket ticket;

        System.out.println("Please create an account using your preferred Username & Password");
        System.out.println("(Enter blank space for both to quit)");

        do {
            System.out.print("Cineplex: ");

            movieTitle = sc.next();

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

            if (password != confirmPassword) {
                System.out.println("Password fields does not match");
                System.out.println("Reenter your credentials");
            } else {
                // Available/Eligible Username & Password will direct to UserMainMenu
                ticket = new Ticket(cineplex, cinema, showtime, movieTitle, movieType, movieRating, seat, ageGroup);
                return new MovieGoerMainMenu(this.getPreviousMenu());
            }

        } while (movieTitle != " ");

        return new MovieGoerMainMenu(this.getPreviousMenu());
    }
}
