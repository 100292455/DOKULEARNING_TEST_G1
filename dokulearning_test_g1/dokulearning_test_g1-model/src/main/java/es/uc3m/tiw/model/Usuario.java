package es.uc3m.tiw.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	
	@Id
 	@GeneratedValue(strategy = AUTO)
    int ID_usuario;
	private String nombre;
	private String apellido;
	private int sexo;
	private String email;
	private String descripcion;
	private String intereses;
	private String telefono;
	private String TIPO_medio_cobro;
	private int TIPO_usuario;
	private String clave;
//	@OneToMany
//	private List<Curso> cursos_deseados;
	@OneToMany
	private List<Curso> cursos_matriculados;
	
	public Usuario(String nombre, String apellido, int sexo,
			String email, String descripcion, String intereses, 
			String telefono, String TIPO_medio_cobro,
			int TIPO_usuario, String clave,
			List<Curso> cursos_matriculados) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.email = email;
		this.telefono = telefono;
		this.TIPO_medio_cobro = TIPO_medio_cobro;
		this.TIPO_usuario = TIPO_usuario;
		this.clave = clave;
		this.descripcion = descripcion;
		this.intereses = intereses;
		this.cursos_matriculados = cursos_matriculados;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Curso> getCursosMatriculados() {
		return cursos_matriculados;
	}

	public void setCursosMatriculados(List<Curso> Cursos_matriculados) {
		cursos_matriculados = Cursos_matriculados;
	}
	
	public int getID_usuario() {
		return ID_usuario;
	}

	public void setID_usuario(int iD_usuario) {
		ID_usuario = iD_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMedio_cobro() {
		return TIPO_medio_cobro;
	}

	public void setMedio_cobro(String medio_cobro) {
		this.TIPO_medio_cobro = medio_cobro;
	}

	public int getTipo_usuario() {
		return TIPO_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.TIPO_usuario = tipo_usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	
}