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
public class cat_asentamientos_humanos {
    
    private Integer id_tipoasen;
    private String descripcion;
    private String tipo_e14;

    public cat_asentamientos_humanos() {
    }

    public cat_asentamientos_humanos(Integer id_tipoasen, String descripcion, String tipo_e14) {
        this.id_tipoasen = id_tipoasen;
        this.descripcion = descripcion;
        this.tipo_e14 = tipo_e14;
    }

    public int getId_tipoasen() {
        return id_tipoasen;
    }

    public void setId_tipoasen(Integer id_tipoasen) {
        this.id_tipoasen = id_tipoasen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_e14() {
        return tipo_e14;
    }

    public void setTipo_e14(String tipo_e14) {
        this.tipo_e14 = tipo_e14;
    }
    
    
    
}
