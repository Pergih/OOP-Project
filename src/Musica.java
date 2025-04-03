import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Musica {
    private String nome, interprete, editora, genero;
    private ArrayList <String> letra, musica;
    private int duracao, streams;

    /**
     * Construtores
     */
    
    public Musica() {
        letra = new ArrayList<>();
        musica = new ArrayList<>();
        nome = new String();
        interprete = new String();
        editora = new String();
        genero = new String();
        duracao = 0;
        streams = 0;
    }

    public Musica(String nome, String interprete, String genero, String editora, 
    ArrayList<String> letra, ArrayList<String> musica, int duracao, int streams) {
        this.nome = nome;
        this.interprete = interprete;
        this.genero = genero;
        this.editora = editora;
        this.duracao = duracao;
        this.streams = streams;
        this.letra = letra;
        this.musica = musica;
    }

    public Musica(Musica m) {
        this.nome = m.getNome();
        this.interprete = m.getInterprete();
        this.editora = m.getEditora();
        this.genero = m.getGenero();
        this.letra = m.getLetra();
        this.musica = m.getMusica();
        this.duracao = m.getDuracao();
        this.streams = m.getStreams();
    }

    /**
     * clone equals toString
     */

    public Musica clone() {
        return new Musica(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true; // Same object
        if (o == null || getClass() != o.getClass()) return false; // Check class type

        Musica musica = (Musica) o;
        return duracao == musica.duracao &&
                streams == musica.streams &&
                Objects.equals(nome, musica.nome) &&
                Objects.equals(interprete, musica.interprete) &&
                Objects.equals(editora, musica.editora) &&
                Objects.equals(genero, musica.genero) &&
                Objects.equals(letra, musica.letra) &&
                Objects.equals(this.musica, musica.musica);
    }

    public String toString() {
        return  "Musica{\n" +
                "nome: " + nome + '\n' +
                "interprete: " + interprete + '\n' +
                "editora: " + editora + '\n' +
                "genero: " + genero + '\n' +
                "letra: " + letra + '\n' +
                "musica: " + musica + '\n' +
                "duracao: " + duracao + '\n' +
                "streams: " + streams + '\n' +
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

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ArrayList <String> getLetra() {
        return letra;
    }

    public void setLetra(ArrayList <String> letra) {
        this.letra = letra;
    }

    public ArrayList <String> getMusica() {
        return musica;
    }

    public void setMusica(ArrayList <String> musica) {
        this.musica = musica;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getStreams() {
        return streams;
    }

    public void setStreams(int streams) {
        this.streams = streams;
    }


    



    /**
     * Metodos
     */

}
