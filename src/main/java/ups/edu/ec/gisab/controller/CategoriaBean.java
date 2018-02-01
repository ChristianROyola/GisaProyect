package ups.edu.ec.gisab.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import ups.edu.ec.gisab.dao.CategoriaDAOV;
import ups.edu.ec.gisab.modelo.CategoriaV;


@ManagedBean
@ViewScoped
public class CategoriaBean {

	
	@Inject
	private CategoriaDAOV cdao;
	
	
	private CategoriaV categoria;
	
	private List<CategoriaV> categorias ;
	

	
	@PostConstruct
	private void init() {
		categoria =new CategoriaV();
		categorias =  cdao.getCategorias();
	}	
	
	
	public CategoriaV getCategoria() {
		return categoria;
	}

	public void setcategoria(CategoriaV categoria) {
		this.categoria = categoria;
	}

	public String doRead() {
		CategoriaV categoria= cdao.read(this.categoria.getId());
		System.out.println(categoria.toString());
		
		return null;
	}
	
	public String doList() {
		List<CategoriaV> categorias = cdao.getCategorias();
		for (CategoriaV categoria : categorias) {
			System.out.println(categoria.toString());
		}
		
		return null;
	}



	public List<CategoriaV> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaV> categorias) {
		this.categorias = categorias;
	}

	public void setCategoria(CategoriaV categoria) {
		this.categoria = categoria;
	}
	
	
	
}
