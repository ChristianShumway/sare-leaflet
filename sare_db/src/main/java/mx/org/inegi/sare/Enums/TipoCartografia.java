/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.Enums;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public enum TipoCartografia {

    Mercator("mer"),
    Geografica("geo");
    private TipoCartografia() {
    }

    private TipoCartografia(String codigo) {
        this.codigo = codigo;
    }
    
    
    
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
