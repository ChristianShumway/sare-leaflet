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
    
 
     public enum MetodosValida
    {
        getCP,validaCP
    }
    
    private ProyectosEnum ProyectosEnum;
    
     List<cat_codigo_postal> listaCP = new ArrayList<>(); 
    
    @Override
    public List<cat_codigo_postal> getCP(String cve_ent, Integer proyecto) throws Exception 
    {
        listaCP=new ArrayList<>();
        StringBuilder sql;
        sql=getSql(proyecto,MetodosValida.getCP);
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
    
    
    @Override
    public String isValidCpMsj(String CP, String entidad, Integer proyecto) throws Exception {
        String valida;
        StringBuilder sql;
        sql=getSql(proyecto,MetodosValida.validaCP);
       valida= jdbcTemplate.query(sql.toString(),new Object[]{CP,entidad}, new ResultSetExtractor<String>() 
       {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                
                String valida = null;
                while (rs.next()) 
                {
                    valida=rs.getString(1);

                }
                
                return valida;
            }
        });
       return valida;
    }
    
    private StringBuilder getSql(Integer proyecto, MetodosValida valida)
    {
        StringBuilder sql = new StringBuilder();
            if(Objects.equals(ProyectosEnum.Establecimientos_GrandesY_Empresas_EGE.getCódigo(), proyecto))
            {
                switch(valida){
                    case getCP:
                        sql.append("SELECT cve_ent,nom_ent,cp_inicial,cp_final FROM ").append(schema).append(".cat_codigo_postal where cve_ent=?");
                    break;
                    case validaCP:
                        sql.append("SELECT ").append(schema).append(".valida_cp_txt(?,?) ");
                    break;
                }
                
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


