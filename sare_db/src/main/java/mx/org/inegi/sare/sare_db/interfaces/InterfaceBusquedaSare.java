/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceBusquedaSare {
    
    public List<cat_vw_punteo_sare> busqueda(Integer proyecto,String tramo, String ce, String usuario, int origen,String id_ue);
    
    public ArrayList<String> getClavesUnicasPG(Integer proyecto);
    
    public List<cat_vw_punteo_sare> getDatosInmuebles(Integer proyecto, String id_ue);
    
    public String getExtentBusquedaCvegeo (cat_vw_punteo_sare cat_vw_punteo_sare,Integer proyecto, int params, String tabla, boolean mza800, String rural[]);
    
    public String getExtentBusquedaCvegeo2 (cat_vw_punteo_sare cat_vw_punteo_sare,Integer proyecto, int params, String tabla, boolean mza800, String rural[]);

    public String getNombreBusqueda(Integer proyecto,Integer tipo, String tabla);
    
    public String getNombreBusqueda(cat_vw_punteo_sare cat_vw_punteo_sare,Integer proyecto,int params, String campo, String tabla);
    
    public boolean liberaCveunicaOCL(Integer proyecto,String cve_unica);
    
    public boolean getValCoorGeo(Integer proyecto, String x, String y, cat_vw_punteo_sare element);
    
    public boolean ocupaCveunicaOCL(Integer proyecto, String id_ue);
    
    public boolean ValidateCoordsEdo(Integer proyecto, cat_vw_punteo_sare element);
    
    
    //public boolean 
}
