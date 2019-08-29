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
@Table(name = "TC_CGO",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcCgo.findAll", query = "SELECT t FROM TcCgo t"),
    @NamedQuery(name = "TcCgo.findByIdCgo", query = "SELECT t FROM TcCgo t WHERE t.idCgo = :idCgo"),
    @NamedQuery(name = "TcCgo.findByDescripcion", query = "SELECT t FROM TcCgo t WHERE t.descripcion = :descripcion")})
public class TcCgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CGO")
    private Short idCgo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idCgo")
    private Collection<TrPredios> trPrediosCollection;

    public TcCgo() {
    }

    public TcCgo(Short idCgo) {
        this.idCgo = idCgo;
    }

    public TcCgo(Short idCgo, String descripcion) {
        this.idCgo = idCgo;
        this.descripcion = descripcion;
    }

    public Short getIdCgo() {
        return idCgo;
    }

    public void setIdCgo(Short idCgo) {
        this.idCgo = idCgo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TrPredios> getTrPrediosCollection() {
        return trPrediosCollection;
    }

    public void setTrPrediosCollection(Collection<TrPredios> trPrediosCollection) {
        this.trPrediosCollection = trPrediosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCgo != null ? idCgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcCgo)) {
            return false;
        }
        TcCgo other = (TcCgo) object;
        if ((this.idCgo == null && other.idCgo != null) || (this.idCgo != null && !this.idCgo.equals(other.idCgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcCgo[ idCgo=" + idCgo + " ]";
    }
    
}
