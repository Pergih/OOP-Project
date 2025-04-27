import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SpotifUM spotifUM;
        Scanner scanner = new Scanner(System.in);

        try {
            spotifUM = SpotifUM.loadFromFile("saves/data.ser");
            System.out.println("Loaded existing data!");
        } catch (Exception e) {
            spotifUM = new SpotifUM();
            System.out.println("Starting new database!");
        }
        
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
                        userMode(scanner, user, spotifUM);
                    }
                    break;
                
                case "2":
                    User newUser = registerUser(scanner);
                    spotifUM.addUser(newUser);
                    System.out.println("Registered: " + newUser.getName());
                    break;

                case "3":
                    adminMode(scanner, spotifUM);
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

    private static void userMode(Scanner scanner, User user, SpotifUM spotifUM) {
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
                        if (musicString.equalsIgnoreCase("exit")) break;
                        Music music = spotifUM.getMusic(musicString);
                        if (music == null) {System.out.print("Music not found"); continue;}
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

    // ========== REGISTER USER ==========

    private static User registerUser(Scanner scanner) {
        System.out.print("Handle: ");
        String handle = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.println("Choose a plan:");
        System.out.println("1. Free");
        System.out.println("2. Premium Base");
        System.out.println("3. Premium Top");
        System.out.print("Option: ");
        int option = Integer.parseInt(scanner.nextLine());

        Plan plan;
        switch (option) {
            case 1: plan = new PlanFree(); break;
            case 2: plan = new PlanBasePremium(); break;
            case 3: plan = new PlanTopPremium(); break;
            default:
                System.out.println("Invalid option. Defaulting to Free.");
                plan = new PlanFree();
        }

        User user = new User(handle, name, email, address, plan);
        user.setPlan(plan); // apply join bonus
        return user;
    }

    // ========== ADMIN MODE ==========

    private static void adminMode(Scanner scanner, SpotifUM spotifUM) {
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
                    Music music = addMusic(scanner);
                    spotifUM.addMusic(music);
                    System.out.println("Music added: " + music);
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

    private static Music addMusic(Scanner scanner) {
        System.out.print("Music name: ");
        String name = scanner.nextLine();
    
        System.out.print("Interpreter (artist name): ");
        String interpreter = scanner.nextLine();
    
        System.out.print("Record label: ");
        String recordLabel = scanner.nextLine();
    
        // Genres
        HashSet<Genre> genres = new HashSet<>();
        System.out.println("Available genres:");
        for (Genre genre : Genre.values()) {
            System.out.println("- " + genre.name());
        }
        System.out.print("Enter genres (comma separated and capital): ");
        String genresInput = scanner.nextLine();
        for (String g : genresInput.split(",")) {
            try {
                genres.add(Genre.valueOf(g.trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown genre: " + g.trim() + " (skipped)");
            }
        }
    
        // Lyrics
        ArrayList<String> lyrics = new ArrayList<>();
        System.out.println("Enter lyrics line by line (type 'END' to finish):");
        String line = "";
        while (!line.equalsIgnoreCase("END")) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("END")) {
                lyrics.add(line);
            }
        }

    
        // Music notes
        ArrayList<String> musicNotes = new ArrayList<>();
        System.out.println("Enter music notes line by line (type 'END' to finish):");
        line = "";
        while (!line.equalsIgnoreCase("END")) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("END")) {
                musicNotes.add(line);
            }
        }

    
        // Duration
        System.out.print("Duration in seconds: ");
        int duration = Integer.parseInt(scanner.nextLine());
    
        // Create music
        System.out.println("Select music type:");
        System.out.println("1. Normal Music");
        System.out.println("2. Explicit Music");
        System.out.println("3. Media Music");
        System.out.print("Option: ");
        int typeOption = Integer.parseInt(scanner.nextLine());

        Music music;
        switch (typeOption) {
            case 1:
                music = new NormalMusic(name, interpreter, recordLabel, genres, lyrics, musicNotes, duration);
                break;
            case 2:
                music = new ExplicitMusic(name, interpreter, recordLabel, genres, lyrics, musicNotes, duration);
                break;
            case 3:
                System.out.print("Enter media video ID (integer): ");
                int videoId = Integer.parseInt(scanner.nextLine());
                music = new MediaMusic(name, interpreter, recordLabel, genres, lyrics, musicNotes, duration, videoId);
                break;
            default:
                System.out.println("Invalid option, defaulting to Normal Music.");
                music = new NormalMusic(name, interpreter, recordLabel, genres, lyrics, musicNotes, duration);
        }

            
        return music;
    }
    
}
