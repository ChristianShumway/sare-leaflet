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
import mx.org.inegi.sare.Enums.TipoAreaEnum;
import mx.org.inegi.sare.sare_db.dto.cat_ubicacion_punteo;
import mx.org.inegi.sare.sare_db.dto.cat_vial;
import mx.org.inegi.sare.sare_db.interfaces.InterfacePunteoSare;
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
@Repository("DaoPunteoSare")
@Profile("dev")
public class DaoPunteoSare extends DaoBusquedaSare implements InterfacePunteoSare {

    @Autowired
    @Qualifier("jdbcTemplatemdm")
    private JdbcTemplate jdbcTemplatemdm;

    @Autowired
    @Qualifier("schemaSaremdm")
    private String schemamdm;
    
    @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    String resultado;
    boolean isMza;
    String entidad;
    cat_ubicacion_punteo ubicacion_punteo;
    List<cat_vial> cat_vial;
    String tipo_vial;
    List<cat_vial> cat_tipo_vial;

    
    public enum Metodo 
    {
        TIPOAREA, ISMANZANA, GETENTIDAD, GETPUNTEO, VALPUNTEO, GET_TIPO_VIAL, GET_CAT_TIPO_VIAL, GETPUNTEORURAL, FRENTES_PROXIMOS
    }
    public class EnumTest
    {
        Metodo metodo;
        
        public EnumTest(Metodo metodo)
        {
            this.metodo=metodo;
        }
    }
  
    @Override
    public String getTipoArea(Integer proyecto, String x, String y) 
    {
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", x, y,Metodo.TIPOAREA);
        jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                    fila = rs.getString("tipoarea");
                }
                if (Objects.equals(TipoAreaEnum.URBANA.getArea(), fila)) 
                {
                    resultado = TipoAreaEnum.URBANA.getArea();
                } 
                else 
                {
                    resultado = TipoAreaEnum.RURAL.getArea();
                }
                return fila;
            }
        });

        return resultado;
    }
    
     @Override
    public boolean isPuntoinMza(Integer proyecto,String x, String y) {
        isMza=false;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", x, y,Metodo.ISMANZANA);
        String point = "POINT(" + x + " " + y + ")";
        isMza=jdbcTemplatemdm.query(sql.toString(),new Object[]{point,point}, new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    fila = rs.getBoolean("contenido");
                }
                return fila;
            }
        });

        return isMza;
    }
    
    @Override
    public String getEntidad(Integer proyecto, String x, String y) {
        entidad=null;
       StringBuilder sql;
       super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos, "",x, y,Metodo.GETENTIDAD);
        String point = "POINT(" + x + " " + y + ")";
        entidad=jdbcTemplatemdm.query(sql.toString(),new Object[]{point}, new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                    fila = rs.getString("cve_ent");
                }
                return fila;
            }
        });

        return entidad;
    }
     @Override
    public cat_ubicacion_punteo getInfoPunteoUrbano(Integer proyecto,String ce, String x, String y) 
    {
        ubicacion_punteo=new cat_ubicacion_punteo();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,ce, x, y,Metodo.GETPUNTEO);
        ubicacion_punteo=jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<cat_ubicacion_punteo>() 
        {
            @Override
            public cat_ubicacion_punteo extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                while (rs.next()) 
                {
                    ubicacion_punteo=new cat_ubicacion_punteo();
                    ubicacion_punteo.setE03(rs.getString("cve_ent")!=null?rs.getString("cve_ent"):"");
                    ubicacion_punteo.setE03n(rs.getString("nom_ent")!=null?rs.getString("nom_ent"):"");
                    ubicacion_punteo.setE04(rs.getString("cve_mun")!=null?rs.getString("cve_mun"):"");
                    ubicacion_punteo.setE04n(rs.getString("nom_mun")!=null?rs.getString("nom_mun"):"");
                    ubicacion_punteo.setE05(rs.getString("cve_loc")!=null?rs.getString("cve_loc"):"");
                    ubicacion_punteo.setE05n(rs.getString("nom_loc")!=null?rs.getString("nom_loc"):"");
                    ubicacion_punteo.setE06(rs.getString("cve_ageb")!=null?rs.getString("cve_ageb"):"");
                    ubicacion_punteo.setE07(rs.getString("cve_mza")!=null?rs.getString("cve_mza"):"");
                    ubicacion_punteo.setCveft(rs.getString("cveft")!=null?rs.getString("cveft"):"");
                    ubicacion_punteo.setTipo_e10n(rs.getString("tipovial")!=null?rs.getString("tipovial"):"");
                    ubicacion_punteo.setE10_cvevial(rs.getString("cvevial")!=null?rs.getString("cvevial"):"");
                    ubicacion_punteo.setE10(rs.getString("nomvial")!=null?rs.getString("nomvial"):"");
                    ubicacion_punteo.setCoord_x(rs.getString("x")!=null?rs.getString("x"):"");
                    ubicacion_punteo.setCoord_y(rs.getString("y")!=null?rs.getString("y"):"");
                    ubicacion_punteo.setPunteo(rs.getString("punteo")!=null?rs.getString("punteo"):"");
                    ubicacion_punteo.setMod_cat(rs.getInt("mod_cat"));
                    ubicacion_punteo.setCvegeo(rs.getString("cvegeo")!=null?rs.getString("cvegeo"):"");
                    ubicacion_punteo.setCvegeo2016(rs.getString("cvegeo2016")!=null?rs.getString("cvegeo2016"):"");
                }
                
                return ubicacion_punteo;
            }
        });

        return ubicacion_punteo;
    }
    
     @Override
    public List<cat_vial> validaInfoPunteoUrbano(String ent, String cve_geo, String cve_ft,Integer proyecto, String ce, String x, String y) 
    {
        cat_vial=new ArrayList<>();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos, ent,x, y,Metodo.VALPUNTEO);
        cat_vial=jdbcTemplatemdm.query(sql.toString(),new Object[]{ent,cve_geo, cve_ft}, new ResultSetExtractor<List<cat_vial>>() 
        {
            @Override
            public List<cat_vial> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_vial fila = null;
                List<cat_vial> lista=new ArrayList<>();
                while (rs.next()) 
                {
                    fila = new cat_vial(null, rs.getString("tipovial"), rs.getString("nomvial"), rs.getString("cvevial"), rs.getString("cveseg"));
                    lista.add(fila);
                }
                return lista;
            }
        });

        return cat_vial;
    }
    @Override
    public String getTipoVial(Integer proyecto, String tipoE10Xn) {
       tipo_vial=null;
       StringBuilder sql;
       super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"","", "",Metodo.GET_TIPO_VIAL);
        tipo_vial=jdbcTemplate.query(sql.toString(),new Object[]{tipoE10Xn}, new ResultSetExtractor<String>() 
        {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                String fila = null;
                while (rs.next()) 
                {
                    fila = rs.getString("tipo_e10");
                }
                return fila;
            }
        });
        
        return tipo_vial;
    }
    
    @Override
    public List<cat_vial> getCatTipoVial(Integer proyecto) 
    {
       StringBuilder sql;
       super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"","", "",Metodo.GET_CAT_TIPO_VIAL);
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<cat_vial>() 
        {
            @Override
            public cat_vial extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_vial fila = null;
                while (rs.next()) 
                {
                    fila = new cat_vial(rs.getString("tipo_e10n"), rs.getString("tipo_e10"));
                    cat_tipo_vial.add(fila);
                }
                return fila;
            }
        });
        
        return cat_tipo_vial;
    }
    
    @Override
    public cat_ubicacion_punteo getInfoPunteoRural(Integer proyecto, String x, String y) 
    {
        ubicacion_punteo=new cat_ubicacion_punteo();
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"", x, y,Metodo.GETPUNTEORURAL);
        String point = "POINT(" + x + " " + y + ")";
        ubicacion_punteo=jdbcTemplatemdm.query(sql.toString(),new Object[]{point}, new ResultSetExtractor<cat_ubicacion_punteo>() 
        {
            @Override
            public cat_ubicacion_punteo extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_ubicacion_punteo fila = null;
                while (rs.next()) 
                {
                    ubicacion_punteo.setE03(rs.getString("cve_ent"));
                    ubicacion_punteo.setE03n(rs.getString("nom_ent"));
                    ubicacion_punteo.setE04(rs.getString("cve_mun"));
                    ubicacion_punteo.setE04n(rs.getString("nom_mun"));
                    ubicacion_punteo.setE05(rs.getString("cve_loc"));
                    ubicacion_punteo.setE05n(rs.getString("nom_loc"));
                    ubicacion_punteo.setE06(rs.getString("cve_ageb"));
                    ubicacion_punteo.setE07(rs.getString("cve_mza"));
                    ubicacion_punteo.setCveft(rs.getString("cveft"));
                    ubicacion_punteo.setTipo_e10n(rs.getString("tipovial"));
                    ubicacion_punteo.setE10_cvevial(rs.getString("cvevial"));
                    ubicacion_punteo.setE10(rs.getString("nomvial"));
                    ubicacion_punteo.setCoord_x(rs.getString("x"));
                    ubicacion_punteo.setCoord_y(rs.getString("y"));
                    ubicacion_punteo.setPunteo(rs.getString("punteo"));
                    ubicacion_punteo.setMod_cat(rs.getInt("mod_cat"));
                    ubicacion_punteo.setCvegeo(rs.getString("cvegeo"));
                    ubicacion_punteo.setCvegeo2016(rs.getString("cvegeo2016"));
                }   
                
                return fila;
            }
        });

        return ubicacion_punteo;
    }

    @Override
    public boolean isFrentesProximos(Integer proyecto, String ent, String x, String y) 
    {
        boolean regresar = false;
        StringBuilder sql;
        super.proyectos=super.getProyecto(proyecto);
        sql = getSql(super.proyectos,"","", "",Metodo.FRENTES_PROXIMOS);
        jdbcTemplatemdm.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                Boolean fila = null;
                while (rs.next()) 
                {
                    fila = rs.getBoolean("frentes");

                }
                return fila;
            }
        });
        
        return regresar;
    }

    private StringBuilder getSql(ProyectosEnum proyecto, String ce, String x, String y, Metodo metodo) 
    {
        StringBuilder sql = new StringBuilder();
        String point = "POINT(" + x + " " + y + ")";
        switch(proyecto)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
                switch(metodo)
            {
                case TIPOAREA:
                    sql = new StringBuilder();
                    sql.append("select tipoarea from(");
                    sql.append("select 'U' tipoarea from ").append(schemamdm).append(".a where st_contains(the_geom,geomfromtext('").append(point).append("',900913))");
                    sql.append(" union all ");
                    sql.append("select 'R' tipoarea from ").append(schemamdm).append(".ar where st_contains(the_geom,geomfromtext('").append(point).append("',900913)))");
                    sql.append("ageb limit 1");
                break;
                case ISMANZANA:
                    sql.append("select case when resultado<>0 or resultado is null then false else true end contenido from(");
                    sql.append("select sum(case when contenida=false then 1 else 0 end) resultado from (");
                    sql.append("select *,ST_ContainsProperly(the_geom,buffer(geomfromtext(?,900913),1)) contenida from (");
                    sql.append("select gid,tipomza,the_geom from ").append(schemamdm).append(".manzanas where st_intersects(the_geom,buffer(geomfromtext(?,900913),1)))a)b)c");
                break;
                case GETENTIDAD:
                    sql.append("SELECT cve_ent FROM ").append(schemamdm).append(".ent WHERE st_intersects(the_geom,ST_GeomFromText(?,900913)) ");
                break;
                case GETPUNTEO:
                    sql.append("select u.cve_ent,ent.nom_ent,u.cve_mun,nom_mun,u.cve_loc,case when l.nomloc is null then u.nom_loc else l.nomloc end nom_loc,cve_ageb,x,y,cve_mza,cveft, nomvial,tipovial,u.cvegeo,cvevial,punteo,mod_cat,cvegeo2016 from(  ");
                    sql.append("(SELECT cve_ent,cve_mun,cve_loc,null nom_loc,cve_ageb,X(ST_astext(ST_ClosestPoint(a.geom,  ST_GeomFromText('").append(point).append("',900913)))),  ");
                    sql.append("Y(ST_astext(ST_ClosestPoint(a.geom,  ST_GeomFromText('").append(point).append("',900913)))),cve_mza,cve_ft cveft, nomvial,tipovial, cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza  cvegeo,cve_vial cvevial,'U' punteo,1 mod_cat,'' cvegeo2016 ");
                    sql.append("FROM ").append(schemamdm).append(".fm").append(ce).append(" a where cve_ent in (select cve_ent from ").append(schemamdm).append(".ent where contains(the_geom, ST_GeomFromText('").append(point).append("',900913))) and   ");
                    sql.append("st_intersects(geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),1)))  ");
                    sql.append("ORDER BY geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                    sql.append("/*union all ");
                    sql.append("(SELECT cve_ent,cve_mun,cve_loc,nom_loc,replace(cve_ageb,'-','') cve_ageb,X(ST_astext(ST_ClosestPoint(a.the_geom,  ST_GeomFromText('").append(point).append("',900913)))),  ");
                    sql.append("Y(ST_astext(ST_ClosestPoint(a.the_geom,  ST_GeomFromText('").append(point).append("',900913)))),cve_mza,'1' cveft, null nomvial,  ");
                    sql.append("null tipovial, cve_ent||cve_mun||cve_loc||replace(cve_ageb,'-','') cvegeo,'99999' cvevial,'U' punteo,2 mod_cat,cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza cvegeo2016 ");
                    sql.append("FROM mgm_cgura_junio2016.manzanas a left join (select cve_ent cve_ent_l,cve_mun cve_mun_l,cve_loc cve_loc_l,nomgeo nom_loc from mgm_cgura_junio2016.l) b on a.cve_ent=b.cve_ent_l and a.cve_mun=b.cve_mun_l and a.cve_loc=b.cve_loc_l ");
                    sql.append("where cve_ent in (select cve_ent from ").append(schemamdm).append(".ent where contains(the_geom, ST_GeomFromText('").append(point).append("',900913))) and   ");
                    sql.append("st_intersects(the_geom,(ST_buffer( ST_GeomFromText('").append(point).append("',900913),1)))   ");
                    sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) */");
                    sql.append("union all ");
                    sql.append("(select cve_ent,cve_mun,cve_loc,null nom_loc,cve_ageb,X( ST_GeomFromText('").append(point).append("',900913)),  ");
                    sql.append("Y( ST_GeomFromText('").append(point).append("',900913)),'' cve_mza,'1' cveft,null nomvial, null tipovial, ");
                    sql.append("cve_ent||cve_mun||cve_loc||cve_ageb cvegeo, '99999' cvevial,'U' punteo,2 mod_cat,'' cvegeo2016 from ").append(schemamdm).append(".a where contains(the_geom, ST_GeomFromText('").append(point).append("',900913)) and   ");
                    sql.append("contains(the_geom, ST_GeomFromText('").append(point).append("',900913))  ");
                    sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1)) u  ");
                    sql.append("INNER JOIN ").append(schemamdm).append(".ent ent ON u.cve_ent=ent.cvegeo  INNER JOIN ").append(schemamdm).append(".mun mun ON u.cve_ent=mun.cve_ent and u.cve_mun=mun.cve_mun  ");
                    sql.append("left JOIN ").append(schemamdm).append(".l l ON u.cve_ent=l.cve_ent and u.cve_mun=l.cve_mun and u.cve_loc=l.cve_loc order by mod_cat limit 1");
                break;
                case VALPUNTEO:
                    sql.append("select tipovial,nomvial,(row_number() over())::text cvevial,null cveseg from ").append(schemamdm).append(".fm").append(ce).append(" where cve_ent=? and cve_ent||cve_mun||cve_loc||cve_ageb||cve_mza=? and  cve_ft<>?  group by 1,2");
                break;
                case GET_TIPO_VIAL:
                    sql.append("SELECT tipo_e10 FROM ").append(schemapg).append(".cat_tipovialidad WHERE lower(descripcion) = ?");
                break;
                case GET_CAT_TIPO_VIAL:
                    sql.append("SELECT tipo_e10,descripcion tipo_e10n FROM ").append(schemapg).append(".cat_tipovialidad order by descripcion");
                    break;
                case GETPUNTEORURAL:
                    sql.append("select r.cve_ent,nom_ent,r.cve_mun,nom_mun,cve_loc,nom_loc,cve_ageb,x,y,cve_mza,cveft, nomvial,tipovial,r.cvegeo,cvevial,punteo,mod_cat,cvegeo2016 from( ");
                    sql.append("(select * from  ");
                    sql.append("((SELECT m.cve_ent,m.cve_mun,m.cve_loc,nom_loc,cve_ageb,X( ST_GeomFromText(?,900913)),  ");
                    sql.append("Y( ST_GeomFromText(?,900913)),cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,1 mod_cat,'' cvegeo2016 ");
                    sql.append("FROM denue2014.manzanas_rurales m inner join (select cve_ent,cve_mun,cve_loc,nom_loc from denue2014.lpr) l on m.cve_ent||m.cve_mun||m.cve_loc=l.cve_ent||l.cve_mun||l.cve_loc  ");
                    sql.append("where st_intersects(the_geom,(ST_buffer( ST_GeomFromText(?,900913),50)))  ");
                    sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                    sql.append("union all ");
                    sql.append("(SELECT cve_ent,cve_mun,cve_loc,nom_loc,cve_ageb,X(the_geom),Y(the_geom),'' cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,1 mod_cat,'' cvegeo2016 ");
                    sql.append("FROM denue2014.lpr where st_intersects(the_geom,(ST_buffer( ST_GeomFromText(?,900913),50))) ");
                    sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                    sql.append(") rc limit 1) ");
                    sql.append("union all ");
                    sql.append("(SELECT cve_ent,cve_mun,cve_loc,nomgeo nom_loc,replace(cve_ageb,'-','') cve_ageb,X(the_geom),Y(the_geom),'' cve_mza,'1' cveft, null nomvial,null tipovial,cvegeo, '99999' cvevial,'R' punteo,2 mod_cat, cve_ent||cve_mun||cve_loc||replace(cve_ageb,'-','') cvegeo2016 ");
                    sql.append("FROM mgm_cgura_junio2016.lpr where st_intersects(the_geom,(ST_buffer( ST_GeomFromText(?,900913),50))) ");
                    sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) ");
                    sql.append("union all ");
                    sql.append("(select cve_ent,cve_mun,'' cve_loc,'' nom_loc,cve_ageb,X( ST_GeomFromText(?,900913)),  ");
                    sql.append("Y( ST_GeomFromText(?,900913)),'' cve_mza,'1' cveft,null nomvial, null tipovial, ");
                    sql.append("cve_ent||cve_mun||cve_ageb cvegeo, '99999' cvevial,'R' punteo,2 mod_cat,'' cvegeo2016 from denue2014.ar where contains(the_geom, ST_GeomFromText(?,900913)) and   ");
                    sql.append("contains(the_geom, ST_GeomFromText(?,900913))  ");
                    sql.append("ORDER BY the_geom <->'SRID=900913;").append(point).append("'::geometry LIMIT 1) )r ");
                    sql.append("INNER JOIN denue2014.ent ent ON r.cve_ent=ent.cvegeo  INNER JOIN denue2014.mun mun ON r.cve_ent=mun.cve_ent and r.cve_mun=mun.cve_mun  order by mod_cat limit 1");
                break;
                case FRENTES_PROXIMOS:
                    sql.append("SELECT case when COUNT(*)>0 then true else false end frentes FROM ").append(schemamdm).append(".fm").append(ce).append(" where cve_ent in (select cve_ent from ").append(schemamdm).append(".ent where contains(the_geom, ST_GeomFromText(?,900913))) and  ");
                    sql.append("st_intersects(geom,(ST_buffer( ST_GeomFromText(?,900913),20)))");
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
