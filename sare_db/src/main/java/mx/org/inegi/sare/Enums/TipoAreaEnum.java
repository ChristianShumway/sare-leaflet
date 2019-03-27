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
public enum TipoAreaEnum {
    
    URBANA("U"),
    RURAL("R");
    
    private String Area;

    private TipoAreaEnum(String Area) {
        this.Area = Area;
    }

    public String getArea() {
        return Area;
    }
    
    
}
