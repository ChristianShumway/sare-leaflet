/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

/**
 *
 * @author FABIOLA.RUIZ
 */
public class cat_frente_geometria {
    private String cveFrente;
    private String geometria;
    private String cve_ent;
    private String cve_mun;
    private String cve_loc;
    private String cve_ageb;
    private String cve_mza;
    private String nom_ent;
    private String nom_mun;
    private String nom_loc;

    public cat_frente_geometria() {
    }

    public cat_frente_geometria(String cveFrente, String geometria, String cve_ent, String cve_mun, String cve_loc, String cve_ageb, String cve_mza, String nom_ent, String nom_mun, String nom_loc) {
        this.cveFrente = cveFrente;
        this.geometria = geometria;
        this.cve_ent = cve_ent;
        this.cve_mun = cve_mun;
        this.cve_loc = cve_loc;
        this.cve_ageb = cve_ageb;
        this.cve_mza = cve_mza;
        this.nom_ent = nom_ent;
        this.nom_mun = nom_mun;
        this.nom_loc = nom_loc;
    }

   
    public String getCveFrente() {
        return cveFrente;
    }

    public void setCveFrente(String cveFrente) {
        this.cveFrente = cveFrente;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

    public String getCve_ent() {
        return cve_ent;
    }

    public void setCve_ent(String cve_ent) {
        this.cve_ent = cve_ent;
    }

    public String getCve_mun() {
        return cve_mun;
    }

    public void setCve_mun(String cve_mun) {
        this.cve_mun = cve_mun;
    }

    public String getCve_loc() {
        return cve_loc;
    }

    public void setCve_loc(String cve_loc) {
        this.cve_loc = cve_loc;
    }

    public String getCve_ageb() {
        return cve_ageb;
    }

    public void setCve_ageb(String cve_ageb) {
        this.cve_ageb = cve_ageb;
    }

    public String getCve_mza() {
        return cve_mza;
    }

    public void setCve_mza(String cve_mza) {
        this.cve_mza = cve_mza;
    }

    public String getNom_ent() {
        return nom_ent;
    }

    public void setNom_ent(String nom_ent) {
        this.nom_ent = nom_ent;
    }

    public String getNom_mun() {
        return nom_mun;
    }

    public void setNom_mun(String nom_mun) {
        this.nom_mun = nom_mun;
    }

    public String getNom_loc() {
        return nom_loc;
    }

    public void setNom_loc(String nom_loc) {
        this.nom_loc = nom_loc;
    }
    
    
    
    
}
