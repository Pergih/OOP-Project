import java.util.Scanner;
/**
 * Represents a AppController with a scanner a spotifUm and a menuHandler. Has methods to run the menu handler
 */
public class AppController {
    private final Scanner scanner;
    private final SpotifUM spotifUM;
    private final MenuHandler menuHandler;

    /**
     * Creates a menu handler and puts scanner and spotifUM in the menu handler.
     * 
     * @param scanner Description of the first parameter.
     * @param spotifUM Description of the second parameter.
     
     */
    public AppController(Scanner scanner, SpotifUM spotifUM) {
        this.scanner = scanner;
        this.spotifUM = spotifUM;
        this.menuHandler = new MenuHandler(scanner, spotifUM);
    }

    /*
     * Runs the menu handler, showing the first menu
     */
    public void run() {
        menuHandler.showMainMenu();
    }
}
