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
import mx.org.inegi.sare.sare_db.dto.cat_IdentifyVialDTO;
import mx.org.inegi.sare.sare_db.dto.cat_InfoInmueblesDTO;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceListUEbyXY;
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
@Repository("DaoBackingListUEbyXY")
@Profile("jdbc")
public class DaoBackingListUEbyXY extends DaoBusquedaSare implements InterfaceListUEbyXY{
    
    @Autowired
    @Qualifier("jdbcTemplatemdm")
    private JdbcTemplate jdbcTemplatemdm;

    @Autowired
    @Qualifier("schemaSaremdm")
    private String schemamdm;
    
//    @Autowired
//    @Qualifier("schemaSarePG")
//    private String schemapg;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
     @Autowired    
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
//    @Autowired
//    @Qualifier("schemaSareOcl")
//    private String schemaocl;

    public enum MetodosUEbyXY 
    {
        getInfoUEDenue,getInfoUE,getInfoVialidad,getActividadUE
    }
    
    @Override
    public List<cat_InfoInmueblesDTO> getInfoUEDenue(Integer proyecto, String x, String y) {
        
        List<cat_InfoInmueblesDTO> regresa=new ArrayList<cat_InfoInmueblesDTO>();;
        StringBuilder sql;
        proyectos=getProyecto(proyecto);
        sql = getSql(proyectos,x,y,"",MetodosUEbyXY.getInfoUEDenue);
        switch(proyectos){
            case Operativo_Masivo:
//                regresa=execSqlInfoUEDenuePg(sql);
//                break;
            case Establecimientos_GrandesY_Empresas_EGE:
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case UEEPA:
              regresa=execSqlInfoUEDenueMdm(sql); 
            break;
                
        }
        return regresa;
    }
    
    private List<cat_InfoInmueblesDTO> execSqlInfoUEDenueMdm(StringBuilder sql){
        final List<cat_InfoInmueblesDTO> regresa=new ArrayList<cat_InfoInmueblesDTO>();;
        jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<ArrayList<String>>() 
        {
            @Override
            public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                ArrayList<String> fila = null;
                cat_InfoInmueblesDTO row = null;
                while (rs.next()) 
                {
                    row = new cat_InfoInmueblesDTO(rs.getString(1), rs.getString(2).replaceAll("'", "&#39;"), rs.getString(3).replaceAll("'", "&#39;"), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18));
                    regresa.add(row);
                }
                return fila;
            }
        });
        return regresa;
    }
    
    private List<cat_InfoInmueblesDTO> execSqlInfoUEDenuePg(StringBuilder sql){
        final List<cat_InfoInmueblesDTO> regresa=new ArrayList<cat_InfoInmueblesDTO>();;
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<ArrayList<String>>() 
        {
            @Override
            public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                ArrayList<String> fila = null;
                cat_InfoInmueblesDTO row = null;
                while (rs.next()) 
                {
                    row = new cat_InfoInmueblesDTO(rs.getString(1), rs.getString(2).replaceAll("'", "&#39;"), rs.getString(3).replaceAll("'", "&#39;"), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18));
                    regresa.add(row);
                }
                return fila;
            }
        });
        return regresa;
    }

    @Override
    public List<cat_InfoInmueblesDTO> getInfoUE(Integer proyecto, String x, String y, String tipo) {
        final List<cat_InfoInmueblesDTO> regresa=new ArrayList<cat_InfoInmueblesDTO>();;
        StringBuilder sql;
        proyectos=getProyecto(proyecto);
        sql = getSql(proyectos,x,y,tipo,MetodosUEbyXY.getInfoUE);
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<ArrayList<String>>() 
        {
            @Override
            public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                ArrayList<String> fila = null;
                cat_InfoInmueblesDTO row = null;
                while (rs.next()) 
                {
                   row = new cat_InfoInmueblesDTO(rs.getString(1), rs.getString(2).replaceAll("'", "&#39;"), rs.getString(3).replaceAll("'", "&#39;"), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18));
                row.setActividad(getActividadUE(rs.getString(1)));
                regresa.add(row);
                }
                return fila;
            }
        });
        return regresa;
    }

    @Override
    public List<cat_IdentifyVialDTO> getInfoVialidad(Integer proyecto, String x, String y) {
        final List<cat_IdentifyVialDTO> regresa=new ArrayList<cat_IdentifyVialDTO>();;
        StringBuilder sql;
        proyectos=getProyecto(proyecto);
        sql = getSql(proyectos,x,y,"",MetodosUEbyXY.getInfoVialidad);
        jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<ArrayList<String>>() 
        {
            @Override
            public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                ArrayList<String> fila = null;
                cat_IdentifyVialDTO row = null;
                while (rs.next()) 
                {
                   row = new cat_IdentifyVialDTO(rs.getString(1), rs.getString(2));
                   regresa.add(row);
                }
                return fila;
            }
        });
        return regresa;
    }
    
    private String getActividadUE(String cve_unica){
        
        String regresa;
        StringBuilder sql;

        sql = getSql(null,"","","",MetodosUEbyXY.getActividadUE);
        regresa=jdbcTemplate.query(sql.toString(),new Object[]{cve_unica}, new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String actividad = null;
                while (rs.next()) 
                {
                   actividad = rs.getString(1);

                }
                return actividad;
            }
        });
        return regresa;
        
    }
    
    private StringBuilder getSql(ProyectosEnum proyecto,String x, String y, String tipo, MetodosUEbyXY MetodosUEbyXY){
        StringBuilder sql=new StringBuilder();
        String esquemaPos,esquemaOcl;
        String buffer = "5";
        String point = "POINT(" + x + " " + y + ")";
        String e23 = tipo.equals("M") ? "'M','U'" : "'S'";
        switch(proyecto){
            case Establecimientos_GrandesY_Empresas_EGE:
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Operativo_Masivo:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case UEEPA:
                esquemaPos=getEsquemaPostgres(proyecto);
                esquemaOcl=getEsquemaOracle(proyecto);
                switch(MetodosUEbyXY){
                    case getInfoUEDenue:
                       sql.append("SELECT d_llave_r,case when nom_est is null then '' else nom_est end nom_est, case when raz_soc is null then '' else raz_soc end raz_soc,desc_scian,cve_ent,cve_mun,cve_loc,cve_ageb,manzana,tipovial,nomvial,numextnum,numextalf,numintnum,numintalf,tipoasen,nomasen,cor_indust FROM denue2017.denue WHERE ST_intersects(st_buffer(geomfromtext('").append(point).append("',900913),").append(buffer).append("),the_geom)"); 
                       break;
                    case getInfoUE:
                        sql.append("select id_ue, case when e08 is null then '' else e08 end e08, case when e09 is null then '' else e09 end e09,'' desc_scian,cve_ent,cve_mun,cve_loc,cve_ageb,cve_mza,tipo_e10,nomvial,numext,numextalf,numint,numintalf,descripcion,e14,e19 from(");
                        sql.append("SELECT id_ue, e08, e09,'' desc_scian,cve_ent,cve_mun,cve_loc,cve_ageb,cve_mza,tipo_e10,nomvial,numext,numextalf,numint,numintalf,descripcion,e14,e19 ");
                        sql.append("FROM ").append(esquemaPos).append(".td_inmuebles a left join  ").append(esquemaPos).append(".cat_asentamientos_humanos b on a.tipo_e14=b.tipo_e14 WHERE (baja=0 or baja is null) and ST_intersects(st_buffer(geomfromtext('").append(point).append("',900913),").append(buffer).append("),the_geom_merc) and e23 in(").append(e23).append(") ) inmuebles ");
                        break;
                    case getInfoVialidad:
                        sql.append("SELECT tipovial,nomvial,ST_Distance(the_geom,geomfromtext('").append(point).append("',900913)) distancia FROM ").append(schemamdm).append(".e WHERE ST_intersects(st_buffer(geomfromtext('").append(point).append("',900913),").append(buffer).append("),the_geom) order by distancia limit 1");
                        break;
                    case getActividadUE:
                        sql.append("select d_descripcion from ").append(esquemaOcl).append(".TR_UE_SUC a inner join TC_SCIAN b on a.e17=b.e17 where id_ue=?");
                        break;
                }
                
        }
        return sql;
    }
    
    
    
    
    
}
