/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import mx.org.inegi.sare.Enums.TipoAreaEnum;
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
@Profile("jdbc")
public class DaoLogin implements InterfaceLogin {
    
    @Autowired
    @Qualifier("schemaSarePG")
    private String schemapg;
    @Autowired    
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean registraAccesoPG(cat_usuarios acceso) {
         boolean regresa = false;
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO ").append(schemapg).append(".td_bitacora_accesos_sare(usuario, tramo_control, id_deftramo, ip)"
                + " VALUES (").append(acceso.getUsuario()).append(",").append(acceso.getTramo_control()).append(",").append(acceso.getId_deftramo())
                .append(",").append(acceso.getIp()).append(")");
        if(jdbcTemplate.update(sql.toString())>0)
        {
          regresa=true; 
        }
        return regresa;
    }
    
    
    
    
    
}
