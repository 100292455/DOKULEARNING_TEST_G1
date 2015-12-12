<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- InformaciÃ³n sobre el documento -->
	
		<title>Practica TIW: Gestion de cursos</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos en linea">
		<meta name="author" content="Miguel Solera Martin">
		<link href="<c:url value="/style/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<link rel="stylesheet" type="text/css" href="style/busqueda.css">
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
		<style type="text/css">
			.error {color: red;}
		</style>
	</head>
	
			<!-- ******************** TO-DO ******************** -->
			<!-- RESCATAR ID DEL PROFESOR CORRESPONDIENTE Y PASARLO EN EL POST A AltaCursosServlet y BajaCursosServlet -->
			<!-- *********************************************** -->
	
	<body>
		
	
			<!-- CABECERA-->
            
    
              <jsp:include page="cabecera.jsp" flush="true"/>

			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id = "mi-empresa">
				
					
					
				
					<h4>Alumnos Matriculados en ${nombrecurso }</h4>
					
					
					
					<div id = "ofertas">
				 		<c:if test="${empty matriculadoscurso }">
							<!-- cursos es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
							<p class="error">Actualmente no existe ningun alumno matriculado en este curso.</p>
						</c:if>
				
						<ul>
		                <c:forEach items="${matriculadoscurso }" var="matricula">  
						
							<li id = "oferta-ejemplo${matricula.alumno.nombre}">
								<div class = "ofertas-descripcion">
								
									
									<p class = "ofertas-resumen">Alumno Matriculado: ${matricula.alumno.nombre} ${matricula.alumno.apellido}</p>
								
									<p class = "ofertas-tipo-contrato">email. ${matricula.alumno.email} </p>
									<p class = "ofertas-jornada">tlfno. ${matricula.alumno.telefono } </p>
								    <p class = "ofertas-jornada">precio pagado.${matricula.precio_pagado } </p>
									
								</div>
							
			
								
								
							</li>
						</c:forEach>
						</ul>
						
						
						
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
					
				
				
					

			</section>

	
			<!--PIE DE PAGINA-->

			<footer>  
				<ul id="pie">
					<li> Copyright Â© jooglecam.com</li>
				 	<li><a href="#">Aviso legal	</a></li>
				 	<li><a href="#">Privacidad 	 </a></li>
				 	<li><a href="#">PolÃ­tica de cookies	</a></li>
				 	<li><a href="#">Accesibilidad  </a></li>
				 	<li><a href="#">Contacto </a></li>
				 	<li><a href="#">Ayuda  </a></li>
				</ul>
			</footer>
		
		<script src ="<c:url value="script/empresa-mis-ofertas2.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>