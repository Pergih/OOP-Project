import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String name, email, address;
    private Plan plan;
    private ArrayList<MusicCollection> library;
    private ArrayList<Streamed> history;
    private int points;

    /**
     * Construtores
     */

    public User() {
        name = new String();
        email = new String();
        address = new String();
        plan = null; // (To Do) Default is freePlan
        points = 0;
        library = new ArrayList<MusicCollection>();
        history = new ArrayList<Streamed>();
    }

    public User(String name, String email, String address, Plan plan, Library library, ArrayList<Streamed> history, int points) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.plan = plan;
        this.library = library;
        this.history = new ArrayList<>(history);
        this.points = points;
    }

    public User(User other) {
        this.name = other.name;
        this.email = other.email;
        this.address = other.address;
        this.plan = other.plan; // shallow copy (assumes Plan is immutable or safely shared)
        this.library = other.library; // same as above
        this.history = new ArrayList<>(other.history);
        this.points = other.points;
    }

    /**
     * Getters e Setters
     */

     public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Plan getPlan() {
        return plan;
    }

    public Library getLibrary() {
        return library;
    }

    public ArrayList<Streamed> getHistory() {
        return new ArrayList<>(history);
    }

    public int getPoints() {
        return points;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setHistory(ArrayList<Streamed> history) {
        this.history = new ArrayList<>(history);
    }

    public void setPoints(int points) {
        this.points = points;
    }


    /**
     * clone equals toString
     */

     public User clone() {
        return new User(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return points == user.points &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                Objects.equals(plan, user.plan) &&
                Objects.equals(library, user.library) &&
                Objects.equals(history, user.history);
    }

    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", plan=" + plan +
                ", library=" + library +
                ", history=" + history +
                ", points=" + points +
                '}';
    }

    /**
     * Metodos
     */


    // example for now
    public void addPlaylist(Playlist playlist) {
        if (plan.allows(playlist)) {
            library.addPlaylist(playlist);
        } else {
            // maybe its not SecurityException we have other ones
            throw new SecurityException("This playlist type is not allowed for your plan");
        }
    }

}
