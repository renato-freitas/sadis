package lar.jena;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;


/**
 * @author Renato Freitas
 */
public class Ontology {
    
    /**
     * Obtém a Ontologia de Domínio.
     * @return OntModel - Ontologia de Domínio.
     * @param file Arquivo da Ontologia de Domínio.
     */
    public static OntModel getOntology(File file) {

        OntModel model = ModelFactory.createOntologyModel();
//        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        try {
            FileInputStream in = new FileInputStream(file);
//            model.read(in, "");
            if (in == null) {
                throw new IllegalArgumentException("File: " + file + " not found");
            }
            model.read(in, null, "TURTLE");
            in.close();
        } catch (Exception e) {
            System.out.println("Ocorreu esse erro: " + e);
        }

        model.write(System.out);
        return model;
    }

   
    /**
     * Retorna uma lista OntClass com todas as classes da ontologia.
     * @param od - Ontologia de Domínio
     * @return Lista de OntClass
     */
    public static List<OntClass> getClasses(OntModel od) {
        List<OntClass> classes = new ArrayList<>();
        Iterator iter;
        iter = od.listClasses();
        while(iter.hasNext()){
            OntClass oc = (OntClass)  iter.next();
            System.out.println("[*** Classes da OD] "+oc);
            classes.add(oc);
        }
        return classes;
    }

    /**
     * Retorna uma lista de String com todas as propriedades objetos da ontologia.
     * @param od - Ontologia de Domínio
     * @return Lista de Propriedades em string
     */
    public static List<String> getProperties(OntModel od) {
        List<String> propriedades = new ArrayList<>();
        Iterator iter;
        iter = od.listObjectProperties();
        while(iter.hasNext()){
            ObjectProperty op = (ObjectProperty) iter.next();
            System.out.println("[*** ObjectProperties] " +op.getLocalName());
            propriedades.add(op.getLocalName());
        }
        return propriedades;
    }

    
    
    
    /**
     * Retorna uma lista DatatypeProperty com todos os tipos de dados da ontologia.
     * @param od - Ontologia de Domínio.
     * @return Lista de DatatypeProperty.
     */
    public static List<DatatypeProperty> getDatatypes(OntModel od) {
        List<DatatypeProperty> data = new ArrayList<>();
        Iterator iter;
        iter = od.listDatatypeProperties();
        while(iter.hasNext()){
            DatatypeProperty dtp = (DatatypeProperty)  iter.next();
//            System.out.println("[***Data] "+dtp.getLocalName());
            data.add(dtp);
        }
        return data;
    }
    
    
    /**
     * Retorna um Map de prefixo e url dos namespace da Ontologia de Domínio.
     * @param od - Ontologia de Domínio
     * @return Map<prefixo, url> em string.
     */
    public static Map<String, String> getOntologyPrefixies(OntModel od){
        Map<String, String> prefixos =  od.getNsPrefixMap();
        return prefixos;
    }
    
}
