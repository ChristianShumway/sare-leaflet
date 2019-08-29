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
@Table(name = "TR_UO_MASIVO",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrUoMasivo.findAll", query = "SELECT t FROM TrUoMasivo t"),
    @NamedQuery(name = "TrUoMasivo.findByIdUoMasivo", query = "SELECT t FROM TrUoMasivo t WHERE t.idUoMasivo = :idUoMasivo"),
    @NamedQuery(name = "TrUoMasivo.findByCveft", query = "SELECT t FROM TrUoMasivo t WHERE t.cveft = :cveft"),
    @NamedQuery(name = "TrUoMasivo.findByTipoE10", query = "SELECT t FROM TrUoMasivo t WHERE t.tipoE10 = :tipoE10"),
    @NamedQuery(name = "TrUoMasivo.findByTe10", query = "SELECT t FROM TrUoMasivo t WHERE t.te10 = :te10"),
    @NamedQuery(name = "TrUoMasivo.findByE10", query = "SELECT t FROM TrUoMasivo t WHERE t.e10 = :e10"),
    @NamedQuery(name = "TrUoMasivo.findByE11", query = "SELECT t FROM TrUoMasivo t WHERE t.e11 = :e11"),
    @NamedQuery(name = "TrUoMasivo.findByE11a", query = "SELECT t FROM TrUoMasivo t WHERE t.e11a = :e11a"),
    @NamedQuery(name = "TrUoMasivo.findByE13", query = "SELECT t FROM TrUoMasivo t WHERE t.e13 = :e13"),
    @NamedQuery(name = "TrUoMasivo.findByE13a", query = "SELECT t FROM TrUoMasivo t WHERE t.e13a = :e13a"),
    @NamedQuery(name = "TrUoMasivo.findByTe12p", query = "SELECT t FROM TrUoMasivo t WHERE t.te12p = :te12p"),
    @NamedQuery(name = "TrUoMasivo.findByE12", query = "SELECT t FROM TrUoMasivo t WHERE t.e12 = :e12"),
    @NamedQuery(name = "TrUoMasivo.findByTe19", query = "SELECT t FROM TrUoMasivo t WHERE t.te19 = :te19"),
    @NamedQuery(name = "TrUoMasivo.findByE19", query = "SELECT t FROM TrUoMasivo t WHERE t.e19 = :e19"),
    @NamedQuery(name = "TrUoMasivo.findByTe10A", query = "SELECT t FROM TrUoMasivo t WHERE t.te10A = :te10A"),
    @NamedQuery(name = "TrUoMasivo.findByE10A", query = "SELECT t FROM TrUoMasivo t WHERE t.e10A = :e10A"),
    @NamedQuery(name = "TrUoMasivo.findByTe10B", query = "SELECT t FROM TrUoMasivo t WHERE t.te10B = :te10B"),
    @NamedQuery(name = "TrUoMasivo.findByE10B", query = "SELECT t FROM TrUoMasivo t WHERE t.e10B = :e10B"),
    @NamedQuery(name = "TrUoMasivo.findByTe10C", query = "SELECT t FROM TrUoMasivo t WHERE t.te10C = :te10C"),
    @NamedQuery(name = "TrUoMasivo.findByE10C", query = "SELECT t FROM TrUoMasivo t WHERE t.e10C = :e10C"),
    @NamedQuery(name = "TrUoMasivo.findByTe14", query = "SELECT t FROM TrUoMasivo t WHERE t.te14 = :te14"),
    @NamedQuery(name = "TrUoMasivo.findByE14", query = "SELECT t FROM TrUoMasivo t WHERE t.e14 = :e14"),
    @NamedQuery(name = "TrUoMasivo.findByE14a", query = "SELECT t FROM TrUoMasivo t WHERE t.e14a = :e14a"),
    @NamedQuery(name = "TrUoMasivo.findByOtroTipoInf", query = "SELECT t FROM TrUoMasivo t WHERE t.otroTipoInf = :otroTipoInf"),
    @NamedQuery(name = "TrUoMasivo.findByOtroTipoPred", query = "SELECT t FROM TrUoMasivo t WHERE t.otroTipoPred = :otroTipoPred"),
    @NamedQuery(name = "TrUoMasivo.findByFechaReg", query = "SELECT t FROM TrUoMasivo t WHERE t.fechaReg = :fechaReg"),
    @NamedQuery(name = "TrUoMasivo.findByX", query = "SELECT t FROM TrUoMasivo t WHERE t.x = :x"),
    @NamedQuery(name = "TrUoMasivo.findByY", query = "SELECT t FROM TrUoMasivo t WHERE t.y = :y"),
    @NamedQuery(name = "TrUoMasivo.findByEstatusViv", query = "SELECT t FROM TrUoMasivo t WHERE t.estatusViv = :estatusViv"),
    @NamedQuery(name = "TrUoMasivo.findByBaja", query = "SELECT t FROM TrUoMasivo t WHERE t.baja = :baja"),
    @NamedQuery(name = "TrUoMasivo.findByTotVivHab", query = "SELECT t FROM TrUoMasivo t WHERE t.totVivHab = :totVivHab"),
    @NamedQuery(name = "TrUoMasivo.findByTotVivDesh", query = "SELECT t FROM TrUoMasivo t WHERE t.totVivDesh = :totVivDesh"),
    @NamedQuery(name = "TrUoMasivo.findByTotViv", query = "SELECT t FROM TrUoMasivo t WHERE t.totViv = :totViv")})
public class TrUoMasivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UO_MASIVO")
    private Long idUoMasivo;
    @Column(name = "CVEFT")
    private Short cveft;
    @Column(name = "TIPO_E10")
    private String tipoE10;
    @Column(name = "TE10")
    private String te10;
    @Column(name = "E10")
    private String e10;
    @Column(name = "E11")
    private Integer e11;
    @Column(name = "E11A")
    private String e11a;
    @Column(name = "E13")
    private Integer e13;
    @Column(name = "E13A")
    private String e13a;
    @Column(name = "TE12P")
    private String te12p;
    @Column(name = "E12")
    private String e12;
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
    @Column(name = "TE14")
    private String te14;
    @Column(name = "E14")
    private String e14;
    @Column(name = "E14A")
    private Integer e14a;
    @Column(name = "OTRO_TIPO_INF")
    private String otroTipoInf;
    @Column(name = "OTRO_TIPO_PRED")
    private String otroTipoPred;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "X")
    private BigDecimal x;
    @Column(name = "Y")
    private BigDecimal y;
    @Column(name = "ESTATUS_VIV")
    private Short estatusViv;
    @Column(name = "BAJA")
    private Short baja;
    @Column(name = "TOT_VIV_HAB")
    private Integer totVivHab;
    @Column(name = "TOT_VIV_DESH")
    private Integer totVivDesh;
    @Column(name = "TOT_VIV")
    private Integer totViv;
    @OneToMany(mappedBy = "idUoMasivo")
    private Collection<TrPredios> trPrediosCollection;
    @JoinColumn(name = "TIPO_E14", referencedColumnName = "CVE_TIPO_ASEN")
    @ManyToOne
    private TcAsentamiento tipoE14;
    @JoinColumn(name = "ST_VIVP", referencedColumnName = "ST_VIVP")
    @ManyToOne
    private TcEstatusVivp stVivp;
    @JoinColumns({
        @JoinColumn(name = "E03", referencedColumnName = "CVE_ENT"),
        @JoinColumn(name = "E04", referencedColumnName = "CVE_MUN"),
        @JoinColumn(name = "E05", referencedColumnName = "CVE_LOC"),
        @JoinColumn(name = "E06", referencedColumnName = "CVE_AGEB"),
        @JoinColumn(name = "E07", referencedColumnName = "CVE_MZA")})
    @ManyToOne(optional = false)
    private TcManzanas tcManzanas;
    @JoinColumn(name = "TIPO_E12P", referencedColumnName = "TIPO_E12P")
    @ManyToOne
    private TcPisos tipoE12p;
    @JoinColumn(name = "TIPO_E19", referencedColumnName = "TIPO_E19")
    @ManyToOne
    private TcTipoConglomerado tipoE19;
    @JoinColumn(name = "ID_INMUEBLE", referencedColumnName = "ID_INMUEBLE")
    @ManyToOne
    private TrInmuebles idInmueble;
    @JoinColumn(name = "TIPO_INF", referencedColumnName = "TIPO_INF")
    @ManyToOne
    private TcTipoInformante tipoInf;
    @JoinColumn(name = "ID_TIPO_INMUEBLE", referencedColumnName = "ID_TIPO_INMUEBLE")
    @ManyToOne
    private TcTipoInmueble idTipoInmueble;
    @JoinColumn(name = "TIPO_PREDIO", referencedColumnName = "TIPO_PREDIO")
    @ManyToOne
    private TcTipoPredio tipoPredio;
    @JoinColumn(name = "TIPO_E10_A", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipoE10A;
    @JoinColumn(name = "TIPO_E10_C", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipoE10C;
    @JoinColumn(name = "ID_CONGLOMERADO", referencedColumnName = "ID_CONGLOMERADO")
    @ManyToOne
    private TrConglomerado idConglomerado;
    @JoinColumn(name = "TIPO_E10_B", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    private TcVialidades tipoE10B;

    public TrUoMasivo() {
    }

    public TrUoMasivo(Long idUoMasivo) {
        this.idUoMasivo = idUoMasivo;
    }

    public Long getIdUoMasivo() {
        return idUoMasivo;
    }

    public void setIdUoMasivo(Long idUoMasivo) {
        this.idUoMasivo = idUoMasivo;
    }

    public Short getCveft() {
        return cveft;
    }

    public void setCveft(Short cveft) {
        this.cveft = cveft;
    }

    public String getTipoE10() {
        return tipoE10;
    }

    public void setTipoE10(String tipoE10) {
        this.tipoE10 = tipoE10;
    }

    public String getTe10() {
        return te10;
    }

    public void setTe10(String te10) {
        this.te10 = te10;
    }

    public String getE10() {
        return e10;
    }

    public void setE10(String e10) {
        this.e10 = e10;
    }

    public Integer getE11() {
        return e11;
    }

    public void setE11(Integer e11) {
        this.e11 = e11;
    }

    public String getE11a() {
        return e11a;
    }

    public void setE11a(String e11a) {
        this.e11a = e11a;
    }

    public Integer getE13() {
        return e13;
    }

    public void setE13(Integer e13) {
        this.e13 = e13;
    }

    public String getE13a() {
        return e13a;
    }

    public void setE13a(String e13a) {
        this.e13a = e13a;
    }

    public String getTe12p() {
        return te12p;
    }

    public void setTe12p(String te12p) {
        this.te12p = te12p;
    }

    public String getE12() {
        return e12;
    }

    public void setE12(String e12) {
        this.e12 = e12;
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

    public String getTe14() {
        return te14;
    }

    public void setTe14(String te14) {
        this.te14 = te14;
    }

    public String getE14() {
        return e14;
    }

    public void setE14(String e14) {
        this.e14 = e14;
    }

    public Integer getE14a() {
        return e14a;
    }

    public void setE14a(Integer e14a) {
        this.e14a = e14a;
    }

    public String getOtroTipoInf() {
        return otroTipoInf;
    }

    public void setOtroTipoInf(String otroTipoInf) {
        this.otroTipoInf = otroTipoInf;
    }

    public String getOtroTipoPred() {
        return otroTipoPred;
    }

    public void setOtroTipoPred(String otroTipoPred) {
        this.otroTipoPred = otroTipoPred;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
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

    public Short getEstatusViv() {
        return estatusViv;
    }

    public void setEstatusViv(Short estatusViv) {
        this.estatusViv = estatusViv;
    }

    public Short getBaja() {
        return baja;
    }

    public void setBaja(Short baja) {
        this.baja = baja;
    }

    public Integer getTotVivHab() {
        return totVivHab;
    }

    public void setTotVivHab(Integer totVivHab) {
        this.totVivHab = totVivHab;
    }

    public Integer getTotVivDesh() {
        return totVivDesh;
    }

    public void setTotVivDesh(Integer totVivDesh) {
        this.totVivDesh = totVivDesh;
    }

    public Integer getTotViv() {
        return totViv;
    }

    public void setTotViv(Integer totViv) {
        this.totViv = totViv;
    }

    @XmlTransient
    public Collection<TrPredios> getTrPrediosCollection() {
        return trPrediosCollection;
    }

    public void setTrPrediosCollection(Collection<TrPredios> trPrediosCollection) {
        this.trPrediosCollection = trPrediosCollection;
    }

    public TcAsentamiento getTipoE14() {
        return tipoE14;
    }

    public void setTipoE14(TcAsentamiento tipoE14) {
        this.tipoE14 = tipoE14;
    }

    public TcEstatusVivp getStVivp() {
        return stVivp;
    }

    public void setStVivp(TcEstatusVivp stVivp) {
        this.stVivp = stVivp;
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

    public TrInmuebles getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(TrInmuebles idInmueble) {
        this.idInmueble = idInmueble;
    }

    public TcTipoInformante getTipoInf() {
        return tipoInf;
    }

    public void setTipoInf(TcTipoInformante tipoInf) {
        this.tipoInf = tipoInf;
    }

    public TcTipoInmueble getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(TcTipoInmueble idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public TcTipoPredio getTipoPredio() {
        return tipoPredio;
    }

    public void setTipoPredio(TcTipoPredio tipoPredio) {
        this.tipoPredio = tipoPredio;
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

    public TrConglomerado getIdConglomerado() {
        return idConglomerado;
    }

    public void setIdConglomerado(TrConglomerado idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    public TcVialidades getTipoE10B() {
        return tipoE10B;
    }

    public void setTipoE10B(TcVialidades tipoE10B) {
        this.tipoE10B = tipoE10B;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUoMasivo != null ? idUoMasivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrUoMasivo)) {
            return false;
        }
        TrUoMasivo other = (TrUoMasivo) object;
        if ((this.idUoMasivo == null && other.idUoMasivo != null) || (this.idUoMasivo != null && !this.idUoMasivo.equals(other.idUoMasivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TrUoMasivo[ idUoMasivo=" + idUoMasivo + " ]";
    }
    
}
