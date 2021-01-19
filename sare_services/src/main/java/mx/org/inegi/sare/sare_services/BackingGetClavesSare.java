/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_get_claves;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceClavesSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingGetClaves")
public class BackingGetClavesSare {

    @Autowired
    @Qualifier("DaoGetClaves")
    InterfaceClavesSare InterfaceClavesSare;

    public List<cat_get_claves> getListadoUnidadesEconomicas(Integer proyecto, String ce, String tramo) throws Exception {
        List<cat_get_claves> ListadoUnidadesEconomicas = InterfaceClavesSare.getListadoUnidadesEconomicas(proyecto, ce, tramo);
        for (int i = 0; i < ListadoUnidadesEconomicas.size(); i++) {
            cat_get_claves clave = ListadoUnidadesEconomicas.get(i);
            String nom_ent=InterfaceClavesSare.getEntidad(ListadoUnidadesEconomicas.get(i).getE03());
            clave.setE04(InterfaceClavesSare.getMunicipio(ListadoUnidadesEconomicas.get(i).getE04(),ListadoUnidadesEconomicas.get(i).getE03()));
            clave.setE03(nom_ent);
            ListadoUnidadesEconomicas.set(i, clave);
        }
        return ListadoUnidadesEconomicas;
    }

    public List<cat_get_claves> getListadoUnidadesEconomicasBloqueadas(Integer proyecto, String ce, String tramo) throws Exception {
        List<cat_get_claves> ListadoUnidadesEconomicas = InterfaceClavesSare.getListadoUnidadesEconomicasBloqueadas(proyecto, ce, tramo);
        return ListadoUnidadesEconomicas;
    }

    public List<cat_get_claves> getListadoConglomerados(Integer proyecto, String ce, String tramo) throws Exception {
        List<cat_get_claves> ListadoUnidadesEconomicas = InterfaceClavesSare.getListadoConglomerados(proyecto, ce, tramo);
        return ListadoUnidadesEconomicas;
    }

}
