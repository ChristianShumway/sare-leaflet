/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardadoUXFrente;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceGuardarUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Repository("DaoBackingGuardarSare")
@Profile("jdbc")
public class DaoBackingGuardarSare extends DaoSincronizaSare implements InterfaceGuardarUE {

    @Autowired
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
     @Autowired
    @Qualifier("jdbcTemplateOclUEEPA")
    private JdbcTemplate jdbcTemplateoclueepa;

    @Autowired
    @Qualifier("jdbcTemplateOclEge")
    private JdbcTemplate jdbcTemplateoclEge;

//    @Autowired
//    @Qualifier("schemaSareOcl")
//    private String schemaocl;
//     @Autowired
//    @Qualifier("schemaSarePG")
//    private String schemapg;
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public enum MetodosGuardar {
        getValidaUe, getClaveProvisional, getGuardaUe, getE23A, getidDeftramo, UpdateOclStatusOk, UpdateOclStatusOcupado, GuardarUnidadesEnFrentes, UpdateOclStatusOkFrentes, getGuardaUePrepared
    }

    @Override
    public boolean GuardarUEFrentes(Integer proyecto, cat_vw_punteo_sare_guardadoUXFrente obj) {
        StringBuilder sql;
        int regresa = 0;
        boolean regresar = false;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case MasivoOtros:
            case UEEPA:
                sql = getSql(proyectos, obj, null, MetodosGuardar.GuardarUnidadesEnFrentes, "", false);
                regresa = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                        boolean regresar = false;
                        int fila = 0;

                        while (rs.next()) {
                            regresar = rs.getInt("resultado") > 0;
                            fila = rs.getInt("resultado");

                        }
                        return fila;
                    }
                });
                break;
        }

        if (regresa > 0) {
            regresar = true;
            obj.setResultado(String.valueOf(regresa));
        }
        return regresar;
    }

    @Override
    public Integer getValidaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        int regresa = 0;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case Operativo_Masivo:
            case MasivoOtros:
            case Establecimientos_GrandesY_Empresas_EGE:
            case UEEPA:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getValidaUe, "", false);
                regresa = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                        int regresar = 0;
                        int fila = 0;
                        while (rs.next()) {
                            regresar = rs.getInt("resultado");

                        }
                        return regresar;
                    }
                });
                break;
        }

        return regresa;
    }

    @Override
    public String getClaveProvisional(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, String capa) {
        StringBuilder sql;
        String regresa = null;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case Operativo_Masivo:
            case MasivoOtros:
            case Establecimientos_GrandesY_Empresas_EGE:
            case UEEPA:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getClaveProvisional, capa, false);
                regresa = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<String>() {
                    @Override
                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                        String regresar = null;
                        while (rs.next()) {
                            regresar = rs.getString("clave");
                        }
                        return regresar;
                    }
                });
        }
        return regresa;
    }

    @Override
    public boolean getGuardaUePreparedStatement(Integer proyecto, final cat_vw_punteo_sare_guardado inmueble, final boolean isAlta) {
        StringBuilder sql;
        double regresa = 0L;
        boolean regresar;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case MasivoOtros:
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
            case UEEPA:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getGuardaUePrepared, "", isAlta);
                try {
                    regresa = jdbcTemplate.execute(sql.toString(), new PreparedStatementCallback<Double>() {
                        @Override
                        @SuppressWarnings("empty-statement")
                        public Double doInPreparedStatement(PreparedStatement ps)
                                throws SQLException, DataAccessException {

                            ps.setBigDecimal(1, new BigDecimal(inmueble.getId_UE().trim()));
                            ps.setString(2, inmueble.getTramo_control());
                            ps.setString(3, inmueble.getCvegeo().toUpperCase());
                            ps.setString(4, inmueble.getCE().toUpperCase());
                            ps.setString(5, inmueble.getE03().toUpperCase());
                            ps.setString(6, inmueble.getE03N().toUpperCase());
                            ps.setString(7, inmueble.getE04().toUpperCase());
                            ps.setString(8, inmueble.getE04N().toUpperCase());
                            ps.setString(9, inmueble.getE05().toUpperCase());
                            ps.setString(10, inmueble.getE05N().toUpperCase());
                            ps.setString(11, inmueble.getE06().toUpperCase());
                            ps.setString(12, inmueble.getE07().toUpperCase());
                            ps.setInt(13, Integer.valueOf(inmueble.getCveft()));
                            ps.setString(14, inmueble.getE08());
                            ps.setString(15, inmueble.getE09());
                            ps.setString(16, inmueble.getTipo_e10());
                            ps.setString(17, inmueble.getE10());
                            ps.setString(18, inmueble.getE11());
                            ps.setString(19, inmueble.gete11A());
                            ps.setString(20, inmueble.getE12());
                            ps.setString(21, inmueble.getE12p());
                            ps.setString(22, inmueble.getE13());
                            ps.setString(23, inmueble.getE13_a());
                            ps.setString(24, inmueble.getTipo_E14());
                            ps.setString(25, inmueble.getE14());
                            ps.setString(26, inmueble.getE14_A());
                            ps.setString(27, inmueble.getTipo_e10_a());
                            ps.setString(28, inmueble.getE10_A());
                            ps.setString(29, inmueble.getTipo_e10_b());
                            ps.setString(30, inmueble.getE10_B());
                            ps.setString(31, inmueble.getTipo_e10_c());
                            ps.setString(32, inmueble.getE10_C());
                            ps.setString(33, inmueble.getE10_e());
                            ps.setString(34, inmueble.getDescrubic());
                            ps.setBigDecimal(35, inmueble.getCoordx());
                            ps.setBigDecimal(36, inmueble.getCoordy());
                            ps.setString(37, inmueble.getE19());
                            ps.setString(38, inmueble.getTipo_e19());
                            ps.setString(39, inmueble.getPunteo());
                            ps.setInt(40, Integer.valueOf(inmueble.getMod_cat()));
                            ps.setString(41, inmueble.getOrigen().toUpperCase());
                            ps.setString(42, inmueble.getCvegeo2016().toUpperCase());
                            ps.setString(43, inmueble.getE20().toUpperCase());
                            ps.setBigDecimal(44, inmueble.getId_deftramo());
                            ps.setString(45, inmueble.getE10_cvevial());
                            ps.setString(46, inmueble.getE23());
                            ps.setBoolean(47, isAlta);
                            ps.setString(48, inmueble.getNavegador());

                            boolean rs = ps.execute();
                            Double result;
                            if (rs = true) {
                                result = 1D;
                            } else {
                                result = 0D;
                            };
                            return result;
                        }
                    });

                } catch (Exception e) {
                    regresa = 0L;
                }

                break;
        }
        //inmueble.setId_UE(String.valueOf(regresa));
        regresar = regresa > 0L;
        return regresar;
    }

    @Override
    public boolean getGuardaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, boolean isAlta) {
        StringBuilder sql;
        double regresa = 0L;
        boolean regresar;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case MasivoOtros:
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
            case UEEPA:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getGuardaUe, "", isAlta);
                try {
                    regresa = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Double>() {
                        @Override
                        public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
                            double regresar;
                            double fila = 0;
                            while (rs.next()) {
                                fila = rs.getDouble("resultado");

                            }
                            regresar = fila;
                            return regresar;
                        }
                    });
                } catch (Exception e) {
                    regresa = 0L;
                }

                break;
        }
        //inmueble.setId_UE(String.valueOf(regresa));
        regresar = regresa > 0L;
        return regresar;
    }

    @Override
    public String e23a(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        String regresa = null;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case MasivoOtros:
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getE23A, "", false);
                regresa = jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<String>() {
                    @Override
                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                        String regresar = "";
                        while (rs.next()) {
                            regresar = rs.getString("E23A");
                        }
                        return regresar;
                    }
                });
                break;
            case UEEPA:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getE23A, "", false);
                regresa = jdbcTemplateoclueepa.query(sql.toString(), new ResultSetExtractor<String>() {
                    @Override
                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                        String regresar = "";
                        while (rs.next()) {
                            regresar = rs.getString("E23A");
                        }
                        return regresar;
                    }
                });
                break;
//            case Establecimientos_GrandesY_Empresas_EGE:
//                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getE23A, "", false);
//                regresa = jdbcTemplateoclEge.query(sql.toString(), new ResultSetExtractor<String>() {
//                    @Override
//                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
//                        String regresar = "";
//                        while (rs.next()) {
//                            regresar = rs.getString("E23A");
//                        }
//                        return regresar;
//                    }
//                });
//                break;
        }

        return regresa;
    }

    @Override
    public Integer getidDeftramo(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        Integer regresa = null;
        proyectos = getProyecto(proyecto);
        switch (proyectos) {
            case Operativo_Masivo:
            case MasivoOtros:
            case Establecimientos_GrandesY_Empresas_EGE:
            case UEEPA:
                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getidDeftramo, "", false);
                regresa = jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Integer regresar = 0;
                        while (rs.next()) {
                            regresar = rs.getInt("id_deftramo");
                        }
                        return regresar;
                    }
                });
                break;
//            case Establecimientos_GrandesY_Empresas_EGE:
//                sql = getSql(proyectos, null, inmueble, MetodosGuardar.getidDeftramo, "", false);
//                regresa = jdbcTemplateoclEge.query(sql.toString(), new ResultSetExtractor<Integer>() {
//                    @Override
//                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
//                        Integer regresar = 0;
//                        while (rs.next()) {
//                            regresar = rs.getInt("id_deftramo");
//                        }
//                        return regresar;
//                    }
//                });
//                break;
        }
        return regresa;
    }

    @Override
    public boolean UpdateOclStatusOk(Integer proyecto, cat_vw_punteo_sare_guardado object, String id_ue, boolean isAlta) {
        boolean regresa = false;
        if (isAlta) {
            return true;
        } else {
            StringBuilder sql;
            proyectos = getProyecto(proyecto);
            sql = getSql(proyectos, null, object, MetodosGuardar.UpdateOclStatusOk, "", false);
            switch (proyectos) {
                case Operativo_Masivo:
                case Establecimientos_GrandesY_Empresas_EGE:
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) {
                        regresa = true;
                    }
                    break;
                case UEEPA:
                    if (jdbcTemplateoclueepa.update(sql.toString(), new Object[]{id_ue}) > 0) {
                        regresa = true;
                    }
                    break;
                case MasivoOtros:
                    sql = getSql(proyectos, null, object, MetodosGuardar.UpdateOclStatusOkFrentes, "", false);
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) {
                        regresa = true;
                    }
                    break;
                default:
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) {
                        regresa = true;
                    }
            }
            return regresa;
        }
    }

    @Override
    public boolean UpdateOclStatusOcupado(Integer proyecto, cat_vw_punteo_sare_guardado object, String id_ue, boolean isAlta) {
        boolean regresa = false;
        if (isAlta) {
            return true;
        } else {
            StringBuilder sql;
            proyectos = getProyecto(proyecto);
            sql = getSql(proyectos, null, object, MetodosGuardar.UpdateOclStatusOcupado, "", false);
            switch (proyectos) {
                case Operativo_Masivo:
                case Establecimientos_GrandesY_Empresas_EGE:
                case UEEPA:
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) {
                        regresa = true;
                    }
                    break;
                default:
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) {
                        regresa = true;
                    }
            }
            return regresa;
        }
    }

    public StringBuilder getSql(ProyectosEnum proyecto, cat_vw_punteo_sare_guardadoUXFrente obj, cat_vw_punteo_sare_guardado inmueble, MetodosGuardar metodo, String capa, boolean isAlta) {
        StringBuilder sql = new StringBuilder();
        String esquemaPos, esquemaOcl;
        switch (proyecto) {
            case Establecimientos_GrandesY_Empresas_EGE:
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Operativo_Masivo:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:

            case MasivoOtros:
                esquemaPos = getEsquemaPostgres(proyecto);
                esquemaOcl = getEsquemaOracle(proyecto);
                switch (metodo) {
                    case getValidaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".valida_ue_sare('")
                                .append(inmueble.getId_UE() == null ? "" : inmueble.getId_UE()).append("','")
                                .append(inmueble.gettramo_control() == null ? "" : inmueble.gettramo_control()).append("','")
                                .append(inmueble.getPunteo() == null ? "" : inmueble.getPunteo()).append("','")
                                .append(inmueble.getMod_cat() == null ? Integer.valueOf(null) : Integer.valueOf(inmueble.getMod_cat())).append("','")
                                .append(inmueble.getE14_A() == null ? "" : inmueble.getE14_A()).append("','")
                                .append(inmueble.getE03() == null ? "" : inmueble.getE03()).append("','")
                                .append(inmueble.getE05() == null ? "" : inmueble.getE05()).append("','")
                                .append(inmueble.getE07() == null ? "" : inmueble.getE07()).append("','")
                                .append(inmueble.gete11A() == null ? "" : inmueble.gete11A()).append("','")
                                .append(inmueble.getTipo_E14() == null ? "" : inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12() == null ? "" : inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12p() == null ? "" : inmueble.getE12p()).append("','")
                                .append(inmueble.getE13() == null ? "" : inmueble.getE13()).append("','")
                                .append(inmueble.getE13_a() == null ? "" : inmueble.getE13_a()).append("','")
                                .append(inmueble.getTipo_e19() == null ? "" : inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getE19() == null ? "" : inmueble.getE19()).append("','")
                                .append(inmueble.getE20() == null ? "" : inmueble.getE20()).append("','")
                                .append(inmueble.getE11() == null ? "" : inmueble.getE11()).append("') resultado");
                        break;
                    case getClaveProvisional:
                        sql.append("SELECT ").append(esquemaPos).append(".calcula_cveprov('").append(inmueble.gettramo_control()).append("','").append(capa).append("') clave");
                        break;
                    case getGuardaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".registra_ue_sare('").append(inmueble.getId_UE()).append("','")
                                .append(inmueble.getTramo_control()).append("','").append(inmueble.getCvegeo().toUpperCase()).append("','")
                                .append(inmueble.getCE().toUpperCase()).append("','").append(inmueble.getE03().toUpperCase()).append("','")
                                .append(inmueble.getE03N().toUpperCase() != null ? inmueble.getE03N().toUpperCase() : "").append("','").append(inmueble.getE04().toUpperCase()).append("','")
                                .append(inmueble.getE04N().toUpperCase() != null ? inmueble.getE04N().toUpperCase() : "").append("','")
                                .append(inmueble.getE05().toUpperCase() != null ? inmueble.getE05().toUpperCase() : "").append("','")
                                .append(inmueble.getE05N().toUpperCase() != null ? inmueble.getE05N().toUpperCase() : "").append("','")
                                .append(inmueble.getE06().toUpperCase() != null ? inmueble.getE06().toUpperCase() : "").append("','")
                                .append(inmueble.getE07().toUpperCase() != null ? inmueble.getE07().toUpperCase() : "").append("','")
                                .append(inmueble.getCveft() != null ? inmueble.getCveft() : "").append("','")
                                .append(inmueble.getE08() != null ? inmueble.getE08().toUpperCase() : "").append("','")
                                .append(inmueble.getE09() != null ? inmueble.getE09().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10() != null ? inmueble.getTipo_e10() : "").append("','")
                                .append(inmueble.getE10() != null ? inmueble.getE10().toUpperCase() : "").append("','")
                                .append(inmueble.getE11() != null ? inmueble.getE11() : "").append("','")
                                .append(inmueble.gete11A() != null ? inmueble.gete11A().toUpperCase() : "").append("','")
                                .append(inmueble.getE12() != null ? inmueble.getE12().toUpperCase() : "").append("','")
                                .append(inmueble.getE12p() != null ? inmueble.getE12p() : "").append("','")
                                .append(inmueble.getE13() != null ? inmueble.getE13() : "").append("','")
                                .append(inmueble.getE13_a() != null ? inmueble.getE13_a().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_E14() != null ? inmueble.getTipo_E14() : "").append("','")
                                .append(inmueble.getE14() != null ? inmueble.getE14().toUpperCase() : "").append("','")
                                .append(inmueble.getE14_A() != null ? inmueble.getE14_A().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10_a() != null ? inmueble.getTipo_e10_a() : "").append("','")
                                .append(inmueble.getE10_A() != null ? inmueble.getE10_A().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10_b() != null ? inmueble.getTipo_e10_b() : "").append("','")
                                .append(inmueble.getE10_B() != null ? inmueble.getE10_B().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10_c().toUpperCase() != null ? inmueble.getTipo_e10_c().toUpperCase() : "").append("','")
                                .append(inmueble.getE10_C() != null ? inmueble.getE10_C().toUpperCase() : "").append("','")
                                .append(inmueble.getE10_e() != null ? inmueble.getE10_e().toUpperCase() : "").append("','")
                                .append(inmueble.getDescrubic() != null ? inmueble.getDescrubic().toUpperCase() : "").append("','")
                                .append(inmueble.getCoordx()).append("','")
                                .append(inmueble.getCoordy()).append("','")
                                .append(inmueble.getE19() != null ? inmueble.getE19().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e19() == null ? "" : inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getPunteo() != null ? inmueble.getPunteo() : "").append("','")
                                .append(inmueble.getMod_cat() != null ? inmueble.getMod_cat() : "").append("','")
                                .append(inmueble.getOrigen().toUpperCase()).append("','")
                                .append(inmueble.getCvegeo2016().toUpperCase()).append("','")
                                .append(inmueble.getE20().toUpperCase()).append("','")
                                .append(inmueble.getId_deftramo()).append("','")
                                .append(inmueble.getE10_cvevial() != null ? inmueble.getE10_cvevial() : "").append("','")
                                .append(inmueble.getE23() != null ? inmueble.getE23() : "").append("','").append(isAlta).append("','").append(inmueble.getNavegador()).append("') resultado");
                        break;
                    case getGuardaUePrepared:
                        sql.append("SELECT resultado from ").append(esquemaPos).append(".registra_ue_sare(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?").append(") resultado");
                        break;
                    case getE23A:
                        sql.append("SELECT E23A FROM ").append(esquemaOcl).append(".tr_etq_val where id_ue = ").append(inmueble.getId_UE());
                        break;
                    case getidDeftramo:
                        sql.append("SELECT id_deftramo");
                        sql.append(" FROM ").append(esquemaOcl).append(".tr_inmuebles where id_inmueble = ").append(inmueble.getId_inmueble());
                        break;
                    case UpdateOclStatusOk:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_PREDIOS set st_sare='01' where id_ue=? and st_sare='20'");
                        break;

                    case UpdateOclStatusOcupado:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_PREDIOS set st_sare='20' where id_ue=? and st_sare='01'");
                        break;
                    case GuardarUnidadesEnFrentes:
                        sql.append("SELECT ").append(esquemaPos).append(".interpolado_inmuebles('").append(obj.getIddeftramo()).append("','").append(obj.getCapa()).append("','")
                                .append(obj.getManzana_origen()).append("','").append(obj.getFrente_origen()).append("','").append(obj.getClaves())
                                .append("','").append(obj.getManzana_destino()).append("','").append(obj.getFrente_destino()).append("') resultado");
                        break;
                    case UpdateOclStatusOkFrentes:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_PREDIOS set st_sare='01' where id_uo_masivo=? and st_sare='20'");
                        break;
                }
            case UEEPA:
                esquemaPos = getEsquemaPostgres(proyecto);
                esquemaOcl = getEsquemaOracle(proyecto);
                switch (metodo) {
                    case getValidaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".valida_ue_sare('")
                                .append(inmueble.getId_UE() == null ? "" : inmueble.getId_UE()).append("','")
                                .append(inmueble.gettramo_control() == null ? "" : inmueble.gettramo_control()).append("','")
                                .append(inmueble.getPunteo() == null ? "" : inmueble.getPunteo()).append("','")
                                .append(inmueble.getMod_cat() == null ? Integer.valueOf(null) : Integer.valueOf(inmueble.getMod_cat())).append("','")
                                .append(inmueble.getE14_A() == null ? "" : inmueble.getE14_A()).append("','")
                                .append(inmueble.getE03() == null ? "" : inmueble.getE03()).append("','")
                                .append(inmueble.getE05() == null ? "" : inmueble.getE05()).append("','")
                                .append(inmueble.getE07() == null ? "" : inmueble.getE07()).append("','")
                                .append(inmueble.gete11A() == null ? "" : inmueble.gete11A()).append("','")
                                .append(inmueble.getTipo_E14() == null ? "" : inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12() == null ? "" : inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12p() == null ? "" : inmueble.getE12p()).append("','")
                                .append(inmueble.getE13() == null ? "" : inmueble.getE13()).append("','")
                                .append(inmueble.getE13_a() == null ? "" : inmueble.getE13_a()).append("','")
                                .append(inmueble.getTipo_e19() == null ? "" : inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getE19() == null ? "" : inmueble.getE19()).append("','")
                                .append(inmueble.getE20() == null ? "" : inmueble.getE20()).append("','")
                                .append(inmueble.getE11() == null ? "" : inmueble.getE11()).append("') resultado");
                        break;
                    case getClaveProvisional:
                        sql.append("SELECT ").append(esquemaPos).append(".calcula_cveprov('").append(inmueble.gettramo_control()).append("','").append(capa).append("') clave");
                        break;
                    case getGuardaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".registra_ue_sare('").append(inmueble.getId_UE()).append("','")
                                .append(inmueble.getTramo_control()).append("','").append(inmueble.getCvegeo().toUpperCase()).append("','")
                                .append(inmueble.getCE().toUpperCase()).append("','").append(inmueble.getE03().toUpperCase()).append("','")
                                .append(inmueble.getE03N().toUpperCase() != null ? inmueble.getE03N().toUpperCase() : "").append("','").append(inmueble.getE04().toUpperCase()).append("','")
                                .append(inmueble.getE04N().toUpperCase() != null ? inmueble.getE04N().toUpperCase() : "").append("','")
                                .append(inmueble.getE05().toUpperCase() != null ? inmueble.getE05().toUpperCase() : "").append("','")
                                .append(inmueble.getE05N().toUpperCase() != null ? inmueble.getE05N().toUpperCase() : "").append("','")
                                .append(inmueble.getE06().toUpperCase() != null ? inmueble.getE06().toUpperCase() : "").append("','")
                                .append(inmueble.getE07().toUpperCase() != null ? inmueble.getE07().toUpperCase() : "").append("','")
                                .append(inmueble.getCveft() != null ? inmueble.getCveft() : "").append("','")
                                .append(inmueble.getE08() != null ? inmueble.getE08().toUpperCase() : "").append("','")
                                .append(inmueble.getE09() != null ? inmueble.getE09().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10() != null ? inmueble.getTipo_e10() : "").append("','")
                                .append(inmueble.getE10() != null ? inmueble.getE10().toUpperCase() : "").append("','")
                                .append(inmueble.getE11() != null ? inmueble.getE11() : "").append("','")
                                .append(inmueble.gete11A() != null ? inmueble.gete11A().toUpperCase() : "").append("','")
                                .append(inmueble.getE12() != null ? inmueble.getE12().toUpperCase() : "").append("','")
                                .append(inmueble.getE12p() != null ? inmueble.getE12p() : "").append("','")
                                .append(inmueble.getE13() != null ? inmueble.getE13() : "").append("','")
                                .append(inmueble.getE13_a() != null ? inmueble.getE13_a().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_E14() != null ? inmueble.getTipo_E14() : "").append("','")
                                .append(inmueble.getE14() != null ? inmueble.getE14().toUpperCase() : "").append("','")
                                .append(inmueble.getE14_A() != null ? inmueble.getE14_A().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10_a() != null ? inmueble.getTipo_e10_a() : "").append("','")
                                .append(inmueble.getE10_A() != null ? inmueble.getE10_A().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10_b() != null ? inmueble.getTipo_e10_b() : "").append("','")
                                .append(inmueble.getE10_B() != null ? inmueble.getE10_B().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e10_c().toUpperCase() != null ? inmueble.getTipo_e10_c().toUpperCase() : "").append("','")
                                .append(inmueble.getE10_C() != null ? inmueble.getE10_C().toUpperCase() : "").append("','")
                                .append(inmueble.getE10_e() != null ? inmueble.getE10_e().toUpperCase() : "").append("','")
                                .append(inmueble.getDescrubic() != null ? inmueble.getDescrubic().toUpperCase() : "").append("','")
                                .append(inmueble.getCoordx()).append("','")
                                .append(inmueble.getCoordy()).append("','")
                                .append(inmueble.getE19() != null ? inmueble.getE19().toUpperCase() : "").append("','")
                                .append(inmueble.getTipo_e19() == null ? "" : inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getPunteo() != null ? inmueble.getPunteo() : "").append("','")
                                .append(inmueble.getMod_cat() != null ? inmueble.getMod_cat() : "").append("','")
                                .append(inmueble.getOrigen().toUpperCase()).append("','")
                                .append(inmueble.getCvegeo2016().toUpperCase()).append("','")
                                .append(inmueble.getE20().toUpperCase()).append("','")
                                .append(inmueble.getId_deftramo()).append("','")
                                .append(inmueble.getE10_cvevial() != null ? inmueble.getE10_cvevial() : "").append("','")
                                .append(inmueble.getE23() != null ? inmueble.getE23() : "").append("','").append(isAlta).append("','").append(inmueble.getNavegador()).append("') resultado");
                        break;
                    case getGuardaUePrepared:
                        sql.append("SELECT resultado from ").append(esquemaPos).append(".registra_ue_sare(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?").append(") resultado");
                        break;
                    case getE23A:
                        sql.append("SELECT e23 as E23A FROM ").append(esquemaOcl).append(".ENC_CUESTIONARIO where id = ").append(inmueble.getId_UE());
                        break;
                    case getidDeftramo:
                        sql.append("SELECT id_deftramo");
                        sql.append(" FROM ").append(esquemaOcl).append(".tr_inmuebles where id_inmueble = ").append(inmueble.getId_inmueble());
                        break;
                    case UpdateOclStatusOk:
                        sql.append("UPDATE ").append(esquemaOcl).append(".ENC_CUESTIONARIO set st_sare='01',FECHA_ST_SARE =CURRENT_DATE where id=? and st_sare='20'");
                        break;

                    case UpdateOclStatusOcupado:
                        sql.append("UPDATE ").append(esquemaOcl).append(".ENC_CUESTIONARIO set st_sare='20',FECHA_ST_SARE =CURRENT_DATE where id=? and st_sare='01'");
                        break;
                    case GuardarUnidadesEnFrentes:
                        sql.append("SELECT ").append(esquemaPos).append(".interpolado_inmuebles('").append(obj.getIddeftramo()).append("','").append(obj.getCapa()).append("','")
                                .append(obj.getManzana_origen()).append("','").append(obj.getFrente_origen()).append("','").append(obj.getClaves())
                                .append("','").append(obj.getManzana_destino()).append("','").append(obj.getFrente_destino()).append("') resultado");
                        break;
                    case UpdateOclStatusOkFrentes:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_PREDIOS set st_sare='01' where id_uo_masivo=? and st_sare='20'");
                        break;
                }
                break;
        }
        return sql;
    }

}
