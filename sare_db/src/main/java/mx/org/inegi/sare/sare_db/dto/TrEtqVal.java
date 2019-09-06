/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "TR_ETQ_VAL",schema = "CE2019_MASRENCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrEtqVal.findAll", query = "SELECT t FROM TrEtqVal t"),
    @NamedQuery(name = "TrEtqVal.findByIdUe", query = "SELECT t FROM TrEtqVal t WHERE t.idUe = :idUe"),
    @NamedQuery(name = "TrEtqVal.findByE48", query = "SELECT t FROM TrEtqVal t WHERE t.e48 = :e48"),
    @NamedQuery(name = "TrEtqVal.findByE08", query = "SELECT t FROM TrEtqVal t WHERE t.e08 = :e08"),
    @NamedQuery(name = "TrEtqVal.findByE09", query = "SELECT t FROM TrEtqVal t WHERE t.e09 = :e09"),
    @NamedQuery(name = "TrEtqVal.findBySectorEco", query = "SELECT t FROM TrEtqVal t WHERE t.sectorEco = :sectorEco"),
    @NamedQuery(name = "TrEtqVal.findByCe", query = "SELECT t FROM TrEtqVal t WHERE t.ce = :ce"),
    @NamedQuery(name = "TrEtqVal.findByE47", query = "SELECT t FROM TrEtqVal t WHERE t.e47 = :e47"),
    @NamedQuery(name = "TrEtqVal.findByE09r", query = "SELECT t FROM TrEtqVal t WHERE t.e09r = :e09r"),
    @NamedQuery(name = "TrEtqVal.findByE09r01", query = "SELECT t FROM TrEtqVal t WHERE t.e09r01 = :e09r01"),
    @NamedQuery(name = "TrEtqVal.findByE09z", query = "SELECT t FROM TrEtqVal t WHERE t.e09z = :e09z"),
    @NamedQuery(name = "TrEtqVal.findByG132", query = "SELECT t FROM TrEtqVal t WHERE t.g132 = :g132"),
    @NamedQuery(name = "TrEtqVal.findByG102", query = "SELECT t FROM TrEtqVal t WHERE t.g102 = :g102"),
    @NamedQuery(name = "TrEtqVal.findByG142a", query = "SELECT t FROM TrEtqVal t WHERE t.g142a = :g142a"),
    @NamedQuery(name = "TrEtqVal.findByTe10", query = "SELECT t FROM TrEtqVal t WHERE t.te10 = :te10"),
    @NamedQuery(name = "TrEtqVal.findByE10", query = "SELECT t FROM TrEtqVal t WHERE t.e10 = :e10"),
    @NamedQuery(name = "TrEtqVal.findByE11", query = "SELECT t FROM TrEtqVal t WHERE t.e11 = :e11"),
    @NamedQuery(name = "TrEtqVal.findByE11a", query = "SELECT t FROM TrEtqVal t WHERE t.e11a = :e11a"),
    @NamedQuery(name = "TrEtqVal.findByE13", query = "SELECT t FROM TrEtqVal t WHERE t.e13 = :e13"),
    @NamedQuery(name = "TrEtqVal.findByE13a", query = "SELECT t FROM TrEtqVal t WHERE t.e13a = :e13a"),
    @NamedQuery(name = "TrEtqVal.findByE12", query = "SELECT t FROM TrEtqVal t WHERE t.e12 = :e12"),
    @NamedQuery(name = "TrEtqVal.findByTe12p", query = "SELECT t FROM TrEtqVal t WHERE t.te12p = :te12p"),
    @NamedQuery(name = "TrEtqVal.findByTe14", query = "SELECT t FROM TrEtqVal t WHERE t.te14 = :te14"),
    @NamedQuery(name = "TrEtqVal.findByE14", query = "SELECT t FROM TrEtqVal t WHERE t.e14 = :e14"),
    @NamedQuery(name = "TrEtqVal.findByE14a", query = "SELECT t FROM TrEtqVal t WHERE t.e14a = :e14a"),
    @NamedQuery(name = "TrEtqVal.findByE22", query = "SELECT t FROM TrEtqVal t WHERE t.e22 = :e22"),
    @NamedQuery(name = "TrEtqVal.findByE21", query = "SELECT t FROM TrEtqVal t WHERE t.e21 = :e21"),
    @NamedQuery(name = "TrEtqVal.findByE25", query = "SELECT t FROM TrEtqVal t WHERE t.e25 = :e25"),
    @NamedQuery(name = "TrEtqVal.findByE26", query = "SELECT t FROM TrEtqVal t WHERE t.e26 = :e26"),
    @NamedQuery(name = "TrEtqVal.findByE15f", query = "SELECT t FROM TrEtqVal t WHERE t.e15f = :e15f"),
    @NamedQuery(name = "TrEtqVal.findByE15c", query = "SELECT t FROM TrEtqVal t WHERE t.e15c = :e15c"),
    @NamedQuery(name = "TrEtqVal.findByTe19", query = "SELECT t FROM TrEtqVal t WHERE t.te19 = :te19"),
    @NamedQuery(name = "TrEtqVal.findByE19", query = "SELECT t FROM TrEtqVal t WHERE t.e19 = :e19"),
    @NamedQuery(name = "TrEtqVal.findByE20", query = "SELECT t FROM TrEtqVal t WHERE t.e20 = :e20"),
    @NamedQuery(name = "TrEtqVal.findByTe10a1", query = "SELECT t FROM TrEtqVal t WHERE t.te10a1 = :te10a1"),
    @NamedQuery(name = "TrEtqVal.findByE10A", query = "SELECT t FROM TrEtqVal t WHERE t.e10A = :e10A"),
    @NamedQuery(name = "TrEtqVal.findByTe10b1", query = "SELECT t FROM TrEtqVal t WHERE t.te10b1 = :te10b1"),
    @NamedQuery(name = "TrEtqVal.findByE10B", query = "SELECT t FROM TrEtqVal t WHERE t.e10B = :e10B"),
    @NamedQuery(name = "TrEtqVal.findByTe10c1", query = "SELECT t FROM TrEtqVal t WHERE t.te10c1 = :te10c1"),
    @NamedQuery(name = "TrEtqVal.findByE10C", query = "SELECT t FROM TrEtqVal t WHERE t.e10C = :e10C"),
    @NamedQuery(name = "TrEtqVal.findByE16", query = "SELECT t FROM TrEtqVal t WHERE t.e16 = :e16"),
    @NamedQuery(name = "TrEtqVal.findByE10za", query = "SELECT t FROM TrEtqVal t WHERE t.e10za = :e10za"),
    @NamedQuery(name = "TrEtqVal.findByE03nz", query = "SELECT t FROM TrEtqVal t WHERE t.e03nz = :e03nz"),
    @NamedQuery(name = "TrEtqVal.findByE04nz", query = "SELECT t FROM TrEtqVal t WHERE t.e04nz = :e04nz"),
    @NamedQuery(name = "TrEtqVal.findByE15zf", query = "SELECT t FROM TrEtqVal t WHERE t.e15zf = :e15zf"),
    @NamedQuery(name = "TrEtqVal.findByE15zc", query = "SELECT t FROM TrEtqVal t WHERE t.e15zc = :e15zc"),
    @NamedQuery(name = "TrEtqVal.findByTipoE11z", query = "SELECT t FROM TrEtqVal t WHERE t.tipoE11z = :tipoE11z"),
    @NamedQuery(name = "TrEtqVal.findByTe11z", query = "SELECT t FROM TrEtqVal t WHERE t.te11z = :te11z"),
    @NamedQuery(name = "TrEtqVal.findByE11z", query = "SELECT t FROM TrEtqVal t WHERE t.e11z = :e11z"),
    @NamedQuery(name = "TrEtqVal.findByE12z", query = "SELECT t FROM TrEtqVal t WHERE t.e12z = :e12z"),
    @NamedQuery(name = "TrEtqVal.findByE12za", query = "SELECT t FROM TrEtqVal t WHERE t.e12za = :e12za"),
    @NamedQuery(name = "TrEtqVal.findByE13z", query = "SELECT t FROM TrEtqVal t WHERE t.e13z = :e13z"),
    @NamedQuery(name = "TrEtqVal.findByE13za", query = "SELECT t FROM TrEtqVal t WHERE t.e13za = :e13za"),
    @NamedQuery(name = "TrEtqVal.findByE14z", query = "SELECT t FROM TrEtqVal t WHERE t.e14z = :e14z"),
    @NamedQuery(name = "TrEtqVal.findByG122a", query = "SELECT t FROM TrEtqVal t WHERE t.g122a = :g122a"),
    @NamedQuery(name = "TrEtqVal.findByG101", query = "SELECT t FROM TrEtqVal t WHERE t.g101 = :g101"),
    @NamedQuery(name = "TrEtqVal.findByE23a", query = "SELECT t FROM TrEtqVal t WHERE t.e23a = :e23a"),
    @NamedQuery(name = "TrEtqVal.findByD108", query = "SELECT t FROM TrEtqVal t WHERE t.d108 = :d108"),
    @NamedQuery(name = "TrEtqVal.findByD112", query = "SELECT t FROM TrEtqVal t WHERE t.d112 = :d112"),
    @NamedQuery(name = "TrEtqVal.findByD117", query = "SELECT t FROM TrEtqVal t WHERE t.d117 = :d117"),
    @NamedQuery(name = "TrEtqVal.findByE14p", query = "SELECT t FROM TrEtqVal t WHERE t.e14p = :e14p"),
    @NamedQuery(name = "TrEtqVal.findByE15pf", query = "SELECT t FROM TrEtqVal t WHERE t.e15pf = :e15pf"),
    @NamedQuery(name = "TrEtqVal.findByE15pc", query = "SELECT t FROM TrEtqVal t WHERE t.e15pc = :e15pc"),
    @NamedQuery(name = "TrEtqVal.findByE03nm", query = "SELECT t FROM TrEtqVal t WHERE t.e03nm = :e03nm"),
    @NamedQuery(name = "TrEtqVal.findByE04nm", query = "SELECT t FROM TrEtqVal t WHERE t.e04nm = :e04nm"),
    @NamedQuery(name = "TrEtqVal.findByTipoE05nm", query = "SELECT t FROM TrEtqVal t WHERE t.tipoE05nm = :tipoE05nm"),
    @NamedQuery(name = "TrEtqVal.findByTe05nm", query = "SELECT t FROM TrEtqVal t WHERE t.te05nm = :te05nm"),
    @NamedQuery(name = "TrEtqVal.findByE05nm", query = "SELECT t FROM TrEtqVal t WHERE t.e05nm = :e05nm"),
    @NamedQuery(name = "TrEtqVal.findByE06nm", query = "SELECT t FROM TrEtqVal t WHERE t.e06nm = :e06nm"),
    @NamedQuery(name = "TrEtqVal.findByE06nma", query = "SELECT t FROM TrEtqVal t WHERE t.e06nma = :e06nma"),
    @NamedQuery(name = "TrEtqVal.findByE07nm", query = "SELECT t FROM TrEtqVal t WHERE t.e07nm = :e07nm"),
    @NamedQuery(name = "TrEtqVal.findByE07nma", query = "SELECT t FROM TrEtqVal t WHERE t.e07nma = :e07nma"),
    @NamedQuery(name = "TrEtqVal.findByE08nm", query = "SELECT t FROM TrEtqVal t WHERE t.e08nm = :e08nm"),
    @NamedQuery(name = "TrEtqVal.findByE15mf", query = "SELECT t FROM TrEtqVal t WHERE t.e15mf = :e15mf"),
    @NamedQuery(name = "TrEtqVal.findByE15mc", query = "SELECT t FROM TrEtqVal t WHERE t.e15mc = :e15mc"),
    @NamedQuery(name = "TrEtqVal.findByD100a", query = "SELECT t FROM TrEtqVal t WHERE t.d100a = :d100a"),
    @NamedQuery(name = "TrEtqVal.findByD113a", query = "SELECT t FROM TrEtqVal t WHERE t.d113a = :d113a"),
    @NamedQuery(name = "TrEtqVal.findByE24", query = "SELECT t FROM TrEtqVal t WHERE t.e24 = :e24"),
    @NamedQuery(name = "TrEtqVal.findByE17d", query = "SELECT t FROM TrEtqVal t WHERE t.e17d = :e17d"),
    @NamedQuery(name = "TrEtqVal.findByAlta", query = "SELECT t FROM TrEtqVal t WHERE t.alta = :alta"),
    @NamedQuery(name = "TrEtqVal.findByStReconsulta", query = "SELECT t FROM TrEtqVal t WHERE t.stReconsulta = :stReconsulta"),
    @NamedQuery(name = "TrEtqVal.findByD111a", query = "SELECT t FROM TrEtqVal t WHERE t.d111a = :d111a"),
    @NamedQuery(name = "TrEtqVal.findByD116a", query = "SELECT t FROM TrEtqVal t WHERE t.d116a = :d116a"),
    @NamedQuery(name = "TrEtqVal.findByD101", query = "SELECT t FROM TrEtqVal t WHERE t.d101 = :d101"),
    @NamedQuery(name = "TrEtqVal.findByG111a", query = "SELECT t FROM TrEtqVal t WHERE t.g111a = :g111a"),
    @NamedQuery(name = "TrEtqVal.findByH001a", query = "SELECT t FROM TrEtqVal t WHERE t.h001a = :h001a"),
    @NamedQuery(name = "TrEtqVal.findByM000a", query = "SELECT t FROM TrEtqVal t WHERE t.m000a = :m000a"),
    @NamedQuery(name = "TrEtqVal.findByC154", query = "SELECT t FROM TrEtqVal t WHERE t.c154 = :c154"),
    @NamedQuery(name = "TrEtqVal.findByStLimpieza", query = "SELECT t FROM TrEtqVal t WHERE t.stLimpieza = :stLimpieza"),
    @NamedQuery(name = "TrEtqVal.findByStValida", query = "SELECT t FROM TrEtqVal t WHERE t.stValida = :stValida"),
    @NamedQuery(name = "TrEtqVal.findByStBajaVal", query = "SELECT t FROM TrEtqVal t WHERE t.stBajaVal = :stBajaVal"),
    @NamedQuery(name = "TrEtqVal.findByStValRenem", query = "SELECT t FROM TrEtqVal t WHERE t.stValRenem = :stValRenem"),
    @NamedQuery(name = "TrEtqVal.findByStCarEge", query = "SELECT t FROM TrEtqVal t WHERE t.stCarEge = :stCarEge"),
    @NamedQuery(name = "TrEtqVal.findByStSepara", query = "SELECT t FROM TrEtqVal t WHERE t.stSepara = :stSepara"),
    @NamedQuery(name = "TrEtqVal.findByStConsEti", query = "SELECT t FROM TrEtqVal t WHERE t.stConsEti = :stConsEti"),
    @NamedQuery(name = "TrEtqVal.findByFechaDescarga", query = "SELECT t FROM TrEtqVal t WHERE t.fechaDescarga = :fechaDescarga"),
    @NamedQuery(name = "TrEtqVal.findByX", query = "SELECT t FROM TrEtqVal t WHERE t.x = :x"),
    @NamedQuery(name = "TrEtqVal.findByY", query = "SELECT t FROM TrEtqVal t WHERE t.y = :y"),
    @NamedQuery(name = "TrEtqVal.findByStRn", query = "SELECT t FROM TrEtqVal t WHERE t.stRn = :stRn"),
    @NamedQuery(name = "TrEtqVal.findByIdCuestionario", query = "SELECT t FROM TrEtqVal t WHERE t.idCuestionario = :idCuestionario"),
    @NamedQuery(name = "TrEtqVal.findByIdGrupo", query = "SELECT t FROM TrEtqVal t WHERE t.idCuestionario = :idCuestionario")})
public class TrEtqVal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UE")
    private Long idUe;
    @Basic(optional = false)
    @Column(name = "E48")
    private String e48;
    @Column(name = "E08")
    private String e08;
    @Column(name = "E09")
    private String e09;
    @Column(name = "SECTOR_ECO")
    private String sectorEco;
    @Column(name = "CE")
    private Short ce;
    @Column(name = "E47")
    private String e47;
    @Column(name = "E09R")
    private Short e09r;
    @Column(name = "E09R01")
    private String e09r01;
    @Column(name = "E09Z")
    private Short e09z;
    @Column(name = "G132")
    private Short g132;
    @Column(name = "G102")
    private String g102;
    @Column(name = "G142A")
    private Short g142a;
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
    @Column(name = "E12")
    private String e12;
    @Column(name = "TE12P")
    private String te12p;
    @Column(name = "TE14")
    private String te14;
    @Column(name = "E14")
    private String e14;
    @Column(name = "E14A")
    private String e14a;
    @Column(name = "E22")
    private String e22;
    @Column(name = "E21")
    private String e21;
    @Column(name = "E25")
    private String e25;
    @Column(name = "E26")
    private String e26;
    @Column(name = "E15F")
    private String e15f;
    @Column(name = "E15C")
    private String e15c;
    @Column(name = "TE19")
    private String te19;
    @Column(name = "E19")
    private String e19;
    @Column(name = "E20")
    private String e20;
    @Column(name = "TE10A1")
    private String te10a1;
    @Column(name = "E10_A")
    private String e10A;
    @Column(name = "TE10B1")
    private String te10b1;
    @Column(name = "E10_B")
    private String e10B;
    @Column(name = "TE10C1")
    private String te10c1;
    @Column(name = "E10_C")
    private String e10C;
    @Column(name = "E16")
    private String e16;
    @Column(name = "E10ZA")
    private Short e10za;
    @Column(name = "E03NZ")
    private String e03nz;
    @Column(name = "E04NZ")
    private String e04nz;
    @Column(name = "E15ZF")
    private String e15zf;
    @Column(name = "E15ZC")
    private String e15zc;
    @Column(name = "TIPO_E11Z")
    private Short tipoE11z;
    @Column(name = "TE11Z")
    private String te11z;
    @Column(name = "E11Z")
    private String e11z;
    @Column(name = "E12Z")
    private Integer e12z;
    @Column(name = "E12ZA")
    private String e12za;
    @Column(name = "E13Z")
    private Integer e13z;
    @Column(name = "E13ZA")
    private String e13za;
    @Column(name = "E14Z")
    private String e14z;
    @Column(name = "G122A")
    private Short g122a;
    @Column(name = "G101")
    private String g101;
    @Column(name = "E23A")
    private String e23a;
    @Column(name = "D108")
    private Short d108;
    @Column(name = "D112")
    private Long d112;
    @Column(name = "D117")
    private Long d117;
    @Column(name = "E14P")
    private String e14p;
    @Column(name = "E15PF")
    private String e15pf;
    @Column(name = "E15PC")
    private String e15pc;
    @Column(name = "E03NM")
    private String e03nm;
    @Column(name = "E04NM")
    private String e04nm;
    @Column(name = "TIPO_E05NM")
    private Short tipoE05nm;
    @Column(name = "TE05NM")
    private String te05nm;
    @Column(name = "E05NM")
    private String e05nm;
    @Column(name = "E06NM")
    private Integer e06nm;
    @Column(name = "E06NMA")
    private String e06nma;
    @Column(name = "E07NM")
    private Integer e07nm;
    @Column(name = "E07NMA")
    private String e07nma;
    @Column(name = "E08NM")
    private String e08nm;
    @Column(name = "E15MF")
    private String e15mf;
    @Column(name = "E15MC")
    private String e15mc;
    @Column(name = "D100A")
    private Short d100a;
    @Column(name = "D113A")
    private Short d113a;
    @Column(name = "E24")
    private String e24;
    @Column(name = "E17D")
    private Short e17d;
    @Column(name = "ALTA")
    private Short alta;
    @Column(name = "ST_RECONSULTA")
    private Short stReconsulta;
    @Column(name = "D111A")
    private Short d111a;
    @Column(name = "D116A")
    private Short d116a;
    @Column(name = "D101")
    private String d101;
    @Column(name = "G111A")
    private Short g111a;
    @Column(name = "H001A")
    private Integer h001a;
    @Column(name = "M000A")
    private Long m000a;
    @Column(name = "C154")
    private String c154;
    @Column(name = "ST_LIMPIEZA")
    private Short stLimpieza;
    @Column(name = "ST_VALIDA")
    private Short stValida;
    @Column(name = "ST_BAJA_VAL")
    private Short stBajaVal;
    @Column(name = "ST_VAL_RENEM")
    private Short stValRenem;
    @Column(name = "ST_CAR_EGE")
    private String stCarEge;
    @Column(name = "ST_SEPARA")
    private Short stSepara;
    @Column(name = "ST_CONS_ETI")
    private String stConsEti;
    @Column(name = "FECHA_DESCARGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDescarga;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "X")
    private BigDecimal x;
    @Column(name = "Y")
    private BigDecimal y;
    @Column(name = "ST_RN")
    private Short stRn;
    @Column(name = "ID_CUESTIONARIO")
    private Long idCuestionario;
//    @Column(name = "ID_GRUPO")
//    private Long idGrupo;
//    @OneToMany(mappedBy = "idUeAnt")
//    private Collection<TrPredios> trPrediosCollection;
//    @OneToMany(mappedBy = "idUe")
//    private Collection<TrPredios> trPrediosCollection1;
    @JoinColumn(name = "TIPO_E14", referencedColumnName = "CVE_TIPO_ASEN")
    @ManyToOne
    //@JsonIgnore
    private TcAsentamiento tipoE14;
    @JoinColumn(name = "E90", referencedColumnName = "ID_E90")
    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonIgnore
    private TcE90TipoEstable e90;
    @JoinColumns({
        @JoinColumn(name = "E03", referencedColumnName = "CVE_ENT"),
        @JoinColumn(name = "E04", referencedColumnName = "CVE_MUN"),
        @JoinColumn(name = "E05", referencedColumnName = "CVE_LOC"),
        @JoinColumn(name = "E06", referencedColumnName = "CVE_AGEB"),
        @JoinColumn(name = "E07", referencedColumnName = "CVE_MZA")})
    @ManyToOne(optional = false)
    //@JsonIgnore
    private TcManzanas tcManzanas;
    @JoinColumn(name = "TIPO_E12P", referencedColumnName = "TIPO_E12P")
    @ManyToOne
    //@JsonIgnore
    private TcPisos tipoE12p;
    @JoinColumn(name = "E17", referencedColumnName = "E17")
    @ManyToOne
    //@JsonIgnore
    private TcScian e17;
    @JoinColumn(name = "TIPO_E19", referencedColumnName = "TIPO_E19")
    @ManyToOne
    //@JsonIgnore
    private TcTipoConglomerado tipoE19;
    @JoinColumn(name = "TIPO_E10_A", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    //@JsonIgnore
    private TcVialidades tipoE10A;
    @JoinColumn(name = "TIPO_E10_C", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    //@JsonIgnore
    private TcVialidades tipoE10C;
    @JoinColumn(name = "TIPO_E10", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    //@JsonIgnore
    private TcVialidades tipoE10;
    @JoinColumn(name = "TIPO_E10_B", referencedColumnName = "CVE_VIALIDAD")
    @ManyToOne
    //@JsonIgnore
    private TcVialidades tipoE10B;
    @JoinColumn(name = "ID_CONGLOMERADO", referencedColumnName = "ID_CONGLOMERADO")
    @ManyToOne
    //@JsonIgnore
    private TrConglomerado idConglomerado;
//    @OneToMany(mappedBy = "idUeAnt")
//    private Collection<TrInmuebles> trInmueblesCollection;
//    @OneToMany(mappedBy = "idUe")
//    private Collection<TrInmuebles> trInmueblesCollection1;

    public TrEtqVal() {
    }

    public TrEtqVal(Long idUe) {
        this.idUe = idUe;
    }

    public TrEtqVal(Long idUe, String e48) {
        this.idUe = idUe;
        this.e48 = e48;
    }

    public Long getIdUe() {
        return idUe;
    }

    public void setIdUe(Long idUe) {
        this.idUe = idUe;
    }

    public String getE48() {
        return e48;
    }

    public void setE48(String e48) {
        this.e48 = e48;
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

    public String getSectorEco() {
        return sectorEco;
    }

    public void setSectorEco(String sectorEco) {
        this.sectorEco = sectorEco;
    }

    public Short getCe() {
        return ce;
    }

    public void setCe(Short ce) {
        this.ce = ce;
    }

    public String getE47() {
        return e47;
    }

    public void setE47(String e47) {
        this.e47 = e47;
    }

    public Short getE09r() {
        return e09r;
    }

    public void setE09r(Short e09r) {
        this.e09r = e09r;
    }

    public String getE09r01() {
        return e09r01;
    }

    public void setE09r01(String e09r01) {
        this.e09r01 = e09r01;
    }

    public Short getE09z() {
        return e09z;
    }

    public void setE09z(Short e09z) {
        this.e09z = e09z;
    }

    public Short getG132() {
        return g132;
    }

    public void setG132(Short g132) {
        this.g132 = g132;
    }

    public String getG102() {
        return g102;
    }

    public void setG102(String g102) {
        this.g102 = g102;
    }

    public Short getG142a() {
        return g142a;
    }

    public void setG142a(Short g142a) {
        this.g142a = g142a;
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

    public String getE12() {
        return e12;
    }

    public void setE12(String e12) {
        this.e12 = e12;
    }

    public String getTe12p() {
        return te12p;
    }

    public void setTe12p(String te12p) {
        this.te12p = te12p;
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

    public String getE14a() {
        return e14a;
    }

    public void setE14a(String e14a) {
        this.e14a = e14a;
    }

    public String getE22() {
        return e22;
    }

    public void setE22(String e22) {
        this.e22 = e22;
    }

    public String getE21() {
        return e21;
    }

    public void setE21(String e21) {
        this.e21 = e21;
    }

    public String getE25() {
        return e25;
    }

    public void setE25(String e25) {
        this.e25 = e25;
    }

    public String getE26() {
        return e26;
    }

    public void setE26(String e26) {
        this.e26 = e26;
    }

    public String getE15f() {
        return e15f;
    }

    public void setE15f(String e15f) {
        this.e15f = e15f;
    }

    public String getE15c() {
        return e15c;
    }

    public void setE15c(String e15c) {
        this.e15c = e15c;
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

    public String getE20() {
        return e20;
    }

    public void setE20(String e20) {
        this.e20 = e20;
    }

    public String getTe10a1() {
        return te10a1;
    }

    public void setTe10a1(String te10a1) {
        this.te10a1 = te10a1;
    }

    public String getE10A() {
        return e10A;
    }

    public void setE10A(String e10A) {
        this.e10A = e10A;
    }

    public String getTe10b1() {
        return te10b1;
    }

    public void setTe10b1(String te10b1) {
        this.te10b1 = te10b1;
    }

    public String getE10B() {
        return e10B;
    }

    public void setE10B(String e10B) {
        this.e10B = e10B;
    }

    public String getTe10c1() {
        return te10c1;
    }

    public void setTe10c1(String te10c1) {
        this.te10c1 = te10c1;
    }

    public String getE10C() {
        return e10C;
    }

    public void setE10C(String e10C) {
        this.e10C = e10C;
    }

    public String getE16() {
        return e16;
    }

    public void setE16(String e16) {
        this.e16 = e16;
    }

    public Short getE10za() {
        return e10za;
    }

    public void setE10za(Short e10za) {
        this.e10za = e10za;
    }

    public String getE03nz() {
        return e03nz;
    }

    public void setE03nz(String e03nz) {
        this.e03nz = e03nz;
    }

    public String getE04nz() {
        return e04nz;
    }

    public void setE04nz(String e04nz) {
        this.e04nz = e04nz;
    }

    public String getE15zf() {
        return e15zf;
    }

    public void setE15zf(String e15zf) {
        this.e15zf = e15zf;
    }

    public String getE15zc() {
        return e15zc;
    }

    public void setE15zc(String e15zc) {
        this.e15zc = e15zc;
    }

    public Short getTipoE11z() {
        return tipoE11z;
    }

    public void setTipoE11z(Short tipoE11z) {
        this.tipoE11z = tipoE11z;
    }

    public String getTe11z() {
        return te11z;
    }

    public void setTe11z(String te11z) {
        this.te11z = te11z;
    }

    public String getE11z() {
        return e11z;
    }

    public void setE11z(String e11z) {
        this.e11z = e11z;
    }

    public Integer getE12z() {
        return e12z;
    }

    public void setE12z(Integer e12z) {
        this.e12z = e12z;
    }

    public String getE12za() {
        return e12za;
    }

    public void setE12za(String e12za) {
        this.e12za = e12za;
    }

    public Integer getE13z() {
        return e13z;
    }

    public void setE13z(Integer e13z) {
        this.e13z = e13z;
    }

    public String getE13za() {
        return e13za;
    }

    public void setE13za(String e13za) {
        this.e13za = e13za;
    }

    public String getE14z() {
        return e14z;
    }

    public void setE14z(String e14z) {
        this.e14z = e14z;
    }

    public Short getG122a() {
        return g122a;
    }

    public void setG122a(Short g122a) {
        this.g122a = g122a;
    }

    public String getG101() {
        return g101;
    }

    public void setG101(String g101) {
        this.g101 = g101;
    }

    public String getE23a() {
        return e23a;
    }

    public void setE23a(String e23a) {
        this.e23a = e23a;
    }

    public Short getD108() {
        return d108;
    }

    public void setD108(Short d108) {
        this.d108 = d108;
    }

    public Long getD112() {
        return d112;
    }

    public void setD112(Long d112) {
        this.d112 = d112;
    }

    public Long getD117() {
        return d117;
    }

    public void setD117(Long d117) {
        this.d117 = d117;
    }

    public String getE14p() {
        return e14p;
    }

    public void setE14p(String e14p) {
        this.e14p = e14p;
    }

    public String getE15pf() {
        return e15pf;
    }

    public void setE15pf(String e15pf) {
        this.e15pf = e15pf;
    }

    public String getE15pc() {
        return e15pc;
    }

    public void setE15pc(String e15pc) {
        this.e15pc = e15pc;
    }

    public String getE03nm() {
        return e03nm;
    }

    public void setE03nm(String e03nm) {
        this.e03nm = e03nm;
    }

    public String getE04nm() {
        return e04nm;
    }

    public void setE04nm(String e04nm) {
        this.e04nm = e04nm;
    }

    public Short getTipoE05nm() {
        return tipoE05nm;
    }

    public void setTipoE05nm(Short tipoE05nm) {
        this.tipoE05nm = tipoE05nm;
    }

    public String getTe05nm() {
        return te05nm;
    }

    public void setTe05nm(String te05nm) {
        this.te05nm = te05nm;
    }

    public String getE05nm() {
        return e05nm;
    }

    public void setE05nm(String e05nm) {
        this.e05nm = e05nm;
    }

    public Integer getE06nm() {
        return e06nm;
    }

    public void setE06nm(Integer e06nm) {
        this.e06nm = e06nm;
    }

    public String getE06nma() {
        return e06nma;
    }

    public void setE06nma(String e06nma) {
        this.e06nma = e06nma;
    }

    public Integer getE07nm() {
        return e07nm;
    }

    public void setE07nm(Integer e07nm) {
        this.e07nm = e07nm;
    }

    public String getE07nma() {
        return e07nma;
    }

    public void setE07nma(String e07nma) {
        this.e07nma = e07nma;
    }

    public String getE08nm() {
        return e08nm;
    }

    public void setE08nm(String e08nm) {
        this.e08nm = e08nm;
    }

    public String getE15mf() {
        return e15mf;
    }

    public void setE15mf(String e15mf) {
        this.e15mf = e15mf;
    }

    public String getE15mc() {
        return e15mc;
    }

    public void setE15mc(String e15mc) {
        this.e15mc = e15mc;
    }

    public Short getD100a() {
        return d100a;
    }

    public void setD100a(Short d100a) {
        this.d100a = d100a;
    }

    public Short getD113a() {
        return d113a;
    }

    public void setD113a(Short d113a) {
        this.d113a = d113a;
    }

    public String getE24() {
        return e24;
    }

    public void setE24(String e24) {
        this.e24 = e24;
    }

    public Short getE17d() {
        return e17d;
    }

    public void setE17d(Short e17d) {
        this.e17d = e17d;
    }

    public Short getAlta() {
        return alta;
    }

    public void setAlta(Short alta) {
        this.alta = alta;
    }

    public Short getStReconsulta() {
        return stReconsulta;
    }

    public void setStReconsulta(Short stReconsulta) {
        this.stReconsulta = stReconsulta;
    }

    public Short getD111a() {
        return d111a;
    }

    public void setD111a(Short d111a) {
        this.d111a = d111a;
    }

    public Short getD116a() {
        return d116a;
    }

    public void setD116a(Short d116a) {
        this.d116a = d116a;
    }

    public String getD101() {
        return d101;
    }

    public void setD101(String d101) {
        this.d101 = d101;
    }

    public Short getG111a() {
        return g111a;
    }

    public void setG111a(Short g111a) {
        this.g111a = g111a;
    }

    public Integer getH001a() {
        return h001a;
    }

    public void setH001a(Integer h001a) {
        this.h001a = h001a;
    }

    public Long getM000a() {
        return m000a;
    }

    public void setM000a(Long m000a) {
        this.m000a = m000a;
    }

    public String getC154() {
        return c154;
    }

    public void setC154(String c154) {
        this.c154 = c154;
    }

    public Short getStLimpieza() {
        return stLimpieza;
    }

    public void setStLimpieza(Short stLimpieza) {
        this.stLimpieza = stLimpieza;
    }

    public Short getStValida() {
        return stValida;
    }

    public void setStValida(Short stValida) {
        this.stValida = stValida;
    }

    public Short getStBajaVal() {
        return stBajaVal;
    }

    public void setStBajaVal(Short stBajaVal) {
        this.stBajaVal = stBajaVal;
    }

    public Short getStValRenem() {
        return stValRenem;
    }

    public void setStValRenem(Short stValRenem) {
        this.stValRenem = stValRenem;
    }

    public String getStCarEge() {
        return stCarEge;
    }

    public void setStCarEge(String stCarEge) {
        this.stCarEge = stCarEge;
    }

    public Short getStSepara() {
        return stSepara;
    }

    public void setStSepara(Short stSepara) {
        this.stSepara = stSepara;
    }

    public String getStConsEti() {
        return stConsEti;
    }

    public void setStConsEti(String stConsEti) {
        this.stConsEti = stConsEti;
    }

    public Date getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(Date fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
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

    public Short getStRn() {
        return stRn;
    }

    public void setStRn(Short stRn) {
        this.stRn = stRn;
    }

    public Long getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Long idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

//    public Long getIdGrupo() {
//        return idGrupo;
//    }
//
//    public void setIdGrupo(Long idGrupo) {
//        this.idGrupo = idGrupo;
//    }

//    @XmlTransient
//    public Collection<TrPredios> getTrPrediosCollection() {
//        return trPrediosCollection;
//    }
//
//    public void setTrPrediosCollection(Collection<TrPredios> trPrediosCollection) {
//        this.trPrediosCollection = trPrediosCollection;
//    }
//
//    @XmlTransient
//    public Collection<TrPredios> getTrPrediosCollection1() {
//        return trPrediosCollection1;
//    }
//
//    public void setTrPrediosCollection1(Collection<TrPredios> trPrediosCollection1) {
//        this.trPrediosCollection1 = trPrediosCollection1;
//    }

    public TcAsentamiento getTipoE14() {
        return tipoE14;
    }

    public void setTipoE14(TcAsentamiento tipoE14) {
        this.tipoE14 = tipoE14;
    }

    public TcE90TipoEstable getE90() {
        return e90;
    }

    public void setE90(TcE90TipoEstable e90) {
        this.e90 = e90;
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

    public TcScian getE17() {
        return e17;
    }

    public void setE17(TcScian e17) {
        this.e17 = e17;
    }

    public TcTipoConglomerado getTipoE19() {
        return tipoE19;
    }

    public void setTipoE19(TcTipoConglomerado tipoE19) {
        this.tipoE19 = tipoE19;
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

    public TcVialidades getTipoE10() {
        return tipoE10;
    }

    public void setTipoE10(TcVialidades tipoE10) {
        this.tipoE10 = tipoE10;
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

//    @XmlTransient
//    public Collection<TrInmuebles> getTrInmueblesCollection() {
//        return trInmueblesCollection;
//    }
//
//    public void setTrInmueblesCollection(Collection<TrInmuebles> trInmueblesCollection) {
//        this.trInmueblesCollection = trInmueblesCollection;
//    }
//
//    @XmlTransient
//    public Collection<TrInmuebles> getTrInmueblesCollection1() {
//        return trInmueblesCollection1;
//    }
//
//    public void setTrInmueblesCollection1(Collection<TrInmuebles> trInmueblesCollection1) {
//        this.trInmueblesCollection1 = trInmueblesCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUe != null ? idUe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrEtqVal)) {
            return false;
        }
        TrEtqVal other = (TrEtqVal) object;
        if ((this.idUe == null && other.idUe != null) || (this.idUe != null && !this.idUe.equals(other.idUe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.org.inegi.sare.sare_db.dto.TrEtqVal[ idUe=" + idUe + " ]";
    }
    
}
