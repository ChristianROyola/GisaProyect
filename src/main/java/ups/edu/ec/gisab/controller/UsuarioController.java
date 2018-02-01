package ups.edu.ec.gisab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotBlank;

import ups.edu.ec.gisab.dao.UsuarioDao;
import ups.edu.ec.gisab.modelo.Usuario;
import ups.edu.ec.gisab.utilidades.SessionUtils;

@ManagedBean
@SessionScoped
public class UsuarioController 
{
	/**
	 * Variable para la validacion de correo
	 */
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	//private Usuario usuario = null;
	
	private int id;
	private String pactual;
	private int idEditUser;

	@NotBlank(message = "Ingrese sus Credenciales")
	private String contrasenia;
	private String conincidencia;
	private String Loginexiste;
	
	private String nusuario;

	private int idrecuprerar; // agregaUser

	private List<Usuario> ListUserID;

	@Inject
	private UsuarioDao pdao;

	private List<Usuario> lisuser;

	private Usuario myUser = null;

	@PostConstruct
	public void init() 
	{
		myUser = new Usuario();
		lisuser = lisuser();
		ListUserID = new ArrayList<Usuario>();
	}

	public String getNusuario() {
		return nusuario;
	}

	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}

	public int getIdrecuprerar() {
		return idrecuprerar;
	}

	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		loadid(idrecuprerar);
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) 
	{
		this.contrasenia = contrasenia;
	}

	public String getConincidencia() 
	{
		return conincidencia;
	}

	public void setConincidencia(String conincidencia) 
	{
		this.conincidencia = conincidencia;
	}

	public String getLoginexiste() 
	{
		return Loginexiste;
	}

	public void setLoginexiste(String loginexiste) 
	{
		Loginexiste = loginexiste;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String redirectmainAdmin() {
		return "mainAmin.xhtml";
	}

	public int getIdEditUser() {
		return idEditUser;
	}

	public void setIdEditUser(int idEditUser) {
		this.idEditUser = idEditUser;
	}

	public List<Usuario> getListUserID() {
		return ListUserID;
	}

	public void setListUserID(List<Usuario> listUserID) {
		ListUserID = listUserID;
	}

	public List<Usuario> getLisuser() {
		return lisuser;
	}

	public void setLisuser(List<Usuario> lisuser) {
		this.lisuser = lisuser;
	}

	public Usuario getMyUser() {
		return myUser;
	}

	public void setMyUser(Usuario myUser) {
		this.myUser = myUser;
	}

	/**
	 * Creacion del Usuario siempre que sus credenciales sean correctas
	 */

	public void crear()
	{
		if (coincidirContrasenia() == true) 
		{
			if (validarCorreo() == true) 
			{
				myUser.setPerfil("User");
				myUser.setEstado("A");
				pdao.guardarUser(myUser);
				inicializar();
				init();
				this.conincidencia = "Registro Realizado con Exito...!";
			} 
			else 
			{
				this.conincidencia = "Correo incorrecto";
			}
		} 
		else 
		{
			this.conincidencia = "Ingrese las mismas contrasenias";
		}
	}

	/**
	 * Validacion de contraseñas similares 
	 * @return True/Flase 
	 */

	public boolean coincidirContrasenia() 
	{
		if (myUser.getContrasenia().equals(this.contrasenia)) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

	/**
	 * Validacion de Correo Electronico del Usuario
	 * @return matcher
	 */
	
	public boolean validarCorreo() 
	{
		String email = myUser.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Inicializa los valores del JSF a blanco
	 */

	public void inicializar() 
	{
		myUser.setId(0);
		myUser.setApellido("");
		myUser.setNombre("");
		myUser.setContrasenia("");
	}

	/**
	 * Metodo que devuelve una lista de usuarios
	 * @return listadoUsuarios
	 */
	public List<Usuario> lisuser() 
	{
		lisuser = pdao.listUser();
		return lisuser;
	}

	/**
	 * Modifica los datos del usuario
	 * @return Vista Main/Inicio.xhtml
	 */

	public String modificar() 
	{
		try 
		{			
			if (myUser.getPerfil().equals("User")) 
			{
				myUser.setContrasenia(pactual);
				pdao.updateUser(myUser);
				System.out.println("Perfil de Usuario -> "+myUser.getPerfil());
				return "userUpdate"; //->> CAMBIAR VISTAAAA
			} 
			return null;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Redirecciona a la creacion de un Usuario nuevo
	 * @param id Recibe como parametro el ID del usuario nuevo
	 * @return Vista CrearUser.xhtml
	 */
	
	public String leer(int id) 
	{
		myUser = pdao.selectUser(id);
		return "crearUser"; // ->>> CAMBIAR VISTAAAA 
	}

	/**
	 * Metodo para la eliminacion de un Usuario
	 * @param id 
	 * @return eliminaUser.xhtml 
	 */
	
	public String eliminar(int id) 
	{
		pdao.deleteUser(id);
		System.out.println("Usuario Eliminado ..:" + myUser);
		return "eliminaUser";  //-->>> CAMBIAR VISTA
	}

	/**
	 * Metodo para cargar datos de los usuarios
	 * @param id 
	 * @return vista recuperaInfo.xhtml
	 */
	public String loadDatosEditar(int id) 
	{
		System.out.println("Usuario a recuperar -> " + id);
		myUser = pdao.selectUser(id);
		pactual = myUser.getContrasenia();
		if (myUser.getPerfil().equals("User")) 
		{
			return "recuperaInfo";
		} 
		return null;
	}

	/**
	 * Session HTTP  para FacesContext 
	 * Acceso a JSF/HTTP
	 */

	public void iniciarSesion()
	{
		if (pdao.login(myUser.getCorreo(), myUser.getContrasenia()).size() != 0)
		{
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", pdao.login(myUser.getCorreo(), myUser.getContrasenia()).get(0).getCorreo());
			session.setAttribute("perfil", pdao.login(myUser.getCorreo(), myUser.getContrasenia()).get(0).getPerfil());
			session.setAttribute("estado", pdao.login(myUser.getCorreo(), myUser.getContrasenia()).get(0).getEstado());
			this.Loginexiste = " ";
			FacesContext contex = FacesContext.getCurrentInstance();
			
			if (pdao.login(myUser.getCorreo(), myUser.getContrasenia()).get(0).getPerfil().equals("User")) 
			{
				try 
				{
					contex.getExternalContext().redirect("actividad.xhtml"); //PaginaPrincipal User Mostrar Contenido
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		myUser.setCorreo("");
		myUser.setContrasenia("");
		this.Loginexiste = "El usuario o la contrasenia son incorrectos";
	}

	/**
	 * Carga de datos de usuario por SessionHTTP
	 */
	
	public void cargarDatosUsuario()
	{
		myUser = new Usuario();
		HttpSession session = SessionUtils.getSession();
		String nus = (String) session.getAttribute("username");
		System.out.println("Usuario ---->> " + nus);
		
		try {
			if (pdao.verificaCorreo(nus).size() != 0) 
			{
				List<Usuario> lusuario = new ArrayList<Usuario>();
				lusuario = pdao.verificaCorreo(nus);
				myUser = lusuario.get(0);
				System.out.println("Email --->: " + myUser.getCorreo());
			} 
			else 
			{
				FacesContext contex = FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("Error al cargar");
			e.printStackTrace();
		}

	}

	/**
	 * Metodo eliminacion por context
	 * @return vista index.xhtml
	 */
	public String cerrarSesion()
	{
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index.xhtml";
	}

	/**
	 * Metodo que permite el redireccionamiento a pagina determinada,
	 * en base al rol de session.
	 */
	
	public void verificaSesion() 
	{
		HttpSession session = SessionUtils.getSession();
		String nusv = (String) session.getAttribute("username");
		if (nusv != null) 
		{
			FacesContext contex = FacesContext.getCurrentInstance(); //Concreta una session
			try 
			{
				if (myUser.getPerfil().equals("User")) 
				{
					contex.getExternalContext().redirect("mainUser.html"); //--->> Cambiar Vista 
				} 
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String consultaUserId()
	{
		System.out.println("ID: " + idrecuprerar + " " + "ENTRA");
		ListUserID = pdao.listUserID(idrecuprerar);
		for (Usuario p : ListUserID) 
		{
			System.out.println(p.getId());
			nusuario = p.getNombre();
		}
		return null;
	}

	public void loadidUser(int id)
	{
		idEditUser = id;
	}

	public void loadid(int id) 
	{
		idrecuprerar = id;
	}
}
