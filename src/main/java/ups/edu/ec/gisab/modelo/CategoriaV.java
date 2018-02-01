package ups.edu.ec.gisab.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "elgg_Categoria")
public class CategoriaV {

	@Id
	@Column(name = "cat_id")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "cat_nombre")
	@NotBlank(message = "Ingrese la categoria")
	@Size(min = 3, max = 30)
	private String nombre;

	@Column(name = "cat_descripcion")
	@NotNull
	private String descipcion;

	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "vid_cat_id", referencedColumnName = "cat_id")
	private List<Video> videos;

	
	
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

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descipcion=" + descipcion + ", videos=" + videos + "]";
	}
//
//	public void addVideo(Video video) {
//		if (videos == null) {
//			videos = new ArrayList<>();
//		}
//		videos.add(video);
//	}
}
