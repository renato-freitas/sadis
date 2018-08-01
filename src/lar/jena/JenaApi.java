/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.jena;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import javax.swing.DefaultListModel;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 *
 * @author renato
 */
public class JenaApi {

//    public static List<String> classes;
//    public static List<String> objectProperties;
//    public static List<String> datatypes;
//    public DefaultListModel listModel;
//    
//    public DefaultListModel getListModel(){
//        return listModel;
//    }
    public static void getOntologiaDominio() {
        final String inputFileName = "resources/sida.rdf";
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        // write it to standard out
        model.write(System.out);
    }

    /**
     * Obtém a Ontologia de Domínio.
     *
     * @return OntModel - Ontologia de Domínio.
     * @param file Arquivo da Ontologia de Domínio.
     */
    public static OntModel obtemOntologiaDominio(File file) {

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        try {
            FileInputStream in = new FileInputStream(file);
            model.read(in, "");
            in.close();
        } catch (Exception e) {
            System.out.println("Ocorreu esse erro: " + e);
        }

        model.write(System.out);
        return model;
    }

    /**
     * This method read and shows all class of ontology file.
     */
    public static DefaultListModel obtemClassesDaOntology(OntModel od) {
        DefaultListModel classes = new DefaultListModel();
        Iterator iter;
        for (iter = od.listNamedClasses(); iter.hasNext();) {
            OntClass oc = (OntClass) iter.next();
            System.out.println("[***Classes] " + oc.getLocalName());
//                        classes.add(oc.getLocalName());
            classes.addElement(oc.getLocalName());
            
        }

        return classes;
    }

    /**
     * This method read and shows all object properties of ontology file
     */
    public static void obtemPredicadosDaOntologia(OntModel od) {
        Iterator iter;
        for (iter = od.listObjectProperties(); iter.hasNext();) {
            OntProperty op = (OntProperty) iter.next();
            System.out.println("[***ObjectProperties] " + op.getLocalName());
        }
    }

    /**
     * Ler e mostra todos os datatypes da ontologia.
     */
    public static void obtemDatatypesDaOntologia(OntModel od) {
        Iterator iter;
        for (iter = od.listDatatypeProperties(); iter.hasNext();) {
            DatatypeProperty dtp = (DatatypeProperty) iter.next();
            //System.out.println(dtp.getLocalName());
            System.out.println("[***Dataproperties] " + dtp.getURI());

        }
    }

}
