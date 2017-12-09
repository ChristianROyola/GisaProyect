package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ups.edu.ec.gisab.modelo.Administrador;

/**
 * Clase Objeto de acceso a datos para la entidad administrador
 * @author Chriss
 *
 */

@Stateless
public class AdministradorDao 
{
	@Inject
	private EntityManager em;

	/**
	 * Metodo para realizar el insert dentro de la BD.
	 * @param p
	 */
	public void insertAdmin(Administrador p) {
		System.out.println("Administrador ---- > ID: "+p.getId()+
				"Correo ----> "+p.getCorreo()+
				"Contrasenia ----> "+p.getContrasenia());
		em.persist(p);
	}
	
	/**
	 * Metodo update del administrador
	 * @param p
	 */
	public void updateAdmin(Administrador p){
		System.out.println("Update ------> "+p.getId()+p.getCorreo()+p.getContrasenia());
		em.merge(p);
	}
	
	/**
	 * Metodo elimindar administrador
	 * @param id
	 */
	public void deleteAdmin (int id) {
		Administrador p = selectAdmin(id);
		em.remove(p);
	}
	
	/**
	 * Metodo Buscar Admin
	 * @param id
	 * @return
	 */
	public Administrador selectAdmin(int id) {
		Administrador p = em.find(Administrador.class, id);
		return p;
	}

	/**
	 * Lista de los administradores
	 * @return
	 */
	
	public List<Administrador> listAdmin() {
		String sql = "SELECT a FROM Administrador a";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		System.out.println("ADMINS --> ");
		List<Administrador> listAdmin = query.getResultList();
		return listAdmin;
	}
	
	
	/**
	 * Almacena informacion administrador
	 * @param a
	 */
	public void guardar (Administrador a) {
		Administrador aux = selectAdmin(a.getId());
		if(aux!=null){
			updateAdmin(a);
		}
		else {
			System.out.println("INSERT!");
			insertAdmin(a);	
		}
	}
	
	/**
	 * Verificar datos para login
	 * @param user
	 * @param pass
	 * @return
	 */
	
	public List<Administrador> login(String user, String pass) {
		System.out.println("USUARIO: "+user+", Pass: "+pass);
		String sql = "SELECT a FROM Administrador a WHERE a.correo = '"+user+"' AND a.contrasenia='"+pass+"'";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		List<Administrador> admin = query.getResultList();
		return admin;
	}
	
	/**
	 * Validar Correo
	 * @param user
	 * @return
	 */
	
	public List<Administrador> verificaCorreo(String user)
	{
		String sql="SELECT a FROM Administrador a WHERE a.correo = '"+user+"'";
		TypedQuery<Administrador> query=em.createQuery(sql,Administrador.class);
		List<Administrador>admin=query.getResultList();
		return admin;
	}
	
	/**
	 * Lista Administradores con ID
	 * @param id
	 * @return
	 */
	
	public List<Administrador> listAdminID(int id){
		String sql = "SELECT a FROM Administrador a WHERE a.id = '"+id+"'";
		TypedQuery<Administrador> query = em.createQuery(sql, Administrador.class);
		List<Administrador>admin= query.getResultList();
		return admin;
	}
}
