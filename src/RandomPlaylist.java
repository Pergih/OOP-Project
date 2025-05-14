import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPlaylist extends Playlist {

    public RandomPlaylist(String name, User creator, SpotifUM spotifUM, int n) {
        super(name, new ArrayList<>(), creator, false);
        addRandomMusic(spotifUM, n);
    }

    private void addRandomMusic(SpotifUM spotifUM, int n) {
        List<Music> musicPool = new ArrayList<>(spotifUM.getMusics().values());
        if (musicPool.isEmpty()) return;

        Collections.shuffle(musicPool); // Shuffle to get randomness
        int count = Math.min(n, musicPool.size());

        for (int i = 0; i < count; i++) {
            musicList.add(musicPool.get(i));
        }
    }
}
