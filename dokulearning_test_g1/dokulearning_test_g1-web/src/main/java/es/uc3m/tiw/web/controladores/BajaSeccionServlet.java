package es.uc3m.tiw.web.controladores;

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
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Leccion;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Seccion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.SeccionDAO;
import es.uc3m.tiw.model.dao.SeccionDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;

@WebServlet("/BajaSeccionServlet")
public class BajaSeccionServlet extends HttpServlet {
	//private static final String ENTRADA_JSP = "/gestionSecciones.jsp";
	private static final String GESTION_CURSOS_JSP = "/contenidoCurso.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private SeccionDAO secDao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		secDao = new SeccionDAOImpl(em, ut);

	}
	
	public void destroy() {
		curDao = null;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		String idSeccionStr = request.getParameter("IdSeccion");
		int idSeccion = Integer.parseInt(idSeccionStr);
		
		Seccion s = secDao.recuperarSeccionPorPK(idSeccion);
		Curso c = s.getCurso();
		Collection<Seccion> seccionesActualizadas=c.getSecciones();
		for (Seccion seccion : seccionesActualizadas) {
			if(s.getId_seccion()==seccion.getId_seccion()){
				seccionesActualizadas.remove(seccion);
				break;
			}
		}
		c.setSecciones(seccionesActualizadas);
		
		try {
			c=curDao.modificarCurso(c);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			secDao.borrarSeccion(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			secDao.borrarSeccion(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<Seccion> listadoSeccionesActualizada=secDao.recuperarSeccionesPorCurso(s.getCurso().getID_curso());
		sesion.setAttribute("secciones", listadoSeccionesActualizada);
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	}

}