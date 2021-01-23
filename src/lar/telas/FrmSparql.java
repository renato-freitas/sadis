package lar.telas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import lar.jena.Ontology;
import static lar.telas.FrmPrincipal.domainOntology;
import static lar.telas.FrmPrincipal.domainOntologyName;
import static lar.telas.FrmPrincipal.ontologyUrl;
import lar.util.global;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;
import static org.apache.jena.vocabulary.DCAT.dataset;

/**
 *
 * @author Renato
 */
public class FrmSparql extends javax.swing.JFrame {

    /**
     * Creates new form FrmSparql
     */
    public FrmSparql() {
        initComponents();
    }
    final String DEFAULT_SPARQL_PREFIXES = "PREFIX  dbo:  <http://dbpedia.org/ontology/>\n"
            + "PREFIX  :     <http://dbpedia.org/resource/>\n"
            + "PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
            + "PREFIX  owl:  <http://www.w3.org/2002/07/owl#>\n"
            + "PREFIX  apf:  <http://jena.hpl.hp.com/ARQ/property#>\n"
            + "PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
            + "PREFIX  fn:   <http://www.w3.org/2005/xpath-functions#>\n"
            + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "PREFIX  foaf: <http://xmlns.com/foaf/0.1/>\n"
            + "PREFIX  dc:   <http://purl.org/dc/elements/1.1/>\n"
            + "PREFIX wikibase: <http://wikiba.se/ontology#>\n"
            + "PREFIX wd: <http://www.wikidata.org/entity/>\n"
            + "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n"
            + "PREFIX schema: <http://schema.org/>\n"
            + "PREFIX bd: <http://www.bigdata.com/rdf#>\n";
    final String WIKIDATA_ENDPOINT = "https://query.wikidata.org/sparql";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGetPeopleByCityName = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaResultSparql = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCityName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnGetDiseaseFromDbpedia = new javax.swing.JButton();
        btnOrpha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGetPeopleByCityName.setText("Go");
        btnGetPeopleByCityName.setName("btnGetPeopleByCityName"); // NOI18N
        btnGetPeopleByCityName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetPeopleByCityNameActionPerformed(evt);
            }
        });

        txaResultSparql.setColumns(20);
        txaResultSparql.setRows(5);
        jScrollPane1.setViewportView(txaResultSparql);

        jLabel1.setText("Pessoas por cidade");

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setForeground(new java.awt.Color(12, 10, 200));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CONSULTAS SPARQL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(252, 252, 252))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        txtCityName.setToolTipText("Digite o nome de uma cidade");

        jLabel3.setText("Doenças from Dbpedia");

        btnGetDiseaseFromDbpedia.setText("Go");
        btnGetDiseaseFromDbpedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDiseaseFromDbpediaActionPerformed(evt);
            }
        });

        btnOrpha.setText("Orpha");
        btnOrpha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrphaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGetPeopleByCityName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGetDiseaseFromDbpedia))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOrpha)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetPeopleByCityName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnGetDiseaseFromDbpedia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOrpha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetPeopleByCityNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetPeopleByCityNameActionPerformed
//        getCityName();
//        getDrugs();
//        getPeopleByCityName();
//getWikiData();
//    getDengueFromWikiData();
//        getDiseaseFromWikidata();
//        getDiseasesThatContainVomitingSymptomsFromWikidata();
        getDiseasesThatSomeSymptomsFromWikidata();
    }//GEN-LAST:event_btnGetPeopleByCityNameActionPerformed

    private void btnGetDiseaseFromDbpediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDiseaseFromDbpediaActionPerformed
        getDiseasesFromDbPedia();
    }//GEN-LAST:event_btnGetDiseaseFromDbpediaActionPerformed

    private void btnOrphaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrphaActionPerformed
//        getDiseasesFromOrpha();
        getDiseasome();
    }//GEN-LAST:event_btnOrphaActionPerformed

    private void getPeopleByCityName() {
        this.txaResultSparql.setText("");
        String cityName = this.txtCityName.getText();
        String query = "SELECT ?person\n"
                + "WHERE{ \n"
                + "   ?person <http://dbpedia.org/ontology/birthPlace> <http://dbpedia.org/resource/" + cityName + "> .\n"
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query)) {

            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getCityName() {
        this.txaResultSparql.setText("");
        String cityName = this.txtCityName.getText();
        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT ?city ?concept\n"
                + "WHERE{ \n"
                + "   ?city a dbo:Place .\n"
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query)) {

            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getDiseasesFromDbPedia() {
        this.txaResultSparql.setText("");

        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT DISTINCT ?disease ?props ?value\n"
                + "WHERE{ \n"
                + "   ?disease a dbo:Disease .\n"
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query)) {

            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getDiseasesFromOrpha() {
        this.txaResultSparql.setText("");

        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT DISTINCT ?concept\n"
                + "WHERE{ \n"
                + "   ?concept a <http://www.w3.org/2002/07/owl#Class> .\n"
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService("https://www.orpha.net/sparql", query)) {

            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getDiseasome() {
        this.txaResultSparql.setText("");
        String endpoint = "http://wifo5-03.informatik.uni-mannheim.de/diseasome/sparql";

        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT DISTINCT ?concept\n"
                + "WHERE{ \n"
                + "   [] a ?concept .\n"
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getDrugs() {
        String endpoint = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/drugs";
        this.txaResultSparql.setText("");
//        String endpoint = "http://wifo5-03.informatik.uni-mannheim.de/diseasome/sparql";

        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT DISTINCT ?concept\n"
                + "WHERE{ \n"
                + "   [] a ?concept .\n"
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getWikiData() {
        this.txaResultSparql.setText("");
        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT DISTINCT ?concept\n"
                + "WHERE{ \n"
                + "   [] a ?concept .\n"
                + "} LIMIT 100";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getDengueFromWikiData() {
        this.txaResultSparql.setText("");
        String query = DEFAULT_SPARQL_PREFIXES
                + "SELECT ?disease\n"
                + "WHERE{ \n"
                + "   ?disease rdfs:label \"dengue fever\".\n"
                + "} LIMIT 100";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    private void getDiseaseFromWikidata() {
        this.txaResultSparql.setText("");
        String query = DEFAULT_SPARQL_PREFIXES
                + "select distinct ?s ?label ?description ?symptoms\n"
                + "WHERE{ \n"
                + " ?s wdt:P31 wd:Q12136 ;\n"
                + " rdfs:label ?label ;\n"
                + " schema:description ?description ;"
                + " wdt:P780 ?symptoms .\n"
                + " SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\" . }"
                + " FILTER (lang(?label) = \"en\")\n"
                + "  Filter(lang(?description) = \"en\")"
                + "} LIMIT 500";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    void getDiseasesThatContainVomitingSymptomsFromWikidata() {
        this.txaResultSparql.setText("");
        String query = DEFAULT_SPARQL_PREFIXES
                + "select distinct ?disease ?symptoms ?label ?description\n"
                + "where { \n"
                + "  ?disease wdt:P31 wd:Q12136 ;\n"
                + "           wdt:P780 ?symptoms .\n"
                + "  ?symptoms rdfs:label \"vomiting\" .\n"
                //                + "  FILTER CONTAINS(?label, \"vomiting\") .\n"
                + "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }\n"
                + "  FILTER (lang(?label) = \"en\")\n"
                + "}\n"
                + "order by(?disease)\n"
                + "limit 500";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    void getDiseasesThatSomeSymptomsFromWikidata() {
        this.txaResultSparql.setText("");
        String query = DEFAULT_SPARQL_PREFIXES
                + "select ?disease ?diseaseLabel ?symptoms ?label\n"
                + "where { \n"
                + "  ?disease wdt:P31 wd:Q12136 ;\n"
                + "        wdt:P780 ?symptoms ;\n"
                + "        rdfs:label ?diseaseLabel .\n"
                + "  ?symptoms rdfs:label ?label .\n"
                + "  FILTER (?label IN (\"vomiting\"@en, \"headache\"@en, \"deafness\"@en, \"pain\"@en))\n"
                + "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en,pt\". }\n"
                + "  FILTER (lang(?diseaseLabel) = \"en\")\n"
                + "}\n"
                + "order by(?disease)";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                String toTextArea = ResultSetFormatter.asText(rs);
                qe.close();
                this.txaResultSparql.setText(toTextArea);
            } else {
                this.txaResultSparql.setText("Nada encontrado!");
            }
        }
    }

    @SuppressWarnings("empty-statement")
    private void teste() {
        File f;
        try {
            f = global.chooseFile();
            String inputFileName = f.getName();
            Model model = ModelFactory.createDefaultModel();

            // use the FileManager to find the input file
//            InputStream in = FileManager.get().open(inputFileName);;
//            InputStream in = RDFDataMgr.open(inputFileName);
            FileInputStream in = new FileInputStream(f);
//            InputStream in = RDFDataMgr.open(inputFileName);
            if (in == null) {
                throw new IllegalArgumentException(
                        "File: " + inputFileName + " not found");
            }

            // read the RDF/XML file
            model.read(in, null);

            // write it to standard out
            model.write(System.out);
        } catch (FileNotFoundException e) {
        }
//        Model model = ... ;
//  String queryString = " .... ";
//        Query query = QueryFactory.create(queryString);
//        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
//            ResultSet results = qexec.execSelect();
//            for (; results.hasNext();) {
//                QuerySolution soln = results.nextSolution();
//                RDFNode x = soln.get("varName");       // Get a result variable by name.
//                Resource r = soln.getResource("VarR"); // Get a result variable - must be a resource
//                Literal l = soln.getLiteral("VarL");   // Get a result variable - must be a literal
//            }
//        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSparql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSparql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSparql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSparql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSparql().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetDiseaseFromDbpedia;
    private javax.swing.JButton btnGetPeopleByCityName;
    private javax.swing.JButton btnOrpha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txaResultSparql;
    private javax.swing.JTextField txtCityName;
    // End of variables declaration//GEN-END:variables
}