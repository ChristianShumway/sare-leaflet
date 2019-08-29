/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Entity
@Table(name = "TR_INMUEBLES",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrInmuebles.findAll", query = "SELECT t FROM TrInmuebles t"),
    @NamedQuery(name = "TrInmuebles.findByIdInmueble", query = "SELECT t FROM TrInmuebles t WHERE t.idInmueble = :idInmueble"),
    @NamedQuery(name = "TrInmuebles.findByIdDeftramo", query = "SELECT t FROM TrInmuebles t WHERE t.idDeftramo = :idDeftramo"),
    @NamedQuery(name = "TrInmuebles.findByCleeEmp", query = "SELECT t FROM TrInmuebles t WHERE t.cleeEmp = :cleeEmp"),
    @NamedQuery(name = "TrInmuebles.findByCleeEst", query = "SELECT t FROM TrInmuebles t WHERE t.cleeEst = :cleeEst"),
    @NamedQuery(name = "TrInmuebles.findByCveInm", query = "SELECT t FROM TrInmuebles t WHERE t.cveInm = :cveInm"),
    @NamedQuery(name = "TrInmuebles.findByCveft", query = "SELECT t FROM TrInmuebles t WHERE t.cveft = :cveft"),
    @NamedQuery(name = "TrInmuebles.findByCvevial", query = "SELECT t FROM TrInmuebles t WHERE t.cvevial = :cvevial"),
    @NamedQuery(name = "TrInmuebles.findByCvegeo", query = "SELECT t FROM TrInmuebles t WHERE t.cvegeo = :cvegeo"),
    @NamedQuery(name = "TrInmuebles.findByNomEst", query = "SELECT t FROM TrInmuebles t WHERE t.nomEst = :nomEst"),
    @NamedQuery(name = "TrInmuebles.findByTe10", query = "SELECT t FROM TrInmuebles t WHERE t.te10 = :te10"),
    @NamedQuery(name = "TrInmuebles.findByNomvial", query = "SELECT t FROM TrInmuebles t WHERE t.nomvial = :nomvial"),
    @NamedQuery(name = "TrInmuebles.findByNumext", query = "SELECT t FROM TrInmuebles t WHERE t.numext = :numext"),
    @NamedQuery(name = "TrInmuebles.findByNumextalf", query = "SELECT t FROM TrInmuebles t WHERE t.numextalf = :numextalf"),
    @NamedQuery(name = "TrInmuebles.findByNumint", query = "SELECT t FROM TrInmuebles t WHERE t.numint = :numint"),
    @NamedQuery(name = "TrInmuebles.findByNumintalf", query = "SELECT t FROM TrInmuebles t WHERE t.numintalf = :numintalf"),
    @NamedQuery(name = "TrInmuebles.findByE12p", query = "SELECT t FROM TrInmuebles t WHERE t.e12p = :e12p"),
    @NamedQuery(name = "TrInmuebles.findByTe19", query = "SELECT t FROM TrInmuebles t WHERE t.te19 = :te19"),
    @NamedQuery(name = "TrInmuebles.findByE19", query = "SELECT t FROM TrInmuebles t WHERE t.e19 = :e19"),
    @NamedQuery(name = "TrInmuebles.findByTe10A", query = "SELECT t FROM TrInmuebles t WHERE t.te10A = :te10A"),
    @NamedQuery(name = "TrInmuebles.findByE10A", query = "SELECT t FROM TrInmuebles t WHERE t.e10A = :e10A"),
    @NamedQuery(name = "TrInmuebles.findByTe10B", query = "SELECT t FROM TrInmuebles t WHERE t.te10B = :te10B"),
    @NamedQuery(name = "TrInmuebles.findByE10B", query = "SELECT t FROM TrInmuebles t WHERE t.e10B = :e10B"),
    @NamedQuery(name = "TrInmuebles.findByTe10C", query = "SELECT t FROM TrInmuebles t WHERE t.te10C = :te10C"),
    @NamedQuery(name = "TrInmuebles.findByE10C", query = "SELECT t FROM TrInmuebles t WHERE t.e10C = :e10C"),
    @NamedQuery(name = "TrInmuebles.findByC154", query = "SELECT t FROM TrInmuebles t WHERE t.c154 = :c154"),
    @NamedQuery(name = "TrInmuebles.findByTipoarea", query = "SELECT t FROM TrInmuebles t WHERE t.tipoarea = :tipoarea"),
    @NamedQuery(name = "TrInmuebles.findByTipo", query = "SELECT t FROM TrInmuebles t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TrInmuebles.findByTransfer", query = "SELECT t FROM TrInmuebles t WHERE t.transfer = :transfer"),
    @NamedQuery(name = "TrInmuebles.findByCveOri", query = "SELECT t FROM TrInmuebles t WHERE t.cveOri = :cveOri"),
    @NamedQuery(name = "TrInmuebles.findByVermcc", query = "SELECT t FROM TrInmuebles t WHERE t.vermcc = :vermcc"),
    @NamedQuery(name = "TrInmuebles.findByRegistroFecha", query = "SELECT t FROM TrInmuebles t WHERE t.registroFecha = :registroFecha"),
    @NamedQuery(name = "TrInmuebles.findByRegistroHora", query = "SELECT t FROM TrInmuebles t WHERE t.registroHora = :registroHora"),
    @NamedQuery(name = "TrInmuebles.findByTipoEdf", query = "SELECT t FROM TrInmuebles t WHERE t.tipoEdf = :tipoEdf"),
    @NamedQuery(name = "TrInmuebles.findByOrigen", query = "SELECT t FROM TrInmuebles t WHERE t.origen = :origen"),
    @NamedQuery(name = "TrInmuebles.findByBaja", query = "SELECT t FROM TrInmuebles t WHERE t.baja = :baja"),
    @NamedQuery(name = "TrInmuebles.findByBajaFecha", query = "SELECT t FROM TrInmuebles t WHERE t.bajaFecha = :bajaFecha"),
    @NamedQuery(name = "TrInmuebles.findByBajaHora", query = "SELECT t FROM TrInmuebles t WHERE t.bajaHora = :bajaHora"),
    @NamedQuery(name = "TrInmuebles.findByFIntegrado", query = "SELECT t FROM TrInmuebles t WHERE t.fIntegrado = :fIntegrado"),
    @NamedQuery(name = "TrInmuebles.findByOrigenMc", query = "SELECT t FROM TrInmuebles t WHERE t.origenMc = :origenMc"),
    @NamedQuery(name = "TrInmuebles.findByX", query = "SELECT t FROM TrInmuebles t WHERE t.x = :x"),
    @NamedQuery(name = "TrInmuebles.findByY", query = "SELECT t FROM TrInmuebles t WHERE t.y = :y"),
    @NamedQuery(name = "TrInmuebles.findByFechaBaja", query = "SELECT t FROM TrInmuebles t WHERE t.fechaBaja = :fechaBaja")})
public class TrInmuebles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_INMUEBLE")
    private Long idInmueble;
    @Column(name = "ID_DEFTRAMO")
    private Integer idDeftramo;
    @Column(name = "CLEE_EMP")
    private String cleeEmp;
    @Column(name = "CLEE_EST")
    private String cleeEst;
    @Column(name = "CVE_INM")
    private String cveInm;
    @Column(name = "CVEFT")
    private Short cveft;
    @Column(name = "CVEVIAL")
    private String cvevial;
    @Column(name = "CVEGEO")
    private String cvegeo;
    @Column(name = "NOM_EST")
    private String nomEst;
    @Column(name = "TE10")
    private String te10;
    @Column(name = "NOMVIAL")
    private String nomvial;
    @Column(name = "NUMEXT")
    private Integer numext;
    @Column(name = "NUMEXTALF")
    private String numextalf;
    @Column(name = "NUMINT")
    private Integer numint;
    @Column(name = "NUMINTALF")
    private String numintalf;
    @Column(name = "E12P")
    private String e12p;
    @Column(name = "TE19")
    private String te19;
    @Column(name = "E19")
    private String e19;
    @Column(name = "TE10_A")
    private String te10A;
    @Column(name = "E10_A")
    private String e10A;
    @Column(name = "TE10_B")
    private String te10B;
    @Column(name = "E10_B")
    private String e10B;
    @Column(name = "TE10_C")
    private String te10C;
    @Column(name = "E10_C")
    private String e10C;
    @Column(name = "C154")
    private String c154;
    @Column(name = "TIPOAREA")
    private String tipoarea;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "TRANSFER")
    private String transfer;
    @Column(name = "CVE_ORI")
    private String cveOri;
    @Column(name = "VERMCC")
    private String vermcc;
    @Column(name = "REGISTRO_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroFecha;
    @Column(name = "REGISTRO_HORA")
    private String registroHora;
    @Column(name = "TIPO_EDF")
    private Short tipoEdf;
    @Column(name = "ORIGEN")
    private String origen;
    @Column(name = "BAJA")
    private Short baja;
    @Column(name = "BAJA_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bajaFecha;
    @Column(name = "BAJA_HORA")
    private String bajaHora;
    @Column(name = "F_INTEGRADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fIntegrado;
    @Column(name = "ORIGEN_MC")
    private String origenMc;
    @Lob
    @Column(name = "THE_GEOM_WKT")
    private String theGeomWkt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "X")
    private BigDecimal x;
    @Column(name = "Y")
    private BigDecimal y;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @OneToMany(mappedBy = "idInmueble")
    private Collection<TrPredios> trPrediosCollection;
    @JoinColumn(name = "CVE_CE", referencedColumnName = "CESTATAL")
    @ManyToOne
    private TcCoestatales cveCe;
    @JoinColumns({
        @JoinColumn(name = "CVE_ENT", referencedColumnName = "CVE_ENT"),
        @JoinColumn(name = "CVE_MUN", referencedColumnName = "CVE_MUN"),
        @JoinColumn(name = "CVE_LOC", referencedColumnName = "CVE_LOC"),
        @JoinColumn(name = "CVE_AGEB", referencedColumnName = "CVE_AGEB"),
        @JoinColumn(name = "CVE_MZA", referencedColumnName = "CVE_MZA")})
    @ManyToOne
    private TcManzanas tcManzanas;
    @JoinColumn(name = "TIPO_E12P", referencedColumnName = "TIPO_E12P")
    @ManyToOne
    private TcPisos tipoE12p;
    @JoinColumn(name = "TIPO_E19", referencedColumnName = "TIPO_E19")
    @ManyToOne
    private TcTipoConglomerado tipoE19;
    @JoinColumn(name = "ID_TIPO_INMUEBLE", referencedColumnName = "ID_TIPO_INMUEBLE")
    @ManyToOne
    private TcTipoInmueble idTipoInmueble;
    @JoinColumn(name = "TIPO_E10_A", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipoE10A;
    @JoinColumn(name = "TIPO_E10_C", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipoE10C;
    @JoinColumn(name = "TIPOVIAL", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipovial;
    @JoinColumn(name = "TIPO_E10_B", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipoE10B;
    @JoinColumn(name = "ID_CONGLOMERADO", referencedColumnName = "ID_CONGLOMERADO")
    @ManyToOne
    private TrConglomerado idConglomerado;
    @JoinColumn(name = "ID_UE_ANT", referencedColumnName = "ID_UE")
    @ManyToOne
    private TrEtqVal idUeAnt;
    @JoinColumn(name = "ID_UE", referencedColumnName = "ID_UE")
    @ManyToOne
    private TrEtqVal idUe;
    @OneToMany(mappedBy = "idInmueble")
    private Collection<TrUoMasivo> trUoMasivoCollection;

    public TrInmuebles() {
    }

    public TrInmuebles(Long idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Long getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Long idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Integer getIdDeftramo() {
        return idDeftramo;
    }

    public void setIdDeftramo(Integer idDeftramo) {
        this.idDeftramo = idDeftramo;
    }

    public String getCleeEmp() {
        return cleeEmp;
    }

    public void setCleeEmp(String cleeEmp) {
        this.cleeEmp = cleeEmp;
    }

    public String getCleeEst() {
        return cleeEst;
    }

    public void setCleeEst(String cleeEst) {
        this.cleeEst = cleeEst;
    }

    public String getCveInm() {
        return cveInm;
    }

    public void setCveInm(String cveInm) {
        this.cveInm = cveInm;
    }

    public Short getCveft() {
        return cveft;
    }

    public void setCveft(Short cveft) {
        this.cveft = cveft;
    }

    public String getCvevial() {
        return cvevial;
    }

    public void setCvevial(String cvevial) {
        this.cvevial = cvevial;
    }

    public String getCvegeo() {
        return cvegeo;
    }

    public void setCvegeo(String cvegeo) {
        this.cvegeo = cvegeo;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getTe10() {
        return te10;
    }

    public void setTe10(String te10) {
        this.te10 = te10;
    }

    public String getNomvial() {
        return nomvial;
    }

    public void setNomvial(String nomvial) {
        this.nomvial = nomvial;
    }

    public Integer getNumext() {
        return numext;
    }

    public void setNumext(Integer numext) {
        this.numext = numext;
    }

    public String getNumextalf() {
        return numextalf;
    }

    public void setNumextalf(String numextalf) {
        this.numextalf = numextalf;
    }

    public Integer getNumint() {
        return numint;
    }

    public void setNumint(Integer numint) {
        this.numint = numint;
    }

    public String getNumintalf() {
        return numintalf;
    }

    public void setNumintalf(String numintalf) {
        this.numintalf = numintalf;
    }

    public String getE12p() {
        return e12p;
    }

    public void setE12p(String e12p) {
        this.e12p = e12p;
    }

    public String getTe19() {
        return te19;
    }

    public void setTe19(String te19) {
        this.te19 = te19;
    }

    public String getE19() {
        return e19;
    }

    public void setE19(String e19) {
        this.e19 = e19;
    }

    public String getTe10A() {
        return te10A;
    }

    public void setTe10A(String te10A) {
        this.te10A = te10A;
    }

    public String getE10A() {
        return e10A;
    }

    public void setE10A(String e10A) {
        this.e10A = e10A;
    }

    public String getTe10B() {
        return te10B;
    }

    public void setTe10B(String te10B) {
        this.te10B = te10B;
    }

    public String getE10B() {
        return e10B;
    }

    public void setE10B(String e10B) {
        this.e10B = e10B;
    }

    public String getTe10C() {
        return te10C;
    }

    public void setTe10C(String te10C) {
        this.te10C = te10C;
    }

    public String getE10C() {
        return e10C;
    }

    public void setE10C(String e10C) {
        this.e10C = e10C;
    }

    public String getC154() {
        return c154;
    }

    public void setC154(String c154) {
        this.c154 = c154;
    }

    public String getTipoarea() {
        return tipoarea;
    }

    public void setTipoarea(String tipoarea) {
        this.tipoarea = tipoarea;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getCveOri() {
        return cveOri;
    }

    public void setCveOri(String cveOri) {
        this.cveOri = cveOri;
    }

    public String getVermcc() {
        return vermcc;
    }

    public void setVermcc(String vermcc) {
        this.vermcc = vermcc;
    }

    public Date getRegistroFecha() {
        return registroFecha;
    }

    public void setRegistroFecha(Date registroFecha) {
        this.registroFecha = registroFecha;
    }

    public String getRegistroHora() {
        return registroHora;
    }

    public void setRegistroHora(String registroHora) {
        this.registroHora = registroHora;
    }

    public Short getTipoEdf() {
        return tipoEdf;
    }

    public void setTipoEdf(Short tipoEdf) {
        this.tipoEdf = tipoEdf;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Short getBaja() {
        return baja;
    }

    public void setBaja(Short baja) {
        this.baja = baja;
    }

    public Date getBajaFecha() {
        return bajaFecha;
    }

    public void setBajaFecha(Date bajaFecha) {
        this.bajaFecha = bajaFecha;
    }

    public String getBajaHora() {
        return bajaHora;
    }

    public void setBajaHora(String bajaHora) {
        this.bajaHora = bajaHora;
    }

    public Date getFIntegrado() {
        return fIntegrado;
    }

    public void setFIntegrado(Date fIntegrado) {
        this.fIntegrado = fIntegrado;
    }

    public String getOrigenMc() {
        return origenMc;
    }

    public void setOrigenMc(String origenMc) {
        this.origenMc = origenMc;
    }

    public String getTheGeomWkt() {
        return theGeomWkt;
    }

    public void setTheGeomWkt(String theGeomWkt) {
        this.theGeomWkt = theGeomWkt;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @XmlTransient
    public Collection<TrPredios> getTrPrediosCollection() {
        return trPrediosCollection;
    }

    public void setTrPrediosCollection(Collection<TrPredios> trPrediosCollection) {
        this.trPrediosCollection = trPrediosCollection;
    }

    public TcCoestatales getCveCe() {
        return cveCe;
    }

    public void setCveCe(TcCoestatales cveCe) {
        this.cveCe = cveCe;
    }

    public TcManzanas getTcManzanas() {
        return tcManzanas;
    }

    public void setTcManzanas(TcManzanas tcManzanas) {
        this.tcManzanas = tcManzanas;
    }

    public TcPisos getTipoE12p() {
        return tipoE12p;
    }

    public void setTipoE12p(TcPisos tipoE12p) {
        this.tipoE12p = tipoE12p;
    }

    public TcTipoConglomerado getTipoE19() {
        return tipoE19;
    }

    public void setTipoE19(TcTipoConglomerado tipoE19) {
        this.tipoE19 = tipoE19;
    }

    public TcTipoInmueble getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(TcTipoInmueble idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public TcVialidades getTipoE10A() {
        return tipoE10A;
    }

    public void setTipoE10A(TcVialidades tipoE10A) {
        this.tipoE10A = tipoE10A;
    }

    public TcVialidades getTipoE10C() {
        return tipoE10C;
    }

    public void setTipoE10C(TcVialidades tipoE10C) {
        this.tipoE10C = tipoE10C;
    }

    public TcVialidades getTipovial() {
        return tipovial;
    }

    public void setTipovial(TcVialidades tipovial) {
        this.tipovial = tipovial;
    }

    public TcVialidades getTipoE10B() {
        return tipoE10B;
    }

    public void setTipoE10B(TcVialidades tipoE10B) {
        this.tipoE10B = tipoE10B;
    }

    public TrConglomerado getIdConglomerado() {
        return idConglomerado;
    }

    public void setIdConglomerado(TrConglomerado idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    public TrEtqVal getIdUeAnt() {
        return idUeAnt;
    }

    public void setIdUeAnt(TrEtqVal idUeAnt) {
        this.idUeAnt = idUeAnt;
    }

    public TrEtqVal getIdUe() {
        return idUe;
    }

    public void setIdUe(TrEtqVal idUe) {
        this.idUe = idUe;
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
        hash += (idInmueble != null ? idInmueble.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrInmuebles)) {
            return false;
        }
        TrInmuebles other = (TrInmuebles) object;
        if ((this.idInmueble == null && other.idInmueble != null) || (this.idInmueble != null && !this.idInmueble.equals(other.idInmueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TrInmuebles[ idInmueble=" + idInmueble + " ]";
    }
    
}
