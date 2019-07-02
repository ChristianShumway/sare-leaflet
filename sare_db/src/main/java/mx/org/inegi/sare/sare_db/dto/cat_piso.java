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
public class cat_piso {
    
    private String id_tipopiso;
    private String tipo_e12p;
    private String descripcion;
    private String posicion;
    private String nivel;

    public String getId_tipopiso() {
        return id_tipopiso;
    }

    public cat_piso(String id_tipopiso, String tipo_e12p, String descripcion, String posicion, String nivel) {
        this.id_tipopiso = id_tipopiso;
        this.tipo_e12p = tipo_e12p;
        this.descripcion = descripcion;
        this.posicion = posicion;
        this.nivel = nivel;
    }

    
    public void setId_tipopiso(String id_tipopiso) {
        this.id_tipopiso = id_tipopiso;
    }

    public String getTipo_e12p() {
        return tipo_e12p;
    }

    public void setTipo_e12p(String tipo_e12p) {
        this.tipo_e12p = tipo_e12p;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
    
}
