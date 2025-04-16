interface Plan {
    // interface fica mais simples
    boolean allows(Playlist playlist);
}

// Caso quiserem por como abstract

// public abstract class Plan {
//     public abstract boolean allows(Playlist playlist);
// }