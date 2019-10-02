const user = document.getElementById('usuario')
const password = document.getElementById('password')
const wrapUser = document.getElementById('wrap-input-user')
const wrapPassword = document.getElementById('wrap-input-password')
const cualSare = document.getElementById('cual-sare')
let dataUserObj = {
  acceso: '',
  ce: '',
  cveOperativa: '',
  nombre: '',
  tipoSare: '',
  tramoControl: '',
  usuario: '',
  proyectoSesion:'',
}
let loading = 'false'

window.onload = ( () => {
  selectListCss()
})

const goFormLogin = () => {
  //const card = document.getElementById('card')
  const selectSare = document.getElementById('cual-sare')
  const selectSelected = document.getElementById('id-select-sare')
  const title = 'Selecciona a cual tipo de SARE deseas ingresar'
  // selectSare.value ? card.style.transform = 'rotatex(180deg)' : alertToastLogin(title)
  if (selectSare.value){
    //card.style.transform = 'rotatex(180deg)'
    dataUserObj.proyectoSesion = parseInt(selectSare.value)
    localStorage.setItem("dataUserObj", JSON.stringify(dataUserObj))
    window.location.href =  './index.html'
    selectSelected.classList.remove('select-no-selected', 'animated', 'shake')
  } else {
    selectSelected.classList.add('select-no-selected', 'animated', 'shake')
    alertToastLogin(title)
    setTimeout( () => selectSelected.classList.remove('animated', 'shake'), 1000 )
  }
}

const goLoginBack = () => {
  const card = document.getElementById('card')
  card.style.transform = 'rotatex(0deg)'
}

const handleClickLogin = () => user.value && password.value ?  handleClickValidaUsuario() : handleClickUsuarioPasswordEmpty()

const handleClickUsuarioPasswordEmpty = () => {
  if(!user.value){
    wrapUser.classList.add('wrap-input-empty', 'animated', 'shake')
    user.focus()
    alertToastLogin('Ingresa tu nombre de usuario')
    setTimeout( () => wrapUser.classList.remove('animated', 'shake'), 1000 )
    return
  } else {
    wrapUser.classList.remove('wrap-input-empty')
  }
  
  if(!password.value){
    wrapPassword.classList.add('wrap-input-empty', 'animated', 'shake')
    password.focus()
    alertToastLogin('Ingresa tu password asignado')
    setTimeout( () => wrapPassword.classList.remove('animated', 'shake'), 1000 )
    return
  } else {
    wrapPassword.classList.remove('wrap-input-empty')
  }
}

const handleClickValidaUsuario = () => {
  const card = document.getElementById('card')
  loading = 'true'
  handleVisibleLoading()
  sendAJAX(
    urlServices['serviceLogin'].url, 
    {
      'proyecto': cualSare.value, 
      'usuario': user.value, 
      'password': password.value,
    }, 
    urlServices['serviceLogin'].type, 
    data => { 
      // wrapUser.classList.remove('wrap-input-empty')
      // wrapPassword.classList.remove('wrap-input-empty')
      console.log(data[0].datos)
      const {type, messages} = data[0].datos.mensaje

      if (type == 'warning'){
        alertToastLogin(messages)
        wrapUser.classList.add('wrap-input-empty', 'animated', 'shake')
        wrapPassword.classList.add('wrap-input-empty', 'animated', 'shake')
        setTimeout( () => {
          wrapUser.classList.remove('animated', 'shake')
          wrapPassword.classList.remove('animated', 'shake')
        },1000 )
        loading = 'false'
        handleVisibleLoading()
      } else if (type == 'Exito') {
        const {acceso, ce, cve_operativa, nombre, tramo_control, usuario} = data[0].datos.usuario
        dataUserObj = {
          acceso,
          ce,
          cveOperativa: cve_operativa,
          nombre,
          tipoSare: cualSare.value,
          tramoControl: cve_operativa,
          usuario,
          proyectoSesion:'',
        }
        loading = 'false'
        handleVisibleLoading() 
        inicializaCapas()
        //localStorage.setItem("dataUserObj", JSON.stringify(dataUserObj))
        card.style.transform = 'rotatex(180deg)'
        //window.location.href =  './index.html'
      }
      
    }, 
    () => {}
  )
}



// validar enter al logear
const handlePressEnter = (e) => {
  tecla = (document.all) ? e.keyCode : e.which;
  tecla == 13 ? handleClickValidaUsuario(e) : false
}

//validar loading visible
const handleVisibleLoading = () =>{
  const wrapLoading = document.getElementById('wrap-loading')
  loading == 'true' ? wrapLoading.style.display = 'block' : wrapLoading.style.display = 'none'
}

// FUNCIÓN FORMATO SELECT
const selectListCss = () => {
  var wrapSelect, i, j, selectSare, divSelect, divSelectItems, divOption

  /*look for any elements with the class "custom-select":*/
  wrapSelect = document.getElementsByClassName("custom-select")

  for (i = 0; i < wrapSelect.length; i++) {
    selectSare = wrapSelect[i].getElementsByTagName("select")[0]
    
    /*for each element, create a new DIV that will act as the selected item:*/
    divSelect = document.createElement("DIV")
    divSelect.setAttribute("class", "select-selected")
    divSelect.setAttribute('id','id-select-sare')
    divSelect.innerHTML = selectSare.options[selectSare.selectedIndex].innerHTML
    wrapSelect[i].appendChild(divSelect)

    /*for each element, create a new DIV that will contain the option list:*/
    divSelectItems = document.createElement("DIV")
    divSelectItems.setAttribute("class", "select-items select-hide")

    for (j = 1; j < selectSare.length; j++) {
      /*for each option in the original select element, create a new DIV that will act as an option item:*/
      divOption = document.createElement("DIV");
      divOption.innerHTML = selectSare.options[j].innerHTML
      divOption.addEventListener("click", function(e) {
        
        /*when an item is clicked, update the original select box,and the selected item:*/
        var y, i, k, s, h
        s = this.parentNode.parentNode.getElementsByTagName("select")[0]
        h = this.parentNode.previousSibling
        for (i = 0; i < s.length; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i
            h.innerHTML = this.innerHTML
            y = this.parentNode.getElementsByClassName("same-as-selected")
            for (k = 0; k < y.length; k++) {
              y[k].removeAttribute("class")
            }
            this.setAttribute("class", "same-as-selected")
            break
          }
        }
        h.click()
      })
      divSelectItems.appendChild(divOption)
    }

    wrapSelect[i].appendChild(divSelectItems)
    divSelect.addEventListener("click", function(e) {
      /*when the select box is clicked, close any other select boxes, and open/close the current select box:*/
      e.stopPropagation()
      closeAllSelect(this)
      this.nextSibling.classList.toggle("select-hide")
      this.classList.toggle("select-arrow-active")
    })
  }

  function closeAllSelect(elmnt) {
    /*a function that will close all select boxes in the document,except the current select box:*/
    var x, y, i, arrNo = []
    x = document.getElementsByClassName("select-items")
    y = document.getElementsByClassName("select-selected")
    for (i = 0; i < y.length; i++) {
      if (elmnt == y[i]) {
        arrNo.push(i)
      } else {
        y[i].classList.remove("select-arrow-active")
      }
    }
    for (i = 0; i < x.length; i++) {
      if (arrNo.indexOf(i)) {
        x[i].classList.add("select-hide")
      }
    }
  }
  /*if the user clicks anywhere outside the select box, then close all select boxes:*/
  document.addEventListener("click", closeAllSelect)
}
//  END FUNCIÓN FORMATO SELECT

// FUNCIÓN ALERTA
const alertToastLogin = title => {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000
  });
  
  Toast.fire({
    type: 'error',
    title
  })
}
