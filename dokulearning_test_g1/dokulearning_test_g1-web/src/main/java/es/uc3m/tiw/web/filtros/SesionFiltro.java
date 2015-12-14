package es.uc3m.tiw.web.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SesionFiltro
 */
@WebFilter( urlPatterns = {"/misCursos.jsp","/listadoCursos.jsp","/GestionCupones.jsp","/formularioPago.jsp", "/contenidoCurso.jsp", "/miPerfilAlumno.jsp", "/miPerfilProfesor.jsp", "/avisoMatricula.jsp", "/GestionCursos.jsp", "/Foro.jsp" })
public class SesionFiltro implements Filter {


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession sesion =  ((HttpServletRequest)request).getSession(); //accedemos a la sesion del servidor usando un casting al objeto HttpServletRequest ya que nos pasan solamente ServletRequest
		if (sesion.getAttribute("acceso")!=null && sesion.getAttribute("acceso").equals("ok")) { //buscamos el token de autenticacion
			
			chain.doFilter(request, response); 
		}else{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}