/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceGuardarUE {
    
    public Integer getValidaUe(Integer proyecto, cat_vw_punteo_sare inmueble);
    
    public String getClaveProvisional(Integer proyecto,cat_vw_punteo_sare inmueble, String capa);
    
    public boolean getGuardaUe(Integer proyecto,cat_vw_punteo_sare inmueble);
    
    public String e23a(Integer proyecto,cat_vw_punteo_sare inmueble);
    
    public Integer getidDeftramo(Integer proyecto,cat_vw_punteo_sare inmueble);
    
    
}
