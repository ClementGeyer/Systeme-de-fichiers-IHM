package Controller;

import Modele.*;

/**
 * Cette classe permet de créer des chemins (fichiers ou répertoires),
 * elle utilise le modèle Singleton
 * @author Clément GEYER
 */
public class Fabrique {
    private static Fabrique fab;

    /**
     * Constructeur de la classe Controller.Fabrique
     */
    private Fabrique(){}

    /**
     * Cette fonction permet de créer une (et une seule) instance de la classe Controller.Fabrique
     * en respectant le principe du modèle Singleton
     */
    public static Fabrique getInstance(){
        if(fab == null){
            fab = new Fabrique();
        }
        return fab;
    }

    /**
     * Cette fonction permet de créer un fichier en lui passant tous les paramètres nécessaires
     * et en vérifiant que ceux-ci sont valides, sinon une exception sera levée
     * @param name nom du fichier
     * @param parent parent du fichier
     * @param objet objet du fichier
     * @return Modele.Fichier
     */
    public Fichier createFile(String name, Repertoire parent, String objet){
        if(name == null || name.isEmpty() || parent == null || objet == null){
            throw new IllegalArgumentException("Illegal arguments");
        }
        Fichier f = new Fichier(name, parent, objet);
        parent.addChild(f);
        return f;
    }

    /**
     * Cette fonction permet de créer un répertoire en lui passant tous les paramètres nécessaires
     * et en vérifiant que ceux-ci sont valides, sinon une exception sera levée
     * @param name nom du répertoire
     * @param parent parent du répertoire
     * @return Modele.Repertoire
     */
    public Repertoire createRepository(String name, Repertoire parent){
        if(name == null || name.isEmpty() || parent == null){
            throw new IllegalArgumentException("Illegal arguments");
        }
        Repertoire r = new Repertoire(name, parent);
        parent.addChild(r);
        return r;
    }
}
