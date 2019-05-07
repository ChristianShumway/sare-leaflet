/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import static mx.org.inegi.sare.Enums.ProyectosEnum.Establecimientos_GrandesY_Empresas_EGE;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceBusquedaSare;
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
@Repository("DaoBusqueda")
@Profile("jdbc")
public class DaoBusquedaSare extends DaoTransformaCartografia implements InterfaceBusquedaSare {

    @Autowired    
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
    @Autowired
    @Qualifier("schemaSareOcl")
    private String schemaocl;
    
    @Autowired
    @Qualifier("jdbcTemplatemdm")
    private JdbcTemplate jdbcTemplatemdm;

    @Autowired
    @Qualifier("schemaSaremdm")
    private String schemamdm;
    
     @Autowired
    @Qualifier("schemaSarePG")
    private String schemapgEge;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    private String campo_geo = "the_geom";

    public String esquemaPg;

    
     public enum MetodosBusqueda 
    {
        BUSQUEDAOCL,GETCLAVESPG, GETDATOSINMUEBLES,GETEXTENTCVEGEO,GETEXTENTCVEGEO2,GETNOMBREBUSQUEDA,GETNOMBREBUSQUEDAOCL,LIBERACLAVEUNICAORACLE,GETVALCOORGEO
    }
    public class MetodosBusquedaClass
    {
        MetodosBusqueda metodo;
        
        public MetodosBusquedaClass(MetodosBusqueda metodo)
        {
            this.metodo=metodo;
        }
    }
    public class Proyectos
    {
        ProyectosEnum proyectos;
        
        public Proyectos(ProyectosEnum proyectos)
        {
            this.proyectos=proyectos;
        }

        public Proyectos() {
        }
        
    }
    ProyectosEnum proyectos;
    
    List<cat_vw_punteo_sare> resultado=new ArrayList<>();
    boolean fsearch = true;
    Integer position = 0;
    Integer breakCve = 5;
    
    @Override
    public List<cat_vw_punteo_sare> busqueda(Integer proyecto, String tramo, String ce, String usuario, String id_ue) 
    {
        resultado=new ArrayList<>();
        StringBuilder sql;
        proyectos=getProyecto(proyecto);
        sql=getSql(null,0,"",null,"",proyectos,ce,id_ue,MetodosBusqueda.BUSQUEDAOCL);
        resultado=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<List<cat_vw_punteo_sare>>() 
        {
            @Override
            public List<cat_vw_punteo_sare> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_vw_punteo_sare fila;
                while(rs.next())
                {
                    fila = new cat_vw_punteo_sare(
                        rs.getString("cestatal")!=null?rs.getString("cestatal"):"",
                        rs.getString("coorx")!=null?new BigDecimal(rs.getString("coorx").replace(",", ".")):new BigDecimal(0),
                        rs.getString("coory")!=null?new BigDecimal(rs.getString("coory").replace(",",".")):new BigDecimal(0), 
                        rs.getString("c154")!=null?rs.getString("c154"):"",
                        rs.getString("descrubic")!=null?rs.getString("descrubic"):"",
                        rs.getString("e03")!=null?rs.getString("e03"):"",
                        rs.getString("e04")!=null?rs.getString("e04"):"",
                        rs.getString("e05")!=null?rs.getString("e05"):"",
                        rs.getString("e06")!=null? rs.getString("e06"):"", 
                        rs.getString("e07")!=null?rs.getString("e07"):"",
                        rs.getString("e08")!=null?rs.getString("e08"):"",
                        rs.getString("e09")!=null?rs.getString("e09"):"",
                        rs.getString("e10")!=null?rs.getString("e10"):"",
                        rs.getString("e10_a")!=null?rs.getString("e10_a"):"",
                        rs.getString("e10_b")!=null?rs.getString("e10_b"):"",
                        rs.getString("e10_c")!=null?rs.getString("e10_c"):"",
                        rs.getString("e11")!=null?new BigDecimal(rs.getString("e11")):new BigDecimal(0),
                        rs.getString("e11a")!=null?rs.getString("e11a"):"",
                        rs.getString("e12")!=null?rs.getString("e12"):"",
                        rs.getString("e13")!=null?new BigDecimal(rs.getString("e13")):new BigDecimal(0),
                        rs.getString("e13_a")!=null?rs.getString("e13_a"):"",
                        rs.getString("e14")!=null?rs.getString("e14"):"", 
                        rs.getString("e14_a")!=null?rs.getString("e14_a"):"",
                        rs.getString("e17")!=null?new BigDecimal(rs.getString("e17")):new BigDecimal(0),
                        rs.getString("codigo_scian")!=null?rs.getString("codigo_scian"):"",
                        rs.getString("e19")!=null?rs.getString("e19"):"",
                        rs.getString("e20")!=null?rs.getString("e20"):"",
                        rs.getString("e23_a")!=null?rs.getString("e23_a"):"", 
                        rs.getString("id_ue")!=null?new BigDecimal(rs.getString("id_ue")):new BigDecimal(0),
                        rs.getString("origen")!=null?new BigDecimal(rs.getString("origen")): new BigDecimal(0), 
                        rs.getString("estatus_punteo")!=null?Integer.valueOf(rs.getString("estatus_punteo")):null,
                        rs.getString("tipo_e10")!=null?rs.getString("tipo_e10"):"",
                        rs.getString("tipo_e10_a")!=null?rs.getString("tipo_e10_a"):"",
                        rs.getString("tipo_e10_b")!=null?rs.getString("tipo_e10_b"):"", 
                        rs.getString("tipo_e10_c")!=null?rs.getString("tipo_e10_c"): "", 
                        rs.getString("tipo_e14")!=null?rs.getString("tipo_e14"):"", 
                        rs.getString("tipo_e19")!=null?rs.getString("tipo_e19"):"");
                    resultado.add(fila);
                }
                return resultado;
            }
        });
        
       return resultado; 
    }
    
     @Override
    public ArrayList<String> getClavesUnicasPG(Integer proyecto) {
         final ArrayList<String> regresa = new ArrayList<>();
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
        sql = getSql(null,0,"",null,"",proyectos,"", "",MetodosBusqueda.GETCLAVESPG);
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<ArrayList<String>>() 
        {
            @Override
            public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                ArrayList<String> fila = null;
                while (rs.next()) 
                {
                    regresa.add(rs.getString(1));
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public List<cat_vw_punteo_sare> getDatosInmuebles(Integer proyecto, String id_ue) 
    {
        final List<cat_vw_punteo_sare> regresa = new ArrayList<>();
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
        sql = getSql(null,0,"",null,"",proyectos,"", id_ue,MetodosBusqueda.GETDATOSINMUEBLES);
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<List<cat_vw_punteo_sare>>() 
        {
            @Override
            public List<cat_vw_punteo_sare> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_vw_punteo_sare fila = null;
                while (rs.next()) 
                {
                    fila=new cat_vw_punteo_sare(
                        rs.getString("id_ue")!=null?new BigDecimal(rs.getString("id_ue")):new BigDecimal(0),
                        rs.getString("e03")!=null?rs.getString("e03"):"",
                        rs.getString("e03n")!=null?rs.getString("e03n"):"", 
                        rs.getString("e04")!=null?rs.getString("e04"):"",
                        rs.getString("e04n")!=null?rs.getString("e04n"):"",
                        rs.getString("e05")!=null?rs.getString("e05"):"",
                        rs.getString("e05n")!=null?rs.getString("e05n"):"",
                        rs.getString("e06")!=null?rs.getString("e06"):"",
                        rs.getString("e07")!=null? rs.getString("e07"):"", 
                        rs.getString("e08")!=null?rs.getString("e08"):"",
                        rs.getString("e09")!=null?rs.getString("e09"):"",
                        rs.getString("tipo_e10")!=null?rs.getString("tipo_e10"):"",
                        rs.getString("e10")!=null?rs.getString("e10"):"",
                        rs.getString("e10_cvevial")!=null?rs.getString("e10_cvevial"):"",
                        rs.getString("e10_cveseg")!=null?rs.getString("e10_cveseg"):"",
                        rs.getString("e11")!=null?new BigDecimal(rs.getString("e11")):new BigDecimal(0),
                        rs.getString("e11_a")!=null?rs.getString("e11_a"):"",
                        rs.getString("tipo_e14")!=null?rs.getString("tipo_e14"):"",
                        rs.getString("e14")!=null?rs.getString("e14"):"",
                        rs.getString("tipo_e10_a")!=null?rs.getString("tipo_e10_a"):"",
                        rs.getString("e10_a")!=null?rs.getString("e10_a"):"",
                        rs.getString("tipo_e10_b")!=null?rs.getString("tipo_e10_b"):"", 
                        rs.getString("e10_b")!=null?rs.getString("e10_b"):"",
                        rs.getString("tipo_e10_c")!=null?rs.getString("tipo_e10_c"):"",
                        rs.getString("e10_c")!=null?rs.getString("e10_c"):"",
                        rs.getString("descrubic")!=null?rs.getString("descrubic"):"",
                        rs.getString("coorx")!=null?new BigDecimal(rs.getString("coorx")):new BigDecimal(0),
                        rs.getString("coory")!=null?new BigDecimal(rs.getString("coory")):new BigDecimal(0), 
                        rs.getString("cod_resultado")!=null?rs.getString("cod_resultado"):"",
                        rs.getString("tipo_reg")!=null?rs.getString("tipo_reg"):"", 
                        rs.getString("e12")!=null?rs.getString("e12"):"",
                        rs.getString("e12p")!=null?rs.getString("e12p"):"",
                        rs.getString("e19")!=null?rs.getString("e19"):"",
                        rs.getString("tipo_e19")!=null?rs.getString("tipo_e19"):"", 
                        rs.getString("e20")!=null?rs.getString("e20"):"", 
                        rs.getString("e13")!=null?new BigDecimal(rs.getString("e13")):new BigDecimal(0), 
                        rs.getString("cve_unica_duplicada")!=null?rs.getString("cve_unica_duplicada"):"");
                }
                regresa.add(fila);
                return regresa;
            }
        });
        return regresa;
    }
    @Override
    public String getExtentBusquedaCvegeo2(cat_vw_punteo_sare cat_vw_punteo_sare, Integer proyecto, int params, String tabla, boolean mza800, String[] rural) {
       String regresa = null;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(cat_vw_punteo_sare,params,tabla,rural,"",proyectos,"", "",MetodosBusqueda.GETEXTENTCVEGEO2);
        switch(proyectos){
            case Operativo_Masivo:
                regresa=execSqlExtentBusquedaCvegeo2Pg(sql);
                break;
            case Establecimientos_GrandesY_Empresas_EGE:
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
              regresa=execSqlExtentBusquedaCvegeo2Mdm(sql); 
            break;
                
        }
        return regresa;
    }
    
    private String execSqlExtentBusquedaCvegeo2Mdm(StringBuilder sql){
        String regresa = null;
        regresa=jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                     fila=rs.getString("extent");
                }
                return fila;
            }
        });
        return regresa;
    }
    private String execSqlExtentBusquedaCvegeo2Pg(StringBuilder sql){
        String regresa = null;
        regresa=jdbcTemplate.query(sql.toString(), new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                     fila=rs.getString("extent");
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public String getNombreBusqueda(Integer proyecto, String tipo, String tabla) {
         String regresa="";
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(null,null,tabla,null,"",proyectos,"", "",MetodosBusqueda.GETNOMBREBUSQUEDA);
        
        regresa=jdbcTemplate.query(sql.toString(),new Object[]{tipo}, new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                     fila=rs.getString(1);
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public String getNombreBusqueda(cat_vw_punteo_sare cat_vw_punteo_sare, Integer proyecto, int params, String campo, String tabla) {
       String regresa="";
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(cat_vw_punteo_sare,params,tabla,null,campo,proyectos,"", "",MetodosBusqueda.GETNOMBREBUSQUEDAOCL);
        
        switch(proyectos){
            case Operativo_Masivo:
                regresa=ExecgetNombreBusquedaPg(sql);
                break;
            case Establecimientos_GrandesY_Empresas_EGE:
            case Construccion:
            case Convenios:
            case Muestra_Rural:
            case Organismos_Operadores_De_Agua:
            case Pesca_Mineria:
            case Transportes:
              regresa=ExecgetNombreBusquedaMdm(sql); 
            break;
                
        }
        return regresa;
    }
    
    private String ExecgetNombreBusquedaMdm(StringBuilder sql){
        String regresa="";
        regresa=jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                     fila=rs.getString(1);
                }
                return fila;
            }
        });
        return regresa;
    }
    
     private String ExecgetNombreBusquedaPg(StringBuilder sql){
        String regresa="";
        regresa=jdbcTemplate.query(sql.toString(), new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                     fila=rs.getString(1);
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean liberaCveunicaOCL(Integer proyecto,String cve_unica) {
        boolean regresa=false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(null,null,"",null,"",proyectos,"", "",MetodosBusqueda.LIBERACLAVEUNICAORACLE);
        
        if(jdbcTemplateocl.update(sql.toString(),new Object[]{cve_unica})>0)
        {
          regresa=true; 
        }
        
        return regresa;
    }
    
    @Override
    public boolean getValCoorGeo(Integer proyecto, String x, String y) {
        boolean regresa=false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(null,null,"",null,"",proyectos,"", "",MetodosBusqueda.GETVALCOORGEO);
        
        regresa=jdbcTemplate.query(sql.toString(),new Object[]{x,y}, new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                     fila=rs.getBoolean("valida");
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public String getExtentBusquedaCvegeo(cat_vw_punteo_sare cat_vw_punteo_sare, Integer proyecto, int params, String tabla, boolean mza800, String[] rural) {
        String regresa="";
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql = getSql(cat_vw_punteo_sare,null,"",null,"",proyectos,"", "",MetodosBusqueda.GETEXTENTCVEGEO);
         
        regresa=jdbcTemplate.query(sql.toString(), new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = "";
                while (rs.next()) 
                {
                     fila=rs.getString("extent");
                }
                if(fila!=null && !fila.equals(""))
                {
                   fsearch = false; 
                }
                else
                {
                    breakCve = position - 1;
                    position = 1;
                }
                return fila;
            }
        });
        return regresa;
    }
    
     private StringBuilder getSql(cat_vw_punteo_sare cat_vw_punteo_sare,Integer params,String tabla,String rural[],String campo,ProyectosEnum proyecto, String ce, String id_ue, MetodosBusqueda metodo)
     {
        StringBuilder sql = new StringBuilder();
        String esquemaPos,esquemaOcl;
        esquemaPos=getEsquemaPostgres(proyecto);
        esquemaOcl=getEsquemaOracle(proyecto);
            switch(proyecto)
            {
                
                case Operativo_Masivo:
                    switch(metodo){
                        case BUSQUEDAOCL:
                            sql=filtrarSqlEge(ce,esquemaOcl,id_ue);
                            break;
                        case GETCLAVESPG:
                             sql.append("SELECT distinct id_ue FROM ").append(esquemaPos).append(".td_ue_suc ");
                            break;
                        case GETDATOSINMUEBLES:
                             sql.append("SELECT cve_unica,e03,e03n,e04,e04n,e05,e05n,e06,e07,e08,e09,tipo_e10,e10,e10_cvevial,e10_cveseg,e11,e11_a,tipo_e14,e14,tipo_e10_a,e10_a,tipo_e10_b")
                             .append(",e10_b,tipo_e10_c,e10_c,descrubic, coord_x coorx, coord_y coory,cod_resultado,tipo_reg,e12,e12p,e19,tipo_e19,e20,e13,cve_unica_duplicada clave_unica_duplicada, ")
                             .append("xmin(buffer(the_geom_merc,50))||','||ymin(buffer(the_geom_merc,50))||','||Xmax(buffer(the_geom_merc,50))||','||Ymax(buffer(the_geom_merc,50)) extent, ")
                             .append(" e10a_cvevial,e10a_cveseg,e10b_cvevial,e10b_cveseg,e10c_cvevial,e10c_cveseg,tipo_administracion,codigo_carretera,tramo_camino,margen,cadenamiento")
                             .append(" FROM ").append(esquemaPos).append(".inmuebles where id_ue=? limit 1");
                             break;
                        case GETEXTENTCVEGEO:
                            sql=GetSqlExtent(proyecto,metodo,params,tabla,rural,cat_vw_punteo_sare);
                            break;
                        case GETEXTENTCVEGEO2:
                            sql=GetSqlExtent(proyecto,metodo,params,tabla,rural,cat_vw_punteo_sare);
                            break;
                        case GETNOMBREBUSQUEDA:
                            sql.append("select descripcion from ").append(esquemaPos).append(".").append(tabla).append(" where tipo_e10=?");
                            break;
                        case GETNOMBREBUSQUEDAOCL:
                            sql.append("select ").append(campo).append(" from ").append(schemapgEge).append(".").append(tabla).append(" where cve_ent= '").append(cat_vw_punteo_sare.getE03()).append("'");
                            if(params>=2)
                            {
                                sql.append("and cve_mun= '").append(cat_vw_punteo_sare.getE04()).append("'");
                            }
                            if (params == 3) 
                            {
                                sql.append("and cve_loc= '").append(cat_vw_punteo_sare.getE05()).append("'");
                            }
                            break;
                        case LIBERACLAVEUNICAORACLE:
                            sql.append("UPDATE ").append(esquemaOcl).append(".TR_UE_SUC set SARE_ST='10' where id_ue=? and sare_st<>'01'");                            break;
                        case GETVALCOORGEO:
                            sql.append("select ").append(esquemaPos).append(".val_coord_geo(?,?) valida");
                            break;
                    }
                    break;
                case Establecimientos_GrandesY_Empresas_EGE:
                case Construccion:
                case Convenios:
                case Muestra_Rural:
                case Organismos_Operadores_De_Agua:
                case Pesca_Mineria:
                case Transportes:
                    switch(metodo){
                        case BUSQUEDAOCL:
                            sql=filtrarSqlEge(ce,esquemaOcl,id_ue);
                            break;
                        case GETCLAVESPG:
                             sql.append("SELECT distinct id_ue FROM ").append(esquemaPos).append(".td_ue_suc ");
                            break;
                        case GETDATOSINMUEBLES:
                             sql.append("SELECT id_ue,e03,e03n,e04,e04n,e05,e05n,e06,e07,e08,e09,tipo_e10,e10,e10_cvevial,e10_cveseg,e11,e11_a,tipo_e14,e14,tipo_e10_a,e10_a,tipo_e10_b")
                             .append(",e10_b,tipo_e10_c,e10_c,descrubic, coord_x coorx, coord_y coory,cod_resultado,tipo_reg,e12,e12p,e19,tipo_e19,e20,e13,cve_unica_duplicada clave_unica_duplicada, ")
                             .append("xmin(buffer(the_geom_merc,50))||','||ymin(buffer(the_geom_merc,50))||','||Xmax(buffer(the_geom_merc,50))||','||Ymax(buffer(the_geom_merc,50)) extent, ")
                             .append(" e10a_cvevial,e10a_cveseg,e10b_cvevial,e10b_cveseg,e10c_cvevial,e10c_cveseg,tipo_administracion,codigo_carretera,tramo_camino,margen,cadenamiento")
                             .append(" FROM ").append(esquemaPos).append(".inmuebles where cve_unica=? limit 1");
                             break;
                        case GETEXTENTCVEGEO:
                            sql=GetSqlExtent(proyecto,metodo,params,tabla,rural,cat_vw_punteo_sare);
                            break;
                        case GETEXTENTCVEGEO2:
                            sql=GetSqlExtent(proyecto,metodo,params,tabla,rural,cat_vw_punteo_sare);
                            break;
                        case GETNOMBREBUSQUEDA:
                            sql.append("select descripcion from ").append(esquemaPos).append(".").append(tabla).append(" where tipo_e10=?");
                            break;
                        case GETNOMBREBUSQUEDAOCL:
                            sql.append("select ").append(campo).append(" from ").append(schemamdm).append(".").append(tabla).append(" where cve_ent= '").append(cat_vw_punteo_sare.getE03()).append("'");
                            if(params>=2)
                            {
                                sql.append("and cve_mun= '").append(cat_vw_punteo_sare.getE04()).append("'");
                            }
                            if (params == 3) 
                            {
                                sql.append("and cve_loc= '").append(cat_vw_punteo_sare.getE05()).append("'");
                            }
                            break;
                        case LIBERACLAVEUNICAORACLE:
                            sql.append("UPDATE ").append(esquemaOcl).append(".TR_UE_SUC set SARE_ST='10' where id_ue=? and sare_st<>'01'");                            break;
                        case GETVALCOORGEO:
                            sql.append("select ").append(esquemaPos).append(".val_coord_geo(?,?) valida");
                            break;
                    }
                    break;
              
            }
        return sql;
    }
     
      private StringBuilder filtrarSqlEge(String ce,String esquemaOcl, String id_ue)
      {
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT to_char(id_ue) as id_ue, e03, e04, e05, e06, e07, e08, e09, lpad(to_char(tipo_e10),2,'0') tipo_e10, e10, e11, TRIM(e11a) as e11a, lpad(to_char(tipo_e14),2,'0') tipo_e14, e14, lpad(to_char(tipo_e10_a),2,'0') tipo_e10_a, e10_a, ");
        sql.append("lpad(to_char(tipo_e10_b),2,'0') tipo_e10_b, e10_b, lpad(to_char(tipo_e10_c),2,'0') tipo_e10_c, e10_c, coord_x as coorx, to_char(coord_y) as coory, descrubic, sare_st estatus_punteo, e12, ");
        sql.append("e19, tipo_e19, e20, e13, TRIM(e13a) as e13_a,e14_a, to_char(origen) origen, cestatal, e23a e23_a,"); //modificar e23a por e23 es solo para pruebas, e13_a por e13a, e12_p por e12, e11_a por e11
        sql.append("e17, e17||' - '||e17_desc as codigo_scian,c154");
                
            if(ce.equals("00"))
            {
                sql.append(" FROM ").append(esquemaOcl).append(".VW_PUNTEO_SARE where id_ue = ").append(id_ue);
            }
            else
            {
                sql.append(" FROM ").append(esquemaOcl).append(".VW_PUNTEO_SARE where id_ue = ").append(id_ue); 
            }
        return sql;
      }
      
      private StringBuilder GetSqlExtent(ProyectosEnum proyecto,MetodosBusqueda metodo,Integer params,String tabla,String rural[],cat_vw_punteo_sare cat_vw_punteo_sare)
      {
         StringBuilder sql = new StringBuilder();
         Boolean continuar = true;
         String cvegeo = "";
         String t = "";
         Boolean isRural = false;
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
                 switch(metodo){
                     case GETEXTENTCVEGEO2:
                 
                            if(!"null".equals(String.valueOf(cat_vw_punteo_sare.getCOORD_X())) && !String.valueOf(cat_vw_punteo_sare.getCOORD_X()).isEmpty() 
                                    && (!"null".equals(String.valueOf(cat_vw_punteo_sare.getCOORD_Y())) && !String.valueOf(cat_vw_punteo_sare.getCOORD_Y()).isEmpty()))
                            {
                                sql.append("select xmin(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50))||','");
                                sql.append("||ymin(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50))||','");
                                sql.append("||xmax(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50))||','");
                                sql.append("||ymax(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50)) as extent");
                            }
                            else
                            {
                                sql.append("select xmin(buffer(").append(campo_geo).append(",50))||','||ymin(buffer(").append(campo_geo).append(",50))||','||xmax(buffer(").append(campo_geo).append(",50))||','||ymax(buffer(").append(campo_geo).append(",50)) as extent");
                                sql.append(" from ").append(schemamdm).append(".").append(tabla).append(" where cve_ent='").append(cat_vw_punteo_sare.getE03()).append("'");
                                if(params==5)
                                {
                                    if((!cat_vw_punteo_sare.getE04().isEmpty() && (!cat_vw_punteo_sare.getE05().isEmpty()) 
                                            && (!cat_vw_punteo_sare.getE06().isEmpty()) && (!cat_vw_punteo_sare.getE07().isEmpty())))
                                    {
                                        if ((!cat_vw_punteo_sare.getE04().isEmpty()) && (!cat_vw_punteo_sare.getE05().isEmpty()) && (!cat_vw_punteo_sare.getE06().isEmpty()) && (!cat_vw_punteo_sare.getE07().isEmpty())) 
                                        {
                                            cvegeo=cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE05().concat(cat_vw_punteo_sare.getE06().concat(cat_vw_punteo_sare.getE07()))));
                                        }
                                        else
                                        {
                                          cvegeo=String.valueOf(1);  
                                        }
                                        sql.append(" and cvegeo='").append(cvegeo).append("'");
                                    }
                                    else
                                    {
                                        sql.append(" and 1=1");
                                    }

                                }
                                else
                                {
                                    if ((params == 4) && (!cat_vw_punteo_sare.getE04().isEmpty()) && (!cat_vw_punteo_sare.getE05().isEmpty()) && (!cat_vw_punteo_sare.getE06().isEmpty())) {
                                        cvegeo=cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE05().concat(cat_vw_punteo_sare.getE06())));
                                        } else if ((params == 3) && (!cat_vw_punteo_sare.getE04().isEmpty()) && (!cat_vw_punteo_sare.getE05().isEmpty())) {
                                           cvegeo=cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE05()));
                                        } else if ((params == 2) && (!cat_vw_punteo_sare.getE04().isEmpty())) {
                                            cvegeo= cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04());
                                        } else {
                                            cvegeo= cat_vw_punteo_sare.getE03();
                                        }
                                    sql.append(" and cvegeo='").append(cvegeo).append("'");
                                }
                                if(params==4)
                                {
                                    sql.append("union all select xmin(buffer(").append(campo_geo).append(",50))||','||ymin(buffer(").append(campo_geo).append(",50))||','||xmax(buffer(").append(campo_geo).append(",50))||','||ymax(buffer(").append(campo_geo).append(",50)) as extent ");
                                    sql.append("from ").append(schemamdm).append(".").append(rural[0]).append(" where cve_ent=").append(cat_vw_punteo_sare.getE03()).append("and cvegeo=").append((cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE05())))).append("union all ");
                                    sql.append("select xmin(buffer(").append(campo_geo).append(",50))||','||ymin(buffer(").append(campo_geo).append(",50))||','||xmax(buffer(").append(campo_geo).append(",50))||','||ymax(buffer(").append(campo_geo).append(",50)) as extent ");
                                    sql.append("from ").append(schemamdm).append(".").append(rural[1]).append(" where cve_ent=").append(cat_vw_punteo_sare.getE03()).append("and cvegeo=").append((cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE05())))).append("limit 1");
                                }
                            }
                         break;
                     case GETEXTENTCVEGEO:
                         if ((cat_vw_punteo_sare.getCOORD_X()!= null && !String.valueOf(cat_vw_punteo_sare.getCOORD_X()).isEmpty()) && (cat_vw_punteo_sare.getCOORD_Y()!= null && !String.valueOf(cat_vw_punteo_sare.getCOORD_Y()).isEmpty())) 
                         {
                             sql.append("select xmin(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50))||','");
                             sql.append("||ymin(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50))||','");
                             sql.append("||xmax(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50))||','");
                             sql.append("||ymax(buffer(st_transform(ST_GeomFromText('POINT(").append(cat_vw_punteo_sare.getCOORD_X()).append(" ").append(cat_vw_punteo_sare.getCOORD_Y()).append(")',4326),900913),50)) as extent");
                         }
                         else
                         {
                            while(fsearch)
                            {
                                if(sql.length()>0)
                                {
                                    sql.delete(0, sql.length() - 1);
                                }
                                if (cat_vw_punteo_sare.getE03() != null && !cat_vw_punteo_sare.getE03().equals("") && continuar) 
                                {
                                    cvegeo = cat_vw_punteo_sare.getE03();
                                    t = "ent";
                                    if (1 != breakCve) 
                                    {
                                        position += 1;
                                    } else 
                                    {
                                        continuar = false;
                                    }
                                    if (cat_vw_punteo_sare.getE04() != null && !cat_vw_punteo_sare.getE04().equals("") && continuar) 
                                    {
                                        cvegeo = cat_vw_punteo_sare.getE03() + cat_vw_punteo_sare.getE04();
                                        t = "mun";
                                        if (2 != breakCve) 
                                        {
                                            position += 1;
                                        }
                                        else
                                        {
                                           continuar=false; 
                                        }
                                        if (cat_vw_punteo_sare.getE05() != null && !cat_vw_punteo_sare.getE05().equals("") && continuar) 
                                        {
                                          cvegeo = cat_vw_punteo_sare.getE03() + cat_vw_punteo_sare.getE04() + cat_vw_punteo_sare.getE05();
                                          t = "l"; 
                                          if(3 != breakCve)
                                          {
                                               position += 1;
                                          }
                                          else
                                          {
                                              continuar = false;
                                          }
                                          if (cat_vw_punteo_sare.getE06() != null && !cat_vw_punteo_sare.getE06().equals("") && continuar) 
                                          {
                                              cvegeo = cat_vw_punteo_sare.getE03() + cat_vw_punteo_sare.getE04() + cat_vw_punteo_sare.getE05() + cat_vw_punteo_sare.getE06();
                                              t = "a";
                                              isRural = true;
                                              if(4!=breakCve)
                                              {
                                                 position += 1; 
                                              }
                                              else
                                              {
                                                continuar=false;  
                                              }
                                              if(cat_vw_punteo_sare.getE07() != null && !cat_vw_punteo_sare.getE07().equals("") && continuar)
                                              {
                                                  cvegeo = cat_vw_punteo_sare.getE03() + cat_vw_punteo_sare.getE04() + cat_vw_punteo_sare.getE05() + cat_vw_punteo_sare.getE06() + cat_vw_punteo_sare.getE07();
                                                  t = "m";
                                                  isRural = false;
                                                  position += 1;
                                              }
                                          }
                                        }
                                    }
                                }
                                 sql.append("select xmin(buffer(").append(campo_geo).append(",50))||','||ymin(buffer(").append(campo_geo).append(",50))||','||xmax(buffer(").append(campo_geo).append(",50))||','||ymax(buffer(").append(campo_geo).append(",50)) as extent");
                                 sql.append(" from ").append(schemamdm).append(".").append(t).append(" where cve_ent= ").append(cat_vw_punteo_sare.getE03()).append(" and cvegeo = ").append(cvegeo);
                                 if(isRural)
                                 {
                                    sql.append("union all select xmin(buffer(").append(campo_geo).append(",50))||','||ymin(buffer(").append(campo_geo).append(",50))||','||xmax(buffer(").append(campo_geo).append(",50))||','||ymax(buffer(").append(campo_geo).append(",50)) as extent ");
                                    sql.append("from ").append(schemamdm).append(".").append(rural[0]).append(" where cve_ent=").append(cat_vw_punteo_sare.getE03()).append(" and cvegeo=").append(cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE05()))).append("union all ");
                                    sql.append("select xmin(buffer(").append(campo_geo).append(",50))||','||ymin(buffer(").append(campo_geo).append(",50))||','||xmax(buffer(").append(campo_geo).append(",50))||','||ymax(buffer(").append(campo_geo).append(",50)) as extent ");
                                    sql.append("from ").append(schemamdm).append(".").append(rural[1]).append(" where cve_ent=").append(cat_vw_punteo_sare.getE03()).append(" and cvegeo= ").append(cat_vw_punteo_sare.getE03().concat(cat_vw_punteo_sare.getE04().concat(cat_vw_punteo_sare.getE06()))).append(" limit 1"); 
                                 }
                                
                            } 
                         }
                         break;
                         
                 }
                 break;
         }
         return sql;
      }
   
}
