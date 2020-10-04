package Modele;

import java.util.*;

/**
 * Cette classe est une classe fille de la classe Modele.Chemin, elle permet de définir un Modele.Chemin en tant que Répertoire
 * @author Clément GEYER
 */
public class Repertoire extends Chemin {

    /**
     * Liste d'enfants du répertoire
     * @param childs
     */
    private ArrayList<Chemin> childs = new ArrayList();

    /**
     * Taille du répertoire
     * @param size
     */
    private final int size = 4096;

    /**
     * Constructeur du fichier prenant en compte un nom et un parent
     * @param name
     * @param parent
     */
    public Repertoire(String name, Repertoire parent) {
        this.setName(name);
        this.setParent(parent);
    }

    /**
     * Surcharge du constructeur permettant de créer le répertoire racine sans ajouter de parent
     * @param name
     */
    public Repertoire(String name){
        this.setName(name);
    }

    /**
     * Permet d'ajouter un enfant a la liste d'enfants du répertoire
     * @param ch
     */
    @Override
    public void addChild(Chemin ch) {
        childs.add(ch);
    }

    /**
     * Permet de supprimer un enfant de la liste d'enfants du répertoire
     * @param ch
     */
    @Override
    public void removeChild(Chemin ch) {
        childs.remove(ch);
    }

    /**
     * Permet d'obtenir la liste des enfants du répertoire
     * @return ArrayList<Modele.Chemin>
     */
    @Override
    public ArrayList<Chemin> getChilds() {
        return this.childs;
    }

    /**
     * Permet de définir une liste d'enfants sur un répertoire
     * @param childs
     */
    @Override
    public void setChilds(ArrayList<Chemin> childs) {
        this.childs = childs;
    }

    /**
     * Permet d'obtenir la taille d'un répertoire
     * @return int
     */
    @Override
    public int getSize() {
        return this.size;
    }
}