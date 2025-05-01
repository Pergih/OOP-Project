
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Represents a MediaMusic with a name interpreter, record_label, genres,lyrics,
 * music, duration, streams and video.
 */
public class MediaMusic extends Music {
    private int video;

    /**
     * Constructs a new MediaMusic with no parameters.
     *
     *
     */
    public MediaMusic() {
        super();
        this.video = 1;
    }

    /**
     * Constructs a new MediaMusic with parameters.
     *
     * @param name
     * @param interpreter
     * @param genres
     * @param record_label
     * @param lyrics
     * @param music
     * @param duration
     * @param streams
     * @param video
     */
    public MediaMusic(String name, ArrayList<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration) {
        super(name, interpreter, record_label, genres, lyrics, music, duration);
        this.video=1;
    }
    public MediaMusic(String name, ArrayList<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration, int streams) {
        super(name, interpreter, record_label, genres, lyrics, music, duration, streams);
        this.video=1;
    }

    /**
     * Constructs a new MediaMusic with a pre constructed MediaMusic.
     * 
     * @param MediaMusic
     */
    public MediaMusic(MediaMusic m) {
        super(m);
        this.video = m.getVideo();
    }

    /**
     * Returns the Mediamusic's video.
     *
     * @return video
     */
    public int getVideo() {
        return video;
    }

    /**
     * Checks if this Media music object is equal to another object.
     *
     * @param object the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        MediaMusic music = (MediaMusic) o;
        return this.video == music.video;
    }

    /**
     * Convert the Mediamusic in a String
     * 
     * @param object
     * @return Bool
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Video: ").append(this.video).append('\n');
        return super.toString() + sb.toString();
    }

    /**
     * Gives the hashcode for a Mediamusic
     * 
     * @return int
     */
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + this.video;
        return hash;
    }
}
