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
    id:'e17_DESC',
    key: 'referencia',
    name:'e17_DESC',
    title:'title-referencia'
  },
  {
    id:'e03',
    key: 'ubicacion-geografica',
    name:'e03',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e03N',
    key: 'ubicacion-geografica',
    name:'e03N',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e04',
    key: 'ubicacion-geografica',
    name:'e04',
    title:'title-ubicacion-geografica',
  },
  {
    id:'e04N',
    key: 'ubicacion-geografica',
    name:'e04N',
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
  {
    id:'e10_A',
    key: 'entre-vialidades',
    name:'Nombre de vialidad 1',
    title:'title-entre-vialidades',
  },
  {
    id:'e10_B',
    key: 'entre-vialidades',
    name:'Nombre de vialidad 2',
    title:'title-entre-vialidades',
  },
  {
    id:'e10_C',
    key: 'calle-posterior',
    name:'Nombre de vialidad',
    title:'title-calle-posterior',
  },
]

const objFormRural=[
   {
    id:'descrubic',
    key: 'calle-posterior',
    name:'Descripción de la ubicación del establecimiento',
    title:'title-calle-posterior'
  },
  {
    id:'e11',
    key: 'domicilio',
    name:'número exterior',
    title:'title-domicilio'
  },   
]
const objFormCentrocomercial=[
   {
    id:'E12p',
    key: 'edificio',
    name:'Piso del edificio',
    title:'title-edificio'
  }, 
  {
    id:'E12',
    key: 'edificio',
    name:'Numero de Edificio',
    title:'title-edificio'
  }, 
  {
    id:'e19',
    key: 'edificio',
    name:'Nombre Edificio',
    title:'title-edificio'
  }, 
  {
    id:'e20',
    key: 'edificio',
    name:'Numero de Local',
    title:'title-edificio'
  }, 
  {
    id:'e13',
    key: 'domicilio',
    name:'Numero interior',
    title:'title-domicilio'
  }, 
  {
    id:'e13_a',
    key: 'domicilio',
    name:'Letra interior',
    title:'title-domicilio'
  }, 
  {
    id:'tipo_E19',
    key: 'edificio',
    name:'Tipo de corredor o centro comercial',
    title:'title-edificio'
  }, 
]

const inputsEditables = [
  {
    id:'tipo_e10n',
  },
  
  {
    id:'e10',
  },
  {
    id:'e10_e',
  },
  {
    id:'e11',
  },
  {
    id:'e11_a',
  },
  {
    id:'e13',
  },
  {
    id:'e13_a',
  },
  {
    id:'e14_A',
  },
  {
    id:'e14',
  },
  {
    id:'tipo_e10_an',
  },
  {
    id:'tipo_e10_bn',
  },
  {
    id:'tipo_e10_cn',
  },
  {
    id:'descrubic',
  },
  {
    id:'E12',
  },
  {
    id:'e19',
  },
  {
    id:'E12p',
  },
  {
    id:'e20',
  },
  {
    id:'id_UE',
  },
]

let inputsVialidadesClean=[
  {
    id:'e10_A',
  },
  {
    id:'e10_B',
  },
  {
    id:'e10_C',
  },
]

let inputsVialidades=[
   {
    nombre:'tipo_e10n' 
   },
   {
    nombre:'e10' 
   },
   {
    nombre:'tipo_e10_an'
   },
   {
    nombre:'tipo_e10_bn'   
   },
   {
    nombre:'tipo_e10_cn'  
   },
]

let inputsByWrap = {}

const arrayErrores = [
  {
    value: 'b1',
    mensaje: 'Los parámetros tramo de control y/o clave unica no tienen datos o son incorrectos'
  },
  {
    value: 'b2',
    mensaje: 'La clave única no existe o no está disponible para su tramo de control'
  },
  {
    value: 'b2a',
    mensaje: 'La clave única ya se encuentra registrada previamente'
  },
  {
    value: 'b3',
    mensaje: 'Error en la busqueda del acercamiento'
  },
  {
    value: 'b4',
    mensaje: 'La UE seleccionada ya fue georreferiada anteriormente'
  },
  {
    value: 'b5',
    mensaje: 'La UE no tiene estatus de punteo'
  },
  {
    value: 'b6',
    mensaje: 'Sin coordenadas'
  },
  {
    value: 'b10',
    mensaje: 'No hay sesión activa'
  },
]

const inputsClean = [
  {
    id:'id_UE',
  },
  {
    id:'origen',
  },
  {
    id:'c154',
  },
  {
    id:'e08',
  },
  {
    id:'e09',
  },
  {
    id:'e17_DESC',
  },
  {
    id:'e03',
  },
  {
    id:'e03N',
  },
  {
    id:'e04',
  },
  {
    id:'e04N',
  },
  {
    id:'e05',
  },
  {
    id:'e05N',
  },
  {
    id:'e06',
  },
  {
    id:'e07',
  },
  {
    id:'tipo_e10n',
  },
  
  {
    id:'e10',
  },
  {
    id:'e10_e',
  },
  {
    id:'e11',
  },
  {
    id:'e11_a',
  },
  {
    id:'e13',
  },
  {
    id:'e13_a',
  },
  {
    id:'e14_A',
  },
  {
    id:'e14',
  },
  {
    id:'tipo_e10_an',
  },
  {
    id:'tipo_e10_bn',
  },
  {
    id:'tipo_e10_cn',
  },
  {
    id:'descrubic',
  },
  {
    id:'E12',
  },
  {
    id:'e19',
  },
  {
    id:'E12p',
  },
  {
    id:'e20',
  },
  {
    id:'id_UE',
  },
  {
    id:'e10_A',
  },
  {
    id:'e10_B',
  },
  {
    id:'e10_C',
  },
  {
    id:'clave-busqueda',
  },
  
  
]