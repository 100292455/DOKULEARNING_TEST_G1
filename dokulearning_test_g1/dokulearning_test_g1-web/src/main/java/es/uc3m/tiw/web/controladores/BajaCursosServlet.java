package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

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

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.Deseo;
import es.uc3m.tiw.model.dao.DeseoDAO;
import es.uc3m.tiw.model.dao.DeseoDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;

@WebServlet("/BajaCursos")
public class BajaCursosServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/misCursos.jsp";
	private static final String GESTION_CURSOS_JSP = "/misCursos.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private PromocionDAO promDao;
	private CuponDAO cupDao;
	private CursoDAO curDao;
	private MatriculaDAO matDao;
	private DeseoDAO desDao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		cupDao = new CuponDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);
		desDao = new DeseoDAOImpl(em, ut);

	}
	
	public void destroy() {
		cupDao = null;
		curDao = null;
		promDao = null;
		matDao = null;
		desDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		config2.getServletContext().getRequestDispatcher(GESTION_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* Cuando el profesor clique en BORRAR CURSO, eliminamos el curso del arrayList 'cursos'. Habria que eliminarlo de la BBDD */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
		
	      //no se pierda la pestaña
			request.setAttribute("selectedTab", "1");
		
		HttpSession sesion = request.getSession();	
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		String idCursoStr = request.getParameter("IdCurso");
		int idCurso = Integer.parseInt(idCursoStr);
		Curso curso=curDao.recuperarCursoPorPK(idCurso);
		
		/*Borramos curso de deseados si existe*/
		Collection<Deseo> deseoEliminar=null;
		try {
			deseoEliminar=desDao.recuperarDeseoporCurso(idCurso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deseoEliminar.isEmpty()==false) {
			for (Deseo deseo : deseoEliminar) {
				try {
					desDao.borrarDeseo(deseo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("---------------------------------");
		pagina = ENTRADA_JSP;
		
		
		
		//Borramos el curso
		try {
			curDao.borrarCurso(curso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*ACTUALIZAMOS LAS LISTAS DE CURSOS DE LA SESION*/
		Collection<Curso> listadoCursos = curDao.buscarTodosLosCursos();
		sesion.setAttribute("cursos", listadoCursos);
		
		Collection<Curso> cursosCreados = curDao.recuperarCursosPorProfesor(user.getID_usuario());
		sesion.setAttribute("cursoscreados", cursosCreados);
		
		Collection<Matricula> listadoMatricula = matDao.recuperarMatriculaPorAlumno(user.getID_usuario());
		sesion.setAttribute("matriculas", listadoMatricula);
		
		Collection<Deseo> listaCursosDeseados = desDao.recuperarCursosDeseadosPorUsuario(user.getID_usuario());
		sesion.setAttribute("listadeseos", listaCursosDeseados);
		
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	}

}