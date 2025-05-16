import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
            System.out.println("4. Statistics' menu");
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
                case "4":
                    showStatisticsMenu();
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
     * Displays the User menu of the SpotifUM application.
     *
     * @param user The chosen user.
     */
    public void showUserMenu(User user) {
        /* menu do utilizador */
        System.out.println("\nWelcome " + user.getName() + "!");
        while (true) {
            System.out.println("\nChoose an option:");
            if (user.getPlan().allows(Album.class) || user.getPlan().allows(Playlist.class)) {
                System.out.println("1. Library");
            }
            System.out.println("2. Play Random Playlists");
            if (user.getPlan().allows(Album.class) || user.getPlan().allows(Playlist.class)) {
                System.out.println("3. Show all Music Names");
                System.out.println("4. Search a Music");
            }
            if (user.getPlan().allows(Album.class)) {
                System.out.println("5. Show all Albums");
                System.out.println("6. Play an Album");
            }
            if (user.getPlan().allows(Playlist.class)) {
                System.out.println("7. Show all public Playlists");
                System.out.println("8. Play a public playlist");
            }
            System.out.println("9. Show user's history");
            System.out.println("10. My Points");
            System.out.println("11. Show Subscription Plan");

            System.out.println("0. Exit");
            System.out.print("Option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    if (user.getPlan().allows(Album.class) || user.getPlan().allows(Playlist.class)) {
                        showLibraryMenu(user);
                    } else {
                        System.out.println("Not an Option");
                    }
                    break;
                case "2":
                    showChooseRandomPlaylistMenu(user);
                    break;

                case "3":
                    if (user.getPlan().allows(Album.class) || user.getPlan().allows(Playlist.class)) {
                        System.out.println("Showing all the Music Names:\n" + spotifUM.getMusicNames().toString());
                    } else {
                        System.out.println("Not an Option");

                    }
                    break;

                case "4":
                    if (user.getPlan().allows(Album.class) || user.getPlan().allows(Playlist.class)) {

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
                    } else {
                        System.out.println("Not an Option");
                    }

                    break;

                case "5":
                    if (user.getPlan().allows(Album.class)) {

                        System.out.println("Showing all the Album Names:\n" + spotifUM.getAlbumNames().toString());
                    } else {
                        System.out.println("Not an Option");
                    }
                    break;
                case "6":
                    if (user.getPlan().allows(Album.class)) {
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
                    } else {
                        System.out.println("Not an Option");
                    }
                    break;
                case "7":
                    if (user.getPlan().allows(Playlist.class)) {
                        System.out.print("Showing all playlists:\n" + spotifUM.getPlaylistNames());
                    } else {
                        System.out.println("Not an Option");
                    }

                    break;
                case "8":
                    if (user.getPlan().allows(Playlist.class)) {
                        System.out.print("Showing available playlists:\n" + spotifUM.getPlaylistNames());
                        System.out.print("Enter Playlist to play (or 'exit'): ");
                        String playlistString = scanner.nextLine();
                        if (playlistString.equalsIgnoreCase("exit"))
                            break;
                        Playlist playlist = spotifUM.getPlaylist(playlistString);
                        if (playlist == null) {
                            System.out.println("Playlist not found");
                            break;
                        }
                        System.out.println("Playing: " + playlistString);
                        showPlaylistMenu(user, playlist);
                    } else {
                        System.out.println("Not an Option");
                    }
                    break;

                case "9":
                    System.out.println(
                            "Showing user's history from the oldest to the most recent: \n" + user.historyToString());
                case "10":
                    System.out.println("Your total Points: " + user.getPoints());
                    break;
                case "11":
                    System.out.println("Subscripton Plan: " + user.getPlan());
                    break;
                case "0":
                    return;
            }
        }
    }

    // ==========
    // LIBRARYMENU=========================================================================================
    /**
     * Displays the Ulibrary menu of the SpotifUM application.
     *
     * @param user The chosen user.
     */
    public void showLibraryMenu(User user) {
        while (true) {
            System.out.println("Library options:");
            System.out.println("1. Show your Library");
            System.out.println("2. Play");
            System.out.println("3. Create a Playlist");
            if (user.getPlan().allows(Favorites.class)) {
                System.out.println("4. Create your Favourite Playlist");
            }

            if (user.getPlan().allows(GenPlaylist.class)) {
                System.out.println("5. Generate a Playlist");
            }
            if (user.getPlan().allows(Playlist.class)) {
                System.out.println("6. Make Playlist Public/Private");
            }
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
                    MusicCollection mc = user.getFromLibrary(collectionString);
                    if (mc != null) {
                        showSequentialPlayMenu(user, mc);
                        break;

                    } else {
                        System.out.println("Album or Playlist not found");
                        break;
                    }

                case "3":
                    Playlist newPlaylist = PlaylistFactory.create(scanner, spotifUM, user);
                    System.out.println("Playlist created: " + newPlaylist.getName());
                    break;

                case "4":
                    if (user.getPlan().allows(Favorites.class)) {

                        Favorites newFavorites = new Favorites("Favourite " + LocalDateTime.now().toString(), user,
                                spotifUM);
                        System.out.println("Playlist created: " + newFavorites.getName());
                    } else {
                        System.out.println("Not an Option");
                    }
                    break;
                case "5":
                    // to do gen playlist menu
                    break;
                case "6":
                    System.out.println("Your Library: \n" + user.libraryToString());
                    System.out.print("Enter the Playlist to change visibility (or 'exit'): ");
                    String collectionString2 = scanner.nextLine();
                    if (collectionString2.equalsIgnoreCase("exit"))
                        break;
                    MusicCollection mc2 = user.getFromLibrary(collectionString2);
                    if (mc2 instanceof Album) {
                        System.out.println(
                                "You selected an Album, whose visibility settings cannot be changed. Please select a Playlist.\n");
                        break;
                    } else if (mc2 instanceof Playlist) {
                        System.out.println(
                                "You selected this Playlist: \n" + mc2.toString() + "\n");
                                if (((Playlist) mc2).getIsPublic() == true) {
                                    ((Playlist)mc2).makePrivate(spotifUM);
                                    System.out.println("Playlist is now private.\n ");
                                }
                                else {
                                    ((Playlist)mc2).makePublic(spotifUM);
                                    System.out.println("Playlist is now public.\n ");
                                }
                        break;

                    } else {
                        System.out.println("Playlist not found");
                        break;
                    }

                case "0":
                    System.out.println("Exiting.");
                    return;
                default:
                    break;
            }
        }
    }

    // =================== Playlist
    // ==================================================================
    /**
     * Displays the Playlist menu of the SpotifUM application.
     *
     * @param user     The chosen user.
     * @param playlist the chosen playlist
     */
    public void showPlaylistMenu(User user, Playlist playlist) {
        while (true) {
            System.out.println("Playlist options:");
            System.out.println("1. Show musics");
            System.out.println("2. Play"); // random, comecar por onde e poder skipar ou n
            System.out.println("3. Add to Library");
            System.out.println("0. Exit.");
            System.out.print("Option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Showing all musics from the Playlist " + playlist.getName() + ":\n"
                            + playlist.getMusicNames());
                    break;
                case "2":
                    showSequentialPlayMenu(user, playlist);
                    break;

                case "3":
                    System.out.println("Playlist added to the Library");
                    user.addToLibrary(playlist);
                    break;
                case "0":
                    System.out.println("Exiting.");
                    return;
                default:
                    break;

            }
        }
    }

    public void showSequentialPlayMenu(User user, MusicCollection collection) {
        int pos = 0;
        int last = collection.getMusicList().size() - 1;

        while (true) {
            Music current = collection.getMusic(pos);
            System.out.println("\nNow playing: " + current.getName());
            System.out.println("Play options:");
            System.out.println("1. Play this song");
            System.out.println("2. Next Song");
            System.out.println("3. Previous Song");
            System.out.println("4. Show all Songs");

            // Show random only if a playlist and not made by Admin
            if (collection instanceof Playlist) {
                if (!((Playlist) collection).getCreator().getHandle().equals("Admin")) {
                    System.out.println("5. Random Song");
                }
            }

            System.out.println("0. Exit");
            System.out.print("Option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    user.play(current);
                    pos++;
                    if (pos > last) {
                        System.out.println("End of collection. Play from start?");
                        System.out.println("1. Yes");
                        System.out.println("0. Exit");
                        System.out.print("Option: ");
                        String restart = scanner.nextLine();

                        if (restart.equals("1")) {
                            pos = 0;
                            user.play(collection.getMusic(pos));
                        } else {
                            return;
                        }
                    } else {
                        pos++;
                        user.play(collection.getMusic(pos));
                    }
                    break;

                case "2":
                    if (pos >= last) {
                        System.out.println("End of collection. Play from start?");
                        System.out.println("1. Yes");
                        System.out.println("0. Exit");
                        System.out.print("Option: ");
                        String restart = scanner.nextLine();

                        if (restart.equals("1")) {
                            pos = 0;
                            user.play(collection.getMusic(pos));
                        } else {
                            return;
                        }
                    } else {
                        pos++;
                        user.play(collection.getMusic(pos));
                    }
                    break;

                case "3":
                    if (collection instanceof Playlist) {
                        if (((Playlist) collection).getCreator().getHandle().equals("Admin")) {
                            System.out.println("This collection is managed by Admin. Cannot go back.");
                        } else {
                            if (pos <= 0) {
                                System.out.println("You're on the first song.");
                            } else {
                                pos--;
                                user.play(collection.getMusic(pos));
                            }
                        }
                    } else {
                        if (pos <= 0) {
                            System.out.println("You're on the first song.");
                        } else {
                            pos--;
                            user.play(collection.getMusic(pos));
                        }
                    }
                    break;

                case "4":
                    System.out.println("Showing all the songs: \n" + collection.getMusicListNames().toString());

                    break;
                case "5":
                    if (collection instanceof Playlist) {
                        if (!((Playlist) collection).getCreator().getHandle().equals("Admin")) {
                            Random rand = new Random();
                            pos = rand.nextInt(collection.getMusicList().size());
                            user.play(collection.getMusic(pos));
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    // ========== Music
    // =========================================================================================
    /**
     * Displays the music Menu of the SpotifUM application.
     *
     * @param user  The chosen user.
     * @param music the chosen music
     */
    public void showMusicMenu(User user, Music music) {
        while (true) {
            System.out.println("\n" + music.getName());
            System.out.println("Music options:");
            System.out.println("1. Play");
            System.out.println("2. Show Music info"); // random, comecar por onde e poder skipar ou n
            if (user.getPlan().allows(Playlist.class))
                System.out.println("3. Add to a playlist"); // preciso de por esta opcao if para cada plano
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
    /**
     * Displays the Album Menu of the SpotifUM application.
     *
     * @param user  The chosen user.
     * @param album the chosen album
     */
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

                    showSequentialPlayMenu(user, album);
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

    // ========== RANDOMPLAYLIST
    // =========================================================================================
    /**
     * Displays the Menu to chose the random playlist that a user wants to listen in
     * the SpotifUM application.
     *
     * @param user The chosen user.
     * 
     */
    public void showChooseRandomPlaylistMenu(User user) {

        while (true) {
            System.out.println("\nChoosing Random Playlists:");
            System.out.println("1. Show Random Playlists");
            System.out.println("2. Choose"); // random, comecar por onde e poder skipar ou n
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

    /**
     * Displays the Random Playlist Menu of the SpotifUM application.
     *
     * @param user           The chosen user.
     * @param randomPlaylist the chosen random playlist
     */
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

    /**
     * Displays the Play menu of a Random Playlist in the SpotifUM application.
     *
     * @param user           The chosen user.
     * @param randomPlaylist the chosen random playlist
     */
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
     * 
     * @param adminUser the admin user
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
                    Music newMusic = MusicFactory.create(scanner, spotifUM);
                    spotifUM.addMusic(newMusic);
                    System.out.println("Music created: " + newMusic.getName());
                    break;

                case "2":
                    try {
                        Album newAlbum = AlbumFactory.create(scanner, spotifUM);
                        spotifUM.addAlbum(newAlbum);
                        System.out.println("Album created: " + newAlbum.getName());
                    } catch (NoMusicsException e) {
                        System.out.println(e);
                    }
                    break;
                case "3":
                    Playlist newPlaylist = PlaylistFactory.create(scanner, spotifUM, adminUser);
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

    // ============ Statistics===============================================
    /**
     * Displays the statistics menu of the SpotifUM application.
     * 
     */
    public void showStatisticsMenu() {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Most played song");
            System.out.println("2. Most listened to interpreter");
            System.out.println("3. Which user has listened to the most songs over a period of time or ever since?");
            System.out.println("4. User with the most points");
            System.out.println("5. Most Played Genre");
            System.out.println("6. how many public playlists are on SpotifUM");
            System.out.println("7. User with the most playlists");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    try {
                        System.out.println(spotifUM.getMostPlayedMusic().toString());
                    } catch (NoMusicsException e) {
                        System.out.println(e);
                    }
                    break;
                case "2":
                    try {
                        String r = spotifUM.getMostListenedInterpreter();
                        System.out.println("Most listened to interpreter: " + r + " with "
                                + spotifUM.getMusic(r).getStreams() + " streams");
                    } catch (NoMusicsException e) {
                        System.out.println(e);
                    }
                    break;
                case "3":
                    System.out.println("Do you want to check:");
                    System.out.println("1. All-time most active listener");
                    System.out.println("2. Most active listener between two dates");
                    System.out.print("Option: ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            try {
                                spotifUM.getTopListener(null, null);
                            } catch (NoUsersException e) {
                                System.out.println(e);
                            }
                            break;

                        case "2":
                            try {
                                System.out.print("Enter start date-time (yyyy-MM-ddTHH:mm): ");
                                String startInput = scanner.nextLine();
                                LocalDateTime start = LocalDateTime.parse(startInput);

                                System.out.print("Enter end date-time (yyyy-MM-ddTHH:mm): ");
                                String endInput = scanner.nextLine();
                                LocalDateTime end = LocalDateTime.parse(endInput);

                                try {
                                    spotifUM.getTopListener(start, end);
                                } catch (NoUsersException e) {
                                    System.out.println(e);
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println(
                                        "Invalid date format. Please use yyyy-MM-ddTHH:mm (e.g., 2025-05-08T14:30).");
                            }
                            break;

                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    break;

                case "4":
                    try {
                        User rr = spotifUM.getMostPointsUser();
                        System.out.println("Most listened to interpreter: " + rr + " with "
                                + rr.getPoints() + " streams");
                    } catch (NoUsersException e) {
                        System.out.println(e);
                    }
                    break;
                case "5":
                    Genre topGenre = spotifUM.getMostPlayedGenre();
                    System.out.println("Most played genre: " + topGenre);
                    break;
                case "6":
                    // count the number of public playlists
                    int count = spotifUM.getNumberPublicPlaylists();
                    System.out.println("Number of public playlists: " + count);
                    break;
                case "7":
                    try {
                        User top = spotifUM.getUserWithMostPlaylists();
                        if (top != null) {
                            System.out.println("User with the most playlists: " + top.getName());
                        } else {
                            System.out.println("No user found.");
                        }
                    } catch (NoUsersException e) {
                        System.out.println(e);
                    }

                    break;
                case "0":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
