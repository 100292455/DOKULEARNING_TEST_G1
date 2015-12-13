package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Mensaje;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.MensajeDAOImpl;
import es.uc3m.tiw.web.jms.EscribirEnQueue;
/**
 * Servlet implementation class MensajesServlet
 */
@WebServlet("/mensajes")
public class MensajesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private MensajeDAOImpl msgDao;
	private MatriculaDAO matDao;
	private CursoDAO curDao;
	@Inject
	private EscribirEnQueue colaMensajes;
       

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		msgDao = new MensajeDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em,ut);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		/*CARGAR LOS MENSAJES*/
		
		HttpSession sesion = request.getSession();
		
		/*Recuperamos de la sesion el curso del foro*/
		int id_curso = (int) sesion.getAttribute("idCurso");
		Curso c = null;
		try {
			c = curDao.recuperarCursoPorPK(id_curso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Recuperamos de la BBDD los matriculados y los mensajes del curso*/
		Collection<Matricula> matriculas = null;
		Collection<Mensaje> mensajes = null;
		try {
			mensajes = msgDao.recuperarMensajePorCurso(c.getID_curso());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			matriculas = matDao.recuperarMatriculaPorCurso(c.getID_curso());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Enviamos a la vista los mensajes y los matriculados*/
		request.setAttribute("listaMensajes", mensajes);
		request.setAttribute("matriculascursoactual", matriculas);
		request.setAttribute("curso", c);
		
		/*Dispahcher*/
		this.getServletContext().getRequestDispatcher("/Foro.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		/*Recuperamos el usuario y el curso de sesion*/
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		/*Recuperamos de la sesion el curso del foro*/
		int id_curso = (int) sesion.getAttribute("idCurso");
		Curso c = null;
		try {
			c = curDao.recuperarCursoPorPK(id_curso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Recuperamos el mensaje*/
		String mensaje = request.getParameter("mensaje");

		/*Creamos un mensaje con el contenido, el emisor y el curso del foro y lo metemos en la cola*/
		Mensaje msg = new Mensaje(mensaje,u, c);

		colaMensajes.enviar(msg);
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/Foro.jsp").forward(request, response);
		
		
		
	
	}

}
