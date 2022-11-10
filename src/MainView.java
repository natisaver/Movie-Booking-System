import View.*;

public class MainView {
    public static void main(String[] args) {

        BaseMenu mainmenu = new MainMenu(null, -1, null, null, null, null, null, null, null, null);
        do {
            mainmenu = mainmenu.execute();
        } while (!(mainmenu instanceof Quit));

    }
}
