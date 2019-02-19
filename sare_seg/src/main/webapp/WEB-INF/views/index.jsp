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
    <link href="resources/css/app.css" rel="stylesheet" type="text/css"/>
    <title>SARE 2019</title>
     <!-- Compiled and minified CSS -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
     <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  </head>

  <body>
    <div class="container-sare">
      <header>
         
      </header>
      <h1>SARE</h1>
      <div id="mapa"></div>
    </div>

    <script src="resources/js/main.js" type="text/javascript"></script>
    <script src="resources/config/dataSource.js" type="text/javascript"></script>
    <script src="resources/config/config.js" type="text/javascript"></script>
    <script src="resources/config/tree.js" type="text/javascript"></script>
    <script src="resources/config/interface.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
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
