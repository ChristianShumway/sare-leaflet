/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceGuardarUE;
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
@Repository("DaoBackingGuardarSare")
@Profile("jdbc")
public class DaoBackingGuardarSare extends DaoSincronizaSare implements InterfaceGuardarUE{
    
    @Autowired    
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
    @Autowired
    @Qualifier("schemaSareOcl")
    private String schemaocl;
    
     @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    public enum MetodosGuardar 
    {
       getValidaUe, getClaveProvisional, getGuardaUe, getE23A, getidDeftramo
    }
    
    @Override
    public Integer getValidaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        int regresa;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getValidaUe, "");
         regresa=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int regresar = 0;
                int fila=0;
                while(rs.next())
                {
                    regresar = rs.getInt("resultado");
                }
                return regresar;
            }
        });
        return regresa; 
    }
    
    @Override
    public String getClaveProvisional(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, String capa) {
        StringBuilder sql;
        String regresa;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getClaveProvisional,capa);
         regresa=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String regresar = null;
                while(rs.next())
                {
                    regresar = rs.getString("clave");
                }
                return regresar;
            }
        });
        return regresa; 
    }
    
    @Override
    public boolean getGuardaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        boolean regresa;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getGuardaUe, "");
         regresa=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean regresar;
                int fila=0;
                while(rs.next())
                {
                    fila = rs.getInt("resultado");
                }
                regresar=fila>0;
                return regresar;
            }
        });
        return regresa;
    }
    
    
    @Override
    public String e23a(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        String regresa;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getE23A,"");
         regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String regresar = null;
                while(rs.next())
                {
                    regresar = rs.getString("E23A");
                }
                return regresar;
            }
        });
        return regresa; 
    }

    @Override
    public Integer getidDeftramo(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        Integer regresa;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getidDeftramo,"");
         regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                Integer regresar = null;
                while(rs.next())
                {
                    regresar = rs.getInt("id_deftramo");
                }
                return regresar;
            }
        });
        return regresa; 
    }
    public StringBuilder getSql(ProyectosEnum proyecto,cat_vw_punteo_sare_guardado inmueble,MetodosGuardar metodo, String capa)
    {
        StringBuilder sql=new StringBuilder();
        String esquemaPos,esquemaOcl;
        switch(proyecto)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Operativo_Masivo:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
                esquemaPos=getEsquemaPostgres(proyecto);
                esquemaOcl=getEsquemaOracle(proyecto);
                switch(metodo)
                {
                    case getValidaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".valida_ue_sare('")
                                .append(inmueble.getId_UE()).append("','")
                                .append(inmueble.gettramo_control()).append("','")
                                .append(inmueble.getPunteo()).append("','")
                                .append(inmueble.getMod_cat()).append("','")
                                .append(inmueble.getE14_A()).append("','")
                                .append(inmueble.getE03()).append("','")
                                .append(inmueble.getE05()).append("','")
                                .append(inmueble.getE07()).append("','")
                                .append(inmueble.getE11_a()).append("','")
                                .append(inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12()).append("','")
                                .append(inmueble.getE12p()).append("','")
                                .append(inmueble.getE13()).append("','")
                                .append(inmueble.getE13_a()).append("','")
                                .append(inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getE19()).append("','")
                                .append(inmueble.getE20()).append("','")
                                .append(inmueble.getE11()).append("') resultado");
                        break;
                    case getClaveProvisional:
                        sql.append("SELECT ").append(esquemaPos).append(".calcula_cveprov(").append(inmueble.gettramo_control()).append(",").append(capa).append(" ) clave");
                        break;
                    case getGuardaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".registra_ue_sare('").append(inmueble.getId_UE()).append("','")
                                .append(inmueble.getTramo_control()).append("','").append(inmueble.getCvegeo().toUpperCase()).append("','")
                                .append(inmueble.getCE().toUpperCase()).append("','").append(inmueble.getE03().toUpperCase()).append("','")
                                .append(inmueble.getE03N().toUpperCase()).append("','").append(inmueble.getE04().toUpperCase()).append("','")
                                .append(inmueble.getE04N().toUpperCase()).append("','")
                                .append(inmueble.getE05().toUpperCase()).append("','")
                                .append(inmueble.getE05N().toUpperCase()).append("','")
                                .append(inmueble.getE06().toUpperCase()).append("','")
                                .append(inmueble.getE07().toUpperCase()).append("','")
                                .append(inmueble.getCveft()).append("','")
                                .append(inmueble.getE08()).append("','")
                                .append(inmueble.getE09()).append("','")
                                .append(inmueble.getTipo_e10()).append("','")
                                .append(inmueble.getE10()).append("','")
                                .append(inmueble.getE11()).append("','")
                                .append(inmueble.getE11_a()).append("','")
                                .append(inmueble.getE12()).append("','")
                                .append(inmueble.getE12p()).append("','")
                                .append(inmueble.getE13()).append("','")
                                .append(inmueble.getE13_a()).append("','")
                                .append(inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE14()).append("','")
                                .append(inmueble.getE14_A()).append("','")
                                .append(inmueble.getTipo_e10_a()).append("','")
                                .append(inmueble.getE10_A().toUpperCase()).append("','")
                                .append(inmueble.getTipo_e10_b()).append("','")
                                .append(inmueble.getE10_B().toUpperCase()).append("','")
                                .append(inmueble.getTipo_e10_c().toUpperCase()).append("','")
                                .append(inmueble.getE10_C().toUpperCase()).append("','")
                                .append(inmueble.getE10_e().toUpperCase()).append("','")
                                .append(inmueble.getDescrubic().toUpperCase()).append("','")
                                .append(inmueble.getCoordx()).append("','")
                                .append(inmueble.getCoordy()).append("','")
                                .append(inmueble.getE19().toUpperCase()).append("','")
                                .append(inmueble.getTipo_e19() == null?"":inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getPunteo()).append("','")
                                .append(inmueble.getMod_cat()).append("','")
                                .append(inmueble.getOrigen().toUpperCase()).append("','")
                                .append(inmueble.getCvegeo2016().toUpperCase()).append("','")
                                .append(inmueble.getE20().toUpperCase()).append("','")
                                .append(inmueble.getId_deftramo()).append("','")
                                .append(inmueble.getE10_cvevial()).append("','")
                                .append(inmueble.getE23()).append("') resultado");
                        break;
                    case getE23A:
                        sql.append("SELECT E23A FROM ").append(esquemaOcl).append(".VW_PUNTEO_SARE where id_ue = ").append(inmueble.getId_UE()); 
                        break;
                    case getidDeftramo:
                        sql.append("SELECT id_deftramo");
                        sql.append(" FROM ").append(esquemaOcl).append(".VW_PUNTEO_SARE where id_ue = ").append(inmueble.getId_UE());
                        break;
                        
                }
        }
        return sql;
    }
    
    
}
