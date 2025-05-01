import java.util.Scanner;
/**
 * Represents Main with a scanner and SpotifUm 
 */
public class NewMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SpotifUM spotifUM;

        try {
            spotifUM = SpotifUM.loadFromFile("saves/data.ser");
        } catch (Exception e) {
            spotifUM = new SpotifUM();
        }

        AppController app = new AppController(scanner, spotifUM);
        app.run();
    }
}
