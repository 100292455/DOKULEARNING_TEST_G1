package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Curso;

public interface CursoDAO {

	public abstract Curso guardarCurso(Curso nuevoCurso)
			throws Exception;

	public abstract Curso modificarCurso(Curso Curso) throws Exception;

	public abstract Collection<Curso> recuperarCursosPorDestacado(int destacado);
	
	public abstract void borrarCurso(Curso Curso) throws Exception;

	public abstract Collection<Curso> recuperarCursosPorDEstado(int estado);
	
	public abstract Curso recuperarCursoPorPK(int pk);

	public abstract Curso recuperarCursoPorNombre(String nombre);

	public abstract Collection<Curso> buscarTodosLosCursos();

	public abstract Collection<Curso> recuperarCursosPorPrecio(int precio);

	public abstract Collection<Curso> recuperarCursosPorDificultad(int dificultad);
	
	public abstract Collection<Curso> recuperarCursosPorDescripcion(String descripcion);
	
	public abstract Collection<Curso> recuperarCursosPorTematica(String tematica);
	
	public abstract Collection<Curso> recuperarCursosPorPrecioYDificultad(int precio, int dificultad);
	
	public abstract Collection<Curso> recuperarCursosPorPrecioYDescripcion(int precio, String descripcion);
	
	public abstract Collection<Curso> recuperarCursosPorDificultadYDescripcion(int dificultad, String descripcion);
	
	public abstract Collection<Curso> recuperarCursosPorPrecioYDificultadYDescripcion(int dificultad, String descripcion, int precio);

	public abstract Collection<Curso> recuperarCursosPorProfesor(int id_profe);
	
	public abstract Collection<Curso> recuperarCursosPorPrecioYTematica(int precio, String tematica);
	
	public abstract Collection<Curso> recuperarCursosPorDificultadYTematica(int dificultad, String tematica);
	
	public abstract Collection<Curso> recuperarCursosPorPrecioYDificultadYTematica(int dificultad, String tematica, int precio);
}