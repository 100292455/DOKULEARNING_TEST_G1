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
import es.uc3m.tiw.model.Deseo;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.DeseoDAO;
import es.uc3m.tiw.model.dao.DeseoDAOImpl;
import es.uc3m.tiw.model.dao.UsuarioDAO;
import es.uc3m.tiw.model.dao.UsuarioDAOImpl;


@WebServlet("/GestionDeseados")
public class GestionDeseadosServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/Buscador.jsp";
	private static final String BUSCADOR_JSP = "/Buscador.jsp";
	private static final String LISTADO_CURSOS_JSP = "/listadoCursos.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private DeseoDAO desDao;
	private UsuarioDAO usDao;
	ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		desDao = new DeseoDAOImpl(em, ut);
		usDao = new UsuarioDAOImpl(em, ut);
	}
	
	public void destroy() {
		curDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("selectedTab", "2");
		String idCursoStr = request.getParameter("IdCurso");
		String pagina_entrada = request.getParameter("Pagina");
		String tipo = request.getParameter("Tipo");
		String pagina_salida = "";
		
		if(pagina_entrada.equals("Buscador")){
			pagina_salida = BUSCADOR_JSP;
		}
		if(pagina_entrada.equals("ListadoCursos")){
			pagina_salida = LISTADO_CURSOS_JSP;
		}
		
		if(pagina_entrada.equals("misCursos")){
			pagina_salida = "/misCursos.jsp";
		}
		HttpSession sesion = request.getSession();
		
		if(tipo.equals("Alta")){
		
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		
		int idCurso = Integer.parseInt(idCursoStr);
		
		Curso deseado = curDao.recuperarCursoPorPK(idCurso);
		
		/*deseado.setTIPO_destacado(1);
		try {
			curDao.modificarCurso(deseado);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		Deseo deseo = new Deseo(user, deseado);
		
		try {
			desDao.guardarDeseo(deseo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection <Curso> cursosactualizados=curDao.buscarTodosLosCursos();
		Collection<Deseo> listaCursosDeseados = desDao.recuperarCursosDeseadosPorUsuario(user.getID_usuario());
		sesion.setAttribute("cursos",cursosactualizados);
		sesion.setAttribute("listadeseos", listaCursosDeseados);
		
		}
		
		if(tipo.equals("Baja")){
			
			Usuario user = (Usuario) sesion.getAttribute("usuario");
			
			int idCurso = Integer.parseInt(idCursoStr);
			
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
			
			
		    
		    
		    Curso deseado = curDao.recuperarCursoPorPK(idCurso);
			deseado.setTIPO_destacado(0);
			try {
				curDao.modificarCurso(deseado);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Collection <Curso> cursosactualizados=curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos",cursosactualizados);
		    Collection<Deseo> listaCursosDeseados = desDao.recuperarCursosDeseadosPorUsuario(user.getID_usuario());
			sesion.setAttribute("listadeseos", listaCursosDeseados);
		}
		config2.getServletContext().getRequestDispatcher(pagina_salida).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		config2.getServletContext().getRequestDispatcher(ENTRADA_JSP).forward(request, response);	
		
	}
}
