package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="leccion")
public class Leccion {
	
	@Id
 	@GeneratedValue(strategy = AUTO)
	int ID_leccion;
	String titulo;
	String descripcion;
	String formato;
	Seccion seccion;
	Curso curso;
	public Leccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Leccion(String titulo, String des, String formato,
			Seccion Seccion, Curso curso) {
		super();
		
		this.titulo = titulo;
		this.descripcion = des;
		this.formato = formato;
		this.seccion = Seccion;
		this.curso = curso;
		
	}
	
	public int getID_leccion() {
		return ID_leccion;
	}
	public void setID_leccion(int iD_leccion) {
		ID_leccion = iD_leccion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String des) {
		this.descripcion = des;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	
}