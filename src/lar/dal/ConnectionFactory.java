package lar.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lar.entidade.Database;
import lar.modelo.Dataset;
import lar.util.Constants;
import lar.util.Functions;

/**
 *
 * @author renato
 */
public class ConnectionFactory {

    private static Connection connection;
    private static Dataset dataset;
    private static Database database;

    //Esse construtor recebe as informações de acesso ao BD.
    public ConnectionFactory(Database bd) {
        ConnectionFactory.database = bd;
    }

    
    /**
     * Abre uma conexão com o BD MySQL.
     * @return Conexão aberta.
     */
    public Connection getMysqlDBConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
//                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection(database.getURL() + database.getName() + Constants.SSL,
                        database.getUser(),
                        database.getPassword());
                System.out.println("[***] Database connection OK.");
            } catch (SQLException | ClassNotFoundException | NullPointerException sqlex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, sqlex);
                JOptionPane.showMessageDialog(null, "Was not possble find the dataset!");
            }
        }
        return connection;
    }

    
    
     /**
     * Open a BD Postgres connection.
     * @return Open connection.
     */
    public Connection getPostgresDBConnection() {
        if (connection == null) {
            try {
                Class.forName(Constants.POSTGRES_DRIVER);
//                DriverManager.registerDriver(new com.);
                connection = DriverManager.getConnection(database.getURL() + database.getName() + Constants.SSL,
                        database.getUser(),
                        database.getPassword());
                System.out.println("[***] Postgres DB connection OK.");
            } catch (SQLException | ClassNotFoundException | NullPointerException sqlex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, sqlex);
                JOptionPane.showMessageDialog(null, Functions.CONNECTION_FAILED);
            }
        }
        return connection;
    }
    
    /**
     * This close the dababase connection.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("[***] Database connection closed.");
            } catch (SQLException e) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }   
}
