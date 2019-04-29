/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_get_claves;
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
    @Qualifier("schemaSareOcl")
    private String schemaocl;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;
    
     public enum Desbloqueo{
        Desbloqueo,VerificaDesbloqueo,completaGuardado, existeUe, updateUE, insertUE
    }

    @Override
    public boolean Desbloqueo(Integer proyecto, String id_ue) {
        boolean regresar;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,Desbloqueo.Desbloqueo);
        regresar=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila=false;
                while(rs.next())
                {
                   fila=rs.getInt(1)>1;
                }
                return fila;
            }
        });
        return regresar;
    }

    @Override
    public boolean completaGuardadoOcl(Integer proyecto,String usuario, String id_ue) {
        boolean regresar=false;
        if(existeUe(proyecto, id_ue)){
            if(CompletaSaveUE(proyecto,id_ue)){
                regresar=UpdateUE(proyecto,id_ue);
            }
        }
        else{
            if(CompletaSaveUE(proyecto,id_ue)){
                regresar=InsertUe(proyecto,usuario, id_ue);
            } 
        }
        return regresar;
    }
    
   
    
    private boolean existeUe(Integer proyecto, String id_ue){
        boolean regresar;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,Desbloqueo.existeUe);
        regresar=jdbcTemplateocl.query(sql.toString(),new Object[]{id_ue},new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila=false;
                while(rs.next())
                {
                   fila=rs.getInt(1)>1;
                }
                return fila;
            }
        });
        return regresar;
    }
    
    private boolean UpdateUE(Integer proyecto, String id_ue){
        boolean regresar;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,Desbloqueo.updateUE);
        regresar=jdbcTemplateocl.query(sql.toString(),new Object[]{id_ue},new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila=false;
                while(rs.next())
                {
                   fila=rs.getInt(1)>1;
                }
                return fila;
            }
        });
        return regresar;
        
    }
    
    private boolean InsertUe(Integer proyecto,String usuario, String id_ue){
        boolean regresar;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,Desbloqueo.insertUE);
        regresar=jdbcTemplateocl.query(sql.toString(),new Object[]{id_ue,usuario},new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila=false;
                while(rs.next())
                {
                   fila=rs.getInt(1)>1;
                }
                return fila;
            }
        });
        return regresar;
        
    }
    
    private boolean CompletaSaveUE(Integer proyecto, String id_ue){
        boolean regresar;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,Desbloqueo.completaGuardado);
        regresar=jdbcTemplateocl.query(sql.toString(),new Object[]{id_ue},new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila=false;
                while(rs.next())
                {
                   fila=rs.getInt(1)>1;
                }
                return fila;
            }
        });
        return regresar;
        
    }
    
    

    @Override
    public Integer VerificaDesbloqueo(Integer proyecto,String id_ue) {
        int regresar;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,Desbloqueo.VerificaDesbloqueo);
        regresar=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<Integer>() 
        {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                int fila=0;
                while(rs.next())
                {
                   fila=rs.getInt("resultado");
                }
                return fila;
            }
        });
        return regresar;
    }
    
    public StringBuilder getSql(ProyectosEnum proyectos, String id_ue, Desbloqueo desbloqueo){
        StringBuilder sql=new StringBuilder();
        switch(proyectos){
            case Establecimientos_GrandesY_Empresas_EGE:
                switch(desbloqueo){
                    case VerificaDesbloqueo:
                        sql.append("SELECT ").append(schemapg).append(".verifica_clave_desbloqueo(").append(id_ue).append(") resultado");
                    break;
                    case Desbloqueo:
                        sql.append("UPDATE ").append(schemaocl).append(".TR_UE_SUC set SARE_ST=10 WHERE id_ue=?");
                    break;
                    case existeUe:
                        sql.append("SELECT count(distinct id_ue) from ").append(schemaocl).append(".TR_UE_COMPLEMENTO where id_ue=?");
                    break;
                    case updateUE:
                        sql.append("UPDATE ").append(schemaocl).append(".TR_UE_COMPLEMENTO set SARE_ST_USR=?, SARE_ST_TIME=systimestamp where ID_UE=?");
                    break;
                    case insertUE:
                        sql.append("INSERT INTO ").append(schemaocl).append(".TR_UE_COMPLEMENTO (SARE_ST_USR,ID_UE, SARE_ST_TIME) values (?,?,systimestamp)");
                    break;
                    case completaGuardado:
                        sql.append("UPDATE ").append(schemaocl).append(".TR_UE_SUC set SARE_ST='01' where id_ue=?");
                    break;
                        
                }
            break;
        }
        return sql;
        
    }
    
    
    
}
