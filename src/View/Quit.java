package View;

/**
 * Termination of the program
 */
public class Quit extends BaseMenu {

    /** 
     * Constructor to store previous page
     * @param previousMenu     the previous page
     */
    public Quit(BaseMenu previousMenu) {
        super(previousMenu, -1);
    }

    /**
     * Quit Functionality
     * Terminate program
     * 
     * @return null
     */
    @Override
    public BaseMenu execute() {
        return null;
    }
}