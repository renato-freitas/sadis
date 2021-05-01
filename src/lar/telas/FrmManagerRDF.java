/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.telas;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import static org.apache.jena.rdf.model.ResourceFactory.createTypedLiteral;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import lar.util.Functions;

/**
 *
 * @author Renato
 */
public class FrmManagerRDF extends javax.swing.JFrame {

    /**
     * Creates new form FrmManagerRDF
     */
    public FrmManagerRDF() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCreateRdf = new javax.swing.JButton();
        txtRdfName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrefix = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("URL base do RDF");

        btnCreateRdf.setText("Criar");
        btnCreateRdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRdfActionPerformed(evt);
            }
        });

        txtRdfName.setText("http://<endereço>/resource#");

        jLabel2.setText("Prefixo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRdfName, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txtPrefix))
                .addGap(18, 18, 18)
                .addComponent(btnCreateRdf)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCreateRdf)
                    .addComponent(txtRdfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateRdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRdfActionPerformed
        Model model = ModelFactory.createDefaultModel();

        String urlBase = this.txtRdfName.getText();
        String prefix = this.txtPrefix.getText();

        model.setNsPrefix(prefix, urlBase);
        model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
        model.setNsPrefix("vcard", "http://www.w3.org/2001/vcard-rdf/3.0#");
        model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        model.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
        model.setNsPrefix("dbr", "http://dbpedia.org/resource/");
        model.setNsPrefix("xsd", "https://www.w3.org/2001/XMLSchema#");

        Resource r = model.createResource(urlBase + "renato-freitas");
        Property pp = model.createProperty(urlBase + "site");
        r.addProperty(RDF.type, model.createResource("http://dbpedia.org/ontology/Person"));
        r.addProperty(RDFS.label, "11", "https://www.w3.org/2001/XMLSchema#integer");
        r.addProperty(FOAF.firstName, "renato", "pt");
        r.addProperty(VCARD.EMAIL, "renato@gmail.com");
        r.addProperty(pp, model.createResource("http://dbpedia/sparql"));

//        RDFDataMgr.write(System.out, model, Lang.NTRIPLES);
        RDFDataMgr.write(System.out, model, Lang.TURTLE);
//        RDFDataMgr.write(System.out, model, Lang.N3);
//        model.write(System.out);
    }//GEN-LAST:event_btnCreateRdfActionPerformed

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
            java.util.logging.Logger.getLogger(FrmManagerRDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmManagerRDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmManagerRDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmManagerRDF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmManagerRDF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateRdf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtPrefix;
    private javax.swing.JTextField txtRdfName;
    // End of variables declaration//GEN-END:variables
}
