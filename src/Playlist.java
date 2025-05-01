import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements MusicCollection, Serializable {

    protected User creator;
    protected String name;
    protected ArrayList<Music> musicList;

    public Playlist() {
        creator = new User();
        musicList = new ArrayList<>();
        name = new String();
    }

    public Playlist(String name, ArrayList<Music> musicList, User creator) {
        this.musicList = new ArrayList<>(musicList);
        this.name = name;
        this.creator = creator;
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

    public User getCreator() {
        return creator;
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

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    
    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    public void setMusic(Music m, int index) {
        this.musicList.set(index, m);
    }

    //ops
    public void addMusic(Music m) {
        this.musicList.add(m);
    }
    
    public void removeMusic(Music m) {
        this.musicList.remove(m);
    }

    public void removeMusic(int index) {
        this.musicList.remove(index);
    }

    public void ClearPlaylist() {
        this.musicList.clear();
    }

    }