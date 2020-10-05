package modele;

/**
 * Cette classe est une classe fille de la classe Modele.Chemin, elle permet de définir un Modele.Chemin en tant que Modele.Fichier
 * @author Clément GEYER
 */
public class Fichier extends Chemin {

    private String objet;

    /**
     * Constructeur du fichier prenant en compte un nom, un parent et un objet
     * @param name nom du fichier
     * @param parent parent du fichier
     * @param objet objet du fichier
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
     * @param objet objet à définir
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