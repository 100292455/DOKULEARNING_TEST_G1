<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Gestion de secciones</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos en linea">
		<meta name="author" content="Miguel Solera Martin">
		<link href="<c:url value="/style/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
		<link rel="stylesheet" type="text/css" href="style/busqueda.css">
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
				
					
				
					<h4>Mi gestion de Secciones</h4>
					
					
					
					<div id = "ofertas">
				 		<c:if test="${empty secciones }">
							<!-- cursos es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
							<!-- RESCATAR NOMBRE DEL CURSO EN EL QUE ESTAMOS-->
							<p class="error">Actualmente no existe ninguna seccion</p>  
						</c:if>
				
						<ul>
		 			 <c:forEach items="${secciones }" var="seccion">     <!-- METER VECTOR AUXILIAR QUE YO QUIERO -->
			
						
							<li id = "oferta-ejemplo${seccion.id_seccion}">
								<div class = "ofertas-descripcion">
									<c:choose>
										<c:when test="${seccion.nombre}">
											<p class = "ofertas-titulo">${seccion.nombre}</p>
										</c:when>
										<c:otherwise>
											<p class = "ofertas-titulo">Nombre: ${seccion.nombre}. AUN POR VALIDAR</p>
											<p class = "ofertas-titulo">Curso al que pertenece: ${nombrecursoactual}  </p>
										</c:otherwise>
									</c:choose>
									
								    
								</div>
								
								<div class = "ofertas-seguidores">
									<img src = "images/edicion/seguidores-icon.png" alt = "Error en la imagen">
									<p class = "numero-seguidores"><a style="color:white" href="EnlaceSL?IdSeccion=${seccion.id_seccion}">Gestionar esta Seccion</a></p>
								</div>
									
								<div class = "ofertas-edicion">
									<form action="BajaSeccionServlet" method="post">
										<input type="hidden" name="IdSeccion" value="${seccion.id_seccion}">
										<button type="submit">	
											<img class="eliminar-icon" src="images/edicion/trash.png" alt="Error en la imagen">
										</button>
									</form>
								</div>
								
							</li>
					</c:forEach>
						</ul>
						
						<input type = "button" name = "actualizar" value = "Añadir seccion" id = "añadir-oferta2" class = "añadir-oferta">
						<a href="GestionCursos.jsp"><input type = "button" name = "actualizar" value = "Volver a mis cursos" id = "añadir-oferta2" class = "añadir-oferta"></a>
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
						<div id="añadir">

					        <h2> Añade una nueva seccion </h2>  
							
							<!-- ******************* TO-DO ******************* -->
					        <!-- ******* AÑADIR FOTOGRAFIA DEL CURSO ********* -->
					        <!-- http://www.tutorialspoint.com/jsp/jsp_file_uploading.htm -->
					        <!-- INCLUIR EN WEB.XML
					        <web-app>
								....
								<context-param> 
   									<description>Location to store uploaded file</description> 
    								<param-name>file-upload</param-name> 
    								<param-value>
         								c:\apache-tomcat-5.5.29\webapps\data\
     								</param-value> 
								</context-param>
								....
								
							</web-app> 
							-->
							<!-- ***************************************************************** -->
			
							<form action="AltaSeccion" method="post">
							
					        <!-- Titulo del curso -->

					       	<div id="formul1">  
					        	<p class="nombre">Titulo de la seccion<span class=aster>*</span>:</p>
						        <p  id="mens1">No ha especificado el titulo de la seccion*</p>
						        <input type="text" name="titulo" id="añadir-titulo"  placeholder = "Título de la seccion"/>
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
		
		<script src ="<c:url value="script/empresa-mis-ofertas.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>