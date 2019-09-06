/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "cat_tipovialidad", schema = "sare_mas2019_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatTipovialidad.findAll", query = "SELECT c FROM CatTipovialidad c"),
    @NamedQuery(name = "CatTipovialidad.findByIdTipovialidad", query = "SELECT c FROM CatTipovialidad c WHERE c.idTipovialidad = :idTipovialidad"),
    @NamedQuery(name = "CatTipovialidad.findByDescripcion", query = "SELECT c FROM CatTipovialidad c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatTipovialidad.findByTipoE10", query = "SELECT c FROM CatTipovialidad c WHERE c.tipoE10 = :tipoE10")})
public class CatTipovialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id_tipovialidad")
    private BigInteger idTipovialidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_e10")
    private String tipoE10;
    @OneToMany(mappedBy = "tipoE10C", fetch = FetchType.EAGER)
    private Collection<TdInmuebles> tdInmueblesCollection;
    @OneToMany(mappedBy = "tipoE10B", fetch = FetchType.EAGER)
    private Collection<TdInmuebles> tdInmueblesCollection1;
    @OneToMany(mappedBy = "tipoE10A", fetch = FetchType.EAGER)
    private Collection<TdInmuebles> tdInmueblesCollection2;
    @OneToMany(mappedBy = "tipoE10", fetch = FetchType.EAGER)
    private Collection<TdInmuebles> tdInmueblesCollection3;

    public CatTipovialidad() {
    }

    public CatTipovialidad(String tipoE10) {
        this.tipoE10 = tipoE10;
    }

    public BigInteger getIdTipovialidad() {
        return idTipovialidad;
    }

    public void setIdTipovialidad(BigInteger idTipovialidad) {
        this.idTipovialidad = idTipovialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoE10() {
        return tipoE10;
    }

    public void setTipoE10(String tipoE10) {
        this.tipoE10 = tipoE10;
    }

    @XmlTransient
    public Collection<TdInmuebles> getTdInmueblesCollection() {
        return tdInmueblesCollection;
    }

    public void setTdInmueblesCollection(Collection<TdInmuebles> tdInmueblesCollection) {
        this.tdInmueblesCollection = tdInmueblesCollection;
    }

    @XmlTransient
    public Collection<TdInmuebles> getTdInmueblesCollection1() {
        return tdInmueblesCollection1;
    }

    public void setTdInmueblesCollection1(Collection<TdInmuebles> tdInmueblesCollection1) {
        this.tdInmueblesCollection1 = tdInmueblesCollection1;
    }

    @XmlTransient
    public Collection<TdInmuebles> getTdInmueblesCollection2() {
        return tdInmueblesCollection2;
    }

    public void setTdInmueblesCollection2(Collection<TdInmuebles> tdInmueblesCollection2) {
        this.tdInmueblesCollection2 = tdInmueblesCollection2;
    }

    @XmlTransient
    public Collection<TdInmuebles> getTdInmueblesCollection3() {
        return tdInmueblesCollection3;
    }

    public void setTdInmueblesCollection3(Collection<TdInmuebles> tdInmueblesCollection3) {
        this.tdInmueblesCollection3 = tdInmueblesCollection3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoE10 != null ? tipoE10.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatTipovialidad)) {
            return false;
        }
        CatTipovialidad other = (CatTipovialidad) object;
        if ((this.tipoE10 == null && other.tipoE10 != null) || (this.tipoE10 != null && !this.tipoE10.equals(other.tipoE10))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.CatTipovialidad[ tipoE10=" + tipoE10 + " ]";
    }
    
}
