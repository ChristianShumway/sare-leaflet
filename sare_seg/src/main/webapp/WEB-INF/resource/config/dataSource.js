var sourcesConfig = {
    proyName: 'mdm6',
    identify: {
        url: 'https://gaia.inegi.org.mx/NLB/tunnel/map/identify',
        type: 'POST',
		contentType : "application/json",
		dataType: "json",
        // resolution:0
    },
    bufferLayer: {
        //url:'http://10.28.19.73:8080/TableAliasV60/consultaTotales',
        url: 'https://gaia.inegi.org.mx/NLB/tunnel/map/totals',
        contentType: "application/json",
        type: 'POST',
        dataType: "json"
    },
    layersSeaIde: {
        url: 'https://gaia.inegi.org.mx/NLB/tunnel/map/fieldtypes',
        contentType : "application/json",
        type: 'POST',
        dataType: "json"
    },
    identifyDetail: {
        url: 'https://gaia.inegi.org.mx/NLB/tunnel/map/query',
        type: 'POST',
        contentType: "application/json",
        dataType: "json"
    },
    mainSearch: {
        url: 'https://gaia.inegi.org.mx/mdmSearchEngine/autocomplete/select',
        //url:'http://gaia.inegi.org.mx/mdmSearchEngine/autocomplete/select',
        //url:'http://10.1.30.101:9090/solr/primera-mano/select',
        //field:'busqueda',
        sfield: 'locacion',
        type: 'POST',
        dataType: "jsonp",
        jsonp: 'json.wrf'
    },
    crossSearch: {
        url: 'https://gaia.inegi.org.mx/NLB/tunnel/TableAliasV60/busqueda',
        contentType: "application/json; charset=utf-8",
        type: 'POST',
        dataType: "json"
    },
    deepSearch: {
        url: 'https://gaia.inegi.org.mx/mdmSearchEngine/busq-calles/shard',
        field: 'busqueda',
        type: 'POST',
        dataType: "jsonp",
        jsonp: 'json.wrf'
    },
    denue: {
        url: 'http://10.1.30.101:9090/solr/denue/select',
        field: 'busqueda',
        type: 'POST',
        dataType: "jsonp",
        jsonp: 'json.wrf'
    },
    category: {
        id: 'AR',
        name: '&Aacute;reas naturales',
        url: 'https://gaia.inegi.org.mx/mdmSearchEngine/cuencasHidro/shard',
        field: 'busqueda',
        type: 'POST',
        dataType: "jsonp",
        jsonp: 'json.wrf',
        facetField: 'descripcion'
                /*toponimosAR:{
                 id:'AR',
                 name:'&Aacute;reas naturales',
                 url:'http://10.1.30.101:9090/solr/cuencasHidro/shard',
                 field:'busqueda',
                 type: 'POST',
                 dataType: "jsonp",
                 jsonp:'json.wrf',
                 facetField:'descripcion'
                 },
                 toponimosRH:{
                 id:'RH',
                 name:'Cuencas hidrol&oacute;gicas',
                 url:'http://10.1.30.101:9090/solr/toponimosRH/select',
                 field:'busqueda',
                 type: 'POST',
                 dataType: "jsonp",
                 jsonp:'json.wrf'
                 }*/
    },
    kml: {
        save: 'https://gaia.inegi.org.mx/mdmexport/kml/download',
        read: 'https://gaia.inegi.org.mx/mdmexport/kml/upload'
    },
    geometry: {
        store: 'https://gaia.inegi.org.mx/NLB/tunnel/map/geometry',
        addBuffer: 'https://gaia.inegi.org.mx/NLB/tunnel/map/buffer'
    },
    timeLine: '',
    school: '',
    leyendUrl: 'https://gaia.inegi.org.mx/NLB/tunnel/wms/mdm6wms?map=/opt/map/mdm60/mdm6leyenda.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=',
    //leyendUrl: 'http://10.106.12.2/cgi-bin/mapserv.exe?map=../../map/vivienda.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=',
    synonyms: {
        list: {
            farmacia: ['botica', 'drogeria'],
            banco: ['cajero'],
            restaurant: ['bar', 'merendero'],
            hospital: ['clinica'],
            hotel: ['motel', 'posada']
        }
    },
    logging: ''
};
   
