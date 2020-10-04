import javax.swing.*;

public class View extends JFrame{

    private JTree tree;
    private Component component;

    public View(Component c){
        this.component = c;
        this.tree = new JTree(component);
        add(this.tree);
        setTitle("test");
        setSize( 500,400 );
        setLocation(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true );
    }
}
