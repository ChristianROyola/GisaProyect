package ups.edu.ec.gisab.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ups.edu.ec.gisab.modelo.Usuario;

@Stateless
public class UsuarioDao 
{
	@Inject
	private EntityManager em;

	public void insertUser(Usuario us) 
	{
		System.out.println("Inserta"+us.getId()+us.getCorreo()+us.getContrasenia());
		em.persist(us);
	}
	
	public void updateUser(Usuario u)
	{
		System.out.println("Actualiza"+u.getId()+u.getCorreo()+u.getContrasenia());
		em.merge(u);
	}
	
	public Usuario selectUser(int id)
	{
		Usuario u = em.find(Usuario.class, id);
		return u;
	}
	
	public void deleteUser(int id) 
	{
		Usuario u = selectUser(id);
		em.remove(u);
	}
	
	public List<Usuario> listUser() 
	{
		String sql = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
		System.out.println("Lista --> ");
		List<Usuario> listuser = query.getResultList();
		return listuser;
	}
	
	public void guardarUser (Usuario u) 
	{
		Usuario aux = selectUser(u.getId());
		System.out.println("ID:" +u.getId());
		if(aux!=null)
		{
			updateUser(u);
		}
		else 
		{
			System.out.println("Inserta Usuario!");
			insertUser(u);	
		}
	}
	
	public List<Usuario> login(String correo, String pass) 
	{
		System.out.println("USUARIO: "+correo+", Pass: "+pass);
		String sql = "SELECT u FROM Usuario u WHERE u.correo = '"+correo+"' AND u.contrasenia='"+pass+"'";
		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
		List<Usuario> user = query.getResultList();
		return user;
	}
	
	public List<Usuario> users() 
	{
		//System.out.println("USUARIO: "+user+", Pass: "+pass);
		String sql = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
		List<Usuario> user = query.getResultList();
		return user;
	}
	
	public List<Usuario> verificaCorreo(String user)
	{
		String sql="SELECT u FROM Usuario u WHERE u.correo = '"+user+"'";
		TypedQuery<Usuario> query=em.createQuery(sql,Usuario.class);
		List<Usuario>us=query.getResultList();
		return us;
	}
	
	public List<Usuario> listUserID(int id){
		String sql = "SELECT u FROM Usuario u WHERE u.id = '"+id+"'";
		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
		List<Usuario>user= query.getResultList();
		return user;
	}
}
