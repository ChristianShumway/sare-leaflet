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
        <script src="resources/js/main.js" type="text/javascript"></script>
        <link href="resources/css/app.css" rel="stylesheet" type="text/css"/>
        <script src="resources/config/dataSource.js" type="text/javascript"></script>
        <script src="resources/config/config.js" type="text/javascript"></script>
        <script src="resources/config/tree.js" type="text/javascript"></script>
        <script src="resources/config/interface.js" type="text/javascript"></script>



        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div id="mapa">
        </div>
    </body>

    <script>
        function loadScript(url, callback) {

            var script = document.createElement("script")
            script.type = "text/javascript";

            if (script.readyState) {  //IE
                script.onreadystatechange = function () {
                    if (script.readyState == "loaded" ||
                            script.readyState == "complete") {
                        script.onreadystatechange = null;
                        callback();
                    }
                };
            } else {  //Others
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
            //initialization code                
            $(".button-collapse").sideNav(
                    {edge: 'left',
                        draggable: true
                    }
            );
            $('.tooltipped').tooltip({delay: 50});
            $('.dropdown-button').dropdown('open');
            $('select').material_select();
        });

    </script>
    <script src="http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6" type="text/javascript"></script>
</html>
