package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ups.edu.ec.gisab.modelo.ContenidoTemporal;
import ups.edu.ec.gisab.modelo.Video;


@Stateless
public class VideoDAO {

	@Inject
	private EntityManager em;
	
	public Video read(int vid_id) {
		Video video = em.find(Video.class, vid_id);
		return video;
	}
	
	public List<Video> getVideos(){
		String jpql = "SELECT v FROM Video v";
		Query query = em.createQuery(jpql, Video.class);
		List<Video> videos = query.getResultList();
		
		return videos;
	}
	
	public void insert(Video v) {
		em.persist(v);
	}
	
	public void update(Video v) {
		em.merge(v);
	}
	
	public void insertarContenido(Video c) {
		em.persist(c);
	}
}
