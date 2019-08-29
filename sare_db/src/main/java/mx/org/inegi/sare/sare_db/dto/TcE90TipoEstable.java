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
@Table(name = "TC_E90_TIPO_ESTABLE",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcE90TipoEstable.findAll", query = "SELECT t FROM TcE90TipoEstable t"),
    @NamedQuery(name = "TcE90TipoEstable.findByIdE90", query = "SELECT t FROM TcE90TipoEstable t WHERE t.idE90 = :idE90"),
    @NamedQuery(name = "TcE90TipoEstable.findByTipoEstable", query = "SELECT t FROM TcE90TipoEstable t WHERE t.tipoEstable = :tipoEstable")})
public class TcE90TipoEstable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_E90")
    private Short idE90;
    @Basic(optional = false)
    @Column(name = "TIPO_ESTABLE")
    private String tipoEstable;
    @OneToMany(mappedBy = "e90")
    private Collection<TrEtqVal> trEtqValCollection;

    public TcE90TipoEstable() {
    }

    public TcE90TipoEstable(Short idE90) {
        this.idE90 = idE90;
    }

    public TcE90TipoEstable(Short idE90, String tipoEstable) {
        this.idE90 = idE90;
        this.tipoEstable = tipoEstable;
    }

    public Short getIdE90() {
        return idE90;
    }

    public void setIdE90(Short idE90) {
        this.idE90 = idE90;
    }

    public String getTipoEstable() {
        return tipoEstable;
    }

    public void setTipoEstable(String tipoEstable) {
        this.tipoEstable = tipoEstable;
    }

    @XmlTransient
    public Collection<TrEtqVal> getTrEtqValCollection() {
        return trEtqValCollection;
    }

    public void setTrEtqValCollection(Collection<TrEtqVal> trEtqValCollection) {
        this.trEtqValCollection = trEtqValCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idE90 != null ? idE90.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcE90TipoEstable)) {
            return false;
        }
        TcE90TipoEstable other = (TcE90TipoEstable) object;
        if ((this.idE90 == null && other.idE90 != null) || (this.idE90 != null && !this.idE90.equals(other.idE90))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcE90TipoEstable[ idE90=" + idE90 + " ]";
    }
    
}
