import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Represents a Album Factory that has methods to create a album
 */
public class AlbumFactory {
    /**
     * Creates a Album
     * 
     * @param scanner  for the inputs.
     * @param spotifUM to get the musics.
     * 
     */

    public static Album create(Scanner scanner, SpotifUM spotifUM) throws NoMusicsException {

        if (spotifUM.getMusicNames().isEmpty()) {
            throw new NoMusicsException("No musics available to create an album.");
        }

        System.out.println("Name: ");
        String name = scanner.nextLine();

        ArrayList<Music> songs = new ArrayList<>();
        HashSet<String> authors = new HashSet<>();

        System.out.println("Songs you can choose: " + spotifUM.getMusicNames().toString());
        System.out.println("Enter the Songs' names line by line (type 'END' to finish):");

        String line = "";

        while (!line.equalsIgnoreCase("END")) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("END")) {
                Music music = spotifUM.getMusic(line);
                if (music == null) {
                    System.out.print("Music not found");
                    continue;
                }

                songs.add(music);
                for (String aut : music.getInterpreter()) {
                    authors.add(aut);
                }
            }
        }

        return new Album(name, songs, authors);
    }

    public static Album create(Scanner scanner, SpotifUM spotifUM, Music music){
        ArrayList<Music> songs = new ArrayList<>();
        HashSet<String> authors = new HashSet<>();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        songs.add(music);
        for (String aut : music.getInterpreter()) {
            authors.add(aut);
        }

        return new Album(name, songs, authors);
    }

}
