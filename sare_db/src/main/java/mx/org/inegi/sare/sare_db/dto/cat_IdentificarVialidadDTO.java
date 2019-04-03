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
public class cat_IdentificarVialidadDTO {
    
    private String capa;
    private List<cat_IdentifyVialDTO> datos;

    public cat_IdentificarVialidadDTO() {
    }

    public cat_IdentificarVialidadDTO(String capa, List<cat_IdentifyVialDTO> datos) {
        this.capa = capa;
        this.datos = datos;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public List<cat_IdentifyVialDTO> getDatos() {
        return datos;
    }

    public void setDatos(List<cat_IdentifyVialDTO> datos) {
        this.datos = datos;
    }
    
    
    
}
