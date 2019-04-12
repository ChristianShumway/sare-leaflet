var projectParams = {
    'aditionalLayers': [
        {
            type: urlServices['map'].type,
            label: urlServices['map'].label,
            url: urlServices['map'].url,
            layer: [''],
            tiled: false,
            format: 'png'
        },
        {
            type: urlServices['mapDENUE'].type,
            label: urlServices['mapDENUE'].label,
            url: urlServices['mapDENUE'].url,
            layer: [''],
            tiled: false,
            format: 'png'
        },
         {
            type: urlServices['mapLeyDENUE'].type,
            label: urlServices['mapLeyDENUE'].label,
            url: urlServices['mapLeyDENUE'].url,
            layer: [''],
            tiled: false,
            format: 'png'
        },
        {
            type: urlServices['mapLeyMap'].type,
            label: urlServices['mapLeyMap'].label,
            url: urlServices['mapLeyMap'].url,
            layer: [''],
            tiled: false,
            format: 'png'
        }
    ],
    'onLoad': init,  
    'onIdentify': identif,
    'onMoveEnd': zooma,
    'onZoomEnd': zooma,
    'btnTogglePanels': true
    
};


