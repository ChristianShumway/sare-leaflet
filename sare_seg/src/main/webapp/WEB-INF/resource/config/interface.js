var controlConfig = {
    ui: {
        denueTurista: true, //herramienta �Que hay aqui?
        miniBaseMap: true,
        startupTotorial: false,
        layersBar: false,
        autoOpenThemeBar: false,
        mainTools:false, 
        toolBar: true, //barra de descarga compartir, imprimir y ayuda
        tool_gps: true //boton GPS en control de vista
    },
    map: {
        geolocation: false,
        identify: {
            enable: true,
            createMarker: true,
            custom: null,
            fixedLayer: null
        },
        elevation: false,
        onOver: {
            showPolygon: false,
            color: {
                filled: "none",
                size: 2,
                line: "#01FCEF"
            },
            changeDisplayOn: 305.74811
        },
        measure: {
            activePrecision: false
        },
        mousewheel: {
            disable: false,
            message: 'Para hacer zoom presione Ctrl + Scroll '
        },
        level: true
    },
    system: {
        activeCookie: true
    }
}
