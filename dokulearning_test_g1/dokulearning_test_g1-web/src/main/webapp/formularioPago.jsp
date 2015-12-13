<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

 
		<!-- Información sobre el documento -->
	
		<title>Practica final: Formulario Pago</title>
		
		<meta name="keywords" content="buscar, trabajo, empleo, salario">
		<meta name="description" content="Web para la busqueda de empleo">
		<meta name="author" content="Jorge Garcia">
	
		<!--Fuente Ubuntu-->
		
		<link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
	
		<!--CSS de la pagina-->
		
		<link rel="stylesheet" type="text/css" href="style/formulario-pago.css">
		
		
		<!--SCRIPT de la pagina-->
		<link href="script/jquery-ui-1.11.2.custom/jquery-ui.css" rel="stylesheet">
		
		<!--JQUERY-->

		<script src="script/jquery-ui-1.11.2.custom/external/jquery/jquery.js"></script>
		<script src="script/jquery-ui-1.11.2.custom/jquery-ui.js"></script>
		<style type="text/css">
			.error {color: red;}
		</style>

	</head>
	
	
	<body>
		
	
			<header>
                
                <a href="index.jsp">    
                    
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

			<!--MENU-->
	
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
			
			<div id = "formulario-pago">
				
				<h4>Pago de Matricula</h4>

				<form method="get" action="WebService" onsubmit="return comprobarTarjeta();">
					<c:choose>
							<c:when test="${not empty mensajePago }">
								<p class="error">${mensajePago }</p>
							</c:when>
							<c:otherwise>
								<p>Introduce tus datos para formalizar la matricula del curso</p>
							</c:otherwise>
						</c:choose>
					<div id="form-name" class="form-pago-class">
							<!--Nombre-->
							<p><span class=obligatorio>Nombre (como aparece en tu tarjeta)</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<input  type="text" id="nombre" name="nombre" placeholder="Alejandro Ruiz" />							
							<p id="mens1" class = "mens">Te falta rellenar el nombre de la tarjeta*</p>							
					</div>		
							<!--Tarjeta-->
					<div id="form-number" class="form-pago-class">		
							<p><span class=obligatorio>Numero de tarjeta (sin guiones o espacios)</span><span class=aster>*</span><span class=obligatorio>:</span></p>
							<input  type="text" name="numero" id="numero" placeholder="A234123412341234" />					
							<p id="mens2" class = "mens">Te falta rellenar el numero de la tarjeta*</p>							
					</div>						
					
					<div id = "registrar-boton">
						<input class = "registrar1" name='filtro' type="submit" value="Facturar">					
					</div>
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
		
		<script type = "text/javascript" src ="script/formulario-pago.js"></script>
		
		
	</body>

</html>