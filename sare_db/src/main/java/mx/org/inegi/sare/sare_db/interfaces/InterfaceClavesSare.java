/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_get_claves;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceClavesSare {
    
    public List<cat_get_claves> getListadoUnidadesEconomicas(Integer proyecto, String tramo, String ce) throws Exception;
    
    public List<cat_get_claves> getListadoUnidadesEconomicasBloqueadas(Integer proyecto, String tramo, String ce) throws Exception;
    
    public List<cat_get_claves> getListadoConglomerados(Integer proyecto, String tramo, String ce) throws Exception;
    
    public String getEntidad(String ent) throws Exception;
    public String getMunicipio(String mun, String ent) throws Exception;
    
}
