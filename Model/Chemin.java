
import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public abstract class Chemin implements Serializable {
    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Repertoire parent;

    /**
     * 
     */
    private static final Repertoire racine = new Repertoire("/");

    public abstract int getSize();

    /**
     * @param parent
     */
    public void setParent(Repertoire parent){
        this.parent = parent;
    };

    /**
     * @return
     */
    public String getName(){
        return this.name;
    };

    /**
     * @param name
     */
    public void setName(String name){
        this.name = name;
    };

    /**
     * @return
     */
    public Repertoire getParent(){
        return this.parent;
    }

    public static Repertoire getRacine(){
        return racine;
    }

    public static boolean isRacine(Chemin ch){
        if(ch.getName().equals("/")){
            return true;
        }
        else{
            return false;
        }
    }
}