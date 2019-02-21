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

      <section class="container-map">
        <div class="row row-map">
          <div class="col s12 l10 wrap-map">
            <div id="mapa"></div>
          </div>
        </div>
      </section>

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
