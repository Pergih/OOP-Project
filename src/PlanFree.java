public class PlanFree extends Plan {
    public int getPointsOnJoin() {
        return 0;
    }

    public int getPointsOnStream(User user) {
        return 5;
    }

    public boolean allows(Class<? extends MusicCollection> collectionType) {
        return !(collectionType == Favorites.class || collectionType == PrivatePlaylist.class);
    }
    
}
