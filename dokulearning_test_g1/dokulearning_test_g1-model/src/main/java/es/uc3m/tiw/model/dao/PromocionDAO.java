package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Promocion;

public interface PromocionDAO {

	public abstract Promocion guardarPromocion(Promocion nuevoPromocion)
			throws Exception;

	public abstract Promocion modificarPromocion(Promocion Promocion) throws Exception;

	public abstract void borrarPromocion(Promocion Promocion) throws Exception;

	public abstract Promocion recuperarPromocionPorPK(int pk);

	public abstract Promocion recuperarPromocionPorNombre(String nombre);

	public abstract Collection<Promocion> buscarTodosLosPromociones();

}