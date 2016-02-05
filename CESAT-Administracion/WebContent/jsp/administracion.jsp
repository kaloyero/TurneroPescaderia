
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <meta http-equiv="expires" content=<%= new java.util.Date() %>>
    <meta http-equiv="no-cache">
    	<script>
    history.forward();
</script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
		
		
<link rel="stylesheet" href="css/style.css" />
<link rel="shortcut icon" type="image/ico"
	href="http://www.datatables.net/favicon.ico">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">

<title>Administracion RuggeMar</title>
<link rel="stylesheet" type="text/css"
	href="/CESAT-Administracion/datatable/media/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="/CESAT-Administracion/datatable/extensions/TableTools/css/dataTables.tableTools.css">
<link rel="stylesheet" type="text/css"
	href="/CESAT-Administracion/datatable/extensions/Editor-1.3.3/css/dataTables.editor.css">
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="/CESAT-Administracion/datatable/examples/resources/syntax/shCore.css">

<link rel="stylesheet"
	href="css/jquery-ui-smooth.css">
<link rel="stylesheet" type="text/css"
	href="/CESAT-Administracion/datatable/examples/resources/demo.css">
<style type="text/css" class="init">
table.dataTable tr td:first-child {
	text-align: center;
}

th {
	text-align: left
}

table.dataTable tr td:first-child:before {
	content: "\f096"; /* fa-square-o */
	font-family: FontAwesome;
}

table.dataTable tr.selected td:first-child:before {
	content: "\f046"; /* fa-check-square-o */
}
</style>
<script type="text/javascript" language="javascript"
	src="/CESAT-Administracion/datatable/media/js/jquery.js"></script>
<script type="text/javascript"
	src="/CESAT-Administracion/datatable/examples/resources/jquery-ui.js"></script>
<script type="text/javascript" language="javascript"
	src="/CESAT-Administracion/datatable/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript"
	src="/CESAT-Administracion/datatable/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" language="javascript"
	src="/CESAT-Administracion/datatable/extensions/Editor-1.3.3/js/dataTables.editor.js"></script>
<script type="text/javascript" language="javascript"
	src="/CESAT-Administracion/datatable/examples/resources/syntax/shCore.js"></script>
<script type="text/javascript" language="javascript"

	src="/CESAT-Administracion/datatable/examples/resources/demo.js"></script>
	<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" language="javascript" class="init">
	var editorSectores; // use a global for the submit and return data rendering in the examples
	var editorPuestos;
	var editorUsuarios;
	var editorSucursal;
	var editorControlesSectores;
	var editorControlesRemoto;
	var editorUsuariosSectores;
	var oTablePuestos;
	var oTableSectores;
	var oTableUsuarios;
	var oTableSucursales;
	var oTableControlRemoto;
	var oTableControlSectores;
	var oTableSectores;

	var idleTime = 0;
	$(document)
			.ready(
					function() {
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
						var self = this;
						var ip;
						//$.getJSON("jsp/Enviroment.json", function(data) {

							//ip = data["ip"];
							//console.log("IP", ip)
							createEditors()
						//});

						function createEditors() {
							$("#tabs")
									.tabs(
											{
												"activate" : function(event, ui) {
													var table = $.fn.dataTable
															.fnTables(true);
													if (table.length > 0) {
														$(table)
																.dataTable()
																.fnAdjustColumnSizing();
													}
												}
											});


						
							var lastValue;
							editorControlesSectores = new $.fn.dataTable.Editor(
									{
										ajax : "controlSectoresOperaciones.action",
										table : "#example9",
										fields : [ {
											label : "Sector",
											name : "sectorId",
											type : "select"
										}, {
											label : "Control remoto:",
											name : "controlId",
											type : "select"
										} ],
										i18n : {
											create : {
												button : "Nuevo",
												title : "Nuevo",
												submit : "Guardar"
											},
											edit : {
												button : "Editar",
												title : "Edicion",
												submit : "Guardar"
											},
											error : {
												system : "No se pudo completar la operacion"
											},
											remove : {
												button : "Borrar",
												title : "Borrar",
												submit : "Borrar",
												confirm : {
													_ : "Desea borrar este registro?",
													1 : "Desea borrar este registro?"
												}
											},
										}
									});
							editorControlesSectores.on("initCreate", function() {

								this.field("sectorId").update(getSectores())
								this.field("controlId").update(getControles())
							})
							editorControlesSectores.on("initEdit", function() {

								this.field("sectorId").update(getSectores())
								console.log("SEC",this.field("sectorId"))
								this.field("controlId").update(getControles())
								console.log("SEC",this.field("controlId"))
							})
								editorControlesSectores.on("preSubmit", function(e, o) {

								return validarControlRemotoSector(e, o)
							})
					
							editorSectores = new $.fn.dataTable.Editor(
									{
										ajax : "sectoresOperaciones.action",
										table : "#example7",
										fields : [// {
										//label: "Sector",
										//name: "sector"
										//},
										{
											label : "Codigo Sector:",
											name : "codigoSector"
										}, {
											label : "Nombre Sector:",
											name : "nombreSector"
										}, {
											label : "Habilitado:",
											name : "habilitado",
											type : "select",
											ipOpts : [ {
												label : "SI",
												value : "SI"
											}, {
												label : "NO",
												value : "NO"
											} ]

										} ],
										i18n : {
											create : {
												button : "Nuevo",
												title : "Nuevo",
												submit : "Guardar"
											},
											edit : {
												button : "Editar",
												title : "Edicion",
												submit : "Guardar"
											},
											remove : {
												button : "Borrar",
												title : "Borrar",
												submit : "Borrar",
												confirm : {
													_ : "Desea borrar este registro?",
													1 : "Desea borrar este registro?"
												}
											},
											error : {
												system : "No se pudo completar la operacion"
											}
										}

									});
							
							console.log("EXAMPLE91")
							

							oTableSectores = $('#example7')
							.DataTable(
									{
										dom : "Tfrtip",
										scrollY : 300,
										paging : false,
										language : {
											"info" : "Mostrando _START_ a _END_ de _TOTAL_ registros",
											"search" : "Busqueda",
											"infoEmpty" : "No existe informacion",
											"emptyTable" : "No existe informacion",
											"zeroRecords" : "No existe informacion",
											"infoFiltered" : "(Filtrando de un maximo de _MAX_ registros)"
										},
										ajax : "listaSectores.action",
										columns : [ {
											data : null,
											defaultContent : '',
											orderable : false
										}, {
											data : "DT_RowId"
										}, {
											data : "codigoSector"
										}, {
											data : "nombreSector"
										}, {
											data : "habilitado"
										} ],
										order : [ 1, 'asc' ],
										tableTools : {
											sRowSelect : "os",
											sRowSelector : 'td:first-child',
											aButtons : [
													{
														sExtends : "editor_create",
														editor : editorSectores,
														sButtonText : "Crear"
													},
													{
														sExtends : "editor_edit",
														editor : editorSectores,
														sButtonText : "Editar"
													},
													{
														sExtends : "editor_remove",
														editor : editorSectores,
														sButtonText : "Borrar"
													}
											//{ sExtends: "editor_remove", editor: editorSectores, sButtonText: "Borrar" }
											]
										}
									});
							console.log("PASA CONTROL1")
	editorControlesRemoto = new $.fn.dataTable.Editor(

									{
										ajax : "controlesRemotoOperaciones.action",
										table : "#example8",
										fields : [// {
										//label: "Sector",
										//name: "sector"
										//},
										{
											label : "Codigo:",
											name : "codigo"
										} ,
										{
											label : "Boton Anterior:",
											name : "anterior"
										} ,
										{
											label : "Boton Siguiente:",
											name : "siguiente"
										} 
										],
										i18n : {
											create : {
												button : "Nuevo",
												title : "Nuevo",
												submit : "Guardar"
											},
											edit : {
												button : "Editar",
												title : "Edicion",
												submit : "Guardar"
											},
											remove : {
												button : "Borrar",
												title : "Borrar",
												submit : "Borrar",
												confirm : {
													_ : "Desea borrar este registro?",
													1 : "Desea borrar este registro?"
												}
											},
											error : {
												system : "No se pudo completar la operacion"
											}
										}

									});
							editorControlesRemoto.on("preSubmit", function(e, o) {

								return validarControlRemoto(e, o)
							})	
							editorControlesRemoto.on("postSubmit", function(e, o) {
								console.log("oTableControlSectores",oTableControlSectores)
								oTableControlSectores.ajax.reload();
							})	
							
							
							oTableControlRemoto = $('#example8')
							.DataTable(
									{
										dom : "Tfrtip",
										scrollY : 300,
										paging : false,
										language : {
											"info" : "Mostrando _START_ a _END_ de _TOTAL_ registros",
											"search" : "Busqueda",
											"infoEmpty" : "No existe informacion",
											"emptyTable" : "No existe informacion",
											"zeroRecords" : "No existe informacion",
											"infoFiltered" : "(Filtrando de un maximo de _MAX_ registros)"
										},
										ajax : "listaControlesRemoto.action",
										columns : [ {
											data : null,
											defaultContent : '',
											orderable : false
										}, {
											data : "DT_RowId"
										}, {
											data : "codigo"
										}, {
											data : "anterior"
										}, {
											data : "siguiente"
										} ],
										order : [ 1, 'asc' ],
										tableTools : {
											sRowSelect : "os",
											sRowSelector : 'td:first-child',
											aButtons : [
													{
														sExtends : "editor_create",
														editor : editorControlesRemoto,
														sButtonText : "Crear"
													},
													{
														sExtends : "editor_edit",
														editor : editorControlesRemoto,
														sButtonText : "Editar"
													},
													{
														sExtends : "editor_remove",
														editor : editorControlesRemoto,
														sButtonText : "Borrar"
													}
											//{ sExtends: "editor_remove", editor: editorSectores, sButtonText: "Borrar" }
											]
										}
									});
							console.log("PASA CONTROL",editorControlesSectores,$('#example9'))
							
							
							oTableControlSectores =$('#example9')
							.DataTable(
									{
										dom : "Tfrtip",
										scrollY : 300,
										paging : false,
										language : {
											"info" : "Mostrando _START_ a _END_ de _TOTAL_ registros",
											"search" : "Busqueda",
											"infoEmpty" : "No existe informacion",
											"emptyTable" : "No existe informacion",
											"zeroRecords" : "No existe informacion",
											"infoFiltered" : "(Filtrando de un maximo de _MAX_ registros)"
										},
										ajax : "listaControlSectores.action",
										columns : [ {
											data : null,
											defaultContent : '',
											orderable : false
										}, {
											data : "sectorNombre"
										}, {
											data : "codigoSector"
										}, {
											data : "codigoControl"
										} ],
										order : [ 1, 'asc' ],
										tableTools : {
											sRowSelect : "os",
											sRowSelector : 'td:first-child',
											aButtons : [
													{
														sExtends : "editor_create",
														editor : editorControlesSectores,
														sButtonText : "Crear"
													},
													{
														sExtends : "editor_edit",
														editor : editorControlesSectores,
														sButtonText : "Editar"
													},
													{
														sExtends : "editor_remove",
														editor : editorControlesSectores,
														sButtonText : "Borrar"
													} ]
										},
										initComplete : function(
												settings, json) {

											editorControlesSectores.field("sectorId").update(getSectores())
											editorControlesSectores.field("controlId").update(getControles())
										}
									});
							
							console.log("EXAMPLE9FIN")
							
							
							
							
							
							
							
							
							
							
							
							
					        function pad(value,max){
					           // var value=$(field).val()
					            //console.log("VALUE",)
					           return value.length < max ? pad("0" + value, max) : value;
					        }					        
					        
					     
						
							
							editorConfiguracion = new $.fn.dataTable.Editor(
									{
										ajax : "configuracionesOperaciones.action",
										table : "#example6",
										fields : [ {
											label : "Segundos a mostrar imagenes PNG/JPG",
											name : "tiempopngjpg"
										},{
											label : "Segundos a mostrar imagenes GIF",
											name : "tiempogif"
										},{
											label : "Directorio",
											name : "directorio"
										},{
											label : "Feed URL",
											name : "feed"
										} ,{
											label : "Imagen mostrador fondo",
											name : "image"
										},{
											label : "Imagen Turnero fondo",
											name : "imageFondoTurnero"
										}  ,{
											label : "Imagen Logo Turnero",
											name : "imageLogoTurnero"
										}    


										],
										i18n : {
											edit : {
												button : "Editar",
												title : "Edicion",
												submit : "Guardar"
											},
											error : {
												system : "No se pudo completar la operacion"
											},

										}
									});
						
						
							editorConfiguracion.on("preSubmit", function(e, o) {

								return validarConfiguracion(e, o)
							})

							oTableConfiguracion = $('#example6')
							.DataTable(
									{
										dom : "Tfrtip",
										scrollY : 300,
										paging : false,
										language : {
											"info" : "Mostrando _START_ a _END_ de _TOTAL_ registros",
											"search" : "Busqueda",
											"infoEmpty" : "No existe informacion",
											"emptyTable" : "No existe informacion",
											"zeroRecords" : "No existe informacion",
											"infoFiltered" : "(Filtrando de un maximo de _MAX_ registros)"
										},
										ajax : "listaConfiguraciones.action",
										columns : [ {
											data : null,
											defaultContent : '',
											orderable : false
										}, {
											data : "tiempopngjpg"
										},{
											data : "tiempogif"
										},{
											data : "directorio"
										},{
											data : "feed"
										}],
										order : [ 1, 'asc' ],
										tableTools : {
											sRowSelect : "os",
											sRowSelector : 'td:first-child',
											aButtons : [
													{
														sExtends : "editor_edit",
														editor : editorConfiguracion,
														sButtonText : "Editar"
													}
											//{ sExtends: "editor_remove", editor: editorPuestos, sButtonText: "Borrar" }
											]
										}
									});

						
							
							

							editorSectores.on("preSubmit", function(e, o) {

								return validarSector(e, o)
							})
							editorSectores.on("postSubmit", function(e, o) {
								oTableControlSectores.ajax.reload();
							})							
						
						function validarControlRemoto(e, o) {
								var typeReturn = true
								var pattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;
								var regex = new RegExp("^[0-9]*$");
								
								if (o.data.codigo === "") {
									editorControlesRemoto.error("codigo",
											"Campo Requerido")
									typeReturn = false;
									console.log("ENTRO AH SI SI")
								} 
								if (o.data.anterior === "") {
									editorControlesRemoto.error("anterior",
											"Campo Requerido")
									typeReturn = false;
									console.log("ENTRO AH SI SI")
								} 
								if (o.data.siguiente === "") {
									editorControlesRemoto.error("siguiente",
											"Campo Requerido")
									typeReturn = false;
									console.log("ENTRO AH SI SI")
								} 

								return typeReturn;

								
							}
							function validarControlRemotoSector(e, o) {
								var typeReturn = true
								var pattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;
								var regex = new RegExp("^[0-9]*$");
								
								console.log("SECO",o.data.sectorId)
								
								
								if (o.data.sectorId === "") {
									editorControlesSectores.error("sectorId",
											"Campo Requerido")
									typeReturn = false;
								} 
								if (o.data.controlId === "") {
									editorControlesSectores.error("controlId",
											"Campo Requerido")
									typeReturn = false;
								} 
							 
								return typeReturn;

								
							}
							
							
							function validarConfiguracion(e, o) {
								var typeReturn = true
								var pattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;
								var regex = new RegExp("^[0-9]*$");
								
								if (o.data.tiempopngjpg === "") {
									editorConfiguracion.error("tiempopngjpg",
											"Campo Requerido")
									typeReturn = false;
								} else {
									if (!o.data.tiempopngjpg.match(regex)) {
										editorConfiguracion
												.error("tiempopngjpg",
														"Solo se permiten numeros")
										typeReturn = false;
									}else if (!(o.data.tiempopngjpg.trim() >0)) {
										editorConfiguracion
										.error("tiempopngjpg",
												"Los segundos deben ser mayor a 0")
										typeReturn = false;
									}
									
								}
								if (o.data.tiempogif === "") {
									editorConfiguracion.error("tiempogif",
											"Campo Requerido")
									typeReturn = false;
								} else {
									if (!o.data.tiempogif.match(regex)) {
										editorConfiguracion
												.error("tiempogif",
														"Solo se permiten numeros")
										typeReturn = false;
									}else if (!(o.data.tiempogif.trim() >0)) {
										editorConfiguracion
										.error("tiempogif",
												"Los segundos deben ser mayor a 0")
										typeReturn = false;
									}
									
								}
								if (o.data.directorio === "") {
									editorConfiguracion.error("directorio",
											"Campo Requerido")
									typeReturn = false;
								} 
								return typeReturn;

								
							}
							
							
							
							
							function validarSector(e, o) {

								var typeReturn = true
								var regex = new RegExp("[a-zA-Z]");
								//if (o.data.sector===""){
								//editorSectores.error("sector","Campo Requerido")
								//typeReturn=false;
								//}else{
								//if (o.data.sector.length >20){
								//	editorSectores.error("sector","El total de caracteres permitido es menor que 20")
								//typeReturn=false;
								//}
								//}
								if (o.data.nombreSector==undefined){
									return typeReturn;
								}
								console.log("ADA",o.data.nombreSector)
								if (o.data.nombreSector === "") {
									editorSectores.error("nombreSector",
											"Campo Requerido")
									typeReturn = false;
								} else {
									if (o.data.nombreSector.trim().length > 15 ) {
										editorSectores
												.error("nombreSector",
														"El total de caracteres permitido es menor que 15")
										typeReturn = false;
									}
								}

								if (o.data.codigoSector === "") {
									editorSectores.error("codigoSector",
											"Campo Requerido")
									typeReturn = false;
								} else {
									if (o.data.codigoSector.length > 1) {
										editorSectores
												.error("codigoSector",
														"El total de caracteres permitido es menor que 1")
										typeReturn = false;
									} else if (!o.data.codigoSector
											.match(regex)) {
										editorSectores
												.error("codigoSector",
														"Solo se permiten letras de la A la Z")
										typeReturn = false;
									}

								}
								if (o.data.habilitado === "") {
									editorSectores.error("habilitado",
											"Campo Requerido")
									typeReturn = false;
								}

								return typeReturn;
							}
						}
						var idleInterval = setInterval(timerIncrement,30000); // 1 minute

					    //Zero the idle timer on mouse movement.
					    $(this).mousemove(function (e) {
					        idleTime = 0;
					    });
					    $(this).keypress(function (e) {
					        idleTime = 0;
					    });
					    
					    function timerIncrement() {
					    	
					        idleTime = idleTime + 1;
					        console.log("PASA",idleTime)
					        if (idleTime > 1) { // 1 minutes
					        	$('#myForm').submit();
					        }
					        	}
					     
						function getControles() {
							var controles = new Array()
							var control = new Object()
							control.value =""
							control.label = ""
							controles.push(control);
							
							for (var i = 0; i < oTableControlRemoto.data().length; i++) {
								var control = new Object()
								
									control.value = oTableControlRemoto.data()[i]["DT_RowId"]
									control.label = oTableControlRemoto.data()[i]["codigo"]
// 									control.label = oTableControlRemoto.data()[i]["anterior"]
// 									control.label = oTableControlRemoto.data()[i]["siguiente"]
									controles.push(control);
								

							}
							console.log("Controles", controles)
							return controles
						}   
					    
						function getSectores() {
							var sectores = new Array()
							var sector = new Object()
							sector.value =""
							sector.label = ""
							sectores.push(sector);
							for (var i = 0; i < oTableSectores.data().length; i++) {
								var sector = new Object()
								if (oTableSectores.data()[i]["habilitado"] == "SI") {
									sector.value = oTableSectores.data()[i]["DT_RowId"]
									sector.label = oTableSectores.data()[i]["nombreSector"]
									sectores.push(sector);
								}
							}
							console.log("Sectores", sectores)
							return sectores
						} 
					    
					});
	

	
</script>
<script>
	$(function() {
		$("#dialog-message").dialog({
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
	});
</script>
</head>

<body class="dt-example" onload="window.history.forward(1);">
<s:hidden name="logueado" />
<s:hidden id="username" name="username" value="%{#session['user']}" />
	<s:if test="logueado == 1" >
	<div id="dialog-message" title="Alerta Usuarios">
		<p>
			<span class="ui-icon ui-icon-circle-check"
				style="float: left; margin: 0 7px 50px 0;"></span> Hay otro usuario conectado en este momento.
				
		</p>
	</div>
	</s:if>
<div id="header-smart">
		<div id="masthead">
			<!-- Logo -->
			<img src="images/osdepym-logo.png">
			<div id="fecha-hora" style="font-weight: 700;font-family: 'Source Sans Pro'" >
				<p style="font-size: 22px;">Rugge Mar Sistema de Administración de Turnos</p>
				<div id="reloj" align="right" style="font-size: 20px;"></div>
			</div>

		

		
		</div>
	</div>
	<div class="container">
	
		<section>
			<h1>
			
				&nbsp;
			</h1>


		</section>
	</div>
	<div><s:form id="myForm" action="desconectar.action"></s:form></div>
	<div id="tabs">
		<ul>

			<li id="confi"><a  href="#tabs-6" style="color: green;">Configuracion</a></li>
			<li id="confi"><a  href="#tabs-7" style="color: green;">Sector</a></li>
			<li id="confi"><a  href="#tabs-8" style="color: green;">Control remoto</a></li>
			<li id="confi"><a  href="#tabs-9" style="color: green;">Sector-ControlRemoto</a></li>
			
			
		</ul>

	

			<div id="tabs-6">
			<table id="example6" class="display" cellspacing="0"
				style="margin-left: 0px; width: 1197px;">
				<thead>
					<tr>
						<th>
						<th>Tiempo Png/Jpg</th>
						<th>Tiempo Gif</th>
						<th>Directorio</th>
						<th>Feed</th>

						
					</tr>
				</thead>
			</table>
		</div>
		<div id="tabs-7">
			<table id="example7" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>
						<th>Sector</th>
						<th>Codigo Sector</th>
						<th>Nombre Sector</th>
						<th>Habilitado</th>

					</tr>
				</thead>
			</table>
		</div>
		<div id="tabs-8">
			<table id="example8" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>
						<th>Id</th>
						<th>Codigo</th>
						<th>Botón Anterior</th>
						<th>Botón Siguiente</th>

					</tr>
				</thead>
			</table>
		</div>
		<div id="tabs-9">
			<table id="example9" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
					<th>
						<th>Sector Nombre</th>
						<th>Sector Codigo</th>
						<th>Control Remoto</th>

					</tr>
				</thead>
			</table>
		</div>
	</div>


	<section>
	<div style="position: absolute; left: 3%;">
	Usuario: <s:property value="%{#session['user']}" />
	</div>
	<div style="position: absolute; right: 3%;">
								<a class="links-footer" href="desconectar">Cerrar
									Sesi&oacute;n</a>
							</div></section>
</body>
</html>