package ups.edu.ec.gisab.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="elgg_video")
@NamedQuery(name="Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable{ 
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable= false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer vid_id;
	
	
	@NotNull
	private String vid_name;
	
	
	@NotNull
	private String vid_descripcion;
	
	
	@NotNull
	private String vid_path;
	
	
//
//	private Categoria vid_categoria;
//	
	public Integer getVid_id() {
		return vid_id;
	}

	public void setVid_id(Integer vid_id) {
		this.vid_id = vid_id;
	}

	public String getVid_name() {
		return vid_name;
	}

	public void setVid_name(String vid_name) {
		this.vid_name = vid_name;
	}

	public String getVid_descripcion() {
		return vid_descripcion;
	}

	public void setVid_descripcion(String vid_descripcion) {
		this.vid_descripcion = vid_descripcion;
	}

	public String getVid_path() {
		System.out.println("----------"+vid_path);
		return vid_path;
	}

	public void setVid_path(String vid_path) {
		this.vid_path = vid_path;
	}


//	public Categoria getVid_categoria() {
//		return vid_categoria;
//	}
//
//	public void setVid_categoria(Categoria vid_categoria) {
//		this.vid_categoria = vid_categoria;
//	}

	@Override
	public String toString() {
		return "Video [vid_id=" + vid_id + ", vid_name=" + vid_name + ", vid_descripcion=" + vid_descripcion
				+ ", vid_path=" + vid_path + ", vid_categoria=" + "]";
	}

	
	
	
}
