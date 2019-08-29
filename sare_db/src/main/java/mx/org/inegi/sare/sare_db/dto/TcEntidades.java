/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TC_ENTIDADES",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcEntidades.findAll", query = "SELECT t FROM TcEntidades t"),
    @NamedQuery(name = "TcEntidades.findByCveEnt", query = "SELECT t FROM TcEntidades t WHERE t.cveEnt = :cveEnt"),
    @NamedQuery(name = "TcEntidades.findByDescripcion", query = "SELECT t FROM TcEntidades t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcEntidades.findByDSiglas", query = "SELECT t FROM TcEntidades t WHERE t.dSiglas = :dSiglas")})
public class TcEntidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CVE_ENT")
    private String cveEnt;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "D_SIGLAS")
    private String dSiglas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcEntidades")
    private Collection<TcMunicipios> tcMunicipiosCollection;

    public TcEntidades() {
    }

    public TcEntidades(String cveEnt) {
        this.cveEnt = cveEnt;
    }

    public String getCveEnt() {
        return cveEnt;
    }

    public void setCveEnt(String cveEnt) {
        this.cveEnt = cveEnt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDSiglas() {
        return dSiglas;
    }

    public void setDSiglas(String dSiglas) {
        this.dSiglas = dSiglas;
    }

    @XmlTransient
    public Collection<TcMunicipios> getTcMunicipiosCollection() {
        return tcMunicipiosCollection;
    }

    public void setTcMunicipiosCollection(Collection<TcMunicipios> tcMunicipiosCollection) {
        this.tcMunicipiosCollection = tcMunicipiosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cveEnt != null ? cveEnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcEntidades)) {
            return false;
        }
        TcEntidades other = (TcEntidades) object;
        if ((this.cveEnt == null && other.cveEnt != null) || (this.cveEnt != null && !this.cveEnt.equals(other.cveEnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcEntidades[ cveEnt=" + cveEnt + " ]";
    }
    
}
