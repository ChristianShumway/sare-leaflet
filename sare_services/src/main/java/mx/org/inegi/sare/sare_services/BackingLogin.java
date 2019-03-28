/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.io.IOException;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_usuarios;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingLogin")
public class BackingLogin {
    
    @Autowired
    @Qualifier("DaoBackingLogin")
    InterfaceLogin InterfaceLogin;
    
    public cat_respuesta_services login(Integer proyecto, String usuario, String password, 
            String acceso, String clave_operativa,String id_deftramo,String nombre,String ce, String id_ue, String consulta, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        
        cat_respuesta_services respuesta = null;
        
        if(acceso!=null)
        {
            if(acceso.equals("getSession"))
            {
                respuesta=getSession(request);
            }else{
                if(acceso.equals("administrador")){
                    Boolean isCreateSession=authWithoutDir(clave_operativa, nombre, ce, request, id_ue, consulta, id_deftramo);
                    if(isCreateSession)
                    {
                       if((id_ue==null || id_ue.isEmpty()||id_ue.equals(""))||(nombre==null || nombre.isEmpty()))
                       {
                         response.sendRedirect("errorParametros.html");  
                       }
                       else
                       {
                           String ip=request.getRemoteAddr();
                           cat_usuarios user=new cat_usuarios(nombre,id_ue,id_deftramo,ip);
                           if(registraAccesoPG(user))
                           {
                             response.sendRedirect("index.html");  
                           }
                           else
                           {
                               response.sendRedirect("error.html");
                           }
                       }
                    }
                    else
                    {
                        response.sendRedirect("error.html");
                    }
                }
            }
        }
        else
        {
           response.sendRedirect("error.html"); 
        }
        
        return respuesta;
        
    }
    
    private boolean registraAccesoPG(cat_usuarios usuario){
        boolean Respuesta;
        Respuesta=InterfaceLogin.registraAccesoPG(usuario);
        return Respuesta;
    }
    
    private cat_respuesta_services getSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        cat_respuesta_services Respuesta=new cat_respuesta_services();
        cat_usuarios user;
        if(session!=null){
            
            user = (cat_usuarios) session.getAttribute("userOnline");
            if(user!=null)
            {
                Respuesta.setDatos(user);
            }else{
                Respuesta.setDatos(null);
                Respuesta.setMensaje(new cat_mensaje("error", "No existe sesion actualmente"));
            }
        }
        else{
            Respuesta.setDatos(null);
            Respuesta.setMensaje(new cat_mensaje("error", "No existe sesion actualmente"));
        }
        return Respuesta;
    }
    
    private Boolean authWithoutDir(String cveoper, String nombre, String ce, HttpServletRequest request, String id_ue, String consulta, String id_deftramo){
        Boolean isConsulta=false;
        if (consulta != null && consulta.equals("true")) {
            cveoper = "0000000";
            ce = "00";
            isConsulta = true;
        }
        Boolean success = false;
        cat_usuarios usuario=new cat_usuarios();
        usuario.setNombre(nombre);
        usuario.setTramo_control(cveoper);
        usuario.setCe(ce);
        usuario.setPerfil(1);
        Boolean masivo=id_ue == null || id_ue.equals("");
        usuario.setMasivo(masivo);
        usuario.setCve_unica(id_ue);
        usuario.setConsulta(isConsulta);
        usuario.setId_deftramo(id_deftramo);
        usuario=makeSession(request, usuario);
        if(usuario!=null){
            success=true;
        }
        return success;
    }
    
    private cat_usuarios makeSession(HttpServletRequest request, cat_usuarios user) {
        HttpSession session = request.getSession(true);
        user.setSuccess(Boolean.TRUE);
        session.setAttribute("userOnline", user);
        return user;
    }
    
}
