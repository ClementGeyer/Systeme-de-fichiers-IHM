package Controller;

import java.io.Console;
import java.util.List;

import Modele.Chemin;
import Vue.View;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class InteractiveShell {

    public static void main(String[] args) {

        Console console = System.console();

        try (JShell jsh = JShell.create()) {

            // imports (à completer)
            jsh.eval("import Controller.Terminal;");
            jsh.eval("import Modele.Chemin;");

            // Creation de la racine du systeme de fichiers
            jsh.eval( "Modele.Repertoire root = Modele.Chemin.getRacine();" );

            // Creation d'un terminal
            jsh.eval( "Controller.Terminal t = new Controller.Terminal( root );" );

            // Creation d'une vue ?
            View fenetre = new View(Chemin.getRacine());

            // Boucle infinie d'évaluations
            do {
                System.out.print("> ");


                String input = console.readLine();
                if (input == null) {
                    break; // Termine sur CTRL + D
                }

                /* On transforme l'input de la façon suivante :
                 * ls -> ls()
                 * rm file -> rm("file")
                 */
                String[] arg = input.split("\\s"); // whitespace character

                input = "t." + arg[0]+'(';
                if( arg.length > 1)
                    input += '"'+arg[1]+'"';
                input += ')';

                // Evaluation de la commande
                List<SnippetEvent> events = jsh.eval(input);

                for (SnippetEvent e : events) {

                    if (e.value() != null) {
                        System.out.print(e.value());

                    } else
                        System.err.println(e);
                    System.out.flush();

                }

                // Mise a jour de la vue ?
                fenetre.getContentPane().remove(0);
                fenetre = new View(Chemin.getRacine());

            } while (true);

        } // Fin try-with-resource

        System.out.println();

    }
}
