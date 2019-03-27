/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.Enums.TipoCartografia;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceTransformaCoordenadas;
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
@Repository("DaoTransformaCartografia")
@Profile("dev")
public class DaoTransformaCartografia implements InterfaceTransformaCoordenadas {

    @Autowired
    @Qualifier("schemaSarePG")
    private String schema;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
     cat_coordenadas listaCP; 

    @Override
    public cat_coordenadas TransformaCartografia(Integer proyecto,String tipo, String x, String y) {
        StringBuilder sql;
        sql=getSql(proyecto,tipo);
        String point = "POINT(" + x + " " + y + ")";
       listaCP= jdbcTemplate.query(sql.toString(),new Object[]{point,point}, new ResultSetExtractor<cat_coordenadas>() 
       {
            @Override
            public cat_coordenadas extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                
                cat_coordenadas coordenada = null;
                while (rs.next()) 
                {
                    coordenada = new cat_coordenadas (rs.getString("x"), rs.getString("y"));

                }
                return coordenada;
            }
        });
       return listaCP;
    }
    
    
    private StringBuilder getSql(Integer proyecto,String tipo)
    {
        StringBuilder sql = new StringBuilder();
            if(Objects.equals(ProyectosEnum.Establecimientos_GrandesY_Empresas_EGE.getCódigo(), proyecto))
            {
                if (TipoCartografia.Geografica.getCodigo().equals(tipo)) 
                {
                    sql.append("select x(astext(st_transform(ST_PointFromText(?, 4326),900913))), y(astext(st_transform(ST_PointFromText(?, 4326),900913)))");
                } 
                else if (TipoCartografia.Mercator.getCodigo().equals(tipo)) 
                {
                    sql.append("select x(astext(st_transform(ST_PointFromText(?, 900913),4326))), y(astext(st_transform(ST_PointFromText(?, 900913),4326)))");
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
