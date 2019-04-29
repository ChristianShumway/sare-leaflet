var urlServices = {
    map: {
        url: 'http://10.106.12.81:88/cgi-bin/mapserv.exe?map=../../map/SARE_RENEM_EGE_2018.map&', //  Consuelo
        //url: 'https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_RENEM_EGE_2018.map&',
        label: 'sare',
        type: 'Wms'
    },
    mapDENUE: {
        url: 'https://gaia.inegi.org.mx/NLB/tunnel/wms/mdm6wms?map=/opt/map/mdm60/mdm61vector.map&',
        label: 'denue',
        type: 'Wms'
    },
    mapLeyDENUE: {
        url: 'http://10.152.11.6/fcgi-bin/ms62/mapserv.exe?map=/opt/map/mdm60/mdm61texto.map&',
        label: 'ley_denue',
        type: 'Wms'
    },
    mapLeyMap: {
        //url: 'http://10.106.12.178/cgi-bin/mapserv.exe?map=../../map/SARE_2016_renem.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=c101',
        url: 'https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE2016.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=c101',
        label: 'ley_denue',
        type: 'Wms'
    },
    serviceIdentifyUE: {
        url: 'getListUEbyXY.do',
        type: 'get'
    },
    serviceIdentifyStreetView:{
        url: 'transformCoords.do',
        type: 'get'
    },
    serviceSearch: {
        url: 'getbusqueda.do',
        type: 'post'
    },
    serviceSaveUEAlter: {
        url: 'guardarUE.do',
        type: 'get'
    },
    getListadoUnidadesEconomicas: {  
        url: 'getListadoUnidadesEconomicas.do',  
        label: 'getListadoUnidadesEconomicas.do',  
        type: 'GET'  
    },   
    serviceCP:{
        url: 'getCP.do',
        type: 'POST'
    },
    serviceValCP:{
        url: 'validaCP.do',
        type: 'GET'
    },
    serviceCatalogoAsentamientos: {
        url: 'getCatAsentamientosHumanos.do',
        type: 'POST'
    },
    serviceIdentify: {
        url: 'getDatabyCoords.do',
        type: 'POST'
    },
    serviceLiberaClave: {
        url: 'liberacve.do',
        type: 'get'
    },
    serviceSaveUE: {
        url: 'guardarUE.do',
        type: 'POST'
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
        url: 'Login.do',
        //url: 'http://10.106.12.23:8080/sisac_2018/getLogin.do',
        type: 'get'
    },
    
    serviceReporte: {
        url: 'Reportes.do',
        label: 'reporte',
        type: 'GET'
    },
    
    validaSesion: {
        url: 'validaSesion.do',
        label: 'validarSesion',
        type: 'GET'
    },
    serviceCatalogoConjuntosComerciales: {
        url: 'getCatConjuntosComerciales.do',
        type: 'POST',
        label: 'catConjuntosComerciales'
    },
    serviceListaClavesBloqueadas: {
        url: 'getListadoUnidadesEconomicasBloqueadas.do',
        type: 'POST',
        label: 'ListadoUnidadesEconomicasBloqueadas'
    },
    serviceDesbloqueoClavesBloqueadas: {
        url: 'getListadoUnidadesEconomicasBloqueadas.do',
        type: 'POST',
        label: 'ListadoUnidadesEconomicasBloqueadas'
    }
};
