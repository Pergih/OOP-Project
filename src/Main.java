public class Main {
    public static void main(String[] args) {
        Musica m1 = new Musica();
        m1.setNome("Bohemian Rhapsody");
        m1.setDuracao(355);

        Musica m2 = new Musica();
        m2.setNome("Bohemian Rhapsody");
        m2.setDuracao(355);

        System.out.println(m1); // Calls toString()
        System.out.println(m1.equals(m2)); // Compares both objects

        Utilizador u1 = new Utilizador();
        u1.setNome("Pedro Mo");
        u1.setMorada("CASA DO PEDRO");
        u1.setEmail("pergihDelas@gmail.com");

        Utilizador u2 = new Utilizador();
        // u2.setNome("Pedro Mo");
        // u2.setMorada("CASA DO PEDRO");
        // u2.setEmail("pergihDelas@gmail.com");
        
        System.out.println(u1); // Calls toString()
        System.out.println(u1.equals(u2)); // Compares both objects

    }
}