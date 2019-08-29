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
@Table(name = "TC_ST_SARE",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcStSare.findAll", query = "SELECT t FROM TcStSare t"),
    @NamedQuery(name = "TcStSare.findByStatusSare", query = "SELECT t FROM TcStSare t WHERE t.statusSare = :statusSare"),
    @NamedQuery(name = "TcStSare.findByDescripcion", query = "SELECT t FROM TcStSare t WHERE t.descripcion = :descripcion")})
public class TcStSare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STATUS_SARE")
    private Short statusSare;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "statusSare")
    private Collection<TrPredios> trPrediosCollection;

    public TcStSare() {
    }

    public TcStSare(Short statusSare) {
        this.statusSare = statusSare;
    }

    public TcStSare(Short statusSare, String descripcion) {
        this.statusSare = statusSare;
        this.descripcion = descripcion;
    }

    public Short getStatusSare() {
        return statusSare;
    }

    public void setStatusSare(Short statusSare) {
        this.statusSare = statusSare;
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
        hash += (statusSare != null ? statusSare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcStSare)) {
            return false;
        }
        TcStSare other = (TcStSare) object;
        if ((this.statusSare == null && other.statusSare != null) || (this.statusSare != null && !this.statusSare.equals(other.statusSare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcStSare[ statusSare=" + statusSare + " ]";
    }
    
}
