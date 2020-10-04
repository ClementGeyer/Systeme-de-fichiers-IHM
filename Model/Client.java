
import Controller.Component;
import Controller.Fabrique;
import Controller.Service;
import Modele.Chemin;
import Modele.Fichier;
import Modele.Repertoire;
import Vue.*;

/**
 * Cette classe sert à tester toutes les fonctionnalités proposées par ce système de fichiers
 * @author Clément GEYER
 */
public class Client {

    Repertoire r1, r2;
    Fichier f1, f2, f3, f4;
    Service service;
    Component component;
    View vue;

    /**
     * Constructeur de la classe client
     */
    public Client() throws Exception {
        createArborescence();
        createControllers();
        testServices();
    }

    /**
     * Cette fonction sert à créer toutes les instances de controlleurs
     */
    public void createControllers(){
        Fabrique.createFabrique();
        service = new Service();
        component = new Component();
        vue = new View(component);
    }

    /**
     * Cette fonction permet de créer une arborescence servant à tester le système de fichiers
     * @throws Exception
     */
    public void createArborescence() throws Exception {
        r1 = Fabrique.createRepository("rep1", Chemin.getRacine());
        r2 = Fabrique.createRepository("rep2", r1);
        f1 = Fabrique.createFile("file1", r1, "ntm");
        f2 = Fabrique.createFile("file2", r1, "test");
        f3 = Fabrique.createFile("file3", r2, "pas d'inspi");
        f4 = Fabrique.createFile("file4", r2, "oue oue oue");
        r1.addChild(f1);
        r1.addChild(f2);
        r2.addChild(f3);
        r2.addChild(f4);
        r1.addChild(r2);
        Chemin.getRacine().addChild(r1);
    }

    /**
     * Cette fonction sert à tester les fonctionnalités proposées par la classe Service
     * @throws Exception
     */
    public void testServices() throws Exception {
        System.out.println("Chemin absolu de rep1 : " + service.getChemin(r1));
        System.out.println("Chemins descendants de rep1 : " + service.getCheminsDesc(r1));
        System.out.println("Chemins descendants de la racine ayant file2 dans le chemin : " + service.getCheminsDescByName("file2"));
        System.out.println("Taille totale de rep1 : " + service.repSize(r1));
    }


    public static void main(String[] args) throws Exception {
        new Client();
    }
}