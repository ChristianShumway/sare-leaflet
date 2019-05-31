let dataUserFromLoginLocalStorage = JSON.parse(localStorage.getItem("dataUserObj"))
let actualPagina = 1
let inicioPaginacion = 1
let finPaginacion = screen.width <= '480' ? 5 : 7
let inicioClavesVista = 0
let finClavesVista = 9
let actualPaginaLock = 1
let inicioPaginacionLock = 1
let finPaginacionLock = screen.width <= '480' ? 5 : 7
let inicioClavesVistaLock = 0
let finClavesVistaLock = 9
let dataCleeListNew = {}
let dataCleeListNewLock = {}
let xycoorsx, xycoorsy, punteo, mod_cat, cve_geo, cve_geo2016, cveft, e10_cve_vial, confirmacionPunteo
screen.width <= '480' 
let layersSARE = ['c100', 'c101', 'wdenue']
let dataResultSearchClee = {}
let dataResultSearchCleeLock = {}
let cleeListType = 'normal'
let titulo_impresion='SARE' 
let fieldExists = false //bandera para momento de puntear saber si ya se hizo cambio de inputs por selects y al reves 
let bandera_ratificar=false
let catalogoCatVial = []
let arrayClavesBloqueadas = ""
let arrayClavesBloqueadasTodas = ""
let banderaDesbloquear = false
let bandera=false
let isAlta=false

var ObjectRequest = {}
const idEleToSelect = ['e10_A', 'e10_B', 'e10_C']


const init = () => addCapas ( { 'checked': true, 'id': 'unidades' } )

const handleChangeOptions = option => {
  const title = document.getElementById(`option-${option}`)
  const idWms = urlServices['map'].label;
  const checkBox = document.getElementById(`checkbox-${option}`)
  checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
  if (option == "checkbox-sucursal") {
    addCapas(checkBox);
  }
  else {
    addLayerEconomicas(checkBox, option);
  }

}

//funcion para agregar capas en las opciones Matrice,unicos,denue
const addLayerEconomicas = (chk, option) => {
  var idWms = urlServices['map'].label
  if (chk.checked === true) {
    if (layersSARE.indexOf(chk.id) < 0) {
      switch (option) {
        case 'denue':
          addLay('wdenue')
        case 'unicos':
          addLay('c101u')
        case 'matrices':
          addLay('C101M')
        case 'postes':
          addLay('c104')
        case 'sucursal':
          addLay('c101')
      }
    }
  } else {
    layersSARE.splice(layersSARE.indexOf(chk.id), 1)
  }
  MDM6('setParams', { layer: idWms, params: { 'layers': layersSARE, 'EDO': '00' } })
}

//Funcion agregar capas en el mapa en la opcion sucursales
const addCapas = chk => {
  var idWms = urlServices['map'].label
  if (chk.checked == true) {
    if (layersSARE.indexOf('c101') < 0) {
      addLay('c101')
    }
  } else {
    if (chk.checked === 'noFalse') {
        
    }
    else {
      remLay('c101')
    }
    if (typeof chk.mza !== 'undefined' && chk.mza === true) {
      remLay('c103')
      addLay('c102')
      addLay('c102r')
    }
    if (typeof chk.ageb !== 'undefined' && chk.ageb === true) {
      remLay('c102')
      remLay('c102r')
      addLay('c103')
    }
  }
  ordenaLayer()
  MDM6('setParams', { layer: idWms, params: { 'layers': layersSARE, 'EDO': '00' } })
}

//Funcion para agregar capas cuando ya tenemos agregadas en el array de las capas
const remLay = item => {
  var index = layersSARE.indexOf(item)
  index >= 0 && (layersSARE.splice(index, 1) )
}

//Funcion para llenar arreglo con las capas que van en el mapa
const addLay = item => {
  var index = layersSARE.indexOf(item)
  index < 0 && ( layersSARE.push(item) )
}

//Funcion para ordenar las capas que llegan al mapa
const ordenaLayer = () => {
  if (layersSARE.indexOf('c102') > 0) {
    remLay('c102')
    remLay('c102r')
    layersSARE.unshift('c102')
    layersSARE.unshift('c102r')
  } else if (layersSARE.indexOf('c103') > 0) {
    remLay('c103')
    layersSARE.unshift('c103')
  }
}

const zooma = () => {
}

//Funcion que hace que se actualice el mapa cada vez que se hace zoom
const eventoMoveZoom = () => {
  var level = MDM6('getZoomLevel')
  level > 9 && level < 13  ? addCapas({ 'checked': 'noFalse', 'id': 'unidades', 'ageb': true, 'mza': false })
  : level >= 13 ? addCapas({ 'checked': 'noFalse', 'id': 'unidades', 'ageb': false, 'mza': true })
  : addCapas({ 'checked': 'noFalse', 'id': 'unidades', 'ageb': false, 'mza': false })
}

// Función buscar clave
const buscarUE = () => {
  const claveBusqueda = document.getElementById('clave-busqueda')
  if (claveBusqueda.value == '') {
    claveBusqueda.classList.add('animated', 'shake', 'wrap-input-empty')
    claveBusqueda.addEventListener('animationend', () => claveBusqueda.classList.remove('animated', 'shake', 'wrap-input-empty') )
    Swal.fire({
      position: 'bottom-end',
      type: 'warning',
      title: 'Ingresa primero la clave a buscar',
      showConfirmButton: false,
      timer: 2000
    })
  } else {
    // animación btns
    bandera_ratificar=false
    findUE(claveBusqueda.value)
  }
}

//Función que busca la id_ue
const findUE = id_ue => {
  xycoorsx=''
  xycoorsy=''
  if (!/^([0-9])*$/.test(id_ue)) {
    Swal.fire({
      position: 'bottom-end',
      type: 'warning',
      title: 'La clave debe ser númerica',
      showConfirmButton: false,
      timer: 2000
    })
  } else {
    callServiceFindUE(id_ue)
    //handleShowRaticaHideSearch()
    //habilita boton cancelar
    
    
  }
}

//Función que manda llamar el servicio que regresa la busqueda
const callServiceFindUE=(id_ue)=>{
  const cancelOption = document.getElementById('item-cancel-option')
  sendAJAX(urlServices['serviceSearch'].url, 
  {
    'proyecto':dataUserFromLoginLocalStorage.proyectoSesion,
    'p':'1',
    'tramo':dataUserFromLoginLocalStorage.tramoControl,
    'ce': dataUserFromLoginLocalStorage.ce, 
    'usuario':dataUserFromLoginLocalStorage.nombre,
    'id_ue': id_ue
  },
  urlServices['serviceSearch'].type, 
  data => {
    if(data[0].operation){
      //muestra mensaje si hay error
      showModalMsgError(data)
      //realiza acercamiento en el mapa
      acercarWithExtent(data)
      cancelOption.removeAttribute('disabled')
      //comienza a mostrar datos en la interfaz
      showDataInterfaz(data)
    } else {
      Swal.fire({
        position: 'bottom-end',
        type: 'warning',
        title: data[0].messages[0] + "! Porfavor intente nuevamente",
        showConfirmButton: false,
        timer: 2000
      })  
    }    
  }, () => {
    swal({
      title: 'Buscando información de la clave:' +id_ue,
      text: 'Por favor espere un momento',
      timer: 2000,
      onOpen:  () => swal.showLoading() 
    })
    .then( () => { },
    )
  })
}

//Comienza a mostrar datos en la interfaz
const showDataInterfaz = data => {
  handleActionPunteoAlta('off')
  //obtiene el código postal
  getCp(data[0].datos.datos[0].e03)
  validateCoord(data)
}

//valida coordenadas xy en caso de venir vacias ya no hará nada
const validateCoord = data => {
  if (data[0].datos.datos[0].coord_X == null || data[0].datos.datos[0].coord_Y == null) {
    ratificar('no')
  }
  else {
    //si trae coordenadas xy mostrará la chincheta sobre el mapa
    xycoorsx = data[0].datos.datos[0].coord_X
    xycoorsy = data[0].datos.datos[0].coord_Y
    MDM6('addMarker', {lon: parseFloat(xycoorsx), lat: parseFloat(xycoorsy), type: 'routen', params: {nom: 'Ubicación Original', desc: xycoorsx + ", " + xycoorsy}})    
    handleActionTargetRef()
 }
  fillForm(data)
}

//función para llenar el formulario
const fillForm = data => {
  $.each( data[0].datos.datos[0], (i, e) => {
    (i === 'e10_A' || i === 'e10_B' || i === 'e10_C' || i=='tipo_E14'|| i=='tipo_E19') 
      ? $("#" + i).html("<option value='" + e + "'>" + e + "</option>")
      : $("#" + i).val(e);
  })
  fillCatalogo()
  fillCatalogoConjuntosComerciales()
}

//función que llena el catalogo al hacer la busqueda
const fillCatalogo = () => {
  sendAJAX(urlServices['serviceCatalogoAsentamientos'].url, 
    {'proyecto':dataUserFromLoginLocalStorage.proyectoSesion}, 
    urlServices['serviceCatalogoAsentamientos'].type, 
    data => {
      if (data[0].operation) {
        const arrAsent = data[0].datos
        const opcSelected =document.getElementById('tipo_E14')            
        
        arrAsent.forEach( (o, i) => {
          let opt = document.createElement('option')
          opt.appendChild( document.createTextNode(o.descripcion) )
          opt.value = o.tipo_e14
          o.tipo_e14 === opcSelected.value ? opt.setAttribute('selected', true) : false
          opcSelected.appendChild(opt)
        })
      } else { }
    }, 
  '')
}

//función que llena el catalogo al hacer la busqueda
const fillCatalogoConjuntosComerciales = () => {
  sendAJAX(urlServices['serviceCatalogoConjuntosComerciales'].url, 
    {'proyecto':dataUserFromLoginLocalStorage.proyectoSesion}, 
    urlServices['serviceCatalogoConjuntosComerciales'].type, 
    data => {
      if (data[0].operation) {
        const arrAsent = data[0].datos
        const opcSelected =document.getElementById('tipo_E19') 
        let opt = document.createElement('option')
        opt.appendChild(document.createTextNode("Seleccione") )
        opt.value="Seleccion"
        opt.setAttribute('selected', true)
        opcSelected.appendChild(opt)
        arrAsent.forEach( (o, i) => {
          let opt = document.createElement('option')
          opt.appendChild( document.createTextNode(o.descripcion) )
          opt.value = o.tipo_e19  
          o.tipo_e19 === opcSelected.value ? opt.setAttribute('selected', true) : false
          opcSelected.appendChild(opt)
        })
      } else { }
    }, 
  '')
}

//Función que hace zoom con el extent al hacer la busqueda
const acercarWithExtent = data => {
  let res = data[0].datos.datos[0].extent.split(",") 
  MDM6("goCoords", parseInt(res[0], 10), parseInt(res[1], 10), parseInt(res[2], 10), parseInt(res[3], 10))
}

//Función que llama el servicio para obtener el código postal
const getCp=ce=>{
  sendAJAX(
    urlServices['serviceCP'].url, 
    { 'cve_ent': ce, 'proyecto':dataUserFromLoginLocalStorage.proyectoSesion}, 
    urlServices['serviceCP'].type, 
    data => {
      cpObj = data[0].datos
    }, 
    () => {}
  )
}

//Función que valida si los datos vienen correctos al hacer la busqueda
const showModalMsgError = data => {
  const dataE =  data[0].datos.datos.e
  let mensaje

  if (typeof dataE !== 'undefined') {
    arrayErrores.map( error => dataE === error.value ? mensaje = error.mensaje : false )
    const claveBusqueda = document.getElementById('clave-busqueda')
    claveBusqueda.classList.add('animated', 'shake', 'wrap-input-empty')
    claveBusqueda.addEventListener('animationend', () => claveBusqueda.classList.remove('animated', 'shake', 'wrap-input-empty') )
    Swal.fire
      ({
        position: 'bottom-end',
        type: 'warning',
        title: mensaje,
        showConfirmButton: false,
        timer: 2000
      })
  } else {
    handleShowRaticaHideSearch()
  }
}

// Función ver lista claves
const handleViewCleeList = () => {
  sendAJAX(
    urlServices['getListadoUnidadesEconomicas'].url, 
    {
      'proyecto': dataUserFromLoginLocalStorage.proyectoSesion, 
      'tramo': dataUserFromLoginLocalStorage.tramoControl, 
      'id_ue': dataUserFromLoginLocalStorage.ce,
    }, 
    urlServices['getListadoUnidadesEconomicas'].type, 
    data => { 
      dataCleeListNew = data[0]
      popupCleeList(data[0].datos)
    }, 
    () => {
      swal ({
        title: '<span style="width:100%;">Buscando información!</span>',
        text: 'Por favor espere un momento',
        timer: 2000,
        //html: true,
        onOpen: () => swal.showLoading()
      })
      .then(
        () => { },
        dismiss => {}
      )
    }
  )
}

const popupCleeList = data => {
  const notFoundClee = document.getElementById('wrap-list-not-found')
  if (data.length == 0){
    notFoundClee.classList.remove('wrap-inactive')
    notFoundClee.classList.add('animated', 'shake')
      // Swal.fire
      //       ({
      //               position: 'bottom-end',
      //               type: 'warning',
      //               title: 'No se encontraron claves disponibles para la coordinación estatal',
      //               showConfirmButton: false,
      //               timer: 2000
      //       })
    return
  }

  Swal.fire({
    title: '<strong style="width:100%">CLAVES DISPONIBLES</strong>',
    html: cleeList(data, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista),
    showCloseButton: true,
    showConfirmButton: false,
    showCancelButton: false,
    focusConfirm: false,
    allowEscapeKey: false,
    allowOutsideClick: false,
    onClose: () => {
      cleeListType = 'normal'
    }
  })
}

const popupCleeListBloqueadas = data => {
  const notFoundClee = document.getElementById('wrap-list-not-found-lock')
  if (data.length == 0){
    notFoundClee.classList.remove('wrap-inactive')
    return
  }

  Swal.fire({
    title: '<strong style="width:100%">CLAVES DISPONIBLES</strong>',
    html: cleeListLock(data, actualPaginaLock, inicioPaginacionLock, finPaginacionLock, inicioClavesVistaLock, finClavesVistaLock),
    showCloseButton: true,
    showConfirmButton: false,
    showCancelButton: false,
    focusConfirm: false,
    allowEscapeKey: false,
    allowOutsideClick: false,
    onClose: () => {
      cleeListType = 'normal'
    }
  })
}

const cleeList = (data, actualPagina, inicioPaginacion, finPaginacion, inicioClavesVista, finClavesVista) => {
  let tabla = ''
  const clavesPorVista = 10
  const totalClaves = data.length
  const totalPaginaciones = Math.ceil(totalClaves/clavesPorVista)
  let posicionFinal = ''
  finClavesVista > totalClaves ? posicionFinal = totalClaves - 1 : posicionFinal = finClavesVista

  tabla = `
    <div id='container-search-cleelist' class='container-search-cleelist'>
      <span class='text-search-cleelist'>Filtrar:</span>
      <div class="wrap-input-search-cleelist">
        <input type='text' id='search-cleelist' name='search-cleelist'  onkeypress="handleSearchCleeEnter(event)" />
      </div>
    </div>

    <div class='wrap-list items not-found wrap-inactive' id="wrap-list-not-found">
      <div class='item-lists'><span>No se encontraron claves disponibles para la Coordinación Estatal</span></div>
    </div>
    
    <div id='container-cleelist' class='container-cleelist row'>
      <div class='wrap-list'>
        <div class='title-column'>Clave</div>
        <div class='title-column'>Código</div>
      </div>`

      for(let num = inicioClavesVista; num <= posicionFinal ; num ++){
        let {idue, c154} = data[num]
        tabla += `<div class='wrap-list items'>
          <div class='item-list clave'><span onclick='callServiceFindUE(${idue})'>${idue}</span></div>
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
            
            if(pag == actualPagina){
              setTimeout( () => document.getElementById(`pag-${pag}`).classList.add('active'), 300 )
            }
          }
          tabla += `<li onclick='handlePaginationActive(${actualPagina}+1)' id="pagination-next" class="waves-effect"><a><i class="material-icons">chevron_right</i></a></li>
        </ul>`
      
    tabla +=`</div>`

  return tabla
}

const cleeListLock = (data, actualPaginaLock, inicioPaginacionLock, finPaginacionLock, inicioClavesVistaLock, finClavesVistaLock) => {
  let tabla = ''
  const clavesPorVista = 10
  const totalClaves = data.length
  const totalPaginaciones = Math.ceil(totalClaves/clavesPorVista)
  let posicionFinal = ''
  finClavesVistaLock >= totalClaves ? posicionFinal = totalClaves - 1 : posicionFinal = finClavesVistaLock

  tabla = `
    <div id='container-search-cleelist-lock' class='container-search-cleelist'>
      <span class='text-search-cleelist'>Filtrar:</span>
      <div class="wrap-input-search-cleelist">
        <input type='text' id='search-cleelist-lock' name='search-cleelist-lock'  onkeypress="handleSearchCleeEnterLock(event)" />
      </div>
    </div>

    <div class='wrap-list-Lock items not-found wrap-inactive' id="wrap-list-not-found">
      <div class='item-lists'><span></span>NO SE ENCONTRARON REFERENCIAS</div>
    </div>
    
    <div id='container-cleelist-lock' class='container-cleelist-Lock row'>
      <div class='wrap-list-Lock'>
        <div class='title-column'>Clee_est</div>
        <div class='title-column'>Usuario</div>
        <div class='title-column'>Tiempo bloqueado</div>
      </div>`

      for(let num = inicioClavesVistaLock; num <= posicionFinal ; num ++){
        let {idue, c154,time_LOCK} = data[num]
        tabla += `<div class='wrap-list-Lock items'>
          <div class='item-list-Lock clave'><span onclick='Desbloquear(${idue})'>${idue}</span></div>
          <div class='item-list-Lock'><span>${c154}</span></div>
          <div class='item-list-Lock'><span>${time_LOCK}</span></div>
        </div>`
      }

      tabla += `
        <ul class="pagination" id="pagination-clee-lock">
          <li onclick='handlePaginationActiveLock(${actualPaginaLock}-1)' id="pagination-back-lock" class="waves-effect">
            <a><i class="material-icons">chevron_left</i></a>
          </li>`
          actualPaginaLock == 1 ? setTimeout( () => document.getElementById('pagination-back-lock').classList.add('disabled'), 300 ) : false
          actualPaginaLock == totalPaginaciones ? setTimeout( () => document.getElementById('pagination-next-lock').classList.add('disabled'), 300 ) : false
          for(let pag = inicioPaginacionLock; pag<=finPaginacionLock; pag++){
            tabla+= `<li onclick='handlePaginationActiveLock(${pag}, ${totalPaginaciones})' class='waves-effect' id='pag-${pag}'><a>${pag}</a></li>`
            
            if(pag == actualPaginaLock){
              setTimeout( () => document.getElementById(`pag-${pag}`).classList.add('active'), 300 )
            }
          }
          tabla += `<li onclick='handlePaginationActiveLock(${actualPaginaLock}+1)' id="pagination-next-lock" class="waves-effect"><a><i class="material-icons">chevron_right</i></a></li>
        </ul>`
      
    tabla +=`</div>`

  return tabla
}


const handlePaginationActive = (page, totalPag) => {
  if (page > actualPagina || page < actualPagina){
    inicioClavesVista = (page -1) * 10
    finClavesVista = inicioClavesVista + 9
  } else if(page == actualPagina) {
    inicioClavesVista = inicioClavesVista
    finClavesVista = finClavesVista
  }

  if (page == finPaginacion) {
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
  if(cleeListType == 'normal'){
    popupCleeList(dataCleeListNew.datos)
  } else if (cleeListType == 'busqueda'){
    popupCleeList(dataResultSearchClee.datos)
  }

}


const handlePaginationActiveLock = (page, totalPagLock) => {
  if (page > actualPaginaLock || page < actualPaginaLock){
    inicioClavesVista = (page -1) * 10
    finClavesVista = inicioClavesVista + 9
  } else if(page == actualPaginaLock) {
    inicioClavesVista = inicioClavesVista
    finClavesVista = finClavesVista
  }

  if (page == finPaginacion) {
    if(screen.width <= '480'){
      inicioPaginacionLock = inicioPaginacionLock + 3
      finPaginacionLock = finPaginacionLock + 3
    } else {
      inicioPaginacionLock = inicioPaginacionLock + 5
      finPaginacionLock = finPaginacionLock + 5
    }
  } else if( page == inicioPaginacionLock) {
    if (page !== 1){
      if(screen.width <= '480'){
        inicioPaginacionLock = inicioPaginacionLock - 3
        finPaginacionLock = finPaginacionLock - 3
      } else {
        inicioPaginacionLock = inicioPaginacionLock - 5
        finPaginacionLock = finPaginacionLock - 5
      }
    }
  }
  
  if( inicioPaginacionLock < 1 ){
    inicioPaginacionLock = 1
    screen.width <= '480' 
      ? finPaginacionLock = inicioPaginacionLock +  (totalPagLock <= 4 ? totalPagLock - 1 : 4)
      : finPaginacionLock = inicioPaginacionLock +  (totalPagLock <= 6 ? totalPagLock - 1 : 6)
  }

  if( finPaginacionLock > totalPagLock ){
    finPaginacionLock = totalPagLock
    screen.width <= '480' 
      ? inicioPaginacionLock = finPaginacionLock - (totalPagLock <= 3 ? totalPagLock - 1 : 3)
      : inicioPaginacionLock = finPaginacionLock - (totalPagLock <= 5 ? totalPagLock - 1 : 5)
  }

  actualPaginaLock = page
  if(cleeListType == 'normal'){
    popupCleeListBloqueadas(dataCleeListNewLock.datos)
  } else if (cleeListType == 'busqueda'){
    popupCleeListBloqueadas(dataResultSearchCleeLock.datos)
  }
}

const handleSearchCleeEnter = e =>  {
  const key = window.event ? e.which : e.keyCode
  key < 48 || key > 57 ? e.preventDefault() : false

  tecla = (document.all) ? e.keyCode : e.which;
  tecla == 13 ? handleSearchCleeList(e) : false
}

const handleSearchCleeEnterLock = e =>  {
  const key = window.event ? e.which : e.keyCode
  key < 48 || key > 57 ? e.preventDefault() : false

  tecla = (document.all) ? e.keyCode : e.which;
  tecla == 13 ? handleSearchCleeListLock(e) : false
}

const handleSearchCleeList = () => {
  const inputValue = document.getElementById('search-cleelist')
  const arrayCleeFind = []
  const data = dataCleeListNew.datos
  
  if (inputValue.value == ''){
    actualPagina = 1
    inicioPaginacion = 1
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

const handleSearchCleeListLock = () => {
  const inputValue = document.getElementById('search-cleelist-lock')
  const arrayCleeFind = []
  const data = dataCleeListNewLock.datos
  
  if (inputValue.value == ''){
    actualPaginaLock = 1
    inicioPaginacionLock = 1
    finPaginacionLock = screen.width <= '480' ? 5 : 7
    inicioClavesVistaLock = 0
    finClavesVistaLock = 9
    cleeListType = 'normal'
    CargaTablaBloqueadas()
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

    actualPaginaLock = 1
    inicioPaginacionLock = 1
    finPaginacionLock = paginacionesAvanzar
    inicioClavesVista = 0
    result.length > 10 ? finClavesVista = 9 : finClavesVista = result.length - 1
  
    cleeListType = 'busqueda'
    dataResultSearchCleeLock.datos = result
    popupCleeListBloqueadas(dataResultSearchCleeLock.datos)
  
  }

}

// Función ratificar
const ratificar = request => {
  handleVisibleRatifica()
  if (request == 'si') {
    enabledInputs()
    handleActionTargetRef()
    handleActionButtons('enabled')
    MDM6('addMarker', {lon: parseFloat(xycoorsx), lat: parseFloat(xycoorsy), type: 'identify', params: {nom: '', desc: xycoorsx + ", " + xycoorsy}})
    handlePunteo(xycoorsx, xycoorsy, 'mercator', 'r')
    bandera_ratificar=true
  }
  else if(request=='no') {
    funcionesNoRatificado()
  } 
}

const funcionesNoRatificado = () => {
  handleShowAlertPickMap()
  enabledInputs()
  handleActionTargetRef()
  xycoorsx = ''
  xycoorsy = ''
  MDM6('hideMarkers', 'identify')      
  const cancelOption = document.getElementById('item-cancel-option')
  cancelOption.removeAttribute('disabled')
}

//función que activa nuevamente funciones para abrir contenedor de busqueda
const handleActiveVisibleSearch = () => {
  const viewSearchContainer = document.getElementById('arrow-search')
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  viewSearchContainer.setAttribute('onclick', 'handleVisibleSearch()')
  tituloBusqueda.setAttribute('onclick', 'handleVisibleSearch()')
}

//Funcion que lleva a cabo el punteo del establecimient
const handlePunteo=(x,y,tc,r)=>{
    xycoorsx=''
    xycoorsy=''
    let id_ue=document.getElementById('id_UE').value
    let ce=dataUserFromLoginLocalStorage.ce
    let tr=dataUserFromLoginLocalStorage.tramo_control
    let u=dataUserFromLoginLocalStorage.nombre
    callServicePunteo(x,y,tc,r,id_ue,ce,tr,u)
}

//Función que llama al servicio para el punteo de unidades economicas

const callServicePunteo = (x, y, tc, r, id_ue, ce, tr, u) => {
     bandera=true;
  //showalertpunteoloading();
  sendAJAX(urlServices['serviceIdentify'].url, 
  {
    'proyecto':dataUserFromLoginLocalStorage.proyectoSesion,
    'x': x, 
    'y': y, 
    'tc': tc, 
    'r': isAlta, 
    'ce': ce, 
    'id_ue': id_ue
  }, urlServices['serviceIdentify'].type,  data => {
    
    
    if (data[0].operation) {
        bandera=false;
        if( data[0].datos.datos != null)
        {
            const {catVial} = data[0].datos.datos
            catalogoCatVial = catVial
        }
       // showalertpunteoloading();
      if (typeof data[0].datos.mensaje.messages === 'undefined' || data[0].datos.mensaje.messages === null ) {
        confirmacionPunteo = false
        actualizaForm(data[0].datos.datos)
        agregaFuncionEliminarDuplicadosSelects()
        handleTipoPunteo()
      }
      else {
        if (typeof data[0].datos.mensaje.type !== 'undefined') {
          if (data[0].datos.mensaje.type === 'confirmar') {
              
            showAlertPunteoConfirma(data[0].datos.datos,'Condiciones insuficientes de punteo', data[0].datos.mensaje.messages)
          }
          else {
            if (data[0].datos.mensaje.type === 'error') {
              showAlertPunteo('Condiciones insuficientes de punteo', data[0].datos.mensaje.messages)
              MDM6('hideMarkers', 'identify')
              xycoorsx = ''
              xycoorsy = '' 
            }
            else {
              showAlertPunteo('Condiciones insuficientes de punteo', data[0].datos.mensaje.messages)
              MDM6('hideMarkers', 'identify')
              xycoorsx = ''
              xycoorsy = ''             
            }
          }
        }
      }
    }
    else {
      MDM6('hideMarkers', 'identify')
      showAlertPunteo(`Punteo no realizado ${data[0].messages}`)    
    }     
        
  }, () => {
    swal ({
      title: '<span style="width:100%;">Buscando información de punteo!</span>',
      text: 'Por favor espere un momento',
      timer: 4000,
      onOpen: () => swal.showLoading()
    })
    .then(
      () => { },
       dismiss => {
           
      }
    )
        
  })
}

const showalertpunteoloading = (bandera) => 
{
    if(bandera==true){
        alert("espere un momento porfavor");
    }
    else{
        alert("punteo realizado");
    }
    
//    swal 
//    ({
//      title: '<span style="width:100%;">Buscando información de punteo!</span>',
//      text: 'Por favor espere un momento',
//    })
}

const agregaFuncionEliminarDuplicadosSelects = () => {
  idEleToSelect.map( id => {
    const idElement = document.getElementById(id)
    idElement.setAttribute('onchange', 'eliminaDuplicados(this)')
    idElement.removeAttribute('disabled')
  })
}

const eliminaFuncionEliminiarDuplicadosSelects = () => {
  idEleToSelect.map( id => {
    const idElement = document.getElementById(id)
    idElement.removeAttribute('onchange')
    idElement.removeAttribute('disabled')
  })
}

const handleTipoPunteo = () => {
  const wrapTipoVialidad = document.getElementById('wrap-tipo-vialidad')
  const wrapTipoVialidadUno = document.getElementById('wrap-tipo-vialidad-uno')
  const wrapNombreVialidadUno = document.getElementById('wrap-nombre-vialidad-uno')
  const wrapTipoVialidadDos = document.getElementById('wrap-tipo-vialidad-dos')
  const wrapNombreVialidadDos = document.getElementById('wrap-nombre-vialidad-dos')
  const wrapTipoVialidadPosterior = document.getElementById('wrap-tipo-vialidad-posterior')
  const wrapNombreVialidadPosterior = document.getElementById('wrap-nombre-vialidad-posterior')
  const tipoE10n = document.getElementById('tipo_e10n') //input
  const tipoE10an = document.getElementById('tipo_e10_an') //input
  const e10A = document.getElementById('e10_A') // select
  const tipoE10bn = document.getElementById('tipo_e10_bn') //input
  const e10B = document.getElementById('e10_B') // select
  const tipoE10cn = document.getElementById('tipo_e10_cn') //input
  const e10C = document.getElementById('e10_C') // select

  if(punteo === 'R' || ( punteo === 'U' && confirmacionPunteo )){
    if(fieldExists === false){
      tipoE10n.style.display = 'none'
      tipoE10n.removeAttribute('id')
      tipoE10an.style.display = 'none'
      tipoE10an.removeAttribute('id')
      e10A.style.display = 'none'
      e10A.removeAttribute('id')
      tipoE10bn.style.display = 'none'
      tipoE10bn.removeAttribute('id')
      e10B.style.display = 'none'
      e10B.removeAttribute('id')
      tipoE10cn.style.display = 'none'
      tipoE10cn.removeAttribute('id')
      e10C.style.display = 'none'
      e10C.removeAttribute('id')
  
      const selectField = document.createElement('select')
      handleAttributesInputOrSelect('select', selectField, 'tipo_e10n')
      const selectFieldTipoE10an = document.createElement('select')
      handleAttributesInputOrSelect('select', selectFieldTipoE10an, 'tipo_e10_an')
      const inputFieldE10a = document.createElement('input')
      handleAttributesInputOrSelect('input', inputFieldE10a, 'e10_A', 'Nombre de la vialidad 1')
      const selectFieldTipoE10bn = document.createElement('select')
      handleAttributesInputOrSelect('select', selectFieldTipoE10bn, 'tipo_e10_bn')
      const inputFieldE10b = document.createElement('input')
      handleAttributesInputOrSelect('input', inputFieldE10b, 'e10_B', 'Nombre de la vialidad 2')
      const selectFieldTipoE10cn = document.createElement('select')
      handleAttributesInputOrSelect('select', selectFieldTipoE10cn, 'tipo_e10_cn')
      const inputFieldE10c = document.createElement('input')
      handleAttributesInputOrSelect('input', inputFieldE10c, 'e10_C', 'Nombre de la vialidad Posterior')
  
      //función donde se agrega options a los selects con el catálogo de tipo de vialidades
      handleFillTipoDeVialidades(selectField)
      handleFillTipoDeVialidades(selectFieldTipoE10an)
      handleFillTipoDeVialidades(selectFieldTipoE10bn)
      handleFillTipoDeVialidades(selectFieldTipoE10cn)
   
      wrapTipoVialidad.appendChild(selectField)
      wrapTipoVialidadUno.appendChild(selectFieldTipoE10an)
      wrapNombreVialidadUno.appendChild(inputFieldE10a)
      wrapTipoVialidadDos.appendChild(selectFieldTipoE10bn)
      wrapNombreVialidadDos.appendChild(inputFieldE10b)
      wrapTipoVialidadPosterior.appendChild(selectFieldTipoE10cn)
      wrapNombreVialidadPosterior.appendChild(inputFieldE10c)
      fieldExists = true
    }

  } else if (punteo === 'U' && !confirmacionPunteo){
    //tipo vialidad domicilio
    handleReturnTipoNombreVialidad(wrapTipoVialidad.children, wrapTipoVialidad, 'tipo_e10n', 'tipo')
    //tipo vialidad 1
    handleReturnTipoNombreVialidad(wrapTipoVialidadUno.children, wrapTipoVialidadUno, 'tipo_e10_an', 'tipo') 
    //nombre vialidad 1
    handleReturnTipoNombreVialidad(wrapNombreVialidadUno.children, wrapNombreVialidadUno, 'e10_A', 'nombre')
    //tipo vialidad 2
    handleReturnTipoNombreVialidad(wrapTipoVialidadDos.children, wrapTipoVialidadDos, 'tipo_e10_bn', 'tipo')
    //nombre vialidad 2
    handleReturnTipoNombreVialidad(wrapNombreVialidadDos.children, wrapNombreVialidadDos, 'e10_B', 'nombre')
    //tipo vialidad posterior
    handleReturnTipoNombreVialidad(wrapTipoVialidadPosterior.children, wrapTipoVialidadPosterior, 'tipo_e10_cn', 'tipo')
    //nombre vialidad 2
    handleReturnTipoNombreVialidad(wrapNombreVialidadPosterior.children, wrapNombreVialidadPosterior, 'e10_C', 'nombre')

    fieldExists = false
  }   
}

//Función crear Input o Select según si es rural
const handleAttributesInputOrSelect = (type, constName, idField, ph='') =>{
  if (type === 'select'){
    constName.setAttribute('id', idField)
    constName.setAttribute('onchange', `asignaValorId(${idField})`)
    constName.classList.add('browser-default')
  }
  else if (type === 'input'){
    constName.setAttribute('id', idField)
    constName.setAttribute('placeholder', ph)
    constName.setAttribute('name', idField)
    constName.setAttribute('type', 'text')
  }
}

//función llenado de catálogo con opciones de tipo de vialidad cuando es rural
const handleFillTipoDeVialidades = selectId => {
    //selectId.setAttribute('onchange', 'asignaValorId()')
  catalogoCatVial.map( item =>{
    let opt = document.createElement('option')
    opt.appendChild( document.createTextNode(item.tipo_e10n) )
    opt.value = item.tipo_e10
    selectId.appendChild(opt)
  })
}

//Función regresa tipo campos  de tipo y nombre vialidad
const handleReturnTipoNombreVialidad = (childrens, wrap, idChildren, field) => {
  for(let chld = 0; chld< childrens.length; chld++){
    let child = childrens[chld]
    let childrenType = childrens[chld].nodeName

    if (field == 'tipo'){
      if(childrenType == 'SELECT'){
        wrap.removeChild(child)
      }
      if(childrenType == 'INPUT'){
        child.style.display = 'initial'
        child.setAttribute('id',idChildren)
        //child.setAttribute('disabled','true')
      }
    }
    else if (field == 'nombre') {
      if(childrenType == 'INPUT'){
        wrap.removeChild(child)
      }
      if(childrenType == 'SELECT'){
        child.style.display = 'initial'
        child.setAttribute('id',idChildren)
      }
    }
  }
}

const asignaValorId = item => {
    const campoTipoE10n = document.getElementById('tipo_e10n')
    const campoTipoE10an = document.getElementById('tipo_e10_an')
    const campoTipoE10bn = document.getElementById('tipo_e10_bn')
    const campoTipoE10cn = document.getElementById('tipo_e10_cn')
    // hiddens
    const campoTipoE10 = document.getElementById('tipo_e10')
    const campoTipoE10a = document.getElementById('tipo_e10_a')
    const campoTipoE10b = document.getElementById('tipo_e10_b')
    const campoTipoE10c = document.getElementById('tipo_e10_c')

    if(item[1].id === 'tipo_e10n'){
      campoTipoE10.value = campoTipoE10n.value
    } else if (item[1].id === 'tipo_e10_an'){
      campoTipoE10a.value = campoTipoE10an.value
    } else if (item[1].id === 'tipo_e10_bn'){
      campoTipoE10b.value = campoTipoE10bn.value
    } else if (item[1].id === 'tipo_e10_cn'){
      campoTipoE10c.value = campoTipoE10cn.value
    }
}

// función sweetaler errores punteo
const showAlertPunteo = (title, text) =>{
  swal.fire ({
    title,
    text,
    type: 'error',
    showCloseButton: true,
    showConfirmButton: false,
    customClass: 'swal-wide',
  }) 
}

// función sweetaler confirma punteo
const showAlertPunteoConfirma = (data, title, text) =>{
  swal.fire ({
    title,
    text,
    //type: 'error',
    showCloseButton: false,
    showConfirmButton: true,
    confirmButtonColor: '#5562eb',
    confirmButtonText: 'Confirmar',
    showCancelButton: true,
    cancelButtonColor: '#424242',
    cancelButtonText: 'Cancelar',
    allowEscapeKey: false,
    allowOutsideClick: false,
    customClass: 'swal-wide',
  }).then ( result => {
    if (result.value){
      actualizaForm(data)
      confirmacionPunteo = true
      handleTipoPunteo()
    } else if (result.dismiss == 'cancel'){
      confirmacionPunteo = false
      handleTipoPunteo()
      funcionesNoRatificado()
    }
  }) 
}

//Funcion que actualiza el formulario al hacer el punteo
let infodenue
const actualizaForm = data => {
  punteo = data.punteo
  mod_cat = data.mod_cat
  cve_geo = data.cvegeo
  cve_geo2016 = data.cvegeo2016
  cveft = data.cveft
  e10_cve_vial = data.e10_cvevial
    
  //inicializa entrevialidades
  if(typeof data.e10_X!=='undefined'){
    infodenue = true
    let node,newnode,oldnew;
    //si traigo entrevialidades
    let idEleToInput = ['tipo_e10n', 'e10', 'tipo_e10_an', 'tipo_e10_bn', 'tipo_e10_cn']
    idEleToInput.forEach( function (o, i) {
        //$('#' + o).replaceWith('<input id="' + o + '" name="' + o + '" type="text" disabled>');
    });
    // var idEleToSelect = ['e10_A', 'e10_B', 'e10_C']
    // idEleToSelect.forEach( function (o, i) {
    //   var html = '<option value="Seleccione">Seleccione</option>'
    //   $('#' + o).replaceWith('<select id="' + o + '" name="' + o + '" class="browser-default" onchange="eliminaDuplicados(this)"></select>')
    //   $('#' + o).html(html)
    // });
  }
  else {
    infodenue = false
    var idTipo_e10_xn = ['tipo_e10n', 'tipo_e10_an', 'tipo_e10_bn', 'tipo_e10_cn']
    var e10x = ['e10', 'e10_A', 'e10_B', 'e10_C']
    idTipo_e10_xn.forEach(function (o, i) {
      $('#' + o).replaceWith('<select id="' + o + '" name="' + o + '" class="browser-default" onchange="asignaTipoVial(this)"><option value="Seleccione">Seleccione</option></select>');
    })
    e10x.forEach( function (o, i) {
        $('#' + o).replaceWith('<input id="' + o + '" name="' + o + '" type="text" >')
    });
  }

  var arrValid = ['e03', 'e04', 'e05', 'e06']
  arrValid.push('e07')
  var success = true
  arrValid.forEach(function (o, i) {
    if (data[o] !== '' || typeof data[o] !== '') {
      success = success & true
    } else {
      success = success & false
    }
  });

  if(!success) {
    swal.fire({
      title: 'La información geoestadística esta incompleta.',
      text: ' Favor de realizar una vez mas el punteo.',
      showConfirmButton: true,
      confirmButtonColor: "#5562eb",
      allowEscapeKey: true,
      allowOutsideClick: true,
      html: true,
      animation: true
    });
  }
  xycoorsx = data.coord_x
  xycoorsy = data.coord_y
  MDM6('hideMarkers', 'identify')
  MDM6('addMarker', {lon: data.coord_x, lat: data.coord_y, type: 'identify', params: {nom: 'Nueva Ubicación', desc: ''}})
  isChange = true
    
  for (var entry in data) {
    if (entry == 'e10_X') {
      var arrData = data[entry]
      var html = '<option data-tipo="" data-tipon="" data-cvevial="" data-cveseg="" value="Seleccione">Seleccione</option>'
      calles = []
      objCalles = []
      if (arrData) {
        arrData.forEach(function (o, i) {
          objCalles.push(o)
          calles.push(o.e10_X_cvevial)
          html += '<option data-tipo="' + o.tipo_e10_X + '" data-tipon="' + o.tipo_e10_Xn + '" data-cvevial="' + o.e10_X_cvevial + '"  value="' + o.e10_X + '">' + o.e10_X + '</option>';
        });
        $('#e10_A').html(html)
        $('#e10_B').html(html)
        if (arrData.length > 2) {
          $('#e10_C').html(html)
          $('#e10_C').attr('disabled', false)
        } else {
          $('#e10_C').attr('disabled', true)
        }
      }
    }
    else {
      if (entry == 'catVial') {
        var arrData = data[entry]
        var html = ''
        if(arrData!=null) {
          arrData.forEach(function (o, i) {
            html += '<option data-tipo="' + o.tipo_e10 + '" value="' + o.tipo_e10n + '">' + o.tipo_e10n + '</option>'
          });
          var idElemAppend = ['tipo_e10n', 'tipo_e10_an', 'tipo_e10_bn', 'tipo_e10_cn']
          idElemAppend.forEach(function (o, i) {
            $('#' + o).append(html)
          });
        }
      }
      else {
        if (entry === 'e05') {
          if (data[entry] === '') {
            $("#e05n").attr('disabled', false).removeAttr('readonly')
            $(".msj-punteo").html("Capture la localidad").show()
          }
          $('#' + entry).val(data[entry])
        }
        else {
          $('#' + entry).val(data[entry])
        }
      }
    }
  }
     
}

//Asigna Tipo_Vial
var asignaTipoVial = function (e) {
  var optionSelected = $("option:selected", e)
  var tipo_e10 = $(optionSelected).attr('data-tipo')
  if (e.id === 'tipo_e10n') {
    //limpia campos de pestaña Datos Vialidad
    $("#tipo_administracion").prop('disabled', true).val(null)
    $("#derecho_transito").prop('disabled', true).val(null)
    $("#codigo_carretera").prop('disabled', true).val(null)
    $("#tramo_camino").prop('disabled', true).val(null)
    $("#cadenamiento").prop('disabled', true).val(null)
    $("#margen").prop('disabled', true).val(null)
    $('#tipo_e10').val(tipo_e10)
      
    if (tipo_e10 === '22')
      $("#e10").val('Ninguno')

  } else if (e.id === 'tipo_e10_an') {
    $('#tipo_e10_a').val(tipo_e10)
    if (tipo_e10 === '22')
      $("#e10_a").val('Ninguno')
  } else if (e.id === 'tipo_e10_bn') {
    $('#tipo_e10_b').val(tipo_e10)
    if (tipo_e10 === '22')
      $("#e10_b").val('Ninguno')
  } else if (e.id === 'tipo_e10_cn') {
    $('#tipo_e10_c').val(tipo_e10)
    if (tipo_e10 === '22')
      $("#e10_c").val('Ninguno')
  }
}

//Funcion elimina duplicados

const eliminaDuplicados=(cmb)=> {
    var optionSelected = $("option:selected", cmb);
    var cveseg = $(optionSelected).attr('data-cveseg');
    var cvevial = $(optionSelected).attr('data-cvevial');
    var tipo_e10 = $(optionSelected).attr('data-tipo');
    var tipo_e10n = $(optionSelected).attr('data-tipon');
    var e10 = $(optionSelected).val();
    var cmbs = ["e10_A", "e10_B", "e10_C"];
    $.each(cmbs, function (i, cm) {

        if (cm === cmb.id || e10.toLowerCase() === 'sin referencia' || e10.toLowerCase() === 'ninguno') {
            //$("#" + cm + " option[value='Seleccione']").remove();
            $("#" + cm + "n").val('Ninguno');
        } else if (cvevial !== '') {
            $("#" + cm + " option[data-cvevial='" + cvevial + "']").remove();
            //$("#" + cm + " option[data-cvevial='" + cvevial + "'][data-cveseg='" + cveseg + "']").remove();
        }
    });
    if (cmb.id === 'e10_A') {
        $('#tipo_e10_a').val(tipo_e10);
        $('#e10a_cvevial').val(cvevial);
        $('#tipo_e10_an').val(tipo_e10n);
        $('#e10a_cveseg').val(cveseg);
    } else if (cmb.id === 'e10_B') {
        $('#tipo_e10_b').val(tipo_e10);
        $('#e10b_cvevial').val(cvevial);
        $('#tipo_e10_bn').val(tipo_e10n);
        $('#e10b_cveseg').val(cveseg);
    } else if (cmb.id === 'e10_C') {
        $('#tipo_e10_c').val(tipo_e10);
        $('#e10c_cvevial').val(cvevial);
        $('#tipo_e10_cn').val(tipo_e10n);
        $('#e10c_cveseg').val(cveseg);
    }

    addLiberados();
}

const addLiberados=()=> {
    var cmbs = ["e10_A", "e10_B", "e10_C"];
    var ocupados = [];
    $.each(cmbs, function (i, cm) {
        if ($("#" + cm).val() !== "Seleccione") {
            //var opcSel = $('#' + cm + ' option:selected').attr('data-cvevial') + '|' + $('#' + cm + ' option:selected').attr('data-cveseg');
            var opcSel = $('#' + cm + ' option:selected').attr('data-cvevial');
            ocupados.push(opcSel);
        }
    });
    var libres = $(calles).not(ocupados).get();
    $.each(libres, function (i, libre) {
        var l = libre.split("|");
        $.each(cmbs, function (i, cm) {
            // reviso si la opcion libre ya esta en el combo
            //if ($("#" + cm + " option[data-cvevial='" + l[0] + "'][data-cveseg='" + l[1] + "']").length === 0) {
            if ($("#" + cm + " option[data-cvevial='" + libre + "']").length === 0) {
                //no existe el elemento y hay que agregarlo
                objCalles.forEach(function (o, i) {
                    if (libre === o.e10_X_cvevial) {
                        var html = '<option data-tipo="' + o.tipo_e10_X + '" data-tipon="' + o.tipo_e10_Xn + '" data-cvevial="' + o.e10_X_cvevial + '" value="' + o.e10_X + '">' + o.e10_X + '</option>';
                        $("#" + cm).append(html);
                    }
                });
            }
        });
    });
}

const validations=(totalInputs,object,campo)=>{
   let inputsInfo = 0 
   let msgInputEmpty;
    for (let input = 0; input < totalInputs; input++) {
    const { id, name, title, key } = object[input]
    const element = document.getElementById(id)
    const wrapTitle = document.getElementById(title)
    let visible = wrapTitle.dataset.visible

    !inputsByWrap[key] ? inputsByWrap[key] = true : false

    if (element.value == '' || element.value=='Seleccione') 
    {
      element.style.borderColor = 'red'
      element.classList.add('animated', 'shake')
      visible == 'hide' ? handleVisibleForm(key) : false
      inputsEmpty = true
      containerInputsVisible = false
      if(campo!=undefined)
      {
           msgInputEmpty = `si no existe ${name} no debe existir ${campo}`
      }
      else
      {
          msgInputEmpty = `Favor de completar la información del campo ${name}` 
      }
      
      alertToastForm(msgInputEmpty, 'error')
      inputsByWrap[key] = false
      setTimeout(() => element.classList.remove('animated', 'shake'), 1000)

      wrapTitle.id == title &&
        wrapTitle.classList.add('error')
      element.focus()
      break
    } else 
    {
      element.style.borderColor = '#eeeeee'
      containerInputsVisible = true
      inputsInfo++

      wrapTitle.id == title &&
        wrapTitle.classList.remove('error')
    }

  }
  let objNameWraps = Object.keys(inputsByWrap)

  for (let wrap = 0; wrap < objNameWraps.length; wrap++) {
    let wrapKey = objNameWraps[wrap]
    if (inputsByWrap[wrapKey]) {
      const wrapTitle = document.getElementById(`title-${wrapKey}`)
      let visible = wrapTitle.dataset.visible
      visible == 'show' ? handleVisibleForm(wrapKey) : false
    }
  }

  inputsInfo == totalInputs && validaCp()
}

// Función validación de formulario campos vacios
const handleFormValidations = () => {
  let totalInputs
  let numero_ext=document.getElementById('e11').value;
  
  if(punteo=='U' && mod_cat=='1') {
    totalInputs = objForm.length
    validations(totalInputs,objForm)
  }else{
    if(punteo=='U' && mod_cat=='2') {
      if (validaEdificio()) {
          totalInputs = objFormCentrocomercial.length
          validations(totalInputs,objFormCentrocomercial,campo)
          validations(totalInputs,objForm2)
      }else{
          totalInputs = objForm2.length
          validations(totalInputs,objForm2)
      }
    }
  else {
    if(punteo=='R' && mod_cat=='1') {
      validaCp()
    }
    else {
      if(punteo=='R' && mod_cat=='2') {
        if (validaEdificio()) {
            
          totalInputs = objFormCentrocomercial.length
          validations(totalInputs,objFormCentrocomercial,campo)
          validations(totalInputs,objFormRural)
        }
        else {
            if(numero_ext<=0){
                document.getElementById('e11').value="";
            }
          totalInputs = objFormRural.length
          validations(totalInputs,objFormRural)
        }  
      }
    }
  }
 }
}

var campo
const validaEdificio = () => {
  let bandera=0
  for (let input = 0; input < objFormCentrocomercial.length; input++) {
    const { id, name, title, key } = objFormCentrocomercial[input]
    const element = document.getElementById(id)
    const wrapTitle = document.getElementById(title)
    let visible = wrapTitle.dataset.visible

    !inputsByWrap[key] ? inputsByWrap[key] = true : false
    if(bandera>0) {
      break
    }
    else {
      if (element.value == '' || element.value=='0' || element.value=='Seleccion') {
        bandera=0
        element.style.borderColor = '#eeeeee'
      }
      else {
        campo=name
        bandera=1
      }
    }
  }
  
  if (bandera==1) {
    return true
  }
  else {
    return false
  }
}
                          
const handleFormValidationsRural = () => {}

const validaCp = () => {
  sendAJAX(urlServices['serviceValCP'].url, 
  {
    'codigo': $("#e14_A").val(),
    'cve_ent': $("#e03").val(),
    'proyecto':dataUserFromLoginLocalStorage.proyectoSesion
  }, 
  urlServices['serviceValCP'].type, 
  data => {
    if (data[0].operation) {
      if (data[0].datos.result === false) {
          
      }
      else {
        modalViewPreliminar()    
      }
    }
  }, () => { })
}

const modalViewPreliminar = () => {
  const myform = $('#frmSARE')
  let disabled = myform.find(':input:disabled').removeAttr('disabled')
  let d = myform.serialize()
  d += "&tramo_control=" + dataUserFromLoginLocalStorage.tramo_control
  d += "&coord_x=" + xycoorsx + "&coord_y=" + xycoorsy
  const htmlDiv = "<div id='vista'> </div>"
  const sizeScreen = screen.width <= '768' ? '90%' : '80%' 
        
  Swal.fire({
    title: '<h2 class="txt-preliminar">VISTA PRELIMINAR</h2>', 
    width: sizeScreen, 
    html: htmlDiv, 
    confirmButtonText: 'Aceptar', 
    customClass: 'swal-wide', 
    confirmButtonColor: '#0f0f0f', 
    allowEscapeKey: false, 
    allowOutsideClick: false, 
    showConfirmButton: true,
    showCancelButton: true,
    showCloseButton: true, 
    onOpen: showViewPreliminar(d) 
  }).then ( result => handleShowResult(result) )
}

const showViewPreliminar = d => {
  loadTemplate('vista', "resources/templates/preview.html?frm=" + Math.random(),
    () => {
      d = d.replace(/Seleccione/g, '')
      var dpv = d.split("&")
      var Type;
      $.each(dpv, function (i, e) {
        var idobj = e.split("=")
        if(idobj[0]!='tramo_control' && idobj[0]!='coord_x' && idobj[0]!='coord_y'){
            Type = document.getElementById(idobj[0]).type;
        }else{
            Type='text';
        }
        
        var a = decodeURIComponent(idobj[1])
        a = a.replace(/\+/g, ' ')
        if(idobj[0]=='id_UE' && isAlta){
            a="00"; //se inicializa en 00 para las altas y evitar que el truene el objeto en el servicio
        }
        if(Type=='select-one')
        {
            a=document.getElementById(idobj[0]).value
        }
            
            ObjectRequest[idobj[0]] = a
            $("#" + idobj[0] + "_pv").text(a)
        })
                
      ObjectRequest['Cvegeo2016'] = cve_geo2016
      ObjectRequest['Cvegeo'] = cve_geo
      ObjectRequest['CE'] = dataUserFromLoginLocalStorage.ce
      ObjectRequest['id_deftramo'] = dataUserFromLoginLocalStorage.tramoControl
      ObjectRequest['tramo_control'] = dataUserFromLoginLocalStorage.tramoControl
      ObjectRequest['mod_cat'] = mod_cat
      ObjectRequest['punteo'] = punteo
      ObjectRequest['coordx'] = xycoorsx
      ObjectRequest['coordy'] = xycoorsy
      ObjectRequest['coordx'] = xycoorsx
      ObjectRequest['coordy'] = xycoorsy
      ObjectRequest['Cveft'] = cveft
      ObjectRequest['e10_cvevial'] = e10_cve_vial
    }
  )
}

const handleShowResult = result => {
  const user = dataUserFromLoginLocalStorage.nombre
  const checkboxPuntearAlta = document.getElementById('puntear-alta')
  if (result.value) {
    sendAJAX(urlServices['serviceSaveUEAlter'].url, 
    {
      'proyecto':dataUserFromLoginLocalStorage.proyectoSesion,
      'obj': JSON.stringify(ObjectRequest),
      'usuario':user,
      'isAlta':isAlta
    }, 
    urlServices['serviceSaveUEAlter'].type, 
    data => {
      if (data[0].operation) {
        if (data[0].datos.mensaje.type === 'false') {
          handleShowSaveAlert('error', 'Error', data[0].datos.mensaje.messages, false)
          return;
        }
        else {
          cleanForm()
          MDM6('hideMarkers', 'identify')
          handleShowSaveAlert('success', 'Guardado', 'El punto ha sido almacenado correctamente', true)
          //handleActiveVisibleSearch()
          !checkboxPuntearAlta.checked ? handleActiveVisibleSearch() : false
          handleActionPunteoAlta('on')
        }
      }
      
      else {
        handleShowSaveAlert('error', 'Error', 'Error de conexión', true)
      }
    }, () => swal({
        title: 'Identificación de Unidades Económicas!',
        text: 'Por favor espere un momento',
        timer: 2000,
        onOpen: () => swal.showLoading() 
      }).then(
        () => { },
        (dismiss) => {
          if (dismiss === 'timer') {
          }
        }
      ) //handleShowSaveAlert('info', 'Guardando', 'Almacenando información, por favor espere un momento', true)
    )
        
  } //close if result.value
}

const handleShowSaveAlert = (type, title, text, animation) => {
  swal.fire({
    type,
    title,
    text,
    showConfirmButton: false,
    showCloseButton: true,
    allowEscapeKey: true,
    allowOutsideClick: true,
    // html: true,
    animation
  }) 
}

const identify = (coor) => HandleWhatDoYouWantToDo(coor)

// Función al seleccionar opciones identificar, puntear  y vista calle
const HandleWhatDoYouWantToDo = (coor) => {  
  let request = $('input:radio[name=accion]:checked').val();
  let level = MDM6('getZoomLevel');
  MDM6('updateSize');
  switch (request) {
    case 'identificar':
      if(level>=13)
      {
        identificaUE(coor.lon, coor.lat);
      }
      else{
        MDM6('hideMarkers', 'identify') 
        Swal.fire ({
          position: 'bottom-end',
          type: 'warning',
          title: 'Debe acercarse mas sobre el mapa para identificar una unidad economica',
          showConfirmButton: false,
          timer: 2000
        }) 
      }
      break
    case 'puntear':
      isAlta=false;
      let clee_est=document.getElementById('id_UE').value;
      if(clee_est!='' || clee_est==null)    
      {
        identificar(coor);
        handleActionButtons('enabled')
      }
      else{
        MDM6('hideMarkers', 'identify') 
        Swal.fire ({
          position: 'bottom-end',
          type: 'warning',
          title: 'Debe seleccionar una unidad economica a puntear',
          showConfirmButton: false,
          timer: 2000
        })
      }
      break
    case 'v_calle':
      if(level>=13) {
        StreetView(coor.lon, coor.lat)
      } else {
        MDM6('hideMarkers', 'identify') 
        Swal.fire ({
          position: 'bottom-end',
          type: 'warning',
          title: 'Debe acercarse mas sobre el mapa para usar la vista de calle',
          showConfirmButton: false,
          timer: 2000
        })
      }
      break
    case 'puntear_alta':
      isAlta=true
      if (level<=13) {
          showAlertIdentify('warning', `${14-level} acercamientos sobre mapa`, 'Realizalos para ubicar correctamente la unidad económica')
          MDM6('hideMarkers', 'identify')
        } else {
          //Lo deja puntear y agrega el punto
          enabledInputs()
          handleActionTargetRef()
          handleActionButtons('enabled')
          MDM6('hideMarkers', 'identify')
          MDM6('addMarker', {lon: parseFloat(coor.lon), lat: parseFloat(coor.lat), type: 'identify', params: {nom: 'Nueva ubicación', desc: coor.lon + ", " + coor.lat}});
          handlePunteo(coor.lon, coor.lat, 'mercator', 'n')
          handleHideAlertPickMap()
          fillCatalogo()
          fillCatalogoConjuntosComerciales()
        }
      break
  }
}

const radioSelect = option => {
  switch (option) {
    case 'identificar':
      alertToastForm('Identificar Activado', 'info')
      HandleActionsSaveNewPoint('no alta')
      break
    case 'puntear':
      alertToastForm('Puntear Activado', 'info')
      HandleActionsSaveNewPoint('no alta')
      break
    case 'calle':
      alertToastForm('Vista Calle Activado', 'info')
      HandleActionsSaveNewPoint('no alta')
      break
    case 'alta':
      alertToastForm('Puntear Alta Activado', 'info')
      HandleActionsSaveNewPoint('alta')
      break
    default:
      break
  }
}

const HandleActionsSaveNewPoint = option =>{
  const containerSearch = document.getElementById('container-search')
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  const arrowSearch = document.getElementById('arrow-search')
  let statusContainer = containerSearch.dataset.visible

  if(option === 'alta') {
    tituloBusqueda.removeAttribute('onclick')
    arrowSearch.removeAttribute('onclick')
    if(statusContainer === 'show') {
      handleVisibleSearch()
    }
  } else if (option === 'no alta') {
    if(!tituloBusqueda.getAttribute('onclick') && !arrowSearch.getAttribute('onclick')) {
      tituloBusqueda.setAttribute('onclick', 'handleVisibleSearch()')
      arrowSearch.setAttribute('onclick', 'handleVisibleSearch()')
    }
  }

}

const identificar = coor => {
  MDM6('hideMarkers', 'identify')
  let level = MDM6('getZoomLevel')
  const id_ue = document.getElementById('id_UE').value
  let visible = document.getElementById('container-ratifica').dataset.visible
     
  if(id_ue != '')
  {
    if(visible=='show')
    {
      showAlertIdentify('error', 'Debe definir si la posición es correcta o incorrecta en el formulario')
    } else {
      if(bandera_ratificar){
        showAlertIdentify('error', 'El punto ha sido ratificado y no se puede mover', 'si desea repuntear porfavor presione el botón con el simbolo X')
      } else {
        if (level<=13) {
          showAlertIdentify('warning', `${14-level} acercamientos sobre mapa`, 'Realizalos para ubicar correctamente la unidad económica')
          MDM6('addMarker', {lon: parseFloat(xycoorsx), lat: parseFloat(xycoorsy), type: 'identify', params: {nom: '', desc: xycoorsx + ", " + xycoorsy}});
        } else {
          //Lo deja puntear y agrega el punto
          MDM6('hideMarkers', 'identify')
          MDM6('addMarker', {lon: parseFloat(coor.lon), lat: parseFloat(coor.lat), type: 'identify', params: {nom: 'Nueva ubicación', desc: coor.lon + ", " + coor.lat}});
          handlePunteo(coor.lon, coor.lat, 'mercator', 'n')
          handleHideAlertPickMap()
        }
      }    
    }
  }
}

const showAlertIdentify = (type, title, text='') => {
  Swal.fire({
    type,
    title,
    text,
    position: 'bottom-end',
    showConfirmButton: false,
    timer: 2000
  })
}

//Funcion para inicializar la vista de calle
const StreetView=(x,y) => modalGoogleMap(x, y, 'mercator')

//modal que manda llamar la vista de calle
const modalGoogleMap = (x, y, tc) => {
  if (tc === 'mercator') {
    sendAJAX(urlServices['serviceIdentifyStreetView'].url,
      { 'proyecto': dataUserFromLoginLocalStorage.proyectoSesion, 'x': x, 'y': y},
      urlServices['serviceIdentifyStreetView'].type, 
      data => {
        if (data[0].operation) {
          MDM6('hideMarkers', 'identify')
          ubicacion =`${data[0].datos['y']} , ${ data[0].datos['x']}`
          let url = `http://maps.google.com/maps?q=&layer=c&cbll=${ubicacion}&cbp=`
          setTimeout(() => win = window.open(url, "_blank", "width=800,height=600,top=150,left=200"), 200)
        }
      },''
    )
  }
}

//Funcion para identificar la unidad economica y llamar el servicio
const identificaUE = (x,y) => {
  let capas = ($('#checkbox-denue').is(":checked")) ? 'DENUE,' : ''
  capas += ($('#checkbox-matrices').is(":checked")) ? 'Matrices,' : ''
  capas += ($('#checkbox-sucursal').is(":checked")) ? 'Sucursales,' : ''
  capas += ($('#checkbox-unicos').is(":checked")) ? 'Unicos,' : ''
  capas += ($('#checkbox-postes').is(":checked")) ? 'Postes,' : ''
  capas = capas.slice(0, -1)

  capas.length === 0 ? mostrarMensaje() : callServicioIdentificar(capas,x,y)
  }

//Funcion que muestra el sweetAlert
const mostrarMensaje = () => {
  swal.fire({
    title: 'Identificación de Unidades Económicas',
    text: 'Seleccione una capa de información',
    showCancelButton: true,
    showConfirmButton: false,
    allowEscapeKey: true,
    allowOutsideClick: true,
    html: true,
    animation: true
  })
  MDM6('hideMarkers', 'identify')
  xycoorsx = ''
  xycoorsy = ''
}

//Funcion que manda llamar el servicio para identificar las unidades económicas
const callServicioIdentificar = (capas, x, y) => {
  sendAJAX(urlServices['serviceIdentifyUE'].url,
    {
      'proyecto': dataUserFromLoginLocalStorage.proyectoSesion,
      'x': x,
      'y': y,
      'opciones': capas
    },
    urlServices['serviceIdentifyUE'].type, function (data) {
      if (data[0].operation) {
        if (data[0].datos.mensaje.messages === null) {
          MDM6('hideMarkers', 'identify')
          var dataToFrm = data[0].datos.datos
          modalShowInfoUE(dataToFrm, capas)
        } else {
          swal.close()
          $('#btnRatificaSi').attr('disabled', false)
          $('#btnRatificaNo').attr('disabled', false)
          swal.fire({
            type: 'error',
            title: 'Identificación de Unidades Económicas',
            text: data[0].datos.mensaje.messages,
            showCloseButton: true,
            showConfirmButton: false,
            confirmButtonColor: "#5562eb",
            allowEscapeKey: true,
            allowOutsideClick: true,
            animation: true
          })

          MDM6('hideMarkers', 'identify')
          xycoorsx = ''
          xycoorsy = ''
        }

      } else {

      }
    }, () => {
      swal({
        title: 'Identificación de Unidades Económicas!',
        text: 'Por favor espere un momento',
        timer: 5000,
        onOpen: () => swal.showLoading() 
      }).then(
        () => { },
        (dismiss) => {
          if (dismiss === 'timer') {
          }
        }
      )
    }
  )
}

//muestra mensaje con la tabla que contiene la información de las unidades economicas para el establecimiento seleccionado
const modalShowInfoUE = (rows, capas) => { 
  capas = capas.split(",") 
  const sizeScreen = screen.width <= '768' ? '90%' : '80%' 
 
  swal.fire({ 
    title: '<h3 class="title-modal-ue">Identificación de Unidades Económicas</h3>', 
    //type:'info', 
    width: sizeScreen, 
    html: '<div id="tabL"></div>', 
    confirmButtonText: 'Aceptarr', 
    customClass: 'swal-wide', 
    confirmButtonColor: '#0f0f0f', 
    allowEscapeKey: false, 
    allowOutsideClick: false, 
    showConfirmButton: false, 
    showCloseButton: true, 
    onOpen: cargaTemplateIdentificaUE(rows) 
  }) 
} 

//Funcion que arma el html con la información de la unidad económica seleccionada
const cargaTemplateIdentificaUE = rows => { 
  loadTemplate('tabL', "resources/templates/table_UE.html?frm=" + Math.random(), () => { 
     
    //interpreta la respuesta 
    rows.forEach(function (o, i) { 
      let html = '' 
 
      o.datos.forEach(function (ob, ix) { 
        const objDetalle = JSON.stringify(ob) 
        o.capa === 'eje'  
          ? html += `<tr> <td>${ob.tipovial}</td> <td>${ob.nomvial}</td> </tr>` 
          : html += `<tr class='row-cont-ue'>  
            <td>${ob.cve_unica}</td>  
            <td>${ob.nom_est}</td> 
            <td>${ob.razon_soc}</td> 
            <td> <a title='Detalle' onclick='buildDetalle(${objDetalle})'> <i class='material-icons icFicha'> assignment </a> </td> 
          </tr>` 
      }) 
 
      $('#tabUE_' + o.capa + ' tbody').html(html) 
      //añade el option al select 
      $('#slcapa').append($('<option>', {value: o.capa, text: o.capa, selected: true})) 
      $('#slcapa').show() 
      showUEficha(o.capa) 
    }) 
  }) 
} 

//función que oculta las tables que no solicito el usuario para mostrar en la opción identificar
const showUEficha = ficha => {
  //escondo las 2 fichas
  $("#tabUE_DENUE").hide()
  $("#tabUE_Matrices").hide()
  $("#tabUE_Sucursales").hide()
  $("#tabUE_detalle").hide()
  $("#tabUE_eje").hide()
  //enciendo la ficha que me dan
  $('#btnIdentificaRegresar').css('display', 'none')
  $("#tabUE_" + ficha).show()
}

// función que muestra el detalle de los elementos devueltos por el servicio que identifica contenidas en la ficha
const buildDetalle = ficha => {
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

  $(".modal-footer").append('<button type="button" class="pure-button" id="btn_regresar" onclick="showUEficha($(\'#slcapa\').val())">Regresar</button>')
  $("#tabUE_detalle").html('<table class="pure-table tabUE" id="tabUE_detalleTab"><tbody></tbody></table>')

  var html = `<tr class='tr-none'><td class='td-title'>Razón Social</td><td> ${ficha.razon_soc} </td></tr>` 
  ficha.actividad !== '-' ? html += `<tr class='tr-par'> <td class='td-title'>Actividad</td> <td> ${ficha.actividad} </td></tr>` : false 
  ficha.cve_ent !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Entidad</td><td> ${ficha.cve_ent} </td></tr>` : false 
  ficha.cve_mun !== '-' ? html += `<tr class='tr-par'><td class='td-title'>Municipio</td><td> ${ficha.cve_mun} </td></tr>` : false 
  ficha.cve_loc !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Localidad</td><td> ${ficha.cve_loc} </td></tr>` : false 
  ficha.cve_ageb !== '-' ? html += `<tr class='tr-par'><td class='td-title'>AGEB</td><td> ${ficha.cve_ageb} </td></tr>` : false 
  ficha.cve_mza !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Manzana</td><td> ${ficha.cve_mza} </td></tr>` : false 
  ficha.tipo_vial !== '-' ? html += `<tr class='tr-par'><td class='td-title'>Tipo Vialidad</td><td> ${ficha.tipo_vial} </td></tr>` : false 
  ficha.nomvial !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Nombre Vialidad</td><td> ${ficha.nomvial} </td></tr>` : false 
  ficha.numextnum !== '-' ? html += `<tr class='tr-par'><td class='td-title'>Número Ext</td><td> ${ficha.numextnum} </td></tr>` : false 
  ficha.numextalf !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Número Ext (letra)</td><td> ${ficha.numextalf} </td></tr>` : false 
  ficha.numintnum !== '-' ? html += `<tr class='tr-par'><td class='td-title'>Número Int</td><td> ${ficha.numintnum} </td></tr>` : false 
  ficha.numintalf !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Número Int (letra)</td><td> ${ficha.numintalf} </td></tr>` : false 
  ficha.tipoasen !== '-' ? html += `<tr class='tr-par'><td class='td-title'>Tipo de Asentamiento</td><td> ${ficha.tipoasen} </td></tr>` : false 
  ficha.nomasen !== '-' ? html += `<tr class='tr-none'><td class='td-title'>Nombre Asentamiento</td><td> ${ficha.nomasen} </td></tr>` : false 
  ficha.cor_indust !== '-' ? html += `<tr class='tr-par'><td class='td-title'>Corredor Industrial</td><td> ${ficha.cor_indust} </td></tr>` : false 

  $('#tabUE_detalleTab tbody').html(html)
}


// función boton opción cancelar
const handleCancelClick = () => {
  let id_ue=document.getElementById('id_UE').value
  const checkboxPuntearAlta = document.getElementById('puntear-alta')
  disabledInputs()
  punteo = 'U'
  confirmacionPunteo = false
  handleTipoPunteo()
  handleActionButtons('disabled')
  handleActionPunteoAlta('on')
  !checkboxPuntearAlta.checked ? handleActiveVisibleSearch() : false
  //handleActiveVisibleSearch()
  eliminaFuncionEliminiarDuplicadosSelects()
  bandera_ratificar=false
  alertToastForm('Ahora puedes realizar una nueva busqueda', 'info')
  //llamar servicio que libera la clave y limpia el form si no limpia formulario
  id_ue != '' ? callServiceLiberaClave(id_ue) : cleanForm()
}

const callServiceLiberaClave=(id_ue)=>{
  sendAJAX(urlServices['serviceLiberaClave'].url, 
    {
      'proyecto':dataUserFromLoginLocalStorage.proyectoSesion,
      'id_ue': id_ue
    }, urlServices['serviceLiberaClave'].type, 
    data => {
      if (data[0].operation) {
      //limpia la forma sin avisarle al usuario
      cleanForm()
      } else {
        swal({
          title: '<i class="fa fa-exclamation-triangle"></i> Aviso',
          text: '<i class="fa fa-info"></i>  Ha ocurrido un error durante el proceso de cancelación, por favor intente nuevamente',
          showConfirmButton: true,
          confirmButtonColor: "#DD6B55",
          allowEscapeKey: true,
          allowOutsideClick: true,
          html: true,
          animation: true
        })
      }
    },  () => {}
  ) 
}

const cleanForm = () => {
  const checkboxPuntearAlta = document.getElementById('puntear-alta')
  //limpia formularios
  handleCleanForms()
  //posicion el mapa en su posicion inicial
  MDM6("goCoords", -6674510.727748, -16067092.761748, 4294907.646543801, 1046639.6931187995)
  //oculta el marcador azul
  MDM6('hideMarkers', 'identify')
  //oculta el marcador naranja
  MDM6('hideMarkers', 'routen')
  //contrae la tarjeta de referencia
  handleVisibleForm('referencia')
  //deshabilita botones limpiar y guardar
  handleActionButtons('disabled')
  //oculta div ratificar y busqueda
  handleVisibleRatificaandbusqueda()
  //oculta busqueda
  !checkboxPuntearAlta.checked ? handleVisibleSearch() : false
  //handleVisibleSearch() 
  //oculta mensaje 
  handleHideAlertPickMap()
  
  
}

// Función habilitar inputs formulario
const enabledInputs = () => inputsEditables.map(input => document.getElementById(input.id).removeAttribute('disabled'))

// Función deshabilitar inputs formulario
const disabledInputs = () => inputsEditables.map(input => document.getElementById(input.id).setAttribute('disabled', true))

// función activa btns guardar y cancelar cuando se ratifica y desactiva cuando se cancela
const handleActionButtons = res => {
  const saveOption = document.getElementById('item-save-option')
  const cancelOption = document.getElementById('item-cancel-option')
  const saveMovilOption = document.getElementById('save-movil-option')
  const cancelMovilOption = document.getElementById('cancel-movil-option')

  if (res == 'enabled') {
    saveOption.removeAttribute('disabled')
    cancelOption.removeAttribute('disabled')
    saveMovilOption.setAttribute('onclick', 'handleFormValidations()')
    saveMovilOption.classList.remove('option-disabled')
    cancelMovilOption.setAttribute('onclick', 'handleCancelClick()')
    cancelMovilOption.classList.remove('option-disabled')
  } else if (res == 'disabled') {
    saveOption.setAttribute('disabled', 'true')
    cancelOption.setAttribute('disabled', 'true')
    saveMovilOption.removeAttribute('onclick', 'handleFormValidations()')
    saveMovilOption.classList.add('option-disabled')
    cancelMovilOption.removeAttribute('onclick', 'handleCancelClick()')
    cancelMovilOption.classList.add('option-disabled')
  }
}

const handleSearchCleeValidation = e => {
  const key = window.event ? e.which : e.keyCode
  key < 48 || key > 57 ? e.preventDefault() : false

  tecla = (document.all) ? e.keyCode : e.which;
  tecla == 13 ? buscarUE(e) : false
}


// alertas formulario
const alertToastForm = (title, type) => {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-start',
    showConfirmButton: false,
    timer: 3000
  });

  Toast.fire({
    type,
    title
  })
}

const handleLogOut = () =>{
  localStorage.clear()
  window.location.href = './'
}

const handleSessionActive = () => {            
  sendAJAX(urlServices['serviceValidasesion'].url, null, urlServices['serviceValidasesion'].type, data => {
    if (data[0].datos.success == false) {                                                
      alertToastForm('No se ha iniciado sesión', 'error')
      setTimeout( () => window.location.href = './' , 1500 )
    } else {
      dataUserFromLoginLocalStorage=data[0].datos.datos
    }
  }, 
    () => {}
  )      
}

// ALERTA NORMAL 
const alertPosition = () => {
  Swal.fire({
    position: 'top-end',
    type: 'success',
    title: 'Your work has been saved',
    showConfirmButton: false,
    timer: 1500
  })
}

/* METODOS PARA LAS OPCIONES DEL MENU INFERIOR IMPRESION Y REPORTES*/ 
const opcionMenu = opcion => {     
  switch (opcion){         
    case 2: 
      OpenReportes('desktop', 'vista') 
      break
    case 3: 
      OpenReportes('movil', 'vista') 
      break
    case 4: 
      imprimir() 
  }     
} 

async function OpenReportes (size, action) {
  const {value: reporte} = await Swal.fire({
    title: action == 'vista' ? '<span style="width:100%;">Reportes</span>' : 'Descarga de Reportes',
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
    let src = urlServices['serviceReporte'].url + '?proyecto=1&tipo=PDF&reporte=' + reporte +'&ce=00&ran=' + Math.random() 
    let leyenda = ''
    let srcExcel = urlServices['serviceReporte'].url + '?proyecto=1&tipo=EXCEL&reporte=' + reporte +'&ce=00&ran=' + Math.random() 
    
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

var imprimir = function() {
  $('#window_bottom').hide();
  var data = $('#map').html();
  $('#window_bottom').show();
  var isMobile = false; 
  
  var ventana = window.open('', '', 'height=1000,width=1024');
  ventana.document.open();
  ventana.document.write('<html><head ><title>'+titulo_impresion+'</title>');
  ventana.document.write('<script src="resources/js/jquery-2.1.1.min.js"></script>');
  ventana.document.write('<script src="resources/js/main.js"></script>');    
  if (navigator.userAgent.indexOf("Chrome") !== -1) 
  {
    ventana.document.write('<style type="text/css"  media="print"> ');
    ventana.document.write('@page{size:portrait;}html { width:29.4cm;height:30.62cm;}');
    ventana.document.write('body{margin-bottom: -2.30cm;margin-top: 2cm;margin-right: -1.0cm;margin-left:-1.0cm;}');
    ventana.document.write('.divMapa{page-break-after : always;} ');
    ventana.document.write('.olControlMousePosition{display:none;} ');
    ventana.document.write('#OpenLayers_Control_ScaleLine_4{display:none;}');
    ventana.document.write('#OpenLayers_Map_5_OpenLayers_ViewPort{ width:82%; position:relative; height:100%; left:-200px} ');
    ventana.document.write('</style>');
  } else if (navigator.userAgent.indexOf("Firefox") !== -1) {
    ventana.document.write('<style type="text/css"  media="print"> @page{size:portrait;}html { width:28.4cm;height:29.90cm;}');
    ventana.document.write('body{margin-bottom: -2.30cm;margin-top: 2cm;margin-right: -1.08cm;margin-left:-1.0cm;} ');
    ventana.document.write('.divMapa{page-break-after : always;}');
    ventana.document.write('.olControlMousePosition{display:none;} ');
    ventana.document.write('#OpenLayers_Control_ScaleLine_4{display:none;}');
    ventana.document.write('#OpenLayers_Map_5_OpenLayers_ViewPort{width:82%;overflow:hidden;position:relative;height:100%;}" </style>');
  } else if (navigator.userAgent.indexOf('Trident') !== -1) {
    ventana.document.write('<link rel="stylesheet" type="text/css" href="css/print_ie.css"/>');
  }
  ventana.document.write('<link rel="stylesheet" type="text/css" href="resources/css/app.css"/>');
  ventana.document.write('<link rel="stylesheet" href="resources/css/materialize_1.0.0.css">');
  ventana.document.write('<script src="resources/js/materialize.min.js"></script>');
  ventana.document.write('</head>');
  ventana.document.write('<body>');
  ventana.document.write('<div class="" id="mapa" >');
  ventana.document.write(data);
  ventana.document.write('</div>');
  ventana.document.write('<div id="modal" class="modal" style="top: 40%!important;">');
  ventana.document.write('<div class="modal-content">');
  ventana.document.write('<div> Cargando</div>');
  ventana.document.write('<div class="preloader-wrapper big active">');
  ventana.document.write('<div class="spinner-layer spinner-blue-only">');
  ventana.document.write('<div class="circle-clipper left">');
  ventana.document.write('<div class="circle"></div>');
  ventana.document.write('</div>');
  ventana.document.write('<div class="gap-patch">');
  ventana.document.write('<div class="circle"></div>');
  ventana.document.write('</div>');
  ventana.document.write('<div class="circle-clipper right">');
  ventana.document.write('<div class="circle"></div>');
  ventana.document.write('</div>');
  ventana.document.write('</div>');
  ventana.document.write('</div>');         
  ventana.document.write('</div>');   
  ventana.document.write('</div>');
  ventana.document.write('<script>modal2();setClassPrint();setTimeout(function(){closeModal2();},2000); window.onafterprint = function(e){');   
  ventana.document.write('$(window).off("mousemove", window.onafterprint);  setTimeout(function () {    window.close(); }, 1000);     ');       
  ventana.document.write('};</script>');    
  ventana.document.write('</body>');
  ventana.document.write('</html>');
  ventana.document.close();       
  setTimeout(function () {       
    ventana.print();          
  }, 2500);    
}

const  modal2 = () => {         
  $('.modal').modal()
  $('.modal').modal('open')
} 
 
 
const closeModal2 = () => {                
  $('.modal').modal('close')
} 
 
function setClassPrint() {  
  if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) 
  || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0,4))) 
  { 
    $('#OpenLayers_Map_5_OpenLayers_ViewPort')
      .css({ 
        'left': '3%', 
        'width': '53%',
        'height': '32%',
        'zoom': '150%'
      }) 
  } else {        
    $('#OpenLayers_Map_5_OpenLayers_ViewPort')
      .css({ 
        'left': '-20%', 
        'width': '150%',
        'height': '100%',
        'zoom': '100%'
      }) 
  }
}
/* FIN OPCIONES DEL MENU INFERIOR DERECHO IMPRESION Y REPORTES*/

/*CLAVES BLOQUEADAS*/
const Desbloquear = id_ue => Actiondesbloquear(id_ue)

const GetClavesBloqueadas=()=> {
    loadTemplate('ClavesBloqueadas', "resources/templates/ClavesBloqueadas.html?frm=" + Math.random(), function (html) {
        $('#tabUE tbody').html(html);
        CargaTablaBloqueadas('#tableClavesBloqueadas');
        // CargaTablaBloqueadas();
    });
}

const CargaTablaBloqueadas=()=> {
  arrayClavesBloqueadasTodas=""
  let oTable, tr
  
  sendAJAX(urlServices['serviceListaClavesBloqueadas'].url, 
    {
      'proyecto': dataUserFromLoginLocalStorage.proyectoSesion, 
      'tramo': dataUserFromLoginLocalStorage.tramoControl, 
      'id_ue':dataUserFromLoginLocalStorage.ce
    }, urlServices['serviceListaClavesBloqueadas'].type, 
     data => {
      if (data[0].datos.length>0) {
        dataCleeListNewLock = data[0]
        popupCleeListBloqueadas(data[0].datos)
      } else {
        Swal.fire ({
          position: 'bottom-end',
          type: 'warning',
          title: 'No existen claves bloqueadas',
          showConfirmButton: false,
          timer: 2000
        })
      }
    },  () => {
      swal ({
        title: '<span style="width:100%;">Buscando información!</span>',
        text: 'Por favor espere un momento',
        timer: 2000,
        onOpen: () => swal.showLoading()
      })
        .then(() => { },
          dismiss => {}
        )
    }
  )
}

const addClavesDesbloquear = (id_ue, check) => {
  if (id_ue !== null) {
    if (check) {
      arrayClavesBloqueadas = arrayClavesBloqueadas + id_ue + ","
    }
  } else {
  }
}

var ActionSeleccionarTodos = function () {
  var accion = $('input:checkbox[name=inputTodos]:checked').val()
  var oTable = $('#tableClavesBloqueadas').dataTable()
  if (accion) {
    oTable.$("input[type='checkbox']").prop('checked', true)
    banderaDesbloquear = true
  } else {
    oTable.$("input[type='checkbox']").prop('checked', false)
    banderaDesbloquear = false
  }
}

var Actiondesbloquear = function (id_ue) {
  swal.fire ({
    title: 'se desbloqueara la claves? ' + id_ue,
    text: "",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Correcto, Desbloquear!',
    cancelButtonText: 'Cancelar'
  })
    .then( result => handleShowResultDesbloqueo(result,id_ue) )
}

const handleShowResultDesbloqueo = (result,id_ue) => {
  const user = dataUserFromLoginLocalStorage.nombre
  if (result.value) {
    sendAJAX(urlServices['serviceDesbloqueoClavesBloqueadas'].url, 
    {
      'proyecto':dataUserFromLoginLocalStorage.proyectoSesion,
      'id_ue': id_ue,
      'usuario':user
    }, 
    urlServices['serviceDesbloqueoClavesBloqueadas'].type, 
    data => {
      if (data[0].operation) {
        if (data[0].datos.mensaje.type === 'false') {
          handleShowSaveAlert('error', 'Error', data[0].datos.mensaje.messages, false)
          return;
        }
        else {
          MDM6('hideMarkers', 'identify')
          handleShowSaveAlert('success', 'Desbloqueo', 'Se ha Desbloqueado la clave', true)
        }
      }
      
      else {
        handleShowSaveAlert('error', 'Error', 'Error de conexión', true)
      }
    }, () => handleShowSaveAlert('info', 'Desbloqueando', 'Desbloqueando clave, por favor espere un momento', true)
    )
        
  } //close if result.value
}
/*FIN CLAVES BLOQUEADAS*/

const tiempoInactividad = () => { 
  let tiempo 
  const resetTimer = () => { 
    clearTimeout(tiempo) 
    tiempo = setTimeout(logout, 3600000)
  }     
  window.onload = resetTimer 
  // DOM Events 
  document.onmousemove = resetTimer
  document.onkeypress = resetTimer
  document.onload = resetTimer
  document.onmousedown = resetTimer // touchscreen presses 
  document.ontouchstart = resetTimer 
  document.onclick = resetTimer  // touchpad clicks 
  document.onscroll = resetTimer // scrolling with arrow keys 
  const logout = () => { 
    localStorage.clear()
    alertToastForm('Sesión se cerrará por permanecer 30 minutos sin actividad', 'error')
  }     
}