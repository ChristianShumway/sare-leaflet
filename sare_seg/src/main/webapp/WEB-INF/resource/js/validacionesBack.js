/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const addInitialCapas=()=>{
switch(dataUserFromLoginLocalStorage.proyecto){
        case 1:
            layersSARE = ['c100', 'c110', 'wdenue']
            break;
        case 5:
            layersSARE = ['c100', 'c101a', 'wdenue']
            break;
    }
    var idWms = urlServices['map'].label
    MDM6('setParams', { layer: idWms, params: { 'layers': layersSARE, 'EDO': '00' } })
}


const servicevalidaobjform = (object,obj) =>{
    var regresa=false
    sendAJAX(urlServices['servicevalidaobjForm'].url, 
  {
    'obj': object,
    'objrequest':obj
  }, 
  urlServices['servicevalidaobjForm'].type, 
  data => {
    if (data[0].operation) {
      if (data[0].datos.mensaje.type === "false") {
          //alertToastForm(data[0].datos.mensaje.messages, 'error')
          const { id, name, title, key } = JSON.parse(data[0].datos.datos)
          objForm.map(obj => {
            const wrapTitle = document.getElementById(obj.title)
            const element=document.getElementById(obj.id)
            let visible = wrapTitle.dataset.visible
            element.style.borderColor = '#eeeeee'
            visible != 'hide' ? handleVisibleForm(key) : false
            if (wrapTitle.classList.contains('error')) wrapTitle.classList.remove('error')    
            })
            handleActionTargetRef()
          showelementwithmistakeform(data[0].datos.mensaje.messages,id,name,title,key)
          regresa = true
      }
      else {
            const { id, name, title, key } = JSON.parse(data[0].datos.datos)
            const wrapTitle = document.getElementById(title)
            const elemento=document.getElementById(id)
            let visible = wrapTitle.dataset.visible
            elemento.style.borderColor = '#eeeeee'
            wrapTitle.classList.remove('error')
            visible != 'hide' ? handleVisibleForm(key) : false
        regresa= false
      }
    }
  }, () => { })
  return regresa
}



