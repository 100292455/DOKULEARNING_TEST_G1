package es.uc3m.tiw.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Promocion;

public class PromocionDAOImpl implements PromocionDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public PromocionDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}



	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#guardarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Promocion guardarPromocion(Promocion nuevoPromocion) throws Exception{
		ut.begin();
		em.persist(nuevoPromocion);
		ut.commit();
		return nuevoPromocion;
		
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#modificarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Promocion modificarPromocion(Promocion promocion) throws Exception{
		ut.begin();
		em.merge(promocion);
		ut.commit();
		return promocion;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#borrarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public void borrarPromocion(Promocion promocion) throws Exception{
		ut.begin();
		em.remove(em.merge(promocion));
		ut.commit();
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorPK(java.lang.Integer)
	 */
	@Override
	public Promocion recuperarPromocionPorPK(Long pk){
		return em.find(Promocion.class, pk);
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorNombre(java.lang.String)
	 */
	@Override
	public Promocion recuperarPromocionPorNombre(String nombre) {
		return em.createQuery("select p from Promocion p where p.nombre="+nombre, Promocion.class).getSingleResult();
	}
	
	public Collection<Promocion> buscarTodosLosPromociones(){
		return em.createQuery("select p from Promocion p",Promocion.class).getResultList();
	}
}
	