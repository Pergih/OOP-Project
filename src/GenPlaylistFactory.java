
import java.util.Scanner;

public class GenPlaylistFactory {

    public static GenPlaylist create(Scanner scanner, SpotifUM spotifUM, User user) {
        User creator = user;
        System.out.println("Name for the Playlist: ");
        String name = scanner.nextLine();

        System.out.println("Select Generated Playlist type:");
        System.out.println("1. Playlist with the user's musical preference ");
        System.out.println("2. Playlist with the user's musical preference and restricted to a certain maximum time");
        System.out.println(
                "3. Playlist with the user's musical preference but only with songs of the Explicit Music type");
        System.out.print("Option: ");
        String option = scanner.nextLine();
        GenPlaylist pp;
        while (true) {
            switch (option) {
                case "1":

                    System.out.println("Available genres:");
                    for (Genre genre : Genre.values()) {
                        System.out.println("- " + genre.name());
                    }
                    System.out.print("Enter a Genre to generate a Playlist (in capital)");
                    String genreString = scanner.nextLine();
                    Genre genre1 = Genre.valueOf(genreString.toUpperCase());
                    pp = new GenPlaylist(name, creator, spotifUM, genre1, false);
                    user.addToLibrary(pp);
                    return pp;

                case "2":
                    System.out.println("Available genres:");
                    for (Genre genre : Genre.values()) {
                        System.out.println("- " + genre.name());
                    }
                    System.out.print("Enter a Genre to generate a Playlist (in capital)");
                    genreString = scanner.nextLine();
                    genre1 = Genre.valueOf(genreString.toUpperCase());
                    System.out.println("Enter the max duration time for the Playlist");
                    int i = scanner.nextInt();
                    pp = new GenPlaylist(name, creator, spotifUM, genre1, i);
                    user.addToLibrary(pp);
                    return pp;
                case "3":
                    System.out.println("Available genres:");
                    for (Genre genre : Genre.values()) {
                        System.out.println("- " + genre.name());
                    }
                    System.out.println("Enter a Genre to generate a Playlist (in capital):");
                    genreString = scanner.nextLine();
                    genre1 = Genre.valueOf(genreString.toUpperCase());
                    pp = new GenPlaylist(name, creator, spotifUM, genre1, true);
                    user.addToLibrary(pp);
                    return pp;

                default:
                    System.out.println("Invalid Option");
                    break;

            }
        }
    }
}
