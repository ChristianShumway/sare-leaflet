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
@Table(name = "TC_VIALIDADES",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcVialidades.findAll", query = "SELECT t FROM TcVialidades t"),
    @NamedQuery(name = "TcVialidades.findByCveVialidad", query = "SELECT t FROM TcVialidades t WHERE t.cveVialidad = :cveVialidad"),
    @NamedQuery(name = "TcVialidades.findByDescripcion", query = "SELECT t FROM TcVialidades t WHERE t.descripcion = :descripcion")})
public class TcVialidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CVE_VIALIDAD")
    private Short cveVialidad;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipoE10A")
    private Collection<TrEtqVal> trEtqValCollection;
    @OneToMany(mappedBy = "tipoE10C")
    private Collection<TrEtqVal> trEtqValCollection1;
    @OneToMany(mappedBy = "tipoE10")
    private Collection<TrEtqVal> trEtqValCollection2;
    @OneToMany(mappedBy = "tipoE10B")
    private Collection<TrEtqVal> trEtqValCollection3;
    @OneToMany(mappedBy = "tipoE10A")
    private Collection<TrInmuebles> trInmueblesCollection;
    @OneToMany(mappedBy = "tipoE10C")
    private Collection<TrInmuebles> trInmueblesCollection1;
    @OneToMany(mappedBy = "tipovial")
    private Collection<TrInmuebles> trInmueblesCollection2;
    @OneToMany(mappedBy = "tipoE10B")
    private Collection<TrInmuebles> trInmueblesCollection3;
    @OneToMany(mappedBy = "tipoE10A")
    private Collection<TrUoMasivo> trUoMasivoCollection;
    @OneToMany(mappedBy = "tipoE10C")
    private Collection<TrUoMasivo> trUoMasivoCollection1;
    @OneToMany(mappedBy = "tipoE10B")
    private Collection<TrUoMasivo> trUoMasivoCollection2;

    public TcVialidades() {
    }

    public TcVialidades(Short cveVialidad) {
        this.cveVialidad = cveVialidad;
    }

    public TcVialidades(Short cveVialidad, String descripcion) {
        this.cveVialidad = cveVialidad;
        this.descripcion = descripcion;
    }

    public Short getCveVialidad() {
        return cveVialidad;
    }

    public void setCveVialidad(Short cveVialidad) {
        this.cveVialidad = cveVialidad;
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
    public Collection<TrEtqVal> getTrEtqValCollection1() {
        return trEtqValCollection1;
    }

    public void setTrEtqValCollection1(Collection<TrEtqVal> trEtqValCollection1) {
        this.trEtqValCollection1 = trEtqValCollection1;
    }

    @XmlTransient
    public Collection<TrEtqVal> getTrEtqValCollection2() {
        return trEtqValCollection2;
    }

    public void setTrEtqValCollection2(Collection<TrEtqVal> trEtqValCollection2) {
        this.trEtqValCollection2 = trEtqValCollection2;
    }

    @XmlTransient
    public Collection<TrEtqVal> getTrEtqValCollection3() {
        return trEtqValCollection3;
    }

    public void setTrEtqValCollection3(Collection<TrEtqVal> trEtqValCollection3) {
        this.trEtqValCollection3 = trEtqValCollection3;
    }

    @XmlTransient
    public Collection<TrInmuebles> getTrInmueblesCollection() {
        return trInmueblesCollection;
    }

    public void setTrInmueblesCollection(Collection<TrInmuebles> trInmueblesCollection) {
        this.trInmueblesCollection = trInmueblesCollection;
    }

    @XmlTransient
    public Collection<TrInmuebles> getTrInmueblesCollection1() {
        return trInmueblesCollection1;
    }

    public void setTrInmueblesCollection1(Collection<TrInmuebles> trInmueblesCollection1) {
        this.trInmueblesCollection1 = trInmueblesCollection1;
    }

    @XmlTransient
    public Collection<TrInmuebles> getTrInmueblesCollection2() {
        return trInmueblesCollection2;
    }

    public void setTrInmueblesCollection2(Collection<TrInmuebles> trInmueblesCollection2) {
        this.trInmueblesCollection2 = trInmueblesCollection2;
    }

    @XmlTransient
    public Collection<TrInmuebles> getTrInmueblesCollection3() {
        return trInmueblesCollection3;
    }

    public void setTrInmueblesCollection3(Collection<TrInmuebles> trInmueblesCollection3) {
        this.trInmueblesCollection3 = trInmueblesCollection3;
    }

    @XmlTransient
    public Collection<TrUoMasivo> getTrUoMasivoCollection() {
        return trUoMasivoCollection;
    }

    public void setTrUoMasivoCollection(Collection<TrUoMasivo> trUoMasivoCollection) {
        this.trUoMasivoCollection = trUoMasivoCollection;
    }

    @XmlTransient
    public Collection<TrUoMasivo> getTrUoMasivoCollection1() {
        return trUoMasivoCollection1;
    }

    public void setTrUoMasivoCollection1(Collection<TrUoMasivo> trUoMasivoCollection1) {
        this.trUoMasivoCollection1 = trUoMasivoCollection1;
    }

    @XmlTransient
    public Collection<TrUoMasivo> getTrUoMasivoCollection2() {
        return trUoMasivoCollection2;
    }

    public void setTrUoMasivoCollection2(Collection<TrUoMasivo> trUoMasivoCollection2) {
        this.trUoMasivoCollection2 = trUoMasivoCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cveVialidad != null ? cveVialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcVialidades)) {
            return false;
        }
        TcVialidades other = (TcVialidades) object;
        if ((this.cveVialidad == null && other.cveVialidad != null) || (this.cveVialidad != null && !this.cveVialidad.equals(other.cveVialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcVialidades[ cveVialidad=" + cveVialidad + " ]";
    }
    
}
