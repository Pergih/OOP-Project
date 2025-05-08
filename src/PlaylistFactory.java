import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Playlist Factory that has methods to create a Playlist
 */
public class PlaylistFactory {
    /**
     * Creates a Playlist
     * 
     * @param scanner for the inputs.
     * @param spotifUM to get the musics.
     
     */
    public static Playlist create(Scanner scanner,SpotifUM spotifUM,User user){
        User creator = user;

        System.out.print("Name: ");
        String name = scanner.nextLine();
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
        Playlist pp;
        if(creator.getHandle()=="admin"){
            
            pp= new PublicPlaylist(name, songs, creator);
            user.addToLibrary(pp);
            spotifUM.addPlaylist(pp);
            return pp;
        }
        
        System.out.println("Select Playlist type:");
        System.out.println("1. Public Playlist");
        System.out.println("2. Private Playlist");
        System.out.print("Option: ");
        String option = scanner.nextLine();
        switch(option) {
            case "1":
                pp=new PublicPlaylist(name, songs, creator);
                user.addToLibrary(pp);
                spotifUM.addPlaylist(pp);
                break;
            case "2":
                pp =new PrivatePlaylist(name, songs, creator);
                user.addToLibrary(pp);
                break;
            default:
                System.out.println("Invalid option, defaulting to Public Playlist.");
                pp=new PublicPlaylist(name, songs, creator);
                spotifUM.addPlaylist(pp);

        }
        return pp;

    }
}
