/**
 * Cette classe permet d'effectuer des fonctions disponibles dans un terminal unix sur les objets du système de fichier
 * @author Clément GEYER
 */
public class Terminal {

    /**
     * Répertoire courrant
     * @param pwd
     */
    private Repertoire pwd;

    /**
     * Constructeur de la classe Terminal en lui passant la racine en paramètre
     * @param racine
     */
    public Terminal(Repertoire racine){
        this.pwd = racine;
    }

    /**
     * Cette fonction permet de créer un fichier avec un nom défini
     * @param fileName
     * @throws Exception
     */
    public void touch(String fileName) throws Exception {
        Fabrique.createFile(fileName, pwd, "");
    }

    /**
     * Cette fonction permet de créer un répertoire avec un nom défini
     * @param repositoryName
     * @throws Exception
     */
    public void mkdir(String repositoryName) throws Exception {
        Fabrique.createRepository(repositoryName, pwd);
    }

    /**
     * Cette fonction permet d'afficher la liste des enfants du répertoire courrant
     */
    public void ls(){
        for(Chemin childs : pwd.getChilds()){
            System.out.print(childs + "   ");
        }
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
        if(name.equals("..")){
            pwd = pwd.getParent();
        }
        else{
            for(Chemin childs : pwd.getChilds()){
                if(childs.getName().equals(name) && childs instanceof Repertoire){
                    Repertoire newPwd = (Repertoire) childs;
                    pwd = newPwd;
                }
            }
        }
    }

    /**
     * Cette fonction permet de supprimer un fichier par son nom
     * @param fileName
     */
    public void rm(String fileName){
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
     * @param repositoryName
     */
    public void rmdir(String repositoryName){
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
     * @param oldName
     * @param newName
     */
    public void mv(String oldName, String newName){
        String[] chemin = newName.split("/");
        Fichier oldFile = null;
        for(Chemin childs : pwd.getChilds()){
            if(childs.getName().equals(oldName) && childs instanceof Fichier){
                oldFile = (Fichier) childs;
                pwd.removeChild(childs);
            }
        }
        for (int i = 0;i<chemin.length;i++){
            for(Chemin childs : pwd.getChilds()){
                if(childs.getName().equals(chemin[i]) && childs instanceof Repertoire && i < chemin.length){
                    cd(chemin[i]);
                }
                if(i == chemin.length && oldFile != null){
                    pwd.addChild(oldFile);
                    oldFile.setParent(pwd);
                }
            }
        }
    }
}
