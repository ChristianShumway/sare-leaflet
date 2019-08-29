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
@Table(name = "TC_PISOS",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcPisos.findAll", query = "SELECT t FROM TcPisos t"),
    @NamedQuery(name = "TcPisos.findByTipoE12p", query = "SELECT t FROM TcPisos t WHERE t.tipoE12p = :tipoE12p"),
    @NamedQuery(name = "TcPisos.findByDescripcion", query = "SELECT t FROM TcPisos t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcPisos.findByPosicion", query = "SELECT t FROM TcPisos t WHERE t.posicion = :posicion"),
    @NamedQuery(name = "TcPisos.findByNivel", query = "SELECT t FROM TcPisos t WHERE t.nivel = :nivel")})
public class TcPisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_E12P")
    private Short tipoE12p;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "POSICION")
    private String posicion;
    @Column(name = "NIVEL")
    private Short nivel;
    @OneToMany(mappedBy = "tipoE12p")
    private Collection<TrPredios> trPrediosCollection;
    @OneToMany(mappedBy = "tipoE12p")
    private Collection<TrEtqVal> trEtqValCollection;
    @OneToMany(mappedBy = "tipoE12p")
    private Collection<TrInmuebles> trInmueblesCollection;
    @OneToMany(mappedBy = "tipoE12p")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcPisos() {
    }

    public TcPisos(Short tipoE12p) {
        this.tipoE12p = tipoE12p;
    }

    public Short getTipoE12p() {
        return tipoE12p;
    }

    public void setTipoE12p(Short tipoE12p) {
        this.tipoE12p = tipoE12p;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Short getNivel() {
        return nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }

    @XmlTransient
    public Collection<TrPredios> getTrPrediosCollection() {
        return trPrediosCollection;
    }

    public void setTrPrediosCollection(Collection<TrPredios> trPrediosCollection) {
        this.trPrediosCollection = trPrediosCollection;
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
        hash += (tipoE12p != null ? tipoE12p.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcPisos)) {
            return false;
        }
        TcPisos other = (TcPisos) object;
        if ((this.tipoE12p == null && other.tipoE12p != null) || (this.tipoE12p != null && !this.tipoE12p.equals(other.tipoE12p))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcPisos[ tipoE12p=" + tipoE12p + " ]";
    }
    
}
