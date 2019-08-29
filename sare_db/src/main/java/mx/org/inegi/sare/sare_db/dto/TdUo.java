/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Entity
@Table(name = "td_uo", schema = "sare_mas2019_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TdUo.findAll", query = "SELECT t FROM TdUo t"),
    @NamedQuery(name = "TdUo.findByGid", query = "SELECT t FROM TdUo t WHERE t.gid = :gid"),
    @NamedQuery(name = "TdUo.findByIdUo", query = "SELECT t FROM TdUo t WHERE t.idUo = :idUo"),
    @NamedQuery(name = "TdUo.findByTramoControl", query = "SELECT t FROM TdUo t WHERE t.tramoControl = :tramoControl"),
    @NamedQuery(name = "TdUo.findByIdDeftramo", query = "SELECT t FROM TdUo t WHERE t.idDeftramo = :idDeftramo"),
    @NamedQuery(name = "TdUo.findByCvegeo", query = "SELECT t FROM TdUo t WHERE t.cvegeo = :cvegeo"),
    @NamedQuery(name = "TdUo.findByCveCe", query = "SELECT t FROM TdUo t WHERE t.cveCe = :cveCe"),
    @NamedQuery(name = "TdUo.findByCveEnt", query = "SELECT t FROM TdUo t WHERE t.cveEnt = :cveEnt"),
    @NamedQuery(name = "TdUo.findByCveMun", query = "SELECT t FROM TdUo t WHERE t.cveMun = :cveMun"),
    @NamedQuery(name = "TdUo.findByCveLoc", query = "SELECT t FROM TdUo t WHERE t.cveLoc = :cveLoc"),
    @NamedQuery(name = "TdUo.findByCveAgeb", query = "SELECT t FROM TdUo t WHERE t.cveAgeb = :cveAgeb"),
    @NamedQuery(name = "TdUo.findByCveMza", query = "SELECT t FROM TdUo t WHERE t.cveMza = :cveMza"),
    @NamedQuery(name = "TdUo.findByCveft", query = "SELECT t FROM TdUo t WHERE t.cveft = :cveft"),
    @NamedQuery(name = "TdUo.findByOracle", query = "SELECT t FROM TdUo t WHERE t.oracle = :oracle"),
    @NamedQuery(name = "TdUo.findByTheGeomWkt", query = "SELECT t FROM TdUo t WHERE t.theGeomWkt = :theGeomWkt"),
    @NamedQuery(name = "TdUo.findByFecha", query = "SELECT t FROM TdUo t WHERE t.fecha = :fecha")})
public class TdUo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "gid")
    private int gid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id_uo")
    private Long idUo;
    @Column(name = "tramo_control")
    private String tramoControl;
    @Column(name = "id_deftramo")
    private Integer idDeftramo;
    @Column(name = "cvegeo")
    private String cvegeo;
    @Column(name = "cve_ce")
    private String cveCe;
    @Column(name = "cve_ent")
    private String cveEnt;
    @Column(name = "cve_mun")
    private String cveMun;
    @Column(name = "cve_loc")
    private String cveLoc;
    @Column(name = "cve_ageb")
    private String cveAgeb;
    @Column(name = "cve_mza")
    private String cveMza;
    @Column(name = "cveft")
    private String cveft;
    @Column(name = "oracle")
    private Boolean oracle;
    @Column(name = "the_geom_wkt")
    private String theGeomWkt;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "the_geom_itrf08")
    private String theGeomItrf08;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "the_geom_merc")
    private String theGeomMerc;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public TdUo() {
    }

    public TdUo(Long idUo) {
        this.idUo = idUo;
    }

    public TdUo(Long idUo, int gid) {
        this.idUo = idUo;
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Long getIdUo() {
        return idUo;
    }

    public void setIdUo(Long idUo) {
        this.idUo = idUo;
    }

    public String getTramoControl() {
        return tramoControl;
    }

    public void setTramoControl(String tramoControl) {
        this.tramoControl = tramoControl;
    }

    public Integer getIdDeftramo() {
        return idDeftramo;
    }

    public void setIdDeftramo(Integer idDeftramo) {
        this.idDeftramo = idDeftramo;
    }

    public String getCvegeo() {
        return cvegeo;
    }

    public void setCvegeo(String cvegeo) {
        this.cvegeo = cvegeo;
    }

    public String getCveCe() {
        return cveCe;
    }

    public void setCveCe(String cveCe) {
        this.cveCe = cveCe;
    }

    public String getCveEnt() {
        return cveEnt;
    }

    public void setCveEnt(String cveEnt) {
        this.cveEnt = cveEnt;
    }

    public String getCveMun() {
        return cveMun;
    }

    public void setCveMun(String cveMun) {
        this.cveMun = cveMun;
    }

    public String getCveLoc() {
        return cveLoc;
    }

    public void setCveLoc(String cveLoc) {
        this.cveLoc = cveLoc;
    }

    public String getCveAgeb() {
        return cveAgeb;
    }

    public void setCveAgeb(String cveAgeb) {
        this.cveAgeb = cveAgeb;
    }

    public String getCveMza() {
        return cveMza;
    }

    public void setCveMza(String cveMza) {
        this.cveMza = cveMza;
    }

    public String getCveft() {
        return cveft;
    }

    public void setCveft(String cveft) {
        this.cveft = cveft;
    }

    public Boolean getOracle() {
        return oracle;
    }

    public void setOracle(Boolean oracle) {
        this.oracle = oracle;
    }

    public String getTheGeomWkt() {
        return theGeomWkt;
    }

    public void setTheGeomWkt(String theGeomWkt) {
        this.theGeomWkt = theGeomWkt;
    }

    public Object getTheGeomItrf08() {
        return theGeomItrf08;
    }

    public void setTheGeomItrf08(String theGeomItrf08) {
        this.theGeomItrf08 = theGeomItrf08;
    }

    public Object getTheGeomMerc() {
        return theGeomMerc;
    }

    public void setTheGeomMerc(String theGeomMerc) {
        this.theGeomMerc = theGeomMerc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUo != null ? idUo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdUo)) {
            return false;
        }
        TdUo other = (TdUo) object;
        if ((this.idUo == null && other.idUo != null) || (this.idUo != null && !this.idUo.equals(other.idUo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TdUo[ idUo=" + idUo + " ]";
    }
    
}
