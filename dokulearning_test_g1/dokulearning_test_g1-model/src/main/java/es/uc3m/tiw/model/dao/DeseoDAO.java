package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Deseo;

public interface DeseoDAO {

	public abstract Deseo guardarDeseo(Deseo nuevoDeseo)
			throws Exception;
	
	public abstract Deseo modificarDeseo(Deseo deseo) throws Exception;
	
	public abstract void borrarDeseo(Deseo deseo) throws Exception;
	
	public abstract Collection<Deseo> buscarTodosLosDeseos();
	
	public abstract Collection<Deseo> recuperarCursosDeseadosPorUsuario(int idUsuario);
	
	public abstract Collection<Deseo> recuperarDeseoporCurso(int idcurso);
	
	public abstract Deseo recuperarDeseoporPK(int pk);
}
