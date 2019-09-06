/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "td_inmuebles", schema = "sare_mas2019_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TdInmuebles.findAll", query = "SELECT t FROM TdInmuebles t"),
    @NamedQuery(name = "TdInmuebles.findByGid", query = "SELECT t FROM TdInmuebles t WHERE t.gid = :gid"),
    @NamedQuery(name = "TdInmuebles.findByIdDeftramo", query = "SELECT t FROM TdInmuebles t WHERE t.idDeftramo = :idDeftramo"),
    @NamedQuery(name = "TdInmuebles.findByIdInmueble", query = "SELECT t FROM TdInmuebles t WHERE t.idInmueble = :idInmueble"),
    @NamedQuery(name = "TdInmuebles.findByIdUe", query = "SELECT t FROM TdInmuebles t WHERE t.idUe = :idUe"),
    @NamedQuery(name = "TdInmuebles.findBySareSt", query = "SELECT t FROM TdInmuebles t WHERE t.sareSt = :sareSt"),
    @NamedQuery(name = "TdInmuebles.findByTramoControl", query = "SELECT t FROM TdInmuebles t WHERE t.tramoControl = :tramoControl"),
    @NamedQuery(name = "TdInmuebles.findByCvegeo", query = "SELECT t FROM TdInmuebles t WHERE t.cvegeo = :cvegeo"),
    @NamedQuery(name = "TdInmuebles.findByCveEnt", query = "SELECT t FROM TdInmuebles t WHERE t.cveEnt = :cveEnt"),
    @NamedQuery(name = "TdInmuebles.findByNomEnt", query = "SELECT t FROM TdInmuebles t WHERE t.nomEnt = :nomEnt"),
    @NamedQuery(name = "TdInmuebles.findByCveMun", query = "SELECT t FROM TdInmuebles t WHERE t.cveMun = :cveMun"),
    @NamedQuery(name = "TdInmuebles.findByNomMun", query = "SELECT t FROM TdInmuebles t WHERE t.nomMun = :nomMun"),
    @NamedQuery(name = "TdInmuebles.findByCveLoc", query = "SELECT t FROM TdInmuebles t WHERE t.cveLoc = :cveLoc"),
    @NamedQuery(name = "TdInmuebles.findByNomLoc", query = "SELECT t FROM TdInmuebles t WHERE t.nomLoc = :nomLoc"),
    @NamedQuery(name = "TdInmuebles.findByCveAgeb", query = "SELECT t FROM TdInmuebles t WHERE t.cveAgeb = :cveAgeb"),
    @NamedQuery(name = "TdInmuebles.findByCveMza", query = "SELECT t FROM TdInmuebles t WHERE t.cveMza = :cveMza"),
    @NamedQuery(name = "TdInmuebles.findByCveInm", query = "SELECT t FROM TdInmuebles t WHERE t.cveInm = :cveInm"),
    @NamedQuery(name = "TdInmuebles.findByCveft", query = "SELECT t FROM TdInmuebles t WHERE t.cveft = :cveft"),
    @NamedQuery(name = "TdInmuebles.findByE08", query = "SELECT t FROM TdInmuebles t WHERE t.e08 = :e08"),
    @NamedQuery(name = "TdInmuebles.findByE09", query = "SELECT t FROM TdInmuebles t WHERE t.e09 = :e09"),
    @NamedQuery(name = "TdInmuebles.findByCvevial", query = "SELECT t FROM TdInmuebles t WHERE t.cvevial = :cvevial"),
    @NamedQuery(name = "TdInmuebles.findByIdFrente", query = "SELECT t FROM TdInmuebles t WHERE t.idFrente = :idFrente"),
    @NamedQuery(name = "TdInmuebles.findByNomvial", query = "SELECT t FROM TdInmuebles t WHERE t.nomvial = :nomvial"),
    @NamedQuery(name = "TdInmuebles.findByNumext", query = "SELECT t FROM TdInmuebles t WHERE t.numext = :numext"),
    @NamedQuery(name = "TdInmuebles.findByNumextalf", query = "SELECT t FROM TdInmuebles t WHERE t.numextalf = :numextalf"),
    @NamedQuery(name = "TdInmuebles.findByE12", query = "SELECT t FROM TdInmuebles t WHERE t.e12 = :e12"),
    @NamedQuery(name = "TdInmuebles.findByNumint", query = "SELECT t FROM TdInmuebles t WHERE t.numint = :numint"),
    @NamedQuery(name = "TdInmuebles.findByNumintalf", query = "SELECT t FROM TdInmuebles t WHERE t.numintalf = :numintalf"),
    @NamedQuery(name = "TdInmuebles.findByE14", query = "SELECT t FROM TdInmuebles t WHERE t.e14 = :e14"),
    @NamedQuery(name = "TdInmuebles.findByE14A", query = "SELECT t FROM TdInmuebles t WHERE t.e14A = :e14A"),
    @NamedQuery(name = "TdInmuebles.findByE10A", query = "SELECT t FROM TdInmuebles t WHERE t.e10A = :e10A"),
    @NamedQuery(name = "TdInmuebles.findByE10B", query = "SELECT t FROM TdInmuebles t WHERE t.e10B = :e10B"),
    @NamedQuery(name = "TdInmuebles.findByE10C", query = "SELECT t FROM TdInmuebles t WHERE t.e10C = :e10C"),
    @NamedQuery(name = "TdInmuebles.findByDescrubic", query = "SELECT t FROM TdInmuebles t WHERE t.descrubic = :descrubic"),
    @NamedQuery(name = "TdInmuebles.findByTheGeomWkt", query = "SELECT t FROM TdInmuebles t WHERE t.theGeomWkt = :theGeomWkt"),
    @NamedQuery(name = "TdInmuebles.findByCoordX", query = "SELECT t FROM TdInmuebles t WHERE t.coordX = :coordX"),
    @NamedQuery(name = "TdInmuebles.findByCoordY", query = "SELECT t FROM TdInmuebles t WHERE t.coordY = :coordY"),
    @NamedQuery(name = "TdInmuebles.findByE19", query = "SELECT t FROM TdInmuebles t WHERE t.e19 = :e19"),
    @NamedQuery(name = "TdInmuebles.findByTipoE19", query = "SELECT t FROM TdInmuebles t WHERE t.tipoE19 = :tipoE19"),
    @NamedQuery(name = "TdInmuebles.findByTipoarea", query = "SELECT t FROM TdInmuebles t WHERE t.tipoarea = :tipoarea"),
    @NamedQuery(name = "TdInmuebles.findByE23", query = "SELECT t FROM TdInmuebles t WHERE t.e23 = :e23"),
    @NamedQuery(name = "TdInmuebles.findByCveOri", query = "SELECT t FROM TdInmuebles t WHERE t.cveOri = :cveOri"),
    @NamedQuery(name = "TdInmuebles.findByTransfer", query = "SELECT t FROM TdInmuebles t WHERE t.transfer = :transfer"),
    @NamedQuery(name = "TdInmuebles.findByVermcc", query = "SELECT t FROM TdInmuebles t WHERE t.vermcc = :vermcc"),
    @NamedQuery(name = "TdInmuebles.findByFecha", query = "SELECT t FROM TdInmuebles t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TdInmuebles.findByRegistroFecha", query = "SELECT t FROM TdInmuebles t WHERE t.registroFecha = :registroFecha"),
    @NamedQuery(name = "TdInmuebles.findByRegistroHora", query = "SELECT t FROM TdInmuebles t WHERE t.registroHora = :registroHora"),
    @NamedQuery(name = "TdInmuebles.findByModCat", query = "SELECT t FROM TdInmuebles t WHERE t.modCat = :modCat"),
    @NamedQuery(name = "TdInmuebles.findByEstatus", query = "SELECT t FROM TdInmuebles t WHERE t.estatus = :estatus"),
    @NamedQuery(name = "TdInmuebles.findByNomEst", query = "SELECT t FROM TdInmuebles t WHERE t.nomEst = :nomEst"),
    @NamedQuery(name = "TdInmuebles.findByTipoEdf", query = "SELECT t FROM TdInmuebles t WHERE t.tipoEdf = :tipoEdf"),
    @NamedQuery(name = "TdInmuebles.findByBajaFecha", query = "SELECT t FROM TdInmuebles t WHERE t.bajaFecha = :bajaFecha"),
    @NamedQuery(name = "TdInmuebles.findByBajaHora", query = "SELECT t FROM TdInmuebles t WHERE t.bajaHora = :bajaHora"),
    @NamedQuery(name = "TdInmuebles.findByOrigen", query = "SELECT t FROM TdInmuebles t WHERE t.origen = :origen"),
    @NamedQuery(name = "TdInmuebles.findByOrigenMc", query = "SELECT t FROM TdInmuebles t WHERE t.origenMc = :origenMc"),
    @NamedQuery(name = "TdInmuebles.findByE10E", query = "SELECT t FROM TdInmuebles t WHERE t.e10E = :e10E"),
    @NamedQuery(name = "TdInmuebles.findByE12p", query = "SELECT t FROM TdInmuebles t WHERE t.e12p = :e12p"),
    @NamedQuery(name = "TdInmuebles.findByE20", query = "SELECT t FROM TdInmuebles t WHERE t.e20 = :e20"),
    @NamedQuery(name = "TdInmuebles.findByBaja", query = "SELECT t FROM TdInmuebles t WHERE t.baja = :baja"),
    @NamedQuery(name = "TdInmuebles.findByCvegeo2016", query = "SELECT t FROM TdInmuebles t WHERE t.cvegeo2016 = :cvegeo2016"),
    @NamedQuery(name = "TdInmuebles.findByC154", query = "SELECT t FROM TdInmuebles t WHERE t.c154 = :c154"),
    @NamedQuery(name = "TdInmuebles.findByActualizaUe", query = "SELECT t FROM TdInmuebles t WHERE t.actualizaUe = :actualizaUe"),
    @NamedQuery(name = "TdInmuebles.findByCleeEmp", query = "SELECT t FROM TdInmuebles t WHERE t.cleeEmp = :cleeEmp"),
    @NamedQuery(name = "TdInmuebles.findByCleeEst", query = "SELECT t FROM TdInmuebles t WHERE t.cleeEst = :cleeEst"),
    @NamedQuery(name = "TdInmuebles.findByTipo", query = "SELECT t FROM TdInmuebles t WHERE t.tipo = :tipo")})
public class TdInmuebles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "gid")
    private int gid;
    @Column(name = "id_deftramo")
    private Integer idDeftramo;
    @Column(name = "id_inmueble")
    private Long idInmueble;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id_ue")
    private BigDecimal idUe;
    @Column(name = "sare_st")
    private String sareSt;
    @Column(name = "tramo_control")
    private String tramoControl;
    @Column(name = "cvegeo")
    private String cvegeo;
    @Basic(optional = false)
    @Column(name = "cve_ent")
    private String cveEnt;
    @Column(name = "nom_ent")
    private String nomEnt;
    @Basic(optional = false)
    @Column(name = "cve_mun")
    private String cveMun;
    @Column(name = "nom_mun")
    private String nomMun;
    @Basic(optional = false)
    @Column(name = "cve_loc")
    private String cveLoc;
    @Column(name = "nom_loc")
    private String nomLoc;
    @Basic(optional = false)
    @Column(name = "cve_ageb")
    private String cveAgeb;
    @Basic(optional = false)
    @Column(name = "cve_mza")
    private String cveMza;
    @Column(name = "cve_inm")
    private String cveInm;
    @Column(name = "cveft")
    private Short cveft;
    @Column(name = "e08")
    private String e08;
    @Column(name = "e09")
    private String e09;
    @Column(name = "cvevial")
    private String cvevial;
    @Column(name = "id_frente")
    private BigInteger idFrente;
    @Column(name = "nomvial")
    private String nomvial;
    @Column(name = "numext")
    private String numext;
    @Column(name = "numextalf")
    private String numextalf;
    @Column(name = "e12")
    private String e12;
    @Column(name = "numint")
    private String numint;
    @Column(name = "numintalf")
    private String numintalf;
    @Column(name = "e14")
    private String e14;
    @Column(name = "e14_a")
    private String e14A;
    @Column(name = "e10_a")
    private String e10A;
    @Column(name = "e10_b")
    private String e10B;
    @Column(name = "e10_c")
    private String e10C;
    @Column(name = "descrubic")
    private String descrubic;
    @Column(name = "the_geom_wkt")
    private String theGeomWkt;
    @Column(name = "the_geom")
    private String theGeom;
    @Column(name = "the_geom_merc")
    private String theGeomMerc;
    @Column(name = "coord_x")
    private BigDecimal coordX;
    @Column(name = "coord_y")
    private BigDecimal coordY;
    @Column(name = "e19")
    private String e19;
    @Column(name = "tipo_e19")
    private String tipoE19;
    @Column(name = "tipoarea")
    private String tipoarea;
    @Column(name = "e23")
    private String e23;
    @Column(name = "cve_ori")
    private String cveOri;
    @Column(name = "transfer")
    private String transfer;
    @Column(name = "vermcc")
    private String vermcc;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "registro_fecha")
    private String registroFecha;
    @Column(name = "registro_hora")
    private String registroHora;
    @Column(name = "mod_cat")
    private Short modCat;
    @Column(name = "estatus")
    private Short estatus;
    @Column(name = "nom_est")
    private String nomEst;
    @Column(name = "tipo_edf")
    private Long tipoEdf;
    @Column(name = "baja_fecha")
    private String bajaFecha;
    @Column(name = "baja_hora")
    private String bajaHora;
    @Column(name = "origen")
    private String origen;
    @Column(name = "origen_mc")
    private String origenMc;
    @Column(name = "e10_e")
    private String e10E;
    @Column(name = "e12p")
    private String e12p;
    @Column(name = "e20")
    private String e20;
    @Column(name = "baja")
    private Short baja;
    @Column(name = "cvegeo2016")
    private String cvegeo2016;
    @Column(name = "c154")
    private String c154;
    @Column(name = "actualiza_ue")
    private Boolean actualizaUe;
    @Column(name = "clee_emp")
    private String cleeEmp;
    @Column(name = "clee_est")
    private String cleeEst;
    @Column(name = "tipo")
    private String tipo;
    @JsonIgnore
    @JoinColumn(name = "tipo_e14", referencedColumnName = "tipo_e14")
    @ManyToOne(fetch = FetchType.EAGER)
    private CatAsentamientosHumanos tipoE14;
    @JsonIgnore
    @JoinColumn(name = "cve_ce", referencedColumnName = "cve_ce")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CatCe cveCe;
    @JsonIgnore
    @JoinColumn(name = "tipo_e10_c", referencedColumnName = "tipo_e10")
    @ManyToOne(fetch = FetchType.EAGER)
    private CatTipovialidad tipoE10C;
    @JsonIgnore
    @JoinColumn(name = "tipo_e10_b", referencedColumnName = "tipo_e10")
    @ManyToOne(fetch = FetchType.EAGER)
    private CatTipovialidad tipoE10B;
    @JsonIgnore
    @JoinColumn(name = "tipo_e10_a", referencedColumnName = "tipo_e10")
    @ManyToOne(fetch = FetchType.EAGER)
    private CatTipovialidad tipoE10A;
    @JsonIgnore
    @JoinColumn(name = "tipo_e10", referencedColumnName = "tipo_e10")
    @ManyToOne(fetch = FetchType.EAGER)
    private CatTipovialidad tipoE10;

    public TdInmuebles() {
    }

    public TdInmuebles(BigDecimal idUe) {
        this.idUe = idUe;
    }

    public TdInmuebles(BigDecimal idUe, int gid, String cveEnt, String cveMun, String cveLoc, String cveAgeb, String cveMza) {
        this.idUe = idUe;
        this.gid = gid;
        this.cveEnt = cveEnt;
        this.cveMun = cveMun;
        this.cveLoc = cveLoc;
        this.cveAgeb = cveAgeb;
        this.cveMza = cveMza;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Integer getIdDeftramo() {
        return idDeftramo;
    }

    public void setIdDeftramo(Integer idDeftramo) {
        this.idDeftramo = idDeftramo;
    }

    public Long getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Long idInmueble) {
        this.idInmueble = idInmueble;
    }

    public BigDecimal getIdUe() {
        return idUe;
    }

    public void setIdUe(BigDecimal idUe) {
        this.idUe = idUe;
    }

    public String getSareSt() {
        return sareSt;
    }

    public void setSareSt(String sareSt) {
        this.sareSt = sareSt;
    }

    public String getTramoControl() {
        return tramoControl;
    }

    public void setTramoControl(String tramoControl) {
        this.tramoControl = tramoControl;
    }

    public String getCvegeo() {
        return cvegeo;
    }

    public void setCvegeo(String cvegeo) {
        this.cvegeo = cvegeo;
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

    public String getCveMun() {
        return cveMun;
    }

    public void setCveMun(String cveMun) {
        this.cveMun = cveMun;
    }

    public String getNomMun() {
        return nomMun;
    }

    public void setNomMun(String nomMun) {
        this.nomMun = nomMun;
    }

    public String getCveLoc() {
        return cveLoc;
    }

    public void setCveLoc(String cveLoc) {
        this.cveLoc = cveLoc;
    }

    public String getNomLoc() {
        return nomLoc;
    }

    public void setNomLoc(String nomLoc) {
        this.nomLoc = nomLoc;
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

    public String getE08() {
        return e08;
    }

    public void setE08(String e08) {
        this.e08 = e08;
    }

    public String getE09() {
        return e09;
    }

    public void setE09(String e09) {
        this.e09 = e09;
    }

    public String getCvevial() {
        return cvevial;
    }

    public void setCvevial(String cvevial) {
        this.cvevial = cvevial;
    }

    public BigInteger getIdFrente() {
        return idFrente;
    }

    public void setIdFrente(BigInteger idFrente) {
        this.idFrente = idFrente;
    }

    public String getNomvial() {
        return nomvial;
    }

    public void setNomvial(String nomvial) {
        this.nomvial = nomvial;
    }

    public String getNumext() {
        return numext;
    }

    public void setNumext(String numext) {
        this.numext = numext;
    }

    public String getNumextalf() {
        return numextalf;
    }

    public void setNumextalf(String numextalf) {
        this.numextalf = numextalf;
    }

    public String getE12() {
        return e12;
    }

    public void setE12(String e12) {
        this.e12 = e12;
    }

    public String getNumint() {
        return numint;
    }

    public void setNumint(String numint) {
        this.numint = numint;
    }

    public String getNumintalf() {
        return numintalf;
    }

    public void setNumintalf(String numintalf) {
        this.numintalf = numintalf;
    }

    public String getE14() {
        return e14;
    }

    public void setE14(String e14) {
        this.e14 = e14;
    }

    public String getE14A() {
        return e14A;
    }

    public void setE14A(String e14A) {
        this.e14A = e14A;
    }

    public String getE10A() {
        return e10A;
    }

    public void setE10A(String e10A) {
        this.e10A = e10A;
    }

    public String getE10B() {
        return e10B;
    }

    public void setE10B(String e10B) {
        this.e10B = e10B;
    }

    public String getE10C() {
        return e10C;
    }

    public void setE10C(String e10C) {
        this.e10C = e10C;
    }

    public String getDescrubic() {
        return descrubic;
    }

    public void setDescrubic(String descrubic) {
        this.descrubic = descrubic;
    }

    public String getTheGeomWkt() {
        return theGeomWkt;
    }

    public void setTheGeomWkt(String theGeomWkt) {
        this.theGeomWkt = theGeomWkt;
    }

    public String getTheGeom() {
        return theGeom;
    }

    public void setTheGeom(String theGeom) {
        this.theGeom = theGeom;
    }

    public String getTheGeomMerc() {
        return theGeomMerc;
    }

    public void setTheGeomMerc(String theGeomMerc) {
        this.theGeomMerc = theGeomMerc;
    }

    public BigDecimal getCoordX() {
        return coordX;
    }

    public void setCoordX(BigDecimal coordX) {
        this.coordX = coordX;
    }

    public BigDecimal getCoordY() {
        return coordY;
    }

    public void setCoordY(BigDecimal coordY) {
        this.coordY = coordY;
    }

    public String getE19() {
        return e19;
    }

    public void setE19(String e19) {
        this.e19 = e19;
    }

    public String getTipoE19() {
        return tipoE19;
    }

    public void setTipoE19(String tipoE19) {
        this.tipoE19 = tipoE19;
    }

    public String getTipoarea() {
        return tipoarea;
    }

    public void setTipoarea(String tipoarea) {
        this.tipoarea = tipoarea;
    }

    public String getE23() {
        return e23;
    }

    public void setE23(String e23) {
        this.e23 = e23;
    }

    public String getCveOri() {
        return cveOri;
    }

    public void setCveOri(String cveOri) {
        this.cveOri = cveOri;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getVermcc() {
        return vermcc;
    }

    public void setVermcc(String vermcc) {
        this.vermcc = vermcc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRegistroFecha() {
        return registroFecha;
    }

    public void setRegistroFecha(String registroFecha) {
        this.registroFecha = registroFecha;
    }

    public String getRegistroHora() {
        return registroHora;
    }

    public void setRegistroHora(String registroHora) {
        this.registroHora = registroHora;
    }

    public Short getModCat() {
        return modCat;
    }

    public void setModCat(Short modCat) {
        this.modCat = modCat;
    }

    public Short getEstatus() {
        return estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public Long getTipoEdf() {
        return tipoEdf;
    }

    public void setTipoEdf(Long tipoEdf) {
        this.tipoEdf = tipoEdf;
    }

    public String getBajaFecha() {
        return bajaFecha;
    }

    public void setBajaFecha(String bajaFecha) {
        this.bajaFecha = bajaFecha;
    }

    public String getBajaHora() {
        return bajaHora;
    }

    public void setBajaHora(String bajaHora) {
        this.bajaHora = bajaHora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigenMc() {
        return origenMc;
    }

    public void setOrigenMc(String origenMc) {
        this.origenMc = origenMc;
    }

    public String getE10E() {
        return e10E;
    }

    public void setE10E(String e10E) {
        this.e10E = e10E;
    }

    public String getE12p() {
        return e12p;
    }

    public void setE12p(String e12p) {
        this.e12p = e12p;
    }

    public String getE20() {
        return e20;
    }

    public void setE20(String e20) {
        this.e20 = e20;
    }

    public Short getBaja() {
        return baja;
    }

    public void setBaja(Short baja) {
        this.baja = baja;
    }

    public String getCvegeo2016() {
        return cvegeo2016;
    }

    public void setCvegeo2016(String cvegeo2016) {
        this.cvegeo2016 = cvegeo2016;
    }

    public String getC154() {
        return c154;
    }

    public void setC154(String c154) {
        this.c154 = c154;
    }

    public Boolean getActualizaUe() {
        return actualizaUe;
    }

    public void setActualizaUe(Boolean actualizaUe) {
        this.actualizaUe = actualizaUe;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public CatAsentamientosHumanos getTipoE14() {
        return tipoE14;
    }

    public void setTipoE14(CatAsentamientosHumanos tipoE14) {
        this.tipoE14 = tipoE14;
    }

    public CatCe getCveCe() {
        return cveCe;
    }

    public void setCveCe(CatCe cveCe) {
        this.cveCe = cveCe;
    }

    public CatTipovialidad getTipoE10C() {
        return tipoE10C;
    }

    public void setTipoE10C(CatTipovialidad tipoE10C) {
        this.tipoE10C = tipoE10C;
    }

    public CatTipovialidad getTipoE10B() {
        return tipoE10B;
    }

    public void setTipoE10B(CatTipovialidad tipoE10B) {
        this.tipoE10B = tipoE10B;
    }

    public CatTipovialidad getTipoE10A() {
        return tipoE10A;
    }

    public void setTipoE10A(CatTipovialidad tipoE10A) {
        this.tipoE10A = tipoE10A;
    }

    public CatTipovialidad getTipoE10() {
        return tipoE10;
    }

    public void setTipoE10(CatTipovialidad tipoE10) {
        this.tipoE10 = tipoE10;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUe != null ? idUe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdInmuebles)) {
            return false;
        }
        TdInmuebles other = (TdInmuebles) object;
        if ((this.idUe == null && other.idUe != null) || (this.idUe != null && !this.idUe.equals(other.idUe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TdInmuebles[ idUe=" + idUe + " ]";
    }
    
}
