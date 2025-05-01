import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a Explicitmusic with a name interpreter, record_label,
 * genres,lyrics,
 * music, duration and streams .
 */
public class ExplicitMusic extends Music {
    /**
     * Constructs a new ExplicitMusic with no parameters.
     *
     *
     */
    public ExplicitMusic() {
        super();
    }

    /**
     * Constructs a new ExplicitMusic with parameters.
     *
     * @param name
     * @param interpreter
     * @param genres
     * @param record_label
     * @param lyrics
     * @param music
     * @param duration
     * @param streams
     * 
     */

    public ExplicitMusic(String name, ArrayList<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration) {
        super(name, interpreter, record_label, genres, lyrics, music, duration);

    }
    

    /**
     * Constructs a new Music with a pre constructed Music.
     * 
     * @param ExplicitMusic
     */
    public ExplicitMusic(ExplicitMusic m) {
        super(m);

    }
}
