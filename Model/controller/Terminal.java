package controller;

import modele.*;

/**
 * Cette classe permet d'effectuer des fonctions disponibles dans un terminal unix sur les objets du système de fichier
 * @author Clément GEYER
 */
public class Terminal {

    private Repertoire pwd;

    /**
     * Constructeur de la classe Controller.Terminal en lui passant la racine en paramètre
     * @param racine Chemin qu'on veut utiliser comme racine du système de fichier
     */
    public Terminal(Chemin racine){
        if(racine instanceof Fichier) {
            throw new IllegalArgumentException("Not a repository");
        }
        this.pwd = (Repertoire) racine;
    }

    /**
     * Cette fonction permet de créer un fichier avec un nom défini
     * @param fileName nom du fichier
     */
    public void touch(String fileName){
        if(fileName == null || fileName.isEmpty()){
            throw new IllegalArgumentException("Illegal argument");
        }
        Fabrique.getInstance().createFile(fileName, pwd, "");
    }

    /**
     * Cette fonction permet de créer un répertoire avec un nom défini
     * @param repositoryName nom du répertoire
     */
    public void mkdir(String repositoryName){
        if(repositoryName == null || repositoryName.isEmpty()) {
            throw new IllegalArgumentException("Illegal argument");
        }
        Fabrique.getInstance().createRepository(repositoryName, pwd);
    }

    /**
     * Cette fonction permet d'afficher la liste des enfants du répertoire courrant
     */
    public void ls(){
        for(Chemin childs : pwd.getChilds()){
            System.out.print(childs.getName() + "   ");
        }
        System.out.println();
    }

    /**
     * Cette fonction permet d'afficher le nom du répertoire courrant
     */
    public void pwd(){
        System.out.println(pwd.getName());
    }

    /**
     * Cette fonction permet d'accéder au répertoire parent ou un des enfants
     */
    public void cd(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Illegal argument");
        }
        if(name.equals("..")){
            pwd = pwd.getParent();
        }
        else{
            for(Chemin childs : pwd.getChilds()){
                if(childs.getName().equals(name) && childs instanceof Repertoire){
                    pwd = (Repertoire) childs;
                }
            }
        }
    }

    /**
     * Cette fonction permet de supprimer un fichier par son nom
     * @param fileName nom du fichier
     */
    public void rm(String fileName){
        if(fileName == null || fileName.isEmpty()){
            throw new IllegalArgumentException("Illegal argument");
        }
        for(Chemin childs : pwd.getChilds()){
            if (childs instanceof Fichier){
                Fichier file = (Fichier) childs;
                if(file.getName().equals(fileName)){
                    pwd.removeChild(file);
                }
            }
        }
    }

    /**
     * Cette fonction permet de supprimer un répertoire par son nom
     * @param repositoryName nom du répertoire
     */
    public void rmdir(String repositoryName){
        if(repositoryName == null || repositoryName.isEmpty()){
            throw new IllegalArgumentException("Illegal argument");
        }
        for(Chemin childs : pwd.getChilds()){
            if (childs instanceof Repertoire){
                Repertoire repository = (Repertoire) childs;
                if(repository.getName().equals(repositoryName)){
                    pwd.removeChild(repository);
                }
            }
        }
    }

    /**
     * Cette fonction permet de renommer ou déplacer un fichier
     * @param oldName ancien nom
     * @param newName nouveau nom
     */
    public void mv(String oldName, String newName){
        if(oldName == null || oldName.isEmpty() || newName == null || newName.isEmpty()){
            throw new IllegalArgumentException("Illegal argument");
        }
        for(Chemin childs : pwd.getChilds()){
            if(childs.getName().equals(oldName)){
                childs.setName(newName);
            }
        }
    }
}
