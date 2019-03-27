/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import mx.org.inegi.sare.sare_db.dto.cat_bitacora_activacion_id_unidad_economica;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceActivacion {
    
    public boolean activaCveUnicaInmuebles(Integer proyecto,String id_ue);
    public boolean activaCveUnicaUESuc(Integer proyecto,String id_ue);
    public boolean activaCveUnicaUEcomplemento(Integer proyecto,String id_ue);
    public int activaCveUnicaUEPG(Integer proyecto, cat_bitacora_activacion_id_unidad_economica cat_bitacora_activacion);
    
}
