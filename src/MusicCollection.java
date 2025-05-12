import java.util.ArrayList;

public interface MusicCollection {
    
    public Music getMusic(int index);
    public void removeMusic(Music m);
    public int getDuration();
    public String toString();
    public int getStreams();
    public ArrayList<Music> getMusicList();
    public void setMusicList(ArrayList<Music> musicList);
    public void setName(String name);
    public String getName();
    public void setMusic(Music m, int index);
    public void removeMusic(int index);
    public ArrayList<String> getMusicListNames();
    


}
