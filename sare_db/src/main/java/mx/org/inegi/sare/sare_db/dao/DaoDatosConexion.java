/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dao;

import javax.sql.DataSource;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Repository("datosConexionDao")
@Profile("jdbc")
public class DaoDatosConexion implements InterfaceReportes{

    @Autowired
    @Qualifier("dataSource")
    private DataSource ds;

    @Override
    public DataSource getDs() {
        DataSource d = ds;
        return d;
    }
}
