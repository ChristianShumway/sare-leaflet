<%@page import="java.io.FileInputStream" %>
  <%@page import="java.io.File" %>
    <%@page import="java.io.InputStreamReader" %>
      <%@page import="java.net.URL" %>
        <%@page import="java.io.FileReader" %>
          <%@page import="java.io.BufferedReader" %>
            <%@page import="java.security.MessageDigest" %>

              <% String rutaMain=request.getServletContext().getRealPath("/WEB-INF/resource/js/main.js"); String
                rutaService=request.getServletContext().getRealPath("/WEB-INF/resource/js/services.js"); String
                rutaAppCss=request.getServletContext().getRealPath("/WEB-INF/resource/css/app.css"); String
                rutaActionDom=request.getServletContext().getRealPath("/WEB-INF/resource/js/actionsDom.js"); String
                rutaObjFormulario=request.getServletContext().getRealPath("/WEB-INF/resource/js/objFormulario.js");
                String archivo=getArchivo(rutaMain); String version_main=getMD5(archivo);
                archivo=getArchivo(rutaService); String version_services=getMD5(archivo);
                archivo=getArchivo(rutaAppCss); String version_appCss=getMD5(archivo);
                archivo=getArchivo(rutaActionDom); String version_actionDom=getMD5(archivo);
                archivo=getArchivo(rutaObjFormulario); String version_objFormulario=getMD5(archivo); %>

                <%! public String getArchivo(String rurta) { BufferedReader br=null; String everything="nada" ; try {
                  br=new BufferedReader(new FileReader(rurta)); StringBuilder sb2=new StringBuilder(); String
                  line2=br.readLine(); while (line2 !=null) { sb2.append(line2); sb2.append(System.lineSeparator());
                  line2=br.readLine(); } everything=sb2.toString(); } catch (Exception e) { e.printStackTrace(); }
                  finally { try { if (br !=null) { br.close(); } } catch (Exception e) { } } return everything; } %>

                  <%! public String getMD5(String rurta) throws Exception { String regreso="nada" ; String
                    plainText=rurta; MessageDigest mdAlgorithm=MessageDigest.getInstance("MD5");
                    mdAlgorithm.update(plainText.getBytes()); byte[] digest=mdAlgorithm.digest(); StringBuffer
                    hexString=new StringBuffer(); for (int i=0; i < digest.length; i++) {
                    plainText=Integer.toHexString(0xFF & digest[i]); if (plainText.length() < 2) { plainText="0" +
                    plainText; } hexString.append(plainText); } return hexString.toString(); } %>

                    <%@page contentType="text/html" pageEncoding="UTF-8" %>
                      <html>

                      <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link href="resources/css/app.css?version=<%=version_appCss%>" rel="stylesheet"
                          type="text/css" />
                        <!--<link href="resources/css/panel-search.css" rel="stylesheet" type="text/css"/>-->
                        <link href="resources/css/animate.css" rel="stylesheet" type="text/css" />
                        <link href="resources/css/search-api.css" rel="stylesheet" type="text/css" />
                        <title>SARE 2019</title>
                        <!-- Compiled and minified CSS -->
                        <script src='resources/js/services.js?version=<%=version_services%>'></script>
                        <link rel="stylesheet" href="resources/css/materialize.css">
                        <link href="resources/css/material-icons.css" rel="stylesheet">
                        <link
                          href="https://fonts.googleapis.com/css?family=Lato:300,400,700|Montserrat:200,300,400,500,700,800,900|Roboto:300,400,500,700"
                          rel="stylesheet">
                        <link rel="stylesheet" href="resources/css/sweetalert2.min.css">


                        <!-- <script src="resources/js/jquery-2.1.1.min.js"  ></script> -->
                        <script src="resources/js/sweetalert2.min.js"></script>
                        <script src="resources/js/sendAjax.js" type="text/javascript"></script>
                        <script src="resources/config/dataSource.js" type="text/javascript"></script>
                        <script src="resources/config/map.js" type="text/javascript"></script>
                        <!-- cargamos archivos de configuración del mapa-->
                        <script src="resources/config/dataSource.js" type="text/javascript"></script>
                        <script src="resources/config/map.js" type="text/javascript"></script>
                        <script src="resources/config/tree.js" type="text/javascript"></script>
                        <script src="resources/config/interface.js" type="text/javascript"></script>



                        <script src="resources/js/validacionesBack.js" type="text/javascript"></script>
                        <script src="resources/js/objFormularioBack.js" type="text/javascript"></script>

                        <script src="resources/js/main.js?version=<%=version_main%>" type="text/javascript"></script>
                        <!-- Compiled and minified JavaScript -->
                        <!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.js"></script> -->
                        <script src='resources/js/jquery-2.1.1.min.js'></script>

                        <script src='resources/js/actionsDom.js?version=<%=version_actionDom%>'></script>
                        <script src="resources/config/config.js" type="text/javascript"></script>
                        <script src='resources/js/objFormulario.js?version=<%=version_objFormulario%>'></script>
                        <!-- FONT AWSOME -->
                        <link href="resources/css/fontawesome.css" rel="stylesheet" type="text/css" />

                        <!-- DATATABLE -->
                        <link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.css">
                        <script type="text/javascript" language="javascript"
                          src="resources/js/jquery.dataTables.js"></script>
                        <script type="text/javascript" language="javascript" src="resources/js/fnAddTr.js"></script>
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/bowser/1.9.4/bowser.min.js"></script>
                        <script>
                          var navegador;
                          var versionnavegador = navigator.userAgent;
                          var getBrowserInfo = function () {
                            var ua = navigator.userAgent, tem,
                              M = ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
                            if (/trident/i.test(M[1])) {
                              tem = /\brv[ :]+(\d+)/g.exec(ua) || [];
                              return 'IE ' + (tem[1] || '');
                            }
                            if (M[1] === 'Chrome') {
                              tem = ua.match(/\b(OPR|Edge)\/(\d+)/);
                              if (tem != null)
                                return tem.slice(1).join(' ').replace('OPR', 'Opera');
                            }
                            M = M[2] ? [M[1], M[2]] : [navigator.appName, navigator.appVersion, '-?'];
                            if ((tem = ua.match(/version\/(\d+)/i)) != null)
                              M.splice(1, 1, tem[1]);
                            return M.join(' ');
                          };

                          console.log(getBrowserInfo());
                          $(document).ready(function () {
                            // disable()

                            var hovered_over = false;
                            document.onmousewheel = function () {
                              $("#mapa").on("wheel", event => {
                                if (event.originalEvent.ctrlKey) {
                                  return;
                                }

                                if (event.originalEvent.metaKey) {
                                  return;
                                }

                                if (event.originalEvent.altKey) {
                                  return;
                                }

                                event.preventDefault();
                                window.addEventListener("touchstart", { passive: false });

                              });
                            } /* IE7, IE8 */
                            if (document.addEventListener) {
                              /* Chrome, Safari, Firefox */
                              navegador = getBrowserInfo();
                              $("#mapa").on("wheel", event => {

                                if (event.originalEvent.ctrlKey) {
                                  event.returnValue = false;
                                }

                                if (event.originalEvent.metaKey) {
                                  event.returnValue = false;
                                }

                                if (event.originalEvent.altKey) {
                                  event.returnValue = false;
                                }
                                if (navegador.includes('Chrome')) {
                                  event.preventDefault();
                                  window.addEventListener("touchstart", { passive: false });



                                }
                              });
                              $(window).mousemove(function () {
                                // MDM6('updateSize');
                              });



                              //document.addEventListener('DOMMouseScroll', stopWheel, false);
                            }

                          });

                        </script>
                        <script type="text/javascript">


                        </script>

                      </head>

                      <body>
                        <div class="sare">
                          <!-- HEADER -->
                          <header class="container-header">
                            <div class="navbar-fixed">
                              <nav>
                                <div class="nav-wrapper">
                                  <a href="#" class="brand-logo">
                                    <img src="resources/images/logos/logo-sare-azul.png">
                                  </a>
                                  <a href="#" data-activates="slide-out" class="button-collapse hide-on-large-only">
                                    <i class="material-icons tooltipped" data-position="right">menu</i>
                                  </a>
                                  <ul id="nav-mobile" class="right hide-on-med-and-down">
                                    <li>
                                      <a id="ancla-map" onclick='handleModuleScroll(event)'
                                        href="#container-map">Mapa</a>
                                    </li>
                                    <li>
                                      <a id="ancla-form" onclick='handleModuleScroll(event)'
                                        href="#container-form">Formulario</a>
                                    </li>
                                    <li>
                                      <a onclick='handleFullScreen()' data-toggle="tooltip" id="btn-fullscreen"
                                        title="Expandir"></a>
                                    </li>
                                  </ul>
                                </div>
                                <div class="container-texto-error" id="error-navegador" style="display: none">
                                  <h2>NAVEGADOR NO SOPORTADO</h2>
                                  <h2>Puedes descargar Chrome
                                    <a class="sublineado" href="https://www.google.com/chrome/" target="blank">Aquí</a>
                                  </h2>
                                </div>
                              </nav>
                              <!-- MENU MOVIL -->
                              <ul id="slide-out" class="sidenav">
                                <li>
                                  <div class="wrap-container-logo-sare-movil">
                                    <img src="resources/images/logos/logo-sare-bw.png" alt="sare">
                                  </div>
                                </li>
                                <li>
                                  <div class="user-view">
                                    <div class="background">
                                      <img src="resources/images/fondos/censos.jpg">
                                    </div>
                                    <a href="#user" class="img-user">
                                      <img class="circle" src="resources/images/iconos/boy.png">
                                    </a>
                                    <!-- <a href="#name"><span class="white-text name">Bievenido(a) al:</span></a>
<a href="#email"><span class="white-text email">SARE</span></a> -->
                                  </div>
                                </li>
>>>>>>> 1cf14880befe98f6a5e185c1fd24d7d7e1338f92

                                <li>
                                  <a class="waves-effect" onclick='handleModuleScroll(event)' id="ancla-map-movil"
                                    href="#container-map">
                                    <i class="material-icons">map</i>Mapa</a>
                                </li>
                                <li>
                                  <a class="waves-effect" onclick='handleModuleScroll(event)' id="ancla-form-movil"
                                    href="#container-form">
                                    <i class="material-icons">storage</i>Formulario</a>
                                </li>
                                <li>
                                  <div class="divider"></div>
                                </li>
                                <li>
                                  <a class="subheader">Opciones</a>
                                </li>
                                <li>
                                  <a disabled id='save-movil-option' class="option-disabled">
                                    <i class="material-icons">save</i>Guardar</a>
                                </li>
                                <li>
                                  <a disabled id="cancel-movil-option" class="option-disabled">
                                    <i class="material-icons">highlight_off</i>Cancelar</a>
                                </li>
                                <li onclick="opcionMenu(3)">
                                  <a href="#!">
                                    <i class="material-icons">content_paste</i>Reportes</a>
                                </li>
                                <li onclick="imprimir()">
                                  <a href="#!">
                                    <i class="material-icons">local_printshop</i>Imprimir</a>
                                </li>
                                <li onclick="">
                                  <a href="#!">
                                    <i class="material-icons">local_printshop</i>Claves bloquedas</a>
                                </li>
                                <li onclick="abrirAyuda">
                                  <a href="#!">
                                    <i class="material-icons">live_help</i>Claves bloquedas</a>
                                </li>
                                <!--            <li><a onclick="handleLogOut()"><i class="material-icons">exit_to_app</i>Cerrar Sesión</a></li>-->
                              </ul>
                              <!-- END MENU MOVIL -->
                            </div>
                          </header>
                          <!--END HEADER  -->

                          <!-- CONTAINER SEARCH -->
                          <section id='container-search' class='container-search initial animated slideInDown slow'
                            data-visible='hide'>
                            <h5 onclick="handleVisibleSearch()" id="titulo-busqueda">
                              <i class="material-icons">search</i>BUSQUEDA DE CLAVE
                            </h5>
                            <a id='arrow-search' class='hide-search' onclick="handleVisibleSearch()" tabindex="2">
                              <i class="material-icons tooltipped" data-position="left"
                                data-tooltip="Ver / Ocultar Busqueda">keyboard_arrow_down</i>
                            </a>
                            <div class="row row-search" id="row-search">
                              <div class="col s12 m8 offset-m2 l6 offset-l3 wrap-search">
                                <div class="input-field">
                                  <input placeholder="Realizar Busqueda de la Clave..." id="clave-busqueda"
                                    name="clave-busqueda" type="text" class="validate"
                                    onkeypress="handleSearchCleeValidation(event)">
                                </div>
                                <div class="btns-search">
                                  <a onclick="buscarUE()" class="btn-search search-four tooltipped"
                                    data-position="bottom" data-tooltip="Busqueda por clave">Buscar</a>
                                  <a onclick="handleViewCleeList()" class="btn-search search-five tooltipped"
                                    data-position="bottom" data-tooltip="Ver lista de claves">Ver</a>
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
                                    <div class="icon-si-ratifica">
                                      <i class="material-icons">check</i>
                                    </div>
                                    <div class="text-si-ratifica">Ratificar</div>
                                  </div>
                                  <div onclick="ratificar('no')" class="btn-ratifica no-ratifica" id="wrap-no-ratifica">
                                    <div class="text-no-ratifica">No Ratificar</div>
                                    <div class="icon-no-ratifica">
                                      <i class="material-icons">close</i>
                                    </div>
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
                                <!--<div id="mapa"></div>-->
                                
                                <div id="mapid">
                                  <div class="container-search-api" id="container-search-gral">
                                    <div class="wrapper-input-search">
                                      <input id="busqueda-api" type="text" placeholder="Buscar...">
                                      <a onclick="searchApi()">
                                        <img src="resources/images/search-api/search.png" srcset="resources/images/search-api/search@2x.png" alt="busqueda" />
                                      </a>
                                    </div>
                                    <div class="wrapper-button-hide-panel" onclick="hidePanel()">
                                      <img src="resources/images/search-api/navigate-before.png" srcset="resources/images/search-api/navigate-before@2x.png" alt="ocultar panel" />
                                    </div>
                                    <div class="wrapper-expand-panel">
                                      <a onclick="expandPanelSearch()">
                                        <img src="resources/images/search-api/navigate-next.png" srcset="resources/images/search-api/navigate-next@2x.png" alt="ver panel completo" />
                                        Ver más opciones
                                      </a>
                                    </div>
                                    <div class="wrapper-button-contract-panel">
                                      <a class="button-contract" onclick="contractrPanelSearch()">
                                        <img src="resources/images/search-api/navigate-next-blue.png" srcset="resources/images/search-api/navigate-next-blue@2x.png" alt="ocultar panel" />
                                        Ocultar Todo
                                      </a>
                                    </div>
                                    <div class="wrapper-searching" id="wrapper-searching">
                                      <h1>BUSCANDO</h1>
                                    </div>
                                    <div class="wrapper-tools" id="wrapper-tools">
                                      <div class="item resultado">
                                        <div class="icon">
                                          <img src="resources/images/search-api/map-search.png" srcset="resources/images/search-api/map-search@2x.png" alt="resultado" />
                                        </div>
                                        <span>Resultado</span>
                                      </div>
                                      <div class="item medir">
                                        <div class="icon">
                                          <img src="resources/images/search-api/ruler.png" srcset="resources/images/search-api/ruler@2x.png" alt="medir" />
                                        </div>
                                        <span>Medir</span>
                                      </div>
                                      <div class="item leyenda">
                                        <div class="icon">
                                          <img src="resources/images/search-api/tag.png" srcset="resources/images/search-api/tag@2x.png" alt="leyenda" />
                                        </div>
                                        <span>Leyenda</span>
                                      </div>
                                    </div>
                                    <!-- OTROS PANELES -->
                                    <div class="container-scrolleable" id="show-results">
                                      <!-- card otros resultados -->
                                      <div class="wrapper-other-results">
                                        <div class="top">
                                          <span>Otros Resultados</span>
                                          <img onclick="collapsableCardOtherResults()" src="resources/images/search-api/row-collapsable.png" srcset="resources/images/search-api/row-collapsable@2x.png" alt="ocultar otros resultados" />
                                        </div>
                                        <div class="results">
                                          <div class="item">
                                            <p>1</p>
                                            <span>Entidad Federativa</span>
                                          </div>
                                          <div class="item">
                                            <p>1</p>
                                            <span>Municipio</span>
                                          </div>
                                          <div class="item">
                                            <p>638</p>
                                            <span>Calle</span>
                                          </div>
                                          <div class="item">
                                            <p>37</p>
                                            <span>Localidad Rural</span>
                                          </div>
                                          <div class="item">
                                            <p>3</p>
                                            <span>Rasgos Hidrográficos</span>
                                          </div>
                                          <div class="item">
                                            <p>2</p>
                                            <span>Localidad Urbana</span>
                                          </div>
                                        </div>
                                      </div>
                                      <!-- end card otros resultados -->
                                      <!-- card resultado ubicaciones -->
                                      <div class="wrapper-locations-result">
                                        <div class="item">
                                          <div class="img" onclick="showLocation()">
                                            <img src="resources/images/search-api/icon-street.png" srcset="resources/images/search-api/icon-street@2x.png" alt="icono calle" />
                                          </div>
                                          <div class="info">
                                            <p onclick="showLocation()">Aguascalientes</p>
                                            <span>Sonora</span>
                                            <span>Caborca</span>
                                            <span>Heroica Caborca</span>
                                          </div>
                                        </div>
                                        <div class="item">
                                          <div class="img">
                                            <img src="resources/images/search-api/icon-street.png" srcset="resources/images/search-api/icon-street@2x.png" alt="icono calle" />
                                          </div>
                                          <div class="info">
                                            <p>Aguascalientes</p>
                                            <span>Sonora</span>
                                            <span>Caborca</span>
                                            <span>Heroica Caborca</span>
                                          </div>
                                        </div>
                                        <div class="item">
                                          <div class="img">
                                            <img src="resources/images/search-api/icon-street.png" srcset="resources/images/search-api/icon-street@2x.png" alt="icono calle" />
                                          </div>
                                          <div class="info">
                                            <p>Aguascalientes</p>
                                            <span>Sonora</span>
                                            <span>Caborca</span>
                                            <span>Heroica Caborca</span>
                                          </div>
                                        </div>
                                      </div>
                                      <!-- end card resultado ubicaciones -->
                                      <!-- card que hay aquí -->
                                      <!-- end card que hay aquí -->
                                      <div class="wrapper-whats-here">
                                        <div class="top">
                                          <a>¿Qué hay aquí?</a>
                                          <img onclick="collapsableCardOtherResults()" src="resources/images/search-api/row-collapsable.png" srcset="resources/images/search-api/row-collapsable@2x.png" alt="ocultar otros resultados" />
                                        </div>
                                        <div class="results">
                                          <div class="item">
                                            <p>1</p>
                                            <span>Entidad Federativa</span>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <!-- END MAPA -->
                                <div class="wrap-alert alert-pick" id="wrap-alert-pick">
                                  <div class="icon-warning">
                                    <img src="resources/images/iconos/warning.png" alt="warning">
                                  </div>
                                  <div class="text-warning">Realice el punteo por favor</div>
                                </div>
                              </div>
                              <!-- end mapa -->
                              <!-- opciones -->
                              <div class="col s12 item-options animated ">
                                <i class="material-icons" id="item-options" data-visible="show"
                                  onclick="handleOptions()">keyboard_arrow_down</i>
                              </div>
                              <div class="col s12 l2 wrap-options animated " id="wrap-options">
                                <div class="items-check">

                                  <!--              <div class="check-option">
    <p class="option-title option-active" id="option-c101">Sucursales</p>
    <div class="switch">
        <label>
        Inactivo
        <input type="checkbox" checked="true" id="c101" onchange="handleChangeOptions('c101')">
        <span class="lever"></span>
        Activo
        </label>
    </div>
    </div>-->

                                  <div class="check-option">
                                    <p class="option-title option-active" id="option-wdenue">DENUE</p>
                                    <div class="switch">
                                      <label>
                                        Inactivo
                                        <input type="checkbox" checked="true" id="wdenue"
                                          onchange="handleChangeOptions('wdenue')">
                                        <span class="lever"></span>
                                        Activo
                                      </label>
                                    </div>
                                  </div>

                                  <!--              <div class="check-option">
    <p class="option-title" id="option-C101M">Matrices</p>
    <div class="switch">
        <label>
        Inactivo
        <input type="checkbox" id="C101M" onchange="handleChangeOptions('C101M')">
        <span class="lever"></span>
        Activo
        </label>
    </div>
    </div>-->

                                  <div class="check-option">
                                    <p class="option-title" id="option-c104">Postes de Kilometraje</p>
                                    <div class="switch">
                                      <label>
                                        Inactivo
                                        <input type="checkbox" id="c104" onchange="handleChangeOptions('c104')">
                                        <span class="lever"></span>
                                        Activo
                                      </label>
                                    </div>

                                  </div>

                                  <span class="options-line"></span>

                                  <div class="items-radio">

                                    <!--<div class="radio-option">
<input id="identificar" value="identificar" name="accion" type="radio" onchange="radioSelect('identificar')"/>
<label for="identificar">Identificar</label>
</div>-->

                                    <div class="radio-option">
                                      <input id="puntear" checked="true" value="puntear" name="accion" type="radio"
                                        onchange="radioSelect('puntear')" />
                                      <label for="puntear">Puntear</label>
                                    </div>

                                    <div class="radio-option">
                                      <input id="v-calle" value="v_calle" name="accion" type="radio"
                                        onchange="radioSelect('calle')" />
                                      <label for="v-calle">Vista Calle</label>
                                    </div>

                                    <div class="radio-option">
                                      <input id="puntear-alta" value="puntear_alta" name="accion" type="radio"
                                        onchange="radioSelect('alta')" disabled="true" />
                                      <label for="puntear-alta"></label>
                                    </div>

                                    <!--  <div class="radio-option">
<input id="puntear-alta" value="puntear_alta" name="accion" type="radio" onchange="radioSelect('alta')"/>
<label for="puntear-alta">Puntear Alta</label>-->
                                  </div>

                                </div>
                              </div>
                              <!-- end opciones -->
                            </div>
                          </section>
                          <!-- END CONTAINER MAP -->

                          <!-- CONTAINER FORM -->
                          <section class="container-form" id="container-form">
                            <form id="frmSARE">
                              <div class="row row-form">

                                <!-- OPCIÓN REFERENCIA -->
                                <div class="col s12 m6 l4 wrap-form op-referencia animated slideInLeft"
                                  id="op-preferencia">
                                  <div class="title-option" data-visible="show"
                                    onclick="handleVisibleForm('referencia')" id="title-referencia">
                                    <i class="material-icons" id="icon-referencia">add</i>
                                    <div class="titlew">
                                      <p>Referencia</p>
                                    </div>
                                    <div id="wrap-btns-referencia" class="wrap-btn-floating-static">
                                      <a id="icon-referencia-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-preferencia', 'referencia', 'float', 'title-referencia')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-referencia-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-preferencia', 'referencia', 'static', 'title-referencia')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-referencia">
                                    <div class="input-field">
                                      <input placeholder="ID_UE" id="id_UE" name="id_UE" type="text" disabled>
                                      <label id="label_idUE" for="id_UE">ID_UE</label>
                                    </div>
                                    <div class="input-field">
                                      <select class="browser-default" id="catorigen" name="catorigen"
                                        style="display: none">
                                      </select>
                                      <input placeholder="Origen" id="origen" name="origen" type="text" disabled>
                                      <label for="origen">Origen</label>
                                    </div>
                                    <div class="input-field">
                                      <select class="browser-default" id="catc154" name="catc154" style="display: none">
                                      </select>
                                      <input placeholder="C154" id="c154" name="c154" type="text" disabled>
                                      <label for="c154">C154</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Nombre del Establecimiento" style="text-transform:uppercase;"
                                        id="e08" name="e08" type="text" disabled>
                                      <label for="e08">Nombre del Establecimiento</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Razón Social" style="text-transform:uppercase;" id="e09"
                                        name="e09" type="text" disabled>
                                      <label for="e09">Razón Social</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="SCIAN" id="e17_DESC" name="e17_DESC" type="text" disabled>
                                      <label for="e17_DESC">SCIAN</label>
                                      <a class="btn-floating colorGrisApp" id="filtroXclase"
                                        onclick="FiltroXClase($(this).attr('id'))">
                                        <i class="material-icons">filter_list</i>
                                      </a>
                                    </div>
                                    <div class="input-field">
                                      <a onclick="handleActionTarget('ubicacion-geografica', 'ubicacion-float-der')"
                                        class="next-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>
                                </div>
                                <!-- OPCIÓN REFERENCIA -->

                                <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->
                                <div class="col s12 m6 l4 wrap-form op-ubicacion-geografica animated slideInUp"
                                  id="op-ugeografica">
                                  <div class="title-option" data-visible="show"
                                    onclick="handleVisibleForm('ubicacion-geografica')" id="title-ubicacion-geografica">
                                    <i class="material-icons" id="icon-ubicacion-geografica">add</i>
                                    <div class="titlew">
                                      <p>Ubicación Geográfica</p>
                                    </div>
                                    <div id="wrap-btns-ugeografica" class="wrap-btn-floating-static">
                                      <a id="icon-ugeografica-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-ugeografica', 'ugeografica', 'float', 'title-ubicacion-geografica')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-ugeografica-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-ugeografica', 'ugeografica', 'static', 'title-ubicacion-geografica')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-ubicacion-geografica">
                                    <div class="input-field">
                                      <input placeholder="Clave" id="e03" name="e03" type="text" disabled>
                                      <label for="e03">Entidad Federativa (Clave)</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Nombre" id="e03N" name="e03N" type="text" disabled>
                                      <label for="e03N">Entidad Federativa (Nombre)</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Clave" id="e04" name="e04" type="text" disabled>
                                      <label for="e04">Municipio o Delegación (Clave)</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Nombre" id="e04N" name="e04N" type="text" disabled>
                                      <label for="e04N">Municipio o Delegación (Nombre)</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Clave" id="e05" name="e05" type="text" disabled>
                                      <label for="e05N">Localidad (Clave)</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Nombre" id="e05N" name="e05N" type="text" disabled>
                                      <label for="e05">Localidad (Nombre)</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="AGEB" id="e06" name="e06" type="text" disabled>
                                      <label for="e06">AGEB</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Manzana" id="e07" name="e07" type="text" disabled>
                                      <label for="e07">Manzana</label>
                                    </div>
                                    <div class="input-field btns-prev-next">
                                      <a onclick="handleActionTarget('referencia', 'referencia-float-izq')"
                                        class="previous-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                      <a onclick="handleActionTarget('domicilio', 'domicilio-float-der')"
                                        class="next-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>
                                </div>
                                <!-- OPCIÓN UBICACIÓN GEOGRÁFICA -->

                                <!-- OPCIÓN DOMICILIO -->
                                <div class="col s12 m6 l4 wrap-form op-domicilio animated slideInRight"
                                  id="op-domicilio">
                                  <div class="title-option" data-visible="show" onclick="handleVisibleForm('domicilio')"
                                    id="title-domicilio">
                                    <i class="material-icons" id="icon-domicilio">add</i>
                                    <div class="titlew">
                                      <p>Domicilio</p>
                                    </div>
                                    <div id="wrap-btns-domicilio" class="wrap-btn-floating-static">
                                      <a id="icon-domicilio-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-domicilio', 'domicilio', 'float', 'title-domicilio')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-domicilio-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-domicilio', 'domicilio', 'static', 'title-domicilio')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-domicilio">
                                    <div class="input-field">
                                      <input id="tipo_e10" name="tipo_e10" type="text" hidden>
                                    </div>
                                    <div class="input-field" id="wrap-tipo-vialidad">
                                      <input placeholder="Tipo de Vialidad" id="tipo_e10n" name="tipo_e10n" type="text"
                                        disabled>
                                      <label for="tipo_e10n">Tipo de Vialidad</label>
                                    </div>
                                    <div class="input-field">
                                      <input id="tipo_e10_cvevial" name="tipo_e10_cvevial" type="text" hidden readonly>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Nombre de Vialidad" style="text-transform:uppercase;"
                                        maxlength="79" id="e10" name="e10" type="text" disabled />
                                      <label for="e10">Nombre de la Vialidad</label>
                                    </div>
                                    <!--<div class="input-field">
<input placeholder="Nombre E10 E" id="e10_e" name="e10_e" type="text" disabled >
<label for="e10_e">Nombre E10 E</label>
</div>-->
                                    <div class="input-field">
                                      <input placeholder="E11 Número Exterior" id="e11" name="e11" type="text"
                                        onKeyPress="return soloNumeros(event)" disabled>
                                      <label for="e11">E11 Número Exterior</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="E11 A Letra" id="e11A" name="e11A" type="text" disabled>
                                      <label for="e11A">E11 A Letra</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="E13 Número Interior" id="e13" name="e13" type="text"
                                        onKeyPress="return soloNumeros(event)" disabled>
                                      <label for="e13">E13 Número Interior</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="E13 A Letra" id="e13_a" name="e13_a" type="text" disabled>
                                      <label for="e13_a">E13 A Letra</label>
                                    </div>
                                    <div class="input-field btns-prev-next">
                                      <a onclick="handleActionTarget('ubicacion-geografica', 'ubicacion-float-izq')"
                                        class="previous-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                      <a onclick="handleActionTarget('asentamiento', 'asentamiento-float-der')"
                                        class="next-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>
                                </div>
                                <!-- OPCIÓN DOMICILIO -->

                                <!-- OPCIÓN ASENTAMIENTO -->
                                <div class="col s12 m6 l4 wrap-form op-asentamiento animated slideInLeft"
                                  id="op-asentamiento">
                                  <div class="title-option" data-visible="show"
                                    onclick="handleVisibleForm('asentamiento')" id="title-asentamiento">
                                    <i class="material-icons" id="icon-asentamiento">add</i>
                                    <div class="titlew">
                                      <p>Asentamiento</p>
                                    </div>
                                    <div id="wrap-btns-asentamiento" class="wrap-btn-floating-static">
                                      <a id="icon-asentamiento-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-asentamiento', 'asentamiento', 'float', 'title-asentamiento')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-asentamiento-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-asentamiento', 'asentamiento', 'static', 'title-asentamiento')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-asentamiento">
                                    <div class="input-field">
                                      <select class="browser-default" id="tipo_E14" name="tipo_E14">
                                      </select>
                                      <label id="labeltipo_E14" for="tipo_E14" class="active">Tipo de Asentamiento
                                        Humano</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="Código Postal" id="e14_A" name="e14_A" type="text" disabled>
                                      <label for="e14_A">Código Postal</label>
                                    </div>
                                    <div id="cp_val"></div>
                                    <div class="input-field">
                                      <input placeholder="Nombre del Asentamiento" style="text-transform:uppercase;"
                                        id="e14" name="e14" type="text" disabled>
                                      <label for="e14">Nombre del Asentamiento</label>
                                    </div>
                                    <div class="input-field btns-prev-next">
                                      <a onclick="handleActionTarget('domicilio', 'domicilio-float-izq')"
                                        class="previous-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                      <a onclick="handleActionTarget('entre-vialidades', 'vialidades-float-der')"
                                        class="next-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>
                                </div>
                                <!-- OPCIÓN ASENTAMIENTO -->

                                <!-- OPCIÓN ENTRE VIALIDADES -->
                                <div class="col s12 m6 l4 wrap-form op-entre-vialidades animated slideInUp"
                                  id="op-evialidades">
                                  <div class="title-option" data-visible="show"
                                    onclick="handleVisibleForm('entre-vialidades')" id="title-entre-vialidades">
                                    <i class="material-icons" id="icon-entre-vialidades">add</i>
                                    <div class="titlew">
                                      <p>Entre Vialidades</p>
                                    </div>
                                    <div id="wrap-btns-evialidades" class="wrap-btn-floating-static">
                                      <a id="icon-evialidades-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-evialidades', 'evialidades', 'float', 'title-entre-vialidades')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-evialidades-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-evialidades', 'evialidades', 'static', 'title-entre-vialidades')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-entre-vialidades">
                                    <div class="input-field">
                                      <input id="tipo_e10_a" name="tipo_e10_a" type="text" hidden>
                                    </div>
                                    <div class="input-field" id="wrap-tipo-vialidad-uno">
                                      <input placeholder="Tipo de Vialidad 1" id="tipo_e10_an" name="tipo_e10_an"
                                        type="text" disabled>
                                      <label for="tipo_e10_an">Tipo de Vialidad No.1</label>
                                    </div>
                                    <div class="input-field">
                                      <input id="e10a_cvevial" name="e10a_cvevial" type="text" hidden>
                                      <label for="e10a_cvevial" hidden="">**Hasta el punteo</label>
                                    </div>
                                    <div class="input-field" id="wrap-nombre-vialidad-uno">
                                      <select class="browser-default" id="e10_A" name="e10_A">
                                      </select>
                                      <label id="labele10_A" for="e10_A" class="active">Nombre de la Vialidad
                                        No.1</label>
                                    </div>
                                    <div class="input-field">
                                      <input id="tipo_e10_b" style="text-transform:uppercase;" name="tipo_e10_b"
                                        type="text" hidden>
                                    </div>
                                    <div class="input-field" id="wrap-tipo-vialidad-dos">
                                      <input placeholder="Tipo de Vialidad 2" id="tipo_e10_bn" name="tipo_e10_bn"
                                        type="text" disabled>
                                      <label for="tipo_e10_bn">Tipo de Vialidad No.2</label>
                                    </div>
                                    <div class="input-field">
                                      <input id="e10b_cvevial" name="e10b_cvevial" type="text" hidden>
                                      <label for="e10b_cvevial" hidden="">**Hasta el punteo</label>
                                    </div>
                                    <div class="input-field" id="wrap-nombre-vialidad-dos">
                                      <select class="browser-default" id="e10_B" name="e10_B">
                                      </select>
                                      <label id="labele10_B" for="e10_B" class="active">Nombre de la Vialidad
                                        No.2</label>
                                    </div>
                                    <div class="input-field btns-prev-next">
                                      <a onclick="handleActionTarget('asentamiento', 'asentamiento-float-izq')"
                                        class="previous-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                      <a onclick="handleActionTarget('calle-posterior', 'calle-float-der')"
                                        class="next-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>
                                </div>
                                <!-- OPCIÓN ENTRE VIALIDADES -->

                                <!-- OPCIÓN CALLE POSTERIOR -->
                                <div class="col s12 m6 l4 wrap-form op-calle-posterior animated slideInRight"
                                  id="op-cposterior">
                                  <div class="title-option" data-visible="show"
                                    onclick="handleVisibleForm('calle-posterior')" id="title-calle-posterior">
                                    <i class="material-icons" id="icon-calle-posterior">add</i>
                                    <div class="titlew">
                                      <p>Calle Posterior</p>
                                    </div>
                                    <div id="wrap-btns-cposterior" class="wrap-btn-floating-static">
                                      <a id="icon-cposterior-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-cposterior', 'cposterior', 'float', 'title-calle-posterior')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-cposterior-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-cposterior', 'cposterior', 'static', 'title-calle-posterior')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-calle-posterior">
                                    <div class="input-field">
                                      <input id="tipo_e10_c" name="tipo_e10_c" type="text" hidden>
                                    </div>
                                    <div class="input-field" id="wrap-tipo-vialidad-posterior">
                                      <input placeholder="Tipo de Vialidad Posterior" id="tipo_e10_cn"
                                        name="tipo_e10_cn" type="text" disabled>
                                      <label for="tipo_e10_cn">Tipo de Vialidad Posterior</label>
                                    </div>
                                    <div class="input-field">
                                      <input id="e10c_cvevial" name="e10c_cvevial" type="text" hidden>
                                      <label for="e10c_cvevial" hidden="">**Hasta el punteo</label>
                                    </div>
                                    <div class="input-field" id="wrap-nombre-vialidad-posterior">
                                      <select class="browser-default" id="e10_C" name="e10_C">
                                      </select>
                                      <label id="labele10_C" for="e10_C" class="active">Nombre de la Vialidad</label>
                                    </div>
                                    <div class="input-field">
                                      <textarea id="descrubic" name="descrubic" class="materialize-textarea"
                                        disabled></textarea>
                                      <label for="textarea1">Descripción de la ubicación del establecimiento</label>
                                    </div>
                                    <div class="input-field btns-prev-next">
                                      <a onclick="handleActionTarget('entre-vialidades', 'vialidades-float-izq')"
                                        class="previous-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                      <a onclick="handleActionTarget('edificio', 'edificio-float-der')"
                                        class="next-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>

                                </div>
                                <!-- OPCIÓN CALLE POSTERIOR -->

                                <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->
                                <div
                                  class="col s12 m6 l4 wrap-form op-edificio-centros-comerciales animated slideInLeft"
                                  id="op-edificios">
                                  <div class="title-option" data-visible="show" onclick="handleVisibleForm('edificio')"
                                    id="title-edificio">
                                    <i class="material-icons" id="icon-edificio">add</i>
                                    <div class="titlew">
                                      <p>Edificio, Centro Comercial</p>
                                    </div>
                                    <div id="wrap-btns-edificios" class="wrap-btn-floating-static">
                                      <a id="icon-edificios-float" class="btn-in-out-container"
                                        onclick="handlePositionContainerForm('op-edificios', 'edificios', 'float', 'title-edificio')">
                                        <i class="material-icons tooltipped" data-position="top"
                                          data-tooltip="Flotar Contenedor">transit_enterexit</i>
                                      </a>
                                      <a id="icon-edificios-static" class="btn-in-out-container btn-inactive"
                                        onclick="handleReturnContainerForm('op-edificios', 'edificios', 'static', 'title-edificio')">
                                        <i class="material-icons tooltipped" data-position="right"
                                          data-tooltip="Regresar Contenedor">transit_enterexit</i>
                                      </a>
                                    </div>
                                  </div>
                                  <div class="inputs-option z-depth-3" id="inputs-edificio">
                                    <div class="input-field">
                                      <input placeholder="E12 Edificio" id="E12" name="E12" type="text" disabled>
                                      <label for="E12">E12 Edificio</label>
                                    </div>
                                    <div class="input-field">
                                      <select class="browser-default" id="tipo_E19" name="tipo_E19">
                                        <option value="" disabled selected>Seleccione...</option>
                                      </select>
                                      <label for="tipo_E19" class="active">Tipo de Corredor o Centro Comercial</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="E19 Nombre" id="e19" name="e19" type="text" disabled>
                                      <label for="e19">E19 Nombre</label>
                                    </div>
                                    <div class="input-field">
                                      <select class="browser-default" id="e12p" name="e12p">
                                      </select>
                                      <label for="e12p" class="active">E12P Piso</label>
                                    </div>
                                    <div class="input-field">
                                      <input placeholder="E20 No. Local" id="e20" name="e20" type="text" disabled>
                                      <label for="e20">E20 No. Local</label>
                                    </div>
                                    <div class="input-field btns-prev-next">
                                      <a onclick="handleActionTarget('calle-posterior', 'calle-float-izq')"
                                        class="previous-wrap">
                                        <img src="resources/images/iconos/right-arrow.png" alt="next">
                                      </a>
                                    </div>
                                  </div>
                                </div>
                                <!-- OPCIÓN EDIFICIO, CENTROS COMERCIALES -->


                              </div>
                            </form>
                          </section>
                          <!-- END CONTAINER FORM -->

                          <!-- CONTAINER OPTIONS BUTTONS -->
                          <div class="fixed-action-btn click-to-toggle btn-float-map ">
                            <a class="btn-floating btn-large btn-options-menu tooltipped animated bounceInUp slower delay-1s"
                              data-position="bottom" data-tooltip="Menu">
                              <i class="large material-icons">menu</i>
                            </a>
                            <ul>
                              <!--          <li><a id='item-save-option' disabled onclick="handleFormValidations()" class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Guardar"><i class="material-icons">save</i></a></li>-->
                              <li>
                                <a id='item-save-option' disabled onclick="validaCp(event)"
                                  class="btn-floating btn-item-menu tooltipped" data-position="left"
                                  data-tooltip="Guardar">
                                  <i class="material-icons">save</i>
                                </a>
                              </li>
                              <li>
                                <a id='item-cancel-option' disabled onclick="handleCancelClick()"
                                  class="btn-floating btn-item-menu tooltipped" data-position="left"
                                  data-tooltip="Cancelar">
                                  <i class="material-icons">highlight_off</i>
                                </a>
                              </li>
                              <li onclick="opcionMenu(2);">
                                <a id='item-report-option' class="btn-floating btn-item-menu tooltipped"
                                  data-position="left" data-tooltip="Reportes">
                                  <i class="material-icons">content_paste</i>
                                </a>
                              </li>
                              <li onclick="opcionMenu(4);">
                                <a class="btn-floating btn-item-menu tooltipped" data-position="left"
                                  data-tooltip="Imprimir">
                                  <i class="material-icons">local_printshop</i>
                                </a>
                              </li>
                              <li onclick="CargaTablaBloqueadas()">
                                <a class="btn-floating btn-item-menu tooltipped" data-position="left"
                                  data-tooltip="Claves Bloqueadas">
                                  <i class="material-icons">lock</i>
                                </a>
                              </li>
                              <li onclick="abrirAyuda()">
                                <a href="#!">
                                  <a class="btn-floating btn-item-menu tooltipped" data-position="left"
                                    data-tooltip="Ayuda">
                                    <i class="material-icons">help_outline</i>
                                  </a>
                                  <!--          <li><a onclick="handleLogOut()" class="btn-floating btn-item-menu tooltipped" data-position="left" data-tooltip="Salir"><i class="material-icons">exit_to_app</i></a></li>-->
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
                            <i class="material-icons tooltipped" data-position="left" data-tooltip="Ir a inicio">
                              arrow_upward
                            </i>
                          </button>

                        </div>
                      </body>

                      <script>
                        if (bowser.name == 'Internet Explorer' || bowser.name == 'Microsoft Edge') {
                          const contenedorAlertaNavegador = document.getElementById('error-navegador')
                          contenedorAlertaNavegador.style.display = "block"
                        }
                      </script>

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
                          } else {
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
                              draggable: true,
                              closeOnClick: true,
                            }
                          );

                          $('.tooltipped').tooltip({ delay: 20 });
                          $('.dropdown-button').dropdown();
                          $('select').material_select();
                        })
                      </script>

                      <script>
                        $(document).ready(function () {
                          $(document).keydown(function (event) {
                            if (event.ctrlKey == true && (event.which == '61' || event.which == '107' || event.which == '173' || event.which == '109' || event.which == '187' || event.which == '189')) {
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


                      <!-- <script src="https://gaia.inegi.org.mx/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6&d=gaia.inegi.org.mx" type="text/javascript"></script> -->
                      <!--  <script src="http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6" type="text/javascript"></script>-->

                      <!--<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
    //     crossorigin="" />

    // <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
    //     crossorigin="">
    // </script>
    -->
                      <link rel="stylesheet" href="resources/leaflet/leaflet.css">
                      <script src="resources/leaflet/leaflet.js"></script>
                      <script src="resources/js/pluginsLeaf/L.singleTile.js"></script>
                      <script src="resources/js/pluginsLeaf/Bing.js"></script>
                      <script src="resources/leaflet/proj4-src.js"></script>
                      <script src="resources/leaflet/proj4leaflet.js"></script>
                      <script src="resources/js/withLeaflet.js"></script>
                      <script src="resources/js/functionsApi.js"></script>

                      </html>