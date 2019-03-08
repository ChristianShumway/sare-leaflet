const objForm = [
  {
    id:'origen',
    key: 'referencia',
    name:'origen',
    title:'title-referencia'
  },
  {
    id:'c154',
    key: 'referencia',
    name:'c154',
    title:'title-referencia'
  },
  {
    id:'e08',
    key: 'referencia',
    name:'e08',
    title:'title-referencia'
  },
  {
    id:'e09',
    key: 'referencia',
    name:'e09',
    title:'title-referencia'
  },
  {
    id:'codigo_scian',
    key: 'referencia',
    name:'codigo_scian',
    title:'title-referencia'
  },
  {
    id:'e03',
    key: 'ubicacion-geografica',
    name:'e03',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e03n',
    key: 'ubicacion-geografica',
    name:'e03n',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e04',
    key: 'ubicacion-geografica',
    name:'e04',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e04n',
    key: 'ubicacion-geografica',
    name:'e04n',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e05n',
    key: 'ubicacion-geografica',
    name:'e05n',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e05',
    key: 'ubicacion-geografica',
    name:'e05',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e06',
    key: 'ubicacion-geografica',
    name:'e06',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e07',
    key: 'ubicacion-geografica',
    name:'e07',
    title:'title-ubicacion-geografica',
  },
]

const handleChangeOptions = option => {
  const title = document.getElementById(`option-${option}`)
  const checkBox = document.getElementById(`checkbox-${option}`)
  checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
}

const buscarUE = () => {
  const viewSearchContainer = document.getElementById('arrow-search')
  viewSearchContainer.removeAttribute('onclick')
  handleVisibleSearch()
  handleVisibleRatifica()
}

const ratificar = request => {
  const viewSearchContainer = document.getElementById('arrow-search')
  viewSearchContainer.setAttribute('onclick','handleVisibleSearch()')
  handleVisibleSearch()
  handleVisibleRatifica()
}

const handleFormValidations = () => {
  let inputsEmpty = false
  let containerInputsVisible = true

  for(let input = 0; input < objForm.length; input ++){
    //console.log(objForm[input])
    const {id, name, title, key} = objForm[input]
    const element = document.getElementById(id)
    const wrapTitle = document.getElementById(title)
    let visible = wrapTitle.dataset.visible
    console.log(id)

    if(element.value == ''){
      element.style.borderColor = 'red'
      inputsEmpty = true
      alertToastForm()

      element.value == '' && wrapTitle.id == title 
      ? wrapTitle.classList.add('error') 
      : wrapTitle.classList.remove('error')

      break
    } else {
      element.style.borderColor = '#eeeeee'
    }

      
  }
  
  // objForm.forEach(input => {
  //   const {id, name, title, key} = input
  //   const element = document.getElementById(id)
  //   const wrapTitle = document.getElementById(title)
  //   let visible = wrapTitle.dataset.visible

  //   if(element.value == ''){
  //     element.style.borderColor = 'red'
  //     inputsEmpty = true
  //     alertToastForm()
  //   } else {
  //     element.style.borderColor = '#eeeeee'
  //   }

    
  //   element.value == '' && wrapTitle.id == title 
  //     ? wrapTitle.classList.add('error') 
  //     : wrapTitle.classList.remove('error')
    
  //   element.value !== '' && wrapTitle.id == title
  //     ? true
  //     : false
  //     //? visible == 'show' ? handleVisibleForm(key) : false

  // })

  //inputsEmpty ? alert('hay inputs vacios') : alert('no hay inputs vacios')
}


const alertToastForm = () => {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-start',
    showConfirmButton: false,
    timer: 3000
  });
  
  Toast.fire({
    type: 'error',
    title: 'Favor de completar los campos seleccionados'
  })
}