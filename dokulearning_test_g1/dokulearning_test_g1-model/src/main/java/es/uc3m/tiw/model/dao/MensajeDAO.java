package es.uc3m.tiw.model.dao;

import java.util.Collection;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import es.uc3m.tiw.model.Mensaje;


public interface MensajeDAO {

	public abstract void guardarMensaje(Mensaje nuevoMensaje)  throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException;		
	public abstract Collection<Mensaje> recuperarMensajePorCurso(int id_curso);

	
}
