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
import mx.org.inegi.sare.sare_db.dto.cat_coordenadas;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceSincroniza;
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
@Repository("DaoSincroniza")
@Profile("jdbc")
public class DaoSincronizaSare extends DaoBusquedaSare implements InterfaceSincroniza{
    
    @Autowired    
    @Qualifier("jdbcTemplateOcl")
    private JdbcTemplate jdbcTemplateocl;
    
    @Autowired
    @Qualifier("schemaSareOcl")
    private String schemaocl;
    
     @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;
    
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

     public enum Metodossincroniza 
    {
        getListPendientesOcl, ValidateLocalidades, InsertaLocalidades,Validateejes, Insertaejes,
        ValidateAgebs, InsertaAgebs, ValidateTrAgebs, InsertaTrAgebs, ValidateTcManzanas, InsertaTcManzanas,
        ValidateTrManzanas, InsertaTrManzanas, ValidateTrLocalidades, InsertaTrLocalidades,ValidateTrFrentes, InsertaTrFrentes,
        ValidateTcFrentes, InsertaTcFrentes, ConsultaSecuenciaFrentes, UpdateTrUESUC, buscaTr_Ue_Complemento, Update_Tr_UE_Complemento,
        Insert_Tr_UE_Complemento, buscaTr_Inmuebles, Update_Tr_Inmuebles,Insert_Tr_Inmuebles,getSecuenciaTrInmuebles,ActualizaBitRegIdUE,
        ActualizaIdUE, ConfirmaUe
    }
    
     private BigDecimal secuencia;
     private String user;
    
    @Override
    public List<cat_vw_punteo_sare> getListPendientesOcl(Integer proyecto, String usuario) {
        resultado=new ArrayList<>();
        StringBuilder sql;
        proyectos=getProyecto(proyecto);
        sql=getSql(proyectos,null,null,Metodossincroniza.getListPendientesOcl);
        resultado=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<List<cat_vw_punteo_sare>>() 
        {
            @Override
            public List<cat_vw_punteo_sare> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                cat_vw_punteo_sare fila;
                while(rs.next())
                {
                    fila = new cat_vw_punteo_sare(
                        rs.getString("cve_ce")!=null  && !"".equals(rs.getString("cve_ce")) ?new BigDecimal(rs.getString("cve_ce")):new BigDecimal(0),
                        rs.getString("coord_x")!=null && !"".equals(rs.getString("coord_x"))?new BigDecimal(rs.getString("coord_x")):new BigDecimal(0),
                        rs.getString("coord_y")!=null && !"".equals(rs.getString("coord_y"))?new BigDecimal(rs.getString("coord_y")):new BigDecimal(0), 
                        rs.getString("descrubic")!=null ?rs.getString("descrubic"):"",
                        rs.getString("cve_ent")!=null?rs.getString("cve_ent"):"",
                        rs.getString("nom_ent")!=null?rs.getString("nom_ent"):"",
                        rs.getString("cve_mun")!=null?rs.getString("cve_mun"):"",
                        rs.getString("nom_mun")!=null? rs.getString("nom_mun"):"", 
                        rs.getString("cve_loc")!=null?rs.getString("cve_loc"):"",
                        rs.getString("cve_loc")!=null?rs.getString("cve_loc"):"",
                        rs.getString("cve_ageb")!=null?rs.getString("cve_ageb"):"",
                        rs.getString("cve_mza")!=null?rs.getString("cve_mza"):"",
                        rs.getString("e08")!=null?rs.getString("e08"):"",
                        rs.getString("e09")!=null?rs.getString("e09"):"",
                        rs.getString("nomvial")!=null?rs.getString("nomvial"):"",
                        rs.getString("e10_a")!=null?rs.getString("e10_a"):"",
                        rs.getString("e10_b")!=null?rs.getString("e10_b"):"",
                        rs.getString("e10_c")!=null?rs.getString("e10_c"):"",
                        rs.getString("numext")!=null && !"".equals(rs.getString("numext"))?new BigDecimal(rs.getString("numext")):new BigDecimal(0),
                        rs.getString("numextalf")!=null?rs.getString("numextalf"):"",
                        rs.getString("e12")!=null?rs.getString("e12"):"",
                        rs.getString("numint")!=null && !"".equals(rs.getString("numint"))?new BigDecimal(rs.getString("numint")):new BigDecimal(0),
                        rs.getString("numintalf")!=null?rs.getString("numintalf"):"",
                        rs.getString("e14")!=null?rs.getString("e14"):"",
                        rs.getString("e14_a")!=null?rs.getString("e14_a"):"",
                        rs.getString("e19")!=null?rs.getString("e19"):"",
                        rs.getString("e20")!=null?rs.getString("e20"):"", 
                        rs.getString("id_ue")!=null && !"".equals(rs.getString("id_ue"))?new BigDecimal(rs.getString("id_ue")):new BigDecimal(0),
                        rs.getString("origen")!=null && !"".equals(rs.getString("origen")) ?new BigDecimal(rs.getString("origen")): new BigDecimal(0), 
                        rs.getString("tipo_e10")!=null && !"".equals(rs.getString("tipo_e10"))?new BigDecimal(rs.getString("tipo_e10")):new BigDecimal(0),
                        rs.getString("tipo_e10_a")!=null && !"".equals(rs.getString("tipo_e10_a"))?new BigDecimal(rs.getString("tipo_e10_a")):new BigDecimal(0),
                        rs.getString("tipo_e10_b")!=null && !"".equals(rs.getString("tipo_e10_b"))?new BigDecimal(rs.getString("tipo_e10_b")):new BigDecimal(0), 
                        rs.getString("tipo_e10_c")!=null && !"".equals(rs.getString("tipo_e10_c"))?new BigDecimal(rs.getString("tipo_e10_c")): new BigDecimal(0), 
                        rs.getString("tipo_e14")!=null && !"".equals(rs.getString("tipo_e14"))?new BigDecimal(rs.getString("tipo_e14")):new BigDecimal(0), 
                        rs.getString("tipo_e19")!=null?rs.getString("tipo_e19"):"",
                        rs.getString("tramo_control")!=null?rs.getString("tramo_control"):"",
                        rs.getString("punteo")!=null? rs.getString("punteo"):"",
                        rs.getString("cvevial")!=null?rs.getString("cvevial"):"",
                        rs.getString("e12p")!=null?rs.getString("e12p"):"",
                        rs.getString("cvegeo")!=null?rs.getString("cvegeo"):"",
                        rs.getString("mod_cat")!=null?rs.getString("mod_cat"):"",
                        rs.getString("cveft")!=null?rs.getString("cveft"):"",
                        rs.getString("cvegeo2016")!=null?rs.getString("cvegeo2016"):"",
                        rs.getString("e10_e")!=null?rs.getString("e10_e"):"",
                        rs.getString("e23")!=null?rs.getString("e23"):"",
                        rs.getString("cveft")!=null && !"".equals(rs.getString("cveft"))?new BigDecimal(rs.getString("cveft")):new BigDecimal(0),
                        rs.getString("id_deftramo")!=null && !"".equals(rs.getString("id_deftramo"))?new BigDecimal(rs.getString("id_deftramo")):new BigDecimal(0));
                    resultado.add(fila);
                }
                return resultado;
            }
        });
        
       return resultado; 
    }
    
     @Override
    public boolean Validatelocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateLocalidades);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean Insertlocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaLocalidades);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
     @Override
    public boolean ValidateEjes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.Validateejes);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertEjes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.Insertaejes);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
     @Override
    public boolean ValidateAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateAgebs);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaAgebs);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean ValidateTrAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateTrAgebs);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertTrAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaTrAgebs);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean ValidateTcManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateTcManzanas);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertTcManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaTcManzanas);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean ValidateTrManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateTrManzanas);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertTrManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaTrManzanas);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean ValidateTrLocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateTrLocalidades);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertTrLocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaTrLocalidades);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean ValidateTrFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateTrFrentes);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    @Override
    public boolean InsertTrFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         ConsultaSecuenciaFrentes(proyecto,inmueble,usuario);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaTrFrentes);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }

    @Override
    public boolean ValidateTcFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ValidateTcFrentes);
        regresa=jdbcTemplateocl.query(sql.toString(), new ResultSetExtractor<Boolean>() 
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                boolean fila = false;
                while (rs.next()) 
                {
                    if(rs.getInt("ENCONTRADO")>0)
                    {
                       fila=true; 
                    }
                }
                return fila;
            }
        });
        return regresa;
    }
    
    public BigDecimal ConsultaSecuenciaFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ConsultaSecuenciaFrentes);
        secuencia=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<BigDecimal>() {
            @Override
            public BigDecimal extractData(ResultSet rs) throws SQLException, DataAccessException {
                while(rs.next())
                {
                    secuencia=rs.getBigDecimal("secuencia");
                }
                return secuencia;
            }
        });
        return secuencia;
        
    }
    
    
    @Override
    public boolean InsertTcFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.InsertaTcFrentes);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean UpdateTrUeSuc(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.UpdateTrUESUC);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    @Override
    public boolean ActualizaTR_UE_Complemento(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresar;
        if(buscaTr_UE_Complemento(proyecto,inmueble,usuario))
        {
            regresar = UpdateTr_UE_Complemento(proyecto,inmueble,usuario);
        }
        else
        {
            regresar=   InsertTr_Ue_Complemento(proyecto,inmueble,usuario);
        }
        return regresar;
    }
  
    private boolean buscaTr_UE_Complemento(Integer proyecto, cat_vw_punteo_sare inmueble,String usuario)
    {
        boolean regresa;
        StringBuilder sql;
        user=usuario;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.buscaTr_Ue_Complemento);
        regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean regresar;
                Integer fila = null;
                while(rs.next())
                {
                    fila=rs.getInt(1);
                }
                regresar = fila>0;
                return regresar;
            }
        });
        return regresa; 
        
    }
    private boolean UpdateTr_UE_Complemento(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario){
        user=usuario;
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.Update_Tr_UE_Complemento);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
        
    }
    private boolean InsertTr_Ue_Complemento(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario){
        user=usuario;
         boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.Insert_Tr_UE_Complemento);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
        
    }
    
    private boolean buscaTr_Inmuebles(Integer proyecto, cat_vw_punteo_sare inmueble,String usuario)
    {
        boolean regresa;
        StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.buscaTr_Inmuebles);
        regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean regresar;
                Integer fila = null;
                while(rs.next())
                {
                    fila=rs.getInt(1);
                }
                regresar = fila>0;
                return regresar;
            }
        });
        return regresa; 
        
    }
    private boolean UpdateTr_Inmuebles(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario){
        
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.Update_Tr_Inmuebles);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
        
    }
    private boolean InsertaTr_Inmuebles(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario){
         boolean regresa = false;
         StringBuilder sql;
         cat_coordenadas coordenadas;
         secuencia=getSecuenciaTrInmuebles(proyecto,inmueble,usuario);
         coordenadas=TransformaCartografia(proyecto,"mercator",String.valueOf(inmueble.getCOORD_X()), String.valueOf(inmueble.getCOORD_Y()));
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,coordenadas,Metodossincroniza.Insert_Tr_Inmuebles);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
        
    }
    
    private BigDecimal getSecuenciaTrInmuebles(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario)
    {
        StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.buscaTr_Inmuebles);
        secuencia=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<BigDecimal>() {
            @Override
            public BigDecimal extractData(ResultSet rs) throws SQLException, DataAccessException {
                BigDecimal regresar = null;
                while(rs.next())
                {
                    regresar=rs.getBigDecimal("secuencia");
                }
                return regresar;
            }
        });
        return secuencia; 
    }
    
    @Override
    public boolean InsertTr_Inmuebles(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        if(buscaTr_Inmuebles(proyecto,inmueble,usuario)){
            regresa=UpdateTr_Inmuebles(proyecto, inmueble, usuario);
        }else{
            regresa=InsertaTr_Inmuebles(proyecto, inmueble, usuario);
        }
        return regresa;
    }
    @Override
    public boolean getActualizaBitRegCveunica(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        user=usuario;
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ActualizaBitRegIdUE);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }

    @Override
    public boolean getActualizaIdUe(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        user=usuario;
        boolean regresa = false;
         StringBuilder sql;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ActualizaIdUE);
        if(jdbcTemplateocl.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }

    @Override
    public boolean getConfirmaUe(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        StringBuilder sql;
        boolean regresa;
         proyectos=getProyecto(proyecto);
         sql=getSql(proyectos,inmueble,null,Metodossincroniza.ConfirmaUe);
        regresa=jdbcTemplateocl.query(sql.toString(),new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean regresar;
                int fila=0;
                while(rs.next())
                {
                    fila=rs.getInt("resultado");
                }
                regresar=fila>0;
                return regresar;
            }
        });
        return regresa; 
    }
    
    public StringBuilder getSql(ProyectosEnum proyecto,cat_vw_punteo_sare inmueble,cat_coordenadas transformaCoord,Metodossincroniza metodo)
    {
        StringBuilder sql=new StringBuilder();
        switch(proyecto)
        {
            case Establecimientos_GrandesY_Empresas_EGE:
            switch (metodo)
                {
                case getListPendientesOcl:
                   sql.append("SELECT id_ue, tramo_control, cvegeo, cve_ce, cve_ent, nom_ent, cve_mun, nom_mun, cve_loc, nom_loc, cve_ageb, cve_mza, cveft, e08, e09, tipo_e10, substr(nomvial,1,110) nomvial, case when numext ='' then null else numext::numeric end numext, numextalf, e12, e12p, numint, ");
                   sql.append("numintalf, tipo_e14, e14, e14_a, tipo_e10_a, e10_a, tipo_e10_b, e10_b, tipo_e10_c, e10_c, e10_e, descrubic, coord_x::varchar, coord_y::varchar, e19, tipo_e19, punteo, mod_cat, origen, cvegeo2016, oracle, ");
                   sql.append("e20, id_inmueble, id_deftramo, cvevial, e23 FROM ").append(schemapg).append(".td_ue_suc where id_ue in (");
                   sql.append("select  distinct id_ue from (SELECT distinct id_ue FROM ").append(schemapg).append(".td_bitacora_registro_cve_unica  where td_inm_ocl is null or td_inm_ocl=false ");
                   sql.append("union all ");
                   sql.append("SELECT distinct id_ue FROM ").append(schemapg).append(".td_ue_suc  where oracle is null or oracle=false ) lista)");
                  break;
                case ValidateLocalidades:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TC_LOCALIDADES WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("'");
                  break;
                case InsertaLocalidades:
                    sql.append("insert into ").append(schemaocl).append(".TC_LOCALIDADES (CVE_ENT,CVE_MUN,CVE_LOC,AGEB,TIPO) values (").append(inmueble.getE03()).append(",").
                            append(inmueble.getE04()).append(",").append(inmueble.getE05()).append(",").append(String.valueOf(inmueble.getPunteo()).equals("R")?"0000":inmueble.getE06()).append(",").append(inmueble.getPunteo()).append(")");
                  break;
                case Validateejes:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TR_EJES WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC='").append(inmueble.getE05()).append("' AND CVEVIAL='").append(inmueble.getE10_cvevial()).append("' AND ID_DEFTRAMO='").append(inmueble.getId_deftramo()).append("'");
                    break;
                case Insertaejes:
                    sql.append("insert into ").append(schemaocl).append(".TR_EJES (CVE_ENT,CVE_MUN,CVE_LOC,CVEVIAL,ID_DEFTRAMO) values ('").append(inmueble.getE03()).append("','").append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(inmueble.getE10_cvevial()).append("','").append(inmueble.getId_deftramo()).append("')");
                    break;
                case ValidateAgebs:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TC_AGEBS WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("' AND CVE_AGEB='").append(inmueble.getE06()).append("'");
                    break;
                case InsertaAgebs:
                    sql.append("insert into ").append(schemaocl).append(".TC_AGEBS (CVE_ENT,CVE_MUN,CVE_LOC,CVE_AGEB) values ('").append(inmueble.getE03()).append("','").
                            append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(String.valueOf(inmueble.getPunteo()).equals("R")?"'0000'":inmueble.getE06()).append("')");
                    break;
                case ValidateTrAgebs:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TR_AGEBS WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("' AND CVE_AGEB='").append(inmueble.getE06()).append("' AND ID_DEFTRAMO='").append(inmueble.getId_deftramo()).append("'");
                    break;
                case InsertaTrAgebs:
                    sql.append("insert into ").append(schemaocl).append(".TR_AGEBS (CVE_ENT,CVE_MUN,CVE_LOC,CVE_AGEB,ID_DEFTRAMO) values ('").append(inmueble.getE03()).append("','").
                            append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(String.valueOf(inmueble.getPunteo()).equals("R")?"'0000'":inmueble.getE06()).append("','").append(inmueble.getId_deftramo()).append("')");
                    break;
                case ValidateTcManzanas:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TC_MANZANAS WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("' AND CVE_AGEB='").append(inmueble.getE06()).append("' AND CVE_MZA='").append(inmueble.getE07()).append("'");
                    break;
                case InsertaTcManzanas:
                    sql.append("insert into ").append(schemaocl).append(".TC_MANZANAS (CVE_ENT,CVE_MUN,CVE_LOC,CVE_AGEB,CVE_MZA) values (").append(inmueble.getE03()).append(",").
                            append(inmueble.getE04()).append(",").append(inmueble.getE05()).append(",").append(String.valueOf(inmueble.getPunteo()).equals("R")?"0000":inmueble.getE06()).append(",").append(inmueble.getE07()).append(")");
                case ValidateTrManzanas:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TR_MANZANAS WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("' AND CVE_AGEB='").append(inmueble.getE06()).append("' AND CVE_MZA='").append(inmueble.getE07()).append("' AND ID_DEFTRAMO='").append(inmueble.getID_DEFTRAMO()).append("'");
                    break;
                case InsertaTrManzanas:
                    sql.append("insert into ").append(schemaocl).append(".TR_MANZANAS (CVE_ENT,CVE_MUN,CVE_LOC,CVE_AGEB,CVE_MZA,ID_DEFTRAMO,TIPOAREA) values ('").append(inmueble.getE03()).append("','").
                            append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(String.valueOf(inmueble.getPunteo()).equals("R")?"'0000'":inmueble.getE06()).append("','").append(inmueble.getE07()).append("','").append(inmueble.getID_DEFTRAMO()).append("','").append(inmueble.getPunteo()).append("')");
                    break;
                case ValidateTrLocalidades:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TR_LOCALIDADES WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("' AND ID_DEFTRAMO='").append(inmueble.getID_DEFTRAMO()).append("'");
                    break;
                case InsertaTrLocalidades:
                    sql.append("insert into ").append(schemaocl).append(".TR_LOCALIDADES (CVE_ENT,CVE_MUN,CVE_LOC,AGEB,DESCRIPCION,TIPO,ID_DEFTRAMO) values ('").append(inmueble.getE03()).append("','").
                            append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(String.valueOf(inmueble.getPunteo()).equals("R")?"'0000'":inmueble.getE06()).append("','").append(inmueble.getE05N()).append("','").append(inmueble.getPunteo()).append("','").append(inmueble.getId_deftramo()).append("')");
                    break;
                case ValidateTrFrentes:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TR_FRENTES WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC= '").append(inmueble.getE05()).append("' AND CVE_AGEB='").append(inmueble.getE06()).append("' AND CVE_MZA='").append(inmueble.getE07()).append("' AND CVEFT='").append(inmueble.getE10_cvevial()).append("' AND ID_DEFTRAMO='").append(inmueble.getID_DEFTRAMO()).append("'");
                    break;
                case InsertaTrFrentes:
                    sql.append("insert into ").append(schemaocl).append(".TR_FRENTES (CVE_ENT,CVE_MUN,CVE_LOC,CVE_AGEB,CVE_MZA,CVEFT,ID_DEFTRAMO,CVEVIAL,TIPOVIAL,NOMVIAL,TIPOAREA,ID_FRENTE,ORIGEN_MC) values ('").append(inmueble.getE03()).append("','").append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(inmueble.getE06()).append("','").append(inmueble.getE07()).append("','").append(inmueble.getE10_cvevial()).append("','").append(inmueble.getId_deftramo()).append("','").append(inmueble.getTIPO_E10()).append("','").append(inmueble.getE10()).append("','").append(inmueble.getTipoarea()).append("',").append(secuencia).append(",").append('N').append(")");
                    break;
                case ValidateTcFrentes:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TC_FRENTES WHERE CVE_ENT='").append(inmueble.getE03()).append("' AND CVE_MUN='").append(inmueble.getE04()).append("' AND CVE_LOC='").append(inmueble.getE05()).append("' AND CVE_AGEB='").append(inmueble.getE06()).append("' AND CVE_MZA='").append(inmueble.getE07()).append("' AND CVEFT='").append(inmueble.getE10_cvevial()).append("'");
                    break;
                case ConsultaSecuenciaFrentes:
                    sql.append("select ").append(schemaocl).append(".SEC_TR_FRENTES.nextval secuencia from dual");
                    break;
                case InsertaTcFrentes:
                    sql.append("insert into ").append(schemaocl).append(".TC_FRENTES (CVE_ENT,CVE_MUN,CVE_LOC,CVE_AGEB,CVE_MZA,CVEFT) values ('").append(inmueble.getE03()).append("','").append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','").append(inmueble.getE07()).append("','").append(inmueble.getTIPO_E10()).append("')");
                    break;
                case UpdateTrUESUC:
                    sql.append("UPDATE ").append(schemaocl).append(".TR_UE_SUC SET E03='").append(inmueble.getE03()).append("',E04='").append(inmueble.getE04()).append("',E05='").append(inmueble.getE05()).append("',E06='")
                            .append(inmueble.getE06()).append("',E07='").append(inmueble.getE07()).append("',TIPO_E10='").append(inmueble.getTIPO_E10()).append("',E10='").append(inmueble.getE10()).append("',E11='")
                            .append(inmueble.getE11()).append("',E11A='").append(inmueble.getE11A()).append("',E12='").append(inmueble.getE12()).append("',TIPO_E12P='").append(inmueble.getE12p()).append("',E13='")
                            .append(inmueble.getE13()).append("',E13A='").append(inmueble.getE13A()).append("',TIPO_E14='").append(inmueble.getTIPO_E14()).append("',");
                    sql.append("E14='").append(inmueble.getTIPO_E14()).append("',").append("E14_A='").append(inmueble.getE14_A()).append("',TIPO_E19='").append(inmueble.getTIPO_E19()).append("',E19='").append(inmueble.getE19()).append("',E20='").append(inmueble.getE20()).append("',TIPO_E10_A='")
                            .append(inmueble.getTIPO_E10_A()).append("',E10_A='").append(inmueble.getE10_A()).append("',TIPO_E10_B='").append(inmueble.getTIPO_E10_B()).append("',E10_B='")
                            .append(inmueble.getE10_B()).append("',TIPO_E10_C='").append(inmueble.getTIPO_E10_C()).append("',E10_C='")
                            .append(inmueble.getE10_C()).append("',DESCRUBIC='").append(inmueble.getDESCRUBIC()).append("',X='").append(inmueble.getCOORD_X()).append("',Y='")
                            .append(inmueble.getCOORD_Y()).append("',E10E='").append(inmueble.getE10_e()).append("',SARE_ST='01'").append("WHERE id_ue='").append(inmueble.getID_UE()).append("'");
                    break;
                case buscaTr_Ue_Complemento:
                    sql.append("SELECT count(distinct id_ue) from ").append(schemaocl).append(".TR_UE_COMPLEMENTO where id_ue='").append(inmueble.getE03()).append("'");
                    break;
                case Update_Tr_UE_Complemento:
                    sql.append("UPDATE ").append(schemaocl).append(".TR_UE_COMPLEMENTO set SARE_ST_USR='").append(user).append("', SARE_ST_TIME=systimestamp where ID_UE='").append(inmueble.getID_UE()).append("'");
                    break;
                case Insert_Tr_UE_Complemento:
                    sql.append("INSERT INTO ").append(schemaocl).append(".TR_UE_COMPLEMENTO (SARE_ST_USR,ID_UE, SARE_ST_TIME) values ('").append(user).append("','").append(inmueble.getE03()).append("',systimestamp)");
                    break;
                case buscaTr_Inmuebles:
                    sql.append("SELECT CASE WHEN COUNT(*)>0 THEN 1 ELSE 0 END ENCONTRADO FROM ").append(schemaocl).append(".TR_INMUEBLES WHERE id_ue='").append(inmueble.getID_UE()).append("' AND (BAJA=0 or baja is null)");
                    break;
                case Update_Tr_Inmuebles:
                    sql.append("UPDATE ").append(schemaocl).append(".TR_INMUEBLES SET BAJA=1 WHERE id_ue='").append(inmueble.getID_UE()).append("'");
                    break;
                case Insert_Tr_Inmuebles:
                    sql.append("INSERT INTO ").append(schemaocl).append(".TR_INMUEBLES(");
                            sql.append("id_inmueble, id_ue, cve_ce, cvegeo, cve_ent, cve_mun, cve_loc, ");
                            sql.append(" cve_ageb, cve_mza, cvevial, cveft, tipovial, nom_est, nomvial, numext, numextalf, ");
                            sql.append(" numint, numintalf, the_geom_wkt, c154, tipoarea,  origen, x, y,TIPO_E10_B,E10_B,TIPO_E10_A,E10_A,TIPO_E10_C,E10_C, origen_mc) ");
                            sql.append("VALUES ");
                            sql.append("( ").append(secuencia).append(",'")
                                    .append(inmueble.getID_UE()).append("','").append(inmueble.getCE()).append("','").append(inmueble.getCvegeo()).append("','")
                                    .append(inmueble.getE03()).append("','").append(inmueble.getE04()).append("','").append(inmueble.getE05()).append("','")
                                    .append(inmueble.getE06()).append("','").append(inmueble.getE07()).append("','").append(inmueble.getE10_cvevial()).append("','")
                                    .append(inmueble.getCveft()).append("','").append(inmueble.getTIPO_E10()).append("','").append(inmueble.getE08())
                                    .append("','").append(inmueble.getE10()).append("','").append(inmueble.getE11()).append("','").append(inmueble.getE11A()).append("','")
                                    .append(inmueble.getE13()).append("','").append(inmueble.getE13A()).append("','").append("POINT( '").append(inmueble.getCOORD_X()).append("' '").append(inmueble.getCOORD_Y()).append("')")
                                    .append("','").append(inmueble.getCod_resultado().toUpperCase()).append("','").append(inmueble.getPunteo()).append("','")
                                    .append(inmueble.getORIGEN()).append("','").append(transformaCoord.getX()).append(transformaCoord.getY()).append("','")
                                    .append(inmueble.getTIPO_E10_B()).append("','").append(inmueble.getE10_B()).append("','").append(inmueble.getTIPO_E10_A()).append("','")
                                    .append(inmueble.getE10_A()).append("','").append(inmueble.getTIPO_E10_C()).append("','").append(inmueble.getE10_C()).append("', 'N')");
                    break; 
                case getSecuenciaTrInmuebles:
                    sql.append("select ").append(schemaocl).append(".SEC_TR_INMUEBLES.nextval secuencia from dual");
                    break;
                case ActualizaBitRegIdUE:
                    sql.append("update ").append(schemapg).append(".td_bitacora_registro_cve_unica set td_inm_ocl=true where id_ue='").append(inmueble.getID_UE()).append("' and id in (select max(id) from ").append(schemapg).append(".td_bitacora_registro_cve_unica where id_ue='").append(inmueble.getID_UE()).append("'");
                    break;
                case ActualizaIdUE:
                    sql.append("update ").append(schemapg).append(".TD_UE_SUC set id_inmueble=").append(inmueble.getId_inmueble()).append(" where id_ue='").append(inmueble.getID_UE()).append("'");
                    break;
                case ConfirmaUe:
                    sql.append("SELECT ").append(schemapg).append(".confirma_ue('").append(inmueble.getID_UE()).append("')").append(" resultado");
                    break;
                
                }
            break;
        }
        return sql;
    }
    
}
