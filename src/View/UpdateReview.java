package View;
import Model.MovieGoer;
import Model.Review;
import Controller.ReviewController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The page for Admin to update reviews or delete reviews
 * the MOBLIMA Cinema
 * Application
 * @author Sally Carrera
 * @version 1.0
 * @since 04-11-2022
 */
public class UpdateReview extends BaseMenu{
    Scanner sc = new Scanner(System.in);
    LocalDateTime insertStart, insertEnd, curStart, curEnd;
    ArrayList<Review> reviews;
    boolean overlaps = false;
    String date, time;
    MovieGoer user;
    DateTimeFormatter formatter;

    /** 
     * Constructor to store previous page, access level, user
     * @param previousMenu      the previous page
     * @param accesslevel       the level of access
     * @param user              the moviegoer that has been logged in
     */
    public UpdateReview(BaseMenu previousMenu, int accesslevel, MovieGoer user) {
        super(previousMenu, accesslevel);
        this.user = user;
    }

    /**
     * Update Review Functionality
     * Update text review or rating, or Delete review entry
     * User can also choose to go Back or Quit
     * 
     * @return AdminMainMenu or Terminates
     * @see AdminMainMenu
     */
    @Override
    public BaseMenu execute(){
        BaseMenu nextMenu = this;
        int choice;
        String choiceStr;
        reviews = ReviewController.readByEmail(this.user.getEmail());
        HashMap<Integer, Review> initialMenu = new HashMap<Integer, Review>();
        HashMap<Integer, Review> SelectionMenu = new HashMap<Integer, Review>();

        String noreviewsavailable = "^[^0-2]$";
        System.out.println(ConsoleColours.WHITE_BOLD + "Here are your reviews:" + ConsoleColours.RESET);
        //moviegoer has no reviews
        if (reviews.size() <= 0){
            System.out.println(ConsoleColours.RED + "You Currently have no reviews Available" + ConsoleColours.RESET);
            do {
                System.out.println(ConsoleColours.YELLOW + "1. Back" + ConsoleColours.RESET);
                System.out.println(ConsoleColours.RED + "2. Quit" + ConsoleColours.RESET);
                String inputty = sc.nextLine();
                while (!inputty.matches(noreviewsavailable)){
                    if(inputty.isBlank()){
                        return this.getPreviousMenu();
                    }
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice" + ConsoleColours.RESET);
                    inputty = sc.nextLine();
                }
                int finalchoice = Integer.valueOf(inputty);
                switch(finalchoice){
                    case 1:
                        return this.getPreviousMenu();
                        
                    case 2:
                        return new Quit(this);
                }
            } while (true);
        }
        //moviegoer has reviews, then display
        else {
            //building hashmap of choices
            for(int i = 0; i < reviews.size() ; i++)
            {
                Review key = reviews.get(i);
                initialMenu.put(i, key);
            }
            //sort by date
            SelectionMenu = sortByDate(initialMenu);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for(int i = 1; i <= SelectionMenu.size() ; i++)
            {
                Review key = SelectionMenu.get(i);
                System.out.println(i + ". " + ConsoleColours.BLUE + key.getMovie().getTitle() + ConsoleColours.RESET + " | "+ key.getRating() + "* | on: "+ key.getDate().format(formatter));
                System.out.println("You wrote: " + key.getReview());
                System.out.println();
            }
            System.out.println(ConsoleColours.YELLOW + (SelectionMenu.size()+1) + ". Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + (SelectionMenu.size()+2) + ". Quit" + ConsoleColours.RESET);

            System.out.println();
        }

        Boolean isOK = false;
        String numregex = "[0-9]+";
        choice = -1;

        while (!isOK) {
            System.out.print("Enter your review to edit (Integer Value): ");
            String choicestr = sc.nextLine();
            if (!choicestr.matches(numregex)){
                System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                continue;
            }
            if (Integer.valueOf(choicestr) <= SelectionMenu.size()+2 && Integer.valueOf(choicestr) > 0){
                choice = Integer.valueOf(choicestr);
                isOK = true;
            }
            else {
                System.out.println(ConsoleColours.RED + "Please enter a valid range between 1<=x<=" + (SelectionMenu.size()+2) + ConsoleColours.RESET);
            }
        }

        Review selectedreview = SelectionMenu.get(choice);

        if (choice == SelectionMenu.size()+1){
            return this.getPreviousMenu();
        }
        else if (choice == SelectionMenu.size()+2){
            return new Quit(this);
        }
        else {
            //Menu choices to Update Movie Details
            System.out.println();
            System.out.println(ConsoleColours.PURPLE_BOLD + "Update/Delete Review:" + ConsoleColours.RESET);
            System.out.println("1. Update Review Content");
            System.out.println("2. Update Review Rating");
            System.out.println("3. Delete Review");
            System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

            //Keep asking for choice
            isOK = false;
            choiceStr = "";
            while (!isOK) {
                System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
                choiceStr = sc.nextLine();
                if (!choiceStr.matches(numregex)){
                    System.out.println(ConsoleColours.RED + "Please enter a valid integer:" + ConsoleColours.RESET);
                    continue;
                }
                if (Integer.valueOf(choiceStr) <= 5 && Integer.valueOf(choiceStr) > 0){
                    choice = Integer.valueOf(choiceStr);
                    isOK = true;
                }
                else {
                    System.out.println(ConsoleColours.RED + "Please enter a valid range between 1<=x<=5" + ConsoleColours.RESET);
                }
            }

            choice = Integer.valueOf(choiceStr);

            switch (choice) {
                //UPDATE REVIEW CONTENT
                case 1:
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter your new review: " + ConsoleColours.RESET);
                    choiceStr = sc.nextLine();
                    if(ReviewController.updateContent(selectedreview, choiceStr)){
                        System.out.println(ConsoleColours.GREEN + "Congratulations successfully updated review" + ConsoleColours.RESET);
                    }
                    else {
                        System.out.println(ConsoleColours.RED + "Failed to update review" + ConsoleColours.RESET);
                    };
                    break;

                //UPDATE REVIEW RATING
                case 2:
                    String reviewRating = "-1";
                    Double rating = 0.0;
                    String ratingRegex = "\\d{1,2}[,\\.]?(\\d{1,1})?";
                    Boolean isOKi = false;
                    while (!isOKi) {
                        System.out.println(ConsoleColours.WHITE_BOLD + "Please enter your updated Rating " + ConsoleColours.RESET);
                        reviewRating = sc.nextLine();
                        if (!reviewRating.matches(ratingRegex)){
                            System.out.println(ConsoleColours.RED + "Please enter a valid range between 0.0 - 5.0" + ConsoleColours.RESET);
                        }
                        else if (Double.valueOf(reviewRating) >= 0 && Double.valueOf(reviewRating) <= 5){
                            rating = Double.valueOf(reviewRating);
                            isOKi = true;
                        }
                        else {
                            System.out.println(ConsoleColours.RED + "Please enter a valid range between 0.0 - 5.0" + ConsoleColours.RESET);
                        }
                    }
                    if(ReviewController.updateRating(selectedreview, rating)){
                        System.out.println(ConsoleColours.GREEN + "Congratulations successfully updated review" + ConsoleColours.RESET);
                    }
                    else {
                        System.out.println(ConsoleColours.RED + "Failed to update review" + ConsoleColours.RESET);
                    };
                    break;
                //DELETE REVIEW
                case 3:
                    if (ReviewController.deleteByTitleEmail(selectedreview.getMovie().getTitle(), selectedreview.getEmail())){
                        System.out.println(ConsoleColours.GREEN + "Congratulations successfully updated review" + ConsoleColours.RESET);
                    }
                    else {
                        System.out.println(ConsoleColours.RED + "Failed to delete review" + ConsoleColours.RESET);
                    }; 
                    break; 
                //GO BACK A PAGE
                case 4:
                    return this.getPreviousMenu();
                //TERMINATE PROGRAM
                case 5:
                    nextMenu = new Quit(this);
                    return nextMenu;
                
                //Should not enter here since regex is used
                default:
                    System.out.println(ConsoleColours.RED + "Invalid choice. Please re-enter your choice." + ConsoleColours.RESET);
                    System.out.println();
                    break;
            }
        }

        String choiceregex = "^(?!(0))[0-3]{1}$";
        System.out.println(ConsoleColours.PURPLE_BOLD + "Would you like to update/delete the review for another movie? " + ConsoleColours.RESET);
        System.out.println("1. Yes ");
        System.out.println("2. No ");
        System.out.println(ConsoleColours.RED + "3. Quit" + ConsoleColours.RESET);
        System.out.println();
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        choiceStr = sc.nextLine();
        System.out.println();

        while(!choiceStr.matches(choiceregex))
        {
            if(choiceStr.isBlank())
            {
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice: " + ConsoleColours.RESET);
            choiceStr = sc.nextLine();
            System.out.println();
        }
        
        choice = Integer.valueOf(choiceStr);
        switch(choice)
        {
            case 1:
                nextMenu = new UpdateReview(nextMenu, 0, this.user);
                break;
            case 2: 
                nextMenu = new MovieGoerMainMenu(nextMenu, 0, user, null, null, null, null, null, null, null);
                break;
            case 3:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println(ConsoleColours.RED + "Please enter a valid choice. " + ConsoleColours.RESET);
                break;
        }
        return nextMenu;

    }
    
    /**
     * Sort By Date Functionality
     * Sorts Review entries by Date of entry
     * 
	 * @param hm		Hashmap<Integer, Movie> Hashmap of Menu Selection and Movie Object
     * @return Hashmap<Integer, Review>
     * @see Hashmap
     */
    public static HashMap<Integer, Review> sortByDate(HashMap<Integer, Review> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Review>> list = new LinkedList<Map.Entry<Integer, Review>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Review>>() {
            public int compare(Map.Entry<Integer, Review> o1,
                    Map.Entry<Integer, Review> o2) {
                if (o1.getValue().getDate().isBefore(o2.getValue().getDate())) {
                    return -1;
                } else if (o1.getValue().getDate().isAfter(o2.getValue().getDate())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Review> temp = new LinkedHashMap<Integer, Review>();
        int i = 1;
        for (Map.Entry<Integer, Review> aa : list) {
            temp.put(i, aa.getValue());
            i++;
        }
        return temp;
    }
}
