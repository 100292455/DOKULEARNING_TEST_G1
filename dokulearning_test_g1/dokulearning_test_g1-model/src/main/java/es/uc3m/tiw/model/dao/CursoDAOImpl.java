package es.uc3m.tiw.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;

public class CursoDAOImpl implements CursoDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public CursoDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}

	@Override
	public Curso guardarCurso(Curso nuevoCurso) throws Exception{
		ut.begin();
		em.persist(nuevoCurso);
		ut.commit();
		return nuevoCurso;
		
	}
	
	@Override
	public Curso modificarCurso(Curso curso) throws Exception{
		ut.begin();
		em.merge(curso);
		ut.commit();
		return curso;
	}
	
	@Override
	public Collection<Curso> recuperarCursosPorDEstado(int estado) {
		return em.createQuery("select c from Curso c where c.TIPO_estado="+estado, Curso.class).getResultList();
	}
	
	@Override
	public void borrarCurso(Curso curso) throws Exception{
		ut.begin();
		em.remove(em.merge(curso));
		ut.commit();
	}
	
	@Override
	public Curso recuperarCursoPorPK(int pk){
		return em.find(Curso.class, pk);
	}
	
	@Override
	public Curso recuperarCursoPorNombre(String nombre) {
		return em.createQuery("select c from Curso c where c.DES_titulo='"+nombre+"'", Curso.class).getSingleResult();
	}
	
	public Collection<Curso> recuperarCursosPorPrecio(int precio) {
		return em.createQuery("select c from Curso c where c.precio_inicial="+precio, Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorDificultad(int dificultad) {
		return em.createQuery("select c from Curso c where c.TIPO_dificultad="+dificultad, Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorDescripcion(String descripcion) {
		return em.createQuery("select c from Curso c where c.DES_descripcion="+descripcion, Curso.class).getResultList();
	}
	
	public Collection<Curso> buscarTodosLosCursos(){
		return em.createQuery("select c from Curso c",Curso.class).getResultList();
	}

	public Collection<Curso> recuperarCursosPorPrecioYDificultad(int precio, int dificultad) {
		return em.createQuery("select c from Curso c where c.precio_inicial="+precio+"and c.TIPO_dificultad="+dificultad, Curso.class).getResultList();
	}

	public Collection<Curso> recuperarCursosPorPrecioYDescripcion(int precio, String descripcion) {
		return em.createQuery("select c from Curso c where c.precio_inicial="+precio+"and c.DES_descripcion="+descripcion, Curso.class).getResultList();
	}

	public Collection<Curso> recuperarCursosPorDificultadYDescripcion(int dificultad, String descripcion) {
		return em.createQuery("select c from Curso c where c.tematica="+descripcion+"and c.TIPO_dificultad="+dificultad, Curso.class).getResultList();
	}

	public Collection<Curso> recuperarCursosPorPrecioYDificultadYDescripcion(int dificultad, String descripcion, int precio) {
		return em.createQuery("select c from Curso c where c.precio_inicial="+precio+"and c.TIPO_dificultad="+dificultad+"and c.tematica="+descripcion, Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorPrecioYTematica(int precio, String tematica) {
		return em.createQuery("select c from Curso c where c.precio_inicial="+precio+" and c.tematica='"+tematica+"'", Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorDificultadYTematica(int dificultad, String tematica) {
		return em.createQuery("select c from Curso c where c.tematica='"+tematica+"' and c.TIPO_dificultad="+dificultad, Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorPrecioYDificultadYTematica(int dificultad, String tematica, int precio) {
		return em.createQuery("select c from Curso c where c.precio_inicial="+precio+" and c.TIPO_dificultad="+dificultad+" and c.tematica='"+tematica+"'", Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorDestacado(int destacado) {
		return em.createQuery("select c from Curso c where c.TIPO_destacado="+destacado, Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorTematica(String tematica) {
		return em.createQuery("select c from Curso c where c.tematica='"+tematica+"'", Curso.class).getResultList();
	}
	
	public Collection<Curso> recuperarCursosPorProfesor(int id_profe) { 
		return em.createQuery("select c from Curso c where c.profesor.ID_usuario="+id_profe, Curso.class).getResultList();
	}
}