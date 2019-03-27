/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceSincroniza {

    public List<cat_vw_punteo_sare> getListPendientesOcl(Integer proyecto, String usuario);

    public boolean Validatelocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean Insertlocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateEjes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertEjes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateTrAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertTrAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateTcManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertTcManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateTrManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertTrManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateTrLocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertTrLocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateTrFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertTrFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean ValidateTcFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);

    public boolean InsertTcFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    
    public boolean UpdateTrUeSuc(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    
    public boolean ActualizaTR_UE_Complemento(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    
    public boolean InsertTr_Inmuebles(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    
    public boolean getActualizaBitRegCveunica(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    
    public boolean getActualizaIdUe(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    
    public boolean getConfirmaUe(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario);
    

}
