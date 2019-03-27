/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.org.inegi.sare.sare_db.dto.cat_usuarios;
/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class BackingLogin {
    
    public cat_respuesta_services login(Integer proyecto, String usuario, String password, 
            String acceso, String clave_operativa,String id_deftramo,String nombre,String ce, String id_ue, String consulta, HttpServletRequest request)
    {
        cat_respuesta_services respuesta = null;
        
        if(acceso!=null)
        {
            if(acceso.equals("getSession"))
            {
                
            }
        }
        
        return respuesta;
        
    }
    
    private String getSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        cat_usuarios user;
        if(session!=null){
            
            user = (cat_usuarios) session.getAttribute("userOnline");
        }
        return "";
    }
    
}
