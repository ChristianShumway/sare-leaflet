/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.Enums.TipoCartografia;
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceTransformaCoordenadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Repository("DaoTransformaCartografia")
public class DaoTransformaCartografia implements InterfaceTransformaCoordenadas {

//    @Autowired
//    @Qualifier("schemaSarePG")
//    private String schema;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
     cat_coordenadas listaCP; 
     ProyectosEnum proyectos;
    @Override
    public cat_coordenadas TransformaCartografia(Integer proyecto,String tipo, String x, String y) {
        StringBuilder sql;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,tipo);
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
    
    
    private StringBuilder getSql(ProyectosEnum proyecto,String tipo)
    {
        StringBuilder sql = new StringBuilder();
            switch(proyecto){
                case Establecimientos_GrandesY_Empresas_EGE:
                case Construccion:
                case Convenios:
                case Muestra_Rural:
                case Operativo_Masivo:
                case Organismos_Operadores_De_Agua:
                case Pesca_Mineria:
                case RENEM:
                case MasivoOtros:
                    if (TipoCartografia.Geografica.getCodigo().equals(tipo)) 
                {
                    sql.append("select x(astext(st_transform(ST_PointFromText(?, 4326),900913))), y(astext(st_transform(ST_PointFromText(?, 4326),900913)))");
                } 
                else if (TipoCartografia.Mercator.getCodigo().equals(tipo)) 
                {
                    sql.append("select x(astext(st_transform(ST_PointFromText(?, 900913),4326))), y(astext(st_transform(ST_PointFromText(?, 900913),4326)))");
                }
            }
        return sql;
    }
    
    public ProyectosEnum getProyecto(Integer proyecto)
      {
          ProyectosEnum ProyectosEnume;
          switch(proyecto)
          {
              case 1:
                  ProyectosEnume=ProyectosEnum.Establecimientos_GrandesY_Empresas_EGE;
                  break;
              case 2:
                  ProyectosEnume=ProyectosEnum.Construccion;
                  break;
              case 3:
                  ProyectosEnume=ProyectosEnum.Convenios;
                  break;
              case 4:   
                  ProyectosEnume=ProyectosEnum.Muestra_Rural;
                  break;
              case 5:
                  ProyectosEnume=ProyectosEnum.Operativo_Masivo;
                  break;
              case 6:
                  ProyectosEnume=ProyectosEnum.Organismos_Operadores_De_Agua;
                  break;
              case 7:
                  ProyectosEnume=ProyectosEnum.Pesca_Mineria;
                  break;
              case 8:
                  ProyectosEnume=ProyectosEnum.RENEM;
                  break;
              case 9:
                  ProyectosEnume=ProyectosEnum.MasivoOtros;
                  break;
              default:
                  ProyectosEnume=ProyectosEnum.Establecimientos_GrandesY_Empresas_EGE;
                  break;
              
          }
          return ProyectosEnume;
      }
    
    
    public String getEsquemaPostgres(ProyectosEnum proyecto)
      {
          String esquema="";
          switch(proyecto){
              case Establecimientos_GrandesY_Empresas_EGE:
                  esquema="sare_ege2019_act";
              break;
              case Construccion:
                  esquema="sare_mas2019_act";
              break;
            case Convenios:
                 esquema="sare_mas2019_act";
              break;
            case Muestra_Rural:
                 esquema="sare_mas2019_act";
              break;
            case Operativo_Masivo:
                 esquema="sare_mas2019_act";
              break;
            case Organismos_Operadores_De_Agua:
                 esquema="sare_mas2019_act";
              break;
            case Pesca_Mineria:
                 esquema="sare_mas2019_act";
              break;
            case RENEM:
                 esquema="sare_mas2019_act";
              break;
            case MasivoOtros:
                esquema="sare_mas2019_act";
                break;
            default:
                esquema="sare_mas2019_act";
          }
          return esquema;
      }
      
       public String getEsquemaOracle(ProyectosEnum proyecto)
      {
          String esquema="";
          switch(proyecto)
          {
              case Establecimientos_GrandesY_Empresas_EGE:
                  esquema="ce2019_masrencal";
              break;
            case Construccion:
                esquema="";
              break;
            case Convenios:
                esquema="";
              break;
            case Muestra_Rural:
                esquema="";
              break;
            case Operativo_Masivo:
                esquema="ce2019_masrencal";
              break;
            case Organismos_Operadores_De_Agua:
                esquema="";
              break;
            case Pesca_Mineria:
                esquema="";
              break;
            case RENEM:
                esquema="ce2019_masrencal";
              break;
            case MasivoOtros:
                esquema="ce2019_masrencal";
              break;
          }
        return esquema;
      }

}
