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
          Swal.fire
          ({
            position: 'bottom-end',
            type: 'warning',
            title: data[0].datos.mensaje.messages,
            showConfirmButton: false,
            timer: 3000
          })
          return true
      }
      else {
        return false
      }
    }
  }, () => { })
}



