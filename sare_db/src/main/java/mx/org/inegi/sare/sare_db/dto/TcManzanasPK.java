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
public class TcManzanasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CVE_ENT")
    private String cveEnt;
    @Basic(optional = false)
    @Column(name = "CVE_MUN")
    private String cveMun;
    @Basic(optional = false)
    @Column(name = "CVE_LOC")
    private String cveLoc;
    @Basic(optional = false)
    @Column(name = "CVE_AGEB")
    private String cveAgeb;
    @Basic(optional = false)
    @Column(name = "CVE_MZA")
    private String cveMza;

    public TcManzanasPK() {
    }

    public TcManzanasPK(String cveEnt, String cveMun, String cveLoc, String cveAgeb, String cveMza) {
        this.cveEnt = cveEnt;
        this.cveMun = cveMun;
        this.cveLoc = cveLoc;
        this.cveAgeb = cveAgeb;
        this.cveMza = cveMza;
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

    public String getCveLoc() {
        return cveLoc;
    }

    public void setCveLoc(String cveLoc) {
        this.cveLoc = cveLoc;
    }

    public String getCveAgeb() {
        return cveAgeb;
    }

    public void setCveAgeb(String cveAgeb) {
        this.cveAgeb = cveAgeb;
    }

    public String getCveMza() {
        return cveMza;
    }

    public void setCveMza(String cveMza) {
        this.cveMza = cveMza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cveEnt != null ? cveEnt.hashCode() : 0);
        hash += (cveMun != null ? cveMun.hashCode() : 0);
        hash += (cveLoc != null ? cveLoc.hashCode() : 0);
        hash += (cveAgeb != null ? cveAgeb.hashCode() : 0);
        hash += (cveMza != null ? cveMza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcManzanasPK)) {
            return false;
        }
        TcManzanasPK other = (TcManzanasPK) object;
        if ((this.cveEnt == null && other.cveEnt != null) || (this.cveEnt != null && !this.cveEnt.equals(other.cveEnt))) {
            return false;
        }
        if ((this.cveMun == null && other.cveMun != null) || (this.cveMun != null && !this.cveMun.equals(other.cveMun))) {
            return false;
        }
        if ((this.cveLoc == null && other.cveLoc != null) || (this.cveLoc != null && !this.cveLoc.equals(other.cveLoc))) {
            return false;
        }
        if ((this.cveAgeb == null && other.cveAgeb != null) || (this.cveAgeb != null && !this.cveAgeb.equals(other.cveAgeb))) {
            return false;
        }
        if ((this.cveMza == null && other.cveMza != null) || (this.cveMza != null && !this.cveMza.equals(other.cveMza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcManzanasPK[ cveEnt=" + cveEnt + ", cveMun=" + cveMun + ", cveLoc=" + cveLoc + ", cveAgeb=" + cveAgeb + ", cveMza=" + cveMza + " ]";
    }
    
}
