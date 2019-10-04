/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.Enums;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public enum ProyectosEnum {
    
    Establecimientos_GrandesY_Empresas_EGE(1,"sare_ege2019_act","ce2019_masrencal"),
    Pesca_Mineria(2,"",""),
    Transportes(3,"",""),
    Construccion(4,"",""),
    Operativo_Masivo(5,"sare_mas2019_act","ce2019_masrencal"),
    Muestra_Rural(6,"",""),
    Convenios(7,"",""),
    Organismos_Operadores_De_Agua(8,"",""),
    MasivoOtros(9,"sare_mas2019_act","ce2019_masrencal"),
    EGE(10,"schemapgEge","");
    public enum MetodosBusqueda {
        BUSQUEDAOCL, GETCLAVESPG, GETDATOSINMUEBLES, GETEXTENTCVEGEO, GETEXTENTCVEGEO2, GETNOMBREBUSQUEDA, GETNOMBREBUSQUEDAOCL,
        LIBERACLAVEUNICAORACLE, GETVALCOORGEO, OCUPACVEUNICA, ACTUALIZACOMPLEMENTO, BUSQUEDAMASIVOOTROS, OCUPACVEUNICACONGLOMERADO, 
        LIBERACLAVEUNICAORACLEOTROS,VALIDA_COORDENADAS_CAIGAN_EN_ESTADO
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
    private String esquemaPg;
    private String esquemaOcl;

    private ProyectosEnum(Integer código) {
        this.código = código;
    }
    private ProyectosEnum(Integer código, String esquemaPg, String esquemaOcl) {
        this.código = código;
        this.esquemaPg=esquemaPg;
        this.esquemaOcl=esquemaOcl;
    }
    public Integer getCódigo() {
        return código;
    }

    public String getEsquemaPg() {
        return esquemaPg;
    }

    public void setEsquemaPg(String esquemaPg) {
        this.esquemaPg = esquemaPg;
    }

    public String getEsquemaOcl() {
        return esquemaOcl;
    }

    public void setEsquemaOcl(String esquemaOcl) {
        this.esquemaOcl = esquemaOcl;
    }

  
}
