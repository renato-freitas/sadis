package lar.telas;

import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lar.dal.Rdb;
import lar.entidade.Database;
import lar.util.global;

/**
 * @author Renato Freitas
 * @since 2018-06-27
 */
public class FrmEscolherBancoDeDados extends javax.swing.JFrame {

    private boolean statusCamposObrigatorios = true;
    public DefaultListModel listModel;
    public DefaultTreeModel arvBaseDeDados;
    public static Database bdSession;
    public static String nomeBD;

    public DefaultListModel getListModel() {
        return listModel;
    }
    public DefaultTreeModel getArvBaseDeDados(){
        return arvBaseDeDados;
    }

    
    public FrmEscolherBancoDeDados() {
        initComponents();
        listModel = new DefaultListModel();
        arvBaseDeDados = new DefaultTreeModel(new DefaultMutableTreeNode());
        this.carregarCombos();
    }

    private void carregarCombos() {
        for (String SGBD : global.SGBDs){
            cbSgbds.addItem(SGBD); 
        }
    }

    
    /**Retorna a base de dados com as crendeciais de acesso
     * @return BancoDeDados.
     */
    public Database getEntidade() {
        Database bd = new Database();

        if (cbSgbds.getSelectedItem() != "") {
            String itemCombo = cbSgbds.getSelectedItem().toString();
            if (!"".equals(itemCombo)) {
                if (global.SGBDs[1].equals(itemCombo)) {
                    bd.setURL(global.MYSQL_URL);
                }
                if (global.SGBDs[2].equals(itemCombo)) {
                    bd.setURL(global.POSTGRES_URL);
                }
            }
        } else {
            statusCamposObrigatorios = false;
        }
        if (!"".equals(this.txtDataset.getText())) {
            bd.setName(this.txtDataset.getText());
        } else {
            statusCamposObrigatorios = false;
        }
        if (!"".equals(this.txtUsuario.getText())) {
            bd.setUser(this.txtUsuario.getText());
        } else { 
            statusCamposObrigatorios = false;
        }
        String pwd = new String(this.txtSenha.getPassword());
        if (!"".equals(pwd)) {
            bd.setPassword(pwd);
        } else {
            statusCamposObrigatorios = false;
        }
        bdSession = bd;
        return bd;
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
        lblChooseServer = new javax.swing.JLabel();
        cbSgbds = new javax.swing.JComboBox<>();
        lblDataset = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtDataset = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnOk = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose Dataset"));

        lblChooseServer.setText("Server");

        lblDataset.setText("Dataset Name");

        lblUsuario.setText("User");

        lblSenha.setText("Password");

        txtSenha.setToolTipText("");

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancelar.setText("Close");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(lblChooseServer)
                    .addComponent(cbSgbds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataset)
                    .addComponent(lblUsuario)
                    .addComponent(lblSenha)
                    .addComponent(txtDataset)
                    .addComponent(txtSenha)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnOk});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChooseServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSgbds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDataset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancelar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(378, 342));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed

        Database bd = this.getEntidade();

        if (statusCamposObrigatorios) {
            List<String> tb = Rdb.getTables(bd);
            if (tb != null) {
                Iterator it_tab = tb.iterator();

                DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(bd.getName());

                while (it_tab.hasNext()) {
                    String tableName = (String) it_tab.next();
                    System.out.println("[*** Table]" + tableName);

                    Rdb.getPKOfTable(tableName);
                    Rdb.getColumnsOfTable(tableName);

                    DefaultMutableTreeNode tabela = new DefaultMutableTreeNode(tableName);

                    for (String col : Rdb.columns) {
                        System.out.println("\t[*** Colunas]" + col);

                        for (String pk : Rdb.pks) {

                            if (pk.equals(col)) {
                                System.out.println("\t\t[*** pk]: " + pk);
                                listModel.addElement(tableName + ":" + col + " (pk)");
                                DefaultMutableTreeNode coluna = new DefaultMutableTreeNode(col+" (pk)");
                                tabela.add(coluna);
                            } else {
                                listModel.addElement(tableName + ":" + col);
                                DefaultMutableTreeNode coluna = new DefaultMutableTreeNode(col);
                                tabela.add(coluna);
                            }                            
                        }
                    }
                    raiz.add(tabela);
                }
                arvBaseDeDados.setRoot(raiz);
        
                JOptionPane.showMessageDialog(null, "Successful Connection!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Was not possible find the dataset!");
            }
        } else {
            global.exibeMenssagemDeErro();
            statusCamposObrigatorios = true;
        }
    }//GEN-LAST:event_btnOkActionPerformed

    
                                   

    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaEscolherDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaEscolherDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaEscolherDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaEscolherDataset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TelaEscolherDataset().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> cbSgbds;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChooseServer;
    private javax.swing.JLabel lblDataset;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtDataset;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
