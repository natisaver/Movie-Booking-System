package View;

import java.util.Scanner;

import Controller.TransactionController;
import Model.Cinema;
import Model.MovieGoer;
import Model.Transaction;
import Model.User;

public class CheckHistory extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    MovieGoer moviegoer = null;

    public CheckHistory(BaseMenu previousMenu, int accesslevel, MovieGoer moviegoer) {
        super(previousMenu, accesslevel);
        this.moviegoer = moviegoer;
    }

    @Override
    public BaseMenu execute() {

        String back;
        Transaction transaction;

        System.out.println("Below is the list of past transactions made: ");
        System.out.println(ConsoleColours.GREEN + "(Leave the field empty to quit)" + ConsoleColours.RESET);

        do {
            // if transaction records exist
            if (TransactionController.readByEmail(moviegoer.getEmail()) != null) {

            }
            // if no transaction records exist
            else {
                System.out.println("No Transaction Records found!");
            }

            // type any character to exit
            back = sc.nextLine();

            if (back.isBlank()) {
                System.out.println("password is blank");
                break;
            }

            return new MovieGoerMainMenu(this.getPreviousMenu(), 0, moviegoer);

        } while (!back.isBlank());

        return this.getPreviousMenu();

    }

}