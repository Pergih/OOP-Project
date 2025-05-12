
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
    public MediaMusic(String name, HashSet<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration,int video) {
        super(name, interpreter, record_label, genres, lyrics, music, duration);
        this.video=video;
    }
    public MediaMusic(String name, HashSet<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration, int streams,int video) {
        super(name, interpreter, record_label, genres, lyrics, music, duration, streams);
        this.video=video;
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
    public void setVideo(int i){
        this.video=i;
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
     * Generates a hash code for this music based on name, duration, record
     * label  and video .
     *
     * @return the hash code value
     */
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + this.video;
        return hash;
    }
}
