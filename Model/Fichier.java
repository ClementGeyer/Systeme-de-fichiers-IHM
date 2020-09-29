
import java.util.*;

/**
 * 
 */
public class Fichier extends Chemin {

    /**
     * 
     */
    private String objet;

    /**
     * @param name
     */
    public Fichier(String name, Repertoire parent, String objet) {
        this.setName(name);
        this.setParent(parent);
        this.setObjet(objet);
    }

    /**
     * @return
     */
    public String getObjet() {
        return this.objet;
    }

    /**
     * @param objet
     */
    public void setObjet(String objet) {
        this.objet = objet;
    }

    public int getSize(){
        return this.objet.length();
    }
}