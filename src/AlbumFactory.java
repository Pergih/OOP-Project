import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents a Album Factory  that has methods to create a album
 */
public class AlbumFactory {
    /**
     * Creates a Album
     * 
     * @param scanner for the inputs.
     * @param spotifUM to get the musics.
     
     */

     public static Album create (Scanner scanner,SpotifUM spotifUM){

        System.out.print("Name: ");
        String name = scanner.nextLine();
        //musics that the admin  wants to add to the album
        ArrayList<Music> songs = new ArrayList<>();
        System.out.println( "Songs you can choose"+ spotifUM.getMusicNames().toString());
        System.out.println("Enter the Songs' names  line by line (type 'END' to finish):");
        String line = "";
        while (!line.equalsIgnoreCase("END")) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("END")) {
                Music music = spotifUM.getMusic(line);
                if (music == null) {System.out.print("Music not found"); continue;}

                songs.add(music);

            }
        }
        ArrayList<String> authors = new ArrayList<>();
        authors.add("ADMIN");
        Album album;
        album = new Album(name, songs, authors);

        return album;
     }
}
