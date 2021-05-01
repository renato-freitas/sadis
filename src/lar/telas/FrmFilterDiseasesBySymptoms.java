package lar.telas;

import java.awt.Color;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.text.Position;
import lar.entidade.ResourceWeb;
import lar.jena.QueriesSparql;

/**
 * @author Renato
 */
public class FrmFilterDiseasesBySymptoms extends javax.swing.JFrame {

    DefaultListModel dbpediaSymptoms = new DefaultListModel();
    DefaultListModel selectedSymptoms = new DefaultListModel();
    DefaultListModel possibleDiseases = new DefaultListModel();
    ResourceWeb disease = new ResourceWeb();

    public FrmFilterDiseasesBySymptoms() {
        initComponents();
        this.btnInferingDisease.setEnabled(false);
        this.btnConfirmDisease.setEnabled(false);
        this.txtSymptomName.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstSymptoms = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaComment = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstSelectedSymptoms = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        btnInferingDisease = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstPossibleDiseases = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txaCommentDisease = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSymptomName = new javax.swing.JTextField();
        btnConfirmDisease = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sadis - Inferir Possíveis Doenças");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 10, 200));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Inferir Doenças");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lstSymptoms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstSymptomsMouseClicked(evt);
            }
        });
        lstSymptoms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lstSymptomsKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(lstSymptoms);

        jLabel1.setText("Selecione os sintomas");

        txaComment.setEditable(false);
        txaComment.setColumns(20);
        txaComment.setLineWrap(true);
        txaComment.setRows(5);
        txaComment.setWrapStyleWord(true);
        txaComment.setAutoscrolls(false);
        txaComment.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane2.setViewportView(txaComment);

        lstSelectedSymptoms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstSelectedSymptomsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstSelectedSymptoms);

        jLabel3.setText("Sintomas selecionados");

        btnInferingDisease.setText("Buscar");
        btnInferingDisease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInferingDiseaseActionPerformed(evt);
            }
        });

        jLabel4.setText("Comentário do Sintoma");

        lstPossibleDiseases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstPossibleDiseasesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lstPossibleDiseases);

        jLabel5.setText("Possíveis Doenças");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("(Dois cliques para remover)");

        txaCommentDisease.setEditable(false);
        txaCommentDisease.setColumns(20);
        txaCommentDisease.setLineWrap(true);
        txaCommentDisease.setRows(5);
        txaCommentDisease.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane5.setViewportView(txaCommentDisease);

        jLabel7.setText("Comentário da Doença");

        jLabel8.setText("Fonte de Dados: http://dbpedia.org, http://wikidata.org");

        txtSymptomName.setToolTipText("Digite o sintoma");
        txtSymptomName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSymptomNameKeyReleased(evt);
            }
        });

        btnConfirmDisease.setText("Confirmar Doença");
        btnConfirmDisease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmDiseaseActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSymptomName, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnInferingDisease))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(294, 294, 294))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnConfirmDisease))
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(jScrollPane5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnInferingDisease)
                                .addComponent(jLabel5)
                                .addComponent(btnConfirmDisease)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSymptomName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        dbpediaSymptoms = QueriesSparql.getSymptomsFromDbpedia();
        lstSymptoms.setModel(dbpediaSymptoms);

        lstSelectedSymptoms.setModel(selectedSymptoms);

        if (((DefaultListModel) lstSymptoms.getModel()).getSize() > 0) {
            txtSymptomName.setEnabled(true);
        }
    }//GEN-LAST:event_formWindowOpened


    private void lstSymptomsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstSymptomsKeyTyped
        Object o = (Object) lstSymptoms.getSelectedValue();
//        ResourceWeb rw = (ResourceWeb) this.lstSymptoms.getSelectedValues()[0];
        ResourceWeb rw = (ResourceWeb) o;
        txaComment.setText(rw.getComment());
//        System.out.println("lar.telas.FrmFilterDiseasesBySymptoms.lstSymtomsKeyTyped()" + rw);

        selectedSymptoms.addElement(rw);

        btnInferingDisease.setEnabled(true);
    }//GEN-LAST:event_lstSymptomsKeyTyped

    private void lstSymptomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstSymptomsMouseClicked
        Object o = (Object) lstSymptoms.getSelectedValue();
        ResourceWeb rw = (ResourceWeb) o;
        if (evt.getClickCount() == 1) {

            //begin teste query to get comment only
            String comment = QueriesSparql.getCommentFromDbpedia(rw);
            this.txaComment.setText(comment);
            //end
            
            
            
            this.txaComment.setText(rw.getComment());
//            System.out.println("lar.telas.FrmFilterDiseasesBySymptoms.lstSymtomsKeyTyped()" + rw);
        }
        if (evt.getClickCount() == 2) {
//            ResourceWeb rw = (ResourceWeb) lstSymptoms.getSelectedValues()[0];
            this.txaComment.setText(rw.getComment());
//            System.out.println("lar.telas.FrmFilterDiseasesBySymptoms.lstSymtomsKeyTyped()" + rw);
            selectedSymptoms.addElement(rw);

            btnInferingDisease.setEnabled(true);
        }
    }//GEN-LAST:event_lstSymptomsMouseClicked

    private void btnInferingDiseaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInferingDiseaseActionPerformed
        txaCommentDisease.setText("");
        ListModel<String> v = lstSelectedSymptoms.getModel();
        this.possibleDiseases = QueriesSparql.getDiseasesBySymptomnsFromDbpedia(v);
        this.lstPossibleDiseases.setModel(this.possibleDiseases);
    }//GEN-LAST:event_btnInferingDiseaseActionPerformed

    private void lstSelectedSymptomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstSelectedSymptomsMouseClicked
        if (evt.getClickCount() == 2) {
            ResourceWeb rw = (ResourceWeb) this.lstSelectedSymptoms.getSelectedValues()[0];
            this.selectedSymptoms.removeElement(rw);
        }
    }//GEN-LAST:event_lstSelectedSymptomsMouseClicked

    private void lstPossibleDiseasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstPossibleDiseasesMouseClicked
        if (evt.getClickCount() == 1) {
            ResourceWeb rw = (ResourceWeb) this.lstPossibleDiseases.getSelectedValues()[0];

            if (rw != null) {

//                System.out.println("lar.telas.FrmFilterDiseasesBySymptoms.lstPossibleDiseasesMouseClicked()" + rw);
                this.txaCommentDisease.setText(rw.getComment());

                this.btnConfirmDisease.setEnabled(true);
            }
        }
    }//GEN-LAST:event_lstPossibleDiseasesMouseClicked

    private void txtSymptomNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSymptomNameKeyReleased
//        System.out.println("lar.telas.FrmFilterDiseasesBySymptoms.txtSymptomNameInputMethodTextChanged()" + this.txtSymptomName.getText());
        filterModel((DefaultListModel) this.lstSymptoms.getModel(), this.txtSymptomName.getText());
    }//GEN-LAST:event_txtSymptomNameKeyReleased

    private void btnConfirmDiseaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmDiseaseActionPerformed

        Object object = lstPossibleDiseases.getSelectedValue();
        ResourceWeb dbpediaDisease = (ResourceWeb) object;
        
        ResourceWeb wikiDisease = QueriesSparql.getWikidataDiseaseFromDbpediaByDbpediaDisease(dbpediaDisease);

        FrmDisease frmDisease = new FrmDisease();
        frmDisease.setDisease(wikiDisease);
        frmDisease.setVisible(true);
    }//GEN-LAST:event_btnConfirmDiseaseActionPerformed

    // MÉTODOS AUXILIARES
    public void filterModel(DefaultListModel<String> model, String filter) {
        int fere = this.lstSymptoms.getNextMatch(filter, WIDTH, Position.Bias.Forward);
        this.lstSymptoms.setSelectedIndex(fere);
        this.lstSymptoms.setAutoscrolls(true);
        this.lstSymptoms.ensureIndexIsVisible(fere);
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
            java.util.logging.Logger.getLogger(FrmFilterDiseasesBySymptoms.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFilterDiseasesBySymptoms.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFilterDiseasesBySymptoms.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFilterDiseasesBySymptoms.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFilterDiseasesBySymptoms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmDisease;
    private javax.swing.JButton btnInferingDisease;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> lstPossibleDiseases;
    private javax.swing.JList<String> lstSelectedSymptoms;
    private javax.swing.JList<String> lstSymptoms;
    private javax.swing.JTextArea txaComment;
    private javax.swing.JTextArea txaCommentDisease;
    private javax.swing.JTextField txtSymptomName;
    // End of variables declaration//GEN-END:variables
}
