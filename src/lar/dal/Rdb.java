/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.dal;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lar.entidade.Database;
import lar.util.Comum;

/**
 * Relational Database
 *
 * @author renato
 */
public class Rdb {

    private static DatabaseMetaData dbmd;
    public static List<String> tables;
    public static List<String> columns;
    public static List<String> pks;
    private static String catalog = null;
    private static String schema = null;
    private static final String[] TYPE_OF_TABLES = {"TABLE", "VIEW", "ALIAS", "SYNONYM", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "SYSTEM TABLE"};

    /**
     * Obtém um lista com todas as tabelas do BD.
     *
     * @param db
     * @return s
     */
//    public static List<String> getTables(Dataset ds) {
    public static List<String> getTables(Database db) {
        tables = new ArrayList<>();
        try {
            if (db.getServer().equals(Comum.SGBDs[1])) {
                dbmd = new ConnectionFactory(db).getMysqlDBConnection().getMetaData();
            }
            if (db.getServer().equals(Comum.SGBDs[2])) {
                Connection conn;
                conn = new ConnectionFactory(db).getPostgresDBConnection();
                dbmd = conn.getMetaData();

                String t
                        = "SELECT tablename FROM pg_tables WHERE tablename NOT LIKE 'pg%' AND tablename NOT LIKE 'sql\\_%'";
//                System.out.println(t);
                PreparedStatement stmt = conn.prepareStatement(t);
                ResultSet rs;
                rs = stmt.executeQuery();

                while (rs.next()) {
                    System.out.println(Comum.printTab(rs.getString(1)));
                    tables.add(rs.getString(1));
                }

            }

//            try (ResultSet rs = dbmd.getTables(catalog, schema, "%", TYPE_OF_TABLES)) {
//                while (rs.next()) {
//                    tables.add(rs.getString(3));
//                }   //essa linha é só pra visualizar melhor a saída do código. Ele poderá ser eliminado posteriomente.
//
//                //getColumnsOfTable();
//            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return tables;
    }

    /**
     *
     * This get the name of table's columns
     */
    private static void getColumnsOfTable() {
        Iterator<String> table = tables.iterator();
        try {
            while (table.hasNext()) {
                String tableName = (String) table.next();
                //just prin
//                System.out.println("[***Table] " + tableName);

                //get columns
                ResultSet rs = dbmd.getColumns(catalog, schema, tableName, "%");
//                ResultSetMetaData rsmd =  rs.getMetaData();
                int i = 1;
                while (rs.next()) {
//                    System.out.println(rsmd.getSchemaName(1));

                    String colName = rs.getString(4);

                    if (colName.trim().toLowerCase().equals(colName)) {
//                        System.out.println("\t[*** Column](" + i + "): " + colName);//Ele poderá ser eliminada posteriomente.
//				    	this.getTipoDaColuna(tableName, conn, i);
                    }
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma lista com os nomes das colunas de uma tabela.
     *
     * @param nomeTabela O nome da tabela.
     * @return List<String>
     */
    public static List<String> getColumnsOfTable(String nomeTabela) {
        columns = new ArrayList<>();
        try {

            ResultSet rs = dbmd.getColumns(catalog, schema, nomeTabela, "%");
            int i = 1;
            while (rs.next()) {

                String colName = rs.getString(4);
                columns.add(rs.getString(4));

                if (colName.trim().toLowerCase().equals(colName)) {
                    System.out.println("\t"+Comum.printTab(colName));
//                    System.out.println("\t[*** Column](" + i + "): " + colName);//Ele poderá ser eliminada posteriomente.
//				    	this.getTipoDaColuna(tableName, conn, i);
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    /**
     * Obtém a coluna com a chave primária da tabela.
     *
     * @param nomeTabela O nome da tabela.
     * @return Column name.
     */
    public static void getPKOfTable(String nomeTabela) {
        pks = new ArrayList<>();
        try {
            ResultSet rs = dbmd.getPrimaryKeys(catalog, schema, nomeTabela);

            while (rs.next()) {
                pks.add(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
