package View;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.HolidayController;

public class ConfigureHoliday {
  Scanner sc = new Scanner(System.in);

  public void viewHolidays() {
    if (!HolidayController.printHolidays()) System.out.println("There are no saved holidays");
  }

  public void addHoliday() {
    String date, name;
    String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		System.out.println("Enter the date of the holiday: (yyyy-MM-dd)");
		date = sc.nextLine();
    while (!date.matches(dateCheck)) {
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

  public void updateHoliday() {
    String oldDate, newDate, name;
    String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
		System.out.println("Enter the date of the holiday to be updated: (yyyy-MM-dd)");
		oldDate = sc.nextLine();
    while (!oldDate.matches(dateCheck)) {
      System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
      oldDate = sc.nextLine();
    }
		oldDate += " 00:00";
		LocalDateTime oldHoliday = LocalDateTime.parse(oldDate, formatter);

    System.out.println("Enter the new date of the holiday: (yyyy-MM-dd)");
    newDate = sc.nextLine();
    while (!newDate.matches(dateCheck)) {
      System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
      newDate = sc.nextLine();
    }
    newDate += " 00:00";
    LocalDateTime newHoliday = LocalDateTime.parse(newDate, formatter);

    System.out.println("Enter the name of the holiday to be updated:");
		name = sc.nextLine();

		if (HolidayController.updateHoliday(oldHoliday, newHoliday, name)) System.out.println("Holiday updated!");
		else System.out.println("Holiday doesn't exist!");
  }

  public void deleteHoliday(Boolean byName) {
    String date, name;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    if (!byName) {
      String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                          + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                          + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                          + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
      System.out.println("Enter the date of the holiday to be deleted: (yyyy-MM-dd)");
      date = sc.nextLine();
      while (!date.matches(dateCheck)) {
        System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
        date = sc.nextLine();
      }
      date += " 00:00";
      LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
      if (HolidayController.deleteSingleHoliday(dateTime)) System.out.println("Holiday deleted!");
      else System.out.println("Holiday date not found!");
    }

    else {
      System.out.println("Enter the name of the holiday to be deleted:");
		  name = sc.nextLine();
      if (HolidayController.deleteHolidayName(name)) System.out.println("Holiday(s) deleted!");
      else System.out.println("Holiday name not found!");
    }
  }
}
