/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_codigo_postal;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceValidacionesSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingValidacionesSare")
public class BackingValidacionesSare {
    
    @Autowired
    @Qualifier("DaoValidacionesSare")
    InterfaceValidacionesSare InterfaceValidacionesSare;
    
    public List<cat_codigo_postal> getcatcp (String cve_ent, Integer proyecto) throws Exception 
    {
        List<cat_codigo_postal> catcp = InterfaceValidacionesSare.getCP(cve_ent, proyecto);
        return catcp;
    }
}
