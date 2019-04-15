/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_codigo_postal;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceValidacionesSare {
    
    public List<cat_codigo_postal> getCP(String CP, Integer proyecto) throws Exception;
    
    public String isValidCpMsj(String CP,String entidad, Integer proyecto) throws Exception;
    
}
