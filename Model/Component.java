import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class Component implements TreeModel {

    public Component(){}

    @Override
    public Chemin getRoot() {
        return Chemin.getRacine();
    }

    @Override
    public Chemin getChild(Object parent, int index){
        if(parent instanceof Repertoire){
            return ((Repertoire) parent).getChilds().get(index);
        }
        else{
            return null;
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if(parent instanceof Repertoire){
            return ((Repertoire) parent).getChilds().size();
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Fichier;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent instanceof Repertoire && child instanceof Chemin){
            return ((Repertoire) parent).getChilds().indexOf(child);
        }
        else {
            return 0;
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
