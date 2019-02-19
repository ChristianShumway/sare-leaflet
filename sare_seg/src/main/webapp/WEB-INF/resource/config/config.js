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
            type: urlServices['mapBase'].type,
            label: urlServices['mapBase'].label,
            url: urlServices['mapBase'].url,
            layer: [''],
            tiled: false,
            format: 'png'
        },
         {
            type: urlServices['mapAvance'].type,
            label: urlServices['mapAvance'].label,
            url: urlServices['mapAvance'].url,
            layer: [''],
            tiled: false,
            format: 'png'
        }
    ],
    'onLoad': init,
    'onIdentify': eventoIdentificar,
    'onZoomEnd':endzoom,
    'btnTogglePanels': true
    
};


