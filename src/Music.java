import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
/**
 * Represents a music with a name interpreter, record_label, genres,lyrics, music, duration and streams .
 */
public class Music {
    private String name, interpreter, record_label;
    private HashSet<Genre> genres;
    private ArrayList <String> lyrics, music;
    private int duration, streams;

    /**
     * Constructs a new Music.
     *
     *
     */

    
    public Music() {
        lyrics = new ArrayList<>();
        music = new ArrayList<>();
        genres= new HashSet<>();
        name = new String();
        interpreter = new String();
        record_label = new String();
        duration = 0;
        streams = 0;
    }
    
    
    /**
     * Constructs a new Music with parameters.
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

    public Music(String name, String interpreter, String record_label, HashSet<Genre> genres,
    ArrayList<String> lyrics, ArrayList<String> music, int duration, int streams) {
        this.name = name;
        this.interpreter = interpreter;
        this.record_label = record_label;
        this.duration = duration;
        this.streams = streams;
        this.lyrics = lyrics;
        this.music = music;
        this.genres= new HashSet<>(genres);
    }
    /**
     * Constructs a new Music with a pre constructed Music.
     *@param Music
     * 
     * 
     */
    public Music(Music m) {
        this.name = m.getName();
        this.interpreter = m.getInterpreter();
        this.record_label = m.getRecLab();
        this.genres=m.getGenres();
        this.lyrics = m.getLyrics();
        this.music = m.getMusic();
        this.duration = m.getDuration();
        this.streams = m.getStreams();
    }

    /**
     * clone equals toString
     */

    

    public boolean equals(Object o) {
        if (this == o) return true; // Same object
        if (o == null || getClass() != o.getClass()) return false; // Check class type

        Music music = (Music) o;
        return duration == music.duration &&
                streams == music.streams &&
                Objects.equals(name, music.name) &&
                Objects.equals(interpreter, music.interpreter) &&
                Objects.equals(record_label, music.record_label) &&
                Objects.equals(genero, music.genero) &&
                Objects.equals(lyrics, music.lyrics) &&
                Objects.equals(this.music, music.music);
    }

    public String toString() {
        return  "Music{\n" +
                "name: " + name + '\n' +
                "interpreter: " + interpreter + '\n' +
                "record_label: " + record_label + '\n' +
                "genero: " + genero + '\n' +
                "lyrics: " + lyrics + '\n' +
                "music: " + music + '\n' +
                "duration: " + duration + '\n' +
                "streams: " + streams + '\n' +
                '}';
    }



    

    /**
     * Getters e Setters
     */

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }

    public String getRecLab() {
        return record_label;
    }

    public void setRecLab(String record_label) {
        this.record_label = record_label;
    }

   

 

    public ArrayList <String> getLyrics() {
        return lyrics;
    }

    public void setLyrics(ArrayList <String> lyrics) {
        this.lyrics = lyrics;
    }

    public ArrayList <String> getMusic() {
        return music;
    }

    public void setMusic(ArrayList <String> music) {
        this.music = music;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStreams() {
        return streams;
    }

    public void setStreams(int streams) {
        this.streams = streams;
    }


    



    /**
     * Metodos
     */

}
