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
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;

/**
 * Servlet implementation class AñadirCurso
 */
@WebServlet("/AnadirMatricula")
public class AnadirMatriculaServlet extends HttpServlet {
	private static final String MISCURSOS_JSP = "/misCursos.jsp";
	private ArrayList<Matricula> matriculas;//tabla matriculas
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private MatriculaDAO matDao;
	private CursoDAO curDao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		matDao = new MatriculaDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		matDao = null;
		curDao = null;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnadirMatriculaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
	public void init() throws ServletException {
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		ServletContext context = sesion.getServletContext();
		
		//Coger el titulo del curso y coger su ID
		String nombreCurso = (String) sesion.getAttribute("nombreCurso");
		Curso c = null;
		try {
			c=curDao.recuperarCursoPorNombre(nombreCurso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Coger el precio final del curso de la BBDD
		int precio_pagado = c.getPrecio_final();
		
		//Coger el ID del usuario
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		
		//Crear la nueva matricula y metela en la BBDD de matriculas
		Matricula matriculaNueva = new Matricula(user, c, precio_pagado);
		try {
			matriculaNueva=matDao.guardarMatricula(matriculaNueva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<Matricula> listadoMatricula = matDao.recuperarMatriculaPorAlumno(user.getID_usuario());
		c.setMatriculas(listadoMatricula);
		try {
			curDao.modificarCurso(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sesion.setAttribute("matriculas", listadoMatricula);
		config2.getServletContext().getRequestDispatcher(MISCURSOS_JSP).forward(request, response);

		// TODO Auto-generated method stub
	
		
	}
	

	
}
