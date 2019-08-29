/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
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
@Table(name = "TC_LOCALIDADES",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcLocalidades.findAll", query = "SELECT t FROM TcLocalidades t"),
    @NamedQuery(name = "TcLocalidades.findByCveEnt", query = "SELECT t FROM TcLocalidades t WHERE t.tcLocalidadesPK.cveEnt = :cveEnt"),
    @NamedQuery(name = "TcLocalidades.findByCveMun", query = "SELECT t FROM TcLocalidades t WHERE t.tcLocalidadesPK.cveMun = :cveMun"),
    @NamedQuery(name = "TcLocalidades.findByCveLoc", query = "SELECT t FROM TcLocalidades t WHERE t.tcLocalidadesPK.cveLoc = :cveLoc"),
    @NamedQuery(name = "TcLocalidades.findByAgeb", query = "SELECT t FROM TcLocalidades t WHERE t.ageb = :ageb"),
    @NamedQuery(name = "TcLocalidades.findByDescripcion", query = "SELECT t FROM TcLocalidades t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TcLocalidades.findByTipo", query = "SELECT t FROM TcLocalidades t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TcLocalidades.findByIdTipoLocalidad", query = "SELECT t FROM TcLocalidades t WHERE t.idTipoLocalidad = :idTipoLocalidad")})
public class TcLocalidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcLocalidadesPK tcLocalidadesPK;
    @Column(name = "AGEB")
    private String ageb;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "ID_TIPO_LOCALIDAD")
    private Short idTipoLocalidad;
    @JoinColumns({
        @JoinColumn(name = "CVE_ENT", referencedColumnName = "CVE_ENT", insertable = false, updatable = false),
        @JoinColumn(name = "CVE_MUN", referencedColumnName = "CVE_MUN", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TcMunicipios tcMunicipios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcLocalidades")
    private Collection<TcAgebs> tcAgebsCollection;

    public TcLocalidades() {
    }

    public TcLocalidades(TcLocalidadesPK tcLocalidadesPK) {
        this.tcLocalidadesPK = tcLocalidadesPK;
    }

    public TcLocalidades(String cveEnt, String cveMun, String cveLoc) {
        this.tcLocalidadesPK = new TcLocalidadesPK(cveEnt, cveMun, cveLoc);
    }

    public TcLocalidadesPK getTcLocalidadesPK() {
        return tcLocalidadesPK;
    }

    public void setTcLocalidadesPK(TcLocalidadesPK tcLocalidadesPK) {
        this.tcLocalidadesPK = tcLocalidadesPK;
    }

    public String getAgeb() {
        return ageb;
    }

    public void setAgeb(String ageb) {
        this.ageb = ageb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Short getIdTipoLocalidad() {
        return idTipoLocalidad;
    }

    public void setIdTipoLocalidad(Short idTipoLocalidad) {
        this.idTipoLocalidad = idTipoLocalidad;
    }

    public TcMunicipios getTcMunicipios() {
        return tcMunicipios;
    }

    public void setTcMunicipios(TcMunicipios tcMunicipios) {
        this.tcMunicipios = tcMunicipios;
    }

    @XmlTransient
    public Collection<TcAgebs> getTcAgebsCollection() {
        return tcAgebsCollection;
    }

    public void setTcAgebsCollection(Collection<TcAgebs> tcAgebsCollection) {
        this.tcAgebsCollection = tcAgebsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcLocalidadesPK != null ? tcLocalidadesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcLocalidades)) {
            return false;
        }
        TcLocalidades other = (TcLocalidades) object;
        if ((this.tcLocalidadesPK == null && other.tcLocalidadesPK != null) || (this.tcLocalidadesPK != null && !this.tcLocalidadesPK.equals(other.tcLocalidadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TcLocalidades[ tcLocalidadesPK=" + tcLocalidadesPK + " ]";
    }
    
}
