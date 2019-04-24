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
public class cat_conjunto_comercial {

    private String id_tipocomercial;
    private String descripcion;
    private String tipo_e19;

    public cat_conjunto_comercial(String id_tipocomercial, String descripcion, String tipo_e19) {
        this.id_tipocomercial = id_tipocomercial;
        this.descripcion = descripcion;
        this.tipo_e19 = tipo_e19;
    }

    public cat_conjunto_comercial() {
    }

    /**
     * @return the id_tipocomercial
     */
    public String getId_tipocomercial() {
        return id_tipocomercial;
    }

    /**
     * @param id_tipocomercial the id_tipocomercial to set
     */
    public void setId_tipocomercial(String id_tipocomercial) {
        this.id_tipocomercial = id_tipocomercial;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipo_e19
     */
    public String getTipo_e19() {
        return tipo_e19;
    }

    /**
     * @param tipo_e19 the tipo_e19 to set
     */
    public void setTipo_e19(String tipo_e19) {
        this.tipo_e19 = tipo_e19;
    }
}
