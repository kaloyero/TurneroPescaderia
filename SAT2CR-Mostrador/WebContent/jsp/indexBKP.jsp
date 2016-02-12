<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Mostrador Ruggemar</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
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


<script>
	function playSound(soundfile) {
		console.log("ENTRA")
		$('#dummy')[0].play();
	}

	var ultimoNumeroLlamado = 0;
	$(document)
			.ready(
					function() {

						var ip;
						//$.getJSON("jsp/Enviroment.json", function(data) {
						//ip = data["ip"];
						//intervaloTurnos();
						intervaloGalera();
						obtenerGaleria()
						//});
						obtenerUrlFeed()
						function intervaloTurnos() {
							setInterval(function() {
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
											$('.primero')
													.html(
															data.listaTurnos[0].numeroTurno)

											if (data.listaTurnos[0].numeroTurno != ultimoNumeroLlamado) {
												ultimoNumeroLlamado = data.listaTurnos[0].numeroTurno;
												//playSound()
												//setTimeout(function() {
												//playSound();
												//}, 7000);
												for (i = 0; i < 5; i++) {
													$('.primero')
															.animate(
																	{
																		backgroundColor : "#A9A9F5"
																	}, 800)
															.animate(
																	{
																		backgroundColor : "#d9534f"
																	}, 800)

												}

											}

											//$('.primero').text(data.listaTurnos[0].sectorAtencion)
											$('.segundo')
													.html(
															data.listaTurnos[1].numeroTurno)
											$('.tercero')
													.html(
															data.listaTurnos[2].numeroTurno)
											$('.cuarto')
													.html(
															data.listaTurnos[3].numeroTurno)
											$('.quinto')
													.html(
															data.listaTurnos[4].numeroTurno)

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
											'<div  id="mygallery" data-autoplayvideo ="true" data-skin="horizontal" data-width="750" data-height="430" data-resizemode="fill" data-showcarousel="false" data-autoslide="true" data-skin="gallery" data-width="480" data-height="270"  style="display:none;"></div>')
							$("#mygallery").html5gallery({
								skin : "gallery",
								remote : "/SAT2CR-Mostrador/galeria.action"
							});
						}

					});
</script>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-4 col-lg-offset-4">
					<img class="logo-sticky scale-with-grid"
						src="http://ruggemar.com.ar/wp-content/uploads/2015/05/logo-steakhouse2.png"
						alt="">
				</div>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-4 izq">
						<button type="button"
							class="btn btn-danger btn-lg btn-block primero"></button>
						<button type="button"
							class="btn btn-warning btn-lg btn-block segundo"></button>
						<button type="button"
							class="btn btn-warning btn-lg btn-block tercero"></button>
						<button type="button"
							class="btn btn-warning btn-lg btn-block cuarto"></button>
						<button type="button"
							class="btn btn-warning btn-lg btn-block quinto"></button>
					</div>
					<div class="col-lg-7 col-lg-offset-1 der">
						<div id="contenedorGaleria">
							<div id="mygallery" data-autoplayvideo="true"
								data-skin="horizontal" data-width="8
							30"
								data-height="830" data-resizemode="fill"
								data-showcarousel="false" data-autoslide="true"
								data-skin="gallery" data-width="480" data-height="270"
								style="display: none;"></div>
							<!-- 							<div  id="mygallery" data-autoplayvideo ="true" data-skin="horizontal" data-resizemode="fill" data-showcarousel="false" data-autoslide="true" data-skin="gallery" data-width="480" data-height="270"  style="display:none;"></div> -->

						</div>
					</div>
				</div>

			</div>

		</div>

		<div class="row contenedor-fix">
			<div class="col-lg-12 marq">
				<ul id="js-news" class="js-hidden">

				</ul>
			</div>
		</div>
	</div>

</body>
</html>