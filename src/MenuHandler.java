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
                    showAdminMenu(spotifUM.getUser("admin"));
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
            System.out.println("2. Play Random Playlists");
            // por quase td com perms nos prints e nos case
            System.out.println("3. Show all Music Names");
            System.out.println("4. Search a Music");
            System.out.println("5. Show all Albums");
            System.out.println("6. Play an Album");
            System.out.println("7. Show all public Playlists");
            System.out.println("8. Play a public playlist");
            System.out.println("9. Show user's history");
            System.out.println("10. My Points");
            System.out.println("11. Show Subscription Plan");

            System.out.println("0. Exit");
            System.out.print("Option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    showLibraryMenu(user);
                    break;
                case "2":
                    showChooseRandomPlaylistMenu(user);
                    break;
               
                case "3":
                    System.out.println("Showing all the Music Names:\n" + spotifUM.getMusicNames().toString());
                    break;
                case "4":
                    System.out.print("Enter music to search (or 'exit'): ");
                    String musicString = scanner.nextLine();
                    if (musicString.equalsIgnoreCase("exit"))
                        break;
                    Music music = spotifUM.getMusic(musicString);
                    if (music == null) {
                        System.out.println("Music not found");
                        continue;
                    }
                    showMusicMenu(user, music);
                    break;
                case "5":
                    System.out.println("Showing all the Album Names:\n" + spotifUM.getAlbumNames().toString());
                    break;
                case "6":
                    System.out.println("Showing available Albums: \n" + spotifUM.getAlbumNames().toString());
                    System.out.print("Enter Album to play (or 'exit'): ");
                    String albumString = scanner.nextLine();
                    if (albumString.equalsIgnoreCase("exit"))
                        break;
                    Album album = spotifUM.getAlbum(albumString);
                    if (album == null) {
                        System.out.println("Album not found");
                        break;
                    }
                    System.out.println("Playing: " + albumString);
                    showAlbumMenu(user, album);
                    break;
                case "7":
                    System.out.print("Showing all playlists:\n" + spotifUM.getPlaylistNames());
                    break;
                case "8":
                    System.out.print("Showing available playlists:\n" + spotifUM.getPlaylistNames());
                    System.out.print("Enter Playlist to play (or 'exit'): ");
                    String playlistString = scanner.nextLine();
                    if (playlistString.equalsIgnoreCase("exit"))
                        break;
                    Playlist playlist= spotifUM.getPlaylist(playlistString);
                    if (playlist == null) {
                        System.out.println("Playlist not found");
                        break;
                    }
                    System.out.println("Playing: " + playlistString);
                    //code
                    break;
                    
                case "9":
                    System.out.println(
                            "Showing user's history from the oldest to the most recent: \n" + user.historyToString());
                case "10":
                    System.out.println("Your total Points: " + user.getPoints());
                    break;
                case "11":
                    System.out.println("Subscripton Plan: " + user.getPlan());
                case "0":
                    return;
            }
        }
    }

    // ==========
    // LIBRARYMENU=========================================================================================
    public void showLibraryMenu(User user) {
        while (true) {
            System.out.println("Library options:");
            System.out.println("1. Show your Library");
            System.out.println("2. Play");
            System.out.println("3. Create a Playlist");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Your Library: \n" + user.libraryToString());
                    break;
                case "2":
                    
                    
                    System.out.println("Your Library: \n" + user.libraryToString());
                    System.out.print("Enter the Playlist or Album to play (or 'exit'): ");
                    String collectionString = scanner.nextLine();
                    if (collectionString.equalsIgnoreCase("exit"))
                        break;
                    MusicCollection mc=user.getFromLibrary(collectionString);
                    if(mc!=null){   
                        if (mc instanceof Album){
                            showPlayAlbumMenu(user,mc);
                            break;
                        }else if (mc instanceof Playlist) {
                            
                            break;
                        }

                    }else{
                        System.out.println("Album or Playlist not found");
                        break;
                    }
                    

                    break;
                case "3":
                    Playlist newPlaylist=PlaylistFactory.create(scanner, spotifUM, user);
                    System.out.println("Playlist created: " + newPlaylist.getName());
                    break;

                case "0":
                    System.out.println("Exiting.");
                    return;
                default:
                    break;
            }
        }
    }

    // ========== Music
    // =========================================================================================
    public void showMusicMenu(User user, Music music) {
        while (true) {
            System.out.println("\n" + music.getName());
            System.out.println("Music options:");
            System.out.println("1. Play");
            System.out.println("2. Show Music info"); // random, comecar por onde e poder skipar ou n
            if (user.getPlan().allows(Playlist.class))
                System.out.println("3. Add to a playlist"); // preciso de por esta opcao if para cada plano
            if (user.getPlan().allows(Favorites.class))
                System.out.println("4. Like");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    user.play(music);
                    // System.out.println("Played: " + music);
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

    // ========== ALBUM
    // =========================================================================================

    public void showAlbumMenu(User user, Album album) {
        while (true) {

            System.out.println("Album options:");
            System.out.println("1. Show musics");
            System.out.println("2. Play"); // random, comecar por onde e poder skipar ou n
            System.out.println("3. Add to Library");
            System.out.println("0. Exit.");
            System.out.print("Option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Showing all musics from the album " + album.getName() + ":\n"
                            + album.getMusicNames().toString());
                    break;

                case "2":

                    showPlayAlbumMenu(user, album);
                    break;
                case "3":
                    System.out.println("Album added to the Library");
                    user.addToLibrary(album);
                    break;
                case "0":
                    return;
                default:
                    break;
            }

        }
    }

    public void showPlayAlbumMenu(User user, MusicCollection album) {
        int pos = 0;

        int last = (album.getMusicList().size())-1;
        user.play(album.getMusic(pos));

        while (true) {

            System.out.println("What you want to do:");
            System.out.println("1. Next Song");
            System.out.println("2. Previous Song"); // random, comecar por onde e poder skipar ou n
            System.out.println("0. Exit.");
            System.out.print("Option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    if (pos >= last) {
                        System.out.println("Album finished. Want to play again?");
                        System.out.println("1. Yes");
                        System.out.println("0. Exit.");
                        System.out.print("Option:");
                        String choice2 = scanner.nextLine();
                        switch (choice2) {
                            case "1":
                                pos = 0;
                                user.play(album.getMusic(pos));

                                break;
                            case "0":
                                return;
                            default:
                                System.out.println("Invalid option");
                                break;
                        }
                        break;

                    } else {
                        pos += 1;
                        user.play(album.getMusic(pos));

                        break;

                    }

                case "2":
                    if (user.canSkip()) {
                        if (pos <= 0) {
                            System.out.println("You're on the first song, there isn't a previous one.");
                            break;
                        }
                        pos -= 1;
                        user.play(album.getMusic(pos));

                        break;
                    }else{
                        System.out.println("Your plan does not have access to this feature ");
                        break;
                    }
                case "0":
                    return;
                default:
                    break;
            }

        }

    }

    // ========== RANDOMPLAYLIST
    // =========================================================================================
    public void showChooseRandomPlaylistMenu(User user) {

        while (true) {
            System.out.println("\nRandom Playlists options:");
            System.out.println("1. Show Random Playlists");
            System.out.println("2. Play"); // random, comecar por onde e poder skipar ou n
            System.out.println("0. Exit.");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print(
                            "Showing all the Random Playlists:\n" + spotifUM.getRandomPlaylistNames().toString());
                    break;

                case "2":
                    System.out.print("Enter a playlist name to play (or 'exit'): ");
                    String randomPlaylistString = scanner.nextLine();
                    if (randomPlaylistString.equalsIgnoreCase("exit"))
                        break;
                    RandomPlaylist randomPlaylist = spotifUM.getRandomPlaylist(randomPlaylistString);
                    if (randomPlaylist == null) {
                        System.out.println("Random Playlist not found");
                        continue;
                    }
                    showRandomPlaylistMenu(user, randomPlaylist);
                    break;

                case "0":
                    return;
                default:
                    break;
            }
        }

    }

    public void showRandomPlaylistMenu(User user, RandomPlaylist randomPlaylist) {
        while (true) {
            System.out.println("\n" + randomPlaylist.getName());
            System.out.println("Play Random Playlists options:");
            System.out.println("1. Show Musics");
            System.out.println("2. Play the Playlist");
            System.out.println("0. Exit.");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Showing all the musics:\n" + randomPlaylist.getMusicList().toString());
                    break;

                case "2":
                    System.out.println("Playing " + randomPlaylist.getName());
                    showRandomPlayMenu(user, randomPlaylist);
                    break;

                case "0":
                    return;

                default:
                    break;
            }
        }
    }

    public void showRandomPlayMenu(User user, RandomPlaylist randomPlaylist) {
        for (Music music : randomPlaylist.getMusicList()) {
            boolean waitingForPlay = true;

            while (waitingForPlay) {
                System.out.println("\n" + music.getName());
                System.out.println("Play Music options:");
                System.out.println("1. Play");
                System.out.println("2. Show Music Info");
                System.out.println("0. Exit");
                System.out.print("Option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        user.play(music);
                        // System.out.println("Played: " + music.getName());
                        waitingForPlay = false; // proceed to next music
                        break;

                    case "2":
                        System.out.println("Music's info:\n" + music.toString());
                        break;

                    case "0":
                        return; // exits the whole loop

                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }
        System.out.println("Exiting (No more musics to play)");
        return;
    }

    // ========== ADMIN MODE
    // =========================================================================================
    /**
     * Displays the Admin menu of the SpotifUM application.
     * Handles the creation of musics album playlists etc.
     */
    public void showAdminMenu(User adminUser) {
        /* menu do admin */
        System.out.println("\nAdmin mode activated.");
        while (true) {
            System.out.println("\nAdmin options:");
            System.out.println("1. Create music");
            System.out.println("2. Create album");
            System.out.println("3. Create Playlist");
            System.out.println("4. Create random playlist");
            System.out.println("5. Show musics");
            System.out.println("6. Show albums");
            System.out.println("7. Show playlists");
            System.out.println("8. Show random playlists");
            System.out.println("0. Exit admin");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Music newMusic = MusicFactory.create(scanner);
                    spotifUM.addMusic(newMusic);
                    System.out.println("Music created: " + newMusic.getName());
                    break;

                case "2":
                    Album newAlbum = AlbumFactory.create(scanner, spotifUM);
                    spotifUM.addAlbum(newAlbum);
                    System.out.println("Album created: " + newAlbum.getName());
                    break;
                case "3":
                    Playlist newPlaylist=PlaylistFactory.create(scanner, spotifUM, adminUser);
                    System.out.println("Playlist created: " + newPlaylist.getName());
                    break;
                case "4":
                    RandomPlaylist newRandomPlaylist = RandomPlaylistFactory.create(scanner, spotifUM, adminUser);
                    spotifUM.addRandomPlaylist(newRandomPlaylist);
                    System.out.println("Random Playlist created: " + newRandomPlaylist.getName());
                    break;

                case "5":
                    System.out.print("Showing all the Musics:\n" + spotifUM.getMusicNames());
                    break;
                case "6":
                    System.out.print("Showing all the Albums:\n" + spotifUM.getAlbumNames());
                    break;
                case "7":
                    System.out.print("Showing all playlists:\n" + spotifUM.getPlaylistNames());
                    break;
                case "8":
                    System.out.print(
                            "Showing all the Random Playlists:\n" + spotifUM.getRandomPlaylists().values().toString());
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
