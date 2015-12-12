package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;

@WebServlet("/AltaCupones")
public class AltaCuponesServlet extends HttpServlet {
	//private static final String ENTRADA_JSP = "/GestionCupones.jsp";
	private static final String CONTENIDO_CURSO_JSP = "/contenidoCurso.jsp";
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
		cupDao = new CuponDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);

	}
	
	public void destroy() {
		cupDao = null;
		curDao = null;
		promDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		config2.getServletContext().getRequestDispatcher(CONTENIDO_CURSO_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Coger los datos del cupon que el profesor quiere crear
		String precio1 = request.getParameter("precio");
		String tipo_cupon1 = request.getParameter("tipo_cupon");
		System.out.println("----------------------------------"+tipo_cupon1);
		String fecha_fin = request.getParameter("datepicker");
		HttpSession sesion = request.getSession();
		Usuario user = (Usuario) sesion.getAttribute("usuario");
		String nombreCurso = (String) sesion.getAttribute("nombreCurso");
		String mensaje ="";
		String pagina = "";
		pagina = CONTENIDO_CURSO_JSP;
		//Comprobar que los datos almacenados en la peticion no estan vacios
		String m = comprobarCupon(precio1, tipo_cupon1, fecha_fin);
		Curso c = curDao.recuperarCursoPorNombre(nombreCurso);
		if(cupDao.recuperarCuponPorCurso(c).size()!=0){
			mensaje = "Error al crear cupon. Ya existe otro cupon para este curso.";
			sesion.setAttribute("mensajeCupones", mensaje);
		}
		//Si el cupon a crear presenta todos los datos necesarios para crearlo y no hay otro cupon en ese curso, entonces:
		else if (m.equals(null) || m.equals("")){
			int precio2 = Integer.parseInt(precio1);
			int tipo_cupon2 = Integer.parseInt(tipo_cupon1);
			System.out.println("-------------------------------------tipo_cupon2"+tipo_cupon2);
			
			//Creamos el cupon
			
			Calendar cal=Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);  
			int day = cal.get(Calendar.DATE);
			int hours = cal.get(Calendar.HOUR_OF_DAY);
			int seconds = cal.get(Calendar.SECOND);
			int miliseconds = cal.get(Calendar.MILLISECOND);
			int PM_AM = cal.get(Calendar.AM_PM);
			String PM_AMStr = "";
			if (PM_AM == 0){PM_AMStr = "AM";}
			else {PM_AMStr = "PM";}
			String nombreCupon = "Cupon"+year+month+day+hours+seconds+miliseconds+PM_AMStr;
			Cupon cuponNuevo = new Cupon(fecha_fin, user, precio2, tipo_cupon2, c, nombreCupon);
			
			//modificar precio del curso
			int precioInicial = c.getPrecio_inicial();
			int descuento = cuponNuevo.getDescuento();
			//el cupon es de tipo fijo
			if (tipo_cupon2==0) {
				if(descuento>0.7*precioInicial){
					m="Error al crear el cupon. El valor del cupon es mayor que los beneficios que usted obtendria";
					sesion.setAttribute("mensajeCupones", m);
				}
				else {
					c.setPrecio_final(precioInicial-descuento);
					c.setFechaFinDescuento(cuponNuevo.getFecha_vto_cupon());
					try {
						cuponNuevo = cupDao.guardarCupon(cuponNuevo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			//el cupon es de tipo %
			else{
				if((descuento*0.01)*precioInicial>0.7*precioInicial){
					m="Error al crear el cupon. El valor del cupon es mayor que los beneficios que usted obtendria";
					sesion.setAttribute("mensajeCupones", m);
				}
				
				else {
					int descuentoTotal = (int) (precioInicial-((descuento*0.01)*precioInicial));
					c.setPrecio_final(descuentoTotal);
					c.setFechaFinDescuento(cuponNuevo.getFecha_vto_cupon());
					try {
						cuponNuevo = cupDao.guardarCupon(cuponNuevo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			//metemos la tabla de cupones en el contexto para poder utilizarla desde otras paginas
			Collection<Cupon> listadoCupones = cupDao.recuperarCuponPorCurso(c);
			c.setCupones(listadoCupones);
			try {
				curDao.modificarCurso(c);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//metemos el curso a matricular en el contexto para luego poder mostrar su informacion
			sesion.setAttribute("curso", c);
			sesion.setAttribute("cupones", listadoCupones);
			
			//Guardamos curso modificado
			try {
				c = curDao.modificarCurso(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//metemos el curso a matricular en el contexto para luego poder mostrar su informacion
			sesion.setAttribute("curso", c);
			//metemos la tabla de cupones en el contexto para poder utilizarla desde otras paginas
			//Collection<Cupon> listadoCupones = cupDao.recuperarCuponPorCurso(c);
			//sesion.setAttribute("cupones", listadoCupones);
			Collection<Curso> listadoCursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", listadoCursos);
			sesion.setAttribute("mensajeCupones", m);
		
		//Si falta algun dato de los introducidos por el formulario para crear el cupon, enviamos un mensaje de error a dicha pagina	
		}else{
			
			mensaje = m;
			sesion.setAttribute("mensajeCupones", mensaje);
			
		}
			
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		}

	//Creamos el cupon
	private Cupon crearCupon(String fecha_fin, Usuario profe, int tipo_cupon3, Curso curso, int descuento) {
		Cupon c = new Cupon();
		c.setFecha_vto_cupon(fecha_fin);
		c.setProfesor(profe);
		c.setTIPO_cupon(tipo_cupon3);
		c.setCurso(curso);
		c.setDescuento(descuento);
		return c;
	}


	//Comprobar que los datos almacenados en la peticion no estan vacios
	private String comprobarCupon(String precio, String tipo_promocion, String fecha_fin) {
		String m = "";
		
		if (precio.equals("") || tipo_promocion.equals("") || fecha_fin.equals("")) {
			m ="Fallo al crear nuevo cupon. ";
		}
		
		else if (promDao.buscarTodosLosPromociones().size()!=0) {
			m ="Fallo al crear nuevo cupon. Hay promociones activas en estos momentos";
		}
		
		
		return m;
	}
	

}