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

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map,
            @RequestParam(value = "acceso", defaultValue = "") String acceso,
            @RequestParam(value = "clave_operativa", defaultValue = "") String clave_operativa,
            @RequestParam(value = "nombre", defaultValue = "") String nombre,
            @RequestParam(value = "tramo_control", defaultValue = "") String tramo_control,
            HttpServletRequest request) {
        if (
            acceso!=null && !acceso.equals("")&& 
            clave_operativa!=null && !clave_operativa.equals("")&& 
            nombre!=null && !nombre.equals("") &&
            tramo_control!=null && !tramo_control.equals("") 
                ) {
            return "login";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index2(
            @RequestParam(value = "u", defaultValue = "") String u,
            @RequestParam(value = "p", defaultValue = "") String p,
            @RequestParam(value = "acceso", defaultValue = "") String acceso,
            @RequestParam(value = "clave_operativa", defaultValue = "") String clave_operativa,
            @RequestParam(value = "id_deftramo", defaultValue = "") String id_deftramo,
            @RequestParam(value = "nombre", defaultValue = "") String nombre,
            @RequestParam(value = "ce", defaultValue = "") String ce,
            @RequestParam(value = "cve_unica", defaultValue = "") String cve_unica,
            @RequestParam(value = "consulta", defaultValue = "") String consulta,
            HttpServletRequest request) {
        return "index";
    }

}
