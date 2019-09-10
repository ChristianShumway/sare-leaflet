/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.Enums.TipoAreaEnum;
import mx.org.inegi.sare.sare_db.dto.cat_frente_geometria;
import mx.org.inegi.sare.sare_db.dto.cat_ubicacion_punteo;
import mx.org.inegi.sare.sare_db.dto.cat_uo;
import mx.org.inegi.sare.sare_db.dto.cat_vial;
import mx.org.inegi.sare.sare_db.interfaces.InterfacePunteoSare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Repository("DaoPunteoSare")
public class DaoPunteoSare extends DaoBusquedaSare implements InterfacePunteoSare {

    @Autowired
    @Qualifier("jdbcTemplatemdm")
    private JdbcTemplate jdbcTemplatemdm;

    @Autowired
    @Qualifier("schemaSaremdm")
    private String schemamdm;

    @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;

    @Autowired
    @Qualifier("schemaSareOcl")
    private String schemaocl;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
    @Autowired
    @Qualifier("jdbcTemplateOclEge")
    private JdbcTemplate jdbcTemplateoclEge;

    String resultado;
    boolean isMza;
    String entidad;
    cat_ubicacion_punteo ubicacion_punteo;
    List<cat_vial> cat_vial;
    String tipo_vial;
    List<cat_vial> cat_tipo_vial;

    @Override
    public boolean isCECorrect(String x, String y, String ent, Integer proyecto) {
        StringBuilder sql = new StringBuilder();
        boolean regresar;
        String point = "POINT(" + x + " " + y + ")";
        super.proyectos = super.getProyecto(proyecto);
        sql.append("SELECT * FROM denue2014.coordinacion_estatal WHERE st_intersects(geom,ST_GeomFromText(?,900913)) AND ce = ?");
        regresar = jdbcTemplatemdm.query(sql.toString(), new Object[]{point, ent}, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean fila = false;
                while (rs.next()) {
                    fila = true;
                }
                return fila;
            }
        });
        return regresar;
    }

    public enum Metodo {
        TIPOAREA, ISMANZANA, GETENTIDAD, GETPUNTEO, VALPUNTEO, GET_TIPO_VIAL, GET_CAT_TIPO_VIAL, GETPUNTEORURAL, FRENTES_PROXIMOS, CVEMANZANA
    }

    public class EnumTest {

        Metodo metodo;

        public EnumTest(Metodo metodo) {
            this.metodo = metodo;
        }
    }

    @Override
    public String getTipoArea(Integer proyecto, String x, String y) {
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", x, y, Metodo.TIPOAREA);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                resultado = execSqlTipoAreaPg(sql);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                resultado = execSqlTipoAreaMdm(sql);
                break;

        }

        return resultado;
    }

    private String execSqlTipoAreaMdm(StringBuilder sql) {
        resultado = "";
        jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String fila = null;
                while (rs.next()) {
                    fila = rs.getString("tipoarea");
                }
                if (Objects.equals(TipoAreaEnum.URBANA.getArea(), fila)) {
                    resultado = TipoAreaEnum.URBANA.getArea();
                } else {
                    resultado = TipoAreaEnum.RURAL.getArea();
                }
                return fila;
            }
        });
        return resultado;
    }

    private String execSqlTipoAreaPg(StringBuilder sql) {
        resultado = "";
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String fila = null;
                while (rs.next()) {
                    fila = rs.getString("tipoarea");
                }
                if (Objects.equals(TipoAreaEnum.URBANA.getArea(), fila)) {
                    resultado = TipoAreaEnum.URBANA.getArea();
                } else {
                    resultado = TipoAreaEnum.RURAL.getArea();
                }
                return fila;
            }
        });
        return resultado;

    }

    @Override
    public boolean isPuntoinMza(Integer proyecto, String x, String y) {

        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", x, y, Metodo.ISMANZANA);
        String point = "POINT(" + x + " " + y + ")";

        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                isMza = execSqlisPuntoinMzaPg(sql, point);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                isMza = execSqlisPuntoinMzaMdm(sql, point);
                break;

        }

        return isMza;
    }

    private boolean execSqlisPuntoinMzaMdm(StringBuilder sql, String point) {
        isMza = false;
        isMza = jdbcTemplatemdm.query(sql.toString(), new Object[]{point, point}, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean fila = false;
                while (rs.next()) {
                    fila = rs.getBoolean("contenido");
                }
                return fila;
            }
        });
        return isMza;
    }

    private boolean execSqlisPuntoinMzaPg(StringBuilder sql, String point) {
        isMza = false;
        isMza = jdbcTemplate.query(sql.toString(), new Object[]{point, point}, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean fila = false;
                while (rs.next()) {
                    fila = rs.getBoolean("contenido");
                }
                return fila;
            }
        });
        return isMza;
    }

    @Override
    public String getEntidad(Integer proyecto, String x, String y) {
        entidad = null;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", x, y, Metodo.GETENTIDAD);
        String point = "POINT(" + x + " " + y + ")";
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                entidad = execSqlEntidadPg(sql, point);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                entidad = execSqlEntidadMgm(sql, point);
                break;

        }

        return entidad;
    }

    private String execSqlEntidadMgm(StringBuilder sql, String point) {
        entidad = null;
        entidad = jdbcTemplatemdm.query(sql.toString(), new Object[]{point}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String fila = null;
                while (rs.next()) {
                    fila = rs.getString("cve_ent");
                }
                return fila;
            }
        });
        return entidad;
    }

    @Override
    public List<cat_uo> getListaUO(Integer proyecto, String cveFrente,String idDeftramo) {
        List<cat_uo> cveManzana = null;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = new StringBuilder();
        sql = sql.append(" select uo.id_uo_masivo,uo.x,uo.y,pre.ID_PREDIO,pre.ID_INMUEBLE from ").append(schemaocl).append(".tr_plan_oper po");
        sql.append(" join ").append(schemaocl).append(".tr_predios pre on pre.id_cop=po.id_cop");
        sql.append(" join ").append(schemaocl).append(".tr_uo_masivo uo on uo.id_uo_masivo =pre.id_uo_masivo");
        sql.append(" join ").append(schemaocl).append(".tr_inmuebles inm on inm.id_inmueble=pre.id_inmueble");
        sql.append(" join ").append(schemaocl).append(".tc_tipo_inmueble ti on ti.id_tipo_inmueble=inm.id_tipo_inmueble ");
        //sql.append(" where  uo.e03||uo.e04||uo.e05||uo.e06||uo.e07||inm.cveft='").append(cveFrente).append("'");
        sql.append(" where  uo.e03||uo.e04||uo.e05||uo.e06||uo.e07||inm.cveft='").append(cveFrente).append("' and pre.st_sare='10' and pre.st_sare<>'01' and inm.ID_DEFTRAMO=").append(idDeftramo);
        cveManzana = jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<List<cat_uo>>() {
            @Override
            public List<cat_uo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<cat_uo> fila = new ArrayList<>();

                while (rs.next()) {
                    fila.add(new cat_uo(rs.getString("id_uo_masivo"), rs.getString("x"), rs.getString("y"), null, rs.getString("id_predio"), rs.getString("id_inmueble")));
                }
                return fila;
            }
        });
        return cveManzana;
    }

    @Override
    public String getConversionPuntosAMercator(String x, String y) {
        String cveManzana = null;
        StringBuilder sql;
        sql = new StringBuilder();
        //sql = sql.append(" select astext(ST_Transform((ST_SetSRID(ST_MakePoint('").append(x).append("','").append(y).append("'),6372)),900913))  as geometria");             
        sql.append("select st_astext(st_transform(st_buffer(ST_GeomFromText(' point(").append(x).append(" ").append(y).append(" )',6372),1),900913)) as geometria ");
        cveManzana = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String fila = "";
                while (rs.next()) {
                    fila = rs.getString("geometria");
                }
                return fila;
            }
        });
        return cveManzana;
    }

    @Override
    public List<cat_frente_geometria> getGeometriaFrente(Integer proyecto, String x, String y) {
        List<cat_frente_geometria> cveManzana = null;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", x, y, Metodo.CVEMANZANA);
        cveManzana = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<List<cat_frente_geometria>>() {
            @Override
            public List<cat_frente_geometria> extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_frente_geometria fila = null;
                List<cat_frente_geometria> resultado = new ArrayList<cat_frente_geometria>();
                while (rs.next()) {
                    fila = new cat_frente_geometria(rs.getString("clave"), rs.getString("the_geom"), rs.getString("cve_ent"), rs.getString("cve_mun"), rs.getString("cve_loc"), rs.getString("cve_ageb"), rs.getString("cve_mza"), rs.getString("nom_ent"), rs.getString("nom_mun"), rs.getString("nom_loc"), rs.getString("cveft"), rs.getString("id_deftramo"));
                    resultado.add(fila);
                }
                return resultado;
            }
        });
        return cveManzana;
    }

    private String execSqlEntidadPg(StringBuilder sql, String point) {
        entidad = null;
        entidad = jdbcTemplate.query(sql.toString(), new Object[]{point}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String fila = null;
                while (rs.next()) {
                    fila = rs.getString("cve_ent");
                }
                return fila;
            }
        });
        return entidad;
    }

    @Override
    public cat_ubicacion_punteo getInfoPunteoUrbano(Integer proyecto, String ce, String x, String y) {
        ubicacion_punteo = new cat_ubicacion_punteo();
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, ce, x, y, Metodo.GETPUNTEO);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                ubicacion_punteo = execSqlInfoPunteoUrbanoPg(sql);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                ubicacion_punteo = execSqlInfoPunteoUrbanoMdm(sql);
                break;

        }

        return ubicacion_punteo;
    }

    private cat_ubicacion_punteo execSqlInfoPunteoUrbanoMdm(StringBuilder sql) {
        ubicacion_punteo = new cat_ubicacion_punteo();
        ubicacion_punteo = jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<cat_ubicacion_punteo>() {
            @Override
            public cat_ubicacion_punteo extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    ubicacion_punteo = new cat_ubicacion_punteo();
                    ubicacion_punteo.setE03(rs.getString("cve_ent") != null ? rs.getString("cve_ent") : "");
                    ubicacion_punteo.sete03N(rs.getString("nom_ent") != null ? rs.getString("nom_ent") : "");
                    ubicacion_punteo.setE04(rs.getString("cve_mun") != null ? rs.getString("cve_mun") : "");
                    ubicacion_punteo.sete04N(rs.getString("nom_mun") != null ? rs.getString("nom_mun") : "");
                    ubicacion_punteo.setE05(rs.getString("cve_loc") != null ? rs.getString("cve_loc") : "");
                    ubicacion_punteo.sete05N(rs.getString("nom_loc") != null ? rs.getString("nom_loc") : "");
                    ubicacion_punteo.setE06(rs.getString("cve_ageb") != null ? rs.getString("cve_ageb") : "");
                    ubicacion_punteo.setE07(rs.getString("cve_mza") != null ? rs.getString("cve_mza") : "");
                    ubicacion_punteo.setCveft(rs.getString("cveft") != null ? rs.getString("cveft") : "");
                    ubicacion_punteo.settipo_e10n(rs.getString("tipovial") != null ? rs.getString("tipovial") : "");
                    ubicacion_punteo.setE10_cvevial(rs.getString("cvevial") != null ? rs.getString("cvevial") : "");
                    ubicacion_punteo.setE10(rs.getString("nomvial") != null ? rs.getString("nomvial") : "");
                    ubicacion_punteo.setCoord_x(rs.getString("x") != null ? rs.getString("x") : "");
                    ubicacion_punteo.setCoord_y(rs.getString("y") != null ? rs.getString("y") : "");
                    ubicacion_punteo.setPunteo(rs.getString("punteo") != null ? rs.getString("punteo") : "");
                    ubicacion_punteo.setMod_cat(rs.getInt("mod_cat"));
                    ubicacion_punteo.setCvegeo(rs.getString("cvegeo") != null ? rs.getString("cvegeo") : "");
                    ubicacion_punteo.setCvegeo2016(rs.getString("cvegeo2016") != null ? rs.getString("cvegeo2016") : "");
                }

                return ubicacion_punteo;
            }
        });
        return ubicacion_punteo;
    }

    private cat_ubicacion_punteo execSqlInfoPunteoUrbanoPg(StringBuilder sql) {
        ubicacion_punteo = null;
        ubicacion_punteo = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<cat_ubicacion_punteo>() {
            @Override
            public cat_ubicacion_punteo extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    ubicacion_punteo = new cat_ubicacion_punteo();
                    ubicacion_punteo.setE03(rs.getString("cve_ent") != null ? rs.getString("cve_ent") : "");
                    ubicacion_punteo.sete03N(rs.getString("nom_ent") != null ? rs.getString("nom_ent") : "");
                    ubicacion_punteo.setE04(rs.getString("cve_mun") != null ? rs.getString("cve_mun") : "");
                    ubicacion_punteo.sete04N(rs.getString("nom_mun") != null ? rs.getString("nom_mun") : "");
                    ubicacion_punteo.setE05(rs.getString("cve_loc") != null ? rs.getString("cve_loc") : "");
                    ubicacion_punteo.sete05N(rs.getString("nom_loc") != null ? rs.getString("nom_loc") : "");
                    ubicacion_punteo.setE06(rs.getString("cve_ageb") != null ? rs.getString("cve_ageb") : "");
                    ubicacion_punteo.setE07(rs.getString("cve_mza") != null ? rs.getString("cve_mza") : "");
                    ubicacion_punteo.setCveft(rs.getString("cveft") != null ? rs.getString("cveft") : "");
                    ubicacion_punteo.settipo_e10n(rs.getString("tipovial") != null ? rs.getString("tipovial") : "");
                    ubicacion_punteo.setE10_cvevial(rs.getString("cvevial") != null ? rs.getString("cvevial") : "");
                    ubicacion_punteo.setE10(rs.getString("nomvial") != null ? rs.getString("nomvial") : "");
                    ubicacion_punteo.setCoord_x(rs.getString("x") != null ? rs.getString("x") : "");
                    ubicacion_punteo.setCoord_y(rs.getString("y") != null ? rs.getString("y") : "");
                    ubicacion_punteo.setPunteo(rs.getString("punteo") != null ? rs.getString("punteo") : "");
                    ubicacion_punteo.setMod_cat(rs.getInt("mod_cat"));
                    ubicacion_punteo.setCvegeo(rs.getString("cvegeo") != null ? rs.getString("cvegeo") : "");
                    ubicacion_punteo.setCvegeo2016(rs.getString("cvegeo2016") != null ? rs.getString("cvegeo2016") : "");
                }

                return ubicacion_punteo;
            }
        });
        return ubicacion_punteo;
    }

    @Override
    public List<cat_vial> validaInfoPunteoUrbano(String ent, String cve_geo, String cve_ft, Integer proyecto, String ce, String x, String y) {
        cat_vial = new ArrayList<>();
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, ent, x, y, Metodo.VALPUNTEO);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                cat_vial = execSqlValidaInfoPunteoUrbanoPg(sql, ent, cve_geo, cve_ft);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                cat_vial = execSqlValidaInfoPunteoUrbanoMdm(sql, ent, cve_geo, cve_ft);
                break;

        }

        return cat_vial;
    }

    private List<cat_vial> execSqlValidaInfoPunteoUrbanoMdm(StringBuilder sql, String ent, String cve_geo, String cve_ft) {
        cat_vial = new ArrayList<>();
        cat_vial = jdbcTemplatemdm.query(sql.toString(), new Object[]{ent, cve_geo, cve_ft}, new ResultSetExtractor<List<cat_vial>>() {
            @Override
            public List<cat_vial> extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_vial fila = null;
                List<cat_vial> lista = new ArrayList<>();
                while (rs.next()) {
                    fila = new cat_vial(null, rs.getString("tipovial"), rs.getString("nomvial"), rs.getString("cvevial"), rs.getString("cveseg"));
                    lista.add(fila);
                }
                return lista;
            }
        });
        return cat_vial;
    }

    private List<cat_vial> execSqlValidaInfoPunteoUrbanoPg(StringBuilder sql, String ent, String cve_geo, String cve_ft) {
        int cve = Integer.valueOf(cve_ft);
        cat_vial = new ArrayList<>();
        cat_vial = jdbcTemplate.query(sql.toString(), new Object[]{ent, cve_geo, cve}, new ResultSetExtractor<List<cat_vial>>() {
            @Override
            public List<cat_vial> extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_vial fila = null;
                List<cat_vial> lista = new ArrayList<>();
                while (rs.next()) {
                    fila = new cat_vial(null, rs.getString("tipovial"), rs.getString("nomvial"), rs.getString("cvevial"), rs.getString("cveseg"));
                    lista.add(fila);
                }
                return lista;
            }
        });
        return cat_vial;
    }

    @Override
    public String getTipoVial(Integer proyecto, String tipoE10Xn) {
        tipo_vial = null;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", "", "", Metodo.GET_TIPO_VIAL);
        tipo_vial = jdbcTemplate.query(sql.toString(), new Object[]{tipoE10Xn}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String fila = null;
                while (rs.next()) {
                    fila = rs.getString("tipo_e10");
                }
                return fila;
            }
        });

        return tipo_vial;
    }

    @Override
    public List<cat_vial> getCatTipoVial(Integer proyecto) {
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", "", "", Metodo.GET_CAT_TIPO_VIAL);
        cat_tipo_vial = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<List<cat_vial>>() {
            @Override
            public List<cat_vial> extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_vial fila;
                List<cat_vial> vial = new ArrayList<>();
                while (rs.next()) {
                    fila = new cat_vial(rs.getString("tipo_e10"), rs.getString("tipo_e10n"));
                    vial.add(fila);
                }
                return vial;
            }
        });
        return cat_tipo_vial;
    }

    @Override
    public cat_ubicacion_punteo getInfoPunteoRural(Integer proyecto, String x, String y) {
        ubicacion_punteo = new cat_ubicacion_punteo();
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "", x, y, Metodo.GETPUNTEORURAL);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                ubicacion_punteo = execInfoPunteoRuralPg(sql);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                ubicacion_punteo = execInfoPunteoRuralMdm(sql);
                break;

        }

        return ubicacion_punteo;
    }

    private cat_ubicacion_punteo execInfoPunteoRuralMdm(StringBuilder sql) {
        ubicacion_punteo = new cat_ubicacion_punteo();
        ubicacion_punteo = jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<cat_ubicacion_punteo>() {
            @Override
            public cat_ubicacion_punteo extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_ubicacion_punteo fila = new cat_ubicacion_punteo();
                while (rs.next()) {
                    fila.setE03(rs.getString("cve_ent"));
                    fila.sete03N(rs.getString("nom_ent"));
                    fila.setE04(rs.getString("cve_mun"));
                    fila.sete04N(rs.getString("nom_mun"));
                    fila.setE05(rs.getString("cve_loc"));
                    fila.sete05N(rs.getString("nom_loc"));
                    fila.setE06(rs.getString("cve_ageb"));
                    fila.setE07(rs.getString("cve_mza"));
                    fila.setCveft(rs.getString("cveft"));
                    fila.settipo_e10n(rs.getString("tipovial"));
                    fila.setE10_cvevial(rs.getString("cvevial"));
                    fila.setE10(rs.getString("nomvial"));
                    fila.setCoord_x(rs.getString("x"));
                    fila.setCoord_y(rs.getString("y"));
                    fila.setPunteo(rs.getString("punteo"));
                    fila.setMod_cat(rs.getInt("mod_cat"));
                    fila.setCvegeo(rs.getString("cvegeo"));
                    fila.setCvegeo2016(rs.getString("cvegeo2016"));
                }

                return fila;
            }
        });
        return ubicacion_punteo;
    }

    private cat_ubicacion_punteo execInfoPunteoRuralPg(StringBuilder sql) {
        ubicacion_punteo = new cat_ubicacion_punteo();
        ubicacion_punteo = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<cat_ubicacion_punteo>() {
            @Override
            public cat_ubicacion_punteo extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_ubicacion_punteo fila = new cat_ubicacion_punteo();
                while (rs.next()) {
                    fila.setE03(rs.getString("cve_ent"));
                    fila.sete03N(rs.getString("nom_ent"));
                    fila.setE04(rs.getString("cve_mun"));
                    fila.sete04N(rs.getString("nom_mun"));
                    fila.setE05(rs.getString("cve_loc"));
                    fila.sete05N(rs.getString("nom_loc"));
                    fila.setE06(rs.getString("cve_ageb"));
                    fila.setE07(rs.getString("cve_mza"));
                    fila.setCveft(rs.getString("cveft"));
                    fila.settipo_e10n(rs.getString("tipovial"));
                    fila.setE10_cvevial(rs.getString("cvevial"));
                    fila.setE10(rs.getString("nomvial"));
                    fila.setCoord_x(rs.getString("x"));
                    fila.setCoord_y(rs.getString("y"));
                    fila.setPunteo(rs.getString("punteo"));
                    fila.setMod_cat(rs.getInt("mod_cat"));
                    fila.setCvegeo(rs.getString("cvegeo"));
                    fila.setCvegeo2016(rs.getString("cvegeo2016"));
                }

                return fila;
            }
        });
        return ubicacion_punteo;
    }

    @Override
    public boolean isFrentesProximos(Integer proyecto, String ent, String x, String y) {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, ent, x, y, Metodo.FRENTES_PROXIMOS);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                regresar = execSqlisFrentesProximosPg(sql);
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                regresar = execSqlisFrentesProximosMdm(sql);
                break;

        }

        return regresar;
    }

    private boolean execSqlisFrentesProximosMdm(StringBuilder sql) {
        boolean regresar = false;
        regresar = jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                Boolean fila = false;
                while (rs.next()) {
                    fila = rs.getBoolean("frentes");

                }
                return fila;
            }
        });
        return regresar;
    }

    private boolean execSqlisFrentesProximosPg(StringBuilder sql) {
        boolean regresar = false;
        regresar = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                Boolean fila = false;
                while (rs.next()) {
                    fila = rs.getBoolean("frentes");

                }
                return fila;
            }
        });
        return regresar;
    }

    private StringBuilder getSql(ProyectosEnum proyecto, String ce, String x, String y, Metodo metodo) {
        StringBuilder sql = new StringBuilder();
        String point = "POINT(" + x + " " + y + ")";
        String esquemaPos, esquemaOcl;
        esquemaPos = getEsquemaPostgres(proyecto);
        esquemaOcl = getEsquemaOracle(proyecto);
        switch (proyecto) {

            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
            case MasivoOtros:
                switch (metodo) {
                    case TIPOAREA:
                        sql = new StringBuilder();
                        sql.append("select tipoarea from(");
                        sql.append("select 'U' tipoarea from ").append(schemapg).append(".td_ageb where st_contains(the_geom_merc,geomfromtext('").append(point).append("',900913))");
                        sql.append(" union all ");
                        sql.append("select 'R' tipoarea from ").append(schemapg).append(".td_ageb_rural where st_contains(the_geom_merc,geomfromtext('").append(point).append("',900913)))");
                        sql.append("ageb limit 1");
                        break;
                    case ISMANZANA:
                        sql.append("select case when resultado<>0 or resultado is null then false else true end contenido from(");
                        sql.append("select sum(case when contenida=false then 1 else 0 end) resultado from (");
                        sql.append("select *,ST_ContainsProperly(the_geom_merc,buffer(geomfromtext(?,900913),1)) contenida from (");
                        sql.append("select gid,the_geom_merc from ").append(schemapg).append(".vw_manzanasmgn2019 where st_intersects(the_geom_merc,buffer(geomfromtext(?,900913),1)))a)b)c");
                        break;
                    case GETENTIDAD:
                        sql.append("SELECT cve_ent FROM ").append(schemapg).append(".td_entidad WHERE st_intersects(the_geom_merc,ST_GeomFromText(?,900913)) ");
                        break;
                    case GETPUNTEO:
                        sql.append("select u.cve_ent,ent.nomgeo nom_ent,u.cve_mun,mun.nomgeo nom_mun,u.cve_loc,case when l.nomgeo is null then u.nom_loc else l.nomgeo end nom_loc,u.cve_ageb,x,y,cve_mza,cveft, nomvial,tipovial,u.cvegeo,cvevial,punteo,mod_cat,cvegeo2016 from(  ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,null nom_loc,cve_ageb,X(ST_astext(ST_ClosestPoint(a.the_geom_merc,  ST_GeomFromText('").append(point).append("',900913)))),  ");
                        sql.append("Y(ST_astext(ST_ClosestPoint(a.the_geom_merc,  ST_GeomFromText('").append(point).append("',900913)))),cve_mza,cveft cveft, nomvial,tipovial, cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza  cvegeo,cvevial cvevial,'U' punteo,1 mod_cat,'' cvegeo2016 ");
                        sql.append("FROM ").append(schemapg).append(".vw_frentesmgn2019").append(" a where cve_ent in (select cve_ent from ").append(schemapg).append(".td_entidad where contains(the_geom_merc, ST_GeomFromText('").append(point).append("',900913))) and   ");
                        sql.append("st_intersects(the_geom_merc,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),1)))  ");
                        sql.append("ORDER BY the_geom_merc <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append("union all ");
                        sql.append("(select cve_ent,cve_mun,cve_loc,null nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                        sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),'' cve_mza,'1' cveft,null nomvial, null tipovial, ");
                        sql.append("cve_ent||cve_mun||cve_loc||cve_ageb cvegeo, '99999' cvevial,'U' punteo,2 mod_cat,'' cvegeo2016 from ").append(schemapg).append(".td_ageb where contains(the_geom_merc, ST_GeomFromText('").append(point).append("',900913)) and   ");
                        sql.append("contains(the_geom_merc, ST_GeomFromText('").append(point).append("',900913))  ");
                        sql.append("ORDER BY the_geom_merc <->'SRID=900913;").append(point).append("'::geometry LIMIT 1)) u  ");
                        sql.append("INNER JOIN ").append(schemapg).append(".td_entidad ent ON u.cve_ent=ent.cvegeo  INNER JOIN ").append(schemapg).append(".td_municipios mun ON u.cve_ent=mun.cve_ent and u.cve_mun=mun.cve_mun  ");
                        sql.append("left JOIN ").append(schemapg).append(".td_localidades l ON u.cve_ent=l.cve_ent and u.cve_mun=l.cve_mun and u.cve_loc=l.cve_loc order by mod_cat limit 1");
                        break;
                    case VALPUNTEO:
                        sql.append("select tipovial,nomvial,cvevial,cveseg from ").append(schemapg).append(".get_cat_vial(?,?,?) order by nomvial");
                        //sql.append("select tipovial,nomvial,(row_number() over())::text cvevial,null cveseg from ").append(schemapg).append(".vw_frentesmgn2019").append(" where cve_ent=? and cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza=? and  cveft<>?  group by 1,2");
                        break;
                    case GET_TIPO_VIAL:
                        sql.append("SELECT tipo_e10 FROM ").append(esquemaPos).append(".cat_tipovialidad WHERE lower(descripcion) = ? union all \n"
                                + "SELECT '99' tipo_e10 limit 1 ");
                        break;
                    case GET_CAT_TIPO_VIAL:
                        sql.append("SELECT tipo_e10,descripcion tipo_e10n FROM ").append(esquemaPos).append(".cat_tipovialidad order by descripcion");
                        break;
                    case GETPUNTEORURAL:
                        sql.append("select r.cve_ent,ent.nomgeo as nom_ent,r.cve_mun,mun.nomgeo as nom_mun,cve_loc,nom_loc,cve_ageb,x,y,cve_mza,cveft, nomvial,tipovial,r.cvegeo,cvevial,punteo,mod_cat,cvegeo2016  from( ");
                        sql.append("(select * from  ");
                        sql.append("((SELECT m.cve_ent,m.cve_mun,m.cve_loc,nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                        sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,1 mod_cat,'' cvegeo2016 ");
                        sql.append("FROM sare_mas2019_carto.vw_manzanasmgn2019 m inner join (select cve_ent,cve_mun,cve_loc,nomgeo as nom_loc from sare_mas2019_carto.td_localidades_rurales_lpr) l "
                                + "on m.cve_ent||m.cve_mun||m.cve_loc=l.cve_ent||l.cve_mun||l.cve_loc  ");
                        sql.append("where st_intersects(the_geom_merc,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),50)))  ");
                        sql.append("ORDER BY the_geom_merc <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append("union all ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,nomgeo as nom_loc,cve_ageb,X(the_geom_merc),Y(the_geom_merc),'' cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,1 mod_cat,'' cvegeo2016 ");
                        sql.append("FROM sare_mas2019_carto.td_localidades_rurales_lpr where st_intersects(the_geom_merc,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),50))) ");
                        sql.append("ORDER BY the_geom_merc <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append(") rc limit 1) ");
                        sql.append("union all ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,nomgeo nom_loc,replace(cve_ageb,'-','') cve_ageb,X(the_geom_merc),Y(the_geom_merc),'' cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,2 mod_cat, cve_ent||cve_mun||cve_loc||replace(cve_ageb,'-','') cvegeo2016 ");
                        sql.append("FROM sare_mas2019_carto.td_localidades_rurales_lpr where st_intersects(the_geom_merc,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),50))) ");
                        sql.append("ORDER BY the_geom_merc <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append("union all ");
                        sql.append("(select cve_ent,cve_mun,'' cve_loc,'' nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                        sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),'' cve_mza,'1' cveft,null nomvial, null tipovial, ");
                        sql.append("cve_ent||cve_mun||cve_ageb cvegeo, '99999' cvevial,'R' punteo,2 mod_cat,'' cvegeo2016 from sare_mas2019_carto.td_ageb_rural where contains(the_geom_merc, ST_GeomFromText('").append(point).append("',900913)) and   ");
                        sql.append("contains(the_geom_merc, ST_GeomFromText('").append(point).append("',900913))  ");
                        sql.append("ORDER BY the_geom_merc <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) )r ");
                        sql.append("INNER JOIN sare_mas2019_carto.td_entidad ent ON r.cve_ent=ent.cvegeo  INNER JOIN sare_mas2019_carto.td_municipios mun ON r.cve_ent=mun.cve_ent and r.cve_mun=mun.cve_mun  order by mod_cat limit 1");
                        break;
                    case FRENTES_PROXIMOS:
                        sql.append("SELECT case when COUNT(*)>0 then true else false end frentes FROM ").append(schemapg).append(".vw_frentesmgn2019").append(" where cve_ent in (select cve_ent from ").append(schemapg).append(".td_entidad where contains(the_geom_merc, ST_GeomFromText('").append(point).append("',900913))) and  ");
                        sql.append("st_intersects(the_geom_merc,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),20)))");
                        break;
                    case CVEMANZANA:
                        sql.append("select astext(ST_SimplifyPreserveTopology(frente.the_geom_merc,0.1)) as the_geom, frente.cvegeo||cveft as clave,frente.cve_ent,frente.cve_mun,frente.cve_loc,frente.cve_ageb,frente.cve_mza,frente.cve_ent||frente.cve_mun||frente.cve_loc||frente.cve_ageb||frente.cve_mza as clave2 ,ent.nomgeo nom_ent,mun.nomgeo nom_mun,loc.nomgeo nom_loc,cveft,id_deftramo from ");
                        sql.append(schemapg).append(".vw_tr_frentes  frente");
                        sql.append(" left join ").append(schemapg).append(".td_entidad ent on frente.cve_ent=ent.cve_ent ");
                        sql.append(" left join ").append(schemapg).append(".td_municipios mun on frente.cve_ent=mun.cve_ent and frente.cve_mun=mun.cve_mun ");
                        sql.append(" left join ").append(schemapg).append(".td_localidades loc on frente.cve_ent=loc.cve_ent and frente.cve_mun=loc.cve_mun and frente.cve_loc=loc.cve_loc ");
                        sql.append(" where st_intersects(frente.the_geom_merc,st_buffer(ST_GeomFromText('").append(point).append("',900913),1))");
                        
                        /*sql.append("select astext(ST_SimplifyPreserveTopology(frente.the_geom_merc,0.1)) as the_geom, frente.cvegeo||cveft as clave,cveft,id_deftramo  from ");
                        sql.append(schemapg).append(".vw_tr_frentes  frente");
                        sql.append(" where st_intersects(frente.the_geom_merc,st_buffer(ST_GeomFromText('").append(point).append("',900913),1))");*/                                                            
                        break;                                               
                }
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:

                switch (metodo) {
                    case TIPOAREA:
                        sql = new StringBuilder();
                        sql.append("select tipoarea from(");
                        sql.append("select 'U' tipoarea from ").append(schemamdm).append(".a where st_contains(the_geom,geomfromtext('").append(point).append("',900913))");
                        sql.append(" union all ");
                        sql.append("select 'R' tipoarea from ").append(schemamdm).append(".ar where st_contains(the_geom,geomfromtext('").append(point).append("',900913)))");
                        sql.append("ageb limit 1");
                        break;
                    case ISMANZANA:
                        sql.append("select case when resultado<>0 or resultado is null then false else true end contenido from(");
                        sql.append("select sum(case when contenida=false then 1 else 0 end) resultado from (");
                        sql.append("select *,ST_ContainsProperly(the_geom,buffer(geomfromtext(?,900913),1)) contenida from (");
                        sql.append("select gid,tipomza,the_geom from ").append(schemamdm).append(".manzanas where st_intersects(the_geom,buffer(geomfromtext(?,900913),1)))a)b)c");
                        break;
                    case GETENTIDAD:
                        sql.append("SELECT cve_ent FROM ").append(schemamdm).append(".ent WHERE st_intersects(the_geom,ST_GeomFromText(?,900913)) ");
                        break;
                    case GETPUNTEO:
                        sql.append("select u.cve_ent,ent.nom_ent,u.cve_mun,nom_mun,u.cve_loc,case when l.nomloc is null then u.nom_loc else l.nomloc end nom_loc,cve_ageb,x,y,cve_mza,cveft, nomvial,tipovial,u.cvegeo,cvevial,punteo,mod_cat,cvegeo2016 from(  ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,null nom_loc,cve_ageb,X(ST_astext(ST_ClosestPoint(a.geom,  ST_GeomFromText('").append(point).append("',900913)))),  ");
                        sql.append("Y(ST_astext(ST_ClosestPoint(a.geom,  ST_GeomFromText('").append(point).append("',900913)))),cve_mza,cve_ft cveft, nomvial,tipovial, cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza  cvegeo,cve_vial cvevial,'U' punteo,1 mod_cat,'' cvegeo2016 ");
                        sql.append("FROM ").append(schemamdm).append(".fm").append(ce).append(" a where cve_ent in (select cve_ent from ").append(schemamdm).append(".ent where contains(the_geom, ST_GeomFromText('").append(point).append("',900913))) and   ");
                        sql.append("st_intersects(geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),1)))  ");
                        sql.append("ORDER BY geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append("/*union all ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,nom_loc,replace(cve_ageb,'-','') cve_ageb,X(ST_astext(ST_ClosestPoint(a.the_geom,  ST_GeomFromText('").append(point).append("',900913)))),  ");
                        sql.append("Y(ST_astext(ST_ClosestPoint(a.the_geom,  ST_GeomFromText('").append(point).append("',900913)))),cve_mza,'1' cveft, null nomvial,  ");
                        sql.append("null tipovial, cve_ent||cve_mun||cve_loc||replace(cve_ageb,'-','') cvegeo,'99999' cvevial,'U' punteo,2 mod_cat,cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza cvegeo2016 ");
                        sql.append("FROM mgm_cgura_junio2016.manzanas a left join (select cve_ent cve_ent_l,cve_mun cve_mun_l,cve_loc cve_loc_l,nomgeo nom_loc from mgm_cgura_junio2016.l) b on a.cve_ent=b.cve_ent_l and a.cve_mun=b.cve_mun_l and a.cve_loc=b.cve_loc_l ");
                        sql.append("where cve_ent in (select cve_ent from ").append(schemamdm).append(".ent where contains(the_geom, ST_GeomFromText('").append(point).append("',900913))) and   ");
                        sql.append("st_intersects(the_geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),1)))   ");
                        sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) */");
                        sql.append("union all ");
                        sql.append("(select cve_ent,cve_mun,cve_loc,null nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                        sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),'' cve_mza,'1' cveft,null nomvial, null tipovial, ");
                        sql.append("cve_ent||cve_mun||cve_loc||cve_ageb cvegeo, '99999' cvevial,'U' punteo,2 mod_cat,'' cvegeo2016 from ").append(schemamdm).append(".a where contains(the_geom, ST_GeomFromText('").append(point).append("',900913)) and   ");
                        sql.append("contains(the_geom, ST_GeomFromText('").append(point).append("',900913))  ");
                        sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1)) u  ");
                        sql.append("INNER JOIN ").append(schemamdm).append(".ent ent ON u.cve_ent=ent.cvegeo  INNER JOIN ").append(schemamdm).append(".mun mun ON u.cve_ent=mun.cve_ent and u.cve_mun=mun.cve_mun  ");
                        sql.append("left JOIN ").append(schemamdm).append(".l l ON u.cve_ent=l.cve_ent and u.cve_mun=l.cve_mun and u.cve_loc=l.cve_loc order by mod_cat limit 1");
                        break;
                    case VALPUNTEO:
                        sql.append("select tipovial,nomvial,(row_number() over())::text cvevial,null cveseg from ").append(schemamdm).append(".fm").append(ce).append(" where cve_ent=? and cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza=? and  cve_ft<>?  group by 1,2");
                        break;
                    case GET_TIPO_VIAL:
                        sql.append("SELECT tipo_e10 FROM ").append(esquemaPos).append(".cat_tipovialidad WHERE lower(descripcion) = ?");
                        break;
                    case GET_CAT_TIPO_VIAL:
                        sql.append("SELECT tipo_e10,descripcion tipo_e10n FROM ").append(esquemaPos).append(".cat_tipovialidad order by descripcion");
                        break;
                    case GETPUNTEORURAL:
                        sql.append("select r.cve_ent,nom_ent,r.cve_mun,nom_mun,cve_loc,nom_loc,cve_ageb,x,y,cve_mza,cveft, nomvial,tipovial,r.cvegeo,cvevial,punteo,mod_cat,cvegeo2016 from( ");
                        sql.append("(select * from  ");
                        sql.append("((SELECT m.cve_ent,m.cve_mun,m.cve_loc,nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                        sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,1 mod_cat,'' cvegeo2016 ");
                        sql.append("FROM denue2014.manzanas_rurales m inner join (select cve_ent,cve_mun,cve_loc,nom_loc from denue2014.lpr) l on m.cve_ent||m.cve_mun||m.cve_loc=l.cve_ent||l.cve_mun||l.cve_loc  ");
                        sql.append("where st_intersects(the_geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),50)))  ");
                        sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append("union all ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,nom_loc,cve_ageb,X(the_geom),Y(the_geom),'' cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,1 mod_cat,'' cvegeo2016 ");
                        sql.append("FROM denue2014.lpr where st_intersects(the_geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),50))) ");
                        sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append(") rc limit 1) ");
                        sql.append("union all ");
                        sql.append("(SELECT cve_ent,cve_mun,cve_loc,nomgeo nom_loc,replace(cve_ageb,'-','') cve_ageb,X(the_geom),Y(the_geom),'' cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,2 mod_cat, cve_ent||cve_mun||cve_loc||replace(cve_ageb,'-','') cvegeo2016 ");
                        sql.append("FROM mgm_cgura_junio2016.lpr where st_intersects(the_geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),50))) ");
                        sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                        sql.append("union all ");
                        sql.append("(select cve_ent,cve_mun,'' cve_loc,'' nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                        sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),'' cve_mza,'1' cveft,null nomvial, null tipovial, ");
                        sql.append("cve_ent||cve_mun||cve_ageb cvegeo, '99999' cvevial,'R' punteo,2 mod_cat,'' cvegeo2016 from denue2014.ar where contains(the_geom, ST_GeomFromText('").append(point).append("',900913)) and   ");
                        sql.append("contains(the_geom, ST_GeomFromText('").append(point).append("',900913))  ");
                        sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) )r ");
                        sql.append("INNER JOIN denue2014.ent ent ON r.cve_ent=ent.cvegeo  INNER JOIN denue2014.mun mun ON r.cve_ent=mun.cve_ent and r.cve_mun=mun.cve_mun  order by mod_cat limit 1");
                        break;
                    case FRENTES_PROXIMOS:
                        sql.append("SELECT case when COUNT(*)>0 then true else false end frentes FROM ").append(schemamdm).append(".fm").append(ce).append(" where cve_ent in (select cve_ent from ").append(schemamdm).append(".ent where contains(the_geom, ST_GeomFromText('").append(point).append("',900913))) and  ");
                        sql.append("st_intersects(geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),20)))");
                        break;
                }
                break;

        }
        return sql;
    }

}
