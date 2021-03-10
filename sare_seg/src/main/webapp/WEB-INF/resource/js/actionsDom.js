let objDataHeight = {}
const arrayNameWraps = ['referencia', 'ubicacion-geografica', 'domicilio', 'asentamiento', 'entre-vialidades', 'calle-posterior', 'edificio']
let wrapFloatActive = false
let nameContainerFloating = ''
const arrayWrapBtns = ['wrap-btns-referencia', 'wrap-btns-ugeografica', 'wrap-btns-domicilio', 'wrap-btns-asentamiento', 'wrap-btns-evialidades', 'wrap-btns-cposterior', 'wrap-btns-edificios']
let flagScreen = 'normal'


window.onload = () => {
  // handleSessionActive()
  handleHideForm()
  addAttributeTitle()
  handleIconFullScreen()
}

const handleFullScreen = () => {
  if(flagScreen === 'normal'){
    document.body.requestFullscreen()
    flagScreen = 'full'
    handleIconFullScreen()
  } else if (flagScreen === 'full') {
    document.exitFullscreen() 
    flagScreen = 'normal'
    handleIconFullScreen()
  }
}

handleIconFullScreen = () =>{
  const btnScreen = document.getElementById('btn-fullscreen')
  if(flagScreen === 'normal'){
    btnScreen.innerHTML = ' <i class="material-icons icon-full">fullscreen</i>'
  } else {
    btnScreen.innerHTML = ' <i class="material-icons icon-full">fullscreen_exit</i>'
  }
}

// Función para mostar u ocultar modulo de busqueda de clave
const handleVisibleSearch = () => {
  const containerSearch = document.getElementById('container-search')
  const arrowSearch = document.getElementById('arrow-search')
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  const row = document.getElementById('row-search')
  let visible = containerSearch.dataset.visible
  if(visible == 'show'){
    containerSearch.style.animation = 'hideSearch .7s 1 linear forwards' 
    arrowSearch.style.animation = 'rotateArrow .7s 1 linear forwards' 
    row.style.display  = 'none'
    setTimeout ( () =>  tituloBusqueda.style.display = 'flex', 700 )
    containerSearch.dataset.visible = 'hide'
  } else if (visible == 'hide') {
    containerSearch.style.animation = 'showSearch .7s 1 linear forwards' 
    arrowSearch.style.animation = 'rotateArrow2 .7s 1 linear forwards'
    containerSearch.classList.remove('initial')
    row.style.display  = 'flex'
    tituloBusqueda.style.display = 'none'
    containerSearch.dataset.visible = 'show'
  }

  if (nameContainerFloating){
    setTimeout (() => {
      // alert('hay valor')
      handlePositionContainerForm(nameContainerFloating)
    }, 700 )
  }
 
}

// Función para mostar u ocultar opciones costado del mapa
const handleOptions = () => {
  const arrow = document.getElementById('item-options')
  const options = document.getElementById('wrap-options')
  const containerHeight = `${options.offsetHeight}px`
  let visible = arrow.dataset.visible

  if(visible == 'show'){
    options.animate([{ height:containerHeight}, {height:0}], {duration:1000, fill:'forwards'})
    options.offsetHeight >= 10  ? objDataHeight.optionsMovil = containerHeight : false
   //options.style.animation = 'hideOptions 1.2s 1 linear forwards' 
    arrow.style.animation = 'rotateArrow 1s 1 linear forwards' 
    arrow.dataset.visible = 'hide'
  } else if (visible == 'hide') {
    //options.style.animation = 'showOptions 1.2s 1 linear forwards' 
    options.animate([{ height: 0}, {height: objDataHeight.optionsMovil}], {duration:1000, fill:'forwards'})
    arrow.style.animation = 'rotateArrow2 1s 1 linear forwards'
    arrow.dataset.visible = 'show'
  }
}

// Función desplegar o contraer contenedores de formulario
const handleVisibleForm = option => {
  const container = document.getElementById(`title-${option}`)
  const contenidoInputs = document.getElementById(`inputs-${option}`)
  const containerHeight = `${contenidoInputs.offsetHeight}px`
  const icon = document.getElementById(`icon-${option}`)
  let visible = container.dataset.visible

  if(visible == 'show'){
    container.classList.add('no-visible')
    if(screen.width <= '480') {
      contenidoInputs.animate([{ height:containerHeight, padding: '5px 20px 5px 20px'}, {height:0, padding: 0,}], {duration:700, fill:'forwards'})
      contenidoInputs.offsetHeight != 0 && !objDataHeight[option] ? objDataHeight[option] = containerHeight : false
    } else {
      contenidoInputs.style.animation = 'hideContainerForm .7s 1 linear forwards'
    }
    icon.style.animation = 'rotateXClose .7s 1 linear forwards'
    container.dataset.visible = 'hide'
  } else if (visible == 'hide'){
    container.classList.remove('no-visible')
    screen.width <= '480' 
      ? contenidoInputs.animate([{ height: 0, padding: 0}, {height: objDataHeight[option], padding: '5px 20px 5px 20px',}], {duration:700, fill:'forwards'}) 
      : contenidoInputs.style.animation = 'showContainerForm .7s 1 linear forwards'
    icon.style.animation = 'rotateXOpen .7s 1 linear forwards'
    container.dataset.visible = 'show'
  }
}

//Función para mostar y ocultar modulo de ratificar
const handleVisibleRatifica = () => {
  const containerRatifica = document.getElementById('container-ratifica')
  let visible = containerRatifica.dataset.visible
  if(visible == 'show'){
    containerRatifica.animate([{ height:'auto' , opacity:1, padding:'5px 0'}, {height:0, opacity:0, padding:0}], {duration:500, fill:'forwards'})
    containerRatifica.dataset.visible = 'hide'
  } else {
    containerRatifica.animate([{ height:0 , opacity:0, padding:0}, {height:'auto', opacity:1, padding:'10px 0'}], {duration:500, fill:'forwards'})
    containerRatifica.dataset.visible = 'show'
   
  }
}

//Funcion para ocultar modulo de ratificar y modulo de busqueda
const handleVisibleRatificaandbusqueda = () => {
  const containerRatifica = document.getElementById('container-ratifica')
  let visible = containerRatifica.dataset.visible
  if(visible == 'show'){
    containerRatifica.animate([{ height:'auto' , opacity:1, padding:'10px 0'}, {height:0, opacity:0, padding:0}], {duration:500, fill:'forwards'})
    containerRatifica.dataset.visible = 'hide'
  } 
}



// función que muestra alerta que indica que hay que puntear
const handleShowAlertPickMap = () => {
  const wrapAlertPick = document.getElementById('wrap-alert-pick')
  screen.width > '768'
    ? wrapAlertPick.animate([{ bottom: '-45px'}, {bottom:'30px'}], {duration:800, fill:'forwards'})
    : wrapAlertPick.animate([{ bottom: '-45px'}, {bottom:'10px'}], {duration:800, fill:'forwards'})

  wrapAlertPick.style.animation = 'latido 2s alternate infinite forwards 2s'
}

// función que oculta alerta que hay que puntear
const handleHideAlertPickMap = () => {
  const wrapAlertPick = document.getElementById('wrap-alert-pick')
  screen.width > '768'
    ? wrapAlertPick.animate([{ bottom: '30px'}, {bottom:'-45px'}], {duration:300, fill:'forwards'})
    : wrapAlertPick.animate([{ bottom: '10px'}, {bottom:'-45px'}], {duration:300, fill:'forwards'})
}

// Función boton scroll a inicio
const handleScrollTop = () =>{
  // alert(document.body.scrollTop)
  var body = $("html, body")
  body.stop().animate({scrollTop:0}, 1000, 'swing', () =>{} )
}

// Función para anclas locales animadas
const handleModuleScroll = e => {
  e.preventDefault()
  const ancla = e.target.hash
  const body = $("html, body")
  body.stop(true,true).animate({				
    scrollTop: $(ancla).offset().top
  },700);
}

const handleActionTargetRef = () => {
  const titleReferencia = document.getElementById('title-referencia')
  const stateWrap = titleReferencia.dataset.visible
  stateWrap == 'hide' ? handleVisibleForm('referencia') : false

  arrayNameWraps.map ( name => {
    const titleWrap = document.getElementById(`title-${name}`)
    const visible = titleWrap.dataset.visible
    
    name != 'referencia' && visible == 'show' ?  handleVisibleForm(name) : false
  })
}

const handleActionTarget = (wrap, float) => {

  arrayNameWraps.map( name => {
    const container = document.getElementById(`title-${name}`)
    let visible = container.dataset.visible
    const contenidoInputs = document.getElementById(`inputs-${name}`)
    if(!wrapFloatActive){
      if (name == wrap) {
        if(visible == 'hide'){
          handleVisibleForm(wrap)
        }
      } else {
        if (visible == 'show'){
          handleVisibleForm(name)
        }
      }
    }
  })

  if(wrapFloatActive){
    switchArrowFlotating(float)
  }
}

const handleHideForm = () => {
  arrayNameWraps.map( name => handleVisibleForm(name) )
} 

//función que muestra modulo de ratificar y oculta el de busqueda
const handleShowRaticaHideSearch = () => {
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  const viewSearchContainer = document.getElementById('arrow-search')
  const wrapSiRatifica = document.getElementById('wrap-si-ratifica')
  const wrapNoRatifica = document.getElementById('wrap-no-ratifica')

  wrapSiRatifica.classList.add('animated', 'slideInLeft', 'slow')
  wrapNoRatifica.classList.add('animated', 'slideInRight', 'slow')
  wrapSiRatifica.addEventListener('animationend', () => wrapSiRatifica.classList.remove('animated', 'slideInLeft', 'slow'))
  wrapNoRatifica.addEventListener('animationend', () => wrapNoRatifica.classList.remove('animated', 'slideInRight', 'slow'))

  viewSearchContainer.removeAttribute('onclick')
  tituloBusqueda.removeAttribute('onclick')
  handleVisibleSearch()
  handleVisibleRatifica()
}

//funcion que limpia los formularios
const handleCleanForms=()=>
{
    inputsClean.map(input => document.getElementById(input.id).value="")
    $("#e10_A").empty().attr("disabled", true);
    $("#e10_B").empty().attr("disabled", true);
    $("#e10_C").empty().attr("disabled", true);
}

//Función para activar y desactivar puntear alta
const handleActionPunteoAlta = status => {
  const checkPuntearAlta = document.getElementById('puntear-alta')

  if(status === 'off' && !checkPuntearAlta.checked) {
    //alert('desactiva opcion')
    checkPuntearAlta.setAttribute('disabled', true)
  } else if (status === 'on'){
    checkPuntearAlta.removeAttribute('disabled')
  }
}

const handlePositionContainerForm = (wrap, idBtn = '', action = '', title = '') => {
  nameContainerFloating = wrap
  const wrapFloating = document.getElementById(wrap)

  if (wrapFloatActive) {
    wrapFloating.style.top = getPositionContainerFlotatingForm()
  } else if (!wrapFloatActive) {
    wrapFloating.classList.add('container-form-float')
    wrapFloating.style.top = getPositionContainerFlotatingForm()
    handleShowIconsFloatingWrap(idBtn, action)
    wrapFloatActive = true
  }

  if(title){
    const idTitle = document.getElementById(title)
    let visible = idTitle.dataset.visible
    if(visible === 'show'){
      titlesWarpForm.map(item => {
        if(item.title === title) handleVisibleForm(item.key)
      })
    }
  }
  
}

const handleReturnContainerForm = (wrap, idBtn = '', action = '', title = '') => {
  nameContainerFloating = ''
  wrapFloatActive = false
  const wrapFloating = document.getElementById(wrap)
  wrapFloating.classList.remove('container-form-float')
  wrapFloating.style.top = 'initial'
  handleShowIconsFloatingWrap(idBtn, action)

  if(title){
    const idTitle = document.getElementById(title)
    let visible = idTitle.dataset.visible
    if(visible === 'hide'){
      titlesWarpForm.map (item => {
        if(item.title === title) handleVisibleForm(item.key)
      })
    }
    // else if(visible === 'show'){
    //   titlesWarpForm.map (item => {
    //     if(item.title === title) handleVisibleForm(item.key)
    //   })
    //   //alert('esta abierto')
    // }
  }
}

const getPositionContainerFlotatingForm = () => {
  const containerMap = document.getElementById('container-map')
  let coords = containerMap.getBoundingClientRect()
  let topContainer = coords.top < 85 ? 85 : coords.top
  //alert(topContainer)
  return `${topContainer}px`
}

const handleShowIconsFloatingWrap = (idBtn, action) => {
  const btnFloat = document.getElementById(`icon-${idBtn}-float`)
  const btnStatic = document.getElementById(`icon-${idBtn}-static`)
  const wrapBtnsFlotating = document.getElementById(`wrap-btns-${idBtn}`)

  if(action === 'float'){
    btnFloat.classList.add('btn-inactive')
    btnStatic.classList.remove('btn-inactive')
    arrayWrapBtns.map (wrap => {
      const idWrap = document.getElementById(wrap)
      if (idWrap !== wrapBtnsFlotating) idWrap.style.display = 'none'
    })
  } else if (action === 'static'){
    btnFloat.classList.remove('btn-inactive')
    btnStatic.classList.add('btn-inactive')
    arrayWrapBtns.map (wrap => {
      const idWrap = document.getElementById(wrap)
      if (idWrap !== wrapBtnsFlotating) idWrap.style.display = 'contents'
    })
  }
}

const switchArrowFlotating = float => {
  switch (float) {
    case 'ubicacion-float-der':
      handleReturnContainerForm('op-preferencia', 'referencia', 'static', 'title-referencia')
      handleVisibleForm('referencia')
      handlePositionContainerForm('op-ugeografica', 'ugeografica', 'float', 'title-ubicacion-geografica' )
      handleVisibleForm('ubicacion-geografica')
      break
    case 'referencia-float-izq':
      handleReturnContainerForm('op-ugeografica', 'ugeografica', 'static', 'title-ubicacion-geografica')
      handleVisibleForm('ubicacion-geografica')
      handlePositionContainerForm('op-preferencia', 'referencia', 'float', 'title-referencia' )
      handleVisibleForm('referencia')
      break
    case 'domicilio-float-der':
      handleReturnContainerForm('op-ugeografica', 'ugeografica', 'static', 'title-ubicacion-geografica')
      handleVisibleForm('ubicacion-geografica')
      handlePositionContainerForm('op-domicilio', 'domicilio', 'float', 'title-domicilio')
      handleVisibleForm('domicilio')
      break
    case 'ubicacion-float-izq':
      handleReturnContainerForm('op-domicilio', 'domicilio', 'static', 'title-domicilio')
      handleVisibleForm('domicilio')
      handlePositionContainerForm('op-ugeografica', 'ugeografica', 'float', 'title-ubicacion-geografica')
      handleVisibleForm('ubicacion-geografica')
      break
    case 'asentamiento-float-der':
      handleReturnContainerForm('op-domicilio', 'domicilio', 'static', 'title-domicilio')
      handleVisibleForm('domicilio')
      handlePositionContainerForm('op-asentamiento', 'asentamiento', 'float', 'title-asentamiento')
      handleVisibleForm('asentamiento')
      break
    case 'domicilio-float-izq':
      handleReturnContainerForm('op-asentamiento', 'asentamiento', 'static', 'title-asentamiento')
      handleVisibleForm('asentamiento')
      handlePositionContainerForm('op-domicilio', 'domicilio', 'float', 'title-domicilio')
      handleVisibleForm('domicilio')
      break
    case 'vialidades-float-der':
      handleReturnContainerForm('op-asentamiento', 'asentamiento', 'static', 'title-asentamiento')
      handleVisibleForm('asentamiento')
      handlePositionContainerForm('op-evialidades', 'evialidades', 'float', 'title-entre-vialidades')
      handleVisibleForm('entre-vialidades')
      break
    case 'asentamiento-float-izq':
      handleReturnContainerForm('op-evialidades', 'evialidades', 'static', 'title-entre-vialidades')
      handleVisibleForm('entre-vialidades')
      handlePositionContainerForm('op-asentamiento', 'asentamiento', 'float', 'title-asentamiento')
      handleVisibleForm('asentamiento')
      break
    case 'calle-float-der':
      handleReturnContainerForm('op-evialidades', 'evialidades', 'static', 'title-entre-vialidades')
      handleVisibleForm('entre-vialidades')
      handlePositionContainerForm('op-cposterior', 'cposterior', 'float', 'title-calle-posterior')
      handleVisibleForm('calle-posterior')
      break
    case 'vialidades-float-izq':
      handleReturnContainerForm('op-cposterior', 'cposterior', 'static', 'title-calle-posterior')
      handleVisibleForm('calle-posterior')
      handlePositionContainerForm('op-evialidades', 'evialidades', 'float', 'title-entre-vialidades')
      handleVisibleForm('entre-vialidades')
      break
    case 'edificio-float-der':
      handleReturnContainerForm('op-cposterior', 'cposterior', 'static', 'title-calle-posterior')
      handleVisibleForm('calle-posterior')
      handlePositionContainerForm('op-edificios', 'edificios', 'float', 'title-edificio')
      handleVisibleForm('edificio')
      break
    case 'calle-float-izq':
      handleReturnContainerForm('op-edificios', 'edificios', 'static', 'title-edificio')
      handleVisibleForm('edificio')
      handlePositionContainerForm('op-cposterior', 'cposterior', 'float', 'title-calle-posterior')
      handleVisibleForm('calle-posterior')
      break
    default:
      break;
  }
}

const handlePressCtrKeyAndKeyCode = e => {
  const evtobj = window.event ? event : e
  const arrowSearch = document.getElementById('arrow-search')
  const containerSearch = document.getElementById('container-search')
  if (arrowSearch.onclick){
    if (evtobj.keyCode == 66 && evtobj.ctrlKey) handleVisibleSearch()
    if (evtobj.keyCode == 76 && evtobj.ctrlKey && evtobj.altKey) {
      handleViewCleeList()
      if (containerSearch.dataset.visible == 'hide') handleVisibleSearch()
    }
  }

  if(evtobj.keyCode == 82 && evtobj.ctrlKey && evtobj.altKey) {
    handlePositionContainerForm('op-preferencia', 'referencia', 'float', 'title-referencia')
    handleVisibleForm('referencia')
  }
  if(evtobj.keyCode == 85 && evtobj.ctrlKey && evtobj.altKey) {
    handlePositionContainerForm('op-ugeografica', 'ugeografica', 'float', 'title-ubicacion-geografica')
    handleVisibleForm('ubicacion-geografica')
  }
  if(evtobj.keyCode == 68 && evtobj.ctrlKey && evtobj.altKey){
    handlePositionContainerForm('op-domicilio', 'domicilio', 'float', 'title-domicilio')
    handleVisibleForm('domicilio')
  } 
  if(evtobj.keyCode == 65 && evtobj.ctrlKey && evtobj.altKey){
    handlePositionContainerForm('op-asentamiento', 'asentamiento', 'float', 'title-asentamiento')
    handleVisibleForm('asentamiento')
  } 
  if(evtobj.keyCode == 86 && evtobj.ctrlKey && evtobj.altKey){
    handlePositionContainerForm('op-evialidades', 'evialidades', 'float', 'title-entre-vialidades')
    handleVisibleForm('entre-vialidades')
  } 
  if(evtobj.keyCode == 67 && evtobj.ctrlKey && evtobj.altKey){
    handlePositionContainerForm('op-cposterior', 'cposterior', 'float', 'title-calle-posterior')
    handleVisibleForm('calle-posterior')
  } 
  if(evtobj.keyCode == 69 && evtobj.ctrlKey && evtobj.altKey){
    handlePositionContainerForm('op-edificios', 'edificios', 'float', 'title-edificio')
    handleVisibleForm('edificio')
  } 
}

document.onkeydown = handlePressCtrKeyAndKeyCode


const addAttributeTitle = () => {
  //const urlApi = 'http://mdm5beta.inegi.org.mx:8181/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6'
 const urlApi = 'https://gaia.inegi.org.mx/mdm-api/api?key=mdmGIfDSZGc6rJYVVmirb6A7tmwfYgCE7UQivS5p6JJPpY&version=V6&d=gaia.inegi.org.mx'
  const script = document.createElement('script')
  script.src = urlApi
  script.addEventListener('load', postLoadFunction)
  document.head.appendChild(script)

  function postLoadFunction() {
    id_ue=document.getElementById('id_UE').value;
    if(id_ue!=null && id_ue!=" " && id_ue!="")
    {
        window.addEventListener("beforeunload", function (e) {
        ejecutar();
        (e || window.event).returnValue = null;
        return null;
        });
    }
    setTimeout( () => {
      document.getElementById('scaleControl_zoomIn').setAttribute('title','Acercar')
      document.getElementById('scaleControl_extent').setAttribute('title','Expandir') 
      document.getElementById('scaleControl_zoomOut').setAttribute('title','Alejar')
    },500) 
  }  
}
