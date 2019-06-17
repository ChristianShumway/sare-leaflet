/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
import mx.org.inegi.sare.sare_db.dto.cat_c154;
import mx.org.inegi.sare.sare_db.dto.cat_conjunto_comercial;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceCatalogosSare {
    
    public List<cat_asentamientos_humanos> getCatalogoAsentamientosHumanos(Integer proyecto) throws Exception;
    public List<cat_conjunto_comercial> getCatalogoConjuntoComercial(Integer proyecto) throws Exception;
    public List<cat_c154>  getDatosClasesPorFiltro(Integer proyecto,String cveoper, String codigoScian);
    
}
