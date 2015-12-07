
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Información sobre el documento -->
	
		<title>Practica final: Inicio</title>
		
		<meta name="keywords" content="buscar, trabajo, empleo, salario">
		<meta name="description" content="Web para la busqueda de empleo">
		<meta name="author" content="Mingda Liu Zhang, Alejando Ruiz de la Cuadra, Carlos Herrero Lozano">
	
		<!--Fuente Ubuntu-->
		
		<link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
	
		<!--CSS de la pagina-->
		
		<link rel="stylesheet" type="text/css" href="./style/index.css">

		<!--CSS redes sociales-->
		
			<link rel="stylesheet" type="text/css" href="./script/plugin/circle/css/common.css" />
	        <link rel="stylesheet" type="text/css" href="./script/plugin/circle/css/style6.css" />
			<link href='http://fonts.googleapis.com/css?family=Open+Sans:300,700' rel='stylesheet' type='text/css' />
			<script type="text/javascript" src="./script/plugin/circle/js/modernizr.custom.79639.js"></script>		
		
		<!--SCRIPT de la pagina-->
		<link href="./script/jquery-ui-1.11.2.custom/jquery-ui.css" rel="stylesheet">
				<script type="text/javascript" src="./script/plugin/slick/slick.min.js"></script>
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
			
			
			
		<!-- MENU -->
		<section>
			<div id = "formulario">
				<div id = "formulario-2">					
						<c:choose>
							<c:when test="${not empty mensajeInicio }">
								<p class="error">${mensajeInicio }</p>
							</c:when>
							<c:otherwise>
								<p>Introduce tu usuario y clave para entrar</p>
							</c:otherwise>
						</c:choose>
						
				<!-- el siguiente formulario  envia los datos al SesionServlet. Los parametros contienen el atributo name -->
						
						<form action="sesion" method="post">
							<div class = "input-sesion">
								<label for="usuario">Usuario:</label><br>
								<input type="text" name="usuario" id="usuario" />
							</div>
							
							<div class = "input-sesion">
								<label for="password">Password:</label><br>
								<input type="password" name="password" id="password" /> 
							</div>
										
							<div class = input-submit>			
								<input type="submit" value="Enviar" id="input-submit"/>
							</div>
							
							</form>
					</div>
			</div>
			
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
		
</body>
</html>
