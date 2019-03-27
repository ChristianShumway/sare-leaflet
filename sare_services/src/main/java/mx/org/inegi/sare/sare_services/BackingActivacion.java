/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_bitacora_activacion_id_unidad_economica;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceActivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingActivacion")
public class BackingActivacion {
    
    @Autowired
    @Qualifier("DaoActivacion")
    InterfaceActivacion InterfaceActivacion;
    
    public cat_respuesta_services getActivaCveunicaPunteo(Integer proyecto, String usuario,String id_ue,String ip)
    {
        cat_respuesta_services Respuesta = new cat_respuesta_services() ;
        int x = 0;
        int y = 0;
        cat_bitacora_activacion_id_unidad_economica Registro=new cat_bitacora_activacion_id_unidad_economica();
        
        try
        {
          String claves[]={id_ue}; 
          if(id_ue.contains(","))
          {
              claves=id_ue.split(",");
          }
          
          for (String ue : claves) 
          {
                Registro.setCve_unica(new BigDecimal(ue));
                Registro.setUsuario(usuario);
                Registro.setIp(ip);
                if(InterfaceActivacion.activaCveUnicaInmuebles(proyecto,ue))
                {
                    if(InterfaceActivacion.activaCveUnicaUESuc(proyecto,ue))
                    {
                      if(InterfaceActivacion.activaCveUnicaUEcomplemento(proyecto,ue))
                      {
                         if(InterfaceActivacion.activaCveUnicaUEPG(proyecto, Registro)>0)
                         {
                             Respuesta.setDatos(Registro);
                             Respuesta.setMensaje(new cat_mensaje("01","Registro reactivado"));
                         }
                         else
                         {
                           Respuesta.setDatos(Registro);
                           Respuesta.setMensaje(new cat_mensaje("02","Error al procesar en postgress")); 
                         }
                      }
                      else
                      {
                       Respuesta.setDatos(Registro);
                       Respuesta.setMensaje(new cat_mensaje("02","Error al procesar UE COMPLEMENTO en Oracle"));    
                      }
                    }
                    else
                    {
                      Respuesta.setDatos(Registro);
                      Respuesta.setMensaje(new cat_mensaje("02","Error al procesar UE SUC en Oracle"));   
                    }
                }
                else
                {
                 Respuesta.setDatos(Registro);
                 Respuesta.setMensaje(new cat_mensaje("02","Error al procesar en inmuebles en Oracle"));    
                }
            } 
        }catch(Exception e)
        {
            Respuesta.setDatos(Registro);
            Respuesta.setMensaje(new cat_mensaje("99","Ocurrió una Excepción " + e.getMessage()));
            Logger.getLogger(BackingActivacion.class.getName()).log(Level.SEVERE, null, e);
        }
        return Respuesta;
        
    }
    


    
}
