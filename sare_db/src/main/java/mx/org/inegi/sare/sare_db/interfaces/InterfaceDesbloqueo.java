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
public interface InterfaceDesbloqueo {
    
    public Integer VerificaDesbloqueo(Integer proyecto,String id_ue);
    public boolean Desbloqueo(Integer proyecto,String id_ue);
    public boolean DesbloqueoBitacora(Integer proyecto,String id_ue);
    public boolean completaGuardadoOcl(Integer proyecto,String usuario,String id_ue);
    public  List<cat_vw_punteo_sare> getRegistroPendientesOcl(Integer proyecto,String usuario, String id_ue);
    public boolean RegistraUEComplemento(Integer proyecto,String ce, String usuario, String id_ue);
    
}
