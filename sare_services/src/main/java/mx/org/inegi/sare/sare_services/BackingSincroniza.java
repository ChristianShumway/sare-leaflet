/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.sare.sare_db.dao.DaoBackingGuardarSare;
import mx.org.inegi.sare.sare_db.dto.cat_mensaje;
import mx.org.inegi.sare.sare_db.dto.cat_respuesta_services;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare;
import mx.org.inegi.sare.sare_db.dto.cat_vw_punteo_sare_guardado;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceGuardarUE;
import mx.org.inegi.sare.sare_db.interfaces.InterfaceSincroniza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Service("BackingSincroniza")
public class BackingSincroniza {

    @Autowired
    @Qualifier("DaoSincroniza")
    InterfaceSincroniza InterfaceSincroniza;

    @Autowired
    @Qualifier("DaoBackingGuardarSare")
    InterfaceGuardarUE InterfaceGuardar;

    public cat_respuesta_services sincronizaBDs(Integer proyecto, String usuario) {
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        List<cat_vw_punteo_sare> pendientes;
        try {
            pendientes = InterfaceSincroniza.getListPendientesOcl(proyecto, usuario);
            if (pendientes.size() > 0) {
                for (cat_vw_punteo_sare inmueble : pendientes) {
                    if (proyecto.equals("3")) {
                        if (ActualizaBitacora(proyecto, inmueble, usuario)) {
                        if(GuardarRegistroUEEPAsincronizar(inmueble, proyecto, usuario)){
                        if (ActualizaIdUEPg(proyecto, inmueble, usuario)) {
                            if (ConfirmaUEPg(proyecto, inmueble, usuario)) {
                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
                            } else {
                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                            }
                        }
                        }
                        }
                    } else {
                        validaObject(inmueble, proyecto, usuario);
                    }

                }
            }

        } catch (Exception e) {
            Logger.getLogger(BackingSincroniza.class.getName()).log(Level.SEVERE, null, e);
        }

        return Respuesta;
    }

    public boolean GuardarRegistroUEEPAsincronizar(cat_vw_punteo_sare inmueble, Integer proyecto, String usuario) {
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        int oclOk = 0;
        int oclNg = 0;
        boolean regresar=false;
        cat_vw_punteo_sare_guardado obj = inicializa(inmueble);
        if (InterfaceGuardar.GuardarOclUEEPA(proyecto, obj)) {
            oclOk++;
            Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
            regresar=true;
        } else {
            Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
            regresar=false;
        }
        Respuesta.setDatos("Actualizados:" + oclOk + " Erroneos " + oclNg);
        return regresar;
    }

    private cat_vw_punteo_sare_guardado inicializa(cat_vw_punteo_sare inmueble) {
        cat_vw_punteo_sare_guardado regresar = new cat_vw_punteo_sare_guardado();
        regresar.setId_UE(inmueble.getID_UE().toString());
        regresar.setOrigen(null);
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
        regresar.setTipo_e10(inmueble.getTIPO_E10());
        regresar.setTipo_e10n(inmueble.getTipo_e10n());
        regresar.setE10(inmueble.getE10());
        regresar.setE10_e(inmueble.getE10_e());
        regresar.setE11(inmueble.getE11());
        regresar.sete11A(inmueble.getE11A());
        regresar.setE13(inmueble.getE13());
        regresar.setE13_a(inmueble.getE13A());
        regresar.setTipo_E14(inmueble.getTIPO_E14());
        regresar.setE14_A(inmueble.getTIPO_E10_A());
        regresar.setE14(inmueble.getE14());
        regresar.setTipo_e10_a(inmueble.getTIPO_E10_A());
        regresar.setTipo_e10_an(inmueble.getTipo_e10_an());
        regresar.setE10_A(inmueble.getE10_A());
        regresar.setTipo_e10_b(inmueble.getTIPO_E10_B());
        regresar.setE10_cvevial(inmueble.getE10_cvevial());
        regresar.setE10_B(inmueble.getE10_B());
        regresar.setTipo_e10_c(inmueble.getTIPO_E10_C());
        regresar.setE10_C(inmueble.getE10_C());
        regresar.setDescrubic(inmueble.getDESCRUBIC());
        regresar.setE12(inmueble.getE12());
        regresar.setTipo_E19(inmueble.getTIPO_E19());
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
        regresar.setCoordx(inmueble.getCOORD_X());
        regresar.setCoordy(inmueble.getCOORD_Y());
        regresar.setTipo_e10n_otro(inmueble.getTipo_e10n_otro());
        regresar.setTipo_e10_an_otro(inmueble.getTipo_e10_an_otro());
        regresar.setTipo_e10_bn_otro(inmueble.getTipo_e10_bn_otro());
        regresar.setTipo_e10_cn_otro(inmueble.getTipo_e10_cn_otro());
        regresar.setId_inmueble(inmueble.getId_inmueble());
        regresar.setNavegador(inmueble.getNavegador());
        return regresar;
    }

    public cat_respuesta_services validaObject(cat_vw_punteo_sare inmueble, Integer proyecto, String usuario) {
        cat_respuesta_services Respuesta = new cat_respuesta_services();
        int oclOk = 0;
        int oclNg = 0;
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
                                                if (InsertTrInmuebles(proyecto, inmueble, usuario)) {
                                                    if (ActualizaBitacora(proyecto, inmueble, usuario)) {
                                                        oclOk++;
                                                        if (ActualizaIdUEPg(proyecto, inmueble, usuario)) {
                                                            if (ConfirmaUEPg(proyecto, inmueble, usuario)) {
                                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Completamente Guardado"));
                                                            } else {
                                                                Respuesta.setMensaje(new cat_mensaje("true", "Registro Parcialmente Guardado"));
                                                            }
                                                        } else {
                                                            Respuesta.setMensaje(new cat_mensaje("Exito", "Registro Parcialmente Guardado"));
                                                        }
                                                    } else {
                                                        oclNg++;
                                                        Respuesta.setMensaje(new cat_mensaje("Exito", "Registro Parcialmente Guardado"));
                                                    }
                                                } else {
                                                    oclNg++;
                                                    Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en inmuebles"));
                                                }
                                            } else {
                                                Respuesta.setMensaje(new cat_mensaje("error", "Error al hacer update en TR_UE_SUC"));
                                            }
                                        } else {
                                            Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en TrFrentes "));
                                        }
                                    } else {
                                        Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en TcFrentes "));
                                    }
                                } else {
                                    Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en TrLocalicades "));
                                }
                            } else {
                                Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en TrManzanas "));
                            }
                        } else {
                            Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en TcManzanas "));
                        }
                    } else {
                        Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en TrAgebs "));
                    }
                } else {
                    Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en Agebs "));
                }
            } else {
                Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en ejes "));
            }
        } else {
            Respuesta.setMensaje(new cat_mensaje("error", "Error al insertar en Localidades "));
        }
        Respuesta.setDatos("Actualizados:" + oclOk + " Erroneos " + oclNg);
        return Respuesta;

    }

    protected boolean validaLocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.Validatelocalidades(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.Insertlocalidades(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaEjes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateEjes(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertEjes(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateAgebs(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertAgebs(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaTrAgebs(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateTrAgebs(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertTrAgebs(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaTcManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateTcManzanas(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertTcManzanas(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaTrManzanas(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateTrManzanas(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertTrManzanas(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaTrLocalidades(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateTrLocalidades(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertTrLocalidades(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaTcFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateTcFrentes(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertTcFrentes(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean validaTrFrentes(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.ValidateTrFrentes(proyecto, inmueble, usuario);
        if (!regresa) {
            regresa = InterfaceSincroniza.InsertTrFrentes(proyecto, inmueble, usuario);
        }
        return regresa;
    }

    protected boolean UpdateTrUESuc(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.UpdateTrUeSuc(proyecto, inmueble, usuario);
        return regresa;
    }

    protected boolean InsertTrInmuebles(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.InsertTr_Inmuebles(proyecto, inmueble, usuario);
        return regresa;
    }

    protected boolean ActualizaBitacora(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.getActualizaBitRegCveunica(proyecto, inmueble, usuario);
        return regresa;
    }

    protected boolean ActualizaIdUEPg(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.getActualizaIdUe(proyecto, inmueble, usuario);
        return regresa;
    }

    protected boolean ConfirmaUEPg(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.getConfirmaUe(proyecto, inmueble, usuario);
        return regresa;
    }

    protected boolean ActualicaEstatusComplemento(Integer proyecto, cat_vw_punteo_sare inmueble, String usuario) {
        boolean regresa;
        regresa = InterfaceSincroniza.getActualizaEstatusComplemento(proyecto, inmueble, usuario);
        return regresa;
    }

}
