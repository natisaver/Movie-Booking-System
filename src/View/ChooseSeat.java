package View;

import Controller.MovieSessionController;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieGoer;
import Model.MovieSession;
import Model.Seat;
import Model.Ticket;
import Model.Transaction;
import Model.cinemaClass_Enum;

public class ChooseSeat extends BaseMenu {
    Scanner sc = new Scanner(System.in);
    MovieGoer user;
    Movie movie;
    MovieSession movieSession;
    Cinema cinema;
    ArrayList<Ticket> ticket = new ArrayList<>();
    Transaction transaction;
    Cineplex cineplex;
    
    public ChooseSeat(BaseMenu previousMenu, int accesslevel, MovieGoer user, Movie movie,
    MovieSession movieSession, Cinema cinema, ArrayList<Ticket> ticket, Transaction transaction,
    Cineplex cineplex) {
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
		System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your seats:" + ConsoleColours.RESET);
		System.out.println(ConsoleColours.GREEN + "(Leave a blank to go back)" + ConsoleColours.RESET);
		BaseMenu nextMenu = this;
		ArrayList<Seat> occupiedArrayList = MovieSessionController.displaySeats(this.cinema.getCinemaCode(), this.movieSession);
		System.out.println(ConsoleColours.WHITE_BOLD + "Enter your seat choice(s): " + ConsoleColours.RESET);
		
		//determine regex based on cinemaclass
		String idRegex;
		cinemaClass_Enum cinemaClass = this.cinema.getCinemaClass();
		if (cinemaClass == cinemaClass_Enum.STANDARD) {
            idRegex = "^([a-kA-K&&[^I]&&[^i]])(1[0-3]|[1-9])$";
        }
        else if (cinemaClass == cinemaClass_Enum.MAX) {
            idRegex = "^(?!s1$|S1$|s2$|S2$|s35$|S35$|s36$|S36$)([a-sA-S&&[^I]&&[^i]&&[^O]&&[^o]])(3[0-6]|[1-2][0-9]|[1-9])$";
        }
        // (cinemaClass == cinemaClass_Enum.GOLD) {
		else {
			idRegex = "^([a-dA-D])([1-8])$";
        }
		String choicestr;
		choicestr = sc.nextLine();
		Boolean firstime = true;
		Boolean exit = true;
		do {
			Boolean isOK = false;
			while(!isOK){
				choicestr = sc.nextLine();
				if (!choicestr.matches(idRegex)) {
					//early termination
					if (firstime == true){
						if(choicestr.isBlank()){
							return this.getPreviousMenu();
						}
					}
					else {
						if(choicestr.isBlank()){
							exit = false;
						}
					}
					System.out.println(ConsoleColours.RED + "Please enter a valid seat ID:" + ConsoleColours.RESET);
					System.out.println();
					continue;
				}
				else if (occupiedArrayList.containsAll(MovieSessionController.getSeats(this.cinema, choicestr, this.movieSession))){
					System.out.println(ConsoleColours.RED + "Seat is already occupied, please try again." + ConsoleColours.RESET);
					System.out.println();
					continue;
				}
				else {
					occupiedArrayList.addAll(null);
					firstime = false;
					MovieSessionController.tempDisplaySeats(this.cinema, occupiedArrayList);
					isOK = true;
				}
			} 
		} while (exit == true);

		return new ChooseAge(nextMenu, this.accesslevel, this.user, this.movie,
		this.movieSession, this.cinema, this.ticket, this.transaction,
		this.cineplex, occupiedArrayList);
    }
}
