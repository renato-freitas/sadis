/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.negocio;

import java.util.Map;
import lar.jena.Ontologia;
import lar.telas.SidaUI;
import lar.util.Comum;

/**
 *
 * @author renato
 */
public class TemplateLinkSpec {
    
    
    private String geraTemplateLinkSpec(String dsSource, String dsTarget, String outFile){
		String source = Comum.cortaExtensao(dsSource);
		String target = Comum.cortaExtensao(dsTarget);
		return "<Silk>\n"+
  "<Prefixes>\n"+
  "    <Prefix id=\"rdf\" namespace=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\n"+
  "    <Prefix id=\"dbpp\" namespace=\"http://dbpedia.org/property/\"/>\n"+
  "    <Prefix id=\"dcterm\" namespace=\"http://purl.org/dc/terms/\"/>\n"+
  "    <Prefix id=\"dc\" namespace=\"http://purl.org/dc/elements/1.1/\"/>\n"+
  "    <Prefix id=\"owl\" namespace=\"http://www.w3.org/2002/07/owl#\"/>\n"+
  "    <Prefix id=\"foaf\" namespace=\"http://xmlns.com/foaf/0.1/\"/>\n"+
  "    <Prefix id=\"rdfs\" namespace=\"http://www.w3.org/2000/01/rdf-schema#\"/>\n"+
  "    <Prefix id=\"vocab\" namespace=\"http://ontologia_domain#\"/>\n"+
  "</Prefixes>\n"+
  "<DataSources>\n"+
  "    <Dataset id=\""+source+"\" type=\"file\">\n"+
  "        <Param name=\"file\" value=\""+dsSource+"\"/>\n"+
  "        <Param name=\"format\" value=\"N-Triples\"/>\n"+
  "        <Param name=\"graph\" value=\"\"/>\n"+
  "    </Dataset>\n"+
  "    <Dataset id=\""+target+"\" type=\"file\">\n"+
  "        <Param name=\"file\" value=\""+dsTarget+"\"/>\n"+
  "        <Param name=\"format\" value=\"N-Triples\"/>\n"+
  "        <Param name=\"graph\" value=\"\"/>\n"+
  "    </Dataset>\n"+
  "</DataSources>\n"+
  "<Interlinks>\n"+
  "    <Interlink id=\"task\">\n"+
  "        <SourceDataset dataSource=\""+source+"\" var=\"a\" typeUri=\"\">\n"+
  "            <RestrictTo>\n"+
  "                ?a ?p ?v .\n"+
  "            </RestrictTo>\n"+
  "        </SourceDataset>\n"+
  "        <TargetDataset dataSource=\""+target+"\" var=\"b\" typeUri=\"\">\n"+
  "            <RestrictTo>\n"+
  "                ?b ?p ?v .\n"+
  "            </RestrictTo>\n"+
  "        </TargetDataset>\n"+
        
  "        <LinkageRule linkType=\"owl:sameAs\">\n"+
  "            <Compare id=\"equality1\" required=\"false\" weight=\"1\" metric=\"equality\" threshold=\"0.0\" indexing=\"true\">\n"+
  "                <Input id=\"sourcePath1\" path=\"/sida:cpf\"/>\n"+
  "                <Input id=\"targetPath1\" path=\"/sida:cpf\"/>\n"+
  "            </Compare>"+
  "            <Filter/>\n"+
  "        </LinkageRule>\n"+
  "    </Interlink>\n"+
  "</Interlinks>\n"+
  "<Transforms>\n"+
  "</Transforms>\n"+
  "<Outputs>\n"+
  "    <Dataset id=\""+source+"_"+target+"\" type=\"file\">\n"+
  "        <Param name=\"file\" value=\"links.nt\"/>\n"+
  "        <Param name=\"format\" value=\"N-Triples\"/>\n"+
  "    </Dataset>\n"+
  "</Outputs>\n"+
"</Silk>";
	}
	
    
    /**
     * Retorna um String com os prefixos padrão juntamente com os prefixos da ontologia de domínio (vocabulario)
     * @since 22/07/2018
     */
    public static String geraPrefixos() {

        String prefixos = "<Prefixes>\n"
                + "    <Prefix id=\"dcterm\" namespace=\"http://purl.org/dc/terms/\"/>\n"
                + "    <Prefix id=\"dc\" namespace=\"http://purl.org/dc/elements/1.1/\"/>\n";
        try {
            for (Map.Entry<String, String> map : Ontologia.obtemPrefixosDaOntologiaDominio(SidaUI.ontologiaDeDominio).entrySet()) {
                if(map.getKey() != ""){
                    prefixos += "    <Prefix id=\"" + map.getKey() + "\" namespace=\""+map.getValue()+"\"/>\n";
                }
            }
        } catch (Exception e) {
        }
        prefixos += "</Prefixes>\n";
        System.out.println(Comum.printTab(prefixos));
        return prefixos;
    }
	
}
