<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Gestion de Cupones</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos en linea">
		<meta name="author" content="Jorge Garcia">
		<link href="<c:url value="/style/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
		<style type="text/css">
			.error {color: red;}
		</style>
		<link rel="stylesheet" type="text/css" href="style/busqueda.css">
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
            
    
                <header>
                
                <a href="index.jsp">    
                    
                    <img class = "cabecera" src="images/logo.png" alt="Error en la imagen">    
                
                    <h1 class = "cabecera">DOKU</h1>
                
                </a>
                
                    <a href = "GestionCursos.jsp"><p class = "cabecera1" id = "cabecera-empresa">MIS CURSOS</p></a>
                    
                    <p class = "cabecera">|</p>
                        
                    <a href = "miPerfilProfesor.jsp"><p class = "cabecera1" id = "cabecera-usuario">MI PERFIL</p></a>
                    
                     <p class = "cabecera">|</p>
                        
                    <a href = "sesion?accion=salir"><p class = "cabecera1" id = "cabecera-usuario">SALIR</p></a>
                    
                    
            
            </header>


			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id = "mi-empresa">
				
					<input type = "button" name = "actualizar" value = "Añadir cupon" id = "añadir-oferta1" class = "añadir-oferta">
				
					<h4>MIS CUPONES</h4>
					
					<div id = "ofertas">
				 		<c:if test="${empty cupones }">
							
							<p class="error">Actualmente no hay cupones.</p>
						</c:if>
						<c:if test="${empty cursos }">
							
							<p class="error">No hay cursos a los que asociar cupones</p>
						</c:if>
				
						<ul>
					<c:forEach items="${cupones }" var="cupon"> 	
							<li id = "oferta-ejemplo">
								<div class = "ofertas-descripcion">
								<!-- TO-DO
										Esto se deja para pruebas, 
										Hay que mostrar solo aquellos cursos cuyo TIPO_estado == 2 -->
									<p class = "ofertas-titulo">Cupon ${cupon.id_cupon }</p>
									<c:choose>
										<c:when test="${cupon.descuento == 0 }">
											<p class = "ofertas-titulo">Descuento Fijo: ${cupon.descuento }€ descuento</p>
		
										</c:when>
										<c:otherwise>
											<p class = "ofertas-titulo">Descuento Porcentajes: ${cupon.descuento }% de descuento</p>
										</c:otherwise>
									</c:choose>
									
									<p class = "ofertas-titulo">${cupon.fecha_fin } fin del cupon.</p>
								</div>		
							</li>
						</c:forEach> 
						</ul>
						
						<input type = "button" name = "actualizar" value = "Añadir cupon" id = "añadir-oferta2" class = "añadir-oferta">
						
						<c:if test="${mensaje1 != null }">
							<p class="error">${mensaje1 }</p>
						</c:if>
						<c:if test="${mensaje2 != null }">
							<p class="error">${mensaje2 }</p>
						</c:if>
						<c:if test="${mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
						<div id="añadir">

					        <h2> Añade un nuevo cupon </h2>  
			
							<form action="AltaCupones" method="post">
							
					        <!-- Titulo del Curso -->

					       	<div id="formul1">  
					        	<p class="nombre">Nombre del curso<span class=aster>*</span>:</p>
						        <p  id="mens1">No ha especificado el nombre del curso*</p>
						        <input type="text" name="nombreCurso" id="nombreCurso"/>
					      	</div>

					        <!-- Precio del descuento -->

					        <div id="formul2">  
					        	<p class="nombre">Precio de descuento<span class=aster>*</span>:</p>  
						        <p  id="mens2">No ha especificado el precio de descuento*</p>
						        <input  type="text" name="precio" id="precio"/>
					        </div>

					        <!-- Tipo de descuento -->

					        <div id="formul3">  
						        <p class="nombre">Tipo de Cupon<span class=aster>*</span>:</p> 
						        <select name="tipo_cupon" id="tipo_cupon">
						        	
  									<option value="0">Precio Fijo</option>
  									<option value="1">Porcentaje</option>
  								</select> 
					        </div>

					        <!-- Fecha fin cupon -->

					        <div id="formul4">  
					        	<p class="nombre">Fecha final del Cupon<span class=aster>*</span>:</p>  
						        <p  id="mens4">No ha especificado la fecha final del Cupon*</p>
								<textarea name="fecha_fin" id="fecha_fin"></textarea>
					        </div>
					        
					        <!-- Boton añadir -->
							<input type="submit" id="añadirboton" value="Añadir" />
							</form>
					   	</div>
					</div>
					
				</div>
				
				<div id = "seguidores">
				
					<h5> seguidores </h5>
					
					<ul>
					
						<li>
							<img class = "seguidores-foto" src = "images/famosos/gates.jpg" alt = "Error en la imagen">
							<p class = "seguidores-info">Bill Gates</p></li>
					
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
		
		<script src ="<c:url value="script/cupones.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>