package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Seccion;

public interface CuponDAO {

	public abstract Cupon guardarCupon(Cupon nuevoCupon)
			throws Exception;

	public abstract Cupon modificarCupon(Cupon Cupon) throws Exception;

	public abstract void borrarCupon(Cupon Cupon) throws Exception;

	public abstract Cupon recuperarCuponPorPK(Long pk);
	
	public abstract Collection<Cupon> recuperarCuponPorCurso(Curso curso);

	public abstract Collection<Cupon> buscarTodosLosCupones();

}