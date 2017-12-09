package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ups.edu.ec.gisab.modelo.Contenido;

/**
 * Creacion de objeto de acceso a datos para Contenidos
 * @author 
 *
 */
@Stateless
public class ContenidoDao {

	@Inject
	private EntityManager em;
	
	/**
	 * Guardar de contenidos
	 * @param e
	 */
	public void saveContenido(Contenido e) {
		Contenido auxe = leerContenido(e.getCodigo());
		if (auxe != null) {
			actualizaContenido(e);
		} else {
			insertarContenido(e);
		}
	}

	/**
	 * Insercion de contenidos 
	 * @param c
	 */
	public void insertarContenido(Contenido c) {
		em.persist(c);
	}
	
	/**
	 * Actualizacion de contenidos 
	 * @param c
	 */
	public void actualizaContenido(Contenido c) {
		System.out.println("Actualiza"+ c.getCodigo() + c.getTitulo());
		em.merge(c);
	}

	/**
	 * Eliminacion de contenidos
	 * @param id
	 */
	
	public void borrarContenido(int id) {
		Contenido e = leerContenido(id);
		em.remove(e);
	}

	/**
	 * Lectura de contenidos
	 * @param id
	 * @return
	 */
	public Contenido leerContenido(int id) {
		Contenido e = em.find(Contenido.class, id);
		return e;
	}
		
	/**
	 * Metodo para la lista de los contenidos en base a sentencia select implementada en DAO
	 * @return
	 */
	
	public List<Contenido> listContenido() 
	{
		String sql = "Select e from Contenido e";
		TypedQuery<Contenido> query = em.createQuery(sql, Contenido.class);
		List<Contenido> lcontent = query.getResultList();
		return lcontent;
	}
	/**
	 * Metodo para listar los contenidos del titulo
	 * @return
	 */

	public List<Contenido> getContenidosTitulo(String filtro) {
		String sql = "SELECT c FROM Contenido c " + "WHERE titulo like ? ";
		Query q = em.createQuery(sql, Contenido.class);
		q.setParameter(1, "%" + filtro + "%");
		List<Contenido> content = q.getResultList();
		return content;
	}

}
