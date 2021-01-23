/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.jena;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
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

    public static List<String> getSymptomsFromWikidata() {
        List<String> symptoms = new ArrayList<String>();
        String query = Constants.DEFAULT_SPARQL_PREFIXES
                + Constants.STR_SELECT + " " + Constants.STR_DISTINCT + " ?item ?itemLabel "
                + Constants.STR_WHERE + " { "
                + " ?item wdt:P31 wd:Q169872;"
                + Constants.STR_WIKIDATA_SERVICE_PT
                + "}";

        try (QueryExecution qe = QueryExecutionFactory.sparqlService(Constants.STR_WIKIDATA_ENDPOINT, query)) {
            if (qe != null) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
//                    Resource subject = qs.getResource("itemLabel");
                    Literal subject = qs.getLiteral("itemLabel");
                    System.out.println("QueriesSparql, getSymptomsFromWikidata(), Subject: " + subject);;
                    symptoms.add(subject.toString());
                }
                qe.close();
                return symptoms;
            } else {
                return null;
            }
        }
    }
}
