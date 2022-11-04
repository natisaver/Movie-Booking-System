package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.CineplexController;
import Model.Movie;
import Model.MovieSession;

public class EnterMovieSession extends BaseMenu{

    private Movie movie;
    private MovieSession movieSession;
    private Pattern regexPattern;
    private Matcher regMatcher;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Scanner sc = new Scanner(System.in);
    String date;

    public EnterMovieSession(BaseMenu previousMenu, int accesslevel, Movie movie) {
        super(previousMenu, accesslevel);
        this.movie = movie;
    }

    public BaseMenu execute(){
        
        System.out.println("Input a New Session for Current Movie");
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);
        
        System.out.println(ConsoleColours.BLUE + "Here are the current codes:" + ConsoleColours.RESET);
        System.out.println(CineplexController.read());

        //accept cinema code and check
        String numregex = "^(?!(0))[0-9]{3}$";
        System.out.println("Enter cinema code of cinema to add to: ");
        String choicestr = sc.nextLine();

        while (!choicestr.matches(numregex)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu().getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
        }
        
        //accept date and check
        String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Enter the date of the holiday: (yyyy-MM-dd)");
		date = sc.nextLine();
        while (!date.matches(dateCheck)) {
            if(date.isBlank()){
                return this.getPreviousMenu().getPreviousMenu();
            }
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            date = sc.nextLine();
        }


        System.out.println("Enter the start time: (HH:mm)");
		date = sc.nextLine();
        while (!date.matches(dateCheck)) {
            if(date.isBlank()){
                return this.getPreviousMenu().getPreviousMenu();
            }
            System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
            date = sc.nextLine();
        }
        

		date += " 00:00";
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

		System.out.println("Enter the name of the holiday:");
		name = sc.nextLine();

		if (HolidayController.addHoliday(dateTime, name)) System.out.println("Holiday added!");
		else System.out.println("Holiday already exists!");
  }
        
        do {
            System.out.println("Enter Cinema Codes");

            name = sc.nextLine();
            if(name.isBlank()){
                break;
            }
            System.out.println("ur indicated name is " + name);

            System.out.println("Email: ");
            //make sure email is valid
            do {
                email = sc.nextLine();
                System.out.println("You entered: " + email);
                //early termination
                if(email.isBlank()){
                    return this.getPreviousMenu();
                }
                regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
                regMatcher = regexPattern.matcher(email);
                //valid email can break
                if (regMatcher.matches() && (MovieGoerController.readByEmail(email.toLowerCase()) == null && AdminController.readByEmail(email.toLowerCase()) == null)){
                    break;
                }
                System.out.println(ConsoleColours.RED + "Either an account already exists under this email address OR the email is invalid" + ConsoleColours.RESET);
                System.out.println("Please Reenter an Email Address:");

            } while (!regMatcher.matches() || (MovieGoerController.readByEmail(email.toLowerCase()) != null || AdminController.readByEmail(email.toLowerCase()) != null));


        System.out.println("Enter Cinema Code: ");
        inputString = sc.nextLine();

        System.out.println("Enter Showtime (yyyy-MM-dd HH:mm): ");
        System.out.println("Enter blank to finish adding showtimes.");
        inputString = sc.nextLine();
        movieSession.setShowtime(LocalDateTime.parse(inputString, formatter));

        int duration = movie.getDuration();
        //iterate through csv & look for same cinema code in the movieSession csv
        //compare inputted start time (use java fn)
        //compare end time (add duration to start time)

        //start & end dates need to be within the limit of release & end date of movie


        //go back
        return this.getPreviousMenu();
    }
}
