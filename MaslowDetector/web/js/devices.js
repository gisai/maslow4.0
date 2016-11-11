/**
 * Graficas de los dispositivos
 * Borja Bordel - Sémola project
 */

var chartCrudo = $("#canvasCrudo");
var chartLF = $("#canvasLF");
var chartCF = $("#canvasCF");
var chartMF = $("#canvasMF");
var chartRF = $("#canvasRF");

var lineChartCrudo;
var lineChartLF;
var lineChartCF;
var lineChartMF;
var lineChartRF;

var largo = 200;

createCrudo ();
createLF();
createCF();
createMF();
createRF();

//Actualización dinámica de la gráfica de emociones
function updateCrudo (rawECG) {
        var longitud = lineChartCrudo.data.datasets[0].data.length;
        lineChartCrudo.data.datasets[0].data = lineChartCrudo.data.datasets[0].data.slice(rawECG.length);
        for (var i = 0; i <rawECG.length; i++) {
            lineChartCrudo.data.datasets[0].data.push({x:rawECG.length+i, y:rawECG[i]});
        }
        for (var i = 0; i<longitud; i++) {
            lineChartCrudo.data.datasets[0].data[i].x = i;
        }        
	lineChartCrudo.update();
};

//Actualización dinámica de la gráfica de emociones
function updateLF (signalLF) {
        var longitud = lineChartLF.data.datasets[0].data.length;
        lineChartLF.data.datasets[0].data = lineChartLF.data.datasets[0].data.slice(signalLF.length);
        for (var i = 0; i <signalLF.length; i++) {
            lineChartLF.data.datasets[0].data.push({x:signalLF.length+i, y:signalLF[i]});
        }
        for (var i = 0; i<longitud; i++) {
            lineChartLF.data.datasets[0].data[i].x = i;
        }
	lineChartLF.update();
};

//Actualización dinámica de la gráfica de emociones
function updateMF (signalMF) {
        var longitud = lineChartMF.data.datasets[0].data.length;
        lineChartMF.data.datasets[0].data = lineChartMF.data.datasets[0].data.slice(signalMF.length);
        for (var i = 0; i <signalMF.length; i++) {
            lineChartMF.data.datasets[0].data.push({x:signalMF.length+i, y:signalMF[i]});
        }
        for (var i = 0; i<longitud; i++) {
            lineChartMF.data.datasets[0].data[i].x = i;
        }
	lineChartMF.update();
};

//Actualización dinámica de la gráfica de emociones
function updateCF (signalCF) {
        var longitud = lineChartCF.data.datasets[0].data.length;
        lineChartCF.data.datasets[0].data = lineChartCF.data.datasets[0].data.slice(signalCF.length);
        for (var i = 0; i <signalCF.length; i++) {
            lineChartCF.data.datasets[0].data.push({x:signalCF.length+i, y:signalCF[i]});
        }
        for (var i = 0; i<longitud; i++) {
            lineChartCF.data.datasets[0].data[i].x = i;
        }
	lineChartCF.update();
};

//Actualización dinámica de la gráfica de emociones
function updateRF (signalRF) {
        var longitud = lineChartRF.data.datasets[0].data.length;
        lineChartRF.data.datasets[0].data = lineChartRF.data.datasets[0].data.slice(signalRF.length);
        for (var i = 0; i <signalRF.length; i++) {
            lineChartRF.data.datasets[0].data.push({x:signalRF.length+i, y:signalRF[i]});
        }
        for (var i = 0; i<longitud; i++) {
            lineChartRF.data.datasets[0].data[i].x = i;
        }
	lineChartRF.update();
};

//Función para inicializar la gráfica de emociones
function createCrudo () {
        var dataArray = new Array(largo)
        for (var i = 0 ; i < largo; i++) {
            dataArray[i] = {x:i,y:0};
        }
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	lineChartCrudo = new Chart(chartCrudo, {
		type: 'line',                
		data: {
                    datasets: [{
                        borderColor: 'rgba(255, 159, 64, 1)',
			label: "Raw ECG data",
			data: dataArray,				
		    }]
		},
                options: {
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
	});	
};

//Función para inicializar la gráfica de emociones
function createLF () {
        var dataArray = new Array(largo)
        for (var i = 0 ; i < largo; i++) {
            dataArray[i] = {x:i,y:0};
        }
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	lineChartLF = new Chart(chartLF, {
		type: 'line',                
		data: {
                    datasets: [{
                        borderColor: 'rgba(255, 159, 64, 1)',
			label: "LF Signal",
			data: dataArray,				
		    }]
		},
                options: {
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
	});	
};

//Función para inicializar la gráfica de emociones
function createRF () {
        var dataArray = new Array(largo)
        for (var i = 0 ; i < largo; i++) {
            dataArray[i] = {x:i,y:0};
        }
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	lineChartRF = new Chart(chartRF, {
		type: 'line',                
		data: {
                    datasets: [{
                        borderColor: 'rgba(255, 159, 64, 1)',
			label: "RF Signal",
			data: dataArray,				
		    }]
		},
                options: {
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
	});	
};

//Función para inicializar la gráfica de emociones
function createCF () {
        var dataArray = new Array(largo)
        for (var i = 0 ; i < largo; i++) {
            dataArray[i] = {x:i,y:0};
        }
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	lineChartCF = new Chart(chartCF, {
		type: 'line',                
		data: {
                    datasets: [{
                        borderColor: 'rgba(255, 159, 64, 1)',
			label: "CF Signal",
			data: dataArray,				
		    }]
		},
                options: {
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
	});	
};

//Función para inicializar la gráfica de emociones
function createMF () {
        var dataArray = new Array(largo)
        for (var i = 0 ; i < largo; i++) {
            dataArray[i] = {x:i,y:0};
        }
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	lineChartMF = new Chart(chartMF, {
		type: 'line',                
		data: {
                    datasets: [{
                        borderColor: 'rgba(255, 159, 64, 1)',
			label: "MF Signal",
			data: dataArray,				
		    }]
		},
                options: {
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
	});	
};


//Función de comprobar el servidor de forma periódica
(function worker() {
  $.ajax({
    url: '../DeviceDataProvider', 
	type: 'POST',
        dataType: 'json',
    success: function(data) {
      $('#result').html("");
      $('#result').append(JSON.stringify(data));
      if(!$.isEmptyObject(data)) {
        $("#ppm").html("");
        $("#bpm").html("");
        $("#ppm").append(data.PROCESSEDDATA.PPMSignal);
        $("#bpm").append(data.PROCESSEDDATA.BPMSignal);
        updateCrudo (data.RAWDATA);
        updateLF (data.PROCESSEDDATA.LFSignal);
        updateCF (data.PROCESSEDDATA.CFSignal);
        updateMF (data.PROCESSEDDATA.MFSignal);
        updateRF (data.PROCESSEDDATA.RFSignal);
      }
    },
    complete: function() {
      // Schedule the next request when the current one's complete
      setTimeout(worker, 150);
    }
  });
})();
