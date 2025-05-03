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
                System.out.println("2. Show all Music Names");
                System.out.println("3. Search a Music");
                System.out.println("4. Show all Albums");
                System.out.println("5. Play an Album");
                System.out.println("9. My Points");
                System.out.println("0. Exit");
                System.out.print("Option: ");
                String input = scanner.nextLine();

                switch (input) {
                    case "2":
                        System.out.print("Showing all the Music Names:\n" + spotifUM.getMusicNames().toString());
                        break;
                    case "3":
                        System.out.print("Enter music to search (or 'exit'): ");
                        // print top 5 songs as recommendation?
                        String musicString = scanner.nextLine();
                        if (musicString.equalsIgnoreCase("exit")) break;
                        Music music = spotifUM.getMusic(musicString);
                        if (music == null) {System.out.print("Music not found"); continue;}
                        showMusicMenu(user, music);
                        break;
                    case "4":
                        System.out.print("Showing all the Album Names:\n" + spotifUM.getAlbumNames().toString());
                        break;
                    case "5":
                        System.out.print("Enter Album to play (or 'exit'): ");
                        // print top 5 songs as recommendation?
                        String albumString = scanner.nextLine();
                        if (albumString.equalsIgnoreCase("exit")) break;
                        Album album = spotifUM.getAlbum(albumString);
                        if (album == null) {System.out.print("Album not found"); continue;}
                        System.out.println("Playing: " + albumString);
                        showAlbumMenu(user, album);
                        break;
                    case "9":
                        System.out.println("Your total Points: " + user.getPoints());
                        break;
                    case "0":
                        return;
            }
        }
    }

    public void libraryMenu(User user) {
        while (true) {
            System.out.println("Library options:");
            System.out.println("1. Show your Library");
            System.out.println("2. Play");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Your Library: \n" + user.libraryToString());
                    break;
                case "2":
                System.out.println("Choose: ");
                break;
                case "0":
                    System.out.println("Exiting.");
                    return;
                default:
                    break;
            }
        }
    }

    public void showMusicMenu(User user, Music music) {
        while (true) {
            System.out.println("\n" + music.getName());
            System.out.println("Music options:");
            System.out.println("1. Play");
            System.out.println("2. Show Music info"); // random, comecar por onde e poder skipar ou n
            if(user.getPlan().allows(Playlist.class)) System.out.println("3. Add to a playlist"); // preciso de por esta opcao if para cada plano 
            if(user.getPlan().allows(Favorites.class)) System.out.println("4. Like");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    user.play(music);
                    System.out.println("Played: " + music);
                    break;

                case "2":
                    System.out.println("Music's info: \n" + music.toString());
                    break;

                case "0":
                    System.out.println("Exiting.");
                    return;
            
                default:
                    break;
            }
        }
    }
    


    
    public void showAlbumMenu(User user, Album album) {
        while (true) {
            System.out.println("\n" + album.getName());
            System.out.println("\nAlbum options:");
            System.out.println("1. Show musics");
            System.out.println("2. Play"); // random, comecar por onde e poder skipar ou n
            System.out.println("3. Add to Library");
            System.out.println("4. ");
            System.out.println("0. Exit.");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

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
                    Album newAlbum = AlbumFactory.create(scanner,spotifUM);
                    spotifUM.addAlbum(newAlbum);            
                    System.out.println("Album added: " + newAlbum.getName());
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
