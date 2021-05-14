<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
  <!-- <meta charset="UTF-8"> -->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700|Montserrat:200,300,400,500,700,800,900|Roboto:300,400,500,700" rel="stylesheet">
  <link rel="stylesheet" href="resources/css/login.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@7.33.1/dist/sweetalert2.min.css">
  <link rel="stylesheet" href="resources/css/animate.css">
  <title>LOGIN - SARE </title>
</head>
<body>
  
  <div class="login-container">
    <div id="flip-container">
      <div id="card" class="card">
    
        <div class="wrap-login-front">
          <!--<a class="go-back" onclick="goLoginBack()" title="Regresar"><img src="resources/images/iconos/left-arrow.png" alt="atrás"></a>-->
          <p class="titulo-inicio-sesion">Inicio de Sesión</p>
          <p class="txt-indicaciones">Ingresa tu información de registro</p>
          <div class="wrap-input user" id="wrap-input-user">
            <span class="txt-user">Usuario</span>
            <input class="input-form" type="text" name="usuario" id="usuario" placeholder="Usuario" onkeypress="handlePressEnter(event)" />
          </div>
          <div class="wrap-input password" id="wrap-input-password">
            <span class="txt-password">Password</span>
            <input class="input-form" type="password" name="password" id="password" placeholder="Password" onkeypress="handlePressEnter(event)" />
          </div>
          <div class="lds-css ng-scope" id="wrap-loading">
            <div class="lds-ellipsis">
              <div><div></div></div>
              <div><div></div></div>
              <div><div></div></div>
              <div><div></div></div>
              <div><div></div></div>
            </div>
          </div>
          <a onclick="handleClickLogin()" class="btn-inicio-sesion start">Iniciar Sesión</a>
          <div class="logo-bienvenida-back">
            <img src="resources/images/logos/logo-inegi.png" alt="inegi">
          </div>
        </div>
        
        <div class="wrap-login-back">
          <div class="logo-bienvenida">
            <img src="resources/images/logos/logo-inegi.png" alt="inegi">
          </div>
          <p class="titulo-bienvenida">Bienvenido al</p>
          <p class="txt-bienvenida">Sistema de Alta y Reubicación de Establecimientos</p>
          <div class="wrap-select-sare custom-select" id="select-sare">
            <select name="cual-sare" id="cual-sare">
              <option value="">Seleccione SARE...</option>
              <!--<option value="4">Construcción</option>
              <option value="7">Convenios</option>
              <option value="1">Establecimientos grandes y empresas</option>
              <option value="6">Muestra Rural</option>
              <option value="5">Operativo Masivo</option>
              <!--<option value="8">Organismos operadores de agua</option>
              <option value="2">Pesca - Mineria</option>-->
              <option value="3">RENEM</option>
            </select>
          </div>
          <a onclick="goFormLogin()" class="btn-inicio-sesion">Iniciar Ahora</a>
        </div>
        
      </div>
    </div>
  </div>
  
  <!-- <script src="resources/js/jquery-2.1.1.min.js"  ></script> -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.33.1/dist/sweetalert2.min.js"></script>
  <script src='resources/js/jquery-2.1.1.min.js'></script>
  <script src='resources/js/services.js'></script>
  <script src="resources/js/sendAjax.js"></script>
  <script src="resources/js/login.js"></script>

</body>
</html>