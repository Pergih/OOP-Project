import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Streamed implements Serializable{
    private Music music;
    private LocalDateTime time;


    // Constructors

    public Streamed() {
        this.music = new NormalMusic();
        this.time = LocalDateTime.now();
    }

    public Streamed(Music music, LocalDateTime time) {
        this.music = music;
        this.time = time;
    }

    public Streamed(Streamed other) {
        this.music = other.music;
        this.time = other.time;
    }

    /**
     * Getters e Setters
     */

    public Music getMusic() {
        return music;
    }

    public LocalDateTime getTime() {
        return time;
    }


    
    public void setMusic(Music music) {
        this.music = music;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * clone equals toString
     */

    public Streamed clone() {
        return new Streamed(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Streamed streamed = (Streamed) o;
        return Objects.equals(music, streamed.music) &&
               Objects.equals(time, streamed.time);
    }

    public String toString() {
        return "Streamed{" +
                "musicName='" + music + '\'' +
                ", time=" + time +
                '}';
    }

}
