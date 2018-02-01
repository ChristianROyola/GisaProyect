package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ups.edu.ec.gisab.modelo.Administrador;

@Stateless
public class AdministradorDao 
{
	@Inject
	private EntityManager em;

	public void insertAdmin(Administrador p) {
		System.out.println("Inserta"+p.getId()+p.getCorreo()+p.getContrasenia());
		em.persist(p);
	}
	
	public void updateAdmin(Administrador p){
		System.out.println("Actualiza"+p.getId()+p.getCorreo()+p.getContrasenia());
		em.merge(p);
	}
	
	public Administrador selectAdmin(int id) {
		Administrador p = em.find(Administrador.class, id);
		return p;
	}
	
	public void deletePersona(int id) {
		Administrador p = selectAdmin(id);
		em.remove(p);
	}
	
	public List<Administrador> listAdmin() {
		String sql = "SELECT a FROM Administrador a";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		System.out.println("Lista --> ");
		List<Administrador> listAdmin = query.getResultList();
		return listAdmin;
	}
	
	public void guardar (Administrador a) {
		Administrador aux = selectAdmin(a.getId());
		System.out.println("ID:" +a.getId());
		if(aux!=null){
			updateAdmin(a);
		}
		else {
			System.out.println("Grabando!");
			insertAdmin(a);	
		}
	}
	
	public List<Administrador> login(String user, String pass) 
	{
		System.out.println("USUARIO: "+user+", Pass: "+pass);
		String sql = "SELECT a FROM Administrador a WHERE a.correo = '"+user+"' AND a.contrasenia='"+pass+"'";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		List<Administrador> admin = query.getResultList();
		return admin;
	}
	
	public List<Administrador> admins() 
	{
		//System.out.println("USUARIO: "+user+", Pass: "+pass);
		String sql = "SELECT a FROM Administrador";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		List<Administrador> admin = query.getResultList();
		return admin;
	}
	
	public List<Administrador> verificaCorreo(String user)
	{
		String sql="SELECT a FROM Administrador a WHERE a.correo = '"+user+"'";
		TypedQuery<Administrador> query=em.createQuery(sql,Administrador.class);
		List<Administrador>admin=query.getResultList();
		return admin;
	}
	
	public List<Administrador> listAdminID(int id){
		String sql = "SELECT a FROM Administrador a WHERE a.id = '"+id+"'";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		List<Administrador>admin= query.getResultList();
		return admin;
	}
}
