package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="matricula")
public class Matricula {
	@Id
 	@GeneratedValue(strategy = AUTO)
	private int ID_matricula;
	@OneToOne
	private Usuario alumno;
	@OneToOne
	private Curso curso;
	private int precio_pagado;
	private double beneficioProfe;
	private double beneficioPortal;
	
	

	public double getBeneficioPortal() {
		return beneficioPortal;
	}

	public void setBeneficioPortal(double beneficioPortal) {
		this.beneficioPortal = beneficioPortal;
	}

	public Matricula() {
		super();

	}
	
	public Matricula(Usuario alumno, Curso curso, int precio_pagado) {
		super();
		this.ID_matricula = ID_matricula;
		this.alumno = alumno;
		this.curso = curso;
		this.precio_pagado = precio_pagado;
	}
	public int getIdMatricula() {
		return ID_matricula;
	}
	public void setIdMatricula(int ID_matricula) {
		this.ID_matricula = ID_matricula;
	}
	public Usuario getAlumno() {
		return alumno;
	}
	public void setAlumno(Usuario alumno) {
		this.alumno = alumno;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public int getPrecio_pagado() {
		return precio_pagado;
	}
	public void setPrecio_pagado(int precio_pagado) {
		this.precio_pagado = precio_pagado;
	}
	
	public double getBeneficioProfe() {
		return beneficioProfe;
	}

	public void setBeneficioProfe(double beneficioProfe) {
		this.beneficioProfe = beneficioProfe;
	}
	
}