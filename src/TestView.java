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

                // LocalDateTime lt = LocalDateTime.now();
                // System.out.println(lt.getDayOfWeek().toString());

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                String formatDateTime = now.format(formatter);

                // LocalDateTime now = LocalDateTime.now();
                // DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                // String formatDateTime = now.format(formatter1);
                System.out.println(123 + formatDateTime);

                // BaseMenu createAccount = new CreateAccount(null);
                // createAccount.execute();

                // BaseMenu movieGoerLogin = new MovieGoerLogin(null);
                // movieGoerLogin.execute();

                // BaseMenu bookTicket = new BookTicket(null);
                // bookTicket.execute();

                // BaseMenu mainMenu = new MovieGoerLogin(null);
                // mainMenu.execute();

                // BaseMenu checkHistory = new CheckHistory(null, 0, null);
                // checkHistory.execute();

                // BaseMenu mainmenu = new MainMenu(null, -1);
                // mainmenu.execute();

                ReviewController.read();

                BaseMenu enterMovieDetails = new EnterMovieDetails(null, 1);
                enterMovieDetails.execute();

        }
}
