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
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
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
    
    @Override
    public List<cat_asentamientos_humanos> getCatalogoAsentamientosHumanos(Integer proyecto) throws Exception{
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos);

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

    private StringBuilder getSql(ProyectosEnum proyecto){
        StringBuilder sql = new StringBuilder();
        switch(proyecto)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
                sql.append("SELECT cve_ent,nom_ent,cp_inicial,cp_final FROM ").append(schemapg).append(".cat_codigo_postal where cve_ent=?");
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
