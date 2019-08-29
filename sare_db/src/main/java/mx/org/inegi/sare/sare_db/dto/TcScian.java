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
@Table(name = "TC_SCIAN",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcScian.findAll", query = "SELECT t FROM TcScian t"),
    @NamedQuery(name = "TcScian.findByE17", query = "SELECT t FROM TcScian t WHERE t.e17 = :e17"),
    @NamedQuery(name = "TcScian.findByDescripcion", query = "SELECT t FROM TcScian t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcScian.findByDescLarga", query = "SELECT t FROM TcScian t WHERE t.descLarga = :descLarga")})
public class TcScian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "E17")
    private String e17;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "DESC_LARGA")
    private String descLarga;
    @OneToMany(mappedBy = "e17")
    private Collection<TrEtqVal> trEtqValCollection;

    public TcScian() {
    }

    public TcScian(String e17) {
        this.e17 = e17;
    }

    public String getE17() {
        return e17;
    }

    public void setE17(String e17) {
        this.e17 = e17;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescLarga() {
        return descLarga;
    }

    public void setDescLarga(String descLarga) {
        this.descLarga = descLarga;
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
        hash += (e17 != null ? e17.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcScian)) {
            return false;
        }
        TcScian other = (TcScian) object;
        if ((this.e17 == null && other.e17 != null) || (this.e17 != null && !this.e17.equals(other.e17))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcScian[ e17=" + e17 + " ]";
    }
    
}
