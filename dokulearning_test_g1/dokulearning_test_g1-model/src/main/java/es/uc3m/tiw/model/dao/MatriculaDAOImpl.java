package es.uc3m.tiw.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Usuario;

public class MatriculaDAOImpl implements MatriculaDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public MatriculaDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}



	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#guardarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Matricula guardarMatricula(Matricula nuevoMatricula) throws Exception{
		ut.begin();
		em.persist(nuevoMatricula);
		ut.commit();
		return nuevoMatricula;
		
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#modificarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Matricula modificarMatricula(Matricula matricula) throws Exception{
		ut.begin();
		em.merge(matricula);
		ut.commit();
		return matricula;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#borrarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public void borrarMatricula(Matricula matricula) throws Exception{
		ut.begin();
		em.remove(em.merge(matricula));
		ut.commit();
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorPK(java.lang.Integer)
	 */
	@Override
	public Matricula recuperarMatriculaPorPK(Long pk){
		return em.find(Matricula.class, pk);
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorNombre(java.lang.String)
	 */
	@Override
	public Collection<Matricula> recuperarMatriculaPorAlumnoYCurso(int id_usuario, int id_curso) {
		return em.createQuery("select m from Matricula m where m.alumno.ID_usuario="+id_usuario+" and m.curso.ID_curso="+id_curso,Matricula.class).getResultList();
	}
	
	@Override
	public Collection<Matricula> recuperarMatriculaPorAlumno(int id_usuario) {
		return em.createQuery("select m from Matricula m where m.alumno.ID_usuario="+id_usuario, Matricula.class).getResultList();
	}
	
	public Collection<Matricula> buscarTodosLosMatriculas(){
		return em.createQuery("select m from Matricula m",Matricula.class).getResultList();
	}	
		
	public Collection<Matricula> recuperarMatriculaPorCurso(int id_curso) {
			return em.createQuery("select m from Matricula m where m.curso.ID_curso="+id_curso, Matricula.class).getResultList();
		}
	}
	
