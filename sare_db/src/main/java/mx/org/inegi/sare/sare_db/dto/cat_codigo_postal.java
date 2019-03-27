/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_codigo_postal {
    
    private String cve_ent;
    private String nom_entidad;
     private String cp_inicial;
    private String cp_final;

    public cat_codigo_postal() {
    }

    public cat_codigo_postal(String cp_inicial) {
        this.cp_inicial = cp_inicial;
    }
    
    

    public cat_codigo_postal(String cve_ent, String nom_entidad,String cp_inicial, String cp_final) {
        this.nom_entidad = nom_entidad;
        this.cve_ent = cve_ent;
        this.cp_inicial = cp_inicial;
        this.cp_final = cp_final;
    }

    public String getCve_ent() {
        return cve_ent;
    }

    public void setCve_ent(String cve_ent) {
        this.cve_ent = cve_ent;
    }

    public String getNom_entidad() {
        return nom_entidad;
    }

    public void setNom_entidad(String nom_entidad) {
        this.nom_entidad = nom_entidad;
    }

    public String getCp_inicial() {
        return cp_inicial;
    }

    public void setCp_inicial(String cp_inicial) {
        this.cp_inicial = cp_inicial;
    }

    public String getCp_final() {
        return cp_final;
    }

    public void setCp_final(String cp_final) {
        this.cp_final = cp_final;
    }

    
    

    
    
    
    
}
