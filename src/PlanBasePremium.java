public class PlanBasePremium extends Plan {
    public int getPointsOnJoin() {
        return 0;
    }

    public int getPointsOnStream(User user) {
        return 10;
    }
    
    public boolean allows(MusicCollection p) {
        return true;
    }
}
