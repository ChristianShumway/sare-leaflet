/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.util.List;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_ubicacion_punteo {
    
    private String e03;
    private String e03n;
    private String e04;
    private String e04n;
    private String e05;
    private String e05n;
    private String e06;
    private String e07;
    private String cveft;
    private String e10;
    private String tipo_e10;
    private String tipo_e10n;
    private String e10_cvevial;
    private String e10_cveseg;
    private String cvegeo;    
    private List<cat_vial> e10_X;
    private String coord_x;
    private String coord_y;
    private String distancia;
    private List<cat_vial> catVial;
    private String punteo;
    private int mod_cat;
    private String cvegeo2016;

    public cat_ubicacion_punteo() {
    }

    public cat_ubicacion_punteo(String e03, String e03n, String e04, String e04n, String e05, String e05n, String e06, String e07, String cveft, String e10, String tipo_e10, String tipo_e10n, String e10_cvevial, String e10_cveseg, String cvegeo, List<cat_vial> e10_X, String coord_x, String coord_y, String distancia, List<cat_tipo_vialidad> catVial, String punteo, int mod_cat, String cvegeo2016) {
        this.e03 = e03;
        this.e03n = e03n;
        this.e04 = e04;
        this.e04n = e04n;
        this.e05 = e05;
        this.e05n = e05n;
        this.e06 = e06;
        this.e07 = e07;
        this.cveft = cveft;
        this.e10 = e10;
        this.tipo_e10 = tipo_e10;
        this.tipo_e10n = tipo_e10n;
        this.e10_cvevial = e10_cvevial;
        this.e10_cveseg = e10_cveseg;
        this.cvegeo = cvegeo;
        this.e10_X = e10_X;
        this.coord_x = coord_x;
        this.coord_y = coord_y;
        this.distancia = distancia;
        this.punteo = punteo;
        this.mod_cat = mod_cat;
        this.cvegeo2016 = cvegeo2016;
    }

    public String getE03() {
        return e03;
    }

    public void setE03(String e03) {
        this.e03 = e03;
    }

    public String getE03n() {
        return e03n;
    }

    public void setE03n(String e03n) {
        this.e03n = e03n;
    }

    public String getE04() {
        return e04;
    }

    public void setE04(String e04) {
        this.e04 = e04;
    }

    public String getE04n() {
        return e04n;
    }

    public void setE04n(String e04n) {
        this.e04n = e04n;
    }

    public String getE05() {
        return e05;
    }

    public void setE05(String e05) {
        this.e05 = e05;
    }

    public String getE05n() {
        return e05n;
    }

    public void setE05n(String e05n) {
        this.e05n = e05n;
    }

    public String getE06() {
        return e06;
    }

    public void setE06(String e06) {
        this.e06 = e06;
    }

    public String getE07() {
        return e07;
    }

    public void setE07(String e07) {
        this.e07 = e07;
    }

    public String getCveft() {
        return cveft;
    }

    public void setCveft(String cveft) {
        this.cveft = cveft;
    }

    public String getE10() {
        return e10;
    }

    public void setE10(String e10) {
        this.e10 = e10;
    }

    public String getTipo_e10() {
        return tipo_e10;
    }

    public void setTipo_e10(String tipo_e10) {
        this.tipo_e10 = tipo_e10;
    }

    public String getTipo_e10n() {
        return tipo_e10n;
    }

    public void setTipo_e10n(String tipo_e10n) {
        this.tipo_e10n = tipo_e10n;
    }

    public String getE10_cvevial() {
        return e10_cvevial;
    }

    public void setE10_cvevial(String e10_cvevial) {
        this.e10_cvevial = e10_cvevial;
    }

    public String getE10_cveseg() {
        return e10_cveseg;
    }

    public void setE10_cveseg(String e10_cveseg) {
        this.e10_cveseg = e10_cveseg;
    }

    public String getCvegeo() {
        return cvegeo;
    }

    public void setCvegeo(String cvegeo) {
        this.cvegeo = cvegeo;
    }

    public List<cat_vial> getE10_X() {
        return e10_X;
    }

    public void setE10_X(List<cat_vial> e10_X) {
        this.e10_X = e10_X;
    }

    public String getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(String coord_x) {
        this.coord_x = coord_x;
    }

    public String getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(String coord_y) {
        this.coord_y = coord_y;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public List<cat_vial> getCatVial() {
        return catVial;
    }

    public void setCatVial(List<cat_vial> catVial) {
        this.catVial = catVial;
    }

    public String getPunteo() {
        return punteo;
    }

    public void setPunteo(String punteo) {
        this.punteo = punteo;
    }

    public int getMod_cat() {
        return mod_cat;
    }

    public void setMod_cat(int mod_cat) {
        this.mod_cat = mod_cat;
    }

    public String getCvegeo2016() {
        return cvegeo2016;
    }

    public void setCvegeo2016(String cvegeo2016) {
        this.cvegeo2016 = cvegeo2016;
    }
    
    
    
}
