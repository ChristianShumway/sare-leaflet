/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_get_claves;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceDesbloqueo;
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
@Repository("DaoDesbloqueo")
@Profile("jdbc")
public class DaoDesbloqueo extends DaoBusquedaSare implements InterfaceDesbloqueo {

    @Autowired
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
    @Autowired
    @Qualifier("jdbcTemplateOclEge")
    private JdbcTemplate jdbcTemplateoclEge;

//    @Autowired
//    @Qualifier("schemaSareOcl")
//    private String schemaocl;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    @Qualifier("schemaSarePG")
//    private String schemapg;

    public enum Desbloqueo {
        Desbloqueo, VerificaDesbloqueo, completaGuardado, existeUe, updateUE, insertUE, consultaPendientes, DesbloqueoBitacora
    }

    @Override
    public List<cat_vw_punteo_sare> getRegistroPendientesOcl(Integer proyecto, String usuario, String id_ue) {
        List<cat_vw_punteo_sare> regresar;
        int ue = Integer.valueOf(id_ue);
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", id_ue, Desbloqueo.consultaPendientes);
        regresar = jdbcTemplate.query(sql.toString(), new Object[]{ue}, new ResultSetExtractor<List<cat_vw_punteo_sare>>() {
            @Override
            public List<cat_vw_punteo_sare> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<cat_vw_punteo_sare> fila = new ArrayList<>();
                cat_vw_punteo_sare registro;
                while (rs.next()) {
                    registro = new cat_vw_punteo_sare(
                            rs.getString("cve_ce") != null && !"".equals(rs.getString("cve_ce")) ? rs.getString("cve_ce") : "",
                            rs.getString("coord_x") != null && !"".equals(rs.getString("coord_x")) ? new BigDecimal(rs.getString("coord_x")) : new BigDecimal(0),
                            rs.getString("coord_y") != null && !"".equals(rs.getString("coord_y")) ? new BigDecimal(rs.getString("coord_y")) : new BigDecimal(0),
                            rs.getString("descrubic") != null ? rs.getString("descrubic") : "",
                            rs.getString("cve_ent") != null ? rs.getString("cve_ent") : "",
                            rs.getString("nom_ent") != null ? rs.getString("nom_ent") : "",
                            rs.getString("cve_mun") != null ? rs.getString("cve_mun") : "",
                            rs.getString("nom_mun") != null ? rs.getString("nom_mun") : "",
                            rs.getString("cve_loc") != null ? rs.getString("cve_loc") : "",
                            rs.getString("cve_loc") != null ? rs.getString("cve_loc") : "",
                            rs.getString("cve_ageb") != null ? rs.getString("cve_ageb") : "",
                            rs.getString("cve_mza") != null ? rs.getString("cve_mza") : "",
                            rs.getString("e08") != null ? rs.getString("e08") : "",
                            rs.getString("e09") != null ? rs.getString("e09") : "",
                            rs.getString("nomvial") != null ? rs.getString("nomvial") : "",
                            rs.getString("e10_a") != null ? rs.getString("e10_a") : "",
                            rs.getString("e10_b") != null ? rs.getString("e10_b") : "",
                            rs.getString("e10_c") != null ? rs.getString("e10_c") : "",
                            rs.getString("numext") != null && !"".equals(rs.getString("numext")) ? rs.getString("numext") : "",
                            rs.getString("numextalf") != null ? rs.getString("numextalf") : "",
                            rs.getString("e12") != null ? rs.getString("e12") : "",
                            rs.getString("numint") != null && !"".equals(rs.getString("numint")) ? rs.getString("numint") : "",
                            rs.getString("numintalf") != null ? rs.getString("numintalf") : "",
                            rs.getString("e14") != null ? rs.getString("e14") : "",
                            rs.getString("e14_a") != null ? rs.getString("e14_a") : "",
                            rs.getString("e19") != null ? rs.getString("e19") : "",
                            rs.getString("e20") != null ? rs.getString("e20") : "",
                            rs.getString("id_ue") != null && !"".equals(rs.getString("id_ue")) ? new BigDecimal(rs.getString("id_ue")) : new BigDecimal(0),
                            rs.getString("origen") != null && !"".equals(rs.getString("origen")) ? new BigDecimal(rs.getString("origen")) : new BigDecimal(0),
                            rs.getString("tipo_e10") != null && !"".equals(rs.getString("tipo_e10")) ? rs.getString("tipo_e10") : "",
                            rs.getString("tipo_e10_a") != null && !"".equals(rs.getString("tipo_e10_a")) ? rs.getString("tipo_e10_a") : "",
                            rs.getString("tipo_e10_b") != null && !"".equals(rs.getString("tipo_e10_b")) ? rs.getString("tipo_e10_b") : "",
                            rs.getString("tipo_e10_c") != null && !"".equals(rs.getString("tipo_e10_c")) ? rs.getString("tipo_e10_c") : "",
                            rs.getString("tipo_e14") != null && !"".equals(rs.getString("tipo_e14")) ? rs.getString("tipo_e14") : "",
                            rs.getString("tipo_e19") != null ? rs.getString("tipo_e19") : "",
                            rs.getString("tramo_control") != null ? rs.getString("tramo_control") : "",
                            rs.getString("punteo") != null ? rs.getString("punteo") : "",
                            rs.getString("cvevial") != null ? rs.getString("cvevial") : "",
                            rs.getString("e12p") != null ? rs.getString("e12p") : "",
                            rs.getString("cvegeo") != null ? rs.getString("cvegeo") : "",
                            rs.getString("mod_cat") != null ? rs.getString("mod_cat") : "",
                            rs.getString("cveft") != null ? rs.getString("cveft") : "",
                            rs.getString("cvegeo2016") != null ? rs.getString("cvegeo2016") : "",
                            rs.getString("e10_e") != null ? rs.getString("e10_e") : "",
                            rs.getString("e23") != null ? rs.getString("e23") : "",
                            rs.getString("cveft") != null && !"".equals(rs.getString("cveft")) ? new BigDecimal(rs.getString("cveft")) : new BigDecimal(0),
                            rs.getString("id_deftramo") != null && !"".equals(rs.getString("id_deftramo")) ? new BigDecimal(rs.getString("id_deftramo")) : new BigDecimal(0));
                    fila.add(registro);
                }
                return fila;
            }
        });
        return regresar;
    }

    @Override
    public boolean Desbloqueo(Integer proyecto, String id_ue) {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", id_ue, Desbloqueo.Desbloqueo);
        if(id_ue!=null || !"".equals(id_ue))
        {
            switch (proyectos)
            {
                case Operativo_Masivo:
                    if (jdbcTemplateocl.update(sql.toString()) > 0) 
                    {
                        regresar = true;
                    }
                    break;
                case Establecimientos_GrandesY_Empresas_EGE:
                    if (jdbcTemplateocl.update(sql.toString()) > 0) 
                    {
                        regresar = true;
                    }
                    break;
                default:
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) 
                    {
                        regresar = true;
                    }
            }
        }
        return regresar;
    }

    @Override
    public boolean DesbloqueoBitacora(Integer proyecto, String id_ue) {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", id_ue, Desbloqueo.DesbloqueoBitacora);
        if(id_ue!=null || !"".equals(id_ue))
        {
            switch (proyectos)
            {
                case Operativo_Masivo:
                case Establecimientos_GrandesY_Empresas_EGE:
                    if (jdbcTemplate.update(sql.toString()) > 0) 
                    {
                        regresar = true;
                    }
                    break;
//                case Establecimientos_GrandesY_Empresas_EGE:
//                    if (jdbcTemplateoclEge.update(sql.toString()) > 0) 
//                    {
//                        regresar = true;
//                    }
//                    break;
                default:
                    if (jdbcTemplateocl.update(sql.toString(), new Object[]{id_ue}) > 0) 
                    {
                        regresar = true;
                    }
            }
        }
        return regresar;
    }
    @Override
    public boolean completaGuardadoOcl(Integer proyecto, String usuario, String id_ue) {
        boolean regresar = false;
        if (existeUe(proyecto, id_ue)) {
            if (CompletaSaveUE(proyecto, id_ue)) {
                regresar = UpdateUE(proyecto,"", usuario, id_ue);
            }
        } else if (CompletaSaveUE(proyecto, id_ue)) {
            regresar = InsertUe(proyecto,"", usuario, id_ue);
        }
        return regresar;
    }

    @Override
    public boolean RegistraUEComplemento(Integer proyecto,String ce, String usuario, String id_ue) {
        boolean regresar = false;
        if (existeUe(proyecto, id_ue)) {
            regresar = UpdateUE(proyecto,ce, usuario, id_ue);
        } else {
            regresar = InsertUe(proyecto,ce, usuario, id_ue);
        }
        return regresar;
    }

    private boolean existeUe(Integer proyecto, String id_ue) {
        boolean regresar;
        StringBuilder sql;
        proyectos = getProyecto(proyecto);
        sql = getSql(proyectos,"", id_ue, Desbloqueo.existeUe);
        switch (proyectos) {
            case Operativo_Masivo:
                regresar = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Boolean>() {
                    @Override
                    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                        boolean fila = false;
                        while (rs.next()) {
                            fila = rs.getInt(1) > 0;
                        }
                        return fila;
                    }
                });
                break;
            case Establecimientos_GrandesY_Empresas_EGE:
                regresar = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Boolean>() {
                    @Override
                    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                        boolean fila = false;
                        while (rs.next()) {
                            fila = rs.getInt(1) > 0;
                        }
                        return fila;
                    }
                });
                break;
            default:
                regresar = jdbcTemplateocl.query(sql.toString(), new Object[]{id_ue}, new ResultSetExtractor<Boolean>() {
                    @Override
                    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                        boolean fila = false;
                        while (rs.next()) {
                            fila = rs.getInt(1) > 0;
                        }
                        return fila;
                    }
                });

        }

        return regresar;
    }

    private boolean UpdateUE(Integer proyecto, String ce, String usuario, String id_ue) {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos, ce,id_ue, Desbloqueo.updateUE);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                if (jdbcTemplate.update(sql.toString(), new Object[]{usuario}) > 0) {
                    regresar = true;
                }
                break;
//            case Establecimientos_GrandesY_Empresas_EGE:
//                if (jdbcTemplate.update(sql.toString(), new Object[]{usuario}) > 0) {
//                    regresar = true;
//                }
//                break;
            default:
                if (jdbcTemplateocl.update(sql.toString(), new Object[]{usuario, id_ue}) > 0) {
                    regresar = true;
                }
        }
        return regresar;

    }

    private boolean InsertUe(Integer proyecto,String ce, String usuario, String id_ue) {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", id_ue, Desbloqueo.insertUE);
        BigDecimal clave = new BigDecimal(id_ue);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:
                if (jdbcTemplate.update(sql.toString(), new Object[]{usuario, clave, ce}) > 0) {
                    regresar = true;
                }
                break;
//            case Establecimientos_GrandesY_Empresas_EGE:
//                if (jdbcTemplateoclEge.update(sql.toString(), new Object[]{usuario, clave}) > 0) {
//                    regresar = true;
//                }
//                break;
            default:
                if (jdbcTemplateocl.update(sql.toString(), new Object[]{usuario, clave}) > 0) {
                    regresar = true;
                }

        }
        return regresar;
    }

    private boolean CompletaSaveUE(Integer proyecto, String id_ue) {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"",id_ue, Desbloqueo.completaGuardado);
        switch (proyectos) {
            case Operativo_Masivo:
                if (jdbcTemplate.update(sql.toString()) > 0) {
                    regresar = true;
                }
                break;
            case Establecimientos_GrandesY_Empresas_EGE:
                if (jdbcTemplateocl.update(sql.toString()) > 0) {
                    regresar = true;
                }
                break;
            default:
                if (jdbcTemplateocl.update(sql.toString()) > 0) {
                    regresar = true;
                }

        }

        return regresar;

    }

    @Override
    public Integer VerificaDesbloqueo(Integer proyecto, String id_ue) {
        int regresar;
        StringBuilder sql;
        super.proyectos = super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", id_ue, Desbloqueo.VerificaDesbloqueo);
        regresar = jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int fila = 0;
                while (rs.next()) {
                    fila = rs.getInt("resultado");
                }
                return fila;
            }
        });
        return regresar;
    }

    public StringBuilder getSql(ProyectosEnum proyectos,String ce, String id_ue, Desbloqueo desbloqueo) {
        StringBuilder sql = new StringBuilder();
        String esquemaPos, esquemaOcl;
        esquemaPos = getEsquemaPostgres(proyectos);
        esquemaOcl = getEsquemaOracle(proyectos);
        switch (proyectos) {
            case Operativo_Masivo:
            case Establecimientos_GrandesY_Empresas_EGE:

                switch (desbloqueo) {
                    case VerificaDesbloqueo:
                        sql.append("SELECT ").append(esquemaPos).append(".verifica_clave_desbloqueo(").append(id_ue).append(") resultado");
                        break;
                    case Desbloqueo:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_PREDIOS set ST_SARE=10 WHERE id_ue='").append(id_ue).append("'");
                        break;
                    case existeUe:
                        sql.append("SELECT count(distinct id_ue) from ").append(esquemaPos).append(".TR_UE_COMPLEMENTO where id_ue='").append(id_ue).append("'");
                        break;
                    case updateUE:
                        sql.append("UPDATE ").append(esquemaPos).append(".TR_UE_COMPLEMENTO set SARE_ST_USR=?, SARE_ST_TIME=current_timestamp, ST_SARE=20 ").append(" where ID_UE='").append(id_ue).append("'");
                        break;
                    case insertUE:
                        sql.append("INSERT INTO ").append(esquemaPos).append(".TR_UE_COMPLEMENTO (SARE_ST_USR,ID_UE, SARE_ST_TIME, ST_SARE, CE) values (?,?,current_timestamp,20,?)");
                        break;
                    case completaGuardado:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_PREDIOS set ST_SARE='01' where id_ue='").append(id_ue).append("'");
                        break;
                    case consultaPendientes:
                        sql.append("SELECT id_ue, tramo_control, cvegeo, cve_ce, cve_ent, nom_ent, cve_mun, nom_mun, cve_loc, nom_loc, cve_ageb, cve_mza, cveft, e08, e09, tipo_e10, substr(nomvial,1,110) nomvial, case when numext ='' then null else numext::numeric end numext, numextalf, e12, e12p, numint, ");
                        sql.append("numintalf, tipo_e14, e14, e14_a, tipo_e10_a, e10_a, tipo_e10_b, e10_b, tipo_e10_c, e10_c, e10_e, descrubic, coord_x::varchar, coord_y::varchar, e19, tipo_e19, punteo, mod_cat, origen, cvegeo2016, oracle, ");
                        sql.append("e20, id_inmueble, id_deftramo, cvevial, e23 FROM ").append(esquemaPos).append(".td_ue_suc where id_ue='").append(id_ue).append("'");
                        break;
                    case DesbloqueoBitacora:
                        sql.append("UPDATE ").append(esquemaPos).append(".TR_UE_COMPLEMENTO set ST_SARE=10 WHERE id_ue='").append(id_ue).append("'");
                        break;

                }
                break;
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                switch (desbloqueo) {
                    case VerificaDesbloqueo:
                        sql.append("SELECT ").append(esquemaPos).append(".verifica_clave_desbloqueo(").append(id_ue).append(") resultado");
                        break;
                    case Desbloqueo:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_UE_SUC set SARE_ST=10 WHERE id_ue=?");
                        break;
                    case existeUe:
                        sql.append("SELECT count(distinct id_ue) from ").append(esquemaOcl).append(".TR_UE_COMPLEMENTO where id_ue=?");
                        break;
                    case updateUE:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_UE_COMPLEMENTO set SARE_ST_USR=?, SARE_ST_TIME=systimestamp where ID_UE=?");
                        break;
                    case insertUE:
                        sql.append("INSERT INTO ").append(esquemaOcl).append(".TR_UE_COMPLEMENTO (SARE_ST_USR,ID_UE, SARE_ST_TIME) values (?,?,systimestamp)");
                        break;
                    case completaGuardado:
                        sql.append("UPDATE ").append(esquemaOcl).append(".TR_UE_SUC set SARE_ST='01' where id_ue=?");
                        break;
                    case consultaPendientes:
                        sql.append("SELECT id_ue, tramo_control, cvegeo, cve_ce, cve_ent, nom_ent, cve_mun, nom_mun, cve_loc, nom_loc, cve_ageb, cve_mza, cveft, e08, e09, tipo_e10, substr(nomvial,1,110) nomvial, case when numext ='' then null else numext::numeric end numext, numextalf, e12, e12p, numint, ");
                        sql.append("numintalf, tipo_e14, e14, e14_a, tipo_e10_a, e10_a, tipo_e10_b, e10_b, tipo_e10_c, e10_c, e10_e, descrubic, coord_x::varchar, coord_y::varchar, e19, tipo_e19, punteo, mod_cat, origen, cvegeo2016, oracle, ");
                        sql.append("e20, id_inmueble, id_deftramo, cvevial, e23 FROM ").append(esquemaPos).append(".td_ue_suc where id_ue=?");
                        break;

                }
                break;
        }
        return sql;

    }

}
