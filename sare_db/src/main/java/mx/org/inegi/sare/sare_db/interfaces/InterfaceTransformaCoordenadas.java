/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceTransformaCoordenadas {
    
    public cat_coordenadas TransformaCartografia(Integer proyecto,String tipo, String x, String y);
    
}
