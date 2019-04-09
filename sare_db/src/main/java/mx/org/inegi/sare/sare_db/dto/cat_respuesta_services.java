/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

import java.util.ArrayList;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_respuesta_services {
    
    private Object datos;
    private cat_mensaje mensaje;
    private cat_usuarios usuario;
    
     public cat_respuesta_services() {
        mensaje = new cat_mensaje();
        datos = new ArrayList<Object>();
    }
     
     public cat_respuesta_services(Object datos, cat_mensaje mensaje) {
        this.datos = datos;
        this.mensaje = mensaje;
    }

    public cat_respuesta_services(Object datos, cat_mensaje mensaje, cat_usuarios usuario) {
        this.datos = datos;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }
     

    public cat_usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(cat_usuarios usuario) {
        this.usuario = usuario;
    }
    
     

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }

    public cat_mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(cat_mensaje mensaje) {
        this.mensaje = mensaje;
    }
    
}
