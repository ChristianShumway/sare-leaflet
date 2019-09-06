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
public class resultadoPrediosEtqVal {
    
        private String ST_SARE;
	private String ID_UE;
	private String E03;
	private String E08;

    public resultadoPrediosEtqVal(String ST_SARE, String ID_UE, String E03, String E08) {
        this.ST_SARE = ST_SARE;
        this.ID_UE = ID_UE;
        this.E03 = E03;
        this.E08 = E08;
    }

    public String getST_SARE() {
        return ST_SARE;
    }

    public void setST_SARE(String ST_SARE) {
        this.ST_SARE = ST_SARE;
    }

    public String getID_UE() {
        return ID_UE;
    }

    public void setID_UE(String ID_UE) {
        this.ID_UE = ID_UE;
    }

    public String getE03() {
        return E03;
    }

    public void setE03(String E03) {
        this.E03 = E03;
    }

    public String getE08() {
        return E08;
    }

    public void setE08(String E08) {
        this.E08 = E08;
    }
        
        
    
}
