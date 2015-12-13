<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Foro</title>
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Foro para curso">
		<meta name="author" content="Alejandro Ruiz">
		
		<!--CSS de la pagina-->
		
		<link rel="stylesheet" type="text/css" href="./style/foro.css">
		
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
</head>
<body>

			<header>

 			<!--CABECERA-->
                
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
            
            	<div id = "contenedor-foro">
            		
            		<h3>${requestScope.curso.DES_titulo}</h3>
            		
            		<div id = "contenedor-mensaje">
            		
            			<div id= "contenedor-participantes">
            				<ul>
            				    <li>
            						<img  src="images/famosos/alice.jpg" alt="Error en la imagen">  
            						 <p>${requestScope.curso.profesor.nombre} ${requestScope.curso.profesor.apellido}</p>
            					</li>
            					<c:forEach items="${requestScope.matriculascursoactual }" var="matricula">
	            					 <li>
	            						<img  src="images/famosos/alice.jpg" alt="Error en la imagen">  
	            						 <p>${matricula.alumno.nombre} ${matricula.alumno.nombre}</p>
	            					</li>
            					</c:forEach>
                			</ul>
            			</div>
            			
            			<div id = "contenedor-conversacion">
            				
            				<div id = conversacion>
            					
            					<ul>
            						<c:forEach items="${requestScope.listaMensajes }" var="listaMensajes">
            							<c:choose>
											<c:when  test="${requestScope.usuario.ID_usuario eq listaMensajes.emisor.ID_usuario}">
	            							   <li>
			            							<div class="emisor">
				            							<p>${listaMensajes.emisor.nombre}</p>
				            							<p>${listaMensajes.DES_mensaje}</p>
			       							     	</div>
			            						</li>								
			            					</c:when>
											<c:otherwise>
	            							   <li>
			            							<div class="remitente">
				            							<p>${listaMensajes.emisor.nombre}</p>
				            							<p>${listaMensajes.DES_mensaje}</p>
			       							     	</div>
			            						</li>
											</c:otherwise>
										</c:choose>
             						</c:forEach>           			
            					</ul>
            				
            				</div>
            				
            				<div id="contenedor-enviar"> 
            					<form action="mensajes" method="post">
 									<input type="text" name="mensaje" placeholder="Escribir mensaje">
  									<input type="submit" value="Enviar">
								</form>              				
              				</div>
            				
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