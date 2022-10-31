package View;

import java.util.Scanner;

import Controller.TransactionController;
import Model.Cinema;
import Model.MovieGoer;
import Model.Transaction;
import Model.User;

public class CheckHistory extends BaseMenu {

    Scanner sc = new Scanner(System.in);

    public CheckHistory(BaseMenu previousMenu) {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() {

        String back;
        Transaction transaction;

        System.out.println("Below is the list of past transactions made: ");
        System.out.println("(Enter blank space for both to quit)");

        do {
            // if transaction records exist
            if (TransactionController.readByName("Bob") != null) {

            }
            // if no transaction records exist
            else {
                System.out.println("No Transaction Records found!");
            }

            // type any character to exit
            back = sc.next();
            return new MovieGoerMainMenu(this.getPreviousMenu());

        } while (back != " ");
    }

}