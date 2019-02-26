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
  let visible = arrow.dataset.visible

  if(visible == 'show'){
    options.style.animation = 'hideOptions 1.2s 1 linear forwards' 
    arrow.style.animation = 'rotateArrow 1.2s 1 linear forwards' 
    //row.style.display  = 'none'
    arrow.dataset.visible = 'hide'
  } else if (visible == 'hide') {
    options.style.animation = 'showOptions 1.2s 1 linear forwards' 
    arrow.style.animation = 'rotateArrow2 1.2s 1 linear forwards'
    //row.style.display  = 'flex'
    arrow.dataset.visible = 'show'
  }
}

const handleVisibleForm = option => {
  const container = document.getElementById(`title-${option}`)
  const contenidoInputs = document.getElementById(`inputs-${option}`)
  const icon = document.getElementById(`icon-${option}`)
  let visible = container.dataset.visible

  if(visible == 'show'){
    container.classList.add('no-visible')
    contenidoInputs.style.animation = 'hideContainerForm 1.2s 1 linear forwards'
    icon.style.animation = 'rotateXClose 1.2s 1 linear forwards'
    container.dataset.visible = 'hide'
  } else if (visible == 'hide'){
    container.classList.remove('no-visible')
    contenidoInputs.style.animation = 'showContainerForm 1.2s 1 linear forwards'
    icon.style.animation = 'rotateXOpen 1.2s 1 linear forwards'
    container.dataset.visible = 'show'
  }
}
