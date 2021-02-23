/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.util;

import java.util.ArrayList;

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
    public static final String STR_SELECT = "SELECT ";
    public static final String STR_DISTINCT = "DISTINCT ";
    public static final String STR_SELECT_DISTINCT = STR_SELECT + STR_DISTINCT;
    public static final String STR_WHERE = "WHERE ";
    public static final String STR_LIMIT = "LIMIT";
    public static final String STR_SERVICE = "SERVICE";
    public static final String STR_FILTER = "FILTER";
    public static final String STR_LANG = "LANG";
    public static final String STR_ORDER_BY = "ORDER BY";
    
    /**W3C VOCABULARY*/
    public static final String STR_RDFS_LABEL = "rdfs:label";
    public static final String STR_SCHEMA_DESCRIPTION = "schema:description";
    
    // ABOUT WIKIDATA
    public static final String STR_WIKIDATA_SERVICE_PT_EN = STR_SERVICE+" wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],pt,en\" . }";
    public static final String STR_WIKIDATA_SERVICE = STR_SERVICE+" wikibase:label { bd:serviceParam wikibase:language \"en\" . }";
    public static final String STR_WIKIDATA_SERVICE_PT = STR_SERVICE+" wikibase:label { bd:serviceParam wikibase:language \"pt\" . }";
    
    // WIKIDATA ARRAY COLUMNS
    public static final int INT_Q = 0;
    public static final int INT_LABEL = 1;
    public static final int INT_DESCRIPTION = 2;
    
    // WIKIDATA OBJECT PROPERTY CODE
    public static final String STR_WDT_P31_INSTANCEOF = "wdt:P31";
    public static final String STR_WDT_P279_SUBCLASSOF = "wdt:P279";
    public static final String STR_WIKIDATA_MAIN_THEME = "wdt:P921";
    public static final String STR_WDT_P780_SYMPTOMS = "wdt:P780";
    public static final String WDT_P2176_DRUG_USED_FOR_TREATMENT = "wdt:P2176";
    public static final String WDT_P18_IMAGE = "wdt:P18";
    
    
    // WIKIDATA ITEM CODE
    public static final String STR_WD_Q169872_SYMPTOMNS = "wd:Q169872";
    public static final String STR_WD_Q12136_DISEASE = "wd:Q12136";
    public static final String[] STR_WIKIDATA_PREPRINT = {"wd:Q580922", "preprint", "Projeto de um artigo científico que não foi ainda publicado em um periódico científico com revisão por pares"};
    public static final String[] STR_WIKIDATA_ARTIGO_CIENTIFICO = {"wd:Q13442814", "Artigo científico", "artigo em uma publicação académica, normalmente revisada por pares"};
    
    
    
    /** SPARQL PREFIXES*/
    public static final String DEFAULT_SPARQL_PREFIXES = "PREFIX  dbo:  <http://dbpedia.org/ontology/>\n"
            + "PREFIX  dbr:     <http://dbpedia.org/resource/>\n"
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
            + "PREFIX bd: <http://www.bigdata.com/rdf#>\n"
            + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n";
    
   
    /**SPARQL ENDPOINTS*/
    public static final String STR_WIKIDATA_ENDPOINT = "https://query.wikidata.org/sparql";
    public static final String STR_DBPEDIA_ENDPOINT = "http://dbpedia.org/sparql";
    public static final String STR_DBPEDIA_PT_ENDPOINT = "http://pt.dbpedia.org/sparql";
}
