package ups.edu.ec.gisab.services;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ups.edu.ec.gisab.dao.AdministradorDao;
import ups.edu.ec.gisab.modelo.Administrador;

@WebService
public class CategoriasSuma 
{
	@Inject
	private AdministradorDao dao;
	
	@WebMethod
	public String saludo(String nombre)
	{
		return "hola "+nombre;
	}
	
	@WebMethod
	public int suma(int a, int b)
	{
		return a + b ;
	}

	@WebMethod
    public List<Administrador> getAdmin(String email, String pass)
    {
    	List<Administrador> admin = dao.login(email, pass);
		return admin;
    }
	
}
