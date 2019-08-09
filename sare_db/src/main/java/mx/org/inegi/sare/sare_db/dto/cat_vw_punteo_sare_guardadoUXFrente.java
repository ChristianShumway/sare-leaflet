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
public class cat_vw_punteo_sare_guardadoUXFrente {
    
    private String capa;
    private String usuario;
    private String ip;
    private String manzana_destino;
    private String manzana_origen;
    private String frente_destino;
    private String frente_origen;
    private String claves;
    private String resultado;

    public cat_vw_punteo_sare_guardadoUXFrente() {
    }

    public cat_vw_punteo_sare_guardadoUXFrente(String capa, String usuario, String ip, String frente_destino, String frente_origen, String claves) {
        this.capa = capa;
        this.usuario = usuario;
        this.ip = ip;
        this.frente_destino = frente_destino;
        this.frente_origen = frente_origen;
        this.claves = claves;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    

    public String getManzana_destino() {
        return manzana_destino;
    }

    public void setManzana_destino(String manzana_destino) {
        this.manzana_destino = manzana_destino;
    }

    public String getManzana_origen() {
        return manzana_origen;
    }

    public void setManzana_origen(String manzana_origen) {
        this.manzana_origen = manzana_origen;
    }
    
    

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFrente_destino() {
        return frente_destino;
    }

    public void setFrente_destino(String frente_destino) {
        this.frente_destino = frente_destino;
    }

    public String getFrente_origen() {
        return frente_origen;
    }

    public void setFrente_origen(String frente_origen) {
        this.frente_origen = frente_origen;
    }

    public String getClaves() {
        return claves;
    }

    public void setClaves(String claves) {
        this.claves = claves;
    }
    
    
    
}
