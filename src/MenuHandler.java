import java.io.IOException;
import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;
    private final SpotifUM spotifUM;

    public MenuHandler(Scanner scanner, SpotifUM spotifUM) {
        this.scanner = scanner;
        this.spotifUM = spotifUM;
    }

    /**
     * Displays the main menu of the SpotifUM application.
     * Handles user input for login, registration, and admin mode.
     */
    public void showMainMenu() {
        /* lógica com switch + chamadas a métodos privados */
        while (true) {
            System.out.println("\nWelcome! Choose an option:");
            System.out.println("1. Login as user");
            System.out.println("2. Register new user");
            System.out.println("3. Admin mode");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    if (spotifUM.getNumUsers() == 0) {
                        System.out.println("No users registered yet.");
                        break;
                    }

                    System.out.println("Select user:");
                    for (User user : spotifUM.getAllUsers()) {
                        System.out.println(user.getHandle() + " - " + user.getName() + " (" + user.getEmail() + ")");
                    }
                    System.out.print("Enter your handle to login: ");
                    String handle = scanner.nextLine();
                    User user = spotifUM.getUser(handle);

                    if (user == null) {
                        System.out.println("User not found!");
                    } else {
                        showUserMenu(user);
                    }
                    break;

                case "2":
                    User newUser = UserFactory.create(scanner);
                    spotifUM.addUser(newUser);
                    System.out.println("Registered: " + newUser.getName());
                    break;

                case "3":
                    showAdminMenu();
                    break;

                case "0":
                    try {
                        spotifUM.saveToFile("saves/data.ser");
                        System.out.println("Data saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Failed to save data: " + e.getMessage());
                    }
                    System.out.println("Bye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ========== USER MODE ==========
    /**
     * [Displays the User menu of the SpotifUM application.]
     *
     * @param user The chosen user.
     */
    public void showUserMenu(User user) {
        /* menu do utilizador */
        System.out.println("\nWelcome " + user.getName() + "!");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Library");
            System.out.println("2. Search for a Song");
            System.out.println("3. My Points");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "2":
                    System.out.print("Enter music to stream (or 'exit'): ");
                    // print top 5 songs as recommendation?
                    String musicString = scanner.nextLine();
                    if (musicString.equalsIgnoreCase("exit"))
                        break;
                    Music music = spotifUM.getMusic(musicString);
                    if (music == null) {
                        System.out.print("Music not found");
                        continue;
                    }
                    user.play(music);
                    System.out.println("Streamed: " + music);
                    break;
                case "3":
                    System.out.println("Your total Points: " + user.getPoints());
                    break;
                case "0":
                    return;
            }
        }
    }

    // ========== ADMIN MODE ==========
    /**
     * Displays the Admin menu of the SpotifUM application.
     * Handles the creation of musics album playlists etc.
     */
    public void showAdminMenu() {
        /* menu do admin */
        System.out.println("\nAdmin mode activated.");
        while (true) {
            System.out.println("\nAdmin options:");
            System.out.println("1. Add music");
            System.out.println("2. Add album");
            System.out.println("3. Add playlist");
            System.out.println("4. Show musics");

            System.out.println("0. Exit admin");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Music newMusic = MusicFactory.create(scanner);
                    spotifUM.addMusic(newMusic);
                    System.out.println("Música criada: " + newMusic.getName());
                    break;

                case "2":
                    System.out.print("Enter album name: ");
                    String album = scanner.nextLine();
                    // Add album logic here
                    System.out.println("Album added: " + album);
                    break;

                case "4":
                    System.out.print("Showing all the Musics:\n" + spotifUM.getMusics().toString());
                    break;

                case "0":
                    System.out.println("Exiting admin mode.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
