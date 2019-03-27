/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.math.BigDecimal;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_bitacora_activacion_id_unidad_economica {
    
    private String usuario;
    private String ip;
    private BigDecimal cve_unica;

    public cat_bitacora_activacion_id_unidad_economica() {
    }

    public cat_bitacora_activacion_id_unidad_economica(String usuario, String ip, BigDecimal cve_unica) {
        this.usuario = usuario;
        this.ip = ip;
        this.cve_unica = cve_unica;
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

    public BigDecimal getCve_unica() {
        return cve_unica;
    }

    public void setCve_unica(BigDecimal cve_unica) {
        this.cve_unica = cve_unica;
    }
    
    
    
}
