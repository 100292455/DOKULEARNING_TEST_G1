package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
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
import es.uc3m.tiw.model.Pedido;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.PedidoDAOImpl;
import es.uc3m.tiw.web.rest.AlumnoWSBanco;

@WebServlet("/WebService")
public class WSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FACTURA_JSP = "/misCursos.jsp";
	private static final String CONCILIAR_JSP = "/WSconciliar.jsp";
	private ServletConfig config2;
	private AlumnoWSBanco ws;

	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private PedidoDAOImpl pedDao;
	private CursoDAOImpl curDao;
	private MatriculaDAO matDao;

	@Override
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		pedDao = new PedidoDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		ws = new AlumnoWSBanco();
	}	

	public void destroy() {
		pedDao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filtro = request.getParameter("filtro");
		HttpSession sesion = request.getSession();
		String cod_operacionBanc = "";
		String pagina = ""; 
		if(filtro.equals("Facturar")) {
			String tarjeta = request.getParameter("numero");
			if(tarjeta.equals("") || tarjeta == null) {
				cod_operacionBanc = "No se formalizo la matricula. Numero de tarjeta erroneo";
				pagina = "/formularioPago.jsp";
			} else{
				//Coger el titulo del curso y coger su ID
				int idCurso = (int) sesion.getAttribute("idCurso");
				 
				Curso curso = null;
				try {
					curso=curDao.recuperarCursoPorPK(idCurso);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Coger el precio final del curso de la BBDD
				int precioInt = curso.getPrecio_final();

				//Coger el ID del usuario
				Usuario user = (Usuario) sesion.getAttribute("usuario");

				String precio = Integer.toString(precioInt);

				Double precioDouble = Double.parseDouble(precio);

				Calendar c=Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH)+1;  
				int day = c.get(Calendar.DATE);
				int hours = c.get(Calendar.HOUR_OF_DAY);
				int seconds = c.get(Calendar.SECOND);
				int miliseconds = c.get(Calendar.MILLISECOND);
				int PM_AM = c.get(Calendar.AM_PM);
				String PM_AMStr = "";
				if (PM_AM == 0){PM_AMStr = "AM";}
				else {PM_AMStr = "PM";}  

				String codigo_pago = "ORDER"+year+month+day+hours+seconds+miliseconds+PM_AMStr;
				cod_operacionBanc = ws.PedidoWSBanco(precio, tarjeta, codigo_pago);
				pagina = FACTURA_JSP;
				if(cod_operacionBanc.equals("fail") == false) {
					Pedido nuevoPedido = new Pedido(precioDouble, 0.0, tarjeta, cod_operacionBanc, codigo_pago, curso, 0);
					try {  
						pedDao.guardarPedido(nuevoPedido);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					int precio_pagado = curso.getPrecio_final();
					Matricula matricula = new Matricula (user, curso, precio_pagado);
					try { 
						matricula=matDao.guardarMatricula(matricula);
					} catch (Exception e) {   
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					
					Collection<Matricula> listadoMatricula2 = matDao.recuperarMatriculaPorAlumno(user.getID_usuario());
					
					sesion.setAttribute("matriculas", listadoMatricula2);
				} else if(cod_operacionBanc.equals("fail")){
					cod_operacionBanc = "No se formalizo la matricula. Numero de tarjeta erroneo";
					pagina = "/formularioPago.jsp";
				}
			}
			request.setAttribute("mensajePago", cod_operacionBanc);
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		}
		if(filtro.equals("Conciliar")) {
			/* Recuperar de DB -> PEDIDOS WHERE CONCILIACION = 0 */
			Collection<Pedido> pedidosSinConciliar=pedDao.recuperarPedidosSinConciliar();
			for (Pedido pedido : pedidosSinConciliar) {
				String cod_pedido = pedido.getCOD_pago();
				/*Llamar a web service pasandole el codPago*/
				Double precioConciliado = ws.ConciliarWSBanco(cod_pedido);
				/*Recibo del banco el precio conciliado (99% del precio original del curso)*/
				pedido.setESTADO_conciliado(1);
				pedido.setImporteCobrado(precioConciliado);
				try {
					pedDao.modificarPedido(pedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			config2.getServletContext().getRequestDispatcher(CONCILIAR_JSP).forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		config2.getServletContext().getRequestDispatcher(FACTURA_JSP).forward(request, response);


	}

}