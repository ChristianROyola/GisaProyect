package ups.edu.ec.gisab.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ups.edu.ec.gisab.dao.AdministradorDao;
import ups.edu.ec.gisab.dao.UsuarioDao;
import ups.edu.ec.gisab.modelo.Administrador;
import ups.edu.ec.gisab.modelo.Usuario;

@Path("/session")
public class LoginServices 
{
	@Inject
	private UsuarioDao dao;
    
    @GET
    @Path("/login")
    @Produces("application/json")   
    
    public List<Usuario> getAdmin(@QueryParam("user") String email, @QueryParam("password") String pass)
    {
    	RegistroRest res = new RegistroRest();
    	List<Usuario> admin=null;
    	try
        {
    		admin = dao.login(email, pass);
        	res.setId(1);
            res.setDescripcion("Login Exitoso!!!");
        }
    	 catch(Exception e)
        {
    		 res.setId(0);
             res.setDescripcion("Error al Ingresar!!!");
        }
    	return admin;
    }
    
    /*
     * @POST
	@Path("/guardar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta saveCategoria(Categoria cat) {
		Respuesta resp = new Respuesta();
		try {
			dao.insert(cat);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}
     */
    
    @POST
    @Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
    public RegistroRest register(Usuario admin)
    {
    	
    	RegistroRest res = new RegistroRest();
        try
        {
        	dao.guardarUser(admin);
            res.setId(1);
            res.setDescripcion("Registrado con Exito!!!");
        }
        catch(Exception e)
        {
            res.setId(0);
            res.setDescripcion("Error al Registrar!!!");
        }
        
        return res;
    }  
    
    @GET
	@Path("/admins")
	@Produces("application/json")
	public List<AdminTemp> getAdmins()
    {
		List<Usuario> admins = dao.users();
		List<AdminTemp> ads = new ArrayList<>();

		for(Usuario ad : admins) 
		{	
			AdminTemp temp = new AdminTemp();
			temp.setId(ad.getId());
			temp.setNombre(ad.getNombre());
			temp.setApellido(ad.getApellido());
			temp.setCorreo(ad.getCorreo());
			temp.setEstado(ad.getEstado());
			temp.setPerfil(ad.getPerfil());
			
			ads.add(temp);
		}
		return ads;
	}
    
}


