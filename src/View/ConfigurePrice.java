package View;

import java.util.Scanner;

import Controller.PriceDataController;

/**
 * Configure Prices for admin
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
  
    public BaseMenu execute() {
        /** 
         * User's indicated details, and a MovieGoer Class Object to store inputted information.
         */
        String ticketid;

        System.out.println(ConsoleColours.WHITE_BOLD + "Types of Tickets:" + ConsoleColours.RESET);
        System.out.println("0. Child Ticket*");
        System.out.println("1. Senior Ticket*");
        System.out.println("2. Adult Ticket (Mon-Wed)");
        System.out.println("3. Adult Ticket (Thu)");
        System.out.println("4. Adult Ticket (Fri before 6pm)");
        System.out.println("5. Adult Ticket (Fri after 6pm)");
        System.out.println("6. Adult Ticket (Weekends)");
        System.out.println(ConsoleColours.RED_BRIGHT + "* means only valid before 6pm, and excluding weekends and public holidays" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "(Leave any field empty to quit)" + ConsoleColours.RESET);


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
        System.out.println("- Base Price for Regular: $" + curprices[0]);
        System.out.println("- Base Price for BlockBuster: $" + curprices[1]);
        System.out.println("- Base Price for 3D: $" + curprices[2]);
        System.out.println("- Increment to Gold Cinema: $" + curprices[3]);
        System.out.println("- Increment to Elite Seat: $" + curprices[4]);

        //
        String numregex2 = "^(?:0|[1-9]\\d{0,2}(?:\\.\\d{3})*),\\d{2}$";
        System.out.println("Enter Regular, Blockbuster, 3D base prices");
        System.out.println("followed by $ increment for Gold Class Cinema from Standard Cinema");
        System.out.println("and $ increment for Elite Seat from Basic Seat");
        int i = 0;
        String input;
        double[] inputList = new double[5];
        do {
            input = sc.nextLine();

            while (!input.matches(numregex2)) {
                //early termination
                if(input.isBlank()){
                    return this.getPreviousMenu();
                }
                System.out.println(ConsoleColours.RED + "Please key a valid Price" + ConsoleColours.RESET);
                input = sc.nextLine();
            }
            inputList[i] = Double.parseDouble(input);
            i++;
        } while (i < 5);
        
        PriceDataController.update(ticketid, inputList);

        return this.getPreviousMenu();
    }
}