public class PlanFree extends Plan {
    public int getPointsOnJoin() {
        return 0;
    }

    public int getPointsOnStream(User user) {
        return 5;
    }

    public boolean allows(MusicCollection p) {
        return !(p instanceof Favorites || p instanceof PrivatePlaylist);
    }
}
