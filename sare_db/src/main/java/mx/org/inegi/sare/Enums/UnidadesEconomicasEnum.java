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
public enum UnidadesEconomicasEnum {
    
    UNIDADES_ECONOMICAS("ue"),
    UNIDADES_ECONOMICAS_BLOQUEADAS("uebloq");
    
    private  String código;

    private UnidadesEconomicasEnum() {
    }
    
    private UnidadesEconomicasEnum(String código) {
        this.código = código;
    }

    public String getCódigo() {
        return código;
    }
   
    
}
