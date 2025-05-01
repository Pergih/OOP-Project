import java.util.ArrayList;
import java.util.List;

public class RandomPlaylist extends Playlist {

    public RandomPlaylist(String name, User creator, SpotifUM spotifUM, int n) {
        super(name, new ArrayList<>(), creator);
        addRandomMusic(spotifUM, n);
    }

    public void addRandomMusic(SpotifUM spotifUM) {
        List<Music> musicPool = new ArrayList<>(spotifUM.getMusics().values());
        if (musicPool.isEmpty())
            return;

        int randomIndex = (int) (Math.random() * musicPool.size());
        Music randomMusic = musicPool.get(randomIndex);

        musicList.add(randomMusic);
    }

    public void addRandomMusic(SpotifUM spotifUM, int n) {
        List<Music> musicPool = new ArrayList<>(spotifUM.getMusics().values());
        if (musicPool.isEmpty())
            return;

        for (int i = 0; i < n; i++) {
            int randomIndex = (int) (Math.random() * musicPool.size());
            Music randomMusic = musicPool.get(randomIndex);
            musicList.add(randomMusic);
        }
    }

}
