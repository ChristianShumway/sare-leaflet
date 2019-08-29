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
@Table(name = "TC_TIPO_INFORMANTE",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcTipoInformante.findAll", query = "SELECT t FROM TcTipoInformante t"),
    @NamedQuery(name = "TcTipoInformante.findByTipoInf", query = "SELECT t FROM TcTipoInformante t WHERE t.tipoInf = :tipoInf"),
    @NamedQuery(name = "TcTipoInformante.findByDescripcion", query = "SELECT t FROM TcTipoInformante t WHERE t.descripcion = :descripcion")})
public class TcTipoInformante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_INF")
    private Short tipoInf;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "tipoInf")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcTipoInformante() {
    }

    public TcTipoInformante(Short tipoInf) {
        this.tipoInf = tipoInf;
    }

    public Short getTipoInf() {
        return tipoInf;
    }

    public void setTipoInf(Short tipoInf) {
        this.tipoInf = tipoInf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (tipoInf != null ? tipoInf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcTipoInformante)) {
            return false;
        }
        TcTipoInformante other = (TcTipoInformante) object;
        if ((this.tipoInf == null && other.tipoInf != null) || (this.tipoInf != null && !this.tipoInf.equals(other.tipoInf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcTipoInformante[ tipoInf=" + tipoInf + " ]";
    }
    
}
