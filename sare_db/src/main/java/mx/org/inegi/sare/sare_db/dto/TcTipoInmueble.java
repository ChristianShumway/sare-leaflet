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
@Table(name = "TC_TIPO_INMUEBLE",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcTipoInmueble.findAll", query = "SELECT t FROM TcTipoInmueble t"),
    @NamedQuery(name = "TcTipoInmueble.findByIdTipoInmueble", query = "SELECT t FROM TcTipoInmueble t WHERE t.idTipoInmueble = :idTipoInmueble"),
    @NamedQuery(name = "TcTipoInmueble.findByDescripcion", query = "SELECT t FROM TcTipoInmueble t WHERE t.descripcion = :descripcion")})
public class TcTipoInmueble implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_INMUEBLE")
    private Short idTipoInmueble;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoInmueble")
    private Collection<TrInmuebles> trInmueblesCollection;
    @OneToMany(mappedBy = "idTipoInmueble")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcTipoInmueble() {
    }

    public TcTipoInmueble(Short idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public Short getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(Short idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TrInmuebles> getTrInmueblesCollection() {
        return trInmueblesCollection;
    }

    public void setTrInmueblesCollection(Collection<TrInmuebles> trInmueblesCollection) {
        this.trInmueblesCollection = trInmueblesCollection;
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
        hash += (idTipoInmueble != null ? idTipoInmueble.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcTipoInmueble)) {
            return false;
        }
        TcTipoInmueble other = (TcTipoInmueble) object;
        if ((this.idTipoInmueble == null && other.idTipoInmueble != null) || (this.idTipoInmueble != null && !this.idTipoInmueble.equals(other.idTipoInmueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcTipoInmueble[ idTipoInmueble=" + idTipoInmueble + " ]";
    }
    
}
