package ups.edu.ec.gisab.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ups.edu.ec.gisab.dao.CategoriaDao;
import ups.edu.ec.gisab.dao.ContenidoDao;
import ups.edu.ec.gisab.modelo.Categoria;
import ups.edu.ec.gisab.modelo.Contenido;
import ups.edu.ec.gisab.modelo.ContenidoTemporal;


@ManagedBean
public class ContenidoController {

	@Inject
	private ContenidoDao contendao;

	@Inject
	private CategoriaDao catedao;

	/*
	 * Declaracion de variables
	 */
	private ContenidoTemporal contenido;
	private Categoria categoria;

	private List<ContenidoTemporal> lcontent;
	//private List<Contenido> leventocercano;
	//private List<Contenido> leventofecha;

	// Busqueda de locales
	private List<Contenido> listadoFiltro;
	private String filtro;

	private int id;
	private int id2;
	private int id3;

	@PostConstruct
	public void init() {
		contenido = new ContenidoTemporal();
		loadContenido();
	}

	public int getId3() {
		return id3;
	}

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

	public void setConetnDao(ContenidoDao evendao) {
		this.contendao = evendao;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	////////////// Fin Getter and Setter///////////////

	/*
	 * RECUPERAR ID PARA LA NAVEGACION DE LOCALES Y EVENTOS Sett ID
	 */
	public void setId(int id) {
		this.id = id;
		//loadContenEditar(id);
		loadID(id);
		//insertarEventoLocalGloba();
		loadCId(id);// agregado
		///// agregar///
		//insertaCategoriaAdmin();

	}

	public ContenidoTemporal getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoTemporal evento) {
		this.contenido = evento;
	}

	public List<ContenidoTemporal> getLContenido() {
		return lcontent;
	}

	public void setLContenido(List<ContenidoTemporal> levento) {
		this.lcontent = levento;
	}

	public void loadContenido() {

		lcontent = contendao.getContenidos();
	}

	public void loadID(int id) {
		id2 = id;
	}
	
	public void loadCId(int id) {
		id3 = id;
	}

	/*
	 * Mantenimiento Controlladores del EventoController
	 */
/*
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
	}*/

	public String eliminar(int id) {
		contendao.borrarContenido(id);
		return "eliminarContenido";
	}

	public List<ContenidoTemporal> listaContenidos() {

		lcontent = contendao.getContenidos();
		return lcontent;
	}

	/*
	 * Metodo para Agregar un evento a un local
	 */

//	public String insertarEventoLocalGloba() {

	//	recupelocal = locdao.leerLocal(id2);
	//	recupelocal.getEvento().add(contenido);
	//	locdao.updateLocal(recupelocal);

//		return null;
	//}
/*
	public String insertaCategoriaAdmin() {
		categoria = catedao.leerCategoria(id3);
		categoria.getContenidos().add(contenido);
		catedao.actualizarCategoria(categoria);
		return null;

	}
*/
	public String buscar() {

		System.out.println("INGRESO AL METODO ==================");
		listadoFiltro = contendao.getContenidosTitulo(filtro);
		return null;
	}

}// fin clase EventoController
