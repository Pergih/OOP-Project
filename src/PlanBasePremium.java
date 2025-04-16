class PlanFree implements Plan {
    public boolean allows(Playlist p) {
        // por o resto qnd tiver
        return !(p instanceof Favorites || p instanceof PrivatePlaylist || p instanceof PublicPlaylist);
    }
}