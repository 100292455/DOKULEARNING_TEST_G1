<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="WebService" method="get" >
			<ul>
				<li class="buscador_elemento">
					<h2 class="label">
						Tarjeta: <input type="text" name="tarjeta"
							id="filtroPuesto" size="20" />
					</h2>
				</li>
				<li class="buscador_elemento">
					<h2 class="label">
						Precio: <input type="text" name="precio"
							id="filtroPuesto" size="20" />
					</h2>
				</li>
				<li class="buscador_elemento">
					<h2 class="label">
						Curso: <input type="text" name="curso"
							id="filtroPuesto" size="20" />
					</h2>
				</li>
				<li class="buscador_elemento"><input type="submit"
					id="buscador_boton" name="filtro" value="Facturar" /></li>
			</ul>
		</form>
<p>${mensaje}</p>
</body>
</html>