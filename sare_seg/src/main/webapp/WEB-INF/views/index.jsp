<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="resources/css/app.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/animate.css" rel="stylesheet" type="text/css"/>
    <title>SARE 2019</title>
     <!-- Compiled and minified CSS -->
     <script src='resources/js/services.js'></script>
     <link rel="stylesheet" href="resources/css/materialize.css">
     <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700|Montserrat:200,300,400,500,700,800,900|Roboto:300,400,500,700" rel="stylesheet">
     <link rel="stylesheet" href="resources/css/sweetalert2.min.css" type="text/css"/>
     
     <!-- <script src="resources/js/jquery-2.1.1.min.js"  ></script> -->
    <script src="resources/js/sweetalert2.min.js"></script>
    <script src="resources/js/sendAjax.js" type="text/javascript"></script>
    <script src="resources/js/main.js" type="text/javascript"></script>
    <script src="resources/config/dataSource.js" type="text/javascript"></script>
    <script src="resources/config/map.js" type="text/javascript"></script>
    <script src="resources/config/config.js" type="text/javascript"></script>
    <script src="resources/config/tree.js" type="text/javascript"></script>
    <script src="resources/config/interface.js" type="text/javascript"></script>
    <!-- Compiled and minified JavaScript -->
    <!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.js"></script> -->
    <script src='resources/js/jquery-2.1.1.min.js'></script>
    <script src='resources/js/actionsDom.js'></script>
    <script src='resources/js/objFormulario.js'></script>
    <script src="resources/js/config.js" type="text/javascript"></script>
     <!-- FONT AWSOME -->
        <link href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" rel="stylesheet" type="text/css"/>
  </head>

  <body>
    <div class="sare">
      <!-- HEADER -->
      <header class="container-header">
        <div class="navbar-fixed"> 
          <nav>
            <div class="nav-wrapper">
              <a href="#" class="brand-logo">SARE</a>
              <a href="#" data-activates="slide-out" class="button-collapse hide-on-large-only"><i class="material-icons tooltipped" data-position="right" data-tooltip="Menu de Opciones" >menu</i></a>
              <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a id="ancla-map" onclick='handleModuleScroll(event)' href="#container-map">Mapa</a></li>
                <li><a id="ancla-form" onclick='handleModuleScroll(event)' href="#container-form">Formulario</a></li>
              </ul>
            </div>
          </nav>
          <!-- MENU MOVIL -->
          <ul id="slide-out" class="sidenav">
            <li><div class="user-view">
              <div class="background">
                <img src="resources/images/fondos/censos.jpg">
              </div>
              <a href="#user" class="img-user"><img class="circle" src="resources/images/iconos/boy.png"></a>
              <a href="#name"><span class="white-text name">Bievenido(a) al:</span></a>
              <a href="#email"><span class="white-text email">SARE</span></a>
            </div></li>
            <li><a class="waves-effect" onclick='handleModuleScroll(event)' id="ancla-map-movil" href="#container-map"><i class="material-icons">map</i>Mapa</a></li>
            <li><a class="waves-effect" onclick='handleModuleScroll(event)' id="ancla-form-movil" href="#container-form"><i class="material-icons">storage</i>Formulario</a></li>
            <li><div class="divider"></div></li>
            <li><a class="subheader">Opciones</a></li>
            <li><a disabled id='save-movil-option' class="option-disabled"><i class="material-icons">save</i>Guardar</a></li>
            <li><a disabled id="cancel-movil-option" class="option-disabled"><i class="material-icons">highlight_off</i>Cancelar</a></li>
            <li onclick="opcionMenu(3)"><a href="#!"><i class="material-icons">content_paste</i>Reportes</a></li>
            <li onclick="imprimir()"><a href="#!"><i class="material-icons">local_printshop</i>Imprimir</a></li>
            <li><a onclick="handleLogOut()"><i class="material-icons">exit_to_app</i>Cerrar Sesión</a></li>
          </ul>
          <!-- END MENU MOVIL -->
        </div>
      </header>
      <!--END HEADER  -->
     
      <!-- CONTAINER SEARCH -->
      <section id='container-search' class='container-search initial animated slideInDown slow' data-visible='hide'>
        <h5  onclick="handleVisibleSearch()" id="titulo-busqueda"><i class="material-icons">search</i>BUSQUEDA DE CLAVE</h5>
        <a id='arrow-search' class='hide-search'  onclick="handleVisibleSearch()" tabindex="2">
          <i class="material-icons tooltipped" data-position="left" data-tooltip="Ver / Ocultar Busqueda">keyboard_arrow_down</i>
        </a>
        <div class="row row-search" id="row-search">
          <div class="col s12 m8 offset-m2 l6 offset-l3 wrap-search">
            <div class="input-field">
              <input 
                placeholder="Realizar Busqueda..." 
                id="clave-busqueda" 
                name="clave-busqueda" 
                type="text" 
                class="validate"
                onkeypress="handleSearchCleeValidation(event)"
              >
            </div>
              <div class="btns-search">   
                <a onclick="buscarUE()" class="btn-search search-third tooltipped" data-position="bottom" data-tooltip="Busqueda por clave" >Buscar</a>
                <a onclick="handleViewCleeList()" class="btn-search search-third tooltipped" data-position="bottom" data-tooltip="Ver lista de claves" >Ver</a>
              </div>
          </div>
        </div>
      </section>
      <!-- END CONTAINER SARCH -->

      <!-- CONTAINER RATIFICA -->
      <section class="container-ratifica" id="container-ratifica" data-visible='hide'>
        <div class="row row-ratifica" id="row-ratifica">
          <div class="col s12 wrap-ratifica" id="wrap-ratifica">
            <p class="title-ratifica">¿Deséas Ratificar?</p>
            <div class="wrap-btns-ratifica">
              <div onclick="ratificar('si')" class="btn-ratifica si-ratifica" id="wrap-si-ratifica">
                <div class="icon-si-ratifica"> <i class="material-icons">check</i></div>
                <div class="text-si-ratifica">Ratificar</div>
              </div>
              <div onclick="ratificar('no')" class="btn-ratifica no-ratifica" id="wrap-no-ratifica">
                <div class="text-no-ratifica">No Ratificar</div>
                <div class="icon-no-ratifica"> <i class="material-icons">close</i></div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- END CONTAINER RATIFICA -->

      <!-- CONTAINER MAP -->
      <section class="container-map" id="container-map">
        <div class="row row-map">
          <!-- mapa -->
          <div class="col s12 l10 wrap-map  ">
            <div id="mapa"></div>
            <div class="wrap-alert alert-pick" id="wrap-alert-pick">
              <div class="icon-warning"><img src="resources/images/iconos/warning.png" alt="warning"></div>
              <div class="text-warning">Realice el punteo por favor</div>
            </div>
          </div>
          <!-- end mapa -->
          <!-- opciones -->
          <div class="col s12 item-options animated ">
            <i class="material-icons" id="item-options" data-visible="show" onclick="handleOptions()">keyboard_arrow_down</i>
          </div>
          <div class="col s12 l2 wrap-options animated " id="wrap-options">
            <div class="items-check">
              <div class="check-option">
                <p class="option-title" id="option-unicos">Unicos</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox" id="checkbox-unicos" onchange="handleChangeOptions('unicos')">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title" id="option-sucursal">Sucursales</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox" checked="true" id="checkbox-sucursal" onchange="handleChangeOptions('sucursal')">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title" id="option-denue">DENUE</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox" checked="true" id="checkbox-denue" onchange="handleChangeOptions('denue')">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title" id="option-matrices">Matrices</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox" id="checkbox-matrices" onchange="handleChangeOptions('matrices')">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title" id="option-postes">Postes de Kilometraje</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox" id="checkbox-postes" onchange="handleChangeOptions('postes')">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>

            </div>

            <span class="options-line"></span>

            <div class="items-radio">  

              <div class="radio-option">
                <input id="identificar" value="identificar" name="accion" type="radio"/>
                <label for="identificar">Identificar</label>
              </div>

              <div class="radio-option">
                  <input  id="puntear" checked="true" value="puntear" name="accion" type="radio"/>
                <label for="puntear">Puntear</label>
              </div>

              <div class="radio-option">
                <input id="v-calle" value="v_calle" name="accion" type="radio"/>
                <label for="v-calle">Vista Calle</label>
              </div>

            </div> 
          </div>
          <!-- end opciones -->
        </div>
      </section>
      <!-- END CONTAINER MAP -->

      <!-- CONTAINER FORM -->
      <section class="container-form" id="container-form">
        <form>
          <div class="row row-form">
            
            <!-- OPCIÓN REFERENCIA -->
            <div class="col s12 m6 l4 wrap-form op-referencia animated slideInLeft">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('referencia')" id="title-referencia">
                <i class="material-icons" id="icon-referencia">add</i>
                <div class="titlew">
                  <p>Referencia</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-referencia">
                <div class="input-field">
                  <input placeholder="CLEE_EST" id="id_UE" name="id_UE" type="text" disabled >
                  <label for="id_UE">CLEE_EST</label>
                </div>
                <div class="input-field">
                  <input placeholder="Origen" id="origen" name="origen" type="text" disabled >
                  <label for="origen">Origen</label>
                </div>
                <div class="input-field">
                  <input placeholder="C154" id="c154" name="c154" type="text" disabled >
                  <label for="c154">C154</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre del Establecimiento" id="e08" name="e08" type="text" disabled >
                  <label for="e08">Nombre del Establecimiento</label>
                </div>
                <div class="input-field">
                  <input placeholder="Razón Social" id="e09" name="e09" type="text" disabled >
                  <label for="e09">Razón Social</label>
                </div>
                <div class="input-field">
                  <input placeholder="SCIAN" id="e17_DESC" name="e17_DESC" type="text" disabled >
                  <label for="e17_DESC">SCIAN</label>
                </div>
                <div class="input-field">
                  <a onclick="handleActionTarget('ubicacion-geografica')" class="next-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN REFERENCIA -->

            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->
            <div class="col s12 m6 l4 wrap-form op-ubicacion-geografica animated slideInUp">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('ubicacion-geografica')" id="title-ubicacion-geografica">
                <i class="material-icons" id="icon-ubicacion-geografica">add</i> 
                <div class="titlew">
                  <p>Ubicación Geográfica</p>
                </div> 
              </div>
              <div class="inputs-option z-depth-3" id="inputs-ubicacion-geografica">
                <div class="input-field">
                  <input placeholder="Clave" id="e03" name="e03" type="text" disabled >
                  <label for="e03">Entidad Federativa (Clave)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e03N" name="e03N" type="text" disabled >
                  <label for="e03N">Entidad Federativa (Nombre)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Clave" id="e04" name="e04" type="text" disabled >
                  <label for="e04">Municipio o Delegación (Clave)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e04N" name="e04N" type="text" disabled >
                  <label for="e04N">Municipio o Delegación (Nombre)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Clave" id="e05" name="e05" type="text" disabled >
                  <label for="e05N">Localidad (Clave)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e05N" name="e05N" type="text" disabled >
                  <label for="e05">Localidad (Nombre)</label>
                </div>
                <div class="input-field">
                  <input placeholder="AGEB" id="e06" name="e06" type="text" disabled >
                  <label for="e06">AGEB</label>
                </div>
                <div class="input-field">
                  <input placeholder="Manzana" id="e07" name="e07" type="text" disabled >
                  <label for="e07">Manzana</label>
                </div>
                <div class="input-field btns-prev-next">
                  <a onclick="handleActionTarget('referencia')" class="previous-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                  <a onclick="handleActionTarget('domicilio')"  class="next-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->

            <!-- OPCIÓN DOMICILIO -->
            <div class="col s12 m6 l4 wrap-form op-domicilio animated slideInRight">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('domicilio')" id="title-domicilio">
                <i class="material-icons" id="icon-domicilio">add</i>
                <div class="titlew">
                  <p>Domicilio</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-domicilio">
                <div class="input-field">
                  <input id="tipo_e10" name="tipo_e10" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad" id="tipo_e10n" name="tipo_e10n" type="text" disabled >
                  <label for="tipo_e10n">Tipo de Vialidad</label>
                </div>
                <div class="input-field">
                  <input id="tipo_e10_cvevial" name="tipo_e10_cvevial" type="text" hidden readonly >
                </div>
                <div class="input-field">
                  <input placeholder="Nombre de Vialidad" id="e10" name="e10" type="text" disabled >
                  <label for="e10">Nombre de la Vialidad</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre E10 E" id="e10_e" name="e10_e" type="text" disabled >
                  <label for="e10_e">Nombre E10 E</label>
                </div>
                <div class="input-field">
                  <input placeholder="E11 Número Exterior" id="e11" name="e11" type="text" disabled >
                  <label for="e11">E11 Número Exterior</label>
                </div>
                <div class="input-field">
                  <input placeholder="E11 A Letra" id="e11_a" name="e11_a" type="text" disabled >
                  <label for="e11_a">E11 A Letra</label>
                </div>
                <div class="input-field">
                  <input placeholder="E13 Número Interior" id="e13" name="e13" type="text" disabled >
                  <label for="e13">E13 Número Interior</label>
                </div>
                <div class="input-field">
                  <input placeholder="E13 A Letra" id="e13_a" name="e13_a" type="text" disabled >
                  <label for="e13_a">E13 A Letra</label>
                </div>
                <div class="input-field btns-prev-next">
                  <a onclick="handleActionTarget('ubicacion-geografica')" class="previous-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                  <a onclick="handleActionTarget('asentamiento')"  class="next-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN DOMICILIO -->

            <!-- OPCIÓN ASENTAMIENTO -->
            <div class="col s12 m6 l4 wrap-form op-referencia animated slideInLeft">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('asentamiento')" id="title-asentamiento">
                <i class="material-icons" id="icon-asentamiento">add</i>
                <div class="titlew">
                  <p>Asentamiento</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-asentamiento">
                <div class="input-field">
                  <select class="browser-default" id="tipo_E14" name="tipo_E14">
                  </select>
                  <label id="labeltipo_E14" for="tipo_E14">Tipo de Asentamiento Humano</label>
                </div>
                <div class="input-field">
                  <input placeholder="Código Postal" id="e14_A" name="e14_A" type="text" disabled >
                  <label for="e14_A">Código Postal</label>
                </div>
                <div id="cp_val"></div>
                <div class="input-field">
                  <input placeholder="Nombre del Asentamiento" id="e14" name="e14" type="text" disabled >
                  <label for="e14">Nombre del Asentamiento</label>
                </div>
                <div class="input-field btns-prev-next">
                  <a onclick="handleActionTarget('domicilio')" class="previous-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                  <a onclick="handleActionTarget('entre-vialidades')"  class="next-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN ASENTAMIENTO -->

            <!-- OPCIÓN ENTRE VIALIDADES -->
            <div class="col s12 m6 l4 wrap-form op-entre-vialidades animated slideInUp">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('entre-vialidades')" id="title-entre-vialidades">
                <i class="material-icons"  id="icon-entre-vialidades">add</i>
                <div class="titlew">
                  <p>Entre Vialidades</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-entre-vialidades">
                <div class="input-field">
                  <input id="tipo_e10_a" name="tipo_e10_a" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad 1" id="tipo_e10_an" name="tipo_e10_an" type="text" disabled >
                  <label for="tipo_e10_an">Tipo de Vialidad No.1</label>
                </div>
                <div class="input-field">
                  <input id="e10a_cvevial" name="e10a_cvevial" type="text" hidden >
                  <label for="e10a_cvevial" hidden="">**Hasta el punteo</label>
                </div>
                <div class="input-field">
                  <select class="browser-default" id="e10_A" name="e10_A">
                  </select>
                  <label id="labele10_A" for="e10_A">Nombre de la Vialidad No.1</label>
                </div>
                <div class="input-field">
                  <input id="tipo_e10_b" name="tipo_e10_b" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad 2" id="tipo_e10_bn" name="tipo_e10_bn" type="text" disabled >
                  <label for="tipo_e10_bn">Tipo de Vialidad No.2</label>
                </div>
                <div class="input-field">
                  <input id="e10b_cvevial" name="e10b_cvevial" type="text" hidden >
                  <label for="e10b_cvevial" hidden="">**Hasta el punteo</label>
                </div>
                <div class="input-field">
                  <select class="browser-default" id="e10_B" name="e10_B">
                  </select>
                  <label id="labele10_B" for="e10_B">Nombre de la Vialidad No.2</label>
                </div>
                <div class="input-field btns-prev-next">
                  <a onclick="handleActionTarget('asentamiento')" class="previous-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                  <a onclick="handleActionTarget('calle-posterior')"  class="next-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN ENTRE VIALIDADES -->

            <!-- OPCIÓN CALLE POSTERIOR -->
            <div class="col s12 m6 l4 wrap-form op-calle-posterior animated slideInRight">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('calle-posterior')" id="title-calle-posterior">
                <i class="material-icons" id="icon-calle-posterior">add</i>
                <div class="titlew">
                  <p>Calle Posterior</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-calle-posterior">
                <div class="input-field">
                  <input  id="tipo_e10_c" name="tipo_e10_c" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad Posterior" id="tipo_e10_cn" name="tipo_e10_cn" type="text" disabled >
                  <label for="tipo_e10_cn">Tipo de Vialidad Posterior</label>
                </div>
                <div class="input-field">
                  <input id="e10c_cvevial" name="e10c_cvevial" type="text" hidden >
                  <label for="e10c_cvevial" hidden="">**Hasta el punteo</label>
                </div>
                <div class="input-field">
                  <select class="browser-default" id="e10_C" name="e10_C">
                  </select>
                  <label id="labele10_C" for="e10_C">Nombre de la Vialidad</label>
                </div>
                <div class="input-field">
                  <textarea id="descrubic" name="descrubic" class="materialize-textarea" disabled></textarea>
                  <label for="textarea1">Descripción de la ubicación del establecimiento</label>
                </div>
                <div class="input-field btns-prev-next">
                  <a onclick="handleActionTarget('entre-vialidades')" class="previous-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                  <a onclick="handleActionTarget('edificio')"  class="next-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN CALLE POSTERIOR -->

            <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->
            <div class="col s12 m6 l4 wrap-form op-edificio-centros-comerciales animated slideInLeft">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('edificio')" id="title-edificio">
                <i class="material-icons" id="icon-edificio">add</i>
                <div class="titlew">
                  <p>Edificio, Centro Comercial</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-edificio">
                <div class="input-field">
                  <input placeholder="E12 Edificio" id="E12" name="E12" type="text" disabled >
                  <label for="E12">E12 Edificio</label>
                </div>
                <div class="input-field">
                  <select id="tipo_e19" name="tipo_e19">
                    <option value="" disabled selected>Seleccione...</option>
                  </select>
                  <label for="tipo_e19">Tipo de Corredor o Centro Comercial</label>
                </div>
                <div class="input-field">
                  <input placeholder="E19 Nombre" id="e19" name="e19" type="text" disabled >
                  <label for="e19">E19 Nombre</label>
                </div>
                <div class="input-field">
                  <input placeholder="E12P Piso" id="E12p" name="E12p" type="text" disabled >
                  <label for="E12p">E12P Piso</label>
                </div>
                <div class="input-field">
                  <input placeholder="E20 No. Local" id="e20" name="e20" type="text" disabled >
                  <label for="e20">E20 No. Local</label>
                </div>
                <div class="input-field btns-prev-next">
                  <a onclick="handleActionTarget('calle-posterior')" class="previous-wrap"><img src="resources/images/iconos/right-arrow.png" alt="next"></a>
                </div>
              </div>
            </div>
            <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->
  

          </div>
        </form>
      </section>
      <!-- END CONTAINER FORM -->

      <!-- CONTAINER OPTIONS BUTTONS -->
      <div class="fixed-action-btn click-to-toggle btn-float-map active">
        <a class="btn-floating btn-large btn-options-menu tooltipped animated bounceInUp slower delay-1s" data-position="bottom" data-tooltip="Menu">
          <i class="large material-icons">menu</i>
        </a>
        <ul>
          <li><a id='item-save-option' disabled onclick="handleFormValidations()" class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Guardar"><i class="material-icons">save</i></a></li>
          <li><a id='item-cancel-option' disabled onclick="handleCancelClick()" class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Cancelar"><i class="material-icons">highlight_off</i></a></li>
          <li onclick="opcionMenu(2);"><a id='item-report-option' class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Reportes"><i class="material-icons">content_paste</i></a></li> 
          <li onclick="opcionMenu(4);"><a class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Imprimir"><i class="material-icons">local_printshop</i></a></li> 
          <li><a onclick="handleLogOut()" class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Salir"><i class="material-icons">exit_to_app</i></a></li>
        </ul>
      </div>
      <!-- END CONTAINER OPTIONS BUTTONS -->

      <!-- FOOTER -->
      <footer class="page-footer">
        <div class="container">
          <img src="resources/images/logos/INEGI.png" alt="inegi">
        </div>
        <div class="footer-copyright">
          <div class="container">
            © 2019 Copyright INEGI
          </div>
        </div>
      </footer>
      <!-- END FOOTER -->

      <button onclick="handleScrollTop()" id="btn-top" class="animated bounceInUp slow delay-2s">
        <i class="material-icons tooltipped" 
          data-position="left" 
          data-tooltip="Ir a inicio">
          arrow_upward
        </i>
      </button>

    </div>    
  </body>

    <script>
      function loadScript(url, callback) {
        var script = document.createElement("script")
        script.type = "text/javascript"

        if (script.readyState) {  
          //IE
          script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
              script.onreadystatechange = null;
              callback();
            }
          };
          } 
          else {  
            //Others
            script.onload = function () {
              callback();
            };
          }

          script.src = url;
          document.getElementsByTagName("head")[0].appendChild(script);
        }
    </script>
  
    <script type="text/javascript">
        
      loadScript("resources/js/materialize.min.js", function () {
        // initialization code                
        $(".button-collapse").sideNav(
          {
            edge: 'left',
            draggable: true
          }
        );

        $('.tooltipped').tooltip({delay: 20});
        $('.dropdown-button').dropdown('open');
        $('select').material_select();
      })
    </script> 

    <script>
      $(document).ready(function(){
	      $(document).keydown(function(event) {
          if (event.ctrlKey==true && (event.which == '61' || event.which == '107' || event.which == '173' || event.which == '109'  || event.which == '187'  || event.which == '189'  ) ) {
          //alert('disabling zooming'); 
		      event.preventDefault()
		      // 107 Num Key  +
		      //109 Num Key  -
		      //173 Min Key  hyphen/underscor Hey
		      // 61 Plus key  +/=
	        }
	      });

        $(window).bind('mousewheel DOMMouseScroll', function (event) {
          if (event.ctrlKey == true) {
            //alert('disabling zooming'); 
            event.preventDefault()
          }
        })
      })
    </script>

    <script src="http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6" type="text/javascript"></script>

</html>
