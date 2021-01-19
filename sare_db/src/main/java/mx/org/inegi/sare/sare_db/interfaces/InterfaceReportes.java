/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import javax.sql.DataSource;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceReportes {
    
    public DataSource getDs();
    
    public DataSource getDsUEEPA();
    
    public DataSource getDsPg();
    
}
