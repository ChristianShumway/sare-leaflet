/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
import mx.org.inegi.sare.sare_db.dto.cat_conjunto_comercial;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceCatalogosSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingCatalogosSare")
public class BackingCatalogosSare {
    
    @Autowired
    @Qualifier("DaoCatalogosSare")
    InterfaceCatalogosSare InterfaceCatalogosSare;
    
    public List<cat_asentamientos_humanos> getCatalogoAsentamientosHumanos (Integer proyecto) throws Exception 
    {
        List<cat_asentamientos_humanos> catAsentamientosHumanos = InterfaceCatalogosSare.getCatalogoAsentamientosHumanos(proyecto);
        return catAsentamientosHumanos;
    }
    
     public List<cat_conjunto_comercial> getCatalogoConjuntosComerciales (Integer proyecto) throws Exception 
    {
        List<cat_conjunto_comercial> catAsentamientosHumanos = InterfaceCatalogosSare.getCatalogoConjuntoComercial(proyecto);
        return catAsentamientosHumanos;
    }
    
    
}
