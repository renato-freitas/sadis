package lar.jena;

import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import lar.entidade.ResourceWeb;
import lar.util.Constants;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;

/**
 * @version 1
 * @author Renato
 * @since 24/02/2021
 */
public class QueriesSparql {

    final static String LANG_PT = "pt";

    

    /**
     * Faz uma consulta ao endpoint da DBpedia para obter uma lista de sintomas
     * a partir dos Recursos dbo:Disease.
     *
     * @see Fazer uma consulta federada aos endpoints wikidata e dbpedia para
     * obter mais sintomas
     * @return sintomas: rdfs:label e rdfs:comment
     */
    public static DefaultListModel getSymptomsFromDbpedia() {
        String varSymptom = "symptom", varSymptomLabel = "symptomLabel", varSymptomComment = "symptomComment";
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT DISTINCT ?symptom ?symptomLabel ?symptomComment WHERE { \n"
                + "   ?disease dbo:symptom ?symptom .\n"
                + "   ?symptom  rdfs:label ?symptomLabel .\n"
                + "   OPTIONAL {\n"
                + "     ?symptom rdfs:comment ?symptomComment .\n"
                + "     FILTER(LANG(?symptomComment)=\"pt\")"
                + "   }\n"
                + "   FILTER(LANG(?symptomLabel)=\"pt\")"
                + "}\n"
                + "ORDER BY ?symptomLabel";

        System.out.println("lar.jena.QueriesSparql.getSymptomsFromDbpedia()" + query);

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.DBPEDIA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet resultSet = qe.execSelect();
                DefaultListModel dlm = new DefaultListModel();

                while (resultSet.hasNext()) {
                    QuerySolution qs = resultSet.next();
                    ResourceWeb rscWeb = new ResourceWeb();

                    Iterator<String> it = qs.varNames();
                    while (it.hasNext()) {
                        String var = it.next();

                        if (var.equals(varSymptom)) {
                            rscWeb.setUri(qs.getResource(varSymptom).toString());
                            rscWeb.setLabel(qs.getLiteral(varSymptomLabel).toString());
                            continue;
                        }
//                        if (var.equals(varSymptomComment)) {
//                            rscWeb.setImage(qs.getLiteral(varSymptomComment).toString());
//                            continue;
//                        }
                    }
                    dlm.addElement(rscWeb);
                }
                qe.close();
//                System.out.println("lar.jena.QueriesSparql.getSymptomsFromDbpedia()");
                return dlm;
            } else {
                return null;
            }
        }
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
        return getSparqlQueryResult(Constants.DBPEDIA_ENDPOINT, query, "d", "dl", "c");
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
        ResourceWeb rscWeb = getSparqlQueryResultFirst__teste(Constants.WIKIDATA_ENDPOINT, query, "w", "i", "d", "dl");
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
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.WIKIDATA_ENDPOINT, query)) {;
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

    //WIKIDATA SPACE
    /**
     * Retorna uma lista de sinais. Prioritariamente em português. Caso
     * contrário em inglês.
     */
    public static DefaultListModel getWikiSignals() {
        String signal = "signal", signalLabel = "label",
                query = Constants.RDF_PREFIX
                + Constants.RDFS_PREFIX
                + "PREFIX wd: <http://www.wikidata.org/entity/>\n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n"
                + "SELECT DISTINCT ?signal ?label WHERE {\n"
                + "  ?signal a|" + Constants.WDT_P31_INSTANCEOF + "|rdf:type|" + Constants.WDT_P279_SUBCLASSOF + Constants.WD_Q1441305_SIGNAL[0] + ";\n"
                + "          rdfs:label ?ptLabel ;\n"
                + "          rdfs:label ?enLabel .\n"
                + "  FILTER(LANG(?ptLabel)=\"pt\") .\n"
                + "  FILTER(LANG(?enLabel)=\"en\") .\n"
                + "  BIND(IF(?ptLabel=\"\", ?enLabel, ?ptLabel) AS ?label)\n"
                + "}\n"
                + "ORDER BY ?label";

        System.out.println("lar.jena.QueriesSparql.getWikiSignals()" + query);

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet resultSet = qe.execSelect();
                DefaultListModel dlm = new DefaultListModel();

                while (resultSet.hasNext()) {
                    QuerySolution qs = resultSet.next();
                    ResourceWeb rscWeb = new ResourceWeb();

                    Iterator<String> it = qs.varNames();
                    while (it.hasNext()) {
                        String var = it.next();

                        if (var.equals(signal)) {
                            rscWeb.setUri(qs.getResource(signal).toString());
                            rscWeb.setLabel(qs.getLiteral(signalLabel).toString());
                        }
                    }
                    dlm.addElement(rscWeb);
                }
                qe.close();
                return dlm;
            } else {
                return null;
            }
        }
    }

//    public static DefaultListModel getSignalsAndSymptomsFromWikidata() {;
    public static DefaultListModel getWikiSymptoms() {
        String symptom = "symptom", symptomLabel = "symptomLabel", symptomComment = "symptomLabel";
        String signal = "signal", signalLabel = "signalLabel", signalComment = "signalComment";

        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT DISTINCT ?symptom ?symptomLabel ?symptomComment ?signal ?signalLabel ?signalComment WHERE {\n"
                + "  {\n"
                + "    ?symptom " + Constants.WDT_P31_INSTANCEOF + "|" + Constants.WDT_P279_SUBCLASSOF + Constants.WD_Q169872_SYMPTOM + " .\n"
                + "    ?symptom rdfs:label ?symptomLabel .\n"
                + "    OPTIONAL { ?symptom schema:description ?symptomComment . FILTER(LANG(?symptomComment)=\"pt\") .}\n"
                + "    FILTER(LANG(?symptomLabel)=\"pt\") .\n"
                + "  }\n"
                + "  UNION\n"
                + "  { \n"
                + "    ?signal " + Constants.WDT_P8656_SYMPTOM_ONTOLOGY_ID + " ?id ;\n"
                + "            rdfs:label ?signalLabel . \n"
                + "    OPTIONAL { ?signal schema:description ?signalComment . FILTER(LANG(?signalComment)=\"pt\") .}\n"
                + "    FILTER(LANG(?signalLabel)=\"pt\") . \n"
                + "  } \n"
                + "}\n"
                + "ORDER BY ?symptomLabel ?signalLabel";

        System.out.println("lar.jena.QueriesSparql.getSignalsAndSymptomsFromWikidata()" + query);
        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet resultSet = qe.execSelect();
                DefaultListModel dlm = new DefaultListModel();

                while (resultSet.hasNext()) {
                    QuerySolution qs = resultSet.next();
                    ResourceWeb rscWeb = new ResourceWeb();

                    Iterator<String> it = qs.varNames();
                    for (; it.hasNext();) {
                        String var = it.next();

                        if (var.equals(symptom)) {
                            rscWeb.setUri(qs.getResource(symptom).toString());
                            rscWeb.setLabel(qs.getLiteral(symptomLabel).toString());
                            continue;
                        }
                        if (var.equals(signal)) {
                            rscWeb.setUri(qs.getResource(signal).toString());
                            rscWeb.setLabel(qs.getLiteral(signalLabel).toString());
                            continue;
                        }
                    }
                    dlm.addElement(rscWeb);
                }
                qe.close();
                return dlm;
            } else {
                return null;
            }
        }
    }

    public static String getWikiComment(ResourceWeb resource) {
        String uri = "<" + resource.getUri() + ">";
        String varComment = "comment", comment = "";
        String query = "PREFIX schema: <http://schema.org/>\n"
                + " SELECT ?comment WHERE {\n"
                + uri + " schema:description ?enComment.\n"
                + "  OPTIONAL {\n"
                + uri + " schema:description ?ptComment.\n"
                + "    FILTER((LANG(?ptComment)) = \"pt\")\n"
                + "  }\n"
                + "  FILTER((LANG(?enComment)) = \"en\")\n"
                + "  BIND(IF(!(BOUND(?ptComment)), ?enComment, ?ptComment) AS ?comment)\n"
                + "}";

        System.out.println("lar.jena.QueriesSparql.getSymptomsFromDbpedia()" + query);

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet resultSet = qe.execSelect();
                while (resultSet.hasNext()) {
                    QuerySolution qs = resultSet.next();
                    comment = qs.getLiteral(varComment).toString();

                    System.out.println("lar.jena.QueriesSparql.getWikiComment(), comment > " + comment);
//                    Iterator<String> it = qs.varNames();
//                    while (it.hasNext()) {
//                        String var = it.next();
//                        if (var.equals(varComment)) {
//                            comment = qs.getResource(varComment).toString();
//                        }
//                    }

                }
                qe.close();
                return comment;
            } else {
                return null;
            }
        }
    }

    public static String getCommentFromDbpedia(ResourceWeb dbpediaResource) {
        String uri = "<" + dbpediaResource.getUri() + ">";
        String varComment = "comment";
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + " SELECT ?comment WHERE {\n"
                + uri + " rdfs:comment ?comment .\n"
                + getFilter(getLang(varComment, LANG_PT))
                + "}";

        System.out.println("lar.jena.QueriesSparql.getSymptomsFromDbpedia()" + query);

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.DBPEDIA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet resultSet = qe.execSelect();
                String comment = "";
                while (resultSet.hasNext()) {
                    QuerySolution qs = resultSet.next();
                    Iterator<String> it = qs.varNames();
                    while (it.hasNext()) {
                        String var = it.next();
                        if (var.equals(varComment)) {
                            comment = qs.getLiteral(varComment).toString();
                            continue;
                        }
                    }
                }
                qe.close();
                return comment;
            } else {
                return null;
            }
        }
    }

    //MÉTODO AUXILIARES
    private static String replaceUriDbpediaToResourcePrefix(String uri) {
        return uri.replace("http://dbpedia.org/resource/", "dbr:");
    }

    private static String replaceUriWikidataToResourcePrefix(String uri) {
        return uri.replace("http://www.wikidata.org/entity/", "wd:");
    }

    // AUXILIAR SPARQL METHODS
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
        return Constants.STR_FILTER + "(" + filter + ") .\n";
    }

    private static String getLang(String var, String lang) {
        return Constants.STR_LANG + "(?" + var + ")=\"" + lang + "\"";
    }

    private static String getOrderby(String variable) {
        return Constants.STR_ORDER_BY + " ?" + variable + " \n";
    }

    private static String getLimit(String value) {
        return Constants.STR_LIMIT + " " + value + "\n";
    }

    /**
     * Faz uma consulta ao endpoint da Wikidaata para obter uma lista de
     * sintomas que são instâncias de WD:Q169872_SYMPTOMNS.
     *
     * @return sintomas
     */
    /**
     * public static DefaultListModel getSymptomsFromWikidata() { String symptom
     * = "symptom", symptomLabel = "symptomLabel"; String query =
     * Constants.DEFAULT_SPARQL_PREFIXES + " select distinct ?symptom
     * ?symptomLabel where {\n" + " ?symptom " +
     * Constants.STR_WDT_P31_INSTANCEOF + "|" + Constants.WDT_P279_SUBCLASSOF +
     * Constants.WD_Q169872_SYMPTOM + " ;\n" + " rdfs:label ?symptomLabel .\n" +
     * getFilter(getLang(symptomLabel, LANG_PT)) + "}\n" +
     * getOrderby(symptomLabel);
     *
     * try (QueryExecution qe =
     * QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT,
     * query)) { if (qe != null) { ResultSet resultSet = qe.execSelect();
     * DefaultListModel dlm = new DefaultListModel();
     *
     * while (resultSet.hasNext()) { QuerySolution qs = resultSet.next();
     * ResourceWeb rscWeb = new ResourceWeb();
     *
     * Iterator<String> it = qs.varNames(); while (it.hasNext()) { String var =
     * it.next();
     *
     * if (var.equals(symptom)) {
     * rscWeb.setUri(qs.getResource(symptom).toString());
     * rscWeb.setLabel(qs.getLiteral(symptomLabel).toString()); continue; } if
     * (var.equals("a")) { rscWeb.setImage(qs.getResource("a").toString());
     * continue; } } dlm.addElement(rscWeb); } qe.close(); return dlm; } else {
     * return null; } } }
     *
     * @return
     */
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
