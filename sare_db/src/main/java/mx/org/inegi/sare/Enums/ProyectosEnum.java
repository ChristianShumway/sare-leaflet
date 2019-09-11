/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.Enums;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public enum ProyectosEnum {
    
    Establecimientos_GrandesY_Empresas_EGE(1,"schemapgEge"),
    Pesca_Mineria(2,"schemapgEge"),
    Transportes(3,"schemapgEge"),
    Construccion(4,"schemapgEge"),
    Operativo_Masivo(5,"schemapgEge"),
    Muestra_Rural(6,"schemapgEge"),
    Convenios(7,"schemapgEge"),
    Organismos_Operadores_De_Agua(8),
    MasivoOtros(9);
    
    public enum MetodosBusqueda {
        BUSQUEDAOCL, GETCLAVESPG, GETDATOSINMUEBLES, GETEXTENTCVEGEO, GETEXTENTCVEGEO2, GETNOMBREBUSQUEDA, GETNOMBREBUSQUEDAOCL,
        LIBERACLAVEUNICAORACLE, GETVALCOORGEO, OCUPACVEUNICA, ACTUALIZACOMPLEMENTO, BUSQUEDAMASIVOOTROS, OCUPACVEUNICACONGLOMERADO, LIBERACLAVEUNICAORACLEOTROS
    }
    
    public class MetodosBusquedaClass {

        MetodosBusqueda metodo;

        public MetodosBusquedaClass(MetodosBusqueda metodo) {
            this.metodo = metodo;
        }
    }
    
    public class Proyectos {

        ProyectosEnum proyectos;

        public Proyectos(ProyectosEnum proyectos) {
            this.proyectos = proyectos;
        }

        public Proyectos() {
        }

    }
    
    private final Integer código;
    private String esquema;

    private ProyectosEnum(Integer código) {
        this.código = código;
    }
    private ProyectosEnum(Integer código, String esquema) {
        this.código = código;
        this.esquema=esquema;
    }
    public Integer getCódigo() {
        return código;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
    
    
    
  
    
    
    
    

   
    
    
    
}
