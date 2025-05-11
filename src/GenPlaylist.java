import java.util.*;
public class GenPlaylist extends Playlist {

    public GenPlaylist(String name, User creator, SpotifUM spotifUM, Genre genre, int duration) {
        super(name, new ArrayList<>(), creator);

        //get random songs with genre
        List<Music> allMusics = new ArrayList<>(spotifUM.getMusics().values());
        Collections.shuffle(allMusics);
        int totalDuration = 0;
        for (Music music : allMusics) {
            if (music.getGenres().contains(genre) && !this.musicList.contains(music)) {
                this.addMusic(music);
                totalDuration += music.getDuration();
            }
            if (totalDuration >= duration * 0.92) {
                return;
            }
        }

    }

    public GenPlaylist(String name, User creator, SpotifUM spotifUM, ArrayList<Genre> genres, int duration) {
        super(name, new ArrayList<>(), creator);
        //get random songs with genre
        List<Music> allMusics = new ArrayList<>(spotifUM.getMusics().values());
        Collections.shuffle(allMusics);
        int totalDuration = 0;
        for (Music music : allMusics) {
            for (Genre genre : genres) {
                if (music.getGenres().contains(genre) && !this.musicList.contains(music)) {
                    this.addMusic(music);
                    totalDuration += music.getDuration();
                }
            }
            if (totalDuration >= duration * 0.92) {
                return;
            }
        }

    }
    
}
