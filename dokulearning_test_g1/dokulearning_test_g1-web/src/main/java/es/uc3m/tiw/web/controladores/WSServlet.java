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
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Pedido;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.PedidoDAOImpl;
import es.uc3m.tiw.web.rest.AlumnoWSBanco;

@WebServlet("/WebService")
public class WSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FACTURA_JSP = "/WStest.jsp";
	private static final String CONCILIAR_JSP = "/WSconciliar.jsp";
	private ServletConfig config2;
	private AlumnoWSBanco ws;

	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private PedidoDAOImpl pedDao;
	private CursoDAOImpl curDao;

	@Override
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		pedDao = new PedidoDAOImpl(em, ut);
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

		if(filtro.equals("Facturar")) {
			String tarjeta = request.getParameter("tarjeta");
			String precio = request.getParameter("precio");
			String idCursoStr = request.getParameter("curso");

			Double precioInt = Double.parseDouble(precio);
			int pk = Integer.parseInt(idCursoStr);
			Curso curso = curDao.recuperarCursoPorPK(pk);

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
			String cod_operacionBanc = ws.PedidoWSBanco(precio, tarjeta, codigo_pago);
			if(cod_operacionBanc.equals("fail") == false) {
				Pedido nuevoPedido = new Pedido(precioInt, 0.0, tarjeta, cod_operacionBanc, codigo_pago, curso, 0);
				try {  
					pedDao.guardarPedido(nuevoPedido);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("curso", idCursoStr); 
			request.setAttribute("mensaje", cod_operacionBanc);
			config2.getServletContext().getRequestDispatcher(FACTURA_JSP).forward(request, response);
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