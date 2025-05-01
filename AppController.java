import java.util.Scanner;

public class AppController {
    private final Scanner scanner;
    private final SpotifUM spotifUM;
    private final MenuHandler menuHandler;

    public AppController(Scanner scanner, SpotifUM spotifUM) {
        this.scanner = scanner;
        this.spotifUM = spotifUM;
        this.menuHandler = new MenuHandler(scanner, spotifUM);
    }

    public void run() {
        menuHandler.showMainMenu();
    }
}
