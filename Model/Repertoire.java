
import java.util.*;

/**
 * 
 */
public class Repertoire extends Chemin {
    /**
     * 
     */
    private ArrayList<Chemin> childs = new ArrayList();

    /**
     * 
     */
    private final int size = 4096;

    /**
     * @param name
     */
    public Repertoire(String name, Repertoire parent) {
        this.setName(name);
        this.setParent(parent);
    }

    public Repertoire(String name){
        this.setName(name);
    }

    /**
     * @param ch
     */
    public void addChild(Chemin ch) {
        childs.add(ch);
    }

    /**
     * @param ch
     */
    public void removeChild(Chemin ch) {
        childs.remove(ch);
    }

    /**
     * @return
     */
    public ArrayList<Chemin> getChilds() {
        return this.childs;
    }

    /**
     * @param childs
     */
    public void setChilds(ArrayList<Chemin> childs) {
        this.childs = childs;
    }

    /**
     * @return
     */
    public int getSize() {
        return this.size;
    }
}