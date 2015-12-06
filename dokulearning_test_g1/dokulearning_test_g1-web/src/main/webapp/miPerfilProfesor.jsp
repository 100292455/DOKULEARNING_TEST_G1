<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- Información sobre el documento -->
	
		<title>Practica final: Mis Ofertas</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="buscar, trabajo, empleo, salario">
		<meta name="description" content="Web para la busqueda de empleo">
		<meta name="author" content="Mingda Liu Zhang, Alejando Ruiz de la Cuadra, Carlos Herrero Lozano">
	
		<!--Fuente Ubuntu-->
		
		<link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
	
		<!--CSS de la pagina-->
		
		<link rel="stylesheet" type="text/css" href="./style/mi-empresa.css">
		<link rel="stylesheet" type="text/css" href="style/busqueda.css">
		
		<!--<script type = "text/javascript" src ="script/myscript.js"></script>-->
		<link href="./script/jquery-ui-1.11.2.custom/jquery-ui.css" rel="stylesheet">

		<script src="./script/jquery-ui-1.11.2.custom/external/jquery/jquery.js"></script>
		<script src="./script/jquery-ui-1.11.2.custom/jquery-ui.js"></script>
		
		<!--Text edit-->
		
		<link type="text/css" rel="stylesheet" href="./script/plugin/edit/jquery-te-1.4.0.css">

		<script type="text/javascript" src="./script/plugin/edit/jquery-te-1.4.0.min.js" charset="utf-8"></script>
		

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


				<!--MENU-->
			
			
				
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id = "mi-empresa">
				
					<h4>PERFIL PROFESOR</h4>
				
					<div id = "informacion-empresa">
						
						<div id= "informacion-empresa-imagen">
										
							<img id = "imagen-empresa" src = "./images/uploadFiles/Alejandro.jpg" alt = "Error en la imagen">
							<form method="post" action="UploadServlet" enctype="multipart/form-data">
       							<input type="file" name="picField" id="picField"  onchange="preview_2(this);" />
        						<input type="submit" value="Upload" />
   							 </form>
							<p>Para cambiar imagen seleccione un archivo</p>
							
						</div>
					
						<div id = "informacion-empresa-basica">
							
						
							<h5>Información básica empresa</h5>
						
							<ul>
								<li>Nombre: <br><input id = "edit-nombre" type="text" name="nombre" value="${sessionScope.usuario.nombre}" tabindex="1"></li>
							
								<li>Apellido: <br><input id = "edit-empresa" type="text" name="empresa" value="${sessionScope.usuario.apellido}" tabindex="2"></li>
							
								<li>Teléfono: <br><input id = "edit-telefono" type="text" name="telefono" value="${sessionScope.usuario.telefono}" tabindex="5"></li>
							
								<li>E-Mail: <br><input id = "edit-email" type="text" name="correo" value="${sessionScope.usuario.email}" tabindex="6"></li>
													
							
							</ul>							
							
						</div>
				
					</div>
					
					<div id = "informacion-empresa-descripcion">
						
						<h5>Descripción general</h5>
						
						Microsoft trabaja para que su modelo de negocio sea cada vez más un motor de crecimiento de la economía española, creando innovaciones y promoviendo iniciativas capaces de crear empleos y mejorar la competitividad de nuestras empresas y el país en su globalidad.
						<br>
						<br>
						Las soluciones de Microsoft permiten la reducción de los costes relacionados con operaciones del día a día y liberar presupuesto para innovaciones que otorguen un mayor valor de negocio. Detrás de cada mejora en nuestra oferta tecnológica está un objetivo y un compromiso: ayudar, con nuestras soluciones y el valor aportado por nuestros socios de negocio, a transformar España hacia una economía y sociedad netamente innovadoras, con la misión de crear empleo como clara prioridad.
						<br>
						<br>
						Creemos firmemente que la innovación bien entendida y bien aplicada es un generador de empleo sostenible y de calidad. Y basándonos en esta premisa, desarrollamos en España diversos programas dirigidos a multiplicar los beneficios de la transferencia de conocimiento y tecnología sobre las empresas locales y a apoyar a las empresas y jóvenes estudiantes emprendedores a sentar las bases de una nueva generación de negocios innovadores, que son ahora y serán más aún en el futuro auténticos dinamizadores del mercado de trabajo.
						
					</div>
					
					
					<div id = "dialog-button"><input type = "button" name = "vista-previa" value = "Vista Previa" id = "opener"></div >
					
					<div id="dialog" title="Vista Previa">
						
						<img id = "imagen-vista-previa" src = "default" alt = "Error en la imagen">
						
						<div id = "vista-previa-datos">
							
					  		<p id = "vista-previa-nombre"></p>
							<p id = "vista-previa-empresa"></p>
							<p id = "vista-previa-direccion"></p>
							<p id = "vista-previa-ciudad"></p>
							<p id = "vista-previa-telefono"></p>
							<p id = "vista-previa-email"></p>
							<p id = "vista-previa-representante"></p>	
							
						</div>
						
						<div id = "vista-previa-descripcion"></div>					
					
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
		
		<script type = "text/javascript" src ="./script/mi-empresa.js"></script>
</body>
</html>