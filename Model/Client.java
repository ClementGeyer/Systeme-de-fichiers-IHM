
import java.io.IOException;
import java.util.*;

/**
 * 
 */
public class Client {

    /**
     * Default constructor
     */
    public Client() throws Exception {
        Fabrique.createFabrique();
        Repertoire r1 = Fabrique.createRepository("rep1", Chemin.getRacine());
        Repertoire r2 = Fabrique.createRepository("rep2", r1);
        Fichier f1 = Fabrique.createFile("file1", r1, "ntm");
        Fichier f2 = Fabrique.createFile("file2", r1, "test");
        Fichier f3 = Fabrique.createFile("file3", r2, "pas d'inspi");
        Fichier f4 = Fabrique.createFile("file4", r2, "oue oue oue");
        r1.addChild(f1);
        r1.addChild(f2);
        r2.addChild(f3);
        r2.addChild(f4);
        r1.addChild(r2);
        Chemin.getRacine().addChild(r1);
        Service s = new Service();
        System.out.println(s.getCheminsDesc(r2));
        Component c = new Component();
        View v = new View(c);
    }


    public static void main(String[] args) throws Exception {
        new Client();
    }
}