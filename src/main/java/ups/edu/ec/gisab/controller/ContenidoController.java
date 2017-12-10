package ups.edu.ec.gisab.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ups.edu.ec.gisab.dao.CategoriaDao;
import ups.edu.ec.gisab.dao.ContenidoDao;
import ups.edu.ec.gisab.modelo.Categoria;
import ups.edu.ec.gisab.modelo.Contenido;

/**
 * Creacion de clase controller de Contenido
 * Validacion, listado y presentacion de contenido. 
 */
@ManagedBean
public class ContenidoController {

	@Inject
	private ContenidoDao contendao;

	@Inject
	private CategoriaDao catedao;

	private Contenido contenido;
	private Categoria categoria;

	private List<Contenido> lcontent;

	private List<Contenido> listadoFiltro;
	private String filtro;

	private int id;
	private int id3;

	@PostConstruct
	public void init() {
		contenido = new Contenido();
		loadContenido();
	}

	public int getId3() {
		return id3;
	}

	/**
	 * Lista de contenidos en base a un filtro de busqueda que s e
	 * pueda implementar 
	 * @return
	 */
	public List<Contenido> getListadoFiltrado() {
		return listadoFiltro;
	}

	public void setListadoFiltrado(List<Contenido> listadoFiltrado) {
		this.listadoFiltro = listadoFiltrado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void setId3(int id3) {
		this.id3 = id3;

	}

	public int getId() {
		return id;
	}

	public ContenidoDao getConetnDao() {
		return contendao;
	}

	public void setConetnDao(ContenidoDao contendao) {
		this.contendao = contendao;
	}


	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
		loadContenEditar(id);
		insertaCategoriaAdmin();
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public List<Contenido> getLContenido() {
		return lcontent;
	}

	public void setLContenido(List<Contenido> lcontent) {
		this.lcontent = lcontent;
	}

	public void loadContenido() {

		lcontent = contendao.listContenido();
	}
	
	public void loadCId(int id) {
		id3 = id;
	}

	public String loadContenEditar(int id) {

		contenido = contendao.leerContenido(id);
		return "crearContenido";
	}

	public String insertar() {
		contendao.saveContenido(contenido);
		loadContenido();
		return "listarContenidos";
	}

	public String actualizar() {
		contendao.actualizaContenido(contenido);
		return null;
	}

	public String leer(int id) {
		contenido = contendao.leerContenido(id);
		return null;
	}

	public String eliminar(int id) {
		contendao.borrarContenido(id);
		return "eliminarContenido";
	}

	public List<Contenido> listaContenidos() {

		lcontent = contendao.listContenido();
		return lcontent;
	}

	/**
	 * Insercion de categorias por parte del Admin
	 * en base a un id. 
	 * @return
	 */
	public String insertaCategoriaAdmin() {
		categoria = catedao.leerCategoria(id3);
		categoria.getContenidos().add(contenido);
		catedao.actualizarCategoria(categoria);
		return null;
	}

	/**
	 * Busqueda de una categoria en base al un parametro de filtro
	 * @return
	 */
	public String buscar() {
		listadoFiltro = contendao.getContenidosTitulo(filtro);
		return null;
	}
}
