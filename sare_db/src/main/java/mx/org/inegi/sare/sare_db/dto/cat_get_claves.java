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
public class cat_get_claves {
    
    private String idue;
    private String c154;
    private String sare_st_usr;
    private String DIFERENCIA_HORAS;
    private String TIME_LOCK;
    private String status;
    private String e08;
    private String e03;
    private String e04;

    public cat_get_claves() {
    }

    public cat_get_claves(String idue, String c154, String status) {
        this.idue = idue;
        this.c154 = c154;
        this.status=status;
    }

    public cat_get_claves(String idue, String e08, String e03, String e04) {
        this.idue = idue;
        this.e08 = e08;
        this.e03 = e03;
        this.e04 = e04;
    }
    
    

    public cat_get_claves(String idue, String c154, String sare_st_usr, String DIFERENCIA_HORAS, String TIME_LOCK) {
        this.idue = idue;
        this.c154 = c154;
        this.sare_st_usr = sare_st_usr;
        this.DIFERENCIA_HORAS = DIFERENCIA_HORAS;
        this.TIME_LOCK = TIME_LOCK;
    }

    public String getE08() {
        return e08;
    }

    public void setE08(String e08) {
        this.e08 = e08;
    }

    public String getE03() {
        return e03;
    }

    public void setE03(String e03) {
        this.e03 = e03;
    }

    public String getE04() {
        return e04;
    }

    public void setE04(String e04) {
        this.e04 = e04;
    }
    
    

    public cat_get_claves(String idue, String status) {
        this.idue = idue;
        this.status = status;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public String getIdue() {
        return idue;
    }

    public void setIdue(String idue) {
        this.idue = idue;
    }

    public String getC154() {
        return c154;
    }

    public void setC154(String c154) {
        this.c154 = c154;
    }

    public String getSare_st_usr() {
        return sare_st_usr;
    }

    public void setSare_st_usr(String sare_st_usr) {
        this.sare_st_usr = sare_st_usr;
    }

    public String getDIFERENCIA_HORAS() {
        return DIFERENCIA_HORAS;
    }

    public void setDIFERENCIA_HORAS(String DIFERENCIA_HORAS) {
        this.DIFERENCIA_HORAS = DIFERENCIA_HORAS;
    }

    public String getTIME_LOCK() {
        return TIME_LOCK;
    }

    public void setTIME_LOCK(String TIME_LOCK) {
        this.TIME_LOCK = TIME_LOCK;
    }
    
    
    
    
    
}
