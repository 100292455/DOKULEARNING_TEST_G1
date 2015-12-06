
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Mis Cursos</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos matriculados">
		<meta name="author" content="Jorge Garcia de la Rosa">
		
		<!--CSS de la pagina-->
		
		<link rel="stylesheet" type="text/css" href="./style/empresa-mis-ofertas.css">
		
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
		
	
		<header>
                
                <a href="miPerfilAlumno.jsp">    
                    
                    <img class = "cabecera" src="images/logo.png" alt="Error en la imagen">    
                
                    <h1 class = "cabecera">DOKU</h1>
                
                </a>
                
                    <a href = "misCursos.jsp"><p class = "cabecera1" id = "cabecera-empresa">MIS CURSOS</p></a>
                    
                    <p class = "cabecera">|</p>
                        
                    <a href = "miPerfilAlumno.jsp"><p class = "cabecera1" id = "cabecera-usuario">MI PERFIL</p></a>
                    
                    <p class = "cabecera">|</p>
                        
                    <a href = "listadoCursos.jsp"><p class = "cabecera1" id = "cabecera-usuario">CURSOS</p></a>
                
                    
               <p class = "cabecera">|</p>
                        
                    <a href = "sesion?accion=salir"><p class = "cabecera1" id = "cabecera-usuario">SALIR</p></a>
                    
                    
            
            </header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
			
			<div id="tabs">
			
			  <ul>
			    <li><a href="#tabs-1">GESTIONAR CUPONES</a></li>
			    <li><a href="#tabs-2">GESTIONAR SECCIONES</a></li>
			  </ul>
			  
			  <!-- CURSOS MATRICULADOS -->
			  
			  <div id="tabs-1">
			 	<div class = "mi-empresa">
					<h4>GESTIONAR CUPONES</h4>
						<input type = "button" name = "actualizar" value = "Añadir cupon" id = "añadir-oferta1" class = "añadir-oferta">
						<div id = "ofertas">
				 		<c:if test="${empty cupones }">
							<p class="error">Actualmente no hay cupones.</p>
						</c:if>
	
						<ul>
							<c:forEach items="${cupones }" var="cupon"> 	
								<li id = "oferta-ejemplo">
									<div class = "ofertas-descripcion">
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
				
			</section>		 
					</div> 
				</div>
				
				<!-- CURSOS CREADOS -->
			  
			  <div id="tabs-2">
			 	<div id = "mi-empresa">
				<h4>CURSOS CREADOS</h4>
					<!-- Usuario con metodo de pago, puede crear curso -->

					<c:if test="${usuario.tipo_usuario == 1 }">
					
						<input type = "button" name = "actualizar" value = "Añadir curso" id = "añadir-oferta1" class = "añadir-oferta añadir-curso">
						<input type="hidden" id="selectedTabInput" value="${requestScope.selectedTab}">
					</c:if>
					
					<!-- Usuario con metodo de pago, puede crear curso -->
					
					<c:if test="${usuario.tipo_usuario == 0 }">
					
						<input type = "button" onclick = "window.location.href='./formularioPago.jsp'"  name = anadir_metodo_pago" value = "Crea tu curso" id = "añadir-oferta2">
						<p class = "ofertas-titulo">Empieza a crear cursos, solo tienes que enlazar una cuenta bancaria. </p>

					</c:if>
					
					<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
					</c:if>
					
					<!-- Lista de cursos creados -->
						<ul>
							<c:forEach items="${cursoscreados }" var="curso">
								<li id = "oferta-ejemplo${curso.ID_curso}">
									<div class = "ofertas-descripcion">
										<!-- Descripcion de ofertas -->
										<p class = "ofertas-titulo">${curso.DES_titulo }</p>
										<p class = "ofertas-empresa">Impartido por: <!-- TO-DO cambiar COD_prof por nombre -->${curso.profesor.nombre }</p>
										<p class = "ofertas-resumen">${curso.DES_descripcion }</p>
										<p class = "ofertas-tipo-contrato">${curso.horas } hrs.</p>
										<p class = "ofertas-jornada">Precio inicial: ${curso.precio_inicial } euros.</p>
										<p class = "ofertas-jornada">Precio final: ${curso.precio_final } euros.</p>
										<c:if test="${not empty curso.fechaFinDescuento }">
												<p class = "ofertas-jornada">fin descuento: ${curso.fechaFinDescuento }</p>
										</c:if>
										<c:choose>
											<c:when test="${curso.TIPO_dificultad == 0 }">
											<p class = "ofertas-salario">Basico.</p>
										</c:when>
										<c:when test="${curso.TIPO_dificultad == 1 }">
											<p class = "ofertas-salario">Intermedio.</p>
										</c:when>
										<c:otherwise>
											<p class = "ofertas-salario">Avanzado.</p>
										</c:otherwise>
										</c:choose>
									</div>
									<div class = "ofertas-seguidores">
										<img src = "images/edicion/seguidores-icon.png" alt = "Error en la imagen">
										<p class = "numero-seguidores"><a  href="contenidoCurso?nombreCurso=${curso.DES_titulo }">Ver Contenidos</a></p>
									</div>
									<div class = "ofertas-edicion">
										<form action="BajaCursos" method="post" onsubmit="false">
											<input type="hidden" name="IdCurso" value="${curso.ID_curso }">
											<button id ="delete-openner" type="submit">	
												<img class="eliminar-icon" src="images/edicion/trash.png" alt="Error en la imagen">
											</button>
										</form>
									</div>
								</li>
							</c:forEach>						
						</ul>
						
						<!-- Crear un curso nuevo -->
									 
						<div id="añadir">

					        <h2> Añade un nuevo curso </h2>  
							
			
							<form action="AltaCursos" method="post" enctype="multipart/form-data" id ="añadir-form" onsubmit="return validarcrearcurso();">
								
								<div> 
								<!-- Titulo del curso -->
	
							       	<div id="formul1">  
							        	<p class="nombre">Titulo del curso<span class=aster>*</span>:</p>
								        <p  id="mens1">No ha especificado el titulo del curso*</p>
								        <input type="text" name="titulo" id="añadir-titulo"  placeholder = "Titulo del curso"/>
							      	</div>		
							      	
								 <!-- Dificultad del curso -->

							        <div id="formul3">  
								        <p class="nombre">Dificultad del curso<span class=aster>*</span>:</p> 
								        <p  id="mens2">No ha especificado la dificultad del curso*</p>
								        <select name="dificultad" id="dificultad-curso">
								        	<option value="-1" selected>Especifique dificultad del curso</option>
		  									<option value="0">Basico</option>
		  									<option value="1">Intermedio</option>
		  									<option value="2">Avanzado</option>
										</select> 
							        </div>
					        	</div>
					        	
					        	<div> 
								<!-- Horas del curso -->

							        <div id="formul2">  
							        	<p class="nombre">Horas de dedicacion del curso<span class=aster>*</span>:</p>  
							        	<p  id="mens3">Falta especificar el numero de horas del curso*</p>
								        <input id="spinner" name="horas" >
							        </div>		
									      	
								<!-- Precio de matricula -->

							        <div id="formul5">  
							        	<p class="nombre">Precio de matricula<span class=aster>*</span>:</p>  
								        <p  id="mens5">No ha especificado precio de matricula*</p>
								        <input  type="text" name="precio" id="añadir-precio" placeholder = "Precio de matricula"/>
							        </div>
					        	</div>
					        	
					        		<div> 
								<!-- Importar imagen -->
								
								
									<div id="formul">  
							    	    <p  id="mens6">Solo se admiten imagenes con formato jpg*</p>
							        	<p class="nombre">Seleccione una imagen <span class=aster>jpg</span> para el curso<span class=aster>*</span>.</p>  
										<input type="file" name="file" id="imagen-curso" size="60" />
									 </div>	
							      	
								 <!-- Dificultad del curso -->

							        <div id="formul7">  
								        <p class="nombre">Tematica del curso<span class=aster>*</span>:</p> 
								        <p  id="mens7">No ha especificado la tematica del curso*</p>
								        <select name="tematica" id="tematica-curso">
								        	<option value="-1" selected>Especifique la tematica del curso</option>
		  									<option value="0">Artes</option>
		  									<option value="1">Ciencias</option>
		  									<option value="2">Ingenieria</option>
										</select> 
							        </div>
					        	</div>
								 

					        <!-- Descripcion del curso -->

					        <div id="formul4">  
					        	<p class="nombre">Descipcion del curso<span class=aster>*</span>:</p>  
						        <p  id="mens4">No ha especificado la descripcion del curso*</p>
								<textarea id="añadir-descripcion" name="descripcion" placeholder = "Descripcion del curso"></textarea>
					        </div>
					
							

							<div id="formul6">  
					        	<p class="nombre">Recuerde que usted sera el encargado de impartir este curso<span class=aster></span>.</p>  
					        </div>

					        <!-- Boton añadir -->

							<input type="submit" id="añadirboton" value="Añadir" />
							</form>
					   	</div>
					</div> 
				</div>				
				
			 
 			<!-- LISTA DE DESEOS -->
			  
			  <div id="tabs-3">
			 	<div class = "mi-empresa">
				<h4>LISTA DE DESEOS</h4>
						<ul>
							<c:forEach items="${listadeseos }" var="curso">
								<li id = "oferta-ejemplo${curso.ID_curso}">
									<div class = "ofertas-descripcion">
										<!-- Descripcion de ofertas -->
										<p class = "ofertas-titulo">${curso.DES_titulo }</p>
										<p class = "ofertas-empresa">Impartido por: <!-- TO-DO cambiar COD_prof por nombre -->${curso.COD_profesor }</p>
										<p class = "ofertas-resumen">${curso.DES_descripcion }</p>
										<p class = "ofertas-tipo-contrato">${curso.horas } hrs.</p>
										<p class = "ofertas-jornada">Precio inicial: ${curso.precio_inicial } euros.</p>
										<p class = "ofertas-jornada">Precio final: ${curso.precio_final } euros.</p>
										<c:if test="${not empty curso.fechaFinDescuento }">
												<p class = "ofertas-jornada">fin descuento: ${curso.fechaFinDescuento }</p>
										</c:if>
										<c:choose>
											<c:when test="${curso.TIPO_dificultad == 0 }">
											<p class = "ofertas-salario">Basico.</p>
										</c:when>
										<c:when test="${curso.TIPO_dificultad == 1 }">
											<p class = "ofertas-salario">Intermedio.</p>
										</c:when>
										<c:otherwise>
											<p class = "ofertas-salario">Avanzado.</p>
										</c:otherwise>
										</c:choose>
									</div>
									<div class = "ofertas-seguidores">
										<img src = "images/edicion/seguidores-icon.png" alt = "Error en la imagen">
										<p class = "numero-seguidores"><a  href="contenidoCurso?nombreCurso=${curso.DES_titulo }">Ver Contenidos</a></p>
									</div>
								</li>
							</c:forEach>
						</ul>			 
					</div> 
				</div>
				
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
