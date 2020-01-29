/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.Enums;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public enum BusquedaEnum {
    BUSQUEDAOCL("SELECT to_char(ue.id_ue) as id_ue, ue.e03, ue.e04, ue.e05, ue.e06,"
            +   "ue.e07, ue.e08, ue.e09,lpad(to_char(ue.tipo_e10),2,'0') tipo_e10, "
            +   "ue.e10, ue.e11, TRIM(ue.e11a) as e11a lpad(to_char(ue.tipo_e14),2,'0') "
            +   "tipo_e14, ue.e14, lpad(to_char(ue.tipo_e10_a),2,'0') tipo_e10_a "
            +   "ue.e10_a,lpad(to_char(ue.tipo_e10_b),2,'0') tipo_e10_b, ue.e10_b, lpad(to_char(ue.tipo_e10_c),2,'0') "
            +   "ue.tipo_e10_c, ue.e10_c, ue.x_val as coorx, to_char(ue.y_val) as coory "
            +   "ue.e16 as descrubic, pre.st_sare estatus_punteo, ue.e12, ue.e19, ue.tipo_e19, ue.e20 "
            +   "ue.e13, TRIM(ue.e13a) as e13_a,ue.e14a as e14_a, --to_char(origen) "
            +   "'' origen, ue.ce as cestatal "
            +   "ue.e23a e23_a,ue.e17, ue.e17 --||' - '|| --e17_desc "
            +   "as codigo_scian,ue.c154, inm.id_inmueble,inm.CVEVIAL "), 
    GETCLAVESPG(""), 
    GETDATOSINMUEBLES(""), 
    GETEXTENTCVEGEO(""), 
    GETEXTENTCVEGEO2(""), 
    GETNOMBREBUSQUEDA(""), 
    GETNOMBREBUSQUEDAOCL(""),
    LIBERACLAVEUNICAORACLE(""), 
    GETVALCOORGEO(""), 
    OCUPACVEUNICA(""), 
    ACTUALIZACOMPLEMENTO(""), 
    BUSQUEDAMASIVOOTROS(""), 
    OCUPACVEUNICACONGLOMERADO(""), 
    LIBERACLAVEUNICAORACLEOTROS("");
    
    private String query;
    
    public String esquema;
    ProyectosEnum proyectos;

    private BusquedaEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
    
    
}
