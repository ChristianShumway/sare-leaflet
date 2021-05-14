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
var lat = 21.541, long = -102.034;
var cities = L.layerGroup();
var boundsZoom,zoom=5;
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
    transparent: false,
    maxZoom: 21,
    maxNativeZoom: 19,
    tiled: true
});
var wmsLayerBase5 = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}', {
    maxZoom: 21,
    maxNativeZoom: 19,

});
var wmsLayerBase6 = L.tileLayer('https://b.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 21,
    maxNativeZoom: 19,

});

var wmsLayerBase7 = new L.BingLayer("At-Y-dJe-yHOoSMPmSuTJD5rRE_oltqeTmSYpMrLLYv-ni4moE-Fe1y8OWiNwZVT", {type: 'AerialWithLabels', maxZoom: 21,
    maxNativeZoom: 19});



var wmsLayerBase8 = L.tileLayer('http://mt0.google.com/vt/lyrs=s&hl=en&x={x}&y={y}&z={z}', {
    maxZoom: 21,
    maxNativeZoom: 19,
});
var wmsLayerBase2 = L.tileLayer.wms('https://gaia.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseTopograficov61_sinsombreado',
    maxZoom: 21,
    maxNativeZoom: 19,
    transparent: false,
    tiled: true
});

var wmsLayerBase3 = L.tileLayer.wms('https://gaia.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseTopograficov61_sinsombreado_gris',
    transparent: true,
    format: 'image/jpeg',
    maxZoom: 21,
    maxNativeZoom: 19,
    useCache: true,
    tiled: true,
    sphericalMercator: false,
});
var wmsLayerBase4 = L.tileLayer.wms('http://gaiamapas1.inegi.org.mx/mdmCache/service/wms?', {
    layers: 'MapaBaseOrtofoto',
    maxZoom: 21,
    maxNativeZoom: 19,
    transparent: false,
    tiled: true
});

var capas = {c103: "c103", c102: "c102", c100: "c100", c101a: "c101a", wdenue: "wdenue", c104: "c104", c103r: "c103r", c107: "c107", c107r: "c107r", c108: "c108"}
var layers = ["c103", "c102", "c100", "c101a", "wdenue", "c103r", "c107", "c107r", "c108"]


var wmsLayerSare = L.singleTile('https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_UEEPA_2020.map', {
    layers: layers,
    transparent: true,
    format: 'image/png',
    maxZoom: 21,
    maxNativeZoom: 19,
    id: 'xpain.test-cach',
    useCache: true,
    crossOrigin: false,
    sphericalMercator: true,
    EDO: '00',
    tiled: true
});


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


var map;
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
    if (option == 'wdenue') {
        if (checkBox.checked == false) {
            let pos = layers.indexOf('wdenue')
            layers.splice(pos, 1)
            pos = layers.indexOf('c104')
            layers.splice(pos, 1)
            chargeMap()
        } else {
            layers.push('wdenue')
            chargeMap()
        }
    } else {
        if (option == 'c104') {
            if (checkBox.checked == true) {
                layers.push('c104')
                chargeMap()
            } else {
                let pos = layers.indexOf('c104')
                layers.splice(pos, 1)
                chargeMap()
            }
        }
    }
}
function chargeMap() {
    //map.removeLayer(capaDenueRemove[caparemover]);
    wmsLayerSare = L.singleTile('https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_UEEPA_2020.map', {
        layers: layers,
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
    
    if (map) {
        zoom = map.getZoom()
        map.remove()
    } else {
        zoom = 5
    }

    var container = L.DomUtil.get('map');
    if (container != null) {
        container._leaflet_id = null;
    }
    map = L.map('mapid', {
        center: [lat, long], //[-17, -67],
        //[21.541, -102.034], 
        zoom: zoom,
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
    if (boundsZoom) {
        map.fitBounds(boundsZoom, {padding: [50, 50]});
    }

    map.on('click', function (e) {

        if (marker !== undefined) {
            map.removeLayer(marker)
        }
        if (zoom > 12) {
            marker = L.marker([e.latlng.lat, e.latlng.lng]).addTo(map);
            lat = e.latlng.lat;
            long = e.latlng.lng;
            identify(e.latlng)
        }
        // alert(e.latlng);
    });
    var baseMaps = {
        "MGE": wmsLayerM,
        "TOPO-OSM-WMS": wmsLayer,
        "wdenue": wmsLayerSare,
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
    map.on('baselayerchange', function (e) {
        wmsLayerBase2 = e.layer;
    });
    map.on('dblclick', function (e) {
        if (marker !== undefined) {
            map.removeLayer(marker)
        }
    });
//var imageUrl = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Sydney_Opera_House_-_Dec_2008.jpg/1024px-Sydney_Opera_House_-_Dec_2008.jpg',
//      imageBounds = [center, [-35.8650, 154.2094]];

    var lcontrol = L.control.layers(baseMaps, overlays).addTo(map);
    map.on('zoomend', function (e) {
        map = e.target,
                boundsZoom = map.getBounds();
        lat = boundsZoom.getNorthEast().lat;
        long = boundsZoom.getSouthWest().lng;
        // here i get southwest & northeast data how to get lat & lng & zoom level 
        console.log(boundsZoom);
        console.log(lat, long);
    });
}
