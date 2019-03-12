<%-- 
    Document   : index
    Created on : 18/02/2019, 10:30:15 AM
    Author     : LIDIA.VAZQUEZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="resources/css/app.css" rel="stylesheet" type="text/css"/>
    <title>SARE 2019</title>
     <!-- Compiled and minified CSS -->
     <script src='resources/js/services.js'></script>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
     <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700|Montserrat:200,300,400,500,700,800,900|Roboto:300,400,500,700" rel="stylesheet">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.33.1/dist/sweetalert2.min.css">
     
     <!-- <script src="resources/js/jquery-2.1.1.min.js"  ></script> -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.33.1/dist/sweetalert2.min.js"></script>
    <script src="resources/js/main.js" type="text/javascript"></script>
    <script src="resources/config/dataSource.js" type="text/javascript"></script>
    <script src="resources/config/map.js" type="text/javascript"></script>
    <script src="resources/config/config.js" type="text/javascript"></script>
    <script src="resources/config/tree.js" type="text/javascript"></script>
    <script src="resources/config/interface.js" type="text/javascript"></script>
    <!-- Compiled and minified JavaScript -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.js"></script>
    <script src='resources/js/actionsDom.js'></script>
    <script src='resources/js/objFormulario.js'></script>
  </head>

  <body>
    <div class="sare">
      <header class="container-header">
        <div class="navbar-fixed"> 
          <nav>
            <div class="nav-wrapper">
              <a href="#" class="brand-logo">SARE</a>
              <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="#container-map">Mapa</a></li>
                <li><a href="#container-form">Opciones</a></li>
              </ul>
            </div>
          </nav>
        </div>
      </header>
      
      <!-- CONTAINER SEARCH -->
      <section id='container-search' class='container-search' data-visible='show'>
        <h5 id="titulo-busqueda">BUSQUEDA</h5>
        <a id='arrow-search' class='hide-search'  onclick="handleVisibleSearch()" tabindex="2">
          <i class="material-icons">keyboard_arrow_down</i>
        </a>
        <div class="row row-search" id="row-search">
          <div class="col s12 m8 offset-m2 l6 offset-l3 wrap-search">
            <div class="input-field">
              <input placeholder="Realizar Busqueda..." id="first_name" type="text" class="validate">
            </div>
              <div class="btns-search">   
                <a onclick="buscarUE()" class="btn-search search-third">Buscar</a>
                <a onclick=" handleHideAlertPickMap()" class="btn-search search-third">Ver</a>
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
              <div onclick="ratificar('si')" class="btn-ratifica si-ratifica">
                <div class="icon-si-ratifica"> <i class="material-icons">check</i></div>
                <div class="text-si-ratifica">Ratificar</div>
              </div>
              <div onclick="ratificar('no')" class="btn-ratifica no-ratifica">
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
          <div class="col s12 l10 wrap-map">
            <div id="mapa"></div>
            <div class="wrap-alert alert-pick" id="wrap-alert-pick">
              <div class="icon-warning"><img src="resources/images/iconos/warning.png" alt="warning"></div>
              <div class="text-warning">Realice el punteo por favor</div>
            </div>
          </div>
          <!-- end mapa -->
          <!-- opciones -->
          <div class="col s12 item-options">
            <i class="material-icons" id="item-options" data-visible="show" onclick="handleOptions()">keyboard_arrow_down</i>
          </div>
          <div class="col s12 l2 wrap-options" id="wrap-options">
            <div class="items-check">
              <div class="check-option">
                <p class="option-title" id="option-altas">Altas</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox" id="checkbox-altas" onchange="handleChangeOptions('altas')">
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
                    <input type="checkbox" id="checkbox-sucursal" onchange="handleChangeOptions('sucursal')">
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
                    <input type="checkbox" id="checkbox-denue" onchange="handleChangeOptions('denue')">
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
                <input onclick="HandleWhatDoYouWantToDo('identificar')" id="identificar" name="radio" type="radio"/>
                <label for="identificar">Identificar</label>
              </div>

              <div class="radio-option">
                <input onclick="HandleWhatDoYouWantToDo('puntear')" id="puntear" name="radio" type="radio"/>
                <label for="puntear">Puntear</label>
              </div>

              <div class="radio-option">
                <input onclick="HandleWhatDoYouWantToDo('calle')" id="v-calle" name="radio" type="radio"/>
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
            <div class="col s12 m6 l4 wrap-form op-referencia">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('referencia')" id="title-referencia">
                <i class="material-icons" id="icon-referencia">add</i>
                <div class="titlew">
                  <p>Referencia</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-referencia">
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
                  <input placeholder="SCIAN" id="codigo_scian" name="codigo_scian" type="text" disabled >
                  <label for="codigo_scian">SCIAN</label>
                </div>
              </div>
            </div>
            <!-- OPCIÓN REFERENCIA -->

            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->
            <div class="col s12 m6 l4 wrap-form op-ubicacion-geografica">
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
                  <input placeholder="Nombre" id="e03n" name="e03n" type="text" disabled >
                  <label for="e03n">Entidad Federativa (Nombre)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Clave" id="e04" name="e04" type="text" disabled >
                  <label for="e04">Municipio o Delegación (Clave)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e04n" name="e04n" type="text" disabled >
                  <label for="e04n">Municipio o Delegación (Nombre)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Clave" id="e05n" name="e05n" type="text" disabled >
                  <label for="e05n">Localidad (Clave)</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e05" name="e05" type="text" disabled >
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
              </div>
            </div>
            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->

            <!-- OPCIÓN DOMICILIO -->
            <div class="col s12 m6 l4 wrap-form op-domicilio">
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
              </div>
            </div>
            <!-- OPCIÓN DOMICILIO -->

            <!-- OPCIÓN ASENTAMIENTO -->
            <div class="col s12 m6 l4 wrap-form op-referencia">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('asentamiento')" id="title-asentamiento">
                <i class="material-icons" id="icon-asentamiento">add</i>
                <div class="titlew">
                  <p>Asentamiento</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-asentamiento">
                <div class="input-field">
                  <select id="tipo_e14" name="tipo_e14" disabled>
                    <option value="" disabled selected>Seleccione...</option>
                    <option value="1">Option 1</option>
                  </select>
                  <label for="tipo_e14">Tipo de Asentamiento Humano</label>
                </div>
                <div class="input-field">
                  <input placeholder="Código Postal" id="e14_a" name="e14_a" type="text" disabled >
                  <label for="e14_a">Código Postal</label>
                </div>
                <div id="cp_val"></div>
                <div class="input-field">
                  <input placeholder="Nombre del Asentamiento" id="e14" name="e14" type="text" disabled >
                  <label for="e14">Nombre del Asentamiento</label>
                </div>
              </div>
            </div>
            <!-- OPCIÓN ASENTAMIENTO -->

            <!-- OPCIÓN ENTRE VIALIDADES -->
            <div class="col s12 m6 l4 wrap-form op-entre-vialidades">
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
                  <select id="tipo_e10_a" name="tipo_e10_a" disabled>
                    <option value="" disabled selected>Seleccione...</option>
                    <option value="1">Option 1</option>
                  </select>
                  <label for="tipo_e10_a">Nombre de la Vialidad No.1</label>
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
                  <select id="tipo_e10_b" name="tipo_e10_b" disabled>
                    <option value="" disabled selected>Seleccione</option>
                    <option value="1">Option 1</option>
                  </select>
                  <label for="tipo_e10_b">Nombre de la Vialidad No.2</label>
                </div>
              </div>
            </div>
            <!-- OPCIÓN ENTRE VIALIDADES -->

            <!-- OPCIÓN CALLE POSTERIOR -->
            <div class="col s12 m6 l4 wrap-form op-calle-posterior">
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
                  <select id="e10_c" name="e10_c" disabled>
                    <option value="" disabled selected>Seleccione...</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                  </select>
                  <label for="e10_c">Nombre de la Vialidad</label>
                </div>
                <div class="input-field">
                  <textarea id="descrubic" name="descrubic" class="materialize-textarea" disabled></textarea>
                  <label for="textarea1">Descripción de la ubicación del establecimiento</label>
                </div>
              </div>
            </div>
            <!-- OPCIÓN CALLE POSTERIOR -->

            <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->
            <div class="col s12 m6 l4 wrap-form op-edificio-centros-comerciales ">
              <div class="title-option" data-visible="show" onclick="handleVisibleForm('edificio')" id="title-edificio">
                <i class="material-icons" id="icon-edificio">add</i>
                <div class="titlew">
                  <p>Edificio, Centro Comercial</p>
                </div>
              </div>
              <div class="inputs-option z-depth-3" id="inputs-edificio">
                <div class="input-field">
                  <input placeholder="E12 Edificio" id="e12" name="e12" type="text" disabled >
                  <label for="e12">E12 Edificio</label>
                </div>
                <div class="input-field">
                  <select id="tipo_e19" name="tipo_e19">
                    <option value="" disabled selected>Seleccione...</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                  </select>
                  <label for="tipo_e19">Tipo de Corredor o Centro Comercial</label>
                </div>
                <div class="input-field">
                  <input placeholder="E19 Nombre" id="e19" name="e19" type="text" disabled >
                  <label for="e19">E19 Nombre</label>
                </div>
                <div class="input-field">
                  <input placeholder="E12P Piso" id="e12p" name="e12p" type="text" disabled >
                  <label for="e12p">E12P Piso</label>
                </div>
                <div class="input-field">
                  <input placeholder="E20 No. Local" id="e20" name="e20" type="text" disabled >
                  <label for="e20">E20 No. Local</label>
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
        <a class="btn-floating btn-large btn-options-menu">
          <i class="large material-icons">menu</i>
        </a>
        <ul>
          <li><a id='item-save-option' disabled onclick="handleFormValidations()" class="btn-floating btn-item-menu tooltipped click-to-toggle" data-position="left" data-tooltip="I am a tooltip"><i class="material-icons">save</i></a></li>
          <li><a id='item-cancel-option' disabled class="btn-floating btn-item-menu"><i class="material-icons">highlight_off</i></a></li>
          <li><a class="btn-floating btn-item-menu"><i class="material-icons">content_paste</i></a></li>
          <li><a class="btn-floating btn-item-menu"><i class="material-icons">local_printshop</i></a></li>
          <li><a class="btn-floating btn-item-menu"><i class="material-icons">exit_to_app</i></a></li>
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

      <button onclick="handleScrollTop()" id="btn-top"><i class="material-icons">arrow_upward</i></button>

    </div>    
  </body>
  
    <script>
      const loadScript = (url, callback) => {
        const script = document.createElement('script')
        // IE
        if (script.readyState) { 
          script.onreadystatechange = () => {
            if (script.readyState === 'loaded' || script.readyState === 'complete') {
              script.onreadystatechange = null
              callback()
            }
          }
        } 
        // Others
        else { script.onload = () => callback() }
      
        script.src = url;
        document.getElementsByTagName('head')[0].appendChild(script);
      }
    </script>
  
    <script type="text/javascript">
      loadScript("resources/js/materialize.min.js", () => {
        // initialization code                
        $(".button-collapse")
          .sideNav({
            edge: 'left',
            draggable: true
          })

        $('.dropdown-button').dropdown('open')
        $('select').material_select()
        $('.tooltipped').tooltip()
        //$('.fixed-action-btn').floatingActionButton();
      })
    </script> 

    <script src="http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6" type="text/javascript"></script>

</html>
