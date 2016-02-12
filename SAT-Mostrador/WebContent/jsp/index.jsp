<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Mostrador Ruggemar</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />



<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />




<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">


<link href="css/bootstrap-responsive.min.css" rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/audio.min.js"></script>
<script type="text/javascript" src="js/html5gallery.js"></script>
<script type="text/javascript"
	src="http://momentjs.com/downloads/moment-with-langs.min.js"></script>
<script type="text/javascript" src="js/FeedEk.js"></script>
<link href="css/ticker-style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.ticker.js" type="text/javascript"></script>

<style type="text/css">
.html5gallery-box-0, .html5gallery-box-1, .html5gallery-box-2,
	.html5gallery-box-3 {
	width: 100% !important;
	height: auto !important;
}

.html5gallery-container-0, .html5gallery-container-1,
	.html5gallery-container-2, .html5gallery-container-3 {
	width: 100% !important;
	height: auto !important;
}

.html5gallery {
	width: 100% !important;
	height: auto !important;
}

.html5gallery-elem-video-0, .html5gallery-elem-video-1,
	.html5gallery-elem-video-2, .html5gallery-elem-video-3 {
	width: 100% !important;
	height: 430px !important;
}

.html5gallery-elem-0, .html5gallery-elem-1, .html5gallery-elem-2,
	.html5gallery-elem-3, .html5gallery-elem-4, .html5gallery-elem-5 {
	width: 100% !important;
	height: 430px !important;
}

iframe {
	width: 100% !important;
	height: 430px !important;
}

@media ( max-width : 1400px) {
	.contenedor-fix {
		margin-top: 3%;
	}
}

@media ( min-width : 1400px) {
	.contenedor {
		margin-top: 7%;
	}
	.contenedor-fix {
		margin-top: 10%;
	}
	.html5gallery-elem-video-0, .html5gallery-elem-video-1,
		.html5gallery-elem-video-2, .html5gallery-elem-video-3,
		.html5gallery-elem-video-4 {
		width: 100% !important;
		height: 530px !important;
	}
	.html5gallery-elem-0, .html5gallery-elem-1, .html5gallery-elem-2,
		.html5gallery-elem-3, .html5gallery-elem-4, .html5gallery-elem-5 {
		width: 100% !important;
		height: 530px !important;
	}
	iframe {
		width: 100% !important;
		height: 530px !important;
	}
}
</style>

<script>
	function playSound(soundfile) {
		console.log("ENTRA")
		$('#dummy')[0].play();
	}

	var ultimoNumeroLlamado = 0;
	var ultimoNumeroLlamadoSec2 = 0;
	$(document)
			.ready(
					function() {

						var ip;
						//$.getJSON("jsp/Enviroment.json", function(data) {
						//ip = data["ip"];
						intervaloTurnos();
						intervaloGalera();
						obtenerGaleria()
						//});
						obtenerUrlFeed()
						function intervaloTurnos() {
							setInterval(function() {
					
//$("#myimg").attr("src", "/myimg.jpg?"+d.getTime());
var val =Math.random();

var img = $("<img />").attr('src', '../../SAT2CR-Administracion/archivos/fondoMostrador.jpg?'+val)
    .on('load', function() {
		$('body').css('background','url(../../SAT2CR-Administracion/archivos/fondoMostrador.jpg?'+val+') repeat');

    });

								obtenerUltimosTurnos()
							}, 2000);
						}
						function intervaloGalera() {
							setInterval(function() {
								obtenerGaleria()
							}, 105000);
						}
						function obtenerUltimosTurnos() {
							console.log("ENTRa")
							$
									.ajax({
										type : 'POST',
										url : "ultimosLlamados.action",
										success : function(data) {

											//actualizarHora()
											console
													.log("NUMERO",
															$('.primero'))
											if (data.listaTurnos[0] != undefined) {
												//Muestra turno
												$('.primero')
														.html(
																data.listaTurnos[0].nombreSector
																		+ '<BR>'
																		+ data.listaTurnos[0].numeroTurno)
												//Cambia turno
												if (data.listaTurnos[0].numeroTurno != ultimoNumeroLlamado) {
													ultimoNumeroLlamado = data.listaTurnos[0].numeroTurno;
													playSound()
													setTimeout(function() {
														playSound();
													}, 7000);
													for (i = 0; i < 5; i++) {
														$('.primero')
																.animate(
																		{
																			backgroundColor : "#rgb(208, 216, 181)"
																		}, 800)
																.animate(
																		{
																			backgroundColor : "#d9534f"
																		}, 800)
													}

												}

											}

											console.log("jojjoo",data.listaTurnos[1]);
											if (data.listaTurnos[1] != undefined) {

												$('.segundo')
														.html(
																data.listaTurnos[1].nombreSector
																		+ '<BR>'
																		+ data.listaTurnos[1].numeroTurno)

												if (data.listaTurnos[1].numeroTurno != ultimoNumeroLlamadoSec2) {
													ultimoNumeroLlamadoSec2 = data.listaTurnos[1].numeroTurno;
													playSound()
													setTimeout(function() {
														playSound();
													}, 7000);
													for (i = 0; i < 5; i++) {
														$('.segundo')
																.animate(
																		{
																			backgroundColor : "#rgb(208, 216, 181)"
																		}, 800)
																.animate(
																		{
																			backgroundColor : "#eea236"
																		}, 800)

													}

												}
											}

											//$('.primero').text(data.listaTurnos[0].sectorAtencion)
											// 											$('.segundo')
											// 													.html(
											// 															data.listaTurnos[1].nombreSector +' '+data.listaTurnos[1].numeroTurno)
											// 											$('.tercero')
											// 													.html(
											// 															data.listaTurnos[2].nombreSector +' '+data.listaTurnos[2].numeroTurno)
											// 											$('.cuarto')
											// 													.html(
											// 															data.listaTurnos[3].nombreSector +' '+data.listaTurnos[3].numeroTurno)
											// 											$('.quinto')
											// 													.html(
											// 															data.listaTurnos[4].nombreSector +' '+data.listaTurnos[4].numeroTurno)

										}

									})
						}

						function actualizarHora() {
							var date = new Date();
							var numero = date.getDate();
							var dia = date.getDate();
							var mes = 1 + date.getMonth();
							var anio = date.getFullYear();
							var fechaFinal = dia + "/" + mes + "/" + anio;
							var h = date.getHours();
							var min = date.getMinutes();
							h = checkTime(h);
							min = checkTime(min);
							var horaFinal = h + ":" + min + "HS";
							$('#fecha').text(fechaFinal)
							$('#hora').text(horaFinal)
						}
						function checkTime(i) {
							if (i < 10) {
								i = "0" + i;
							}
							return i;
						}
						function obtenerUrlFeed() {

							console.log("ENTRa URl")
							$.ajax({
								type : 'POST',
								url : "urlFeed.action",
								success : function(data) {
									console.log("LA DATA URL SA", data.urlFeed)
									var feed = "http://" + data.urlFeed
									$('#js-news').ticker({
										htmlFeed : false,
										ajaxFeed : true,
										feedUrl : feed,
										feedType : 'xml'
									});
								}

							})
						}

						function obtenerGaleria() {
							console.log("OBTENER")
							$("#contenedorGaleria").empty()
							$("#contenedorGaleria")
									.append(
											'<div  id="mygallery" data-autoplayvideo ="true" data-skin="horizontal" data-width="750" data-height="430" data-resizemode="fill" data-showcarousel="false" data-autoslide="true" data-skin="gallery"  style="display:none;"></div>')
							$("#mygallery").html5gallery({
								skin : "gallery",
								remote : "/SAT2CR-Mostrador/galeria.action"
							});
						}

					});
</script>
<script type="text/javascript">
	function startTime() {
		today = new Date();
		h = today.getHours();
		m = today.getMinutes();
		d = today.getDate();
		mm = 1 + today.getMonth();
		y = today.getFullYear();
		m = checkTime(m);
		document.getElementById('reloj').innerHTML = d + "/" + mm + "/" + y
				+ "  " + h + ":" + m;
		t = setTimeout('startTime()', 500);
	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
	window.onload = function() {
		startTime();
	}
</script>
<body>
	<div></div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-4 col-lg-offset-4">
					<img class="logo-sticky scale-with-grid"
						src="images/logo-steakhouse2.png" alt="" />
					<div id="reloj" align="right"
						style="font-weight: 900; font-size: 40px; color: #F7F7F5; /* right: 150px; */ top: -65px; opacity: 0.9; position: relative; margin-right: 50px; right: -80%; height: 10px;"></div>
				</div>
			</div>
		</div>
	</nav>

	<div class="container-fluid contenedor">
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3 col-lg-offset-1 izq">
						<button type="button"
							class="btn btn-danger btn-lg btn-block primero"></button>
						<button type="button"
							class="btn btn-warning btn-lg btn-block segundo"></button>
						<!-- 						<button type="button" -->
						<!-- 							class="btn btn-warning btn-lg btn-block tercero"></button> -->
						<!-- 						<button type="button" -->
						<!-- 							class="btn btn-warning btn-lg btn-block cuarto"></button> -->
						<!-- 						<button type="button" -->
						<!-- 							class="btn btn-warning btn-lg btn-block quinto"></button> -->
					</div>
					<div class="col-lg-6 col-lg-offset-1 der">
						<div id="contenedorGaleria">
							<div id="mygallery" data-autoplayvideo="true"
								class="html5gallery" data-skin="horizontal"
								data-resizemode="fill" data-showcarousel="false"
								data-autoslide="true" data-skin="gallery" style="display: none;">
							</div>
							<!-- 							<div  id="mygallery" data-autoplayvideo ="true" data-skin="horizontal" data-resizemode="fill" data-showcarousel="false" data-autoslide="true" data-skin="gallery" data-width="480" data-height="270"  style="display:none;"></div> -->

						</div>
					</div>
				</div>

			</div>

		</div>


		<div class="row contenedor-fix ">
			<div class="col-lg-12 marq">
				<ul id="js-news" class="js-hidden">

				</ul>
			</div>
		</div>

	</div>
	<audio id="dummy" hidden="true" src="js/sonido.mp3"></audio>
</body>
</html>