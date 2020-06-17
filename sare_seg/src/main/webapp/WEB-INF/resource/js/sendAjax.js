var sendAJAX = function (URL, data, type, myfunction, bs, withCredentials) {
  if (type !== null && type !== '') {
      type = type.toUpperCase();
  }
  if (URL === null && URL === '') {
      return '';
  }
  var credential = false;
  if (typeof withCredentials !== 'undefined') {
      credential = false || withCredentials;

  }
  $.ajax({
      type: type.toString(),
      url: URL,
      contentType: 'application/x-www-form-urlencoded; charset=UTF-8', //multipart/form-data, or text/plain
      dataType: 'json', //(xml, json, script, or html
      cache: false,
      async: true,
      xhrFields: {withCredentials: credential},
      crossDomain: true, //false si es mismo dominio, true  para forzar el uso de cross domain usar sonp
      data: data,
      beforeSend: function () {
          if (bs !== null && bs !== '') {
              bs(); //beforeSend function 
          }
      },
      success: function (dataJSON) {            
          var dataBack = [{}];
          dataBack[0].operation = true;
          dataBack[0].datos = dataJSON;
          myfunction(dataBack);

      },
      error: function (xhr, ajaxOptions, thrownError, jqXHR) {
          var dataBack = [{}];
          dataBack[0].operation = false;
          dataBack[0].messages = [thrownError];//['Error de conexión'];
          myfunction(dataBack);
      }

  });
};

var loadTemplate = function (idTarget, template, functionBack) {
  if (typeof (idTarget) !== 'undefined' && typeof (template) !== 'undefined') {
      $.get(template, function (t) {
          if (typeof (functionBack) === 'function') {
              $('#' + idTarget).html(t);
              functionBack(t)
          } else {
              $('#' + idTarget).html(t);
          }
      });
  }
};
