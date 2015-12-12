package es.uc3m.tiw.web.controladores;

import java.io.File;
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
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Leccion;
import es.uc3m.tiw.model.Seccion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.LeccionDAO;
import es.uc3m.tiw.model.dao.LeccionDAOImpl;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.SeccionDAO;
import es.uc3m.tiw.model.dao.SeccionDAOImpl;

@WebServlet("/AltaLeccion")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50,
location = "/")   // 50MB
public class AltaLeccionServlet extends HttpServlet {
	//private static final String ENTRADA_JSP = "/gestionlecciones.jsp";
	private static final String FORMULARIO_LECCIONES_JSP = "/formularioLecciones.jsp";
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "lecciones";
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private SeccionDAO secDao;
	private LeccionDAO lecDao;
	ServletContext context;
	ServletContext sesion;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		lecDao = new LeccionDAOImpl(em, ut);
		secDao = new SeccionDAOImpl(em, ut);

	}
	
	public void destroy() {
		curDao = null;
		lecDao = null;
		secDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		config2.getServletContext().getRequestDispatcher(FORMULARIO_LECCIONES_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		// gets absolute path of the web application
        String appPath = "/home/tiw/workspace/maven.1444917813281/grupo1/grupo1-web/src/main/webapp/images";
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
		int id_seccion =  (int) sesion.getAttribute("idseccion");
		System.out.println("-------------------------------------------------222222222222222222"+id_seccion);
		String titulo = request.getParameter("titulo");
		String formato = request.getParameter("formato");
		String descripcion= request.getParameter("descripcion");
		Seccion seccionactual= secDao.recuperarSeccionPorPK(id_seccion);
		String nombreseccionactual=seccionactual.getNombre();
		sesion.setAttribute("nombreseccionactual",nombreseccionactual);
		//Collection<Leccion> listadoLeccionesIniciales = lecDao.recuperarLeccionesPorSeccion(seccionactual.getId_seccion());
		//sesion.setAttribute("lecciones", listadoLeccionesIniciales);
		Collection<Leccion> listadoLeccionesIniciales = lecDao.buscarTodosLosLecciones();
		sesion.setAttribute("lecciones", listadoLeccionesIniciales);
		/*****/
		
		String mensaje ="";
		String pagina = "";
		
	
		String m = comprobarLeccion(titulo, descripcion, formato);
		if (m == null || m == ""){
		  
			
		
			Leccion l = crearLeccion(titulo, descripcion,formato,seccionactual);
			try {
				l=lecDao.guardarLeccion(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	         
	       
			
			pagina = "/contenidoCurso.jsp";
			Collection<Leccion> listaLecciones = lecDao.recuperarLeccionesPorSeccion(id_seccion);
			seccionactual.setLecciones(listaLecciones);
			try {
				secDao.modificarSeccion(seccionactual);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sesion.setAttribute("lecciones", listaLecciones);
			sesion.setAttribute("leccion", l);
			mensaje = m;
			sesion.setAttribute("mensajeLecciones", mensaje);
			
		}else{
			Collection<Leccion> listaLecciones = lecDao.recuperarLeccionesPorSeccion(id_seccion);
			sesion.setAttribute("lecciones", listaLecciones);
			mensaje = m;
			sesion.setAttribute("mensajeLecciones", mensaje);
		}
			
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

	private Leccion crearLeccion(String titulo, String descripcion, String formato, Seccion s) {
		Leccion c = new Leccion();
		
	c.setTitulo(titulo);
	c.setDescripcion(descripcion);
	c.setFormato(formato);
	c.setSeccion(s);
		return c;
	}



	private String comprobarLeccion(String titulo, String descripcion, String formato) {
		String m = "";
		
		if (titulo.equals("") || titulo.equals(null) || descripcion.equals("") || descripcion.equals(null)) {
			m ="Fallo al crear nueva leccion. ";
		}
		
		return m;
	}

}