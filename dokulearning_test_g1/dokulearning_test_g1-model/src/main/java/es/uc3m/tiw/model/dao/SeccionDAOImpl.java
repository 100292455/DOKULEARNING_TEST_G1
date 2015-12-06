package es.uc3m.tiw.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Seccion;

public class SeccionDAOImpl implements SeccionDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public SeccionDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}



	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#guardarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Seccion guardarSeccion(Seccion nuevoSeccion) throws Exception{
		ut.begin();
		em.persist(nuevoSeccion);
		ut.commit();
		return nuevoSeccion;
		
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#modificarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Seccion modificarSeccion(Seccion seccion) throws Exception{
		ut.begin();
		em.merge(seccion);
		ut.commit();
		return seccion;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#borrarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public void borrarSeccion(Seccion seccion) throws Exception{
		ut.begin();
		em.remove(em.merge(seccion));
		ut.commit();
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorPK(java.lang.Integer)
	 */
	@Override
	public Seccion recuperarSeccionPorPK(int pk){
		return em.find(Seccion.class, pk);
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorNombre(java.lang.String)
	 */
	@Override
	public Seccion recuperarSeccionPorNombre(String nombre) {
		return em.createQuery("select s from Seccion s where s.nombre="+nombre, Seccion.class).getSingleResult();
	}
	
	@Override
	public Seccion recuperarSeccionPorCurso(int id_curso) {
		return em.createQuery("select s from Seccion s where s.curso.ID_curso="+id_curso, Seccion.class).getSingleResult();
	}
	
	@Override
	public Collection<Seccion> recuperarSeccionesPorCurso(int id_curso) {
		return em.createQuery("select s from Seccion s where s.curso.ID_curso="+id_curso, Seccion.class).getResultList();
	}
	
	public Collection<Seccion> buscarTodosLosSecciones(){
		return em.createQuery("select s from Seccion s",Seccion.class).getResultList();
	}
	
}