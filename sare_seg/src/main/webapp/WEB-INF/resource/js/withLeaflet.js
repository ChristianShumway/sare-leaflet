
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


var wmsLayer = L.tileLayer.wms('http://ows.mundialis.de/services/service?', {
  layers: 'TOPO-OSM-WMS',
  sphericalMercator: true
})//.addTo(map);

var wmsLayerM = L.tileLayer.wms('http://gaia.inegi.org.mx/NLB/tunnel/wms/wms61?', {
  layers: 'Hipsografico,MGE',
  transparent: false,
  format: 'image/png',
  //cql_filter:"ambito='U'",
  id: 'xpain.test-cach',
  useCache: true,
  crossOrigin: false,
  sphericalMercator: true
})//.addTo(map);

var wmsLayerSare = L.tileLayer.wms('https://gaia.inegi.org.mx/NLB_CE/balancer.do?map=/opt/map/SARE_UEEPA_2020.map', {
  layers: 'c103,c102,c100,c101a,wdenue,c103r,c107,c107r,c108',
  transparent: false,
  format: 'image/png',
  // //cql_filter:"ambito='U'",
  id: 'xpain.test-cach',
  useCache: true,
  crossOrigin: false,
  sphericalMercator: true,
  EDO:'00',
});

var crs = new L.Proj.CRS(
  'EPSG:900913',
  // '+proj=utm +zone=33 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs',
  '+proj=merc +a=6378137 +b=6378137 +lat_ts=0 +lon_0=0 +x_0=0 +y_0=0 +k=1 +units=m +nadgrids=@null +wktext +no_defs',
  {
    // resolutions: [8192, 4096, 2048, 1024, 512, 256, 128],
    resolutions: [8192, 4096, 2048, 1024, 512, 256, 128,64,32,16,8,4,2,1,0.5, 0],
    // resolutions: [8192, 4096, 2048], // 3 example zoom level resolutions
    origin: [0, 0]
  }
);

console.log(crs);

var map = L.map('mapid', {
  center: [21.541, -102.034],//[-17, -67],
  zoom: 1,
  maxZoom: 18,
  layers: [wmsLayerM,wmsLayer,wmsLayerSare],
  crs: crs,
  continuousWorld: true,
  worldCopyJump: false
});

console.log("map crs: " + map.options.crs.code);


var baseMaps = {
  "MGE": wmsLayerM,
  "TOPO-OSM-WMS": wmsLayer,
  "wdenue": wmsLayerSare,
};


L.control.layers(baseMaps).addTo(map);

