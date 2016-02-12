<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
 <% response.setHeader("Cache-Control", "no-cache");
 response.setHeader("Cache-Control", "no-store");
 response.setHeader("Pragma", "no-cache");
 response.setDateHeader("Expires", 0);  
%>


<html>
<head>
<title>RuggerMar</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="css/jquery-ui-smooth.css">
<link href="css/style.css" rel="stylesheet" id="style1">
<link href="css/style-general.css" rel="stylesheet" id="style2">
<script src="js/jquery.js"></script>
<link type="text/css" href="css/jquery.keypad.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.keypad.js"></script>
<script src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.keypad-es.js"></script>


<style type="text/css">
.ui-dialog {
	z-index: 10
}

.keypad-key, .keypad-special {
	width: 98px;
	height: 38px;
	padding: 5px;
	color: black;
	font-size: 1.8em;
	font-weight: bold;
}

input[type='checkbox'] {
	display: none;
}

input[type='checkbox']+label {
	display: inline-block;
	width: 30px;
	height: 30px;
	background-color: rgb(213, 213, 237);
}

input[type='checkbox']:checked+label {
	background-color: rgb(152, 208, 172);
}

.keypad-start, .keypad-end {
	background: #ff0;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#apagar").click(function(e) {
			$("#opcion").val("Apagar")
			$("#secreto").submit()
		})
		$("#reiniciar").click(function(e) {
			$("#opcion").val("Reiniciar")
			$("#secreto").submit()
		})
	})
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

</head>

<body style="background: url(../../SAT2CR-Administracion/archivos/fondoTurnero.jpg) repeat center center fixed;">
	<div id="home" class="home">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-4 col-lg-offset4">
						<img class="logo-sticky scale-with-grid"
							src="images/logo-steakhouse2.png" alt="">
						<div id="reloj" align="right"
							style="font-weight: 900; font-size: 40px; color: #F7F7F5; /* right: 150px; */ top: -65px; opacity: 0.9; position: relative; margin-right: 50px; height: 10px;"></div>

					</div>
				</div>
			</div>
		</nav>

		<div id="header-wrapper">
			<div id="header">
				<!-- Logo -->
				<h1>
					<img id="imagenMenu"
						src="../../SAT2CR-Administracion/archivos/logoTurnero.jpg">
				</h1>
				<!-- 				<div id="reloj" align="right" -->
				<!-- 					style="padding: 30px; border-radius: 15px; background: #FFFDFC; border-color: beige; border: 12px !important; font-weight: 900; font-size: 40px; -->
				<!-- 	/* font-size: 20px; */ color: #58582A; position: absolute; right: 150px; top: 50px; opacity: 0.8;"></div> -->
				<!-- Intro -->
				<h2>LE DA LA BIENVENIDA</h2>
				<p style="font-weight: 500; font-size: 25px;">Seleccione el
					sector</p>

				<div id="buttons">
					<s:iterator value="sectoresDisponibles" status="sector">

						<s:form action="inicio.action">
							<s:hidden name="nro_sector" value="%{numeroSector}" />

							<s:hidden name="letra_sector" value="%{letra}" />
							<s:hidden name="nom_sector" value="%{nombre}" />

							<a class="btn" href=""><s:property value="nombre" /></a>

						</s:form>



					</s:iterator>
				</div>
				<!-- 		</div> -->
				<div id="dialog" title="Apagado/Reinicio">
					<p>Ingrese el codigo para Reiniciar/Apagar Turnero</p>
					<form action="shutdown.action" id="secreto" method="POST">

						<br> <br> <br>

						<fieldset>

							<label for="password">Codigo</label> <input type="password"
								value="" name="password" id="password" placeholder="Contraseña"
								class="text ui-widget-content ui-corner-all">

							<!-- Allow form submission with keyboard without duplicating the dialog button -->
							<input type="hidden" id="opcion" name="opcion">

						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		var opcionSeleccionada = false;
		var lastImageUrl=""
		var dialog;
		$("a").click(function(e) {
			if (opcionSeleccionada == false) {
				opcionSeleccionada = true
				console.log("Data", $(this).closest('form'))
				$.ajax({
					type : 'POST',
					data : $(this).closest('form').serialize(),
					url : "sacarTicket.action",
					success : function(data) {
						//$("body").replaceWith(data); 
						//$("#style1").attr("disabled", "disabled");
						//NO deberia parsar nada con e fondo ,porque es un estio del body.El ody no se cambiea
						$("body").empty()
						$("body").append(data)
						if ($("body").find('.ticketSalida').length == 0) {
							$("#style1").attr("disabled", "disabled");
						}
						$("#logoTurnero").attr("src", lastImageUrl);
						window.setTimeout(function() {
							//Salvo que le ponagamos a imagen no de css,sino directamnte de aca a ver
							window.location.reload(true);
						}, 6000);
					}
				})
			} else {
				e.preventDefault();

			}
			return false

		});
		$("#imagenMenu").dblclick(function(e) {
			$("#password").val("");
			dialog.dialog("open");
		})
		dialog = $("#dialog").dialog({
			autoOpen : false
		});
		$.keypad.addKeyDef('START', 'start', function(inst) {
			$("#opcion").val("Apagar")
			$("#secreto").submit();
		});
		$.keypad.addKeyDef('END', 'end', function(inst) {
			$("#opcion").val("Reiniciar")
			$("#secreto").submit();
		});
		$("#password").keypad(
				{
					prompt : '',
					keypadOnly : false,
					startText : 'Apagar',
					startStatus : 'Apagar',
					endText : 'Reiniciar',
					endStatus : 'Reiniciar',
					layout : [
							'789' + $.keypad.CLOSE,
							'456' + $.keypad.CLEAR,
							'123' + $.keypad.BACK,
							'.0' + $.keypad.SPACE + $.keypad.START
									+ $.keypad.END ]
				});
		intervaloImagenFondo();

		function intervaloImagenFondo() {
			setInterval(function() {

				//$("#myimg").attr("src", "/myimg.jpg?"+d.getTime());
				var val = Math.random();

				var img = $("<img />").attr(
						'src',
						'../../SAT2CR-Administracion/archivos/fondoTurnero.jpg?'
								+ val).on(
						'load',
						function() {
							console.log("ENTRAAAAAAAAAAAAAAAAAAAAAAAAAAA")
							$('body').css('background','url(../../SAT2CR-Administracion/archivos/fondoTurnero.jpg?'+ val + ')  repeat center center fixed');
							
							
							
						});
				lastImageUrl="../../SAT2CR-Administracion/archivos/logoTurnero.jpg?"+ val
				var img = $("<img />").attr(
						'src',
						'../../SAT2CR-Administracion/archivos/logoTurnero.jpg?'
								+ val).on(
						'load',
						function() {
							$('#imagenMenu').attr("src","../../SAT2CR-Administracion/archivos/logoTurnero.jpg?"+ val);
							
						});
				
				
				
				
				
				//ES MUYYYY RARO O QUE PASa porque as propiedasdes del no cache y reaload true,seria para TODO
				
				
				
				
				
				
			}, 2000);
		}
	</script>

</body>

</html>