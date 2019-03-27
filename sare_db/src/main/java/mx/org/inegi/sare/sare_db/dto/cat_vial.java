/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_vial {
     private String tipo_e10_X;
    private String tipo_e10_Xn;
    private String e10_X;
    private String e10_X_cvevial;
    private String e10_X_cveseg;
    
    private String tipo_e10;
    private String tipo_e10n;

    public cat_vial() {
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

    public cat_vial(String tipo_e10, String tipo_e10n) {
        this.tipo_e10 = tipo_e10;
        this.tipo_e10n = tipo_e10n;
    }
    
    

    public cat_vial(String tipo_e10_X, String tipo_e10_Xn, String e10_X, String e10_X_cvevial, String e10_X_cveseg) {
        this.tipo_e10_X = tipo_e10_X;
        this.tipo_e10_Xn = tipo_e10_Xn;
        this.e10_X = e10_X;
        this.e10_X_cvevial = e10_X_cvevial;
        this.e10_X_cveseg = e10_X_cveseg;
    }

    public String getTipo_e10_X() {
        return tipo_e10_X;
    }

    public void setTipo_e10_X(String tipo_e10_X) {
        this.tipo_e10_X = tipo_e10_X;
    }

    public String getTipo_e10_Xn() {
        return tipo_e10_Xn;
    }

    public void setTipo_e10_Xn(String tipo_e10_Xn) {
        this.tipo_e10_Xn = tipo_e10_Xn;
    }

    public String getE10_X() {
        return e10_X;
    }

    public void setE10_X(String e10_X) {
        this.e10_X = e10_X;
    }

    public String getE10_X_cvevial() {
        return e10_X_cvevial;
    }

    public void setE10_X_cvevial(String e10_X_cvevial) {
        this.e10_X_cvevial = e10_X_cvevial;
    }

    public String getE10_X_cveseg() {
        return e10_X_cveseg;
    }

    public void setE10_X_cveseg(String e10_X_cveseg) {
        this.e10_X_cveseg = e10_X_cveseg;
    }
    
    
}
