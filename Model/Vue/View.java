package Vue;

import Controller.*;

import javax.swing.*;

/**
 * Cette classe sert à afficher graphiquement l'arborescence à l'aide d'une fenêtre graphique
 * @author Clément GEYER
 */
public class View extends JFrame{

    private Component component;

    public View(Component c){
        this.component = c;
        add(new JTree(this.component));
        setTitle("Système de fichier");
        setSize( 500,400 );
        setLocation(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true );
    }
}
