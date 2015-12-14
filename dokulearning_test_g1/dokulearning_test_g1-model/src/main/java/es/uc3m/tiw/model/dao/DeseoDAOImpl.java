package es.uc3m.tiw.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Deseo;

public class DeseoDAOImpl implements DeseoDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public DeseoDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}

	@Override
	public Deseo guardarDeseo(Deseo nuevoDeseo) throws Exception{
		ut.begin();
		em.persist(nuevoDeseo);
		ut.commit();
		return nuevoDeseo;
		
	}
	
	@Override
	public void borrarDeseo(Deseo deseo) throws Exception{
		ut.begin();
		em.remove(em.merge(deseo));
		ut.commit();
	}
	
	public Collection<Deseo> buscarTodosLosDeseos(){
		return em.createQuery("select c from Deseo c",Deseo.class).getResultList();
	}
	
	@Override
	public Collection<Deseo> recuperarCursosDeseadosPorUsuario(int idUsuario){
		return em.createQuery("select d from Deseo d where d.usuario.ID_usuario="+idUsuario, Deseo.class).getResultList();
	}
	public  Collection<Deseo> recuperarDeseoporCurso(int idcurso){
		return em.createQuery("select d from Deseo d where d.cursoDeseado.ID_curso="+idcurso, Deseo.class).getResultList();
	}
	
	@Override
	public Deseo modificarDeseo(Deseo deseo) throws Exception{
		ut.begin();
		em.merge(deseo);
		ut.commit();
		return deseo;
	}
	
	public Deseo recuperarDeseoporPK(int pk){
		return em.find(Deseo.class, pk);
	}
}