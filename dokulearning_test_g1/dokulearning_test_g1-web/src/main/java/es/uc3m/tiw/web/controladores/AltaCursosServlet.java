package es.uc3m.tiw.web.controladores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.Part;
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
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;


@WebServlet("/AltaCursos")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50,
location = "/")   // 50MB
public class AltaCursosServlet extends HttpServlet {
	private static final String MIS_CURSOS_JSP = "/misCursos.jsp";
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "cursoImages";
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);

	}
	
	public void destroy() {
		curDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(MIS_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = MIS_CURSOS_JSP;
		String mensaje ="";
		// gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
		
      //no se pierda la pestaña
		request.setAttribute("selectedTab", "1");
		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion");
		String dificultadStr = request.getParameter("dificultad");
		String tematicaStr = request.getParameter("tematica");
		String horasStr = request.getParameter("horas");
		String precioStr = request.getParameter("precio");
		String codProfStr = "10";
		
		HttpSession sesion = request.getSession();
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		
		
		String m = comprobarCurso(titulo, descripcion, dificultadStr, horasStr, precioStr, tematicaStr);
		if (m == null || m.equals("")){
			int dificultad = Integer.parseInt(dificultadStr);
			int horas = Integer.parseInt(horasStr);
			int precio = Integer.parseInt(precioStr);  
			if(tematicaStr.equals("0")){
				tematicaStr="artes";
			}
			else if (tematicaStr.equals("1")) {
				tematicaStr="ciencias";
			}
			else {
				tematicaStr="ingenieria";
			}
			
			
		
			
			// creates the save directory if it does not exists
			File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	         
	        for (Part part : request.getParts()) {
	            String fileName = "Alejandro.jpg";
	            part.write(savePath + File.separator + fileName);
	        }
			
	        
	        Curso c = crearCurso(titulo, descripcion, dificultad, horas, precio, user, tematicaStr);
			try {
				c=curDao.guardarCurso(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Collection<Curso> cursosCreados = curDao.recuperarCursosPorProfesor(user.getID_usuario());
			sesion.setAttribute("cursoscreados", cursosCreados);
			Collection<Curso> listaCursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", listaCursos);
			mensaje = m;
			sesion.setAttribute("mensaje", mensaje);
			
			
		}else{
			
			mensaje = m;
			sesion.setAttribute("mensaje", mensaje);
			Collection<Curso> listaCursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", listaCursos);
		}
		
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

	private Curso crearCurso(String titulo, String descripcion, int dificultad, int horas, int precio, Usuario profesor, String tematica) {
		Curso c = new Curso();
		
		c.setProfesor(profesor);
		c.setDES_descripcion(descripcion);
		c.setDES_titulo(titulo);
		c.setHoras(horas);
		c.setPrecio_final(precio);
		c.setPrecio_inicial(precio);
		c.setTIPO_destacado(0);
		c.setTIPO_dificultad(dificultad);
		c.setTIPO_estado(0);
		c.setTematica(tematica);
		
		return c;
	}



	private String comprobarCurso(String titulo, String descripcion, String dificultad, String horas, String precio, String tematica) {
		String m = "";
		
		if (titulo.equals("") || titulo.equals(null) || descripcion.equals("") || descripcion.equals(null) || dificultad.equals("-1") || horas.equals("") || horas.equals(null) || precio.equals("") || precio.equals(null) || tematica.equals("-1") || tematica.equals(null) ) {
			m ="Fallo al crear nuevo curso. ";
		}
		
		return m;
	}
}
