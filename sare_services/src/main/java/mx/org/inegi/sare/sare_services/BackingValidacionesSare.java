/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_codigo_postal;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceValidacionesSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingValidacionesSare")
public class BackingValidacionesSare {
    
    @Autowired
    @Qualifier("DaoValidacionesSare")
    InterfaceValidacionesSare InterfaceValidacionesSare;
    
    public List<cat_codigo_postal> getcatcp (String cve_ent, Integer proyecto) throws Exception 
    {
        List<cat_codigo_postal> catcp = InterfaceValidacionesSare.getCP(cve_ent, proyecto);
        return catcp;
    }
    
    public cat_respuesta_services validacp(String cp, String entidad, Integer proyecto) throws Exception{
        cat_respuesta_services respuesta=new cat_respuesta_services();
        try
        {
        respuesta.setDatos(InterfaceValidacionesSare.isValidCpMsj(cp, entidad, proyecto));
        }catch(FileNotFoundException ex)
        {
            respuesta.setMensaje(new cat_mensaje("false", "Fallo al conectar a la base de datos"));  
            Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex)
        {
            respuesta.setMensaje(new cat_mensaje("false", "Fallo al validar Codigo Postal")); 
            Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if("".equals(respuesta.getDatos().toString()) || respuesta.getDatos()==null)
        {
        respuesta.setMensaje(new cat_mensaje("true", ""));
        }else
        {
         respuesta.setMensaje(new cat_mensaje("false", respuesta.getDatos().toString()));   
        }
        return respuesta;
    }
}
