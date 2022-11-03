package View;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.HolidayController;

public class ConfigureHoliday {
  Scanner sc = new Scanner(System.in);

  public void addHoliday() {
    String date, name;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Enter the date of the holiday: (dd/MM/yyyy)");
		date = sc.nextLine();
		date += " 00:00";
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

		System.out.println("Enter the name of the holiday:");
		name = sc.nextLine();

		if (HolidayController.addHoliday(dateTime, name)) System.out.println("Holiday added!");
		else System.out.println("Holiday already exists!");
  }

  public void updateHoliday() {
    String date, name;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Enter the date of the holiday: (dd/MM/yyyy)");
		date = sc.nextLine();
		date += " 00:00";
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

		System.out.println("Enter the name of the holiday:");
		name = sc.nextLine();

		if (HolidayController.addHoliday(dateTime, name)) System.out.println("Holiday added!");
		else System.out.println("Holiday already exists!");
  }

  public void removeHoliday() {

  }
}
