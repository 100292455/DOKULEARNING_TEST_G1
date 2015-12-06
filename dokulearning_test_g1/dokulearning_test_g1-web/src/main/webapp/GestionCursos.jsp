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
				
					<input type = "button" name = "actualizar" value = "Añadir curso" id = "añadir-oferta1" class = "añadir-oferta añadir-curso">
					<a href="GestionCupones.jsp"><input style="margin-top:8%; margin-left:4%" type = "button" value = "Añadir cupon" id = "añadir-oferta2" class = "añadir-oferta" ></a>
				
					<h4>MIS CURSOS</h4>
					
					
					
					<div id = "ofertas">
				 		<c:if test="${empty cursos }">
							<!-- cursos es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
							<p class="error">Actualmente no imparte ningun curso.</p>
						</c:if>
				
						<ul>
		<!-- 				<c:forEach items="${cursos }" var="curso"> 
						<!-- recorremos todos los objetos de la coleccion cursos 
							y cada objeto devuelto lo asignamos a la variable curso -->
						
							<li id = "oferta-ejemplo${curso.ID_curso}">
								<div class = "ofertas-descripcion">
								<!-- TO-DO
										Esto se deja para pruebas, 
										Hay que mostrar solo aquellos cursos cuyo TIPO_estado == 2 -->
									<c:choose>
										<c:when test="${curso.TIPO_estado == 2 }">
											<p class = "ofertas-titulo">${curso.DES_titulo }</p>
										</c:when>
										<c:otherwise>
											<p class = "ofertas-titulo">${curso.DES_titulo }. AUN POR VALIDAR</p>
										</c:otherwise>
									</c:choose>
									<p class = "ofertas-empresa">Impartido por: <!-- TO-DO cambiar COD_prof por nombre -->${curso.profesor.ID_usuario }</p>
									<p class = "ofertas-resumen">${curso.DES_descripcion }</p>
									<p class = "ofertas-tipo-contrato">${curso.horas } hrs.</p>
									<p class = "ofertas-jornada">${curso.precio_final } euros.</p>
									<p class = "ofertas-jornada">${curso.ID_curso } ID.</p>
									<c:choose>
										<c:when test="${curso.TIPO_dificultad == 0 }">
											<p class = "ofertas-salario">Nivel basico.</p>
										</c:when>
										<c:when test="${curso.TIPO_dificultad == 1 }">
											<p class = "ofertas-salario">Nivel intermedio.</p>
										</c:when>
										<c:otherwise>
											<p class = "ofertas-salario">Nivel avanzado.</p>
										</c:otherwise>
									</c:choose>
								</div>
							
								<!-- TO-DO -->
								<div class = "ofertas-seguidores">
									<img src = "images/cursoImages/curso_${curso.ID_curso }.jpg" alt = "Error en la imagen del curso ${curso.ID_curso }">
									<p class = "numero-seguidores"><a style="color:white" href="EnlaceCS?IdCurso=${curso.ID_curso }">Gestionar este Curso</a></p>
								</div>
								<!-- *** -->
								<div class = "ofertas-edicion">
									<form action="BajaCursos" method="post">
										<input type="hidden" name="IdCurso" value="${curso.ID_curso }">
										<button type="submit">	
											<img class="eliminar-icon" src="images/edicion/trash.png" alt="Error en la imagen">
										</button>
									</form>
								</div>
								
							</li>
		<!-- 				</c:forEach> -->
						</ul>
						
						<input type = "button" name = "actualizar" value = "Añadir curso" id = "añadir-oferta3" class = "añadir-oferta añadir-curso">
						
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
						<div id="añadir">

					        <h2> Añade un nuevo curso </h2>  
							<!-- 				
							<div id="formul">  
					        	<p class="nombre">Seleccione una imagen para el curso<span class=aster>*</span>.</p>  
					        </div>
							<form action="UploadCursoImagesServlet" method="post" enctype="multipart/form-data">
								<input type="hidden" name="Curso" size="60" />
								<input type="file" name="file" size="60" /> <br />
								<input type="submit" value="Cargar Imagen" />
							</form>
							<!-- ***************************************************************** -->
			
							<form action="AltaCursos" method="post" enctype="multipart/form-data">
							
							<div id="formul">  
					        	<p class="nombre">Seleccione una imagen para el curso<span class=aster>*</span>.</p>  
					        </div>
							
							<input type="hidden" name="Curso" size="60" />
							<input type="file" name="file" size="60" /> <br />
							
					        <!-- Titulo del curso -->

					       	<div id="formul1">  
					        	<p class="nombre">Titulo del curso<span class=aster>*</span>:</p>
						        <p  id="mens1">No ha especificado el titulo del curso*</p>
						        <input type="text" name="titulo" id="añadir-titulo"  placeholder = "TÃ­tulo del curso"/>
					      	</div>

					        <!-- Horas del curso -->

					        <div id="formul2">  
					        	<p class="nombre">Horas de dedicacion del curso<span class=aster>*</span>:</p>  
						        <p  id="mens2">No ha especificado el numero de horas de dedicacion del curso*</p>
						        <input  type="text" name="horas" id="añadir-horas" placeholder = "Horas de dedicacion del curso"/>
					        </div>

					        <!-- Dificultad del curso -->

					        <div id="formul3">  
						        <p class="nombre">Dificultad del curso<span class=aster>*</span>:</p> 
						        <select name="dificultad">
						        	<option value="-1" selected>Especifique dificultad del curso</option>
  									<option value="0">Basico</option>
  									<option value="1">Intermedio</option>
  									<option value="2">Avanzado</option>
								</select> 
					        </div>

					        <!-- DescripciÃ³n del curso -->

					        <div id="formul4">  
					        	<p class="nombre">DescripciÃ³n del curso<span class=aster>*</span>:</p>  
						        <p  id="mens4">No ha especificado la descripciÃ³n del curso*</p>
								<textarea id="añadir-descripcion" name="descripcion" placeholder = "Descripcion del curso"></textarea>
					        </div>
					
							<!-- Precio de matricula -->
					        <<div id="formul7">  
						        <p class="nombre">Tematica<span class=aster>*</span>:</p> 
						        <select name="tematica">
						        	<option value="-1" selected>Especifique la tematica del curso</option>
  									<option value="0">Humanidades</option>
  									<option value="1">Ciencias</option>
  									<option value="2">Letras</option>
								</select> 
					        </div>
					        		
							<!-- Precio de matricula -->
					        <div id="formul5">  
					        	<p class="nombre">Precio de matricula<span class=aster>*</span>:</p>  
						        <p  id="mens5">No ha especificado precio de matricula*</p>
						        <input  type="text" name="precio" id="añadir-precio" placeholder = "Precio de matricula"/>
					        </div>

							<div id="formul6">  
					        	<p class="nombre">Recuerde que usted sera el encargado de impartir este curso<span class=aster>*</span>.</p>  
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