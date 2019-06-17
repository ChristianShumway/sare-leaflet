/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
import mx.org.inegi.sare.sare_db.dto.cat_c154;
import mx.org.inegi.sare.sare_db.dto.cat_codigo;
import mx.org.inegi.sare.sare_db.dto.cat_conjunto_comercial;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceCatalogosSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingCatalogosSare")
public class BackingCatalogosSare {

    @Autowired
    @Qualifier("DaoCatalogosSare")
    InterfaceCatalogosSare InterfaceCatalogosSare;

    public List<cat_asentamientos_humanos> getCatalogoAsentamientosHumanos(Integer proyecto) throws Exception {
        List<cat_asentamientos_humanos> catAsentamientosHumanos = InterfaceCatalogosSare.getCatalogoAsentamientosHumanos(proyecto);
        return catAsentamientosHumanos;
    }

    public List<cat_conjunto_comercial> getCatalogoConjuntosComerciales(Integer proyecto) throws Exception {
        List<cat_conjunto_comercial> catAsentamientosHumanos = InterfaceCatalogosSare.getCatalogoConjuntoComercial(proyecto);
        return catAsentamientosHumanos;
    }

    public List<cat_c154> getCatalogoC154(Integer proyecto) throws Exception {
        List<cat_c154> cat154 = new ArrayList<>();

        cat154.add(new cat_c154("01", "Levantado con información completa"));
        cat154.add(new cat_c154("A1", "Alta levantada con información completa"));
        cat154.add(new cat_c154("34", "Levantado por Internet"));
        cat154.add(new cat_c154("09", "Cierre por Huelga"));
        cat154.add(new cat_c154("10", "Cierre por Temporal"));
        cat154.add(new cat_c154("15", "Negativa"));
        cat154.add(new cat_c154("21", "Entrevista Incompleta"));
        cat154.add(new cat_c154("22A", "Ausencia del Informante"));

        return cat154;
    }
    public List<cat_codigo> getCodigos(Integer proyecto) throws Exception 
    {
        List<cat_codigo> codigos = new ArrayList<>();

        codigos.add(new cat_codigo("1", "Censos 2014"));
        codigos.add(new cat_codigo("2", "Intercensal"));
        codigos.add(new cat_codigo("3", "IMMEX"));
        codigos.add(new cat_codigo("4", "Expansión"));
        codigos.add(new cat_codigo("5", "CFE"));
        codigos.add(new cat_codigo("6", "SAT"));
        codigos.add(new cat_codigo("7", "Cámaras"));
        codigos.add(new cat_codigo("8", "Agropecuario"));
        codigos.add(new cat_codigo("9", "Altas"));

        return codigos;
    }
    
     public cat_respuesta_services getDatosClasesPorFiltro(Integer proyecto,String cveoper, String codigoScian) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        try {
            respuesta.setDatos(InterfaceCatalogosSare.getDatosClasesPorFiltro(proyecto,cveoper, codigoScian));
            respuesta.setMensaje(new cat_mensaje("true", "Se cargo la información"));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta.setMensaje(new cat_mensaje("true", "No se cargo la información"+ex));
        }
        return respuesta;
    }
}
