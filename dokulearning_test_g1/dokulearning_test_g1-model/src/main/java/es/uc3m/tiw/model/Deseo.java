package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="deseo")
public class Deseo {
	
	@Id
 	@GeneratedValue(strategy = AUTO)
    int ID_deseo;
	private Usuario usuario;
	private Curso cursoDeseado;
	
	public Deseo(Usuario usuario, Curso cursoDeseado) {
		super();
		this.usuario = usuario;
		this.cursoDeseado = cursoDeseado;
	}

	public Deseo() {
		// TODO Auto-generated constructor stub
	}
	
	public int getID_deseo() {
		return ID_deseo;
	}

	public void setID_deseo(int iD_deseo) {
		ID_deseo = iD_deseo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCursoDeseado() {
		return cursoDeseado;
	}

	public void setCursoDeseado(Curso cursoDeseado) {
		this.cursoDeseado = cursoDeseado;
	}
	
}