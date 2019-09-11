/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_codigo_postal;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceValidacionesSare;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    public cat_respuesta_services valida_num_ext(String num_ext, String letra_ext) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        if (num_ext.equals("") && !"".equals(letra_ext)) {
            respuesta.setMensaje(new cat_mensaje("true", ""));
        } else if ((num_ext != null)) {
            try {
                Integer.parseInt(num_ext);
                respuesta.setMensaje(new cat_mensaje("true", ""));
            } catch (NumberFormatException excepcion) {
                respuesta.setMensaje(new cat_mensaje("false", "el numero exterior debe ser numerico"));

            }
        } else if (letra_ext.equals("") && num_ext.equals("")) {
            respuesta.setMensaje(new cat_mensaje("falso", "agregue numero exterior"));
        } else {
            respuesta.setMensaje(new cat_mensaje("true", ""));
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

    public cat_respuesta_services validationsobjForm(String objects, String obj) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        JSONObject outerObject = new JSONObject(obj);
        JSONObject outerObject1 = new JSONObject(objects);
        JSONArray jsonArray = outerObject.getJSONArray("object");
        Map<String, Object> jsonArray1 = outerObject1.toMap();
        boolean banderaletra = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject json;
                json = jsonArray.getJSONObject(i);
                Object ele = json.getString("id");
                Object nombre = json.getString("name");
                Object value = (Object) jsonArray1.get(ele);
                if (nombre.equals("Letra interior")) {
                    if (value != null || !value.equals("")) {
                        banderaletra = true;
                    }
                } else if (nombre.equals("número exterior") && banderaletra == false) {
                    if (value == null || value.equals("")) {
                        respuesta.setMensaje(new cat_mensaje("false", "Ingrese " + nombre));
                        respuesta.setDatos(json.toString());
                        break;
                    }

                } else if (!nombre.equals("número exterior")) {
                    if (value == null || value.equals("")) {
                        respuesta.setMensaje(new cat_mensaje("false", "Ingrese " + nombre));
                        respuesta.setDatos(json.toString());
                        break;
                    } else if (value.equals("Seleccione")) {
                        respuesta.setMensaje(new cat_mensaje("false", "Ingrese " + nombre));
                        respuesta.setDatos(json.toString());
                        break;
                    } else if (!nombre.equals("número exterior") && value.equals("")) {
                        respuesta.setMensaje(new cat_mensaje("false", "Ingrese " + nombre));
                        respuesta.setDatos(json.toString());
                        break;
                    } else {
                        respuesta.setMensaje(new cat_mensaje("true", ""));
                        respuesta.setDatos(json.toString());
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

}
