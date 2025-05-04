public class PlanAdmin extends Plan {
    public int getPointsOnJoin() {
        return 0;
    }

    public int getPointsOnStream(User user) {
        return 0;
    }

    public boolean allows(Class<? extends MusicCollection> collectionType) {
        return true;
    }
}
