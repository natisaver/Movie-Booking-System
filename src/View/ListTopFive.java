package View;

import java.util.Scanner;

import java.util.ArrayList;

import Controller.*;
import Model.*;

public class ListTopFive extends BaseMenu
{
    Scanner sc = new Scanner(System.in);

    public ListTopFive(BaseMenu previousMenu, int accesslevel) 
    {
        super(previousMenu, accesslevel);
    }

    @Override
    public BaseMenu execute() 
    {
        int choice;
        ArrayList<Movie> movies;
        int numberMovie;
        Movie temp;
        
        System.out.println("Would you like to: ");
        System.out.println("1. Print out Top 5 Movies by Ticket Sales");
        System.out.println("2. Print out Top 5 Movies by Average Reviews");
        System.out.println("3. Back");
        BaseMenu nextMenu = this;

        do{
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            movies = MovieController.read();
            numberMovie = movies.size();
            Movie[] movieArray = new Movie[numberMovie];
            for(int x = 0; x < numberMovie; x++)
            {
                movieArray[x] = movies.get(x);
            }

            switch (choice) {
                case 1:
                for(int i = 1; i < numberMovie; i++)
                {
                    Movie key = movieArray[i];
                    int j = i-1;
                    while(j >= 0 && key.getTicketSales() > movieArray[j].getTicketSales())
                    {
                        temp = movieArray[j+1];
                        movieArray[j+1] = movieArray[j];
                        movieArray[j] = temp;
                        j = j - 1;
                    }
                }
        
                System.out.println("Top 5 Movies (By Ticket Sales)");
        
                for(int k = 1; k <= 5; k++)
                {
                    System.out.println("Number " + k + " : " + movieArray[k-1].getTitle());
                }
                    break;
            
                
                case 2:
                for(int i = 1; i < numberMovie; i++)
                {
                    Movie key = movieArray[i];
                    int j = i-1;
                    while(j >= 0 && key.getOverallRating() > movieArray[j].getOverallRating())
                    {
                        temp = movieArray[j+1];
                        movieArray[j+1] = movieArray[j];
                        movieArray[j] = temp;
                        j = j - 1;
                    }
                }
        
                System.out.println("Top 5 Movies (By Overall Review)");
        
                for(int k = 1; k <= 5; k++)
                {
                    System.out.println("Number " + k + " : " + movieArray[k-1].getTitle());
                }
                break;
                
                case 3:
                    nextMenu = this.getPreviousMenu();
                    break;
                
                default:
                    choice = -1;
                    System.out.println(ConsoleColours.RED + "Please enter a valid choice." + ConsoleColours.RESET);
                    break;
            }
        }while(choice == -1);
        return nextMenu;
    }
}