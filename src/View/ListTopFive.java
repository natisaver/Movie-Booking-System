package View;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Controller.*;
import Model.*;

/**
 * List Top 5 Movies Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class ListTopFive extends BaseMenu {
    Scanner sc = new Scanner(System.in);

    public ListTopFive(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    /**
     * List Top 5 Movies Functionality
     * Display top 5 list of movies based on the selected option of "by ticket
     * sales" or "by average reviews"
     * 
     * @return Next Page or Previous Page
     * @see ListTopFive 
     * @see AdminMainMenu
     */
    @Override
    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;
        ArrayList<Movie> movies;

        String numregex = "^(?!(0))[0-5]{1}$";

        do {
            movies = MovieController.read();

            System.out.println(ConsoleColours.PURPLE_BOLD + "List Top 5: " + ConsoleColours.RESET);
            System.out.println("1. Print out Top 5 Movies by Ticket Sales");
            System.out.println("2. Print out Top 5 Movies by Average Reviews");
            System.out.println("3. Viewable Top 5 to be set by:");
            System.out.println(ConsoleColours.YELLOW + "4. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "5. Quit" + ConsoleColours.RESET);

            // Keep asking for choice
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
            String choicestr = sc.nextLine();

            while (!choicestr.matches(numregex)) {
                if (choicestr.isBlank()) {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choicestr = sc.nextLine();
            }

            choice = Integer.valueOf(choicestr);

            switch (choice) {
                case 1:
                    HashMap<String, Integer> OverallSales = new HashMap<String, Integer>();

                    for (int i = 1; i < movies.size(); i++) {
                        Movie key = movies.get(i);
                        OverallSales.put(key.getTitle(), TransactionController.salesByTitle(key.getTitle()));
                    }
                    Map<String, Integer> hm2 = sortByValue(OverallSales);

                    // print the sorted hashmap
                    int x = 1;

                    System.out.println(
                            ConsoleColours.WHITE_BOLD + "Top 5 Movies (By Overall Sales)" + ConsoleColours.RESET);
                    for (Map.Entry<String, Integer> en : hm2.entrySet()) {
                        if (x == 6) {
                            break;
                        }
                        System.out
                                .println("Number " + x + " : " + en.getKey() + " | " + en.getValue() + " Tickets Sold");
                        x++;
                    }
                    break;

                case 2:
                    HashMap<String, Double> OverallRatings = new HashMap<String, Double>();
                    // store all the average ratings in a hashmap
                    for (int i = 0; i < movies.size(); i++) {
                        Movie key = movies.get(i);
                        ArrayList<Review> reviewlist = ReviewController.readByTitle(key.getTitle());
                        double sum = 0;
                        double average = 0;
                        if (!reviewlist.isEmpty()) {
                            for (Review review : reviewlist) {
                                sum += review.getRating();
                            }
                            average = sum / reviewlist.size();
                        }
                        OverallRatings.put(key.getTitle(), average);
                    }
                    Map<String, Double> hm1 = sortByValueDouble(OverallRatings);

                    // print the sorted hashmap
                    int i = 1;

                    System.out.println(
                            ConsoleColours.WHITE_BOLD + "Top 5 Movies (By Overall Review)" + ConsoleColours.RESET);
                    for (Map.Entry<String, Double> en : hm1.entrySet()) {
                        if (i == 6) {
                            break;
                        }
                        System.out.println("Number " + i + " : " + en.getKey() + " | " + en.getValue() + "*");
                        i++;
                    }
                    break;

                case 3:
                    String regex = "^(?!(0))[1-2]{1}$";
                    System.out.println(ConsoleColours.PURPLE_BOLD + "List Top 5 by: " + ConsoleColours.RESET);
                    System.out.println("1. Ticket Sales");
                    System.out.println("2. Ratings");

                    // Keep asking for choice
                    System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
                    choicestr = sc.nextLine();

                    while (!choicestr.matches(regex)) {
                        if (choicestr.isBlank()) {
                            return this.getPreviousMenu();
                        }
                        System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                        choicestr = sc.nextLine();
                    }
                    ListTopFiveController.update(choicestr);

                case 4:
                    return this.getPreviousMenu();

                case 5:
                    nextMenu = new Quit(this);
                    break;
            }
        } while (choice < 4);
        return nextMenu;
    }

    /**
     * Sort by Value Funtionality
     * Sorts Movie by numerical order of Overall Ticket Sales
     * 
     * @param hm {@code HashMap} unsorted collection that contains {@code String} Movie Title as key and {@code Integer} Overall Ticket Sales as value.
     * @return {@code HashMap} sorted collection that contains {@code String} Movie Title as key and {@code Integer} Overall Ticket Sales as value.
     */
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (-1) * (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * Sort by Value Double Funtionality
     * Sorts Movie by numerical order of Overall Review Ratings
     * @param hm {@code HashMap} unsorted collection that contains {@code String} Movie Title as key and {@code Double} Overall Review Rating as value.
     * @return {@code HashMap} sorted collection that contains {@code String} Movie Title as key and {@code Double} Overall Review Rating as value.
     */
    public static HashMap<String, Double> sortByValueDouble(HashMap<String, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                    Map.Entry<String, Double> o2) {
                return (-1) * (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
