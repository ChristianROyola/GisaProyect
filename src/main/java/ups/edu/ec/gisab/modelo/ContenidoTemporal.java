package ups.edu.ec.gisab.modelo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ups.edu.ec.gisab.modelo.Categoria;

@Entity
@Table(name="elgg_contenidotemp")
public class ContenidoTemporal {

	@Id
	@Column(name="content_Codigo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	
	@Column(name="content_Titulo")
	@NotNull 
	@Size(min=5,max=50)
	private String titulo;
	
	@Column(name="content_Descripcion")
	@NotNull 
	@Size(min=5,max=600)
	private String descripcion;
	
	@Column(name="content_video")
	private String video;
			
	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "ContenidoTemporal [codigo=" + codigo + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", video=" + video + "]";
	}
	
/*
	public List<AsistenciaEvento> getAsistenciaEventos() {
		return AsistenciaEventos;
	}

	public void setAsistenciaEventos(List<AsistenciaEvento> asistenciaEventos) {
		AsistenciaEventos = asistenciaEventos;
	}
  
  	public List<SalonRecepcion> getSalones() {
		return salones;
	}

	public void setSalones(List<SalonRecepcion> salones) {
		this.salones = salones;
	}

*/
	

	/*@Override
	public String toString() {
		return "Evento [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto="
				+ Arrays.toString(foto) + ", costo=" + costo + ", fechaEvento=" + fechaEvento + "]";
	}*/
	
	

	
			
}
