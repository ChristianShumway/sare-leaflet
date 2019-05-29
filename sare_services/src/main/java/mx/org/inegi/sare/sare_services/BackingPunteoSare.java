/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.Enums.TipoAreaEnum;
import mx.org.inegi.sare.Enums.TipoCartografia;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_ubicacion_punteo;
import mx.org.inegi.sare.sare_db.dto.cat_vial;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfacePunteoSare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceTransformaCoordenadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingPunteo")
public class BackingPunteoSare extends BackingBusquedaSare
{
    
    @Autowired
    @Qualifier("DaoPunteoSare")
    InterfacePunteoSare InterfacePunteoSare;
    
    @Autowired
    @Qualifier("DaoTransformaCartografia")
    InterfaceTransformaCoordenadas InterfaceTransformaCoordenadas;
    
    cat_respuesta_services Respuesta=new cat_respuesta_services();
    
    public String getTipoArea (Integer proyecto, String x, String y) throws Exception 
    {
        String TipoArea = InterfacePunteoSare.getTipoArea(proyecto, x, y);
        return TipoArea;
    }
    
    public cat_respuesta_services getDatabyCoords(Integer proyecto,String x, String y, String tc, Boolean isAlta, String ce, String id_ue){
        Respuesta=new cat_respuesta_services();
        cat_coordenadas coordMercator;
        cat_ubicacion_punteo ubicacion_punteo = null;
        List<cat_vial> cat_vial = null;
        cat_vial vial=null;
        Boolean isCE=true;
        if(TipoCartografia.Geografica.getCodigo().equals(tc))
        {
            Double cX = Double.parseDouble(x.replace(",", "."));
            Double cY = Double.parseDouble(y.replace(",", "."));
            coordMercator=InterfaceTransformaCoordenadas.TransformaCartografia(proyecto, x, y, tc);
        }
        else
        {
            coordMercator = new cat_coordenadas(x, y);
        }
        if(!isAlta){
            List<cat_vw_punteo_sare> catBusquedaOracle=InterfaceBusquedaSare.busqueda(proyecto, tc, ce, "", 2,id_ue);
            if(catBusquedaOracle.size()>0)
            {
                 isCE = InterfacePunteoSare.isCECorrect(coordMercator.getX(), coordMercator.getY(), catBusquedaOracle.get(0).getE03(), proyecto);
            }
                else
                {
                     Respuesta=new cat_respuesta_services("error",new cat_mensaje("error", "La UE no tiene una Coordinación Estatal asginada"));
                }
        }
            if(isCE)
            {
                 String ta = InterfacePunteoSare.getTipoArea(proyecto,coordMercator.getX(), coordMercator.getY());
                 if(ta!=null)
                 {
                     if(TipoAreaEnum.URBANA.getArea().equals(ta))
                     {
                         if(InterfacePunteoSare.isPuntoinMza(proyecto, coordMercator.getX(), coordMercator.getY()))
                         {
                            Respuesta=new cat_respuesta_services("error",new cat_mensaje("false","La ubicacion del inmueble debe ser realizada sobre el frente de la manzana, no al interior")); 
                         }
                         else
                         {
                             String ent = InterfacePunteoSare.getEntidad(proyecto,coordMercator.getX(), coordMercator.getY()); 
                             ubicacion_punteo=InterfacePunteoSare.getInfoPunteoUrbano(proyecto, ent, coordMercator.getX(), coordMercator.getY());
                             if(ubicacion_punteo!=null && ubicacion_punteo.getMod_cat()==1)
                             {
                                 cat_vial=InterfacePunteoSare.validaInfoPunteoUrbano(ubicacion_punteo.getE03(),ubicacion_punteo.getCvegeo(),ubicacion_punteo.getCveft(),proyecto,ce,coordMercator.getX(), coordMercator.getY());
                                 ubicacion_punteo.setE10_X(new ArrayList<cat_vial>());
                                 ubicacion_punteo.setE10_X(cat_vial);
                                 switch(ubicacion_punteo.getE10_X().size())
                                 {
                                     case 2:
                                        vial = new cat_vial(null, "Ninguno", "Ninguno", "000", "");
                                        cat_vial.add(vial);
                                        ubicacion_punteo.setE10_X(cat_vial);
                                     break;
                                     case 1:
                                        vial = new cat_vial(null, "Ninguno", "Ninguno", "000", "");
                                        cat_vial.add(vial);
                                        vial = new cat_vial(null, "Ninguno", "Ninguno", "999", "");
                                        cat_vial.add(vial);
                                        ubicacion_punteo.setE10_X(cat_vial);
                                     break;
                                     case 0:
                                        vial = new cat_vial(null, "Ninguno", "Ninguno", "000", "");
                                        cat_vial.add(vial);
                                        ubicacion_punteo.setE10_X(cat_vial);
                                        vial = new cat_vial(null, "Ninguno", "Ninguno", "999", "");
                                        cat_vial.add(vial);
                                        ubicacion_punteo.setE10_X(cat_vial);
                                        vial = new cat_vial(null, "Ninguno", "Ninguno", "FFF", "");
                                        cat_vial.add(vial);
                                        ubicacion_punteo.setE10_X(cat_vial);
                                     break;
                                 }
                                 
                             }
                             if(ubicacion_punteo!=null)
                             {
                                 if(ubicacion_punteo.getE10_X()!=null && ubicacion_punteo.getE10_X().size()>0)
                                 {
                                    ubicacion_punteo.setE10_X(getVialidades(proyecto,ubicacion_punteo.getE10_X()));
                                    if(ubicacion_punteo.gettipo_e10n()!=null && !ubicacion_punteo.gettipo_e10n().equals(""))
                                    {
                                        ubicacion_punteo.setTIPO_E10(InterfacePunteoSare.getTipoVial(proyecto,ubicacion_punteo.gettipo_e10n().toLowerCase()));
                                    }
                                 }
                                 if(ubicacion_punteo.getMod_cat()==2 || isAlta)
                                 {
                                     List<cat_vial> catVial=InterfacePunteoSare.getCatTipoVial(proyecto); 
                                     ubicacion_punteo.setCatVial(catVial);
                                     if(InterfacePunteoSare.isFrentesProximos(proyecto,ent,coordMercator.getX(),coordMercator.getY()))
                                     {
                                         Respuesta=new cat_respuesta_services("",new cat_mensaje("confirmar","Existen Frentes proximos donde se podria ubicar la unidad económica, ¿Confirma que es correcta la ubicación seleccionada?"));
                                     }
                                 }
                                 ubicacion_punteo.setPunteo(ta);
                             }
                             else
                             {
                               Respuesta=new cat_respuesta_services("error",new cat_mensaje("","Ocurrio un error al realizar el punteo, favor de volverlo a intentar")); 
                             }
                         }
                     }
                     else
                     {
                      if(TipoAreaEnum.RURAL.getArea().equals(ta))
                      {
                          ubicacion_punteo=InterfacePunteoSare.getInfoPunteoRural(proyecto,coordMercator.getX(), coordMercator.getY());
                          List<cat_vial> catVial=InterfacePunteoSare.getCatTipoVial(proyecto);
                          ubicacion_punteo.setCatVial(catVial);
                          ubicacion_punteo.setPunteo(ta);
                      }   
                     }
                     Respuesta.setDatos(ubicacion_punteo);
                 }
            }
            else
            {
                Respuesta=new cat_respuesta_services("error",new cat_mensaje("error", "La ubicación del punto esta fuera de la Coordinación Estatal asginada"));
            }
         
        return Respuesta;
    }
    
    public List<cat_vial> getVialidades(Integer proyecto,List<cat_vial> vialidades){
        List<cat_vial> returnVialidades = new ArrayList<>();
        String tipo_vial = null;
        
        for(cat_vial vial:vialidades){
            cat_vial vialidad=new cat_vial(null, vial.getTipo_e10_Xn(), vial.getE10_X(),vial.getE10_X_cvevial(), vial.getE10_X_cveseg());
            vialidad.setTipo_e10_X("99");
            tipo_vial=InterfacePunteoSare.getTipoVial(proyecto, vial.getTipo_e10_Xn().toLowerCase());
            vialidad.setTipo_e10_X(tipo_vial);
            returnVialidades.add(vialidad);
        }
        return returnVialidades;
    }
   
    
    
    
}
