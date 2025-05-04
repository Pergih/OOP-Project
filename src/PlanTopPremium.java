public class PlanTopPremium extends Plan {
    public int getPointsOnJoin() {
        return 100;
    }

    public int getPointsOnStream(User user) {
        return (int)(user.getPoints() * 0.025);
    }

    public boolean allows(Class<? extends MusicCollection> collectionType) {
        return true;
    }
}
