package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Seccion;

public interface SeccionDAO {

	public abstract Seccion guardarSeccion(Seccion nuevoSeccion)
			throws Exception;

	public abstract Seccion modificarSeccion(Seccion Seccion) throws Exception;

	public abstract void borrarSeccion(Seccion Seccion) throws Exception;

	public abstract Seccion recuperarSeccionPorPK(int pk);

	public abstract Seccion recuperarSeccionPorNombre(String nombre);

	public abstract Seccion recuperarSeccionPorCurso(int id_curso);

	public abstract Collection<Seccion> buscarTodosLosSecciones();

	public abstract Collection<Seccion> recuperarSeccionesPorCurso(int id_curso);

}