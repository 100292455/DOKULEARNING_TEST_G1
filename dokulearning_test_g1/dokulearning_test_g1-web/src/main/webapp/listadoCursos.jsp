<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<!-- Información sobre el documento -->

<title>Practica TIW: Listado Cursos</title>
<meta charset="UTF-8">
<meta name="keywords" content="e-learning, cursos">
<meta name="description" content="Web de cursos en linea">
<meta name="author" content="Jorge Garcia de la Rosa">
<link href="<c:url value="/style/empresa-mis-ofertas.css" />"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />"
	rel='stylesheet' type='text/css'>
<link
	href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />"
	rel="stylesheet">
<script
	src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
<script
	src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
<link rel="stylesheet" type="text/css" href="style/busqueda.css">
<style type="text/css">
.error {
	color: red;
}
</style>
</head>

<!-- ******************** TO-DO ******************** -->
<!-- RESCATAR ID DEL PROFESOR CORRESPONDIENTE Y PASARLO EN EL POST A AltaCursosServlet y BajaCursosServlet -->
<!-- *********************************************** -->

<body>


	<jsp:include page="cabecera.jsp" flush="true" />

	<!--CUERPO DE LA PAGINA-->

	<section>

		<div id="mi-empresa">



			<h4>CURSOS DISPONIBLES</h4>

			<div id="ofertas">
				<c:if test="${empty cursos }">
					<!-- cursos es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
					<p class="error">Actualmente no hay cursos disponibles.</p>
				</c:if>

				<ul>
					<c:forEach items="${cursos }" var="curso">
						<!-- recorremos todos los objetos de la coleccion cursos 
							y cada objeto devuelto lo asignamos a la variable curso -->

						<li id="oferta-ejemplo${curso.ID_curso}">
							<div class="ofertas-descripcion">
								<c:choose>
									<c:when test="${curso.TIPO_estado == 2 }">
										<p class="ofertas-titulo">${curso.DES_titulo }</p>
										<p class="ofertas-empresa">Impartido por:
											${curso.profesor.nombre }</p>
										<p class="ofertas-resumen">${curso.DES_descripcion }</p>
										<p class="ofertas-tipo-contrato">${curso.horas }hrs.</p>
										<p class="ofertas-jornada">Precio inicial:
											${curso.precio_inicial } €.</p>
										<p class="ofertas-jornada">Precio final:
											${curso.precio_final } €.</p>
										<c:choose>
											<c:when test="${empty curso.fechaFinDescuento }">
											</c:when>
											<c:otherwise>
												<p class="ofertas-jornada">fin descuento:
													${curso.fechaFinDescuento }</p>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when test="${curso.TIPO_dificultad == 0 }">
												<p class="ofertas-salario">Basico.</p>
											</c:when>
											<c:when test="${curso.TIPO_dificultad == 1 }">
												<p class="ofertas-salario">Intermedio.</p>
											</c:when>
											<c:otherwise>
												<p class="ofertas-salario">Avanzado.</p>
											</c:otherwise>

										</c:choose>

										<c:choose>
											<c:when test="${empty sessionScope.listadeseos }">

												<div class="ofertas-seguidores">
													<img src="images/deseado.png" alt="Error en la imagen">
													<p class="numero-seguidores">
														<a
															href="GestionDeseados?IdCurso=${curso.ID_curso }&Pagina=ListadoCursos&Tipo=Alta">Añadir
															curso a deseados.</a>
													</p>
												</div>

											</c:when>
											<c:otherwise>

												<c:set var="encontrado" value="0" />

												<c:forEach items="${sessionScope.listadeseos }" var="deseo">

													<c:choose>
														<c:when
															test="${curso.ID_curso == deseo.cursoDeseado.ID_curso}">
										
														   ${encontrado = 1}
														 </c:when>
														 <c:otherwise>
														 </c:otherwise>
													</c:choose>

												</c:forEach>

												<c:choose>
													<c:when test="${encontrado == 1}">
														


													</c:when>
													<c:otherwise>
														<div class="ofertas-seguidores">
															<img src="images/deseado.png" alt="Error en la imagen">
															<p class="numero-seguidores">
																<a
																	href="GestionDeseados?IdCurso=${curso.ID_curso }&Pagina=ListadoCursos&Tipo=Alta">Añadir
																	curso a deseados.</a>
															</p>
														
														</div>
													</c:otherwise>
													</c:choose>
											</c:otherwise>

										</c:choose>


										<div class="ofertas-seguidores">
											<img src="images/edicion/seguidores-icon.png"
												alt="Error en la imagen">
											<p class="numero-seguidores">
												<a href="contenidoCursos?nombreCurso=${curso.DES_titulo }&idCurso=${curso.ID_curso}">Ver
													Contenidos</a>
											</p>
										</div>

									</c:when>

									<c:otherwise>
										<p class="error">El curso ${curso.DES_titulo } esta
											pendiente de validacion</p>
									</c:otherwise>

								</c:choose>
							</div>





						</li>

					</c:forEach>
				</ul>



				<c:if test="${ mensaje != null }">
					<p class="error">${mensaje }</p>
				</c:if>


			</div>

		</div>

		<div id="seguidores">

			<h5>Destacados</h5>
			<ul>
				<c:forEach items="${cursos }" var="curso">
					<li><c:choose>

							<c:when test="${curso.TIPO_destacado == 1 }">
								<img class="seguidores-foto" src="images/famosos/gates.jpg"
									alt="Error en la imagen">
								<p class="seguidores-info">
									<a style="color: black"
										href="contenidoCursos?nombreCurso=${curso.DES_titulo }">${curso.DES_titulo }</a>
								</p>
							</c:when>

						</c:choose></li>
				</c:forEach>
			</ul>
		</div>
		<div id="seguidores">

			<h5>Recomendados</h5>
			<ul>
				<c:forEach items="${cursosRecomendados }" var="curso">
					<!-- recorremos todos los objetos de la coleccion cursos 
							y cada objeto devuelto lo asignamos a la variable curso -->
					<li><img class="seguidores-foto"
						src="images/famosos/gates.jpg" alt="Error en la imagen">
						<p class="seguidores-info">
							<a style="color: black"
								href="contenidoCursos?nombreCurso=${curso.DES_titulo }">${curso.DES_titulo }</a>
						</p></li>
					</li>
				</c:forEach>
			</ul>
		</div>


	</section>


	<!--PIE DE PAGINA-->

	<footer>
		<ul id="pie">
			<li>Copyright © jooglecam.com</li>
			<li><a href="#">Aviso legal </a></li>
			<li><a href="#">Privacidad </a></li>
			<li><a href="#">Política de cookies </a></li>
			<li><a href="#">Accesibilidad </a></li>
			<li><a href="#">Contacto </a></li>
			<li><a href="#">Ayuda </a></li>
		</ul>
	</footer>

	<script src="<c:url value="script/empresa-mis-ofertas.js" />"
		type="text/javascript"></script>


</body>

</html>

