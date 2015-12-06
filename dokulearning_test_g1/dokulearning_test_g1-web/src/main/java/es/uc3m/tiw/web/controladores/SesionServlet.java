package es.uc3m.tiw.web.controladores;

import java.io.IOException;
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

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Deseo;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.DeseoDAO;
import es.uc3m.tiw.model.dao.DeseoDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.UsuarioDAO;
import es.uc3m.tiw.model.dao.UsuarioDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;


/**
 * Servlet de ejemplo que muestra distintos aspectos dentro de los ambitos request y session. 
 * -Como usar el metodo init para inicializar datos
 * -Como recoger datos desde un formulario por post
 * -Como no permitir acceso por get redirigiendo a la pagina de login.jsp
 * -Uso del objeto RequestDispatcher y forward
 * -Introduccion de atributos en el objeto request
 * -Uso de objeto sesion para mantener al usuario autenticado
 * -Control de flujo y logica de negicio de un controlador.
 * 
 */
@WebServlet("/sesion")
public class SesionServlet extends HttpServlet {
	private static final String ENTRADA_ALUMNO_JSP = "/miPerfilAlumno.jsp";
	private static final String LOGIN_JSP = "/login.jsp";
	private static final String INDEX_JSP = "/index.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private UsuarioDAO usDao;
	private CursoDAO curDao;
	private MatriculaDAO matDao;
	private DeseoDAO desDao;
	//private Usuario usuario;
	//private Usuario usuario_2;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		usDao = new UsuarioDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);
		desDao = new DeseoDAOImpl(em, ut);
		/*Usuario usuario = new Usuario("Alejandro", "Ruiz", 1, "alex@uc3m.es","636780470","VISA",null, null, 0, "1234", null, null);
		Usuario usuario_2 = new Usuario("Miguel", "Solera", 1, "miguel@uc3m.es","636780470","VISA",null, null, 1, "1234", null, null);
		try {
			usuario=usDao.guardarUsuario(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			usuario_2=usDao.guardarUsuario(usuario_2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void destroy() {
		usDao = null;
		curDao = null;
		matDao=null;
		desDao=null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("accion");
		if (salir != null && !salir.equals("")) {
			request.getSession().invalidate();
		}
		config2.getServletContext().getRequestDispatcher(INDEX_JSP).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("usuario");
		String password = request.getParameter("password");
		String mensaje ="";
		String pagina = "";
		pagina = LOGIN_JSP;
		HttpSession sesion = request.getSession(true);
		ServletContext context = sesion.getServletContext();
		Usuario u = null;
		try {
			u=usDao.buscarPorEmailYpassword(user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (u != null){
			pagina = ENTRADA_ALUMNO_JSP;
			
			Collection<Usuario> usuarios = usDao.buscarTodosLosUsuarios();	
			Collection<Deseo> deseos = desDao.recuperarCursosDeseadosPorUsuario(u.getID_usuario());
			sesion.setAttribute("listadeseos", deseos);
			sesion.setAttribute("usuarios", usuarios);
			sesion.setAttribute("usuario", u);
			sesion.setAttribute("acceso", "ok");
			Collection<Curso> cursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", cursos);
			Collection<Curso> cursosCreados = curDao.recuperarCursosPorProfesor(u.getID_usuario());
			sesion.setAttribute("cursoscreados", cursosCreados);
			Collection<Matricula> listadoMatricula = matDao.recuperarMatriculaPorAlumno(u.getID_usuario());
			sesion.setAttribute("matriculas", listadoMatricula);
			//context.setAttribute("matriculas", matriculas);
			//context.setAttribute("secciones", secciones);
			//context.setAttribute("lecciones", lecciones);

			
		}else{
			
			mensaje = "No existen usuarios registrados con esos datos.";
			request.setAttribute("mensaje", mensaje);
		}
		Collection<Curso> listaCursos = curDao.buscarTodosLosCursos();
		sesion.setAttribute("cursos", listaCursos);
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

}