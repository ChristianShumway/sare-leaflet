let actualPagina = 1
let inicioPaginacion = 1
let finPaginacion = screen.width <= '480' ? 5 : 7
let inicioClavesVista = 0
let finClavesVista = 9
let dataCleeListNew = {}
let dataResultSearchClee = {}
let cleeListType = 'normal'

const handleChangeOptions = option => {
  const title = document.getElementById(`option-${option}`)
  const checkBox = document.getElementById(`checkbox-${option}`)
  checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
}

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

// Función al seleccionar opciones identificar, puntear  y vista calle
const HandleWhatDoYouWantToDo = request => {
  alert(request)
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


