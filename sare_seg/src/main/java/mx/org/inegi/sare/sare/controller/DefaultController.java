/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.org.inegi.sare.sare_db.dto.cat_usuarios;
import mx.org.inegi.sare.sare_services.BackingLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController extends BackingLogin {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(ModelMap map,
            @RequestParam(value = "acceso", defaultValue = "") String acceso,
            @RequestParam(value = "clave_operativa", defaultValue = "") String clave_operativa,
            @RequestParam(value = "nombre", defaultValue = "") String nombre,
            @RequestParam(value = "ce", defaultValue = "") String ce,
            @RequestParam(value = "tramo_control", defaultValue = "") String tramo_control,
            @RequestParam(value = "proyecto", defaultValue = "") Integer proyecto,
            HttpServletRequest request) {
        if (acceso != null && !acceso.equals("")
                && clave_operativa != null && !clave_operativa.equals("")
                && nombre != null && !nombre.equals("")
                && ce!=null && !ce.equals("")
                && tramo_control != null && !tramo_control.equals("")
                && proyecto != null ) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30 * 60);
            Map data = new HashMap();
            if(tramo_control.equals("00000") && clave_operativa.equals("00000")){
                ce="00";
                tramo_control="00";
                clave_operativa="00";
            }
            data.put("clave_operativa",clave_operativa);
            data.put("tramo_control",tramo_control);
            data.put("proyecto",proyecto);
            data.put("ce",ce);
            data.put("nombre", nombre);
            session.setAttribute("respuesta", data);
            return "index";
        } else {
            return "error";
        }
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index1(ModelMap map,
            @RequestParam(value = "acceso", defaultValue = "") String acceso,
            @RequestParam(value = "clave_operativa", defaultValue = "") String clave_operativa,
            @RequestParam(value = "nombre", defaultValue = "") String nombre,
            @RequestParam(value = "ce", defaultValue = "") String ce,
            @RequestParam(value = "tramo_control", defaultValue = "") String tramo_control,
            @RequestParam(value = "proyecto", defaultValue = "") Integer proyecto,
            HttpServletRequest request) {
        if (acceso != null && !acceso.equals("")
                && clave_operativa != null && !clave_operativa.equals("")
                && nombre != null && !nombre.equals("")
                && ce!=null && !ce.equals("")
                && tramo_control != null && !tramo_control.equals("") 
                && proyecto != null ) {
           HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30 * 60);
            Map data = new HashMap();
            if(tramo_control.equals("00000") && clave_operativa.equals("00000")){
                ce="00";
                tramo_control="00";
                clave_operativa="00";
            }
            data.put("clave_operativa",clave_operativa);
            data.put("tramo_control",tramo_control);
            data.put("proyecto",proyecto);
            data.put("ce",ce);
            data.put("nombre", nombre);
            String ip=request.getRemoteAddr();
            cat_usuarios user=new cat_usuarios(nombre,"",tramo_control,ip);
            user.setProyecto(proyecto);
            user.setUsuario(nombre);
            user.setTramo_control(tramo_control);
            registraAccesoPG(user);
            session.setAttribute("respuesta", data);
            return "index";
        } else {
            return "error";
        }
    }
    
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(ModelMap map) {
//        return "login";
//    }

//    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
//    public String index2(
//            @RequestParam(value = "acceso", defaultValue = "") String acceso,
//            @RequestParam(value = "clave_operativa", defaultValue = "") String clave_operativa,
//            @RequestParam(value = "nombre", defaultValue = "") String nombre,
//            @RequestParam(value = "tramo_control", defaultValue = "") String tramo_control,
//            @RequestParam(value = "proyecto", defaultValue = "") Integer proyecto,
//            HttpServletRequest request) {
//        if (acceso != null && !acceso.equals("")
//                && clave_operativa != null && !clave_operativa.equals("")
//                && nombre != null && !nombre.equals("")
//                && tramo_control != null && !tramo_control.equals("")
//                && proyecto != null ) {
//            return "login";
//        } else {
//            return "index";
//        }
//    }

    @RequestMapping(value = "/error.html", method = RequestMethod.POST)
    public String index3(ModelMap map) {
        return "error";
    }
     @RequestMapping(value = "/error.html", method = RequestMethod.GET)
    public String index2(ModelMap map) {
        return "error";
    }

}
