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
var lat=21.541, long=-102.034;
var cities = L.layerGroup();
var wmsLayer = L.tileLayer.wms('http://ows.mundialis.de/services/service?', {
    layers: 'TOPO-OSM-WMS',
    sphericalMercator: true,
    maxZoom: 21,
    maxNativeZoom: 19,
})//.addTo(map);

var wmsLayerM = L.tileLayer.wms('http://gaia.inegi.org.mx/NLB/tunnel/wms/wms61?', {
    layers: 'Hipsografico,MGE',
    transparent: true,
    maxzoom: 21,
    format: 'image/png',
    //cql_filter:"ambito='U'",
    id: 'xpain.test-cach',
    useCache: false,
    crossOrigin: false,
    sphericalMercator: false,
    maxZoom: 21,
    maxNativeZoom: 19,
})//.addTo(map);

var wmsLayerBase1 = L.tileLayer.wms('https://censo2020.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseHipsografico',
//crs:'4326'
    transparent: false,
    maxZoom: 21,
    maxNativeZoom: 19,
//format: 'image/jpeg',
// //cql_filter:"ambito='U'",
//id: 'xpain.test-cach',
//useCache: true,
//crossOrigin: false,
    tiled: true
//sphericalMercator: false,
});
var wmsLayerBase5 = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}', {
    maxZoom: 21,
    maxNativeZoom: 19,
    //img: 'resources/img/mapaBase/Esri.jpg',

});
var wmsLayerBase6 = L.tileLayer('https://b.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 21,
    maxNativeZoom: 19,
    // tms: true,

});

var wmsLayerBase7 = new L.BingLayer("At-Y-dJe-yHOoSMPmSuTJD5rRE_oltqeTmSYpMrLLYv-ni4moE-Fe1y8OWiNwZVT", {type: 'AerialWithLabels', maxZoom: 21,
    maxNativeZoom: 19});



var wmsLayerBase8 = L.tileLayer('http://mt0.google.com/vt/lyrs=s&hl=en&x={x}&y={y}&z={z}', {
    // tms: true,
    maxZoom: 21,
    maxNativeZoom: 19,
});
var wmsLayerBase2 = L.tileLayer.wms('https://gaia.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseTopograficov61_sinsombreado',
//crs:'4326'
    maxZoom: 21,
    maxNativeZoom: 19,
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
    maxZoom: 21,
    maxNativeZoom: 19,
    useCache: true,
//crossOrigin: false,
    tiled: true,
    sphericalMercator: false,
});
var wmsLayerBase4 = L.tileLayer.wms('http://gaiamapas1.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseOrtofoto',
    maxZoom: 21,
    maxNativeZoom: 19,
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

var wmsLayerSare = L.singleTile('https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_UEEPA_2020.map', {
    layers: 'c103,c102,c100,c101a,wdenue,c103r,c107,c107r,c108',
    transparent: true,
    format: 'image/png',
    maxZoom: 21,
    maxNativeZoom: 19,
    // //cql_filter:"ambito='U'",
    id: 'xpain.test-cach',
    useCache: true,
    crossOrigin: false,
    sphericalMercator: true,
    EDO: '00',
    tiled: true
});

var wmsLayerSareWithoutlayer = L.singleTile('https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_UEEPA_2020.map', {
    layers: 'c103,c102,c100,c101a,c103r,c107,c107r,c108',
    transparent: true,
    format: 'image/png',
    maxZoom: 21,
    maxNativeZoom: 19,
    // //cql_filter:"ambito='U'",
    id: 'xpain.test-cach',
    useCache: true,
    crossOrigin: false,
    sphericalMercator: true,
    EDO: '00',
    tiled: true
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
            // resolutions: [8192, 4096, 2048, 1024, 512, 256, 128],
            //resolutions: [8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0],
            // resolutions: [8192, 4096, 2048], // 3 example zoom level resolutions
            origin: [0, 0],
            // resolutions: [8192, 4096, 2048, 1024, 512, 256, 128],
            resolutions: [8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0],
            origin: [0, 0]
        }
);


var map = L.map('mapid', {
    center: [lat, long], //[-17, -67],
    //[21.541, -102.034], 
    zoom: 5,
    maxZoom: 21,
    minZoom: 5,
    layers: [wmsLayerBase2, wmsLayerSare],
    crs: L.CRS.EPSG900913,
    zoomControl: true,
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
    lat = e.latlng.lat;
    long = e.latlng.lng;
    identify(e.latlng)
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

/* var bing = new L.BingLayer("At-Y-dJe-yHOoSMPmSuTJD5rRE_oltqeTmSYpMrLLYv-ni4moE-Fe1y8OWiNwZVT");
 map.addLayer(bing);*/

var baseMaps = {
    "MGE": wmsLayerM,
    "TOPO-OSM-WMS": wmsLayer,
    "wdenue": wmsLayerSare,
    "wdenue1": wmsLayerSareWithoutlayer,
    "Hipsogr&aacute;fico - INEGI": wmsLayerBase1,
    "Topogr&aacute;fico - INEGI": wmsLayerBase2,
    "Topogr&aacute;fico gris - INEGI": wmsLayerBase3,
    "Ortofotos - INEGI": wmsLayerBase4,
    "Esri": wmsLayerBase5,
    "Osm": wmsLayerBase6,
    "Bing": wmsLayerBase7,
    "Google Satelite": wmsLayerBase8
};
var overlays = {
    "Sare": wmsLayerSare
};
//var imageUrl = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Sydney_Opera_House_-_Dec_2008.jpg/1024px-Sydney_Opera_House_-_Dec_2008.jpg',
//      imageBounds = [center, [-35.8650, 154.2094]];

var lcontrol = L.control.layers(baseMaps, overlays).addTo(map);


var formData = {
    q: "aguascalientes",
    point1: "3.2919132745585262,-143.6874999924407",
    point2: "40.74561102796926,-59.312500007554476",
    pt: "23.320084961929044, -101.4999999999976"
};





function busqueda() {
    $.ajax({
        url: "http://gaia.inegi.org.mx/mdm_searchengine/search",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function (response, textStatus, jqXHR) {
            console.log(response.data);
        }
    });
}

/*
 const busqueda = () => {
 //map.flyTo([21.879120, -102.303263], 17)
 sendAJAX(
 "http://gaia.inegi.org.mx/mdm_searchengine/search", 
 JSON.stringify(formData), 
 'POST', 
 data => { 
 // wrapUser.classList.remove('wrap-input-empty')
 // wrapPassword.classList.remove('wrap-input-empty')
 console.log(data[0].datos)
 
 }, 
 () => {}
 )
 }*/
map.on('zoomend', function () {
    //alert("hola")

});
const handleChangeOptions = option => {
    const title = document.getElementById(`option-${option}`)
    const idWms = urlServices['map'].label;
    const checkBox = document.getElementById(`${option}`)
    checkBox.checked ? title.classList.add('option-active') : title.classList.remove('option-active')
    if (option == "c101") {
        addCapas(checkBox);
    } else {
        addLayerEconomicas(checkBox, option);
    }
    if (option == 'wdenue' && checkBox.checked == false) {
        removeLayer("wmslayerSare", "wmsLayerSareWithoutlayer")
    } else {
        if (checkBox.checked == true) {
            removeLayer("wmsLayerSareWithoutlayer", "wmslayerSare")
        }
    }


}
var capaDenueRemove = {
    "wmslayerSare": wmsLayerSare,
    "wmsLayerSareWithoutlayer": wmsLayerSareWithoutlayer
}
function removeLayer(caparemover, capaagregar) {
    //map.removeLayer(capaDenueRemove[caparemover]);
    let zoom=map.getZoom()
    map.remove()
    var container = L.DomUtil.get('map');
      if(container != null){
        container._leaflet_id = null;
      }
     map = L.map('mapid', {
        center: [lat, long], //[-17, -67],
        //[21.541, -102.034], 
        zoom: zoom,
        maxZoom: 21,
        minZoom: 5,
        layers: [wmsLayerBase2, capaDenueRemove[capaagregar]],
        crs: L.CRS.EPSG900913,
        zoomControl: true,
        //crs:crs,
        continuousWorld: false,
        worldCopyJump: false,
        //scrollWheelZoom: false
    });
}