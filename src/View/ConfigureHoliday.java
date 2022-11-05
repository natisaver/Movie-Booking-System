package View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.HolidayController;
import Model.Holiday;

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
      System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
      choiceStr = sc.nextLine();
      System.out.println();
      numRegex = "^(?!(0))[0-6]{1}$";
      while (!choiceStr.matches(numRegex)) {
          //early termination
          if(choiceStr.isBlank()){
              return this.getPreviousMenu();
          }
          System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
          choiceStr = sc.nextLine();
          System.out.println();
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
        //VIEW HOLIDAY
        case 1:
          DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          ArrayList<Holiday> HolidayList = HolidayController.read();
          HolidayList.sort(Comparator.comparing( ( Holiday holiday )->holiday.getDate().toLocalDate() ));
          if(HolidayList.isEmpty()){
            System.out.println(ConsoleColours.RED + "There are no saved holidays" + ConsoleColours.RESET);
            System.out.println();
          }else{
            for(int i=0; i<HolidayList.size(); i++){
              System.out.print(HolidayList.get(i).getDate().format(dateFormatter) + "\t");
              System.out.println(HolidayList.get(i).getName());
            }
          }
          break;
        
        //ADD NEW HOLIDAY
        case 2:
          System.out.println(ConsoleColours.WHITE_BOLD + "Enter the date of the holiday: (yyyy-MM-dd)" + ConsoleColours.RESET);
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

          System.out.println(ConsoleColours.WHITE_BOLD + "Enter the name of the holiday:" + ConsoleColours.RESET);
          name = sc.nextLine();

          if (HolidayController.addHoliday(dateTime, name)){
            System.out.println(ConsoleColours.GREEN + "Holiday added!" + ConsoleColours.RESET);
            System.out.println();
          }
          else{
            System.out.println(ConsoleColours.RED + "Holiday already exists!" + ConsoleColours.RESET);
            System.out.println();
          }
          break;
        
        //UPDATE HOLIDAY
        case 3:
          System.out.println(ConsoleColours.WHITE_BOLD + "Enter the date of the holiday to be updated: (yyyy-MM-dd)" + ConsoleColours.RESET);
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
      
          System.out.println(ConsoleColours.WHITE_BOLD + "Enter the new date of the holiday: (yyyy-MM-dd)" + ConsoleColours.RESET);
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
      
          System.out.println(ConsoleColours.WHITE_BOLD + "Enter the name of the holiday to be updated:" + ConsoleColours.RESET);
          name = sc.nextLine();
      
          if(HolidayController.updateHoliday(oldHoliday, newHoliday, name)){
            System.out.println(ConsoleColours.GREEN + "Holiday updated!" + ConsoleColours.RESET);
            System.out.println();
          }
          else{
            System.out.println(ConsoleColours.RED + "Holiday doesn't exist!" + ConsoleColours.RESET);
            System.out.println();
          }
          break;

        //DELETE HOLIDAY
        case 4:
          Boolean byName = true;
          numRegex = "^([0-1])$";

          System.out.println(ConsoleColours.WHITE_BOLD + "Enter Holiday by Name or by Date:" + ConsoleColours.RESET);
          System.out.println(ConsoleColours.BLUE + "(Enter 0 for Name, 1 for Date)" + ConsoleColours.RESET);
          String byWhat = sc.nextLine();
          
          while (!byWhat.matches(numRegex)) {
              //early termination
              if(byWhat.isBlank()){
                  return this.getPreviousMenu();
              }
              System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
              byWhat = sc.nextLine();
          }
          if(byWhat.equals("1"))
          {
            byName = false;
          }
          
          if (!byName) {
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter the date of the holiday to be deleted: (yyyy-MM-dd)" + ConsoleColours.RESET);
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
            System.out.println();
          }
      
          else {
            System.out.println(ConsoleColours.WHITE_BOLD + "Enter the name of the holiday to be deleted:" + ConsoleColours.RESET);
            name = sc.nextLine();
            if(name.isBlank()){
              return this.getPreviousMenu();
            }
            if (HolidayController.deleteHolidayName(name)) System.out.println(ConsoleColours.GREEN + "Holiday(s) deleted!" + ConsoleColours.RESET);
            else System.out.println(ConsoleColours.RED + "Holiday name not found!" + ConsoleColours.RESET);
            System.out.println();
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
}
