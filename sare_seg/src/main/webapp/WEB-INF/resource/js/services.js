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
    serviceCP:{
        url: 'getCP.do',
        type: 'POST'
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
        url: 'Reportes.do',
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
    },
    getListadoUnidadesEconomicas: {
        url: 'getListadoUnidadesEconomicas.do',
        label: 'getListadoUnidadesEconomicas.do',
        type: 'GET'
    }
    
};
