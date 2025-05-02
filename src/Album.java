import java.io.Serializable;
import java.util.ArrayList;


public class Album implements MusicCollection, Serializable {
    private String name;
    private ArrayList<String> authors;
    private ArrayList<Music> musicList;

    public Album() {
        musicList = new ArrayList<>();
        name = new String();
        authors = new ArrayList<>();
    }

    public Album(String name, ArrayList<Music> musicList, ArrayList<String> authors) {
        this.musicList = new ArrayList<>(musicList);
        this.name = name;
        this.authors = new ArrayList<>(authors);
    }

    //getters

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

    public Music getMusic(int index) {
            return musicList.get(index);
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }
    //setters

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

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    //ops


    public void removeMusic(Music m) {
        musicList.remove(m);
    }

    public void removeMusic(int index) { 
        musicList.remove(index);
    }
    //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (!name.equals(album.name)) return false;
        return musicList.equals(album.musicList);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Album: " + name + ", Duration: " + this.getDuration() + ", Streams: " + this.getStreams() + ", Music List:\n");
        for (Music music : musicList) {
            result.append(music.toString()).append("\n");
        }
        return result.toString();
    }
}
