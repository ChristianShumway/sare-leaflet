/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_usuarios;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceDesbloqueo;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceLogin;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
    @Qualifier("DaoLogin")
    InterfaceLogin InterfaceLogin;
    
    @Autowired
    @Qualifier("DaoDesbloqueo")
    InterfaceDesbloqueo InterfaceDesbloqueo;

    public cat_respuesta_services login(Integer proyecto, String usuario, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        HttpSession session = request.getSession(true);
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        cat_usuarios user = new cat_usuarios();
        user.setUsuario(usuario);
        user.setProyecto(proyecto);
        user = consultaUsuarioUEEPA(user);
        if(user.getPass()==null){
            user = consultaUsuariobyjefeUEEPA(user);
            if(user.getNombre()!=null){
                user = consultaUsuarioUEEPA(user);
            }
        }
        if (!Objects.equals(proyecto, null)) {
            user.setProyecto(proyecto);
        } else {
            user.setProyecto(3); //se inicializa con el proyecto en 5 de operativo masivo pero se necesita ver como cachar este 
        }
        if (user.getPass() != null && user.getPass().equals(password)) {
            //InterfaceDesbloqueo.RegistraUEComplemento(proyecto, user.getCe(), usuario, "");
            Respuesta.setMensaje(new cat_mensaje("Exito", ""));
        } else {
            if(user.getProyecto()==3){
                Respuesta.setMensaje(new cat_mensaje("Exito", ""));
            }else{
               Respuesta = getAuthValidWeb(usuario, password, ip, user.getProyecto()); 
            }
            
        }
        if (Respuesta.getMensaje().getType().equals("Exito")) {

            {
                if (user.getCe() != null) {
                    user.setUsuario(usuario);
                    if (!Objects.equals(proyecto, null)) {
                        user.setProyecto(proyecto);
                    } else {
                        user.setProyecto(3); //se inicializa con el proyecto en 5 de operativo masivo pero se necesita ver como cachar este 
                    }
                    user.setIp(ip);
                    if (registraAccesoPG(user)) {
                        session.setAttribute("respuesta", user);
                        Respuesta.setUsuario(user);
                    }
                } else {
                    Respuesta.setMensaje(new cat_mensaje("warning", "No cuenta con permiso para acceder al sistema"));
                }
            }
        }

        return Respuesta;
    }

    public cat_respuesta_services login(Integer proyecto, String usuario, String password,
            String acceso, String clave_operativa, String id_deftramo, String nombre, String ce, String id_ue, String consulta, HttpServletRequest request, HttpServletResponse response) throws IOException {

        cat_respuesta_services respuesta = null;

        if (acceso != null) {
            if (acceso.equals("getSession")) {
                respuesta = getSession(request);
            } else {
                if (acceso.equals("administrador")) {
                    Boolean isCreateSession = authWithoutDir(clave_operativa, nombre, ce, request, id_ue, consulta, id_deftramo);
                    if (isCreateSession) {
                        if ((id_ue == null || id_ue.isEmpty() || id_ue.equals("")) || (nombre == null || nombre.isEmpty())) {
                            response.sendRedirect("errorParametros.html");
                        } else {
                            String ip = request.getRemoteAddr();
                            cat_usuarios user = new cat_usuarios(nombre, id_ue, id_deftramo, ip);
                            if (registraAccesoPG(user)) {
                                response.sendRedirect("index.html");
                            } else {
                                response.sendRedirect("error.html");
                            }
                        }
                    } else {
                        response.sendRedirect("error.html");
                    }
                }
            }
        } else {
            response.sendRedirect("error.html");
        }

        return respuesta;

    }

    private cat_respuesta_services getAuthValidWeb(String user, String password, String ip, Integer proyecto) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        //cat_usuarios usuario = InterfaceLogin.getUserByUser(user.toLowerCase());
        Map data = new HashMap();
        Boolean success = false;
        if (user != null && proyecto!=3) {
            if ((password != null && !password.equals("")) && (user != null && !user.equals(""))) {
                success = autenticarDirAct(user, password);
            } else {
                success = autenticarDirAct(user, password);
            }
            if (success) {
                respuesta.setMensaje(new cat_mensaje("Exito", ""));
            } else {
                respuesta.setMensaje(new cat_mensaje("warning", "Datos de autenticación erroneos"));
            }
        }else{
            respuesta.setMensaje(new cat_mensaje("Exito", ""));
        }
        return respuesta;
    }

    private Boolean autenticarDirAct(String user, String password) {
        Boolean success = true;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://10.153.3.5:8080/AuntenticaDirectorioActivo/autentica.do?u=" + user + "&p=" + password);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(entity.getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            Gson g = new Gson();
            JsonObject jsonObject = new JsonParser().parse(result.toString()).getAsJsonObject();
            Boolean resp = Boolean.valueOf(jsonObject.get("result").getAsString());
            success = resp;
        } catch (IOException | IllegalStateException | JsonSyntaxException ex) {
            Logger.getLogger(BackingLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;

    }

    public boolean registraAccesoPG(cat_usuarios usuario) {
        boolean Respuesta;
        Respuesta = InterfaceLogin.registraAccesoPG(usuario);
        return Respuesta;
    }

    private cat_usuarios consultaUsuario(cat_usuarios usuario) {
        cat_usuarios Respuesta;
        Respuesta = InterfaceLogin.consultaUsuario(usuario);
        return Respuesta;
    }

    private cat_usuarios consultaUsuarioUEEPA(cat_usuarios usuario) {
        cat_usuarios Respuesta;
        Respuesta = InterfaceLogin.consultaUsuarioUEEPA(usuario);
        return Respuesta;
    }
    
    
    private cat_usuarios consultaUsuariobyjefeUEEPA(cat_usuarios usuario) {
        cat_usuarios Respuesta;
        Respuesta = InterfaceLogin.consultaUsuariobyjefeUEEPA(usuario);
        return Respuesta;
    }

    private cat_respuesta_services getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        cat_usuarios user;
        if (session != null) {

            user = (cat_usuarios) session.getAttribute("userOnline");
            if (user != null) {
                Respuesta.setDatos(user);
            } else {
                Respuesta.setDatos(null);
                Respuesta.setMensaje(new cat_mensaje("error", "No existe sesion actualmente"));
            }
        } else {
            Respuesta.setDatos(null);
            Respuesta.setMensaje(new cat_mensaje("error", "No existe sesion actualmente"));
        }
        return Respuesta;
    }

    private Boolean authWithoutDir(String cveoper, String nombre, String ce, HttpServletRequest request, String id_ue, String consulta, String id_deftramo) {
        Boolean isConsulta = false;
        if (consulta != null && consulta.equals("true")) {
            cveoper = "0000000";
            ce = "00";
            isConsulta = true;
        }
        Boolean success = false;
        cat_usuarios usuario = new cat_usuarios();
        usuario.setNombre(nombre);
        usuario.setTramo_control(cveoper);
        usuario.setCe(ce);
        usuario.setPerfil(1);
        Boolean masivo = id_ue == null || id_ue.equals("");
        usuario.setMasivo(masivo);
        usuario.setCve_unica(id_ue);
        usuario.setConsulta(isConsulta);
        usuario.setId_deftramo(id_deftramo);
        usuario = makeSession(request, usuario);
        if (usuario != null) {
            success = true;
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
