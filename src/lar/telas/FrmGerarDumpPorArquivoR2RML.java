package lar.telas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lar.entidade.Assertion;
import lar.negocio.TemplatesR2RML;
import lar.negocio.Transforma;
import static lar.telas.SidaUI.colunasParaSQL;
import lar.util.Functions;

/**
 *
 * @author Renato Freitas
 */
public class FrmGerarDumpPorArquivoR2RML extends javax.swing.JFrame {

    static String  nomeArquivoTtl;
    public FrmGerarDumpPorArquivoR2RML() {
        initComponents();
        geraR2RMLCustomizado();
    }
    
    
   
    
    private void geraR2RMLCustomizado(){
        areaArquivoR2RML.append(TemplatesR2RML.getPrefixies());
        geraMapeamentoDeClasse();
    }
    
    
    
    
    
    
    
    private void geraMapeamentoDeClasse(){
        String nomeDaTabelaAtual = "";
        String colunas = "";
        
        for(Assertion assercao : SidaUI.listaDosAsserts){
            String[] coluna = assercao.getOrigem().split(", ");

            if(coluna[2].contains("pk")){
                colunasParaSQL += coluna[2].substring(0, coluna[2].lastIndexOf(" ")) + ", ";
            }else{
                colunasParaSQL += coluna[2].substring(0, coluna[2].lastIndexOf("]")) + ", "; //nome da coluna
            }
        }
        
        for(Assertion assercao : SidaUI.listaDosAsserts){
            
            String[] origem = assercao.getOrigem().split(", ");
            String[] alvo = assercao.getAlvo().split(", ");
            
//            System.out.println(Comum.printTab(alvo[1]));
            String origem2 = origem[2].substring(0, origem[2].lastIndexOf("]")); //nome da coluna
            String alvo2 = alvo[2].substring(0, alvo[2].lastIndexOf("]")); //vocabulário da ontologia
            
            
            
            if(alvo[1].contains("Classes")){
                Functions.printTab("Gerando R2RML: dentro de if Classes");
                
                String pk = origem[2].substring(0, origem[2].lastIndexOf(" "));
                colunas += pk;
                nomeDaTabelaAtual = origem[1];
                areaArquivoR2RML.append(TemplatesR2RML.mapeaTabelaLogica(origem[1], colunasParaSQL));
                areaArquivoR2RML.append(TemplatesR2RML.mapeaSujeito(Functions.prefixoOD(), alvo2, pk));
            }
            else{
                colunas += origem2;
            }
            if(alvo[1].contains("Datatype")){
                areaArquivoR2RML.append(TemplatesR2RML.mapeaPredicadoObjeto(Functions.prefixoOD(), alvo2, origem2));
            }
        }
    }
    
    
    public void salvaArquivo() {
        JFileChooser caixa_dialogo = new JFileChooser();
        int retorno = caixa_dialogo.showSaveDialog(areaArquivoR2RML);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivo = caixa_dialogo.getSelectedFile();
            try {
                BufferedWriter grava = new BufferedWriter(new FileWriter(arquivo));
                grava.write(areaArquivoR2RML.getText());
                grava.close();
            } catch (IOException e) {
            }
        }
    }
    
    /** Salva o arquivo ttl gerado na pasta temporária /tmp
     */
    public void salvaArquivoTemp(){
        nomeArquivoTtl = JOptionPane.showInputDialog(null, "Type a file ttl name", "*.ttl");
        
                        
        File file = null;
        FileWriter out = null;
        try {
            file = new File("/tmp/" + nomeArquivoTtl);
            out = new FileWriter(file);
            out.write(areaArquivoR2RML.getText());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        areaArquivoR2RML = new javax.swing.JTextArea();
        btnSavar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGerarDump = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        areaArquivoR2RML.setColumns(20);
        areaArquivoR2RML.setRows(5);
        jScrollPane1.setViewportView(areaArquivoR2RML);

        btnSavar.setText("Save");
        btnSavar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancel");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGerarDump.setText("To Generate Dump");
        btnGerarDump.setEnabled(false);
        btnGerarDump.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarDumpActionPerformed(evt);
            }
        });

        btnFechar.setText("Close");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGerarDump, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSavar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSavar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnFechar)
                        .addGap(27, 27, 27)
                        .addComponent(btnGerarDump)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(919, 586));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        areaArquivoR2RML = null;
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSavarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavarActionPerformed
        salvaArquivoTemp();
        btnGerarDump.setEnabled(true);
    }//GEN-LAST:event_btnSavarActionPerformed

    private void btnGerarDumpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarDumpActionPerformed
        new Transforma(nomeArquivoTtl, FrmEscolherBancoDeDados.bdSession);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnGerarDumpActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFecharActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGerarDumpPorArquivoR2RML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGerarDumpPorArquivoR2RML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGerarDumpPorArquivoR2RML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGerarDumpPorArquivoR2RML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGerarDumpPorArquivoR2RML().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaArquivoR2RML;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGerarDump;
    private javax.swing.JButton btnSavar;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

