/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const servicevalidaobjform = (object,obj) =>{
    sendAJAX(urlServices['servicevalidaobjForm'].url, 
  {
    'obj': object,
    'objrequest':obj
  }, 
  urlServices['servicevalidaobjForm'].type, 
  data => {
    if (data[0].operation) {
      if (data[0].datos.mensaje.type === "false") {
          alertToastForm(data[0].datos.mensaje.messages, 'error')
          const { id, name, title, key } = JSON.parse(data[0].datos.datos)
          showelementwithmistakeform(data[0].datos.mensaje.messages,id,name,title,key)
          return true
      }
      else {
            const { id, name, title, key } = JSON.parse(data[0].datos.datos)
            const wrapTitle = document.getElementById(title)
            const elemento=document.getElementById(id)
            let visible = wrapTitle.dataset.visible
            elemento.style.borderColor = '#eeeeee'
            wrapTitle.classList.remove('error')
            visible != 'hide' ? handleVisibleForm(key) : false
        return false
      }
    }
  }, () => { })
}



