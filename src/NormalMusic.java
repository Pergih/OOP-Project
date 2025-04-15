import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a Normalmusic with a name interpreter, record_label,
 * genres,lyrics,
 * music, duration and streams .
 */
public class NormalMusic extends Music {
    /**
     * Constructs a new NormalMusic with no parameters.
     *
     *
     */

    public NormalMusic() {
        super();
    }

    /**
     * Constructs a new NormalMusic with parameters.
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
    public NormalMusic(String name, String interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration, int streams) {
        super(name, interpreter, record_label, genres, lyrics, music, duration, streams);

    }

    /**
     * Constructs a new NormalMusic with a pre constructed NormalMusic.
     * 
     * @param Music
     */
    public NormalMusic(NormalMusic m) {
        super(m);

    }
}
