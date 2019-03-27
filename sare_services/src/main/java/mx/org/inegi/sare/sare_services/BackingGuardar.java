/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceGuardarUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingGuardar")
public class BackingGuardar extends BackingSincroniza {
    
     @Autowired
    @Qualifier("BackingGuardar")
    InterfaceGuardarUE InterfaceGuardar;

    public cat_respuesta_services SaveUE(Integer proyecto, cat_vw_punteo_sare inmueble,String usuario, String ip)
    {
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        
        if(inmueble!=null)
        {
            if(inmueble.getID_UE()==null || String.valueOf(inmueble.getCE()).equals("00") || inmueble.getTRAMO_CONTROL().substring(0,2).equals("00"))
            {
                Respuesta.setMensaje(new cat_mensaje("false", "Privilegios insuficientes para modificar datos"));
                Respuesta.setDatos(false);
            }
            else
            {
                try
                {
                    int validacion=InterfaceGuardar.getValidaUe(proyecto, inmueble);
                  if(validacion==1)
                  {
                      if(asignaClavesProvisionales(inmueble,proyecto))
                      {
                        if(InterfaceGuardar.getGuardaUe(proyecto, inmueble))
                        {
                          if(GuardarUeOCl(inmueble,proyecto, usuario))
                          {
                              if(ActualizaBitacora(proyecto, inmueble, usuario))
                              {
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
                                    Respuesta.setMensaje(new cat_mensaje("true","Registro Parcialmente Guardado"));
                                }
                              }
                              else
                              {
                                Respuesta.setMensaje(new cat_mensaje("true","Registro Parcialmente Guardado"));
                              }
                          }
                          else
                          {
                              Respuesta.setMensaje(new cat_mensaje("true","Registro Parcialmente Guardado"));
                          }
                        }
                        else
                        {
                            Respuesta.setMensaje(new cat_mensaje("true","Registro Parcialmente Guardado"));
                        }
                      }
                      else
                      {
                          Respuesta.setMensaje(new cat_mensaje("true","Registro Parcialmente Guardado"));
                      }
                  }
                  else
                  {
                    if(validacion==96)
                    {
                       Respuesta.setMensaje(new cat_mensaje("false","Clave unica duplicada")); 
                    }
                    else
                    {
                        if(validacion==99)
                        {
                            Respuesta.setMensaje(new cat_mensaje("false","Datos nulos")); 
                        }
                        else
                        {
                            if(validacion==99)
                            {
                                Respuesta.setMensaje(new cat_mensaje("false","Datos nulos")); 
                            } 
                            else
                            {
                                if(validacion==98)
                                {
                                    Respuesta.setMensaje(new cat_mensaje("false","C&oacute;digo Postal fuera de rango para la ubicaci&oacute;n seleccionada")); 
                                }
                                else
                                {
                                    if(validacion==98)
                                    {
                                    Respuesta.setMensaje(new cat_mensaje("false","Datos erroneos.")); 
                                    }  
                                }
                            }
                        }
                    }
                  }
                }
                catch(Exception e)
                {
                    
                }
            }
        }
        
        return Respuesta;
        
    }
    
    private boolean asignaClavesProvisionales(cat_vw_punteo_sare inmueble, Integer proyecto)
    {
        boolean regresar=false;
        if(Integer.valueOf(inmueble.getMod_cat())==2)
        {
            inmueble.setCveft(String.valueOf(1));
            if((inmueble.getE05()==null || (inmueble.getE05().isEmpty())) && (inmueble.getPunteo().equals("R")))
            {
                inmueble.setE05(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "l"));
                inmueble.setE07("800");
                regresar=true;
            }
            else
            {
                if(inmueble.getE07()==null || inmueble.getE07().isEmpty())
                {
                    inmueble.setE07(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "m"));
                    regresar=true;
                }
            }
        }
        else
        {
          if(inmueble.getE07()==null || inmueble.getE07().isEmpty())
                {
                    inmueble.setE07(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "m"));
                    regresar=true;
                }
                inmueble.setE23(InterfaceGuardar.e23a(proyecto,inmueble));
                inmueble.setE23(String.valueOf(InterfaceGuardar.getidDeftramo(proyecto,inmueble)));
        }
        return regresar;
    }
    private boolean GuardarUeOCl(cat_vw_punteo_sare inmueble, Integer proyecto, String usuario)
    {
        boolean regresar=false;
        if(validaLocalidades(proyecto, inmueble, usuario))
        {
           if(validaEjes(proyecto, inmueble, usuario))
           {
              if(validaAgebs(proyecto, inmueble, usuario))
              {
                  if(validaTrAgebs(proyecto, inmueble, usuario))
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
                                            regresar = InsertTrInmuebles(proyecto, inmueble, usuario);
                                        }
                                        
                                    }
                                }
                                
                            }
                        }
                     }
                  }
              } 
           } 
        }
        return regresar;
    }
    
}
