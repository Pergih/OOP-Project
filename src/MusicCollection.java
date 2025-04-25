
public interface MusicCollection {
    
    public Music getMusic(int index);
    public void removeMusic(Music m);
    public int getDuration();
    public String toString();
    public int getStreams();
}
