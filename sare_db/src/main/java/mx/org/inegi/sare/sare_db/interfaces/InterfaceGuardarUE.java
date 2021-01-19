/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardadoUXFrente;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceGuardarUE {

    public Integer getValidaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble);

    public String getClaveProvisional(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, String capa);

    public boolean getGuardaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, boolean isAlta);

    public boolean getGuardaUePreparedStatement(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, boolean isAlta);

    public boolean UpdateOclStatusOk(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, String id_ue, boolean isAlta);

    public String e23a(Integer proyecto, cat_vw_punteo_sare_guardado inmueble);

    public Integer getidDeftramo(Integer proyecto, cat_vw_punteo_sare_guardado inmueble);

    public boolean UpdateOclStatusOcupado(Integer proyecto, cat_vw_punteo_sare_guardado object, String id_ue, boolean isAlta);

    public boolean GuardarUEFrentes(Integer proyecto, cat_vw_punteo_sare_guardadoUXFrente object);
    
    public boolean GuardarOclUEEPA(Integer proyecto, cat_vw_punteo_sare_guardado inmueble);

}
