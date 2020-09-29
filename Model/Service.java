import java.io.*;
import java.util.ArrayList;

public class Service {

    public Service(){
    }

    public String getChemin(Chemin chemin){
        Chemin chFinder = chemin;
        String ch = chFinder.getName();
        while(!Chemin.isRacine(chFinder)){
            ch += " -> " + chFinder.getParent().getName();
            chFinder = chFinder.getParent();
        }
        return ch;
    }

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

    public Chemin deserialisation(){
        Chemin ch = null;
        try{
            FileInputStream fis = new FileInputStream("Chemin.txt");
            ObjectInputStream is = new ObjectInputStream(fis);
            ch = (Chemin) is.readObject();
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ch;
    }
}
