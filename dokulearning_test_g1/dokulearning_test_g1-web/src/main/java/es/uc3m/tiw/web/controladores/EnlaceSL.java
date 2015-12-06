package es.uc3m.tiw.web.controladores;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Leccion;
import es.uc3m.tiw.model.Seccion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.LeccionDAO;
import es.uc3m.tiw.model.dao.LeccionDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.SeccionDAO;
import es.uc3m.tiw.model.dao.SeccionDAOImpl;
@WebServlet("/EnlaceSL")
public class EnlaceSL extends HttpServlet {
	private static final String ENTRADA_JSP = "/formularioLecciones.jsp";
	private static final String GESTION_CURSOS_JSP = "/formularioLecciones.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private LeccionDAO lecDao;
	private SeccionDAO secDao;
	ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		lecDao = new LeccionDAOImpl(em, ut);
		secDao = new SeccionDAOImpl(em, ut);

	}
	
	public void destroy() {
		lecDao = null;
		secDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id_seccion= request.getParameter("IdSeccion"); //cuidado con esto
		System.out.println("-------------------------------------------------"+id_seccion);
		int idseccion = Integer.parseInt(id_seccion);
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
	
		Seccion seccionActual = secDao.recuperarSeccionPorPK(idseccion);
		HttpSession sesion = request.getSession();
	    sesion.setAttribute("seccion_actual",seccionActual);
	    sesion.setAttribute("idseccion", idseccion);

		
		pagina = ENTRADA_JSP;
		
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	    }
		
	}

