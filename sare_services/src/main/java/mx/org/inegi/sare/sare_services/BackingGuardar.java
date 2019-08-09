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
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardadoUXFrente;
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

    public cat_respuesta_services SaveUE(Integer proyecto, cat_vw_punteo_sare_guardado object, String usuario, String ip, Boolean isAlta) {
        if (Integer.valueOf(object.getCE()) < 10) {
            if (object.getCE().length() < 2) {
                object.setCE("0" + object.getCE());
            }
        }
        if (object.getE11() != null && object.getE11().equals("0")) {
            object.setE11(null);
        }

        if (object.getE13() != null && object.getE13().equals("0")) {
            object.setE13(null);
        }
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        cat_vw_punteo_sare inmueble = inicializa(object);
        int validacion = 1;
        if (inmueble != null) {
            if (inmueble.getID_UE() == null || inmueble.getCE().equals("00") || inmueble.getTRAMO_CONTROL().substring(0, 2).equals("00")) {
                Respuesta.setMensaje(new cat_mensaje("false", "Privilegios insuficientes para modificar datos"));
                Respuesta.setDatos(false);
            } else {
                try {
                    if (!isAlta) {
                        validacion = InterfaceGuardar.getValidaUe(proyecto, object);
                    }
                    if (validacion == 1) {
                        if (asignaClavesProvisionales(inmueble, object, proyecto, isAlta)) {
                            if (InterfaceGuardar.UpdateOclStatusOk(proyecto, object, object.getId_UE(), isAlta)) {
                                if (InterfaceGuardar.getGuardaUe(proyecto, object, isAlta)) {
                                    //if (GuardarUeOCl(inmueble, proyecto, usuario)) { //se comenta ya que no se va a manejar oracle 
                                    inmueble.setID_UE(new BigDecimal(object.getId_UE())); //se inicializa el objeto con el id_ue que contiene y viene esto debido a las altas
                                    if (ActualizaBitacora(proyecto, inmueble, usuario)) {
                                        if (ActualizaIdUEPg(proyecto, inmueble, usuario)) {
                                            /*if (ConfirmaUEPg(proyecto, inmueble, usuario)) {
                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
                                            } else {
                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                            }*/
                                            if (ActualicaEstatusComplemento(proyecto, inmueble, usuario)) {
                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
                                            } else {
                                                Respuesta.setMensaje(new cat_mensaje("false", "No fue posible guardar el registro porfavor revise la información"));
                                            }
                                        } else {
                                            Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                        }
                                    } else {
                                        Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                    }
                                } else {
                                    InterfaceGuardar.UpdateOclStatusOcupado(proyecto, object, object.getId_UE(), isAlta);
                                    Respuesta.setMensaje(new cat_mensaje("false", "No fue posible guardar el registro porfavor revise la información"));
                                }
                            } else {
                                Respuesta.setMensaje(new cat_mensaje("error", "No fue posible guardar el registro, hubo un error de conexión"));
                            }
                        } else {
                            Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                        }
//                        } else {
//                            Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
//                        }
                    } else if (validacion == 96) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Clave unica duplicada"));
                    } else if (validacion == 99) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Datos nulos"));
                    } else if (validacion == 99) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Datos nulos"));
                    } else if (validacion == 98) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Código Postal fuera de rango para la ubicación seleccionada"));
                    } else if (validacion == 98) {
                        Respuesta.setMensaje(new cat_mensaje("false", "Datos erroneos."));
                    }
                } catch (Exception e) {
                    Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, e);
                    Respuesta.setDatos(false);
                    Respuesta.setMensaje(new cat_mensaje("false", "Fallo al guardar, Error interno de Servidor"));
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
        regresar.setID_UE(new BigDecimal(inmueble.getId_UE()));
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
        regresar.setE11A(inmueble.gete11A());
        regresar.setE13(inmueble.getE13());
        regresar.setE13A(inmueble.getE13_a());
        regresar.setTIPO_E14(inmueble.getTipo_E14());
        regresar.setE14_A(inmueble.getTipo_e10_a());
        regresar.setE14(inmueble.getE14());
        regresar.setTIPO_E10_A(inmueble.getTipo_e10_a());
        regresar.setTipo_e10_an(inmueble.getTipo_e10_an());
        regresar.setE10_A(inmueble.getE10_A());
        regresar.setTIPO_E10_B(inmueble.getTipo_e10_b());
        regresar.setE10_cvevial(inmueble.getE10_cvevial());
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
        regresar.setTipo_e10n_otro(inmueble.getTipo_e10n_otro());
        regresar.setTipo_e10_an_otro(inmueble.getTipo_e10_an_otro());
        regresar.setTipo_e10_bn_otro(inmueble.getTipo_e10_bn_otro());
        regresar.setTipo_e10_cn_otro(inmueble.getTipo_e10_cn_otro());
        regresar.setId_inmueble(inmueble.getId_inmueble());
        regresar.setNavegador(inmueble.getNavegador());
        return regresar;
    }

    private boolean asignaClavesProvisionales(cat_vw_punteo_sare object, cat_vw_punteo_sare_guardado inmueble, Integer proyecto, boolean isAlta) {
        boolean regresar = false;
        if (Integer.valueOf(inmueble.getMod_cat()) == 2) {
            inmueble.setCveft(String.valueOf(1));
            if ((inmueble.getE05() == null || inmueble.getE05().equals("") || (inmueble.getE05().isEmpty())) && (inmueble.getPunteo().equals("R"))) {
                inmueble.setE05(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "l"));
                inmueble.setE07("800");
                object.setE05(inmueble.getE05());
                object.setE07(inmueble.getE07());
                regresar = true;
            } else if (inmueble.getE07() == null || inmueble.getE07().equals("") || inmueble.getE07().isEmpty()) {
                inmueble.setE07(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "m"));
                object.setE07(inmueble.getE07());
                object.setCvegeo(object.getE03().concat(object.getE04()).concat(object.getE05()).concat(object.getE06()).concat(object.getE07()));
                inmueble.setCvegeo(object.getE03().concat(object.getE04()).concat(object.getE05()).concat(object.getE06()).concat(object.getE07()));
                regresar = true;
            }
        } else {
            if (inmueble.getE07() == null || inmueble.getE07().equals("") || inmueble.getE07().isEmpty()) {
                inmueble.setE07(InterfaceGuardar.getClaveProvisional(proyecto, inmueble, "m"));
                object.setE07(inmueble.getE07());
                object.setCvegeo(object.getE03().concat(object.getE04()).concat(object.getE05()).concat(object.getE06()).concat(object.getE07()));
                inmueble.setCvegeo(object.getE03().concat(object.getE04()).concat(object.getE05()).concat(object.getE06()).concat(object.getE07()));
                regresar = true;
            }

            regresar = true;
        }
        int deftramo = 12;
        if (isAlta) {
            inmueble.setE23("A");
        } else {
            inmueble.setE23(InterfaceGuardar.e23a(proyecto, inmueble));
            deftramo = InterfaceGuardar.getidDeftramo(proyecto, inmueble);
        }
        inmueble.setId_deftramo(new BigDecimal(deftramo));
        if (validadeftramo(deftramo)) {
            inmueble.setId_deftramo(new BigDecimal(inmueble.getId_UE()));
        }
        object.setE23(inmueble.getE23());
        object.setId_deftramo(inmueble.getId_deftramo());

        return regresar;
    }

    private boolean validadeftramo(int deftramo) {
        boolean regresar = false;
        if (deftramo == 0) {
            regresar = true;
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
    
    public cat_respuesta_services SaveUEFrentes(Integer proyecto, String capa, String usuario, String ip,
            String manzana_destino, String manzana_origen,String frente_destino, String frente_origen, String claves) {
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        cat_vw_punteo_sare_guardadoUXFrente object=new cat_vw_punteo_sare_guardadoUXFrente();
        object.setCapa(capa);
        object.setClaves(claves);
        object.setFrente_destino(frente_destino);
        object.setFrente_origen(frente_origen);
        object.setManzana_destino(manzana_destino);
        object.setManzana_origen(manzana_origen);
        object.setIp(ip);
        object.setUsuario(usuario);
        
        try{
            if(InterfaceGuardar.GuardarUEFrentes(proyecto,object))
            {
               switch(object.getResultado()){
                   case "0":
                       Respuesta.setMensaje(new cat_mensaje("false", "Ocurrio una exception")); 
                       break;
                   case "1":
                       Respuesta.setMensaje(new cat_mensaje("true", "Se movieron los registros correctamente"));
                       break;
                   case "98":
                       Respuesta.setMensaje(new cat_mensaje("false", "NO SE PUEDEN MOVER A LA MISMA UBICACION GEOGRAFICA!"));
                       break;
                   case "99":
                       Respuesta.setMensaje(new cat_mensaje("false", "NO EXISTE EL FRENTE!"));
                       break;
               }
               
            }
        }catch(Exception e){
                    Logger.getLogger(BackingGuardar.class.getName()).log(Level.SEVERE, null, e);
                    Respuesta.setDatos(false);
                    Respuesta.setMensaje(new cat_mensaje("false", "Fallo al guardar, Reporte este error con su administrador"));   
         }
        return Respuesta;
    }

}
