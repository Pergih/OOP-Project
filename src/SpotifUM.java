import java.io.*;
import java.util.*;


// falta playlists
public class SpotifUM implements Serializable {
    private Map<String, User> users;
    private Map<String, Music> musics;
    private Map<String, Album> albums;

    public SpotifUM() {
        users = new HashMap<String, User>();
        musics = new HashMap<String, Music>();
        albums = new HashMap<String, Album>();
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
    public Set<String> getMusicNames() {
        return musics.keySet();
    }

    public Set<String> getAlbumNames() {
        return albums.keySet();
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
}
