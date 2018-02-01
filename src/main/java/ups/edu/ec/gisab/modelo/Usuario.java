package ups.edu.ec.gisab.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "gisa_user")
public class Usuario 
{
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "user_nombre")
	@NotBlank(message = "Ingrese un nombre Porfavor")
	private String nombre;
	
	@Column(name = "user_apellido")
	@NotBlank(message = "Ingrese un apellido Porfavor")
	private String apellido;
	
	@Column(name = "user_correo")
	@NotBlank(message = "Ingrese un correo Porfavor") 
	private String correo;
	
	@Column(name = "user_perfil")
	private String perfil;
	
	@Column(name = "user_contrasenia")
	@Size(min = 4, message = "Debe ingresar un minimo de 4 caracteres")
	private String contrasenia;
	
	@Column(name = "user_estado")
	private String estado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", perfil="
				+ perfil + ", contrasenia=" + contrasenia + ", estado=" + estado + "]";
	}	
}
