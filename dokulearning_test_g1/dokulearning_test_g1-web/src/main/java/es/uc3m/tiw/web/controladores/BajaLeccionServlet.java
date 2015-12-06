package es.uc3m.tiw.web.controladores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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


@WebServlet("/BajaLeccionServlet")
public class BajaLeccionServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/contenidoCurso.jsp";
	private static final String GESTION_CURSOS_JSP = "/contenidoCurso.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private SeccionDAO secDao;
	private LeccionDAO lecDao;
	ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		lecDao = new LeccionDAOImpl(em, ut);
		secDao = new SeccionDAOImpl(em, ut);

	}
	
	public void destroy() {
		curDao = null;
		lecDao = null;
		secDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		String idleccionStr = request.getParameter("IdLeccion");
		int idleccion = Integer.parseInt(idleccionStr);
		
		Leccion l = lecDao.recuperarLeccionPorPK(idleccion);
		Seccion s = l.getSeccion();
		Collection<Leccion> leccionesActualizadas=s.getLecciones();
		
		for (Leccion leccion : leccionesActualizadas) {
			if(l.getID_leccion()==leccion.getID_leccion()){
				leccionesActualizadas.remove(leccion);
				break;
			}
		}
		s.setLecciones(leccionesActualizadas);
		
		try {
			s=secDao.modificarSeccion(s);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			lecDao.borrarLeccion(l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collection<Leccion> listaLeccionesActualizada = lecDao.recuperarLeccionesPorSeccion(l.getSeccion().getId_seccion());
		pagina = ENTRADA_JSP;
		sesion.setAttribute("lecciones", listaLeccionesActualizada);
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	}

}