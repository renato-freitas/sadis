/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.telas;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import lar.entidade.Assertion;
import lar.util.Comum;

/**
 *
 * @author renato
 */
public class FrmGenerateR2rmlByAssertions extends javax.swing.JFrame {

    /**
     * Creates new form FrmGenerateR2rmlByAssertions
     */
    public FrmGenerateR2rmlByAssertions() {
        initComponents();
        this.fillTreeWithAssertions(FrmPrincipal.assertionsList);
        Comum.changeIcon(this.treeAssertions);
//        mudaIcone(this.treeAssertions);
    }

    /**
     * Preencher a árvoe da Ontologia de Domínio
     */
    private void fillTreeWithAssertions(List<Assertion> lstAsserts) {

        if (lstAsserts != null | lstAsserts.size() > 0) {
            DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("All Assertions");

            for (Assertion asst : lstAsserts) {
                DefaultMutableTreeNode cls = new DefaultMutableTreeNode(asst.getOrigem() + " --> " + asst.getAlvo());
                raiz.add(cls);
            }

            DefaultTreeModel arvoreListAssert = new DefaultTreeModel(raiz);

            this.treeAssertions.setModel(arvoreListAssert);
        } else {
            new Exception().getStackTrace();
        }
    }

    protected void deletaItensSelecionados() {
        DefaultMutableTreeNode node = null;
        DefaultTreeModel model = (DefaultTreeModel) (this.treeAssertions.getModel());
        TreePath[] paths = this.treeAssertions.getSelectionPaths();
        for (int i = 0; i < paths.length; i++) {
            node = (DefaultMutableTreeNode) (paths[i].getLastPathComponent());
            model.removeNodeFromParent(node);
            System.out.println("nó deletado: " + node);
        }

        Iterator<Assertion> iterator = SidaUI.listaDosAsserts.iterator();
        while (iterator.hasNext()) {
            Assertion os = iterator.next();
            System.out.println("Assert atual: " + os.getOrigem() + os.getAlvo());
            if ((os.getOrigem() + " --> " + os.getAlvo()).equals(node.toString())) {
                // Aqui aplica sua lógica
                System.out.println("Assert: " + os.getOrigem() + os.getAlvo());
                //System.out.println("Node:" + node.toString());
                //System.out.println("Deletou o assert da lista de Asserts: "+node);
                SidaUI.listaDosAsserts.remove(os);
                break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        treeAssertions = new javax.swing.JTree();
        btnBack = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnGenerateR2rml = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generate R2RML by Assertions");

        jScrollPane1.setViewportView(treeAssertions);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnGenerateR2rml.setText("To generate R2RML file");
        btnGenerateR2rml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateR2rmlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerateR2rml, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnCancel, btnRemove});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnGenerateR2rml))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(789, 479));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        deletaItensSelecionados();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        SidaUI.listaDosAsserts.clear();
        SidaUI.colunasParaSQL = "";
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnGenerateR2rmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateR2rmlActionPerformed
        new FrmDumpByR2rml().setVisible(true);
    }//GEN-LAST:event_btnGenerateR2rmlActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGenerateR2rmlByAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGenerateR2rmlByAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGenerateR2rmlByAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGenerateR2rmlByAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGenerateR2rmlByAssertions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGenerateR2rml;
    private javax.swing.JButton btnRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treeAssertions;
    // End of variables declaration//GEN-END:variables
}
