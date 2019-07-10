/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.math.BigDecimal;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_vw_punteo_sare {

    private BigDecimal A;
    private String CE;
    private String CE_DESCRIPCION;
    private String CESTATAL;
    private String CLEE_EMP;
    private BigDecimal COORD_X;
    private BigDecimal COORD_Y;
    private String C154;
    private String DESCRUBIC;
    private String E03;
    private String E03N;
    private String E04;
    private String E04N;
    private String E05;
    private String E05N;
    private String E06;
    private String E07;
    private String E08;
    private String E09;
    private String E10;
    private String E10_A;
    private String E10_B;
    private String E10_C;
    private String E11;
    private String E11A;
    private String E12;
    private String E13;
    private String E13A;
    private String E14;
    private String E14_A;
    private BigDecimal E17;
    private String E17_DESC;
    private String E19;
    private String E20;
    private String E23A;
    private BigDecimal ID_DEFTRAMO;
    private BigDecimal ID_EVENTO;
    private BigDecimal ID_UE;
    private BigDecimal JA;
    private BigDecimal JC;
    private BigDecimal ORIGEN;
    private String SARE_ST;
    private String TIPO_E10;
    private String TIPO_E10_A;
    private String TIPO_E10_B;
    private String TIPO_E10_C;
    private String TIPO_E14;
    private String TIPO_E19;
    private String TRAMO_CONTROL;
    private Integer estatus_punteo;
    private String punteo;
    private String extent;
    private String e10_cvevial;
    private String e10_cveseg;
    private String cve_unica_duplicada;
    private String cod_resultado;
    private String tipo_reg;
    private String e12p;
    private String Tipo_e10n;
    private String Tipo_e10_an;
    private String Tipo_e10_bn;
    private String Tipo_e10_cn;
    private String tramo_control;
    private String cvegeo;
    private String tipoarea;
    private String Mod_cat;
    private String Cveft;
    private String Cvegeo2016;
    private String E10_e;
    private String E23;
    private BigDecimal Id_inmueble;
    private BigDecimal Id_deftramo;
     private String tipo_e10n_otro;
     private String tipo_e10_an_otro;
     private String tipo_e10_bn_otro;
     private String tipo_e10_cn_otro;
    

    public cat_vw_punteo_sare() {
    }

    public cat_vw_punteo_sare(String CE, BigDecimal COORD_X, BigDecimal COORD_Y, String DESCRUBIC, String E03, String E03N, String E04, String E04N, 
            String E05, String E05N, String E06, String E07, String E08, String E09, String E10, String E10_A, String E10_B,
            String E10_C, String E11, String E11A, String E12, String E13, String E13A, String E14, String E14_A, String E19, String E20, 
            BigDecimal ID_UE, BigDecimal ORIGEN, String TIPO_E10, String TIPO_E10_A, String TIPO_E10_B, String TIPO_E10_C, 
            String TIPO_E14, String TIPO_E19, String TRAMO_CONTROL, String punteo, String e10_cvevial, String e12p, String cvegeo, 
            String Mod_cat, String Cveft, String Cvegeo2016, String E10_e, String E23, BigDecimal Id_inmueble, BigDecimal Id_deftramo) {
        this.CE = CE;
        this.COORD_X = COORD_X;
        this.COORD_Y = COORD_Y;
        this.DESCRUBIC = DESCRUBIC;
        this.E03 = E03;
        this.E03N = E03N;
        this.E04 = E04;
        this.E04N = E04N;
        this.E05 = E05;
        this.E05N = E05N;
        this.E06 = E06;
        this.E07 = E07;
        this.E08 = E08;
        this.E09 = E09;
        this.E10 = E10;
        this.E10_A = E10_A;
        this.E10_B = E10_B;
        this.E10_C = E10_C;
        this.E11 = E11;
        this.E11A = E11A;
        this.E12 = E12;
        this.E13 = E13;
        this.E13A = E13A;
        this.E14 = E14;
        this.E14_A = E14_A;
        this.E19 = E19;
        this.E20 = E20;
        this.ID_UE = ID_UE;
        this.ORIGEN = ORIGEN;
        this.TIPO_E10 = TIPO_E10;
        this.TIPO_E10_A = TIPO_E10_A;
        this.TIPO_E10_B = TIPO_E10_B;
        this.TIPO_E10_C = TIPO_E10_C;
        this.TIPO_E14 = TIPO_E14;
        this.TIPO_E19 = TIPO_E19;
        this.TRAMO_CONTROL = TRAMO_CONTROL;
        this.punteo = punteo;
        this.e10_cvevial = e10_cvevial;
        this.e12p = e12p;
        this.cvegeo = cvegeo;
        this.Mod_cat = Mod_cat;
        this.Cveft = Cveft;
        this.Cvegeo2016 = Cvegeo2016;
        this.E10_e = E10_e;
        this.E23 = E23;
        this.Id_inmueble = Id_inmueble;
        this.Id_deftramo = Id_deftramo;
    }
    
    

    public cat_vw_punteo_sare(BigDecimal ID_UE, String E03, String E03N, String E04, String E04N, String E05, String E05N, String E06, String E07, String E08, String E09,
            String TIPO_E10, String E10, String e10_cvevial, String e10_cveseg, String E11, String E11A, String TIPO_E14, String E14,
            String TIPO_E10_A, String E10_A, String TIPO_E10_B, String E10_B, String TIPO_E10_C, String E10_C, String DESCRUBIC,
            BigDecimal COORD_X, BigDecimal COORD_Y, String cod_resultado, String tipo_reg, String E12, String e12p, String E19, String TIPO_E19,
            String E20, String E13, String cve_unica_duplicada) {
        this.ID_UE = ID_UE;
        this.E03 = E03;
        this.E03N = E03N;
        this.E04 = E04;
        this.E04N = E04N;
        this.E05 = E05;
        this.E05N = E05N;
        this.E06 = E06;
        this.E07 = E07;
        this.E08 = E08;
        this.E09 = E09;
        this.TIPO_E10 = TIPO_E10;
        this.E10 = E10;
        this.e10_cvevial = e10_cvevial;
        this.e10_cveseg = e10_cveseg;
        this.E11 = E11;
        this.E11A = E11A;
        this.TIPO_E14 = TIPO_E14;
        this.E14 = E14;
        this.TIPO_E10_A = TIPO_E10_A;
        this.E10_A = E10_A;
        this.TIPO_E10_B = TIPO_E10_B;
        this.E10_B = E10_B;
        this.TIPO_E10_C = TIPO_E10_C;
        this.E10_C = E10_C;
        this.DESCRUBIC = DESCRUBIC;
        this.COORD_X = COORD_X;
        this.COORD_Y = COORD_Y;
        this.cod_resultado = cod_resultado;
        this.tipo_reg = tipo_reg;
        this.E12 = E12;
        this.e12p = e12p;
        this.E19 = E19;
        this.TIPO_E19 = TIPO_E19;
        this.E20 = E20;
        this.E13 = E13;
        this.cve_unica_duplicada = cve_unica_duplicada;
    }

    public cat_vw_punteo_sare(BigDecimal A, String CE, String CE_DESCRIPCION, String CESTATAL, String CLEE_EMP, BigDecimal COORD_X,
            BigDecimal COORD_Y, String C154, String DESCRUBIC, String E03, String E03N, String E04, String E04N, String E05, String E05N,
            String E06, String E07, String E08, String E09, String E10, String E10_A, String E10_B, String E10_C, String E11,
            String E11A, String E12, String E13, String E13A, String E14, String E14_A, BigDecimal E17, String E17_DESC, String E19,
            String E20, String E23A, BigDecimal ID_DEFTRAMO, BigDecimal ID_EVENTO, BigDecimal ID_UE, BigDecimal JA, BigDecimal JC, 
            BigDecimal ORIGEN, String SARE_ST, String TIPO_E10, String TIPO_E10_A, String TIPO_E10_B, String TIPO_E10_C, 
            String TIPO_E14, String TIPO_E19, String TRAMO_CONTROL) {
        this.A = A;
        this.CE = CE;
        this.CE_DESCRIPCION = CE_DESCRIPCION;
        this.CESTATAL = CESTATAL;
        this.CLEE_EMP = CLEE_EMP;
        this.COORD_X = COORD_X;
        this.COORD_Y = COORD_Y;
        this.C154 = C154;
        this.DESCRUBIC = DESCRUBIC;
        this.E03 = E03;
        this.E03N = E03N;
        this.E04 = E04;
        this.E04N = E04N;
        this.E05 = E05;
        this.E05N = E05N;
        this.E06 = E06;
        this.E07 = E07;
        this.E08 = E08;
        this.E09 = E09;
        this.E10 = E10;
        this.E10_A = E10_A;
        this.E10_B = E10_B;
        this.E10_C = E10_C;
        this.E11 = E11;
        this.E11A = E11A;
        this.E12 = E12;
        this.E13 = E13;
        this.E13A = E13A;
        this.E14 = E14;
        this.E14_A = E14_A;
        this.E17 = E17;
        this.E17_DESC = E17_DESC;
        this.E19 = E19;
        this.E20 = E20;
        this.E23A = E23A;
        this.ID_DEFTRAMO = ID_DEFTRAMO;
        this.ID_EVENTO = ID_EVENTO;
        this.ID_UE = ID_UE;
        this.JA = JA;
        this.JC = JC;
        this.ORIGEN = ORIGEN;
        this.SARE_ST = SARE_ST;
        this.TIPO_E10 = TIPO_E10;
        this.TIPO_E10_A = TIPO_E10_A;
        this.TIPO_E10_B = TIPO_E10_B;
        this.TIPO_E10_C = TIPO_E10_C;
        this.TIPO_E14 = TIPO_E14;
        this.TIPO_E19 = TIPO_E19;
        this.TRAMO_CONTROL = TRAMO_CONTROL;
    }

    public cat_vw_punteo_sare(String CESTATAL, BigDecimal COORD_X, BigDecimal COORD_Y, String C154, String DESCRUBIC, String E03, String E04, 
            String E05, String E06, String E07, String E08, String E09, String E10, String E10_A, String E10_B, String E10_C, String E11, 
            String E11A, String E12, String E13, String E13A, String E14, String E14_A, BigDecimal E17, String E17_DESC, String E19, String E20, 
            String E23A, BigDecimal ID_UE, BigDecimal ORIGEN, Integer estatus_punteo, String TIPO_E10, String TIPO_E10_A, String TIPO_E10_B, 
            String TIPO_E10_C, String TIPO_E14, String TIPO_E19, BigDecimal id_inmueble) {
        this.CESTATAL = CESTATAL;
        this.COORD_X = COORD_X;
        this.COORD_Y = COORD_Y;
        this.C154 = C154;
        this.DESCRUBIC = DESCRUBIC;
        this.E03 = E03;
        this.E04 = E04;
        this.E05 = E05;
        this.E06 = E06;
        this.E07 = E07;
        this.E08 = E08;
        this.E09 = E09;
        this.E10 = E10;
        this.E10_A = E10_A;
        this.E10_B = E10_B;
        this.E10_C = E10_C;
        this.E11 = E11;
        this.E11A = E11A;
        this.E12 = E12;
        this.E13 = E13;
        this.E13A = E13A;
        this.E14 = E14;
        this.E14_A = E14_A;
        this.E17 = E17;
        this.E17_DESC = E17_DESC;
        this.E19 = E19;
        this.E20 = E20;
        this.E23A = E23A;
        this.ID_UE = ID_UE;
        this.ORIGEN = ORIGEN;
        this.estatus_punteo = estatus_punteo;
        this.TIPO_E10 = TIPO_E10;
        this.TIPO_E10_A = TIPO_E10_A;
        this.TIPO_E10_B = TIPO_E10_B;
        this.TIPO_E10_C = TIPO_E10_C;
        this.TIPO_E14 = TIPO_E14;
        this.TIPO_E19 = TIPO_E19;
        this.Id_inmueble=id_inmueble;
    }

    public String getPunteo() {
        return punteo;
    }

    public void setPunteo(String punteo) {
        this.punteo = punteo;
    }

    
    
    public String getTramo_control() {
        return tramo_control;
    }

    public void setTramo_control(String tramo_control) {
        this.tramo_control = tramo_control;
    }

    public String getCvegeo() {
        return cvegeo;
    }

    public void setCvegeo(String cvegeo) {
        this.cvegeo = cvegeo;
    }

    public String getTipoarea() {
        return tipoarea;
    }

    public void setTipoarea(String tipoarea) {
        this.tipoarea = tipoarea;
    }

    public String getMod_cat() {
        return Mod_cat;
    }

    public void setMod_cat(String Mod_cat) {
        this.Mod_cat = Mod_cat;
    }

    public String getCveft() {
        return Cveft;
    }

    public void setCveft(String Cveft) {
        this.Cveft = Cveft;
    }

    public String getCvegeo2016() {
        return Cvegeo2016;
    }

    public void setCvegeo2016(String Cvegeo2016) {
        this.Cvegeo2016 = Cvegeo2016;
    }

    public String getE10_e() {
        return E10_e;
    }

    public void setE10_e(String E10_e) {
        this.E10_e = E10_e;
    }

    public String getE23() {
        return E23;
    }

    public void setE23(String E23) {
        this.E23 = E23;
    }

    public BigDecimal getId_inmueble() {
        return Id_inmueble;
    }

    public void setId_inmueble(BigDecimal Id_inmueble) {
        this.Id_inmueble = Id_inmueble;
    }

    public BigDecimal getId_deftramo() {
        return Id_deftramo;
    }

    public void setId_deftramo(BigDecimal Id_deftramo) {
        this.Id_deftramo = Id_deftramo;
    }

    
    
    public String getTipo_e10_an() {
        return Tipo_e10_an;
    }

    public void setTipo_e10_an(String Tipo_e10_an) {
        this.Tipo_e10_an = Tipo_e10_an;
    }

    public String getTipo_e10_bn() {
        return Tipo_e10_bn;
    }

    public void setTipo_e10_bn(String Tipo_e10_bn) {
        this.Tipo_e10_bn = Tipo_e10_bn;
    }

    public String getTipo_e10_cn() {
        return Tipo_e10_cn;
    }

    public void setTipo_e10_cn(String Tipo_e10_cn) {
        this.Tipo_e10_cn = Tipo_e10_cn;
    }
    
    

    public String getTipo_e10n() {
        return Tipo_e10n;
    }

    public void setTipo_e10n(String Tipo_e10n) {
        this.Tipo_e10n = Tipo_e10n;
    }
    
    

    public String getCve_unica_duplicada() {
        return cve_unica_duplicada;
    }

    public void setCve_unica_duplicada(String cve_unica_duplicada) {
        this.cve_unica_duplicada = cve_unica_duplicada;
    }

    public String getCod_resultado() {
        return cod_resultado;
    }

    public void setCod_resultado(String cod_resultado) {
        this.cod_resultado = cod_resultado;
    }

    public String getTipo_reg() {
        return tipo_reg;
    }

    public void setTipo_reg(String tipo_reg) {
        this.tipo_reg = tipo_reg;
    }

    public String getE12p() {
        return e12p;
    }

    public void setE12p(String e12p) {
        this.e12p = e12p;
    }

    public String getE10_cvevial() {
        return e10_cvevial;
    }

    public void setE10_cvevial(String e10_cvevial) {
        this.e10_cvevial = e10_cvevial;
    }

    public String getE10_cveseg() {
        return e10_cveseg;
    }

    public void setE10_cveseg(String e10_cveseg) {
        this.e10_cveseg = e10_cveseg;
    }

    public Integer getEstatus_punteo() {
        return estatus_punteo;
    }

    public void setEstatus_punteo(Integer estatus_punteo) {
        this.estatus_punteo = estatus_punteo;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public BigDecimal getA() {
        return A;
    }

    public void setA(BigDecimal A) {
        this.A = A;
    }

    public String getCE() {
        return CE;
    }

    public void setCE(String CE) {
        this.CE = CE;
    }

    public String getCE_DESCRIPCION() {
        return CE_DESCRIPCION;
    }

    public void setCE_DESCRIPCION(String CE_DESCRIPCION) {
        this.CE_DESCRIPCION = CE_DESCRIPCION;
    }

    public String getCESTATAL() {
        return CESTATAL;
    }

    public void setCESTATAL(String CESTATAL) {
        this.CESTATAL = CESTATAL;
    }

    public String getCLEE_EMP() {
        return CLEE_EMP;
    }

    public void setCLEE_EMP(String CLEE_EMP) {
        this.CLEE_EMP = CLEE_EMP;
    }

    public BigDecimal getCOORD_X() {
        return COORD_X;
    }

    public void setCOORD_X(BigDecimal COORD_X) {
        this.COORD_X = COORD_X;
    }

    public BigDecimal getCOORD_Y() {
        return COORD_Y;
    }

    public void setCOORD_Y(BigDecimal COORD_Y) {
        this.COORD_Y = COORD_Y;
    }

    public String getC154() {
        return C154;
    }

    public void setC154(String C154) {
        this.C154 = C154;
    }

    public String getDESCRUBIC() {
        return DESCRUBIC;
    }

    public void setDESCRUBIC(String DESCRUBIC) {
        this.DESCRUBIC = DESCRUBIC;
    }

    public String getE03() {
        return E03;
    }

    public void setE03(String E03) {
        this.E03 = E03;
    }

    public String getE03N() {
        return E03N;
    }

    public void setE03N(String E03N) {
        this.E03N = E03N;
    }

    public String getE04() {
        return E04;
    }

    public void setE04(String E04) {
        this.E04 = E04;
    }

    public String getE04N() {
        return E04N;
    }

    public void setE04N(String E04N) {
        this.E04N = E04N;
    }

    public String getE05() {
        return E05;
    }

    public void setE05(String E05) {
        this.E05 = E05;
    }

    public String getE05N() {
        return E05N;
    }

    public void setE05N(String E05N) {
        this.E05N = E05N;
    }

    public String getE06() {
        return E06;
    }

    public void setE06(String E06) {
        this.E06 = E06;
    }

    public String getE07() {
        return E07;
    }

    public void setE07(String E07) {
        this.E07 = E07;
    }

    public String getE08() {
        return E08;
    }

    public void setE08(String E08) {
        this.E08 = E08;
    }

    public String getE09() {
        return E09;
    }

    public void setE09(String E09) {
        this.E09 = E09;
    }

    public String getE10() {
        return E10;
    }

    public void setE10(String E10) {
        this.E10 = E10;
    }

    public String getE10_A() {
        return E10_A;
    }

    public void setE10_A(String E10_A) {
        this.E10_A = E10_A;
    }

    public String getE10_B() {
        return E10_B;
    }

    public void setE10_B(String E10_B) {
        this.E10_B = E10_B;
    }

    public String getE10_C() {
        return E10_C;
    }

    public void setE10_C(String E10_C) {
        this.E10_C = E10_C;
    }

    public String getE11() {
        return E11;
    }

    public void setE11(String E11) {
        this.E11 = E11;
    }

    public String getE11A() {
        return E11A;
    }

    public void setE11A(String E11A) {
        this.E11A = E11A;
    }

    public String getE12() {
        return E12;
    }

    public void setE12(String E12) {
        this.E12 = E12;
    }

    public String getE13() {
        return E13;
    }

    public void setE13(String E13) {
        this.E13 = E13;
    }

    public String getE13A() {
        return E13A;
    }

    public void setE13A(String E13A) {
        this.E13A = E13A;
    }

    public String getE14() {
        return E14;
    }

    public void setE14(String E14) {
        this.E14 = E14;
    }

    public String getE14_A() {
        return E14_A;
    }

    public void setE14_A(String E14_A) {
        this.E14_A = E14_A;
    }

    public BigDecimal getE17() {
        return E17;
    }

    public void setE17(BigDecimal E17) {
        this.E17 = E17;
    }

    public String getE17_DESC() {
        return E17_DESC;
    }

    public void setE17_DESC(String E17_DESC) {
        this.E17_DESC = E17_DESC;
    }

    public String getE19() {
        return E19;
    }

    public void setE19(String E19) {
        this.E19 = E19;
    }

    public String getE20() {
        return E20;
    }

    public void setE20(String E20) {
        this.E20 = E20;
    }

    public String getE23A() {
        return E23A;
    }

    public void setE23A(String E23A) {
        this.E23A = E23A;
    }

    public BigDecimal getID_DEFTRAMO() {
        return ID_DEFTRAMO;
    }

    public void setID_DEFTRAMO(BigDecimal ID_DEFTRAMO) {
        this.ID_DEFTRAMO = ID_DEFTRAMO;
    }

    public BigDecimal getID_EVENTO() {
        return ID_EVENTO;
    }

    public void setID_EVENTO(BigDecimal ID_EVENTO) {
        this.ID_EVENTO = ID_EVENTO;
    }

    public BigDecimal getID_UE() {
        return ID_UE;
    }

    public void setID_UE(BigDecimal ID_UE) {
        this.ID_UE = ID_UE;
    }

    public BigDecimal getJA() {
        return JA;
    }

    public void setJA(BigDecimal JA) {
        this.JA = JA;
    }

    public BigDecimal getJC() {
        return JC;
    }

    public void setJC(BigDecimal JC) {
        this.JC = JC;
    }

    public BigDecimal getORIGEN() {
        return ORIGEN;
    }

    public void setORIGEN(BigDecimal ORIGEN) {
        this.ORIGEN = ORIGEN;
    }

    public String getSARE_ST() {
        return SARE_ST;
    }

    public void setSARE_ST(String SARE_ST) {
        this.SARE_ST = SARE_ST;
    }

    public String getTIPO_E10() {
        return TIPO_E10;
    }

    public void setTIPO_E10(String TIPO_E10) {
        this.TIPO_E10 = TIPO_E10;
    }

    public String getTIPO_E10_A() {
        return TIPO_E10_A;
    }

    public void setTIPO_E10_A(String TIPO_E10_A) {
        this.TIPO_E10_A = TIPO_E10_A;
    }

    public String getTIPO_E10_B() {
        return TIPO_E10_B;
    }

    public void setTIPO_E10_B(String TIPO_E10_B) {
        this.TIPO_E10_B = TIPO_E10_B;
    }

    public String getTIPO_E10_C() {
        return TIPO_E10_C;
    }

    public void setTIPO_E10_C(String TIPO_E10_C) {
        this.TIPO_E10_C = TIPO_E10_C;
    }

    public String getTIPO_E14() {
        return TIPO_E14;
    }

    public void setTIPO_E14(String TIPO_E14) {
        this.TIPO_E14 = TIPO_E14;
    }

    public String getTIPO_E19() {
        return TIPO_E19;
    }

    public void setTIPO_E19(String TIPO_E19) {
        this.TIPO_E19 = TIPO_E19;
    }

    public String getTRAMO_CONTROL() {
        return TRAMO_CONTROL;
    }

    public void setTRAMO_CONTROL(String TRAMO_CONTROL) {
        this.TRAMO_CONTROL = TRAMO_CONTROL;
    }

    public String getTipo_e10n_otro() {
        return tipo_e10n_otro;
    }

    public void setTipo_e10n_otro(String tipo_e10n_otro) {
        this.tipo_e10n_otro = tipo_e10n_otro;
    }

    public String getTipo_e10_an_otro() {
        return tipo_e10_an_otro;
    }

    public void setTipo_e10_an_otro(String tipo_e10_an_otro) {
        this.tipo_e10_an_otro = tipo_e10_an_otro;
    }

    public String getTipo_e10_bn_otro() {
        return tipo_e10_bn_otro;
    }

    public void setTipo_e10_bn_otro(String tipo_e10_bn_otro) {
        this.tipo_e10_bn_otro = tipo_e10_bn_otro;
    }

    public String getTipo_e10_cn_otro() {
        return tipo_e10_cn_otro;
    }

    public void setTipo_e10_cn_otro(String tipo_e10_cn_otro) {
        this.tipo_e10_cn_otro = tipo_e10_cn_otro;
    }
    
    

}
