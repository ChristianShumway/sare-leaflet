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
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_asentamientos_humanos;
import mx.org.inegi.sare.sare_db.dto.cat_c154;
import mx.org.inegi.sare.sare_db.dto.cat_conjunto_comercial;
import mx.org.inegi.sare.sare_db.dto.cat_piso;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
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
    private List<cat_piso> resultadoCatPiso=new ArrayList<>();

    

    public enum catalogos{
        AsentamientosHumanos, ConjuntoComercial, CodigoScian, CatalogoPiso
    }
    
    @Override
    public List<cat_piso> getCatalogoPiso(Integer proyecto) throws Exception {
       resultadoCatPiso=new ArrayList<>();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,catalogos.CatalogoPiso);

        resultadoCatPiso=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<List<cat_piso>>() 
        {
            @Override
            public List<cat_piso> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_piso fila;
                while(rs.next())
                {
                    fila=new cat_piso(rs.getString("id_tipopiso"), rs.getString("tipo_e12p"), rs.getString("descripcion"), rs.getString("posicion"), rs.getString("nivel"));
                    resultadoCatPiso.add(fila);
                }
                return resultadoCatPiso;
            }
        });
        
       return resultadoCatPiso; 
    }
    
    @Override
    public List<cat_c154> getDatosClasesPorFiltro(Integer proyecto, String cveoper, String codigoScian) {
       StringBuilder sql = new StringBuilder();
       final Object[] params = new Object[1];
       
         params[0]=codigoScian;
         super.proyectos=super.getProyecto(proyecto);
        sql=getSql(super.proyectos,catalogos.CodigoScian);
        
        List<cat_c154> listaCEs = new ArrayList<>();
        listaCEs = jdbcTemplate.query(sql.toString(), params, new ResultSetExtractor<List<cat_c154>>() {
            @Override
            public List<cat_c154> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<cat_c154> FiltroPorClase = new ArrayList<>();

                cat_c154 fila = new cat_c154();
                while (rs.next()) {
                    fila = new cat_c154(rs.getString("codigo"), rs.getString("descripcion_scian"));
                    FiltroPorClase.add(fila);
                }
                rs.close();
                return FiltroPorClase;
            }
        });
        try {
            jdbcTemplate.getDataSource().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoCatalogosSare.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCEs;
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
            case Transportes:
                esquemaPos=getEsquemaPostgres(proyecto);
                esquemaOcl=getEsquemaOracle(proyecto);
                switch(catalogo)
                {
                    case CodigoScian:
                         sql.append("select codigo,descripcion_scian from ").append(esquemaPos).append(".get_tc_scian(?)");
                        break;
                    case AsentamientosHumanos:
                        sql.append("select '0' id_tipoasen,'Seleccione' descripcion, 'Seleccione' tipo_e14 union all (SELECT id_tipoasen::text, descripcion, tipo_e14 FROM ").append(esquemaPos).append(".cat_asentamientos_humanos order by descripcion)");
                    break;
                    case ConjuntoComercial:
                       sql.append("SELECT id_tipocom::text id_tipocomercial, descripcion, tipo_e19 FROM ").append(esquemaPos).append(".cat_tipo_conjunto_comercial");
                    break;
                    case CatalogoPiso:
                    sql.append("SELECT id_tipopiso, tipo_e12p, descripcion, posicion, nivel FROM ").append(esquemaPos).append(".cat_piso order by descripcion");
                    break;   
                }
                break;
            
        }
        return sql;
    }
    
    
    
    
    
}
