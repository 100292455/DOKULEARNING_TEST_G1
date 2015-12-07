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
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Seccion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.SeccionDAO;
import es.uc3m.tiw.model.dao.SeccionDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;


@WebServlet("/AltaSeccion")
public class AltaSeccionServlet extends HttpServlet {
	//private static final String ENTRADA_JSP = "/gestionSecciones.jsp";
	private static final String CONTENIDO_CURSO_JSP = "/contenidoCurso.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private SeccionDAO secDao;
	ServletContext context;
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
		config2.getServletContext().getRequestDispatcher(CONTENIDO_CURSO_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession sesion = request.getSession();
		Curso cursoactual=(Curso)sesion.getAttribute("curso_actual");
		String nombreSeccion = request.getParameter("nombreSeccion");
		int id_curso= (int) sesion.getAttribute("idCurso");
		String mensaje ="";
		String pagina = CONTENIDO_CURSO_JSP;
		
		String m = comprobarCurso(nombreSeccion);
		if (m.equals(null) || m.equals("")){
			Curso c = curDao.recuperarCursoPorPK(id_curso);
			Seccion s = crearSeccion(nombreSeccion,c);
			try {
				s=secDao.guardarSeccion(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Collection<Seccion> listaSeccionesCursoActual = secDao.recuperarSeccionesPorCurso(id_curso);
			c.setSecciones(listaSeccionesCursoActual);
			try {
				curDao.modificarCurso(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sesion.setAttribute("secciones", listaSeccionesCursoActual);
			mensaje = m;
			sesion.setAttribute("mensajeSecciones", mensaje);
			
		}else{
			
			mensaje = m;
			sesion.setAttribute("mensajeSecciones", mensaje);
		}
			
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

	private Seccion crearSeccion(String nombreSeccion, Curso curso) {
		Seccion c = new Seccion();
		c.setNombre(nombreSeccion);
		c.setCurso(curso);
		
		return c;
	}



	private String comprobarCurso(String nombreCurso) {
		String m = "";
		
		if (nombreCurso.equals("") || nombreCurso.equals(null)) {
			m ="Fallo al crear nueva seccion. ";
		}
		
		return m;
	}

}