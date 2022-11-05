package View;

import java.util.Scanner;

public class ConfigureSettings extends BaseMenu{
    Scanner sc = new Scanner(System.in);

    /** 
     * Constructor to store previous page and access level
     * @param previousMenu     the previous page
     * @param accesslevel      the level of access
     */
    public ConfigureSettings(BaseMenu previousMenu, int accesslevel) {
        super(previousMenu, accesslevel);
    }

    public BaseMenu execute() {
        BaseMenu nextMenu = this;
        int choice;
        String numregex = "^(?!(0))[0-4]{1}$";

        System.out.println(ConsoleColours.PURPLE_BOLD + "Configure Settings:" + ConsoleColours.RESET);
        System.out.println("1. Configure Price");
        System.out.println("2. Configure Holiday");
        System.out.println(ConsoleColours.YELLOW + "3. Back" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + "4. Quit" + ConsoleColours.RESET);

        //Keep asking for choice
        System.out.println(ConsoleColours.WHITE_BOLD + "Enter your choice: " + ConsoleColours.RESET);
        String choicestr = sc.nextLine();

        while (!choicestr.matches(numregex)) {
            //early termination
            if(choicestr.isBlank()){
                return this.getPreviousMenu();
            }
            System.out.println(ConsoleColours.RED + "Please enter a valid choice:" + ConsoleColours.RESET);
            choicestr = sc.nextLine();
        }

        choice = Integer.valueOf(choicestr);

        switch (choice) {
            case 1:
                nextMenu = new ConfigurePrice(this, 1);
                break;
            case 2:
                nextMenu = new ConfigureHoliday(this, 1);
                break;
            case 3:
                nextMenu = this.getPreviousMenu();
                break;
            case 4:
                nextMenu = new Quit(this);
                break;
            default:
                choice = -1;
                System.out.println(ConsoleColours.RED + "Quitting." + ConsoleColours.RESET);
                break;
        }

        return nextMenu;
    }
}
