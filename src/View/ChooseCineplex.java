package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.CineplexController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

public class ChooseCineplex extends BaseMenu{
        Scanner sc = new Scanner(System.in);
        MovieGoer user;
        Movie movie = null;
        MovieSession movieSession = null;
        Cinema cinema = null;
        ArrayList<Ticket> ticket;
        Transaction transaction = null;
    
        public ChooseCineplex(BaseMenu previousMenu, int accesslevel, MovieGoer user,  Movie movie,
        MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction) {
            super(previousMenu, accesslevel);
            this.user = user;
            this.movie = movie;
            this.movieSession = movieSession;
            this.cinema = cinema;
            this.ticket = ticket;
            this.transaction = transaction;
        }
    
        @Override
        public BaseMenu execute() {
            int choice;
            String numregex = "^(?!(0))[0-5]{1}$";
    
            System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Cineplex:" + ConsoleColours.RESET);
            System.out.println("1. Bishan");
            System.out.println("2. NEX");
            System.out.println("3. Jewel");
            System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);
    
            BaseMenu nextMenu = this;
    
            //keep asking for choice
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
            String choicestr = sc.nextLine();
            System.out.println();
    
            while (!choicestr.matches(numregex)) {
                //early termination
                if(choicestr.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choicestr = sc.nextLine();
                System.out.println();
            }
    
            choice = Integer.valueOf(choicestr);
    
            switch (choice) {
                case 1:
                    ArrayList<Cinema> cinemaArrayBishan = CineplexController.readByLocation("Bishan");
                    Cineplex cineplexBishan = new Cineplex(cinemaArrayBishan, "Bishan");
                    
                    System.out.println(ConsoleColours.GREEN + "You have picked Bishan Cineplex" + ConsoleColours.RESET);
                    nextMenu = new ChooseMovie(this, 0, this.user, null, null, null, null, null, cineplexBishan);
                    break;
                case 2:
                    ArrayList<Cinema> cinemaArrayNex = CineplexController.readByLocation("Nex");
                    Cineplex cineplexNex = new Cineplex(cinemaArrayNex, "Nex");
                    
                    System.out.println(ConsoleColours.GREEN + "You have picked NEX Cineplex" + ConsoleColours.RESET);
                    nextMenu = new ChooseMovie(this, 0, this.user, null, null, null, null, null, cineplexNex);
                    break;
                case 3:
                    ArrayList<Cinema> cinemaArrayJewel = CineplexController.readByLocation("Jewel");
                    Cineplex cineplexJewel = new Cineplex(cinemaArrayJewel, "Jewel");
                    
                    System.out.println(ConsoleColours.GREEN + "You have picked Jewel Cineplex" + ConsoleColours.RESET);
                    nextMenu = new ChooseMovie(this, 0, this.user, null, null, null, null, null, cineplexJewel);
                    break;
                case 4:
                    nextMenu = this.getPreviousMenu();
                    break;
                case 5:
                    nextMenu = new Quit(this);
                    break;
                default:
                    choice = -1;
                    System.out.println("Please enter a valid choice.");
                    break;
            }
    
            return nextMenu;
    
        }
}
