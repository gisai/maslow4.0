/**
 * Control de gráficas globales
 * Borja Bordel - Sémola project
 */

var chartMaslow = $("#canvasMaslow");

var barChartMaslow;

createMaslow ();


//Actualización dinámica de la gráfica de emociones
function updateMaslow (maslowLevels) {
    barChartMaslow.data.datasets[0].data = 
	             [Math.round(maslowLevels.NIVEL1), Math.round(maslowLevels.NIVEL2), Math.round(maslowLevels.NIVEL3), Math.round(maslowLevels.NIVEL4), 
	              Math.round(maslowLevels.NIVEL5), Math.round(maslowLevels.NIVEL6), Math.round(maslowLevels.NIVEL7), Math.round(maslowLevels.NIVEL8)];
	barChartMaslow.update();
};

function updateTable (maslowLevels) {
    $('#physiological').html("");
    $('#safety').html("");
    $('#love').html("");
    $('#esteem').html("");
    $('#cognitive').html("");
    $('#aesthetic').html("");
    $('#selfactualization').html("");
    $('#transcendence').html("");
    
    $('#physiological').append(maslowLevels.NIVEL1);
    $('#safety').append(maslowLevels.NIVEL2);
    $('#love').append(maslowLevels.NIVEL3);
    $('#esteem').append(maslowLevels.NIVEL4);
    $('#cognitive').append(maslowLevels.NIVEL5);
    $('#aesthetic').append(maslowLevels.NIVEL6);
    $('#selfactualization').append(maslowLevels.NIVEL7);
    $('#transcendence').append(maslowLevels.NIVEL8);
}

//Función para inicializar la gráfica de emociones
function createMaslow () {
    Chart.defaults.global.defaultFontColor = '#FFFFFF';
	barChartMaslow = new Chart(chartMaslow, {
		type: 'bar',
		data: {
			labels: ["Physiological needs", "Safety needs", "Love needs", "Esteem needs", "Cognitive needs", "Aesthetic needs", "Selfactualiztion needs", "Transcendence needs"],
			datasets: [{
				label: 'Maslow needs',
				data: [0,0,0,0,0,0,0,0],
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(100, 30, 22, 0.2)',
					'rgba(74, 35, 90, 0.2)'
				],
				borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 30, 22, 1)',
					'rgba(74, 35, 90, 1)'					
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				yAxes: [{
					ticks: {
						min:0,
						max:100
					}
				}]
			}
		}
	});	
};

//Función de comprobar el servidor de forma periódica
(function worker() {
  $.ajax({
    url: '../GlobalDataProvider', 
	type: 'POST',
        dataType: 'json',
    success: function(data) {
      $('#result').html("");
      $('#result').append(JSON.stringify(data));
      if(!$.isEmptyObject(data)) {
        updateMaslow (data);
        updateTable (data);
      }
    },
    complete: function() {
      // Schedule the next request when the current one's complete
      setTimeout(worker, 150);
    }
  });
})();
