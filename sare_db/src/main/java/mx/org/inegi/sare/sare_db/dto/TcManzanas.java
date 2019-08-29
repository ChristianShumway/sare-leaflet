/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "TC_MANZANAS",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcManzanas.findAll", query = "SELECT t FROM TcManzanas t"),
    @NamedQuery(name = "TcManzanas.findByCveEnt", query = "SELECT t FROM TcManzanas t WHERE t.tcManzanasPK.cveEnt = :cveEnt"),
    @NamedQuery(name = "TcManzanas.findByCveMun", query = "SELECT t FROM TcManzanas t WHERE t.tcManzanasPK.cveMun = :cveMun"),
    @NamedQuery(name = "TcManzanas.findByCveLoc", query = "SELECT t FROM TcManzanas t WHERE t.tcManzanasPK.cveLoc = :cveLoc"),
    @NamedQuery(name = "TcManzanas.findByCveAgeb", query = "SELECT t FROM TcManzanas t WHERE t.tcManzanasPK.cveAgeb = :cveAgeb"),
    @NamedQuery(name = "TcManzanas.findByCveMza", query = "SELECT t FROM TcManzanas t WHERE t.tcManzanasPK.cveMza = :cveMza"),
    @NamedQuery(name = "TcManzanas.findByTipo", query = "SELECT t FROM TcManzanas t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TcManzanas.findByCkCorrecto", query = "SELECT t FROM TcManzanas t WHERE t.ckCorrecto = :ckCorrecto"),
    @NamedQuery(name = "TcManzanas.findByCkInvertirClee", query = "SELECT t FROM TcManzanas t WHERE t.ckInvertirClee = :ckInvertirClee")})
public class TcManzanas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcManzanasPK tcManzanasPK;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "CK_CORRECTO")
    private BigInteger ckCorrecto;
    @Column(name = "CK_INVERTIR_CLEE")
    private BigInteger ckInvertirClee;
    @JoinColumns({
        @JoinColumn(name = "CVE_ENT", referencedColumnName = "CVE_ENT", insertable = false, updatable = false),
        @JoinColumn(name = "CVE_MUN", referencedColumnName = "CVE_MUN", insertable = false, updatable = false),
        @JoinColumn(name = "CVE_LOC", referencedColumnName = "CVE_LOC", insertable = false, updatable = false),
        @JoinColumn(name = "CVE_AGEB", referencedColumnName = "CVE_AGEB", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TcAgebs tcAgebs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcManzanas")
    private Collection<TrEtqVal> trEtqValCollection;
    @OneToMany(mappedBy = "tcManzanas")
    private Collection<TrInmuebles> trInmueblesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcManzanas")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TcManzanas() {
    }

    public TcManzanas(TcManzanasPK tcManzanasPK) {
        this.tcManzanasPK = tcManzanasPK;
    }

    public TcManzanas(String cveEnt, String cveMun, String cveLoc, String cveAgeb, String cveMza) {
        this.tcManzanasPK = new TcManzanasPK(cveEnt, cveMun, cveLoc, cveAgeb, cveMza);
    }

    public TcManzanasPK getTcManzanasPK() {
        return tcManzanasPK;
    }

    public void setTcManzanasPK(TcManzanasPK tcManzanasPK) {
        this.tcManzanasPK = tcManzanasPK;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigInteger getCkCorrecto() {
        return ckCorrecto;
    }

    public void setCkCorrecto(BigInteger ckCorrecto) {
        this.ckCorrecto = ckCorrecto;
    }

    public BigInteger getCkInvertirClee() {
        return ckInvertirClee;
    }

    public void setCkInvertirClee(BigInteger ckInvertirClee) {
        this.ckInvertirClee = ckInvertirClee;
    }

    public TcAgebs getTcAgebs() {
        return tcAgebs;
    }

    public void setTcAgebs(TcAgebs tcAgebs) {
        this.tcAgebs = tcAgebs;
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
        hash += (tcManzanasPK != null ? tcManzanasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcManzanas)) {
            return false;
        }
        TcManzanas other = (TcManzanas) object;
        if ((this.tcManzanasPK == null && other.tcManzanasPK != null) || (this.tcManzanasPK != null && !this.tcManzanasPK.equals(other.tcManzanasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcManzanas[ tcManzanasPK=" + tcManzanasPK + " ]";
    }
    
}
