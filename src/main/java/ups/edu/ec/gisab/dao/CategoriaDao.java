package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ups.edu.ec.gisab.modelo.Categoria;


@Stateless
public class CategoriaDao {

	@Inject
	private EntityManager em;

	/**
	 * Guardar las categorias en base a un id de la misma 
	 * @param c
	 */
	public void guardarCategoria(Categoria c) {
		Categoria aux = leerCategoria(c.getId());
		if (aux != null) {
			actualizarCategoria(c);
		} else {
			insetarCategoria(c);
		}
	}

	/**
	 * Insert de categorias
	 */
	public void insetarCategoria(Categoria c) {
		em.persist(c);
	}

	/**
	 * Update de categorias
	 * @param c
	 */
	public void actualizarCategoria(Categoria c) {
		em.merge(c);
	}

	/**
	 * Lectura de categorias
	 * @param id
	 * @return
	 */
	public Categoria leerCategoria(int id) {
		Categoria c = em.find(Categoria.class, id);
		return c;
	}

	public void eliminarCategoria(int id) {
		Categoria c = leerCategoria(id);
		em.remove(c);
	}

	/**
	 * Lista da Categorias mediante select DAO
	 * @return
	 */
	public List<Categoria> listCategoria() {
		Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> listado = query.getResultList();
		return listado;
	}
}
