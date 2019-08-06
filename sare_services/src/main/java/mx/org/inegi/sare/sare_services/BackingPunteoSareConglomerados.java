/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.Enums.TipoAreaEnum;
import mx.org.inegi.sare.Enums.TipoCartografia;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.dto.cat_frente_geometria;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_ubicacion_punteo;
import mx.org.inegi.sare.sare_db.dto.cat_vial;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfacePunteoSareConglomerado;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceTransformaCoordenadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingPunteoConglomerados")
public class BackingPunteoSareConglomerados extends BackingBusquedaSare {

    @Autowired
    @Qualifier("DaoPunteoSareConglomerado")
    InterfacePunteoSareConglomerado InterfacePunteoSare;

    @Autowired
    @Qualifier("DaoTransformaCartografia")
    InterfaceTransformaCoordenadas InterfaceTransformaCoordenadas;

    cat_respuesta_services Respuesta = new cat_respuesta_services();

    public String getTipoArea(Integer proyecto, String x, String y) throws Exception {
        String TipoArea = InterfacePunteoSare.getTipoArea(proyecto, x, y);
        return TipoArea;
    }

    public cat_respuesta_services getDatabyCoords(Integer proyecto, String x, String y, String tc, Boolean isAlta, String ce, String id_ue) {
        Respuesta = new cat_respuesta_services();
        cat_coordenadas coordMercator;
        if (TipoCartografia.Geografica.getCodigo().equals(tc)) {
            Double cX = Double.parseDouble(x.replace(",", "."));
            Double cY = Double.parseDouble(y.replace(",", "."));
            coordMercator = InterfaceTransformaCoordenadas.TransformaCartografia(proyecto, x, y, tc);
        } else {
            coordMercator = new cat_coordenadas(x, y);
        }

        List<cat_frente_geometria> listaFrente = InterfacePunteoSare.getGeometriaFrente(proyecto, coordMercator.getX(), coordMercator.getY());
        if (listaFrente != null && listaFrente.size() > 0) {
            Respuesta.setDatos(listaFrente);
        } else {
            Respuesta = new cat_respuesta_services("error", new cat_mensaje("error", "La ubicación del punto esta fuera de la Coordinación Estatal asginada"));
        }
        return Respuesta;
    }

    public cat_respuesta_services getListaUO(Integer proyecto, String cveManzana) {
        Respuesta = new cat_respuesta_services();
        List<String> listaUO = InterfacePunteoSare.getListaUO(proyecto, cveManzana);
        if (listaUO != null && listaUO.size() > 0) {
            Respuesta.setDatos(listaUO);
        } else {
            Respuesta = new cat_respuesta_services("error", new cat_mensaje("error", "No se encontraron unidades para este frente "));
        }
        return Respuesta;
    }

    public List<cat_vial> getVialidades(Integer proyecto, List<cat_vial> vialidades) {
        List<cat_vial> returnVialidades = new ArrayList<>();
        String tipo_vial = null;

        for (cat_vial vial : vialidades) {
            cat_vial vialidad = new cat_vial(null, vial.getTipo_e10_Xn(), vial.getE10_X(), vial.getE10_X_cvevial(), vial.getE10_X_cveseg());
            vialidad.setTipo_e10_X("99");
            tipo_vial = InterfacePunteoSare.getTipoVial(proyecto, vial.getTipo_e10_Xn().toLowerCase());
            if (tipo_vial == null) {
                tipo_vial = InterfacePunteoSare.getTipoVial(proyecto, "otro (especifique)");
            }
            vialidad.setTipo_e10_X(tipo_vial);
            returnVialidades.add(vialidad);
        }
        return returnVialidades;
    }

}
