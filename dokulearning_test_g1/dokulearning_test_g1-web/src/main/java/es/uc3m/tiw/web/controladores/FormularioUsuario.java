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

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Deseo;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.DeseoDAO;
import es.uc3m.tiw.model.dao.DeseoDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.UsuarioDAO;
import es.uc3m.tiw.model.dao.UsuarioDAOImpl;



/**
 * Servlet implementation class FormularioUsuario
 */
@WebServlet("/FormularioUsuario")
public class FormularioUsuario extends HttpServlet {
	private static final String ENTRADA_ALUMNO_JSP = "/miPerfilAlumno.jsp";
	private static final String FORMULARIO_USUARIO_JSP = "/formulario-usuario.jsp";
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
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		usDao = new UsuarioDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);
		desDao = new DeseoDAOImpl(em, ut);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		usDao = null;
		curDao = null;
		matDao=null;
		desDao=null;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellidos");
		String sexo=request.getParameter("sex");
		String email=request.getParameter("correo");
		String tel=request.getParameter("telefono");
		String cobro=request.getParameter("cobro");	
		//String descripcion=request.getParameter("descripcion");
		String password1=request.getParameter("password1");
		
		/*if (nombre.equals("") || nombre == null){
			request.getRequestDispatcher("formulario-usuario.jsp");
		}
		
		if (apellido.equals("") || apellido == null){
			request.getRequestDispatcher("formulario-usuario.jsp");
		}
		
		if (email.equals("") || email == null){
			request.getRequestDispatcher("formulario-usuario.jsp");
		}
		
		if (tel.equals("") || tel == null){
			request.getRequestDispatcher("formulario-usuario.jsp");
		}
		
	
		
		if (password1.equals("") || password1 == null){
			request.getRequestDispatcher("formulario-usuario.jsp");
		}
		
		if (password2.equals("") || password2 == null){
			request.getRequestDispatcher("formulario-usuario.jsp");
		}
		*/
		HttpSession sesion = request.getSession(); 
		
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setEmail(email);
		usuario.setTelefono(tel);
		usuario.setClave(password1);
		
		
		if(sexo.equals("hombre")){
			
			usuario.setSexo(1);
		}
		if(sexo.equals("mujer")){
			
			usuario.setSexo(0);
		}	
		
		if(cobro.equals("otros")){
			usuario.setTipo_usuario(0);
		}else{
			usuario.setTipo_usuario(1);
		}
	
		Usuario u = null;
		try {
			u=usDao.buscarPorEmail(usuario.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (u == null){
			try {
				usuario=usDao.guardarUsuario(usuario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sesion.setAttribute("usuario", usuario);
			Collection<Usuario> usuarios = usDao.buscarTodosLosUsuarios();
			sesion.setAttribute("usuarios", usuarios);
			sesion.setAttribute("acceso", "ok");
			Collection<Curso> cursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", cursos);
			config2.getServletContext().getRequestDispatcher(ENTRADA_ALUMNO_JSP).forward(request, response);
			
		}else{
			
			String mensaje = "Ya existe un usuario con ese email. Por favor, elija otro";
			sesion.setAttribute("mensaje", mensaje);
			sesion.setAttribute("usuario", usuario);
			config2.getServletContext().getRequestDispatcher(FORMULARIO_USUARIO_JSP).forward(request, response);
		}
		
		
		
	    

			
		
	
		
	}

}
