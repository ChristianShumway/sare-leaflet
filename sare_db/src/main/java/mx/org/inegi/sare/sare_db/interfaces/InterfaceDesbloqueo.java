/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceDesbloqueo {
    
    public Integer VerificaDesbloqueo(Integer proyecto,String id_ue);
    public boolean Desbloqueo(Integer proyecto,String id_ue);
    public boolean completaGuardadoOcl(Integer proyecto,String usuario,String id_ue);
    
}
