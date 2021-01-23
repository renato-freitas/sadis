package lar.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lar.entidade.Database;
import lar.modelo.Dataset;
import lar.util.global;

/**
 *
 * @author renato
 */
public class ConnectionFactory {

    private static Connection conn;
    private static Dataset dataset;
    private static Database bd;

    //Esse construtor recebe as informações de acesso ao BD.
    public ConnectionFactory(Database bd) {
//        ConnectionFactory.dataset = ds;
        ConnectionFactory.bd = bd;
    }

    
    /**
     * Abre uma conexão com o BD.
     * @return Conexão aberta.
     */
    public Connection getMysqlDBConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
//                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection(bd.getURL() + bd.getName() + global.SSL,
                        bd.getUser(),
                        bd.getPassword());
                System.out.println("[***] Database connection OK.");
            } catch (SQLException | ClassNotFoundException | NullPointerException sqlex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, sqlex);
                JOptionPane.showMessageDialog(null, "Was not possble find the dataset!");
            }
        }
        return conn;
    }

    
    
     /**
     * Abre uma conexão com o BD Postgres.
     * @return Conexão aberta.
     */
    public Connection getPostgresDBConnection() {
        
        if (conn == null) {
            try {
                Class.forName(global.POSTGRES_DRIVER);
//                DriverManager.registerDriver(new com.);
                conn = DriverManager.getConnection(bd.getURL() + bd.getName() + global.SSL,
                        bd.getUser(),
                        bd.getPassword());
                System.out.println("[***] Postgres DB connection OK.");
            } catch (SQLException | ClassNotFoundException | NullPointerException sqlex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, sqlex);
                JOptionPane.showMessageDialog(null, global.CONNECTION_FAILED);
            }
        }
        return conn;
    }
    /**
     * This close the dababase connection
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("[***] Database connection closed.");
            } catch (SQLException e) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

//	
//	public String getTabelas1(){
//		String retorno = null;
//		Connection conexao = null;
//		
//		try {
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());			
//			conexao = DriverManager.getConnection(this.DRIVER, this.ROOT, this.SENHA);
//			DatabaseMetaData dbmd = conexao.getMetaData();
//			ResultSet rs = dbmd.getTables(this.catalog, this.schema, "%", this.tipoTabelas);
//			//Banco bd = new Banco();
//			//List<Tabela> lstTb = new ArrayList<Tabela>();
//			
//			while (rs.next()) {
//				//posso trocar o tableName pro rs.getString(3) para verificar se há tabela como nome
//				//String tableName = rs.getString(3);
//				//if(!tableName.isEmpty()){//adiciona à lista se tiver algo
//				//if(!rs.getString(3).isEmpty()){//adiciona à lista se tiver algo
//					//**
//					
////					Tabela tb = new Tabela();
////					tb.setNome(rs.getString(3));
////					lstTb.add(tb);
//
//					//**
//					//this.tabelas.add(tableName);
//					//bd.setTabelas(lstTb);//Teste
//					//System.out.print(tb.getNome());//apenas exibir o nome das tabelas. Ele poderá ser eliminado posteriomente.
//					//retorno = tb.getNome();
//				//}
//		    }
//		    System.out.println();//essa linha é só pra visualizar melhor a saída do código. Ele poderá ser eliminado posteriomente.
//		    
//			this.getColunasDaTabela(dbmd, conexao);
//			
//			rs.close();
//			conexao.close();
//		} catch (SQLException ex) {
//			throw new RuntimeException(ex);
//		}
//		return retorno;
//	}
//	
//	
//	/**Responsável por obter os nomes das colunas das tabelas*/
//	private void getColunasDaTabela(DatabaseMetaData dbmd, Connection conn) throws SQLException {
//		
//		//Iterator<String> iter = this.tabelas.iterator();
//		//Iterator<Tabela> ite = this.bd.getTabelas().iterator();
//		
////		while (ite.hasNext()) {
////			String tableName = (String) ite.next().getNome();
////			//obtém as colunas da tabela atual
////			ResultSet rs = dbmd.getColumns(this.catalog, this.schema, tableName, "%");
////			
////			int i = 1;
////			while (rs.next()) {
////				String colName = rs.getString(4);
////				
////			    if (colName.trim().toLowerCase().equals(colName)){
////			    	System.out.println("Found column'" + colName + "' in " + tableName );//Ele poderá ser eliminada posteriomente.
////			    	this.getTipoDaColuna(tableName, conn, i);//
////			    }
////			    i++;
////			}
////	    }		  
//	}
//
//	/**Responsável por obter os tipos das colunas da tabela atual*/
//	private void getTipoDaColuna(String tab,Connection conn, int increment) 
//	throws SQLException{
//		
////		try {
//////			Statement stmt = conn.createStatement();
//////			ResultSet r = stmt.executeQuery("select * from "+ tab);//seleciona todas as colunas da tb atual;
//////			ResultSetMetaData rsmdCol = r.getMetaData();
////			//String tipo = rsmdCol.getColumnTypeName(increment);
////			//System.out.println("tipo da coluna: "+tipo);
////		    
////		} catch (SQLException e) {
////			throw new RuntimeException(e);
////		}
//		
//	}
//    
}
