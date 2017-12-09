package ups.edu.ec.gisab.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ups.edu.ec.gisab.dao.CategoriaDao;
import ups.edu.ec.gisab.modelo.Categoria;


/**
 * Entidad de validacion de categorias 
 * @author Chriss
 *
 */
@ManagedBean
public class CategoriaController {
	
	 
	@Inject
	private CategoriaDao catedao;
	
	private Categoria categoria = null;
	private List<Categoria> categorias;
	
	private int id;
		
	@PostConstruct
	public void init(){
		categoria=new Categoria();
		loadCategoria();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadCategoriaEditar(id);
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void loadCategoria(){	
		categorias = catedao.listCategoria();
	}
		
	public String loadCategoriaEditar(int id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		categoria = catedao.leerCategoria(id);
		return "listadoCategoriaAcciones";	
	}
	
	public String eliminaCategoria(int id){
		catedao.eliminarCategoria(id);
		return "listadoCategoriaAcciones";
	}
	
	/**
	 * Guardar categorias 
	 * @return
	 */
	
	public String guardar(){
		catedao.guardarCategoria(categoria);
		System.out.println(categoria);	

		loadCategoria();
		return "listadoCategoria";
	}
	
	/**
	 * Agrega categorias 
	 * @return
	 */
	public String agregaCategoria(){
		categoria.getContenidos().add(new Contenido());
		return null;
	}
}
