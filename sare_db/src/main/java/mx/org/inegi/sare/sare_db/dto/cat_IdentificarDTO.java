/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.util.List;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_IdentificarDTO {
    
    private String capa;
    private List<cat_InfoInmueblesDTO> datos;
    
    

    public cat_IdentificarDTO() {
    }

    public cat_IdentificarDTO(String capa, List<cat_InfoInmueblesDTO> datos) {
        this.capa = capa;
        this.datos = datos;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public List<cat_InfoInmueblesDTO> getDatos() {
        return datos;
    }

    public void setDatos(List<cat_InfoInmueblesDTO> datos) {
        this.datos = datos;
    }
    
    
}
