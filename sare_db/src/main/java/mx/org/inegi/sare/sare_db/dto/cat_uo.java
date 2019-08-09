/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.math.BigDecimal;

/**
 *
 * @author FABIOLA.RUIZ
 */
public class cat_uo {
    private String idUoMasivo;
    private String x;
    private String y;
    private String geometria;
    private String idPredio;
    private String idInmueble;    

    public cat_uo() {
    }

    public cat_uo(String idUoMasivo, String x, String y,String geometria,String idPredio,String idInmueble) {
        this.idUoMasivo = idUoMasivo;
        this.x = x;
        this.y = y;
        this.geometria=geometria;
        this.idPredio=idPredio;
        this.idInmueble=idInmueble;
    }

    public String getIdUoMasivo() {
        return idUoMasivo;
    }

    public void setIdUoMasivo(String idUoMasivo) {
        this.idUoMasivo = idUoMasivo;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

    public String getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(String idPredio) {
        this.idPredio = idPredio;
    }

    public String getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }
    
    
}
