/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Embeddable
public class TcMunicipiosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CVE_ENT")
    private String cveEnt;
    @Basic(optional = false)
    @Column(name = "CVE_MUN")
    private String cveMun;

    public TcMunicipiosPK() {
    }

    public TcMunicipiosPK(String cveEnt, String cveMun) {
        this.cveEnt = cveEnt;
        this.cveMun = cveMun;
    }

    public String getCveEnt() {
        return cveEnt;
    }

    public void setCveEnt(String cveEnt) {
        this.cveEnt = cveEnt;
    }

    public String getCveMun() {
        return cveMun;
    }

    public void setCveMun(String cveMun) {
        this.cveMun = cveMun;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cveEnt != null ? cveEnt.hashCode() : 0);
        hash += (cveMun != null ? cveMun.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcMunicipiosPK)) {
            return false;
        }
        TcMunicipiosPK other = (TcMunicipiosPK) object;
        if ((this.cveEnt == null && other.cveEnt != null) || (this.cveEnt != null && !this.cveEnt.equals(other.cveEnt))) {
            return false;
        }
        if ((this.cveMun == null && other.cveMun != null) || (this.cveMun != null && !this.cveMun.equals(other.cveMun))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcMunicipiosPK[ cveEnt=" + cveEnt + ", cveMun=" + cveMun + " ]";
    }
    
}
