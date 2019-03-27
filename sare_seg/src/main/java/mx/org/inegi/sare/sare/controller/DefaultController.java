/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Controller
public class DefaultController {
    
//    @Autowired
//    @Qualifier("usuarioService")
   // private UsuarioService usuarioService;

    //AccesoSARE bitAcceso;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map, @RequestParam(value = "u", defaultValue = "") String user,
            @RequestParam(value = "p", defaultValue = "") String password,
            @RequestParam(value = "acceso", defaultValue = "") String acceso,
            @RequestParam(value = "tramo_control", defaultValue = "") String tramo_control,
            @RequestParam(value = "id_deftramo", defaultValue = "") String id_deftramo,
            @RequestParam(value = "nombre", defaultValue = "") String nombre,
            @RequestParam(value = "ce", defaultValue = "") String ce,
            @RequestParam(value = "cve_unica", defaultValue = "") String cve_unica,
            @RequestParam(value = "consulta", defaultValue = "") String consulta,
            HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (tramo_control.equals("") || tramo_control == null || tramo_control.length() != 8) {
            return "error";
        } else {
            Boolean sigue = true;
            String referer = request.getHeader("referer");
            String ip = request.getRemoteAddr();
            if (sigue) {
                Integer id_sistema = 1;
                //if (usuarioService.setBitacoraAcceso(ip, evento, id_sistema, tramo_control)) {
                return "error";
                /*} else {
                    return "error";
                }*/
            } else {
                return "error";
            }
        }
        

    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index2(ModelMap map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("respuesta") != null) {

        } else {
            map.addAttribute("user", null);
        }
        map.addAttribute("iktan", 0);
        return "index";
    }

    @RequestMapping(value = "/INDEX.HTML", method = RequestMethod.GET)
    public String index3(ModelMap map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("respuesta") != null) {

        } else {
            map.addAttribute("user", null);
        }
        map.addAttribute("iktan", 0);
        return "index";
    }

    @RequestMapping(value = "/status.html", method = RequestMethod.GET)
    public String statusPage(ModelMap map, HttpServletRequest request, @RequestParam(value = "iktan", defaultValue = "") String iktan) {
        return "status";
    }

//    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
//    public String login(ModelMap map, @RequestParam(value = "u", defaultValue = "0") String user,
//            @RequestParam(value = "p", defaultValue = "0") String password,
//            @RequestParam(value = "acceso", defaultValue = "0") String acceso,
//            @RequestParam(value = "clave_operativa", defaultValue = "00") String clave_operativa,
//            @RequestParam(value = "id_deftramo", defaultValue = "0") String id_deftramo,
//            @RequestParam(value = "nombre", defaultValue = "0") String nombre,
//            @RequestParam(value = "ce", defaultValue = "0") String ce,
//            @RequestParam(value = "cve_unica", defaultValue = "0") String cve_unica,
//            @RequestParam(value = "consulta", defaultValue = "0") String consulta,
//            @RequestParam(value = "tramo_control", defaultValue = "0") String tramo_control,
//            HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
//        String varReturn = "";
//        if (acceso != null) {
//            if (clave_operativa.equals("") || clave_operativa == null ) {
//                return "error";
//            } else if (acceso.equals("getSession")) {
//                varReturn = "index";
//            } else if (acceso.equals("login")) {
//                varReturn = "index";
//            } else if (acceso.equals("administrador")) {
//                Boolean isCreateSession = true; //authWithoutDir(ce, nombre, ce, request, cve_unica, consulta, id_deftramo);
//                if (isCreateSession) {
//                    String ip = request.getRemoteAddr();
//                    AccesoSARE bitAcceso = new AccesoSARE(nombre, clave_operativa, id_deftramo, ip,ce);
//                    if (usuarioService.registraAccesoPG(bitAcceso)) {
//                        String usuario="0";
//                        String tramo_ctrl="0";
//                        String id_deftr="0";
//                        String _ip="0";
//                        if(!"".equals(bitAcceso.getUsuario()) || bitAcceso.getUsuario()!=null){
//                          usuario=bitAcceso.getUsuario();
//                        }
//                        if(!"".equals(bitAcceso.getTramo_control())|| bitAcceso.getTramo_control()!=null){
//                         tramo_ctrl=bitAcceso.getTramo_control();
//                        }
//                        if(!"".equals(bitAcceso.getId_deftramo())||bitAcceso.getId_deftramo()!=null){
//                        id_deftr=bitAcceso.getId_deftramo();
//                        }
//                        if(!"".equals(bitAcceso.getIp()) || bitAcceso.getIp()!=null){
//                         _ip=bitAcceso.getIp();
//                        }
//                        Map<String, String> map2 = new HashMap<>();
//                        map2.put("usuario",usuario);
//                        map2.put("tramo_control",tramo_ctrl);
//                        map2.put("id_deftramo", id_deftr);
//                        map2.put("ip","12");   
//                        String regresa= bitAcceso.toString();
//                        map.addAttribute("test",regresa);
//                        //response.sendRedirect("index.html");
//                        varReturn = "index";
//                    } else {
//                        varReturn = "error";
//                    }
//                } else {
//                    varReturn = "error";
//                }
//            }
//        } else {
//            varReturn = "error";
//        }
//        return varReturn;
//    }

//    public TdUsuariosDTO makeSession(HttpServletRequest request, TdUsuariosDTO user) {
//        HttpSession session = request.getSession(true);
//        user.setSuccess(Boolean.TRUE);
//        session.setAttribute("userOnline", user);
//        return user;
//    }

//    public Boolean authWithoutDir(String cveoper, String nombre, String ce, HttpServletRequest request, String cve_unica, String consulta, String id_deftramo) {
//        Boolean isConsulta = false;
//        if (consulta != null && consulta.equals("true")) {
//            cveoper = "0000000";
//            ce = "00";
//            isConsulta = true;
//        }
//        Boolean success = false;
//        TdUsuariosDTO user = new TdUsuariosDTO();
//        user.setNombre(nombre);
//        user.setTramo_control(cveoper);
//        user.setCe(ce);
//        user.setPerfil(1);
//        Boolean masivo = cve_unica != null && !cve_unica.equals("") ? false : true;
//        user.setMasivo(masivo);
//        user.setCve_unica(cve_unica);
//        user.setConsulta(isConsulta);
//        user.setId_deftramo(id_deftramo);
//        user = makeSession(request, user);
//        if (user != null) {
//            success = true;
//        }
//        return success;
//    }
    
}
