package lar.telas;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import lar.dal.Rdb;
import lar.entidade.Assertion;
import lar.entidade.Database;
import lar.jena.Ontology;
import lar.util.global;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

/**
 *
 * @author Renato Freitas
 * @since 27/06/2018
 */
public class FrmPrincipal extends javax.swing.JFrame {

    public static OntModel domainOntology;
    public static String domainOntologyName = "File Name";
    public static String ontologyUrl = "";
    public static String columnsToSQL = "";
    public static List<Assertion> assertionsList;
    private String assertBD = "";
    private String assertOD = "";

    public FrmPrincipal() {
        initComponents();
        this.arvDatabase.setModel(null);
        this.arvOntology.setModel(null);
        assertionsList = new ArrayList<>();
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
        jButton1 = new javax.swing.JButton();
        btnOpenManagerRdf = new javax.swing.JButton();
        tabMapping = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        arvDatabase = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        arvOntology = new javax.swing.JTree();
        btnOpenOntology = new javax.swing.JButton();
        txtOD = new javax.swing.JTextField();
        btnOpenDB = new javax.swing.JButton();
        txtBD = new javax.swing.JTextField();
        btnAbrirFrmAssertions = new javax.swing.JButton();
        lblDatasetSchema = new javax.swing.JLabel();
        lblOntTargetVocabulary = new javax.swing.JLabel();
        txtAssertions = new javax.swing.JTextField();
        btnSaveAssertions = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaLinkSpec = new javax.swing.JTextArea();
        lblDatasetSource = new javax.swing.JLabel();
        lblDatasetTarget = new javax.swing.JLabel();
        cbDatasetSource = new javax.swing.JComboBox<>();
        cbDatasetTarget = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lblCompareProperty = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbCompareProperty = new javax.swing.JComboBox<>();
        cbCompareType = new javax.swing.JComboBox<>();
        btnAddCompare = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbEditLinkSpecFile = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnCloseNoSave = new javax.swing.JButton();
        btnSetLinkSpec = new javax.swing.JButton();
        btnSaveOtherFolder = new javax.swing.JButton();
        btnBringPaper = new javax.swing.JButton();
        btnDiseases = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("SADIS - Semi Automatic Data Integration Suite");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnOpenManagerRdf.setText("RDFs");
        btnOpenManagerRdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenManagerRdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpenManagerRdf)
                .addGap(93, 93, 93)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(btnOpenManagerRdf))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        arvDatabase.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                arvDatabaseValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(arvDatabase);

        arvOntology.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                arvOntologyValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(arvOntology);

        btnOpenOntology.setText("Select Domain Ontology");
        btnOpenOntology.setName("btnSelectDomainOntology"); // NOI18N
        btnOpenOntology.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenOntologyActionPerformed(evt);
            }
        });

        btnOpenDB.setText("Open Dataset");
        btnOpenDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenDBActionPerformed(evt);
            }
        });

        btnAbrirFrmAssertions.setText("See all Assertions");
        btnAbrirFrmAssertions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirFrmAssertionsActionPerformed(evt);
            }
        });

        lblDatasetSchema.setText("Dataset (Schema)");

        lblOntTargetVocabulary.setText("Ontology Target (Vocabulary)");

        btnSaveAssertions.setText("Save Assertions");
        btnSaveAssertions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAssertionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAssertions, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveAssertions, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDatasetSchema)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblOntTargetVocabulary)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnOpenOntology, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOpenDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOD)
                                .addComponent(txtBD))
                            .addComponent(btnAbrirFrmAssertions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jScrollPane3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatasetSchema)
                    .addComponent(lblOntTargetVocabulary))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnOpenOntology)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpenDB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbrirFrmAssertions)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAssertions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveAssertions))
                .addContainerGap())
        );

        tabMapping.addTab("Mapping", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAreaLinkSpec.setColumns(20);
        txtAreaLinkSpec.setRows(5);
        jScrollPane1.setViewportView(txtAreaLinkSpec);

        lblDatasetSource.setText("Dataset Source");

        lblDatasetTarget.setText("Dataset Target");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCompareProperty.setText("Compare Property");

        jLabel1.setText("Compare Type");

        btnAddCompare.setText("Add");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lblCompareProperty)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(cbCompareProperty, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cbCompareType, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnAddCompare)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbCompareProperty, cbCompareType});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblCompareProperty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbCompareProperty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCompareType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddCompare, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel3.setText("Edit Links Spec File");

        btnSave.setText("Save");

        btnCloseNoSave.setText("Close no save");

        btnSetLinkSpec.setText("Specify Semantic Links");

        btnSaveOtherFolder.setText("Save in other folder");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDatasetSource, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbDatasetTarget, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(btnSave)
                    .addComponent(btnCloseNoSave)
                    .addComponent(btnSaveOtherFolder)
                    .addComponent(btnSetLinkSpec)
                    .addComponent(lblDatasetSource)
                    .addComponent(lblDatasetTarget)
                    .addComponent(cbEditLinkSpecFile, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCloseNoSave, btnSave, btnSaveOtherFolder, btnSetLinkSpec});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDatasetSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbDatasetSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDatasetTarget)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDatasetTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEditLinkSpecFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCloseNoSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveOtherFolder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(btnSetLinkSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        tabMapping.addTab("Link Specifications", jPanel3);

        btnBringPaper.setText("Bring Papers");
        btnBringPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBringPaperActionPerformed(evt);
            }
        });

        btnDiseases.setText("Inferir Doença");
        btnDiseases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiseasesActionPerformed(evt);
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
                    .addComponent(tabMapping, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBringPaper)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDiseases)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBringPaper)
                    .addComponent(btnDiseases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(tabMapping, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1091, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Retorna o prefixo do NameSpace
     *
     * @param datatype url do DatatypeProperty.
     * @param od Ontologia de Domínio.
     * @return O prefixo do NameSpace definido na OD.
     */
    private String getNSPrefix(String datatype, OntModel od) {
        String prefixo = "";
        String url = "";
        String prop = "";
        if (!datatype.contains("#")) {
            url = global.cortaAte(datatype, '/');
            prop = global.cortaDepoisDe(datatype, '/');

        } else {
            url = global.cortaAte(datatype, '#');
            prop = global.cortaDepoisDe(datatype, '#');
        }
        System.out.println("url do NameSpace => " + url);

        for (Map.Entry<String, String> map : Ontology.getOntologyPrefixies(od).entrySet()) {
            System.out.println(global.printTab("Map<String, String>: " + map.getKey() + ":" + map.getValue()));
            if (map.getValue() == null ? url == null : map.getValue().equals(url)) {
                prefixo = map.getKey() + ":" + prop;
                System.out.println("Dentro do if => retorno no método retornarPrefixoNs: " + prefixo);
                return prefixo;
            }
        }
        return prefixo;
    }

    /**
     * Fill the Domain Ontology Tree.
     */
    private void fillOntoTree(OntModel ontologia, String nomeArquivo) {
        //root
        DefaultMutableTreeNode onto = new DefaultMutableTreeNode(nomeArquivo);

        DefaultMutableTreeNode classes = new DefaultMutableTreeNode("Classes");
        DefaultMutableTreeNode propriedades = new DefaultMutableTreeNode("Properties");
        DefaultMutableTreeNode dados = new DefaultMutableTreeNode("Datatype");

        for (OntClass classe : Ontology.getClasses(ontologia)) {
            System.out.println("[*** OntClass within tree]" + classe.getLocalName());
            DefaultMutableTreeNode cls = new DefaultMutableTreeNode(classe.getLocalName());
            classes.add(cls);
        }
        for (String propObjeto : Ontology.getProperties(ontologia)) {
            DefaultMutableTreeNode po = new DefaultMutableTreeNode(propObjeto);
            propriedades.add(po);
        }
        for (DatatypeProperty dado : Ontology.getDatatypes(ontologia)) {
            System.out.println(global.printTab("Datatypes da ontologia " + dado.toString()));
            String d = dado.toString();

//          usae map<String prefixo, String url> here.
            String pre = this.getNSPrefix(d, ontologia);
            System.out.println(global.printTab("prefixo encontrado: " + pre));

            DefaultMutableTreeNode dp = new DefaultMutableTreeNode(pre);
            dados.add(dp);
        }
        onto.add(classes);
        onto.add(propriedades);
        onto.add(dados);

        DefaultTreeModel arvoreOD = new DefaultTreeModel(onto);

        this.arvOntology.setModel(arvoreOD);
    }

    /**
     * Este método adicionar um ícone ao nó correspondente.
     *
     * @param tree - Árvore
     */
    public void changeIcon(JTree tree) {
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            private final Icon pkIcon = new ImageIcon(getClass().getResource("/lar/resources/img/pk.png"));
            private final Icon setaDireitaIcon = new ImageIcon(getClass().getResource("/lar/resources/img/seta_direita.png"));

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean selected, boolean expanded, boolean isLeaf, int row,
                    boolean focused) {
                Component componente = super.getTreeCellRendererComponent(tree, value, selected,
                        expanded, isLeaf, row, focused);
                if (value.toString().contains("(pk)")) {
                    setIcon(pkIcon);
                } else if (isLeaf == true) {
                    setIcon(setaDireitaIcon);
                }
                return componente;
            }
        });
    }

    /*EVENTOS*/

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        new SidaUI().setVisible(true);
        new FrmSparql().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOpenOntologyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenOntologyActionPerformed
        File f;
        try {
            f = global.chooseFile();
            domainOntologyName = f.getName();

            domainOntology = Ontology.getOntology(f);
//            global.print("frmPrincipal", "btnOpenOntology()", domainOntology.getNsPrefixURI(""));

            ontologyUrl = domainOntology.getNsPrefixURI("");
            this.txtOD.setText(domainOntologyName);
            this.fillOntoTree(domainOntology, domainOntologyName);
        } catch (FileNotFoundException e) {
        }
    }//GEN-LAST:event_btnOpenOntologyActionPerformed

    private void btnOpenDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenDBActionPerformed
        //Necessário instanciar
//        FrmEscolherBancoDeDados frmBD = new FrmEscolherBancoDeDados();
        FrmChooseDatabase frmCdb = new FrmChooseDatabase();
        frmCdb.setVisible(true);

//        Database bd = new Database();
//        bd.setServer(Comum.SGBDs[2]);
//        bd.setName("Sadis");
//        bd.setURL(Comum.POSTGRES_URL);
//        bd.setUser("postgres");
//        bd.setPassword("r00t");
//        List<String> tb = Rdb.getTables(bd);
//        Iterator it = tb.iterator();
//        while(it.hasNext()){
//            String out = it.next().toString();
//            System.out.println(Comum.printTab(out));
//            Rdb.getColumnsOfTable(out);
//        }
        this.txtBD.setText(global.NOME_BD_MYSQL);
        this.arvDatabase.setModel(frmCdb.arvBaseDeDados);
        changeIcon(arvDatabase);
    }//GEN-LAST:event_btnOpenDBActionPerformed

    private void btnAbrirFrmAssertionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirFrmAssertionsActionPerformed
//        new FrmGerarR2RMLPorAssertions().setVisible(true);
        new FrmGenerateR2rmlByAssertions().setVisible(true);
    }//GEN-LAST:event_btnAbrirFrmAssertionsActionPerformed

    private void btnSaveAssertionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAssertionsActionPerformed
        Assertion assertion = new Assertion();
        assertion.setOrigem(assertBD);
        assertion.setAlvo(assertOD);

        System.out.println("[***Assertion]" + assertion.getOrigem() + assertion.getAlvo());
        assertionsList.add(assertion);
        this.txtAssertions.setBackground(Color.WHITE);
        this.txtAssertions.setText("");
    }//GEN-LAST:event_btnSaveAssertionsActionPerformed

    private void arvDatabaseValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_arvDatabaseValueChanged
        assertBD = "";
        assertBD = arvDatabase.getSelectionPath().toString();
        this.txtAssertions.setText(assertBD);
    }//GEN-LAST:event_arvDatabaseValueChanged

    private void arvOntologyValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_arvOntologyValueChanged
        assertOD = "";
        assertOD = arvOntology.getSelectionPath().toString();
        this.txtAssertions.setText(assertBD + " --> " + assertOD);
        this.txtAssertions.setBackground(Color.GREEN);
    }//GEN-LAST:event_arvOntologyValueChanged

    private void btnOpenManagerRdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenManagerRdfActionPerformed
        // TODO add your handling code here:
//        new FrmManagerRDF().setVisible(true);
        new FrmSparql().setVisible(true);
    }//GEN-LAST:event_btnOpenManagerRdfActionPerformed

    private void btnDiseasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiseasesActionPerformed
        // TODO add your handling code here:
        new FrmFilterDiseasesBySymptoms().setVisible(true);
    }//GEN-LAST:event_btnDiseasesActionPerformed

    private void btnBringPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBringPaperActionPerformed
        // TODO add your handling code here:
        new FrmFindPaperByTheme().setVisible(true);
    }//GEN-LAST:event_btnBringPaperActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new FrmPrincipal().setVisible(true);
                new FrmFilterDiseasesBySymptoms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arvDatabase;
    private javax.swing.JTree arvOntology;
    private javax.swing.JButton btnAbrirFrmAssertions;
    private javax.swing.JButton btnAddCompare;
    private javax.swing.JButton btnBringPaper;
    private javax.swing.JButton btnCloseNoSave;
    private javax.swing.JButton btnDiseases;
    private javax.swing.JButton btnOpenDB;
    private javax.swing.JButton btnOpenManagerRdf;
    private javax.swing.JButton btnOpenOntology;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveAssertions;
    private javax.swing.JButton btnSaveOtherFolder;
    private javax.swing.JButton btnSetLinkSpec;
    private javax.swing.JComboBox<String> cbCompareProperty;
    private javax.swing.JComboBox<String> cbCompareType;
    private javax.swing.JComboBox<String> cbDatasetSource;
    private javax.swing.JComboBox<String> cbDatasetTarget;
    private javax.swing.JComboBox<String> cbEditLinkSpecFile;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCompareProperty;
    private javax.swing.JLabel lblDatasetSchema;
    private javax.swing.JLabel lblDatasetSource;
    private javax.swing.JLabel lblDatasetTarget;
    private javax.swing.JLabel lblOntTargetVocabulary;
    private javax.swing.JTabbedPane tabMapping;
    private javax.swing.JTextArea txtAreaLinkSpec;
    private javax.swing.JTextField txtAssertions;
    private javax.swing.JTextField txtBD;
    private javax.swing.JTextField txtOD;
    // End of variables declaration//GEN-END:variables
}
