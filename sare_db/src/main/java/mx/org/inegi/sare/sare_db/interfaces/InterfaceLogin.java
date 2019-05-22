/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.interfaces;

import mx.org.inegi.sare.sare_db.dto.cat_usuarios;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public interface InterfaceLogin {
    
    
    public boolean registraAccesoPG(cat_usuarios acceso);
    
     public cat_usuarios consultaUsuario(cat_usuarios acceso);
    
}
