package es.uc3m.tiw.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Table;

@Entity
@Table(name="cupon")
public class Cupon {

	@Id
 	@GeneratedValue(strategy = AUTO)
	private int ID_cupon;
	private String fecha_vto_cupon;
	@OneToOne
	private Usuario profesor;
	private int descuento;
	private int TIPO_cupon;
	@OneToOne
	private Curso curso;
	private String nombreCupon;
	
	
	
	public Cupon() {
		super();
	}

	
	public Cupon(String fecha_vto_cupon, Usuario profesor, int descuento,
			int tIPO_cupon, Curso curso, String nombreCupon) {
		super();
		this.fecha_vto_cupon = fecha_vto_cupon;
		this.profesor = profesor;
		this.descuento = descuento;
		TIPO_cupon = tIPO_cupon;
		this.curso = curso;
		this.nombreCupon = nombreCupon;
	}


	public String getNombreCupon() {
		return nombreCupon;
	}


	public void setNombreCupon(String nombreCupon) {
		this.nombreCupon = nombreCupon;
	}


	public int getID_cupon() {
		return ID_cupon;
	}
	public void setID_cupon(int iD_cupon) {
		ID_cupon = iD_cupon;
	}
	public String getFecha_vto_cupon() {
		return fecha_vto_cupon;
	}
	public void setFecha_vto_cupon(String fecha_vto_cupon) {
		this.fecha_vto_cupon = fecha_vto_cupon;
	}

	
	public int getTIPO_cupon() {
		return TIPO_cupon;
	}
	public void setTIPO_cupon(int TIPO_cupon) {
		TIPO_cupon = TIPO_cupon;
	}
	public Usuario getProfesor() {
		return profesor;
	}
	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	
}
