package Modele;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe permet de créer un chemin, qui ensuite peut être instancier en fichier ou répertoire
 * @author Clément GEYER
 */
public abstract class Chemin implements Serializable, TreeModel {

    /**
     * Nom du chemin
     *
     * @param name
     */
    private String name;

    /**
     * Répertoire parent
     *
     * @param parent
     */
    private Repertoire parent;

    /**
     * Racine du système de fichier : static
     *
     * @param racine
     */
    private static final Repertoire racine = new Repertoire("/");

    /**
     * Permet d'obtenir la taille du chemin
     *
     * @return int
     */
    public abstract int getSize();

    /**
     * Permet de définir un parent au chemin
     *
     * @param parent
     */
    public void setParent(Repertoire parent) {
        this.parent = parent;
    }

    ;

    /**
     * Permet d'obtenir le nom du chemin
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    ;

    /**
     * Permet de définir le nom du chemin
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    ;

    /**
     * Permet d'obtenir le répertoire parent du chemin
     *
     * @return Modele.Repertoire
     */
    public Repertoire getParent() {
        return this.parent;
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param ch
     * @throws IllegalArgumentException
     */
    public void addChild(Chemin ch) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param ch
     * @throws IllegalArgumentException
     */
    public void removeChild(Chemin ch) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @return ArrayList<Modele.Chemin>
     * @throws IllegalArgumentException
     */
    public ArrayList<Chemin> getChilds() throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param childs
     * @throws IllegalArgumentException
     */
    public void setChilds(ArrayList<Chemin> childs) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @return String
     * @throws IllegalArgumentException
     */
    public String getObjet() throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a file");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param objet
     * @throws IllegalArgumentException
     */
    public void setObjet(String objet) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a file");
    }

    /**
     * Permet d'obtenir le racine du système de fichier
     *
     * @return Modele.Repertoire
     */
    public static Repertoire getRacine() {
        return racine;
    }

    /**
     * Permet de savoir si le chemin passé en paramètre est la racine du système de fichier
     *
     * @param ch
     * @return boolean
     */
    public static boolean isRacine(Chemin ch) {
        if (ch.getName().equals("/")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permet d'obtenir la racine du système de fichiers
     *
     * @return Modele.Chemin
     */
    @Override
    public Chemin getRoot() {
        return Chemin.getRacine();
    }

    /**
     * Permet de récupérer un chemin enfant par recherche dans la liste d'enfants du parent
     *
     * @param parent
     * @param index
     * @return Modele.Chemin
     */
    @Override
    public Chemin getChild(Object parent, int index) {
        if (parent instanceof Repertoire) {
            return ((Repertoire) parent).getChilds().get(index);
        } else {
            return null;
        }
    }

    /**
     * Permet d'obtenir le nombre d'enfants d'un répertoire parent
     *
     * @param parent
     * @return int
     */
    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Repertoire) {
            return ((Repertoire) parent).getChilds().size();
        } else {
            return 0;
        }
    }

    /**
     * Permet de savoir si l'objet passé en paramètre est un fichier
     *
     * @param node
     * @return boolean
     */
    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Fichier;
    }

    /**
     * Permet de savoir quel est l'index de l'enfant passé en paramètre dans la liste du parent
     *
     * @param parent
     * @param child
     * @return int
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof Repertoire && child instanceof Chemin) {
            return ((Repertoire) parent).getChilds().indexOf(child);
        } else {
            return 0;
        }
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) { }

    @Override
    public void addTreeModelListener(TreeModelListener l) { }

    @Override
    public void removeTreeModelListener(TreeModelListener l) { }
}