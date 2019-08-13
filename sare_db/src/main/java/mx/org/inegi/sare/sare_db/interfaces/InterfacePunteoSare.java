/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import java.util.List;
import mx.org.inegi.sare.sare_db.dto.cat_frente_geometria;
import mx.org.inegi.sare.sare_db.dto.cat_ubicacion_punteo;
import mx.org.inegi.sare.sare_db.dto.cat_uo;
import mx.org.inegi.sare.sare_db.dto.cat_vial;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfacePunteoSare {

    public String getTipoArea(Integer proyecto, String x, String y);

    public boolean isPuntoinMza(Integer proyecto, String x, String y);

    public String getEntidad(Integer proyecto, String x, String y);

    public cat_ubicacion_punteo getInfoPunteoUrbano(Integer proyecto, String ce, String x, String y);

    public List<cat_uo> getListaUO(Integer proyecto, String cveFrente, String idDeftramo);

    public String getConversionPuntosAMercator(String x, String y);

    public List<cat_frente_geometria> getGeometriaFrente(Integer proyecto, String x, String y);

    public List<cat_vial> validaInfoPunteoUrbano(String ent, String cve_geo, String cve_ft, Integer proyecto, String ce, String x, String y);

    public String getTipoVial(Integer proyecto, String tipoE10Xn);

    public List<cat_vial> getCatTipoVial(Integer proyecto);

    public cat_ubicacion_punteo getInfoPunteoRural(Integer proyecto, String x, String y);

    public boolean isFrentesProximos(Integer proyecto, String ent, String x, String y);

    public boolean isCECorrect(String x, String y, String ent, Integer proyecto);
}
