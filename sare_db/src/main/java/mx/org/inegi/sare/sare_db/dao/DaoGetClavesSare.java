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
import mx.org.inegi.sare.Enums.UnidadesEconomicasEnum;
import mx.org.inegi.sare.sare_db.dto.cat_get_claves;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceClavesSare;
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

@Repository("DaoGetClaves")
@Profile("jdbc")
public class DaoGetClavesSare extends DaoBusquedaSare implements InterfaceClavesSare {
    
    @Autowired    
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
    @Autowired
    @Qualifier("schemaSareOcl")
    private String schemaocl;
    
    UnidadesEconomicasEnum UnidadesEconomicasEnum;
    
    private List<cat_get_claves> resultado1;
    
    @Override
   public List<cat_get_claves> getListadoUnidadesEconomicas(Integer proyecto, String id_ue, String tramo) throws Exception 
   {
        resultado1=new ArrayList<>();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,id_ue,tramo,UnidadesEconomicasEnum.UNIDADES_ECONOMICAS.getCódigo());
        resultado1=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<List<cat_get_claves>>() 
        {
            @Override
            public List<cat_get_claves> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_get_claves fila;
                while(rs.next())
                {
                    fila=new cat_get_claves(rs.getString("id_ue"), rs.getString("c154"));
                    resultado1.add(fila);
                }
                return resultado1;
            }
        });
        
       return resultado1; 
        
    }
   
    @Override
   public List<cat_get_claves> getListadoUnidadesEconomicasBloqueadas(Integer proyecto, String ce, String tramo) throws Exception 
   {
        resultado1=new ArrayList<>();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,ce,tramo,UnidadesEconomicasEnum.UNIDADES_ECONOMICAS_BLOQUEADAS.getCódigo());
        resultado1=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<List<cat_get_claves>>() 
        {
            @Override
            public List<cat_get_claves> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_get_claves fila;
                while(rs.next())
                {
                    fila=new cat_get_claves(rs.getString("id_ue"), rs.getString("sare_st_usr"), rs.getString("sare_st_time"),rs.getString("DIFERENCIA_HORAS"),rs.getString("TIME_LOCK"));
                    resultado1.add(fila);
                }
                return resultado1;
            }
        });
        
       return resultado1; 
        
    }

    private StringBuilder getSql(ProyectosEnum proyecto, String ce, String tramo, String ue){
        StringBuilder sql = new StringBuilder();
        switch(proyecto)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
                sql=getFiltroSql(ce, tramo,ue); 
                break;
            case Construccion:
                break;
            case Convenios:
                break;
            case Muestra_Rural:
                break;
            case Operativo_Masivo:
                break;
            case Organismos_Operadores_De_Agua:
                break;
            case Pesca_Mineria:
                break;
            case Transportes:
                break;
        }    
        return sql;
    }
    
    private StringBuilder getFiltroSql(String ce, String tramo, String ue){
        StringBuilder sql = new StringBuilder();
       if(UnidadesEconomicasEnum.UNIDADES_ECONOMICAS.getCódigo().equals(ue))
       {
                if (ce.equals("00")) 
                {
                   sql.append("SELECT id_ue,c154 FROM ").append(schemaocl).append(".VW_PUNTEO_SARE where sare_st='10' ");
                } 
                else 
                {
                   sql.append("SELECT id_ue,c154 FROM ").append(schemaocl).append(".VW_PUNTEO_SARE where sare_st='10' ");
                   sql.append(" and cestatal=").append(ce).append("and tramo_control=").append(tramo).append("order by 1");
                }

       }else
       { 
           if(UnidadesEconomicasEnum.UNIDADES_ECONOMICAS_BLOQUEADAS.getCódigo().equals(ue))
           {
                if (ce.equals("00")) 
                {
                    sql.append("SELECT id_ue, sare_st_usr, sare_st_time,DIFERENCIA_HORAS,DIFERENCIA_DIAS || ' dias' || ' - ' || TO_CHAR(DIFERENCIA_HORAS, '00') || ':' || "
                    + "TO_CHAR(DIFERENCIA_MINUTOS, '00') || ':' || TO_CHAR(DIFERENCIA_SEGUNDOS, '00') AS TIME_LOCK FROM (SELECT id_ue, sare_st_usr, "
                    + "sare_st_time,TRUNC(MOD((FECHA_UNO - FECHA_DOS) * 24, 24)) DIFERENCIA_HORAS,TRUNC(MOD((FECHA_UNO - FECHA_DOS) * (60 * 24), 60)) "
                    + "DIFERENCIA_MINUTOS,TRUNC(MOD((FECHA_UNO - FECHA_DOS) * (60 * 60 * 24), 60)) DIFERENCIA_SEGUNDOS,TRUNC((FECHA_UNO - FECHA_DOS))"
                    + " DIFERENCIA_DIAS FROM (SELECT id_ue, sare_st_usr, sare_st_time,TO_DATE(LTRIM(FECHA_UNO,'0'),'DD.MM.YYYY HH24:MI:SS') FECHA_UNO,TO_DATE"
                    + "(LTRIM(FECHA_DOS,'0'),'DD.MM.YYYY HH24:MI:SS') FECHA_DOS FROM (SELECT TO_CHAR(SYSTIMESTAMP, 'DD.MM.YYYY HH24:MI:SS') FECHA_UNO,TO_CHAR("
                    + "SARE_ST_TIME, 'DD.MM.YYYY HH24:MI:SS') FECHA_DOS,ue.id_ue, sare_st_usr, sare_st_time FROM ").append(schemaocl).append(".vw_punteo_sare ue inner join ").
                    append(schemaocl).append(".tr_ue_complemento com on ue.id_ue=com.id_ue where ").append(" ue.sare_st='20' and (systimestamp-sare_st_time)>'00 01:00:00'))) order by time_lock desc");
                } else 
                {
                    sql.append("SELECT id_ue, sare_st_usr, sare_st_time,DIFERENCIA_HORAS, DIFERENCIA_DIAS || 'dias' || ' - ' || TO_CHAR(DIFERENCIA_HORAS, '00') || ':' || "
                    + "TO_CHAR(DIFERENCIA_MINUTOS, '00') || ':' || TO_CHAR(DIFERENCIA_SEGUNDOS, '00') AS TIME_LOCK FROM (SELECT id_ue, sare_st_usr, "
                    + "sare_st_time,TRUNC(MOD((FECHA_UNO - FECHA_DOS) * 24, 24)) DIFERENCIA_HORAS,TRUNC(MOD((FECHA_UNO - FECHA_DOS) * (60 * 24), 60)) "
                    + "DIFERENCIA_MINUTOS,TRUNC(MOD((FECHA_UNO - FECHA_DOS) * (60 * 60 * 24), 60)) DIFERENCIA_SEGUNDOS,TRUNC((FECHA_UNO - FECHA_DOS))"
                    + " DIFERENCIA_DIAS FROM (SELECT id_ue, sare_st_usr, sare_st_time,TO_DATE(LTRIM(FECHA_UNO,'0'),'DD.MM.YYYY HH24:MI:SS') FECHA_UNO,TO_DATE"
                    + "(LTRIM(FECHA_DOS,'0'),'DD.MM.YYYY HH24:MI:SS') FECHA_DOS FROM (SELECT TO_CHAR(SYSTIMESTAMP, 'DD.MM.YYYY HH24:MI:SS') FECHA_UNO,TO_CHAR("
                    + "SARE_ST_TIME, 'DD.MM.YYYY HH24:MI:SS') FECHA_DOS,ue.id_ue, sare_st_usr, sare_st_time FROM ").append(schemaocl).append(".vw_punteo_sare ue inner join ").
                    append(schemaocl).append(".tr_ue_complemento com on ue.id_ue=com.id_ue where ue.ce=").append(ce).append(" and ue.sare_st='20' and (systimestamp-sare_st_time)>'00 01:00:00'))) order by time_lock desc");
                       
                }
                       
        
            }
       }
        return sql;
    }
}
