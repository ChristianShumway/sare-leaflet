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
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
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

    public List<cat_codigo_postal> getcatcp(String cve_ent, Integer proyecto) throws Exception {
        List<cat_codigo_postal> catcp = InterfaceValidacionesSare.getCP(cve_ent, proyecto);
        return catcp;
    }

    public cat_respuesta_services validacp(String cp, String entidad, Integer proyecto) throws Exception {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (cp.equals("") || cp == null) {
            List<cat_codigo_postal> codigoPostal = getcatcp(entidad, proyecto);
            cp = codigoPostal.get(0).getCp_final();
        }
        try {
            respuesta.setDatos(InterfaceValidacionesSare.isValidCpMsj(cp, entidad, proyecto));
        } catch (FileNotFoundException ex) {
            respuesta.setMensaje(new cat_mensaje("false", "Fallo al conectar a la base de datos"));
            Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            respuesta.setMensaje(new cat_mensaje("false", "Fallo al validar Codigo Postal"));
            Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(respuesta.getDatos().toString()) || respuesta.getDatos() == null) {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        } else {
            respuesta.setMensaje(new cat_mensaje("false", respuesta.getDatos().toString()));
        }
        return respuesta;
    }

    public cat_respuesta_services valida_Id_ue_busqueda(String id_ue) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (id_ue.equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese primero la clave a buscar"));
        } else {
            try {
                Integer.parseInt(id_ue);
            } catch (NumberFormatException excepcion) {
                respuesta.setMensaje(new cat_mensaje("false", "La clave debe ser numerica"));
            }

        }
        return respuesta;
    }

    public cat_respuesta_services valida_num_ext(String num_ext) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (num_ext.equals("")) {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        } else {
            try {
                Integer.parseInt(num_ext);
                respuesta.setMensaje(new cat_mensaje("true", ""));
            } catch (NumberFormatException excepcion) {
                respuesta.setMensaje(new cat_mensaje("false", "el numero exterior debe ser numerico"));
            }

        }
        return respuesta;
    }

    public cat_respuesta_services valida_letra_ext(String letra_ext) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (letra_ext.equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese letra exterior"));
        } else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        }
        return respuesta;
    }

    public cat_respuesta_services validationsobjForm(cat_vw_punteo_sare_guardado objects) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (objects.getE08().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese E08"));
        } else if (objects.getE09().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese E09"));
        } else if (objects.getE17_DESC().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese e17_DESC"));
        } else if (objects.getTipo_E14().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese tipo de asentamiento humano"));
        } else if (objects.getE14_A().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese código postal"));
        } else if (objects.getE14().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Nombre del asentamiento"));
        } else if (objects.getTipo_e10n().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de Vialidad"));
        } else if (objects.getE10().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de Vialidad"));
        } else if (objects.getE10_A().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad 1"));
        } else if (objects.getE10_B().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad 2"));
        } else if (objects.getE10_C().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad 3"));
        } else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        }
        return respuesta;
    }

    public cat_respuesta_services validationsobjFormPunteoEnFrente(cat_vw_punteo_sare_guardado objects) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (objects.getE08().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese E08"));
        } else if (objects.getE09().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese E09"));
        } else if (objects.getE17_DESC().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese e17_DESC"));
        } else if (objects.getTipo_E14().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese tipo de asentamiento humano"));
        } else if (objects.getE11().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese número exterior"));
        } else if (objects.getTipo_E14().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese tipo de asentamiento humano"));
        } else if (objects.getE14_A().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese código postal"));
        } else if (objects.getE14().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Nombre del asentamiento"));
        } else if (objects.getTipo_e10n().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de Vialidad"));
        } else if (objects.getTipo_e10_an().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de Vialidad"));
        } else if (objects.getE10().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de Vialidad"));
        } else if (objects.getE10_A().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad 1"));
        } else if (objects.getTipo_e10_b().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de Vialidad 2"));
        } else if (objects.getE10_B().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad 2"));
        } else if (objects.getTipo_e10_c().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de Vialidad Posterior"));
        } else if (objects.getE10_C().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad 3"));
        } else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        }
        return respuesta;
    }

    public cat_respuesta_services validationsobjFormAlta(cat_vw_punteo_sare_guardado objects) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (objects.getOrigen().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Origen"));
        } else if (objects.getC154().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese c154"));
        } else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        }
        return respuesta;
    }

    public cat_respuesta_services validationsobjFormRural(cat_vw_punteo_sare_guardado objects) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (objects.getTipo_e10n().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de vialidad"));
        } else if (objects.getE10().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre de vialidad"));
        } else if (objects.getE11().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Numero Exterior"));
        } else if (objects.getTipo_E14().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese tipo de asentamiento humano"));
        } else if (objects.getTipo_e10_an().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo vialidad 1"));
        } else if (objects.getTipo_e10_b().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo vialidad 2"));
        } else if (objects.getTipo_e10_c().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo vialidad 2"));
        } else if (objects.getDescrubic().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Descripción de la ubicación del establecimiento"));
        } else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        }
        return respuesta;
    }
     public cat_respuesta_services validationsobjFormCentrocomercial(cat_vw_punteo_sare_guardado objects) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (objects.getE12p().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Piso del edificio"));
        } else if (objects.getE12().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Numero de Edificio"));
        } else if (objects.getE19().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Nombre Edificio"));
        } else if (objects.getE20().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Numero de Local"));
        } else if (objects.getE13().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Numero interior"));
        } else if (objects.getE13_a().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Letra interior"));
        } else if (objects.getTipo_E19().equals("")) {
            respuesta.setMensaje(new cat_mensaje("false", "Ingrese Tipo de corredor o centro comercial"));
        }  else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        }
        return respuesta;
    }

}
