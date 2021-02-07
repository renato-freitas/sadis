/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.jena;

import java.awt.Image;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import lar.entidade.ResourceWeb;
import lar.util.Constants;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author Renato
 */
public class QueriesSparql {

//DBPEDIA-PT METHODS
    public static DefaultListModel getSymptomsFromDbpedia() {
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " select distinct ?s ?sl ?c where { \n"
                + "   ?d a dbo:Disease .\n"
                + "   ?d dbo:symptom ?s .\n"
                + "   ?s rdfs:label ?sl .\n"
                + "   ?s rdfs:comment ?c\n"
                + "   filter( lang(?sl) = \"pt\")\n"
                + "   filter(lang(?c) = \"pt\")\n"
                + "}\n" 
                +"order by(?sl)";

        return getSparqlQueryResult(Constants.STR_DBPEDIA_ENDPOINT, query, "s", "sl", "c");
    }

    public static DefaultListModel getDiseasesBySymptomnsFromDbpedia() {
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + "select distinct ?d ?dl where {\n"
                + "   values ?s { dbr:Arthralgia dbr:Fatige }\n"
                + "   ?d dbo:symptom ?s .\n"
                + "   ?d rdfs:label ?dl .\n"
                + "   filter(lang(?dl) = \"pt\")\n"
                + "}\n"
                + "order by(?dl)";
        return getSparqlQueryResult(Constants.STR_DBPEDIA_ENDPOINT, query, "d", "dl", "");
    }

    public static List<String> getSymptomsFromWikidata() {
        List<String> symptoms = new ArrayList<String>();
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?itemLabel "
                + Constants.STR_WHERE + " { "
                + " ?item " + Constants.STR_WDT_P31_INSTANCEOF + " " + Constants.STR_WD_Q169872_SYMPTOMNS + " ."
                + Constants.STR_WIKIDATA_SERVICE_PT
                + "} orderby(?itemLabel)";

        return getResultFromSparqlQuery(Constants.STR_WIKIDATA_ENDPOINT, query, "item", "itemLabel");
    }

    public static List<String> filterDiseasesBySymptomsFromWikidata(String symptoms) {
        String lg = "pt",
                vDisease = "?disease", vDiseaseLabel = "?diseaseLabel",
                vSymptoms = "?symptoms", vSymptomsLabel = "?symptomsLabel";

        System.out.println("lar.jena.QueriesSparql.filterDiseasesBySymptomsFromWikidata()" + symptoms);

        String query = Constants.DEFAULT_SPARQL_PREFIXES + "\n\n"
                + Constants.STR_SELECT_DISTINCT + " "
                + vDisease + " " + vDiseaseLabel + " "
                + Constants.STR_WHERE + " {\n "
                + getStatmentContinue(vDisease, Constants.STR_WDT_P31_INSTANCEOF + "/" + Constants.STR_WDT_P279_SUBCLASSOF, Constants.STR_WD_Q12136_DISEASE)
                + getStatmentContinue(" ", Constants.STR_WDT_P780_SYMPTOMS, vSymptoms)
                + getStatmentEnd(" ", Constants.STR_RDFS_LABEL, vDiseaseLabel)
                + getStatmentEnd(vSymptoms, Constants.STR_RDFS_LABEL, vSymptomsLabel)
                + getFilter("(LANG(" + vDiseaseLabel + ")) = \"" + lg + "\"")
                //                + getFilter(vSymptomsLabel + " IN(\"artralgia\"@" + lg + ")")
                //                + getFilter(vSymptomsLabel + " IN(\"" + symptoms + "\"@" + lg + ")")
                + getFilter(vSymptomsLabel + " IN(\"" + symptoms + ")")
                + " }\n"
                + getOrderby(vDiseaseLabel);

        return getResultFromSparqlQuery(Constants.STR_WIKIDATA_ENDPOINT, query, "disease", "diseaseLabel");
    }

    public static Image getDiseaseImageFromWikidata(String disease) {
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?itemLabel "
                + Constants.STR_WHERE + " { "
                + " ?item " + Constants.STR_WDT_P31_INSTANCEOF + " " + Constants.STR_WD_Q169872_SYMPTOMNS + " ."
                + Constants.STR_WIKIDATA_SERVICE_PT
                + "} orderby(?itemLabel)";
//        return getResultFromSparqlQuery(Constants.STR_WIKIDATA_ENDPOINT, query);
        return null;
    }

    public static List<String> getPapersFromWikidata() {
        List<String> symptoms = new ArrayList<String>();
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?itemLabel "
                + Constants.STR_WHERE + " { "
                + " ?item " + Constants.STR_WDT_P31_INSTANCEOF + " " + Constants.STR_WD_Q169872_SYMPTOMNS + " ."
                + Constants.STR_WIKIDATA_SERVICE_PT
                + "} orderby(?itemLabel)";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    Literal label = qs.getLiteral("itemLabel");
                    Resource s = qs.getResource("item");
                    System.out.println("QueriesSparql, getSymptomsFromWikidata(), Subject: " + label);
                    symptoms.add(s.toString() + " " + label.toString());
                }
                qe.close();
                return symptoms;
            } else {
                return null;
            }
        }
    }

    public static List<String> getPaperThemeFromWikidata(String theme) {
        List<String> themes = new ArrayList<String>();
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?theme ?itemLabel "
                + Constants.STR_WHERE + " { "
                + " ?item " + Constants.STR_WIKIDATA_MAIN_THEME + " ?theme ;"
                + Constants.STR_FILTER + " regex(str(?itemLabel), \"" + theme + "\", \"i\") "
                //                + Constants.STR_WIKIDATA_SERVICE_PT
                + "} ";

        System.out.println("lar.jena.QueriesSparql.getPaperThemeFromWikidata()" + query);
//        FILTER (regex(str(?label), "Choque", "i")
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    Literal label = qs.getLiteral("itemLabel");
                    Resource s = qs.getResource("item");
                    System.out.println("QueriesSparql, getSymptomsFromWikidata(), Subject: " + label);
                    themes.add(s.toString() + " " + label.toString());
                }
                qe.close();
                return themes;
            } else {
                return null;
            }
        }
    }

    //MÉTODO AUXILIAR
    private static String getNamePlusLanguage(String name, String lang) {
        return "\"" + name + "\"@" + lang;
    }

    // SPARQL AUXILIAR METHODS
    public static List<String> getResultFromSparqlQuery(String endpoint, String query, String resource, String labelOut) {
        List<String> result = new ArrayList<String>();
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    Literal label = qs.getLiteral(labelOut);
                    Resource s = qs.getResource(resource);
                    System.out.println("QueriesSparql, getResultFromSparqlQuery(), Subject: " + label);
                    result.add(label.toString());
                }
                qe.close();
                return result;
            } else {
                return null;
            }
        }
    }

    public static DefaultListModel getSparqlQueryResult(String endpoint, String query, String resource, String l, String cl) {
        DefaultListModel result = new DefaultListModel();
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    Resource s = qs.getResource(resource);
                    Literal label = qs.getLiteral(l);
                    Literal comment = qs.getLiteral(cl);
//                    System.out.println("QueriesSparql, getResultFromSparqlQuery(), Subject: " + label);

                    ResourceWeb rsc = new ResourceWeb();
                    rsc.setUri(s.toString());
                    rsc.setLabel(label.toString());
                    rsc.setComment(comment.toString());
                    result.addElement(rsc);
                }
                qe.close();
                return result;
            } else {
                return null;
            }
        }
    }

    public static String getVariables(String variables) {
        return " " + variables + " ";
    }

    public static String getStatmentContinue(String s, String p, String o) {
        return "\t" + s + " " + p + " " + o + ";\n";
    }

    public static String getStatmentEnd(String s, String p, String o) {
        return "\t" + s + " " + p + " " + o + ".\n";
    }

    public static String getFilter(String filter) {
        return Constants.STR_FILTER + "(" + filter + ")\n";
    }

    public static String getOrderby(String variable) {
        return Constants.STR_ORDER_BY + "(" + variable + ")\n";
    }

    public static String getLimit(String value) {
        return Constants.STR_LIMIT + " " + value + "\n";
    }
}
