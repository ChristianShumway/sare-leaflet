/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceGuardarUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingGuardar")
public class BackingGuardar extends BackingSincroniza {

    @Autowired
    @Qualifier("DaoBackingGuardarSare")
    InterfaceGuardarUE InterfaceGuardar;

    public cat_respuesta_services SaveUE(Integer proyecto, cat_vw_punteo_sare_guardado object, String usuario, String ip) {
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        cat_vw_punteo_sare inmueble = inicializa(object);

        if (inmueble != null) {
            if (inmueble.getID_UE() == null /*|| inmueble.getCE().equals("00") || inmueble.getTRAMO_CONTROL().substring(0, 2).equals("00")*/) {
                Respuesta.setMensaje(new cat_mensaje("false", "Privilegios insuficientes para modificar datos"));
                Respuesta.setDatos(false);
            } else {
                try {
                    int validacion = InterfaceGuardar.getValidaUe(proyecto, object);
                    if (validacion == 1) {
                        if (asignaClavesProvisionales(inmueble,object, proyecto)) {
                            if (InterfaceGuardar.getGuardaUe(proyecto, object)) {
                                if (GuardarUeOCl(inmueble, proyecto, usuario)) {
                                    if (ActualizaBitacora(proyecto, inmueble, usuario)) {
                                        if (ActualizaIdUEPg(proyecto, inmueble, usuario)) {
                                            if (ConfirmaUEPg(proyecto, inmueble, usuario)) {
                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
                                            } else {
                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                            }
                                        } else {
                                            Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                        }
                                    } else {
                                        Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                    }
                                } else {
                                    Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                }
                            } else {
                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                            }
                        } else {
                            Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                        }
                    } else if (validacion == 96) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Clave unica duplicada"));
                    } else if (validacion == 99) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Datos nulos"));
                    } else if (validacion == 99) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Datos nulos"));
                    } else if (validacion == 98) {
                        Respuesta.setMensaje(new cat_mensaje("false", "C&oacute;digo Postal fuera de rango para la ubicaci&oacute;n seleccionada"));
                    } else if (validacion == 98) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Datos erroneos."));
                    }
                } catch (Exception e) {
                    Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, e);
                    Respuesta.setDatos(false);
                    Respuesta.setMensaje(new cat_mensaje("false","Fallo al guardar, Error interno de Servidor"));
                }
            }
        }

        return Respuesta;

    }

    /*Se crea este metodo para inicializar el dto cat_vw_punteo_sare ya que se vino utilizando para todo pero no 
    cuadraban los nombres de las variables con el DOM de tal forma que se creo 
    el  dto cat_vw_punteo_sare_guardado y se pasan a cat_vw_punteo_sare los valores*/
    private cat_vw_punteo_sare inicializa(cat_vw_punteo_sare_guardado inmueble) {
        cat_vw_punteo_sare regresar = new cat_vw_punteo_sare();
        regresar.setID_UE(inmueble.getId_UE());
        regresar.setORIGEN(new BigDecimal(inmueble.getOrigen()));
        regresar.setC154(inmueble.getC154());
        regresar.setE08(inmueble.getE08());
        regresar.setE09(inmueble.getE09());
        regresar.setE17_DESC(inmueble.getE17_DESC());
        regresar.setE03(inmueble.getE03());
        regresar.setE03N(inmueble.getE03N());
        regresar.setE04(inmueble.getE04());
        regresar.setE04N(inmueble.getE04N());
        regresar.setE05(inmueble.getE05());
        regresar.setE05N(inmueble.getE05N());
        regresar.setE06(inmueble.getE06());
        regresar.setE07(inmueble.getE07());
        regresar.setTIPO_E10(inmueble.getTipo_e10());
        regresar.setTipo_e10n(inmueble.getTipo_e10n());
        regresar.setE10(inmueble.getE10());
        regresar.setE10_e(inmueble.getE10_e());
        regresar.setE11(inmueble.getE11());
        regresar.setE11A(inmueble.getE11_a());
        regresar.setE13(inmueble.getE13());
        regresar.setE13A(inmueble.getE13_a());
        regresar.setTIPO_E14(inmueble.getTipo_E14());
        regresar.setE14_A(inmueble.getTipo_e10_a());
        regresar.setE14(inmueble.getE14());
        regresar.setTIPO_E10_A(inmueble.getTipo_e10_a());
        regresar.setTipo_e10_an(inmueble.getTipo_e10_an());
        regresar.setE10_A(inmueble.getE10_A());
        regresar.setTIPO_E10_B(inmueble.getTipo_e10_b());
        regresar.setE10_cvevial(inmueble.getE10a_cvevial());
        regresar.setE10_B(inmueble.getE10_B());
        regresar.setTIPO_E10_C(inmueble.getTipo_e10_c());
        regresar.setE10_C(inmueble.getE10_C());
        regresar.setDESCRUBIC(inmueble.getDescrubic());
        regresar.setE12(inmueble.getE12());
        regresar.setTIPO_E19(inmueble.getTipo_e19());
        regresar.setE19(inmueble.getE19());
        regresar.setE12p(inmueble.getE12p());
        regresar.setE20(inmueble.getE20());
        regresar.setPunteo(inmueble.getPunteo());
        regresar.setMod_cat(inmueble.getMod_cat());
        regresar.setTRAMO_CONTROL(inmueble.getTramo_control());
        regresar.setId_deftramo(inmueble.getId_deftramo());
        regresar.setE23(inmueble.getE23());
        regresar.setCvegeo(inmueble.getCvegeo());
        regresar.setCE(inmueble.getCE());
        regresar.setCveft(inmueble.getCveft());
        regresar.setCOORD_X(inmueble.getCoordx());
        regresar.setCOORD_Y(inmueble.getCoordy());

        return regresar;
    }

    private boolean asignaClavesProvisionales(cat_vw_punteo_sare object,cat_vw_punteo_sare_guardado inmueble, Integer proyecto) {
        boolean regresar = false;
        if (Integer.valueOf(inmueble.getMod_cat()) == 2) {
            inmueble.setCveft(String.valueOf(1));
            if ((inmueble.getE05() == null || (inmueble.getE05().isEmpty())) && (inmueble.getPunteo().equals("R"))) {
                inmueble.setE05(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "l"));
                inmueble.setE07("800");
                regresar = true;
            } else if (inmueble.getE07() == null || inmueble.getE07().isEmpty()) {
                inmueble.setE07(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "m"));
                regresar = true;
            }
        } else {
            if (inmueble.getE07() == null || inmueble.getE07().isEmpty()) {
                inmueble.setE07(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "m"));
                regresar = true;
            }
            inmueble.setE23(InterfaceGuardar.e23a(proyecto, inmueble));
            inmueble.setId_deftramo(new BigDecimal(InterfaceGuardar.getidDeftramo(proyecto, inmueble)));
            object.setE23(inmueble.getE23());
            object.setId_deftramo(inmueble.getId_deftramo());
            
            regresar=true;
        }
        return regresar;
    }

    private boolean GuardarUeOCl(cat_vw_punteo_sare inmueble, Integer proyecto, String usuario) {
        boolean regresar = false;
        if (validaLocalidades(proyecto, inmueble, usuario)) {
            if (validaEjes(proyecto, inmueble, usuario)) {
                if (validaAgebs(proyecto, inmueble, usuario)) {
                    if (validaTrAgebs(proyecto, inmueble, usuario)) {
                        if (validaTcManzanas(proyecto, inmueble, usuario)) {
                            if (validaTrManzanas(proyecto, inmueble, usuario)) {
                                if (validaTrLocalidades(proyecto, inmueble, usuario)) {
                                    if (validaTcFrentes(proyecto, inmueble, usuario)) {
                                        if (validaTrFrentes(proyecto, inmueble, usuario)) {
                                            if (UpdateTrUESuc(proyecto, inmueble, usuario)) {
                                                regresar = InsertTrInmuebles(proyecto, inmueble, usuario);
                                            }

                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return regresar;
    }

}
