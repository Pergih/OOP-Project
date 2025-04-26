import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements MusicCollection, Serializable {

    //algum burro fez isto não abstract mas isso é para depois

    
    private String name;
    private ArrayList<Music> musicList;

    public Playlist() {
        musicList = new ArrayList<>();
        name = new String();
    }

    public Playlist(String name, ArrayList<Music> musicList) {
        this.musicList = new ArrayList<>(musicList);
        this.name = name;
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

    //setters
    public void setName(String name) {
        this.name = name;
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