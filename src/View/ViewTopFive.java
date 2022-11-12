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

public class ViewTopFive extends BaseMenu{
    Scanner sc = new Scanner(System.in);

    public ViewTopFive(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    /**
     * View Top 5 Movies Functionality
     * Display top 5 list of movies based on the selected option of "by ticket
     * sales" or "by average reviews" (determined by admin)
     * 
     * @return Admin Main Menu
     * @see AdminMainMenu
     */
    @Override
    public BaseMenu execute() {
        ArrayList<Movie> movies;

        movies = MovieController.read();

        System.out.println(ConsoleColours.PURPLE_BOLD + "Top 5 Movies: " + ConsoleColours.RESET);

        //PRINT TOP 5 BY SALES
        if(ListTopFiveController.read() == 1){
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
            System.out.println();
        }
        //PRINT TOP 5 BY RATINGS
        else{
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
            System.out.println();
        }
        return this.getPreviousMenu();
    }

    /**
     * Sort by Value Funtionality
     * Sorts Movie by numerical order of Overall Ticket Sales
     * 
     * @param hm HashMap<String, Integer> Hashmap of Movie Title and Overall Ticket
     *           Sales
     * @return HashMap<String, Integer>
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
     * 
     * @param hm HashMap<String, Double> Hashmap of Movie Title and Overall Review
     *           Ratings
     * @return HashMap<String, Double>
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
