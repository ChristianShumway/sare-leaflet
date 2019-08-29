/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "TC_ASENTAMIENTO",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcAsentamiento.findAll", query = "SELECT t FROM TcAsentamiento t"),
    @NamedQuery(name = "TcAsentamiento.findByCveTipoAsen", query = "SELECT t FROM TcAsentamiento t WHERE t.cveTipoAsen = :cveTipoAsen"),
    @NamedQuery(name = "TcAsentamiento.findByDescripcion", query = "SELECT t FROM TcAsentamiento t WHERE t.descripcion = :descripcion")})
public class TcAsentamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CVE_TIPO_ASEN")
    private Short cveTipoAsen;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipoE14")
    private Collection<TrEtqVal> trEtqValCollection;
    @OneToMany(mappedBy = "tipoE14")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcAsentamiento() {
    }

    public TcAsentamiento(Short cveTipoAsen) {
        this.cveTipoAsen = cveTipoAsen;
    }

    public TcAsentamiento(Short cveTipoAsen, String descripcion) {
        this.cveTipoAsen = cveTipoAsen;
        this.descripcion = descripcion;
    }

    public Short getCveTipoAsen() {
        return cveTipoAsen;
    }

    public void setCveTipoAsen(Short cveTipoAsen) {
        this.cveTipoAsen = cveTipoAsen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TrEtqVal> getTrEtqValCollection() {
        return trEtqValCollection;
    }

    public void setTrEtqValCollection(Collection<TrEtqVal> trEtqValCollection) {
        this.trEtqValCollection = trEtqValCollection;
    }

    @XmlTransient
    public Collection<TrUoMasivo> getTrUoMasivoCollection() {
        return trUoMasivoCollection;
    }

    public void setTrUoMasivoCollection(Collection<TrUoMasivo> trUoMasivoCollection) {
        this.trUoMasivoCollection = trUoMasivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cveTipoAsen != null ? cveTipoAsen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcAsentamiento)) {
            return false;
        }
        TcAsentamiento other = (TcAsentamiento) object;
        if ((this.cveTipoAsen == null && other.cveTipoAsen != null) || (this.cveTipoAsen != null && !this.cveTipoAsen.equals(other.cveTipoAsen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcAsentamiento[ cveTipoAsen=" + cveTipoAsen + " ]";
    }
    
}
