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
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;

@WebServlet("/AltaPromociones")
public class AltaPromocionesServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/GestionPromociones.jsp";
	private static final String GESTION_CURSOS_JSP = "/GestionPromociones.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private PromocionDAO promDao;
	private CuponDAO cupDao;
	private CursoDAO curDao;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		cupDao = new CuponDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		cupDao = null;
		curDao = null;
		promDao = null;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Coger los datos de la promocion que el admin quiere crear
		String nombrePromo = request.getParameter("nombrePromo");
		String precio1 = request.getParameter("precio");
		String tipo_promocion1 = request.getParameter("tipo_promocion");
		String fecha_fin = request.getParameter("fecha_fin");
		
		String mensaje ="";
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		
		//Comprobar que los datos almacenados en la peticion no estan vacios
		String m = comprobarPromocion(nombrePromo, precio1, tipo_promocion1, fecha_fin);
		//Si la promocion a crear presenta todos los datos necesarios para crearla, entonces:
		if (m.equals(null) || m.equals("")){
			int precio2 = Integer.parseInt(precio1);
			int tipo_promocion2 = Integer.parseInt(tipo_promocion1);
			//Creamos la promocion y guardamos en BBDD
			Promocion p = crearPromocion(nombrePromo, precio2, tipo_promocion2, fecha_fin);
			try {
				p=promDao.guardarPromocion(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cambiarPrecioCursos(p, curDao.buscarTodosLosCursos());
			pagina = ENTRADA_JSP;
			mensaje = m;
			sesion.setAttribute("mensaje", mensaje);
			//metemos la tabla de promociones en el contexto para poder utilizarla desde otras paginas
			//context.setAttribute("promociones", promociones);
			//metemos la promocion  en el contexto para poder mostar su informacion en las paginas de promociones creadas (GestionPromociones)
			//context.setAttribute("promocion", promocion);
			
			//Si falta algun dato de los introducidos por el formulario para crear la promocion, enviamos un mensaje de error a dicha pagina	
		}else{
			
			mensaje = m;
			sesion.setAttribute("mensaje", mensaje);
		}
			
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}
	//Creamos la promocion
	private Promocion crearPromocion(String nombrePromo, int precio, int tipo_promocion, String fecha_fin) {
		Promocion p = new Promocion();
		p.setNombrePromo(nombrePromo);
		p.setDescuento(precio);
		p.setTipo_promo(tipo_promocion);
		p.setFecha_fin(fecha_fin);
		
		return p;
	}


	//Comprobar que los datos almacenados en la peticion no estan vacios
	private String comprobarPromocion(String nombrePromo, String precio, String tipo_promocion, String fecha_fin) {
		String m = "";
		
		if (nombrePromo.equals("") || precio.equals("") || tipo_promocion.equals("") || fecha_fin.equals("")) {
			m ="Fallo al crear nuevo cupon. ";
		}
		
		return m;
	}
	
	private void cambiarPrecioCursos(Promocion p, Collection<Curso> cursos) {
			for (Curso curso : cursos) {
				int precioInicial = curso.getPrecio_inicial();
				int descuento = p.getDescuento();
				int tipoDescuento = p.getTipo_promo();
				if (tipoDescuento==0) {
					curso.setPrecio_final(precioInicial-descuento);
				}
				else{
					
					int descuentoTotal = (int) (precioInicial-((descuento*0.01)*precioInicial));
					curso.setPrecio_final(descuentoTotal);
					
				}
				
				curso.setFechaFinDescuento(p.getFecha_fin());
				try {
					curso = curDao.modificarCurso(curso);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

}