let objDataHeight = {}

const handleVisibleSearch = () => {
  const containerSearch = document.getElementById('container-search')
  const arrowSearch = document.getElementById('arrow-search')
  const tituloBusqueda = document.getElementById('titulo-busqueda')
  const row = document.getElementById('row-search')
  let visible = containerSearch.dataset.visible
  if(visible == 'show'){
    containerSearch.style.animation = 'hideSearch 1.2s 1 linear forwards' 
    arrowSearch.style.animation = 'rotateArrow 1.2s 1 linear forwards' 
    row.style.display  = 'none'
    setTimeout ( () =>  tituloBusqueda.style.display = 'block', 1000 )
    containerSearch.dataset.visible = 'hide'
  } else if (visible == 'hide') {
    containerSearch.style.animation = 'showSearch 1.2s 1 linear forwards' 
    arrowSearch.style.animation = 'rotateArrow2 1.2s 1 linear forwards'
    row.style.display  = 'flex'
    tituloBusqueda.style.display = 'none'
    containerSearch.dataset.visible = 'show'
  }
 
}

const handleOptions = () => {
  const arrow = document.getElementById('item-options')
  const options = document.getElementById('wrap-options')
  const containerHeight = `${options.offsetHeight}px`
  let visible = arrow.dataset.visible

  if(visible == 'show'){
    options.animate([{ height:containerHeight}, {height:0}], {duration:1200, fill:'forwards'})
    options.offsetHeight >= 10  ? objDataHeight.optionsMovil = containerHeight : false
   //options.style.animation = 'hideOptions 1.2s 1 linear forwards' 
    arrow.style.animation = 'rotateArrow 1.2s 1 linear forwards' 
    arrow.dataset.visible = 'hide'
  } else if (visible == 'hide') {
    //options.style.animation = 'showOptions 1.2s 1 linear forwards' 
    options.animate([{ height: 0}, {height: objDataHeight.optionsMovil}], {duration:1200, fill:'forwards'})
    arrow.style.animation = 'rotateArrow2 1.2s 1 linear forwards'
    arrow.dataset.visible = 'show'
  }
}

const handleVisibleForm = option => {
  const container = document.getElementById(`title-${option}`)
  const contenidoInputs = document.getElementById(`inputs-${option}`)
  const containerHeight = `${contenidoInputs.offsetHeight}px`
  const icon = document.getElementById(`icon-${option}`)
  let visible = container.dataset.visible

  if(visible == 'show'){
    container.classList.add('no-visible')
    if(screen.width <= '480') {
      contenidoInputs.animate([{ height:containerHeight, padding: '5px 20px 5px 20px'}, {height:0, padding: 0,}], {duration:1200, fill:'forwards'})
      contenidoInputs.offsetHeight != 0 && !objDataHeight[option] ? objDataHeight[option] = containerHeight : false
    } else {
      contenidoInputs.style.animation = 'hideContainerForm 1.2s 1 linear forwards'
    }
    icon.style.animation = 'rotateXClose 1.2s 1 linear forwards'
    container.dataset.visible = 'hide'
  } else if (visible == 'hide'){
    container.classList.remove('no-visible')
    screen.width <= '480' 
      ? contenidoInputs.animate([{ height: 0, padding: 0}, {height: objDataHeight[option], padding: '5px 20px 5px 20px',}], {duration:1200, fill:'forwards'}) 
      : contenidoInputs.style.animation = 'showContainerForm 1.2s 1 linear forwards'
    icon.style.animation = 'rotateXOpen 1.2s 1 linear forwards'
    container.dataset.visible = 'show'
  }
}

const handleVisibleRatifica = () => {
  const containerRatifica = document.getElementById('container-ratifica')
  let visible = containerRatifica.dataset.visible
  if(visible == 'show'){
    containerRatifica.animate([{ height:'auto' , opacity:1, padding:'10px 0'}, {height:0, opacity:0, padding:0}], {duration:400, fill:'forwards'})
    containerRatifica.dataset.visible = 'hide'
  } else {
    containerRatifica.animate([{ height:0 , opacity:0, padding:0}, {height:'auto', opacity:1, padding:'10px 0'}], {duration:400, fill:'forwards'})
    containerRatifica.dataset.visible = 'show'
   
  }
}
