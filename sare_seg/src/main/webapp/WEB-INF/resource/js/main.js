let actualPagina = 1
let inicioPaginacion = 1
let finPaginacion = screen.width <= '480' ? 5 : 7
let inicioClavesVista = 0
let finClavesVista = 9
let dataCleeListNew = {}
screen.width <= '480' 

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
      console.log(data[0])
      popupCleeList(data[0].datos)
      
    }, 
    () => {}
  )
}

const popupCleeList = data => {
  const clavesPorVista = 10
  Swal.fire({
    title: '<strong>LISTA CLAVES</strong>',
    html: cleeList(data, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista, clavesPorVista),
    showCloseButton: true,
    showConfirmButton: false,
    showCancelButton: false,
    focusConfirm: false,
  })
}


const cleeList = (data, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista, clavesPorVista) => {

  const totalClaves = data.length
  const totalPaginaciones = Math.ceil(totalClaves/clavesPorVista)
  console.log(data)

  let tabla = `
    <div id='container-search-cleelist' class='container-search-cleelist'>
      <span class='text-search-cleelist'>Filtrar:</span>
      <div class="wrap-input-search-cleelist">
        <input type='text' id='search-cleelist' name='search-cleelist'  onkeypress="handleSearchCleeEnter(event)" />
      </div>
    </div>
    <div id='container-cleelist' class='container-cleelist row'>
      <div class='wrap-list'>
        <div class='title-column'>Clave</div>
        <div class='title-column'>Código</div>
      </div>`

      for(let num = inicioClavesVista; num <= finClavesVista; num ++){
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
            tabla+= `<li onclick='handlePaginationActive(${pag})' class='waves-effect' id='pag-${pag}'><a>${pag}</a></li>`
            const pagActive = document.getElementById('pag-1')
            //setTimeout( () => console.log(document.getElementById(`pag-${pag}`)), 300 )
            if(pag == actualPagina){
              setTimeout( () => document.getElementById(`pag-${pag}`).classList.add('active'), 300 )
            }
          }
          tabla += `<li onclick='handlePaginationActive(${actualPagina}+1)' id="pagination-next" class="waves-effect"><a><i class="material-icons">chevron_right</i></a></li>
        </ul>`
      
        totalClaves <= 7 ? setTimeout( () => document.getElementById('pagination-clee').style.display = 'none', 100 ) : false

      
    tabla +=`</div>`

  //console.log(data)
  return tabla
}

const handlePaginationActive = page => {
  if (page > actualPagina || page < actualPagina){
    inicioClavesVista = (page -1) * 10
    finClavesVista = inicioClavesVista + 9
  } else if(page == actualPagina) {
    inicioClavesVista = inicioClavesVista
    finClavesVista = finClavesVista
  }

  if (page == finPaginacion) {
    //finPaginacion = screen.width <= '480' ? 5 : 7
    if(screen.width <= '480'){
      inicioPaginacion = inicioPaginacion + 3
      finPaginacion = finPaginacion + 3
    } else {
      inicioPaginacion = inicioPaginacion + 5
      finPaginacion = finPaginacion + 5
    }
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

  actualPagina = page
  handleViewCleeList()
  
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
    handleViewCleeList()
  } else {
    const result = data.find( clee => clee.idue == inputValue.value)
    if (!result) {
      alert('no se encontraron')
    } else {
      arrayCleeFind.push(result)
      inicioClavesVista = 0
      finClavesVista = arrayCleeFind.length - 1
      inicioPaginacion = 1
      finPaginacion = arrayCleeFind.length
      popupCleeList(arrayCleeFind)
    }
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
            case 'calle':
            break;
            
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
    swal({
            title: '<i class="fa fa-map-marker"></i> Identificación de Unidades Económicas',
            text: 'Selecciones una capa de información',
            showConfirmButton: true,
            confirmButtonColor: "#DD6B55",
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
    sendAJAX(urlServices['serviceIdentifyUE'].url, {'x': x, 'y': y, 't': capas}, urlServices['serviceIdentifyUE'].type, function (data) {
        boxModal.close();
        if (data[0].operation) {
            if (typeof data[0].datos.mensaje.messages === 'undefined') {
                MDM6('hideMarkers', 'identify');
                var dataToFrm = data[0].datos.datos;
                modalShowInfoUE(dataToFrm, capas);
            } else {
                boxModal.close();
                $('#btnRatificaSi').attr('disabled', false);
                $('#btnRatificaNo').attr('disabled', false);
                swal({
                    title: '<i class="fa fa-map-marker"></i> Identificación de Unidades Económicas',
                    text: data[0].datos.mensaje.messages,
                    showConfirmButton: true,
                    confirmButtonColor: "#DD6B55",
                    allowEscapeKey: true,
                    allowOutsideClick: true,
                    html: true,
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
            title: '<i class="fa fa-map-marker"></i> Identificación de Unidades Económicas',
            text: '<i class="fa fa-refresh fa-spin"></i> Por favor espere un momento',
            showConfirmButton: false,

            html: true
        });
    });
}
//muestra mensaje con la tabla que contiene la información de las unidades economicas para el establecimiento seleccionado
var modalShowInfoUE = function (rows, capas) {
    capas = capas.split(",");
    //swal.close();
    swal({
        title: '<h2 style="border-bottom: 1px solid lightgray; padding-bottom:10px;">Identificación de Unidades Económicas</h2>',
        text: '<div id="tabL"></div>',
        html: true,
        confirmButtonText: 'Aceptar',
        customClass: 'swal-wide',
        confirmButtonColor: '#01579b',
        onOpen: cargaTemplateIdentificaUE(rows)
    });

};

const cargaTemplateIdentificaUE=rows=> {
    loadTemplate('tabL', "resource/views/table_UE.html?frm=" + Math.random(),
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


