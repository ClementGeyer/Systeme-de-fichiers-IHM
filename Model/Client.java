import Vue.*;
import Controller.*;
import Modele.*;

/**
 * Cette classe sert à tester toutes les fonctionnalités proposées par ce système de fichiers
 * @author Clément GEYER
 */
public class Client {

    Repertoire r1, r2;
    Fichier f1, f2, f3, f4;
    Service service;
    View vue;
    Terminal terminal;

    /**
     * Constructeur de la classe client
     */
    public Client(){
        createArborescence();
        createControllers();
        testServices();
    }

    /**
     * Cette fonction sert à créer toutes les instances de controlleurs
     */
    public void createControllers(){
        Fabrique.getInstance();
        service = new Service();
        //vue = new View(Chemin.getRacine());
        terminal = new Terminal(Chemin.getRacine());
    }

    /**
     * Cette fonction permet de créer une arborescence servant à tester le système de fichiers
     */
    public void createArborescence(){
        r1 = Fabrique.getInstance().createRepository("rep1", Chemin.getRacine());
        r2 = Fabrique.getInstance().createRepository("rep2", r1);
        f1 = Fabrique.getInstance().createFile("file1", r1, "ntm");
        f2 = Fabrique.getInstance().createFile("file2", r1, "test");
        f3 = Fabrique.getInstance().createFile("file3", r2, "pas d'inspi");
        f4 = Fabrique.getInstance().createFile("file4", r2, "oue oue oue");
    }

    /**
     * Cette fonction sert à tester les fonctionnalités proposées par la classe Service
     */
    public void testServices(){
        System.out.println("Chemin absolu de rep1 : " + service.getChemin(r1));
        System.out.println("Chemins descendants de rep1 : " + service.getCheminsDesc(r1));
        System.out.println("Chemins descendants de la racine ayant file2 dans le chemin : " + service.getCheminsDescByName("file2"));
        System.out.println("Taille totale de rep1 : " + service.repSize(r1));
    }


    public static void main(String[] args){
        new Client();
    }
}