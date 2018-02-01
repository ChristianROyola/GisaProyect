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

import ups.edu.ec.gisab.dao.AdministradorDao;
import ups.edu.ec.gisab.modelo.Administrador;
import ups.edu.ec.gisab.utilidades.SessionUtils;

@ManagedBean
@SessionScoped
public class AdministradorController 
{
	/*
	 * Variable para la validacion de la cedula
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Administrador administrador = null;

	private int id;
	private String pactual;
	private int idEditAdmin;

	/*
	 * Definicion de variables para la validacion-coincidencia del numero de cedula
	 * ingresado
	 */
	@NotBlank(message = "Ingrese sus Credenciales")
	private String contrasenia;
	private String conincidencia;
	private String Loginexiste;

	/*
	 * Varibles donde almaceno los valores de la consulta maestro-detalles
	 */
	private String nusuario;

	private int idrecuprerar; // -----------agregado

	private List<Administrador> ListAdminID;

	@Inject
	private AdministradorDao pdao;

	private List<Administrador> ladmins;

	private Administrador myUser;

	@PostConstruct
	public void init() {
		administrador = new Administrador();
		ladmins = listaAdmin();
		ListAdminID = new ArrayList<Administrador>();
		// consultaLocalEventos();
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
		// consultaLocalEventos();
	}

	public List<Administrador> getListPerID() {
		return ListAdminID;
	}

	public void setListPerID(List<Administrador> listPerID) {
		ListAdminID = listPerID;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConincidencia() {
		return conincidencia;
	}

	public void setConincidencia(String conincidencia) {
		this.conincidencia = conincidencia;
	}

	public String getLoginexiste() {
		return Loginexiste;
	}

	public void setLoginexiste(String loginexiste) {
		Loginexiste = loginexiste;
	}

	public Administrador getAdmin() {
		return administrador;
	}

	public void setAdmin(Administrador admin) {
		this.administrador = admin;
	}

	public List<Administrador> getLadmins() {
		return ladmins;
	}

	public void setLadmins(List<Administrador> ladmins) {
		this.ladmins = ladmins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEditAdmin() {
		return idEditAdmin;
	}

	public void setIdEditAdmin(int idEditAdmin) {
		this.idEditAdmin = idEditAdmin;
	}

	public AdministradorDao getPdao() {
		return pdao;
	}

	public void setPdao(AdministradorDao pdao) {
		this.pdao = pdao;
	}

	public Administrador getMyUser() {
		return myUser;
	}

	public void setMyUser(Administrador myUser) {
		this.myUser = myUser;
	}

	public String redirectmainAdmin() {
		return "mainAmin.xhtml";
	}

	/*
	 * Creacion del objeto Persona condicinamiento segun las sentencias de
	 * validacion
	 */
	public void crear() {
		if (coincidirContrasenia() == true) {
			if (validarCorreo() == true) {
				administrador.setPerfil("ADMIN");
				administrador.setEstado("A");
				pdao.guardar(administrador);
				inicializar();
				init();
				this.conincidencia = "Grabado exitoso!";
			} else {
				this.conincidencia = "El formato del correo es incorrecto";
			}
		} else {
			this.conincidencia = "Ingrese las mismas contrasenias";
		}
	}

	/*
	 * Comparacion de los 2 campos referentes a la contrasenia,
	 * devolucion(true/false), segun sea la cedula valida o no valida
	 * respectivamente.
	 */
	public boolean coincidirContrasenia() {
		if (administrador.getContrasenia().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Metodo para la validacion de un correo electronico
	 */
	public boolean validarCorreo() {
		String email = administrador.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/*
	 * Setea las variable como vacias, ocupado al momento de haber creado el usuario
	 * y dejar los h:inputText del JSF en blanco
	 */
	public void inicializar() {
		administrador.setId(0);
		administrador.setApellido("");
		administrador.setNombre("");
		administrador.setContrasenia("");
	}

	/*
	 * Metodo listado, devuelve un objeto Listado de tipo Persona(Devuelve todas las
	 * personas)
	 */
	public List<Administrador> listaAdmin() {
		ladmins = pdao.listAdmin();
		return ladmins;
	}

	/*
	 * Modificacion de los objetos de tipo Persona(USUARIO/ADMIN)
	 */
	public String modificar() 
	{
		try 
		{
			System.out.println(administrador.getPerfil());
			
			if (myUser.getPerfil().equals("ADMIN"))
			{
				administrador.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :" + administrador.getId());
				System.out.println("ELSE IF ADMIN");
				pdao.updateAdmin(administrador);
				return "mainAdmin";

			} 
			else if (myUser.getPerfil().equals("ADMIN-SUPER")) 
			{
				administrador.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :" + administrador.getId());
				System.out.println("ELSE IF ADMIN");
				pdao.updateAdmin(administrador);
				return "pages-blank";

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

	/*
	 * Metodo leer, donde dirije a el archivo crearPersona, dado como parametro un
	 * Id
	 */
	public String leer(int id) {
		administrador = pdao.selectAdmin(id);
		return "crearAdmin";
	}

	/*
	 * Metodo eliminar, llama al metodo Delete de PersonaDAO, parametro Id, para
	 * eliminar un registro especifica
	 */
	public String eliminar(int id) {
		pdao.deletePersona(id);
		System.out.println("Eliminado admin ..:" + administrador);
		return "actualizar";
	}

	// Metodo para cargar Datos de una persona, pasado un parametro Id especifico,
	// navegacion hacia recuperarPersona
	public String loadDatosEditar(int id) 
	{
		System.out.println("Cargando...admin a Editar" + id);
		administrador = pdao.selectAdmin(id);
		pactual = administrador.getContrasenia();
		if (administrador.getPerfil().equals("USUARIO")) 
		{
			return "recuperaPersona";
		} else if (administrador.getPerfil().equals("ADMIN")) {
			System.out.println("LOAD DATOS ");
			return "editAdmin";
		}
		return null;
	}

	/*
	 * inicilizar una Sesion HTTP y establecimiento de parametros en session,
	 * FacesContext acceso tanto al contexto de JSF como HTTP
	 */
	public void iniciarSesion()
	{
		if (pdao.login(administrador.getCorreo(), administrador.getContrasenia()).size() != 0) {

			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username",
					pdao.login(administrador.getCorreo(), administrador.getContrasenia()).get(0).getCorreo());
			session.setAttribute("perfil",
					pdao.login(administrador.getCorreo(), administrador.getContrasenia()).get(0).getPerfil());
			session.setAttribute("estado",
					pdao.login(administrador.getCorreo(), administrador.getContrasenia()).get(0).getEstado());
			this.Loginexiste = " ";
			FacesContext contex = FacesContext.getCurrentInstance();
			if (pdao.login(administrador.getCorreo(), administrador.getContrasenia()).get(0).getPerfil()
					.equals("USUARIO")) {

				System.out.println("CONTEXTO USER");
				try {
					contex.getExternalContext().redirect("mainUser.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (pdao.login(administrador.getCorreo(), administrador.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN-SUPER")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("pages-blank.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (pdao.login(administrador.getCorreo(), administrador.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				System.out.println("CONTEXTO ADMINN");
				try {
					contex.getExternalContext().redirect("pages-blank.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		administrador.setCorreo("");
		administrador.setContrasenia("");
		this.Loginexiste = "El usuario o la contrasenia son incorrectos";
	}

	/*
	 * Cargar datos del usuario obtenidos en session(HTTP) y su respectiva
	 * validacion
	 */
	public void cargarDatosUsuario()
	{
		myUser = new Administrador();
		HttpSession session = SessionUtils.getSession();
		String nus = (String) session.getAttribute("username");
		System.out.println("USER " + nus);
		
		try {
			if (pdao.verificaCorreo(nus).size() != 0) 
			{
				List<Administrador> lusuario = new ArrayList<Administrador>();
				lusuario = pdao.verificaCorreo(nus);
				myUser = lusuario.get(0);
				System.out.println("MYUSER EMAIL: " + myUser.getCorreo());
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

	/*
	 * Metodo Utilizado para la eliminacion de una sesion HTTP, con su respectiva
	 * navegacion
	 */
	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index.xhtml";
	}

	/*
	 * Metodo Utilizado para la verificacion de la sesion establecida, con un
	 * respectivo contexto hacia la pagina de inicio
	 */
	
	public void verificaSesion() {
		HttpSession session = SessionUtils.getSession();
		String nusv = (String) session.getAttribute("username");
		if (nusv != null) {
			System.out.println("si tiene sesion");
			FacesContext contex = FacesContext.getCurrentInstance();
			try {
				if (myUser.getPerfil().equals("USUARIO")) {
					contex.getExternalContext().redirect("mainUser.html");
				} else if (myUser.getPerfil().equals("ADMIN")) {
					contex.getExternalContext().redirect("mainAdmin.html");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * OBTENCION DE LA LISTA MAESTRA (PERSONA)
	 */
	public String consultaLocalEventos() {

		System.out.println("ID: " + idrecuprerar + " " + "ENTRA");
		ListAdminID = pdao.listAdminID(idrecuprerar);
		for (Administrador p : ListAdminID) {
			System.out.println("CED====================================" + p.getId());
			nusuario = p.getNombre();

		}
		return null;
	}

	public void loadidUser(int id) {
		idEditAdmin = id;
	}

	public void loadid(int id) {
		idrecuprerar = id;
	}
}
