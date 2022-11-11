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

/**
 * Choose Seat Menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */
public class ChooseSeat extends BaseMenu {
	Scanner sc = new Scanner(System.in);
	MovieGoer user;
	Movie movie;
	MovieSession movieSession;
	Cinema cinema;
	ArrayList<Ticket> ticket = new ArrayList<Ticket>();
	Transaction transaction;
	Cineplex cineplex;

	/**
	 * Constructor to store previous page, access level, moviegoer, movie,
	 * movieSession, cinema, cineplex, ticket, transaction, bookedSeats
	 * 
	 * @param previousMenu the previous page
	 * @param accesslevel  the level of access
	 * @param moviegoer    the moviegoer that has been logged in
	 * @param movie        the selected movie
	 * @param movieSession the selected movieSession
	 * @param cinema       the selected cinema
	 * @param cineplex     the selected cineplex
	 * @param ticket       the array of ticket(s) booked
	 * @param transaction  the transaction made
	 */
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

	/**
	 * Choose Seat Functionality
	 * Display seats options for moviegoer to choose from based on the cinemaType
	 * 
	 * @return Next Page or Previous Page
	 * @see ChooseAge or ChooseSession
	 */
	@Override
	public BaseMenu execute() {
		System.out.println(ConsoleColours.PURPLE_BOLD + "Choose your seats:" + ConsoleColours.RESET);
		System.out.println(ConsoleColours.GREEN + "(Leave a blank to go back)" + ConsoleColours.RESET);
		BaseMenu nextMenu = this;
		ArrayList<Seat> occupiedArrayList = MovieSessionController.displaySeats(this.cinema.getCinemaCode(),
				this.movieSession);
		ArrayList<String> occupiedSeats = new ArrayList<String>();
		if (occupiedArrayList.size() != 0) {
			for (int i = 0; i < occupiedArrayList.size(); i++) {
				occupiedSeats.add(occupiedArrayList.get(i).getSeatID());
			}
		}
		int originalSize = occupiedArrayList.size();

		// determine regex based on cinemaclass
		String idRegex;
		cinemaClass_Enum cinemaClass = this.cinema.getCinemaClass();
		if (cinemaClass == cinemaClass_Enum.STANDARD) {
			idRegex = "^([a-kA-K&&[^I]&&[^i]])(1[0-3]|[1-9])$";
		} else if (cinemaClass == cinemaClass_Enum.MAX) {
			idRegex = "^(?!s1$|S1$|s2$|S2$|s35$|S35$|s36$|S36$)([a-sA-S&&[^I]&&[^i]&&[^O]&&[^o]])(3[0-6]|[1-2][0-9]|[1-9])$";
		}
		// (cinemaClass == cinemaClass_Enum.GOLD) {
		else {
			idRegex = "^([a-dA-D])([1-8])$";
		}
		String choicestr;
		Boolean firstime = true;
		Boolean exit = true;
		do {
			Boolean isOK = false;
			while (!isOK) {
				if (firstime == true) {
					System.out
							.println(ConsoleColours.WHITE_BOLD + "Enter your seat choice(s): " + ConsoleColours.RESET);
				} else {
					System.out.println(
							ConsoleColours.WHITE_BOLD + "Enter your other seat choice(s)" + ConsoleColours.GREEN_BOLD
									+ " (Leave Blank to confirm your selection)" + ConsoleColours.RESET);
				}

				choicestr = sc.nextLine();
				if (!choicestr.matches(idRegex)) {
					// early termination
					if (firstime == true) {
						if (choicestr.isBlank()) {
							return this.getPreviousMenu();
						}
					} else {
						if (choicestr.isBlank()) {
							exit = false;
							break;
						}
					}
					System.out.println(ConsoleColours.RED + "Please enter a valid seat ID:" + ConsoleColours.RESET);
					System.out.println();
					continue;
				} else if (occupiedSeats.contains(MovieSessionController
						.getSeats(this.cinema, choicestr, this.movieSession).get(0).getSeatID())) {
					System.out.println(
							ConsoleColours.RED + "Seat is already occupied, please try again." + ConsoleColours.RESET);
					System.out.println();
					continue;
				} else {
					ArrayList<Seat> bookedSeat = MovieSessionController.getSeats(this.cinema, choicestr,
							this.movieSession);
					if (bookedSeat.size() == 2)
						System.out.println("You have booked couple seats");
					occupiedArrayList.addAll(bookedSeat);
					for (int i = 0; i < bookedSeat.size(); i++) {
						occupiedSeats.add(bookedSeat.get(i).getSeatID());
					}
					firstime = false;
					MovieSessionController.tempDisplaySeats(this.cinema, occupiedArrayList);
					isOK = true;
				}
			}
		} while (exit);

		return new ChooseAge(nextMenu, this.accesslevel, this.user, this.movie,
				this.movieSession, this.cinema, this.ticket, this.transaction,
				this.cineplex, occupiedArrayList, originalSize);
	}
}
