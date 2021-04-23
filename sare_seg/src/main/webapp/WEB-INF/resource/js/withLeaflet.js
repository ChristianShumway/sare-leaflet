
// let layersSARE = ['c100', 'c101a', 'wdenue'] //capas para el masivo

// var mymap = L.map('mapid', { crs: L.CRS.EPSG900913 /*,minZoom: 0,maxZoom: 22*/ }).setView([19.4978, -99.1269], 6);
// var mymap = L.map('mapid').setView([51.505, -0.09], 13);

// L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiY2hyaXN0aWFuc2h1bXdheSIsImEiOiJja2tzbHc1eDEwbjE2MndxbXViZmVlb3o2In0.wx5i6lADTpznAjvPdO-7Gw', {
//   attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
//   maxZoom: 18,
//   id: 'mapbox/streets-v11',
//   tileSize: 512,
//   zoomOffset: -1,
//   accessToken: 'your.mapbox.access.token'
// }).addTo(mymap);

var marker;
var wmsLayer = L.tileLayer.wms('http://ows.mundialis.de/services/service?', {
    layers: 'TOPO-OSM-WMS',
    sphericalMercator: true
})//.addTo(map);

var wmsLayerM = L.tileLayer.wms('http://gaia.inegi.org.mx/NLB/tunnel/wms/wms61?', {
    layers: 'Hipsografico,MGE',
    transparent: true,
    format: 'image/png',
    //cql_filter:"ambito='U'",
    id: 'xpain.test-cach',
    useCache: false,
    crossOrigin: false,
    sphericalMercator: false
})//.addTo(map);

var wmsLayerBase1 = L.tileLayer.wms('https://censo2020.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseHipsografico',
//crs:'4326'
    transparent: false,
//format: 'image/jpeg',
// //cql_filter:"ambito='U'",
//id: 'xpain.test-cach',
//useCache: true,
//crossOrigin: false,
    tiled: true
//sphericalMercator: false,
});
var wmsLayerBase5 = L.tileLayer('http://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/${z}/${y}/${x}', {
    img: '../../resources/img/mapaBase/Esri.jpg',
    //tms: true
});
var wmsLayerBase6 = L.tileLayer('resources/img/mapaBase/Osm.jpg', {
    //img: '../../resources/img/mapaBase/Esri.jpg',
    tms: true
});

var wmsLayerBase2 = L.tileLayer.wms('https://gaia.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseTopograficov61_sinsombreado',
//crs:'4326'
    transparent: false,
//format: 'image/jpeg',
// //cql_filter:"ambito='U'",
//id: 'xpain.test-cach',
//useCache: true,
//crossOrigin: false,
    tiled: true
//sphericalMercator: false,
});

var wmsLayerBase3 = L.tileLayer.wms('https://gaia.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseTopograficov61_sinsombreado_gris',
//crs:'4326'
    transparent: true,
    format: 'image/jpeg',
// //cql_filter:"ambito='U'",
//id: 'xpain.test-cach',
    useCache: true,
//crossOrigin: false,
    tiled: true,
    sphericalMercator: false,
});
var wmsLayerBase4 = L.tileLayer.wms('http://gaiamapas1.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseOrtofoto',
//crs:'4326'
    transparent: false,
//format: 'image/jpeg',
// //cql_filter:"ambito='U'",
//id: 'xpain.test-cach',
//useCache: true,
//crossOrigin: false,
    tiled: true
//sphericalMercator: false,
});

var wmsLayerSare = L.tileLayer.wms('https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_UEEPA_2020.map', {
    layers: 'c103,c102,c100,c101a,wdenue,c103r,c107,c107r,c108',
    transparent: true,
    format: 'image/png',
    // //cql_filter:"ambito='U'",
    id: 'xpain.test-cach',
    useCache: true,
    crossOrigin: false,
    sphericalMercator: true,
    EDO: '00',
    tiled:true
});

//var wmsLayerSareB2 = L.tileLayer.wms('https://gaia.inegi.org.mx/mdmCache/service/wms?', {
//    layers: 'MapaBaseTopograficov61_sinsombreado',
//    transparent: false,
//    label: 'Topogr&aacute;fico - INEGI',
//    img: '../../resources/img/mapaBase/Wms.jpg',
//    format: 'image/png',
//    // //cql_filter:"ambito='U'",
//    id: 'xpain.test-cach',
//    useCache: true,
//    crossOrigin: false,
//    sphericalMercator: true,
//    EDO: '00',
//});


var crs = new L.Proj.CRS(
        'EPSG:900913',
        // '+proj=utm +zone=33 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs',
        '+proj=merc +a=6378137 +b=6378137 +lat_ts=0 +lon_0=0 +x_0=0 +y_0=0 +k=1 +units=m +nadgrids=@null +wktext +no_defs',
        {
            //
            //resolutions: [8192, 4096, 2048, 1024, 512, 256, 128],
            resolutions: [8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0],
            // resolutions: [8192, 4096, 2048], // 3 example zoom level resolutions
            origin: [0, 0]
        }
);

var crs2 = new L.Proj.CRS(
        'EPSG:3857',
        // '+proj=utm +zone=33 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs',
        '+proj=merc +a=6378137 +b=6378137 +lat_ts=0 +lon_0=0 +x_0=0 +y_0=0 +k=1 +units=m +nadgrids=@null +wktext +no_defs',
        {
            //
            resolutions: [8192, 4096, 2048, 1024, 512, 256, 128],
            //resolutions: [8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0],
            // resolutions: [8192, 4096, 2048], // 3 example zoom level resolutions
            origin: [0, 0]
        }
);

console.log(crs);

var map = L.map('mapid', {
    center: [21.541, -102.034], //[-17, -67],
    //[21.541, -102.034], 
    zoom: 5,
    maxZoom: 18,
    layers: [wmsLayerBase2, wmsLayerSare],
    crs: L.CRS.EPSG900913,
    //crs:crs,
    continuousWorld: false,
    worldCopyJump: false,
    //scrollWheelZoom: false
});
map.on('click', function (e) {

    if (marker !== undefined) {
        map.removeLayer(marker)
    }
    marker = L.marker([e.latlng.lat, e.latlng.lng]).addTo(map);

    // alert(e.latlng);
});
window.addEventListener('keydown', function (event)
{
    if (event.ctrlKey == true)
    {
        map.scrollWheelZoom.enable();
        event.preventDefault();
        /*if (event.originalEvent.detail > 0) {
         console.log('Down');
         } else {
         console.log('Up');
         }*/
    }
});

window.addEventListener('mousewheel', function (e) {
    if (event.ctrlKey == true)
    {
        map.scrollWheelZoom.enable();
        event.preventDefault();
    }
    if (map.scrollWheelZoom.enabled()) {
        //map.scrollWheelZoom.disable();
    } else {
        alert("presion ctrl para hacer zoom")
        //map.scrollWheelZoom.enable();
    }
},
        false);
console.log("map crs: " + map.options.crs.code);


var baseMaps = {
    "MGE": wmsLayerM,
    "TOPO-OSM-WMS": wmsLayer,
    "wdenue": wmsLayerSare,
    "Hipsogr&aacute;fico - INEGI": wmsLayerBase1,
    "Topogr&aacute;fico - INEGI": wmsLayerBase2,
    "Topogr&aacute;fico gris - INEGI": wmsLayerBase3,
    "Ortofotos - INEGI": wmsLayerBase4,
    "Esri": wmsLayerBase5,
    "Osm": wmsLayerBase6
};
var overlays = {
    "Sare": wmsLayerSare
};
//var imageUrl = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Sydney_Opera_House_-_Dec_2008.jpg/1024px-Sydney_Opera_House_-_Dec_2008.jpg',
  //      imageBounds = [center, [-35.8650, 154.2094]];

L.control.layers(baseMaps,overlays).addTo(map);

