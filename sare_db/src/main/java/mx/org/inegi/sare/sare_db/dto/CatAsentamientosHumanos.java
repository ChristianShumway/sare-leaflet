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
@Table(name = "cat_asentamientos_humanos", schema = "sare_mas2019_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatAsentamientosHumanos.findAll", query = "SELECT c FROM CatAsentamientosHumanos c"),
    @NamedQuery(name = "CatAsentamientosHumanos.findByIdTipoasen", query = "SELECT c FROM CatAsentamientosHumanos c WHERE c.idTipoasen = :idTipoasen"),
    @NamedQuery(name = "CatAsentamientosHumanos.findByDescripcion", query = "SELECT c FROM CatAsentamientosHumanos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatAsentamientosHumanos.findByTipoE14", query = "SELECT c FROM CatAsentamientosHumanos c WHERE c.tipoE14 = :tipoE14")})
public class CatAsentamientosHumanos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id_tipoasen")
    private BigInteger idTipoasen;
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_e14")
    private String tipoE14;
    @OneToMany(mappedBy = "tipoE14", fetch = FetchType.EAGER)
    private Collection<TdInmuebles> tdInmueblesCollection;

    public CatAsentamientosHumanos() {
    }

    public CatAsentamientosHumanos(String tipoE14) {
        this.tipoE14 = tipoE14;
    }

    public BigInteger getIdTipoasen() {
        return idTipoasen;
    }

    public void setIdTipoasen(BigInteger idTipoasen) {
        this.idTipoasen = idTipoasen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoE14() {
        return tipoE14;
    }

    public void setTipoE14(String tipoE14) {
        this.tipoE14 = tipoE14;
    }

    @XmlTransient
    public Collection<TdInmuebles> getTdInmueblesCollection() {
        return tdInmueblesCollection;
    }

    public void setTdInmueblesCollection(Collection<TdInmuebles> tdInmueblesCollection) {
        this.tdInmueblesCollection = tdInmueblesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoE14 != null ? tipoE14.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatAsentamientosHumanos)) {
            return false;
        }
        CatAsentamientosHumanos other = (CatAsentamientosHumanos) object;
        if ((this.tipoE14 == null && other.tipoE14 != null) || (this.tipoE14 != null && !this.tipoE14.equals(other.tipoE14))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.CatAsentamientosHumanos[ tipoE14=" + tipoE14 + " ]";
    }
    
}
