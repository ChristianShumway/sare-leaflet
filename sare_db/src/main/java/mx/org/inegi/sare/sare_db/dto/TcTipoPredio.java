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
@Table(name = "TC_TIPO_PREDIO",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcTipoPredio.findAll", query = "SELECT t FROM TcTipoPredio t"),
    @NamedQuery(name = "TcTipoPredio.findByTipoPredio", query = "SELECT t FROM TcTipoPredio t WHERE t.tipoPredio = :tipoPredio"),
    @NamedQuery(name = "TcTipoPredio.findByDescripcion", query = "SELECT t FROM TcTipoPredio t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcTipoPredio.findBySiglas", query = "SELECT t FROM TcTipoPredio t WHERE t.siglas = :siglas")})
public class TcTipoPredio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_PREDIO")
    private Short tipoPredio;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "SIGLAS")
    private String siglas;
    @OneToMany(mappedBy = "tipoPredio")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcTipoPredio() {
    }

    public TcTipoPredio(Short tipoPredio) {
        this.tipoPredio = tipoPredio;
    }

    public Short getTipoPredio() {
        return tipoPredio;
    }

    public void setTipoPredio(Short tipoPredio) {
        this.tipoPredio = tipoPredio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
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
        hash += (tipoPredio != null ? tipoPredio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcTipoPredio)) {
            return false;
        }
        TcTipoPredio other = (TcTipoPredio) object;
        if ((this.tipoPredio == null && other.tipoPredio != null) || (this.tipoPredio != null && !this.tipoPredio.equals(other.tipoPredio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcTipoPredio[ tipoPredio=" + tipoPredio + " ]";
    }
    
}
