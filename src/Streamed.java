import java.time.LocalDateTime;
import java.util.Objects;

public class Streamed {
    private String musicName;
    private LocalDateTime time;


    // Constructors

    public Streamed() {
        this.musicName = new String();
        this.time = LocalDateTime.now();
    }

    public Streamed(String musicName, LocalDateTime time) {
        this.musicName = musicName;
        this.time = time;
    }

    public Streamed(Streamed other) {
        this.musicName = other.musicName;
        this.time = other.time;
    }

    /**
     * Getters e Setters
     */

    public String getMusicName() {
        return musicName;
    }

    public LocalDateTime getTime() {
        return time;
    }


    
    public void setMusicName(String musicName) {
        this.musicName = musicName;
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
        return Objects.equals(musicName, streamed.musicName) &&
               Objects.equals(time, streamed.time);
    }

    public String toString() {
        return "Streamed{" +
                "musicName='" + musicName + '\'' +
                ", time=" + time +
                '}';
    }

}
