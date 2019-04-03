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
public class cat_IdentifyVialDTO {
    
    private String tipovial;
    private String nomvial;

    public cat_IdentifyVialDTO() {
    }

    public cat_IdentifyVialDTO(String tipovial, String nomvial) {
        this.tipovial = tipovial;
        this.nomvial = nomvial;
    }

    public String getTipovial() {
        return tipovial;
    }

    public void setTipovial(String tipovial) {
        this.tipovial = tipovial;
    }

    public String getNomvial() {
        return nomvial;
    }

    public void setNomvial(String nomvial) {
        this.nomvial = nomvial;
    }
    
    
    
}
