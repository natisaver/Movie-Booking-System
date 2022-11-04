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
        BaseMenu nextMenu = this;
        int choice;
        ArrayList<Movie> movies;
        Movie temp;
        
        String numregex = "^(?!(0))[0-4]{1}$";

        do{
            movies = MovieController.read();
            Movie[] movieArray = new Movie[movies.size()];
            for(int x = 0; x < movies.size(); x++)
            {
                movieArray[x] = movies.get(x);
            }

            System.out.println(ConsoleColours.PURPLE_BOLD + "List Top 5: " + ConsoleColours.RESET);
            System.out.println("1. Print out Top 5 Movies by Ticket Sales");
            System.out.println("2. Print out Top 5 Movies by Average Reviews");
            System.out.println(ConsoleColours.YELLOW + "3. Back" + ConsoleColours.RESET);
            System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);
            
            // Keep asking for choice
            System.out.print("Enter your choice: ");
            String choicestr = sc.nextLine();

            while(!choicestr.matches(numregex))
            {
                if(choicestr.isBlank())
                {
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
                choicestr = sc.nextLine();
            }

            choice = Integer.valueOf(choicestr);

            switch (choice) {
                case 1:
                for(int i = 1; i < movies.size(); i++)
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
                for(int i = 1; i < movies.size() ; i++)
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
                
                case 4:
                    nextMenu = new Quit(this);
                    break;
            }
        }while(choice < 4);
        return nextMenu;
    }
}