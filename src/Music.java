import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.Objects;

/**
 * Represents a music with a name interpreter, record_label, genres,lyrics,
 * music, duration and streams .
 */
public abstract class Music implements Serializable {
    private String name, record_label;
    private HashSet<Genre> genres;
    private HashSet<String> interpreter;
    private ArrayList<String> lyrics, music;
    private int duration, streams;

    /**
     * Constructs a new Music with no parameters.
     *
     *
     */

    public Music() {
        lyrics = new ArrayList<>();
        music = new ArrayList<>();
        genres = new HashSet<>();
        name = new String();
        interpreter = new HashSet<>();
        record_label = new String();
        duration = 0;
        streams = 0;
    }

    /**
     * Constructs a new Music with the given parameters.
     *
     * @param name         the name of the music
     * @param interpreter  the set of interpreters
     * @param record_label the record label
     * @param genres       the set of genres
     * @param lyrics       the lyrics of the song
     * @param music        the music notation or representation
     * @param duration     the duration of the song in seconds
     *                     this is for a new music
     */

    public Music(String name, HashSet<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration) {
        this.name = name;
        this.interpreter = interpreter;
        this.record_label = record_label;
        this.duration = duration;
        this.lyrics = lyrics;
        this.music = music;
        this.genres = genres;
        this.streams = 0;
    }

    /**
     * Constructs a new Music with the given parameters.
     *
     * @param name         the name of the music
     * @param interpreter  the set of interpreters
     * @param record_label the record label
     * @param genres       the set of genres
     * @param lyrics       the lyrics of the song
     * @param music        the music notation or representation
     * @param duration     the duration of the song in seconds
     *                     this is for creating a music with more streams
     */
    public Music(String name, HashSet<String> interpreter, String record_label, HashSet<Genre> genres,
            ArrayList<String> lyrics, ArrayList<String> music, int duration, int streams) {
        this.name = name;
        this.interpreter = interpreter;
        this.record_label = record_label;
        this.duration = duration;
        this.streams = streams;
        this.lyrics = lyrics;
        this.music = music;
        this.genres = genres;

    }

    /**
     * Constructs a new Music with a pre constructed Music.
     * 
     * @param Music
     */
    public Music(Music m) {
        this.name = m.getName();
        this.interpreter = m.getInterpreter();
        this.record_label = m.getRecLab();
        this.genres = m.getGenres();
        this.lyrics = m.getLyrics();
        this.music = m.getMusic();
        this.duration = m.getDuration();
        this.streams = m.getStreams();
    }

    /**
     * Checks if this music object is equal to another object.
     *
     * @param object the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o)
            return true; // Same object
        if (o == null || getClass() != o.getClass())
            return false; // Check class type

        Music music = (Music) o;
        return this.duration == music.duration &&
                this.streams == music.streams &&
                Objects.equals(this.genres, music.genres) &&
                Objects.equals(name, music.name) &&
                Objects.equals(interpreter, music.interpreter) &&
                Objects.equals(record_label, music.record_label) &&
                Objects.equals(lyrics, music.lyrics) &&
                Objects.equals(this.music, music.music);
    }

    /**
     * Converts the music object into a string representation.
     *
     * @return a string representing this music
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append('\n');
        sb.append("Interpreter: ").append(this.interpreter).append('\n');
        sb.append("Record Label: ").append(this.record_label).append('\n');
        sb.append("Genres: ").append(this.genres).append('\n');

        sb.append("Lyrics:\n\"\n");
        for (String line : this.lyrics) {
            sb.append(line).append('\n');
        }
        sb.append("\"\nMusic: \n\"\n");
        for (String line : this.music) {
            sb.append(line).append('\n');
        }
        sb.append("\"\n");

        sb.append("Duration: ").append(this.duration).append('\n');
        sb.append("Streams: ").append(this.streams).append('\n');
        return sb.toString();
    }

    /**
     * Convert the lyrics of a song in a string
     * 
     * 
     * @return the lyrics of the song as a string
     */
    public void playLyricString() {
        StringBuilder sb = new StringBuilder();
        for (String line : this.lyrics) {
            sb.append(line).append('\n');
        }
        System.out.println(sb.toString());
    }

    /**
     * Generates a hash code for this music based on name, duration, and record
     * label.
     *
     * @return the hash code value
     */

    public int hashCode() {
        int hash = 12;
        hash = 37 * hash + this.name.hashCode();
        hash = 37 * hash + this.duration;
        hash = 37 * hash + this.record_label.hashCode();
        return hash;
    }

    /**
     * Returns the music's name.
     *
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the music's name.
     *
     * @param name the string of name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the music's interpreter.
     *
     * @return interpreter
     */
    public HashSet<String> getInterpreter() {
        return new HashSet<>(interpreter);
    }

    /**
     * Sets the music's interpreter.
     *
     * @param interpreter the stirng of interpreter to set
     */
    public void setInterpreter(HashSet<String> interpreter) {
        this.interpreter = new HashSet<>(interpreter);
        ;
    }

    /**
     * Returns the music's record label.
     *
     * @return record_label
     */
    public String getRecLab() {
        return record_label;
    }

    /**
     * Sets the music's record label.
     *
     * @param record_label the string of record_label to set
     */
    public void setRecLab(String record_label) {
        this.record_label = record_label;
    }

    /**
     * Returns the music's lyrics.
     *
     * @return lyrics
     */
    public ArrayList<String> getLyrics() {
        return new ArrayList<>(lyrics);
    }

    /**
     * Sets the music's lyrics.
     *
     * @param lyrics the lyrics to set
     */
    public void setLyrics(ArrayList<String> lyrics) {
        this.lyrics = new ArrayList<>(lyrics);
    }

    /**
     * Returns the music's music.
     *
     * @return music
     */
    public ArrayList<String> getMusic() {
        return new ArrayList<>(music);
    }

    /**
     * Sets the music's music.
     *
     * @param music the music to set
     */
    public void setMusic(ArrayList<String> music) {
        this.music = new ArrayList<>(music);
    }

    /**
     * Returns the music's duration.
     *
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the music's duration.
     *
     * @param duration the number to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the music's streams.
     *
     * @return streams
     */
    public int getStreams() {
        return streams;
    }

    /**
     * Sets the music's streams.
     *
     * @param streams the number to set
     */
    public void setStreams(int streams) {
        this.streams = streams;
    }

    /**
     * Returns the music's genres.
     *
     * @return genres
     */
    public HashSet<Genre> getGenres() {
        return new HashSet<>(genres);

    }

    /**
     * Sets the music's genres.
     *
     * @param genres the genres to set
     */
    public void setGenres(HashSet<Genre> genres) {
        this.genres = new HashSet<>(genres);

    }

    /**
     * Plays the music by printing its lyrics and incrementing the stream count.
     */

    public void playMusic() {
        this.streams += 1;
        this.playLyricString();
    }
}
