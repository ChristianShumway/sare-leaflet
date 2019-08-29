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
@Table(name = "TC_ESTATUS_SARE",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcEstatusSare.findAll", query = "SELECT t FROM TcEstatusSare t"),
    @NamedQuery(name = "TcEstatusSare.findByStSare", query = "SELECT t FROM TcEstatusSare t WHERE t.stSare = :stSare"),
    @NamedQuery(name = "TcEstatusSare.findByDescripcion", query = "SELECT t FROM TcEstatusSare t WHERE t.descripcion = :descripcion")})
public class TcEstatusSare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ST_SARE")
    private String stSare;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "stSare")
    private Collection<TrPredios> trPrediosCollection;

    public TcEstatusSare() {
    }

    public TcEstatusSare(String stSare) {
        this.stSare = stSare;
    }

    public String getStSare() {
        return stSare;
    }

    public void setStSare(String stSare) {
        this.stSare = stSare;
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
        hash += (stSare != null ? stSare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcEstatusSare)) {
            return false;
        }
        TcEstatusSare other = (TcEstatusSare) object;
        if ((this.stSare == null && other.stSare != null) || (this.stSare != null && !this.stSare.equals(other.stSare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcEstatusSare[ stSare=" + stSare + " ]";
    }
    
}
