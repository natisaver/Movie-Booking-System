package View;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.HolidayController;

public class ConfigureHoliday extends BaseMenu{
  Scanner sc = new Scanner(System.in);

  /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public ConfigureHoliday(BaseMenu previousMenu, int accesslevel) {
      super(previousMenu, accesslevel);
  }

  public BaseMenu execute() {
    BaseMenu nextMenu = this;
    int choice;
    String numRegex, choiceStr;

    do{
      //Menu choices to Update Movie Details
      System.out.println(ConsoleColours.PURPLE_BOLD + "Update Details:" + ConsoleColours.RESET);
      System.out.println("1. View Holidays");
      System.out.println("2. Add Holiday");
      System.out.println("3. Update Holiday");
      System.out.println("4. Delete Holiday");
      System.out.println(ConsoleColours.YELLOW + "5. Back" + ConsoleColours.RESET);
      System.out.println(ConsoleColours.RED + "6. Quit" + ConsoleColours.RESET);
      System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);

      //Keep asking for choice
      System.out.println("Enter your choice: ");
      choiceStr = sc.nextLine();
      numRegex = "^(?!(0))[0-6]{1}$";
      while (!choiceStr.matches(numRegex)) {
          //early termination
          if(choiceStr.isBlank()){
              return this.getPreviousMenu();
          }
          System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
          choiceStr = sc.nextLine();
      }

      choice = Integer.valueOf(choiceStr);

      String date, oldDate, newDate, name;
      LocalDateTime dateTime;
      String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
                              + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                              + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
                              + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      switch (choice) {
        //View Holiday
        case 1:
          if (!HolidayController.printHolidays())
            System.out.println(ConsoleColours.RED + "There are no saved holidays" + ConsoleColours.RESET);
          break;
        
        //Add New Holiday
        case 2:
          System.out.println("Enter the date of the holiday: (yyyy-MM-dd)");
          date = sc.nextLine();
          while (!date.matches(dateCheck)) {
            if(date.isBlank()){
              return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED +"Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
            date = sc.nextLine();
          }
          date += " 00:00";
          dateTime = LocalDateTime.parse(date, formatter);

          System.out.println("Enter the name of the holiday:");
          name = sc.nextLine();

          if (HolidayController.addHoliday(dateTime, name))
            System.out.println(ConsoleColours.GREEN + "Holiday added!" + ConsoleColours.RESET);
          else
            System.out.println(ConsoleColours.RED + "Holiday already exists!" + ConsoleColours.RESET);
          break;
        
        //Update Holiday
        case 3:
          System.out.println("Enter the date of the holiday to be updated: (yyyy-MM-dd)");
          oldDate = sc.nextLine();
          while (!oldDate.matches(dateCheck)) {
            if(oldDate.isBlank()){
              return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED +"Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
            oldDate = sc.nextLine();
          }
          oldDate += " 00:00";
          LocalDateTime oldHoliday = LocalDateTime.parse(oldDate, formatter);
      
          System.out.println("Enter the new date of the holiday: (yyyy-MM-dd)");
          newDate = sc.nextLine();
          while (!newDate.matches(dateCheck)) {
            if(newDate.isBlank()){
              return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED +"Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
            newDate = sc.nextLine();
          }
          newDate += " 00:00";
          LocalDateTime newHoliday = LocalDateTime.parse(newDate, formatter);
      
          System.out.println("Enter the name of the holiday to be updated:");
          name = sc.nextLine();
      
          if (HolidayController.updateHoliday(oldHoliday, newHoliday, name))
            System.out.println(ConsoleColours.GREEN + "Holiday updated!" + ConsoleColours.RESET);
          else
            System.out.println(ConsoleColours.RED + "Holiday doesn't exist!" + ConsoleColours.RESET);
          break;

        //Delete Holiday
        case 4:
          Boolean byName;
          numRegex = "^(?!(0))[0-1]{1}$";

          System.out.println("Enter Holiday by Name or by Date:");
          System.out.println("(Enter O for Name, 1 for Date)");
          String byWhat = sc.nextLine();
          
          while (!byWhat.matches(numRegex)) {
              //early termination
              if(byWhat.isBlank()){
                  return this.getPreviousMenu();
              }
              System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
              byWhat = sc.nextLine();
          }
          if(byWhat == "0"){
            byName = true;
          }else{
            byName = false;
          }
          
          if (!byName) {
            System.out.println("Enter the date of the holiday to be deleted: (yyyy-MM-dd)");
            date = sc.nextLine();
            while (!date.matches(dateCheck)) {
              if(date.isBlank()){
                return this.getPreviousMenu();
              }
              System.out.println(ConsoleColours.RED +"Please enter the date in the required format: (yyyy-MM-dd)" + ConsoleColours.RESET);
              date = sc.nextLine();
            }
            date += " 00:00";
            dateTime = LocalDateTime.parse(date, formatter);
            if (HolidayController.deleteSingleHoliday(dateTime)) System.out.println(ConsoleColours.GREEN + "Holiday deleted!" + ConsoleColours.RESET);
            else System.out.println(ConsoleColours.RED + "Holiday date not found!" + ConsoleColours.RESET);
          }
      
          else {
            System.out.println("Enter the name of the holiday to be deleted:");
            name = sc.nextLine();
            if(name.isBlank()){
              return this.getPreviousMenu();
            }
            if (HolidayController.deleteHolidayName(name)) System.out.println("Holiday(s) deleted!");
            else System.out.println(ConsoleColours.RED + "Holiday name not found!" + ConsoleColours.RESET);
          }
          break;
        
        //Return to previousMenu
        case 5:
          nextMenu = this.getPreviousMenu();
          return nextMenu;

        //Terminate Program
        case 6:
          nextMenu = new Quit(this);
          return nextMenu;
      }
    }while(true);
  }

  public void viewHolidays() {
      if (!HolidayController.printHolidays()) System.out.println("There are no saved holidays");
  }

  // public void addHoliday() {
  //   String date, name;
  //   String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
  //                       + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
  //                       + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
  //                       + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
  //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  //   System.out.println("Enter the date of the holiday: (yyyy-MM-dd)");
  //   date = sc.nextLine();
  //   while (!date.matches(dateCheck)) {
  //     System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
  //     date = sc.nextLine();
  //   }
  //   date += " 00:00";
  //   LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

  //   System.out.println("Enter the name of the holiday:");
  //   name = sc.nextLine();

  //   if (HolidayController.addHoliday(dateTime, name)) System.out.println("Holiday added!");
  //   else System.out.println("Holiday already exists!");
  // }

  // public void updateHoliday() {
  //   String oldDate, newDate, name;
  //   String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
  //                       + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
  //                       + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
  //                       + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
  //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
  //   System.out.println("Enter the date of the holiday to be updated: (yyyy-MM-dd)");
  //   oldDate = sc.nextLine();
  //   while (!oldDate.matches(dateCheck)) {
  //     System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
  //     oldDate = sc.nextLine();
  //   }
  //   oldDate += " 00:00";
  //   LocalDateTime oldHoliday = LocalDateTime.parse(oldDate, formatter);

  //   System.out.println("Enter the new date of the holiday: (yyyy-MM-dd)");
  //   newDate = sc.nextLine();
  //   while (!newDate.matches(dateCheck)) {
  //     System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
  //     newDate = sc.nextLine();
  //   }
  //   newDate += " 00:00";
  //   LocalDateTime newHoliday = LocalDateTime.parse(newDate, formatter);

  //   System.out.println("Enter the name of the holiday to be updated:");
  //   name = sc.nextLine();

  //   if (HolidayController.updateHoliday(oldHoliday, newHoliday, name)) System.out.println("Holiday updated!");
  //   else System.out.println("Holiday doesn't exist!");
  // }

  // public void deleteHoliday(Boolean byName) {
  //   String date, name;
  //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  //   if (!byName) {
  //     String dateCheck = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
  //                         + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
  //                         + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
  //                         + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
  //     System.out.println("Enter the date of the holiday to be deleted: (yyyy-MM-dd)");
  //     date = sc.nextLine();
  //     while (!date.matches(dateCheck)) {
  //       System.out.println("Please enter the date in the required format: (yyyy-MM-dd)");
  //       date = sc.nextLine();
  //     }
  //     date += " 00:00";
  //     LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
  //     if (HolidayController.deleteSingleHoliday(dateTime)) System.out.println("Holiday deleted!");
  //     else System.out.println("Holiday date not found!");
  //   }

  //   else {
  //     System.out.println("Enter the name of the holiday to be deleted:");
  //     name = sc.nextLine();
  //     if (HolidayController.deleteHolidayName(name)) System.out.println("Holiday(s) deleted!");
  //     else System.out.println("Holiday name not found!");
  //   }
  // }
}
