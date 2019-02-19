var treeConfig = {
    themes: {
    },
    baseLayers: {
        B1: {
            type: 'Wms',
            label: 'Topogr&aacute;fico gris - INEGI ',
            img: '../../resource/img/mapaBase/mapa_gris.jpg',
            url: ['http://gaiamapas1.inegi.org.mx/mdmCache/service/wms?', 'http://gaiamapas3.inegi.org.mx/mdmCache/service/wms?', 'http://gaiamapas2.inegi.org.mx/mdmCache/service/wms?'],
            layer: 'MapaBaseTopograficov61_sinsombreado_gris',
            rights: 'Derechos Reservados &copy; INEGI',
            tiled: true,
            legendlayer: ['c100', 'c101', 'c102', 'c102m', 'c103', 'c109', 'c110', 'c112', 'c200', 'c202', 'c203', 'c300', 'c301', 'c302', 'c310', 'c311', 'c793', 'c795'],
            //legendUrl:'http://10.152.11.6/fcgi-bin/ms62/mapserv.exe?map=/opt/map/mdm60/mdm61leyendaprueba_gris.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=',
            legendUrl: '/NLB/tunnel/wms/mdm6wms?map=/opt/map/mdm60/mdm61leyendaprueba_gris.map&Request=GetLegendGraphic&format=image/png&Version=1.1.1&Service=WMS&LAYER=',
            desc: 'REPRESENTACION DE RECURSOS NATURALES Y CULTURALES DEL TERRITORIO NACIONAL A ESCALA 1: 250 000, BASADO EN IMAGENES DE SATELITE DEL  2002 Y TRABAJO DE CAMPO REALIZADO EN 2003',
            clasification: 'VECTORIAL'
        },
        B2: {
            type: 'Wms',
            label: 'Topogr&aacute;fico - INEGI',
            img: '../../resource/img/mapaBase/Wms.jpg',
            url: ['https://gaia.inegi.org.mx/mdmCache/service/wms?', 'https://gaia.inegi.org.mx/mdmCache/service/wms?', 'https://gaia.inegi.org.mx/mdmCache/service/wms?'],
            layer: 'MapaBaseTopograficov61_sinsombreado',
            rights: '&copy; INEGI 2013',
            tiled: true,
            legendlayer: ['c100', 'c101', 'c102', 'c102-r', 'c102m', 'c103', 'c109', 'c110', 'c111', 'c112', 'c200', 'c201', 'c202', 'c203', 'c206', 'c300', 'c301', 'c302', 'c310', 'c311', 'c354', 'c762', 'c793', 'c795'],
            desc: 'REPRESENTACION DE RECURSOS NATURALES Y CULTURALES DEL TERRITORIO NACIONAL A ESCALA 1: 250 000, BASADO EN IMAGENES DE SATELITE DEL  2002 Y TRABAJO DE CAMPO REALIZADO EN 2003'

        },
        B3: {
            type: 'Bing',
            label: 'Bing maps',
            img: '../../resource/img/mapaBase/Bing.jpg',
            key: 'At-Y-dJe-yHOoSMPmSuTJD5rRE_oltqeTmSYpMrLLYv-ni4moE-Fe1y8OWiNwZVT',
            layer: 'Aerial',
            rights: '&copy; Bing Maps'
        },
        B4: {
            type: 'Wms',
            label: 'Hipsogr&aacute;fico - INEGI',
            img: '../../resource/img/mapaBase/baseHipsografico.jpg',
            url: ['https://gaia.inegi.org.mx/mdmCache/service/wms?', 'https://gaia.inegi.org.mx/mdmCache/service/wms?', 'https://gaia.inegi.org.mx/mdmCache/service/wms?'],
            layer: 'MapaBaseHipsografico',
            rights: '&copy; INEGI 2013',
            tiled: true,
            legendlayer: ['img_altimetria.png'],
            desc: 'IMAGEN DE RELIEVE QUE MUESTRA UNA COMBINACION DE ELEVACION A TRAVES DE COLORES HIPSOGRAFICOS, GENERADA POR PROCESAMIENTO DEL CONTINUO DE ELEVACIONES MEXICANOS DE 3.0 DE 15 METROS.'
        },
        B5: {
            type: 'Osm',
            label: 'Open Street Map',
            img: '../../resource/img/mapaBase/Osm.jpg',
            rights: '&copy; OpenStreetMap contributors'
        },
        B6: {
            type: 'Google',
            label: 'Google Satellite',
            img: '../../resource/img/mapaBase/Google.jpg',
            layer: 'google.maps.MapTypeId.SATELLITE',
            rights: '&copy; Google'
        }
    },
    layers: {
        groups: {
        }

    }
};
