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
@Table(name = "TC_TIPO_CONGLOMERADO",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcTipoConglomerado.findAll", query = "SELECT t FROM TcTipoConglomerado t"),
    @NamedQuery(name = "TcTipoConglomerado.findByTipoE19", query = "SELECT t FROM TcTipoConglomerado t WHERE t.tipoE19 = :tipoE19"),
    @NamedQuery(name = "TcTipoConglomerado.findByDescripcion", query = "SELECT t FROM TcTipoConglomerado t WHERE t.descripcion = :descripcion")})
public class TcTipoConglomerado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_E19")
    private String tipoE19;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipoE19")
    private Collection<TrEtqVal> trEtqValCollection;
    @OneToMany(mappedBy = "tipoE19")
    private Collection<TrInmuebles> trInmueblesCollection;
    @OneToMany(mappedBy = "tipoE19")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcTipoConglomerado() {
    }

    public TcTipoConglomerado(String tipoE19) {
        this.tipoE19 = tipoE19;
    }

    public String getTipoE19() {
        return tipoE19;
    }

    public void setTipoE19(String tipoE19) {
        this.tipoE19 = tipoE19;
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
        hash += (tipoE19 != null ? tipoE19.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcTipoConglomerado)) {
            return false;
        }
        TcTipoConglomerado other = (TcTipoConglomerado) object;
        if ((this.tipoE19 == null && other.tipoE19 != null) || (this.tipoE19 != null && !this.tipoE19.equals(other.tipoE19))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcTipoConglomerado[ tipoE19=" + tipoE19 + " ]";
    }
    
}
