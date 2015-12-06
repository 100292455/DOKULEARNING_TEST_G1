package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Leccion;
import es.uc3m.tiw.model.Seccion;

public interface LeccionDAO {

	public abstract Leccion guardarLeccion(Leccion nuevaLeccion)
			throws Exception;

	public abstract Leccion modificarLeccion(Leccion Leccion) throws Exception;

	public abstract void borrarLeccion(Leccion Leccion) throws Exception;

	public abstract Leccion recuperarLeccionPorPK(int pk);

	public abstract Leccion recuperarLeccionPorNombre(String nombre);
	
	public abstract Collection<Leccion> recuperarLeccionesPorSeccion(int id_seccion);

	public abstract Collection<Leccion> buscarTodosLosLecciones();

}