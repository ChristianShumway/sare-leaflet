/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mx.org.inegi.sare.Enums.ProyectosEnum;
import mx.org.inegi.sare.sare_db.dto.cat_usuarios;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceLogin;
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

@Repository("DaoLogin")
@Profile("prod")
public class DaoLogin extends DaoTransformaCartografia implements InterfaceLogin {
    
//    @Autowired
//    @Qualifier("schemaSarePG")
//    private String schemapg;
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    @Qualifier("jdbcTemplateOclUEEPA")
    private JdbcTemplate dataSourceOclUEEPA;

    @Override
    public boolean registraAccesoPG(cat_usuarios acceso) {
         boolean regresa = false;
        StringBuilder sql=new StringBuilder();
        ProyectosEnum proyecto=getProyecto(acceso.getProyecto());
        sql.append("INSERT INTO ").append(getEsquemaPostgres(proyecto)).append(".td_bitacora_accesos_sare(usuario, tramo_control, id_deftramo, ip)"
                + " VALUES ('").append(acceso.getUsuario()).append("','").append(acceso.getTramo_control()).append("','").append(acceso.getId_deftramo())
                .append("','").append(acceso.getIp()).append("')");
        if(jdbcTemplate.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }

    @Override
    public cat_usuarios consultaUsuario(cat_usuarios usuario) {
        cat_usuarios regresa;
        
        StringBuilder sql=new StringBuilder();
        sql.append("select * from sare_mas2019_act.td_usuarios where cuenta= '").append(usuario.getUsuario()).append("'");
        regresa=jdbcTemplate.query(sql.toString(),new ResultSetExtractor<cat_usuarios>(){
            @Override
            public cat_usuarios extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_usuarios usuario=new cat_usuarios();
                boolean regresa=false;
                while(rs.next()){
                    usuario.setCve_operativa(rs.getString("cveoper"));
                    usuario.setCe(rs.getString("ce"));
                    usuario.setTramo_control(rs.getString("tramo_control"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setPass(rs.getString("password"));
                    regresa=true;
                }
                return usuario;
            }
        
        });
        return regresa;
    }
    
    @Override
    public cat_usuarios consultaUsuarioUEEPA(cat_usuarios usuario) {
        cat_usuarios regresa;
        StringBuilder sql=new StringBuilder();
        String usuariofinal=usuario.getNombre();
        if(usuariofinal==null){
            usuariofinal=usuario.getUsuario();
        }
        
        sql.append("select * from inpc_campo.ENC_USUARIO where nombre= '").append(usuariofinal).append("'");
        regresa=dataSourceOclUEEPA.query(sql.toString(),new ResultSetExtractor<cat_usuarios>(){
            @Override
            public cat_usuarios extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_usuarios usuario=new cat_usuarios();
                boolean regresa=false;
                while(rs.next()){
                    usuario.setCve_operativa(null);
                    usuario.setCe(rs.getString("password").substring(0,2));
                    usuario.setTramo_control(null);
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setPass(rs.getString("password"));
                    regresa=true;
                }
                return usuario;
            }
        
        });
        return regresa;
    }
    
     @Override
    public cat_usuarios consultaUsuariobyjefeUEEPA(cat_usuarios usuario) {
        cat_usuarios regresa;
        StringBuilder sql=new StringBuilder();
        sql.append("select * from inpc_campo.ENC_VBCUESTIONARIO_PUNTEO where usuario_jefecontrol= '").append(usuario.getUsuario()).append("'");
        regresa=dataSourceOclUEEPA.query(sql.toString(),new ResultSetExtractor<cat_usuarios>(){
            @Override
            public cat_usuarios extractData(ResultSet rs) throws SQLException, DataAccessException {
                cat_usuarios usuario=new cat_usuarios();
                boolean regresa=false;
                while(rs.next()){
                    usuario.setCve_operativa(null);
                    usuario.setCe(null);
                    usuario.setTramo_control(null);
                    usuario.setNombre(rs.getString("usuario_entrevistador"));
                    usuario.setPass(null);
                    regresa=true;
                }
                return usuario;
            }
        
        });
        return regresa;
    }
    
    
    
    
}
