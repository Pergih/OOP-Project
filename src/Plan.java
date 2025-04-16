public abstract class Plan {
    public abstract int getPointsOnJoin();
    public abstract int getPointsOnStream(User user); // depends on user for PremiumTop
    public abstract boolean allows(MusicCollection playlist);
}
