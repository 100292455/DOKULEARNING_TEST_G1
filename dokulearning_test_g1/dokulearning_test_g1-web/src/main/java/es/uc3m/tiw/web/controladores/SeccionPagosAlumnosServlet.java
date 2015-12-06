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
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Seccion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.SeccionDAO;
import es.uc3m.tiw.model.dao.SeccionDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;

@WebServlet("/SeccionPagosAlumnosServlet")
public class SeccionPagosAlumnosServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/seccionPagoAlumnos.jsp";
	private static final String GESTION_CURSOS_JSP = "/seccionPagoAlumnos.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private MatriculaDAO matDao;
	ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);

	}
	
	public void destroy() {
		curDao = null;
		matDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		//this.getServletContext().getRequestDispatcher(GESTION_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession();
	
		
		String id_curso= request.getParameter("idcurso"); 
		System.out.println("-------------------------idcurso:"+id_curso);
		int idcurso = Integer.parseInt(id_curso);
	    Curso curso=curDao.recuperarCursoPorPK(idcurso);
	    String nombrecurso= curso.getDES_titulo();
	    sesion.setAttribute("nombrecurso",nombrecurso);
	    
		Collection <Matricula> MatriculadosCurso = matDao.recuperarMatriculaPorCurso(idcurso);
		System.out.println("-------------------------Matriculados:"+MatriculadosCurso);
		sesion.setAttribute("matriculadoscurso",MatriculadosCurso);
		String pagina = GESTION_CURSOS_JSP;

		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	    }
		
	}

