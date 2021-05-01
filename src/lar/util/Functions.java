package lar.util;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import lar.telas.FrmPrincipal;
import lar.telas.SidaUI;

/**
 *
 * @author Renato Freitas
 */
public class Functions {
    

    /**
     * Retorna o nome do arquivo sem a extensão.
     */
    public static String cortaExtensao(String file) {
        return file.substring(0, file.lastIndexOf('.'));
    }

    /**
     * Retorna uma string do início até encontrar o último caracter passado por
     * parâmetro.
     *
     * @param s String a ser manipulada.
     * @param c Caracter que delimita o fim do corte.
     * @return String cortada.
     */
    public static String cortaAte(String s, char c) {
        return s.substring(0, s.lastIndexOf(c) + 1);
    }

    /**
     * Retorna uma string que inicia depois do último caracater passado por
     * parâmetro até o fim da string passada.
     *
     * @param s Strign a ser manipulada.
     * @param c Caracater que delimita o início do corte.
     * @return String cortada.
     */
    public static String cortaDepoisDe(String s, char c) {
        return s.substring(s.lastIndexOf(c) + 1, s.length());
    }

    public static String prefixoOD() {
        return cortaExtensao(FrmPrincipal.domainOntologyName);
    }

    public static File chooseFile() throws FileNotFoundException {
//        File f = new File("OD");
        File f = null;
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showOpenDialog(null);
        if (i == 1) {
        } else {
            f = file.getSelectedFile();
        }
        return f;
    }

    public static void changeIcon(JTree tree) {
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            private final Icon setaIcon = new ImageIcon(getClass().getResource("/lar/resources/img/seta_direita.png"));

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean selected, boolean expanded, boolean isLeaf, int row,
                    boolean focused) {
                Component c = super.getTreeCellRendererComponent(tree, value, selected,
                        expanded, isLeaf, row, focused);
                if (isLeaf == true) {
                    setIcon(setaIcon);
                }
                return c;
            }
        });
    }

    // MESSAGES
    public final static String CONNECTION_FAILED = "Wrong connection!";

    public static void exibeMenssagemDeErro() {
        JOptionPane.showMessageDialog(null, "Sorry, you can not send empty field!", "Error validation!!", JOptionPane.ERROR_MESSAGE);
    }

    public static void printTab(String s) {
        System.out.println("\t[***] " + s);
    }

    public static void print(String local, String message, String value) {
        System.out.println("[*** " + local + "] " + message + " " + value + "\n");
    }
}
