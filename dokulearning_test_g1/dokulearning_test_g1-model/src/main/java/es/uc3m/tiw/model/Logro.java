package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="logro")
public class Logro {
	@Id
 	@GeneratedValue(strategy = AUTO)
	private int ID_logro;
	@OneToOne
	private Usuario usuario;
	@OneToOne
	private Curso curso;
	private int TIPO_nivel;
	
	public Logro() {
		super();

	}
	
	public Logro(int ID_logro, Usuario usuario, Curso curso, int TIPO_nivel) {
		super();
		this.ID_logro = ID_logro;
		this.usuario = usuario;
		this.curso = curso;
		this.TIPO_nivel = TIPO_nivel;
	}
	public int getIdLogro() {
		return ID_logro;
	}
	public void setIdLogro(int ID_logro) {
		this.ID_logro = ID_logro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public int getNivel() {
		return TIPO_nivel;
	}
	public void setNivel(int TIPO_nivel) {
		this.TIPO_nivel = TIPO_nivel;
	}
	
}