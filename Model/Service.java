import java.io.*;
import java.util.ArrayList;

/**
 * Cette classe permet d'effectuer des opérations sur un chemin
 * @author Clément GEYER
 */
public class Service {

    /**
     * Constructeur de la classe Service
     */
    public Service(){}

    /**
     * Cette fonction permet d'avoir le chemin absolu (depuis la racine du système de fichier) d'un chemin donné en paramètre
     * @param chemin
     * @return String
     */
    public String getChemin(Chemin chemin){
        Chemin chFinder = chemin;
        String ch = chFinder.getName();
        while(!Chemin.isRacine(chFinder)){
            ch += " -> " + chFinder.getParent().getName();
            chFinder = chFinder.getParent();
        }
        return ch;
    }

    /**
     * Cette fonction permet d'obtenir tous les chemins descendants
     * du Chemin donné en paramètre sous la forme d'une liste
     * @param chemin
     * @return ArrayList<String>
     */
    public ArrayList<String> getCheminsDesc(Repertoire chemin){
        ArrayList<String> lst_ch = new ArrayList<>();
        ArrayList<Chemin> fileAttente = new ArrayList<>(chemin.getChilds());
        ArrayList<Chemin> majFileAttente = new ArrayList<>(fileAttente);
        while(fileAttente.size() > 0) {
            for(Chemin ch : fileAttente){
                if(ch instanceof Fichier){
                    lst_ch.add(getChemin(ch));
                    majFileAttente.remove(ch);
                }
                else{
                    Repertoire rep = (Repertoire) ch;
                    if(rep.getChilds().size() == 0){
                        lst_ch.add(getChemin(ch));
                        majFileAttente.remove(rep);
                    }
                    else{
                        majFileAttente.addAll(rep.getChilds());
                        majFileAttente.remove(rep);
                    }
                }
            }
            fileAttente = new ArrayList<>(majFileAttente);
        }
        return lst_ch;
    }

    /**
     * Cette fonction permet d'obtenir les chemins comprenant un nom donné
     * descendants de la racine du système de fichier
     * @param name
     * @return ArrayList<String>
     */
    public ArrayList<String> getCheminsDescByName(String name){
        ArrayList<String> tree = getCheminsDesc(Chemin.getRacine());
        ArrayList<String> newTree = new ArrayList<>(tree);
        for(String str : tree){
            if(!str.contains(name)){
                newTree.remove(str);
            }
        }
        return newTree;
    }

    /**
     * Cette fonction permet d'obtenir la taille totale
     * d'un chemin donné comprenant toute l'arborescence sous ce chemin
     * @param rep
     * @return int
     */
    public int repSize(Repertoire rep){
        int size = rep.getSize();
        ArrayList<Chemin> fileAttente = new ArrayList<>(rep.getChilds());
        ArrayList<Chemin> newFileAttente = new ArrayList<>(rep.getChilds());
        while(fileAttente.size() > 0){
            for(Chemin ch : fileAttente){
                if(ch instanceof Fichier){
                    size += ch.getSize();
                    newFileAttente.remove(ch);
                }
                else{
                    Repertoire r = (Repertoire) ch;
                    if(r.getChilds().size() == 0){
                        size += r.getSize();
                        newFileAttente.remove(r);
                    }
                    else{
                        size += r.getSize();
                        newFileAttente.addAll(r.getChilds());
                        newFileAttente.remove(r);
                    }
                }
            }
            fileAttente = new ArrayList<>(newFileAttente);
        }
        return size;
    }

    /**
     * Cette fonction permet d'enregistrer un Chemin dans un fichier texte
     * @param ch
     */
    public void serialisation(Chemin ch){
        try {
            // Ouverture d'un flux en écriture vers un fichier
            FileOutputStream fos = new FileOutputStream("Chemin.txt");
            // Création d'un flux de sérialisation
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ch);
            os.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Cette fonction permet de lire les données précedemment enregistrer dans un fichier texte
     * @return Chemin
     */
    public Chemin deserialisation(){
        Chemin ch = null;
        try{
            FileInputStream fis = new FileInputStream("Chemin.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ch = (Chemin) ois.readObject();
            ois.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ch;
    }
}
