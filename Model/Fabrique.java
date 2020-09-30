/**
 * Cette classe permet de créer des chemins (fichiers ou répertoires),
 * elle utilise le modèle Singleton
 * @author Clément GEYER
 */
public class Fabrique {

    /**
     * @param fab
     */
    private static Fabrique fab;

    /**
     * Constructeur de la classe Fabrique
     */
    private Fabrique(){}

    /**
     * Cette fonction permet de créer une (et une seule) instance de la classe Fabrique
     * en respectant le principe du modèle Singleton
     */
    public static void createFabrique(){
        if(fab == null){
            fab = new Fabrique();
        }
    }

    /**
     * Cette fonction permet de créer un fichier en lui passant tous les paramètres nécessaires
     * et en vérifiant que ceux-ci sont valides, sinon une exception sera levée
     * @param name
     * @param parent
     * @param objet
     * @return Fichier
     * @throws Exception
     */
    public static Fichier createFile(String name, Repertoire parent, String objet) throws Exception {
        if(!name.isEmpty() && !objet.isEmpty()){
            return new Fichier(name, parent, objet);
        }
        else{
            throw new Exception("Invalid arguments");
        }
    }

    /**
     * Cette fonction permet de créer un répertoire en lui passant tous les paramètres nécessaires
     * et en vérifiant que ceux-ci sont valides, sinon une exception sera levée
     * @param name
     * @param parent
     * @return Repertoire
     * @throws Exception
     */
    public static Repertoire createRepository(String name, Repertoire parent) throws Exception {
        if(!name.isEmpty()){
            return new Repertoire(name, parent);
        }
        else{
            throw new Exception("Invalid arguments");
        }
    }
}
