package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="seccion")
public class Seccion {
	
	@Id
 	@GeneratedValue(strategy = AUTO)
	private int id_seccion;
	@ManyToOne
	Curso curso;
	String nombre;
	@OneToMany(cascade=CascadeType.ALL)
	private Collection<Leccion> lecciones;

	public Seccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seccion(int id_seccion, Curso curso, String nombre) {
		super();
		this.id_seccion = id_seccion;
		this.curso = curso;
		this.nombre = nombre;
	}

	public int getId_seccion() {
		return id_seccion;
	}
	public void setId_seccion(int id_seccion) {
		this.id_seccion = id_seccion;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Collection<Leccion> getLecciones() {
		return lecciones;
	}

	public void setLecciones(Collection<Leccion> lecciones) {
		this.lecciones = lecciones;
	}
	
}
