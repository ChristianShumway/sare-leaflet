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
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
     <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  </head>

  <body>
    <div class="sare">
      <header class="container-header">
        <div class="navbar-fixed"> 
          <nav>
            <div class="nav-wrapper">
              <a href="#" class="brand-logo">SARE</a>
              <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="#">Mapa</a></li>
                <li><a href="#">Opciones</a></li>
              </ul>
            </div>
          </nav>
        </div>
      </header>
      
      <section id='container-search' class='container-search' data-visible='show'>
        <a id='arrow-search' class='hide-search'  onclick="handleVisibleSearch()" tabindex="2">
          <i class="material-icons">keyboard_arrow_down</i>
        </a>
        <div class="row row-search" id="row-search">
          <div class="col s12 m8 offset-m2 l6 offset-l3 wrap-search">
            <div class="input-field">
              <input placeholder="Realizar Busqueda..." id="first_name" type="text" class="validate">
            </div>
              <a class="btn-search search-third">Buscar</a>
              <a class="btn-search search-third">Ver</a>
          </div>
        </div>
      </section>

      <!-- CONTAINER MAP -->
      <section class="container-map">
        <div class="row row-map">
          <!-- mapa -->
          <div class="col s12 l10 wrap-map">
            <div id="mapa"></div>
          </div>
          <!-- end mapa -->
          <!-- opciones -->
          <div class="col s12 l2 wrap-options">
            <div class="items-check">
              
              <div class="check-option">
                <p class="option-title">Altas</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title">Sucursales</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title">DENUE</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title">Matrices</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>
  
              <div class="check-option">
                <p class="option-title">Postes de Kilometraje</p>
                <div class="switch">
                  <label>
                    Inactivo
                    <input type="checkbox">
                    <span class="lever"></span>
                    Activo
                  </label>
                </div>
              </div>

            </div>

            <span class="options-line"></span>

            <div class="items-radio">
              
              <div class="radio-option">
                <input id="radio1" name="radio" type="radio"/>
                <label for="radio1">Vista Calle</label>
              </div>

              <div class="radio-option">
                <input id="radio2" name="radio" type="radio"/>
                <label for="radio2">Identificar</label>
              </div>

            </div> 
          </div>
          <!-- end opciones -->
        </div>
      </section>
      <!-- END CONTAINER MAP -->

      <!-- CONTAINER FORM -->
      <section class="container-form">
        <form>
          <div class="row row-form">
            
            <!-- OPCIÓN REFERENCIA -->
            <div class="col s12 m6 l4 wrap-form op-referencia ">
              <div class="title-option">
                <p>Referencia</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <input placeholder="Origen" id="origen" name="origen" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="C154" id="c154" name="c154" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="Nombre del Establecimiento" id="e08" name="e08" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="Razón Social" id="e09" name="e09" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="SCIAN" id="codigo_scian" name="codigo_scian" type="text" >
                </div>
              </div>
            </div>
            <!-- OPCIÓN REFERENCIA -->

            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->
            <div class="col s12 m6 l4 wrap-form op-ubicacion-geografica ">
              <div class="title-option">
                <p>Ubicación Geográfica</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <input placeholder="Clave" id="e03" name="e03" type="text" >
                  <label for="e03">Entidad Federativa</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e03n" name="e03n" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="Clave" id="e04" name="e04" type="text" >
                  <label for="e04">Municipio o Delegación</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e04n" name="e04n" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="Clave" id="e05n" name="e05n" type="text" >
                  <label for="e05n">Localidad</label>
                </div>
                <div class="input-field">
                  <input placeholder="Nombre" id="e05" name="e05" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="AGEB" id="e06" name="e06" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="Manzana" id="e07" name="e07" type="text" >
                </div>
              </div>
            </div>
            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->

            <!-- OPCIÓN DOMICILIO -->
            <div class="col s12 m6 l4 wrap-form op-domicilio">
              <div class="title-option">
                <p>Domicilio</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <input id="tipo_e10" name="tipo_e10" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad" id="tipo_e10n" name="tipo_e10n" type="text" >
                </div>
                <div class="input-field">
                  <input id="tipo_e10_cvevial" name="tipo_e10_cvevial" type="text" hidden readonly >
                </div>
                <div class="input-field">
                  <input placeholder="Nombre de Vialidad" id="e10" name="e10" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="Nombre E10 E" id="e10_e" name="e10_e" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="E11 Número Exterior" id="e11" name="e11" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="E11 A Letra" id="e11_a" name="e11_a" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="E11 Número Interior" id="e13" name="e13" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="E13 A Letra" id="e13_a" name="e13_a" type="text" >
                </div>
              </div>
            </div>
            <!-- OPCIÓN DOMICILIO -->

            <!-- OPCIÓN ASENTAMIENTO -->
            <div class="col s12 m6 l4 wrap-form op-referencia">
              <div class="title-option">
                <p>Asentamiento</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <select id="tipo_e14" name="tipo_e14">
                    <option value="" disabled selected>Tipo de Asentamiento Humano</option>
                    <option value="1">Option 1</option>
                  </select>
                </div>
                <div class="input-field">
                  <input placeholder="Código Postal" id="e14_a" name="e14_a" type="text" >
                </div>
                <div id="cp_val"></div>
                <div class="input-field">
                  <input placeholder="Nombre del Asentamiento" id="e14" name="e14" type="text" >
                </div>
              </div>
            </div>
            <!-- OPCIÓN ASENTAMIENTO -->

            <!-- OPCIÓN ENTRE VIALIDADES -->
            <div class="col s12 m6 l4 wrap-form op-entre-vialidades">
              <div class="title-option">
                <p>Entre Vialidades</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <input id="tipo_e10_a" name="tipo_e10_a" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad 1" id="tipo_e10_an" name="tipo_e10_an" type="text" >
                </div>
                <div class="input-field">
                  <input id="e10a_cvevial" name="e10a_cvevial" type="text" hidden >
                  <label for="e10a_cvevial" hidden="">**Hasta el punteo</label>
                </div>
                <div class="input-field">
                  <select id="tipo_e10_a" name="tipo_e10_a">
                    <option value="" disabled selected>Nombre de Vialidad 1</option>
                    <option value="1">Option 1</option>
                  </select>
                </div>
                <div class="input-field">
                  <input id="tipo_e10_b" name="tipo_e10_b" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad 2" id="tipo_e10_bn" name="tipo_e10_bn" type="text" >
                </div>
                <div class="input-field">
                  <input id="e10b_cvevial" name="e10b_cvevial" type="text" hidden >
                  <label for="e10b_cvevial" hidden="">**Hasta el punteo</label>
                </div>
                <div class="input-field">
                  <select id="tipo_e10_b" name="tipo_e10_b">
                    <option value="" disabled selected>Nombre de Vialidad 2</option>
                    <option value="1">Option 1</option>
                  </select>
                </div>
              </div>
            </div>
            <!-- OPCIÓN ENTRE VIALIDADES -->

            <!-- OPCIÓN CALLE POSTERIOR -->
            <div class="col s12 m6 l4 wrap-form op-calle-posterior">
              <div class="title-option">
                <p>Calle Posterior</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <input  id="tipo_e10_c" name="tipo_e10_c" type="text" hidden >
                </div>
                <div class="input-field">
                  <input placeholder="Tipo de Vialidad Posterior" id="tipo_e10_cn" name="tipo_e10_cn" type="text" >
                </div>
                <div class="input-field">
                  <input id="e10c_cvevial" name="e10c_cvevial" type="text" hidden >
                  <label for="e10c_cvevial" hidden="">**Hasta el punteo</label>
                </div>
                <div class="input-field">
                  <select id="e10_c" name="e10_c">
                    <option value="" disabled selected>Nombre de Vialidad</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                  </select>
                </div>
                <div class="input-field">
                  <textarea id="descrubic" name="descrubic" class="materialize-textarea"></textarea>
                  <label for="textarea1">Descripción de la ubicación del establecimiento</label>
                </div>
              </div>
            </div>
            <!-- OPCIÓN CALLE POSTERIOR -->

            <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->
            <div class="col s12 m6 l4 wrap-form op-edificio-centros-comerciales ">
              <div class="title-option">
                <p>Edificio, Centro Comercial</p>
              </div>
              <div class="inputs-option z-depth-3">
                <div class="input-field">
                  <input placeholder="E12 Edificio" id="e12" name="e12" type="text" >
                </div>
                <div class="input-field">
                  <select id="tipo_e19" name="tipo_e19">
                    <option value="" disabled selected>Tipo de Corredor o Centro Comercial</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                  </select>
                </div>
                <div class="input-field">
                  <input placeholder="E19 Nombre" id="e19" name="e19" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="E12P Piso" id="e12p" name="e12p" type="text" >
                </div>
                <div class="input-field">
                  <input placeholder="E20 No. Local" id="e20" name="e20" type="text" >
                </div>
              </div>
            </div>
            <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->
  

          </div>
        </form>
      </section>
      <!-- END CONTAINER FORM -->

    </div>

    <!-- <script src="resources/js/main.js" type="text/javascript"></script> -->
    <script src="resources/config/dataSource.js" type="text/javascript"></script>
    <script src="resources/config/config.js" type="text/javascript"></script>
    <script src="resources/config/tree.js" type="text/javascript"></script>
    <script src="resources/config/interface.js" type="text/javascript"></script>
    <!-- Compiled and minified JavaScript -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src='resources/js/actionsDom.js'></script>
    <script> 
      const loadScript = (url, callback) => {
        let script = document.createElement('script')
        script.type = 'text/javascript'
  
        //IE
        if (script.readyState) {
          script.onreadystatechange = () => {
            if (script.readyState == "loaded" || script.readyState == "complete") {
              script.onreadystatechange = null
              callback()
            }
          } 
        } else {
          //others
          script.onload =  () =>  callback() 
        }
        script.src = url
        document.getElementsByTagName("head")[0].appendChild(script)
      }   
    </script>

    <script>
        $materialize = jQuery.noConflict(true);
    </script>
      
    <script type="text/javascript">
      loadScript("resources/js/materialize.min.js", () => {
        //initialization code                
       // $(".button-collapse")
          // .sideNav({
          //   edge: 'left',
          //   draggable: true
          // });
          
          $('.tooltipped').tooltip({delay: 50});
          $('.dropdown-button').dropdown('open');
          $('select').material_select();
          //$('select').formSelect();
      })

    </script>
  
    <script src="http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6" type="text/javascript"></script>
  </body>
</html>
