import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import Controller.*;
import Model.*;

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
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        LocalDateTime lt = LocalDateTime.now();
        System.out.println(lt.getDayOfWeek().toString());

    }
}
