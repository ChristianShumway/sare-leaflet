/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceTransformaCoordenadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */

@Service("BackingTransformCoordtoGeo")
public class BackingTransformCoordtoGeo {
    
    @Autowired
    @Qualifier("DaoTransformaCartografia")
    private InterfaceTransformaCoordenadas InterfaceTransformaCoordenadas;
    
    public cat_coordenadas transformCoords(Integer proyecto, String x, String y){
        cat_coordenadas coords = null;
        try {
            String cX = String.valueOf(Double.parseDouble(x.replace(",", ".")));
            String cY = String.valueOf(Double.parseDouble(y.replace(",", ".")));
            coords = InterfaceTransformaCoordenadas.TransformaCartografia(proyecto,"mer",cX, cY);
        } catch (Exception ex) {
            Logger.getLogger(BackingTransformCoordtoGeo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coords;
    }
    
}
