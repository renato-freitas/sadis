/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.util;

/**
 *
 * @author Renato
 */
public class Constants {

    /*About Database*/
    public final static String NOME_BD_MYSQL = "tcc2";
    public final static String POSTGRES_BD_NAME = "sadis";
    public final static String MYSQL_DRIVER = "jdbc:mysql://localhost/";
    public final static String MYSQL_URL = "jdbc:mysql://localhost/";
    public final static String MYSQL_ROOT = "root";
    public final static String MYSQL_SENHA = "r00t";
    public final static String POSTGRES_DRIVER = "org.postgresql.Driver";
    public final static String POSTGRES_URL = "jdbc:postgresql://localhost:5432/";
    public final static String POSTGRES_ROOT = "root";
    public final static String POSTGRES_SENHA = "r00t";
    public final static String SSL = "?useSSL=false";

    /**RESERVED WORDS TO QUERIES SPARQL*/
    public static final String STR_SELECT = "SELECT";
    public static final String STR_DISTINCT = "DISTINCT";
    public static final String STR_WHERE = "WHERE";
    public static final String STR_LIMIT = "LIMIT";
    public static final String STR_SERVICE = "SERVICE";
    public static final String STR_FILTER = "FILTER";
    
    /**SPARQL PRORPERTIES*/
    public static final String STR_RDFS_LABEL = "rdfs:label";
    public static final String STR_SCHEMA_DESCRIPTION = "schema:description";
    
    public static final String STR_WIKIDATA_SERVICE = STR_SERVICE+" wikibase:label { bd:serviceParam wikibase:language \"en\" . }";
    public static final String STR_WIKIDATA_SERVICE_PT = STR_SERVICE+" wikibase:label { bd:serviceParam wikibase:language \"pt\" . }";
    
    /** SPARQL PREFIXES*/
    public static final String DEFAULT_SPARQL_PREFIXES = "PREFIX  dbo:  <http://dbpedia.org/ontology/>\n"
            + "PREFIX  :     <http://dbpedia.org/resource/>\n"
            + "PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
            + "PREFIX  owl:  <http://www.w3.org/2002/07/owl#>\n"
            + "PREFIX  apf:  <http://jena.hpl.hp.com/ARQ/property#>\n"
            + "PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#>\n"
            + "PREFIX  fn:   <http://www.w3.org/2005/xpath-functions#>\n"
            + "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "PREFIX  foaf: <http://xmlns.com/foaf/0.1/>\n"
            + "PREFIX  dc:   <http://purl.org/dc/elements/1.1/>\n"
            + "PREFIX wikibase: <http://wikiba.se/ontology#>\n"
            + "PREFIX wd: <http://www.wikidata.org/entity/>\n"
            + "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n"
            + "PREFIX schema: <http://schema.org/>\n"
            + "PREFIX bd: <http://www.bigdata.com/rdf#>\n";
    
    /**SPARQL ENDPOINTS*/
    public static final String STR_WIKIDATA_ENDPOINT = "https://query.wikidata.org/sparql";
}
