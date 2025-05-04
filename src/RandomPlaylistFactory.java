import java.util.Scanner;


public class RandomPlaylistFactory {
    public static RandomPlaylist create(Scanner scanner, SpotifUM spotifUM, User adminUser) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Number of Musics: ");
        int n = Integer.parseInt(scanner.nextLine());
        RandomPlaylist newRandomPlaylist = new RandomPlaylist(name, adminUser, spotifUM, n);
        return newRandomPlaylist;
    }
}