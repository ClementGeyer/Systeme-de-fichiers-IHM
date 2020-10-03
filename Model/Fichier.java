/**
 * Cette classe est une classe fille de la classe Chemin, elle permet de définir un Chemin en tant que Fichier
 * @author Clément GEYER
 */
public class Fichier extends Chemin {

    /**
     * L'objet présent dans un fichier
     * @param objet
     */
    private String objet;

    /**
     * Constructeur du fichier prenant en compte un nom, un parent et un objet
     * @param name
     * @param parent
     * @param objet
     */
    public Fichier(String name, Repertoire parent, String objet) {
        this.setName(name);
        this.setParent(parent);
        this.setObjet(objet);
    }

    /**
     * Permet d'obtenir l'objet contenu dans le fichier
     * @return String
     */
    @Override
    public String getObjet() {
        return this.objet;
    }

    /**
     * Permet de définir un objet au fichier
     * @param objet
     */
    @Override
    public void setObjet(String objet) {
        this.objet = objet;
    }

    /**
     * Permet d'obtenir la taille de l'objet
     * @return int
     */
    @Override
    public int getSize(){
        return this.objet.length();
    }
}