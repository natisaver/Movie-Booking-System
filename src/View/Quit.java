package View;

/**
 * Termination of the program
 */
public class Quit extends BaseMenu {
    public Quit(BaseMenu previousMenu) {
        super(previousMenu, -1);
    }

    @Override
    public BaseMenu execute() {
        return null;
    }
}