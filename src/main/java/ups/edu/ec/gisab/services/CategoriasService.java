package ups.edu.ec.gisab.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ups.edu.ec.gisab.dao.CategoriaDao;
import ups.edu.ec.gisab.modelo.Categoria;

@Path("/cat")
public class CategoriasService 
{
	@Inject
	private CategoriaDao dao;
	
	@GET
	@Path("/categorias")
	@Produces("application/json")
	public List<Categoria> getCategorias2() 
	{
		List<Categoria> categorias = dao.getCategorias2();		
		return categorias;
	}
}
