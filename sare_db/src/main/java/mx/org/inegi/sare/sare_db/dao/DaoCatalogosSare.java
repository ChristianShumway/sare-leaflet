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
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
import mx.org.inegi.sare.sare_db.dto.cat_conjunto_comercial;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceCatalogosSare;
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
@Repository("DaoCatalogosSare")
@Profile("jdbc")
public class DaoCatalogosSare extends DaoBusquedaSare implements InterfaceCatalogosSare {
    
    @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    private List<cat_asentamientos_humanos> resultado=new ArrayList<>();
    private List<cat_conjunto_comercial> resultadoCC=new ArrayList<>();
    
    public enum catalogos{
        AsentamientosHumanos, ConjuntoComercial
    }
    
    @Override
    public List<cat_asentamientos_humanos> getCatalogoAsentamientosHumanos(Integer proyecto) throws Exception{
        resultado=new ArrayList<>();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,catalogos.AsentamientosHumanos);

        resultado=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<List<cat_asentamientos_humanos>>() 
        {
            @Override
            public List<cat_asentamientos_humanos> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_asentamientos_humanos fila;
                while(rs.next())
                {
                    fila=new cat_asentamientos_humanos(rs.getInt("id_tipoasen"), rs.getString("descripcion"), rs.getString("tipo_e14"));
                    resultado.add(fila);
                }
                return resultado;
            }
        });
        
       return resultado; 
        
    }
    
    @Override
    public List<cat_conjunto_comercial> getCatalogoConjuntoComercial(Integer proyecto) throws Exception{
        resultadoCC=new ArrayList<>();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,catalogos.ConjuntoComercial);

        resultadoCC=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<List<cat_conjunto_comercial>>() 
        {
            @Override
            public List<cat_conjunto_comercial> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_conjunto_comercial fila;
                while(rs.next())
                {
                    fila=new cat_conjunto_comercial(rs.getString("id_tipocomercial"), rs.getString("descripcion"), rs.getString("tipo_e19"));
                    resultadoCC.add(fila);
                }
                return resultadoCC;
            }
        });
        
       return resultadoCC; 
        
    }

    private StringBuilder getSql(ProyectosEnum proyecto, catalogos catalogo){
        StringBuilder sql = new StringBuilder();
        switch(proyecto)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
                switch(catalogo)
                {
                    case AsentamientosHumanos:
                        sql.append("select '0' id_tipoasen,'Seleccione' descripcion, '00' tipo_e14 union all (SELECT id_tipoasen::text, descripcion, tipo_e14 FROM ").append(schemapg).append(".cat_asentamientos_humanos order by descripcion)");
                    break;
                    case ConjuntoComercial:
                       sql.append("SELECT id_tipocom::text id_tipocomercial, descripcion, tipo_e19 FROM ").append(schemapg).append(".cat_tipo_conjunto_comercial");
                    break;
                }
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
    
    
    
    
    
}
