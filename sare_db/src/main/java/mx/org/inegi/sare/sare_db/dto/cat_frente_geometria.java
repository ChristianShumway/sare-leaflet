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
    private String cveft;
   private String id_deftramo;
    public cat_frente_geometria() {
    }

    public cat_frente_geometria(String cveFrente, String geometria, String cveft,String id_deftramo) {
        this.cveFrente = cveFrente;
        this.geometria = geometria;
        this.id_deftramo = id_deftramo;     
        this.cveft=cveft;
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

    public String getCveft() {
        return cveft;
    }

    public void setCveft(String cveft) {
        this.cveft = cveft;
    }

    public String getId_deftramo() {
        return id_deftramo;
    }

    public void setId_deftramo(String id_deftramo) {
        this.id_deftramo = id_deftramo;
    }
    
    
    
    
    
    
}
