/*
* Script para gestionar las gráficas de resultados
* Borja Bordel - Sémola project
*/

var chartEmotions = $("#canvasEmotions");
var chartExpressions = $("#canvasExpressions");
var chartEmojis = $("#canvasEmojis");

createEmotions ();
createExpressions ();
createEmojis ();

var barChartEmotions;
var barChartExpressions;
var barChartEmojis;

//Actualización dinámica de la gráfica de emociones
function updateEmotions (emotions) {
	barChartEmotions.data.datasets[0].data = 
	             [Math.round(emotions.joy), Math.round(emotions.sadness), Math.round(emotions.disgust), Math.round(emotions.contempt), 
				  Math.round(emotions.anger), 
	              Math.round(emotions.fear), Math.round(emotions.surprise), Math.round(emotions.valence), Math.round(emotions.engagement)];
	barChartEmotions.update();
};

//Actualización dinámica de la gráfica de emociones
function updateExpressions (expressions) {	
	barChartExpressions.data.datasets[0].data = 
	             [Math.round(expressions.smile),Math.round(expressions.innerBrowRaise),Math.round(expressions.browRaise),Math.round(expressions.browFurrow),Math.round(expressions.noseWrinkle),
				 Math.round(expressions.upperLipRaise),Math.round(expressions.lipCornerDepressor),Math.round(expressions.chinRaise),Math.round(expressions.lipPucker),
				 Math.round(expressions.lipPress),Math.round(expressions.lipSuck),Math.round(expressions.mouthOpen),Math.round(expressions.smirk),Math.round(expressions.eyeClosure),
				 Math.round(expressions.attention),Math.round(expressions.lidTighten),Math.round(expressions.jawDrop),Math.round(expressions.dimpler),Math.round(expressions.eyeWiden),
				 Math.round(expressions.cheekRaise),Math.round(expressions.lipStretch)];
	barChartExpressions.update();
};

//Actualización dinámica de la gráfica de emojis
function updateEmojis (emojis) {	
	barChartEmojis.data.datasets[0].data = 
	             [Math.round(emojis.relaxed),Math.round(emojis.smiley),Math.round(emojis.laughing),Math.round(emojis.kissing),
				 Math.round(emojis.disappointed),Math.round(emojis.rage),Math.round(emojis.smirk),Math.round(emojis.wink),
				 Math.round(emojis.stuckOutTongueWinkingEye),Math.round(emojis.stuckOutTongue),Math.round(emojis.flushed),
				 Math.round(emojis.flushed),Math.round(emojis.scream)];
	barChartEmojis.update();
};

//Función para inicializar la gráfica de emociones
function createEmotions () {
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	barChartEmotions = new Chart(chartEmotions, {
		type: 'bar',
		data: {
			labels: ["Joy", "Sadness", "Disgust", "Contempt", "Anger", "Fear", "Surprise", "Valence", "Engagement"],
			datasets: [{
				label: 'Emotions',
				data: [0,0,0,0,0,0,0,0,0],
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(100, 30, 22, 0.2)',
					'rgba(74, 35, 90, 0.2)',
					'rgba(125, 102, 8, 0.2)'
				],
				borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 30, 22, 1)',
					'rgba(74, 35, 90, 1)',
					'rgba(125, 102, 8, 1)'
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				yAxes: [{
					ticks: {
						min:-100,
						max:100
					}
				}]
			}
		}
	});	
};

//Función para inicializar la gráfica de emojis
function createEmojis() {
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	barChartEmojis = new Chart(chartEmojis, {
		type: 'bar',
		data: {
			labels: ["Relaxed", "Smiley", "Laughing", "Kissing", "Disappointed", "Rage", "Smirk", "Wink", "WinkingEye", "StuckOutTongue", "Flushed", "Scream"],
			datasets: [{
				label: 'Emojis',
				data: [0,0,0,0,0,0,0,0,0,0,0,0],
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(100, 30, 22, 0.2)',
					'rgba(74, 35, 90, 0.2)',
					'rgba(125, 102, 8, 0.2)',
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)'
				],
				borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 30, 22, 1)',
					'rgba(74, 35, 90, 1)',
					'rgba(125, 102, 8, 1)',
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)'
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				yAxes: [{
					ticks: {
						min:-0,
						max:100
					}
				}]
			}
		}
	});	
};

//Función para inicializar la gráfica de expresiones
function createExpressions () {
	Chart.defaults.global.defaultFontColor = '#FFFFFF';
	barChartExpressions = new Chart(chartExpressions, {
		type: 'bar',
		data: {
			labels: ["Smile", "InnerBrowRaise", "BrowRaise", "BrowFurrow", "NoseWrinkle", "UpperLipRaise", "LipCornerDepressor", "ChinRaise", "LipPucker", "LipPress",
			         "LipSuck", "MouthOpen", "Smirk", "EyeClousure", "Attention","LidTihgten", "JawDrop", "Dimpler", "EyeWiden", "CheekRaise", "LipStretch"],
			datasets: [{
				label: 'Expressions',
				data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0],
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(100, 30, 22, 0.2)',
					'rgba(74, 35, 90, 0.2)',
					'rgba(125, 102, 8, 0.2)',
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(100, 30, 22, 0.2)',
					'rgba(74, 35, 90, 0.2)',
					'rgba(125, 102, 8, 0.2)',
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)'
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 30, 22, 1)',
					'rgba(74, 35, 90, 1)',
					'rgba(125, 102, 8, 1)',
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
					'rgba(100, 30, 22, 1)',
					'rgba(74, 35, 90, 1)',
					'rgba(125, 102, 8, 1)',
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)'
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




