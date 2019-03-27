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
public class cat_tipo_vialidad {
    
     private String tipo_e10n;
     private String tipo_e10;

    public cat_tipo_vialidad() {
    }

    public cat_tipo_vialidad(String tipo_e10n, String tipo_e10) {
        this.tipo_e10n = tipo_e10n;
        this.tipo_e10 = tipo_e10;
    }

    public String getTipo_e10n() {
        return tipo_e10n;
    }

    public void setTipo_e10n(String tipo_e10n) {
        this.tipo_e10n = tipo_e10n;
    }

    public String getTipo_e10() {
        return tipo_e10;
    }

    public void setTipo_e10(String tipo_e10) {
        this.tipo_e10 = tipo_e10;
    }
     
     
    
}
