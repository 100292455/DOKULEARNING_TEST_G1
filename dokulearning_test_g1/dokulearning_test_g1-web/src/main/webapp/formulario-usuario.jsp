<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica final: Formulario usuario</title>
		
		<meta name="keywords" content="buscar, trabajo, empleo, salario">
		<meta name="description" content="Web para la busqueda de empleo">
		<meta name="author" content="Mingda Liu Zhang, Alejando Ruiz de la Cuadra, Carlos Herrero Lozano">
	
		<!--Fuente Ubuntu-->
		
		<link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
	
		<!--CSS de la pagina-->
		
		<link rel="stylesheet" type="text/css" href="style/formulario-usuario.css">
		
		
		<!--SCRIPT de la pagina-->
		<link href="script/jquery-ui-1.11.2.custom/jquery-ui.css" rel="stylesheet">
		
		<!--JQUERY-->

		<script src="script/jquery-ui-1.11.2.custom/external/jquery/jquery.js"></script>
		<script src="script/jquery-ui-1.11.2.custom/jquery-ui.js"></script>
		
		

		
		<!--CSS redes sociales-->
		
			<link rel="stylesheet" type="text/css" href="script/plugin/circle/css/common.css" />
	        <link rel="stylesheet" type="text/css" href="script/plugin/circle/css/style6.css" />
			<link href='http://fonts.googleapis.com/css?family=Open+Sans:300,700' rel='stylesheet' type='text/css' />
			<script type="text/javascript" src="script/plugin/circle/js/modernizr.custom.79639.js"></script>
			
			
	        <link rel="stylesheet" type="text/css" href="style/formacion/style_common.css" />
	        <link rel="stylesheet" type="text/css" href="style/formacion/style10.css" />
	        <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css' />
			<style type="text/css">
			.error {color: red;}
			</style>
	</head>
	
	
	<body>
		
	
			<!-- CABECERA-->
	
				<header>
				
				<a href="index.jsp">	
					
					<img class = "cabecera" src="./images/logo.png" alt="Error en la imagen">    
				
					<h1 class = "cabecera">DOKU</h1>
				
				</a>
				
					<p class = "cabecera1" id = "cabecera-empresa" onclick = "window.location.href='./login.jsp'">INICAR SESION</p>
				
					<input class = "cabecera" name='registrar' type="button" value="REGISTRAR" onclick = "window.location.href='./formulario-usuario.jsp'">
					
			</header>	

			<!--MENU-->
	
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<h4>EMPIEZA A CREAR TU NUEVO PERFIL (PARTICULAR)</h4>

				<form method="POST" action="FormularioUsuario" onsubmit="return validar();">
				
				<div id="cuestionario">
					<c:choose>
					<c:when test="${not empty mensajeRegistro }">
								<p class="error">${mensajeRegistro }</p>
							</c:when>
							<c:otherwise>
							</c:otherwise>
					</c:choose>
				
					<div><div>
							<!--Nombre-->
							<p><span class=obligatorio>Nombre</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<p id="mens1" class = "mens">Has rellenado mal el nombre*</p>							
							<input  type="text" id="nombre" name="nombre" placeholder="Carlos" />							

					</div>		
							<!--Apellido-->
					<div>		
							<p><span class=obligatorio>Apellidos</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<p id="mens2" class = "mens">Te falta rellenar la casilla apellido*</p>							
							<input  type="text" name="apellidos" id="apellidos" placeholder="Ruiz" />

							
					</div></div>
							<!--Telefono-->
					<div><div>		
							<p><span class=obligatorio>Telefono</span><span class=aster>*</span><span class=obligatorio>:</span></p>	
							<p id="mens5" class = "mens">Has rellenado mal el teléfono</p>
							<input  type="text" name="telefono" id="telefono" placeholder="666666666, sin prefijo" />
					</div>	
							<!--Email-->
					<div>		
							<p><span class=obligatorio>Email</span><span class=aster>*</span><span class=obligatorio>:</span></p>	
							<p id="mens4" class = "mens">Te falta rellenar la casilla correo*</p>							
							<input type="email" name="correo" id="correo"placeholder="carlos_ruiz@gmail.com" />

					</div></div>			
							<!--Descripcion-->
					<div><div class = "div-largo">		
							<p><span class=obligatorio>Descripcion</span><span class=aster></span><span class=obligatorio>:</span></p>
							<textarea name="descripcion" id= "rol" placeholder="Escribe una pequeña descripcion"></textarea>

					</div></div>
					
							<!--Modo de cobro-->
					<div><div class = "div-largo">		
							<p><span class=obligatorio>Medio de cobro</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<select  id="medio_cobro" name = "cobro">
								<option value="otros"> Sin especificar</option>
								<option value="transferencia">Transferencia bancaria</option>
								<option value="paypal">PayPal</option>
								<option value="alertpay">AlertPay</option>
								<option value="tarjeta">Pago con tarjeta de credito</option>
							</select>
					</div></div>		
							<!--Sexo -->
					<div><div id = "sexo-radio-button">
							<p><span class=obligatorio>Sexo</span><span class=aster></span><span class=obligatorio>:</span></p>	
							 	<input type="radio" id = "sex1" name="sex" value="hombre" > <p class = "sexo-radio-button">Hombre</p>
 								<br>
 								<input type="radio" id = "sex2" name="sex" value="mujer"> <p class = "sexo-radio-button">Mujer</p>
							
					</div>		
							<!--Intereses-->
					<div id = intereses-div>		
							<p><span class=obligatorio>Intereses</span><span class=aster></span><span class=obligatorio>:</span></p>
							<input  type="text" name="int" id="int" placeholder="Ciencias Sociales" />

					</div></div>		
							<!--password 1-->
					<div><div>
							
							<p><span class=obligatorio>password</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<p id="mens9" class = "mens">Introduce una password*</p>							
							<input type="password" name="password1" id="password1" placeholder="max. 8 caracteres a-z, 0-9" />

					</div>
							<!--password 2-->
					<div>
							<p><span class=obligatorio>Confirmar password</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<p id="mens10" class = "mens">Tienes que confirmar password*</p>
							<input type="password" name="password2" id="password2"  placeholder="max. 8 caracteres a-z, 0-9" />
					</div></div>
							<!--Código Postal-->
					<div id = "subir-imagen">
							<p>Subir imagen perfil:</p>
							<input type="file" name="imagen"/>
					</div>
					
					<div id = "condiciones"> 
						<p> He leído  y acepto los términos y condiciones de uso<span class=aster>*</span>
						<input  id="acepto"  type="checkbox" name="condiciones" value="condiciones_uso"></p>
						<p id="mens3" class = "mens">Tienes que aceptar las condiciones de uso*</p>
						
						<p id= "condiciones-texto">Condiciones generales:<br>1. Su relación con Lavoro TV<br>
					1.1 Su utilización del sitio web de Lavoro TV (el "Sitio Web") y de todos los productos, canales, software, alimentación de datos y servicios de Lavoro TV, incluyendo el reproductor de video de Lavoro TV incorporado
					(el "Reproductor de Video de Infojobs TV") que Lavoro TV le facilite en, desde o a través del Sitio Web (conjuntamente, el "Servicio") estará sujeto a las condiciones de un acuerdo legal entre Ud. y Lavoro SL. "Lavoro
					TV" es titularidad de  Lavoro, S.A. con domicilio en Barcelona, España, en la Calle Numancia 46, 4ª planta; provista de CIF: A-62134309 E Inscrita en el RMB T.32517, F.15, H.210876 (en adelante "Lavoro") es la titular
					y propietaria de los Sitios Web www.lavoro.net, formacion.lavoro.net yblog.lavoro.net (en adelante "Lavoro" o "Sitio/s Web").<br>1.2 Su acuerdo legal con Lavoro TV se compone de (A) los términos y condiciones establecidos
					en este documento, (B) las condiciones legales de Infojobs (https://www.lavoro.net/lex.xhtml?dgv=2771209485035851227)  (conjuntamente, los "Términos y Condiciones").<br>1.3 Los Términos y Condiciones constituyen un contrato
					legalmente vinculante para Ud. y para Lavoro TV en relación con su utilización de los Servicios, por lo que es importante que los lea detenidamente.<br>1.4 Los Términos y Condiciones son de aplicación a todos los usuarios del
					Servicio, incluyendo aquellos usuarios que aporten igualmente Contenido en el Servicio.<br>Entendemos por “Contenido” todo aquel texto, software, guiones, gráficos, fotos, sonidos, música, videos, combinaciones audiovisuales,
					funcionalidades interactivas y otros materiales que Ud. pueda visualizar en, o acceder a través de, o aportar a, el Servicio.<br>2. Aceptación de los Términos y Condiciones<br>2.1 Para poder utilizar los Servicios, deberá
					aceptar previamente los Términos y Condiciones. Ud. no podrá utilizar los Servicios si no acepta los Términos y Condiciones.<br>2.2 Puede aceptar los Términos y Condiciones mediante la simple utilización de los Servicios.
						</p>
					</div>
					
					<div id = "registrar-boton">
						<input class = "registrar1" name='registrar1' type="submit" value="CREAR USUARIO">					
					</div>	
				
						
			</div>
				</form>	
				<div id = "redes-sociales">
						
					<h4>SÍGUENOS EN LAS REDES SOCIALES</h4>
						
					<ul class="ch-grid">
						<li>
							<div class="ch-item ch-img-1">				
								<a href= "https://www.facebook.com/"><div class="ch-info-wrap">
									<div class="ch-info">
										<div class="ch-info-front ch-img-1"></div>
										<div class="ch-info-back" id = "facebook">
											<h3>Facebook</h3>
											<p>Ya somos 17.8K</p>
										</div>	
									</div>
								</div>
								</a>
							</div>
						</li>
						<li>
							<div class="ch-item ch-img-2">
								<a href = "https://www.twitter.com/"><div class="ch-info-wrap">
									<div class="ch-info">
										<div class="ch-info-front ch-img-2"></div>
										<div class="ch-info-back" id = "twitter">
											<h3>Twitter</h3>
											<p>Ya somos 34.3K</p>
										</div>
									</div>
								</div>
								</a>
							</div>
						</li>
						<li>
							<div class="ch-item ch-img-3">
								<a href="https://www.facebook.com/">
								<div class="ch-info-wrap">
									<div class="ch-info">
										<div class="ch-info-front ch-img-3"></div>
										<div class="ch-info-back" id = "google">
											<h3>Google+</h3>
											<p>Ya somos 4.1K</p>
										</div>
									</div>
								</div>
								</a>
							</div>
						</li>
					</ul>
						
				</div>
				
				
			</section>

	
			<!--PIE DE PAGINA-->

			<footer>  
				<ul id="pie">
					<li> Copyright © jooglecam.com</li>
				 	<li><a href="#">Aviso legal	</a></li>
				 	<li><a href="#">Privacidad 	 </a></li>
				 	<li><a href="#">Política de cookies	</a></li>
				 	<li><a href="#">Accesibilidad  </a></li>
				 	<li><a href="#">Contacto </a></li>
				 	<li><a href="#">Ayuda  </a></li>
				</ul>
			</footer>
		
		<script type = "text/javascript" src ="script/formulario-usuario.js"></script>
		<script type="text/javascript" src="script/plugin/slick/slick.min.js"></script>
		
		
	</body>

</html>
