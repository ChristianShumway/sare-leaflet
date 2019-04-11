var sourcesConfig = {
    	proyName:'mdm6',
        identify:{
                    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/identify',
                    field:'busqueda',
                    type: 'POST',
                    contentType : "application/json; charset=utf-8",
                    dataType: "json"
        },
        search:{
				contentType : "application/json; charset=utf-8",
				url:'https://gaia.inegi.org.mx/mdm_searchengine/search',
				type: 'post',
				dataType: "json",
				jsonp:'json.wrf',
				params:{},
				stringify:true
        },
        bufferLayer:{
                    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/totals',
                    contentType : "application/json; charset=utf-8",
                    type: 'POST',
                    dataType: "json"
        },
        layersSeaIde:{
                    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/fieldtypes',
					contentType : "application/json; charset=utf-8",
                    type: 'POST',
                    dataType: "json"
        },
        identifyDetail:{
					url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/query',
                    type:'POST',
                    contentType : "application/json; charset=utf-8",
                    dataType: "json"
        },
        mainSearch:{
                    url:'https://10.1.30.101:9090/solr/autocomplete/select',
					sfield:'locacion',
                    type: 'POST',
                    dataType: "jsonp",
                    jsonp:'json.wrf'
        },
        crossSearch:{
                    url:'https://gaia.inegi.org.mx/NLB/tunnel/TableAliasV60/busqueda',
                    contentType : "application/json",
                    type: 'POST',
                    dataType: "json"
        },
        deepSearch:{
                   url:'https://gaia.inegi.org.mx/mdmSearchEngine/busq-calles/shard',
                    field:'busqueda',
                    type: 'POST',
                    dataType: "jsonp",
                    jsonp:'json.wrf'
        },
		deepSearchTranslate:{
                    url:'https://gaia.inegi.org.mx/NLB/tunnel/TableAliasV60beta/busqueda',
                    type: 'POST',
                    dataType: "json",
					contentType : "application/json; charset=utf-8",
					stringify:true,
					params:{
						tabla: 'geolocator',
						pagina: 1,
						searchCriteria: '',
						proyName: 'mdm6',
						whereTipo: ''
					}
        },
        denue:{
		        	url:'https://10.1.30.101:9090/solr/denue/select',
		            field:'busqueda',
		            type: 'POST',
		            dataType: "jsonp",
		            jsonp:'json.wrf'
        },
	kml:{
			save:'/mdmexport/kml/download',
			read:'/mdmexport/kml/upload'
	},
	gpx:{
			save:'/mdmexport/gpx/download',
			read:'/mdmexport/gpx/upload'
	},
	geometry:{
			store:{
			    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/geometry',
			    type: 'POST',
			    dataType: "json",
			    contentType : "application/json; charset=utf-8"
			    
			},
			addBuffer:{
			    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/buffer',
			    type:'POST',
			    dataType:'json',
			    contentType:'application/json; charset=utf-8'
			},
			restore:{
			    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/wkt/geometries',
			    type: 'GET',
			    dataType: "json",
			    contentType : "application/json; charset=utf-8"
			},
	},
        category:{
                    id:'AR',
                    name:'&Aacute;reas naturales',
                    url:'https://gaia.inegi.org.mx/mdmSearchEngine/cuencasHidro/shard',
                    field:'busqueda',
                    type: 'POST',
                    dataType: "jsonp",
                    jsonp:'json.wrf',
                    facetField:'descripcion'
        },
        kml:{
			save:'/mdmexport/kml/download',
			read:'/mdmexport/kml/upload'
		},
		geometry:{
			store:{
					url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/geometry',
					type: 'POST',
					dataType: "json",
					contentType : "application/json"

				},
				addBuffer:{
					url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/buffer',
					type:'POST',
					dataType:'json',
					contentType:'application/json'
				},
				restore:{
					url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/wkt/geometries',
					type: 'GET',
					dataType: "json",
					contentType : "application/json"
				}
		},
		timeLine:'json/linetime.do',
        school:'',//'json/cctsAGS.json',//'https://10.1.30.102/TableAliasV60/busqueda.do',
        //Otars Url de informacion---------------------------------------------
        leyendUrl:'https://gaia.inegi.org.mx/NLB/tunnel/wms/mdm6wms?map=/opt/map/mdm60/mdm6leyenda.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=',
        synonyms:{
        	list:{
	        		farmacia:['botica','drogeria'],
	        		banco:['cajero'],
	        		restaurant:['bar','merendero'],
	        		hospital:['clinica'],
	        		hotel:['motel','posada']
        		}
        },
	routing:{
	    movePoint:'/routing/point/move'
	},
	cluster:{
	    moreLevels:[2.388657133483887,1.1943285667419434,0.5971642833709717,0.29858214168548586],
	    enableOn:{
		layer:'cdenue14'
	    },
	    recordCard:{
		url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/denue/label',
		type:'POST',
		dataType:'json'
	    },
	    nodes:{
		url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/denue/scian',
		type:'POST',
		dataType:'json'
	    },
	    geometry:{
		url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/wkt/feature',
		type:'POST',
		dataType:'json'
	    }
	},
		displayGeometry:{
			url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/wkt/feature',
			type:'POST',
			dataType:'json',
			contentType : "application/json; charset=utf-8"
		},
        logging:'https://10.1.32.5/SISEC2013/jerseyservices/ServicioSesionJson',
	georeferenceAddress:{
	    url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/reversegeocoding',
	    type: 'POST',
	    dataType: "json",
	    contentType : "application/json; charset=utf-8",
        stringify:true
	    
	},
	mousePosition:{
	    elevation:{
		url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/raster/elevation',
		type:'POST',
		dataType:'json'
	    }
	},
    getGeometry:{
			url:'https://gaia.inegi.org.mx/NLB/tunnel/mapGeoelectoral/wkt',
			type:'GET',
			dataType:'json',
			contentType : "application/json; charset=utf-8"
	},
	files:{
	    download:'https://gaia.inegi.org.mx/NLB/tunnel/mdm_downloadfile/download'
	},
    wktGeojson:{
            url:'https://gaia.inegi.org.mx/NLB/tunnel/mdm_downloadfile/download',
			type:'GET',
			dataType:'json'
    }
    };