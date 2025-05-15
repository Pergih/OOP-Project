public class PlanBasePremium extends Plan {
    public int getPointsOnJoin() {
        return 0;
    }

    public int getPointsOnStream(User user) {
        return 10;
    }
    
    public boolean allows(Class<? extends MusicCollection> collectionType) {
        return (collectionType == Favorites.class);
    }
}
