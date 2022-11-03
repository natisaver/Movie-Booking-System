package View;

/**
 * BaseMenu Abstract Class 
 * Template for implementation, to create any type of desired menu
 * the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 01-11-2022
 */

public abstract class BaseMenu {
    /** 
     * previousMenu attribute to store previous page
     * accesslevel attribute to determine user feature access
     * -1: no login access
     *  0: moviegoer
     *  1: admin
     */

    private BaseMenu previousMenu;
    private int accesslevel = -1;

    /** 
     * Constructor to store the previous menu page
     * @param previousMenu     the previous page
    */
    public BaseMenu(BaseMenu previousMenu, int accesslevel) {
        this.previousMenu = previousMenu;
        this.accesslevel = accesslevel;
    }

    /**
     * This method is called to execute page functionality
     * on the start of the page
     * @return the next page to execute
     */
    public abstract BaseMenu execute();

    /**
     * This method provides backing functionality
     * checks if current page has a previous page
     * @return the previous page 
     */
    protected BaseMenu getPreviousMenu() {
        if (previousMenu != null)
            return previousMenu;
        // if it's the first page
        else
            return this;
    }
}
