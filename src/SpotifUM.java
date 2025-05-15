import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

// falta playlists
public class SpotifUM implements Serializable {
    private Map<String, User> users;
    private Map<String, Music> musics;
    private Map<String, Album> albums;
    private Map<String, RandomPlaylist> randomPlaylists;
    private Map<String, Playlist> playlists;

    public SpotifUM() {
        users = new HashMap<String, User>();
        musics = new HashMap<String, Music>();
        albums = new HashMap<String, Album>();
        playlists = new HashMap<String, Playlist>();
        randomPlaylists = new HashMap<String, RandomPlaylist>();
    }

    // Getters
    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Music> getMusics() {
        return musics;
    }

    public Map<String, Album> getAlbums() {
        return albums;
    }

    public Map<String, RandomPlaylist> getRandomPlaylists() {
        return randomPlaylists;
    }

    public Map<String, Playlist> getPlaylists() {
        return playlists;
    }

    // Add methods
    public void addUser(User user) {
        users.put(user.getHandle(), user);
    }

    public void addMusic(Music music) {
        musics.put(music.getName(), music);
    }

    public void addAlbum(Album album) {
        albums.put(album.getName(), album);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.put(playlist.getName(), playlist);
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist.getName(), playlist);
    }

    public void addRandomPlaylist(RandomPlaylist RandomPlaylist) {
        randomPlaylists.put(RandomPlaylist.getName(), RandomPlaylist);
    }

    // Save data to file
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    // Load data from file
    public static SpotifUM loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (SpotifUM) in.readObject();
        }
    }

    public User getUser(String handle) {
        return users.get(handle);
    }

    public Music getMusic(String music) {
        return musics.get(music);
    }

    public Playlist getPlaylist(String playlist) {
        return playlists.get(playlist);
    }

    public Set<String> getPlaylistNames() {
        return playlists.keySet();
    }

    public Set<String> getMusicNames() {
        return musics.keySet();
    }

    public Set<String> getAlbumNames() {
        return albums.keySet();
    }

    public Set<String> getRandomPlaylistNames() {
        return randomPlaylists.keySet();
    }

    public Album getAlbum(String album) {
        return albums.get(album);
    }

    public int getNumUsers() {
        return users.size();
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public RandomPlaylist getRandomPlaylist(String randomPlaylistString) {
        return randomPlaylists.get(randomPlaylistString);
    }

    public Music getMostPlayedMusic () throws NoMusicsException  {

        if (musics.isEmpty()) {
            throw new NoMusicsException("No musics available.");
        }
        Music r = null;
        for (Music m : musics.values()) {
            if (r == null || m.getStreams() > r.getStreams()) {
                r = m;
            }
        }
        return r;
    }

    public String getMostListenedInterpreter()  throws NoMusicsException {
        if (musics.isEmpty()) {
            throw new NoMusicsException("No musics available.");
        }
        String r = null;
        int maxStreams = -1;

        Map<String, Integer> interpreters = new HashMap<>();

        for (Music music : musics.values()) {
            for (String interpreter : music.getInterpreter()) {
                interpreters.put(interpreter,
                        interpreters.getOrDefault(interpreter, 0) + music.getStreams());
            }
        }

        for (Map.Entry<String, Integer> entry : interpreters.entrySet()) {
            if (entry.getValue() > maxStreams) {
                maxStreams = entry.getValue();
                r = entry.getKey();
            }
        }
        return r;
    }

    public User getTopListener(LocalDateTime start, LocalDateTime end) throws NoUsersException {
        if (users.isEmpty()) {
            throw new NoUsersException("No users available.");
        }
        User topUser = null;
        int maxCount = -1;

        for (User user : users.values()) {
            int count = (start == null || end == null)
                    ? user.countSongsAllTime()
                    : user.countSongsBetween(start, end);

            if (count > maxCount) {
                maxCount = count;
                topUser = user;
            }
        }

        if (topUser != null) {
            System.out.println("User with the most songs listened to: " +
                    topUser.getName() + " with " + maxCount + " songs listened to.");
        } else {
            System.out.println("No users found or no listening data available.");
        }

        return topUser;
    }

    public User getMostPointsUser() throws NoUsersException {
        if (users.isEmpty()) {
            throw new NoUsersException("No users available.");
        }
        User topUser = null;
        int maxPoints = -1;

        for (User u : users.values()) {
            int userPoints = u.getPoints();
            if (userPoints > maxPoints) {
                maxPoints = userPoints;
                topUser = u;
            }
        }

        return topUser;
    }

    public Genre getMostPlayedGenre() {
        Map<Genre, Integer> genreStreams = new HashMap<>();

        for (Music music : musics.values()) {
            for (Genre genre : music.getGenres()) {
                genreStreams.put(genre, genreStreams.getOrDefault(genre, 0) + music.getStreams());
            }
        }

        Genre topGenre = null;
        int maxStreams = -1;

        for (Map.Entry<Genre, Integer> entry : genreStreams.entrySet()) {
            if (entry.getValue() > maxStreams) {
                maxStreams = entry.getValue();
                topGenre = entry.getKey();
            }
        }

        return topGenre;
    }

    // change this when we solve the playlist public thing
    public int getNumberPublicPlaylists() {
        int count = 0;
        for (Playlist playlist : playlists.values()) {
            if (playlist.getIsPublic()) {
                count++;
            }
        }
        return count;
    }

    public User getUserWithMostPlaylists() throws NoUsersException {
        if (users.isEmpty()) {
            throw new NoUsersException("No users available.");
        }
        User topUser = null;
        int maxPlaylists = -1;

        for (User user : users.values()) {
            int playlistCount = 0;
            for (MusicCollection collection : user.getLibrary()) {
                if (collection instanceof Playlist) {
                    playlistCount++;
                }
            }

            if (playlistCount > maxPlaylists) {
                maxPlaylists = playlistCount;
                topUser = user;
            }
        }

        return topUser;
    }
}
