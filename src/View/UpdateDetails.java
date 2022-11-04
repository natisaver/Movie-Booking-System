package View;
import Model.Movie;
import Model.movieRating_Enum;
import Model.movieType_Enum;

import java.util.Scanner;

import Controller.MovieController;

public class UpdateDetails extends BaseMenu{
    private Movie movie;

    public UpdateDetails(BaseMenu previousMenu) {
        super(previousMenu);
    }

    public BaseMenu execute(){
        BaseMenu nextMenu = this;
        Scanner sc = new Scanner(System.in);
        int choice;
        String choiceStr, inputString, numRegex, strRegex;

        System.out.println("Enter Title of existing Movie to be updated:");
        inputString = sc.nextLine();
        movie = MovieController.readByTitle(inputString);
        //Checks if Movie exists in the database
        while(movie == null){
            System.out.println(ConsoleColours.RED + "Movie does not exist." + ConsoleColours.RESET);
            System.out.println("Re-enter Movie Title");
            inputString = sc.nextLine();
            movie = MovieController.readByTitle(inputString);
        }
        do{
            System.out.println(ConsoleColours.PURPLE_BOLD + "Update Details:" + ConsoleColours.RESET);
            System.out.println("1. Movie Title");
            System.out.println("2. Movie Type");
            System.out.println("3. Movie Rating");
            System.out.println("4. Movie Duration");
            System.out.println("5. Synopsis");
            System.out.println("6. Director");
            System.out.println("7. Cast");
            System.out.println("8. Release Date & End Date");
            System.out.println("9. Showtime");
            System.out.println("10. Cinema");
            System.out.println(ConsoleColours.YELLOW + "11. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

            //Keep asking for choice
            System.out.println("Enter your choice: ");
            choiceStr = sc.nextLine();
            numRegex = "^([1-9]|[1][0-1])$";
            while (!choiceStr.matches(numRegex)) {
                //early termination
                if(choiceStr.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choiceStr = sc.nextLine();
            }

            choice = Integer.valueOf(choiceStr);

            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    strRegex = "TWOD" + "THREED" + "BLOCKBUSTER";
                    System.out.println("Enter Movie Type: ");
                    inputString = sc.nextLine().toUpperCase();
                    while (!inputString.matches(strRegex)) {
                        //early termination
                        if(inputString.isBlank()){
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter a valid Movie Type:" + ConsoleColours.RESET);
                        inputString = sc.nextLine();
                    }
                    movie.setMovieType(movieType_Enum.valueOf(inputString));
                    break;
                case 3:
                    strRegex = "PG" + "PG13" + "NC16" + "M18" + "R21";
                    System.out.println("Enter Movie Rating: ");
                    inputString = sc.nextLine().toUpperCase();
                    while (!inputString.matches(strRegex)) {
                        //early termination
                        if(inputString.isBlank()){
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter a valid Movie Rating:" + ConsoleColours.RESET);
                        inputString = sc.nextLine();
                    }
                    movie.setMovieRating(movieRating_Enum.valueOf(inputString));
                case 4:
                    numRegex = "^([1-9][0-9]|[1-2][0-9][0-9])$";
                    System.out.println("Enter Movie Duration (in minutes): ");
                    inputString = sc.nextLine();
                    while (!inputString.matches(numRegex)) {
                        //early termination
                        if(inputString.isBlank()){
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter a valid Duration:" + ConsoleColours.RESET);
                        inputString = sc.nextLine();
                    }
                    movie.setDuration(Integer.parseInt(inputString));
                case 5:
                    System.out.println("Enter Synopsis: ");
                    inputString = sc.nextLine();
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    movie.setSynopsis(inputString);
                case 6:
                    System.out.println("Enter Director: ");
                    inputString = sc.nextLine();
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    movie.setDirector(inputString);
                case 7:
                    System.out.println("Enter Cast: ");
                    int i=0;
                    do{
                        inputString = sc.nextLine();
                        if(inputString.isBlank()){
                            return this.getPreviousMenu();
                        }
                        else{
                            inputArray.add(inputString);
                            i++;
                        }
                    }while(inputArray.get(i) != null);
                    movie.setCast(inputArray);
                    //clear array after - for use next time
                    inputArray.clear();
                case 8:
                    String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

                    System.out.println("Enter Release Date (yyyy-MM-dd): ");
                    inputString = sc.nextLine();
                    while (!inputString.matches(dateCheck)){
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
                    inputString = sc.nextLine();
                    }
                    movie.setReleaseDate(inputString);

                    System.out.println("Enter End Date (yyyy-MM-dd): ");
                    inputString = sc.nextLine();
                    while (!inputString.matches(dateCheck)){
                    if(inputString.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
                    inputString = sc.nextLine();
                    }
                    movie.setEndDate(inputString);
                case 9:

                case 10:
                
                case 11:

                default:
                    choice = -1;
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }

        return nextMenu;
    }
}
