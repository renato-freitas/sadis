/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.negocio;

import lar.telas.SidaUI;
import lar.util.Comum;

/**
 *
 * @author renato
 */
public class TemplatesR2RML {
    
    /**
     *Obtém o cabeçalho com os prefixos default para arquivo R2RML no formato .ttl.
     * @return String Cabeçalho com prefixos pradrão.
     */
    public static String prefixosPadrao(){
        return "@prefix map: <#>.\n" +
                "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>.\n" +
                "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>.\n" +
                "@prefix rr: <http://www.w3.org/ns/r2rml#>.\n" +
                "@prefix xsd: <http://www.w3.org/2001/XMLSchema#>.\n" +
                "@prefix foaf: <http://xmlns.com/foaf/0.1/>.\n"+
                "@prefix "+ Comum.cortaExtensao(SidaUI.nomeDaOD)+": <"+SidaUI.urlDaOD+">.";
    }
    
    public static String mapeaTabelaLogica(String tabela, String sql) {
        return "\n\nmap:"+tabela+" a rr:TriplesMap;\n"
                + "   rr:logicalTable [ \n"
                + "   rr:sqlQuery \"\"\"\n"
                + "   SELECT "//id, doc_ident, nome, vencimento\n"
                + " " +sql.substring(0, sql.lastIndexOf(",")) + "\n"
                + "   FROM "+tabela+"\n"
                + "   \"\"\"\n"
                + "   ];\n";
    }
    
    
    public static String mapeaSujeito(String prefixo, String tabela, String pk){
        return "   rr:subjectMap [ rr:class "+prefixo+":"+tabela+"; rr:template \""+tabela+"/{`"+pk+"`}\"; ];\n";
    }
    
    
    public static String mapeaPredicadoObjeto(String pre, String prop, String col) {
        return "   rr:predicateObjectMap [\n"
                + "      rr:predicate "+prop+";\n"
                + "      rr:objectMap [ rr:column \"`"+col+"`\"; ];\n"
                + "   ];\n";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String classe(String classe) {
        return "<#C_TriplesMap>\n"
                + "rr:logicalTable [ rr:tableName \"C\" ];\n"
                + "rr:subjectMap [\n"
                + "rr:template \"namespaceOfC/{K 1 }/{K 2 }/... /{K n }/\";\n"
                + "rr:class C; ];";
    }
    
    /** Se o datapyte tem cardinaliade igual a 1.
     */
    public String predicado() {
        return "rr:predicateObjectMap [\n"
                + "rr:predicate P;\n"
                + "rr:objectMap [ rr:column \"P\" ]; ];";
    }
    
    public String t3() {
        return "<#D_P_TriplesMap>\n"
                + "rr:logicalTable [ rr:tableName \"D_P\" ];\n"
                + "rr:subjectMap [\n"
                + "rr:template \"namespaceOfD/{K D1 }/{K D2 }/... /{K Dn }/\";\n"
                + "rr:class D; ];\n"
                + "rr:predicateObjectMap [\n"
                + "rr:predicate P;\n"
                + "rr:objectMap [ rr:column \"P\" ]; ];";
    }
    
    public String t4() {
        return "rr:predicateObjectMap [\n"
                + "rr:predicate P;\n"
                + "rr:objectMap [\n"
                + "rr:parentTriplesMap <R_TriplesMap>;\n"
                + "rr:joinCondition [\n"
                + "rr:child “K R1 ”;\n"
                + "rr:parent “K R1 ”; ];\n"
                + "...\n"
                + "rr:joinCondition [\n"
                + "rr:child “K Rn ”;\n"
                + "rr:parent “K Rn ”; ]; ]; ];";
    }
        
    public String t5() {
        return "<#D_P_TriplesMap>\n"
                + "rr:logicalTable [ rr:tableName \"D_P \" ];\n"
                + "rr:subjectMap [\n"
                + "rr:template \"namespaceOfD/{K D1 }/{K D2 }/... /{K Dn }/\";\n"
                + "rr:class D; ];\n"
                + "rr:predicateObjectMap [\n"
                + "rr:predicate P;\n"
                + "rr:objectMap [\n"
                + "rr:parentTriplesMap <R_TriplesMap>;\n"
                + "rr:joinCondition [\n"
                + "rr:child “K R1 ”;\n"
                + "rr:parent “K R1 ”; ];\n"
                + "...\n"
                + "rr:joinCondition [\n"
                + "rr:child “K Rn ”;\n"
                + "rr:parent “K Rn ”; ]; ]; ];";
    }
}
