import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Album implements MusicCollection, Serializable {
    private String name;
    private HashSet<String> authors;
    private ArrayList<Music> musicList;

    public Album() {
        musicList = new ArrayList<>();
        name = new String();
        authors = new HashSet<>();
    }

    public Album(String name, ArrayList<Music> musicList, HashSet<String> authors) {
        this.musicList = new ArrayList<>(musicList);
        this.name = name;
        this.authors = new HashSet<>(authors);
    }

    // getters

    public int getStreams() {
        int totalStreams = 0;
        for (Music music : musicList) {
            totalStreams += music.getStreams();
        }
        return totalStreams;
    }

    public int getDuration() {
        int totalDuration = 0;
        for (Music music : musicList) {
            totalDuration += music.getDuration();
        }
        return totalDuration;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public Set<String> getMusicNames() {
        Set<String> names = new HashSet<>();
        for (Music music : musicList) {
            names.add(music.getName());
        }
        return names;
    }

    public Music getMusic(int index) {
        return musicList.get(index);
    }

    public HashSet<String> getAuthors() {
        return new HashSet<>(authors);
    }
    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    public void setMusic(Music m, int index) {
        if (index >= 0) {
            musicList.set(index, m);
        }
    }

    public void setMusic(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    public void setAuthors(HashSet<String> authors) {
        this.authors = new HashSet<>(authors);
    }

    // ops

    public void removeMusic(Music m) {
        musicList.remove(m);
    }

    public void removeMusic(int index) {
        musicList.remove(index);
    }
    //

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Album album = (Album) o;

        if (!name.equals(album.name))
            return false;
        return musicList.equals(album.musicList);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Album: " + name + ", Duration: " + this.getDuration() + ", Streams: "
                + this.getStreams() + ", Music List:\n");
        for (Music music : musicList) {
            result.append(music.toString()).append("\n");
        }
        return result.toString();
    }

    public int hashCode() {
        int hash = 12;
        hash = 37 * hash + this.name.hashCode();

        return hash;
    }
    public void addMusic(Music music) {
        musicList.add(music);
        for(String a:music.getInterpreter()){
            authors.add(a);
        }
    }
}
