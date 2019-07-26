/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceSincroniza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingSincroniza")
public class BackingSincroniza {
    
    @Autowired
    @Qualifier("DaoSincroniza")
    InterfaceSincroniza InterfaceSincroniza;
    
    public cat_respuesta_services sincronizaBDs(Integer proyecto,String usuario)
    {
        cat_respuesta_services Respuesta=new cat_respuesta_services();
        List<cat_vw_punteo_sare> pendientes;
        try{
            pendientes=InterfaceSincroniza.getListPendientesOcl(proyecto, usuario);
            if(pendientes.size()>0)
            {
                for(cat_vw_punteo_sare inmueble:pendientes)
                {
                    validaObject(inmueble,proyecto,usuario);
                }
            }
            
        }catch(Exception e){
            Logger.getLogger(BackingSincroniza.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return Respuesta;
    }
    
    public cat_respuesta_services validaObject(cat_vw_punteo_sare inmueble, Integer proyecto, String usuario){
        cat_respuesta_services Respuesta=new cat_respuesta_services();
        int oclOk = 0;
        int oclNg = 0;
        if(validaLocalidades(proyecto,inmueble,usuario))
                    {
                        if(validaEjes(proyecto,inmueble,usuario))
                        {
                            if(validaAgebs(proyecto,inmueble,usuario))
                            {
                                 if(validaTrAgebs(proyecto,inmueble,usuario))
                                 {
                                     if(validaTcManzanas(proyecto, inmueble, usuario))
                                     {
                                        if(validaTrManzanas(proyecto, inmueble, usuario))
                                        {
                                            if(validaTrLocalidades(proyecto, inmueble, usuario))
                                            {
                                                if(validaTcFrentes(proyecto, inmueble, usuario))
                                                {
                                                   if(validaTrFrentes(proyecto, inmueble, usuario))
                                                   {
                                                       if(UpdateTrUESuc(proyecto, inmueble, usuario))
                                                       {
                                                          if(InsertTrInmuebles(proyecto, inmueble, usuario))
                                                          {
                                                             if(ActualizaBitacora(proyecto, inmueble, usuario))
                                                             {
                                                               oclOk++;  
                                                               if(ActualizaIdUEPg(proyecto, inmueble, usuario))
                                                               {
                                                                if(ConfirmaUEPg(proyecto, inmueble, usuario))
                                                                {
                                                                   Respuesta.setMensaje(new cat_mensaje("true","Registro Completamente Guardado"));  
                                                                }
                                                                else
                                                                {
                                                                    Respuesta.setMensaje(new cat_mensaje("true","Registro Parcialmente Guardado")); 
                                                                }
                                                               }
                                                               else
                                                               {
                                                                   Respuesta.setMensaje(new cat_mensaje("Exito","Registro Parcialmente Guardado")); 
                                                               }
                                                             }
                                                             else
                                                             {
                                                                 oclNg++;
                                                                 Respuesta.setMensaje(new cat_mensaje("Exito","Registro Parcialmente Guardado")); 
                                                             }
                                                          }
                                                          else
                                                          {
                                                           oclNg++; 
                                                           Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en inmuebles"));   
                                                          }
                                                       }
                                                       else
                                                       {
                                                          Respuesta.setMensaje(new cat_mensaje("error","Error al hacer update en TR_UE_SUC"));  
                                                       }
                                                   }
                                                   else
                                                   {
                                                     Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en TrFrentes "));   
                                                   }
                                                }
                                                else
                                                {
                                                 Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en TcFrentes "));      
                                                }
                                            }
                                            else
                                            {
                                               Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en TrLocalicades ")); 
                                            }
                                        }
                                        else
                                        {
                                          Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en TrManzanas "));  
                                        }
                                     }else
                                     {
                                        Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en TcManzanas "));   
                                     }
                                 }else
                                 {
                                    Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en TrAgebs "));   
                                 }
                            }
                            else
                            {
                               Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en Agebs ")); 
                            }
                        }
                        else
                        {
                           Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en ejes ")); 
                        }
                    }else
                    {
                       Respuesta.setMensaje(new cat_mensaje("error","Error al insertar en Localidades "));  
                    }
        Respuesta.setDatos("Actualizados:"+oclOk+" Erroneos "+oclNg);
        return Respuesta;
        
    }
    
    protected boolean validaLocalidades(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.Validatelocalidades(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa= InterfaceSincroniza.Insertlocalidades(proyecto, inmueble, usuario);
       }
       return regresa;
    }
    
    protected boolean validaEjes(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateEjes(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertEjes(proyecto, inmueble, usuario); 
       }
       return regresa;
    }
    protected boolean validaAgebs(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateAgebs(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertAgebs(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean validaTrAgebs(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateTrAgebs(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertTrAgebs(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean validaTcManzanas(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateTcManzanas(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertTcManzanas(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean validaTrManzanas(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateTrManzanas(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertTrManzanas(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean validaTrLocalidades(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateTrLocalidades(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertTrLocalidades(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean validaTcFrentes(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateTcFrentes(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertTcFrentes(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean validaTrFrentes(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.ValidateTrFrentes(proyecto, inmueble, usuario);
       if(!regresa)
       {
          regresa=InterfaceSincroniza.InsertTrFrentes(proyecto, inmueble, usuario); 
       }
       return regresa;  
    }
    protected boolean UpdateTrUESuc(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.UpdateTrUeSuc(proyecto, inmueble, usuario);
       return regresa;  
    }
    protected boolean InsertTrInmuebles(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
       boolean regresa;
       regresa=InterfaceSincroniza.InsertTr_Inmuebles(proyecto, inmueble, usuario);
       return regresa;  
    }
    protected boolean ActualizaBitacora(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
        boolean regresa;
        regresa=InterfaceSincroniza.getActualizaBitRegCveunica(proyecto, inmueble, usuario);
        return regresa;  
    }
    protected boolean ActualizaIdUEPg(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
        boolean regresa;
        regresa=InterfaceSincroniza.getActualizaIdUe(proyecto, inmueble, usuario);
        return regresa;  
    }
    protected boolean ConfirmaUEPg(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
        boolean regresa;
        regresa=InterfaceSincroniza.getConfirmaUe(proyecto, inmueble, usuario);
        return regresa;  
    }
    
     protected boolean ActualicaEstatusComplemento(Integer proyecto,cat_vw_punteo_sare inmueble, String usuario)
    {
        boolean regresa;
        regresa=InterfaceSincroniza.getActualizaEstatusComplemento(proyecto, inmueble, usuario);
        return regresa;  
    }
     
     
}
