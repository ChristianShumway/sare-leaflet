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
@Table(name = "TC_ESTATUS_VIVP",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcEstatusVivp.findAll", query = "SELECT t FROM TcEstatusVivp t"),
    @NamedQuery(name = "TcEstatusVivp.findByStVivp", query = "SELECT t FROM TcEstatusVivp t WHERE t.stVivp = :stVivp"),
    @NamedQuery(name = "TcEstatusVivp.findByDescripcion", query = "SELECT t FROM TcEstatusVivp t WHERE t.descripcion = :descripcion")})
public class TcEstatusVivp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ST_VIVP")
    private String stVivp;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "stVivp")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcEstatusVivp() {
    }

    public TcEstatusVivp(String stVivp) {
        this.stVivp = stVivp;
    }

    public String getStVivp() {
        return stVivp;
    }

    public void setStVivp(String stVivp) {
        this.stVivp = stVivp;
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
        hash += (stVivp != null ? stVivp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcEstatusVivp)) {
            return false;
        }
        TcEstatusVivp other = (TcEstatusVivp) object;
        if ((this.stVivp == null && other.stVivp != null) || (this.stVivp != null && !this.stVivp.equals(other.stVivp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcEstatusVivp[ stVivp=" + stVivp + " ]";
    }
    
}
