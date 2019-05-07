let objDataHeight = {}
const arrayNameWraps = ['referencia', 'ubicacion-geografica', 'domicilio', 'asentamiento', 'entre-vialidades', 'calle-posterior', 'edificio']

window.onload = () => {
  handleSessionActive()
  handleHideForm()
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
    containerRatifica.animate([{ height:'auto' , opacity:1, padding:'10px 0'}, {height:0, opacity:0, padding:0}], {duration:500, fill:'forwards'})
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

const handleActionTarget = wrap => {

  arrayNameWraps.map( name => {
    const container = document.getElementById(`title-${name}`)
    const contenidoInputs = document.getElementById(`inputs-${name}`)
    let visible = container.dataset.visible
    
    if (name == wrap) {
      if(visible == 'hide'){
        handleVisibleForm(wrap)
      }
    } else {
      if (visible == 'show'){
        handleVisibleForm(name)
      }
    }
  })
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


const handlePressCtrKeyAndKeyCode = e => {
  const evtobj = window.event ? event : e
  const arrowSearch = document.getElementById('arrow-search')
  if (arrowSearch.onclick){
    if (evtobj.keyCode == 66 && evtobj.ctrlKey) handleVisibleSearch()
    if (evtobj.keyCode == 76 && evtobj.ctrlKey && evtobj.altKey) {
      handleVisibleSearch()
      handleViewCleeList()
    }
  }

  if(evtobj.keyCode == 82 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('referencia')
  if(evtobj.keyCode == 85 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('ubicacion-geografica')
  if(evtobj.keyCode == 68 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('domicilio')
  if(evtobj.keyCode == 65 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('asentamiento')
  if(evtobj.keyCode == 86 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('entre-vialidades')
  if(evtobj.keyCode == 67 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('calle-posterior')
  if(evtobj.keyCode == 69 && evtobj.ctrlKey && evtobj.altKey) handleVisibleForm('edificio')
}

document.onkeydown = handlePressCtrKeyAndKeyCode