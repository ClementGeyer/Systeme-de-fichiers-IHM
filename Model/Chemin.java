import java.io.Serializable;

/**
 * Cette classe permet de créer un chemin, qui ensuite peut être instancier en fichier ou répertoire
 * @author Clément GEYER
 */
public abstract class Chemin implements Serializable {

    /**
     * Nom du chemin
     * @param name
     */
    private String name;

    /**
     * Répertoire parent
     * @param parent
     */
    private Repertoire parent;   

    /**
     * Racine du système de fichier : static
     * @param racine
     */
    private static final Repertoire racine = new Repertoire("/");

    /**
     * Permet d'obtenir la taille du chemin
     * @return int
     */
    public abstract int getSize();

    /**
     * Permet de définir un parent au chemin
     * @param parent
     */
    public void setParent(Repertoire parent) {
        this.parent = parent;
    }

    ;

    /**
     * Permet d'obtenir le nom du chemin
     * @return String
     */
    public String getName() {
        return this.name;
    }

    ;

    /**
     * Permet de définir le nom du chemin
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    ;

    /**
     * Permet d'obtenir le répertoire parent du chemin
     * @return Repertoire
     */
    public Repertoire getParent() {
        return this.parent;
    }

    /**
     * Permet d'obtenir le racine du système de fichier
     * @return Repertoire
     */
    public static Repertoire getRacine() {
        return racine;
    }

    /**
     * Permet de savoir si le chemin passé en paramètre est la racine du système de fichier
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
}