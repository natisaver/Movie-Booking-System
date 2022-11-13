package View;

import java.util.Scanner;

import Controller.PriceDataController;

/**
 * Configure Prices Page
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 03-11-2022
 */

public class ConfigurePrice extends BaseMenu{
    Scanner sc = new Scanner(System.in);
  
    /** 
       * Constructor to store previous page and access level
       * @param previousMenu     the previous page
       * @param accesslevel      the level of access
       */
      public ConfigurePrice(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }
    
    /**
     * Configure Price Functionality
     * Updating of Price Details by Admin
     * 
     * @return Previous Page 
     * @see ConfigureSettings
     */
    @Override    
    public BaseMenu execute() {
        /** 
         * User's indicated details, and a MovieGoer Class Object to store inputted information.
         */
        String ticketid;

        System.out.println(ConsoleColours.WHITE_BOLD + "Types of Tickets:" + ConsoleColours.RESET);
        System.out.println("> Child Ticket" + ConsoleColours.RED + "*" + ConsoleColours.RESET + ConsoleColours.YELLOW + "\t\t\t(ID=0)" + ConsoleColours.RESET);
        System.out.println("> Senior Ticket" + ConsoleColours.RED + "*" + ConsoleColours.RESET + ConsoleColours.YELLOW + "\t\t(ID=1)" + ConsoleColours.RESET);
        System.out.println("> Adult Ticket (Mon-Wed)" + ConsoleColours.YELLOW + "\t(ID=2)" + ConsoleColours.RESET);
        System.out.println("> Adult Ticket (Thu)" + ConsoleColours.YELLOW + "\t\t(ID=3)" + ConsoleColours.RESET);
        System.out.println("> Adult Ticket (Fri before 6pm)"+ ConsoleColours.YELLOW + "\t(ID=4)" + ConsoleColours.RESET);
        System.out.println("> Adult Ticket (Fri after 6pm)" + ConsoleColours.YELLOW + "\t(ID=5)" + ConsoleColours.RESET);
        System.out.println("> Adult Ticket (Weekends)" + ConsoleColours.YELLOW + "\t(ID=6)" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED_BRIGHT + "* means only valid before 6pm, and excluding weekends and public holidays" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to back)" + ConsoleColours.RESET);
        System.out.println();

        String numregex = "^[0-6]{1}$";
        System.out.println("Please indicate ID of Ticket to Edit Price: ");
        ticketid = sc.nextLine();

        while (!ticketid.matches(numregex)) {
            //early termination
            if(ticketid.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please key a valid ticket ID Type:" + ConsoleColours.RESET);
            ticketid = sc.nextLine();
        }

        System.out.println(ConsoleColours.WHITE_BOLD + "The current prices for this ticket are:" + ConsoleColours.RESET);
        double[] curprices = PriceDataController.readByID(ticketid); 
        System.out.println("> Base Price for Regular: $" + curprices[0]);
        System.out.println("> Base Price for BlockBuster: $" + curprices[1]);
        System.out.println("> Base Price for 3D: $" + curprices[2]);
        System.out.println("> Increment to Gold Cinema: $" + curprices[3]);
        System.out.println("> Increment to Elite Seat: $" + curprices[4]);

        double[] inputList = new double[5];
        String numregex2 = "\\d{1,3}[,\\.]?(\\d{1,2})?";
        System.out.println(ConsoleColours.CYAN_BOLD + "Enter Price with Decimal (X.XX)" + ConsoleColours.RESET);
        System.out.println();
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Regular Base Price" + ConsoleColours.RESET);
        String input = sc.nextLine();
        while (!input.matches(numregex2)) {
            //early termination
            if(input.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please key a valid Price" + ConsoleColours.RESET);
            input = sc.nextLine();
        }
        inputList[0] = Double.parseDouble(input);

        System.out.println(ConsoleColours.WHITE_BOLD + "Enter BlockBuster Base Price" + ConsoleColours.RESET);
        input = sc.nextLine();
        while (!input.matches(numregex2)) {
            //early termination
            if(input.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please key a valid Price" + ConsoleColours.RESET);
            input = sc.nextLine();
        }
        inputList[1] = Double.parseDouble(input);

        
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter 3D Base Price" + ConsoleColours.RESET);
        input = sc.nextLine();
        while (!input.matches(numregex2)) {
            //early termination
            if(input.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please key a valid Price" + ConsoleColours.RESET);
            input = sc.nextLine();
        }
        inputList[2] = Double.parseDouble(input);

        
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Increment Amount from Standard Cinema -> Gold Cinema" + ConsoleColours.RESET);
        input = sc.nextLine();
        while (!input.matches(numregex2)) {
            //early termination
            if(input.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please key a valid Price" + ConsoleColours.RESET);
            input = sc.nextLine();
        }
        inputList[3] = Double.parseDouble(input);

        System.out.println(ConsoleColours.WHITE_BOLD + "Enter Increment Amount from Basic Seat -> Elite Seat" + ConsoleColours.RESET);
        input = sc.nextLine();
        while (!input.matches(numregex2)) {
            //early termination
            if(input.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please key a valid Price" + ConsoleColours.RESET);
            input = sc.nextLine();
        }
        inputList[4] = Double.parseDouble(input);
        
        if(PriceDataController.update(ticketid, inputList) == true){
            System.out.println(ConsoleColours.GREEN + "Successfully Updated Prices" + ConsoleColours.RESET);
        }
        else{
            System.out.println(ConsoleColours.RED + "Error in updating ticket prices" + ConsoleColours.RESET);
            return this.getPreviousMenu();
        }

        return this.getPreviousMenu();
    }
}