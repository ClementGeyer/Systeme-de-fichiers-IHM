package Vue;

import Modele.Chemin;
import Modele.Repertoire;

import javax.swing.*;

/**
 * Cette classe sert à afficher graphiquement l'arborescence à l'aide d'une fenêtre graphique
 * @author Clément GEYER
 */
public class View extends JFrame{

    public View(Chemin c){
        add(new JTree(c));
        setTitle("Système de fichier");
        setSize( 500,400 );
        setLocation(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true );
    }

    public void addJTree(Repertoire racine){
        add(new JTree(racine));
    }
}
