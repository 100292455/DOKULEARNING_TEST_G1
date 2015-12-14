package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Usuario;

public interface MatriculaDAO {

	public abstract Matricula guardarMatricula(Matricula nuevoMatricula)
			throws Exception;

	public abstract Matricula modificarMatricula(Matricula matricula) throws Exception;

	public abstract void borrarMatricula(Matricula matricula) throws Exception;

	public abstract Matricula recuperarMatriculaPorPK(Long pk);

	public abstract Collection<Matricula> recuperarMatriculaPorAlumnoYCurso(int id_alumno, int id_curso);

	public abstract Collection<Matricula> buscarTodosLosMatriculas();

	public abstract Collection<Matricula> recuperarMatriculaPorAlumno(int id_usuario);
	
	public abstract Collection<Matricula> recuperarMatriculaPorCurso(int id_curso);

}