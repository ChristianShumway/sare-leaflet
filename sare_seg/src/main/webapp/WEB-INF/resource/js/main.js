let actualPagina = 1
let inicioPaginacion = 1
let finPaginacion = screen.width <= '480' ? 5 : 7
let inicioClavesVista = 0
let finClavesVista = 9
let dataCleeListNew = {}
let dataResultSearchClee = {}
let cleeListType = 'normal'
let titulo_impresion='SARE'

let layersSARE = ['c100', 'c101', /*'cc200', 'cc201', 'cc202', 'cc203', 'cc2031', 'cc2032',*/ 'wdenue'];


const init=()=>{
        addCapas({'checked': true, 'id': 'unidades'});
}

const handleChangeOptions = option => {
  const title = document.getElementById(`option-${option}`)
  const idWms= urlServices['map'].label;
  const checkBox = document.getElementById(`checkbox-${option}`)
  checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
  if(option=="sucursal"){
    addCapas(checkBox);
  }
  else{
   addLayerEconomicas(checkBox,option);
  }
  
}

//funcion para agregar capas en las opciones Matrice,unicos,denue

const addLayerEconomicas = (chk,option) => {
    var idWms = urlServices['map'].label;
    if (chk.checked === true) {
        if (layersSARE.indexOf(chk.id) < 0) {
            switch(option){
                case 'denue':
                  addLay('wdenue'); 
                case 'unicos':
                  addLay('c101u');
                case 'matrices':
                  addLay('C101M');
                case 'postes':
                  addLay('c104');
              case 'sucursal':
                  addLay('c101');   
            }
        }
    } else {
        layersSARE.splice(layersSARE.indexOf(chk.id), 1);
    }
    console.log("layersSARE " + layersSARE);
    MDM6('setParams', {layer: idWms, params: {'layers': layersSARE, 'EDO': '00'}});
};

//Funcion agregar capas en el mapa en la opcion sucursales
const addCapas=chk=>{
    var idWms = urlServices['map'].label;
    if(chk.checked==true){
       if(layersSARE.indexOf('c101')<0)
       {
           addLay('c101');
       } 
    }else{
        if(chk.checked==='noFalse'){
            
        }
        else
        {
          remLay('c101')  
        }
        if(typeof chk.mza !== 'undefined' && chk.mza === true)
        {
          remLay('c103');
          addLay('c102');
          addLay('c102r');  
        }
        if(typeof chk.ageb !== 'undefined' && chk.ageb === true)
        {
            remLay('c102');
            remLay('c102r');
            addLay('c103');
        }
    }
    ordenaLayer();
    MDM6('setParams', {layer: idWms, params: {'layers': layersSARE, 'EDO': '00'}});
}

//Funcion para agregar capas cuando ya tenemos agregadas en las capas

const remLay=item=> {
    var index = layersSARE.indexOf(item);
    if (index >= 0) {
        layersSARE.splice(index, 1);
    }
}

//Funcion para llenar arreglo con las capas que van en el mapa

const addLay=item=> {
    var index = layersSARE.indexOf(item);
    if (index < 0) {
        layersSARE.push(item);
    }
}

//Funcion para ordenar las capas que llegan al mapa

const ordenaLayer=()=>{
    if (layersSARE.indexOf('c102') > 0) {
        remLay('c102');
        remLay('c102r');
        layersSARE.unshift('c102');
        layersSARE.unshift('c102r');
    } else if (layersSARE.indexOf('c103') > 0) {
        remLay('c103');
        layersSARE.unshift('c103');
    }
}

//Funcion que hace que se actualice el mapa cada vez que se hace zoom
const eventoMoveZoom =()=>{
    var level = MDM6('getZoomLevel');
    if (level > 9 && level < 13) {
        addCapas({'checked': 'noFalse', 'id': 'unidades', 'ageb': true, 'mza': false});
    } else if (level >= 13) {
        addCapas({'checked': 'noFalse', 'id': 'unidades', 'ageb': false, 'mza': true});
    } else {
        addCapas({'checked': 'noFalse', 'id': 'unidades', 'ageb': false, 'mza': false});
    }
};


// Función buscar clave
const buscarUE = () => {
  const viewSearchContainer = document.getElementById('arrow-search')
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  const claveBusqueda = document.getElementById('clave-busqueda')
  const wrapSiRatifica = document.getElementById('wrap-si-ratifica')
  const wrapNoRatifica = document.getElementById('wrap-no-ratifica')

  if (claveBusqueda.value  == '') {
    Swal.fire({
      position: 'bottom-end',
      type: 'warning',
      title: 'Ingresa primero la clave a buscar',
      showConfirmButton: false,
      timer: 2000
    })
  } else {
    // animación btns
    wrapSiRatifica.classList.add('animated', 'slideInLeft', 'slow')
    wrapNoRatifica.classList.add('animated', 'slideInRight', 'slow')
    wrapSiRatifica.addEventListener('animationend', () => wrapSiRatifica.classList.remove('animated', 'slideInLeft', 'slow'))
    wrapNoRatifica.addEventListener('animationend', () => wrapNoRatifica.classList.remove('animated', 'slideInRight', 'slow'))

    viewSearchContainer.removeAttribute('onclick')
    tituloBusqueda.removeAttribute('onclick')
    handleVisibleSearch()
    handleVisibleRatifica()

  }
}

// Función ver lista claves
const handleViewCleeList = () => {
  sendAJAX(
    urlServices['getListadoUnidadesEconomicas'].url, 
    {
      'proyecto': 1, 
      'tramo': '01000000000', 
      'id_ue': 01,
    }, 
    urlServices['getListadoUnidadesEconomicas'].type, 
    data => { 

      dataCleeListNew = data[0]
      popupCleeList(data[0].datos)
      
    }, 
    () => {}
  )
}

const popupCleeList = data => {
  console.log(data)
  const notFoundClee = document.getElementById('wrap-list-not-found')
  //totalClaves == 0 ? notFoundClee.classList.remove('wrap-inactive') : null
  if (data.length == 0){
    notFoundClee.classList.remove('wrap-inactive')
    return
  }

  Swal.fire({
    title: '<strong>LISTA CLAVES</strong>',
    html: cleeList(data, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista),
    showCloseButton: true,
    showConfirmButton: false,
    showCancelButton: false,
    focusConfirm: false,
  })
}


const cleeList = (data, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista) => {
  let tabla = ''
  const clavesPorVista = 10
  const totalClaves = data.length
  const totalPaginaciones = Math.ceil(totalClaves/clavesPorVista)
  console.log(data)
  let posicionFinal = ''
  finClavesVista > totalClaves ? posicionFinal = totalClaves - 1 : posicionFinal = finClavesVista
  //alert(totalClaves)

  tabla = `
    <div id='container-search-cleelist' class='container-search-cleelist'>
      <span class='text-search-cleelist'>Filtrar:</span>
      <div class="wrap-input-search-cleelist">
        <input type='text' id='search-cleelist' name='search-cleelist'  onkeypress="handleSearchCleeEnter(event)" />
      </div>
    </div>

    <div class='wrap-list items not-found wrap-inactive' id="wrap-list-not-found">
      <div class='item-lists'><span></span>NO SE ENCONTRARON REFERENCIAS</div>
    </div>
    
    <div id='container-cleelist' class='container-cleelist row'>
      <div class='wrap-list'>
        <div class='title-column'>Clave</div>
        <div class='title-column'>Código</div>
      </div>`

      for(let num = inicioClavesVista; num <= posicionFinal ; num ++){
        let {idue, c154} = data[num]
        tabla += `<div class='wrap-list items'>
          <div class='item-list'><span>${idue}</span></div>
          <div class='item-list'><span>${c154}</span></div>
        </div>`
      }

      tabla += `
        <ul class="pagination" id="pagination-clee">
          <li onclick='handlePaginationActive(${actualPagina}-1)' id="pagination-back" class="waves-effect">
            <a><i class="material-icons">chevron_left</i></a>
          </li>`
          actualPagina == 1 ? setTimeout( () => document.getElementById('pagination-back').classList.add('disabled'), 300 ) : false
          actualPagina == totalPaginaciones ? setTimeout( () => document.getElementById('pagination-next').classList.add('disabled'), 300 ) : false
          for(let pag = inicioPaginacion; pag<=finPaginacion; pag++){
            tabla+= `<li onclick='handlePaginationActive(${pag}, ${totalPaginaciones})' class='waves-effect' id='pag-${pag}'><a>${pag}</a></li>`
            const pagActive = document.getElementById('pag-1')
            
            if(pag == actualPagina){
              setTimeout( () => document.getElementById(`pag-${pag}`).classList.add('active'), 300 )
            }
          }
          tabla += `<li onclick='handlePaginationActive(${actualPagina}+1)' id="pagination-next" class="waves-effect"><a><i class="material-icons">chevron_right</i></a></li>
        </ul>`
      
        //totalClaves <= 10 ? setTimeout( () => document.getElementById('pagination-clee').style.display = 'none', 100 ) : false

    tabla +=`</div>`

  return tabla
}

const handlePaginationActive = (page, totalPag) => {
  //alert(totalPag)
  if (page > actualPagina || page < actualPagina){
    inicioClavesVista = (page -1) * 10
    finClavesVista = inicioClavesVista + 9
  } else if(page == actualPagina) {
    inicioClavesVista = inicioClavesVista
    finClavesVista = finClavesVista
  }

  if (page == finPaginacion) {
    // if(finPaginacion <= totalPag){
      if(screen.width <= '480'){
        inicioPaginacion = inicioPaginacion + 3
        finPaginacion = finPaginacion + 3
      } else {
        inicioPaginacion = inicioPaginacion + 5
        finPaginacion = finPaginacion + 5
      }
    // } else {
    //   finPaginacion = totalPag
    // }

  } else if( page == inicioPaginacion) {
    if (page !== 1){
      if(screen.width <= '480'){
        inicioPaginacion = inicioPaginacion - 3
        finPaginacion = finPaginacion - 3
      } else {
        inicioPaginacion = inicioPaginacion - 5
        finPaginacion = finPaginacion - 5
      }
    }
  }
  
  if( inicioPaginacion < 1 ){
    inicioPaginacion = 1
    screen.width <= '480' 
      ? finPaginacion = inicioPaginacion +  (totalPag <= 4 ? totalPag - 1 : 4)
      : finPaginacion = inicioPaginacion +  (totalPag <= 6 ? totalPag - 1 : 6)
  }

  if( finPaginacion > totalPag ){
    finPaginacion = totalPag
    screen.width <= '480' 
      ? inicioPaginacion = finPaginacion - (totalPag <= 3 ? totalPag - 1 : 3)
      : inicioPaginacion = finPaginacion - (totalPag <= 5 ? totalPag - 1 : 5)
  }


  actualPagina = page
  //console.log(dataCleeListNew)
  if(cleeListType == 'normal'){
    popupCleeList(dataCleeListNew.datos)
  } else if (cleeListType == 'busqueda'){
    popupCleeList(dataResultSearchClee.datos)
  }
  //const containerCleeList = document.getElementById('container-cleelist')
  //containerCleeList.innerHTML = ''
  //cleeList(dataCleeListNew.datos, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista)
  
  console.log(`pagina actual ${actualPagina}`)
  console.log(inicioPaginacion)
  console.log(finPaginacion)
  console.log(inicioClavesVista)
  console.log(finClavesVista)
}


const handleSearchCleeEnter = e =>  {
  tecla = (document.all) ? e.keyCode : e.which;
  tecla == 13 ? handleSearchCleeList(e) : false
}

const handleSearchCleeList = () => {
  const inputValue = document.getElementById('search-cleelist')
  const arrayCleeFind = []
  const data = dataCleeListNew.datos
  
  if (inputValue.value == ''){
    actualPagina = 1
    inicioPaginacion = 1
    //finPaginacion = 7
    finPaginacion = screen.width <= '480' ? 5 : 7
    inicioClavesVista = 0
    finClavesVista = 9
    cleeListType = 'normal'
    handleViewCleeList()
  } else {

    // encontar similitudes referente al valor de la busqueda y agregarlos a un nuevo objeto
    data.map (item => {
      if(item.idue.indexOf(inputValue.value) != -1){
        arrayCleeFind.push(item)
      }
    })

    // filtramos solo los que no son repetidos
    let result = arrayCleeFind.filter((valorActual, indiceActual, arreglo) => {
      return arreglo.findIndex(valorDelArreglo => JSON.stringify(valorDelArreglo) === JSON.stringify(valorActual)) === indiceActual
    })

    const totalPaginaciones = Math.ceil(result.length/10)
    const numPaginaciones = screen.width <= '480' ? 5 : 7
    const paginacionesAvanzar = totalPaginaciones >= numPaginaciones ? numPaginaciones : totalPaginaciones

    actualPagina = 1
    inicioPaginacion = 1
    finPaginacion = paginacionesAvanzar
    inicioClavesVista = 0
    result.length > 10 ? finClavesVista = 9 : finClavesVista = result.length - 1
  
    cleeListType = 'busqueda'
    dataResultSearchClee.datos = result
    popupCleeList(dataResultSearchClee.datos)
  
  }

}


// Función ratificar
const ratificar = request => {
  const viewSearchContainer = document.getElementById('arrow-search')
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  viewSearchContainer.setAttribute('onclick','handleVisibleSearch()')
  tituloBusqueda.setAttribute('onclick', 'handleVisibleSearch()')
  handleVisibleSearch()
  handleVisibleRatifica()
  handleShowAlertPickMap()

  if(request == 'si'){
    enabledInputs()
    handleActionTargetRef()
    handleActionButtons('enabled')
  }
}

// Función validación de formulario campos vacios
const handleFormValidations = () => {
  const totalInputs = objForm.length
  let inputsInfo = 0
  //$('.button-collapse').sideNav('hide')

  for(let input = 0; input < objForm.length; input ++){
    const {id, name, title, key} = objForm[input]
    const element = document.getElementById(id)
    const wrapTitle = document.getElementById(title)
    let visible = wrapTitle.dataset.visible

    !inputsByWrap[key] ? inputsByWrap[key] = true : false   

    if(element.value == ''){
      element.style.borderColor = 'red'
      element.classList.add('animated', 'shake')
      visible == 'hide' ? handleVisibleForm(key) : false
      inputsEmpty = true
      containerInputsVisible = false
      const msgInputEmpty = `Favor de completar la información del campo ${name}`
      alertToastForm(msgInputEmpty)
      inputsByWrap[key] = false
      setTimeout(() => element.classList.remove('animated', 'shake'), 1000)

      wrapTitle.id == title && 
        wrapTitle.classList.add('error') 
        element.focus()

      break
    } else {
      element.style.borderColor = '#eeeeee'
      containerInputsVisible = true
      inputsInfo ++
    
      wrapTitle.id == title && 
        wrapTitle.classList.remove('error') 
    }   
    
  }
  console.log(inputsByWrap)
  let objNameWraps = Object.keys(inputsByWrap)

  for(let wrap=0; wrap < objNameWraps.length; wrap++){
    let wrapKey = objNameWraps[wrap]
    if(inputsByWrap[wrapKey]){
      const wrapTitle = document.getElementById(`title-${wrapKey}`)
      let visible = wrapTitle.dataset.visible
      visible == 'show' ? handleVisibleForm(wrapKey) : false
    }
  }
  
  inputsInfo == totalInputs && alert('no hay inputs vacios')  

}

const identify=(coor)=>{
    HandleWhatDoYouWantToDo(coor)
}


// Función al seleccionar opciones identificar, puntear  y vista calle
const HandleWhatDoYouWantToDo = (coor) => {
    let request=$('input:radio[name=accion]:checked').val();
    switch(request)
        {
            case 'identificar':
                identificaUE(coor.lon, coor.lat);
            break;
            case 'puntear':
            break;
            case 'v_calle':
                StreetView(coor.lon,coor.lat);
            break;
            
        }
}

//Funcion para inicializar la vista de calle

const StreetView=(x,y)=>{
    modalGoogleMap(x, y, 'mercator');
}

//modal que manda llamar la vista de calle
const modalGoogleMap=(x,y,tc)=>{
    if(tc==='mercator'){
        sendAJAX(urlServices['serviceIdentifyStreetView'].url, {'proyecto':1,'x': x, 'y': y}, urlServices['serviceIdentifyStreetView'].type, function (data) {
            if (data[0].operation) {
                MDM6('hideMarkers', 'identify');
                //$('div.modal-dialog').css({width: '750px'});
                //initializeStreetView(data[0].datos['x'], data[0].datos['y']);
                ubicacion = data[0].datos['y'] + ',' + data[0].datos['x'];
                //alert(ubicacion);
                var url = 'http://maps.google.com/maps?q=&layer=c&cbll=' + ubicacion + '&cbp=';
                //alert(url);
                setTimeout(function () {
                    win = window.open(url, "_blank", "width=800,height=600,top=150,left=200");
                }, 200);

            }
        }, '');
    }
}
//Funcion para identificar la unidad economica y llamar el servicio
const identificaUE=(x,y)=>{
    let capas = ($('#checkbox-denue').is(":checked")) ? 'DENUE,' : '';
    capas += ($('#checkbox-matrices').is(":checked")) ? 'Matrices,' : '';
    capas += ($('#checkbox-sucursal').is(":checked")) ? 'Sucursales,' : '';
    capas += ($('#checkbox-unicos').is(":checked")) ? 'Unicos,' : '';
    capas += ($('#checkbox-postes').is(":checked")) ? 'Postes,' : '';
    capas = capas.slice(0, -1);
    if(capas.lenght===0)
    {
        mostrarMensaje();
    }
    else{
        callServicioIdentificar(capas,x,y);
    }
}

//Funcion que muestra el sweetAlert

const mostrarMensaje=()=>{
    swal.fire({
            title: '<i class="fa fa-map-marker"></i> Identificación de Unidades Económicas',
            text: 'Selecciones una capa de información',
            showConfirmButton: true,
            confirmButtonColor: "#0f0f0f",
            allowEscapeKey: true,
            allowOutsideClick: true,
            html: true,
            animation: true
        });
        MDM6('hideMarkers', 'identify');
        xycoorsx = '';
        xycoorsy = '';
}

//Funcion que manda llamar el servicio para identificar las unidades económicas
const callServicioIdentificar=(capas,x,y)=>
{
    sendAJAX(urlServices['serviceIdentifyUE'].url, {'proyecto':1,'x': x, 'y': y, 'opciones': capas}, urlServices['serviceIdentifyUE'].type, function (data) {
        if (data[0].operation) {
            if (data[0].datos.mensaje.messages === null) {
                MDM6('hideMarkers', 'identify');
                var dataToFrm = data[0].datos.datos;
                modalShowInfoUE(dataToFrm, capas);
            } else {
                swal.close();
                $('#btnRatificaSi').attr('disabled', false);
                $('#btnRatificaNo').attr('disabled', false);
                swal.fire({
                    type:'error',
                    title: 'Identificación de Unidades Económicas',
                    text: data[0].datos.mensaje.messages,
                    showConfirmButton: true,
                    confirmButtonColor: "#5562eb",
                    allowEscapeKey: true,
                    allowOutsideClick: true,
                    animation: true
                });

                MDM6('hideMarkers', 'identify');
                xycoorsx = '';
                xycoorsy = '';
            }

        } else {

        }
    }, function () {
          swal({
          title: 'Identificación de Unidades Económicas!',
          text: 'Por favor espere un momento',
          timer: 5000,
          onOpen: function () {
            swal.showLoading()
          }
        }).then(
          function () {},
          function (dismiss) {
            if (dismiss === 'timer') {
              console.log('I was closed by the timer')
            }
          }
        )
    });
}
//muestra mensaje con la tabla que contiene la información de las unidades economicas para el establecimiento seleccionado
var modalShowInfoUE = function (rows, capas) {
    capas = capas.split(",");
    swal.fire({
        title: '<h2 style="border-bottom: 1px solid lightgray; padding-bottom:10px;">Identificación de Unidades Económicas</h2>',
        type:'info',
        width: '800px',
        html: '<div id="tabL"></div>',
        confirmButtonText: 'Aceptar',
        customClass: 'swal-wide',
        confirmButtonColor: '#0f0f0f',
        onOpen: cargaTemplateIdentificaUE(rows)
    });

};

const cargaTemplateIdentificaUE=rows=> {
    loadTemplate('tabL', "resources/templates/table_UE.html?frm=" + Math.random(),
            function () {
                //interpreta la respuesta
                rows.forEach(function (o, i) {
                    var html = '';
                    o.datos.forEach(function (ob, ix) {
                        var objDetalle = JSON.stringify(ob);
                        if (o.capa === 'eje') {
                            html += "<tr><td>" + ob.tipovial + "</td><td>" + ob.nomvial + "</td></tr>";
                        } else {
                            html += "<tr><td>" + ob.cve_unica + "</td><td>" + ob.nom_est + "</td><td>" + ob.razon_soc + "</td><td><a title='Detalle' onclick='buildDetalle(" + objDetalle + ")'><i class='material-icons icFicha'>assignment</a></td></tr>";
                        }
                    });
                    $('#tabUE_' + o.capa + ' tbody').html(html);
                    //añade el option al select
                    $('#slcapa').append($('<option>', {value: o.capa, text: o.capa, selected: true}));
                    $('#slcapa').show();
                    showUEficha(o.capa);
                });
            });
}
//esta función oculta las tables que no solicito el usuario para mostrar en la opción identificar
const showUEficha=ficha=> {
    //escondo las 2 fichas
    $("#tabUE_DENUE").hide();
    $("#tabUE_Matrices").hide();
    $("#tabUE_Sucursales").hide();
    $("#tabUE_detalle").hide();
    $("#tabUE_eje").hide();
    //enciendo la ficha que me dan
    $('#btnIdentificaRegresar').css('display', 'none');
    $("#tabUE_" + ficha).show();
}

//esta función muestra el detalle de los elementos devueltos por el servicio que identifica contenidas en la ficha
const buildDetalle =ficha=> {
    showUEficha('detalle');
    $('#btnIdentificaRegresar').css('display', 'flex');
    ficha.actividad = (typeof ficha.actividad !== 'undefined') ? ficha.actividad : '-';
    ficha.cor_indust = (typeof ficha.cor_indust !== 'undefined') ? ficha.cor_indust : '-';
    ficha.cve_ageb = (typeof ficha.cve_ageb !== 'undefined') ? ficha.cve_ageb : '-';
    ficha.cve_ent = (typeof ficha.cve_ent !== 'undefined') ? ficha.cve_ent : '-';
    ficha.cve_loc = (typeof ficha.cve_loc !== 'undefined') ? ficha.cve_loc : '-';
    ficha.cve_mun = (typeof ficha.cve_mun !== 'undefined') ? ficha.cve_mun : '-';
    ficha.cve_mza = (typeof ficha.cve_mza !== 'undefined') ? ficha.cve_mza : '-';
    ficha.cve_unica = (typeof ficha.cve_unica !== 'undefined') ? ficha.cve_unica : '-';
    ficha.nom_est = (typeof ficha.nom_est !== 'undefined') ? ficha.nom_est : '-';
    ficha.nomasen = (typeof ficha.nomasen !== 'undefined') ? ficha.nomasen : '-';
    ficha.nomvial = (typeof ficha.nomvial !== 'undefined') ? ficha.nomvial : '-';
    ficha.numextalf = (typeof ficha.numextalf !== 'undefined') ? ficha.numextalf : '-';
    ficha.numextnum = (typeof ficha.numextnum !== 'undefined') ? ficha.numextnum : '-';
    ficha.numintalf = (typeof ficha.numintalf !== 'undefined') ? ficha.numintalf : '-';
    ficha.numintnum = (typeof ficha.numintnum !== 'undefined') ? ficha.numintnum : '-';
    ficha.razon_soc = (typeof ficha.razon_soc !== 'undefined') ? ficha.razon_soc : '-';
    ficha.tipo_vial = (typeof ficha.tipo_vial !== 'undefined') ? ficha.tipo_vial : '-';
    ficha.tipoasen = (typeof ficha.tipoasen !== 'undefined') ? ficha.tipoasen : '-';



    $(".modal-footer").append('<button type="button" class="pure-button" id="btn_regresar" onclick="showUEficha($(\'#slcapa\').val())">Regresar</button>');
    $("#tabUE_detalle").html('<table class="pure-table tabUE" id="tabUE_detalleTab"><tbody></tbody></table>');

    var html = '<tr><td>Razón Social</td><td>' + ficha.razon_soc + '</td></tr>';
    if (ficha.actividad !== '-')
        html += '<tr><td>Actividad</td><td>' + ficha.actividad + '</td></tr>'
    if (ficha.cve_ent !== '-')
        html += '<tr><td>Entidad</td><td>' + ficha.cve_ent + '</td></tr>'
    if (ficha.cve_mun !== '-')
        html += '<tr><td>Municipio</td><td>' + ficha.cve_mun + '</td></tr>'
    if (ficha.cve_loc !== '-')
        html += '<tr><td>Localidad</td><td>' + ficha.cve_loc + '</td></tr>'
    if (ficha.cve_ageb !== '-')
        html += '<tr><td>AGEB</td><td>' + ficha.cve_ageb + '</td></tr>'
    if (ficha.cve_mza !== '-')
        html += '<tr><td>Manzana</td><td>' + ficha.cve_mza + '</td></tr>'
    if (ficha.tipo_vial !== '-')
        html += '<tr><td>Tipo Vialidad</td><td>' + ficha.tipo_vial + '</td></tr>'
    if (ficha.nomvial !== '-')
        html += '<tr><td>Nombre Vialidad</td><td>' + ficha.nomvial + '</td></tr>'
    if (ficha.numextnum !== '-')
        html += '<tr><td>Número Ext</td><td>' + ficha.numextnum + '</td></tr>'
    if (ficha.numextalf !== '-')
        html += '<tr><td>Número Ext (letra)</td><td>' + ficha.numextalf + '</td></tr>'
    if (ficha.numintnum !== '-')
        html += '<tr><td>Número Int</td><td>' + ficha.numintnum + '</td></tr>'
    if (ficha.numintalf !== '-')
        html += '<tr><td>Número Int (letra)</td><td>' + ficha.numintalf + '</td></tr>'
    if (ficha.tipoasen !== '-')
        html += '<tr><td>Tipo de Asentamiento</td><td>' + ficha.tipoasen + '</td></tr>'
    if (ficha.nomasen !== '-')
        html += '<tr><td>Nombre Asentamiento</td><td>' + ficha.nomasen + '</td></tr>'
    if (ficha.cor_indust !== '-')
        html += '<tr><td>Corredor Industrial</td><td>' + ficha.cor_indust + '</td></tr>'

    $('#tabUE_detalleTab tbody').html(html);
}


// función boton opción cancelar
const handleCancelClick = () => {
  //$('.button-collapse').sideNav('hide')
  disabledInputs()
  handleActionButtons('disabled')
}

// Función habilitar inputs formulario
const enabledInputs = () => {
  inputsEditables.map( input => document.getElementById(input.id).removeAttribute('disabled') )
}

// Función deshabilitar inputs formulario
const disabledInputs = () => {
  inputsEditables.map( input => document.getElementById(input.id).setAttribute('disabled', true) )
}

// función activa btns guardar y cancelar cuando se ratifica y desactiva cuando se cancela
const handleActionButtons = res =>{
  const saveOption = document.getElementById('item-save-option')
  const cancelOption = document.getElementById('item-cancel-option')
  const saveMovilOption = document.getElementById('save-movil-option')
  const cancelMovilOption = document.getElementById('cancel-movil-option')
  
  if(res == 'enabled'){
    saveOption.removeAttribute('disabled')
    cancelOption.removeAttribute('disabled')
    saveMovilOption.setAttribute('onclick','handleFormValidations()')
    saveMovilOption.classList.remove('option-disabled')
    cancelMovilOption.setAttribute('onclick','handleCancelClick()')
    cancelMovilOption.classList.remove('option-disabled')
  } else if (res == 'disabled'){
    saveOption.setAttribute('disabled', 'true')
    cancelOption.setAttribute('disabled', 'true')
    saveMovilOption.removeAttribute('onclick','handleFormValidations()')
    saveMovilOption.classList.add('option-disabled')
    cancelMovilOption.removeAttribute('onclick','handleCancelClick()')
    cancelMovilOption.classList.add('option-disabled')
  }
}

const handleSearchCleeValidation = e => {
  const key = window.event ? e.which : e.keyCode
  key < 48 || key > 57 ?  e.preventDefault() : false
}

// alertas formulario
const alertToastForm = title => {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-start',
    showConfirmButton: false,
    timer: 3000
  });
  
  Toast.fire({
    type: 'error',
    title
  })
}

/* METODOS PARA LAS OPCIONES DEL MENU INFERIOR IMPRESION Y REPORTES*/
const opcionMenu = opcion => {    
    switch (opcion){        
        case 2:
            OpenReportes('desktop', 'vista')
            break;
        case 3:
          OpenReportes('movil', 'vista')
            break;
        case 4:
          imprimir();
    }    
}


async function OpenReportes (size, action) {
    const {value: reporte} = await Swal.fire({
      title: action == 'vista' ? 'Reportes' : 'Descarga de Reportes',
      input: 'select',
      inputOptions: {
        '1': 'Reporte de Avance de Registros Punteados',   
        '2': 'Reporte de Establecimientos Pendientes de Punteo',   
      },
      inputPlaceholder: 'Selecciona un Reporte',
      showCancelButton: true,
      confirmButtonText:'Generar',
      cancelButtonText:'Cancelar',
      inputValidator: (value) => {
        return new Promise((resolve) => {
          if (value === '1' || value === '2'|| value === '3') {
            resolve()
          } else {
            resolve('Selecciona el reporte a visualizar')
          }
        })
      }
    })
    
    if (reporte) {
        let src = urlServices['serviceReporte'].url + '?proyecto=1&tipo=PDF&reporte=' + reporte +'&ce=00&ran=' + Math.random();
        let leyenda = ''
        let srcExcel = urlServices['serviceReporte'].url + '?proyecto=1&tipo=EXCEL&reporte=' + reporte +'&ce=00&ran=' + Math.random();
       
        if(reporte === '1'){
            leyenda = 'Descargaste reporte de manzanas'
        } else if (reporte === '2'){
            leyenda = 'Descargaste reporte de localidades'
        }

        if (action == 'vista'){
            if (size == 'desktop'){
                Swal.fire({
                    title: '<strong>Reporte</strong>',
                    width: '100%',
                    html: `<iframe class='iframe-reporte' src=${src}></iframe>`,
                    showCloseButton: true,
                    showCancelButton: false,
                    showConfirmButton: false,
                    focusConfirm: false,
                })
            } else  if (size == 'movil'){
                window.open(src, 'fullscreen=yes')
            } 
        } else if (action == 'descarga'){
            window.location.href = srcExcel    
        }
    }  
}



var imprimir=function(){
    $('#window_bottom').hide();
    var data = $('#map').html();
    $('#window_bottom').show();
    var ventana = window.open('', '', 'height=1000,width=1024');
    ventana.document.open();
    ventana.document.write('<html><head ><title>'+titulo_impresion+'</title>');
    ventana.document.write('<script src="resources/js/jquery-2.1.1.min.js"></script>');
    ventana.document.write('<script src="resources/js/main.js"></script>');
    if (navigator.userAgent.indexOf("Chrome") !== -1) {
        ventana.document.write('<style type="text/css"  media="print"> '+
                '@page{size:portrait;}html { width:29.4cm;height:30.62cm;}'+
                'body{margin-bottom: -2.30cm;margin-top: 2cm;margin-right: -1.0cm;margin-left:-1.0cm;}'+
                '.divMapa{page-break-after : always;} '
                '.olControlMousePosition{display:none;} '+
                '#OpenLayers_Control_ScaleLine_4{display:none;}'+
                '#OpenLayers_Map_5_OpenLayers_ViewPort{ width:82%; position:relative; height:100%; left:-200px} '+
                '</style>');
    } else if (navigator.userAgent.indexOf("Firefox") !== -1) {
        ventana.document.write('<style type="text/css"  media="print"> @page{size:portrait;}html { width:28.4cm;height:29.90cm;}'+
                'body{margin-bottom: -2.30cm;margin-top: 2cm;margin-right: -1.08cm;margin-left:-1.0cm;} '+
                '.divMapa{page-break-after : always;}'+
                '.olControlMousePosition{display:none;} '+
                '#OpenLayers_Control_ScaleLine_4{display:none;}'+
                '  #OpenLayers_Map_5_OpenLayers_ViewPort{width:82%;overflow:hidden;position:relative;height:100%;}" </style>');
    } else if (navigator.userAgent.indexOf('Trident') !== -1) {
        ventana.document.write('<link rel="stylesheet" type="text/css" href="css/print_ie.css"/>');
    }
    ventana.document.write('<link rel="stylesheet" type="text/css" href="resources/css/app.css"/>');
    ventana.document.write('<link rel="stylesheet" type="text/css" href="resources/css/materialize.css"/>');
    ventana.document.write('<script src="resources/js/materialize.min.js"></script>');
    ventana.document.write('</head>');
    ventana.document.write('<body>');
    ventana.document.write('<div class="" id="" >');
    ventana.document.write(data);
    ventana.document.write('</div>');
    ventana.document.write('<div id="modal" class="modal" style="top: 40%!important;">'+
            '<div class="modal-content">'+
                '<div> Cargando</div>'+
                      '<div class="preloader-wrapper big active">'+    
                           '<div class="spinner-layer spinner-blue-only">'+
                               '<div class="circle-clipper left">'+
                                    '<div class="circle"></div>'+
                               '</div>'+
                               '<div class="gap-patch">'+
                               '<div class="circle"></div>'+
                            '</div>'+
                            '<div class="circle-clipper right">'+
                                 '<div class="circle"></div>'+
                            '</div>'+
                      '</div>'+
                '</div>'+          
            '</div>'+   
            '</div>');
    ventana.document.write('<script>modal2();setClassPrint();setTimeout(function(){closeModal2();},5000);</script>');
    ventana.document.write('</body>');
    ventana.document.write('</html>');
    ventana.document.close();
    setTimeout(function () {
        ventana.print();
        ventana.close();
    }, 6000);
}

function modal2() {        
    $('.modal').modal();    
    $('.modal').modal('open');
}


function closeModal2() {               
    $('.modal').modal('close');
}

function setClassPrint() {
    $('#OpenLayers_Map_5_OpenLayers_ViewPort').css({
      'left': '-310',
        'width': '110%'
    }); 
}

/* FIN OPCIONES DEL MENU INFERIOR DERECHO IMPRESION Y REPORTES*/
