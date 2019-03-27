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
import mx.org.inegi.sare.sare_db.interfaces.InterfaceValidacionesSare;
import mx.org.inegi.sare.sare_db.dto.cat_codigo_postal;
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
@Repository("DaoValidacionesSare")
@Profile("jdbc")
public class DaoValidacionesSare implements InterfaceValidacionesSare {

    
    @Autowired
    @Qualifier("schemaSarePG")
    private String schema;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
 
    private ProyectosEnum ProyectosEnum;
    
     List<cat_codigo_postal> listaCP = new ArrayList<>(); 
    
    @Override
    public List<cat_codigo_postal> getCP(String cve_ent, Integer proyecto) throws Exception 
    {
        StringBuilder sql;
        sql=getSql(proyecto);
       listaCP= jdbcTemplate.query(sql.toString(),new Object[]{cve_ent}, new ResultSetExtractor<List<cat_codigo_postal>>() 
       {
            @Override
            public List<cat_codigo_postal> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                
                cat_codigo_postal coordenada = null;
                while (rs.next()) 
                {
                    coordenada = new cat_codigo_postal (rs.getString("cve_ent"), rs.getString("nom_ent"),rs.getString("cp_inicial"), rs.getString("cp_final"));

                }
                listaCP.add(coordenada);
                return listaCP;
            }
        });
       return listaCP;
    }
    
    
    private StringBuilder getSql(Integer proyecto)
    {
        StringBuilder sql = new StringBuilder();
            if(Objects.equals(ProyectosEnum.Establecimientos_GrandesY_Empresas_EGE.getCódigo(), proyecto))
            {
                sql.append("SELECT cve_ent,nom_ent,cp_inicial,cp_final FROM ").append(schema).append(".cat_codigo_postal where cve_ent=?");
            }
            else
            {
                if(Objects.equals(ProyectosEnum.Pesca_Mineria.getCódigo(), proyecto))
                {
                    
                }
                else
                {
                    if(Objects.equals(ProyectosEnum.Transportes.getCódigo(), proyecto))
                    {
                    }
                    else
                    {
                       if(Objects.equals(ProyectosEnum.Construccion.getCódigo(), proyecto)) 
                       {
                       }
                       else
                       {
                          if(Objects.equals(ProyectosEnum.Operativo_Masivo.getCódigo(), proyecto))
                          {
                              
                          }
                          else
                          {
                              if(Objects.equals(ProyectosEnum.Muestra_Rural.getCódigo(), proyecto))  
                              {
                              }
                              else
                              {
                                if(Objects.equals(ProyectosEnum.Convenios.getCódigo(), proyecto))
                                {
                                    
                                } 
                                else
                                {
                                    if(Objects.equals(ProyectosEnum.Organismos_Operadores_De_Agua.getCódigo(), proyecto))
                                    {
                                    }
                                } 
                              }
                          }
                       }
                    }
                }
            }
        return sql;
    }
}


