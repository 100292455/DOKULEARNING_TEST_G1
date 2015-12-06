
package es.uc3m.tiw.web.controladores;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import es.uc3m.tiw.model.Usuario;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50,
location = "/")   // 50MB
public class UploadServlet extends HttpServlet {
	private static final String MIS_CURSOS_JSP = "/miPerfilAlumno.jsp";
	private static final long serialVersionUID = 1L;
	 /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "usuarios-img";
     
    /**
     * hand
	@Override
	public void init() throws ServletException {
	
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
	        String appPath = request.getServletContext().getRealPath("/images");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + File.separator + SAVE_DIR;
			HttpSession sesion = request.getSession(true);
			Usuario usuario = (Usuario)sesion.getAttribute("usuario");
			int idUsuario = usuario.getID_usuario();
	         
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("empresa");
			String telefono = request.getParameter("telefono");
			String correo = request.getParameter("correo");
			String descripcion = request.getParameter("descripcion");
			String intereses = request.getParameter("intereses");
			
			String m = comprobarCurso(nombre, apellido, telefono, correo);
			if (m == null || m.equals( "")){
				
				// creates the save directory if it does not exists
				// creates the save directory if it does not exists
		        File fileSaveDir = new File(savePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdir();
		        }
		         
		        for (Part part : request.getParts()) {
		            String fileName = idUsuario+".jpg";
		            part.write(savePath + File.separator + fileName);
		        }
				
				 usuario = actualizarUsuario(nombre, apellido, telefono, correo, usuario, descripcion, intereses);
				sesion.setAttribute("usuario", usuario);

				
				
			}else{
				
				mensaje = m;
				request.setAttribute("mensaje", mensaje);
			}
			
				this.getServletContext().getRequestDispatcher(pagina).forward(request, response);

			
		
	}

	private Usuario actualizarUsuario(String nombre, String apellido, String telefono, String correo, Usuario usuario, String descripcion, String intereses) {
		
		usuario.setApellido(apellido);
		usuario.setNombre(nombre);
		usuario.setEmail(correo);
		usuario.setTelefono(telefono);
		usuario.setDescripcion(descripcion);
		usuario.setIntereses(intereses);
		
		return usuario;
	}

	private String comprobarCurso(String nombre, String apellido, String telefono, String correo) {
		String m = "";
		
		if (nombre.equals("") || nombre ==null || apellido.equals("") || apellido == null || telefono.equals("") || telefono == null || correo.equals("") || correo == null) {
			m ="Fallo al actualizar. ";
		}
		
		return m;
	}

}
