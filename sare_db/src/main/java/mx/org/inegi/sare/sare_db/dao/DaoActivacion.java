/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_bitacora_activacion_id_unidad_economica;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceActivacion;
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
@Repository("DaoActivacion")
@Profile("jdbc")
public class DaoActivacion extends DaoBusquedaSare implements InterfaceActivacion {
    
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

    
    
    public enum MetodosActivacion
    {
       ActivaClaveUnicaInmuebles,ActivaClaveUnicaUESUC,ActivaClaveUnicaUECOMPLEMENTO, ActivaClaveUnicaUEPG
    }

    @Override
    public boolean activaCveUnicaInmuebles(Integer proyecto, String id_ue) {
        boolean regresa=false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(proyectos,MetodosActivacion.ActivaClaveUnicaInmuebles);
        if(jdbcTemplateocl.update(sql.toString(),new Object[]{id_ue})>0)
        {
          regresa=true; 
        }
        
        return regresa;
    }
    @Override
    public boolean activaCveUnicaUESuc(Integer proyecto, String id_ue) {
        boolean regresa=false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(proyectos,MetodosActivacion.ActivaClaveUnicaUESUC);
        if(jdbcTemplateocl.update(sql.toString(),new Object[]{id_ue})>0)
        {
          regresa=true; 
        }
        
        return regresa;
    }
    @Override
    public boolean activaCveUnicaUEcomplemento(Integer proyecto, String id_ue) {
        boolean regresa=false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(proyectos,MetodosActivacion.ActivaClaveUnicaUECOMPLEMENTO);
        if(jdbcTemplateocl.update(sql.toString(),new Object[]{id_ue})>0)
        {
          regresa=true; 
        }
        
        return regresa;
    }
    @Override
    public int activaCveUnicaUEPG(Integer proyecto, cat_bitacora_activacion_id_unidad_economica cat_bitacora_activacion) {
        int regresa = 0;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
        sql = getSql(proyectos,MetodosActivacion.ActivaClaveUnicaUEPG);
        regresa=jdbcTemplate.query(sql.toString(),new Object[]{cat_bitacora_activacion.getCve_unica(),cat_bitacora_activacion.getUsuario(),cat_bitacora_activacion.getIp()}, new ResultSetExtractor<Integer>() 
        {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
               int fila = 0;
                while (rs.next()) 
                {
                    fila=rs.getInt("activa");
                }
                return fila;
            }
        });
        return regresa;
    }
    public StringBuilder getSql(ProyectosEnum proyectos, MetodosActivacion metodo)
    {
        StringBuilder sql=new StringBuilder();
        switch(proyectos)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
                switch(metodo)
                {
                    case ActivaClaveUnicaInmuebles:
                      sql.append("update ").append(schemaocl).append(".TR_INMUEBLES  set baja=1 where ID_UE=?");
                      break;
                    case ActivaClaveUnicaUESUC:
                      sql.append("UPDATE ").append(schemaocl).append(".TR_UE_SUC set SARE_ST='10' where ID_UE=?");
                      break;
                    case ActivaClaveUnicaUECOMPLEMENTO:
                      sql.append("UPDATE ").append(schemaocl).append(".TR_UE_COMPLEMENTO set SARE_ST_USR=NULL, SARE_ST_TIME=NULL where ID_UE=?");
                      break;
                    case ActivaClaveUnicaUEPG:
                      sql.append("select ").append(schemapg).append(".activa_cve_unica(?,?,?) activa");
                      break;
                }
            break;
        }
        return sql;
    }
    
    
    
}