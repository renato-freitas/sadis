/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.jena;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import lar.entidade.ResourceWeb;
import lar.util.Constants;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author Renato
 */
public class QueriesSparql {

    //DBPEDIA METHODS
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
                + "order by(?sl)";

        return getSparqlQueryResult(Constants.STR_DBPEDIA_ENDPOINT, query, "s", "sl", "c");
    }

    public static DefaultListModel getDiseasesBySymptomnsFromDbpedia(ListModel<String> symptomns) {
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + "select distinct ?d ?dl ?c where {\n"
                + getValues("?s", symptomns)
                + "   ?d dbo:symptom ?s .\n"
                + "   ?d rdfs:label ?dl .\n"
                + "   ?d rdfs:comment ?c .\n"
                + "   filter(lang(?dl) = \"pt\")\n"
                + "   filter(lang(?c) = \"pt\")\n"
                + "}\n"
                + "order by(?dl)";
        System.out.println("lar.jena.QueriesSparql.getDiseasesBySymptomnsFromDbpedia()" + query);
        return getSparqlQueryResult(Constants.STR_DBPEDIA_ENDPOINT, query, "d", "dl", "c");
    }

//    public static DefaultListModel getWikidataDiseaseFromDbpediaByDbpediaDisease(ResourceWeb dbpediaDisease) {
//        System.out.println("lar.jena.QueriesSparql.getWikidataDiseaseFromDbpediaByDbpediaDisease()" + dbpediaDisease);
//        String uri = dbpediaDisease.getUri();
//        uri = replaceUriDbpediaToResourcePrefix(uri.replace("(", "\\(").replace(")", "\\)")) + " ";
//
//        String query = Constants.DEFAULT_SPARQL_PREFIXES
//                + "\nSELECT DISTINCT ?w where { \n\t"
//                + uri + " owl:sameAs ?w . \n"
//                + "  FILTER( regex(?w, \"http://www.wikidata.org/entity\", \"i\"))\n"
//                + "} ";
//
//        System.out.println("lar.jena.QueriesSparql.getWikidataDisease()" + query);
//
//        DefaultListModel sss = getSparqlQueryResultFirst(Constants.STR_DBPEDIA_ENDPOINT, query, "w");
//        System.out.println("lar.jena.QueriesSparql.getWikidataDiseaseFromDbpediaByDbpediaDisease(), result >> " + sss);
//        return sss;
//    }
    /**
     * teste está funcinando muito bem
     */
    public static ResourceWeb teste(ResourceWeb dbpediaDisease) {
        String uri = dbpediaDisease.getUri();
        uri = replaceUriDbpediaToResourcePrefix(uri.replace("(", "\\(").replace(")", "\\)")) + " ";

        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT DISTINCT ?w ?i ?d ?dl WHERE {\n"
                + "    SERVICE <http://dbpedia.org/sparql> { \n"
                + uri + " owl:sameAs ?w . \n"
                + "       FILTER( regex(?w, \"http://www.wikidata.org/entity\", \"i\"))\n"
                + "    }\n"
                + "    SERVICE SILENT <https://query.wikidata.org/sparql> {\n\t\t\t"
                + "       OPTIONAL { "
                + "          ?w " + Constants.WDT_P18_IMAGE + " ?i ; "
                + Constants.WDT_P2176_DRUG_USED_FOR_TREATMENT + " ?d ."
                + "          ?d rdfs:label ?dl ."
                + "          FILTER(LANG(?dl)=\"pt\")"
                + "       }\n"
                + "    }\n"
                + " }";

        ResourceWeb sss = getSparqlQueryResultFirst__teste(Constants.STR_WIKIDATA_ENDPOINT, query, "w", "i", "d", "dl");
        sss.setLabel(dbpediaDisease.getLabel());
        return sss;
    }

    /**
     * Retorna o Recurso Drog com sua descrição e conhecido como
     */
    public static ResourceWeb getDescriptonAndAlsoKnowAsFromWikiDataByDrug(ResourceWeb drug) {
        String uri = drug.getUri();
        uri = replaceUriWikidataToResourcePrefix(uri.replace("(", "\\(").replace(")", "\\)"));

        System.out.println("lar.jena.QueriesSparql.getDescriptonAndAlsoKnowAsFromWikiDataByDrug(), uri > " + uri);

        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT DISTINCT ?d ?k ?il WHERE { \n"
                + "  OPTIONAL { " + uri + " schema:description ?d . FILTER(LANG(?d)=\"pt\") . }\n"
                + "  OPTIONAL { " + uri + " skos:altLabel ?k. FILTER((LANG(?k)) = \"pt\") .}\n"
                + "  OPTIONAL {\n"
                +      uri + " wdt:P3780 ?i .\n"
                + "    ?i rdfs:label ?il .\n"
                + "    FILTER((LANG(?il)) = \"pt\") .\n"
                + "  }\n"
                + "}";

//        ResourceWeb sss = getSparqlQueryResultFirst__teste(Constants.STR_WIKIDATA_ENDPOINT, query, "w", "i", "d", "dl");
        System.out.println("lar.jena.QueriesSparql.getDescriptonAndAlsoKnowAsFromWikiDataByDrug(), query > " + query);
        DefaultListModel alsoKnowAs = new DefaultListModel();
        DefaultListModel activeIngredientIn = new DefaultListModel();
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT, query)) {;
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                ResourceWeb rscWeb = new ResourceWeb();
                rscWeb.setUri(uri);

                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();

                    rscWeb.setComment(qs.getLiteral("d").toString());
                    alsoKnowAs.addElement(qs.getLiteral("k").toString());
                    activeIngredientIn.addElement(qs.getLiteral("il").toString());

                }
                rscWeb.setAlsoKnowAs(alsoKnowAs);
                rscWeb.setActiveIngredientIn(activeIngredientIn);
                qe.close();
                return rscWeb;
            } else {
                return null;
            }
        }
//        return sss;
    }

    /**
     *
     * // public static List<String> getSymptomsFromWikidata() { //
     * List<String>
     * symptoms = new ArrayList<String>(); // String query =
     * Constants.DEFAULT_SPARQL_PREFIXES // + Constants.STR_SELECT +
     * Constants.STR_DISTINCT + " ?item ?itemLabel " // + Constants.STR_WHERE +
     * " { " // + " ?item " + Constants.STR_WDT_P31_INSTANCEOF + " " +
     * Constants.STR_WD_Q169872_SYMPTOMNS + " ." // +
     * Constants.STR_WIKIDATA_SERVICE_PT // + "} orderby(?itemLabel)"; // //
     * return getResultFromSparqlQuery(Constants.STR_WIKIDATA_ENDPOINT, query,
     * "item", "itemLabel"); // };
     *
     * // public static List<String>
     * filterDiseasesBySymptomsFromWikidata(String symptoms) { // String lg =
     * "pt", // vDisease = "?disease", vDiseaseLabel = "?diseaseLabel", //
     * vSymptoms = "?symptoms", vSymptomsLabel = "?symptomsLabel"; // //
     * System.out.println("lar.jena.QueriesSparql.filterDiseasesBySymptomsFromWikidata()"
     * + symptoms); // // String query = Constants.DEFAULT_SPARQL_PREFIXES +
     * "\n\n" // + Constants.STR_SELECT_DISTINCT + " " // + vDisease + " " +
     * vDiseaseLabel + " " // + Constants.STR_WHERE + " {\n " // +
     * getStatmentContinue(vDisease, Constants.STR_WDT_P31_INSTANCEOF + "/" +
     * Constants.STR_WDT_P279_SUBCLASSOF, Constants.STR_WD_Q12136_DISEASE) // +
     * getStatmentContinue(" ", Constants.STR_WDT_P780_SYMPTOMS, vSymptoms) // +
     * getStatmentEnd(" ", Constants.STR_RDFS_LABEL, vDiseaseLabel) // +
     * getStatmentEnd(vSymptoms, Constants.STR_RDFS_LABEL, vSymptomsLabel) // +
     * getFilter("(LANG(" + vDiseaseLabel + ")) = \"" + lg + "\"") // +
     * getFilter(vSymptomsLabel + " IN(\"" + symptoms + ")") // + " }\n" // +
     * getOrderby(vDiseaseLabel); // // return
     * getResultFromSparqlQuery(Constants.STR_WIKIDATA_ENDPOINT, query,
     * "disease", "diseaseLabel"); // }
     *
     * // public static Image getDiseaseImageFromWikidata(String disease) { //
     * String query = Constants.DEFAULT_SPARQL_PREFIXES // +
     * Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?itemLabel " // +
     * Constants.STR_WHERE + " { " // + " ?item " +
     * Constants.STR_WDT_P31_INSTANCEOF + " " +
     * Constants.STR_WD_Q169872_SYMPTOMNS + " ." // +
     * Constants.STR_WIKIDATA_SERVICE_PT // + "} orderby(?itemLabel)"; // return
     * null; // }
     *
     * // public static List<String> getPapersFromWikidata() { // List<String>
     * symptoms = new ArrayList<String>(); // String query =
     * Constants.DEFAULT_SPARQL_PREFIXES // + Constants.STR_SELECT +
     * Constants.STR_DISTINCT + " ?item ?itemLabel " // + Constants.STR_WHERE +
     * " { " // + " ?item " + Constants.STR_WDT_P31_INSTANCEOF + " " +
     * Constants.STR_WD_Q169872_SYMPTOMNS + " ." // +
     * Constants.STR_WIKIDATA_SERVICE_PT // + "} orderby(?itemLabel)"; // // try
     * (QueryExecution qe =
     * QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT,
     * query)) { // if (qe != null) { // ResultSet rs = qe.execSelect(); //
     * while (rs.hasNext()) { // QuerySolution qs = rs.next(); // Literal label
     * = qs.getLiteral("itemLabel"); // Resource s = qs.getResource("item"); //
     * System.out.println("QueriesSparql, getSymptomsFromWikidata(), Subject: "
     * + label); // symptoms.add(s.toString() + " " + label.toString()); // } //
     * qe.close(); // return symptoms; // } else { // return null; // } // } //
     * }
     *
     *
     *
     */
    public static List<String> getPaperThemeFromWikidata(String theme) {
        List<String> themes = new ArrayList<String>();
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?theme ?itemLabel "
                + Constants.STR_WHERE + " { "
                + " ?item " + Constants.STR_WIKIDATA_MAIN_THEME + " ?theme ;"
                + Constants.STR_FILTER + " regex(str(?itemLabel), \"" + theme + "\", \"i\") "
                + "} ";

        System.out.println("lar.jena.QueriesSparql.getPaperThemeFromWikidata()" + query);
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
    private static String replaceUriDbpediaToResourcePrefix(String uri) {
        return uri.replace("http://dbpedia.org/resource/", "dbr:");
    }

    private static String replaceUriWikidataToResourcePrefix(String uri) {
        return uri.replace("http://www.wikidata.org/entity/", "wd:");
    }

    // SPARQL AUXILIAR METHODS
//    public static List<String> getResultFromSparqlQuery(String endpoint, String query, String resource, String labelOut) {
//        List<String> result = new ArrayList<String>();
//        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {
//            if (qe != null) {
//                ResultSet rs = qe.execSelect();
//                while (rs.hasNext()) {
//                    QuerySolution qs = rs.next();
//                    Literal label = qs.getLiteral(labelOut);
//                    Resource s = qs.getResource(resource);
//                    System.out.println("QueriesSparql, getResultFromSparqlQuery(), Subject: " + label);
//                    result.add(label.toString());
//                }
//                qe.close();
//                return result;
//            } else {
//                return null;
//            }
//        }
//    }
    public static DefaultListModel getSparqlQueryResult(String endpoint, String query, String resource, String l, String cl) {
        DefaultListModel result = new DefaultListModel();
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    ResourceWeb rscWeb = new ResourceWeb();

                    Resource r = qs.getResource(resource);
                    rscWeb.setUri(r.toString());
//                    System.out.println("lar.jena.QueriesSparql.getSparqlQueryResult()" + resource);

                    if (l != null | l != "") {
//                        Literal label = qs.getLiteral(l);
                        rscWeb.setLabel(qs.getLiteral(l).toString());
//                        System.out.println("lar.jena.QueriesSparql.getSparqlQueryResult()" + l);
                    }
                    if (cl != null | cl != "") {
//                        Literal comment = qs.getLiteral(cl);
                        rscWeb.setComment(qs.getLiteral(cl).toString());
//                        System.out.println("lar.jena.QueriesSparql.getSparqlQueryResult()" + cl);
                    }
//                    System.out.println("QueriesSparql, getResultFromSparqlQuery(), Subject: " + rscWeb);

                    result.addElement(rscWeb);
                }
                qe.close();
                return result;
            } else {
                return null;
            }
        }
    }

    public static DefaultListModel getSparqlQueryResultFirst(String endpoint, String query, String resource) {
        DefaultListModel result = new DefaultListModel();
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {;
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    System.out.println("lar.jena.QueriesSparql.getSparqlQueryResultFirst()" + qs);

                    Resource rcs = qs.getResource(resource);
                    result.addElement(rcs);
                }
                qe.close();
                return result;
            } else {
                return null;
            }
        }
    }

    public static ResourceWeb getSparqlQueryResultFirst__teste(String endpoint, String query, String resource, String image, String drug, String drugLabel) {
        DefaultListModel drugs = new DefaultListModel();
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {;
            if (qe != null) {
                ResultSet rs = qe.execSelect();
//                System.out.println("lar.jena.QueriesSparql.getSparqlQueryResultFirst__teste() <result>, " + rs);
                ResourceWeb rscWeb = new ResourceWeb();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
//                    System.out.println("lar.jena.QueriesSparql.getSparqlQueryResultFirst__teste()" + qs);

                    Resource r = qs.getResource(resource);
                    rscWeb.setUri(r.toString());
//                    rscWeb.setLabel(r.get);

                    if (image != null && !image.trim().isEmpty()) {
                        rscWeb.setImage(qs.getResource(image).toString());
                    }

                    if (drug != null && !drug.trim().isEmpty()) {
                        ResourceWeb drugRw = new ResourceWeb();
                        Resource rr = qs.getResource(drug);
                        drugRw.setUri(rr.toString());
                        drugRw.setLabel(qs.getLiteral(drugLabel).toString());

//                        System.out.println("lar.jena.QueriesSparql.getSparqlQueryResultFirst__teste(), NÃO VAZIO" + qs.getResource(drug));
//                        drugs.addElement(qs.getResource(drug));
                        drugs.addElement(drugRw);
                    }
                }
                rscWeb.setDrugs(drugs);
                qe.close();
                return rscWeb;
            } else {
                return null;
            }
        }
    }

    public static String getVariables(String variables) {
        return " " + variables + " ";
    }

    public static String getValues(String variable, ListModel<String> values) {
        String _values = "VALUES " + variable + " { ";

        for (int i = 0; i < values.getSize(); i++) {
            Object o = values.getElementAt(i);
            String uri = ((ResourceWeb) o).getUri();
            System.out.println("lar.jena.QueriesSparql.getValues()" + uri.replace("(", "\\(").replace(")", "\\)"));
            _values += replaceUriDbpediaToResourcePrefix(uri.replace("(", "\\(").replace(")", "\\)")) + " ";
        }
        _values += "}\n";
        return _values;
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
