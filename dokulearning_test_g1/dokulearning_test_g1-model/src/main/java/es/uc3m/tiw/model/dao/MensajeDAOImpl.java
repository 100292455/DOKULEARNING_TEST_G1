package es.uc3m.tiw.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import es.uc3m.tiw.model.Mensaje;

public class MensajeDAOImpl implements MensajeDAO {
	
	private final EntityManager em;
	private final UserTransaction ut;
	
	public MensajeDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}

	@Override
	public void guardarMensaje(Mensaje nuevoMensaje) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		ut.begin();
		em.persist(nuevoMensaje);
		ut.commit();
	}

	@Override
	public Collection<Mensaje> recuperarMensajePorCurso(int id_curso) {
		return em.createQuery("select m from Mensaje m where m.curso.ID_curso="+id_curso, Mensaje.class).getResultList();
	}

}
