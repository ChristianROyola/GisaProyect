package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ups.edu.ec.gisab.modelo.Contenido;
import ups.edu.ec.gisab.modelo.ContenidoTemporal;

@Stateless
public class ContenidoDao {

	@Inject
	private EntityManager em;

	// Metodo para editar y guardar

	public void saveContenido(ContenidoTemporal e) {

		ContenidoTemporal auxe = leerContenido(e.getCodigo());
		if (auxe != null) {
			actualizaContenido(e);
		} else {
			insertarContenido(e);
		}
	}

	// ---METODOS CRUD
	// Crear Evento

	public void insertarContenido(ContenidoTemporal c) {
		em.persist(c);
	}

	// Metodo para Actualizar Evento
	public void actualizaContenido(ContenidoTemporal c) {
		System.out.println("Actualiza"+ c.getCodigo() + c.getTitulo());
		em.merge(c);
	}

	// Metodo para Leer Evento
	public ContenidoTemporal leerContenido(int id) {

		ContenidoTemporal e = em.find(ContenidoTemporal.class, id);
		return e;

	}

	// Metodo para Eliminar Evento
	public void borrarContenido(int id) {
		ContenidoTemporal e = leerContenido(id);
		em.remove(e);
	}

	// Metodo para Listar Eventos
	public List<Contenido> listContenido() 
	{
		String sql = "Select e from Contenido e";
		TypedQuery<Contenido> query = em.createQuery(sql, Contenido.class);
		List<Contenido> levento = query.getResultList();
		return levento;
	}

	/// Filtro de busqueda para los eventos...
	public List<Contenido> getContenidosTitulo(String filtro) {
		String sql = "SELECT c FROM Contenido c " + "WHERE titulo like ? ";

		Query q = em.createQuery(sql, Contenido.class);
		
		q.setParameter(1, "%" + filtro + "%");
		List<Contenido> content = q.getResultList();
		
		return content;

	}
	
	public List<ContenidoTemporal> getContenidos()
	{
		String sql = "Select e from ContenidoTemporal e";
		TypedQuery<ContenidoTemporal> query = em.createQuery(sql, ContenidoTemporal.class);
		List<ContenidoTemporal> listaContenidos = query.getResultList();
			
		return null;
	}

}