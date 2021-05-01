/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.negocio;

import javax.swing.JOptionPane;
import lar.entidade.Database;

import lar.modelo.Dataset;
import lar.util.Functions;

/**
 *
 * @author renato
 */
public class Transforma {

    public String[] comando;
    private Dataset dataset;

    public Dataset getDataset() {
        return this.dataset;
    }

    public void setDataset(Dataset dadosDb) {
        this.dataset = dadosDb;
    }

    public Transforma(Dataset ds) {
        comando = new String[3];
        comando[0] = "bash";
        comando[1] = "-c";

        tableToRML(ds);
    }

    public Transforma(String in_ttl, Database ds) {

        comando = new String[3];
        comando[0] = "bash";
        comando[1] = "-c";

        rml2NTriple(in_ttl, ds);
    }

    /**
     * Faz o mapeamento da tabela para RDF Mapping Language -RML
     */
    public void tableToRML(Dataset dataset) {
        try {
            if (dataset.getServidorBD() == "MYSQL") {
                comando[2] = mysqlStringConnectionToMapping(dataset);
            } else if (dataset.getServidorBD() == "POSTGRESQL") {
                System.out.println(postgresStringConnectionToMapping(dataset));
                comando[2] = postgresStringConnectionToMapping(dataset);
            }
            Process p = Runtime.getRuntime().exec(comando);
            if (p != null) {
                System.out.println("postgres(p): " + p);
                JOptionPane.showMessageDialog(null, "Sucessfull Dataset Choose!");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void rml2NTriple(String in, Database ds) {
        try {

            comando[2] = mysqlStringConnectionToDump(in, ds);
            Process p = Runtime.getRuntime().exec(comando);
            if (p != null) {
                JOptionPane.showMessageDialog(null, "Sucessfull Dump to N-triple!");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String mysqlStringConnectionToMapping(Dataset ds) {

        return "resources/d2rqlib/generate-mapping"
                + " -u " + ds.getUsuario()
                + " -p " + ds.getSenha()
                + " -d com.mysql.jdbc.Driver --r2rml"
                + " --tables " + ds.getNomeTabela()
                + " jdbc:mysql://localhost/" + ds.getNomeDataset()
                + " > /tmp/" + ds.getNomeArquivoSaida() + ".ttl";
    }

    /** Este tem a opção de escolhe o path do arquivo ttl, onde salvar o dump gerado e o nome do dump.
     */
    public String mysqlStringConnectionToDump(String in_ttl, Database ds) {

        String config
                = "resources/d2rqlib/dump-rdf"
                + " -u " + ds.getUser()
                + " -p " + ds.getPassword()
                + " -f n-triple"
                + " -j jdbc:mysql://localhost/" + ds.getName()
                + " /tmp/" + in_ttl
                + " > /tmp/" + Functions.cortaExtensao(in_ttl) + ".nt";

        return config;
    }

    
    
    
    public String postgresStringConnectionToMapping(Dataset ds) {

        return "resources/d2rqlib/generate-mapping"
                + " -u " + ds.getUsuario()
                + " -p " + ds.getSenha()
                + " -d org.postgresql.Driver --r2rml"
                + " --tables " + ds.getNomeTabela()
                + " jdbc:postgresql://localhost:5432/" + ds.getNomeDataset()
                + " > /tmp/" + ds.getNomeArquivoSaida() + ".ttl";
    }
}
