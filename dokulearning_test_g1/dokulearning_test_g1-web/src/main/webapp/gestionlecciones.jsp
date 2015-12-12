<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Gestion de lecciones</title>
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
				
					
				
					<h4>GESTION DE LECCIONES</h4>
					
					
					
					<div id = "ofertas">
				 		<c:if test="${empty lecciones }">
							<!-- cursos es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
							<p class="error">Actualmente no existe ninguna leccion asociada a este curso.</p>
						</c:if>
				
						<ul>
		 				<c:forEach items="${lecciones }" var="leccion"> 
			
						
							<li id = "oferta-ejemplo${leccion.ID_leccion}">
								<div class = "ofertas-descripcion">
								<!-- TO-DO
										Esto se deja para pruebas, 
										Hay que mostrar solo aquellos cursos cuyo TIPO_estado == 2 -->
									<c:choose>
										<c:when test="${leccion.titulo}">
											<p class = "ofertas-titulo">${leccion.titulo}</p>
										</c:when>
										<c:otherwise>
											<p class = "ofertas-titulo">Nombre: ${leccion.titulo}. AUN POR VALIDAR</p>
										</c:otherwise>
									</c:choose>
									<p class = "ofertas-empresa">Descripcion de la leccion:</p>
									<p class = "ofertas-resumen">${leccion.des }</p>
			
									<p class = "ofertas-tipo-contrato">formato: ${leccion.formato} </p>
								    <p class = "ofertas-titulo">Seccion a la que pertenece: ${nombreseccionactual}  </p>
								</div>
								

									
								<div class = "ofertas-edicion">
									<form action="BajaLeccionServlet" method="post">
										<input type="hidden" name="IdLeccion" value="${leccion.ID_leccion}">
										<button type="submit">	
											<img class="eliminar-icon" src="images/edicion/trash.png" alt="Error en la imagen">
										</button>
									</form>
								</div>
								
							</li>
					</c:forEach>
						</ul>
						
						<input type = "button" name = "actualizar" value = "Añadir leccion" id = "añadir-oferta2" class = "añadir-oferta">
						<a href="gestionSecciones.jsp"><input type = "button" name = "actualizar" value = "Volver a secciones" id = "añadir-oferta2" class = "añadir-oferta"></a>
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
						<div id="añadir">

					        <h2> Añade una nueva leccion </h2>  
							
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
			
							<form action="AltaLeccion" method="post" enctype="multipart/form-data">
							
							<div id="formul">  
					        	<p class="nombre">Seleccione un archivo para la leccion<span class=aster>*</span>.</p>  
					        </div>
							
							<input type="hidden" name="Leccion" size="60" />
							<input type="file" name="file" size="60" /><br /> 
							     
							
					        <!-- Titulo del curso -->

					       	<div id="formul1">  
					        	<p class="nombre">Titulo de la leccion<span class=aster>*</span>:</p>
						        <p  id="mens1">No ha especificado el titulo de la leccion*</p>
						        <input type="text" name="titulo" id="añadir-titulo"  placeholder = "Título de la leccion"/>
					      	</div>

					       

					        <!-- Formato -->

					        <div id="formul3">  
					        	<p class="nombre">Formato<span class=aster>*</span>:</p>  
						        <p  id="mens2">No ha especificado el id del curso*</p>
						        <input  type="text" name="formato" id="añadir-horas" placeholder = ".txt"/>
					        </div>

					        <!-- Descripción del curso -->

					        <div id="formul4">  
					        	<p class="nombre">Descripción de la leccion<span class=aster>*</span>:</p>  
						        <p  id="mens4">No ha especificado la descripción de la leccion*</p>
								<textarea id="añadir-descripcion" name="descripcion" placeholder = "Descripcion de la leccion"></textarea>
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