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

    private String name;
    private Repertoire parent;
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
     * @param parent parent du Chemin
     */
    public void setParent(Repertoire parent) {
        this.parent = parent;
    }

    /**
     * Permet d'obtenir le nom du chemin
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Permet de définir le nom du chemin
     *
     * @param name nom du Chemin
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @param ch Chemin à ajouter en enfant
     * @throws IllegalArgumentException le Chemin n'est pas un répertoire
     */
    public void addChild(Chemin ch) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param ch Chemin à enlever en enfant
     * @throws IllegalArgumentException le Chemin n'est pas un répertoire
     */
    public void removeChild(Chemin ch) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @return ArrayList<Modele.Chemin>
     * @throws IllegalArgumentException le Chemin n'est pas un répertoire
     */
    public ArrayList<Chemin> getChilds() throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param childs liste d'enfants
     * @throws IllegalArgumentException le Chemin n'est pas un répertoire
     */
    public void setChilds(ArrayList<Chemin> childs) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a repository");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @return String
     * @throws IllegalArgumentException le Chemin n'est pas un fichier
     */
    public String getObjet() throws IllegalArgumentException {
        throw new IllegalArgumentException("Not a file");
    }

    /**
     * Lance une exception si la méthode n'est pas redéfinie
     *
     * @param objet objet contenu dans un fichier
     * @throws IllegalArgumentException le Chemin n'est pas un fichier
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
     * @param ch Chemin à vérifier
     * @return boolean
     */
    public static boolean isRacine(Chemin ch) {
        return ch.getName().equals("/");
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
     * @param parent parent du Chemin
     * @param index index dans la liste d'enfants du parent
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
     * @param parent parent du Chemin
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
     * Permet de savoir si l'objet passé en paramètre est une feuille (fichier ou répertoire sans enfants)
     *
     * @param node objet à vérifier en tant que feuille
     * @return boolean
     */
    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Fichier;
    }

    /**
     * Permet de savoir quel est l'index de l'enfant passé en paramètre dans la liste du parent
     *
     * @param parent Chemin parent
     * @param child enfant à vérifier
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

    /**
     * Permet de récupérer uniquement le nom du chemin et non la référence de l'objet quand on appelle la fonction toString()
     *
     * @return String
     */
    @Override
    public String toString(){
        return getName();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) { }

    @Override
    public void addTreeModelListener(TreeModelListener l) { }

    @Override
    public void removeTreeModelListener(TreeModelListener l) { }
}