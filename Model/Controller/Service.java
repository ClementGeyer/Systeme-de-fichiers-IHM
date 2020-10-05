package Controller;

import Modele.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Cette classe permet d'effectuer des opérations sur un chemin
 * @author Clément GEYER
 */
public class Service {

    /**
     * Constructeur de la classe Controller.Service
     */
    public Service(){}

    /**
     * Cette fonction permet d'avoir le chemin absolu (depuis la racine du système de fichier) d'un chemin donné en paramètre
     * @param chemin Chemin dont on cherche le chemin absolu
     * @return String
     */
    public String getChemin(Chemin chemin){
        if(chemin == null){
            throw new IllegalArgumentException("Illegal argument");
        }
        Chemin chFinder = chemin;
        ArrayList<String> cheminAbsolu = new ArrayList<>();
        StringBuilder ch = new StringBuilder("/");
        /* Ici on passe dans une boucle while afin de récuperer le chemin absolu depuis l'objet chemin
        jusqu'a la racine */
        while(!Chemin.isRacine(chFinder)){
            if(!chFinder.getName().equals("/")){
                // On l'ajoute à une liste
                cheminAbsolu.add(chFinder.getName());
            }
            // Puis le chercheur devient le parent de l'objet courrant
            chFinder = chFinder.getParent();
        }
        // On passe ensuite par une boucle for qui parcours la liste a l'envers
        for(int i = cheminAbsolu.size()-1;i>=0;i--){
            // Quand c'est le dernier élément on ne met pas de '/' sinon oui
            if(i != 0){
                ch.append(cheminAbsolu.get(i)).append("/");
            }
            else{
                ch.append(cheminAbsolu.get(i));
            }
        }
        return ch.toString();
    }

    /**
     * Cette fonction permet d'obtenir tous les chemins descendants
     * du Modele.Chemin donné en paramètre sous la forme d'une liste
     * @param chemin Chemin dont on cherche tous les chemins descendants
     * @return ArrayList<String>
     */
    public ArrayList<String> getCheminsDesc(Chemin chemin){
        if (chemin instanceof Fichier) {
            throw new IllegalArgumentException("Not a repository");
        }
        ArrayList<String> lst_ch = new ArrayList<>();
        // On utilise ici une boucle for afin de parcourir tous les enfants du chemin
        for (Chemin ch : chemin.getChilds()) {
            // Si c'est un fichier on l'ajoute a la liste
            if (ch instanceof Fichier) {
                lst_ch.add(getChemin(ch));
            }
            else {
                Repertoire rep = (Repertoire) ch;
                // Sinon c'est un répertoire et s'il est vide on l'ajoute comme un fichier
                if (rep.getChilds().size() == 0) {
                    lst_ch.add(getChemin(ch));
                }
                // S'il est non vide on réexecute la fonction avec comme chemin l'enfant répertoire
                else {
                    lst_ch.addAll(getCheminsDesc(ch));
                }
            }
        }
        return lst_ch;
    }

    /**
     * Cette fonction permet d'obtenir les chemins comprenant un nom donné
     * descendants de la racine du système de fichier
     * @param name nom du Chemin cherché
     * @return ArrayList<String>
     */
    public ArrayList<String> getCheminsDescByName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Illegal argument");
        }
        // On initialise un arbre depuis la racine
        ArrayList<String> tree = getCheminsDesc(Chemin.getRacine());
        ArrayList<String> newTree = new ArrayList<>(tree);
        /* Puis pour chaque chemin dans l'aborescence on enlève les chemins qui ne contienent pas
        le nom de fichier passé en paramètre */
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
     * @param chemin Chemin dont on cherche la taille totale
     * @return int
     */
    public int repSize(Chemin chemin){
        if(chemin instanceof Fichier) {
            throw new IllegalArgumentException("Not a repository");
        }
        // On part du principe que le chemin envoyé est un répertoire
        Repertoire rep = (Repertoire) chemin;
        int size = rep.getSize();
        for(Chemin ch : chemin.getChilds()){
            // Pour chaque enfant du chemin on regarde si c'est un fichier, si oui on ajoute simplement sa taille
            if(ch instanceof Fichier){
                size += ch.getSize();
            }
            else{
                // Si c'est un répertoire vide on ajoute sa taille
                Repertoire r = (Repertoire) ch;
                if(r.getChilds().size() == 0){
                    size += r.getSize();
                }
                // S'il est non vide on réexecute la fonction et on récupère le résultat qu'on ajoute a la taille totale
                else{
                    size += repSize(r);
                }
            }
        }
        return size;
    }

    /**
     * Cette fonction permet d'enregistrer un Modele.Chemin dans un fichier texte
     * @param ch Chemin qu'on veut sérialiser
     */
    public void serialisation(Chemin ch){
        try {
            // Ouverture d'un flux en écriture vers un fichier
            FileOutputStream fos = new FileOutputStream("modele/Chemin.txt");
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
     * @return Modele.Chemin
     */
    public Chemin deserialisation(){
        Chemin ch = null;
        try{
            FileInputStream fis = new FileInputStream("modele/Chemin.txt");
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
