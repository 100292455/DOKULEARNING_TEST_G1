
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Contenido Curso</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos en linea">
		<meta name="author" content="Jorge Garcia de la Rosa">
		<link href="<c:url value="/style/usuario-contenido-curso.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
		<style type="text/css">
			.error {color: red;}
		</style>
	</head>
	

	
	<body>
		
	<jsp:include page="cabecera.jsp" flush="true"/>

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id = "mi-empresa">
					
					<h4>MIS CURSOS - ${nombreCurso}</h4>
					<div id = "descripcion-curso">
						<p>Profesor: Javier</p>
						<p>4 alumnos</p>
						<p>Actualmente, todas las ciencias aportan problemas que son estudiados por matemáticos, al mismo tiempo que aparecen nuevos problemas dentro de las propias matemáticas. Por ejemplo, el físico Richard Feynman propuso la integral de caminos como fundamento de la mecánica cuántica, combinando el razonamiento matemático y el enfoque de la física, pero todavía no se ha logrado una definición plenamente satisfactoria en términos matemáticos. Similarmente, la teoría de cuerdas, una teoría científica en desarrollo que trata de unificar las cuatro fuerzas fundamentales de la física, sigue inspirando a las más modernas matemáticas.</p>
					</div>
					<c:choose>
						<c:when test="${not empty mensajeCupones }">
							<c:out value="${mensajeCupones}"/>
							<c:set var="mensajeCupones" scope="session" value="${mensajeCupones}"/>
							<c:set var="mensajeCupones" scope="session" value=""/>
						</c:when>
						<c:otherwise>
						</c:otherwise>
						</c:choose>
					<c:forEach items="${sessionScope.cupones }" var="cupon">
							<ul class ="lista-seccion"> 	
							<li id = "oferta-ejemplo">
								<div class = "ofertas-descripcion">
									<p class = "ofertas-titulo">Cupon ${cupon.nombreCupon }</p>
									<c:choose>
										<c:when test="${cupon.TIPO_cupon == 0 }">
											<p class = "ofertas-titulo">Descuento Fijo: ${cupon.descuento }euros de descuento</p>
		
										</c:when>
										<c:otherwise>
											<p class = "ofertas-titulo">Descuento Porcentajes: ${cupon.descuento }% de descuento</p>
										</c:otherwise>
									</c:choose>
									
									<p class = "ofertas-titulo">${cupon.fecha_vto_cupon } fin del cupon.</p>
								</div>		
							</li>
							</ul>
						</c:forEach>
					
					<c:forEach items="${secciones }" var="seccion">
						<ul class ="lista-seccion">
							<p>${seccion.nombre }</p>
							<a  class = "añadir-leccion" href="BajaSeccionServlet?IdSeccion=${seccion.id_seccion }">Borrar seccion</a>
							<a  class = "añadir-leccion" href="EnlaceSL?IdSeccion=${seccion.id_seccion }">Añadir leccion</a>
							<c:forEach items="${lecciones }" var="leccion">							
								<c:if test="${leccion.seccion.id_seccion.equals(seccion.id_seccion)}">
									<li>
										<img class = "leccion" src="images/formatos/${leccion.formato}.png" alt="Error en la imagen"> 
										<p class = "leccion">${leccion.titulo }</p>
										<a  class = "añadir-leccion" href="BajaLeccionServlet?IdLeccion=${leccion.ID_leccion }">Borrar leccion</a>
									</li>	
								</c:if>			
							</c:forEach>
						</ul>
					</c:forEach>
				</div>
				
				<!-- Crear un leccion nuevo -->
									 
						
					   	
					   		<!-- Crear un cupon nuevo -->
									 
						<div id="anadir-cupon">

					        <h2> Añade un nuevo cupon </h2>  

							<form action="AltaCupones" method="post" id ="anadir-cupon-form" onsubmit="return validarcrearcupon();">
								
								<div> 
								<!-- Precio cupon -->
	
							       	<div id="formul7">  
							        	<p class="nombre">Precio de descuento<span class=aster>*</span>:</p>  
								        <p  id="mens7">No ha especificado precio de descuento*</p>
								        <input  type="text" name="precio" id="precio" value="0">
							        </div>		
							      	
								 <!-- Formato del cupon -->

							        <div id="formul8">  
								        <p class="nombre">Formato cupon<span class=aster>*</span>:</p> 
								        <p  id="mens8">No ha especificado el formato del cupon*</p>
								        <select name="tipo_cupon" id="tipo_cupon">
								        	<option value="-1" selected>Sin especificar</option>
		  									<option value="0">Fijo</option>
		  									<option value="1">Porcentaje</option>
										</select> 
							        </div>
					        	</div>
					        	
								
								
								<!-- Fecha fin de cupon -->
								
								
								<div id="formul9">
						        	<p class="nombre">Fecha fin de cupon<span class=aster>*</span>.</p>  
							        <p  id="mens9">No ha especificado el formato de la leccion*</p>  
									<input type="text" name="datepicker" id="datepicker">
								 </div>
								 
					        <!-- Boton añadir -->

							<input type="submit" id="crear-cupon-boton" value="Anadir" />
							</form>
					   	</div>
					   	
					   	<div id="anadir-seccion">

					        <h2> Añade una nueva seccion </h2>  

							<form action="AltaSeccion" method="post" id ="anadir-seccion-form" onsubmit="return validarcrearseccion();">
								
								<!-- Nombre seccion -->
	
							       	<div id="formul10">  
							        	<p class="nombre">Nombre de la seccion<span class=aster>*</span>:</p>  
								        <p  id="mens10">No ha especificado el nombre de la seccion*</p>
								        <input  type="text" name="nombreSeccion" id="nombreSeccion" placeholder="Seccion 1">
							        </div>		
								 
					        <!-- Boton añadir -->

							<input type="submit" id="crear-seccion-boton" value="Anadir" />
							</form>
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
		
		<script src ="<c:url value="script/empresa-mis-ofertas2.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>
