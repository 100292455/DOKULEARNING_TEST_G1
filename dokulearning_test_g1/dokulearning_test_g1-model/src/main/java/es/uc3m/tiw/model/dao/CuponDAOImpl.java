package es.uc3m.tiw.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Curso;

public class CuponDAOImpl implements CuponDAO {
	
	/*
persist:
    Insert a new register to the database
    Attach the object to the entity manager.

merge:
    Find an attached object with the same id and update it.
    If exists update and return the already attached object.
    If doesn't exist insert the new register to the database.
    
    
The begin method starts a global transaction and associates the transaction with the calling thread. 
The commit method completes the transaction associated with the current thread. 
All statements within begin and commit execute in the transaction scope

*/
	private final EntityManager em;
	private final UserTransaction ut;
	
	
	public CuponDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}



	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.CuponDAO#guardarCupon(es.uc3m.tiw.dominios.Cupon)
	 */
	@Override
	public Cupon guardarCupon(Cupon nuevoCupon) throws Exception{
		ut.begin();
		em.persist(nuevoCupon);
		ut.commit();
		return nuevoCupon;
		
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#modificarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Cupon modificarCupon(Cupon cupon) throws Exception{
		ut.begin();
		em.merge(cupon);
		ut.commit();
		return cupon;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#borrarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public void borrarCupon(Cupon cupon) throws Exception{
		ut.begin();
		em.remove(em.merge(cupon));
		ut.commit();
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorPK(java.lang.Integer)
	 */
	@Override
	public Cupon recuperarCuponPorPK(Long pk){
		return em.find(Cupon.class, pk);
	}
	
	
	public Collection<Cupon> buscarTodosLosCupones(){
		return em.createQuery("select c from Cupon c",Cupon.class).getResultList();
	}
	
	@Override
	public Collection<Cupon> recuperarCuponPorCurso(Curso curso) {
		Query query = em.createQuery("select c from Cupon c where c.curso=:curso", Cupon.class);
		query.setParameter("curso", curso);
		return query.getResultList();
	}
	
}


