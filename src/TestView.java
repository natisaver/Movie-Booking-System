import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import Controller.*;
import Model.*;
import View.*;

public class TestView {
    public static void main(String[] args) {
        // MovieGoer test = new MovieGoer("dogg", "pingpong@asda.com", "123123", 44,
        // "12312");
        // System.out.println(MovieGoerController.read());
        // System.out.println(MovieGoerController.create(test));
        // System.out.println(MovieGoerController.read());

        // System.out.println(CineplexController.readByLocation("Jewel"));

        // String str = "1986-04-08";
        // str = str + " 00:00";
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
        // HH:mm");
        // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        // System.out.println(dateTime);

        // String str = "06/12/1992";
        // str = str + " 00:00";
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy
        // HH:mm");
        // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        LocalDateTime lt = LocalDateTime.now();
        System.out.println(lt.getDayOfWeek().toString());

        // BaseMenu createAccount = new CreateAccount(null);
        // createAccount.execute();

        // BaseMenu movieGoerLogin = new MovieGoerLogin(null);
        // movieGoerLogin.execute();

        // BaseMenu bookTicket = new BookTicket(null);
        // bookTicket.execute();

        // BaseMenu checkHistory = new CheckHistory(null);
        // checkHistory.execute();

        BaseMenu mainmenu = new Login(null, -1);
        mainmenu.execute();

    }
}
