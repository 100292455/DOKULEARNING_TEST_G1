
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Contenido Lecciones</title>
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
				
				<!-- Crear un leccion nuevo -->
									 
						<div id="añadir-leccion">

					        <h2> Añade una nueva leccion </h2>  
			
							<form action="AltaLeccion" method="post" enctype="multipart/form-data" id ="añadir-leccion-form" onsubmit="return validarcrearleccion();">
								
								<div> 
								<!-- Titulo de la leccion -->
	
							       	<div id="formul1">  
							        	<p class="nombre">Titulo de la leccion<span class=aster>*</span>:</p>
								        <p  id="mens1">No ha especificado el titulo de la leccion*</p>
								        <input type="text" name="titulo" id="titulo-leccion"  placeholder = "Titulo de la leccion"/>
							      	</div>		
							      	
								 <!-- Formato de la leccion -->

							        <div id="formul3">  
								        <p class="nombre">Formato de la leccion<span class=aster>*</span>:</p> 
								        <p  id="mens2">No ha especificado el formato de la leccion*</p>
								        <select name="formato" id="formato-leccion">
								        	<option value="otro" selected>Especifique el formato de la leccion</option>
		  									<option value="txt">Archivo txt</option>
		  									<option value="doc">Archivo doc</option>
		  									<option value="docx">Archivo docx</option>
		  									<option value="xls">Archivo excel</option>
		  									<option value="ppt">Archivo pdf</option>
		  									<option value="mp4">Video en mp4</option>
		  									<option value="m4v">Video en m4v</option>
											<option value="mp3">Audio en mp3</option>
										</select> 
							        </div>
					        	</div>
					        	
								
								
								<!-- Importar imagen -->
								
								
								<div id="formul">  
						        	<p class="nombre">Seleccione una imagen para la leccion<span class=aster>*</span>.</p>  
									<input type="hidden" name="Curso" size="60" />
									<input type="file" name="file" size="60" /> <br />
								 </div>
								 

					        <!-- Descripcion de la leccion -->

					        <div id="formul4">  
					        	<p class="nombre">Descipcion de la leccion<span class=aster>*</span>:</p>  
						        <p  id="mens4">No ha especificado la descripcion de la leccion*</p>
								<textarea id="añadir-descripcion" name="descripcion" placeholder = "Descripcion de la leccion"></textarea>
					        </div>
					
							

							<div id="formul6">  
					        	<p class="nombre">Recuerde que usted sera el encargado de impartir esta leccion<span class=aster></span>.</p>  
					        </div>

					        <!-- Boton añadir -->

							<input type="submit" id="añadir-leccion-button" value="Añadir" />
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
