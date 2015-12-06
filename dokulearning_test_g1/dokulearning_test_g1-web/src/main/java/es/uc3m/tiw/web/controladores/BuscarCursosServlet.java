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
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;

@WebServlet("/BuscarCursos")
public class BuscarCursosServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/Buscador.jsp";
	private static final String BUSCAR_CURSOS_JSP = "/Buscador.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	ServletContext context;
	@Override
	/**
	* @see Servlet#init(ServletConfig)
	*/
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
	}
       
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	curDao = null;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(BUSCAR_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tematica = request.getParameter("filtroTematica");
		String precioStr = request.getParameter("filtroPrecio");
		String dificultadStr = request.getParameter("filtroDificultad");
		
		int dificultad = Integer.parseInt(dificultadStr);
		int precio = Integer.parseInt(precioStr);
		

		Collection <Curso> cursosRet = null;
		
		String pagina = "";
		pagina = ENTRADA_JSP;

		if (!tematica.equals("") && precio==0 && dificultad==-1){  
			
			try {
				cursosRet = curDao.recuperarCursosPorTematica(tematica);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
			
		}if (tematica.equals("") && precio!=0 && dificultad==-1){
			
			try {
				cursosRet = curDao.recuperarCursosPorPrecio(precio);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}if (tematica.equals("") && precio==0 && dificultad!=-1){
			
			try {
				cursosRet = curDao.recuperarCursosPorDificultad(dificultad);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}if (!tematica.equals("") && precio!=0 && dificultad==-1){
			
			try {
				cursosRet = curDao.recuperarCursosPorPrecioYTematica(precio, tematica);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!tematica.equals("") && precio==0 && dificultad!=-1){
			
			try {
				cursosRet = curDao.recuperarCursosPorDificultadYTematica(dificultad, tematica);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (tematica.equals("") && precio!=0 && dificultad!=-1){
			
			try {
				cursosRet = curDao.recuperarCursosPorPrecioYDificultad(precio, dificultad);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!tematica.equals("") && precio!=0 && dificultad!=-1){
			
			try {
				cursosRet = curDao.recuperarCursosPorPrecioYDificultadYTematica(dificultad, tematica, precio);
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (tematica.equals("") && precio==0 && dificultad==-1){
			
			try {
				cursosRet = curDao.buscarTodosLosCursos();
				pagina = BUSCAR_CURSOS_JSP;
				request.setAttribute("cursos", cursosRet);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

}