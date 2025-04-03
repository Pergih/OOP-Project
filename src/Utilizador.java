import java.util.ArrayList;
import java.util.Objects;

public class Utilizador {
    private String nome, email, morada, planoSub;
    private ArrayList<Playlist> playlists;
    private int pontos;

    /**
     * Construtores
     */

    public Utilizador() {
        playlists = new ArrayList<>();
        nome = new String();
        email = new String();
        morada = new String();
        planoSub = "Free";
        pontos = 0;
    }

    public Utilizador(String nome, String email, String morada, String planoSub,
                      ArrayList<Playlist> playlists, int pontos) {
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.planoSub = planoSub;
        this.playlists = playlists;
        this.pontos = pontos;

    }

    public Utilizador(Utilizador u) {
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.morada = u.getMorada();
        this.planoSub = u.getPlanoSub();
        this.playlists = u.getPlaylists();
        this.pontos = u.getPontos();
    }

    /**
     * clone equals toString
     */

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true; // Same object
        if (o == null || getClass() != o.getClass())
            return false; // Check class type

        Utilizador utilizador = (Utilizador) o;
        return pontos == utilizador.pontos &&
                Objects.equals(nome, utilizador.nome) &&
                Objects.equals(email, utilizador.email) &&
                Objects.equals(morada, utilizador.morada) &&
                Objects.equals(planoSub, utilizador.planoSub) &&
                Objects.equals(this.playlists, utilizador.playlists);
    }

    public String toString() {
        return "Utilizador{\n" + "nome: " + nome + '\n' +
                "email: " + email + '\n' +
                "morada: " + morada + '\n' +
                "plano: " + planoSub + '\n' +
                "playlists: " + playlists + '\n' +
                "pontos: " + pontos + '\n' +
                '}';
    }

    /**
     * Getters e Setters
     */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getPlanoSub() {
        return planoSub;
    }

    public void setPlanoSub(String planoSub) {
        this.planoSub = planoSub;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    /**
     * Metodos
     */

}
