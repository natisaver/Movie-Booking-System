import Controller.*;
import Model.*;

public class TestView {
    public static void main(String[] args) {
        MovieGoer test = new MovieGoer("dogg", "pingpong@asda.com", "123123", 44, "12312");
        System.out.println(MovieGoerController.read());
        System.out.println(MovieGoerController.create(test));
        System.out.println(MovieGoerController.read());
    }
}
