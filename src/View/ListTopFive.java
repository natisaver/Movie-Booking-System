package View;

import java.util.Scanner;

import java.util.ArrayList;

import Controller.MovieController;
import Model.Movie;

public class ListTopFive extends BaseMenu
{
    Scanner sc = new Scanner(System.in);

    public ListTopFive(BaseMenu previousMenu)
    {
        super(previousMenu);
    }

    @Override
    public BaseMenu execute() 
    {
        Movie[] movieArray;
        ArrayList<Movie> movies;
        int numberMovie;

        movies = MovieController.read();

        movieArray = (Movie[]) movies.toArray();
        numberMovie = movies.size();

        for(int i = 0; i < numberMovie; i++)
        {
            for(int j = i; j > 0; j--)
            {
                if(movieArray[j].getTicketSales() > movieArray[j-1].getTicketSales())
                {
                    break;
                }
                else
                {
                    movieArray[j] = movieArray[j-1];
                    j = j-1;
                }
            }
        }

        System.out.println("Top 5 Movies (By Ticket Sales)");

        for(int k = 1; k <= 5; k++)
        {
            System.out.println("Number " + k + " : " + movieArray[k-1]);
        }

        return null;
    }
}