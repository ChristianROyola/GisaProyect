package ups.edu.ec.gisab.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Clase Pojo para la persistencia de
 * los datos del administrador 
 * (Nombre a manejar de tabla Administrador: elgg_admin )
 * @author Chriss
 *
 */
@Entity
@Table(name = "elgg_admin")
public class Administrador 
{
	/**
	 * Validacion de campos 
	 * para registro.
	 */
	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Generacion de autoincrementable
	private int id;
	
	@Column(name = "admin_nombre")
	@NotBlank(message = "Ingrese su Porfavor")
	private String nombre;

	@Column(name = "admin_apellido")
	@NotBlank(message = "Ingrese su apellido ")
	private String apellido;
		
	@Column(name = "admin_correo")
	@NotBlank(message = "Ingrese un correo Porfavor") 
	private String correo;
	
	@Column(name = "admin_perfil")
	private String perfil;

	@Column(name = "admin_contrasenia")
	@Size(min = 4, message = "Debe ingresar un minimo de 6 caracteres")
	private String contrasenia;

	@Column(name = "admin_estado")
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
		return "Administrador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", perfil=" + perfil + ", contrasenia=" + contrasenia + ", estado=" + estado + "]";
	}
}
