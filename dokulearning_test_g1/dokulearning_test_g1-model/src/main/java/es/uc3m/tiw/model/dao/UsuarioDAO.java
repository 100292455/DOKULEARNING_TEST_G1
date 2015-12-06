package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Usuario;

public interface UsuarioDAO {

	public abstract Usuario guardarUsuario(Usuario nuevoUsuario)
			throws Exception;

	public abstract Usuario modificarUsuario(Usuario usuario) throws Exception;

	public abstract void borrarUsuario(Usuario usuario) throws Exception;

	public abstract Usuario recuperarUsuarioPorPK(Long pk);

	public abstract Usuario recuperarUsuarioPorNombre(String nombre);

	public abstract Usuario buscarPorEmailYpassword(String email, String password);
	
	public abstract Usuario buscarPorEmail(String email);

	public abstract Collection<Usuario> buscarTodosLosUsuarios();

}