package Controller;

import Modele.Chemin;
import Modele.Fichier;
import Modele.Repertoire;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Cette classe rédéfinie les méthodes de TreeModel en fonction des objets crées par ce système de fichier
 * @author Clément GEYER
 */
public class Component implements TreeModel {

    /**
     * Constructeur de la classe Controller.Component
     */
    public Component(){}

    /**
     * Permet d'obtenir la racine du système de fichiers
     * @return Modele.Chemin
     */
    @Override
    public Chemin getRoot() {
        return Chemin.getRacine();
    }

    /**
     * Permet de récupérer un chemin enfant par recherche dans la liste d'enfants du parent
     * @param parent
     * @param index
     * @return Modele.Chemin
     */
    @Override
    public Chemin getChild(Object parent, int index){
        if(parent instanceof Repertoire){
            return ((Repertoire) parent).getChilds().get(index);
        }
        else{
            return null;
        }
    }

    /**
     * Permet d'obtenir le nombre d'enfants d'un répertoire parent
     * @param parent
     * @return int
     */
    @Override
    public int getChildCount(Object parent) {
        if(parent instanceof Repertoire){
            return ((Repertoire) parent).getChilds().size();
        }
        else{
            return 0;
        }
    }

    /**
     * Permet de savoir si l'objet passé en paramètre est un fichier
     * @param node
     * @return boolean
     */
    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Fichier;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}

    /**
     * Permet de savoir quel est l'index de l'enfant passé en paramètre dans la liste du parent
     * @param parent
     * @param child
     * @return int
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent instanceof Repertoire && child instanceof Chemin){
            return ((Repertoire) parent).getChilds().indexOf(child);
        }
        else {
            return 0;
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {}

    @Override
    public void removeTreeModelListener(TreeModelListener l) {}
}
