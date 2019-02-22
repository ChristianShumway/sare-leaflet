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
            <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->

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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Compiled and minified JavaScript -->
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
      
    <script type="text/javascript">
      loadScript("resources/js/materialize.min.js", () => {
        //initialization code                
        $(".button-collapse")
          .sideNav({
            edge: 'left',
            draggable: true
          })
          
          $('.tooltipped').tooltip({delay: 50})
          $('.dropdown-button').dropdown('open')
          $('select').material_select()
      })

    </script>
  
    <script src="http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6" type="text/javascript"></script>
  </body>
</html>
