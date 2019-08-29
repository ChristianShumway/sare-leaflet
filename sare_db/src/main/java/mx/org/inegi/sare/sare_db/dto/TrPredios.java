/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Entity
@Table(name = "TR_PREDIOS", schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrPredios.findAll", query = "SELECT t FROM TrPredios t"),
    @NamedQuery(name = "TrPredios.findByIdPredio", query = "SELECT t FROM TrPredios t WHERE t.idPredio = :idPredio"),
    @NamedQuery(name = "TrPredios.findByRegistroFecha", query = "SELECT t FROM TrPredios t WHERE t.registroFecha = :registroFecha"),
    @NamedQuery(name = "TrPredios.findByBaja", query = "SELECT t FROM TrPredios t WHERE t.baja = :baja"),
    @NamedQuery(name = "TrPredios.findByBajaFecha", query = "SELECT t FROM TrPredios t WHERE t.bajaFecha = :bajaFecha"),
    @NamedQuery(name = "TrPredios.findByFIntegrado", query = "SELECT t FROM TrPredios t WHERE t.fIntegrado = :fIntegrado"),
    @NamedQuery(name = "TrPredios.findByFechaSare", query = "SELECT t FROM TrPredios t WHERE t.fechaSare = :fechaSare"),
    @NamedQuery(name = "TrPredios.findByPunteoSare", query = "SELECT t FROM TrPredios t WHERE t.punteoSare = :punteoSare")})
public class TrPredios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PREDIO")
    private Long idPredio;
    @Column(name = "REGISTRO_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroFecha;
    @Column(name = "BAJA")
    private Short baja;
    @Column(name = "BAJA_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bajaFecha;
    @Column(name = "F_INTEGRADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fIntegrado;
    @Column(name = "FECHA_SARE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSare;
    @Basic(optional = false)
    @Column(name = "PUNTEO_SARE")
    private short punteoSare;
    @JoinColumn(name = "ID_CGO", referencedColumnName = "ID_CGO")
    @ManyToOne
    private TcCgo idCgo;
    @JoinColumn(name = "ST_SARE", referencedColumnName = "ST_SARE")
    @ManyToOne
    private TcEstatusSare stSare;
    @JoinColumn(name = "TIPO_E12P", referencedColumnName = "TIPO_E12P")
    @ManyToOne
    private TcPisos tipoE12p;
    @JoinColumn(name = "STATUS_SARE", referencedColumnName = "STATUS_SARE")
    @ManyToOne
    private TcStSare statusSare;
    @JoinColumn(name = "ID_UE_ANT", referencedColumnName = "ID_UE")
    @ManyToOne
    private TrEtqVal idUeAnt;
    @JoinColumn(name = "ID_UE", referencedColumnName = "ID_UE")
    @ManyToOne
    private TrEtqVal idUe;
    @JoinColumn(name = "ID_INMUEBLE", referencedColumnName = "ID_INMUEBLE")
    @ManyToOne
    private TrInmuebles idInmueble;
    @JoinColumn(name = "ID_COP", referencedColumnName = "ID_COP")
    @ManyToOne
    private TrPlanOper idCop;
    @JoinColumn(name = "ID_UO_MASIVO", referencedColumnName = "ID_UO_MASIVO")
    @ManyToOne
    private TrUoMasivo idUoMasivo;

    public TrPredios() {
    }

    public TrPredios(Long idPredio) {
        this.idPredio = idPredio;
    }

    public TrPredios(Long idPredio, short punteoSare) {
        this.idPredio = idPredio;
        this.punteoSare = punteoSare;
    }

    public Long getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Long idPredio) {
        this.idPredio = idPredio;
    }

    public Date getRegistroFecha() {
        return registroFecha;
    }

    public void setRegistroFecha(Date registroFecha) {
        this.registroFecha = registroFecha;
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

    public Date getFIntegrado() {
        return fIntegrado;
    }

    public void setFIntegrado(Date fIntegrado) {
        this.fIntegrado = fIntegrado;
    }

    public Date getFechaSare() {
        return fechaSare;
    }

    public void setFechaSare(Date fechaSare) {
        this.fechaSare = fechaSare;
    }

    public short getPunteoSare() {
        return punteoSare;
    }

    public void setPunteoSare(short punteoSare) {
        this.punteoSare = punteoSare;
    }

    public TcCgo getIdCgo() {
        return idCgo;
    }

    public void setIdCgo(TcCgo idCgo) {
        this.idCgo = idCgo;
    }

    public TcEstatusSare getStSare() {
        return stSare;
    }

    public void setStSare(TcEstatusSare stSare) {
        this.stSare = stSare;
    }

    public TcPisos getTipoE12p() {
        return tipoE12p;
    }

    public void setTipoE12p(TcPisos tipoE12p) {
        this.tipoE12p = tipoE12p;
    }

    public TcStSare getStatusSare() {
        return statusSare;
    }

    public void setStatusSare(TcStSare statusSare) {
        this.statusSare = statusSare;
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

    public TrInmuebles getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(TrInmuebles idInmueble) {
        this.idInmueble = idInmueble;
    }

    public TrPlanOper getIdCop() {
        return idCop;
    }

    public void setIdCop(TrPlanOper idCop) {
        this.idCop = idCop;
    }

    public TrUoMasivo getIdUoMasivo() {
        return idUoMasivo;
    }

    public void setIdUoMasivo(TrUoMasivo idUoMasivo) {
        this.idUoMasivo = idUoMasivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPredio != null ? idPredio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrPredios)) {
            return false;
        }
        TrPredios other = (TrPredios) object;
        if ((this.idPredio == null && other.idPredio != null) || (this.idPredio != null && !this.idPredio.equals(other.idPredio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TrPredios[ idPredio=" + idPredio + " ]";
    }
    
}
