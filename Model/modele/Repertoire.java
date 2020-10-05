package modele;

import java.util.*;

/**
 * Cette classe est une classe fille de la classe Modele.Chemin, elle permet de définir un Modele.Chemin en tant que Répertoire
 * @author Clément GEYER
 */
public class Repertoire extends Chemin {

    private ArrayList<Chemin> childs = new ArrayList<>();

    /**
     * Constructeur du fichier prenant en compte un nom et un parent
     * @param name nom du répertoire
     * @param parent parent du répertoire
     */
    public Repertoire(String name, Repertoire parent) {
        this.setName(name);
        this.setParent(parent);
    }

    /**
     * Surcharge du constructeur permettant de créer le répertoire racine sans ajouter de parent
     * @param name nom à définir
     */
    public Repertoire(String name){
        this.setName(name);
    }

    /**
     * Permet d'ajouter un enfant a la liste d'enfants du répertoire
     * @param ch Chemin enfant à ajouter au répertoire
     */
    @Override
    public void addChild(Chemin ch) {
        childs.add(ch);
    }

    /**
     * Permet de supprimer un enfant de la liste d'enfants du répertoire
     * @param ch Chemin enfant à supprimer au répertoire
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
     * @param childs Liste d'enfants à définir au répertoire
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
        return 4096;
    }
}