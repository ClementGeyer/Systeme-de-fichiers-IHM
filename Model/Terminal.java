import java.util.ArrayList;

public class Terminal {

    private Repertoire pwd;

    public Terminal(Repertoire racine){
        this.pwd = racine;
    }

    public void touch(String fileName) throws Exception {
        Fabrique.createFile(fileName, pwd, "");
    }

    public void mkdir(String repositoryName) throws Exception {
        Fabrique.createRepository(repositoryName, pwd);
    }

    public void ls(){
        for(Chemin childs : pwd.getChilds()){
            System.out.print(childs + "   ");
        }
    }

    public void pwd(){
        System.out.println(pwd.getName());
    }

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
