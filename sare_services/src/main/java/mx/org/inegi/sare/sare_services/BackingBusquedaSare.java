/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dao.DaoTransformaCartografia;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceBusquedaSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingBusqueda")
public class BackingBusquedaSare {

    @Autowired
    @Qualifier("DaoBusqueda")
    InterfaceBusquedaSare InterfaceBusquedaSare;

    @Autowired
    @Qualifier("DaoTransformaCartografia")
    DaoTransformaCartografia DaoTransformaCartografia;

    boolean mza800 = false;

    public cat_respuesta_services getBusqueda(Integer proyecto, int t, String tramo, String ce, String usuario, String id_ue, Boolean consulta) throws Exception {
        cat_respuesta_services Regresar = new cat_respuesta_services();
        List<cat_vw_punteo_sare> catBusquedaOracle;
        List<cat_vw_punteo_sare> catBusquedaPG = new ArrayList<>();
        Map<String, String> busqueda = new HashMap<>();
        String extent = null;
        String[] tabla = {"ent", "mun", "l", "a", "m", "cat_tipovialidad"};//producción
        String[] tabla_rural = {"lpr", "ar"};//producción
        String[] nombre = {"nom_ent", "nom_mun", "nomloc"};
        boolean regPg = false;
        boolean fsearch;
        ArrayList<String> listCUPG;
        int params;

        Double cX;
        Double cY;
        cat_coordenadas coord_merc;
        if ((id_ue != null && !id_ue.isEmpty()) && (ce != null && !ce.isEmpty())) 
        {
            try 
            {
                catBusquedaOracle = InterfaceBusquedaSare.busqueda(proyecto, tramo, ce, usuario, id_ue);
                if (catBusquedaOracle.size() > 0) 
                {
                    listCUPG = InterfaceBusquedaSare.getClavesUnicasPG(proyecto);
                    if (listCUPG != null) 
                    {
                        for (String ue : listCUPG) 
                        {
                            if (String.valueOf(catBusquedaOracle.get(0).getID_UE()).equals(ue)) 
                            {
                                regPg = true;
                            }
                        }
                    }
                }
                if (!regPg) 
                {
                    if (consulta && catBusquedaOracle.size() > 0) 
                    {
                        catBusquedaOracle.get(0).setEstatus_punteo(1);
                    }
                    if (catBusquedaOracle.size() > 0) 
                    {
                        if (t == 1) 
                        {
                            if (catBusquedaOracle.get(0).getEstatus_punteo() == null) 
                            {
                                busqueda.put("e", "b5");
                            } 
                            else 
                            {
                                switch (catBusquedaOracle.get(0).getEstatus_punteo()) 
                                {
                                    case 2:
                                        busqueda.put("e", "b4");
                                        break;
                                    case 4:
                                        for (cat_vw_punteo_sare element : catBusquedaOracle) 
                                        {
                                            catBusquedaPG = InterfaceBusquedaSare.getDatosInmuebles(proyecto,id_ue);
                                            cX = Double.parseDouble(String.valueOf(catBusquedaPG.get(0).getCOORD_X()).replace(",", "."));
                                            cY = Double.parseDouble(String.valueOf(catBusquedaPG.get(0).getCOORD_Y()).replace(",", "."));
                                            coord_merc = DaoTransformaCartografia.TransformaCartografia(proyecto, "geo", String.valueOf(cX), String.valueOf(cY));
                                            if (coord_merc != null) 
                                            {
                                                catBusquedaPG.get(0).setCOORD_X(new BigDecimal(coord_merc.getX()));
                                                catBusquedaPG.get(0).setCOORD_Y(new BigDecimal(coord_merc.getY()));
                                            }
                                            params = returnParams(element);
                                            if (params > 1) 
                                            {
                                                fsearch = true;
                                                while (fsearch) 
                                                {
                                                    extent = InterfaceBusquedaSare.getExtentBusquedaCvegeo2(element,proyecto, params, tabla[params - 1], mza800, tabla_rural);
                                                    params = params - 1;
                                                    if ((extent != null && !extent.equals("")) || params == 1) 
                                                    {
                                                        fsearch = false;
                                                    }
                                                }
                                            }
                                            if (catBusquedaPG.size() > 0) 
                                            {
                                                if (catBusquedaPG.get(0).getE05() != null && !catBusquedaPG.get(0).getE05().equals("")) 
                                                {
                                                    catBusquedaPG.get(0).setE05N(catBusquedaOracle.get(0).getE05N());
                                                }
                                                if (catBusquedaPG.get(0).getTIPO_E10()!= null && !String.valueOf(catBusquedaPG.get(0).getTIPO_E10()).equals("")) 
                                                {
                                                    catBusquedaPG.get(0).setTipo_e10n(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(catBusquedaPG.get(0).getTIPO_E10()), tabla[5]));
                                                }
                                                if (catBusquedaPG.get(0).getTIPO_E10_A()!= null && !String.valueOf(catBusquedaPG.get(0).getTIPO_E10_A()).equals("")) 
                                                {
                                                    catBusquedaPG.get(0).setTipo_e10_an(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(catBusquedaPG.get(0).getTIPO_E10_A()), tabla[5]));
                                                }
                                                if (catBusquedaPG.get(0).getTIPO_E10_B()!= null && !String.valueOf(catBusquedaPG.get(0).getTIPO_E10_B()).equals("")) 
                                                {
                                                    catBusquedaPG.get(0).setTipo_e10_bn(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(catBusquedaPG.get(0).getTIPO_E10_B()), tabla[5]));
                                                }
                                                if (catBusquedaPG.get(0).getTIPO_E10_C()!= null && !String.valueOf(catBusquedaPG.get(0).getTIPO_E10_C()).equals("")) 
                                                {
                                                    catBusquedaPG.get(0).setTipo_e10_cn(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(catBusquedaPG.get(0).getTIPO_E10_C()), tabla[5]));
                                                }
                                                catBusquedaPG.get(0).setExtent(extent);
                                            }
                                        }
                                        if(catBusquedaPG.get(0).getExtent() !=null && !catBusquedaPG.get(0).getExtent().equals(""))
                                        {
                                            Regresar.setDatos(catBusquedaPG);
                                        }
                                        else
                                        {
                                          InterfaceBusquedaSare.liberaCveunicaOCL(proyecto,id_ue);
                                          busqueda.put("e", "b3");
                                          Regresar.setDatos(busqueda);
                                        }
                                        break;
                                    default:
                                       params=0;
                                       mza800 = false;
                                       for(cat_vw_punteo_sare element:catBusquedaOracle)
                                       {
                                          if(element.getCOORD_X() != null && element.getCOORD_Y() != null)
                                          {
                                               cX = Double.parseDouble(String.valueOf(element.getCOORD_X()).replace(",", "."));
                                               cY = Double.parseDouble(String.valueOf(element.getCOORD_Y()).replace(",", "."));
                                               if(cX==0.0 && cY==0.0)
                                               {
                                                   element.setCOORD_X(null);
                                                   element.setCOORD_Y(null);
                                               }
                                               else
                                               {
                                                if(!InterfaceBusquedaSare.getValCoorGeo(proyecto,cX.toString(), cY.toString()))
                                                {
                                                    element.setCOORD_X(null);
                                                    element.setCOORD_Y(null);
                                                }   
                                               }
                                          }
                                          if(element.getCOORD_X()!=null && !String.valueOf(element.getCOORD_X()).equals("") && element.getCOORD_Y()!=null && String.valueOf(element.getCOORD_Y()).equals(""))
                                          {
                                                extent=InterfaceBusquedaSare.getExtentBusquedaCvegeo(element,proyecto, 0, null, mza800, null);
                                                cX = Double.parseDouble(String.valueOf(element.getCOORD_X()).replace(",", "."));
                                                cY = Double.parseDouble(String.valueOf(element.getCOORD_Y()).replace(",", "."));
                                                coord_merc = DaoTransformaCartografia.TransformaCartografia(proyecto,String.valueOf(cX),String.valueOf(cY), "geo");
                                                if(coord_merc!=null)
                                                {
                                                    element.setCOORD_X(new BigDecimal(coord_merc.getX()));
                                                    element.setCOORD_Y(new BigDecimal(coord_merc.getY()));
                                                }
                                          }
                                          else
                                          {
                                              params=returnParams(element);
                                              if(params>=1)
                                              {
                                                fsearch=true;
                                                while(fsearch)
                                                {
                                                    extent=InterfaceBusquedaSare.getExtentBusquedaCvegeo2(element,proyecto,params, tabla[params - 1], mza800, tabla_rural);
                                                    params=params-1;
                                                    if((extent!=null && !extent.equals("")) || params==1 || params==0)
                                                    {
                                                        fsearch = false;
                                                    }
                                                }
                                              }
                                              
                                          }
                                             if (element.getE03() != null && !element.getE03().equals("")) 
                                             {
                                                 element.setE03N(InterfaceBusquedaSare.getNombreBusqueda(element,proyecto, 1, nombre[0],tabla[0]));
                                             }
                                             if (element.getE04() != null && !element.getE04().equals("")) 
                                             {
                                                 element.setE04N(InterfaceBusquedaSare.getNombreBusqueda(element,proyecto, 2,nombre[1], tabla[1]));
                                             }
                                             if (element.getE05() != null && !element.getE05().equals("")) 
                                             {
                                                 element.setE05N(InterfaceBusquedaSare.getNombreBusqueda(element,proyecto, 3,nombre[2],tabla[2]));
                                             }
                                             if (element.getTIPO_E10()!= null && !String.valueOf(element.getTIPO_E10()).equals("")) 
                                             {
                                                 element.setTipo_e10n(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(element.getTIPO_E10()), tabla[5]));
                                             }
                                             if (element.getTIPO_E10_A()!= null && !String.valueOf(element.getTIPO_E10_A()).equals("")) 
                                             {
                                                 element.setTipo_e10_an(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(element.getTIPO_E10_A()), tabla[5]));
                                             }
                                             if (element.getTIPO_E10_B()!= null && !String.valueOf(element.getTIPO_E10_B()).equals("")) 
                                             {
                                                 element.setTipo_e10_bn(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(element.getTIPO_E10_B()), tabla[5]));
                                             }
                                             if (element.getTIPO_E10_C()!= null && !String.valueOf(element.getTIPO_E10_C()).equals("")) 
                                             {
                                                 element.setTipo_e10_cn(InterfaceBusquedaSare.getNombreBusqueda(proyecto,String.valueOf(element.getTIPO_E10_C()), tabla[5]));
                                             }
                                             element.setExtent(extent);
                                          
                                    }
                                    if(catBusquedaOracle.get(0).getExtent()!=null && !catBusquedaOracle.get(0).getExtent().equals(""))
                                    {
                                        Regresar.setDatos(catBusquedaOracle);
                                    }
                                    else
                                    {
                                          InterfaceBusquedaSare.liberaCveunicaOCL(proyecto,id_ue);
                                          busqueda.put("e", "b3");
                                          Regresar.setDatos(busqueda);
                                    }
                                    break;
                                    
                                        
                                }
                            }
                        }
                    }
                    else
                    {
                      busqueda.put("e", "b2");
                      Regresar.setDatos(busqueda);  
                    }

                }
                else
                {
                   busqueda.put("e", "b2a");
                   Regresar.setDatos(busqueda);  
                }

            } catch (Exception e) {
                 Logger.getLogger(BackingBusquedaSare.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else
        {
           busqueda.put("e", "b1");
           Regresar.setDatos(busqueda);  
        }

        return Regresar;
    }

    private int returnParams(cat_vw_punteo_sare element) {
        int params;
        if (element.getE07() != null && !element.getE07().isEmpty()) 
        {
            params = 5;
            if (element.getE07().equals("800")) 
            {
                mza800 = true;
            }
        } 
        else 
            if (element.getE06() != null && !element.getE06().isEmpty()) 
            {
                params = 4;
            } 
            else
            { 
                if (element.getE05() != null && !element.getE05().isEmpty()) 
                {
                    params = 3;
                } 
                else
                { 
                    if (element.getE04() != null && !element.getE04().isEmpty()) 
                    {
                        params = 2;
                    } 
                    else 
                    {
                        params = 1;
                    }
                }
            }
        return params;
    }
    
    public cat_respuesta_services liberacve(Integer proyecto,String id_ue)
    {
        cat_respuesta_services Regresar = new cat_respuesta_services();
        if(InterfaceBusquedaSare.liberaCveunicaOCL(proyecto,id_ue))
        {
          Regresar.setDatos("");
        }
        else
        {
          Regresar.setDatos("Error");  
        }
        return Regresar;
    }

}
