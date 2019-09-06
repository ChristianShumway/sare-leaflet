/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "cat_ce", schema = "sare_mas2019_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatCe.findAll", query = "SELECT c FROM CatCe c"),
    @NamedQuery(name = "CatCe.findByIdCe", query = "SELECT c FROM CatCe c WHERE c.idCe = :idCe"),
    @NamedQuery(name = "CatCe.findByCveEnt", query = "SELECT c FROM CatCe c WHERE c.cveEnt = :cveEnt"),
    @NamedQuery(name = "CatCe.findByNomEnt", query = "SELECT c FROM CatCe c WHERE c.nomEnt = :nomEnt"),
    @NamedQuery(name = "CatCe.findByCveCe", query = "SELECT c FROM CatCe c WHERE c.cveCe = :cveCe"),
    @NamedQuery(name = "CatCe.findBySiglasCe", query = "SELECT c FROM CatCe c WHERE c.siglasCe = :siglasCe"),
    @NamedQuery(name = "CatCe.findByNomCe", query = "SELECT c FROM CatCe c WHERE c.nomCe = :nomCe")})
public class CatCe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id_ce")
    private BigInteger idCe;
    @Column(name = "cve_ent")
    private String cveEnt;
    @Column(name = "nom_ent")
    private String nomEnt;
    @Id
    @Basic(optional = false)
    @Column(name = "cve_ce")
    private String cveCe;
    @Column(name = "siglas_ce")
    private String siglasCe;
    @Column(name = "nom_ce")
    private String nomCe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cveCe", fetch = FetchType.EAGER)
    private Collection<TdInmuebles> tdInmueblesCollection;

    public CatCe() {
    }

    public CatCe(String cveCe) {
        this.cveCe = cveCe;
    }

    public BigInteger getIdCe() {
        return idCe;
    }

    public void setIdCe(BigInteger idCe) {
        this.idCe = idCe;
    }

    public String getCveEnt() {
        return cveEnt;
    }

    public void setCveEnt(String cveEnt) {
        this.cveEnt = cveEnt;
    }

    public String getNomEnt() {
        return nomEnt;
    }

    public void setNomEnt(String nomEnt) {
        this.nomEnt = nomEnt;
    }

    public String getCveCe() {
        return cveCe;
    }

    public void setCveCe(String cveCe) {
        this.cveCe = cveCe;
    }

    public String getSiglasCe() {
        return siglasCe;
    }

    public void setSiglasCe(String siglasCe) {
        this.siglasCe = siglasCe;
    }

    public String getNomCe() {
        return nomCe;
    }

    public void setNomCe(String nomCe) {
        this.nomCe = nomCe;
    }

    @XmlTransient
    public Collection<TdInmuebles> getTdInmueblesCollection() {
        return tdInmueblesCollection;
    }

    public void setTdInmueblesCollection(Collection<TdInmuebles> tdInmueblesCollection) {
        this.tdInmueblesCollection = tdInmueblesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cveCe != null ? cveCe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatCe)) {
            return false;
        }
        CatCe other = (CatCe) object;
        if ((this.cveCe == null && other.cveCe != null) || (this.cveCe != null && !this.cveCe.equals(other.cveCe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.CatCe[ cveCe=" + cveCe + " ]";
    }
    
}
