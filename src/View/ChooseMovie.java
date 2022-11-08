package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.MovieController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Ticket;
import Model.Transaction;

public class ChooseMovie extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    
    MovieGoer user;
    Movie movie;
    MovieSession movieSession;
    Cinema cinema;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction;
    Cineplex cineplex;

    public ChooseMovie(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
    MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction, Cineplex cineplex) {
        super(previousMenu, accesslevel);
        this.user = user;
        this.movie = movie;
        this.movieSession = movieSession;
        this.cinema = cinema;
        this.ticket = ticket;
        this.transaction = transaction;
        this.cineplex = cineplex;
    }

    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;
        ArrayList<Movie> movieArr = MovieController.read();

        System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your desired Movie:" + ConsoleColours.RESET);
        HashMap<Integer, Movie> hashMapMovie = new HashMap();
        for (int j = 0; j < movieArr.size(); j++) {
            hashMapMovie.put(j, movieArr.get(j));
            System.out.println(j+1 + ": " + hashMapMovie.get(j).getTitle());
        }
        System.out.println(ConsoleColours.YELLOW + (movieArr.size() + 1) + ". Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + (movieArr.size() + 2) + ". Quit" + ConsoleColours.RESET);

        //keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();
        System.out.println();

        choice = Integer.valueOf(choicestr);
        while (choice <= 0 || choice > (movieArr.size() + 2)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
            choice = Integer.valueOf(choicestr);
            System.out.println();
        }

        if(choice<=movieArr.size()){
            this.movie = MovieController.readByTitle(hashMapMovie.get(choice).getTitle());
            System.out.println(ConsoleColours.GREEN + "Movie successfully chosen" + ConsoleColours.RESET);
            System.out.println();
            nextMenu = new ChooseSession(nextMenu, 0, this.user, this.movie, this.movieSession, this.cinema, this.ticket, this.transaction, this.cineplex);
            return nextMenu;
        }

        if (choice == movieArr.size() + 1){
            nextMenu = this.getPreviousMenu();
            return nextMenu;
        }
        else if (choice == movieArr.size() + 2){
            nextMenu = new Quit(this);
            return nextMenu;
        }
        return nextMenu;
    }
}


 // @Override
    // public BaseMenu execute() {
    //     BaseMenu nextMenu = this;
    //     int choice;
    //     String numregex = "^(?!(0))[0-4]{1}$";
    //     do{
    //         System.out.println(ConsoleColours.PURPLE_BOLD + "Movie Selection:" + ConsoleColours.RESET);
    //         System.out.println("1. View Movie List");
    //         System.out.println("2. Choose a Movie");
    //         System.out.println(ConsoleColours.YELLOW + "3. Logout" + ConsoleColours.RESET);
    //         System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);

    //         //keep asking for choice
    //         System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
    //         String choicestr = sc.nextLine();
    //         System.out.println();

    //         while (!choicestr.matches(numregex)) {
    //             //early termination
    //             if(choicestr.isBlank()){
    //                 return this.getPreviousMenu();
    //             }
    //             System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
    //             choicestr = sc.nextLine();
    //             System.out.println();
    //         }

    //         choice = Integer.valueOf(choicestr);


    //         switch (choice) {
    //             //VIEW MOVIE LIST
    //             case 1:
    //             System.out.println(ConsoleColours.BLUE_BOLD + "List of Available Movies:" + ConsoleColours.RESET);
    //                 ArrayList<Movie> movieArray = MovieController.read();
    //                 Map<Integer, Movie> hashMapMovie = new HashMap();
    //                 for (int j = 0; j < movieArray.size(); j++) {
    //                     hashMapMovie.put(j, movieArray.get(j));
    //                     System.out.println("> " + hashMapMovie.get(j).getTitle());
    //                 }
    //                 System.out.println();
    //                 break;
    //             //CHOOSE MOVIE
    //             case 2:
    //                 String strRegex = "^[^0-9]+$";
    //                 System.out.println(ConsoleColours.WHITE_BOLD + "Enter title of Movie to watch:" + ConsoleColours.RESET);
    //                 String inputString = sc.nextLine();
                    
    //                 do{
    //                     //Checks for movie format
    //                     while (!inputString.matches(strRegex)){
    //                         if(inputString.isBlank()){
    //                             return this.getPreviousMenu();
    //                         }
    //                         System.out.println(ConsoleColours.RED + "Please enter a valid Movie Title:" + ConsoleColours.RESET);
    //                         inputString = sc.nextLine();
    //                     }
    //                     movie = MovieController.readByTitle(inputString);
                        
    //                     //Checks if Movie exists in the database
    //                     if(movie == null){
    //                         System.out.println(ConsoleColours.RED + "Movie does not exist." + ConsoleColours.RESET);
    //                         System.out.println(ConsoleColours.WHITE_BOLD + "Re-enter Movie Title:" + ConsoleColours.RESET);
    //                         inputString = sc.nextLine();
    //                         movie = MovieController.readByTitle(inputString);
    //                     }
    //                 }while(movie == null);

    //                 System.out.println(ConsoleColours.GREEN + "Movie successfully chosen" + ConsoleColours.RESET);
    //                 System.out.println();
    //                 movie = MovieController.readByTitle(inputString);

    //                 nextMenu = new ChooseSession(nextMenu, choice, user, movie, movieSession, cinema, ticket, transaction);
    //                 return nextMenu;
    //             //GO TO PREVIOUS PAGE
    //             case 3:
    //                 nextMenu = this.getPreviousMenu();
    //                 return nextMenu;
    //             //QUIT
    //             case 4:
    //                 nextMenu = new Quit(this);
    //                 return nextMenu;
    //             default:
    //                 choice = -1;
    //                 System.out.println("Please enter a valid choice.");
    //                 break;
    //         }
    //     }while(true);
    // }