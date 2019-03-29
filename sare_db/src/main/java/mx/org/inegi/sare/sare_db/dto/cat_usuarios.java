/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.dto;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
public class cat_usuarios {
    
    private String usuario;
    private String nombre;
    private String ce;
    private String jc;
    private String ta;
    private Integer perfil;
    private String tramo_control;
    private Boolean success;
    private String cve_unica;
    private Boolean masivo;
    private Boolean consulta;
    private String id_deftramo;
    private String ip;
    private String pass;
    private String proyecto;

    public cat_usuarios() {
    }

    public cat_usuarios(String nombre, String cve_unica, String id_deftramo, String ip) {
        this.nombre = nombre;
        this.cve_unica = cve_unica;
        this.id_deftramo = id_deftramo;
        this.ip = ip;
    }
    
    public cat_usuarios(String usuario, String nombre, String ce, String jc, String ta, Integer perfil, String tramo_control, Boolean success, String cve_unica, Boolean masivo, Boolean consulta, String id_deftramo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.ce = ce;
        this.jc = jc;
        this.ta = ta;
        this.perfil = perfil;
        this.tramo_control = tramo_control;
        this.success = success;
        this.cve_unica = cve_unica;
        this.masivo = masivo;
        this.consulta = consulta;
        this.id_deftramo = id_deftramo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    
    
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public String getTramo_control() {
        return tramo_control;
    }

    public void setTramo_control(String tramo_control) {
        this.tramo_control = tramo_control;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCve_unica() {
        return cve_unica;
    }

    public void setCve_unica(String cve_unica) {
        this.cve_unica = cve_unica;
    }

    public Boolean getMasivo() {
        return masivo;
    }

    public void setMasivo(Boolean masivo) {
        this.masivo = masivo;
    }

    public Boolean getConsulta() {
        return consulta;
    }

    public void setConsulta(Boolean consulta) {
        this.consulta = consulta;
    }

    public String getId_deftramo() {
        return id_deftramo;
    }

    public void setId_deftramo(String id_deftramo) {
        this.id_deftramo = id_deftramo;
    }

    
    
    
}
