package lar.util;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lar.telas.SidaUI;

/**
 *
 * @author Renato Freitas
 */
public class Comum {

    /*About Database*/
    public final static String NOME_BD_MYSQL = "tcc2";
    public final static String POSTGRES_BD_NAME = "sadis";
    public final static String MYSQL_DRIVER = "jdbc:mysql://localhost/";
    public final static String MYSQL_URL = "jdbc:mysql://localhost/";
    public final static String MYSQL_ROOT = "root";
    public final static String MYSQL_SENHA = "r00t";
    public final static String POSTGRES_DRIVER = "org.postgresql.Driver";
    public final static String POSTGRES_URL = "jdbc:postgresql://localhost:5432/";
    public final static String POSTGRES_ROOT = "root";
    public final static String POSTGRES_SENHA = "r00t";
    public final static String SSL = "?useSSL=false";
    
    /**
     * Array com os nomes dos bancos de dados mais comuns.
     */
    public final static String SGBDs[] = {"", "Mysql", "Postgres"};


    /**
     * Retorna o nome do arquivo sem a extensão.
     */
    public static String cortaExtensao(String file) {
        return file.substring(0, file.lastIndexOf('.'));
    }
    
    /**
     * Retorna uma string do início até encontrar o último caracter passado por parâmetro.
     * @param s String a ser manipulada.
     * @param c Caracter que delimita o fim do corte.
     * @return String cortada.
     */
    public static String cortaAte(String s, char c){
        return s.substring(0, s.lastIndexOf(c) + 1);
    }
    
    /**
     * Retorna uma string que inicia depois do último caracater passado por parâmetro até o fim da string passada.
     * @param s Strign a ser manipulada.
     * @param c Caracater que delimita o início do corte.
     * @return String cortada.
     */
    public static String cortaDepoisDe(String s, char c){
        return s.substring(s.lastIndexOf(c) + 1, s.length());
    }
    
    public static String prefixoOD(){
        return cortaExtensao(SidaUI.nomeDaOD);
    }

    public static File chooseFile() throws FileNotFoundException {
        File f = new File("OD");
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showOpenDialog(null);
        if (i == 1) {
        } else {
            f = file.getSelectedFile();
        }
        return f;
    }

    public static void exibeMenssagemDeErro() {
        JOptionPane.showMessageDialog(null, "Sorry, you can not send empty field!", "Error validation!!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static String printTab(String s){
        return "\t[***]" + s;
    }
}
