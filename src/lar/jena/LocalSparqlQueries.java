package lar.jena;

import java.io.InputStream;
import lar.util.Constants;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;

public class LocalSparqlQueries {
    final static String QUERY_SYMPTOMS_BY_INSTANCIAS_OR_SUBCLASS = 
            Constants.WIKI_RESOURCE_PREFIX + Constants.WIKI_PROPERTY_PREFIX + Constants.RDFS_PREFIX + Constants.SCHEMA_PREFIX
            + "SELECT DISTINCT * WHERE {"
            + "?symptom wdt:P31|wdt:P279 " + Constants.WD_Q169872_SYMPTOM + " ;"
            + " rdfs:label ?ptLabel ."
            + "OPTIONAL { ?symptom schema:description ?description . FILTER(LANG(?description)=\"pt\") .}"
            + "FILTER(LANG(?ptLabel)=\"pt\")"
            + "}";

    final static String QUERY_ANY_RESOURCE_BY_ONTOLOGY_SYMPTOM_IDENTIFIER = 
            Constants.WIKI_RESOURCE_PREFIX + Constants.WIKI_PROPERTY_PREFIX + Constants.RDFS_PREFIX + Constants.SCHEMA_PREFIX
            + "SELECT DISTINCT * WHERE {"
            + "?s " + Constants.WDT_P8656_SYMPTOM_ONTOLOGY_ID + " ?osi ;\n"
            + "            rdfs:label ?label . \n"
            + "    OPTIONAL { ?s schema:description ?description . FILTER(LANG(?description)=\"pt\") .}\n"
            + "    FILTER(LANG(?label)=\"pt\") ."
            + "}";

    final static String QUERY_UNION_SYMPTOM_BY_INSTANCE_OR_SUBCLASS_AND_ANY_RESOURCE_WITH_ONTOLOGY_SYMPTOM_IDENTIFIER = 
            Constants.WIKI_RESOURCE_PREFIX + Constants.WIKI_PROPERTY_PREFIX + Constants.RDFS_PREFIX + Constants.SCHEMA_PREFIX
            + "SELECT DISTINCT * WHERE {"
            + "{\n"
            + "    ?symptom wdt:P31|wdt:P279 " + Constants.WD_Q169872_SYMPTOM + " .\n"
            + "    ?symptom rdfs:label ?symptomLabel .\n"
            + "    OPTIONAL { ?symptom schema:description ?symptomComment . FILTER(LANG(?symptomComment)=\"pt\") .}\n"
            + "    FILTER(LANG(?symptomLabel)=\"pt\") .\n"
            + "  }\n"
            + "  UNION\n"
            + "  { \n"
            + "    ?signal " + Constants.WDT_P8656_SYMPTOM_ONTOLOGY_ID + " ?osi ;\n"
            + "            rdfs:label ?signalLabel . \n"
            + "    OPTIONAL { ?signal schema:description ?signalComment . FILTER(LANG(?signalComment)=\"pt\") .}\n"
            + "    FILTER(LANG(?signalLabel)=\"pt\") . \n"
            + "  } \n"
            + "}\n";
//            + "ORDER BY ?symptomLabel ?signalLabel";

    final static String QUERY_UNION_INSTANCE_SUBCLASS_AND_OSI_NO_DUPLICATE = Constants.WIKI_RESOURCE_PREFIX + Constants.WIKI_PROPERTY_PREFIX + Constants.RDFS_PREFIX + Constants.SCHEMA_PREFIX
            + "SELECT DISTINCT * WHERE {"
            + "{\n"
            + "    ?symptom wdt:P31|wdt:P279 " + Constants.WD_Q169872_SYMPTOM + " .\n"
            + "    ?symptom rdfs:label ?symptomLabel .\n"
            + "    OPTIONAL { ?symptom schema:description ?symptomComment . FILTER(LANG(?symptomComment)=\"pt\") .}\n"
            + "    FILTER(LANG(?symptomLabel)=\"pt\") .\n"
            + "  }\n"
            + "  UNION\n"
            + "  { \n"
            + "    ?s " + Constants.WDT_P8656_SYMPTOM_ONTOLOGY_ID + "?osi ."
            + "    ?s !(wdt:P31|wdt:P279) " + Constants.WD_Q169872_SYMPTOM + " ;\n"
            + "            rdfs:label ?signalLabel . \n"
            + "    OPTIONAL { ?signal schema:description ?signalComment . FILTER(LANG(?signalComment)=\"pt\") .}\n"
            + "    FILTER(LANG(?signalLabel)=\"pt\") . \n"
            + "  } \n"
            + "}\n";

    //https://jena.apache.org/documentation/rdfconnection/
    public static void localRDF() {

        String inputFileName = "D:/Dev/DataIntegration/sint.ttl";

        InputStream is = RDFDataMgr.open(inputFileName);
        if (is == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        };

        Model model = FileManager.getInternal().loadModelInternal(inputFileName);

//        String queryStr = "select * where { ?s ?p ?o .}";
//        Query query = QueryFactory.create(QUERY_INSTANCIAS_OR_SUBCLASSE_SINTOMAS);
        Query query = QueryFactory.create(QUERY_ANY_RESOURCE_BY_ONTOLOGY_SYMPTOM_IDENTIFIER);
//        Query query = QueryFactory.create(QUERY_UNION_INSTANCE_SUBCLASS_AND_OSI);
//        Query query = QueryFactory.create(QUERY_UNION_INSTANCE_SUBCLASS_AND_OSI_NO_DUPLICATE);

        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        ResultSetFormatter.out(System.out, results, query);

        qe.close();
    }
    
}
