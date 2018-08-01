/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.negocio;

/**
 *
 * @author renato
 */
public class TemplatesSQLView {
    
    public String t1(String classe){
        return "CREATE OR REPLACE VIEW "+classe+"_VIEW AS"
                + "SELECT "+classe+".id, "+classe+".nome"
                + "FROM "+classe+";";
    }
}
