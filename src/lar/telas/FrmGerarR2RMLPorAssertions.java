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

/**
 *
 * @author renato
 */
public class FrmGerarR2RMLPorAssertions extends javax.swing.JFrame {

    /**
     * Creates new form FrmGerarR2RML
     */
    public FrmGerarR2RMLPorAssertions() {
        initComponents();
        preencheArvoreComListaAsserts(SidaUI.listaDosAsserts);
        mudaIcone(this.treeListaAsserts);
    }

    
    /**Preencher a árvoe da Ontologia de Domínio*/
    private void preencheArvoreComListaAsserts(List<Assertion> lstAsserts) {
        
        if(lstAsserts != null | lstAsserts.size() > 0){
            DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("All Assertions");

        
        for (Assertion asst : lstAsserts) {
            DefaultMutableTreeNode cls = new DefaultMutableTreeNode(asst.getOrigem()+" --> "+asst.getAlvo());
            raiz.add(cls);
        }
        
        
        DefaultTreeModel arvoreListAssert = new DefaultTreeModel(raiz);

        this.treeListaAsserts.setModel(arvoreListAssert);
        }
        else{
            new Exception().getStackTrace();
        }
    }
    
    public void mudaIcone(JTree tree) {
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            private final Icon setaIcon = new ImageIcon(getClass().getResource("/lar/resources/img/seta_direita.png"));

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean selected, boolean expanded, boolean isLeaf, int row,
                    boolean focused) {
                Component c = super.getTreeCellRendererComponent(tree, value, selected,
                        expanded, isLeaf, row, focused);
                if(isLeaf == true){
                    setIcon(setaIcon);
                }
                return c;
            }
        });
    }
    
    
    
    protected void deletaItensSelecionados() {
        DefaultMutableTreeNode node = null;
        DefaultTreeModel model = (DefaultTreeModel) (treeListaAsserts.getModel());
        TreePath[] paths = treeListaAsserts.getSelectionPaths();
        for (int i = 0; i < paths.length; i++) {
            node = (DefaultMutableTreeNode) (paths[i].getLastPathComponent());
            model.removeNodeFromParent(node);
            System.out.println("nó deletado: "+node);
        }
        
        
        Iterator<Assertion> iterator = SidaUI.listaDosAsserts.iterator();
        while (iterator.hasNext()) {
            Assertion os = iterator.next();
            System.out.println("Assert atual: " + os.getOrigem() + os.getAlvo());
            if ((os.getOrigem() +" --> "+ os.getAlvo()).equals(node.toString())) {
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
        treeListaAsserts = new javax.swing.JTree();
        btnGerarR2RML = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(treeListaAsserts);

        btnGerarR2RML.setText("To Generate R2RML File");
        btnGerarR2RML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarR2RMLActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancel");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remove");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnVoltar.setText("Back");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGerarR2RML)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGerarR2RML)
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemover)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(969, 551));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        SidaUI.listaDosAsserts.clear();
        SidaUI.colunasParaSQL = "";
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGerarR2RMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarR2RMLActionPerformed
        new FrmGerarDumpPorArquivoR2RML().setVisible(true);
    }//GEN-LAST:event_btnGerarR2RMLActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        deletaItensSelecionados();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGerarR2RMLPorAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGerarR2RMLPorAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGerarR2RMLPorAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGerarR2RMLPorAssertions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGerarR2RMLPorAssertions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGerarR2RML;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treeListaAsserts;
    // End of variables declaration//GEN-END:variables
}
