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
    @Qualifier("schemaSare")
    private String schemapg;
    
    public enum Desbloqueo{
        Desbloqueo
    }
    
    

    @Override
    public boolean VerificaDesbloqueo(Integer proyecto,String id_ue) {
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
                   fila=rs.getInt("resultado")>1;
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
                    case Desbloqueo:
                        sql.append("SELECT ").append(schemapg).append(".verifica_clave_desbloqueo(?)");
                    break;
                }
            break;
        }
        return sql;
        
    }
    
    
    
}
