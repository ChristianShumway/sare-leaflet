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
public class DaoValidacionesSare extends DaoTransformaCartografia implements InterfaceValidacionesSare {

    
//    @Autowired
//    @Qualifier("schemaSarePG")
//    private String schema;
    
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
        proyectos=getProyecto(proyecto);
        StringBuilder sql;
        sql=getSql(proyectos,MetodosValida.getCP);
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
        proyectos=getProyecto(proyecto);
        String valida;
        StringBuilder sql;
        sql=getSql(proyectos,MetodosValida.validaCP);
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
    
    private StringBuilder getSql(ProyectosEnum proyecto, MetodosValida valida)
    {
        StringBuilder sql = new StringBuilder();
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
            case RENEM:
            esquemaPos=getEsquemaPostgres(proyecto);
            esquemaOcl=getEsquemaOracle(proyecto);
                switch(valida){
                    case getCP:
                        sql.append("SELECT cve_ent,nom_ent,cp_inicial,cp_final FROM ").append(esquemaPos).append(".cat_codigo_postal where cve_ent=?");
                    break;
                    case validaCP:
                        sql.append("SELECT ").append(esquemaPos).append(".valida_cp_txt(?,?) ");
                    break;
                }
                
            }
        return sql;
    }

    
}


