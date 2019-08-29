/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "TC_MUNICIPIOS",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcMunicipios.findAll", query = "SELECT t FROM TcMunicipios t"),
    @NamedQuery(name = "TcMunicipios.findByCveEnt", query = "SELECT t FROM TcMunicipios t WHERE t.tcMunicipiosPK.cveEnt = :cveEnt"),
    @NamedQuery(name = "TcMunicipios.findByCveMun", query = "SELECT t FROM TcMunicipios t WHERE t.tcMunicipiosPK.cveMun = :cveMun"),
    @NamedQuery(name = "TcMunicipios.findByDescripcion", query = "SELECT t FROM TcMunicipios t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcMunicipios.findByCestatal", query = "SELECT t FROM TcMunicipios t WHERE t.cestatal = :cestatal")})
public class TcMunicipios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcMunicipiosPK tcMunicipiosPK;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CESTATAL")
    private String cestatal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcMunicipios")
    private Collection<TcLocalidades> tcLocalidadesCollection;
    @JoinColumn(name = "CVE_ENT", referencedColumnName = "CVE_ENT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TcEntidades tcEntidades;

    public TcMunicipios() {
    }

    public TcMunicipios(TcMunicipiosPK tcMunicipiosPK) {
        this.tcMunicipiosPK = tcMunicipiosPK;
    }

    public TcMunicipios(String cveEnt, String cveMun) {
        this.tcMunicipiosPK = new TcMunicipiosPK(cveEnt, cveMun);
    }

    public TcMunicipiosPK getTcMunicipiosPK() {
        return tcMunicipiosPK;
    }

    public void setTcMunicipiosPK(TcMunicipiosPK tcMunicipiosPK) {
        this.tcMunicipiosPK = tcMunicipiosPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCestatal() {
        return cestatal;
    }

    public void setCestatal(String cestatal) {
        this.cestatal = cestatal;
    }

    @XmlTransient
    public Collection<TcLocalidades> getTcLocalidadesCollection() {
        return tcLocalidadesCollection;
    }

    public void setTcLocalidadesCollection(Collection<TcLocalidades> tcLocalidadesCollection) {
        this.tcLocalidadesCollection = tcLocalidadesCollection;
    }

    public TcEntidades getTcEntidades() {
        return tcEntidades;
    }

    public void setTcEntidades(TcEntidades tcEntidades) {
        this.tcEntidades = tcEntidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcMunicipiosPK != null ? tcMunicipiosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcMunicipios)) {
            return false;
        }
        TcMunicipios other = (TcMunicipios) object;
        if ((this.tcMunicipiosPK == null && other.tcMunicipiosPK != null) || (this.tcMunicipiosPK != null && !this.tcMunicipiosPK.equals(other.tcMunicipiosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcMunicipios[ tcMunicipiosPK=" + tcMunicipiosPK + " ]";
    }
    
}
