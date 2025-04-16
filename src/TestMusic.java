import java.util.ArrayList;
import java.util.HashSet;

public class TestMusic {
    public static void main(String[] args) {
        HashSet<Genre> genres1 = new HashSet<>();
        genres1.add(Genre.ROCK);
        genres1.add(Genre.CLASSICAL);
        HashSet<Genre> genres2 = new HashSet<>();
        genres2.add(Genre.POP);
        genres2.add(Genre.CLASSICAL);



        ArrayList<String> lyrics1 = new ArrayList<>();
        lyrics1.add("Is this the real life?");
        lyrics1.add("Is this just fantasy?");
        ArrayList<String> lyrics2 = new ArrayList<>();
        lyrics2.add("Ca em cima ta o tiro liro liro");
        lyrics2.add("Cá em baixo ta o tiro tiro lao");


        ArrayList<String> musicLines1 = new ArrayList<>();
        musicLines1.add("G D Em");
        musicLines1.add("C G Am D7");

        ArrayList<String> musicLines2 = new ArrayList<>();
        musicLines2.add("G D Em");
        musicLines2.add("C G Am D7");

        Music m1 = new MediaMusic();
        m1.setName("Bohemian Rhapsody");
        m1.setDuration(355);
        m1.setInterpreter("pedro Mo");
        m1.setRecLab("Pedro Mo Productions");
        m1.setGenres(genres1);
        m1.setLyrics(lyrics2);
        m1.setMusic(musicLines1);
        m1.setStreams(2);

        Music m2 = new MediaMusic("Bohemian Rhapsody", "pedro Mo", "Pedro Mo Productions", genres1, lyrics1, musicLines1, 355, 2);
        // m2.setName("Bohemian Rhapsody");
        // m2.setInterpreter("pedro Mo");
        // m2.setRecLab("Pedro Mo Productions");
        // m2.setDuration(355);
        // m2.setGenres(genres2);
        // m2.setLyrics(lyrics2);
        // m2.setMusic(musicLines2);

        System.out.println(m1); // Calls toString()
        System.out.println(m1.equals(m2)); // Compares both objects

        

        
        
        System.out.println(m2); // Calls toString()
        m2.playMusic();
        System.out.println(m2);
        

    }
}
