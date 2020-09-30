
import java.io.IOException;
import java.util.*;

/**
 * 
 */
public class Client {

    /**
     * Default constructor
     */
    public Client(){
        Repertoire r1 = new Repertoire("rep1", Chemin.getRacine());
        Repertoire r2 = new Repertoire("rep2", r1);
        Fichier f1 = new Fichier("file1", r1, "ntm");
        Fichier f2 = new Fichier("file2", r1, "test");
        Fichier f3 = new Fichier("file3", r2, "pas d'inspi");
        Fichier f4 = new Fichier("file4", r2, "oue oue oue");
        r1.addChild(f1);
        r1.addChild(f2);
        r2.addChild(f3);
        r2.addChild(f4);
        r1.addChild(r2);
        Chemin.getRacine().addChild(r1);
        Service s = new Service();
        //s.serialisation(r2);
        Chemin ch = s.deserialisation();
        System.out.println(ch.getName());
    }


    public static void main(String[] args) throws IOException {
        new Client();
    }
}