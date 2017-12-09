package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="elgg_Content")
public class Contenido {
	@Id
	@Column(name="content_Codigo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	
	@Column(name="content_Titulo")
	@NotNull 
	@Size(min=5,max=50)
	private String titulo;
	
	@Column(name="eve_Descripcion")
	@NotNull 
	@Size(min=5,max=600)
	private String descripcion;
	
	@Column(name="even_video")
	private byte[] video;
	
	@ManyToOne
	private Categoria categoria;
		
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getVideo() {
		return video;
	}

	public void setFoto(byte[] video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "Contenido [codigo=" + codigo + ", titulo=" + titulo + ", descripcion=" + descripcion + ", conten="
				+ Arrays.toString(video) + ", categoria=" + categoria + "]";
	}		
}

}
