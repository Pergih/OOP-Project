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

    // toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Playlist ===\n");
        sb.append("Name: ").append(name).append('\n');
        sb.append("Creator: ").append(creator != null ? creator.getHandle() : "Unknown").append('\n');
        sb.append("Total Duration: ").append(getDuration()).append(" seconds\n");
        sb.append("Total Streams: ").append(getStreams()).append('\n');
        sb.append("Music List:\n");

        if (musicList.isEmpty()) {
            sb.append("  (No music in this playlist)\n");
        } else {
            for (int i = 0; i < musicList.size(); i++) {
                Music music = musicList.get(i);
                sb.append("  ").append(i + 1).append(". ").append(music.getName())
                .append(" by ").append(music.getInterpreter())
                .append(" (").append(music.getDuration()).append("s, ")
                .append(music.getStreams()).append(" streams)\n");
            }
        }

        return sb.toString();
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