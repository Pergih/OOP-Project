import java.util.*;

public class Favorites extends Playlist {

    public Favorites(String name, User creator, SpotifUM spotifUM) {
        super(name, new ArrayList<>(), creator);

        Map<Genre, Integer> genreListeningStats = creator.buildGenreStats(spotifUM);

        // Sort genres by most listened
        List<Map.Entry<Genre, Integer>> sortedGenres = new ArrayList<>(genreListeningStats.entrySet());
        sortedGenres.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // just gets all the songs with the most listened genre to the least
        Set<Music> added = new HashSet<>();
        for (Map.Entry<Genre, Integer> entry : sortedGenres) {
            Genre genre = entry.getKey();

            for (Music music : spotifUM.getMusics().values()) {
                if (music.getGenres().contains(genre) && !added.contains(music)) {
                    this.addMusic(music);
                    added.add(music);

                    // limit playlist size
                    if (this.musicList.size() >= 30)
                        return;
                }
            }
        }
    }
}
