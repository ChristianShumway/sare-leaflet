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
public class cat_registro_ue_complemento_sare {
    
    private String ce;
    private String id_ue;
    private String sare_st_usr;
    private String sare_St_time;
    private String st_sare;

    public cat_registro_ue_complemento_sare() {
    }

    public cat_registro_ue_complemento_sare(String ce, String id_ue, String sare_st_usr, String sare_St_time, String st_sare) {
        this.ce = ce;
        this.id_ue = id_ue;
        this.sare_st_usr = sare_st_usr;
        this.sare_St_time = sare_St_time;
        this.st_sare = st_sare;
    }

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public String getId_ue() {
        return id_ue;
    }

    public void setId_ue(String id_ue) {
        this.id_ue = id_ue;
    }

    public String getSare_st_usr() {
        return sare_st_usr;
    }

    public void setSare_st_usr(String sare_st_usr) {
        this.sare_st_usr = sare_st_usr;
    }

    public String getSare_St_time() {
        return sare_St_time;
    }

    public void setSare_St_time(String sare_St_time) {
        this.sare_St_time = sare_St_time;
    }

    public String getSt_sare() {
        return st_sare;
    }

    public void setSt_sare(String st_sare) {
        this.st_sare = st_sare;
    }
    
    
    
    
}
