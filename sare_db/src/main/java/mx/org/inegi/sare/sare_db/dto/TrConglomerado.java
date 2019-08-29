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
@Table(name = "TR_CONGLOMERADO",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrConglomerado.findAll", query = "SELECT t FROM TrConglomerado t"),
    @NamedQuery(name = "TrConglomerado.findByIdConglomerado", query = "SELECT t FROM TrConglomerado t WHERE t.idConglomerado = :idConglomerado"),
    @NamedQuery(name = "TrConglomerado.findByTipoE19", query = "SELECT t FROM TrConglomerado t WHERE t.tipoE19 = :tipoE19"),
    @NamedQuery(name = "TrConglomerado.findByE19", query = "SELECT t FROM TrConglomerado t WHERE t.e19 = :e19"),
    @NamedQuery(name = "TrConglomerado.findByPisoSs", query = "SELECT t FROM TrConglomerado t WHERE t.pisoSs = :pisoSs"),
    @NamedQuery(name = "TrConglomerado.findByPisoBs", query = "SELECT t FROM TrConglomerado t WHERE t.pisoBs = :pisoBs"),
    @NamedQuery(name = "TrConglomerado.findByAcceso", query = "SELECT t FROM TrConglomerado t WHERE t.acceso = :acceso"),
    @NamedQuery(name = "TrConglomerado.findByBaja", query = "SELECT t FROM TrConglomerado t WHERE t.baja = :baja"),
    @NamedQuery(name = "TrConglomerado.findByE10", query = "SELECT t FROM TrConglomerado t WHERE t.e10 = :e10"),
    @NamedQuery(name = "TrConglomerado.findByE11", query = "SELECT t FROM TrConglomerado t WHERE t.e11 = :e11"),
    @NamedQuery(name = "TrConglomerado.findByE11a", query = "SELECT t FROM TrConglomerado t WHERE t.e11a = :e11a")})
public class TrConglomerado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONGLOMERADO")
    private Long idConglomerado;
    @Column(name = "TIPO_E19")
    private String tipoE19;
    @Column(name = "E19")
    private String e19;
    @Basic(optional = false)
    @Column(name = "PISO_SS")
    private short pisoSs;
    @Basic(optional = false)
    @Column(name = "PISO_BS")
    private short pisoBs;
    @Basic(optional = false)
    @Column(name = "ACCESO")
    private short acceso;
    @Basic(optional = false)
    @Column(name = "BAJA")
    private short baja;
    @Column(name = "E10")
    private String e10;
    @Basic(optional = false)
    @Column(name = "E11")
    private int e11;
    @Column(name = "E11A")
    private String e11a;
    @OneToMany(mappedBy = "idConglomerado")
    private Collection<TrEtqVal> trEtqValCollection;
    @OneToMany(mappedBy = "idConglomerado")
    private Collection<TrInmuebles> trInmueblesCollection;
    @OneToMany(mappedBy = "idConglomerado")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TrConglomerado() {
    }

    public TrConglomerado(Long idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    public TrConglomerado(Long idConglomerado, short pisoSs, short pisoBs, short acceso, short baja, int e11) {
        this.idConglomerado = idConglomerado;
        this.pisoSs = pisoSs;
        this.pisoBs = pisoBs;
        this.acceso = acceso;
        this.baja = baja;
        this.e11 = e11;
    }

    public Long getIdConglomerado() {
        return idConglomerado;
    }

    public void setIdConglomerado(Long idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    public String getTipoE19() {
        return tipoE19;
    }

    public void setTipoE19(String tipoE19) {
        this.tipoE19 = tipoE19;
    }

    public String getE19() {
        return e19;
    }

    public void setE19(String e19) {
        this.e19 = e19;
    }

    public short getPisoSs() {
        return pisoSs;
    }

    public void setPisoSs(short pisoSs) {
        this.pisoSs = pisoSs;
    }

    public short getPisoBs() {
        return pisoBs;
    }

    public void setPisoBs(short pisoBs) {
        this.pisoBs = pisoBs;
    }

    public short getAcceso() {
        return acceso;
    }

    public void setAcceso(short acceso) {
        this.acceso = acceso;
    }

    public short getBaja() {
        return baja;
    }

    public void setBaja(short baja) {
        this.baja = baja;
    }

    public String getE10() {
        return e10;
    }

    public void setE10(String e10) {
        this.e10 = e10;
    }

    public int getE11() {
        return e11;
    }

    public void setE11(int e11) {
        this.e11 = e11;
    }

    public String getE11a() {
        return e11a;
    }

    public void setE11a(String e11a) {
        this.e11a = e11a;
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
        hash += (idConglomerado != null ? idConglomerado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrConglomerado)) {
            return false;
        }
        TrConglomerado other = (TrConglomerado) object;
        if ((this.idConglomerado == null && other.idConglomerado != null) || (this.idConglomerado != null && !this.idConglomerado.equals(other.idConglomerado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TrConglomerado[ idConglomerado=" + idConglomerado + " ]";
    }
    
}
