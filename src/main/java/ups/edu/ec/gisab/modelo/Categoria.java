package ups.edu.ec.gisab.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Creacion de entidad categoria de contenidos
 * @author Chriss
 *
 */
@Entity
@Table(name = "elgg_Categoria")
public class Categoria {

	@Id
	@Column(name = "cat_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
   
	@Column(name = "cat_nombre")
	@NotBlank(message = "Ingrese la categoria")
	@Size(min = 3, max = 30)
	private String nombre;

	@Column(name = "cat_descripcion")
	private String descipcion;

	/**
	 * Relacion con contenidos 
	 * Drop en cascada
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cont_cat_id", referencedColumnName = "cat_id")

	private List<Contenido> contenido;

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

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public List<Contenido> getContenidos() {
		return contenido;
	}

	public void setContenidos(List<Contenido> contenidos) {
		this.contenido = contenidos;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + ", contenido=" + contenido
				+ "]";
	}

	/**
	 * Ingreso de contenidos
	 * @param contenidos
	 */
	public void addContent(Contenido contenidos) {
		if (contenido == null) {
			contenido = new ArrayList<>();
			contenido.add(contenidos);
		}
	}
}
