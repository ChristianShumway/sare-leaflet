/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Entity
@Table(name = "TC_AGEBS",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcAgebs.findAll", query = "SELECT t FROM TcAgebs t"),
    @NamedQuery(name = "TcAgebs.findByCveEnt", query = "SELECT t FROM TcAgebs t WHERE t.tcAgebsPK.cveEnt = :cveEnt"),
    @NamedQuery(name = "TcAgebs.findByCveMun", query = "SELECT t FROM TcAgebs t WHERE t.tcAgebsPK.cveMun = :cveMun"),
    @NamedQuery(name = "TcAgebs.findByCveLoc", query = "SELECT t FROM TcAgebs t WHERE t.tcAgebsPK.cveLoc = :cveLoc"),
    @NamedQuery(name = "TcAgebs.findByCveAgeb", query = "SELECT t FROM TcAgebs t WHERE t.tcAgebsPK.cveAgeb = :cveAgeb")})
public class TcAgebs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcAgebsPK tcAgebsPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcAgebs")
    private Collection<TcManzanas> tcManzanasCollection;
    @JoinColumns({
        @JoinColumn(name = "CVE_ENT", referencedColumnName = "CVE_ENT", insertable = false, updatable = false),
        @JoinColumn(name = "CVE_MUN", referencedColumnName = "CVE_MUN", insertable = false, updatable = false),
        @JoinColumn(name = "CVE_LOC", referencedColumnName = "CVE_LOC", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TcLocalidades tcLocalidades;

    public TcAgebs() {
    }

    public TcAgebs(TcAgebsPK tcAgebsPK) {
        this.tcAgebsPK = tcAgebsPK;
    }

    public TcAgebs(String cveEnt, String cveMun, String cveLoc, String cveAgeb) {
        this.tcAgebsPK = new TcAgebsPK(cveEnt, cveMun, cveLoc, cveAgeb);
    }

    public TcAgebsPK getTcAgebsPK() {
        return tcAgebsPK;
    }

    public void setTcAgebsPK(TcAgebsPK tcAgebsPK) {
        this.tcAgebsPK = tcAgebsPK;
    }

    @XmlTransient
    public Collection<TcManzanas> getTcManzanasCollection() {
        return tcManzanasCollection;
    }

    public void setTcManzanasCollection(Collection<TcManzanas> tcManzanasCollection) {
        this.tcManzanasCollection = tcManzanasCollection;
    }

    public TcLocalidades getTcLocalidades() {
        return tcLocalidades;
    }

    public void setTcLocalidades(TcLocalidades tcLocalidades) {
        this.tcLocalidades = tcLocalidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcAgebsPK != null ? tcAgebsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcAgebs)) {
            return false;
        }
        TcAgebs other = (TcAgebs) object;
        if ((this.tcAgebsPK == null && other.tcAgebsPK != null) || (this.tcAgebsPK != null && !this.tcAgebsPK.equals(other.tcAgebsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcAgebs[ tcAgebsPK=" + tcAgebsPK + " ]";
    }
    
}
