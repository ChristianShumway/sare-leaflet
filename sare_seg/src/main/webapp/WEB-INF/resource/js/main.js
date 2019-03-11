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
  let containerInputsVisible = true
  const totalInputs = objForm.length
  let inputsInfo = 0

  for(let input = 0; input < objForm.length; input ++){
    const {id, name, title, key} = objForm[input]
    const element = document.getElementById(id)
    const wrapTitle = document.getElementById(title)
    let visible = wrapTitle.dataset.visible
    console.log(id)

    if(element.value == ''){
      element.style.borderColor = 'red'
      inputsEmpty = true
      containerInputsVisible = false
      const msgInputEmpty = `Favor de completar la información del campo ${name}`
      alertToastForm(msgInputEmpty)

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

  inputsInfo == totalInputs && alert('no hay inputs vacios')  

}


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

