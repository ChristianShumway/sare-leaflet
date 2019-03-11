var urlServices = {
    /* map: {
     url: 'http://seguimientoce2014.inegi.org.mx/NLB/balancer.do?map=/opt/map/mdm60/ce2014.map&', //  prod
     //url: 'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/VISUALIZADOR_censo_poblacion_2018.map&transparent=true&', //dev CLAVE=00&LAYERS=cestatalr,cmunr,clocr,C109',// ,wdenue 200c,201c,202c,203c,203c1,203c2', 
     //url: 'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/VISUALIZADOR_censo_poblacion_2018.map&transparent=true&', //dev CLAVE=00&LAYERS=cestatalr,cmunr,clocr,C109',// ,wdenue 200c,201c,202c,203c,203c1,203c2', 
     label: 'economicos',
     type: 'Wms'
     }*/
    map: {
        url: 'https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SeguimientoCA2016.map', //PROD
//        url: 'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/SeguimientoCA2016.map',
        //url: 'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/mgn2018.map',
        label: 'ca',
        type: 'Wms'
    },
    mapBase: {
        url: 'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/pruebas_seguimiento_censo_poblacion_2019_base.map',
        label: "seguimiento_base",
        type: 'Wms'
    },
    mapAvance:{
       //url:'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/censo_poblacion_avance_operativo_2019.map' , //pruebas consuelo 
       url:'https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/censo_poblacion_avance_operativo_2019.map' , //produccion gaia
       label:"seguimiento_oper",
       type:'Wms'
    },
    serviceAvanceOperativo: {
        url: 'getAvanceOperativo.do',
        type: 'get'
    },
    serviceAvanceSemanalOperativo: {
        url: 'getAvanceSemanalOperativo.do',
        type: 'get'
    },
    serviceAvancePorEntidadOperativo: {
        url: 'getAvancePorEntidadOperativo.do',
        type: 'get'
    },
    serviceGetExtent: {
        url: 'getExtent.do',
        type: 'get'
    },
    serviceIdentifica: {
        url: 'getIdentifica.do',
        type: 'get'
    },
    serviceLogin: {
        url: 'getLogin.do',
        //url: 'http://10.106.12.23:8080/sisac_2018/getLogin.do',
        type: 'get'
    },
    serviceUnidadesActividad: {
        url: 'getUnidadesPorActividad.do',
        type: 'get'
    },
    serviceCascaronFiltro: {
        url: 'getCascaronFiltro.do',
        type: 'get'
    },
    serviceNivelDesagregacion: {
        url: 'getNivelDesagregacion.do',
        type: 'get'
    },
    serviceTramosControl: {
        url: 'getDataTramos.do',
        type: 'get'
    },
    serviceCascaronBusqueda: {
        url: 'getCascaronBusqueda.do',
        type: 'get'
    },
    logoutService: {
        url: 'logout.do',
        type: 'get'
    },
    serviceIdentifyStreetView: {
        url: 'getCoordsGeo.do',
        type: 'get'
    },
    serviceServiciosFiltro: {
        url: 'getServiciosFiltro.do',
        type: 'get'
    },
    filtroTcar: {
        url: 'getFiltroTcar.do',
        label: 'filtro',
        type: 'GET'
    },
    serviceReporte: {
        url: 'getReporte',
        label: 'reporte',
        type: 'GET'
    },
    serviceAvanceDiarioOperativo: {
        url: 'getAvanceDiarioOperativo.do',
        label: 'avancediario',
        type: 'GET'
    },
    serviceGetRanking: {
        url: 'getRanking.do',
        label: 'ranking',
        type: 'GET',
    },
    ServiceGetPersonalOcupado: {
        url: 'getPersonalOcupado.do',
        label: 'personal',
        type: 'GET',
    },    
    ServiceIngresos: {
        url: 'getIngresos.do',
        label: 'personal',
        type: 'GET',
    },
    validaSesion: {
        url: 'validaSesion.do',
        label: 'validarSesion',
        type: 'GET'
    },
    tarjetonRanking: {
        url: 'getTarjetonRanking.do',
        label: 'tarjetaRanking',
        type: 'GET'
    },
    serviceGetActividadPorSector: {
        url: 'getActividadPorSector.do',
        label: 'actividad',
        type: 'GET'
    },
    serviceGetDatosLeyenda: {
        url: 'getDatosLeyenda.do',
        label: 'actividad',
        type: 'GET'
    },
    servicePersonalOcupadoXClase: {
        url: 'getActividadPorSector.do',
        label: 'servicePersonalOcupadoXClase',
        type: 'GET'
    },
    getDatosClasesPorFiltro: {
        url: 'getDatosClasesPorFiltro.do',
        label: 'getDatosClasesPorFiltro',
        type: 'GET'
    }
};
