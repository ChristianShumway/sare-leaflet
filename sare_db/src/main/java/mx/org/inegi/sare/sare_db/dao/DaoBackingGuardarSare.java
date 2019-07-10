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
        sql=getSql(proyectos,inmueble,MetodosGuardar.getValidaUe, "",false);
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
        sql=getSql(proyectos,inmueble,MetodosGuardar.getClaveProvisional,capa,false);
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
    public boolean getGuardaUe(Integer proyecto, cat_vw_punteo_sare_guardado inmueble, boolean isAlta) {
        StringBuilder sql;
        double regresa=0L;
        boolean regresar;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getGuardaUe, "", isAlta);
        try{
         regresa=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<Double>() {
            @Override
            public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
                double regresar;
                double fila=0;
                while(rs.next())
                {
                    fila = rs.getDouble("resultado");
                    
                }
                regresar=fila;
                return regresar;
            }
        });
        }
         catch(Exception e){
             regresa=0L;
         }
         inmueble.setId_UE(String.valueOf(regresa));
         regresar=regresa>0L;
        return regresar;
    }
    
    
    @Override
    public String e23a(Integer proyecto, cat_vw_punteo_sare_guardado inmueble) {
        StringBuilder sql;
        String regresa;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,inmueble,MetodosGuardar.getE23A,"",false);
         regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String regresar = "";
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
        sql=getSql(proyectos,inmueble,MetodosGuardar.getidDeftramo,"",false);
         regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                Integer regresar = 0;
                while(rs.next())
                {
                    regresar = rs.getInt("id_deftramo");
                }
                return regresar;
            }
        });
        return regresa; 
    }
    public StringBuilder getSql(ProyectosEnum proyecto,cat_vw_punteo_sare_guardado inmueble,MetodosGuardar metodo, String capa, boolean isAlta)
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
                                .append(inmueble.getId_UE()==null?"":inmueble.getId_UE()).append("','")
                                .append(inmueble.gettramo_control()==null?"":inmueble.gettramo_control()).append("','")
                                .append(inmueble.getPunteo()==null?"":inmueble.getPunteo()).append("','")
                                .append(inmueble.getMod_cat()==null?Integer.valueOf(null):Integer.valueOf(inmueble.getMod_cat())).append("','")
                                .append(inmueble.getE14_A()==null?"":inmueble.getE14_A()).append("','")
                                .append(inmueble.getE03()==null?"":inmueble.getE03()).append("','")
                                .append(inmueble.getE05()==null?"":inmueble.getE05()).append("','")
                                .append(inmueble.getE07()==null?"":inmueble.getE07()).append("','")
                                .append(inmueble.getE11_a()==null?"":inmueble.getE11_a()).append("','")
                                .append(inmueble.getTipo_E14()==null?"":inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12()==null?"":inmueble.getTipo_E14()).append("','")
                                .append(inmueble.getE12p()==null?"":inmueble.getE12p()).append("','")
                                .append(inmueble.getE13()==null?"":inmueble.getE13()).append("','")
                                .append(inmueble.getE13_a()==null?"":inmueble.getE13_a()).append("','")
                                .append(inmueble.getTipo_e19()==null?"":inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getE19()==null?"":inmueble.getE19()).append("','")
                                .append(inmueble.getE20()==null?"":inmueble.getE20()).append("','")
                                .append(inmueble.getE11()==null?"":inmueble.getE11()).append("') resultado");
                        break;
                    case getClaveProvisional:
                        sql.append("SELECT ").append(esquemaPos).append(".calcula_cveprov('").append(inmueble.gettramo_control()).append("','").append(capa).append("') clave");
                        break;
                    case getGuardaUe:
                        sql.append("SELECT ").append(esquemaPos).append(".registra_ue_sare('").append(inmueble.getId_UE()).append("','")
                                .append(inmueble.getTramo_control()).append("','").append(inmueble.getCvegeo().toUpperCase()).append("','")
                                .append(inmueble.getCE().toUpperCase()).append("','").append(inmueble.getE03().toUpperCase()).append("','")
                                .append(inmueble.getE03N().toUpperCase()!=null?inmueble.getE03N().toUpperCase():"").append("','").append(inmueble.getE04().toUpperCase()).append("','")
                                .append(inmueble.getE04N().toUpperCase()!=null?inmueble.getE04N().toUpperCase():"").append("','")
                                .append(inmueble.getE05().toUpperCase()!=null?inmueble.getE05().toUpperCase():"").append("','")
                                .append(inmueble.getE05N().toUpperCase()!=null?inmueble.getE05N().toUpperCase():"").append("','")
                                .append(inmueble.getE06().toUpperCase()!=null?inmueble.getE06().toUpperCase():"").append("','")
                                .append(inmueble.getE07().toUpperCase()!=null?inmueble.getE07().toUpperCase():"").append("','")
                                .append(inmueble.getCveft()!=null?inmueble.getCveft():"").append("','")
                                .append(inmueble.getE08()!=null?inmueble.getE08():"").append("','")
                                .append(inmueble.getE09()!=null?inmueble.getE09():"").append("','")
                                .append(inmueble.getTipo_e10()!=null?inmueble.getTipo_e10():"").append("','")
                                .append(inmueble.getE10()!=null?inmueble.getE10():"").append("','")
                                .append(inmueble.getE11()!=null?inmueble.getE11():"").append("','")
                                .append(inmueble.getE11_a()!=null?inmueble.getE11_a():"").append("','")
                                .append(inmueble.getE12()!=null?inmueble.getE12():"").append("','")
                                .append(inmueble.getE12p()!=null?inmueble.getE12p():"").append("','")
                                .append(inmueble.getE13()!=null?inmueble.getE13():"").append("','")
                                .append(inmueble.getE13_a()!=null?inmueble.getE13_a():"").append("','")
                                .append(inmueble.getTipo_E14()!=null?inmueble.getTipo_E14():"").append("','")
                                .append(inmueble.getE14()!=null?inmueble.getE14():"").append("','")
                                .append(inmueble.getE14_A()!=null?inmueble.getE14_A():"").append("','")
                                .append(inmueble.getTipo_e10_a()!=null?inmueble.getTipo_e10_a():"").append("','")
                                .append(inmueble.getE10_A().toUpperCase()!=null?inmueble.getE10_A().toUpperCase():"").append("','")
                                .append(inmueble.getTipo_e10_b()!=null?inmueble.getTipo_e10_b():"").append("','")
                                .append(inmueble.getE10_B().toUpperCase()!=null?inmueble.getE10_B().toUpperCase():"").append("','")
                                .append(inmueble.getTipo_e10_c().toUpperCase()!=null?inmueble.getTipo_e10_c().toUpperCase():"").append("','")
                                .append(inmueble.getE10_C().toUpperCase()!=null?inmueble.getE10_C().toUpperCase():"").append("','")
                                .append(inmueble.getE10_e().toUpperCase()!=null?inmueble.getE10_e().toUpperCase():"").append("','")
                                .append(inmueble.getDescrubic().toUpperCase()!=null?inmueble.getDescrubic().toUpperCase():"").append("','")
                                .append(inmueble.getCoordx()).append("','")
                                .append(inmueble.getCoordy()).append("','")
                                .append(inmueble.getE19().toUpperCase()!=null?inmueble.getE19().toUpperCase():"").append("','")
                                .append(inmueble.getTipo_e19() == null?"":inmueble.getTipo_e19()).append("','")
                                .append(inmueble.getPunteo()!=null?inmueble.getPunteo():"").append("','")
                                .append(inmueble.getMod_cat()!=null?inmueble.getMod_cat():"").append("','")
                                .append(inmueble.getOrigen().toUpperCase()).append("','")
                                .append(inmueble.getCvegeo2016().toUpperCase()).append("','")
                                .append(inmueble.getE20().toUpperCase()).append("','")
                                .append(inmueble.getId_deftramo()).append("','")
                                .append(inmueble.getE10_cvevial()!=null?inmueble.getE10_cvevial():"").append("','")
                                .append(inmueble.getE23()!=null?inmueble.getE23():"").append("','").append(isAlta).append("') resultado");
                        break;
                    case getE23A:
                        sql.append("SELECT E23A FROM ").append(esquemaOcl).append(".tr_etq_val where id_ue = ").append(inmueble.getId_UE()); 
                        break;
                    case getidDeftramo:
                        sql.append("SELECT id_deftramo");
                        sql.append(" FROM ").append(esquemaOcl).append(".tr_inmuebles where id_inmueble = ").append(inmueble.getId_inmueble());
                        break;
                        
                }
        }
        return sql;
    }
    
    
}
