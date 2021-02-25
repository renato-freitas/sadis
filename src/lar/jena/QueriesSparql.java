package lar.jena;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import lar.entidade.ResourceWeb;
import lar.util.Constants;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

/**
 * @version 1
 * @author Renato
 * @since 24/02/2021
 */
public class QueriesSparql {

    /**
     * Faz uma consulta ao endpoint da DBpedia para obter uma lista de sintomas a partir dos Recursos dbo:Disease.
     * @see Fazer uma consulta federada aos endpoints wikidata e dbpedia para obter mais sintomas
     * @return sintomas: rdfs:label e rdfs:comment
     */
    public static DefaultListModel getSymptomsFromDbpedia() {
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT DISTINCT ?s ?sl ?c WHERE { \n"
                + "   ?d a dbo:Disease .\n"
                + "   ?d dbo:symptom ?s .\n"
                + "   ?s rdfs:label ?sl .\n"
                + "   ?s rdfs:comment ?c .\n"
                + getFilter(getLang("?sl", "pt"))
                + getFilter(getLang("?c", "pt"))
                + "}\n"
                + getOrderby("?sl");

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

    /**
     * Faz uma consulta federada a dois endpoints. Por meio de Linked Data, a
     * partir do Recurso tipo dbr:Disease e a propriedade owl:sameAs, obtém-se,
     * opcionalmente: uma imagem (Recurso), os possiveis medicamentos (Recurso)
     * e suas respectivas labels (Literal).
     *
     * @param dbpediaDisease do tipo dbr:Disease
     * @return ResourceWeb
     */
    public static ResourceWeb getWikidataDiseaseFromDbpediaByDbpediaDisease(ResourceWeb dbpediaDisease) {
        String uri = "<" + dbpediaDisease.getUri() + ">";
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT DISTINCT ?w ?i ?d ?dl WHERE {\n"
                + "    SERVICE <http://dbpedia.org/sparql> { \n"
                + uri + " owl:sameAs ?w . \n"
                + "       FILTER( regex(?w, \"http://www.wikidata.org/entity\", \"i\"))\n"
                + "    }\n"
                + "    SERVICE SILENT <https://query.wikidata.org/sparql> {\n"
                + "       {OPTIONAL { ?w " + Constants.WDT_P18_IMAGE + " ?i . }} UNION {\n "
                + "       OPTIONAL { "
                + "          ?w " + Constants.WDT_P2176_DRUG_USED_FOR_TREATMENT + " ?d ."
                + "          ?d rdfs:label ?dl .\n"
                + "       }\n"
                + "       FILTER(LANG(?dl)=\"pt\")\n"
                + "       }\n"
                + "    }\n"
                + " }";
        ResourceWeb rscWeb = getSparqlQueryResultFirst__teste(Constants.STR_WIKIDATA_ENDPOINT, query, "w", "i", "d", "dl");
        rscWeb.setLabel(dbpediaDisease.getLabel());
        return rscWeb;
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
                + uri + " wdt:P3780 ?i .\n"
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

    //MÉTODO AUXILIARES
    private static String replaceUriDbpediaToResourcePrefix(String uri) {
        return uri.replace("http://dbpedia.org/resource/", "dbr:");
    }

    private static String replaceUriWikidataToResourcePrefix(String uri) {
        return uri.replace("http://www.wikidata.org/entity/", "wd:");
    }

    // SPARQL AUXILIAR METHODS
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

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query)) {;
            if (qe != null) {
                DefaultListModel drugs = new DefaultListModel();
                ResourceWeb rscWeb = new ResourceWeb();
                ResultSet resultSet = qe.execSelect();

                while (resultSet.hasNext()) {
                    QuerySolution qs = resultSet.next();

                    ResourceWeb drugRscWeb = new ResourceWeb();

                    Iterator<String> it = qs.varNames();
                    while (it.hasNext()) {
                        String var = it.next();

                        if (var.equals(resource)) {
                            rscWeb.setUri(qs.getResource(resource).toString());
                            continue;
                        }
                        if (var.equals(image)) {
                            rscWeb.setImage(qs.getResource(image).toString());
                            continue;
                        }
                        if (var.equals(drugLabel)) {
                            drugRscWeb.setUri(qs.getResource(drug).toString());
                            drugRscWeb.setLabel(qs.getLiteral(drugLabel).toString());
                            drugs.addElement(drugRscWeb);
                            continue;
                        }
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

    
    
    
    
    private static String getVariables(String variables) {
        return " " + variables + " ";
    }

    private static String getValues(String variable, ListModel<String> values) {
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

    private static String getStatmentContinue(String s, String p, String o) {
        return "\t" + s + " " + p + " " + o + ";\n";
    }

    private static String getStatmentEnd(String s, String p, String o) {
        return "\t" + s + " " + p + " " + o + ".\n";
    }

    private static String getFilter(String filter) {
        return Constants.STR_FILTER + "(" + filter + ")\n";
    }

    private static String getLang(String var, String lang) {
        return Constants.STR_LANG + "(" + var + ")=\""+lang+"\"";
    }

    private static String getOrderby(String variable) {
        return Constants.STR_ORDER_BY + "(" + variable + ")\n";
    }

    private static String getLimit(String value) {
        return Constants.STR_LIMIT + " " + value + "\n";
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
//    public static List<String> getPaperThemeFromWikidata(String theme) {
//        List<String> themes = new ArrayList<String>();
//        String query = Constants.DEFAULT_SPARQL_PREFIXES
//                + Constants.STR_SELECT + Constants.STR_DISTINCT + " ?item ?theme ?itemLabel "
//                + Constants.STR_WHERE + " { "
//                + " ?item " + Constants.STR_WIKIDATA_MAIN_THEME + " ?theme ;"
//                + Constants.STR_FILTER + " regex(str(?itemLabel), \"" + theme + "\", \"i\") "
//                + "} ";
//
//        System.out.println("lar.jena.QueriesSparql.getPaperThemeFromWikidata()" + query);
//        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT, query)) {
//            if (qe != null) {
//                ResultSet rs = qe.execSelect();
//                while (rs.hasNext()) {
//                    QuerySolution qs = rs.next();
//                    Literal label = qs.getLiteral("itemLabel");
//                    Resource s = qs.getResource("item");
//                    System.out.println("QueriesSparql, getSymptomsFromWikidata(), Subject: " + label);
//                    themes.add(s.toString() + " " + label.toString());
//                }
//                qe.close();
//                return themes;
//            } else {
//                return null;
//            }
//        }
//    }
}
