import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.*;

public class User implements Serializable{
    private String handle, name, email, address;
    private Plan plan;
    private ArrayList<MusicCollection> library;
    private ArrayList<Streamed> history;
    private int points;

    /**
     * Construtores
     */

    public User() {
        handle = new String();
        name = new String();
        email = new String();
        address = new String();
        plan = new PlanFree(); // Default is freePlan
        library = new ArrayList<MusicCollection>();
        history = new ArrayList<Streamed>();
        points = 0;
    }

    public User(String handle, String name, String email, String address, Plan plan) {
        this.handle = handle;
        this.name = name;
        this.email = email;
        this.address = address;
        this.plan = plan;
        library = new ArrayList<MusicCollection>();
        history = new ArrayList<Streamed>();
        this.points = 0;
    }

    public User(User other) {
        this.handle = other.getHandle();
        this.name = other.getName();
        this.email = other.getEmail();
        this.address = other.getAddress();
        this.plan = other.getPlan(); // shallow copy (assumes Plan is immutable or safely shared)
        this.library = new ArrayList<>(other.library); // same as above
        this.history = new ArrayList<>(other.history);
        this.points = other.points;
    }

    /**
     * Getters e Setters
     */


    public String getHandle() {
        return handle;
    }
    
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

    public ArrayList<MusicCollection> getLibrary() {
        return new ArrayList<>(library);
    }

    public ArrayList<Streamed> getHistory() {
        return new ArrayList<>(history);
    }

    public int getPoints() {
        return points;
    }
    public MusicCollection getFromLibrary(String wanted) {
        for (MusicCollection mc : library) {
            if (mc.getName().equalsIgnoreCase(wanted)) {
                return mc;
            }
        }
        return null; // não encontrado
    }
    // Setters

    public void setHandle(String handle) {
        this.handle = handle;
    }

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
        this.points += plan.getPointsOnJoin();
        this.plan = plan;
    }

    public void setLibrary(ArrayList<MusicCollection> library) {
        this.library = new ArrayList<>(library);
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
                "handle='" + handle + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", plan=" + plan +
                ", library=" + library +
                ", history=" + history +
                ", points=" + points +
                '}';
    }
    public String historyToString(){
        StringBuilder sb = new StringBuilder();
        for(int i=history.size()-1;i >= 0; i--){
            sb.append(history.get(i)).append("\n");
        }
        
        return sb.toString();
    }
    public String libraryToString() {
        StringBuilder sb = new StringBuilder();
        for (MusicCollection mc : library) {
            String type = mc.getClass().getSimpleName(); // Gets "Album", "PublicPlaylist", etc.
            sb.append("[").append(type).append("] ").append(mc.getName()).append("\n");
        }
        return sb.toString();
    }
    

    /**
     * Metodos
     */


    // example for now
    public void addPlaylist(MusicCollection playlist) {
        if (plan.allows(playlist.getClass())) {
            library.add(playlist);
        } else {
            // maybe its not SecurityException we have other ones
            throw new SecurityException("This playlist type is not allowed for your plan");
        }
    }

    public void play(Music music) {
        //making the music count++
        music.playMusic();

        // adding the points
        this.points += plan.getPointsOnStream(this);;

        // adding to history
        Streamed stream = new Streamed(music.getName(), LocalDateTime.now());
        history.add(stream);
    }

    public List<String> getLastNMusicNames(int n) {
        List<String> names = new ArrayList<>();
        int start = Math.max(0, history.size() - n);

        for (int i = start; i < history.size(); i++) {
            names.add(history.get(i).getMusicName());
        }
        return names;
    }

    public List<String> getMusicBetween(LocalDateTime start, LocalDateTime end) {
        List<String> names = new ArrayList<>();
    
        for (Streamed s : history) {
            LocalDateTime time = s.getTime();
            if ((start == null || !time.isBefore(start)) && (end == null || !time.isAfter(end))) {
                names.add(s.getMusicName());
            }
        }
        return names;
    }
    public Boolean canSkip(){
        return true;
    }
    public void addToLibrary(MusicCollection collection) {
        library.add(collection);
    }
    
}
