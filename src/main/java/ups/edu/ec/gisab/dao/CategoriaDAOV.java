package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ups.edu.ec.gisab.modelo.CategoriaV;


@Stateless
public class CategoriaDAOV {

	@Inject
	private EntityManager em;
	
	public CategoriaV read(int vid_id) {
		CategoriaV categoria = em.find(CategoriaV.class, vid_id);
		return categoria;
	}
	
	public List<CategoriaV> getCategorias(){
		String jpql = "SELECT v FROM CategoriaV v";
		Query query = em.createQuery(jpql, CategoriaV.class);
		List<CategoriaV> categorias = query.getResultList();
		
		return categorias;
	}
	
	public void insert(CategoriaV v) {
		em.persist(v);
	}
	
	public void update(CategoriaV v) {
		em.merge(v);
	}
}
