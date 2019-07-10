/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceDesbloqueo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingDesbloqueo")
public class BackingDesbloqueo extends BackingSincroniza {
    
    @Autowired
    @Qualifier("DaoDesbloqueo")
    InterfaceDesbloqueo InterfaceDesbloqueo;
    
    
    public cat_respuesta_services Desbloqueo(Integer proyecto, String id_ue, String usuario){
        cat_respuesta_services respuesta=new cat_respuesta_services();
        int desbloqueo;
        try{
            String claves[]={id_ue};
            if(id_ue.contains(",")){
                claves=id_ue.split(",");
            }
            int x=0;
            int y=0;
            for(String ue:claves){
                desbloqueo=InterfaceDesbloqueo.VerificaDesbloqueo(proyecto, id_ue);
                switch(desbloqueo){
                    case 0:
                        if(InterfaceDesbloqueo.Desbloqueo(proyecto, id_ue)){
                            if(InterfaceDesbloqueo.DesbloqueoBitacora(proyecto,id_ue)){
                               respuesta.setMensaje(new cat_mensaje("true", "Exito al desbloquear")); 
                            }
                            
                        }
                        else{
                            respuesta.setMensaje(new cat_mensaje("false", "- Ocurrió un error al desbloquear el registro en Oracle"));
                        }
                    break;
                    case 1:
                        if(InterfaceDesbloqueo.completaGuardadoOcl(proyecto,usuario,id_ue)){
                            respuesta.setMensaje(new cat_mensaje("true", "Se actualizó el estatus y el registro ya está correctamente almacenado"));
                        }
                        else{
                            respuesta.setMensaje(new cat_mensaje("false", "- Ocurrió un error al actualizar el estatus del registro en Oracle"));
                        }
                    break;
                    case 2:
                        if(sincronizaRegistroBds(proyecto,usuario,id_ue)){
                            respuesta.setMensaje(new cat_mensaje("true", "- Se sincronizó el almacenamiento del registro correctamente"));
                        }
                        else
                        {
                           respuesta.setMensaje(new cat_mensaje("false", "- Ocurrió un error al sincronizar el registro")); 
                        }
                    break;
                    default:
                        respuesta.setMensaje(new cat_mensaje("false", "- Ocurrió un error al verificar el estatus del registro"));
                }
            }
        }
        catch(Exception e){
            respuesta.setMensaje(new cat_mensaje("false", "- Ocurrió una Excepción "+e.getMessage()));
        }
        return respuesta;
    }
    
    public boolean sincronizaRegistroBds(Integer proyecto,String usuario,String id_ue){
        boolean regresar = false;
        List<cat_vw_punteo_sare> pendientes;
        try{
            pendientes=InterfaceDesbloqueo.getRegistroPendientesOcl(proyecto,usuario,id_ue);
            if (pendientes!=null && pendientes.size() > 0) {
                 for (cat_vw_punteo_sare obj : pendientes) 
                 {
                     validaObject(obj,proyecto,usuario);
                 }
                 regresar=true;
            }
        }
        catch(Exception e){
            regresar=false;
        }
        return regresar;
    }
    
}
