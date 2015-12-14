package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Leccion;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Seccion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.SeccionDAOImpl;
import es.uc3m.tiw.model.dao.SeccionDAO;
import es.uc3m.tiw.model.dao.LeccionDAOImpl;
import es.uc3m.tiw.model.dao.LeccionDAO;

@WebServlet("/contenidoCursos")
public class ContenidoCursosServlet extends HttpServlet {
	private static final String ENTRADACONTENIDOCURSO_JSP = "/contenidoCurso.jsp";
	private static final String ENTRADA_CONTENIDO_CURSO_ALUMNO_JSP = "/contenidoCursoAlumno.jsp";
	private static final String AVISOMATRICULA_JSP = "/avisoMatricula.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private MatriculaDAO matDao;
	private CursoDAO curDao;
	private CuponDAO cupDao;
	private SeccionDAO secDao;
	private LeccionDAO lecDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		matDao = new MatriculaDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		cupDao = new CuponDAOImpl(em, ut);
		secDao = new SeccionDAOImpl(em, ut);
		lecDao = new LeccionDAOImpl(em, ut);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		matDao = null;
		curDao = null;
		cupDao = null;
		secDao = null;
		lecDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		ServletContext context = sesion.getServletContext();
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		String nombreCurso = request.getParameter("nombreCurso");//Coger nombre del curso a matricular
		Curso contenidoCurso = null;
		try {
			contenidoCurso = curDao.recuperarCursoPorNombre(nombreCurso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user.getID_usuario()==contenidoCurso.getProfesor().getID_usuario()){
			sesion.setAttribute("nombreCurso", nombreCurso);
			sesion.setAttribute("idCurso", contenidoCurso.getID_curso());
			//Cursos relacionados con el curso al que se quiere acceder
			Collection<Curso> cursosRecomendados = null;
			try {
				cursosRecomendados = curDao.recuperarCursosPorTematica(contenidoCurso.getTematica());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sesion.setAttribute("cursosRecomendados", cursosRecomendados);
			//metemos la tabla de cupones en el contexto para poder utilizarla desde otras paginas
			Collection<Cupon> listadoCupones = cupDao.recuperarCuponPorCurso(contenidoCurso);
			sesion.setAttribute("cupones", listadoCupones);
			
			Collection<Seccion> listaSeccionesCursoActual = secDao.recuperarSeccionesPorCurso(contenidoCurso.getID_curso());
			sesion.setAttribute("secciones", listaSeccionesCursoActual);
			
			Collection<Leccion> listadoLeccionesIniciales = lecDao.buscarTodosLosLecciones();
			sesion.setAttribute("lecciones", listadoLeccionesIniciales);
			config2.getServletContext().getRequestDispatcher(ENTRADACONTENIDOCURSO_JSP).forward(request, response);
		}
		
		//Comprobar si el usuario ya esta matriculado en el curso
		Collection<Matricula> MatriculasUsuarioActual = null;
		try {
			MatriculasUsuarioActual = matDao.recuperarMatriculaPorAlumnoYCurso(user.getID_usuario(), contenidoCurso.getID_curso());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Cursos relacionados con el curso al que se quiere acceder
		Collection<Curso> cursosRecomendados = null;
		try {
			cursosRecomendados = curDao.recuperarCursosPorTematica(contenidoCurso.getTematica());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sesion.setAttribute("cursosRecomendados", cursosRecomendados);
		//metemos la tabla de cupones en el contexto para poder utilizarla desde otras paginas
		Collection<Cupon> listadoCupones = cupDao.recuperarCuponPorCurso(contenidoCurso);
		sesion.setAttribute("cupones", listadoCupones);
		
		Collection<Seccion> listaSeccionesCursoActual = secDao.recuperarSeccionesPorCurso(contenidoCurso.getID_curso());
		sesion.setAttribute("secciones", listaSeccionesCursoActual);
		
		Collection<Leccion> listadoLeccionesIniciales = lecDao.buscarTodosLosLecciones();
		sesion.setAttribute("lecciones", listadoLeccionesIniciales);
		//Si el usuario esta matriculado en este curso, el servlet le manda a ver el contenido del curso
		if (MatriculasUsuarioActual.isEmpty()){
			//Si el usuario no matriculado en este curso le manda a matricularse
			sesion.setAttribute("nombreCurso", nombreCurso);
			config2.getServletContext().getRequestDispatcher(AVISOMATRICULA_JSP).forward(request, response);
		}
		
		else{//Meto el titulo del curso en el contexto para que el servlet AñadirMatriculaServlet pueda leerlo
			sesion.setAttribute("idCurso", contenidoCurso.getID_curso());
			sesion.setAttribute("nombreCurso", nombreCurso);
			if(user.getID_usuario()!=contenidoCurso.getProfesor().getID_usuario()){
				config2.getServletContext().getRequestDispatcher(ENTRADA_CONTENIDO_CURSO_ALUMNO_JSP).forward(request, response);
			}
			config2.getServletContext().getRequestDispatcher(ENTRADACONTENIDOCURSO_JSP).forward(request, response);

		}		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
