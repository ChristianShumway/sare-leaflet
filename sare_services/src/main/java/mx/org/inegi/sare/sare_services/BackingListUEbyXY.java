/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_IdentificarDTO;
import mx.org.inegi.sare.sare_db.dto.cat_IdentificarVialidadDTO;
import mx.org.inegi.sare.sare_db.dto.cat_IdentifyVialDTO;
import mx.org.inegi.sare.sare_db.dto.cat_InfoInmueblesDTO;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceListUEbyXY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingListUEbyXY")
public class BackingListUEbyXY {

    @Autowired
    @Qualifier("DaoBackingListUEbyXY")
    InterfaceListUEbyXY InterfaceListUEbyXY;

    public cat_respuesta_services getListUEbyXY(Integer proyecto, String x, String y, String opciones) {
        cat_respuesta_services respuesta = new cat_respuesta_services();
        List<cat_InfoInmueblesDTO> listUE = null;
        List<cat_IdentifyVialDTO> listVial = null;
        cat_IdentificarDTO info = null;
        cat_IdentificarVialidadDTO infoVial = null;
        ArrayList res = new ArrayList();

        try {
            String opc[] = {opciones};
            if (opciones.contains(",")) {
                opc = opciones.split(",");
            }
            for (String op : opc) {
                switch (op.toLowerCase()) {
                    case "denue":
                        listUE = InterfaceListUEbyXY.getInfoUEDenue(proyecto,x, y);
                        if (listUE.size() > 0) {
                            info = new cat_IdentificarDTO("DENUE", listUE);
                            res.add(info);
                        }
                        break;
                    case "matrices":
                        listUE = InterfaceListUEbyXY.getInfoUE(proyecto,x, y, "M");
                        if (listUE.size() > 0) {
                            info = new cat_IdentificarDTO("Matrices", listUE);
                            res.add(info);
                        }
                        break;
                    case "sucursales":
                        listUE = InterfaceListUEbyXY.getInfoUE(proyecto,x, y, "S");
                        if (listUE.size() > 0) {
                            info = new cat_IdentificarDTO("Sucursales", listUE);
                            res.add(info);
                        }
                        break;
                    case "unicos":
                        listUE = InterfaceListUEbyXY.getInfoUE(proyecto,x, y, "U");
                        if (listUE.size() > 0) {
                            info = new cat_IdentificarDTO("unicos", listUE);
                            res.add(info);
                        }
                        break;
                    }
                        listVial = InterfaceListUEbyXY.getInfoVialidad(proyecto, x, y);
                        if (listVial.size() > 0) {
                            infoVial = new cat_IdentificarVialidadDTO("eje", listVial);
                            res.add(infoVial);
                        }
                        if (res.size() > 0) {
                            respuesta.setDatos(res);
                        } else {
                            respuesta.setMensaje(new cat_mensaje("error", "No se puede encontrar información en el punto"));
                        }
            }
        } catch (Exception e) {

        }

        return respuesta;
    }

}
