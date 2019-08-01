/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare.controller;


import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
import mx.org.inegi.sare.sare_db.dto.cat_c154;
import mx.org.inegi.sare.sare_db.dto.cat_codigo;
import mx.org.inegi.sare.sare_db.dto.cat_codigo_postal;
import mx.org.inegi.sare.sare_db.dto.cat_conjunto_comercial;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.dto.cat_get_claves;
import mx.org.inegi.sare.sare_db.dto.cat_piso;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_services.BackingActivacion;
import mx.org.inegi.sare.sare_services.BackingBusquedaSare;
import mx.org.inegi.sare.sare_services.BackingCatalogosSare;
import mx.org.inegi.sare.sare_services.BackingDesbloqueo;
import mx.org.inegi.sare.sare_services.BackingGetClavesSare;
import mx.org.inegi.sare.sare_services.BackingGuardar;
import mx.org.inegi.sare.sare_services.BackingListUEbyXY;
import mx.org.inegi.sare.sare_services.BackingLogin;
import mx.org.inegi.sare.sare_services.BackingPunteoSare;
import mx.org.inegi.sare.sare_services.BackingReportes;
import mx.org.inegi.sare.sare_services.BackingSincroniza;
import mx.org.inegi.sare.sare_services.BackingTransformCoordtoGeo;
import mx.org.inegi.sare.sare_services.BackingValidacionesSare;
import mx.org.inegi.sare.sare_services.utils.ResponseLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@RestController
public class ServiceController {

    @Autowired
    @Qualifier("BackingCatalogosSare")
    private BackingCatalogosSare BackingCatalogosSare;

    @Autowired
    @Qualifier("BackingValidacionesSare")
    private BackingValidacionesSare BackingValidacionesSare;

    @Autowired
    @Qualifier("BackingGetClaves")
    private BackingGetClavesSare BackingGetClaves;

    @Autowired
    @Qualifier("BackingBusqueda")
    private BackingBusquedaSare BackingBusqueda;

    @Autowired
    @Qualifier("BackingPunteo")
    private BackingPunteoSare BackingPunteo;

    @Autowired
    @Qualifier("BackingActivacion")
    private BackingActivacion BackingActivacion;

    @Autowired
    @Qualifier("BackingSincroniza")
    private BackingSincroniza BackingSincroniza;

    @Autowired
    @Qualifier("BackingGuardar")
    private BackingGuardar BackingGuardar;
    
    @Autowired
    @Qualifier("BackingLogin")
    private BackingLogin BackingLogin;
    
    @Autowired
    @Qualifier("BackingReportes")
    private BackingReportes BackingReportes;
    
    @Autowired
    @Qualifier("BackingListUEbyXY")
    private BackingListUEbyXY BackingListUEbyXY;
    
    @Autowired
    @Qualifier("BackingTransformCoordtoGeo")
    private BackingTransformCoordtoGeo BackingTransformCoordtoGeo;
    
    @Autowired
    @Qualifier("BackingDesbloqueo")
    private BackingDesbloqueo BackingDesbloqueo;

    @RequestMapping(value = "getCP.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_codigo_postal> getCP(@RequestParam(value = "cve_ent") String cve_ent, @RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingValidacionesSare.getcatcp(cve_ent, proyecto);
    }
    
    @RequestMapping(value = "validaCP.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services validaCP(@RequestParam(value = "codigo") String codigo,@RequestParam(value = "cve_ent") String cve_ent, @RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingValidacionesSare.validacp(codigo,cve_ent, proyecto);
    }
    @RequestMapping(value = "validaobjForm.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services validationsobjForm(@RequestParam(value = "obj") String obj, @RequestParam(value = "objrequest") String objrequest) throws Exception {
        //Gson gson = new Gson();
        //cat_vw_punteo_sare_guardado inmueble = (cat_vw_punteo_sare_guardado) gson.fromJson(obj, cat_vw_punteo_sare_guardado.class);
        return BackingValidacionesSare.validationsobjForm(obj,objrequest);
    }
    @RequestMapping(value = "valida_num_ext.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services valida_num_ext(@RequestParam(value = "numext") String numext) throws Exception {
        return BackingValidacionesSare.valida_num_ext(numext);
    }
    @RequestMapping(value = "valida_letra_ext.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services valida_letra_ext(@RequestParam(value = "letraext") String letraext) throws Exception {
        return BackingValidacionesSare.valida_num_ext(letraext);
    }

    @RequestMapping(value = "getCatAsentamientosHumanos.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_asentamientos_humanos> getCatAsentamientosHumanos(@RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingCatalogosSare.getCatalogoAsentamientosHumanos(proyecto);
    }

    @RequestMapping(value = "getListadoUnidadesEconomicas.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_get_claves> getListadoUnidadesEconomicas(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "tramo") String tramo, @RequestParam(value = "id_ue") String id_ue) throws Exception {
        return BackingGetClaves.getListadoUnidadesEconomicas(proyecto, id_ue, tramo);
    }

    @RequestMapping(value = "getListadoUnidadesEconomicasBloqueadas.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_get_claves> getListadoUnidadesEconomicasBloqueadas(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "tramo") String tramo, @RequestParam(value = "id_ue") String id_ue) throws Exception {
        return BackingGetClaves.getListadoUnidadesEconomicasBloqueadas(proyecto, id_ue, tramo);
    }

    @RequestMapping(value = "getbusqueda.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services getbusqueda(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "p") String p, @RequestParam(value = "tramo") String tramo, @RequestParam(value = "ce") String ce, @RequestParam(value = "usuario") String usuario, @RequestParam(value = "id_ue") String id_ue) throws Exception {
        int t = Integer.MIN_VALUE;
        Boolean consulta = false;
        if (p != null) {
            t = Integer.parseInt(p);
        }
        return BackingBusqueda.getBusqueda(proyecto, t, tramo, ce, usuario, id_ue, consulta);
    }

    @RequestMapping(value = "getDatabyCoords.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services getDatabyCoords(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "x") String x, @RequestParam(value = "y") String y, @RequestParam(value = "tc") String tc, @RequestParam(value = "r") Boolean r, @RequestParam(value = "ce") String ce, @RequestParam(value = "id_ue") String id_ue) throws Exception {
        return BackingPunteo.getDatabyCoords(proyecto, x, y, tc, r, ce, id_ue);
    }

    @RequestMapping(value = "liberacve.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services liberacve(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "id_ue") String id_ue) throws Exception {
        return BackingBusqueda.liberacve(proyecto, id_ue);
    }

    @RequestMapping(value = "sincronizaBDs.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services sincronizaBDs(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "usuario") String usuario) throws Exception {
        return BackingSincroniza.sincronizaBDs(proyecto, usuario);
    }

    @RequestMapping(value = "activa.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services activa(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "usuario") String usuario, @RequestParam(value = "id_ue") String id_ue, HttpServletRequest request) throws Exception {
        String ip = request.getRemoteAddr();
        return BackingActivacion.getActivaCveunicaPunteo(proyecto, usuario, id_ue, ip);
    }
    
    @RequestMapping(value = "getListUEbyXY.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services getListUEbyXY(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "x") String x,@RequestParam(value = "y") String y,@RequestParam(value = "opciones") String opciones, HttpServletRequest request) throws Exception {
        return BackingListUEbyXY.getListUEbyXY(proyecto, x, y, opciones);
    }

    @RequestMapping(value = "guardarUE.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services guardarUE(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "obj") String obj, @RequestParam(value = "usuario") String usuario, HttpServletRequest request, @RequestParam(value = "isAlta") Boolean isAlta) throws Exception {
        Gson gson = new Gson();
        cat_vw_punteo_sare_guardado inmueble = (cat_vw_punteo_sare_guardado) gson.fromJson(obj, cat_vw_punteo_sare_guardado.class);
        String ip = request.getRemoteAddr();
        return BackingGuardar.SaveUE(proyecto, inmueble, usuario, ip, isAlta);
    }
    
    @RequestMapping(value = "login.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services login(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "usuario") String usuario,@RequestParam(value = "password") String password,@RequestParam(value = "acceso") String acceso,@RequestParam(value = "clave_operativa") String clave_operativa,@RequestParam(value = "id_deftramo") String id_deftramo,@RequestParam(value = "nombre") String nombre,@RequestParam(value = "ce") String ce,@RequestParam(value = "id_ue") String id_ue,@RequestParam(value = "consulta") String consulta, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return BackingLogin.login(proyecto, usuario, password, acceso,clave_operativa,id_deftramo,nombre,ce,id_ue,consulta, request, response);
    }
    
    @RequestMapping(value = "Login.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services Login(@RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "usuario") String usuario,@RequestParam(value = "password") String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return BackingLogin.login(proyecto, usuario, password, request, response);
    }
    
    @RequestMapping(value = "Reportes.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services Reportes(@RequestParam(value = "proyecto") Integer proyecto,@RequestParam(value = "tipo") String tipo,@RequestParam(value = "reporte") String reporte,@RequestParam(value = "ce") String ce, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return BackingReportes.getReporte(proyecto,tipo,reporte,ce, request, response);
    }
    
    @RequestMapping(value = "transformCoords.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_coordenadas transformCoords(@RequestParam(value = "proyecto") Integer proyecto,@RequestParam(value = "x") String x,@RequestParam(value = "y") String y) throws Exception {
        return BackingTransformCoordtoGeo.transformCoords(proyecto,x,y);
    }
    
    @RequestMapping(value = "getCatConjuntosComerciales.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_conjunto_comercial> getCatConjuntosComerciales(@RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingCatalogosSare.getCatalogoConjuntosComerciales(proyecto);
    }
    
    @RequestMapping(value = "getCatPiso.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_piso> getCatPiso(@RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingCatalogosSare.getPiso(proyecto);
    }
    
    @RequestMapping(value = "desbloquea.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services desbloquea(@RequestParam(value = "proyecto") Integer proyecto,@RequestParam(value = "id_ue") String id_ue,@RequestParam(value = "usuario") String usuario) throws Exception {
        return BackingDesbloqueo.Desbloqueo(proyecto,id_ue,usuario);
    }
    
    @RequestMapping(value = "getC154_catalogo.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_c154> getC154_catalogo(@RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingCatalogosSare.getCatalogoC154(proyecto);
    }
    @RequestMapping(value = "getOrigen_catalogo.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<cat_codigo> getOrigen_catalogo(@RequestParam(value = "proyecto") Integer proyecto) throws Exception {
        return BackingCatalogosSare.getCodigos(proyecto);
    }
    
    @RequestMapping(value = "getDatosClasesPorFiltro.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public cat_respuesta_services getDatosClasesPorFiltro(@RequestParam(value = "cveoper") String cveoper, @RequestParam(value = "proyecto") Integer proyecto, @RequestParam(value = "codigoScian") String codigoScian) {
        return BackingCatalogosSare.getDatosClasesPorFiltro(proyecto,cveoper, codigoScian);
    }
    
    @RequestMapping(value = "/validaSesion.do", method = RequestMethod.POST, produces = javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public ResponseLocal validaSesion(HttpSession session) {
        String respuesta = null;
        ResponseLocal response = new ResponseLocal();
        Object u = (Object) session.getAttribute("respuesta");
        if (u == null) {
            respuesta = "/";
            response.setSuccess(false);
        } else {
            response.setDatos(u);
            response.setSuccess(true);
            respuesta = "/index";
        }
        return response;
    }
}
