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
@Table(name = "TC_COESTATALES",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcCoestatales.findAll", query = "SELECT t FROM TcCoestatales t"),
    @NamedQuery(name = "TcCoestatales.findByCestatal", query = "SELECT t FROM TcCoestatales t WHERE t.cestatal = :cestatal"),
    @NamedQuery(name = "TcCoestatales.findByEntidad", query = "SELECT t FROM TcCoestatales t WHERE t.entidad = :entidad"),
    @NamedQuery(name = "TcCoestatales.findByDescripcion", query = "SELECT t FROM TcCoestatales t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcCoestatales.findByDSiglas", query = "SELECT t FROM TcCoestatales t WHERE t.dSiglas = :dSiglas"),
    @NamedQuery(name = "TcCoestatales.findByDirregional", query = "SELECT t FROM TcCoestatales t WHERE t.dirregional = :dirregional")})
public class TcCoestatales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CESTATAL")
    private String cestatal;
    @Basic(optional = false)
    @Column(name = "ENTIDAD")
    private String entidad;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "D_SIGLAS")
    private String dSiglas;
    @Column(name = "DIRREGIONAL")
    private Short dirregional;
    @OneToMany(mappedBy = "cveCe")
    private Collection<TrInmuebles> trInmueblesCollection;

    public TcCoestatales() {
    }

    public TcCoestatales(String cestatal) {
        this.cestatal = cestatal;
    }

    public TcCoestatales(String cestatal, String entidad) {
        this.cestatal = cestatal;
        this.entidad = entidad;
    }

    public String getCestatal() {
        return cestatal;
    }

    public void setCestatal(String cestatal) {
        this.cestatal = cestatal;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDSiglas() {
        return dSiglas;
    }

    public void setDSiglas(String dSiglas) {
        this.dSiglas = dSiglas;
    }

    public Short getDirregional() {
        return dirregional;
    }

    public void setDirregional(Short dirregional) {
        this.dirregional = dirregional;
    }

    @XmlTransient
    public Collection<TrInmuebles> getTrInmueblesCollection() {
        return trInmueblesCollection;
    }

    public void setTrInmueblesCollection(Collection<TrInmuebles> trInmueblesCollection) {
        this.trInmueblesCollection = trInmueblesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cestatal != null ? cestatal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcCoestatales)) {
            return false;
        }
        TcCoestatales other = (TcCoestatales) object;
        if ((this.cestatal == null && other.cestatal != null) || (this.cestatal != null && !this.cestatal.equals(other.cestatal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcCoestatales[ cestatal=" + cestatal + " ]";
    }
    
}
