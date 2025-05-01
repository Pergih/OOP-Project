import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MusicFactory {
    public static Music create(Scanner scanner) {
        
        System.out.print("Music name: ");
        String name = scanner.nextLine();
        //interpreter
        ArrayList<String> interpreter = new ArrayList<>();
        System.out.println("Enter the interpreters (music artists) line by line (type 'END' to finish):");
        String line = "";
        while (!line.equalsIgnoreCase("END")) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("END")) {
                interpreter.add(line);
            }
        }
    
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
         line = "";
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
