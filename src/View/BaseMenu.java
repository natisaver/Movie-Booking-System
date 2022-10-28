package View;

public abstract class BaseMenu {

    private BaseMenu previousMenu;

    public BaseMenu(BaseMenu previousMenu) {
        this.previousMenu = previousMenu;
    }

    //abstract execute class to navigate to next pages
    public abstract BaseMenu execute();

    //method to get previous menu
    protected BaseMenu getPreviousMenu() {
        if (previousMenu != null)
            return previousMenu;
        //if it's the first page
        else
            return this;
    }
}
